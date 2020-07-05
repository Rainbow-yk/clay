package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.SearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Reference
    private SearchService searchService;

    @RequestMapping("/search.do")
    public List<TbWorkInfo> search(String keyword) {
        try {
            // 对数据进行utf-8格式的解码
            keyword = URLDecoder.decode(keyword, "utf-8");
            return searchService.search(keyword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
