package com.yanzhen.front;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Comment;
import com.yanzhen.entity.User;
import com.yanzhen.service.CommentService;
import com.yanzhen.service.UserService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 596183363@qq.com
 * @Description:
 * @date 2020/11/517:59
 */
@RestController
@RequestMapping("/f/comment")
public class FrontCommentController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment_query")
    public Result reg(Integer articleId){
        Comment param = new Comment();
        param.setArticleId(articleId);
        List<Comment> list = commentService.queryByArticleId(param);
        list.forEach(item->{
            item.setUser(userService.detail(item.getAuthor()));
        });
        return Result.ok(list);
    }
}
