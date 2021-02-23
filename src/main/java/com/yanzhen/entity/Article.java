package com.yanzhen.entity;

import com.yanzhen.utils.Entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Article extends Entity {
    private Integer id;
    private Integer channelId;
    private String title;
    private String titleImg;
    private String summary;
    private String author;
    private String url;
    private String content;
    private Integer status;
    private Integer commentStatus;
    private Integer rotation;
    private Integer top;
    private Integer orderby;
    private Date createDate;
    private Integer createUser;
    private Date updateDate;
    private List<Map<String,Object>> articleAttachmentList;
    private List<Integer> selectTagList;

    public List<Integer> getSelectTagList() {
        return selectTagList;
    }

    public void setSelectTagList(List<Integer> selectTagList) {
        this.selectTagList = selectTagList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderBy(Integer orderby) {
        this.orderby = orderby;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Map<String, Object>> getArticleAttachmentList() {
        return articleAttachmentList;
    }

    public void setArticleAttachmentList(List<Map<String, Object>> articleAttachmentList) {
        this.articleAttachmentList = articleAttachmentList;
    }
}
