package config;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import security.CustomAccessDeniedHandler;
import security.CustomLoginSuccessHandler;
import security.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Setter(onMethod_ = @Autowired)
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private CustomLoginSuccessHandler customLoginSuccessHandler;
    private CustomUserDetailsService  customUserDetailsService;


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')")
            .antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/resources/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            //.successHandler(customLoginSuccessHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID","remember-me")
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(customAccessDeniedHandler)
            .and()
            .rememberMe()
            .key("ggobuk")
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(604800)
            .and()
            .httpBasic();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

}
