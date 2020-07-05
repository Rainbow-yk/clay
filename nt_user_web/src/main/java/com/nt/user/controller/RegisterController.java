package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;
import com.nt.user.service.RegisterService;
import com.nt.user.service.UserService;
import com.nt.utils.CodeUtil;
import com.nt.utils.SendSmsUtil;
//import com.sun.deploy.nativesandbox.comm.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 99362
 */
@RequestMapping("/register")
@RestController
public class RegisterController {

    @Reference
    private RegisterService registerService;

    @Reference
    private UserService userService;

    /**
     * 用户注册
     * @param user, securityCode, request
     * @return result
     */
    @RequestMapping("/register")
    public Result register(@RequestBody TbUser user, String securityCode, HttpServletRequest request){
        try {
            String code = (String) request.getSession().getAttribute("SMS_VERIFICATION_CODE");
            System.out.println("从session中获取的code："+code);
            Result result = userService.addUser(user, code, securityCode);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("注册失败，请重试", false);
        }
    }

    /**
     * 发送短信验证码，同时将验证码存入session中
     * @param mobile
     * @return void
     */
    @RequestMapping("/sendSecurityCode")
    public void sendSecurityCode(String mobile, HttpServletRequest request){
        try {
            String code = CodeUtil.getCode();
            // 发送验证码
            registerService.sendSecurityCode(mobile, code);
            System.out.println("即将存入session中的code:"+code);
            HttpSession session = request.getSession();
            // 将短信验证码存入session中
            session.setAttribute("SMS_VERIFICATION_CODE", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
