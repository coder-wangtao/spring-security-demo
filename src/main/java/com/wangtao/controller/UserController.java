package com.wangtao.controller;

import com.wangtao.pojo.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @PreAuthorize("hasAnyAuthority('findAll')")
    @RequestMapping("/findAll.do")
    public String findAll(){
        return "findAll";
    }

    @PreAuthorize("hasAnyAuthority('save')")
    @RequestMapping("/save.do")
    public String save(){
        return "save";
    }

    @PreAuthorize("hasAnyAuthority('del')")
    @RequestMapping("/del.do")
    public String del(){
        return "del";
    }

    @PreAuthorize("hasAnyAuthority('edit')")
    @RequestMapping("/edit.do")
    public String edit(){
        return "edit";
    }

}
