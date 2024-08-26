package wjx.bgs.vuespringbootmpkaoshi.pojo;

import lombok.Data;

@Data
public class UserXXdept
{
    private Integer id;//编号
    private String userName;//用户名
    private String password;//密码
    private String trueName;//真实姓名
    private String roleName;//角色
    private Integer deptId;//所在部门id
    private String deptName;//工程姓名
}
