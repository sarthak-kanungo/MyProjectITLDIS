/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Other.ActionFormBean.UsageDetailBean;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author manish.kishore
 */
public class UsageDetailAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "fail";
        Connection sqlConnection = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        UsageDetailBean usageBean = (UsageDetailBean) form;

        try {
            HttpSession session = request.getSession();
            String ecatPath = (String) session.getValue("ecatPATH");
            String userCode = (String) session.getValue("userCode");
            String mainURL = (String) session.getValue("mainURL");

            sqlConnection = new dbConnection.dbConnection().getDbConnection();
            //stmt = sqlConnection.createStatement();
            Calendar cal = Calendar.getInstance();

            String user_id = "";
            String company_name = "";
            Vector usage = new Vector();
            int reportMonth = usageBean.getMonth();
            String reportYear = usageBean.getYear();
            int reportYeartemp=0;
            try
            {
            reportYeartemp=Integer.parseInt(reportYear);
            }catch(Exception e){e.printStackTrace();}
            cal.set(Calendar.MONTH, reportMonth);
            cal.set(Calendar.YEAR,reportYeartemp);
            int noOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//            rs = stmt.executeQuery("select user_id,NAME,user_type  from UM_user_check where status='LIVE' order by user_id");
           // rs = stmt.executeQuery("SELECT U.LOGINID, U.FIRSTNAME, S.USER_TYPE FROM UM_USERS(NOLOCK) U , UM_spas101 S WHERE U.USER_TYPE=S.USER_TYPE_ID AND U.Status='A' ORDER BY U.USERID");
           String query = ("SELECT U.LOGINID, U.FIRSTNAME, S.USER_TYPE FROM UM_USERS(NOLOCK) U , UM_spas101 S WHERE U.USER_TYPE=S.USER_TYPE_ID AND U.Status='A' ORDER BY U.USERID");
           stmt = sqlConnection.prepareStatement(query);
           rs = stmt.executeQuery();
            while (rs.next()) {
                user_id = rs.getString(1);
                if ((user_id.equals("hes"))) {
                    continue;
                }
                company_name = rs.getString(2);
                user_id = user_id.toLowerCase();
                user_id = user_id.trim();
                if (company_name == null) {
                    company_name = "-";
                }
                usage.addElement(user_id);
                usage.addElement(company_name);
                usage.addElement(rs.getString(3));
            }
            //rs.close();
            //stmt.close();

            //String saveLocation = "D:/HHML_UTILITY/xls/";
            // for server
            String saveLocation = ecatPath + "/dealer/excels/" + userCode;
            File saveFile = new File(saveLocation);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }


            String month_display = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            String year_display = reportYear.substring(2, reportYear.length());
            WritableWorkbook workbook = Workbook.createWorkbook(new File(saveLocation + "/E-Parts Catalogue Usage Details " + month_display + "-" + year_display + ".xls"));
            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.createFont("VERDANA"), 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.createFont("VERDANA"), 10, WritableFont.BOLD);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.createFont("VERDANA"), 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITHOUT BORDER

            WritableCellFormat textFormat_without = new WritableCellFormat(text);
            textFormat_without.setAlignment(Alignment.CENTRE);
            textFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT
            WritableFont text1 = new WritableFont(WritableFont.createFont("VERDANA"), 9);
            text1.setColour(Colour.BLACK);
            WritableCellFormat textFormat_LEFT = new WritableCellFormat(text1);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            //	textFormat_LEFT.setBackground(Colour.DARK_BLUE);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTRE ALIGNMENT AND BACKGROUND COLOUR GREEN

            WritableCellFormat textFormat_CENTRE = new WritableCellFormat(text);
            textFormat_CENTRE.setAlignment(Alignment.CENTRE);
            textFormat_CENTRE.setBackground(Colour.SEA_GREEN);
            textFormat_CENTRE.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_CENTRE.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTRE ALIGNMENT AND BACKGROUND COLOUR WHITE

            WritableCellFormat textFormat_CENTRE1 = new WritableCellFormat(text);
            textFormat_CENTRE1.setAlignment(Alignment.CENTRE);
            textFormat_CENTRE1.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_CENTRE1.setBorder(Border.ALL, BorderLineStyle.THIN);
            // CREATE A CELL FORMAT FOR TEXT WITH CENTRE ALIGNMENT AND BACKGROUND COLOUR YELLOW

            WritableCellFormat textFormat_CENTRE2 = new WritableCellFormat(text);
            textFormat_CENTRE2.setAlignment(Alignment.CENTRE);
            textFormat_CENTRE2.setBackground(Colour.YELLOW);
            textFormat_CENTRE2.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_CENTRE2.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.createFont("VERDANA"), 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING 

            WritableFont tableHeading = new WritableFont(WritableFont.createFont("VERDANA"), 9, WritableFont.BOLD);

            WritableFont tableHeading_1 = new WritableFont(WritableFont.createFont("VERDANA"), 10, WritableFont.BOLD);
            tableHeading_1.setColour(Colour.WHITE);

            WritableCellFormat tableHeadingFormatMain = new WritableCellFormat(tableHeading_1);
            tableHeadingFormatMain.setAlignment(Alignment.LEFT);
            tableHeadingFormatMain.setBackground(Colour.GRAY_50);
            tableHeadingFormatMain.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormatMain.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tot_hits = new WritableCellFormat(tableHeading);
            tot_hits.setAlignment(Alignment.LEFT);
            tot_hits.setBackground(Colour.ORANGE);
            tot_hits.setVerticalAlignment(VerticalAlignment.CENTRE);
            tot_hits.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tot_count = new WritableCellFormat(tableHeading);
            tot_count.setAlignment(Alignment.LEFT);
            tot_count.setBackground(Colour.ORANGE);
            tot_count.setVerticalAlignment(VerticalAlignment.CENTRE);
            tot_count.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormat1 = new WritableCellFormat(tableHeading);
            tableHeadingFormat1.setAlignment(Alignment.CENTRE);
            tableHeadingFormat1.setBackground(Colour.GREY_25_PERCENT);
            tableHeadingFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormat2 = new WritableCellFormat(tableHeading);
            tableHeadingFormat2.setAlignment(Alignment.CENTRE);
            tableHeadingFormat2.setBackground(Colour.GREY_25_PERCENT);
            tableHeadingFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormat3 = new WritableCellFormat(tableHeading);
            tableHeadingFormat3.setAlignment(Alignment.CENTRE);
            tableHeadingFormat3.setBackground(Colour.GREY_25_PERCENT);
            tableHeadingFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableSheet CIL_REPORT = workbook.createSheet("Usage Details " + month_display + "-" + year_display + "", 0);
            CIL_REPORT.setColumnView(0, 20);
            CIL_REPORT.getSettings().setPaperSize(PaperSize.A4);
            CIL_REPORT.getSettings().setOrientation(PageOrientation.LANDSCAPE);
            CIL_REPORT.getSettings().setHeaderMargin(.5);
            CIL_REPORT.getSettings().setFooterMargin(.5);
            CIL_REPORT.getSettings().setTopMargin(.5);
            CIL_REPORT.getSettings().setBottomMargin(.5);
            int rowCounter = 0;
            jxl.write.Label mainHeading = new jxl.write.Label(0, rowCounter, "E-Parts Catalogue Usage Details " + month_display + "-" + year_display + "", tableHeadingFormatMain);
            CIL_REPORT.mergeCells(0, rowCounter, noOfDays + 3, rowCounter);
            CIL_REPORT.addCell(mainHeading);
            CIL_REPORT.setRowView(rowCounter, 480);
            rowCounter++;
            jxl.write.Label briefD = new jxl.write.Label(0, rowCounter, "USER-ID", tableHeadingFormat1);
            CIL_REPORT.addCell(briefD);
            jxl.write.Label briefD1 = new jxl.write.Label(1, rowCounter, "USER-NAME", tableHeadingFormat1);
            CIL_REPORT.addCell(briefD1);
            
            jxl.write.Label briefD2 = new jxl.write.Label(2, rowCounter, "USER-TYPE", tableHeadingFormat1);
            CIL_REPORT.addCell(briefD2);
            
            CIL_REPORT.setColumnView(0, 11);
            CIL_REPORT.setColumnView(1, 70);
            CIL_REPORT.setColumnView(2, 50);
            int column = 3;
            jxl.write.Label lab[] = new jxl.write.Label[noOfDays];
            for (int i = 0; i < noOfDays; i++) {
                CIL_REPORT.setColumnView(column, 5);
                lab[i] = new jxl.write.Label(column, rowCounter, "" + (i + 1), tableHeadingFormat2);
                CIL_REPORT.addCell(lab[i]);
                column++;
            }
            CIL_REPORT.setColumnView(noOfDays + 1, 10);
            jxl.write.Label total = new jxl.write.Label(noOfDays + 3, rowCounter, "Total", tableHeadingFormat3);
            CIL_REPORT.addCell(total);

            int overall_total = 0;
            rowCounter++;
            jxl.write.Number labs[][] = new jxl.write.Number[(usage.size() / 3)][noOfDays + 3];
            jxl.write.Label labs1[][] = new jxl.write.Label[(usage.size() / 3)][noOfDays + 3];
            int cnt = 0;
            String month = (reportMonth + 1) + "";
            //System.out.println("month:" + month);
            //System.out.println("month:QUERY" + "select count(*) from login_track where month(login_date)=" + month + " and year(login_date)=" + reportYear + "");
            //rs = stmt.executeQuery("select count(*) from UM_login_track where month(login_date)=" + month + " and year(login_date)=" + reportYear + " and usage_type_id=1");
            String query1 = ("select count(*) from UM_login_track(NOLOCK) where month(login_date)=" + month + " and year(login_date)=" + reportYear + " and usage_type_id=1");
            stmt = sqlConnection.prepareStatement(query1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cnt = rs.getInt(1);
            }
            //rs.close();
            //stmt.close();
           // System.out.println("cnt:" + cnt);
            String usg[][] = new String[cnt][100];
            int l = 0;
           // rs = stmt.executeQuery("select user_id, DAY(login_date) from UM_login_track where month(login_date)=" + month + " and year(login_date)=" + reportYear + " and usage_type_id=1");
            String query2 = ("select user_id, DAY(login_date) from UM_login_track(NOLOCK) where month(login_date)=" + month + " and year(login_date)=" + reportYear + " and usage_type_id=1");
            stmt = sqlConnection.prepareStatement(query2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                usg[l][0] = rs.getString(1);
                usg[l][1] = rs.getString(2);
                usg[l][0] = usg[l][0].toLowerCase();
                usg[l][0] = usg[l][0].trim();
                l++;
            }
            //rs.close();
            //stmt.close();
            for (int j = 0; j < (usage.size()) / 3; j++) {
                labs1[j][0] = new jxl.write.Label(0, rowCounter, "" + usage.elementAt(3 * j), textFormat_LEFT);
                CIL_REPORT.addCell(labs1[j][0]);
                
                labs1[j][0] = new jxl.write.Label(1, rowCounter, "" + usage.elementAt((3 * j) + 1), textFormat_LEFT);
                CIL_REPORT.addCell(labs1[j][0]);
                
                labs1[j][0] = new jxl.write.Label(2, rowCounter, "" + usage.elementAt((3 * j) + 2), textFormat_LEFT);
                CIL_REPORT.addCell(labs1[j][0]);
               
                int total_hits = 0;
                column = 3;
                for (int i = 1; i <= noOfDays; i++) {
                    String k = "" + i;
                    int hits = 0;
                    for (int m = 0; m < cnt; m++) {
                        if (((usg[m][0]) != null) && (usg[m][1]) != null) {
                            if ((usg[m][0].equals(usage.elementAt(3 * j))) && (usg[m][1].equals(k))) {
                                hits++;
                            }
                        }
                    }
                    if (hits > 0) {
                        total_hits = total_hits + hits;
                        labs[j][i] = new jxl.write.Number(column, rowCounter, hits, textFormat_CENTRE);
                        CIL_REPORT.addCell(labs[j][i]);
                    } else if (hits == 0) {
                        labs1[j][i] = new jxl.write.Label(column, rowCounter, " ", textFormat_CENTRE1);
                        CIL_REPORT.addCell(labs1[j][i]);
                    }
                    column++;
                }
                labs[j][noOfDays + 1] = new jxl.write.Number(noOfDays + 3, rowCounter, total_hits, textFormat_CENTRE2);
                CIL_REPORT.addCell(labs[j][noOfDays + 1]);
                rowCounter++;

                overall_total = overall_total + total_hits;
            }

            CIL_REPORT.mergeCells(0, rowCounter, noOfDays + 2, rowCounter);
            jxl.write.Label tt = new jxl.write.Label(0, rowCounter, "Total Hits By Users", tot_hits);
            CIL_REPORT.addCell(tt);
            jxl.write.Label totl = new jxl.write.Label(noOfDays + 3, rowCounter, "" + overall_total, tot_hits);
            CIL_REPORT.addCell(totl);
            //sqlConnection.close();
            workbook.write();
            workbook.close();

            request.setAttribute("tdData", "USAGE DETAILS");
            request.setAttribute("heading", "USAGE DETAILS");
            request.setAttribute("show_message", "Usage Details excel has been created successfully.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", mainURL + "/dealer/excels/" + userCode + "/" + "E-Parts Catalogue Usage Details " + month_display + "-" + year_display + ".xls");
            request.setAttribute("optionLinkName", "click here to download");

            forward = "success";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("tdData", "USAGE DETAILS");
            request.setAttribute("heading", "USAGE DETAILS");
            request.setAttribute("show_message", "Unable to create excel ,please contact System Admin.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (sqlConnection != null) {
                    sqlConnection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return mapping.findForward(forward);
    }
}
