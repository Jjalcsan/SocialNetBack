package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;



public class UsuariosController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@GetMapping("/user")
	public Usuario getUserDetails(){
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepo.findByEmail(email).get();
		
	}
	
	@GetMapping("/registrados")
	public List<Usuario> registrados() {
		
		return userRepo.findAll();
		
	}
}
