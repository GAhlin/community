package com.example.community.cache;

import com.example.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();

        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java", "c/c#", "python", "golang", "c#", "php", "javascript", "scala", "html", "html5", "css", "node·js", "objective-c", "typescript", "shell", "swift", "sass", "ruby", "bash", "less", "asp·net", "lua", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "springmvc", "springboot", "springcloud", "mybatis", "bootstrap", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts", "laravel"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "elasticsearch", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("intellij-idea", "git", "github", "vscode", "vim", "sublime", "eclipse", "xcode", "maven", "ide", "svn", "android-studio", "atom", "emacs", "textmate", "hg"));
        tagDTOS.add(tool);

        TagDTO other = new TagDTO();
        other.setCategoryName("其他");
        other.setTags(Arrays.asList("找bug", "测试", "冒泡", "交友", "生活", "电影", "音乐", "读书", "美食", "游戏", "科技", "数码", "理财"));
        tagDTOS.add(other);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        //为空不能提交
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

    public static void main(String[] args) {
        int i = (5 - 1) >>> 1;
        System.out.println(i);
    }
}
