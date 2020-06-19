package com.example.community.controller;


import com.example.community.dto.FileDTO;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.provider.AliyunOssProvider;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Api(tags = "FileController", description = "图片上传")
@Controller
@Slf4j
public class FileController {

    @Autowired
    private AliyunOssProvider aliyunOssProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        FileDTO fileDto = new FileDTO();
        try {
            assert file != null;
            String url = aliyunOssProvider.upload(file.getInputStream(), file.getOriginalFilename());
            fileDto.setUrl(url);
            fileDto.setSuccess(1);
            fileDto.setMessage("上传成功");
        } catch (IOException e) {
            log.error("upload error", e);
            fileDto.setSuccess(0);
            fileDto.setMessage("上传失败");
            throw new CustomizeException(CustomizeErrorCode.UPLOAD_FILE_ERROR);
        }
        return fileDto;
    }
}
