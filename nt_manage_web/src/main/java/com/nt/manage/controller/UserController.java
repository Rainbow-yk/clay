package com.nt.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.PageResult;
import com.nt.pojo.Result;
import com.nt.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/findPage.do")
    public PageResult findPage(int pageNum, int pageSize, String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "utf-8");
            PageResult pageResult = userService.findPage(pageNum, pageSize, keyword);
            return pageResult;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("delete.do")
    public Result delete(@RequestBody Integer[] ids) {
        userService.delete(ids);
        return new Result("删除成功", true);
    }

}
