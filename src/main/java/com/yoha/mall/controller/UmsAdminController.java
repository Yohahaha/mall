package com.yoha.mall.controller;

import com.yoha.mall.common.CommonResult;
import com.yoha.mall.mbg.model.UmsAdmin;
import com.yoha.mall.mbg.model.UmsPermission;
import com.yoha.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody UmsAdmin admin) {
        UmsAdmin user = adminService.register(admin);
        if (user == null) {
            return CommonResult.failed("注册失败");
        }
        return CommonResult.success(user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdmin admin) {
        String token = adminService.login(admin.getUsername(), admin.getPassword());
        if (StringUtils.isEmpty(token)) {
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户权限列表")
    @GetMapping("/permission/{adminId}")
    public CommonResult getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
