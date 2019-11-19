package com.yoha.mall.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据包装类
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为详细的分页信息
     * 传入的list其实是Page类，使用方法参考PageHelper文档例子三
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> page = new PageInfo<>(list);
        result.setList(page.getList());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotalPage(page.getPages());
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 将Spring Data分页后的list转成分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<>();
        result.setList(pageInfo.getContent());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotalPage(pageInfo.getTotalPages());
        result.setTotal(pageInfo.getTotalElements());
        return result;
    }
}
