package com.yanzhen.front;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.User;
import com.yanzhen.service.ArticleService;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 596183363@qq.com
 * @Description:
 * @date 2020/11/517:59
 */
@RestController
@RequestMapping("/f/user")
public class FrontUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public Result reg(@RequestBody User user){
        User param = new User();
        param.setUserName(user.getUserName());
        PageInfo<User> pageInfo = userService.query(param);
        if(pageInfo != null && pageInfo.getList().size() >0){
            return Result.fail("账号已存在");
        }else{
            //1:注册用户
            user.setType(1);
            userService.create(user);
            return Result.ok(user);
        }
    }
}
