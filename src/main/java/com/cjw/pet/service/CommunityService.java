package com.cjw.pet.service;


import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.dto.response.TagsRowVo;
import com.cjw.pet.pojo.Community;
import com.cjw.pet.pojo.PageList;


import java.util.List;

/**
 * @author cjw
 */
public interface CommunityService {
    /**
     * 新增宠物食品
     * @param community 宠物食品
     * @return 宠物食品id
     */
    Long addCommunity(Community community);

    /**
     * 修改宠物食品
     * @param community 宠物食品
     * @return 结果
     */
    Boolean updateCommunity(Community community);

    /**
     * 分页查询
     * @param query 参数
     * @return 分页数据
     */
    PageList<PetRowVo> listCommunity(PetQuery query);

    /**
     * 加载后台商品列表
     * @param query 参数
     * @return 商品列表
     */
    PageList<PetRowVo> backstageListCommunity(PetQuery query);

    /**
     * 加载前台首页商品列表
     * @return 商品列表
     */
    List<TagsRowVo> listTagRowVo();

    /**
     * 查询商品详情
     * @param id id
     * @return 详情
     */
    Community getCommunityDetail(Long id);

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
}
