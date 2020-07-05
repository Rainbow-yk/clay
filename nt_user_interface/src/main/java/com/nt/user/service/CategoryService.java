package com.nt.user.service;

import com.nt.pojo.TbCategory;

import java.util.List;
import java.util.Map;

/**
 * @author 99362
 */
public interface CategoryService {

    List<Map> getAll();

    void saveCategoryAndWork(String[] categoryIds, int workId);

    List<Map> findCategoryByWorkId(int id);

    void deleteByWorkId(Integer workId);
}
