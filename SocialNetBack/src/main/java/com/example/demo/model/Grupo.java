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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
public class Grupo {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Constructores
	
	public Grupo () {
		this.usuarios = new ArrayList<>();
	}
	
	public Grupo (String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarios = new ArrayList<>();
	}
	
	public Grupo (String titulo, String descripcion, List<Usuario> usuarios) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarios = usuarios;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getId() {
		return id;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

}
