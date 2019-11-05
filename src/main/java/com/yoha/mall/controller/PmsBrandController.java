package com.yoha.mall.controller;

import com.yoha.mall.common.CommonPage;
import com.yoha.mall.common.CommonResult;
import com.yoha.mall.mbg.model.PmsBrand;
import com.yoha.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @GetMapping("/listAll")
    public CommonResult getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

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

    @GetMapping("/list")
    public CommonResult listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value="pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brands));
    }

    @GetMapping("/{id}")
    public CommonResult brand(@PathVariable Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
