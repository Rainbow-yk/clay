package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.TbInformation;
import com.nt.user.service.InformationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 99362
 */
@RestController
@RequestMapping("information")
public class InformationController {

    @Reference
    private InformationService informationService;

    @RequestMapping("getInformation.do")
    public TbInformation findAll() {
        return informationService.getAll().get(0);
    }

}
