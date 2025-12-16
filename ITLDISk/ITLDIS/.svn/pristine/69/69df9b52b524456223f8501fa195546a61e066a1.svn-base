package viewEcat.orderEcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

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
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
Exporting Cart Items To Excel.
@author  Deepak Mangal	
@version v3.4
@since 
 */
public class ExportCartToExcel extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        PreparedStatement pst=null;
        res.setContentType("text/html");
        try {

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PriceDetails pd= new PriceDetails(holder.getConnection());
            Connection conn=null;
            String dealeraddr="";
            String dealeraddr2="";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sql="select dealerName,address from UM_DealerMaster where dealerCode='"+(String)session.getAttribute("dealerCode")+"'";
            conn=holder.getConnection();
            pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){

                dealeraddr=rs.getString(1);
                dealeraddr2=rs.getString(2);

            }
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String header = "";
            header = object_pageTemplate.header(getType);

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ps.println(header);
            if (session_id.equals(getSession)) {

                // CREATE A CELL FORMAT HEADING WITH WRAP

                WritableFont heading = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
                WritableCellFormat headingFormat = new WritableCellFormat(heading);
                headingFormat.setWrap(true);
                headingFormat.setAlignment(Alignment.CENTRE);
                headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                headingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

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


                // CREATE A CELL FORMAT FOR TABLE HEADING

                WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
                WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
                tableHeadingFormat.setAlignment(Alignment.CENTRE);
                tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);


                Vector totalParts;

                totalParts = (Vector) session.getValue("cartItems");


                if ((totalParts == null) || totalParts.size() == 0) {
                    ps.println("<br><br><br>");
                    ps.println("<br><br><br>");
                    ps.println("<br><br><br>");
                    ps.println("<br><br><br>");

                    ps.println("<center><table cellspacing=0 cellpadding=10 border=0 width=50% bordercolor=#D7D7D7>");
                    ps.println("<tr bgcolor = #003399>");
                    ps.println("<td align = center>");
                    ps.println("<font color = #FFFFFF><b>");
                    ps.println("PARTS CART EMPTY");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("<tr>");
                    ps.println("<td valign = middle align = right>");

                    ps.println("<a href = Main>");
                    ps.println("<b><font color=#000099>");
                    ps.println("<< HOME");
                    ps.println("</font></a>");

                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                } else {

                    // CREATE FILE NAME

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    long millis = timestamp.getTime();
                    String fileName = userCode + "_" + millis;
                    String mrp="";
                    String sno="" ;
                    double amt=0.0;
                    
                    //Excel related definitions start
                    String saveLocation = ecatPATH + "/dealer/excels/";
                    WritableWorkbook workbook = Workbook.createWorkbook(new File(saveLocation + fileName + ".xls"));

                    WritableSheet cartExcel = workbook.createSheet("Cart Excel", 0);

                    cartExcel.setColumnView(0, 8);
                    cartExcel.setColumnView(1, 15);
                    cartExcel.setColumnView(3, 15);
                    cartExcel.setColumnView(4, 15);
                    cartExcel.setColumnView(5, 15);
                    cartExcel.setColumnView(6, 15);


                    jxl.write.Label briefD = new jxl.write.Label(0, 0, dealeraddr, headingFormat);
                    //briefD.setCellFormat(textFormat)
                    cartExcel.mergeCells(0, 0, 5, 0);
                    cartExcel.addCell(briefD);

                    jxl.write.Label briefD1 = new jxl.write.Label(0, 1, dealeraddr2, headingFormat);
                    cartExcel.mergeCells(0, 1, 5, 1);
                    cartExcel.addCell(briefD1);

                    jxl.write.Label briefD2 = new jxl.write.Label(3, 3, "General Order", headingFormat);
                    cartExcel.mergeCells(0,3, 2, 3);
                    cartExcel.addCell(briefD2);

                    jxl.write.Label briefD3 = new jxl.write.Label(4, 3, "Dated", headingFormat);
                    cartExcel.addCell(briefD3);

                    jxl.write.Label briefD7 = new jxl.write.Label(5, 3, sdf.format(new java.util.Date()), headingFormat);
                    cartExcel.addCell(briefD7);

                    jxl.write.Label briefD4 = new jxl.write.Label(0, 4, "Sir", textFormat_LEFT);
                    cartExcel.mergeCells(0, 4, 5, 4);
                    cartExcel.addCell(briefD4);



                    jxl.write.Label briefD5 = new jxl.write.Label(0, 5, "Please supply the following material by road transport/through tractor driver/by ", textFormat_LEFT);
                    cartExcel.mergeCells(0, 5, 5, 5);
                    cartExcel.addCell(briefD5);

                    jxl.write.Label briefD6 = new jxl.write.Label(0, 6, "hand/courier at our own risk.", textFormat_LEFT);
                    cartExcel.mergeCells(0, 6, 5, 6);
                    cartExcel.addCell(briefD6);




                    jxl.write.Label briefD8 = new jxl.write.Label(0, 7, "SNO.", headingFormat);
                    cartExcel.addCell(briefD8);

                    jxl.write.Label briefD9 = new jxl.write.Label(1, 7, "PART NUMBER", headingFormat);
                    cartExcel.addCell(briefD9);

                    jxl.write.Label briefD10 = new jxl.write.Label(2, 7, "DESCRIPTION", headingFormat);
                    cartExcel.setColumnView(2, 40);
                    cartExcel.addCell(briefD10);

                    jxl.write.Label briefD11 = new jxl.write.Label(3, 7, "QTY", headingFormat);
                    cartExcel.addCell(briefD11);

                    jxl.write.Label briefD12 = new jxl.write.Label(4, 7, "MRP", headingFormat);
                    cartExcel.addCell(briefD12);

                    jxl.write.Label briefD13 = new jxl.write.Label(5, 7, "AMOUNT", headingFormat);
                    cartExcel.addCell(briefD13);

                    jxl.write.Label partVal = null;
                    jxl.write.Label qtyVal = null;
                    jxl.write.Label snumb = null;
                    jxl.write.Label amtVal = null;
                    jxl.write.Label descVal = null;
                    jxl.write.Label mrpVal = null;

                    int rowCounter = 8;
                    int ct=1;
                    for (int i = 0; i < totalParts.size(); i = i + 2) {
                        

                        snumb = new jxl.write.Label(0, rowCounter, "" + ct, textFormat_LEFT);
                        cartExcel.addCell(snumb);

                        partVal = new jxl.write.Label(1, rowCounter, "" + totalParts.elementAt(i), textFormat_LEFT);
                        cartExcel.addCell(partVal);

                        descVal = new jxl.write.Label(2, rowCounter, "" + pd.getPartDesc("" + totalParts.elementAt(i)), textFormat_LEFT);
                        
                        cartExcel.addCell(descVal);

                        mrp=pd.getPartPrice(totalParts.elementAt(i)+"","MRP_INR",new java.util.Date());

                        if(mrp!=null && !mrp.equals("")){

                         amt= Double.parseDouble(mrp)*Double.parseDouble("" + totalParts.elementAt(i + 1));
                        
                        }
                        else{
                        mrp="0";
                        amt=0.0;
                        }


                        qtyVal = new jxl.write.Label(3, rowCounter, "" + totalParts.elementAt(i + 1), textFormat_LEFT);
                        cartExcel.addCell(qtyVal);

                        mrpVal = new jxl.write.Label(4, rowCounter, "" + mrp, textFormat_LEFT);
                        cartExcel.addCell(mrpVal);

                        amtVal = new jxl.write.Label(5, rowCounter, "" + amt, textFormat_LEFT);
                        cartExcel.addCell(amtVal);


                        rowCounter++;
                        ct++;
                    }

                    workbook.write();
                    workbook.close();

                    session.removeValue("cartItems");

                    String url = mainURL + "dealer/excels/" + fileName + ".xls";
                    object_pageTemplate.ShowMsg(ps, "SUCCESS", "Your Order has been successfully saved in excel.", "YES", "Click here to download.", url, "", imagesURL);

                }

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }
}
