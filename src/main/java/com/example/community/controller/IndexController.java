package com.example.community.controller;

import com.example.community.cache.HotTagCache;
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
import java.util.List;

@Api(tags = "IndexController", description = "首页查询")
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @ApiOperation("分页查询问题列表")
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") @ApiParam("页码") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") @ApiParam("每页数量") Integer size,
                        @RequestParam(name = "search", required = false) @ApiParam("搜索问题") String search,
                        @RequestParam(name = "tag", required = false) @ApiParam("热门标签") String tag,
                        @RequestParam(name = "sortName", required = false) @ApiParam("排序名称") String sortName,
                        @RequestParam(value = "sort", required = false) @ApiParam("问题排序") String sort) {
        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        if (sortName==null) {
            sortName = "最新";
            model.addAttribute("sortName", sortName);
        } else {
            model.addAttribute("sortName", sortName);
        }
        return "index";
    }
}