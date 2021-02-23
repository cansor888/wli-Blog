package com.yanzhen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.ArticleTag;
import com.yanzhen.mapper.ArticleTagDao;
import com.yanzhen.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagDao articleTagDao;

    public int create(ArticleTag articleTag){
        return articleTagDao.create(articleTag);
    }
    public int delete(Integer id){
            return articleTagDao.delete(Maps.build(id).getMap());
    }
    public int update(ArticleTag articleTag){
        return articleTagDao.update(Maps.build(articleTag.getId()).beanToMapForUpdate(articleTag));
    }
    public PageInfo<ArticleTag> query(ArticleTag articleTag){
        if(articleTag != null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),articleTag.getLimit());
        }
        List<ArticleTag> list = articleTagDao.query(Maps.build().beanToMap(articleTag));
        return new PageInfo<ArticleTag>(list);
    }
    public ArticleTag detail(Integer id){
        return articleTagDao.detail(Maps.build(id).getMap());
    }
    public int count(ArticleTag articleTag){
        return articleTagDao.count(Maps.build().beanToMap(articleTag));
    }
}
