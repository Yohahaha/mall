package com.yoha.mall.controller;

import com.yoha.mall.common.CommonResult;
import com.yoha.mall.nosql.mongodb.document.MemberReadHistory;
import com.yoha.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService historyService;

    @ApiOperation("新建浏览记录")
    @PostMapping("/create")
    public CommonResult create(@RequestBody MemberReadHistory history) {
        int res = historyService.create(history);
        if (res > 0) {
            return CommonResult.success(res);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int cnt = historyService.delete(ids);
        if (cnt > 0) {
            return CommonResult.success(cnt);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("展示浏览记录")
    @GetMapping("/list/{memberId}")
    public CommonResult list(@PathVariable Long memberId) {
        List<MemberReadHistory> list = historyService.list(memberId);
        return CommonResult.success(list);
    }
}
