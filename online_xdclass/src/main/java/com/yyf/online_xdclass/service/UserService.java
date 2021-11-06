package com.yyf.online_xdclass.service;

import com.yyf.online_xdclass.model.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     */
    String findByPhoneAndPwd(String phone, String pwd);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);
}
