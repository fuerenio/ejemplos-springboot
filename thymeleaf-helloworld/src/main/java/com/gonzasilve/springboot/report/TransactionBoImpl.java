package com.gonzasilve.springboot.report;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class TransactionBoImpl implements ITransactionBo{
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionBoImpl.class);
	
	private String reporteJasperFilePath;
	
	@SuppressWarnings("unchecked")
	public InputStream  reporteVentasPdf() throws Exception{
		Locale defaultLocale = new Locale("es","MX"); 
		Locale.setDefault(defaultLocale);
		
		StringBuilder log = new StringBuilder();
		
		try {
			ReporteRowsWrapper reporteVentas  = new ReporteRowsWrapper();
			
			List<ReportDto> transactionReportDtoList = new ArrayList<>();
			transactionReportDtoList.add(new ReportDto(1,453.2, new Date()));
			transactionReportDtoList.add(new ReportDto(2,4554.64, new Date()));
			transactionReportDtoList.add(new ReportDto(3,8432.86, new Date()));
			transactionReportDtoList.add(new ReportDto(4,234.6, new Date()));
			reporteVentas.setRows(transactionReportDtoList);
			log.append("\nTotal de transacciones para el reporte: "+transactionReportDtoList.size());

			List<ReporteRowsWrapper> listReporte = new ArrayList<ReporteRowsWrapper>();
			listReporte.add(reporteVentas);
			
			 InputStream inputStream = null;

			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
			byte[] data;
		
			data = IOUtils.toByteArray(getClass().getResourceAsStream("/reports/logo-280x44.png"));
			Image logo  = (new ImageIcon(data)).getImage();
			
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("fecha", dateFormat.format(new Date()));
			parameters.put("logo", logo);
			parameters.put("label_id","id Transaccion" );
			parameters.put("label_monto", "Monto");
			parameters.put("label_fecha", "Fecha");
			
			ReportsUtils report = new ReportsUtils();
			JasperReport jreport = report.getJasperReport(getReporteJasperFilePath());
			log.append("\nPath del reporte: "+getReporteJasperFilePath());
			byte[] fichero = JasperRunManager.runReportToPdf( jreport, parameters, new JRBeanCollectionDataSource(listReporte) );
			inputStream = new ByteArrayInputStream(fichero);
		    
			System.out.println("\n\n"+log.toString()+"\n\n");
			return inputStream;
		} catch (Exception e) {
			//Error obteniendo recursos
			e.printStackTrace();
			logger.info("Exception");
		}
		
		return null;
	}
	
	
	public String getReporteJasperFilePath() {
		this.reporteJasperFilePath = "/reports/reporte.jasper";
		return reporteJasperFilePath;
	}
	
}
