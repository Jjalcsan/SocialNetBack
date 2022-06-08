package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	private static int contador = 1;
	
	@Id
	private int id;
	
	@Column(name = "contenido", nullable = false)
	private String contenido;
	
	@Column(name = "likes", nullable = false)
	private Integer likes;
	
	
	private static Date fecha = new Date();
	
	@Column(name = "fecha", nullable = false)
	private String goodFecha;
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	public Post() {
		this.id = contador;
		this.likes = 0;
		this.goodFecha = getGoodFecha();
		contador++;
	}
	
	public Post(String contenido) {
		this.id = contador;
		this.contenido = contenido;
		this.likes = 0;
		this.goodFecha = getGoodFecha();
		contador++;
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

	public Integer getId() {
		return id;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Metodos adicionales de la clase
	
	
	public String getGoodFecha() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Post.fecha);
	}
}
