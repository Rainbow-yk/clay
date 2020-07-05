package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;
import com.nt.user.service.UserService;
import com.nt.utils.ImageUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Reference
    private UserService userService;

    /**
     * 改变图片验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("changePicture")
    public void changePicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageUtil.createImage(request,response);
    }

    /**
     * 判断用户输入的图片验证码是否正确
     * @param securityCode
     * @param request
     * @return 结果对象
     */
    @RequestMapping("checkSecurityCode")
    public Result checkSecurityCode(String securityCode, HttpServletRequest request){
        // 从session中获取图片验证码
        String checkCodeSession = (String) request.getSession().getAttribute("checkcode_session");
        // 将用户输入的验证码与session中的验证码进行判断
        if(securityCode.equalsIgnoreCase(checkCodeSession)){
            return new Result("验证码正确", true);
        }else {
            return new Result("验证码错误", false);
        }
    }

    /**
     * 通过用户的手机号查询单个用户
     * @param mobile
     * @return user
     */
    @RequestMapping("/findOneUser")
    public TbUser findOneUser(String mobile){
        TbUser user = userService.findOne(mobile);
        return user;
    }

    /**
     * 获取用户的昵称
     * @return map集合
     */
    @RequestMapping("/getUserInfo")
    public TbUser getName(){
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        TbUser user = userService.findOne(mobile);
        user.setPassword(null);
        return user;
    }
}
