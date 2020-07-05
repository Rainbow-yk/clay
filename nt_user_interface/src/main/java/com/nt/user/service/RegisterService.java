package com.nt.user.service;

/**
 * @author 99362
 */
public interface RegisterService {

    /**
     * 发送短信验证码
     * @param mobile
     * @param code
     * @throws Exception
     */
    void sendSecurityCode(String mobile, String code) throws Exception;
}
