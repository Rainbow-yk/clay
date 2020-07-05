package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nt.mapper.TbInformationMapper;
import com.nt.pojo.TbInformation;
import com.nt.user.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 99362
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private TbInformationMapper informationMapper;

    @Override
    public List<TbInformation> getAll() {
        return informationMapper.findAll();
    }

    @Override
    public void update(TbInformation information) {
        informationMapper.update(information);
    }
}
