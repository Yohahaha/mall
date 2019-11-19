package com.yoha.mall.service;

import com.yoha.mall.nosql.es.EsProduct;
import org.springframework.data.domain.Page;

/**
 * 商品搜索service
 */
public interface EsProductService {

    /**
     * 导入所有商品
     */
    int importAll();

    /**
     * 根据关键字搜索商品
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
