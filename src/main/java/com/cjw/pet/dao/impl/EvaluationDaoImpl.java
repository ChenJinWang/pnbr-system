package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.EvaluationDao;
import com.cjw.pet.mapper.EvaluationMapper;
import com.cjw.pet.pojo.Evaluation;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class EvaluationDaoImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationDao {
}
