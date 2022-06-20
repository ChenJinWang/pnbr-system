package com.cjw.pet.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 分类
 * @author cjw
 */
@Data
@TableName("tb_category")
public class Category {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
}
