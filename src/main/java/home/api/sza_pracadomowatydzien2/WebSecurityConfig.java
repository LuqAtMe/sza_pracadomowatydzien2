package home.api.sza_pracadomowatydzien2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User adminUser = new User(
                "Jan",
                getPasswordEncoder().encode("123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        User userUser = new User(
                "Milosz",
                getPasswordEncoder().encode("123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );

        auth.inMemoryAuthentication().withUser(adminUser);
        auth.inMemoryAuthentication().withUser(userUser);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAll").permitAll()
                .antMatchers("/forUsers").hasAnyRole("USER", "ADMIN")
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/end").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/forAll")
                .and()
                .logout().logoutSuccessUrl("/end");

    }
}
