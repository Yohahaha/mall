package com.yoha.mall.service;

import com.yoha.mall.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(long id);
}
