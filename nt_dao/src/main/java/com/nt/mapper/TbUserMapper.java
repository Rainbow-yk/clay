package com.nt.mapper;

import com.nt.pojo.TbUser;

import java.util.List;

/**
 * @author 99362
 */
public interface TbUserMapper {

    TbUser findOne(String mobile);

    void addUser(TbUser user) throws Exception;

    List<TbUser> getAllUser(String keyword);

    void delete(Integer id);

    void updateAvatar(String filePath, String mobile);

    void update(TbUser user);
}
