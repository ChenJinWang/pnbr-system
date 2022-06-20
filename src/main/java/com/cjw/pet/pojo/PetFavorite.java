package com.cjw.pet.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 宠物食品 收藏
 * @author cjw
 */
@Data
@TableName("tb_pet_favorite")
public class PetFavorite {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 宠物食品的id
     */
    private Long favoriteId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 数量
     */
    private Integer num;
}
