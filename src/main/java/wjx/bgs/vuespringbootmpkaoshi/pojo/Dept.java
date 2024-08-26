package wjx.bgs.vuespringbootmpkaoshi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Dept {
    @TableId(type = IdType.AUTO)
    private Integer id;//工程编号
    private String deptName;//工程姓名
    private String remark;//备注
}
