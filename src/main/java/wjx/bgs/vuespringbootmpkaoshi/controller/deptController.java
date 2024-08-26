package wjx.bgs.vuespringbootmpkaoshi.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Result;
import wjx.bgs.vuespringbootmpkaoshi.service.DeptService;

import java.util.List;

@RestController
@RequestMapping("/dept")
@CrossOrigin
public class deptController {
    @Autowired
    private DeptService deptService;
    //分页模糊查
    @GetMapping("/AllDept")
    public IPage<Dept> AllDept(
            @RequestParam(defaultValue = "1", value = "page") Integer page,
            @RequestParam(defaultValue = "50", value = "size") Integer size,
            @RequestParam(defaultValue = "") String deptName
    ) {
        //创建分页对象
        IPage<Dept> iPage =new Page<>(page, size);
        //调用service
        IPage<Dept>deptIPage = deptService.AllDept(iPage, deptName);
        return deptIPage;
    }
    //添加部门
    @PostMapping("/addDept")
    public Result<Dept> addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }
    //删除部门 如果该部门有成员时提示不删除
    @DeleteMapping("/deleteDept")
    public Result<Dept> deleteDept(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }
    //修改部门
    @PutMapping("/updateDept")
    public Result<Dept> updateDept(@RequestBody Dept dept){
        deptService.updateById(dept);
        return Result.success();
    }
}
