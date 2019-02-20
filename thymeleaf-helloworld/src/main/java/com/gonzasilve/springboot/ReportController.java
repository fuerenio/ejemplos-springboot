package com.gonzasilve.springboot;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gonzasilve.springboot.report.ITransactionBo;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ITransactionBo transactionBo;

	@RequestMapping(value = "export", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name)
			throws IOException {

		try {
			InputStream is = transactionBo.reporteVentasPdf();

			HttpHeaders header = new HttpHeaders();
			byte[] documentBody = IOUtils.toByteArray(is);
			header.set("Content-Disposition", "attachment; filename=Test_Report.pdf");
			header.setContentLength(documentBody.length);

			return new HttpEntity<byte[]>(documentBody, header);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("error/alert_error");
		model.addObject("errMsg", "No se encuentra el archivo");

		return model;
	}

}
