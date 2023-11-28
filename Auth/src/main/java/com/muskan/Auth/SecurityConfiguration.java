package com.muskan.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//this web security configurer, other is method level
//create a bean of type password Encoder
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    //WebSecurityConfigurerAdapte
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //         .withUser("blah")
    //         .password(passwordEncoder().encode("blah"))
    //         .roles("USER");
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                        .withUser("blah")
                        .password("blah")
                        .roles("USER");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //it does nothing
    }
}
