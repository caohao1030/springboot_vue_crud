package com.ch.demo.controller;

import com.ch.demo.utils.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/api/upload")
public class   ApiUploadController {
    /**
     * 文件上传根路径
     */
    private static final String UPLOAD_BASE_PATH = "D:\\files\\";
    private static final String AVATAR_BASE_URL = "http://localhost:8081/file/";
    @RequestMapping("/avatar")
    public ResultEntity avatarUpload(MultipartFile file) {
        String orginalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + orginalName.substring(orginalName.lastIndexOf('.'));
        try {
            file.transferTo(new File(UPLOAD_BASE_PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.success(AVATAR_BASE_URL + fileName);
    }
}
