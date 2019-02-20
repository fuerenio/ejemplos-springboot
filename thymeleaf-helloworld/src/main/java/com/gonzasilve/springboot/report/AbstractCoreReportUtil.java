package com.gonzasilve.springboot.report;

import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public abstract class AbstractCoreReportUtil {
	
	/*
	 *  Returns an jasperReport class by an sourceFileLocation.jasper
	 */
	public JasperReport getJasperReport(String sourceFileLocation) throws JRException{
		try{
			InputStream jasperfile = this.getClass().getResourceAsStream(sourceFileLocation);
			return (JasperReport) JRLoader.loadObject(jasperfile);
		}catch(NullPointerException e){
			throw new JRException("File not found: "+sourceFileLocation);
		}
		
	}
	
	
	/**
	 * Obtiene el dise&ntilde;o de un reporte guardado en un archivo jrxml.
	 * 
	 * @param ubicacionJrxml Ubicaci&oacute;n del archivo jrxml
	 * @return {@link net.sf.jasperreports.engine.design.JasperDesign}
	 * @throws Exception
	 */
	public JasperDesign getDesignFromJrxml(String ubicacionJrxml) throws Exception{
		
		try {
			
			// ------------------------------------------------------
			// Obtenemos el InputStream de la ubicaci&oacute;n 
			// ------------------------------------------------------
			InputStream is = this.getClass().getResourceAsStream(ubicacionJrxml);
			
			// ------------------------------------------
			// Cargamos el dise&ntilde;o del reporte
			// ------------------------------------------
			return JRXmlLoader.load(is);
			
		} catch(Exception e ) {
			e.printStackTrace();
		} 

		return null;
	}
	
	
	/**
	 * Compila un dese&ntilde;o de reporte
	 * 
	 * @param jasperDesign El dese&ntilde;o del reporte
	 * @return {@link net.sf.jasperreports.engine.JasperReport}
	 * @throws Exception
	 */
	public JasperReport getJasperReport(JasperDesign jasperDesign) throws Exception{
		
		try{
			
			// ----------------------------------
			// Compilamos el reporte
			// ----------------------------------
			return JasperCompileManager.compileReport(jasperDesign);
			
		} catch (JRException e ) {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	
	public JasperPrint getJasperPrint( JasperReport reporte, Map<String, Object> parameters ,JRDataSource dataSource) throws JRException{
		return  JasperFillManager.fillReport(reporte, parameters,  dataSource);
	}
	
	
	/*
	 * Returns an JasperPrint by an template file sourceFileName.jasper
	 */
	public JasperPrint getJasperPrint( String sourceFileName, Map<String, Object> parameters ,JRDataSource dataSource) throws JRException{
		return  JasperFillManager.fillReport(sourceFileName, parameters,  dataSource);
	}
	
	
	public void exportToPdf(JasperPrint jasperPrint, String destinationFile) throws JRException{
		JasperExportManager.exportReportToPdfFile(jasperPrint, destinationFile);
	}

	
	// ----------------------------------------
	// Cambia la extension .jasper por .jrxml
	// en la ruta del archivo
	// ----------------------------------------
	public String getTicketJasperXmlFilePath(String jasperFilePath) {
		String path = CoreUtil.getFileFullPath(jasperFilePath);
		String fileName = CoreUtil.getFileName(jasperFilePath);
		return path+fileName+".jrxml";
	}
	
}
