package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.service.ArticleService;
import com.yanzhen.service.ChannelService;
import com.yanzhen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    public Result create(@RequestBody Article article){
        articleService.create(article);
        return Result.ok(article);
    }
    @PostMapping("/delete")
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.ok();
    }
    @PostMapping("/update")
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.ok(article);
    }

    @PostMapping("/query")
    public Map query(@RequestBody Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        return Result.ok(pageInfo);
    }
    @PostMapping("/detail")
    public Result detail(Integer id){
        Article article = articleService.detail(id);
        return Result.ok(article);
    }
    @PostMapping("/count")
    public Result count(@RequestBody Article article){
        Integer count = articleService.count(article);
        return Result.ok(count);
    }
}
