package com.yoha.mall.nosql.mongodb.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberReadHistoryRepositoryTest {

    @Autowired
    private MemberReadHistoryRepository historyRepository;

//    @Test
//    public void create() {
//        MemberReadHistory history = new MemberReadHistory();
//        history.setMemberId(1L);
//        history.setProductName("小米");
//        history.setCreateTime(new Date());
//        history.setProductId(123L);
//        MemberReadHistory save = historyRepository.save(history);
//        System.out.println(save);
//    }
}