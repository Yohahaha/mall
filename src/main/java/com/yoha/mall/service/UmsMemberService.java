package com.yoha.mall.service;

import com.yoha.mall.common.CommonResult;

public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult getAuthCode(String phone);

    /**
     * 检验验证码
     */
    CommonResult verify(String phone, String authCode);
}
