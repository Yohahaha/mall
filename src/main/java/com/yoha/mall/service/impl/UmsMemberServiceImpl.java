package com.yoha.mall.service.impl;

import com.yoha.mall.common.CommonResult;
import com.yoha.mall.service.RedisService;
import com.yoha.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_TIME;

    @Override
    public CommonResult getAuthCode(String phone) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int code = random.nextInt(10);
            sb.append(code);
        }
        String authCode = sb.toString();
        // redis缓存验证信息
        redisService.set(AUTH_CODE + phone, authCode);
        redisService.expire(AUTH_CODE + phone, AUTH_CODE_EXPIRE_TIME);
        return CommonResult.success(authCode, "获取验证码成功");
    }

    @Override
    public CommonResult verify(String phone, String code) {
        /*
            验证逻辑：
                - 从redis中获取正确的验证码
                - 比对
                - 返回结果
         */
        String authCode = redisService.get(AUTH_CODE + phone);
        CommonResult commonResult;
        if (code.equals(authCode)) {
            commonResult = CommonResult.success(null, "验证成功");
        } else {
            commonResult = CommonResult.validateFailed("验证失败");
        }
        return commonResult;
    }
}
