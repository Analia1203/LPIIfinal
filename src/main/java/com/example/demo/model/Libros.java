package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "libro")
public class Libros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", nullable = false)
	public Long id;
	
	@Column(name = "nombre", nullable = false, length = 60)
	@Size(min = 4,max = 60 )
	public String nombre;
	
	@Column(name = "autor", nullable = false, length = 60)
	@Size(min = 4,max = 60 )
	public String autor;
	
	@Column(name = "fecha", nullable = false)
	public Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "idGenero", nullable = false)
	public Genero genero;

	public Libros() {
		super();
	}

	public Libros(Long id, String nombre, String autor, Date fecha, Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.fecha = fecha;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	

}
