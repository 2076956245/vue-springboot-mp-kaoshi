package wjx.bgs.vuespringbootmpkaoshi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import wjx.bgs.vuespringbootmpkaoshi.pojo.Dept;

import java.util.List;

public interface DeptService  {
    //查询所有部门
    IPage<Dept> AllDept(IPage<Dept> iPage, String deptName);
    //删除
    void deleteById(Integer id);
    //添加
    void addDept(Dept dept);
    //修改
    void updateById(Dept dept);
}
