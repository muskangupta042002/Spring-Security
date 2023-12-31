package com.muskan.Auth;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//this web security configurer, other is method level
//create a bean of type password Encoder
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;
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
        // auth.inMemoryAuthentication()
        //                 .withUser("blah")
        //                 .password("blah")
        //                 .roles("USER")
        //                 .and()
        //                 .withUser("foo")
        //                 .password("foo")
        //                 .roles("ADMIN");

        auth.jdbcAuthentication()
            .dataSource(dataSource)

            // .withDefaultSchema()
            // .withUser(
            //     User.withUsername("user")
            //         .password("pass")
            //         .roles("USER")
            // )
            // .withUser(
            //     User.withUsername("admin")
            //         .password("pass")
            //         .roles("ADMIN")
            // )
            .usersByUsernameQuery("select username,password,enabled "
                                        + "from users " 
                                        + "where username = ?"          
            ) 
            .authoritiesByUsernameQuery("select username,authority" 
                                + "from authorities"
                                + "where username = ?"
                                )
        ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //it does nothing
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //goes from most restricted to least restricted
        http.authorizeRequests()
                        .antMatchers("/admin").hasRole("ADMIN")
                        .antMatchers("/user").hasAnyRole("USER","ADMIN")
                        .antMatchers("/").permitAll()
                        .and().formLogin();
    }
}
