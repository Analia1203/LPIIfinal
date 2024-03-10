package com.example.demo.model;

public class ReporteLibros {
	
	public Integer cantidad;
	public ReporteLibros() {
		super();
	
	}
	public ReporteLibros(Integer cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
