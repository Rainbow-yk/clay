package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.TbCategory;
import com.nt.user.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    /**
     * 获取所有分类列表
     * @return
     */
    @RequestMapping("/getAll.do")
    public List<Map> getAll() {
        return categoryService.getAll();
    }

    /**
     * 通过作品的id获取分类表的信息
     * @param id
     * @return
     */
    @RequestMapping("/findCategoryByWorkId.do")
    public List<Map> findCategoryByWorkId(int id) {
        return categoryService.findCategoryByWorkId(id);
    }

}
