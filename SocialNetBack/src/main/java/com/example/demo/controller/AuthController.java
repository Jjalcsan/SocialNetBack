package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JWTUtil;
import com.example.demo.model.Usuario;
import com.example.demo.model.LoginCredentials;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario usuario){
        String encodedPass = passwordEncoder.encode(usuario.getContra());
        usuario.setContra(encodedPass);
        usuario = userRepo.save(usuario);
        String token = jwtUtil.generateToken(usuario.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }

}
