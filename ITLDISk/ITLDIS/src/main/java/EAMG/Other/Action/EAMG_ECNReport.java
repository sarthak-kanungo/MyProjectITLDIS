/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.NumberFormats;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_ECNReport extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            HttpSession session = request.getSession();
            String contextPath = request.getContextPath();
            String userCode = (String) session.getValue("userCode");
            String ecatPATH = (String) session.getValue("ecatPATH");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            Connection conn = holder.getConnection();       
            String fromDate =request.getParameter("fromDate");
            String toDate =request.getParameter("toDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");           
            Vector ecn_vec = new Vector();
            String status = "";
            String effective_tsn = "";
            String newPart = "";
            String oldPart = "";
            String ecnNo="";
            PreparedStatement pstmt = null;           
            pstmt = conn.prepareStatement("select distinct GROUP_ASSY_NO,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,EFFECTIVE_DATE,EFFECTIVE_TSN,ECN_NO from CAT_ECN_IMPL_HISTORY(NOLOCK) where  EFFECTIVE_DATE >=? AND EFFECTIVE_DATE <=? order by EFFECTIVE_DATE");
            pstmt.setDate(1, new java.sql.Date(sdf1.parse(fromDate).getTime()));
            pstmt.setDate(2, new java.sql.Date(sdf1.parse(toDate).getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ecn_vec.addElement(rs.getString(1));
                newPart = rs.getString(2);
                if (newPart == null || newPart.equals("null")) {
                    newPart = "-";
                }
                ecn_vec.addElement(newPart);
                ecn_vec.addElement(rs.getString(3));
                oldPart = rs.getString(4);
                if (oldPart == null || oldPart.equals("null")) {
                    oldPart = "-";
                }
                ecn_vec.addElement(oldPart);
                status = rs.getString(5);
                if (status == null || status.equals("null")) {
                    status = "-";
                }
                ecn_vec.addElement(status);
                ecn_vec.addElement(rs.getDate(6));
                effective_tsn = rs.getString(7);
                if (effective_tsn == null || effective_tsn.equals("null")) {
                    effective_tsn = "-";
                }
                ecn_vec.addElement(effective_tsn);
                ecnNo=rs.getString(8);
                if (ecnNo == null || ecnNo.equals("null")) {
                    ecnNo = "-";
                }
                ecn_vec.addElement(ecnNo);

            }
            rs.close();
            pstmt.close();
            File ExcelFolder = null;
            ExcelFolder = new File(ecatPATH + "/dealer/excels/" + userCode + "/");
            if (ExcelFolder.exists()) {
                del_all(ExcelFolder);
            }
            if (!ExcelFolder.exists()) {
                ExcelFolder.mkdir();
            }

            String zipName = ecatPATH + "/dealer/excels/" + userCode + "/ECNReportExcel.zip";
            Vector filesToZip = new Vector();

            String saveLocation = ecatPATH + "/dealer/excels/" + userCode + "/";
            String format = "xls";

            WritableWorkbook workbook = Workbook.createWorkbook(new File(saveLocation + "ECNReportExcel.xls"));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITHOUT BORDER

            WritableCellFormat textFormat_without = new WritableCellFormat(text);
            textFormat_without.setAlignment(Alignment.CENTRE);
            textFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

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

            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.CENTRE);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            WritableCellFormat tableHeadingFormat_LEFT = new WritableCellFormat(tableHeading);
            tableHeadingFormat_LEFT.setAlignment(Alignment.LEFT);
            tableHeadingFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.MEDIUM);


            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);

            int cols = 9;

            WritableSheet excelReport = workbook.createSheet("ECN Report", 0);

            excelReport.setColumnView(0, 7);
            excelReport.setColumnView(1, 20);
            excelReport.setColumnView(2, 20);
            excelReport.setColumnView(3, 15);
            excelReport.setColumnView(4, 15);
            excelReport.setColumnView(5, 15);
            excelReport.setColumnView(6, 15);
            excelReport.setColumnView(7, 15);
            excelReport.setColumnView(8, 15);
            excelReport.setColumnView(9, 15);



            jxl.write.Label briefD = new jxl.write.Label(0, 0, "ECN Report", headingFormat);
            excelReport.mergeCells(0, 0, cols - 1, 0);
            excelReport.addCell(briefD);

            int colCounter = 0;
            int rowCounter = 1;

            jxl.write.Label colHeadings = new jxl.write.Label(colCounter++, rowCounter, "S No", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Table No.", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "New Part", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Change Type", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Old Part", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Interchangeability", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Effective Date", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "Effective TSN", tableHeadingFormat);
            excelReport.addCell(colHeadings);

            colHeadings = new jxl.write.Label(colCounter++, rowCounter, "ECN No", tableHeadingFormat);
            excelReport.addCell(colHeadings);
            rowCounter++;

            if (ecn_vec.size() == 0) {
                jxl.write.Label noEcnLabel = new jxl.write.Label(0, rowCounter, "No ECN exist in the selected Date.", textFormat_LEFT);
                excelReport.mergeCells(0, rowCounter, 8, rowCounter);
                excelReport.addCell(noEcnLabel);
                rowCounter++;
            } else {
                jxl.write.Label colVal;
                int counter = 1;

                for (int i = 0; i < ecn_vec.size(); i += 8) {
                    colCounter = 0;
                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + counter), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 1)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 2)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 3)), textFormat_LEFT);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 4)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 5)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 6)), textFormat);
                    excelReport.addCell(colVal);

                    colVal = new jxl.write.Label(colCounter++, rowCounter, ("" + ecn_vec.elementAt(i + 7)), textFormat);
                    excelReport.addCell(colVal);

                    rowCounter++;
                    counter++;
                }
            }
            workbook.write();
            workbook.close();

            filesToZip.addElement(saveLocation + "ECNReportExcel." + format);
            boolean state = compress_file(filesToZip, zipName, "ECNReportExcel", saveLocation, ecatPATH);
            if (state) {
                request.setAttribute("meaasge", "SUCCESS");
                return mapping.findForward(SUCCESS);
            } else {

                request.setAttribute("meaasge", "FAILURE");
                return mapping.findForward(FAILURE);

            }


        } catch (Exception e) {

            request.setAttribute("meaasge", "FAILURE");
            return mapping.findForward(FAILURE);
        }

    }

    public static boolean compress_file(Vector filename, String outfilename, String group, String saveLocation, String ecatPATH) {
        try {
            byte[] buf = new byte[102400];
            int retval;

            FileInputStream is;
            FileOutputStream os = new FileOutputStream(outfilename);
            ZipOutputStream zos = new ZipOutputStream(os);

            for (int i = 0; i < filename.size(); i++) {
                is = new FileInputStream("" + filename.elementAt(i));

                String entryPath = "" + filename.elementAt(i);
                entryPath = entryPath.replaceAll(saveLocation, "");

                zos.putNextEntry(new ZipEntry(entryPath));
                do {
                    retval = is.read(buf, 0, 102400);
                    if (retval != -1) {
                        zos.write(buf, 0, retval);
                    }
                } while (retval != -1);
                zos.closeEntry();
                is.close();
            }
            zos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
}
