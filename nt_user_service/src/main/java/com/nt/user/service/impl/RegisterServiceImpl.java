package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nt.user.service.RegisterService;
import com.nt.utils.CodeUtil;
import com.nt.utils.SendSmsUtil;


/**
 * @author 99362
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public void sendSecurityCode(String mobile, String code) {
        SendSmsUtil.SendSM(mobile, code);
        System.out.println("短信发送的code："+code);
    }
}
