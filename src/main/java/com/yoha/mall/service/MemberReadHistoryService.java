package com.yoha.mall.service;

import com.yoha.mall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 会员浏览记录管理
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory history);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 列出用户浏览记录
     */
    List<MemberReadHistory> list(Long memberId);
}
