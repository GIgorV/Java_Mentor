package ru.gigorv.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.gigorv.web.service.UserDetailsServiceImpl;
import ru.gigorv.web.service.UserService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    //LoginSuccessHandler
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // токен безопасности CSRF для предоставления доступа только авторизованным пользователям.
                .authorizeRequests() // делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().formLogin()  // Spring сам подставит свою логин форму
                .successHandler(successUserHandler) // подключаем наш SuccessHandler для перенеправления по ролям
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        // from SpringMVC_Security_Example
//                .antMatchers("/").permitAll() // доступность всем
//                .antMatchers("/user").access("hasAnyRole('ROLE_USER')") // разрешаем входить на /user пользователям с ролью User
//                .and().formLogin()  // Spring сам подставит свою логин форму
//                .successHandler(successUserHandler); // подключаем наш SuccessHandler для перенеправления по ролям

        //from spring-security
//        http.formLogin()
//                .loginPage("/login") // указываем страницу с формой логина
//                .successHandler(new SuccessUserHandler()) //указываем логику обработки при логине
//                .loginProcessingUrl("/login") // указываем action с формы логина
//                .usernameParameter("j_username") // Указываем параметры логина и пароля с формы логина
//                .passwordParameter("j_password")
//                .permitAll(); // даем доступ к форме логина всем

//        http.logout()
//                .permitAll() // разрешаем делать логаут всем
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // указываем URL логаута
//                .logoutSuccessUrl("/login?logout") // указываем URL при удачном логауте
//                .and().csrf().disable(); //выключаем кроссдоменную секьюрность (на этапе обучения неважна)
//
//        http
//                .authorizeRequests() // делаем страницу регистрации недоступной для авторизированных пользователей
//                .antMatchers("/login").anonymous() //страницы аутентификаци доступна всем
//                .antMatchers("/hello").access("hasAnyRole('ADMIN')").anyRequest().authenticated();// защищенные URL
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
