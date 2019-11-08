package com.yoha.mall.controller;

import com.yoha.mall.common.CommonPage;
import com.yoha.mall.common.CommonResult;
import com.yoha.mall.mbg.model.PmsBrand;
import com.yoha.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("列出所有品牌")
    @GetMapping("/listAll")
    public CommonResult getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand brand) {
        CommonResult commonResult;
        int cnt = pmsBrandService.createBrand(brand);
        if (cnt == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("create success:{}", brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create failed:{}", brand);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable Long id, PmsBrand brand) {
        CommonResult commonResult;
        int cnt = pmsBrandService.updateBrand(id, brand);
        if (cnt == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("update success:{}", brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update failed:{}", brand);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id品牌")
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable Long id) {
        int cnt = pmsBrandService.deleteBrand(id);
        if (cnt == 1) {
            LOGGER.debug("deleteBrand success: brand id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed: brand id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询品牌列表")
    @GetMapping("/list")
    public CommonResult listBrand(@RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize) {
        List<PmsBrand> brands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brands));
    }

    @ApiOperation("根据品牌id获取品牌信息")
    @GetMapping("/{id}")
    public CommonResult brand(@PathVariable Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
