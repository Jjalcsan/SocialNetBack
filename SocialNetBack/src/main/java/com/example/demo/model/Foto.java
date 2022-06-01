package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Foto {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "ruta", nullable = false)
	private String ruta;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	/**
	 * Constructor vacio
	 */
	public Foto() {}
	
	/**
	 * Constructor con el titulo y la ruta de la foto
	 * @param titulo
	 * @param ruta
	 */
	public Foto(String titulo, String ruta) {
		this.titulo = titulo;
		this.ruta = ruta;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public Integer getId() {
		return Id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(Id, ruta, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(ruta, other.ruta) && Objects.equals(titulo, other.titulo);
	}
	
}
