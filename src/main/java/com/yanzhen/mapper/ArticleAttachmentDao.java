package com.yanzhen.mapper;

import com.yanzhen.entity.ArticleAttachment;

import java.util.List;
import java.util.Map;

public interface ArticleAttachmentDao {
	public int create(ArticleAttachment pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<ArticleAttachment> query(Map<String, Object> paramMap);
	public ArticleAttachment detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}