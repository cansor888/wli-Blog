package com.yanzhen.mapper;

import com.yanzhen.entity.FriendLink;

import java.util.List;
import java.util.Map;

public interface FriendLinkDao {
	public int create(FriendLink pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<FriendLink> query(Map<String, Object> paramMap);
	public FriendLink detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}