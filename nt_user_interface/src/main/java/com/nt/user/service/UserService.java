package com.nt.user.service;

import com.nt.pojo.PageResult;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;

/**
 * @author 99362
 */
public interface UserService {

    /**
     * 通过手机号查询单个用户
     * @param mobile
     * @return User
     */
    TbUser findOne(String mobile);

    /**
     * 添加用户
     * @param user
     * @param code
     * @param securityCode
     * @return Result对象
     * @throws Exception
     */
    Result addUser(TbUser user, String code, String securityCode) throws Exception;

    /**
     * 修改用户头像
     * @param filePath
     * @param mobile
     */
    void updateAvatar(String filePath, String mobile);

    /**
     * 删除用户
     * @param ids
     */
    void delete(Integer[] ids);

    /**
     * 查询所有的用户并分页
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return PageResult对象
     */
    PageResult findPage(int pageNum, int pageSize, String keyword);

    /**
     * 修改用户信息
     * @param user
     */
    void update(TbUser user);
}
