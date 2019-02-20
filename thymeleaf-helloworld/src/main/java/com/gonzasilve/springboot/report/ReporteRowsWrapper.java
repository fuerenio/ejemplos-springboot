package com.gonzasilve.springboot.report;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteRowsWrapper {
	private List<ReportDto> rows;

	public List<ReportDto> getRows() {
		return rows;
	}

	public void setRows(List<ReportDto> rows) {
		this.rows = rows;
	}
	
	public JRDataSource getTransacciones(){       
        return new JRBeanCollectionDataSource(rows);   
    }
	
}
