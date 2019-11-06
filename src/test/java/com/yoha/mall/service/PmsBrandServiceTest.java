package com.yoha.mall.service;

import com.github.pagehelper.PageInfo;
import com.yoha.mall.mbg.model.PmsBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsBrandServiceTest {
    @Autowired
    PmsBrandService pmsBrandService;
    @Test
    public void listAllBrand() {
        List<PmsBrand> brands = pmsBrandService.listAllBrand();
        for (PmsBrand item : brands) {
            System.out.println(item);
        }
    }

    @Test
    public void createBrand() {
        PmsBrand brand = new PmsBrand();
        brand.setName("yoha");
        brand.setFirstLetter("Y");
        int id = pmsBrandService.createBrand(brand);
        System.out.println(id);
    }

    @Test
    public void updateBrand() {
        PmsBrand brand = new PmsBrand();
        brand.setName("yoha");
        brand.setFirstLetter("A");
        int res = pmsBrandService.updateBrand(59L, brand);
        System.out.println(res);
    }

    @Test
    public void deleteBrand() {
        int cnt = pmsBrandService.deleteBrand(60L);
        assert cnt == 1;
    }

    @Test
    public void listBrand() {
        int pageNum = 2;
        int pageSize = 3;
        List<PmsBrand> brands = pmsBrandService.listBrand(pageNum, pageSize);
        PageInfo<PmsBrand> info = new PageInfo<>(brands);
        System.out.println("total: " + info.getTotal());
        System.out.println("pages: " + info.getPages());
        System.out.println("nextPage: " + info.getNextPage());
        for (PmsBrand item : brands) {
            System.out.println(item);
        }
    }

    @Test
    public void getBrand() {
    }
}