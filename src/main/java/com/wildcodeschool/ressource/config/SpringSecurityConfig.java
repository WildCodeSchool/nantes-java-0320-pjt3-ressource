package com.wildcodeschool.ressource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // TODO : authenticationManagerBean
    // https://stackoverflow.com/a/21639553
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // https://openclassrooms.com/en/courses/5683681-secure-your-web-application-with-spring-security/6695821-configure-role-based-access-control
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/company").permitAll()
                .antMatchers("/results").permitAll()
                .antMatchers("/results/filter").permitAll()
                .antMatchers("/results/more/{filter}/{all}").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/upload").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/photo/**").permitAll()
                .antMatchers("/image/**").permitAll()
                .antMatchers("/fragments/**").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/admin/admin").hasRole("ALL")
                .antMatchers("/admin/admin/search").hasRole("ALL")
                .antMatchers("/admin/profile").hasAnyRole("ALL", "PRODUCTS", "PAGES")
                .antMatchers("/admin/admin/create").hasRole("ALL")
                .antMatchers("/admin/companies").hasAnyRole("ALL", "PAGES")
                .antMatchers("/admin/company/*").hasAnyRole("ALL", "PAGES")
                .antMatchers("/admin/products").hasAnyRole("ALL", "PRODUCTS", "PAGES")
                .antMatchers("/admin/products/search").hasAnyRole("ALL", "PRODUCTS", "PAGES")
                .antMatchers("/admin/products/delete").hasAnyRole("ALL", "PRODUCTS", "PAGES")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/admin/profile", true)
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
