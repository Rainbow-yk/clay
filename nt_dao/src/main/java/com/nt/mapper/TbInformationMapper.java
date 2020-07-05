package com.nt.mapper;

import com.nt.pojo.TbInformation;

import java.util.List;

/**
 * @author 99362
 */
public interface TbInformationMapper {

    List<TbInformation> findAll();

    void update(TbInformation information);

}
