package web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
//    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям
//
//    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
//        this.userDetailsService = userDetailsService;
//        this.successUserHandler = successUserHandler;
//    }
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
//    }
//

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ADMIN").password("1").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("USER").password("1").roles("USER");
        auth.inMemoryAuthentication().withUser("XODAVIT").password("1").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("VIP").password("1").roles("VIP");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выключаем кросс-доменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable(); //- попробуйте выяснить сами, что это даёт

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()

                .antMatchers("/").permitAll() // доступность всем

                //страница аутентификации доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user").access("hasAnyRole('USER')") // разрешаем входить на /user пользователям с ролью User
                .antMatchers("/admin").access("hasAnyRole('ADMIN')")
                .antMatchers("/vip").access("hasAnyRole('VIP')")
                .anyRequest().authenticated();

    }

    // Необходимо для шифрования паролей
    // В данном примере не используется, отключен
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
