package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
                    new UsernamePasswordAuthenticationToken(body.getNick(), body.getContra());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getNick());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario user){
        String encodedPass = passwordEncoder.encode(user.getContra());
        user.setContra(encodedPass);
        user = userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }
    
    @GetMapping("/email")
    public boolean existeEmail(@RequestParam String email) {
    	boolean existe = false;
    	
    	for (Usuario u : userRepo.findAll()) {
    		if(u.getEmail().equals(email)){
    			existe = true;
    		}
    	}
    	
    	return existe;
    }
    
    @GetMapping("/nick")
    public boolean existeNick(@RequestParam String nick) {
    	boolean existe = false;
    	
    	for(Usuario u : userRepo.findAll()) {
    		if(u.getNick().equals(nick)) {
    			existe = true;
    		}
    	}
    	
    	return existe;    	
    }

}
