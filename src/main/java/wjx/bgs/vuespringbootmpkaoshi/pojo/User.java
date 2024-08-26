package wjx.bgs.vuespringbootmpkaoshi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;//编号
    private String userName;//用户名 
    private String password;//密码
    private String trueName;//真实姓名
    private String roleName;//角色
    private Integer deptId;//所在部门id

    /*private Dept dept; // 新增的字段，用于存储部门信息*/
}
