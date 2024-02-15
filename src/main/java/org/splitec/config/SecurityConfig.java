package org.splitec.config;

import org.splitec.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // Desabilita CSRF (Cross Site Request Forgery)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Define a política de sessão como stateless
        .and()
        .authorizeRequests()
        .antMatchers("/login").permitAll() // Permite o acesso não autenticado ao endpoint de login
        .anyRequest().authenticated() // Todos os outros requests precisam ser autenticados
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT antes do filtro de autenticação padrão
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Define o encoder para senhas (necessário mesmo que não use diretamente, para evitar erro de bean ausente)
  }
}