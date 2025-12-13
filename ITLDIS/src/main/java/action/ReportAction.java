/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Session;

import com.common.MethodUtility;

import HibernateUtil.HibernateUtil;
import beans.ReportForm;
import dao.ReportDao;
import dbConnection.dbConnection;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author satendra.aditya
 */
public class ReportAction extends DispatchAction {

    public ActionForward options(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("options");
    }

    public ActionForward initMisReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String forward = "success";
        HttpSession session = request.getSession();
        ReportForm rptForm = (ReportForm) form;
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");

        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
        if (userFunctionalities.contains("101")) {
            rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
        } else {
            request.setAttribute("dealerList", dealerList);
        }
        return mapping.findForward(forward);
    }

    public ActionForward generateMisReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Session hSession = HibernateUtil.getSessionFactory().openSession();
        String forward = "";
        ReportForm rptForm = (ReportForm) form;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date convertedFromDate = sdf.parse(request.getParameter("fromDate"));
            Date convertedToDate = sdf.parse(request.getParameter("toDate"));
            ReportDao reportDao = new ReportDao();
            String inputFromDate = inputFormat.format(convertedFromDate);
            String inputToDate = inputFormat.format(convertedToDate);

            reportDao.getJobTypes(rptForm.getDealerCode(), inputFromDate, inputToDate, rptForm, hSession);
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportviewMisDMSReport";
            } else {
                forward = "viewMisDMSReport";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hSession != null) {
                    hSession.close();
                    hSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward getListSpareLubeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forward = "viewSpareLube";
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {
            ReportDao reportDao = new ReportDao();
            ArrayList<String> country = reportDao.getHierarchyList(user_id, flag);
            request.setAttribute("country", country);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward spareAndLubeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        OutputStream ouputStream = null;
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/spareAndLube.jasper";
            String country = request.getParameter("country");
            String zone = request.getParameter("zone");
            String state = request.getParameter("state");
            String ccm = request.getParameter("ccm");
            String cce = request.getParameter("cce");
            String dealer = request.getParameter("dealer");
            String finYear = request.getParameter("finYear");
            int month = Integer.parseInt(request.getParameter("month"));
            int week = Integer.parseInt(request.getParameter("week"));
            String weekName = "";
            if (week == 1) {
                weekName = "Week - 1";
            }
            if (week == 2) {
                weekName = "Week - 2";
            }
            if (week == 3) {
                weekName = "Week - 3";
            }
            if (week == 4) {
                weekName = "Week - 4";
            }
            String monthDisplay = new DateFormatSymbols().getMonths()[month - 1];

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("loginUserID", user_id);
            parameterMap.put("country", country);
            parameterMap.put("zone", zone);
            parameterMap.put("state", state);
            parameterMap.put("ccm", ccm);
            parameterMap.put("cce", cce);
            parameterMap.put("dealer", dealer);
            parameterMap.put("finYear", finYear);
            parameterMap.put("month", month);
            parameterMap.put("week", week);
            parameterMap.put("monthDisplay", monthDisplay);
            parameterMap.put("weekName", weekName);

            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "xls");
            request.setAttribute("reportName", "Spares & Lubes Review Report");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            try {
                if (ouputStream != null) {
                    ouputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward getListSpareLubeRolling(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("viewSpareLubeRolling");
    }

    public ActionForward getRollingSpareLubeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        OutputStream ouputStream = null;
        HttpSession session = request.getSession();
        String dealerCode = (String) session.getAttribute("dealerCode");
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {

            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/spareAndLubeRolling.jasper";
            String finYear = request.getParameter("finYear");
            String a[] = finYear.split("-");
            String finYearDisplay = finYear.split("-")[0];
            int month = Integer.parseInt(request.getParameter("month"));
            String monthDisplay = new DateFormatSymbols().getMonths()[month - 1];

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("month", month);
            parameterMap.put("monthDisplay", monthDisplay);
            parameterMap.put("finYearDisplay", finYearDisplay);
            parameterMap.put("finYear", finYear);
            parameterMap.put("loginUserId", user_id);

            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "xls");
            request.setAttribute("reportName", "Spares & Lubes Rolling Report");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            try {
                if (ouputStream != null) {
                    ouputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward getListSpareLubeReportSTK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forward = "viewSpareLubeSTK";
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {
            ReportDao reportDao = new ReportDao();
            ArrayList<String> country = reportDao.getHierarchyList(user_id, flag);
            request.setAttribute("country", country);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward spareAndLubeReportSTK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        OutputStream ouputStream = null;
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/sparesReviewSTK.jasper";
            String country = request.getParameter("country");
            String zone = request.getParameter("zone");
            String state = request.getParameter("state");
            String ccm = request.getParameter("ccm");
            String cce = request.getParameter("cce");
            String dealer = request.getParameter("dealer");
            String finYear = request.getParameter("finYear");
            int month = Integer.parseInt(request.getParameter("month"));
            int week = Integer.parseInt(request.getParameter("week"));
            String weekName = "";
            if (week == 1) {
                weekName = "Week - 1";
            }
            if (week == 2) {
                weekName = "Week - 2";
            }
            if (week == 3) {
                weekName = "Week - 3";
            }
            if (week == 4) {
                weekName = "Week - 4";
            }
            String monthDisplay = new DateFormatSymbols().getMonths()[month - 1];

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("loginUserID", user_id);
            parameterMap.put("country", country);
            parameterMap.put("zone", zone);
            parameterMap.put("state", state);
            parameterMap.put("ccm", ccm);
            parameterMap.put("cce", cce);
            parameterMap.put("dealer", dealer);
            parameterMap.put("finYear", finYear);
            parameterMap.put("month", month);
            parameterMap.put("week", week);
            parameterMap.put("monthDisplay", monthDisplay);
            parameterMap.put("weekName", weekName);

            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "xls");
            request.setAttribute("reportName", "Review Report(STK)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            try {
                if (ouputStream != null) {
                    ouputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward getListSpareLubeRollingSTK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("viewSpareLubeRollingSTK");
    }

    public ActionForward getRollingSpareLubeReportSTK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        OutputStream ouputStream = null;
        HttpSession session = request.getSession();
        String dealerCode = (String) session.getAttribute("dealerCode");
        String user_id = (String) session.getAttribute("user_id");
        String flag = request.getParameter("flag");
        try {

            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/sparesRollingSTK.jasper";
            String finYear = request.getParameter("finYear");
            String a[] = finYear.split("-");
            String finYearDisplay = finYear.split("-")[0];
            int month = Integer.parseInt(request.getParameter("month"));
            String monthDisplay = new DateFormatSymbols().getMonths()[month - 1];

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("month", month);
            parameterMap.put("monthDisplay", monthDisplay);
            parameterMap.put("finYearDisplay", finYearDisplay);
            parameterMap.put("finYear", finYear);
            parameterMap.put("loginUserId", user_id);

            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "xls");
            request.setAttribute("reportName", "Rolling Plan Report(STK)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            try {
                if (ouputStream != null) {
                    ouputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward getSubFieldDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            ReportDao reportDao = new ReportDao();
            String colValue = request.getParameter("colValue");
            String getColName = request.getParameter("getColName");
            String whereColName = request.getParameter("whereColName");
            String flag = request.getParameter("flag");
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String subField = "";
            try {
                if (!"%".equals(colValue)) {
                    subField = ReportDao.getListOnSelect(user_id, getColName, whereColName, colValue, flag);
                }
                PrintWriter pw = response.getWriter();
                pw.write(subField);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return null;
    }

    public ActionForward viewSaleInvoiceReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewSaleInvoice";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            ReportForm rptForm = (ReportForm) form;
            ReportDao reportDao = new ReportDao();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                rptForm.setRange("1");
            }
            String user_id = (String) session.getAttribute("user_id");
            rptForm.setUserid((String) session.getAttribute("user_id"));
            rptForm.setPartnum(rptForm.getPartnum() == null ? "" : rptForm.getPartnum());
            rptForm.setFromdate(rptForm.getFromdate() == null ? fromdate : rptForm.getFromdate());
            rptForm.setTodate(rptForm.getTodate() == null ? todate : rptForm.getTodate());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            List countryList = MethodUtility.getDealersCountryUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("countryList", countryList);
            request.setAttribute("range", rptForm.getRange());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward createSaleInvoiceReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            // String contextPath = request.getContextPath();
            String userCode = (String) session.getValue("userCode");
            String user_id = (String) session.getAttribute("user_id");
            String ecatPATH = (String) session.getValue("ecatPATH");
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //Connection conn = holder.getConnection();
            // String fromDate = request.getParameter("fromdate");
            //String toDate = request.getParameter("todate");
            String radio = request.getParameter("radio");
            //SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            ArrayList<String> list = new ArrayList<String>();
            ReportForm rptForm = (ReportForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

            ReportDao reportDao = new ReportDao();
            list = reportDao.getViewOrderInvoiceList(rptForm, userFunctionalities, radio);

            File ExcelFolder = null;
            ExcelFolder = new File(ecatPATH + "/dealer/excels/" + userCode + "/");
            if (ExcelFolder.exists()) {
                del_all(ExcelFolder);
            }
            if (!ExcelFolder.exists()) {
                ExcelFolder.mkdir();
            }

            String saveLocation = ecatPATH + "/dealer/excels/" + userCode + "/";

            WritableWorkbook workbook = null;
            if (radio.equals("part Wise")) {
                workbook = Workbook.createWorkbook(new File(saveLocation + "PartWiseSummaryReportExcel.xls"));
            } else {
                workbook = Workbook.createWorkbook(new File(saveLocation + "CustomerWiseSummaryReportExcel.xls"));
            }
            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableFont message = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat msgFormat = new WritableCellFormat(message);
            msgFormat.setWrap(true);
            msgFormat.setAlignment(Alignment.CENTRE);
            msgFormat.setBackground(Colour.GRAY_25);
            msgFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);


            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(text);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH RIGHT ALIGNMENT

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_RIGHT.setBorder(Border.ALL, BorderLineStyle.THIN);



            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.CENTRE);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);



            int cols = 0;
            WritableSheet excelReport = null;

            if (radio.equals("part Wise")) {
                excelReport = workbook.createSheet("Part Wise Sales Summary", 0);
                cols = 6;
                excelReport.setColumnView(0, 20);
                excelReport.setColumnView(1, 20);
                excelReport.setColumnView(2, 20);
                excelReport.setColumnView(3, 20);
                excelReport.setColumnView(4, 30);
                excelReport.setColumnView(5, 20);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "Part Wise Sales Summary", headingFormat);
                excelReport.mergeCells(0, 0, cols - 1, 0);
                excelReport.addCell(briefD);
            } else {
                excelReport = workbook.createSheet("Customer  Wise Sales Summary", 0);
                cols = 11;
                excelReport.setColumnView(0, 20);
                excelReport.setColumnView(1, 20);
                excelReport.setColumnView(2, 20);
                excelReport.setColumnView(3, 20);
                excelReport.setColumnView(4, 20);
                excelReport.setColumnView(5, 20);
                excelReport.setColumnView(6, 20);
                excelReport.setColumnView(7, 20);
                excelReport.setColumnView(8, 20);
                excelReport.setColumnView(9, 20);
                excelReport.setColumnView(10, 20);
                excelReport.setColumnView(11, 20);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "Customer  Wise Sales Summary", headingFormat);
                excelReport.mergeCells(0, 0, cols - 1, 0);
                excelReport.addCell(briefD);
            }

            String invnomsg = rptForm.getInvNo() != null ? rptForm.getInvNo() : "";
            String pinomsg = rptForm.getPiNo() != null ? rptForm.getPiNo() : "";
            String partnomsg = rptForm.getPartnum() != null ? rptForm.getPartnum() : "";
            String fromdatemsg = rptForm.getFromdate() != null ? rptForm.getFromdate() : "";
            String todatemsg = rptForm.getTodate() != null ? rptForm.getTodate() : "";
            String countrymsg = (rptForm.getCountry() != null && !rptForm.getCountry().equals("")) ? rptForm.getCountry().split("@@")[1] : "";
            String dealercodemsg = rptForm.getDealerCode() != null ? rptForm.getDealerCode() : "";

            String msg = "Report for Invoice No: " + invnomsg + ", PI No: " + pinomsg + ", Part No: " + partnomsg + ", Invoice Date From : "
                    + fromdatemsg + " To : " + todatemsg + ", Country : " + countrymsg + ", Dealer Code  : " + dealercodemsg;

            jxl.write.Label briefD = new jxl.write.Label(0, 1, "" + msg, msgFormat);
            excelReport.mergeCells(0, 1, cols - 1, 1);
            excelReport.addCell(briefD);

            int colCounter = 0;
            int rowCounter = 2;
            if (radio.equals("part Wise")) {

                jxl.write.Label colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Code", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Name", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Country", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Part Code", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Part Description", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Qty", tableHeadingFormat);
                excelReport.addCell(colHeadings);

            } else {

                jxl.write.Label colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Code", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Name", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Country", tableHeadingFormat);
                excelReport.addCell(colHeadings);


                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "CI No.", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "CI Date", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Currency", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Amount", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Other Charges", tableHeadingFormat);
                excelReport.addCell(colHeadings);


                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Mode of Dispatch", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Docket No", tableHeadingFormat);
                excelReport.addCell(colHeadings);

                colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Docket Date", tableHeadingFormat);
                excelReport.addCell(colHeadings);
            }
            rowCounter++;

            if (list.size() == 0) {
                jxl.write.Label noEcnLabel = new jxl.write.Label(0, rowCounter, "No Data exist for selected Criteria.", headingFormat_without);
                excelReport.mergeCells(0, rowCounter, 8, rowCounter);
                excelReport.addCell(noEcnLabel);
                rowCounter++;
            } else {
                jxl.write.Label colVal;
                int counter = 1;
                if (radio.equals("part Wise")) {
                    for (int i = 0; i < list.size(); i += 6) {
                        colCounter = 0;

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 1)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 2)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 3)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 4)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 5)), textFormat);
                        excelReport.addCell(colVal);

                        rowCounter++;
                        counter++;
                    }
                } else {
                    for (int i = 0; i < list.size(); i += 11) {
                        colCounter = 0;

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 1)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 2)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 3)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 4)), textFormat);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 5)), textFormat);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 6)), textFormat);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 7)), textFormat);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 8)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 9)), textFormat_LEFT);
                        excelReport.addCell(colVal);

                        colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 10)), textFormat);
                        excelReport.addCell(colVal);


                        rowCounter++;
                        counter++;
                    }
                }
            }
            workbook.write();
            workbook.close();


            rptForm.setInvNo(rptForm.getInvNo());
            rptForm.setPiNo(rptForm.getPiNo());
            rptForm.setPartnum(rptForm.getPartnum() == null ? "" : rptForm.getPartnum());
            rptForm.setFromdate(rptForm.getFromdate());
            rptForm.setTodate(rptForm.getTodate());
            rptForm.setCountry((rptForm.getCountry() != null && !rptForm.getCountry().equals("")) ? rptForm.getCountry().split("@@")[0] : "");
            rptForm.setDealerCode(rptForm.getDealerCode());
            rptForm.setUserid((String) session.getAttribute("user_id"));


            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            List countryList = MethodUtility.getDealersCountryUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("countryList", countryList);
            request.setAttribute("range", rptForm.getRange());
            request.setAttribute("meaasge", "SUCCESS");
            request.setAttribute("range", rptForm.getRange());
            request.setAttribute("flag", "flag");
            return mapping.findForward("successReport");
        } catch (Exception e) {
            request.setAttribute("meaasge", "FAILURE");
            request.setAttribute("flag", "flag");
            return mapping.findForward("failure");
        }
    }

    public void del_all(File ss) {
        int j = 0;
        int total_items = 0;
        File kk = ss;
        File pp = ss;
        if (ss.exists() && ss.isDirectory()) {
            String parts[] = ss.list();
            String parts_del[] = new String[parts.length];
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("")) {
                    break;
                } else {
                    parts_del[j] = parts[i];
                    j++;
                }
            }
            total_items = j;
            if (total_items > 0) {
                for (int i = 0; i < total_items; i++) {
                    ss = new File(pp + "/" + parts_del[i]);
                    //File pp =ss;
                    if (ss.exists() && ss.isFile()) {
                        ss.delete();
                    } else if (ss.exists() && ss.isDirectory()) {
                        del_all(ss);
                    }
                    //ss=pp;
                }
                ss.delete();
            }
            kk.delete();
        }
    }

    public ActionForward viewPedningPIConfirmationReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewPedningPIConfirmationReport";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            ReportForm rptForm = (ReportForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                rptForm.setRange("1");
            }
            String user_id = (String) session.getAttribute("user_id");
            rptForm.setUserid((String) session.getAttribute("user_id"));
            rptForm.setPartnum(rptForm.getPartnum() == null ? "" : rptForm.getPartnum());
            rptForm.setFromdate(rptForm.getFromdate() == null ? fromdate : rptForm.getFromdate());
            rptForm.setTodate(rptForm.getTodate() == null ? todate : rptForm.getTodate());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            List countryList = MethodUtility.getDealersCountryUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("countryList", countryList);
            request.setAttribute("range", rptForm.getRange());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward createPedningPIConfirmationReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String userCode = (String) session.getValue("userCode");
            String user_id = (String) session.getAttribute("user_id");
            String ecatPATH = (String) session.getValue("ecatPATH");
            ArrayList<String> list = new ArrayList<String>();
            ReportForm rptForm = (ReportForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");

            ReportDao reportDao = new ReportDao();
            list = reportDao.getPedningPIConfirmation(rptForm, userFunctionalities);

            File ExcelFolder = null;
            ExcelFolder = new File(ecatPATH + "/dealer/excels/" + userCode + "/");
            if (ExcelFolder.exists()) {
                del_all(ExcelFolder);
            }
            if (!ExcelFolder.exists()) {
                ExcelFolder.mkdir();
            }

            String saveLocation = ecatPATH + "/dealer/excels/" + userCode + "/";

            WritableWorkbook workbook = null;

            workbook = Workbook.createWorkbook(new File(saveLocation + "PendingPIAtBuyerReportExcel.xls"));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableFont message = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat msgFormat = new WritableCellFormat(message);
            msgFormat.setWrap(true);
            msgFormat.setAlignment(Alignment.CENTRE);
            msgFormat.setBackground(Colour.GRAY_25);
            msgFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);


            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(text);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH RIGHT ALIGNMENT

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_RIGHT.setBorder(Border.ALL, BorderLineStyle.THIN);




            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.CENTRE);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);



            int cols = 0;
            WritableSheet excelReport = null;


            excelReport = workbook.createSheet("PENDING PI AT BUYER", 0);
            cols = 10;
            excelReport.setColumnView(0, 20);
            excelReport.setColumnView(1, 20);
            excelReport.setColumnView(2, 20);
            excelReport.setColumnView(3, 20);
            excelReport.setColumnView(4, 20);
            excelReport.setColumnView(5, 20);
            excelReport.setColumnView(6, 20);
            excelReport.setColumnView(7, 20);
            excelReport.setColumnView(8, 20);
            excelReport.setColumnView(9, 20);
            excelReport.setColumnView(10, 20);


            jxl.write.Label briefD = new jxl.write.Label(0, 0, "PENDING PI AT BUYER", headingFormat);
            excelReport.mergeCells(0, 0, cols - 1, 0);
            excelReport.addCell(briefD);



            String pinomsg = rptForm.getPiNo() != null ? rptForm.getPiNo() : "";
            String partnomsg = rptForm.getPartnum() != null ? rptForm.getPartnum() : "";
            String fromdatemsg = rptForm.getFromdate() != null ? rptForm.getFromdate() : "";
            String todatemsg = rptForm.getTodate() != null ? rptForm.getTodate() : "";
            String countrymsg = (rptForm.getCountry() != null && !rptForm.getCountry().equals("")) ? rptForm.getCountry().split("@@")[1] : "";
            String dealercodemsg = rptForm.getDealerCode() != null ? rptForm.getDealerCode() : "";

            String msg = "Report for  PI No: " + pinomsg + ", Part No: " + partnomsg + ", PI Date From : "
                    + fromdatemsg + " To : " + todatemsg + ", Country : " + countrymsg + ", Dealer Code  : " + dealercodemsg;

            briefD = new jxl.write.Label(0, 1, "" + msg, msgFormat);
            excelReport.mergeCells(0, 1, cols - 1, 1);
            excelReport.addCell(briefD);

            int colCounter = 0;
            int rowCounter = 2;
            jxl.write.Label colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Code", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Customer Name", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Country", tableHeadingFormat);
            excelReport.addCell(colHeadings);


            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "PO No.", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "PI No.", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "PI Date", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Part Code", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Part Description", tableHeadingFormat);
            excelReport.addCell(colHeadings);


            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Lead Time", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Qty", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            rowCounter++;

            if (list.size() == 0) {
                jxl.write.Label noEcnLabel = new jxl.write.Label(0, rowCounter, "No Data exist for selected Criteria.", headingFormat_without);
                excelReport.mergeCells(0, rowCounter, 10, rowCounter);
                excelReport.addCell(noEcnLabel);
                rowCounter++;
            } else {
                jxl.write.Label colVal;
                int counter = 1;
                for (int i = 0; i < list.size(); i += 10) {
                    colCounter = 0;

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 1)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 2)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 3)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 4)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 5)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 6)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 7)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 8)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + list.get(i + 9)), textFormat);
                    excelReport.addCell(colVal);

                    rowCounter++;

                    counter++;
                }
            }
            workbook.write();
            workbook.close();


            rptForm.setInvNo(rptForm.getInvNo());
            rptForm.setPiNo(rptForm.getPiNo());
            rptForm.setPartnum(rptForm.getPartnum() == null ? "" : rptForm.getPartnum());
            rptForm.setFromdate(rptForm.getFromdate());
            rptForm.setTodate(rptForm.getTodate());
            rptForm.setCountry((rptForm.getCountry() != null && !rptForm.getCountry().equals("")) ? rptForm.getCountry().split("@@")[0] : "");
            rptForm.setDealerCode(rptForm.getDealerCode());
            rptForm.setUserid((String) session.getAttribute("user_id"));


            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            List countryList = MethodUtility.getDealersCountryUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("countryList", countryList);
            request.setAttribute("range", rptForm.getRange());
            request.setAttribute("meaasge", "SUCCESS");
            request.setAttribute("range", rptForm.getRange());
            request.setAttribute("flag", "flag");
            return mapping.findForward("successPIConfirmationReport");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("meaasge", "FAILURE");
            return mapping.findForward("failurePIConfirmation");

        }
    }

    public ActionForward viewOrderInvDetReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrderInvDetReport";
        String fromdate = "";
        String todate = "";
        Connection conn = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            ReportDao reportDao = new ReportDao();
            conn = new dbConnection().getConnection();
            ReportForm rForm = (ReportForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String flag = request.getParameter("flag");
            String cce = request.getParameter("cce");
            rForm.setCce(cce);
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                rForm.setRange("1");
            }
            String user_id = (String) session.getAttribute("user_id");
            rForm.setUserid(user_id);
            rForm.setPartnum(rForm.getPartnum() == null ? "" : rForm.getPartnum());
            rForm.setFromdate(rForm.getFromdate() == null ? fromdate : rForm.getFromdate());
            rForm.setTodate(rForm.getTodate() == null ? todate : rForm.getTodate());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                rForm.setDealerCode((String) session.getAttribute("dealerCode"));
            }

            request.setAttribute("dealerList", dealerList);

            if (rForm.getDealerCode() == null) {
                rForm.setDealerCode("ALL");
            }

            request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            request.setAttribute("range", rForm.getRange());
            if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
                request.setAttribute("viewOrderList", reportDao.getViewOrderList(rForm, userFunctionalities, conn));
                forward = "exportViewOrderInvDetReport";
            } else {
                forward = "viewOrderInvDetReport";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }
     public ActionForward initGstr1Report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String forward = "successGstr1";
        HttpSession session = request.getSession();
        ReportForm rptForm = (ReportForm) form;
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");

        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
        if (userFunctionalities.contains("101")) {
            rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
        } else {
            request.setAttribute("dealerList", dealerList);
        }
        return mapping.findForward(forward);
    }
     public ActionForward initGstr2Report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String forward = "successGstr2";
        HttpSession session = request.getSession();
        ReportForm rptForm = (ReportForm) form;
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");

        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
        if (userFunctionalities.contains("101")) {
            rptForm.setDealerCode((String) session.getAttribute("dealerCode"));
        } else {
            request.setAttribute("dealerList", dealerList);
        }
        return mapping.findForward(forward);
    }

     public ActionForward generateGstr1Report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        Session hSession = HibernateUtil.getSessionFactory().openSession();
        String forward = "";
        ReportForm rptForm = (ReportForm) form;
        ReportDao reportDao = new ReportDao();
        //String path = request.getSession().getServletContext().getRealPath("/");
        //String filePath=request.getSession().getServletContext().getRealPath("/WEB-INF/template/GSTR-1_2018-19.xls");
        String filePath=request.getSession().getServletContext().getRealPath("/templates/GSTR-1_2018-19.xls");

        try {
            //Integer branchId = (Integer)httpSession.getAttribute("branchId");
              request.setAttribute("serverDate",new Date());
              request.setAttribute("serverDateTime",new Date().getTime());
             
              rptForm.setOrderType("GSTR-1");
              Map<String,List<Object[]>> data = reportDao.gstrDetails(rptForm);
              SimpleDateFormat df = new SimpleDateFormat("MMM-yyyy");
              SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
              String fromDate = df.format(df1.parse(rptForm.getFromDate()));
              String filename = "GSTR_1_"+fromDate+".xls";
              try{
                     reportDao.GSTR_1Report(response, request, data, filename,filePath);
              }catch(Exception ex){
                     ex.printStackTrace();
                     response.sendRedirect("gstr-1");
              }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hSession != null) {
                    hSession.close();
                    hSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }
     public ActionForward generateGstr2Report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        Session hSession = HibernateUtil.getSessionFactory().openSession();
        String forward = "";
        ReportForm rptForm = (ReportForm) form;
        ReportDao reportDao = new ReportDao();
        
        String filePath=request.getSession().getServletContext().getRealPath("/templates/GSTR-2_2018-19.xlsx");

        try {
           
              request.setAttribute("serverDate",new Date());
              request.setAttribute("serverDateTime",new Date().getTime());
              rptForm.setOrderType("GSTR-2");
              Map<String,List<Object[]>> data = reportDao.gstrDetails(rptForm);
              SimpleDateFormat df = new SimpleDateFormat("MMM-yyyy");
              SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
              String fromDate = df.format(df1.parse(rptForm.getFromDate()));
              String filename = "GSTR_2_"+fromDate+".xlsx";
              try{
                     reportDao.GSTR_2Report(response, request, data, filename,filePath);
              }catch(Exception ex){
                     ex.printStackTrace();
                     response.sendRedirect("gstr-2");
              }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hSession != null) {
                    hSession.close();
                    hSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }
}
