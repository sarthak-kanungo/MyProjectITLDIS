/*
File Name: ItemMasterExcel.java
PURPOSE: To export all the items(part, assy, kits) present in ecatalogue into excel
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Shivani Chauhan			$$0 Created
 */

package viewEcat.comEcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/*
 * @author shivani.chauhan
 */
public class ItemMasterExcel extends HttpServlet
{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter ps = response.getWriter();
        try
        {
            HttpSession session = request.getSession(true);

            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            
            if (session_id.equals(getSession))
            {
                ArrayList detail = new ArrayList();

                String item_no = "";
                String desc = "";
                String service = "";
                String remark = "";
                String QML = "";
                String series = "";
                String origin = "";
                String selleble = "";

                Connection conn = holder.getConnection();


                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                //stmt = conn.createStatement();
                
                String idb_path_1 = ecatPATH + "/dealer/anim/";
                String idb_path_2 = ".idb";
                String idb_full_path = "";
                
                //rs = stmt.executeQuery("Select * from CAT_PART");
                String query = ("Select * from CAT_PART(NOLOCK)");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();

                while (rs.next())
                {
                    item_no = rs.getString("part_no");
                    desc = rs.getString("p1");
                    service = rs.getString("p4");
                    remark = rs.getString("p3");
                    QML = rs.getString("np3");
                    series = rs.getString("np4");
                    origin = rs.getString("np5");
                    selleble = rs.getString("np6");

                    if (desc == null || desc.equals(""))
                    {
                        desc = "--";
                    }
                    if (service == null || service.equals(""))
                    {
                        service = "--";
                    }
                    if (remark == null || remark.equals(""))
                    {
                        remark = "--";
                    }
                    if (QML == null || QML.equals(""))
                    {
                        QML = "--";
                    }
                    if (series == null || series.equals(""))
                    {
                        series = "--";
                    }
                    if (origin == null || origin.equals(""))
                    {
                        origin = "--";
                    }
                    if (selleble == null || selleble.equals(""))
                    {
                        selleble = "--";
                    }

                    detail.add(item_no);
                    detail.add(desc);
                    detail.add("PRT");
                    detail.add(service);
                    detail.add(remark);
                    detail.add(QML);
                    detail.add(series);
                    detail.add(origin);
                    detail.add(selleble);
                    
                    idb_full_path = idb_path_1 + item_no + idb_path_2;

                    File idb_File = new File(idb_full_path);
                    if (idb_File.isFile())
                    {
                        detail.add("YES");
                    }      
                    else
                    {
                        detail.add("NO");
                    }
                }
                rs.close();

                //rs = stmt.executeQuery("Select * from ASSY_DETAIL");
                String query1 = ("Select * from ASSY_DETAIL(NOLOCK)");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();

                while (rs.next())
                {
                    item_no = rs.getString("ASSY_NO");
                    desc = rs.getString("P1");
                    service = rs.getString("P3");
                    remark = rs.getString("P4");
                    QML = rs.getString("np3");
                    series = rs.getString("np4");
                    origin = rs.getString("np5");
                    selleble = rs.getString("np6");

                    if (desc == null || desc.equals(""))
                    {
                        desc = "--";
                    }
                    if (service == null || service.equals(""))
                    {
                        service = "--";
                    }
                    if (remark == null || remark.equals(""))
                    {
                        remark = "--";
                    }
                    if (QML == null || QML.equals(""))
                    {
                        QML = "--";
                    }
                    if (series == null || series.equals(""))
                    {
                        series = "--";
                    }
                    if (origin == null || origin.equals(""))
                    {
                        origin = "--";
                    }
                    if (selleble == null || selleble.equals(""))
                    {
                        selleble = "--";
                    }

                    detail.add(item_no);
                    detail.add(desc);
                    detail.add("ASY");
                    detail.add(service);
                    detail.add(remark);
                    detail.add(QML);
                    detail.add(series);
                    detail.add(origin);
                    detail.add(selleble);
                    
                    idb_full_path = idb_path_1 + item_no + idb_path_2;

                    File idb_File = new File(idb_full_path);
                    if (idb_File.isFile())
                    {
                        detail.add("YES");
                    }      
                    else
                    {
                        detail.add("NO");
                    }                
                }
                rs.close();

                //rs = stmt.executeQuery("Select * from S_KIT_DETAIL");
                String query2 = ("Select * from S_KIT_DETAIL(NOLOCK)");
                stmt = conn.prepareStatement(query2);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    item_no = rs.getString(1);
                    desc = rs.getString(3);
                    service = rs.getString(2);
                    QML = rs.getString(10);
                    series = rs.getString(11);
                    origin = rs.getString(12);
                    selleble = rs.getString(13);

                    if (desc == null || desc.equals(""))
                    {
                        desc = "--";
                    }
                    if (service == null || service.equals(""))
                    {
                        service = "--";
                    }
                    if (QML == null || QML.equals(""))
                    {
                        QML = "--";
                    }
                    if (series == null || series.equals(""))
                    {
                        series = "--";
                    }
                    if (origin == null || origin.equals(""))
                    {
                        origin = "--";
                    }
                    if (selleble == null || selleble.equals(""))
                    {
                        selleble = "--";
                    }

                    detail.add(item_no);
                    detail.add(desc);
                    detail.add("Kit");
                    detail.add(service);
                    detail.add("--");
                    detail.add(QML);
                    detail.add(series);
                    detail.add(origin);
                    detail.add(selleble);
                    
                    idb_full_path = idb_path_1 + item_no + idb_path_2;

                    File idb_File = new File(idb_full_path);
                    if (idb_File.isFile())
                    {
                        detail.add("YES");
                    }      
                    else
                    {
                        detail.add("NO");
                    }
                }
                rs.close();
                
                stmt.close();


                File ExcelFolder = null;
                ExcelFolder = new File(ecatPATH + "/dealer/excels/" + userCode + "/");
                if (!ExcelFolder.exists())
                {
                    ExcelFolder.mkdir();
                }

                String saveLocation = ecatPATH + "/dealer/excels/" + userCode + "/";
                // String format = "xls";
                WritableWorkbook workbook = Workbook.createWorkbook(new File(saveLocation + "ItemMaster.xls"));

                // CREATE A CELL FORMAT HEADING WITH WRAP

                WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
                WritableCellFormat headingFormat = new WritableCellFormat(heading);
                headingFormat.setWrap(true);
                headingFormat.setAlignment(Alignment.CENTRE);
                headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                headingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);


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

                WritableCellFormat textFormat_center = new WritableCellFormat(text);
                textFormat_center.setAlignment(Alignment.CENTRE);
                textFormat_center.setVerticalAlignment(VerticalAlignment.CENTRE);
                textFormat_center.setBorder(Border.ALL, BorderLineStyle.THIN);


                // CREATE A CELL FORMAT FOR TABLE HEADING

                WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
                WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
                tableHeadingFormat.setAlignment(Alignment.CENTRE);
                tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);


                WritableSheet excelReport = workbook.createSheet("ItemMaster", 0);

                excelReport.setColumnView(0, 7);
                excelReport.setColumnView(1, 10);
                excelReport.setColumnView(2, 25);
                excelReport.setColumnView(3, 15);
                excelReport.setColumnView(4, 15);
                excelReport.setColumnView(5, 15);
                excelReport.setColumnView(6, 15);
                excelReport.setColumnView(7, 15);
                excelReport.setColumnView(8, 15);
                excelReport.setColumnView(9, 15);
                excelReport.setColumnView(10, 15);
                
                int colspan = 11;

                int rowCounter = 0;
                int colCounter = 0;


                jxl.write.Label briefD = new jxl.write.Label(0, 0, "Item Master", headingFormat);
                excelReport.mergeCells(0, 0, colspan - 1, 0);
                excelReport.addCell(briefD);
                rowCounter = rowCounter + 2;

                jxl.write.Label colHeading1 = new jxl.write.Label(colCounter, rowCounter, "S No.", tableHeadingFormat);
                excelReport.addCell(colHeading1);
                colCounter++;

                jxl.write.Label colHeading2 = new jxl.write.Label(colCounter, rowCounter, "ITEM NO", tableHeadingFormat);
                excelReport.addCell(colHeading2);
                colCounter++;

                jxl.write.Label colHeading3 = new jxl.write.Label(colCounter, rowCounter, "DESCRIPTION", tableHeadingFormat);
                excelReport.addCell(colHeading3);
                colCounter++;

                jxl.write.Label colHeading4 = new jxl.write.Label(colCounter, rowCounter, "ITEM TYPE", tableHeadingFormat);
                excelReport.addCell(colHeading4);
                colCounter++;

                jxl.write.Label colHeading5 = new jxl.write.Label(colCounter, rowCounter, "SERVICEABILITY", tableHeadingFormat);
                excelReport.addCell(colHeading5);
                colCounter++;

                jxl.write.Label colHeading6 = new jxl.write.Label(colCounter, rowCounter, "REMARK", tableHeadingFormat);
                excelReport.addCell(colHeading6);
                colCounter++;

                jxl.write.Label colHeading7 = new jxl.write.Label(colCounter, rowCounter, "QMO", tableHeadingFormat);
                excelReport.addCell(colHeading7);
                colCounter++;

                jxl.write.Label colHeading8 = new jxl.write.Label(colCounter, rowCounter, "SERIES", tableHeadingFormat);
                excelReport.addCell(colHeading8);
                colCounter++;

                jxl.write.Label colHeading9 = new jxl.write.Label(colCounter, rowCounter, "ORIGIN", tableHeadingFormat);
                excelReport.addCell(colHeading9);
                colCounter++;

                jxl.write.Label colHeading10 = new jxl.write.Label(colCounter, rowCounter, "SELLEBLE IN INDIA", tableHeadingFormat);
                excelReport.addCell(colHeading10);
                colCounter++;
                
                jxl.write.Label colHeading11 = new jxl.write.Label(colCounter, rowCounter, "3D AVAILABILITY", tableHeadingFormat);
                excelReport.addCell(colHeading11);                
                colCounter = 0;

                rowCounter++;

                int p = 1;
                jxl.write.Label colVal1;
                jxl.write.Label colVal2;
                jxl.write.Label colVal3;
                jxl.write.Label colVal4;
                jxl.write.Label colVal5;
                jxl.write.Label colVal6;
                jxl.write.Label colVal7;
                jxl.write.Label colVal8;
                jxl.write.Label colVal9;
                jxl.write.Label colVal10;
                jxl.write.Label colVal11;
                
                for (int i = 0; i < detail.size(); i = i + 10)
                {
                    colVal1 = new jxl.write.Label(colCounter, rowCounter, "" + p, textFormat_center);
                    excelReport.addCell(colVal1);
                    colCounter++;

                    colVal2 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i), textFormat_LEFT);
                    excelReport.addCell(colVal2);
                    colCounter++;

                    colVal3 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 1), textFormat_LEFT);
                    excelReport.addCell(colVal3);
                    colCounter++;

                    colVal4 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 2), textFormat_center);
                    excelReport.addCell(colVal4);
                    colCounter++;

                    colVal5 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 3), textFormat_center);
                    excelReport.addCell(colVal5);
                    colCounter++;

                    colVal6 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 4), textFormat_LEFT);
                    excelReport.addCell(colVal6);
                    colCounter++;

                    colVal7 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 5), textFormat_center);
                    excelReport.addCell(colVal7);
                    colCounter++;

                    colVal8 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 6), textFormat_center);
                    excelReport.addCell(colVal8);
                    colCounter++;

                    colVal9 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 7), textFormat_center);
                    excelReport.addCell(colVal9);
                    colCounter++;

                    colVal10 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 8), textFormat_center);
                    excelReport.addCell(colVal10);
                    colCounter++;

                    colVal11 = new jxl.write.Label(colCounter, rowCounter, "" + detail.get(i + 9), textFormat_center);
                    excelReport.addCell(colVal11);                    
                    colCounter = 0;

                    rowCounter++;
                    p++;

                }

                workbook.write();
                workbook.close();

                String fileDownloadPath = mainURL + "/dealer/excels/" + userCode + "/ItemMaster.xls";
                
                
                ps.println("<html><body  onunload=window.open('" + servletURL + "DeleteExcel?usr="+userCode+"') target=_parent> ");
                ps.println("<center><br><br><br><br><br><table align=center width=300 height=10%>");
                ps.println("<tr><td bgcolor=#003399 align=center>");
                ps.println("<a href='" + fileDownloadPath + "' target=_blank><b><font face=helvetica size=2 color=#FFFFFF>Click Here To Open Item Master Excel</font></a>");
                ps.println("</td></tr>");
                ps.println("</table>");
                ps.println("</body></html>");
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            ps.println(e);
        }
        finally
        {
            ps.close();

        }
    }
}
