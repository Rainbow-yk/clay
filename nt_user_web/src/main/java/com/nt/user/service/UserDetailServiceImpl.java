package com.nt.user.service;

import com.nt.pojo.TbUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * spring security进行用户验证的类
 * @author 99362
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        // GrantedAuthority : 用来指定权限的类
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        // 通过手机号获取用户
        TbUser tbUser = userService.findOne(mobile);

        if (tbUser == null || tbUser.isDelete()){
            return null;
        }else{
            String password = tbUser.getPassword();
            // 这里的user是UserDetails的一个实现类
            User user = new User(mobile, password, list);
            // security 会把user的内容和用户输入的内容进行对比
            return user;
        }
    }

    
}
