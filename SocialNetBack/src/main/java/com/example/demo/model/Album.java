package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "album")
public class Album {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@OneToMany
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Foto> fotos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	/**
	 * Constructor vacio
	 */
	public Album() {	
		this.fotos = new ArrayList<>();	
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos vacia
	 * @param nombre
	 * @param usuario
	 */
	public Album(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.fotos = new ArrayList<>();
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos
	 * @param nombre
	 * @param fotos
	 * @param usuario
	 */
	public Album(String nombre, List<Foto> fotos, Usuario usuario) {
		this.nombre = nombre;
		this.fotos = fotos;
		this.usuario = usuario;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(usuario, other.usuario);
	}

}
