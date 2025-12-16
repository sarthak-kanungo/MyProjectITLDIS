<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection" %>
<%@page import="java.io.IOException"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.export.JRCsvExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRRtfExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.*"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.j2ee.servlets.ImageServlet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dbConnection.dbConnection"%>

<%! Connection conn = null;
    OutputStream ouputStream = null;
        HashMap<String, Object> parameterMap = null;
    JasperPrint jasperPrint = null;
    JRExporter exporter = null;    
%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String reportType = (String) request.getAttribute("reportType");
            jasperPrint = (JasperPrint) request.getAttribute("jasperPrint");
            String reportName = (String) request.getAttribute("reportName");
            conn = new dbConnection().getConnection();
            ouputStream = response.getOutputStream();
        
             if ("pdf".equalsIgnoreCase(reportType)){
                 response.setContentType("application/pdf");
                 response.setHeader("Content-Disposition", "filename=\"" + reportName + ".pdf\"");
                 exporter = new JRPdfExporter();                 
                 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);                 
                 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

            } else if ("rtf".equalsIgnoreCase(reportType)) {
                response.setContentType("application/rtf");
                response.setHeader("Content-Disposition", "attachment; filename=\"report.rtf\"");
                exporter = new JRRtfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

            } else if ("html".equalsIgnoreCase(reportType)) {
                response.setContentType("application/html");                
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");

            /*} else if ("xls".equalsIgnoreCase(reportType)) {
                response.setContentType("application/xls");                
                //response.setHeader("Content-Disposition", "attachment; filename=\"report.xls\"");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + ".xls\"");
                exporter = new JRXlsExporter();
                jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
 *
 */
    /*           } else if ("xls".equalsIgnoreCase(reportType)) {
                response.setContentType("application/xls");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + ".xls\"");
                exporter = new JRXlsExporter();
                jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

 *
 * */

              } else if ("xls".equalsIgnoreCase(reportType)) {
                  response.setContentType("application/xls");
                  response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName + ".xls\"");
                  exporter = new JRXlsExporter();
                  jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
                  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                  exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                  exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                  exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                  exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                  exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                  exporter.exportReport();
                  
            } else if ("csv".equalsIgnoreCase(reportType)) {
                response.setContentType("application/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"report.csv\"");
                exporter = new JRCsvExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            }
            try {
                exporter.exportReport();
            } catch (JRException e) {
                throw new ServletException(e);
            } finally {
                if (ouputStream != null) {
                    try {
                        ouputStream.close();
                    } catch (IOException ex) {
                    }
                }
            }
%>
