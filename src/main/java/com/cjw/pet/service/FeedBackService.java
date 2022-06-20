package com.cjw.pet.service;


import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.dto.response.TagRowVo;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.pojo.FeedBack;


import java.util.List;

/**
 * @author cjw
 */
public interface FeedBackService {
    /**
     * 新增宠物食品
     * @param feedBack 宠物食品
     * @return 宠物食品id
     */
    Long addFeedBack(FeedBack feedBack);

    /**
     * 修改宠物食品
     * @param feedBack 宠物食品
     * @return 结果
     */
    Boolean updateFeedBack(FeedBack feedBack);

    /**
     * 分页查询
     * @param query 参数
     * @return 分页数据
     */
    PageList<PetRowVo> listFeedBack(PetQuery query);

    /**
     * 加载后台商品列表
     * @param query 参数
     * @return 商品列表
     */
    PageList<PetRowVo> backstageListFeedBack(PetQuery query);

    /**
     * 加载前台首页商品列表
     * @return 商品列表
     */
    List<TagRowVo> listTagRowVo();

    /**
     * 查询商品详情
     * @param id id
     * @return 详情
     */
    FeedBack getFeedBackDetail(Long id);

    /**
     * 修改审核状态
     * @param id id
     * @param auditStatus 审核状态
     * @return 结果
     */
    Boolean updateAuditStatus(Long id,String auditStatus);


    /**
     * 修改上架下架
     * @param id id
     * @param saleable 上架下架
     * @return 结果
     */
    Boolean updateSaleable(Long id,Boolean saleable);

    /**
     * 代理商品
     * @param id
     * @param auditStatus
     * @return
     */
    Boolean agency(Long id,String auditStatus);

    /**
     * 修改上架下架
     * @param id id
     * @param saleable 上架下架
     * @return 结果
     */
    Boolean agencyUpdateSaleable(Long id,Boolean saleable);

    /**
     * 删除反馈
     * @param id 反馈id
     * @return 结果
     */
    Boolean deletedFeedBack(String id);
}
