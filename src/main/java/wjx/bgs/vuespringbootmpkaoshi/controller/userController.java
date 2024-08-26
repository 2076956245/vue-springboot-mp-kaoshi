package wjx.bgs.vuespringbootmpkaoshi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Result;
import wjx.bgs.vuespringbootmpkaoshi.pojo.User;
import wjx.bgs.vuespringbootmpkaoshi.pojo.UserXXdept;
import wjx.bgs.vuespringbootmpkaoshi.service.UserService;

import java.util.List;

//跨域
@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;
    //全查用户
    @GetMapping("AllUser")
        public IPage<UserXXdept> AllUser(
            @RequestParam(defaultValue = "1",value = "page") Integer page,
            @RequestParam(defaultValue = "10",value = "size") Integer size,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") Integer deptId
    ){
        IPage <UserXXdept> page1 = new Page<>(page, size);
        IPage <UserXXdept> userIPage = userService.AllUser(page1, userName, deptId);
        return userIPage;
    }
    //添加用户
    @PostMapping("addUser")
    public Result<User> addUser(@RequestBody User user){
        userService.addUser(user);
        return Result.success();
    }
    //根据id来获取用户列表
    @GetMapping("getUserById")
    public Result<User> getUserById(Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }
   //更新用户信息
    @PutMapping("updateUser")
    public Result<User> updateUser(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }
    //根据id来删除一条用户
    @DeleteMapping("deleteUser")
    public Result<User> deleteUser(Integer id){
        userService.removeById(id);
        return Result.success();
    }
    //批量删除
    @DeleteMapping("deleteUsers")
    public void deleteUsers(@RequestBody List<Integer> ids){
        userService.removeByIds(ids);
    }
    //批量删除
    @DeleteMapping("deleteUsers")
    public void deleteUsersS(@RequestBody List<Integer> ids){
        userService.removeByIds(ids);
    }

}
