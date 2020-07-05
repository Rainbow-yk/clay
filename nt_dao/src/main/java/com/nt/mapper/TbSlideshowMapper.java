package com.nt.mapper;

import com.nt.pojo.TbSlideshowInfo;

import java.util.List;

/**
 * @author 99362
 */
public interface TbSlideshowMapper {

    /**
     * 获取所有的轮播图信息
     * @return
     */
    List<TbSlideshowInfo> getAll();

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    TbSlideshowInfo findOne(int id);

    void updatePicture(String filePath, String id);

    void save(TbSlideshowInfo slideshowInfo);
}
