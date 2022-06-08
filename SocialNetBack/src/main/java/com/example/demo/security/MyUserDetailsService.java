package com.example.demo.security;


import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        Optional<Usuario> userRes = userRepo.findById(nick);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with nick = " + nick);
        Usuario user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
        		nick,
                user.getContra(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}