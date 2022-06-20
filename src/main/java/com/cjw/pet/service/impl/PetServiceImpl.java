package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.CategoryDao;
import com.cjw.pet.dao.PetDao;
import com.cjw.pet.dao.PetFavoriteDao;
import com.cjw.pet.dto.response.TagRowVo;
import com.cjw.pet.pojo.*;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;
    private final PetFavoriteDao petFavoriteDao;

    @Autowired
    private UserUtils userUtils;

    public PetServiceImpl(PetDao petDao, PetFavoriteDao petFavoriteDao, CategoryDao categoryDao, ModelMapper modelMapper) {
        this.petDao = petDao;
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
        this.petFavoriteDao = petFavoriteDao;
    }

    @Override
    public Long addPet(Pet pet) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!"sjuser".equals(user.getRole())) {
            throw new ExceptionResult("user","false",null,"请联系管理员申请商家！");
        }
        pet.setCreateTime(new Date());
        pet.setUpdateTime(new Date());
        pet.setAuditStatus("0");
        pet.setCreateId(user.getId());
        if(!petDao.save(pet)) {
            throw new ExceptionResult("pet","false",null,"新增宠物食品失败");
        }
        return pet.getId();
    }

    @Override
    public Boolean updatePet(Pet pet) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        pet.setUpdateTime(new Date());
        if(!petDao.lambdaUpdate().eq(Pet::getId,pet.getId()).update(pet)) {
            throw new ExceptionResult("pet","false",null,"修改宠物食品失败");
        }
        return true;
    }

    @Override
    public PageList<PetRowVo> listPet(PetQuery query) {
        LambdaQueryChainWrapper<Pet> lambdaQuery = petDao.lambdaQuery();
        if (query.getCid()!=null) {
            lambdaQuery.eq(Pet::getCid,query.getCid());
        }

        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(Pet::getTitle,query.getTitle());
        }
//        List<OrderDetail> list = orderDetailDao.lambdaQuery().select(OrderDetail::getSkuId, OrderDetail::getNum).list();
        if (query.getCreateId()!=null) {
            lambdaQuery.eq(Pet::getCreateId,query.getCreateId());
        }

        if (query.getAuditStatus()!=null) {
            lambdaQuery.eq(Pet::getAuditStatus,query.getAuditStatus());
        }

        if(query.getType()!=null) {
            lambdaQuery.eq(Pet::getType,query.getType());
        }

        lambdaQuery.orderByDesc(Pet::getUpdateTime);
        lambdaQuery.eq(Pet::getSaleable,true);
        Page<Pet> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<Pet> pets = page.getRecords();
        List<PetRowVo> petRowVos = new ArrayList<>();
        pets.forEach(pet -> {
            Category category = categoryDao.getById(pet.getCid());
            PetRowVo petRowVo = modelMapper.map(pet,PetRowVo.class);
            petRowVo.setCategoryName(Objects.isNull(category)? "分类已删除":category.getName());
            petRowVos.add(petRowVo);
        });
        return PageList.of(petRowVos, page);
    }

    @Override
    public PageList<PetRowVo> backstageListPet(PetQuery query) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        return this.listPet(query);
    }

    @Override
    public List<TagRowVo> listTagRowVo() {

        TagRowVo tagRowVo = new TagRowVo();
        tagRowVo.setStyle(0);
        tagRowVo.setTitle("新品推荐");
        tagRowVo.setId(null);
        Page<Pet> petPage = petDao.lambdaQuery()
                .in(Pet::getCid, "0", "1", "2", "3", "4", "5", "6", "7", "8","9")
                .orderByDesc(Pet::getCreateTime)
                .eq(Pet::getSaleable,"1")
                .page(new Page<>(1, 3));
        tagRowVo.setProds(petPage.getRecords());

        TagRowVo tagRowVo2 = new TagRowVo();
        tagRowVo2.setStyle(1);
        tagRowVo2.setTitle("宠物食品");
        tagRowVo2.setId(2L);
        Page<Pet> petPage2 = petDao.lambdaQuery()
                .in(Pet::getCid, 1, 2, 3, 4)
                .eq(Pet::getSaleable,"1")
                .page(new Page<>(1, 3));
        tagRowVo2.setProds(petPage2.getRecords());

        TagRowVo tagRowVo3 = new TagRowVo();
        tagRowVo3.setStyle(2);
        tagRowVo3.setTitle("商城热卖");
        tagRowVo3.setId(3L);
        Page<Pet> petPage3 = petDao.lambdaQuery()
                .in(Pet::getCid, 3)
                .eq(Pet::getSaleable,"1")
                .page(new Page<>(1, 2));
        tagRowVo3.setProds(petPage3.getRecords());
        List<TagRowVo> tagRowVos = new ArrayList<>();
        tagRowVos.add(tagRowVo);
        tagRowVos.add(tagRowVo2);
        tagRowVos.add(tagRowVo3);
        return tagRowVos;
    }

    @Override
    public Pet getPetDetail(Long id) {
        return petDao.getById(id);
    }

    @Override
    public Boolean updateAuditStatus(Long id, String auditStatus) {
        return petDao.lambdaUpdate().set(Pet::getAuditStatus,auditStatus).eq(Pet::getId,id).update();
    }

    @Override
    public Boolean updateSaleable(Long id, Boolean saleable) {
        return petDao.lambdaUpdate().set(Pet::getSaleable,saleable).eq(Pet::getId,id).update();
    }

    @Override
    public Boolean agency(Long id, String auditStatus) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Pet pet = petDao.getById(id);
        if (Objects.isNull(pet)) {
            throw new ExceptionResult("pet","false",null,"商品不存在");
        }
        if (pet.getType()==0) {
            throw new ExceptionResult("pet","false",null,"非退回产品不可代理");
        }
        if (pet.getBackgroundAgentId()==null) {
            return petDao.lambdaUpdate().set(Pet::getAuditStatus,auditStatus).set(Pet::getBackgroundAgentId,user.getId()).eq(Pet::getId,id).update();
        }else if (pet.getBackgroundAgentId()>0&&!pet.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("pet","false",null,"该产品已被代理");
        }
        return petDao.lambdaUpdate().set(Pet::getAuditStatus,auditStatus).set(Pet::getBackgroundAgentId,user.getId()).eq(Pet::getId,id).update();
    }

    @Override
    public Boolean agencyUpdateSaleable(Long id, Boolean saleable) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Pet pet = petDao.getById(id);
        if (Objects.isNull(pet)) {
            throw new ExceptionResult("pet","false",null,"商品不存在");
        }
        if (pet.getBackgroundAgentId()==null) {
            petDao.lambdaUpdate().set(Pet::getSaleable,saleable).eq(Pet::getId,id).update();
        }else if (pet.getBackgroundAgentId()>0&&!pet.getBackgroundAgentId().equals(user.getId())) {
            throw new ExceptionResult("pet","false",null,"该产品已被代理");
        }
        return petDao.lambdaUpdate().set(Pet::getSaleable,saleable).eq(Pet::getId,id).update();
    }
}
