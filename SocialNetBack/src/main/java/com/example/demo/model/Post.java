package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Post {

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "contenido", nullable = false)
	private String contenido;
	
	@Column(name = "likes", nullable = false)
	private Integer likes;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	public Post() {}
	
	public Post(String contenido, Usuario usuario) {
		this.contenido = contenido;
		this.usuario = usuario;
		this.likes = 0;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Setters y Getters
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(id, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id) && Objects.equals(usuario, other.usuario);
	}
	
}
