package com.example.Organic_Food.Security;


import com.example.Organic_Food.Helper.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig
{
    @Autowired
    CustomUserDetailService myUserDetailService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user_assest/**", "/","/login/","/admin_assest/**","/admin_assest_bill/**","/master/login/","/data/pass/controller/","/user/order/","/new/user/registration/","/user/add/order/details/","/oder/placed/","/admin/payment_order/","/add/product/").permitAll()
                .requestMatchers("/product/","/feature/","/blog/","/add/feature/","/add/product/","/dashboard/","/scheme/","/add/scheme/","/admin/review/status/update/*","/review/report/","/admin/review/update/*","/admin/review/delete/*").hasRole("ADMIN")
                .requestMatchers("/add/product/**","/","/addtocart/","/add/to/card/**","/add/to/card/*","/admin/payment_order/","/review/","/add/review/","/payment/").hasRole("USER")
                .requestMatchers("/dashboard/").hasRole("FARMER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login/")
                .usernameParameter("email")
                .passwordParameter("password")
//                    .loginProcessingUrl("/user/login/")
                .defaultSuccessUrl("/default/", true)
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login/").permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember_me").key("mySecreteKey").tokenValiditySeconds(60 * 15);

        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider()
    {
        System.out.println("My user details service ");
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(myUserDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
//         return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
