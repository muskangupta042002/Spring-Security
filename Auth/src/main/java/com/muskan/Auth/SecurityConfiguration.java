package com.muskan.Auth;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
//this web security configurer, other is method level
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    //WebSecurityConfigurerAdapte
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                        .withUser("blah")
                        .password("blah")
                        .roles("USER");
    }
}
