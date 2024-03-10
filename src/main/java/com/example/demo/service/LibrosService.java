package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Libros;
import com.example.demo.repository.LibroRepository;

@Service
public class LibrosService {
	@Autowired
	private LibroRepository librosRepository;
	
	public List<Libros> getAllLibros() {
		return librosRepository.findAll();
	}
	
	public Libros createLibros (Libros libros) {
		return librosRepository.save(libros);
	}
	
	public void deleteLibros(Long id) {
		librosRepository.deleteById(id);
	}
	
	public Libros getLibrosById(Long id) {
	return librosRepository.findById(id).orElse(null);
	}
	
	public int getCantidad() {
		int cant =0;
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.MONTH, -6);
		List<Libros> ult6=this.getAllLibros();
		for (int i=0; i<ult6.size(); i++) {
		    if (ult6.get(i).getFecha().after(fecha.getTime()))
		         ult6.remove(i);
		}
cant= ult6.size();		
		return cant;

	}
}
