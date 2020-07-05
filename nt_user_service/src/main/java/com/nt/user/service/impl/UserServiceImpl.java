package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nt.mapper.TbUserMapper;
import com.nt.pojo.PageResult;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;
import com.nt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 99362
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    /**
     * 查询单个用户
     * @param mobile
     * @return
     */
    @Override
    public TbUser findOne(String mobile) {
        return userMapper.findOne(mobile);
    }

    @Override
    public Result addUser(TbUser user, String code, String securityCode) throws Exception {
        TbUser one = userMapper.findOne(user.getMobile());
        if (one != null) {
            return new Result("手机号已经注册", false);
        }
        if (!securityCode.equals(code)) {
            return new Result("验证码输入有误", false);
        }
        if (user.getNickname().length() > 16 || user.getPassword().length() > 16 || user.getMobile().length() > 11 || securityCode.length() > 6) {
            return new Result("数据长度超出", false);
        }
        user.setDelete(false);
        user.setAvatarUrl("/upload/avatar/default.jpg");
        userMapper.addUser(user);
        return new Result("/login.html", true);
    }

    @Override
    public void delete(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            userMapper.delete(ids[i]);
        }
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, String keyword) {
        // 使用插件指定查询的内容
        PageHelper.startPage(pageNum, pageSize);
        Page<TbUser> page = (Page<TbUser>) userMapper.getAllUser(keyword);
        // 获取当前页的数据
        List<TbUser> result = page.getResult();
        // 获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public void update(TbUser user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String filePath, String mobile) {
        userMapper.updateAvatar(filePath, mobile);
    }

}
