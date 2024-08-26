package wjx.bgs.vuespringbootmpkaoshi.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjx.bgs.vuespringbootmpkaoshi.mapper.DeptMapper;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;
import wjx.bgs.vuespringbootmpkaoshi.pojo.User;
import wjx.bgs.vuespringbootmpkaoshi.pojo.UserXXdept;
import wjx.bgs.vuespringbootmpkaoshi.service.DeptService;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public IPage<Dept> AllDept(IPage<Dept> iPage, String deptName) {
        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        /**
         * 如果部门名称不为空，添加一个LIKE查询条件
         * 这里的like方法用于添加一个模糊查询条件，提高查询的灵活性。
         */
        if(deptName != null){
            lambdaQueryWrapper.like(Dept::getDeptName,deptName);
        }
        return deptMapper.selectPage(iPage,lambdaQueryWrapper);
    }

    @Override
    public void deleteById(Integer id) {
    /**
     * 根据id来删除一条部门信息
     * 如果User用户表中有关联，则不能删除
     * 提示：如果部门下有员工，则不能删除
     * 删除部门中的员工信息后才能删除部门表
     * */
    MPJLambdaWrapper<Dept> lambdaWrapper = new MPJLambdaWrapper<>();
    lambdaWrapper
            // 选择User表中的所有列
            .selectAll(User.class)
            // 左连接User表，连接条件是User表的dept_id等于Dept表的id
            .leftJoin(User.class, User::getDeptId, Dept::getId)
            // 添加过滤条件，确保只选择dept_id等于传入id的记录
            .eq(User::getDeptId, id);

    if (deptMapper.selectList(lambdaWrapper).size() > 0) {
        throw new RuntimeException("部门下有员工，不能删除，还有"+deptMapper.selectList(lambdaWrapper).size()+"个员工");
    }

    deptMapper.deleteById(id);
}

    @Override
    public void addDept(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public void updateById(Dept dept) {
        deptMapper.updateById(dept);
    }

}

