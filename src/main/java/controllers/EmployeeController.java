package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dao.EmployeeDao;
import models.Employee;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao empDao;
	

	private ModelAndView mv = new ModelAndView();
	
	private Calendar calender=Calendar.getInstance();
	
	private Date date=new Date();
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id=0;
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String role = request.getParameter("role");
		boolean status = false;
		List<Employee> employees = null;

		status = empDao.insert(new Employee(id,fname, lname, email, role));
		if (status == true) {
			employees = empDao.getAll();
			mv.setViewName("index.jsp");
			mv.addObject("employees", employees);
			System.out.println("success");
		} else {
			employees = empDao.getAll();
			mv.setViewName("index.jsp");
			mv.addObject("employees", employees);
			System.out.println("fail");
		}

		return mv;

	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String email = request.getParameter("email");
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String role = request.getParameter("role");
		boolean status = false;
		List<Employee> employees = null;

		status = empDao.update(new Employee(id, fname, lname, email, role));
		if (status == true) {
			employees = empDao.getAll();
			mv.setViewName("index.jsp");
			mv.addObject("employees", employees);
			System.out.println("success");
		} else {
			employees = empDao.getAll();
			mv.setViewName("index.jsp");
			mv.addObject("employees", employees);
			System.out.println("fail");
		}

		return mv;

	}

	@RequestMapping("/delete-employee")
	public ModelAndView deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean status = false;
		List<Employee> employees = null;
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		status = empDao.deleteEmployee(id);
		if (status == true) {
			mv.setViewName("index.jsp");
			mv.addObject("status", "deleted successfully");
			employees = empDao.getAll();
			mv.addObject("employees", employees);
		} else {
			mv.setViewName("index.jsp");
			mv.addObject("status", "not deleted");
			employees = empDao.getAll();
			mv.addObject("employees", employees);
		}

		return mv;

	}

	@RequestMapping("/delete")
	public ModelAndView deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean status = false;

		status = empDao.deleteAll();
		if (status == true) {
			mv.setViewName("index.jsp");
			mv.addObject("status", "deleted successfully");
		} else {
			mv.setViewName("index.jsp");
			mv.addObject("status", "not deleted");
		}

		return mv;

	}

	@RequestMapping("/employees")

	public ModelAndView getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> employees = empDao.getAll();
		mv.setViewName("index.jsp");
		mv.addObject("employees", employees);
		

		return mv;
	}
	@RequestMapping("/download-pdf")

	public void downloadpdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> employees = empDao.getAll();
		System.out.println(employees);
		
		try {
			final InputStream stream = this.getClass().getResourceAsStream("/employee-report.jrxml");
			 
	        // Compile the Jasper report from .jrxml to .japser
	        final JasperReport report = JasperCompileManager.compileReport(stream);
	 
	        // Fetching the employees from the data source.
	        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
	 
	        // Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters =null;
	        
	 
	        // Filling the report with the employee data and additional parameters information.
	        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
	 
	        //final String filePath = "C://Users//user//Documents//" + "Employee_report"+date.getDate()+date.getMonth()+date.getYear()+"_"+date.getHours()+"_"+date.getMinutes()+"_"+date.getSeconds()+".pdf";
	        // Export the report to a PDF file.
	        Integer month=calender.get(Calendar.MONTH);
	        response.setContentType("application/x-download");
	        response.addHeader("Content-disposition", "attachment; filename="+"Employee_report"+calender.get(Calendar.DATE)+"-"+String.valueOf(month+1)+"-"+calender.get(Calendar.YEAR)+".pdf");
	        OutputStream out = response.getOutputStream();
	        JasperExportManager.exportReportToPdfStream(print, out);
	        out.flush();
	        out.close();
	        
	        
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@RequestMapping("/download-excel")

	public void downloadexcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> employees = empDao.getAll();
		
		try {
			final InputStream stream = this.getClass().getResourceAsStream("/employee-report.jrxml");
			 
	        // Compile the Jasper report from .jrxml to .japser
	        final JasperReport report = JasperCompileManager.compileReport(stream);
	 
	        // Fetching the employees from the data source.
	        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
	 
	        // Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = null ;
	        
	 
	        // Filling the report with the employee data and additional parameters information.
	        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
	 
	        Integer month=calender.get(Calendar.MONTH);
	        
	        OutputStream out = response.getOutputStream();

	        JRXlsxExporter exporter = new JRXlsxExporter();
	        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
	        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
	        exporter.setConfiguration(reportConfigXLS);
	        exporter.setExporterInput(new SimpleExporterInput(print));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
	        response.setContentType("application/x-download");
	        response.addHeader("Content-disposition", "attachment; filename="+"Employee_report"+calender.get(Calendar.DATE)+"-"+String.valueOf(month+1)+"-"+calender.get(Calendar.YEAR)+".xlsx");
	        exporter.exportReport();
	        out.flush();
	        out.close();
	       
	        
	        
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@RequestMapping("/download-doc")

	public void downloadcsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> employees = empDao.getAll();
		
		try {
			final InputStream stream = this.getClass().getResourceAsStream("/employee-report.jrxml");
			 
	        // Compile the Jasper report from .jrxml to .japser
	        final JasperReport report = JasperCompileManager.compileReport(stream);
	 
	        // Fetching the employees from the data source.
	        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
	 
	        // Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = null;
	        
	 
	        // Filling the report with the employee data and additional parameters information.
	        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
	 
	        
	        Integer month=calender.get(Calendar.MONTH);
	        JRDocxExporter exporter = new JRDocxExporter();
	        OutputStream out = response.getOutputStream();


	        exporter.setExporterInput(new SimpleExporterInput(print));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
	        response.setContentType("application/x-download");
	        response.addHeader("Content-disposition", "attachment; filename="+"Employee_report"+calender.get(Calendar.DATE)+"-"+String.valueOf(month+1)+"-"+calender.get(Calendar.YEAR)+".docx");
	        exporter.exportReport();
	        out.flush();
	        out.close();   
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@RequestMapping("/create")
	public ModelAndView createEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {


		mv.setViewName("create.jsp");
		
		return mv;

	}
}
