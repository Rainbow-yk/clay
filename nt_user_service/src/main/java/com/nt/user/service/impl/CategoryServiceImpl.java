package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nt.mapper.TbCategoryMapper;
import com.nt.pojo.TbCategory;
import com.nt.user.service.CategoryService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author 99362
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TbCategoryMapper categoryMapper;

    @Override
    public List<Map> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public void saveCategoryAndWork(String[] categoryIds, int workId) {
        for (String id : categoryIds) {
            categoryMapper.saveCategoryAndWork(id, workId);
        }
    }

    @Override
    public List<Map> findCategoryByWorkId(int id) {
        return categoryMapper.findCategoryByWorkId(id);
    }

    @Override
    public void deleteByWorkId(Integer workId) {
        categoryMapper.deleteByWorkId(workId);
    }

}
