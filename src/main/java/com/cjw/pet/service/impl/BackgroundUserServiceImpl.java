package com.cjw.pet.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.BackgroundUserDao;
import com.cjw.pet.mapper.BackgroundUserMapper;
import com.cjw.pet.pojo.BackgroundUser;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.BackgroundUserService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.exception.ExceptionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class BackgroundUserServiceImpl implements BackgroundUserService {

    private final BackgroundUserMapper userMapper;
    private final BackgroundUserDao userDao;

    @Autowired
    private UserUtils userUtils;

    public BackgroundUserServiceImpl(BackgroundUserMapper userMapper, BackgroundUserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @Override
    public Boolean register(BackgroundUser user) {
        if (!"cjw".equals(user.getKeyNum())) {
            throw new ExceptionResult("user","false",null,"注册失败，密钥错误");
        }
        if(userDao.lambdaQuery().eq(BackgroundUser::getPhone,user.getPhone()).count()>0) {
            throw new ExceptionResult("user","false",null,"注册失败，手机号码已存在");
        }
        user.setCreateTime(new Date());
        if (userMapper.insert(user)==0) {
            throw new ExceptionResult("user","false",null,"注册失败");
        }
        return true;
    }

    @Override
    public BackgroundUser login(BackgroundUser user) {
        if (userDao.lambdaQuery()
                .eq(BackgroundUser::getPhone,user.getPhone())
                .count()==0) {
            throw new ExceptionResult("user","false",null,"登陆失败，手机号码不存在");
        }
        BackgroundUser loginUser = userDao.lambdaQuery()
                .eq(BackgroundUser::getPhone, user.getPhone())
                .eq(BackgroundUser::getPassword, user.getPassword())
                .one();
        if(Objects.isNull(loginUser)) {
            throw new ExceptionResult("user","false",null,"登陆失败，密码不正确");
        }
        HttpSession session = ServletUtils.getSession();
        session.setAttribute("token", JSON.toJSONString(loginUser));
        //session过期时间设置，以秒为单位，即在没有活动120分钟后，session将失效
        session.setMaxInactiveInterval(120 * 60);
        //CookieUtils.setCookie(ServletUtils.getRequest(),ServletUtils.getResponse(),"token", JSON.toJSONString(loginUser),1);
        return loginUser;
    }

    @Override
    public Boolean updateUser(BackgroundUser user) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (userDao.lambdaQuery().eq(BackgroundUser::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"当前用户不存在");
        }
        if(userDao.lambdaQuery().eq(BackgroundUser::getPhone,user.getPhone()).ne(BackgroundUser::getId,loginUser.getId()).count()>0) {
            throw new ExceptionResult("user","false",null,"修改失败，手机号码已存在");
        }
        if(!userDao.lambdaUpdate().eq(BackgroundUser::getId,user.getId()).update(user)) {
            throw new ExceptionResult("user","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public Boolean adminUpdateUser(BackgroundUser user) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if (userDao.lambdaQuery().eq(BackgroundUser::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"当前用户不存在");
        }
        if(userDao.lambdaQuery().eq(BackgroundUser::getPhone,user.getPhone()).ne(BackgroundUser::getId,user.getId()).count()>0) {
            throw new ExceptionResult("user","false",null,"修改失败，手机号码已存在");
        }
        if(!userDao.lambdaUpdate().eq(BackgroundUser::getId,user.getId()).update(user)) {
            throw new ExceptionResult("user","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public PageList<BackgroundUser> ListUser(PageDomain pageDomain) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        Page<BackgroundUser> page = userDao.lambdaQuery().page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        return PageList.of(page.getRecords(),page);
    }

    @Override
    public BackgroundUser getUserInfo() {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        return userDao.getById(loginUser.getId());
    }

    @Override
    public Boolean deletedUser(Long id) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if (userDao.lambdaQuery().eq(BackgroundUser::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"当前用户不存在");
        }
        if(userMapper.deleteById(id)==0) {
            throw new ExceptionResult("user","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public BackgroundUser getUserById(Long id) {
        return userDao.getById(id);
    }
}
