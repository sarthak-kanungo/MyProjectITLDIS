/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/***************************************************************************************************************
 *
 * @author Pramod Vishwakarma
 **  S.No.  Method                          Author              Version     Methods Detail
 *    1.    createTemplateWithData       Pramod Vishwakarma     1.0         to create templates with data.
 *    2.    getCompParamValue            Pramod Vishwakarma     1.0         to get component parameter values.
 *    3.    getComponentBom              Pramod Vishwakarma     1.0         to get Component Bom Vector.
 *    4.    isCompExist                  Pramod Vishwakarma     1.0         to check whether the Component Exist in CompDetails or not.
 *    5.    resizeImage                  Pramod Vishwakarma     1.0         to resize the image.
 *    6.    createThumb                  Pramod Vishwakarma     1.0         to create thumbnail of the image.
 *    7.    getAffectedGrps              Pramod Vishwakarma     1.0         to get affected grps by the Component.
 *    8.    getAffectedAsms              Pramod Vishwakarma     1.0         to get affected Asms by the Component.
 *    9.    getAffectedKits              Pramod Vishwakarma     1.0         to get affected Kits by the Component.
 *   10.    getAffectedGrpsInEcn         Pramod Vishwakarma     1.0         to get affected Grps in ECN.
 *   11.    getMinECNNumber              Pramod Vishwakarma     1.0         to get minimum ECN Number.
 *   12.    getECNDetails                Pramod Vishwakarma     1.0         to get ECN Details from database.
 *   13.    getAffectedGrpsStrFrmECNGrps Pramod Vishwakarma     1.0         to get Affected Grps String from database.
 *   14.    getECNIdFrmECNMaster         Pramod Vishwakarma     1.0         to get ECN Id from database.
 */
package EAMG.Group.Action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 *
 * @author pramod.vishwakarma
 */
import com.EAMG.common.EAMG_MethodsUtility;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import jxl.Workbook;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.NumberFormats;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class EAMG_MethodUtility3 {
    //1. to create templates with data.
    public boolean createTemplateWithData(String comptype, String path, Connection conn) {

        //declaration of variables used.
       // Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        Vector paramDescVec = new Vector();
        Vector paramDomainVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        int colCounter = 0, rowCounter = 0;
        WritableWorkbook workbook = null;
        String comp_no = "";
        String comp_desc = "",param_domain="";
        String param_val ="";
        int param_id = 0;
        String filepath = path + comptype + "_template.xls";
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
        ////System.out.println("File Path" + filepath);

        try {
            //getting the workbook.
            workbook = Workbook.createWorkbook(new File(filepath));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
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

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(NumberFormats.TEXT);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBackground(Colour.YELLOW);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);


            WritableCellFormat textFormat_CENTER = new WritableCellFormat(text);
            textFormat_CENTER.setAlignment(Alignment.CENTRE);
            textFormat_CENTER.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat textFormat_TOP = new WritableCellFormat(text);
            textFormat_TOP.setAlignment(Alignment.LEFT);
            textFormat_TOP.setVerticalAlignment(VerticalAlignment.TOP);

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.LEFT);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//				tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            WritableFont mandatoryFieldsHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat mandatoryFieldsTableHeadingFormat = new WritableCellFormat(mandatoryFieldsHeading);
            mandatoryFieldsTableHeadingFormat.setAlignment(Alignment.LEFT);
            mandatoryFieldsTableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            mandatoryFieldsTableHeadingFormat.setBackground(Colour.RED);
            mandatoryFieldsTableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormatRIGHT = new WritableCellFormat(tableHeading);
            tableHeadingFormatRIGHT.setAlignment(Alignment.RIGHT);
            tableHeadingFormatRIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setAlignment(Alignment.LEFT);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            integerFormat.setBackground(Colour.YELLOW);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setAlignment(Alignment.LEFT);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            floatFormat.setBackground(Colour.YELLOW);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableSheet ws = null;
            paramDescVec.removeAllElements();
            //creating a sheet and setting its column views.
            ws = workbook.createSheet("Template With Data", 0);
            ws.setColumnView(0, 15);
            ws.setColumnView(1, 20);
            ws.setColumnView(2, 10);
            ws.setColumnView(3, 5);
            ws.setColumnView(4, 5);
            ws.setColumnView(5, 5);
            ws.setColumnView(6, 8);
            ws.setColumnView(7, 15);
            ws.setColumnView(8, 10);
            ws.setColumnView(9, 10);
            ws.setColumnView(10, 10);
            ws.setColumnView(11, 10);
            ws.setColumnView(12, 10);
            ws.setColumnView(13, 10);
            ws.setColumnView(14, 10);
            ws.setColumnView(15, 10);


            ws.getSettings().setPaperSize(PaperSize.A4);
            ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
//				ws.getSettings().setFitWidth(1);
//				ws.getSettings().setFitHeight(1);
            ws.getSettings().setHeaderMargin(.5);
            ws.getSettings().setFooterMargin(.5);
            ws.getSettings().setTopMargin(.5);
            ws.getSettings().setBottomMargin(.5);
            if (comptype.equals("select"))
            {
                return false;
            }
            //if component type is Part.
            if (comptype.equals("PRT")) {

                jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "PART TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD1);

                jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "PART NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD3);
                jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "PART DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD4);
                label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                ws.addCell(label_10);


            }
            //if component type is Assembly.
            if (comptype.equals("ASM")) {

                jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "ASSEMBLY TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD1);

                jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "ASSEMBLY NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD3);
                jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "ASSEMBLY DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD4);
                label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

            }
            //if component type is Kit.
            if (comptype.equals("KIT")) {
                jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "KIT TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD1);

                jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "KIT NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD3);
                jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "KIT DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD4);
                label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                ws.addCell(label_10);
                jxl.write.Label briefD5 = new jxl.write.Label(1, 3, "KIT REMARKS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD5);
                label_10 = new jxl.write.Label(1, 4, "", textFormat_LEFT);
                ws.addCell(label_10);
            }

            //if component type is Tool.
            if (comptype.equals("TOL")) {

                jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "TOOL TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD1);

                jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "TOOL NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD3);
                jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "TOOL DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD4);
                label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                ws.addCell(label_10);
            }
            colCounter = 2;
            rowCounter = 2;
            //getting the parameter description from database and setting it into sheet.
            try {
                //st = conn.createStatement();
                //rs = st.executeQuery("select PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER where PARAM_TYPE='" + comptype + "' order by PARAM_DESC");
                String query = ("select PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='" + comptype + "' order by PARAM_DESC");
                st = conn.prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {
                    String param_desc = rs.getString(1);
                    paramDescVec.add(param_desc);
                    paramDomainVec.add(rs.getString(2));
                    jxl.write.Label briefD5 = new jxl.write.Label(colCounter, rowCounter, param_desc, tableHeadingFormat);
                    ws.addCell(briefD5);
                    colCounter++;
                }
                rs.close();
                rowCounter++;
                //getting the parameter values from database and setting it into sheet.
                //rs = st.executeQuery("select COMP_NO,COMP_DESC from COMP_DETAIL where COMP_TYPE='" + comptype + "'");
                String query1 = ("select COMP_NO,COMP_DESC from COMP_DETAIL(NOLOCK) where COMP_TYPE='" + comptype + "'");
                st = conn.prepareStatement(query1);
                rs = st.executeQuery();

                while (rs.next()) {
                    colCounter = 0;
                    comp_no = rs.getString(1);
                    jxl.write.Label briefD5 = new jxl.write.Label(colCounter, rowCounter, comp_no, textFormat_LEFT);
                    ws.addCell(briefD5);
                    colCounter = 1;
                    comp_desc = rs.getString(2);
                    briefD5 = new jxl.write.Label(colCounter, rowCounter, comp_desc, textFormat_LEFT);
                    ws.addCell(briefD5);
                    for (int i = 0; i < paramDescVec.size(); i++) {
                        colCounter++;
                        param_id = methodutil.getCompParamId("" + paramDescVec.elementAt(i), comptype, conn);
                        param_val = getCompParamValue(comp_no, param_id, conn);
                        param_domain= ""+paramDomainVec.elementAt(i);
                        //System.out.println("param_domain :"+param_domain);

                        if(param_domain.equals("Integer"))
                        {
                            briefD5 = new jxl.write.Label(colCounter, rowCounter, param_val, integerFormat);
                            ws.addCell(briefD5);
                        }
                        else if(param_domain.equals("Double"))
                        {
                            briefD5 = new jxl.write.Label(colCounter, rowCounter, param_val, floatFormat);
                            ws.addCell(briefD5);
                        }
                        else
                        {
                            briefD5 = new jxl.write.Label(colCounter, rowCounter, param_val, textFormat_LEFT);
                            ws.addCell(briefD5);
                        }
                    }
                    rowCounter++;
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //setting the bottom of the sheet.
            jxl.write.Label label_11 = new jxl.write.Label(0, rowCounter, "end", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_11);

            rowCounter += 2;
            label_11 = new jxl.write.Label(0, rowCounter, "", textFormat_LEFT);
            ws.addCell(label_11);
            jxl.write.Label label_12 = new jxl.write.Label(1, rowCounter, ":To Be Filled By User", tableHeadingFormat);
            ws.addCell(label_12);
            rowCounter++;

            jxl.write.Label label_13 = new jxl.write.Label(0, rowCounter, "", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_13);

            jxl.write.Label label_14 = new jxl.write.Label(1, rowCounter, ":Mandatory Fields", tableHeadingFormat);
            ws.addCell(label_14);

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    //2. to get component parameter values.
    public String getCompParamValue(String comp_no, int param_id, Connection conn) {

        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String param_value = "";
        try {
            //st = conn.createStatement();
            //rs = st.executeQuery("SELECT PARAM_VALUE FROM COMP_PARAM_VALUES WHERE PARAM_ID=" + param_id + " AND COMP_NO='" + comp_no + "'");
            String query = ("SELECT PARAM_VALUE FROM COMP_PARAM_VALUES(NOLOCK) WHERE PARAM_ID=" + param_id + " AND COMP_NO='" + comp_no + "'");
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            if (rs.next()) {
                param_value = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param_value;
    }
    //3. to get Bom Vector.
    public Vector getComponentBom(String comp_no,String comp_type, Vector bomVec ,Connection conn) {

        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String query="";
        if(comp_type.equals("ASM"))
        {
            query="Select COMPONENT,COMP_TYPE from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + comp_no + "'";
        }
        else
        {
            query="Select COMPONENT,COMP_TYPE from CAT_S_KIT_BOM(NOLOCK) where KIT_NO='" + comp_no + "'";
        }
        ////System.out.println("comp_no :"+comp_no);
        try {
            //st = conn.createStatement();
            //rs = st.executeQuery(query);
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            while (rs.next()) {
                String comp=rs.getString(1);
                String type=rs.getString(2);
                if(type.equals(comp_type))
                {
                    bomVec.add(comp);
                    bomVec=getComponentBom(comp,comp_type,bomVec,conn);
                }
            }
//            //System.out.println("bomVec :"+bomVec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bomVec;

    }
    //4. to check whether the Component exist or not.
    public String isCompExist(String comp_no,String comp_type, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt  = null;
        String result = "notpresent";
        //stmt = conn.createStatement();
        String query = "Select * from cat_part(NOLOCK) where part_no='" + comp_no + "'";
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            result="present";
        }
//        //System.out.println("result :"+result);

        rs.close();
        stmt.close();
        return result;
    }
    //5. to resize the image.
    public String resizeImage(String inputName,String outputName,int w,int h) throws Exception {
        String result="";
        FileOutputStream fout = new FileOutputStream(outputName);
        try {
            File originalFile = new File(inputName);
            ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());

            Image image = ii.getImage();
            Image resizedImage = null;

            resizedImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

            Image outputImage = new ImageIcon(resizedImage).getImage();

            BufferedImage imagemBuffer = new BufferedImage(outputImage.getWidth(null), outputImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            imagemBuffer.createGraphics().drawImage(outputImage, 0, 0, null);


            ////System.out.println("listFiles[i]: " + inputName);

            JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(fout);
            JPEGEncodeParam enParam = jencoder.getDefaultJPEGEncodeParam(imagemBuffer);

            enParam.setQuality(1.0F, true);
            jencoder.setJPEGEncodeParam(enParam);
            jencoder.encode(imagemBuffer);


            result="resized";
        } catch (Exception e) {
            e.printStackTrace();
            result="Image can not be Resized.";
            //System.out.println("Interrupted while loading image: " + e);
        }
        finally{
            fout.close();
        }
        return result;
    }
    //6. to create thumbnail of the image.
    public String createThumb(String inputName,String outputName) throws Exception {
        String result="";
        FileOutputStream fout = new FileOutputStream(outputName);
        try {
            File originalFile = new File(inputName);
            ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());

            Image image = ii.getImage();
            Image resizedImage = null;

            resizedImage = image.getScaledInstance(105, 105, Image.SCALE_SMOOTH);

            Image outputImage = new ImageIcon(resizedImage).getImage();

            BufferedImage imagemBuffer = new BufferedImage(outputImage.getWidth(null), outputImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            imagemBuffer.createGraphics().drawImage(outputImage, 0, 0, null);


            ////System.out.println("listFiles[i]: " + inputName);

            JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(fout);
            JPEGEncodeParam enParam = jencoder.getDefaultJPEGEncodeParam(imagemBuffer);

            enParam.setQuality(1.0F, true);
            jencoder.setJPEGEncodeParam(enParam);
            jencoder.encode(imagemBuffer);

            fout.close();
            result="created";
        } catch (Exception e) {
            e.printStackTrace();
            result="Image can not be Resized.";
            //System.out.println("Interrupted while loading image: " + e);
        }
        finally{
            fout.close();
        }
        return result;
    }
    //7. to get affected grps by the Component.
    public Vector getAffectedGrps(String comp_no, Connection conn) throws Exception {

        //Statement st = null;
        PreparedStatement st = null;
    	ResultSet rs = null;
        String grp = "";
        String asm = "";
        String kit = "";
        Vector affectedgrps=new Vector();
        Vector affectedasms=new Vector();
        Vector affectedkits=null;

        try {
            affectedasms=getAffectedAsms(comp_no,affectedasms,conn);
            affectedasms.add(comp_no);
            //st = conn.createStatement();
            //System.out.println("affectedasms in grp:"+affectedasms);
            for(int i=0;i<affectedasms.size();i++)
            {
                asm=""+affectedasms.elementAt(i);

                //rs = st.executeQuery("select GRP_KIT_NO from CAT_GROUP_KIT_BOM where COMPONENT='" + asm + "'");
                String query = ("select GRP_KIT_NO from CAT_GROUP_KIT_BOM(NOLOCK) where COMPONENT='" + asm + "'");
                st = conn.prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {
                    grp = rs.getString(1);
                    if(!affectedgrps.contains(grp))
                    {
                        affectedgrps.add(grp);
                    }
                }
            }

            affectedkits=getAffectedKits(comp_no,conn);
            for(int i=0;i<affectedkits.size();i++)
            {
                kit=""+affectedkits.elementAt(i);
               // rs = st.executeQuery("select GRP_KIT_NO from CAT_GROUP_KIT_BOM where COMPONENT='" + kit + "'");
                String query1 = ("select GRP_KIT_NO from CAT_GROUP_KIT_BOM(NOLOCK) where COMPONENT='" + kit + "'");
                st = conn.prepareStatement(query1);
                rs = st.executeQuery();
                while (rs.next()) {
                    grp = rs.getString(1);
                    if(!affectedgrps.contains(grp))
                    {
                        affectedgrps.add(grp);
                    }
                }
            }

            //System.out.println("affectedgrps :"+affectedgrps);

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedgrps;
    }
    //8. to get affected Asms by the Component.
    public Vector getAffectedAsms(String comp_no,Vector affectedasms, Connection conn)  throws Exception{
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String asm = "";

        try {
            //st = conn.createStatement();
           // rs = st.executeQuery("select ASSY_NO from CAT_ASSY_BOM where COMPONENT='" + comp_no + "'");
            String query = ("select ASSY_NO from CAT_ASSY_BOM(NOLOCK) where COMPONENT='" + comp_no + "'");
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                asm = rs.getString(1);
                if(!affectedasms.contains(asm))
                {
                    affectedasms.add(asm);
                }
               affectedasms=getAffectedAsms(asm,affectedasms,conn);

            }

            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedasms;
    }
    //9. to get affected Kits by the Component.
    public Vector getAffectedKits(String comp_no, Connection conn)  throws Exception{
        Vector affectedkits=new Vector();
        Vector affectedasms=new Vector();
        //Statement st = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String kit = "";
        String asm = "";
        try {
            affectedasms=getAffectedAsms(comp_no,affectedasms,conn);
            affectedasms.add(comp_no);
            //st = conn.createStatement();
            for(int i=0;i<affectedasms.size();i++)
            {
                asm=""+affectedasms.elementAt(i);

                //rs = st.executeQuery("select KIT_NO from CAT_S_KIT_BOM where COMPONENT='" + asm + "'");
                String query = ("select KIT_NO from CAT_S_KIT_BOM(NOLOCK) where COMPONENT='" + asm + "'");
                st = conn.prepareStatement(query);
                rs = st.executeQuery();
                while (rs.next()) {
                    kit = rs.getString(1);
                    if(!affectedkits.contains(kit))
                    {
                        affectedkits.add(kit);
                    }
                }
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedkits;
    }
    //10. to get affected Grps in ECN.
    public Vector getAffectedGrpsInEcn(String model_no, String item1,Connection conn) throws Exception {
        Vector affectedgrps=new Vector();
        String grp_no = "";
        String query1 = "";
        String query2 = "";
        //Statement stmt1 = null;
        //Statement stmt2 = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet rs1, rs2;
        query1 = "Select distinct GROUP_NO from CAT_MODEL_GROUP(NOLOCK) where MODEL_NO='" + model_no + "' and REV_NO=(Select max(REV_NO) from MODEL_REVISIONS where MODEL_NO='" + model_no + "')";
        //stmt1 = conn.createStatement();
        stmt1 = conn.prepareStatement(query1);		
        //rs1 = stmt1.executeQuery(query1);
        rs1 = stmt1.executeQuery();
        while (rs1.next()) {
            grp_no = rs1.getString(1);
            query2 = "Select GRP_KIT_NO from CAT_GROUP_KIT_BOM(NOLOCK) where GRP_KIT_NO='" + grp_no + "' and COMPONENT='" + item1 + "'";
            //stmt2 = conn.createStatement();
            stmt2 = conn.prepareStatement(query2);
            rs2 = stmt2.executeQuery();
            //rs2 = stmt2.executeQuery(query2);
            while (rs2.next()) {
                if (!affectedgrps.contains(grp_no)) {
                    affectedgrps.add(rs2.getString(1));
                }
            }
            rs2.close();

        }
        rs1.close();
        //System.out.println("affectedgrps :"+affectedgrps);
        return affectedgrps;
    }
    //11. to get minimum ECN Number.
    public int getMinECNNumber(Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        int ecnno = 0;
        String query = "";
        query = "select min(ECN_NO) from ecn_status(NOLOCK) where STATUS <> 'CLOSE'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
       // rs = stmt.executeQuery(query);
        if (rs.next()) {
            ecnno=rs.getInt(1);
        }
        rs.close();
        return ecnno;
    }
    public int getMaxECNNumber(Connection conn) throws Exception {
        ResultSet rs;
       // Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        int ecnno = 0;
        String query = "";
        query = "select max(ECN_NO) from ecn_status(NOLOCK)";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        if (rs.next()) {
            ecnno=rs.getInt(1);
        }
        rs.close();
        return ecnno;
    }
    //12. to get ECN Details from database.
    public Vector getECNDetails(int ecnno,String status, Connection conn) throws Exception {
        Vector detailsVec = new Vector();
        Vector modelVec = new Vector();
        Vector effDateVec = new Vector();
        Vector esnVec = new Vector();
        Vector itemVec = new Vector();
        Vector changeTypeVec = new Vector();
        Vector replacedByVec = new Vector();
        Vector quantityVec = new Vector();
        Vector statusVec = new Vector();
        Vector ecnIdVec = new Vector();

        ResultSet rs;
        //Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        String query = "";
        String replacedby="";
        int esn = 0;
        Date effdate=new Date();
        if(status.equals("YES")){
            query = "Select MODEL_NO,EFF_DATE,START_ESN,ITEM,CHANGE_TYPE,REPLACE_BY,QUANTITY,STATUS,ECN_ID from ECN_MASTER(NOLOCK) where STATUS='"+status+"' and ECN_NO=" + ecnno;
        }else{
            query = "Select MODEL_NO,EFF_DATE,START_ESN,ITEM,CHANGE_TYPE,REPLACE_BY,QUANTITY,STATUS,ECN_ID from ECN_MASTER(NOLOCK) where ECN_NO=" + ecnno;
        }
       stmt = conn.prepareStatement(query);
       rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);

        while (rs.next()) {
            modelVec.add(rs.getString(1));
            effdate=rs.getDate(2);
            esn=rs.getInt(3);
            if(effdate==null || esn==0)
            {
                return detailsVec;
            }
            effDateVec.add(effdate);
            esnVec.add(esn);
            itemVec.add(rs.getString(4));
            changeTypeVec.add(rs.getString(5));
            replacedby=rs.getString(6);
            //System.out.println("replacedby :"+replacedby);
            if(replacedby==null)
            {
                replacedByVec.add("-");
            }
            else{
                replacedByVec.add(replacedby);
            }

            quantityVec.add(rs.getString(7));
            statusVec.add(rs.getString(8));
            ecnIdVec.add(rs.getInt(9));
        }

        detailsVec.add(modelVec);
        detailsVec.add(effDateVec);
        detailsVec.add(esnVec);
        detailsVec.add(itemVec);
        detailsVec.add(changeTypeVec);
        detailsVec.add(replacedByVec);
        detailsVec.add(quantityVec);
        detailsVec.add(statusVec);
        detailsVec.add(ecnIdVec);
        //System.out.println("detailsVec :"+detailsVec);
        rs.close();
        stmt.close();
        return detailsVec;
    }
    public boolean isCompleteModel(String model_no, Connection conn) throws Exception {
        boolean flag=false;
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select * from cat_models(NOLOCK) where MODEL_NO='" + model_no+"' and STATUS='COMPLETE'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            flag=true;
        }
        return flag;
    }
    public Vector getInValidECNDetails(int ecn_no,String model_no,Vector invalidEcnVec, Connection conn) throws Exception {
        ResultSet rs=null,rs1=null;
        //Statement stmt = null,stmt1 = null;
        PreparedStatement stmt = null,stmt1 = null;
        
        String item="";
        //getting the parent id of the component.
        String query = "select ITEM from ecn_master(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='"+model_no+"' and (CHANGE_TYPE='DELETE' or CHANGE_TYPE='REPLACE')";
        String query1 = "select mg.MODEL_NO,mg.GROUP_NO from cat_model_group mg,cat_group_kit_bom gkt where gkt.GRP_KIT_NO=mg.GROUP_NO and gkt.COMPONENT='P2'";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next()) {
            item=rs.getString(1);
            //stmt1 = conn.createStatement();
            query1 = "select mg.MODEL_NO,mg.GROUP_NO from cat_model_group(NOLOCK) mg,cat_group_kit_bom gkt where gkt.GRP_KIT_NO=mg.GROUP_NO and gkt.COMPONENT='"+item+"'";
            //rs1 = stmt1.executeQuery(query1);
            stmt1 = conn.prepareStatement(query1);
            rs1 = stmt1.executeQuery();
            if (!rs1.next()) {
                if((!invalidEcnVec.contains(model_no)) && (!invalidEcnVec.contains(model_no))){
                    invalidEcnVec.add(model_no);
                    invalidEcnVec.add(item);
                }

            }
            rs1.close();
            stmt1.close();
        }
        rs.close();

        stmt.close();

        return invalidEcnVec;
    }
    //13. to get Affected Grps String from database.
    public String getAffectedGrpsStrFrmECNGrps(int ecnno, Vector ecndetailsVec, int i, Connection conn) throws Exception {
        String ecngrps = "";
        ResultSet rs1;
        //Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        String query1 = "";

        int ecnid = getECNIdFrmECNMaster(ecnno, ecndetailsVec, i, conn);
        query1 = "Select GROUP_NO from ECN_GROUPS(NOLOCK) where ECN_ID=" + ecnid;
        //stmt = conn.createStatement();
        //rs1 = stmt.executeQuery(query1);
        stmt = conn.prepareStatement(query1);
        rs1 = stmt.executeQuery();
        if (rs1.next()) {
            ecngrps = rs1.getString(1);
            while (rs1.next()) {
                ecngrps +=","+ rs1.getString(1);
            }
        }
        rs1.close();
        stmt.close();
        //System.out.println("ecngrps :"+ecngrps);
        return ecngrps;
    }
    //14. to get ECN Id from database.
    public int getECNIdFrmECNMaster(int ecnno, Vector ecndetailsVec, int i, Connection conn) throws Exception {
        Vector modelVec = (Vector)ecndetailsVec.elementAt(0);
        Vector effDateVec = (Vector)ecndetailsVec.elementAt(1);
        Vector esnVec = (Vector)ecndetailsVec.elementAt(2);
        Vector itemVec = (Vector)ecndetailsVec.elementAt(3);
        Vector changeTypeVec = (Vector)ecndetailsVec.elementAt(4);
        ResultSet rs;
        PreparedStatement ps=null;
        String query = "";
        int ecnid=0;
        query = "select ECN_ID from ECN_MASTER(NOLOCK) where ECN_NO=? and MODEL_NO=? and EFF_DATE=? and START_ESN=? and ITEM=? and CHANGE_TYPE=?";
        ps=conn.prepareStatement(query);
        ps.setInt(1,ecnno);
        ps.setString(2,""+modelVec.elementAt(i));
        ps.setDate(3,(java.sql.Date)effDateVec.elementAt(i));
        ps.setInt(4,((Integer)esnVec.elementAt(i)).intValue());
        ps.setString(5,""+itemVec.elementAt(i));
        ps.setString(6,""+changeTypeVec.elementAt(i));
        rs=ps.executeQuery();
        if (rs.next()) {
            ecnid=rs.getInt(1);
        }
        return ecnid;
    }
     //14. to get ECN Id from database.

    public Vector findCostomizedAffectedGroups(Vector groupVec,TreeMap masterMap) throws Exception {
        Vector affectedModelVec=new Vector();
        Vector affectedGrpsVec=new Vector();
        Vector finalVec=new Vector();
        finalVec.add(affectedModelVec);
        finalVec.add(affectedGrpsVec);
        Vector tempVec = new Vector();
        Set set = masterMap.entrySet() ;
         Iterator iterator = set.iterator() ;
         while (  iterator.hasNext (  )   )   {
           Map.Entry entry =  ( Map.Entry ) iterator.next (  ) ;
           tempVec.add(entry.getValue ());
          }
         for(int i=0;i<groupVec.size();i++){
             String grp_no=""+groupVec.elementAt(i);
             String model_no=""+((Vector)tempVec.elementAt(i)).elementAt(0);
             finalVec=checkGrpDetailsInECN(grp_no,model_no,tempVec,finalVec);
         }


        return finalVec;
    }
    public Vector checkGrpDetailsInECN(String grp_no,String model_no,Vector tempVec,Vector finalVec) throws Exception {
        Vector modelVec=new Vector();
        Vector affectedModelVec=(Vector)finalVec.elementAt(0);
        Vector affectedGrpsVec=(Vector)finalVec.elementAt(1);
        boolean flag1=true;
         for(int i=0;i<tempVec.size();i++){
             Vector masterVec=(Vector)tempVec.elementAt(i);
             if(masterVec.contains(grp_no)){
                 if(!modelVec.contains(model_no)){
                     modelVec.add(model_no);
                 }

                 Vector indexVec=findAffectedGroupIndexVec(grp_no,i+1,tempVec);
                 for(int j=0;j<indexVec.size();j++){
                     int ind=Integer.parseInt(""+indexVec.elementAt(j));
                     Vector tempVec1=(Vector)tempVec.elementAt(ind);
                     Vector tempVec3=new Vector();
                     if(masterVec.size()!=tempVec1.size()){
                             affectedGrpsVec.add(grp_no);
                             tempVec3.add(tempVec1.elementAt(0));
                             affectedModelVec.add(tempVec3);
                             finalVec.add(affectedModelVec);
                             finalVec.add(affectedGrpsVec);
                         }else{
                             boolean flag=true;
                             for(int k=2;k<tempVec1.size();k++){
                                 String masterCompNo=""+((Vector)masterVec.elementAt(k)).elementAt(0);
                                 String masterChangeType=""+((Vector)masterVec.elementAt(k)).elementAt(1);
                                 String masterReplacedBy=""+((Vector)masterVec.elementAt(k)).elementAt(2);
                                 int masterQty=Integer.parseInt(""+((Vector)masterVec.elementAt(k)).elementAt(3));
                                 Vector tempVec2=(Vector)tempVec1.elementAt(k);
                                 if(tempVec2.contains(masterCompNo)){
                                     if(!tempVec2.elementAt(1).equals(masterChangeType)){
                                        affectedGrpsVec.add(grp_no);
                                         tempVec3.add(tempVec1.elementAt(0));
                                         affectedModelVec.add(tempVec3);
                                         finalVec.add(affectedModelVec);
                                         finalVec.add(affectedGrpsVec);
                                         flag=false;
                                         break;
                                     }
                                     if(!tempVec2.elementAt(2).equals(masterReplacedBy)){
                                        affectedGrpsVec.add(grp_no);
                                         tempVec3.add(tempVec1.elementAt(0));
                                         affectedModelVec.add(tempVec3);
                                         finalVec.add(affectedModelVec);
                                         finalVec.add(affectedGrpsVec);
                                         flag=false;
                                         break;
                                     }
                                     if(!tempVec2.elementAt(3).equals(""+masterQty)){
                                        affectedGrpsVec.add(grp_no);
                                         tempVec3.add(tempVec1.elementAt(0));
                                         affectedModelVec.add(tempVec3);
                                         finalVec.add(affectedModelVec);
                                         finalVec.add(affectedGrpsVec);
                                         flag=false;
                                         break;
                                     }
                                 }
                                 else if(tempVec1.size()==3){

                                     tempVec3.add(tempVec1.elementAt(0));
                                     if(!affectedModelVec.contains(tempVec3)){
                                         affectedModelVec.add(tempVec3);
                                         affectedGrpsVec.add(grp_no);
                                         finalVec.add(affectedModelVec);
                                         finalVec.add(affectedGrpsVec);
                                     }
                                     flag=false;
                                     break;
                                 }
                             }
                         if(flag){
                            modelVec.add(tempVec1.elementAt(0));
                         }
                         }
                 }

             }
         }
        affectedGrpsVec.add(grp_no);
        affectedModelVec.add(modelVec);
        finalVec.add(affectedModelVec);
        finalVec.add(affectedGrpsVec);
        return finalVec;
    }
    public Vector findAffectedGroupIndexVec(String grp_no,int index,Vector tempVec) throws Exception {
        Vector indexVec=new Vector();
        for(int i=index;i<tempVec.size();i++){
            Vector tempVec1=(Vector)tempVec.elementAt(i);
             if(tempVec1.contains(grp_no)){
                 indexVec.add(i);
             }
        }

        return indexVec;
    }
    public Vector findMissingComponentsInDb(int ecn_no,Connection conn) throws Exception {
        Vector missingCompVec=new Vector();
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        //stmt = conn.createStatement();
        String query = "";
        String item = "";
        String changeType = "";
        String replacedBy = "";

        query = "select ITEM,CHANGE_TYPE, REPLACE_BY from ecn_master(NOLOCK) where ECN_NO="+ ecn_no;
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()) {
            item=rs.getString(1);
            changeType=rs.getString(2);
            replacedBy=rs.getString(3);
            if(changeType.equals("ADD")){
                String comp_check = methodutil.isCompPresent(item, conn);
                if (comp_check.equals("notpresent")) {
                    if(!missingCompVec.contains(item))
                        missingCompVec.add(item);
                }
            }
            if(changeType.equals("REPLACE")){
                String comp_check = methodutil.isCompPresent(replacedBy, conn);
                if (comp_check.equals("notpresent")) {
                    if(!missingCompVec.contains(replacedBy))
                        missingCompVec.add(replacedBy);
                }
            }
        }
        rs.close();
        stmt.close();
        return missingCompVec;
    }
    public boolean updateDatabaseInECN(int ecn_no,String old_grp_no,String new_grp_no,String models,TreeMap masterGroupMap,Connection conn) throws Exception {
        boolean status=false;
        int ecnid = 0;
        int rev_no = 0;
        int start_esn = 0;
        int end_esn = 0;
        Vector modelChangeDetails=new Vector();
        Vector modelRevDetails=new Vector();
        String[] modelArr=models.split(",");
        String model_no="";
        for(int i=0;i<modelArr.length;i++){
            model_no=modelArr[i];
            model_no=model_no.replace('[', ' ').trim();
            model_no=model_no.replace(']', ' ').trim();
            rev_no=getMaxRevNoInECN(model_no,conn);
            start_esn=getStartESN(ecn_no,model_no,conn);
            setEndESN(rev_no, model_no,start_esn, conn);
            modelRevDetails.removeAllElements();
            modelRevDetails.add(ecn_no);
            modelRevDetails.add(model_no);
            modelRevDetails.add(rev_no+1);
            modelRevDetails.add(start_esn);
            modelRevDetails.add(end_esn);
            modelRevDetails.add("INCOMPLETE");

            insertModelRevision(modelRevDetails,conn);
            updateModelGroup(rev_no,model_no,old_grp_no,new_grp_no, conn);
            Vector detailsVec=(Vector)masterGroupMap.get(model_no+old_grp_no);
            for(int k=2;k<detailsVec.size();k++){
                Vector tempvec=(Vector)detailsVec.elementAt(k);
                ecnid=Integer.parseInt(""+tempvec.elementAt(4));
                String item=""+tempvec.elementAt(0);
                String change_type=""+tempvec.elementAt(1);
                String replaced_by=""+tempvec.elementAt(2);
                if(replaced_by.equals("-")){
                    replaced_by="";
                }
                setGroupECNStatus(ecnid,old_grp_no,conn);
                modelChangeDetails.removeAllElements();
                modelChangeDetails.add(ecn_no);
                modelChangeDetails.add(model_no);
                modelChangeDetails.add(old_grp_no);
                modelChangeDetails.add(new_grp_no);
                modelChangeDetails.add(item);
                modelChangeDetails.add(change_type);
                modelChangeDetails.add(replaced_by);
                modelChangeDetails.add(rev_no+1);
                status=insertModelChanges(modelChangeDetails,conn);
            }
        }

        return status;
    }
    public int getMaxRevNoInECN(String model_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select max(REV_NO) from model_revisions(NOLOCK) where MODEL_NO='" + model_no+"'and STATUS='COMPLETE'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int rev_no = 0;
        if (rs.next()) {
            rev_no = rs.getInt(1);
        }
        return rev_no;
    }
    public Vector getTempModelGroupInECN(String model_no,String group_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        Vector tempModelGroupVec=new Vector();
        String query = "select * from temp_model_group(NOLOCK) where MODEL_NO='" + model_no+"'and GROUP_NO='" + group_no+"'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            tempModelGroupVec.add(rs.getString(1));
            tempModelGroupVec.add(rs.getString(2));
            tempModelGroupVec.add(rs.getString(3));
            tempModelGroupVec.add(rs.getString(4));
            tempModelGroupVec.add(rs.getString(5));
            tempModelGroupVec.add(rs.getString(6));
            tempModelGroupVec.add(""+rs.getInt(7));
            tempModelGroupVec.add(""+rs.getInt(8));
        }
        return tempModelGroupVec;
    }
    public int getStartESN(int ecn_no,String model_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select distinct(START_ESN) from ecn_master(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='" + model_no+"'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int start_esn = 0;
        if (rs.next()) {
            start_esn = rs.getInt(1);
        }
        return start_esn;
    }
    public int setEndESN(int rev_no,String model_no,int start_esn,Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        PreparedStatement ps = null;
        int ecn_no=0;
        String query = "select max(ECN_NO) from ecn_status(NOLOCK) where  STATUS='CLOSE'";
        //stmt = conn.createStatement();
       // rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int end_esn = 0;
        if (rs.next()) {
            ecn_no = rs.getInt(1);
            query = "update model_revisions set END=? where  ECN_NO="+ecn_no+" and MODEL_NO='"+model_no+"' and REV_NO="+rev_no;
            ps = conn.prepareStatement(query);
            ps.setInt(1, start_esn-1);
            ps.executeUpdate();
            ps.close();
        }
        return end_esn;
    }
    public void setGroupECNStatus(int ecnid,String old_grp_no,Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        String update_status_query = "update ecn_groups set STATUS='CLOSE' where ECN_ID=? and GROUP_NO=?";
        pstmt=conn.prepareStatement(update_status_query);
        pstmt.setInt(1,ecnid);
        pstmt.setString(2,old_grp_no);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateModelGroup(int rev_no,String model_no,String old_grp_no,String new_grp_no, Connection conn) throws Exception {
        ResultSet rs,rs1;
        //Statement stmt = null,stmt1 = null;
        PreparedStatement stmt = null,stmt1 = null;
        PreparedStatement ps = null;
        String query = "select * from temp_model_group(NOLOCK) where MODEL_NO='"+model_no+"' and REV_NO="+(rev_no+1);
        //stmt1 = conn.createStatement();
        //rs1 = stmt1.executeQuery(query);
        stmt1 = conn.prepareStatement(query);
        rs1 = stmt1.executeQuery();
        if(!rs1.next()){
            query = "select * from cat_model_group(NOLOCK) where MODEL_NO='"+model_no+"' and REV_NO="+rev_no;
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery(query);
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            query = "insert into temp_model_group values (?,?,?,?,?,?,?,?)";
            while(rs.next()){
                ps = conn.prepareStatement(query);
                ps.setString(1, model_no);
                ps.setString(2, rs.getString(2));
                ps.setString(3, rs.getString(3));
                ps.setString(4, rs.getString(4));
                ps.setString(5, rs.getString(5));
                ps.setString(6, rs.getString(6));
                ps.setInt(7, rs.getInt(7));
                ps.setInt(8, rev_no+1);
                ps.executeUpdate();
                ps.close();
            }
        }
        query = "update temp_model_group set GROUP_NO=? where MODEL_NO=? and REV_NO=? and GROUP_NO=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, new_grp_no);
        ps.setString(2, model_no);
        ps.setInt(3, rev_no+1);
        ps.setString(4, old_grp_no);
        ps.executeUpdate();
        ps.close();

    }
    public boolean insertModelRevision(Vector modelRevDetails,Connection conn) throws Exception {
        boolean flag=false;
        String insert_changes_query = "insert into model_revisions values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt=null;
        int ecn_no=Integer.parseInt(""+modelRevDetails.elementAt(0));
        String model_no=""+modelRevDetails.elementAt(1);
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select * from model_revisions(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='" + model_no+"'";
        //stmt = conn.createStatement();
       // rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (!rs.next()) {
            pstmt=conn.prepareStatement(insert_changes_query);
            pstmt.setString(1,model_no);
            pstmt.setInt(2,ecn_no);
            pstmt.setInt(3,Integer.parseInt(""+modelRevDetails.elementAt(2)));
            pstmt.setInt(4,Integer.parseInt(""+modelRevDetails.elementAt(3)));
            pstmt.setInt(5,Integer.parseInt(""+modelRevDetails.elementAt(4)));
            pstmt.setDate(6,getECNEffectiveDate(model_no,ecn_no,Integer.parseInt(""+modelRevDetails.elementAt(3)), conn));
            pstmt.setString(7,""+modelRevDetails.elementAt(5));

            pstmt.executeUpdate();
        }

        flag=true;
        return flag;
    }
    public boolean insertModelChanges(Vector modelChangeDetails,Connection conn) throws Exception {
        boolean flag=false;
        String insert_changes_query = "insert into model_changes values (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=null;
        pstmt=conn.prepareStatement(insert_changes_query);
        pstmt.setInt(1,Integer.parseInt(""+modelChangeDetails.elementAt(0)));
        pstmt.setString(2,""+modelChangeDetails.elementAt(1));
        pstmt.setString(3,""+modelChangeDetails.elementAt(2));
        pstmt.setString(4,""+modelChangeDetails.elementAt(3));
        pstmt.setString(5,""+modelChangeDetails.elementAt(4));
        pstmt.setString(6,""+modelChangeDetails.elementAt(5));
        pstmt.setString(7,""+modelChangeDetails.elementAt(6));
        pstmt.setInt(8,Integer.parseInt(""+modelChangeDetails.elementAt(7)));
        pstmt.executeUpdate();
        flag=true;
        return flag;
    }
    public Vector getGroupDetailsInECN(String grp_no,Connection conn) throws Exception {
        Vector groupDetailsVec=new Vector();
        Vector compNoVec=new Vector();
        Vector qtyVec=new Vector();
        ResultSet rs;
       // Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        String query = "";
        query = "select COMPONENT,QTY from cat_group_kit_bom(NOLOCK) where GRP_KIT_NO='"+grp_no+"'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next()) {
            String comp_no=rs.getString(1);
            String qty=rs.getString(2);
            if(qty.equals("AR")){
                qty="0";
            }
            if(!compNoVec.contains(comp_no)){
                compNoVec.add(comp_no);
                qtyVec.add(qty);
            }else{
                int ind=compNoVec.indexOf(comp_no);
                int qty1=Integer.parseInt(""+qtyVec.elementAt(ind));
                qty+=qty1;
                qtyVec.remove(ind);
                qtyVec.insertElementAt(""+qty, ind);
            }

        }
        rs.close();
        stmt.close();
        groupDetailsVec.add(compNoVec);
        groupDetailsVec.add(qtyVec);
        return groupDetailsVec;
    }
    public static Vector createdGrpNoVec=new Vector();
    public String getNewGroupNoInECN(String grp_no,Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        if(createdGrpNoVec.size()==0){
            getAllECNGrpNo(createdGrpNoVec, conn);
        }
        String query = "";
        String new_grp_no="";
        String temp_grp_no="";
        LinkedList tempVec=new LinkedList();
        int ind=0;
        if(grp_no.indexOf("--")!=-1){
            String[] tempArr=grp_no.split("--");
            grp_no=tempArr[0];
        }

        query = "select GRP_KIT_NO from cat_group_kit_detail(NOLOCK) where GRP_KIT_NO LIKE '"+grp_no+"%'";
       // rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()) {
            temp_grp_no=rs.getString(1);
            if(temp_grp_no.indexOf("--")!=-1){
                String[] tempArr=temp_grp_no.split("--");
                if(tempArr[0].equals(grp_no))
                tempVec.add(tempArr[1]);
            }
        }
        Collections.sort(tempVec);
        String indStr="";
        if(tempVec.size()==0){
            indStr="0";
        }else{
            indStr=""+tempVec.getLast();
        }

        ind=Integer.parseInt(indStr)+1;
        new_grp_no=grp_no+"--"+ind;
        while(createdGrpNoVec.contains(new_grp_no)){
            new_grp_no=grp_no+"--"+ind;
            ind++;
        }
        createdGrpNoVec.add(new_grp_no);
        rs.close();
        stmt.close();
        return new_grp_no;
    }
    public boolean checkModifiedGroupBOM(String grp_no,String model_no,TreeMap masterMap,ArrayList compNoAl,ArrayList quantityAl,ArrayList delAl,Connection conn) throws Exception {
        boolean isValidChange=true;
        Vector groupDetailsVec=getGroupDetailsInECN(grp_no,conn);
        Vector compNoVecInDb=(Vector)groupDetailsVec.elementAt(0);
        Vector qtyVecInDb=(Vector)groupDetailsVec.elementAt(1);
        Vector compNoVec=new Vector();
        Vector qtyVec=new Vector();
        Vector affectedGrp=(Vector)masterMap.get(model_no+grp_no);
        for(int i=0;i<compNoAl.size();i++){
            if(!delAl.contains(""+(i+1))){
                String comp_no=""+compNoAl.get(i);
                comp_no=comp_no.toUpperCase();
                String qty=""+quantityAl.get(i);
                if(qty.equals("AR")){
                    qty="0";
                }
                if(!compNoVec.contains(comp_no)){
                    compNoVec.add(comp_no);
                    qtyVec.add(qty);
                }else{
                    int ind=compNoVec.indexOf(comp_no);
                    int qty1=Integer.parseInt(qty);
                    int qty2=Integer.parseInt(""+qtyVec.elementAt(ind));
                    qty1+=qty2;
                    qtyVec.remove(ind);
                    qtyVec.insertElementAt(""+qty1, ind);
                }
            }

        }
        Vector modifiedCompVec=new Vector();
        for(int i=0;i<compNoVecInDb.size();i++){
            if(!modifiedCompVec.contains(compNoVecInDb.elementAt(i))){
                    modifiedCompVec.add(compNoVecInDb.elementAt(i));
                }
        }

        for(int k=2;k<affectedGrp.size();k++){
            String comp_no=""+((Vector)affectedGrp.elementAt(k)).elementAt(0);
            String change_type=""+((Vector)affectedGrp.elementAt(k)).elementAt(1);
            String qtyStr=""+((Vector)affectedGrp.elementAt(k)).elementAt(3);
            if(qtyStr.equals("AR")){
                qtyStr="0";
            }
            int qty=Integer.parseInt(qtyStr);
            int ind=compNoVec.indexOf(comp_no);
            int ind_db=compNoVecInDb.indexOf(comp_no);
            /*if(change_type.equals("DELETE")){
                if(ind==-1){
                    int qty1=Integer.parseInt(""+qtyVecInDb.elementAt(ind_db));
                    if(qty!=qty1)
                        return false;
                    modifiedCompVec.remove(comp_no);
                }else{
                    int qty1=Integer.parseInt(""+qtyVecInDb.elementAt(ind_db))-qty;
                    int qty2=Integer.parseInt(""+qtyVec.elementAt(ind));
                    if(qty2!=qty1){
                        return false;
                    }
                }
            }*/
            if(change_type.equals("ADD")){
                if(ind==-1){
                    return false;
                }else if(ind_db==-1){
                    int qty2=Integer.parseInt(""+qtyVec.elementAt(ind));
                    if(qty2!=qty){
                        return false;
                    }
                }else{
                    int qty2=Integer.parseInt(""+qtyVec.elementAt(ind));
                    int qty1=Integer.parseInt(""+qtyVecInDb.elementAt(ind_db))+qty;
                    if(qty2!=qty1){
                        return false;
                    }
                }
                if(!modifiedCompVec.contains(comp_no)){
                    modifiedCompVec.add(comp_no);
                }
            }else{
                if(change_type.equals("DELETE")){
                    if(ind==-1){
                        modifiedCompVec.remove(comp_no);
                    }else{
                        return false;
                    }
                }
                if(change_type.equals("REPLACE")){
                    if(ind_db==-1){
                        return false;
                    }else{
                        String replaced_by=""+((Vector)affectedGrp.elementAt(k)).elementAt(2);
                        modifiedCompVec.remove(comp_no);
                        modifiedCompVec.add(replaced_by);
                    }
                }
            }
            /*if(change_type.equals("REPLACE")){
                if(ind_db==-1){
                    return false;
                }else{
                    int qty_db=Integer.parseInt(""+qtyVecInDb.elementAt(ind_db));
                    if(qty_db<qty){
                        return false;
                    }

                    else{
                        String replaced_by=""+((Vector)affectedGrp.elementAt(k)).elementAt(2);
                        int ind1=compNoVec.indexOf(replaced_by);
                        if(ind1==-1){
                            return false;
                        }
                        if(!compNoVec.elementAt(ind1).equals(replaced_by)){
                             return false;
                        }
                        if(qty_db>qty){
                           int qty2=Integer.parseInt(""+qtyVec.elementAt(ind1));
                           if(qty2==qty){
                                if(ind==-1){
                                    return false;
                                }
                                int qty3=Integer.parseInt(""+qtyVec.elementAt(ind));
                                if(qty3!=(qty_db-qty)){
                                    return false;
                                }
                            }
                            else{
                                return false;
                            }
                            modifiedCompVec.add(replaced_by);
                        }
                        if(qty_db==qty){
                            modifiedCompVec.remove(comp_no);
                            modifiedCompVec.add(replaced_by);
                        }
                    }
                }
            }*/

        }
        if(modifiedCompVec.size()!=compNoVec.size()){
            return false;
        }
        for(int i=0;i<compNoVec.size();i++){
            if(!modifiedCompVec.contains(compNoVec.elementAt(i))){
                return false;
            }
        }
        return isValidChange;
    }
    public String getGroupECNStatus(String grp_no,Vector modelVec,TreeMap masterMap, Connection conn) throws Exception {
        String status="";
        String model_no=""+modelVec.elementAt(0);
        model_no=model_no.replace('[', ' ').trim();
        model_no=model_no.replace(']', ' ').trim();
        Vector affectedGrp=(Vector)masterMap.get(model_no+grp_no);
        //System.out.println("affectedGrp :"+affectedGrp);
        String ecn_idStr=""+((Vector)affectedGrp.elementAt(2)).elementAt(4);
        int ecn_id=Integer.parseInt(ecn_idStr);
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select STATUS from ecn_groups(NOLOCK) where ECN_ID="+ecn_id+" and GROUP_NO='"+grp_no+"'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            status = rs.getString(1);
        }
        return status;
    }

    public java.sql.Date  getECNEffectiveDate(String model,int ecn_no,int start_esn, Connection conn) throws Exception {
        java.sql.Date eff_date=null;

        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select EFF_DATE from ecn_master(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='"+model+"' and START_ESN="+start_esn;
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            eff_date = rs.getDate(1);
        }
        return eff_date;
    }
    public String getECNGrpNoType(int ecn_no,String models, String grp_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select NEW_GROUP_NO,TYPE from temp_model_changes(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='" + models+"' and OLD_GROUP_NO='" + grp_no+"'";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String ecn_grp_no_type = "";
        if (rs.next()) {
            ecn_grp_no_type = rs.getString(1)+"@@"+rs.getString(2);
        }
        return ecn_grp_no_type;
    }
    public String checkECNGrpNo(int ecn_no,String grp_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select NEW_GROUP_NO from temp_model_changes(NOLOCK) where NEW_GROUP_NO='" + grp_no+"' and ECN_NO="+ecn_no+" and STATUS!='CLOSE'";
        if(ecn_no==-1){
            query = "select NEW_GROUP_NO from temp_model_changes(NOLOCK)";
        }

        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String ecn_grp_no = "";
        if (rs.next()) {
            ecn_grp_no = rs.getString(1);
        }
        return ecn_grp_no;
    }
    public String getAllECNGrpNo(Vector createdGrpNoVec,Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;

        //getting the parent id of the component.
        String query = "select NEW_GROUP_NO from temp_model_changes(NOLOCK)";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String ecn_grp_no = "";
        while (rs.next()) {
            ecn_grp_no = rs.getString(1);
            createdGrpNoVec.add(ecn_grp_no);
        }
        return ecn_grp_no;
    }
    public String getECNGrpStatus(int ecn_no,String models,String grp_no, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "select STATUS from temp_model_changes(NOLOCK) where ECN_NO="+ecn_no+" and MODEL_NO='" + models+"' and OLD_GROUP_NO='" + grp_no+"'";
        //stmt = conn.createStatement();
       // rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String status = "";
        if (rs.next()) {
            status = rs.getString(1);
        }
        return status;
    }

}