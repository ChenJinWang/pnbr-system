package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.ComEvaluationDao;
import com.cjw.pet.dao.EvaluationDao;
import com.cjw.pet.mapper.ComEvaluationMapper;
import com.cjw.pet.mapper.EvaluationMapper;
import com.cjw.pet.pojo.ComEvaluation;
import com.cjw.pet.pojo.Evaluation;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class ComEvaluationDaoImpl extends ServiceImpl<ComEvaluationMapper, ComEvaluation> implements ComEvaluationDao {
}
