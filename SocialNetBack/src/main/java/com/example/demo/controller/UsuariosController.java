package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RestController
public class UsuariosController {

	@Autowired
	private UsuarioRepository repoUsu;
	
	@GetMapping("/usuarios/{idUsu}")
	public Usuario getUserDetails(@PathVariable String idUsu){		
		return repoUsu.findById(idUsu).orElse(null);
		//Añadir excepcion		
	}
	
	@GetMapping("/usuarios")
	public List<Usuario> registrados() {		
		return repoUsu.findAll();		
	}
	
	@PutMapping("/usuarios/{idUsu}")
	public Usuario editarInfo(@PathVariable String idUsu, @RequestBody Usuario usuario) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		//añadir excepcion
		usuBBDD.setContra(usuario.getContra());
		usuBBDD.setNombre(usuario.getNombre());
		usuBBDD.setApellidos(usuario.getApellidos());
		usuBBDD.setEmail(usuario.getEmail());
		usuBBDD.setDireccion(usuario.getDireccion());
		usuBBDD.setTelefono(usuario.getTelefono());
		usuBBDD.setEdad(usuario.getEdad());
		usuBBDD.setFotoPerfil(usuario.getFotoPerfil());
		repoUsu.save(usuBBDD);
		
		return usuBBDD;
	}
	
	@DeleteMapping("/usuarios/{idUsu}")
	public Usuario borrarUsuario(@PathVariable String idUsu) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		repoUsu.delete(usuBBDD);
		
		return usuBBDD;
	}
	
	@PostMapping("/usuarios/{idUsu}")
	public Usuario seguirUsuario(@PathVariable String idUsu, @RequestBody Usuario usuario) {
		Usuario usuBBDDSeguidor = repoUsu.findById(idUsu).orElse(null);
		Usuario usuBBDDSeguido = repoUsu.findById(usuario.getNick()).orElse(null);
		
		usuBBDDSeguidor.getSeguidos().add(usuBBDDSeguido);
		usuBBDDSeguido.getSeguidores().add(usuBBDDSeguidor);
		
		repoUsu.save(usuBBDDSeguidor);
		repoUsu.save(usuBBDDSeguido);
		
		return usuBBDDSeguido;
	}
	
}
