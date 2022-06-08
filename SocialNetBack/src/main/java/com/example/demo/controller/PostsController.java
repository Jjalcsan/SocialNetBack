package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsuarioRepository;

@RestController
public class PostsController {
	
	@Autowired private UsuarioRepository repoUsu;
	
	@Autowired private PostRepository repoPost;
	
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		return repoPost.findAll();
	}
	
	@GetMapping("/posts/{idUsu}")
	public List<Post> comentariosUsuario(@PathVariable String idUsu){
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		return usuBBDD.getPosts();
	}
	
	@PostMapping("/posts/{idUsu}")
	public Post newPost(@PathVariable String idUsu, @RequestParam String contenido) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		
		Post post = new Post(contenido);
		repoPost.save(post);
		usuBBDD.getPosts().add(post);
		repoUsu.save(usuBBDD);
		
		return post;
	}
	
	@DeleteMapping("/posts/{idUsu}/{idPost}")
	public Post deletePost(@PathVariable String idUsu, @PathVariable int idPost) {
		Usuario usuBBDD = repoUsu.findById(idUsu).orElse(null);
		Post post = repoPost.findById(idPost).orElse(null);
		
		usuBBDD.getPosts().remove(post);
		repoUsu.save(usuBBDD);
		repoPost.delete(post);
		
		return post;
	}

}
