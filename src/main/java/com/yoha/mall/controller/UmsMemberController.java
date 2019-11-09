package com.yoha.mall.controller;

import com.yoha.mall.common.CommonResult;
import com.yoha.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso")
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
public class UmsMemberController {

    @Autowired
    UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String phone) {
        return umsMemberService.getAuthCode(phone);
    }

    @ApiOperation("判断验证码正确性")
    @PostMapping("/verify")
    public CommonResult verify(@RequestParam String phone, @RequestParam String authCode) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(authCode)) {
            return CommonResult.validateFailed("输入信息不能为空");
        }
        return umsMemberService.verify(phone, authCode);
    }
}
