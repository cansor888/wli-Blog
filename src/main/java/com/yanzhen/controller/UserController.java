package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.User;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.RequestUtils;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result create(@RequestBody User user){
        userService.create(user);
        return Result.ok(user);
    }
    @PostMapping("/delete")
    public Result delete(Integer id){
        userService.delete(id);
        return Result.ok();
    }
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.ok(user);
    }

    @PostMapping("/query")
    public Map query(@RequestBody User user){
        PageInfo<User> pageInfo = userService.query(user);
        return Result.ok(pageInfo);
    }
    @PostMapping("/detail")
    public Result detail(Integer id){
        User user = userService.detail(id);
        return Result.ok(user);
    }
    @PostMapping("/count")
    public Result count(@RequestBody User user){
        Integer count = userService.count(user);
        return Result.ok(count);
    }
}
