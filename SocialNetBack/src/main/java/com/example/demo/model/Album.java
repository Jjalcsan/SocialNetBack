package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "album")
public class Album {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	private static int contador = 1;
	
	@Id
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@OneToMany
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Foto> fotos;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	/**
	 * Constructor vacio
	 */
	public Album() {
		this.id = contador;
		this.fotos = new ArrayList<>();	
		contador++;
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos vacia
	 * @param nombre
	 * @param usuario
	 */
	public Album(String nombre) {
		this.id = contador;
		this.nombre = nombre;
		this.fotos = new ArrayList<>();
		contador++;
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos
	 * @param nombre
	 * @param fotos
	 * @param usuario
	 */
	public Album(String nombre, List<Foto> fotos) {
		this.id = contador;
		this.nombre = nombre;
		this.fotos = fotos;
		contador++;
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

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
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
		return Objects.equals(nombre, other.nombre);
	}

}
