package wjx.bgs.vuespringbootmpkaoshi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;
import wjx.bgs.vuespringbootmpkaoshi.pojo.User;
import wjx.bgs.vuespringbootmpkaoshi.pojo.UserXXdept;

import java.util.List;

public interface UserService {
    // 全查
   
    //添加用户
    void addUser(User user);
    //根据id来获取用户列表
    User getById(Integer id);
    //更新
    void updateById(User user);
    //删除
    void removeById(Integer id);

    IPage<UserXXdept> AllUser(IPage<UserXXdept> page1, String userName, Integer deptId);
    //批量删除
    void removeByIds(List<Integer> ids);
}
