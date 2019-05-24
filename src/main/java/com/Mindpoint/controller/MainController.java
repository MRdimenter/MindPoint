package com.Mindpoint.controller;

import com.Mindpoint.domain.Message;
import com.Mindpoint.domain.User;
import com.Mindpoint.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;


    @GetMapping("/") //слушает запросы по этому пути и возвращает какие то данные
    public String greeting(Map<String, Object> model) { //получает на вход имя и модель

        return "greeting"; //возвращаем
    }

    @GetMapping("/main") //главная страница
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages =  messageRepo.findAll();

        if(filter != null && !filter.isEmpty()) //если фильтр не пустой
            messages = messageRepo.findByTag(filter);
        else messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        ;
        return "main";
    }

    @PostMapping("/main") //обработка запросов из формы
    public String add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        messageRepo.save(new Message(text, tag, user )); //сохраняем в БД

        Iterable<Message> messages =  messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }


}