package com.yanzhen.front;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.service.ArticleService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/f/article")
public class FrontArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/get")
    public Result getById(Integer id){
        Article detail = articleService.detail(id);
        return Result.ok(detail);
    }
    @GetMapping("/getList")
    public Map getByChannelId(Article article){
        PageInfo<Article> page = articleService.query(article);
        return Result.ok(page);
    }
    @PostMapping("/getNew")
    public Map getNew(@RequestBody Article article){
        PageInfo<Article> page = articleService.queryByChannelId(article);
        return Result.ok(page);
    }
    @GetMapping("/getTop")
    public Result getByTop(Article article,Integer top){
        List<Article> list =  articleService.top(article,top);
        return Result.ok(list);
    }
    @GetMapping("/getUp")
    public Map getUp(){
        Article article = new Article();
        article.setTop(1);
        PageInfo<Article> page = articleService.query(article);
        return Result.ok(page);
    }
}
