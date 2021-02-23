package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Article;
import com.yanzhen.entity.ArticleAttachment;
import com.yanzhen.entity.ArticleTag;
import com.yanzhen.mapper.ArticleAttachmentDao;
import com.yanzhen.mapper.ArticleDao;
import com.yanzhen.mapper.ArticleTagDao;
import com.yanzhen.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleAttachmentDao articleAttachmentDao;
    @Autowired
    private ArticleTagDao articleTagDao;

    @Transactional
    public int create(Article article){
        int row = articleDao.create(article);
        if(article.getArticleAttachmentList() !=null){
            article.getArticleAttachmentList().forEach(entity ->{
                ArticleAttachment articleAttachment = new ArticleAttachment();
                articleAttachment.setArticleId(article.getId());
                articleAttachment.setDescription(entity.get("name")+"");
                articleAttachment.setUrl(entity.get("url")+"");
                articleAttachmentDao.create(articleAttachment);
            });
        }
        if(article.getSelectTagList() != null){
            article.getSelectTagList().forEach(x ->{
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(x);
                articleTagDao.create(articleTag);
            });
        }

        return row;
    }
    @Transactional
    public int delete(Integer id){
        int row  = articleDao.delete(Maps.build(id).getMap());
        articleAttachmentDao.delete(Maps.build().put("articleId",id).getMap());
        articleTagDao.delete(Maps.build().put("articleId",id).getMap());
        return row;
    }
    public int update(Article article){
        int update = articleDao.update(Maps.build(article.getId()).beanToMapForUpdate(article));
        articleAttachmentDao.delete(Maps.build().put("articleId",article.getId()).getMap());
        articleTagDao.delete(Maps.build().put("articleId",article.getId()).getMap());
        article.getArticleAttachmentList().forEach(entity ->{
            ArticleAttachment articleAttachment = new ArticleAttachment();
            articleAttachment.setArticleId(article.getId());
            articleAttachment.setDescription(entity.get("name")+"");
            articleAttachment.setUrl(entity.get("url")+"");
            articleAttachmentDao.create(articleAttachment);
        });
        article.getSelectTagList().forEach(x ->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(x);
            articleTagDao.create(articleTag);
        });
        return update;
    }
    public PageInfo<Article> query(Article article){
        if(article != null && article.getPage() != null){
            PageHelper.startPage(article.getPage(),article.getLimit());
        }
        List<Article> list = articleDao.query(Maps.build().beanToMap(article));
        return new PageInfo<Article>(list);
    }
    public List<Article> top(Article article,Integer top){
        PageHelper.startPage(0,top);
        List<Article> list = articleDao.query(Maps.build().beanToMap(article));
        return list;
    }

    public Article detail(Integer id){
        Article article = articleDao.detail(Maps.build(id).getMap());
        List<ArticleTag> tagList = articleTagDao.query(Maps.build().put("articleId", id).getMap());
        List<ArticleAttachment> attachmentList = articleAttachmentDao.query(Maps.build().put("articleId", id).getMap());
        List<Integer> tags = new ArrayList<>();
        List<Map<String,Object>> attachments = new ArrayList<>();
        tagList.forEach(entity->{
            tags.add(entity.getTagId());
        });
        attachmentList.forEach(entity->{
            Map map = new HashMap<>();
            map.put("name",entity.getDescription());
            map.put("url",entity.getUrl());
            attachments.add(map);
        });
        article.setSelectTagList(tags);
        article.setArticleAttachmentList(attachments);
        return article;
    }
    public int count(Article article){
        return articleDao.count(Maps.build().beanToMap(article));
    }
    public PageInfo<Article> queryByChannelId(Article article){
        if(article != null && article.getPage() != null){
            PageHelper.startPage(article.getPage(),article.getLimit());
        }
        List<Article> list = articleDao.queryByChannelId(article.getChannelId());
        return new PageInfo<Article>(list);
    }
}
