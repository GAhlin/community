package com.example.community.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "gitee")
@Data
public class GiteeParams {
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
