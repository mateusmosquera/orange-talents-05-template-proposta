package br.com.zupacademy.mateus.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_propostas:read")
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_cartoes:read")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_propostas:write")
                        .antMatchers(HttpMethod.POST, "/viagem/***").hasAuthority("SCOPE_propostas:write")
                        .antMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
        ).csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}