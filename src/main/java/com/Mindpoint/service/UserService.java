package com.Mindpoint.service;

import com.Mindpoint.domain.Role;
import com.Mindpoint.domain.User;
import com.Mindpoint.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    public UserService(UserRepo userRepo) { //вместо @Autowired
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        //если нашли учетную запись в БД, нужно сообщить об этом пользователю
        if (userFromDb != null) {
            return false; //пользователь найден
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);


        if (!StringUtils.isEmpty(user.getEmail())) { //StringUtils.isEmpty - проверяет не равно null и не пустые
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Mindpoint \n" +
                            "Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null); //означает что пользователь подтвердил свой почтовый ящик
        userRepo.save(user);
        return true;
    }
}
