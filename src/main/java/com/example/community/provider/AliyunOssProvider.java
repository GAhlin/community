package com.example.community.provider;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Component
public class AliyunOssProvider {

    //这里的配置可以在properties或者yml中进行配置
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.endPoint}")
    private  String  endPoint ;

    //文件直接上传的方式，filename 为定义的文件名字
    public String upload(InputStream fileStream, String fileName) {
        // 生成OSSClient
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        //生成唯一的文件名
        String[] fileArray = fileName.split("\\.");
        if (fileArray.length > 1) {
            fileName = UUID.randomUUID().toString() + "." + fileArray[fileArray.length - 1];
        }else{
            throw new CustomizeException(CustomizeErrorCode.UPLOAD_FILE_ERROR);
        }

        try {
            // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
            ossClient.putObject(bucketName, fileName, fileStream);
            // 设置URL过期时间为1年。3600 * 1000 * 24 * 365
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);

            return url.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            if (ossClient != null)
                ossClient.shutdown();
        }
        throw new CustomizeException(CustomizeErrorCode.UPLOAD_FILE_ERROR);
    }

}