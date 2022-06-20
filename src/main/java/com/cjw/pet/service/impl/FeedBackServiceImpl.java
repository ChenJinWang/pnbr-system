package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.CategoryDao;

import com.cjw.pet.dao.FeedBackDao;
import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.dto.response.TagRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.*;
import com.cjw.pet.service.FeedBackService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
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
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackDao feedBackDao;
    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    public FeedBackServiceImpl(FeedBackDao feedBackDao, CategoryDao categoryDao, ModelMapper modelMapper) {
        this.feedBackDao = feedBackDao;
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addFeedBack(FeedBack feedBack) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        feedBack.setCreateTime(new Date());
        feedBack.setUpdateTime(new Date());
        feedBack.setAuditStatus("0");
        feedBack.setCreateId(user.getId());
        if(!feedBackDao.save(feedBack)) {
            throw new ExceptionResult("pet","false",null,"新增宠物食品失败");
        }
        return feedBack.getId();
    }

    @Override
    public Boolean updateFeedBack(FeedBack feedBack) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        feedBack.setUpdateTime(new Date());
        if(!feedBackDao.lambdaUpdate().eq(FeedBack::getId,feedBack.getId()).update(feedBack)) {
            throw new ExceptionResult("feedback","false",null,"修改宠物食品失败");
        }
        return true;
    }

    @Override
    public PageList<PetRowVo> listFeedBack(PetQuery query) {
        LambdaQueryChainWrapper<FeedBack> lambdaQuery = feedBackDao.lambdaQuery();
        if (query.getCid()!=null) {
            lambdaQuery.eq(FeedBack::getCid,query.getCid());
        }

        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(FeedBack::getTitle,query.getTitle());
        }
//        List<OrderDetail> list = orderDetailDao.lambdaQuery().select(OrderDetail::getSkuId, OrderDetail::getNum).list();
        if (query.getCreateId()!=null) {
            lambdaQuery.eq(FeedBack::getCreateId,query.getCreateId());
        }

        if (query.getAuditStatus()!=null) {
            lambdaQuery.eq(FeedBack::getAuditStatus,query.getAuditStatus());
        }

        if(query.getType()!=null) {
            lambdaQuery.eq(FeedBack::getType,query.getType());
        }

        lambdaQuery.orderByDesc(FeedBack::getUpdateTime);
        lambdaQuery.eq(FeedBack::getSaleable,true);
        Page<FeedBack> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<FeedBack> feedBacks = page.getRecords();
        List<PetRowVo> petRowVos = new ArrayList<>();
        feedBacks.forEach(feedBack -> {
            Category category = categoryDao.getById(feedBack.getCid());
            PetRowVo petRowVo = modelMapper.map(feedBack,PetRowVo.class);
            petRowVo.setCategoryName(Objects.isNull(category)? "分类已删除":category.getName());
            petRowVos.add(petRowVo);
        });
        return PageList.of(petRowVos, page);
    }

    @Override
    public PageList<PetRowVo> backstageListFeedBack(PetQuery query) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        return this.listFeedBack(query);
    }

    @Override
    public List<TagRowVo> listTagRowVo() {
        TagRowVo tagRowVo = new TagRowVo();
        tagRowVo.setStyle(0);
        tagRowVo.setTitle("新品收藏");
        tagRowVo.setId(null);
        Page<FeedBack> petPage = feedBackDao.lambdaQuery()
                .in(FeedBack::getCid, 3, 4)
                .eq(FeedBack::getAuditStatus,"1")
                .page(new Page<>(1, 3));
        tagRowVo.setFeedBacks(petPage.getRecords());

        TagRowVo tagRowVo2 = new TagRowVo();
        tagRowVo2.setStyle(1);
        tagRowVo2.setTitle("日用粮");
        tagRowVo2.setId(2L);
        Page<FeedBack> petPage2 = feedBackDao.lambdaQuery()
                .in(FeedBack::getCid, 3, 4)
                .eq(FeedBack::getAuditStatus,"1")
                .page(new Page<>(1, 2));
        tagRowVo2.setFeedBacks(petPage2.getRecords());

        TagRowVo tagRowVo3 = new TagRowVo();
        tagRowVo3.setStyle(2);
        tagRowVo3.setTitle("保健品");
        tagRowVo3.setId(3L);
        Page<FeedBack> petPage3 = feedBackDao.lambdaQuery()
                .in(FeedBack::getCid, 2)
                .eq(FeedBack::getAuditStatus,"1")
                .page(new Page<>(1, 2));
        tagRowVo3.setFeedBacks(petPage3.getRecords());
        List<TagRowVo> tagRowVos = new ArrayList<>();
        tagRowVos.add(tagRowVo);
        tagRowVos.add(tagRowVo2);
        tagRowVos.add(tagRowVo3);
        return tagRowVos;
    }

    @Override
    public FeedBack getFeedBackDetail(Long id) {
        return feedBackDao.getById(id);
    }

    @Override
    public Boolean updateAuditStatus(Long id, String auditStatus) {
        return feedBackDao.lambdaUpdate().set(FeedBack::getAuditStatus,auditStatus).eq(FeedBack::getId,id).update();
    }

    @Override
    public Boolean updateSaleable(Long id, Boolean saleable) {
        return feedBackDao.lambdaUpdate().set(FeedBack::getSaleable,saleable).eq(FeedBack::getId,id).update();
    }

    @Override
    public Boolean agency(Long id, String auditStatus) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }

        FeedBack feedBack = feedBackDao.getById(id);
        if (Objects.isNull(feedBack)) {
            throw new ExceptionResult("feedBack","false",null,"商品不存在");
        }
        if (feedBack.getType()==0) {
            throw new ExceptionResult("feedBack","false",null,"非退回产品不可代理");
        }
        if (feedBack.getBackgroundAgentId()==null) {
            return feedBackDao.lambdaUpdate().set(FeedBack::getAuditStatus,auditStatus).set(FeedBack::getBackgroundAgentId,user.getId()).eq(FeedBack::getId,id).update();
        }else if (feedBack.getBackgroundAgentId()>0&&!feedBack.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("feedBack","false",null,"该产品已被代理");
        }
        return feedBackDao.lambdaUpdate().set(FeedBack::getAuditStatus,auditStatus).set(FeedBack::getBackgroundAgentId,user.getId()).eq(FeedBack::getId,id).update();
    }

    @Override
    public Boolean agencyUpdateSaleable(Long id, Boolean saleable) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        FeedBack feedBack = feedBackDao.getById(id);
        if (Objects.isNull(feedBack)) {
            throw new ExceptionResult("feedBack","false",null,"商品不存在");
        }
        if (feedBack.getType()==0) {
            throw new ExceptionResult("feedBack","false",null,"非退回产品不可代理");
        }
        if (feedBack.getBackgroundAgentId()==null) {
            feedBackDao.lambdaUpdate().set(FeedBack::getSaleable,saleable).eq(FeedBack::getId,id).update();
        }else if (feedBack.getBackgroundAgentId()>0&&!feedBack.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("feedBack","false",null,"该产品已被代理");
        }
        return feedBackDao.lambdaUpdate().set(FeedBack::getSaleable,saleable).eq(FeedBack::getId,id).update();
    }

    @Override
    public Boolean deletedFeedBack(String id) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        return feedBackDao.removeById(id);
    }
}
