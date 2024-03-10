package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ReporteLibros;
import com.example.demo.service.LibrosService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
@Controller
@RequestMapping("/report")
public class ReporteController{
	@Autowired
	private LibrosService librosService;
	@GetMapping("/reporte")
	public void report(HttpServletResponse response) throws JRException, IOException {

		// 1. Acceder al reporte 
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/librosRegistrados.jasper");		

		// 2. Preparadar los datos 
		Map<String, Object> params = new HashMap<>();
		
		params.put("usuario", "Analia Fiestas");
				
		List<ReporteLibros> cantidadList = new ArrayList<ReporteLibros>();
    	ReporteLibros cantidad = new ReporteLibros();
    	cantidad.setCantidad(librosService.getCantidad());
    	cantidadList.add(cantidad);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cantidadList);
	
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		// 3. Configuracion 
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_final.pdf");
				

		// 4. Exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}}
		
	/*@Autowired
	private LibrosService librosService;
	
	@GetMapping("/report")
	public void report(HttpServletResponse response) throws JRException, IOException {

		// 1. Acceder al reporte 
		InputStream jasperStream = this.getClass().getResourceAsStream("/MyReports/Simple_Blue.jasper");		

		// 2. Preparadar los datos 
		Map<String, Object> params = new HashMap<>();
		
		params.put("usuario", "Analia Fiestas");
    	List<ReporteLibros> cantidadList = new ArrayList<ReporteLibros>();
    	ReporteLibros cant = new ReporteLibros();
    	
    	cant.setCant(librosService.getCantidad());
    	cantidadList.add(cant);
			
		List<Reporte> listLibros = new ArrayList<>();
		
		listLibros.add(new Reporte("1", "Libro1", "disney"));
	

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLibros);
	
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		// 3. Configuracion 
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_reporteLibros.pdf");
				

		// 4. Exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
	}
*/

