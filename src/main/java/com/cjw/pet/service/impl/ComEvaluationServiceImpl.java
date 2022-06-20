package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.BackgroundUserDao;
import com.cjw.pet.dao.ComEvaluationDao;
import com.cjw.pet.dao.EvaluationDao;
import com.cjw.pet.dao.UserDao;
import com.cjw.pet.dto.request.ComEvaluationQuery;
import com.cjw.pet.dto.request.EvaluationQuery;
import com.cjw.pet.dto.response.ComEvaluationRowVo;
import com.cjw.pet.dto.response.EvaluationRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.*;
import com.cjw.pet.service.ComEvaluationService;
import com.cjw.pet.service.EvaluationService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class ComEvaluationServiceImpl implements ComEvaluationService {

    private final ComEvaluationDao comEvaluationDao;
    private final UserDao userDao;
    private final BackgroundUserDao backgroundUserDao;
    private final ModelMapper modelMapper;
    @Autowired
    private UserUtils userUtils;

    public ComEvaluationServiceImpl(ComEvaluationDao comEvaluationDao, UserDao userDao, BackgroundUserDao backgroundUserDao, ModelMapper modelMapper) {
        this.comEvaluationDao = comEvaluationDao;
        this.userDao = userDao;
        this.backgroundUserDao = backgroundUserDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveEvaluation(ComEvaluation comEvaluation) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (comEvaluation.getParentId()>0) {
            ComEvaluation parenEvaluation = comEvaluationDao.getById(comEvaluation.getParentId());
            if (Objects.isNull(parenEvaluation)) {
                throw new ExceptionResult("Evaluation","false",null,"父评论不存在");
            }
            comEvaluation.setToUserId(parenEvaluation.getCreatorId());
            comEvaluation.setToUserType(parenEvaluation.getCreatorType());
        }
        comEvaluation.setCreatedTime(new Date());
        comEvaluation.setUpdatedTime(new Date());
        comEvaluation.setCreatorId(user.getId());
        comEvaluation.setCreatorType(user.getRole().equals("ybuser")?0L:1L);
        comEvaluationDao.save(comEvaluation);
        return comEvaluation.getId();
    }

    @Override
    public PageList<ComEvaluationRowVo> queryEvaluationList(ComEvaluationQuery query) {
        Page<ComEvaluation> page = comEvaluationDao
                .lambdaQuery()
                .eq(ComEvaluation::getPetId,query.getPetId())
                .eq(ComEvaluation::getParentId,0)
                .orderByDesc(ComEvaluation::getCreatedTime)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<ComEvaluationRowVo> comEvaluationRowVos = new ArrayList<>();
        page.getRecords().forEach(comEvaluation -> {
            ComEvaluationRowVo comEvaluationRowVo = modelMapper.map(comEvaluation,ComEvaluationRowVo.class);
            if (comEvaluation.getCreatorType()==0) {
                User creator = userDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    comEvaluationRowVo.setCommentator(creator.getUsername());
                    comEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }else {
                BackgroundUser creator = backgroundUserDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    comEvaluationRowVo.setCommentator(creator.getUsername());
                    comEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }
            ComEvaluationQuery query2 = new ComEvaluationQuery();
            query2.setPageNum(1);
            query2.setPageSize(100);
            query2.setParentId(comEvaluation.getId());
            PageList<ComEvaluationRowVo> subQueryEvaluationList = subQueryEvaluationList(query2);
            comEvaluationRowVo.setSubEvaluationRowVos(subQueryEvaluationList);
            comEvaluationRowVos.add(comEvaluationRowVo);
        });
        return PageList.of(comEvaluationRowVos, page);
    }

    @Override
    public PageList<ComEvaluationRowVo> subQueryEvaluationList(ComEvaluationQuery query) {
        Page<ComEvaluation> page = comEvaluationDao
                .lambdaQuery()
                .eq(ComEvaluation::getParentId,query.getParentId())
                .orderByDesc(ComEvaluation::getCreatedTime)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<ComEvaluationRowVo> comEvaluationRowVos = new ArrayList<>();
        page.getRecords().forEach(comEvaluation -> {
            ComEvaluationRowVo comEvaluationRowVo = modelMapper.map(comEvaluation,ComEvaluationRowVo.class);
            if (comEvaluation.getCreatorType()==0) {
                User creator = userDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    comEvaluationRowVo.setCommentator(creator.getUsername());
                    comEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }else {
                BackgroundUser creator = backgroundUserDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    comEvaluationRowVo.setCommentator(creator.getUsername());
                    comEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }
            if (comEvaluation.getToUserType()==0) {
                User toUser = userDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(toUser)) {
                    comEvaluationRowVo.setToUserName(toUser.getUsername());
                    comEvaluationRowVo.setToAvatarImage(toUser.getAvatarImage());
                }
            }else {
                BackgroundUser toUser = backgroundUserDao.getById(comEvaluation.getCreatorId());
                if (!Objects.isNull(toUser)) {
                    comEvaluationRowVo.setToUserName(toUser.getUsername());
                    comEvaluationRowVo.setToAvatarImage(toUser.getAvatarImage());
                }
            }
            comEvaluationRowVos.add(comEvaluationRowVo);
        });
        return PageList.of(comEvaluationRowVos, page);
    }

    @Override
    public Integer countEvaluationByProdId(Long prodId) {
        return comEvaluationDao.lambdaQuery().eq(ComEvaluation::getPetId,prodId).count();
    }
}
