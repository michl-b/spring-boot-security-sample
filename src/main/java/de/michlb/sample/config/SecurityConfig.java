package de.michlb.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by michael on 04.06.17.
 */
@Profile("secure")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.user.name}")
    private String defaultUser;
    @Value("${security.user.password}")
    private String defaultPassword;
    @Value("${security.user.role}")
    private String defaultRole;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/health").permitAll() // allow access to /heath for monitoring
                .antMatchers("/**")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("swagger").password("secure").roles("USER");
        auth.inMemoryAuthentication().withUser("api").password("secure").roles("USER");
        auth.inMemoryAuthentication().withUser(defaultUser).password(defaultPassword).roles(defaultRole);
    }
}
