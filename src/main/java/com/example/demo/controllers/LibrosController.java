package com.example.demo.controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libros;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibrosService;

@Controller
@RequestMapping("/libros")

public class LibrosController {
	@Autowired
	private LibrosService librosService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibros(Model model) {
		List<Libros> listLibros = librosService.getAllLibros();
		model.addAttribute("libros", listLibros);
		return "librosList";
	}
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "librosRegister";
	}
	
	@PostMapping("/register")
	public String createLibros(@RequestParam("nombre") String nombre,
			@RequestParam("autor") String autor,
				@RequestParam("fecha") Date fecha,
						@RequestParam("id") Long id, Model model) {
				Libros libros = new Libros();
				libros.nombre = nombre;
				libros.autor = autor;
				libros.fecha = fecha;
				
				Genero genero = generoService.getGeneroById(id);
				
				libros.genero = genero;
				
				librosService.createLibros(libros);
				
				model.addAttribute("libros", librosService.getAllLibros());
				model.addAttribute("generos", generoService.getAllGeneros());
				
				return "librosList";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Libros libros = librosService.getLibrosById(id);
		
		model.addAttribute("libros", libros);
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "librosEdit";
	}
	
	@PostMapping("/edit")
	public String createLibros(@RequestParam("id") Long id,
			@RequestParam("nombre") String nombre,
			@RequestParam("autor") String autor,
				@RequestParam("fecha") Date fecha,
						@RequestParam("idGenero") Long idGenero, Model model) {
				Libros libros = librosService.getLibrosById(id);
				libros.nombre = nombre;
				libros.autor = autor;
				libros.fecha = fecha;
				
				Genero genero = generoService.getGeneroById(idGenero);
				
				libros.genero = genero;
				
				librosService.createLibros(libros);
				
				model.addAttribute("libros", librosService.getAllLibros());
				model.addAttribute("generos", generoService.getAllGeneros());
				
				return "librosList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLibros(@PathVariable Long id, Model model) {
		librosService.deleteLibros(id);
		
		model.addAttribute("libros", librosService.getAllLibros());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "librosList";
	}

}