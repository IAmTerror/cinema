package fr.iat.cinema.configuration;

import fr.iat.cinema.service.JpaUserDetailsService;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private JpaUserDetailsService jpaUserDetailsService;
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public WebSecurityConfig(JpaUserDetailsService cinemaUserDetailsService)
    {   this.jpaUserDetailsService = cinemaUserDetailsService;

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // TODO : configurer toutes les routes restreintes, notamment pour l'admin
                // TODO : changer les commentaires en doc java
                .authorizeRequests()
                // pages acessibles à l'ensemble de l'application
                    .antMatchers("/", "/webjars/**", "/css/*").permitAll()
                // pages dont l'accès est restreint à certains utilisateurs
                    .antMatchers("/film/**").hasAuthority("ADMIN")
                    .antMatchers("/recovery/**").hasAnyAuthority("ADMIN", "USERS")
                // .anyRequest().authenticated() = pages qui n'ont pas un accès restreint
                // (hasAuthority ou hasAnyAuthority) et donc acessibles pour tous les utilisateurs authentifiés
                // c'est à dire, toutes les pages autres que celles qui sont restreintes
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
        // avoid error 403 with @PostMapping
//                .and()
//                    .csrf().disable();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("recup")
//                .password("recup")
//                .roles("ADMIN","USER")
//                .authorities("WITHDRAW","DEPOSIT", "ADMIN");
        auth.userDetailsService(jpaUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
