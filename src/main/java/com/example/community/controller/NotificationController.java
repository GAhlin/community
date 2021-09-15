package com.example.community.controller;

import com.example.community.annotation.WebLog;
import com.example.community.dto.NotificationDTO;
import com.example.community.enums.NotificationTypeEnum;
import com.example.community.model.User;
import com.example.community.service.NotificationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "NotificationController", description = "通知功能")
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    @WebLog(description = "通知功能")
    public String profile(HttpServletRequest request,@PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }
        if (id == 0) {
            this.notificationService.readAll(user.getId());
            return "redirect:/profile/replies";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == (notificationDTO.getType()) ||
                NotificationTypeEnum.REPLY_QUESTION.getType() ==(notificationDTO.getType())) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        }
        return "profile";
    }
}
