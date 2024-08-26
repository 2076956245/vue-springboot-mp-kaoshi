package wjx.bgs.vuespringbootmpkaoshi.service.Impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjx.bgs.vuespringbootmpkaoshi.mapper.UserMapper;
import wjx.bgs.vuespringbootmpkaoshi.mapper.UserxxDeptMapper;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;
import wjx.bgs.vuespringbootmpkaoshi.pojo.User;
import wjx.bgs.vuespringbootmpkaoshi.pojo.UserXXdept;
import wjx.bgs.vuespringbootmpkaoshi.service.UserService;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserxxDeptMapper userxxDeptMapper;
    //全查
    //如果title不为空 添加模糊查询条件
    //如果roleName不为空 添加模糊查询条件
    // 使用 MPJLambdaWrapper 进行联查

    @Override
    public IPage<UserXXdept> AllUser(IPage<UserXXdept> page1, String userName, Integer deptId) {
        /**
         * 初始化一个MPJLambdaWrapper实例，用于构建复杂的查询条件。
         * MPJLambdaWrapper是一个工具类，用于简化MyBatis的复杂查询构建过程。
         */
        MPJLambdaWrapper<User> lambdaWrapper = new MPJLambdaWrapper<>();
        /**
         * 配置查询条件，选择所有用户，并指定如何映射部门名称。
         * 这里的selectAll和selectAs方法用于定义查询的字段和字段之间的映射关系。
         * leftJoin方法用于建立用户表和部门表的左连接，根据部门ID进行关联。
         */
        lambdaWrapper
                .selectAll(User.class)
                .selectAs(Dept::getDeptName, UserXXdept::getDeptName)
                .leftJoin(Dept.class, Dept::getId, User::getDeptId);
        /**
         * 如果用户名不为空，添加一个LIKE查询条件，用于筛选用户名包含指定字符串的用户。
         * 这里的like方法用于添加一个模糊查询条件，提高查询的灵活性。
         */
        if (isNotBlank(userName)) {
            lambdaWrapper.like(User::getUserName, userName);
        }
        if (deptId!=null && deptId!=null){
            lambdaWrapper.eq(User::getDeptId, deptId);
        }
        /**
         * 如果角色名不为空，添加一个LIKE查询条件，用于筛选角色名包含指定字符串的用户。
         * 通过多次调用lambdaWrapper的方法，可以灵活地构建复杂的查询条件。
         */
      /*  if (isNotBlank(deptId)) {
            lambdaWrapper.like(User::getDeptId, deptId);
        }*/
        /**
         * 使用构建好的查询条件，执行分页查询操作。
         * 这里的selectJoinPage方法是用户Mapper接口中的一个方法，用于执行带连接的分页查询。
         * 它接收一个分页对象、一个类类型和一个查询条件对象作为参数，返回分页查询的结果。
         */
        return userMapper.selectJoinPage(page1, UserXXdept.class, lambdaWrapper);

    }
    //多选删除
    @Override
    public void removeByIds(List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
    }


    @Override
    public void addUser(User user) {
        //执行添加
        userMapper.insert(user);
    }

    @Override
    public User getById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void removeById(Integer id) {
        userMapper.deleteById(id);
    }


}