package com.cjw.pet.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author cjw
 */
@Data
@TableName("tb_prod_evaluation")
public class Evaluation {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 商品id
     */
    private Long petId;
    /**
     * 父评论id
     */
    private Long parentId;
    /**
     * 被评论人
     */
    private Long toUserId;
    /**
     * 被评论人类型
     */
    private Long toUserType;
    /**
     * 评论者
     */
    private Long creatorId;
    /**
     * 评论人类型
     */
    private Long creatorType;
    /**
     * 评论时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 是否删除
     */
    private Long isDeleted;

}
