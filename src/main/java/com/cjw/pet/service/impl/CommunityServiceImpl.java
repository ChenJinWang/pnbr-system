package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.CategoryDao;
import com.cjw.pet.dao.CommunityDao;
import com.cjw.pet.dao.PetFavoriteDao;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.dto.response.TagsRowVo;
import com.cjw.pet.pojo.*;
import com.cjw.pet.service.CommunityService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.exception.ExceptionResult;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityDao communityDao;
    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;
    private final PetFavoriteDao petFavoriteDao;

    @Autowired
    private UserUtils userUtils;

    public CommunityServiceImpl(CommunityDao communityDao, PetFavoriteDao petFavoriteDao, CategoryDao categoryDao, ModelMapper modelMapper) {
        this.communityDao = communityDao;
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
        this.petFavoriteDao = petFavoriteDao;
    }

    @Override
    public Long addCommunity(Community community) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        community.setCreateTime(new Date());
        community.setUpdateTime(new Date());
        community.setAuditStatus("0");
        community.setCreateId(user.getId());
        if(!communityDao.save(community)) {
            throw new ExceptionResult("community","false",null,"新增宠物食品失败");
        }
        return community.getId();
    }

    @Override
    public Boolean updateCommunity(Community community) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        community.setUpdateTime(new Date());
        if(!communityDao.lambdaUpdate().eq(Community::getId,community.getId()).update(community)) {
            throw new ExceptionResult("community","false",null,"修改宠物食品失败");
        }
        return true;
    }

    @Override
    public PageList<PetRowVo> listCommunity(PetQuery query) {
        LambdaQueryChainWrapper<Community> lambdaQuery = communityDao.lambdaQuery();
        if (query.getCid()!=null) {
            lambdaQuery.eq(Community::getCid,query.getCid());
        }

        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(Community::getTitle,query.getTitle());
        }
//        List<OrderDetail> list = orderDetailDao.lambdaQuery().select(OrderDetail::getSkuId, OrderDetail::getNum).list();
        if (query.getCreateId()!=null) {
            lambdaQuery.eq(Community::getCreateId,query.getCreateId());
        }

        if (query.getAuditStatus()!=null) {
            lambdaQuery.eq(Community::getAuditStatus,query.getAuditStatus());
        }

        if(query.getType()!=null) {
            lambdaQuery.eq(Community::getType,query.getType());
        }

        lambdaQuery.orderByDesc(Community::getUpdateTime);
        lambdaQuery.eq(Community::getSaleable,true);
        Page<Community> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<Community> pets = page.getRecords();
        List<PetRowVo> petRowVos = new ArrayList<>();
        pets.forEach(community -> {
            Category category = categoryDao.getById(community.getCid());
            PetRowVo petRowVo = modelMapper.map(community,PetRowVo.class);
            petRowVo.setCategoryName(Objects.isNull(category)? "分类已删除":category.getName());
            petRowVos.add(petRowVo);
        });
        return PageList.of(petRowVos, page);
    }

    @Override
    public PageList<PetRowVo> backstageListCommunity(PetQuery query) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        return this.listCommunity(query);
    }

    @Override
    public List<TagsRowVo> listTagRowVo() {


        TagsRowVo tagRowVo = new TagsRowVo();
        tagRowVo.setStyle(0);
        tagRowVo.setTitle("新帖推荐");
        tagRowVo.setId(null);
        Page<Community> petPage = communityDao.lambdaQuery()
                .in(Community::getCid, 1, 2, 3, 4)
                .orderByDesc(Community::getCreateTime)
                .eq(Community::getAuditStatus,"1")
                .page(new Page<>(1, 3));
        tagRowVo.setCommunities(petPage.getRecords());

        TagsRowVo tagRowVo2 = new TagsRowVo();
        tagRowVo2.setStyle(1);
        tagRowVo2.setTitle("精选帖子");
        tagRowVo2.setId(2L);
        Page<Community> petPage2 = communityDao.lambdaQuery()
                .in(Community::getCid,  2)
                .eq(Community::getAuditStatus,"1")
                .page(new Page<>(1, 3));
        tagRowVo2.setCommunities(petPage2.getRecords());

        TagsRowVo tagRowVo3 = new TagsRowVo();
        tagRowVo3.setStyle(2);
        tagRowVo3.setTitle("热门帖子");
        tagRowVo3.setId(3L);
        Page<Community> petPage3 = communityDao.lambdaQuery()
                .in(Community::getCid, 1, 2, 3, 4)
                .eq(Community::getAuditStatus,"1")
                .page(new Page<>(1, 2));
        tagRowVo3.setCommunities(petPage3.getRecords());
        List<TagsRowVo> tagRowVos = new ArrayList<>();
        tagRowVos.add(tagRowVo);
        tagRowVos.add(tagRowVo2);
        tagRowVos.add(tagRowVo3);
        return tagRowVos;
    }

    @Override
    public Community getCommunityDetail(Long id) {
        return communityDao.getById(id);
    }

    @Override
    public Boolean updateAuditStatus(Long id, String auditStatus) {
        return communityDao.lambdaUpdate().set(Community::getAuditStatus,auditStatus).eq(Community::getId,id).update();
    }

    @Override
    public Boolean updateSaleable(Long id, Boolean saleable) {
        return communityDao.lambdaUpdate().set(Community::getSaleable,saleable).eq(Community::getId,id).update();
    }

    @Override
    public Boolean agency(Long id, String auditStatus) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Community community = communityDao.getById(id);
        if (Objects.isNull(community)) {
            throw new ExceptionResult("community","false",null,"商品不存在");
        }
        if (community.getType()==0) {
            throw new ExceptionResult("community","false",null,"非退回产品不可代理");
        }
        if (community.getBackgroundAgentId()==null) {
            return communityDao.lambdaUpdate().set(Community::getAuditStatus,auditStatus).set(Community::getBackgroundAgentId,user.getId()).eq(Community::getId,id).update();
        }else if (community.getBackgroundAgentId()>0&&!community.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("community","false",null,"该产品已被代理");
        }
        return communityDao.lambdaUpdate().set(Community::getAuditStatus,auditStatus).set(Community::getBackgroundAgentId,user.getId()).eq(Community::getId,id).update();
    }

    @Override
    public Boolean agencyUpdateSaleable(Long id, Boolean saleable) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Community community = communityDao.getById(id);
        if (Objects.isNull(community)) {
            throw new ExceptionResult("community","false",null,"商品不存在");
        }
        if (community.getType()==0) {
            throw new ExceptionResult("community","false",null,"非退回产品不可代理");
        }
        if (community.getBackgroundAgentId()==null) {
            communityDao.lambdaUpdate().set(Community::getSaleable,saleable).eq(Community::getId,id).update();
        }else if (community.getBackgroundAgentId()>0&&!community.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("community","false",null,"该产品已被代理");
        }
        return communityDao.lambdaUpdate().set(Community::getSaleable,saleable).eq(Community::getId,id).update();
    }
}
