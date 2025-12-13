/*
<% String PAGE_CODE="HRR006C";
System.out.println(" Inside payRegisterController.jsp");

%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file= "/common/sessionValidator.jsp" %>
<%@ page language="java" import="com.sdnet.util.common.*" %>
<%@ page import="java.io.*, java.util.*, java.sql.*,net.sf.jasperreports.engine.*, net.sf.jasperreports.engine.design.JasperDesign,net.sf.jasperreports.engine.xml.JRXmlLoader,net.sf.jasperreports.engine.export.*"%>
<%


	String category = request.getParameter("category");
	System.out.println("category type= "+category);
	String empCode=request.getParameter("Employee");
	String locationCode = request.getParameter("locationCode");
	System.out.println("loactionCode="+locationCode);
	String companyCode = request.getParameter("companyCode");
	String yearcode=request.getParameter("fdate");
	System.out.println("=====Yearcode===="+ yearcode);
	String yearcodeTo=request.getParameter("tdate");
	System.out.println("=====yearcodeTo===="+ yearcodeTo);
	int yearCode = Integer.parseInt(yearcode);
	int yearCodeTo = Integer.parseInt(yearcodeTo);
	System.out.println("yearcode in integer: "+yearCode);
	System.out.println("yearCodeTo in integer: "+yearCodeTo);
	System.out.println("$%^$%$%$%$%$companycode=="+companyCode);
	//added by deepti
	String reportType = request.getParameter("reportType");
	System.out.println("report type1===="+request.getParameter("reportType"));
//	String reportType2 = request.getParameter("report_xls");
//	System.out.println("report type2===="+request.getParameter("report_xls"));
//	String qryStr = "";
//	String path="";
//	String report= "";
	String reportName="";
	String format = "";
/*if(locationCode.equalsIgnoreCase("OM"))
{
locationCode="ALL";
qryStr = "prompt0="+locationCode+ "%26prompt1="+category+ "%26prompt2="+yearCode+ "%26prompt3="+companyCode+"%26prompt4="+empCode;
		report = "SalaryRegisterOM.rpt";
}
else
{

		//qryStr = "prompt0="+locationCode+ "%26prompt1="+category+ "%26prompt2="+yearCode+ "%26prompt3="+companyCode+"%26prompt4="+empCode;
		//report = "SalaryRegister.rpt";


*/
/*
try
{
	// //added by vijay
	if(reportType.equals("pdf"))
	{
		reportName="AdvanceReport";
		format="pdf";
	}
	else if(reportType.equals("xls"))
	{
		reportName="AdvanceReport_XLS";
		format="xls";
	}

	JRExporter pdfExporter = null;

	JExcelApiExporter xlsExporter = null;
	System.out.println("==================Inside try=============================== ");
	// //new added by rakesh for jasper implementation
	System.out.println("aaa real path "+getServletConfig().getServletContext().getRealPath("/"));
	File report1=new File(getServletConfig().getServletContext().getRealPath("/hr/reports/"+reportName+".jrxml"));
	System.out.println("==================1=============================== ");
	InputStream input=new FileInputStream(report1);
	//  //load file
	JasperDesign design = JRXmlLoader.load(input);
	System.out.println("==================2=============================== ");

	//  //compile file
	JasperReport report2 = JasperCompileManager.compileReport(design);
	Map parameters = new HashMap();

	parameters.put("location",locationCode);
	parameters.put("category",category);
	parameters.put("empCode",empCode);
	parameters.put("fYearCode",yearCode);
	parameters.put("tYearCode",yearCodeTo);
	parameters.put("companyCode",companyCode);
	System.out.println("==================3=============================== ");
	Connection conn = SDNETConnect.getConnection();
	JasperPrint print = JasperFillManager.fillReport(report2,parameters,conn);
	OutputStream output=null;
	System.out.println("==================4=============================== ");
	String fileName="";
	if(format!=null && ! format.equals("") && format.equals("pdf") || format.equals("xls"))
	{
		System.out.println("==================5=============================== ");
		if(reportType.equals("pdf"))
		{
			fileName=reportName+".pdf";
			output=new FileOutputStream(new File(getServletConfig().getServletContext().getRealPath(fileName)));
			pdfExporter = new JRPdfExporter();
		}
		if(reportType.equals("xls"))
		{
			fileName=reportName+".xls";
			output=new FileOutputStream(new File(getServletConfig().getServletContext().getRealPath(fileName)));
			xlsExporter = new JExcelApiExporter();
			//xlsExporter = new JRXlsExporter();
		}
		System.out.println("==================6=============================== ");
	}

	System.out.println("==================7=============================== ");
	if(reportType.equals("pdf"))
	{
		pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
		pdfExporter.exportReport();
	}
	if(reportType.equals("xls"))
	{

		xlsExporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, print);
		xlsExporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, output);
		xlsExporter.exportReport();
	}
	System.out.println("==================8=============================== ");
	System.out.println("Report Name "+reportName);
	pageContext.setAttribute("file",fileName);
	pageContext.setAttribute("report",reportName);
	System.out.println("==================9=============================== ");
	if(conn!=null)
	{
		conn.close();
	}
	%>
		<c:redirect url="/${pageScope.file}"></c:redirect>
	<%
	System.out.println("==================10============================== ");
}
catch(Exception e)
{
	out.println("Exception :"+e);
}

%>*/