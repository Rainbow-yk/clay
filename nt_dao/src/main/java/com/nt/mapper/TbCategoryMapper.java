package com.nt.mapper;

import com.nt.pojo.TbCategory;

import java.util.List;
import java.util.Map;

public interface TbCategoryMapper {

    List<Map> getAll();

    void saveCategoryAndWork(String id, int workId);

    List<Map> findCategoryByWorkId(int id);

    void deleteByWorkId(Integer workId);
}
