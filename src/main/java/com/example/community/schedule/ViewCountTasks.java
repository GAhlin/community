package com.example.community.schedule;

import com.alibaba.fastjson.JSON;
import com.example.community.mapper.QuestionMapper;
import com.example.community.model.Question;
import com.example.community.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.example.community.constants.RedisConstants.REDIS_VIEW_COUNT;

/**
 * @author ahlin
 * @date 2021/9/15 17:39
 */
@Component
@Slf4j
public class ViewCountTasks {

    @Autowired
    private RedisService redisService;

    @Autowired
    private QuestionMapper questionMapper;

    @Scheduled(cron = "0 0 3 * * ?")// cron 表达式，每天凌晨3点执行
    public void viewCountSchedule() {
        log.info("浏览数同步定时任务开始执行...");
        Set<Object> key = redisService.hashKeys(REDIS_VIEW_COUNT);
        Question question = new Question();
        for (Object k : key) {
            Long k1 = JSON.parseObject(String.valueOf(k), Long.class);
            Object value = redisService.getHash(REDIS_VIEW_COUNT, k1.toString());
            Integer viewCount = JSON.parseObject(String.valueOf(value), Integer.class);
            question.setId(k1);
            question.setViewCount(viewCount);
            questionMapper.updateByPrimaryKeySelective(question);
            redisService.delHash(REDIS_VIEW_COUNT, k);
        }
        log.info("浏览数同步定时任务结束...");
    }
}
