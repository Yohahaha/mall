package com.yoha.mall.service;

import com.yoha.mall.mbg.model.UmsAdmin;
import com.yoha.mall.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {
    /**
     * 依据用户名查询用户详细信息
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 用户注册
     */
    UmsAdmin register(UmsAdmin umsAdmin);

    /**
     * 用户登录
     */
    String login(String username, String password);

    /**
     * 获取权限列表
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
