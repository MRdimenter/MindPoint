package com.Mindpoint.controller;

import com.Mindpoint.domain.Role;
import com.Mindpoint.domain.User;
import com.Mindpoint.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
//аннотация для каждого из методов в данном контроллере проверять перед выполнением метода наличие у пользователя права доступа
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userlist";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) { //получаем юзера сразу из базы данных, без userRepo
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user) {
        user.setUsername(username);


        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); //очищаем роль пользователя

        for (String key : form.keySet()) {
            if (roles.contains(key)) user.getRoles().add(Role.valueOf(key));
        }

        userRepo.save(user);
        return "redirect:/user";
    }
}
