package com.cjw.pet.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.UserDao;
import com.cjw.pet.mapper.UserMapper;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.UserService;
import com.cjw.pet.utils.CookieUtils;
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
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserDao userDao;

    @Autowired
    private UserUtils userUtils;

    public UserServiceImpl(UserMapper userMapper, UserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @Override
    public Boolean register(User user) {
        if(userDao.lambdaQuery().eq(User::getPhone,user.getPhone()).count()>0) {
            throw new ExceptionResult("user","false",null,"注册失败，手机号码已存在");
        }
        user.setCreateTime(new Date());
        if (userMapper.insert(user)==0) {
            throw new ExceptionResult("user","false",null,"注册失败");
        }
        return true;
    }

    @Override
    public User login(User user) {
        if (userDao.lambdaQuery()
                .eq(User::getPhone,user.getPhone())
                .count()==0) {
            throw new ExceptionResult("user","false",null,"登陆失败，手机号码不存在，或已被禁用");
        }
        User loginUser = userDao.lambdaQuery()
                .eq(User::getPhone, user.getPhone())
                .eq(User::getPassword, user.getPassword())
                .one();
        if(Objects.isNull(loginUser)) {
            throw new ExceptionResult("user","false",null,"登陆失败，密码不正确");
        }
        HttpSession session = ServletUtils.getSession();
        session.setAttribute("token", loginUser);
        //session过期时间设置，以秒为单位，即在没有活动120分钟后，session将失效
        session.setMaxInactiveInterval(120 * 60);
        CookieUtils.setCookie(ServletUtils.getRequest(),ServletUtils.getResponse(),"token", JSON.toJSONString(loginUser),1);
        return loginUser;
    }

    @Override
    public Boolean updateUser(User user) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if(userDao.lambdaQuery().eq(User::getGrade,user.getGrade()).ne(User::getRole,loginUser.getRole()).count()!=0) {
            throw new ExceptionResult("user","false",null,"请联系客服成为商家");
        }
        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"该用户不存在");
        }
        if(userDao.lambdaQuery().eq(User::getPhone,user.getPhone()).ne(User::getId,loginUser.getId()).count()>0) {
            throw new ExceptionResult("user","false",null,"修改失败，手机号码已存在");
        }
        if(!userDao.lambdaUpdate().eq(User::getId,user.getId()).update(user)) {
            throw new ExceptionResult("user","false",null,"修改失败");
        }
        return true;
    }

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    @Override
    public Boolean adminUpdateUser(User user) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
//        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
//            throw new ExceptionResult("user","false",null,"该用户不存在");
//        }
        if(userDao.lambdaQuery().eq(User::getPhone,user.getPhone()).ne(User::getId,user.getId()).count()>0) {
            throw new ExceptionResult("user","false",null,"修改失败，手机号码已存在");
        }
        if(!userDao.lambdaUpdate().eq(User::getId,user.getId()).update(user)) {
            throw new ExceptionResult("user","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public PageList<User> ListUser(PageDomain pageDomain) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        Page<User> page = userDao.lambdaQuery().page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        return PageList.of(page.getRecords(),page);
    }

    @Override
    public Boolean deletedUser(Long id) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (!"admin".equals(loginUser.getRole()) && !"superadmin".equals(loginUser.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if (userDao.lambdaQuery().eq(User::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"当前用户不存在");
        }
        if(userMapper.deleteById(id)==0) {
            throw new ExceptionResult("user","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public User getUserInfo() {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        return userDao.getById(loginUser.getId());
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }
}
