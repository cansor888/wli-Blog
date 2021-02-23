package com.yanzhen.controller;

import com.yanzhen.utils.RequestUtils;
import com.yanzhen.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class uploadController {
    @PostMapping("/uploadFile")
    public Result upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String originalFilename =  multipartFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        String newFileNamePrefix = UUID.randomUUID().toString();
        String newFileName = newFileNamePrefix+"."+ext;
        multipartFile.transferTo(new File("e:/upload",newFileName));
        return Result.ok(RequestUtils.getBasePath(request)+"upload/"+newFileName);
    }
}
