package com.example.demo.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UsuarioRepository userRepo;
    @Autowired private JWTFilter filter;
    @Autowired private MyUserDetailsService uds;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/albumes").hasRole("USER")
                .antMatchers("/albumes/{idUsu}").hasRole("USER")
                .antMatchers("/albumes/{idAlb}").hasRole("USER")
                .antMatchers("/albumes/{idUsu}/{idAlb}").hasRole("USER")
                .antMatchers("/fotos/{idAlb}").hasRole("USER")
                .antMatchers("/fotos/{idAlb}/{idFot}").hasRole("USER")
                .antMatchers("/grupos").hasRole("USER")
                .antMatchers("/grupos/{idGrup}").hasRole("USER")
                .antMatchers("/grupos/{idGrup}/{idUsu}").hasRole("USER")
                .antMatchers("/grupos/{idUsu}").hasRole("USER")
                .antMatchers("/posts").hasRole("USER")
                .antMatchers("/posts/{idUsu}").hasRole("USER")
                .antMatchers("/posts/{idUsu}/{idPost}").hasRole("USER")
                .antMatchers("/usuarios/{idUsu}").hasRole("USER")
                .antMatchers("/usuarios").hasRole("USER")
                .antMatchers("/email").hasRole("USER")
                .antMatchers("/nick").hasRole("USER")
                
                
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}