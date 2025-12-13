/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import viewEcat.comEcat.Get_groupInfo;
import viewEcat.comEcat.GroupBom;
import viewEcat.comEcat.PageTemplate;

public class CreatePDFDAO {

    Font headerFont;
    Font footerfont1, groupDesFont, partHeaderFont, partValueFont, headerGroupIndex, groupHead, groupIndexVal, engineSeriesFont, engineModelFont;
    BaseFont bf, arialNarrow, headerBase, partBaseFont, partBaseFont_1, engineSeriesBase;
    int printIndex = 0;
    int partPageCount = 0;
    String engineSeries = null;
    String engineModel = null;
    String groupMap=null;

    public int createGroupPDF(Connection conn, PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String group, java.sql.Date buckleDate, String group_imagesPrintURL, String imagesURL, String pdfFileNameS, String headerEscort, String engineSeries, String engineModel,String ecatPATH) throws DocumentException, IOException {
        int forward_main = 0;
        this.engineSeries = engineSeries;
        this.engineModel = engineModel;
        bf = BaseFont.createFont("C:/WINDOWS/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        headerBase = BaseFont.createFont("C:/WINDOWS/Fonts/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        arialNarrow = BaseFont.createFont("C:/WINDOWS/Fonts/ARIALN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        engineSeriesBase = BaseFont.createFont("C:/WINDOWS/Fonts/REVUEN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        footerfont1 = new Font(bf, 5.50f, Font.NORMAL);
        groupDesFont = new Font(headerBase, 6.50f, Font.NORMAL);
        headerFont = new Font(headerBase,6.50f, Font.NORMAL);
        engineModelFont = new Font(headerBase, 12f, Font.NORMAL);
        engineSeriesFont = new Font(engineSeriesBase, 5.5f, Font.BOLD);
        partValueFont = new Font(FontFactory.getFont(FontFactory.HELVETICA, 9f));

        Document document = new Document(PageSize.A4);

        String  pdfFileNameS1 = ecatPATH + "dealer/ecat_print/group_pdf/"+group+"_1.pdf";
        //String model = null;
        try {

            FileOutputStream FOP = new FileOutputStream(pdfFileNameS1);
            PdfWriter pd = PdfWriter.getInstance(document, FOP);
            pd.setPageEmpty(false);
            document.open();
            //document.setPageSize(PageSize.A4);
            forward_main = createGroupPDFPage(document, conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate, group_imagesPrintURL, imagesURL, headerEscort);
            document.close();
            FOP.flush();
            FOP.close();
            pd.close();

            Groupstamp(pdfFileNameS1, pdfFileNameS,groupMap);

            File f=new File(pdfFileNameS1);
            if(f.exists())
                f.delete();

        } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
        return forward_main;

    }

    public int createModelPDF(String modelNo, Connection conn, String imageURL, String headerEscort, String ecatPATH, String engineSeries, String engineModel) throws DocumentException, IOException {
        int forward_main = 0;

        this.engineSeries = engineSeries;
        this.engineModel = engineModel;

        bf = BaseFont.createFont("C:/WINDOWS/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        headerBase = BaseFont.createFont("C:/WINDOWS/Fonts/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        arialNarrow = BaseFont.createFont("C:/WINDOWS/Fonts/ARIALN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        engineSeriesBase = BaseFont.createFont("C:/WINDOWS/Fonts/REVUEN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //partBaseFont = BaseFont.createFont(imageURL+"Fonts/Helvetica CE Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //partBaseFont_1 = BaseFont.createFont(imageURL+"Fonts/Helvetica CE Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //partHeaderFont = new Font(headerBase, 10f, Font.NORMAL);
        partHeaderFont = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10f));
        partValueFont = new Font(FontFactory.getFont(FontFactory.HELVETICA, 9f));
        footerfont1 = new Font(bf, 8.0f, Font.NORMAL);
        groupDesFont = new Font(headerBase, 8.50f, Font.NORMAL);
        headerFont = new Font(headerBase, 9.96f, Font.NORMAL);
        headerGroupIndex = new Font(headerBase, 13f, Font.NORMAL);
        engineSeriesFont = new Font(engineSeriesBase,10.5f, Font.BOLD);
        groupHead = new Font(headerBase, 10f, Font.NORMAL);
        groupIndexVal = new Font(bf, 9f, Font.NORMAL);
        engineModelFont = new Font(headerBase, 12f, Font.NORMAL);

        //Document document = new Document(PageSize.A4.rotate(), 50f, 50f, 25, 10f);//25f, 20, 25, 10f
        Document document = new Document(PageSize.A4);

        String pdfFileNameS = null;
        try {
            //document.setPageCount(2);



            //Creating ModelContent start////////
            pdfFileNameS = ecatPATH + "dealer/ecat_print/model_pdf";

            File f = new File(pdfFileNameS);
            if (!f.exists()) {
                f.mkdirs();
            }

            // pdfFileNameS = pdfFileNameS + "/" + model + ".pdf";


            FileOutputStream FOP = new FileOutputStream(pdfFileNameS + "/Content.pdf");
            PdfWriter pd = PdfWriter.getInstance(document, FOP);
            pd.setPageEmpty(false);
            document.open();
            //document.setPageSize(PageSize.A4);
            ArrayList<String> groupList = createModelPDFPageContent(document, conn, imageURL, modelNo, headerEscort,ecatPATH);
            document.close();
            FOP.flush();
            FOP.close();
            pd.close();

            //Creating ModelContent end////////

            //Creating partIndex

            document = new Document(PageSize.A4);

            FOP = new FileOutputStream(pdfFileNameS + "/PartIndex.pdf");
            pd = PdfWriter.getInstance(document, FOP);
            pd.setPageEmpty(false);
            document.open();
            //document.setPageSize(PageSize.A4);
            forward_main = createModelPDFPagePartIndex(document, conn, imageURL, modelNo, headerEscort);
            document.close();
            FOP.flush();
            FOP.close();
            pd.close();

            //Creating partIndex end

            List<InputStream> pdfs = new ArrayList<InputStream>();
            pdfs.add(new FileInputStream(pdfFileNameS + "/StaticPage1.pdf"));
            pdfs.add(new FileInputStream(pdfFileNameS + "/Content.pdf"));

            for (String groupTemp : groupList) {
                f = new File(ecatPATH + "dealer/ecat_print/group_pdf" + groupTemp + ".pdf");
                if (f.exists()) {
                    pdfs.add(new FileInputStream(f));
                }
            }
            pdfs.add(new FileInputStream(pdfFileNameS + "/PartIndex.pdf"));

            String output = pdfFileNameS + "/" + modelNo + ".pdf";
            mergPdfsWithPdfCopy(pdfs, output);

            forward_main = 1;


        } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
        return forward_main;
    }

    public ArrayList<String> createModelPDFPageContent(Document document, Connection conn, String imageURL, String model, String headerEscort,String ecatPATH) throws BadElementException, MalformedURLException, IOException {
        //int rtn = 0;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        String tempgroupMap = null;
        String groupNo = null;
        Map<Integer, ArrayList<String>> group_Map = null;
        Map<Integer, String> groupFileMap = null;
        ArrayList<String> innerList = null;
        ArrayList<String> groupList = null;

        try {

            Image headerImage = Image.getInstance(headerEscort);

            group_Map = new TreeMap();
            groupFileMap = new TreeMap();
            groupList = new ArrayList();

            PdfPTable topHeader = null;
            PdfPCell cell = new PdfPCell();

            int map = 0;
            int a=1;
            int b=0;
            Phrase p;

            float wF1[] = new float[4];

            wF1[0] = 30f;
            wF1[1] = 300f;//370f;
            wF1[2] = 45f;
            wF1[3] = 45f;

            //main table.............
            PdfPTable tb = new PdfPTable(1);
            tb.setWidthPercentage(98);
            tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            topHeader = new PdfPTable(1);
            topHeader.setWidthPercentage(100);

            topHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            p = new Phrase();
            p.add(new Chunk("PLATES INDEX", engineSeriesFont));
            cell = new PdfPCell(p);
            cell.setPaddingLeft(8f);
            cell.setBorder(0);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            topHeader.addCell(cell);

            setHeaderRowTable(topHeader,tb);
            tb.completeRow();

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(98);
            table.setWidths(wF1);

            int maxRowPerSheet =40;
            int counter = 0;


            //stmt = conn.createStatement();
           // rs = stmt.executeQuery("SELECT distinct MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM  CAT_MODEL_GROUP MG,CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND  MG.GROUP_NO=GKD.GRP_KIT_NO");
            String query = ("SELECT distinct MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM  CAT_MODEL_GROUP(NOLOCK) MG,CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + model + "' AND  MG.GROUP_NO=GKD.GRP_KIT_NO");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tempgroupMap = rs.getString(2);
                groupNo = rs.getString(1);
                innerList = new ArrayList();

                innerList.add(groupNo);
                innerList.add(rs.getString(3));

                groupFileMap.put(new Integer(tempgroupMap.substring(7).trim()), groupNo);
                group_Map.put(new Integer(tempgroupMap.substring(7).trim()), innerList);
            }
            rs.close();

            int m=createCombinePdf(ecatPATH,groupFileMap);
            for (Map.Entry<Integer, ArrayList<String>> entryMap : group_Map.entrySet()) {


                if (counter == 0 || maxRowPerSheet == 0) {
                    if (maxRowPerSheet == 0) {
                        maxRowPerSheet =40;

                        cell = new PdfPCell(table);
                        cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT | PdfPCell.LEFT);
                        cell.setBorderWidthBottom(.4f);
                        cell.setBorderWidthRight(.4f);
                        cell.setBorderWidthLeft(.4f);
                        cell.setBorderWidthTop(.4f);
                        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                        tb.addCell(cell);
                        tb.completeRow();
                        document.add(tb);
                        document.newPage();

                        tb = new PdfPTable(1);
                        tb.setWidthPercentage(98);
                        table = new PdfPTable(4);
                        table.setWidthPercentage(98);
                        table.setWidths(wF1);
                        setHeaderRowTable(topHeader, tb);

                    }
                    setHeading1(table, new String[]{"Sr. No.", "Plate Name","Plate No.","Page No."});
                    maxRowPerSheet--;
                    counter++;
                }
                if (maxRowPerSheet == 0) {

                    maxRowPerSheet =40;

                    cell = new PdfPCell(table);
                    cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT | PdfPCell.LEFT);
                    cell.setBorderWidthBottom(.4f);
                    cell.setBorderWidthRight(.4f);
                    cell.setBorderWidthLeft(.4f);
                    cell.setBorderWidthTop(.4f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                    tb.addCell(cell);
                    tb.completeRow();

                    document.add(tb);
                    document.newPage();
                    tb = new PdfPTable(1);
                    tb.setWidthPercentage(98);
                    table = new PdfPTable(4);
                    table.setWidthPercentage(98);
                    table.setWidths(wF1);

                    setHeaderRowTable(topHeader, tb);
                    setHeading1(table, new String[]{"Sr. No.", "Plate Name","Plate No.","Page No."});
                    maxRowPerSheet--;

                }


                p = new Phrase();
                p.add(new Chunk("" + entryMap.getKey(), groupIndexVal));
                cell = new PdfPCell(p);// Sr No.
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                cell.setBorderWidthBottom(0f);
                cell.setBorderWidthTop(0f);
                cell.setBorderWidthRight(0.4f);
                //cell.setPadding(10f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setFixedHeight(18);
                table.addCell(cell);

                map = entryMap.getKey();

                innerList = entryMap.getValue();

                for (int k = 0; k < innerList.size(); k += 2) {

                    groupList.add(innerList.get(k));

                    p = new Phrase(); // Plate Name.
                    p.add(new Chunk(innerList.get(k + 1).toUpperCase(), groupIndexVal));
                    cell = new PdfPCell(p);
                    cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                    cell.setBorderWidthBottom(0f);
                    cell.setBorderWidthTop(0f);
                   // cell.setBorderWidthRight(0.4f);
                    cell.setPaddingLeft(7f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cell.setFixedHeight(18);
                    table.addCell(cell);

                   //Start of plate No.
                    String roman=EAMG.Other.Action.NumberToRoman.getRoman(entryMap.getKey());
                    p = new Phrase();
                    p.add(new Chunk(""+roman, groupIndexVal));
                    cell = new PdfPCell(p);// plate No.
                    cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                    cell.setBorderWidthBottom(0f);
                    cell.setBorderWidthTop(0f);
                    //cell.setBorderWidthRight(0.4f);
                    //cell.setPadding(7f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cell.setFixedHeight(18);
                    table.addCell(cell);

                    //end of Plate No.


                    p = new Phrase(); // Page No.

                    int noOfPages=getNumberOfPagesFromPdf(entryMap.getKey(),groupFileMap,ecatPATH);
                    b=a+noOfPages-1;
                    p.add(new Chunk(""+a +"-"+b, groupIndexVal));
                    a=b+1;
                    cell = new PdfPCell(p);
                    cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                    cell.setBorderWidthBottom(0f);
                    cell.setBorderWidthTop(0f);
                    //cell.setBorderWidthRight(0.4f);
                    cell.setPaddingLeft(7f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cell.setFixedHeight(18);
                    table.addCell(cell);
                    table.completeRow();
                    maxRowPerSheet--;

                }
                //document.newPage();
            }

            if (maxRowPerSheet > 0) {

                for (int i = 0; i <4; i++) {
                    p = new Phrase();        //  DESCRIPTION
                    p.add(new Chunk(" ", groupIndexVal));
                    cell = new PdfPCell(p);
                    //cell.setColspan(5);
                    cell.setBorder(PdfPCell.RIGHT);
                    cell.setBorderWidthBottom(0f);
                    cell.setBorderWidthRight(0.4f);
                    cell.setPaddingLeft(7f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cell.setFixedHeight(18f * maxRowPerSheet);
                    //cell.setRowspan(maxRowPerSheet);
                    table.addCell(cell);
                }
                table.completeRow();


                cell = new PdfPCell(table);
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT | PdfPCell.LEFT);
                cell.setBorderWidthBottom(.4f);
                cell.setBorderWidthRight(.4f);
                cell.setBorderWidthLeft(.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                //cell.setFixedHeight(18f*maxRowPerSheet);
                tb.addCell(cell);
                tb.completeRow();
                document.add(tb);
            }
            //document.newPage();
            //END MIAN TABLE

            //rtn = 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.gc();

        }
        return groupList;
    }

    public int createModelPDFPagePartIndex(Document document, Connection conn, String imageURL, String model, String headerEscort) throws BadElementException, MalformedURLException, IOException {
        int rtn = 0;
        ///////////////////////////// CREATE SESSION /////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

//        LinkedHashMap<String, ArrayList<String>> GrouppartDetailsMap = null;
//        LinkedHashMap<String, String> GroupMap = null;
        ArrayList<String> partDetails = null;
       // Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        PdfPTable outertable = null, innerTable = null, Outermaintable = null;
        PdfPCell cells = null;
        Phrase p = null;
        int MaxRowPerSheetI = 0;

        try {

            partDetails = new ArrayList<String>();
            //stmt = conn.createStatement();
            //System.out.println("model="+model);
            sql = "select distinct MG.MAP_NAME,gkb.COMPONENT,gkb.COMP_TYPE,gkb.SEQUENCE,gkb.qty from CAT_MODEL_GROUP(NOLOCK) MG,CAT_GROUP_KIT_BOM(NOLOCK) gkb where MG.GROUP_NO=gkb.GRP_KIT_NO AND COMPONENT not like 'TBA%' and MG.MODEL_NO = '" + model + "'  order by COMPONENT";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            //rs = stmt.executeQuery(sql);

            while (rs.next()) {

                partDetails.add(rs.getString(1));//fig name
                partDetails.add(rs.getString(2));//Part no.
                partDetails.add(rs.getString(3));//compType
                partDetails.add(rs.getString(4));//page no.
                partDetails.add(rs.getString(5));//qty

            }
            rs.close();


            Outermaintable = new PdfPTable(1);
            Outermaintable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Outermaintable.setWidthPercentage(98);

            CreatePDFDAO spm=new CreatePDFDAO();
            //added for first page ....................
            spm.insertPartFirstPage(document, imageURL);

            MaxRowPerSheetI = 50;

            outertable = new PdfPTable(new float[]{100f,4f,100f});  //table1 start
            outertable.setWidthPercentage(100);
            outertable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            int counter =0;
            int colIndex = 0;

            innerTable = new PdfPTable(new float[]{50f,35f,30f,20f});
            innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            innerTable.setWidthPercentage(100);


            for (int l = 0; l < partDetails.size(); l +=5) {

                if (counter == 0 || MaxRowPerSheetI == 0) {

                    if (MaxRowPerSheetI == 0) {
                        MaxRowPerSheetI = 50;

                        cells = new PdfPCell(innerTable);
                        cells.setBorder(PdfPCell.RIGHT);
                        cells.setBorderWidthRight(0.4f);
                        cells.setBorderWidthTop(0.4f);
                        cells.setBorderWidthBottom(0.4f);
                        outertable.addCell(cells);
                        cells = new PdfPCell(new Phrase(""));
                        cells.setBorder(PdfPCell.RIGHT);
                        cells.setPaddingLeft(1f);
                        cells.setPaddingRight(1f);
                        cells.setBorderWidthTop(0f);
                        outertable.addCell(cells);
                        colIndex++;

                        innerTable = new PdfPTable(new float[]{50f,35f,30f,20f});
                        innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                        innerTable.setWidthPercentage(100);

                        if (colIndex == 3) {

                            colIndex = 0;

                            outertable.completeRow();
                            cells = new PdfPCell(outertable);
                            cells.setFixedHeight(696.5f);
                            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cells.setVerticalAlignment(Element.ALIGN_TOP);
                            cells.setBorder(PdfPCell.RIGHT  | PdfPCell.LEFT);
                            cells.setBorderWidthLeft(.4f);
                            cells.setBorderWidthRight(.4f);
                            Outermaintable.addCell(cells);
                            document.add(Outermaintable);
                            document.newPage();

                            Outermaintable = new PdfPTable(1);
                            Outermaintable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                            Outermaintable.setWidthPercentage(98);

                            outertable = new PdfPTable(new float[]{100f,4f,100f}); //table1 start.........
                            outertable.setWidthPercentage(100);

                            //setHeaderRowTable(topHeader, Outermaintable);
                        }
                    }

                    p = new Phrase();
                    p.add(new Chunk("PART No.", partHeaderFont));//PART NO.
                    cells = new PdfPCell(p);
                    cells.setNoWrap(true);
                    cells.setPaddingLeft(4f);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("PAGE No.", partHeaderFont));//PAGE NO.
                    cells = new PdfPCell(p);
                    cells.setNoWrap(true);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("Fig No.", partHeaderFont));//FIG NO.
                    cells = new PdfPCell(p);
                    cells.setNoWrap(true);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("Qty", partHeaderFont));//Qty
                    cells = new PdfPCell(p);
                    cells.setNoWrap(true);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);

                    innerTable.addCell(cells);
                    counter++;
                }


                if (MaxRowPerSheetI == 0) {

                    MaxRowPerSheetI = 50;

                    cells = new PdfPCell(innerTable);
                    cells.setBorder(PdfPCell.RIGHT);
                    cells.setBorderWidthRight(0.4f);
                    cells.setBorderWidthBottom(0.4f);
                    outertable.addCell(cells);

                    cells = new PdfPCell(new Phrase(""));
                    cells.setBorder(PdfPCell.RIGHT);
                    cells.setPaddingLeft(1f);
                    cells.setPaddingRight(1f);
                    cells.setBorderWidthTop(0f);
                    outertable.addCell(cells);

                    colIndex++;

                    innerTable = new PdfPTable(new float[]{50f,35f,30f,20f});
                    innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                    innerTable.setWidthPercentage(100);

                    if (colIndex == 3) {

                        colIndex = 0;

                        outertable.completeRow();
                        cells = new PdfPCell(outertable);
                        cells.setFixedHeight(696.5f);
                        cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cells.setVerticalAlignment(Element.ALIGN_TOP);
                        cells.setBorder(PdfPCell.RIGHT  | PdfPCell.LEFT);
                        cells.setBorderWidthLeft(.4f);
                        cells.setBorderWidthRight(.4f);
                        Outermaintable.addCell(cells);
                        document.add(Outermaintable);
                        document.newPage();

                        Outermaintable = new PdfPTable(1);
                        Outermaintable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                        Outermaintable.setWidthPercentage(98);
                        outertable = new PdfPTable(new float[]{100f,4f,100f}); //table1 start
                        outertable.setWidthPercentage(100);


                    }

                    p = new Phrase();
                    p.add(new Chunk("PART No.", partHeaderFont));//PART NO.
                    cells = new PdfPCell(p);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setNoWrap(true);
                    cells.setPaddingLeft(4f);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("PAGE No.", partHeaderFont));//PAGE NO.
                    cells = new PdfPCell(p);
                    cells.setNoWrap(true);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("Fig No.", partHeaderFont));//Fig NO.
                    cells = new PdfPCell(p);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setNoWrap(true);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    p = new Phrase();
                    p.add(new Chunk("Qty", partHeaderFont));//Qty.
                    cells = new PdfPCell(p);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setNoWrap(true);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setBorderWidthRight(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);

                    // MaxRowPerSheetI--;
                }

                cells = new PdfPCell(new Phrase(partDetails.get(l + 1), partValueFont)); //component....
                cells.setBorder(0);
                cells.setFixedHeight(13f);
                cells.setPaddingLeft(4f);
                cells.setBorderWidthRight(0.4f);
                cells.setHorizontalAlignment(Element.ALIGN_LEFT);
                cells.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                innerTable.addCell(cells);

                cells = new PdfPCell(new Phrase(partDetails.get(l+3), partValueFont));
                cells.setBorder(0);
                cells.setFixedHeight(13f);
                cells.setNoWrap(true);
                cells.setBorderWidthRight(0.4f);
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                cells.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                innerTable.addCell(cells);

                cells = new PdfPCell(new Phrase(partDetails.get(l).substring(7), partValueFont));
                cells.setBorder(0);
                cells.setFixedHeight(13f);
                cells.setBorderWidthRight(0.4f);
                cells.setNoWrap(true);
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                cells.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                innerTable.addCell(cells);

                cells = new PdfPCell(new Phrase(partDetails.get(l + 4), partValueFont));
                cells.setBorder(0);
                cells.setFixedHeight(13f);
                cells.setBorderWidthRight(0.4f);
                cells.setNoWrap(true);
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                cells.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                innerTable.addCell(cells);

                MaxRowPerSheetI--;
            }


            if (MaxRowPerSheetI > 0) {


                cells = new PdfPCell(innerTable);
                cells.setBorder(PdfPCell.RIGHT);
                cells.setBorderWidthRight(0.4f);
                cells.setBorderWidthBottom(0.4f);
                cells.setBorderWidthTop(0.4f);
                outertable.addCell(cells);

                cells = new PdfPCell(new Phrase(""));
                cells.setBorder(PdfPCell.RIGHT);
                cells.setPaddingLeft(1f);
                cells.setPaddingRight(1f);
                cells.setBorderWidthTop(0f);
                outertable.addCell(cells);

                colIndex++;

                for (int c = 0; c < (3 - colIndex); c++) {
                    innerTable = new PdfPTable(new float[]{50f,35f,30f,20f});
                    innerTable.setWidthPercentage(100);

                    p = new Phrase();
                    p.add(new Chunk(" ", partHeaderFont));//TABLE NO.
                    cells = new PdfPCell(p);
                    cells.setColspan(3);
                    cells.setNoWrap(true);
                    cells.setBorder(PdfPCell.BOTTOM);
                    cells.setBorderWidthBottom(0.4f);
                    cells.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cells.setFixedHeight(25);
                    innerTable.addCell(cells);


                    cells = new PdfPCell(new Phrase("", partValueFont));
                    cells.setBorder(0);
                    cells.setFixedHeight(13f);
                    cells.setNoWrap(true);
                    cells.setColspan(3);
                    cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cells.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                    innerTable.addCell(cells);

                    cells = new PdfPCell(innerTable);
                    cells.setBorderWidthTop(0.4f);
                    outertable.addCell(cells);
                }
             }
                outertable.completeRow();

                cells = new PdfPCell(outertable);
                cells.setFixedHeight(696.5f);
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                cells.setVerticalAlignment(Element.ALIGN_TOP);
                cells.setBorder(PdfPCell.RIGHT |  PdfPCell.LEFT);
                cells.setBorderWidthLeft(.4f);
                cells.setBorderWidthRight(.4f);
                Outermaintable.addCell(cells);
                document.add(Outermaintable);

           // }

        } catch (Exception e) {
            // sb.append(e);
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            stmt = null;
            rs = null;
            sql = null;
            outertable = null;
            Outermaintable = null;
            innerTable = null;
            cells = null;
            imageURL = null;

        }
        return rtn;
    }

     public int createGroupPDFPage(Document document, Connection conn, PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String group, java.sql.Date buckleDate, String group_imagesPrintURL, String imagesURL, String headerEscort) throws BadElementException, MalformedURLException, IOException {
        int rtn = 0;
        ResultSet rs = null;
       // Statement stmt = null;
        PreparedStatement stmt = null;
        try {

            //System.out.println("group"+group);
            String groupImage = group_imagesPrintURL + group + ".jpg";

            Image grimage = Image.getInstance(new URL(groupImage));

            Image headerImage = Image.getInstance(headerEscort);
            int Checker = 1;

            PdfPTable topHeader = null;

            GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
            GroupBom ob2 = ob.getGroupBom();
            Get_groupInfo ob_get_grp_inf = new Get_groupInfo();

            String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
            int grp_arr_count = ob2.print_data_counter;

            String var_REF_NO = "";
            String var_DESCRIPTION = "";
            String var_SERVICEABLE = "";
            String var_REMARK = "";

            String var_HEADER_DESCRIPTION = "";
            String var_QTY = "";

            for (int i = 0; i < grp_arr_count; i++) {
                var_REF_NO = "";
                var_DESCRIPTION = "";
                var_SERVICEABLE = "";
                var_REMARK = "";
                var_HEADER_DESCRIPTION = "";


                for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                    grp_arr[i][aa] = ob2.print_data[i][aa];
                }

                String linkPart1 = grp_arr[i][0].replaceAll("&nbsp;", "");
                //stmt = conn.createStatement();

                //rs = stmt.executeQuery("SELECT distinct p.p1,p.p2,gkb.remarks,p.p4,p.p5,p.part_type FROM cat_part p,cat_group_kit_bom gkb WHERE gkb.component=p.part_no and p.part_no ='" + linkPart1 + "' ");
                String query = ("SELECT distinct p.p1,p.p2,gkb.remarks,p.p4,p.p5,p.part_type FROM cat_part(NOLOCK) p,cat_group_kit_bom(NOLOCK) gkb WHERE gkb.component=p.part_no and p.part_no ='" + linkPart1 + "' ");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();


                if (rs.next()) {
                    var_DESCRIPTION = rs.getString(1);
                    var_REF_NO = rs.getString(2);
                    var_REMARK = rs.getString(3);
                    var_SERVICEABLE = rs.getString(4);
                    var_HEADER_DESCRIPTION = rs.getString(5);

                }
                rs.close();


                if (var_DESCRIPTION == null) {
                    var_DESCRIPTION = "";
                }
                if (var_REMARK == null) {
                    var_REMARK = "";
                }

                grp_arr[i][9] = var_REF_NO;
                grp_arr[i][10] = var_DESCRIPTION.toUpperCase();
                grp_arr[i][11] = var_SERVICEABLE;
                grp_arr[i][12] = var_REMARK;
                grp_arr[i][13] = var_HEADER_DESCRIPTION;
            }

            for (int i = 0; i < grp_arr_count; i++) {
                for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                    if (grp_arr[i][aa] == null) {
                        grp_arr[i][aa] = "&nbsp;";
                    }
                }
            }
            String groupMap = "";

            //rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "'");
            String query = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                groupMap = rs.getString(1);
            }
            rs.close();

            String groupDesc = "";
            String groupRemark = "";
            //rs = stmt.executeQuery("SELECT P1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO = '" + group + "'");
            String query1 = ("SELECT P1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO = '" + group + "'");
            stmt = conn.prepareStatement(query1);
            rs = stmt.executeQuery();
            if (rs.next()) {
                groupDesc = rs.getString(1);
                groupRemark = rs.getString(2);
            }
            rs.close();

            this.groupMap=groupMap;
            //String part_array[] = new String[grp_arr.length];
            // part_counter = 0;
            //String linkPart1 = "";

            //CREATION OF  GROUP PDF START
            document.addTitle(group);

            PdfPCell cell = new PdfPCell();
            PdfPCell outercell = new PdfPCell();
            PdfPCell outercellImage = new PdfPCell();
            PdfPCell outercellDescription = new PdfPCell();

            Phrase p;

            float wF1[] = new float[4];


            wF1[0] =.3f;
            wF1[1] =.6f;
            wF1[2] =3f;
            wF1[3] =.4f;

            //Main Table
            PdfPTable tb = new PdfPTable(1);
            tb.setWidthPercentage(98);
            tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            topHeader = new PdfPTable(new float[]{2f,.2f});
            topHeader.setWidthPercentage(100);
            p = new Phrase();
            p.add(new Chunk(""+group+" \nDIFFRENTIAL UNIT,FARE HOUSING AND SPEED DRIVE ",headerFont));
            cell = new PdfPCell(p);
            cell.setPaddingLeft(0f);
            cell.setPaddingBottom(5f);
            cell.setPaddingTop(5f);
            cell.setBorderWidthLeft(0f);
            cell.setBorderWidthRight(0f);
            //cell.setFixedHeight(25);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            topHeader.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(0f);
            cell.setBorderWidthLeft(0f);
            cell.setBorderWidthRight(0f);
            cell.setPaddingTop(4f);
            cell.setPaddingBottom(4f);
            cell.setFixedHeight(.2f);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setImage(headerImage);
            topHeader.addCell(cell);


            cell = new PdfPCell(topHeader);
            cell.setPadding(0f);
            cell.setBorderWidthLeft(0f);
            cell.setBorderWidthRight(0f);
            //cell.setFixedHeight(25);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setPaddingBottom(5f);
            tb.addCell(cell);


            PdfPTable imagetable = new PdfPTable(1);
            imagetable.setWidths(new float[]{100f});
            outercell = new PdfPCell();
            outercell.setBorder(0);
            outercell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            outercell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            outercell.setImage(grimage);
            outercell.setFixedHeight(230f);
            imagetable.addCell(outercell);


            outercellImage = new PdfPCell(imagetable);
            outercellImage.setBorder(0);
            outercellImage.setPaddingTop(15f);
            outercellImage.setPaddingBottom(15f);
            outercellImage.setPaddingRight(15f);
            outercellImage.setPaddingLeft(15f);
            outercellImage.setBorderWidthLeft(.4f);
            outercellImage.setBorderWidthRight(.4f);
            outercellImage.setBorderWidthBottom(.4f);
            //outercellImage.setBorderWidthTop(.4f);
            outercellImage.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            outercellImage.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            outercellImage.setFixedHeight(355f);//480
            tb.addCell(outercellImage);
            tb.completeRow();

            //for break the table
            PdfPCell cellEmpty = new PdfPCell();
            cellEmpty.setPaddingBottom(3.5f);
            cellEmpty.setBorderWidthLeft(0f);
            cellEmpty.setBorderWidthRight(0f);
            cellEmpty.setBorderWidthTop(0f);
            cellEmpty.setBorderWidthBottom(0f);
            tb.addCell(cellEmpty);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(98);
            table.setWidths(wF1);

            int maxRowPerSheet =35;
            int counter = 0;

            for (int i = 0; i < grp_arr_count; i++)
            {
                if (counter == 0 || maxRowPerSheet == 0)
                {

                    if (maxRowPerSheet == 0)
                    {

                        outercellDescription = new PdfPCell(table);
                        outercellDescription.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                        outercellDescription.setBorderWidthBottom(.4f);
                        outercellDescription.setBorderWidthRight(.4f);
                        outercellDescription.setBorderWidthLeft(.4f);
                        outercellDescription.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        outercellDescription.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                        tb.addCell(outercellDescription);
                        tb.completeRow();

                        document.add(tb);
                        document.newPage();

                        tb = new PdfPTable(1);
                        tb.setWidthPercentage(98);
                        tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                        setHeaderRowTable(topHeader, tb);

                        outercell = new PdfPCell(imagetable);
                        outercell.setBorder(0);
                        outercell.setPaddingTop(15f);
                        outercell.setPaddingBottom(15f);
                        outercell.setPaddingRight(15f);
                        outercell.setPaddingLeft(15f);
                        outercell.setBorderWidthLeft(.4f);
                        outercell.setBorderWidthRight(.4f);
                        outercell.setBorderWidthBottom(.4f);
                        outercell.setBorderWidthTop(.4f);
                        outercell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        outercell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                        outercell.setFixedHeight(355f);//480
                        tb.addCell(outercell);
                        tb.completeRow();
                        document.add(tb);
                        //document.newPage();

                        tb = new PdfPTable(1);
                        tb.setWidthPercentage(98);
                        tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                        tb.addCell(cellEmpty);
                        table = new PdfPTable(4);
                        table.setWidthPercentage(98);
                        table.setWidths(wF1);
                        //setHeaderRowTable(topHeader, tb);
                        maxRowPerSheet =35;
                    }
                    setHeading(table, new String[]{"Fig No.", "Part No.", "Description", "Qty."});
                    maxRowPerSheet--;
                    counter++;
                }
                if (maxRowPerSheet == 0) {

                   cell = new PdfPCell(table);
                   cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT );
                   cell.setBorderWidthBottom(.4f);
                   cell.setBorderWidthRight(.4f);
                   cell.setBorderWidthLeft(.4f);
                   cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                   cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                    tb.addCell(cell);
                    tb.completeRow();

                    document.add(tb);
                    document.newPage();

                    tb = new PdfPTable(1);
                    tb.setWidthPercentage(98);
                    tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                    setHeaderRowTable(topHeader, tb);

                    outercell = new PdfPCell(imagetable);
                    outercell.setBorder(0);
                    outercell.setPaddingTop(15f);
                    outercell.setPaddingBottom(15f);
                    outercell.setPaddingRight(15f);
                    outercell.setPaddingLeft(15f);
                    outercell.setBorderWidthLeft(.4f);
                    outercell.setBorderWidthRight(.4f);
                    outercell.setBorderWidthBottom(.4f);
                    outercell.setBorderWidthTop(.4f);
                    outercell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    outercell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                    outercell.setFixedHeight(355f);//480
                    tb.addCell(outercell);


                    tb.completeRow();
                    document.add(tb);
                    //document.newPage();

                    tb = new PdfPTable(1);
                    tb.setWidthPercentage(98);
                    tb.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                    tb.addCell(cellEmpty);
                    table = new PdfPTable(4);
                    table.setWidthPercentage(98);
                    table.setWidths(wF1);

                   // setHeaderRowTable(topHeader, tb);
                    setHeading(table, new String[]{"Fig No.", "Part No.", "Description", "Qty."});
                    maxRowPerSheet =35;
                }


                p = new Phrase();

                 /*if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA")) {
                    p.add(new Chunk(grp_arr[i][1], footerfont1));
                   }else {
                    p.add(new Chunk(String.valueOf(Checker++), footerfont1));
                     Checker++;
                }*/
                p.add(new Chunk(String.valueOf(Checker++), footerfont1));
                cell = new PdfPCell(p);// FIG NO.
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT );
                cell.setBorderWidthBottom(0f);
                cell.setBorderWidthRight(0.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setFixedHeight(10);
                cell.setPaddingLeft(4f);
                //cell.setPaddingTop(.1f);
                //cell.setPaddingBottom(.1f);
                table.addCell(cell);

                p = new Phrase(); // PART NO.
                if (grp_arr[i][0].startsWith("TBA")) {
                    p.add(new Chunk(PageTemplate.tbaPart, footerfont1));
                } else {
                    p.add(new Chunk(grp_arr[i][0].replaceAll("_", "&nbsp;"), footerfont1));
                }
                cell = new PdfPCell(p);
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                cell.setBorderWidthBottom(0f);
                cell.setBorderWidthRight(0.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setFixedHeight(10);
                cell.setPaddingLeft(4f);
                //cell.setPaddingTop(.1f);
                //cell.setPaddingBottom(.1f);
                table.addCell(cell);

                p = new Phrase();        //  DESCRIPTION
                p.add(new Chunk(grp_arr[i][10].toUpperCase(), footerfont1));
                cell = new PdfPCell(p);
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                cell.setBorderWidthBottom(0f);
                cell.setBorderWidthRight(0.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setFixedHeight(10);
                cell.setPaddingLeft(4f);
                //cell.setPaddingTop(.1f);
                //cell.setPaddingBottom(.1f);
                table.addCell(cell);


                p = new Phrase(); // QTY
                if (grp_arr[i][7].equals("YES")) {
                    p.add(new Chunk("AR",footerfont1));
                } else {
                    p.add(new Chunk(grp_arr[i][3].toUpperCase(), footerfont1));
                }
                cell = new PdfPCell(p);
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT );
                cell.setBorderWidthBottom(0f);
                cell.setBorderWidthRight(0.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                cell.setFixedHeight(10);
                //cell.setPaddingTop(.1f);
                //cell.setPaddingBottom(.1f);
                table.addCell(cell);

                table.completeRow();

                maxRowPerSheet--;
                //document.newPage();

            }
            if (maxRowPerSheet > 0) {

                for (int i = 0; i < 4; i++) {
                    p = new Phrase();        //  DESCRIPTION
                    p.add(new Chunk(" ", footerfont1));
                    cell = new PdfPCell(p);
                    //cell.setColspan(5);
                    cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
                    cell.setBorderWidthBottom(0f);
                    cell.setBorderWidthRight(0.4f);
                    //cell.setPaddingLeft(7f);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                    cell.setFixedHeight(10f * maxRowPerSheet);
                    cell.setPaddingLeft(4f);
                    //cell.setPaddingTop(.1f);
                    //cell.setPaddingBottom(.1f);
                    //cell.setRowspan(maxRowPerSheet);
                    table.addCell(cell);
                }
                table.completeRow();
 }

                cell = new PdfPCell(table);
                cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT | PdfPCell.LEFT);
                cell.setBorderWidthBottom(0.4f);
                cell.setBorderWidthRight(0.4f);
                cell.setBorderWidthLeft(0.4f);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
                //cell.setFixedHeight(18f*maxRowPerSheet);
                tb.addCell(cell);
                tb.completeRow();
                document.add(tb);


            //document.newPage();
            //END MIAN TABLE

            rtn = 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.gc();

        }
        return rtn;
    }

    public void setHeaderRowTable(PdfPTable topHeader, PdfPTable tb) {

        PdfPCell cell = new PdfPCell(topHeader);
        cell.setBorder(PdfPCell.RIGHT | PdfPCell.LEFT | PdfPCell.BOTTOM | PdfPCell.TOP);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthBottom(0);
        //cell.setFixedHeight(25);
        cell.setPaddingBottom(5f);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        tb.addCell(cell);


        /*cell = new PdfPCell(headerTable);
        cell.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM | PdfPCell.LEFT);
        cell.setBorderWidthLeft(1.4f);
        cell.setBorderWidthRight(1.5f);
        cell.setBorderWidthBottom(0.5f);
        cell.setFixedHeight(25);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        tb.addCell(cell);*/
        tb.setHeaderRows(1);

    }

    public void setHeading(PdfPTable table, String[] heading) {
        Phrase p;
        PdfPCell cell;

        for (String temp : heading) {
            p = new Phrase();
            p.add(new Chunk(temp, groupDesFont));//TABLE NO.
            cell = new PdfPCell(p);
            cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT | PdfPCell.TOP);
            cell.setBorderWidthBottom(.4f);
            cell.setBorderWidthRight(0.4f);
            cell.setBorderWidthTop(0.4f);
            if(temp.equals("Fig No."))
            {
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setPaddingLeft(4f);
            }
            else if(temp.equals("Part No."))
            {
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setPaddingLeft(4f);
            }
            else if(temp.equals("Description"))
            {
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setPaddingLeft(4f);
            }
            else if(temp.equals("Qty."))
            {
              cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            }

            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setFixedHeight(20);
            table.addCell(cell);
        }
        table.completeRow();
    }

    public void setHeading1(PdfPTable table, String[] heading) {
        Phrase p;
        PdfPCell cell;

        for (String temp : heading) {
            p = new Phrase();
            p.add(new Chunk(temp, groupHead));//TABLE NO.
            cell = new PdfPCell(p);
            cell.setBorder(PdfPCell.BOTTOM | PdfPCell.RIGHT);
            cell.setBorderWidthTop(0.4f);
            if(temp.equals("Plate Name"))
            {
             cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
             cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
             cell.setPaddingLeft(7f);
            }
            else
            {
             cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
             cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            }
            cell.setFixedHeight(20);
            table.addCell(cell);
        }
        table.completeRow();
    }

    public void mergPdfsWithPdfCopy(List<InputStream> pdf, String output) throws IOException, DocumentException {
        // using previous examples to create PDFs

        // step 1
        Document document = new Document();
        // step 2
        PdfSmartCopy copy = new PdfSmartCopy(document, new FileOutputStream(output));
        // step 3
        document.open();
        // step 4
        PdfReader reader;
        int n;
        // loop over the documents you want to concatenate
        for (InputStream is : pdf) {
            reader = new PdfReader(is);


            // loop over the pages in that document
            n = reader.getNumberOfPages();
            for (int page = 0; page < n;) {

                copy.addPage(copy.getImportedPage(reader, ++page));
            }
            copy.freeReader(reader);
        }
        // step 5
        document.close();

    }

    public void Groupstamp(String src, String dest, String groupMap)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        Phrase p = null;

        int map=0;
        try
        {
            //map=Integer.parseInt(groupMap.substring(6).trim())+2;
        }catch(Exception e){e.printStackTrace();}

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte canvas = null;
        for (int k = 1; k <= reader.getNumberOfPages(); ++k) {//reader.getNumberOfPages()
            canvas = stamper.getOverContent(k);
            canvas.setColorFill(BaseColor.BLACK);

            p = new Phrase();
            p.add(new Chunk(""+k, partValueFont));//modelNo
            ColumnText.showTextAligned(canvas, PdfContentByte.ALIGN_RIGHT, p, 550f, 30f, 0);
        }
        stamper.close();
    }

    public ArrayList<String> getEngineSeriesDetails(Connection conn, String model, String group) {
        //Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> data = new ArrayList();
        String sql = null;

        try {
            //stmt = conn.createStatement();

            if (group != null) {
                sql = "select mc.engine_series, mc.engine_model from cat_model_classification(NOLOCK) mc,cat_model_group(NOLOCK) mg where mc.model_no=mg.model_no  and mg.group_no='" + group + "'";
            } else {
                sql = "select engine_series,engine_model from cat_model_classification(NOLOCK) where model_no='" + model + "'";
            }
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
            //rs = stmt.executeQuery(sql);
            if (rs.next()) {
                data.add(rs.getString(1));
                data.add(rs.getString(2));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

     public void insertPartFirstPage(Document document,String imageURL)
     {

        PdfPTable innerTable;
        PdfPTable outertable, Outermaintable;
        Outermaintable = new PdfPTable(1);
        Outermaintable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        Outermaintable.setWidthPercentage(100);

        try {

            Font textFont;
            BaseFont textBase;
            textBase = BaseFont.createFont("C:/WINDOWS/Fonts/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //textBase= BaseFont.createFont("C:/WINDOWS/Fonts/REVUEN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //textBase = BaseFont.createFont("C:/WINDOWS/Fonts/ARIALN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            textFont = new Font(textBase,35f, Font.BOLD);
            outertable = new PdfPTable(new float[]{100f,4f,100f});
            outertable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            outertable.setWidthPercentage(100);

            innerTable = new PdfPTable(1);
            innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            innerTable.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell();
            cell.setFixedHeight(50);
            cell.setBorder(0);
            Image headerImage=Image.getInstance(new URL(imageURL + "piaggio-logo.jpg"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            cell.setImage(headerImage);
            innerTable.addCell(cell);

            Phrase p = new Phrase();
            p.add(new Chunk("PART  \nNUMBER \nINDEX",textFont));
            cell = new PdfPCell(p);
            cell.setBorder(0);
            cell.setPaddingTop(230);
            cell.setFixedHeight(660);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPaddingBottom(100);
            innerTable.addCell(cell);

            cell = new PdfPCell();
            cell.setFixedHeight(40);
            cell.setBorder(0);
            Image headerImage1=Image.getInstance(new URL(imageURL + "piaggio_mini.jpg"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
            cell.setImage(headerImage1);
            innerTable.addCell(cell);

            cell = new PdfPCell(innerTable);
            cell.setBorder(0);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            Outermaintable.addCell(cell);
            document.add(Outermaintable);
        } catch (Exception e) {
            e.printStackTrace();
            //   ps.println(e);
        } finally {

            innerTable = null;
            outertable = null;

        }

    }

     public int createCombinePdf(String ecatPATH,Map<Integer, String> listOfFile)
	         {
	           List<InputStream> pdfFiles = new ArrayList<InputStream>();
                   ArrayList<String> files=new ArrayList<String>();
	           try
	           {

                    for (Integer key : listOfFile.keySet())
                    {

                        files.add(listOfFile.get(key).toString());

                    }

	               for(String file : files)
	               {
	                       File f = new File(ecatPATH + "dealer/ecat_print/group_pdf/"+file+".pdf");
	                        if (f.exists())
	                        {
	                             pdfFiles.add(new FileInputStream(f));
	                        }
	               }
	            String output_1=ecatPATH + "dealer/ecat_print/model_pdf/groupContent_1.pdf";
	            String output=ecatPATH + "dealer/ecat_print/model_pdf/groupContent.pdf";

	            mergPdfsWithPdfCopy(pdfFiles, output_1);
                    Groupstamp(output_1, output,"satyaTestPdfFile");
                    File f=new File(output_1);
                     if(f.exists())
                     f.delete();
	        }catch(Exception e){e.printStackTrace();}

           return (1);
     }

     public int getNumberOfPagesFromPdf(int key,Map<Integer,String>mapList,String ecatPATH)
     {
       int noPages=0;
       try
       {
          String fileName=mapList.get(key).toString();
          File f = new File(ecatPATH + "dealer/ecat_print/group_pdf/"+fileName+".pdf");
          if(f.exists())
          {
            PdfReader document = new PdfReader(new FileInputStream(new File(f.toString())));
            noPages = document.getNumberOfPages();
          }

       }catch(Exception e){e.printStackTrace();}

       return noPages;
     }



}

