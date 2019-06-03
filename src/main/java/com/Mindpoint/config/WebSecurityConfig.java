package com.Mindpoint.config;

import com.Mindpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //авторизация
                .antMatchers("/", "/registration", "/static/**", "*/activate/*").permitAll() //полный доступ для главной страницы
                .anyRequest().authenticated() //для всех остальных запросов требуем авторазицию
                .and()
                .formLogin() //включаем форму логин
                .loginPage("/login") //страница логина находится на таком мепинге
                .permitAll() //разрешаем этим пользоваться всем
                .and()
                .logout() //выход из профиля
                .permitAll(); //разрешаем пользоваться всем
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)//нужен для того что бы менеджер мог ходить в БД и искать там пользователей
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); //шифрует пароли что бы они не хранились в явном виде
    }
}