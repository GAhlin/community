package com.example.community.controller;

import com.example.community.dto.CommentCreateDTO;
import com.example.community.dto.CommentDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.service.CommentService;
import com.example.community.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "QuestionController", description = "问题详情")
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @ApiOperation("查询问题详情")
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") @ApiParam("问题ID") Long id, Model model) {

        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
