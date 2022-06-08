package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Grupo;
import com.example.demo.model.Usuario;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.repository.UsuarioRepository;

@RestController
public class GruposController {
	
	@Autowired
	private GrupoRepository repoGrup;
	
	@Autowired
	private UsuarioRepository repoUsu;
	
	@GetMapping("/grupos")
	public List<Grupo> findAll(){
		return repoGrup.findAll();
	}
	
	@GetMapping("/grupos/{idGrup}")
	public Grupo findById(@PathVariable int idGrup) {
		Grupo grupoBBDD = repoGrup.findById(idGrup).orElse(null);
		
		return grupoBBDD;
	}
	
	@GetMapping("/grupos/{idGrup}/{idUsu}")
	public Grupo addUsuario(@PathVariable int idGrup, @PathVariable String idUsu) {
		Grupo grupoBBDD = repoGrup.findById(idGrup).orElse(null);
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		grupoBBDD.getUsuarios().add(usuBBDD);
		repoGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@PostMapping("/grupos/{idUsu}")
	public Grupo newGrupo(@PathVariable String idUsu, @RequestParam String titulo, @RequestParam String descripcion) {
		Grupo grupo = new Grupo(titulo, descripcion);
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		grupo.getUsuarios().add(usuBBDD);
		repoGrup.save(grupo);
		
		return grupo;
	}
	
	@PostMapping("/grupos/{idGrup}/{idUsu}")
	public Grupo delUsuario(@PathVariable int idGrup, @PathVariable String idUsu) {
		Grupo grupoBBDD = repoGrup.findById(idGrup).orElse(null);
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		grupoBBDD.getUsuarios().remove(usuBBDD);
		repoGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@PutMapping("/grupos/{idGrup}")
	public Grupo changeGrupo(@PathVariable int idGrup, @RequestParam String titulo, @RequestParam String descripcion) {
		Grupo grupoBBDD = repoGrup.findById(idGrup).orElse(null);
		grupoBBDD.setTitulo(titulo);
		grupoBBDD.setDescripcion(descripcion);
		repoGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@DeleteMapping("/grupos/{idGrup}")
	public Grupo delGrupo(@PathVariable int idGrup) {
		Grupo grupoBBDD = repoGrup.findById(idGrup).orElse(null);
		repoGrup.delete(grupoBBDD);
		
		return grupoBBDD;
	}

}
