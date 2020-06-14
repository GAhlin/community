package com.example.community.controller;

import com.example.community.dto.PaginationDTO;
import com.example.community.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "IndexController", description = "首页查询")
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation("分页查询问题列表")
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") @ApiParam("页码") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") @ApiParam("每页数量") Integer size) {

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}