package com.yoha.mall.dao;

import com.yoha.mall.nosql.es.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义doa
 */
public interface EsProductDao {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
