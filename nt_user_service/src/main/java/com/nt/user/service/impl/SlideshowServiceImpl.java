package com.nt.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.nt.mapper.TbSlideshowMapper;
import com.nt.pojo.TbSlideshowInfo;
import com.nt.user.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 99362
 */
@Service
public class SlideshowServiceImpl implements SlideshowService {

    @Autowired
    private TbSlideshowMapper slideshowMapper;

    @Override
    public List<TbSlideshowInfo> getAll() {
        return slideshowMapper.getAll();
    }

    @Override
    public TbSlideshowInfo findOne(int id) {
        return slideshowMapper.findOne(id);
    }

    @Override
    public void updatePicture(String filePath, String id) {
        slideshowMapper.updatePicture(filePath, id);
    }

    @Override
    public void save(TbSlideshowInfo slideshowInfo) {
        slideshowMapper.save(slideshowInfo);
    }


}
