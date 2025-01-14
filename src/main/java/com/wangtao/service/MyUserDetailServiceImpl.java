package com.wangtao.service;

import com.wangtao.pojo.User;
import com.wangtao.util.MD5;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.*;

@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    //模拟数据库中的用户数据
    static Map<String, User> map =   new HashMap<String, User>();
    static {
        User user1 =  new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setTelephone("123");

        User user2 =  new User();
        user2.setUsername("zhangsan");
        user2.setPassword("123");
        user2.setTelephone("321");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    //根据数据库查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = map.get(username);
        if(user==null){
            throw new UsernameNotFoundException("账号不存在");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("findAll"));
        authorities.add(new SimpleGrantedAuthority("delete"));
        authorities.add(new SimpleGrantedAuthority("save"));
        authorities.add(new SimpleGrantedAuthority("A"));
        authorities.add(new SimpleGrantedAuthority("B"));

        String password = user.getPassword();
//        String pwd = MD5.encrypt(password);
        String pwd = new BCryptPasswordEncoder().encode(password);
        System.out.println(pwd);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),pwd, authorities);
    }
}
