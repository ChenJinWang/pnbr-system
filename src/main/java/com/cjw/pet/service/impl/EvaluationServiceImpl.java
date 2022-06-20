package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.BackgroundUserDao;
import com.cjw.pet.dao.EvaluationDao;
import com.cjw.pet.dao.UserDao;
import com.cjw.pet.dto.request.EvaluationQuery;
import com.cjw.pet.dto.response.EvaluationRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.BackgroundUser;
import com.cjw.pet.pojo.Evaluation;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.EvaluationService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.pojo.*;
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
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationDao evaluationDao;
    private final UserDao userDao;
    private final BackgroundUserDao backgroundUserDao;
    private final ModelMapper modelMapper;
    @Autowired
    private UserUtils userUtils;

    public EvaluationServiceImpl(EvaluationDao evaluationDao, UserDao userDao, BackgroundUserDao backgroundUserDao, ModelMapper modelMapper) {
        this.evaluationDao = evaluationDao;
        this.userDao = userDao;
        this.backgroundUserDao = backgroundUserDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveEvaluation(Evaluation evaluation) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (evaluation.getParentId()>0) {
            Evaluation parenEvaluation = evaluationDao.getById(evaluation.getParentId());
            if (Objects.isNull(parenEvaluation)) {
                throw new ExceptionResult("Evaluation","false",null,"父评论不存在");
            }
            evaluation.setToUserId(parenEvaluation.getCreatorId());
            evaluation.setToUserType(parenEvaluation.getCreatorType());
        }
        evaluation.setCreatedTime(new Date());
        evaluation.setUpdatedTime(new Date());
        evaluation.setCreatorId(user.getId());
        evaluation.setCreatorType(user.getRole().equals("ybuser")?0L:1L);
        evaluationDao.save(evaluation);
        return evaluation.getId();
    }

    @Override
    public PageList<EvaluationRowVo> queryEvaluationList(EvaluationQuery query) {
        Page<Evaluation> page = evaluationDao
                .lambdaQuery()
                .eq(Evaluation::getPetId,query.getPetId())
                .eq(Evaluation::getParentId,0)
                .orderByDesc(Evaluation::getCreatedTime)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<EvaluationRowVo> evaluationRowVos = new ArrayList<>();
        page.getRecords().forEach(evaluation -> {
            EvaluationRowVo evaluationRowVo = modelMapper.map(evaluation,EvaluationRowVo.class);
            if (evaluation.getCreatorType()==0) {
                User creator = userDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    evaluationRowVo.setCommentator(creator.getUsername());
                    evaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }else {
                BackgroundUser creator = backgroundUserDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    evaluationRowVo.setCommentator(creator.getUsername());
                    evaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }
            EvaluationQuery query2 = new EvaluationQuery();
            query2.setPageNum(1);
            query2.setPageSize(3);
            query2.setParentId(evaluation.getId());
            PageList<EvaluationRowVo> subQueryEvaluationList = subQueryEvaluationList(query2);
            evaluationRowVo.setSubEvaluationRowVos(subQueryEvaluationList);
            evaluationRowVos.add(evaluationRowVo);
        });
        return PageList.of(evaluationRowVos, page);
    }

    @Override
    public PageList<EvaluationRowVo> subQueryEvaluationList(EvaluationQuery query) {
        Page<Evaluation> page = evaluationDao
                .lambdaQuery()
                .eq(Evaluation::getParentId,query.getParentId())
                .orderByDesc(Evaluation::getCreatedTime)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<EvaluationRowVo> evaluationRowVos = new ArrayList<>();
        page.getRecords().forEach(evaluation -> {
            EvaluationRowVo subEvaluationRowVo = modelMapper.map(evaluation,EvaluationRowVo.class);
            if (evaluation.getCreatorType()==0) {
                User creator = userDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    subEvaluationRowVo.setCommentator(creator.getUsername());
                    subEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }else {
                BackgroundUser creator = backgroundUserDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(creator)) {
                    subEvaluationRowVo.setCommentator(creator.getUsername());
                    subEvaluationRowVo.setCommentatorAvatarImage(creator.getAvatarImage());
                }
            }
            if (evaluation.getToUserType()==0) {
                User toUser = userDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(toUser)) {
                    subEvaluationRowVo.setToUserName(toUser.getUsername());
                    subEvaluationRowVo.setToAvatarImage(toUser.getAvatarImage());
                }
            }else {
                BackgroundUser toUser = backgroundUserDao.getById(evaluation.getCreatorId());
                if (!Objects.isNull(toUser)) {
                    subEvaluationRowVo.setToUserName(toUser.getUsername());
                    subEvaluationRowVo.setToAvatarImage(toUser.getAvatarImage());
                }
            }
            evaluationRowVos.add(subEvaluationRowVo);
        });
        return PageList.of(evaluationRowVos, page);
    }

    @Override
    public Integer countEvaluationByProdId(Long prodId) {
        return evaluationDao.lambdaQuery().eq(Evaluation::getPetId,prodId).count();
    }
}
