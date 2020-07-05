package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.TbSlideshowInfo;
import com.nt.user.service.SlideshowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/slideshow")
public class SlideshowController {

    @Reference
    private SlideshowService slideshowService;

    /**
     * 获取所有的轮播图信息
     * @return
     */
    @RequestMapping("/getAll.do")
    public List<TbSlideshowInfo> getAll() {
        List<TbSlideshowInfo> slideshowServiceAll = slideshowService.getAll();
        return slideshowServiceAll;
    }

}
