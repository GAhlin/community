package com.example.community.controller;

import com.example.community.config.GiteeParams;
import com.example.community.config.GithubParams;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GiteeUser;
import com.example.community.dto.GithubUser;
import com.example.community.model.User;
import com.example.community.provider.GiteeProvider;
import com.example.community.provider.GithubProvider;
import com.example.community.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Api(tags = "AuthorizeController", description = "登录管理")
@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GiteeProvider giteeProvider;

    @Autowired
    private GithubParams githubParams;

    @Autowired
    private GiteeParams giteeParams;

    @Autowired
    private UserService userService;

    private AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

    @ApiOperation("获取Github返回的信息")
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") @ApiParam("code") String code,
                           @RequestParam(name = "state") @ApiParam("state") String state,
                           HttpServletResponse response) {
        accessTokenDTO.setClient_id(githubParams.getClient_id());
        accessTokenDTO.setClient_secret(githubParams.getClient_secret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubParams.getRedirect_uri());
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            //登录成功，写cookie和session
            return "redirect:/";
        } else {
            //登录失败，重新登录
            log.error("callback get github error, {}", githubUser);
            return "redirect:/";
        }
    }

    @GetMapping("/giteeCallback")
    public String giteeCallback(@RequestParam("code") String code,
                                @RequestParam("state") String state,
                                HttpServletResponse response) {
        accessTokenDTO.setClient_id(giteeParams.getClient_id());
        accessTokenDTO.setClient_secret(giteeParams.getClient_secret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(giteeParams.getRedirect_uri());
        accessTokenDTO.setState(state);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getGiteeUser(accessToken);
        if (giteeUser != null && giteeUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            //设置user信息
            user.setToken(token);
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setAvatarUrl(giteeUser.getAvatarUrl());
            user.setBio(giteeUser.getBio());
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            //登录失败，重新登录
            log.error("callback get gitee error, {}", giteeUser);
            return "redirect:/";
        }
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
