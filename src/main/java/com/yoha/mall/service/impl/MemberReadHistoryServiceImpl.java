package com.yoha.mall.service.impl;

import com.yoha.mall.nosql.mongodb.document.MemberReadHistory;
import com.yoha.mall.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.yoha.mall.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository historyRepository;

    @Override
    public int create(MemberReadHistory history) {
        history.setId(null);
        history.setCreateTime(new Date());
        historyRepository.save(history);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory history = new MemberReadHistory();
            history.setId(id);
        }
        historyRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return historyRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
