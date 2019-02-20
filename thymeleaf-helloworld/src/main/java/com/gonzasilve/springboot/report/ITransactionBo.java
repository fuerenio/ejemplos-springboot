package com.gonzasilve.springboot.report;

import java.io.InputStream;

public interface ITransactionBo {

		
	/**
	 * Crea el reporte de ventas en PDF
	 * @return regresa el flujo del reporte 
	 * @throws Exception
	 */
	public InputStream  reporteVentasPdf() throws Exception;
	
}
