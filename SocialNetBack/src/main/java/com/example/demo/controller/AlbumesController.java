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

import com.example.demo.model.Album;
import com.example.demo.model.Foto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.FotoRepository;
import com.example.demo.repository.UsuarioRepository;

@RestController
public class AlbumesController {
	
	@Autowired
	private AlbumRepository repoAlb;
	
	@Autowired
	private FotoRepository repoFot;
	
	@Autowired
	private UsuarioRepository repoUsu;
	
	@GetMapping("/albumes")
	public List<Album> findAll(){
		return repoAlb.findAll();
	}
	
	@GetMapping("/albumes/{idAlb}")
	public Album findAlbumById(@PathVariable int idAlb) {
		 return repoAlb.findById(idAlb).orElse(null);
	}
	
	@PostMapping("/albumes/{idUsu}")
	public Album newAlbum(@PathVariable String idUsu, @RequestParam String nombre) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		Album album = new Album(nombre);
		usuBBDD.getAlbumes().add(album);
		repoUsu.save(usuBBDD);
		
		return album;
	}
	
	@PutMapping("/albumes/{idAlb}")
	public Album newNombreAlbum(@PathVariable int idAlb, @RequestParam String newNombre) {
		Album albBBDD = repoAlb.findById(idAlb).orElse(null);
		albBBDD.setNombre(newNombre);
		repoAlb.save(albBBDD);
		
		return albBBDD;
	}
	
	@DeleteMapping("/albumes/{idUsu}/{idAlb}")
	public Album deleteAlbum(@PathVariable int idAlb, @PathVariable String idUsu) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		Album albBBDD = repoAlb.findById(idAlb).orElse(null);
		usuBBDD.getAlbumes().remove(albBBDD);
		repoUsu.save(usuBBDD);
		repoAlb.delete(albBBDD);
		
		return albBBDD;
	}
	
	@GetMapping("/fotos/{idAlb}")
	public List<Foto> findAllOfAlbum(@PathVariable int idAlb) {
		Album albBBDD = repoAlb.findById(idAlb).orElse(null);
		
		return albBBDD.getFotos();
	}
	
	@GetMapping("/fotos/{idAlb}/{idFot}")
	public Foto findFotoById(@PathVariable int idFot) {
		Foto foto = repoFot.findById(idFot).orElse(null);
		
		return foto;
	}
	
	@PostMapping("/fotos/{idAlb}")
	public Foto newFoto(@PathVariable int idAlb, @RequestParam String titulo, @RequestParam String ruta) {
		Album albBBDD = repoAlb.findById(idAlb).orElse(null);
		Foto foto = new Foto(titulo, ruta);
		repoFot.save(foto);
		albBBDD.getFotos().add(foto);
		repoAlb.save(albBBDD);
		
		return foto;
	}
	
	@DeleteMapping("/fotos/{idAlb}/{idFot}")
	public Foto deleteFoto(@PathVariable int idAlb, @PathVariable int idFot) {
		Album albBBDD = repoAlb.findById(idAlb).orElse(null);
		Foto fotoBBDD = repoFot.findById(idFot).orElse(null);
		albBBDD.getFotos().remove(fotoBBDD);
		repoAlb.save(albBBDD);
		repoFot.delete(fotoBBDD);
		
		return fotoBBDD;
	}
	
	
	

}
