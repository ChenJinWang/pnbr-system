package com.cjw.pet.utils;

import com.alibaba.fastjson.JSON;
import com.cjw.pet.pojo.BackgroundUser;
import com.cjw.pet.pojo.User;
import com.cjw.pet.exception.ExceptionResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author cjw
 */
@Component
public class UserUtils {
    private static final String TOKEN = "token";


    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public User getUser(HttpServletRequest request)
    {
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token)) {
            throw new ExceptionResult("user","403",null,"请先登陆");
        }
        User user = null;
        try {
            user = JSON.parseObject(java.net.URLDecoder.decode(token,"UTF-8"), User.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return user;
    }

    public BackgroundUser getBackgroundUser(HttpServletRequest request)
    {
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token)) {
            throw new ExceptionResult("user","403",null,"请先登陆");
        }
        BackgroundUser backgroundUser = null;
        try {
            backgroundUser = JSON.parseObject(java.net.URLDecoder.decode(token,"UTF-8"), BackgroundUser.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return backgroundUser;
    }
}
