package com.nt.user.service;

import com.nt.pojo.TbInformation;

import java.util.List;

/**
 * @author 99362
 */
public interface InformationService {

    List<TbInformation> getAll();

    void update(TbInformation information);

}
