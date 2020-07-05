package com.nt.user.service;


import com.nt.pojo.TbSlideshowInfo;

import java.util.List;

/**
 * @author 99362
 */
public interface SlideshowService {

    /**
     * 获取所有的轮播图信息
     * @return
     */
    List<TbSlideshowInfo> getAll();

    /**
     * 获取单条信息
     * @param id
     * @return
     */
    TbSlideshowInfo findOne(int id);

    void updatePicture(String filePath, String id);

    void save(TbSlideshowInfo slideshowInfo);
}
