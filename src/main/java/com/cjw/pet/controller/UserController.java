package com.cjw.pet.controller;

import com.alibaba.fastjson.JSON;
import com.cjw.pet.pojo.AjaxResult;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author cjw
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public AjaxResult register(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.register(user));
        return ajaxResult;
    }

    /**
     * 加载后台用户
     * @param pageDomain 参数
     * @return 结果
     */
    @PostMapping("/list")
    @ApiOperation("加载后台用户")
    public AjaxResult ListUser(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success(userService.ListUser(pageDomain));
        return ajaxResult;
    }

    /**
     * 登陆
     * @param user 参数
     * @return 结果
     */
    @PostMapping("/login")
    @ApiOperation("登陆")
    public AjaxResult login(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(JSON.toJSON(userService.login(user)));
        return ajaxResult;
    }

    /**
     * 修改用户信息，密码，手机号码，头像
     * @param user 用户
     * @return 结果
     */
    @PutMapping
    @ApiOperation("修改用户信息")
    public AjaxResult updateUser(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.updateUser(user));
        return ajaxResult;
    }

    /**
     * 修改用户信息，密码，手机号码，头像
     * @param user 用户
     * @return 结果
     */
    @PutMapping("/admin")
    @ApiOperation("修改用户信息")
    public AjaxResult adminUpdateUser(@Valid @RequestBody User user , BindingResult bindingResult) {
        AjaxResult ajaxResult = AjaxResult.success(userService.adminUpdateUser(user));
        return ajaxResult;
    }

    /**
     * 获取当前用户登陆的信息
     * @return 结果
     */
    @PostMapping("/info")
    @ApiOperation("获取当前用户登陆的信息")
    public AjaxResult getUserInfo() {
        AjaxResult ajaxResult = AjaxResult.success(JSON.toJSON(userService.getUserInfo()));
        return ajaxResult;
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public AjaxResult deletedUser(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success(userService.deletedUser(id));
        return ajaxResult;
    }

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据用户id获取用户信息")
    public AjaxResult getUserById(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success(userService.getUserById(id));
        return ajaxResult;
    }
}
