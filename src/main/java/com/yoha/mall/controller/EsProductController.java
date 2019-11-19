package com.yoha.mall.controller;

import com.yoha.mall.common.CommonPage;
import com.yoha.mall.common.CommonResult;
import com.yoha.mall.nosql.es.EsProduct;
import com.yoha.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
public class EsProductController {

    @Autowired
    private EsProductService productService;

    @GetMapping("/importAll")
    @ApiOperation(value = "导出数据库所有商品到ES")
    public CommonResult importAll() {
        int cnt = productService.importAll();
        return CommonResult.success(cnt);
    }

    @GetMapping("/simple/search")
    @ApiOperation("简单搜索")
    public CommonResult simpleSearch(@RequestParam(required = false) String keyword,
                                     @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> page = productService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }
}
