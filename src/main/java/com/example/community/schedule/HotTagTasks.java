package com.example.community.schedule;

import com.example.community.cache.HotTagCache;
import com.example.community.mapper.QuestionMapper;
import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 4)//四个小时
    public void hotTagSchedule() {
        log.info("开始时间： {}", new Date());
        int offset = 0;
        int limit = 20;
        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            //将问题提取出来
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
        log.info("结束时间： {}", new Date());
    }
}