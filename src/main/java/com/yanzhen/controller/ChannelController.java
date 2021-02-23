package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.entity.Channel;
import com.yanzhen.entity.User;
import com.yanzhen.service.ChannelService;
import com.yanzhen.utils.RequestUtils;
import com.yanzhen.utils.Result;
import com.yanzhen.entity.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    public Result create(@RequestBody Channel channel,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        channel.setCreateUser(user.getId());
        if(channel.getParentId()==null){
            channel.setParentId(0);
        }
        channelService.create(channel);
        return Result.ok(channel);
    }
    @PostMapping("/delete")
    public Result delete(Integer id){
        channelService.delete(id);
        return Result.ok();
    }
    @PostMapping("/update")
    public Result update(@RequestBody Channel channel){
        channelService.update(channel);
        return Result.ok(channel);
    }

    @PostMapping("/query")
    public Map query(@RequestBody Channel channel){
        PageInfo<Channel> pageInfo = channelService.query(channel);
        return Result.ok(pageInfo);
    }
    @PostMapping("/tree")
    public Result tree(){
        List<Channel> list = channelService.all();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(Channel channel : list){
            if(channel.getParentId() == 0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                List<Map<String,Object>> children = new ArrayList<>();
                for(Channel entity : list){
                    if(entity.getParentId() == channel.getId()){
                        Map<String,Object> subMap = new HashMap<>();
                        subMap.put("id",entity.getId());
                        subMap.put("label",entity.getName());
                        children.add(subMap);
                    }
                }
                map.put("children",children);
                mapList.add(map);
            }
        }
        return Result.ok(mapList);
    }
    @PostMapping("/detail")
    public Result detail(Integer id){
        Channel channel = channelService.detail(id);
        return Result.ok(channel);
    }
    @PostMapping("/count")
    public Result count(@RequestBody Channel channel){
        Integer count = channelService.count(channel);
        return Result.ok(count);
    }
}
