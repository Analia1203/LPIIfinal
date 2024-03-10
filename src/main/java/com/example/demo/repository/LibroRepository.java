package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Date;

import com.example.demo.model.Libros;

public interface LibroRepository extends JpaRepository <Libros, Long>{
	
/*@Query("select l from Libro l where l.fechaPublicacion >= :fecha")
    public List<Libros> findAllWithCreationDateTimeBefore(
      @Param("fecha") Date  fecha);*/
}
