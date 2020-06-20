package com.example.community.controller;

import com.example.community.cache.TagCache;
import com.example.community.dto.QuestionDTO;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "PublishController", description = "发布提问")
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation("编辑页面")
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") @ApiParam("问题ID") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @ApiOperation("返回发布页面")
    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @ApiOperation("发布功能")
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) @ApiParam("问题标题") String title,
            @RequestParam(value = "description", required = false) @ApiParam("问题描述") String description,
            @RequestParam(value = "tag", required = false) @ApiParam("问题标签") String tag,
            @RequestParam(value = "id", required = false) @ApiParam("问题ID") Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        //title == null || title == ""  会导致可以输入空格
        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "输入非法标签：" + invalid);
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
