/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DateType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.EAMG.common.commonUtilMethods;
import com.common.MethodUtility;

import HibernateMapping.SpCurrentInventoryView;
import HibernateMapping.SpInventOtherDealer;
import HibernateMapping.SpInventOtherDealerDetails;
import HibernateMapping.SpInventOtherDealerDetailsPK;
import HibernateMapping.SpInventReorderLevel;
import HibernateMapping.SpInventReorderLevelPK;
import HibernateMapping.SpInventSaleDetails;
import HibernateMapping.SpInventSaleDetailsPK;
import HibernateMapping.SpInventSaleMaster;
import HibernateMapping.SpInventSreturn;
import HibernateMapping.SpInventSreturnDetails;
import HibernateMapping.SpInventSreturnDetailsPK;
import HibernateMapping.SpInventory;
import HibernateMapping.SpInventoryLedger;
import HibernateMapping.SpInventoryLedgerPK;
import HibernateMapping.SpInventoryLock;
import HibernateMapping.SpInventoryPK;
import HibernateMapping.SpOrderDetails;
import HibernateMapping.SpOrderDetailsPK;
import HibernateMapping.SpOrderInvGrn;
import HibernateMapping.SpOrderInvGrnDetails;
import HibernateMapping.SpOrderInvGrnDetailsPK;
import HibernateMapping.SpOrderInvoices;
import HibernateMapping.SpOrderMaster;
import HibernateMapping.SpStockAdjDetails;
import HibernateMapping.SpStockAdjHDR;
import HibernateUtil.HibernateUtil;
import beans.inventoryForm;
import dbConnection.dbConnection;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author sreeja.vijayakumaran
 */
public class inventoryDAO {

    public boolean uploadXls(String fileName, String filePath, FormFile uploadFile, String realPath) {
        boolean isUploaded = false;
        if (!fileName.equals("")) {
            fileName.toUpperCase().replaceAll(".XLS", ".xls");
            File currentToolDb = new File(filePath, fileName);

            try {
                FileOutputStream fileOutStream = new FileOutputStream(currentToolDb);
                fileOutStream.write(uploadFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
                isUploaded = true;
            } catch (Exception e) {
                e.printStackTrace();
                isUploaded = false;
            }
        }
        return isUploaded;
    }

    public File uploadXlsxImage(String fileName, String filePath, String ecatPATH) throws IOException, InterruptedException {
        File xlsfile = null, checkExcelData = null;
        Process p = Runtime.getRuntime().exec("cscript \"" + ecatPATH + "VBScript/oxl.vbs\" \"" + filePath + "\\" + fileName + "\"");
        if (p != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // System.out.println("***vbscript out=" + inputLine);
            }
        }
        p.waitFor();
        p.destroy();
        xlsfile = new File(filePath + "/" + fileName.substring(0, fileName.length() - 1));
        checkExcelData = new File(filePath + "/" + fileName);
        checkExcelData.delete();
        return xlsfile;
    }

    public static String isInventoryExcelValidated(File xlsfile) throws Exception {

        ArrayList<String> part_no = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "failure1", partno = "", qty = "", binLoc = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        try {
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'QTY' Missing. Template error.";
            }
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, No Records Available.";
            }
            totalrows = (sheet.getRows());
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                    for (column = 0; column < 3; column++) {

                        if (column == 0) {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partno.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be blank. ";
                            }
                            if (partno.length() > 50) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be greater than 50 characters.";
                            }
                            if (partno.equals("-") || partno.equals("/")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Please enter valid Part No. ";
                            }
                            r = isRegularExpression(partno);
                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Part No.";

                            }
                            if (part_no.contains(partno)) {
                                return "Error In Row " + (row + 1) + " , Column " + (column + 1) + ". Part No cannot be entered twice.";
                            }
                            if (!part_no.contains(partno)) {
                                part_no.add(partno);
                            }
                        } else if (column == 1) {

                            qty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (qty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Quantity can not be blank.";
                            } else {
                                r = containsOnlyNumbers(qty);
                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Quantity.";
                                }
                            }
                        } else if (column == 2) {
                            binLoc = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (binLoc.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Bin Location can not be blank.";
                            }
                            if (binLoc.length() > 70) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Bin Location can not be greater than 70 characters.";
                            }
                            r = isRegularExpressBin(binLoc);
                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Bin Location.";
                            }
                        }
                    }
                } else {
                    result = "success1";
                    break;
                }

                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";


        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;
    }

    public static boolean isRegularExpression(String s) {
        final char[] regExpChars = new char[]{
            '*', '+', '[', ']', '.', '^', '&', '\\', '$', '~', '`', '\'', '!', '|', '/', '#', '(', ')', ',', '<', '>',
            '?', '{', '}', '=', '@', '%', '"'
        };
        boolean result = false;
        if ((s != null) && (s.length() > 0)) {
            for (int i = 0; i < regExpChars.length; i++) {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result) {
                    break;
                }
            }
        }
        if ((s != null) && (s.length() > 0)) {
            for (int i = 0; i < regExpChars.length; i++) {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isRegularExpressBin(String s) {
        final char[] regExpChars = new char[]{
            '*', '+', '[', ']', '^', '&', '\\', '$', '~', '`', '\'', '!', '|', '#', '(', ')', '<', '>',
            '?', '{', '}', '=', '@', '%', '"'
        };
        boolean result = false;
        if ((s != null) && (s.length() > 0)) {
            for (int i = 0; i < regExpChars.length; i++) {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result) {
                    break;
                }
            }
        }
        if ((s != null) && (s.length() > 0)) {
            for (int i = 0; i < regExpChars.length; i++) {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    public static boolean containsOnlyNumbers(String str) {

        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        if(str.indexOf(".")!=-1)
        {
             return false;
        }

        return true;
    }

    public ArrayList<ArrayList<String>> inventoryExcelInsertion(File xlsfile, String dealercode, String cntxpath, inventoryForm invForm) throws Exception {
        Session hrbsession = null;
        int row = 1, column = 0;
        ArrayList<ArrayList<String>> result = null;
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<inventoryForm> failPartList = new ArrayList<inventoryForm>();
        inventoryForm inForm = null;
        String failPartLink = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        String binLoc = "";
        boolean bin = false;
        int qty = 0;
        int partnoAdded = 0;
        Query query = null;
        ArrayList partList = null;
        List tmpList = null;
        Transaction tx = null;
        SpInventory spi = null;
        SpInventoryPK spik = null;
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            tx = hrbsession.beginTransaction();
            result = new ArrayList<ArrayList<String>>();
            String hql = "delete from SpInventory where spInventoryPK.dealerCode='" + dealercode + "'";
            query = hrbsession.createQuery(hql);
            query.executeUpdate();
            hrbsession.flush();
            Query partQuery = hrbsession.createQuery("select upper(partNo) from CatPart ");
            partList = (ArrayList) partQuery.list();
            int i = 0;
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END"))) {
                spi = new SpInventory();
                spik = new SpInventoryPK();
                partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                qty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                binLoc = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                column++;
                if (partList.contains(partno)) {
                    spik.setDealerCode(dealercode);
                    spik.setPartNo(partno);
                    spi.setQuantity(qty);
                    spi.setBinLocation(binLoc);
                    spi.setSpInventoryPK(spik);
                    spi.setCreatedOn(new Date(new java.util.Date().getTime()));
                    spi.setCreatedBy(dealercode);
                    hrbsession.save(spi);
                    partnoAdded++;
                    column = 0;
                    row++;
                } else {
                    inForm = new inventoryForm();
                    inForm.setPartno(partno);
                    inForm.setQty("" + qty);
                    inForm.setBinLocation(binLoc);
                    failPartList.add(inForm);
                    column = 0;
                    row++;
                }
                if (i % 50 == 0) {

                    hrbsession.flush();
                    hrbsession.clear();
                }

                i = i + 1;
            }
            invForm.setFailPartList(failPartList);
            failPartLink = "<a href='" + cntxpath + "/inventoryAction.do?option=failPartExcelCreation'><font color=blue>Click Here</font></a> to view the Parts that has not been Uploaded.";
            tx.commit();
            if (row == (partnoAdded + 1)) {
                message.add(0, "All the given " + partnoAdded + " parts has been uploaded successfully. Please verify the parts before locking the inventory by using View Inventory link. ");
            } else if (partnoAdded == 0) {
                message.add(0, "Part No. has not been added , because of part uploaded in Excel does not exist in Part Master.<br>" + failPartLink);
            } else if ((row != (partnoAdded + 1)) && (partnoAdded != 0)) {
                message.add(0, "Out of the given " + (row - 1) + " parts, only " + partnoAdded + "  parts has been uploaded. Please verify the parts before locking the inventory by using View Inventory link.   <br>" + failPartLink);
            } else {
                message.add(0, "Part No. has not been added , because of part uploaded in Excel does not exist in Part Master.<br>" + failPartLink);
            }
            result.add(message);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//    public String checkInDb(String dealercode, String column1, String tablename) {
//        String val = "notexist";
//        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
//        try {
//
//            String hql = " FROM " + tablename + " where " + (column1) + "='" + dealercode + "'";
//            Query query = hrbsession.createQuery(hql);
//            List results = query.list();
//
//            Iterator itr = results.iterator();
////            System.out.println("check_in_Db:select * from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery);
//            if (itr.hasNext()) {
//                val = "exist";
//            }
//
//        } catch (Exception ae) {
//            ae.printStackTrace();
//        } finally {
//            try {
//               hrbsession.getTransaction().rollback();                     hrbsession.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return val;
//    }
    public ArrayList<inventoryForm> getInventoryList(inventoryForm invf, String priceListCode) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Query hqlQuery = null;
        Iterator itr = null;
        List spIList = null;
        ArrayList<inventoryForm> dataList = null;
        String part_no = (invf.getPartnum() == null) ? "" : invf.getPartnum();
        
        System.out.println("==========================");
        System.out.println("priceListCode "+priceListCode);
        System.out.println("invf.getDealerCode() "+invf.getDealerCode());
        System.out.println("part_no "+part_no);

        try {
            dataList = new ArrayList<inventoryForm>();
//            if (part_no.equalsIgnoreCase(""))
//            {
//                hqlQuery = hrbsession.createQuery(" from SpCurrentInventoryView where dealerCode=:dealercode");
//                hqlQuery.setParameter("dealercode", dealercode);
//            }
//            else
//            {
            hqlQuery = hrbsession.createQuery(" from SpCurrentInventoryView cv where cv.dealerCode=isnull(?,cv.dealerCode) and cv.partNo like '%" + part_no + "%' and cv.pricelistCode='" + priceListCode + "' order by cv.partNo");
            hqlQuery.setString(0, invf.getDealerCode());
//            }
            spIList = hqlQuery.list();
            
            System.out.println("spIList "+spIList.size());
            
            itr = spIList.iterator();
            while (itr.hasNext()) {
                SpCurrentInventoryView spi = (SpCurrentInventoryView) itr.next();
                invf = new inventoryForm();
                invf.setPartno(spi.getPartNo());
                invf.setPart_desc(spi.getPartDesc() == null ? "" : spi.getPartDesc());
                invf.setDealerCode(spi.getDealerCode() == null ? "" : spi.getDealerCode());
                if (spi.getQuantity() != null && spi.getQuantity() == Math.ceil(spi.getQuantity())) {
                    invf.setQty("" + (spi.getQuantity().intValue()));
                } else {
                    invf.setQty(spi.getQuantity() == null ? "0" : commonUtilMethods.roundUpDecimalToTwoPlace(spi.getQuantity()) + "");
                }
                if (spi.getWipQty() != null && spi.getWipQty() == Math.ceil(spi.getWipQty())) {
                    invf.setWip_qty("" + (spi.getWipQty().intValue()));
                } else {
                    invf.setWip_qty(spi.getWipQty() == null ? "0" : spi.getWipQty() + "");
                }
                if (spi.getCurrentQty() != null && spi.getCurrentQty() == Math.ceil(spi.getCurrentQty())) {
                    invf.setTotal_qty("" + (spi.getCurrentQty().intValue()));
                } else {
                    invf.setTotal_qty(spi.getCurrentQty() == null ? "0" : commonUtilMethods.roundUpDecimalToTwoPlace(spi.getCurrentQty()) + "");
                }
//                invf.setWip_qty(spi.getWipQty()==null?"0.0":spi.getWipQty()+"");
//                invf.setTotal_qty(spi.getCurrentQty()==null?"0.0":spi.getCurrentQty()+"");
                invf.setBinLocation(spi.getBinLocation() == null ? "" : spi.getBinLocation());
                invf.setMrp(spi.getMrp() + "");
                invf.setFinalamount(spi.getAmount() + "");
                dataList.add(invf);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public void insertInventoryLeger(String dealercode) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Iterator itr = null;
        SpInventoryLedger spi = null;
        SpInventoryLedgerPK spik = null;
        SpInventoryPK sp = null;
        SpInventory spinv = null;
        Query query = null;
        Query query1 = null;
        try {
            tx = hrbsession.beginTransaction();
            String hql = "delete from SpInventoryLedger where spInventoryLedgerPK.dealerCode='" + dealercode + "'";
            query = hrbsession.createQuery(hql);
            query.executeUpdate();
            hrbsession.flush();
            query1 = hrbsession.createQuery(" select spInventoryPK.dealerCode,spInventoryPK.partNo,quantity from SpInventory where spInventoryPK.dealerCode='" + dealercode + "'");
            itr = query1.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                spi = new SpInventoryLedger();
                spik = new SpInventoryLedgerPK();
                spik.setDealerCode(o[0].toString());
                spik.setPartNo(o[1].toString());//sp
                spik.setTransactionDate(new java.util.Date());
                spi.setSpInventoryLedgerPK(spik);
                spi.setTransactionDescription("Initial Inventory");
//                spi.setOpeningStk(0);
                spi.setOutward(0);
//                spi.setClosingStk(o[2] == null ? 0 : Integer.parseInt(o[2].toString()));
                spi.setInward(o[2] == null ? 0 : Double.parseDouble(o[2].toString()));
                spi.setLastModifiedOn(new java.util.Date());
                spi.setLastModifiedBy(dealercode);
                hrbsession.save(spi);
            }
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertInventoryLock(String dealercode) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            SpInventoryLock splock = new SpInventoryLock();
            splock.setDealerCode(dealercode);
            splock.setLockDate(new Date(new java.util.Date().getTime()));
            splock.setLockedBy(dealercode);
            hrbsession.save(splock);
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList getDealerCode() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList delList = new ArrayList();
        try {
            Query dcQuery = session.createQuery("select distinct dealerCode from SpInventoryLock ");
            delList = (ArrayList) dcQuery.list();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return delList;
    }

    public String addInventoryToDB(inventoryForm invForm) {
        String result = "FAILURE@@test";
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session.beginTransaction();
            String inventoryNo = new MethodUtility().getNumber(session, "SpInventOtherDealer", invForm.getDealerCode(), "O");
            if (inventoryNo.equals("notexist")) {
                return "FAILURE@@vehicleInfoFailure4locCode";
            }
            SpInventOtherDealer spInvOth = new SpInventOtherDealer();
            spInvOth.setDealerCode(invForm.getDealerCode());
            spInvOth.setInventNo(inventoryNo);
            spInvOth.setSubTotal(Double.parseDouble(invForm.getFinalamount()));
            spInvOth.setTaxValue(invForm.getTaxamount());
            spInvOth.setTotalValue(invForm.getTotalFinalamount());
            spInvOth.setBillDate(sdf.parse(invForm.getBilldate()));
            spInvOth.setBillNo(invForm.getBillNo());
            spInvOth.setVendorName(invForm.getDealerName());
            spInvOth.setCreatedOn(new Date(new java.util.Date().getTime()));
            spInvOth.setCreatedBy(invForm.getUserid());
            session.save(spInvOth);

            for (int i = 0; i < invForm.getPartNo().length; i++) {
                if (!invForm.getPartNo()[i].equals("")) {
                	
                	String isRestPart = getCheckRestrictedPart(invForm.getPartNo()[i], session);
                    if (!isRestPart.equals("")) {
                        return "FAILUREREST@@"+invForm.getPartNo()[i];
                    }
                	
                    SpInventOtherDealerDetailsPK spInvOthDetailPK = new SpInventOtherDealerDetailsPK(inventoryNo, invForm.getPartNo()[i]);
                    SpInventOtherDealerDetails spInvOthDetail = new SpInventOtherDealerDetails(spInvOthDetailPK);
                    spInvOthDetail.setPartdesc(invForm.getPartDesc()[i]);
                    spInvOthDetail.setQty(Double.parseDouble(invForm.getQuantity()[i]));
                    spInvOthDetail.setUnitvalue(Double.parseDouble(invForm.getUnitprice()[i]));
                    session.save(spInvOthDetail);
                    MethodUtility.inventoryLedgerEntry(invForm.getUserid(), invForm.getDealerCode(), invForm.getPartNo()[i], Double.parseDouble(invForm.getQuantity()[i]), "Add Inventory", session);
                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            }
            session.getTransaction().commit();
            result = "SUCCESS@@test";
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<inventoryForm> getBilledList() {
        ArrayList<inventoryForm> dataList = null;
        inventoryForm inForm = null;
        String hql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<inventoryForm>();
            hql = "select bm.billID, bm.billDesc, bm.discount from Billmaster bm where bm.isActive='Y' and bm.warrantyType='No' "; //'" + stateId + "'

            Query query = session.createQuery(hql);

            List<String> list = (List<String>) query.list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                inForm = new inventoryForm();
                inForm.setBillId(obj[0] == null ? "" : obj[0].toString().trim());
                inForm.setBilldesc(obj[1] == null ? "" : obj[1].toString().trim());
                inForm.setDiscount(obj[2] == null ? "" : obj[2].toString().trim());
                dataList.add(inForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public inventoryForm saveCounterSale(inventoryForm invForm, String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        double percentAmt = 0.0, finalDis = 0.0, discountAmt = 0.0;
        String discountAmmount = "";
        String result = "fail";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dfAmt = new DecimalFormat("#0.00");
        try {
            session.beginTransaction();
            String invoiceNo = new MethodUtility().getNumber(session, "SpInventSaleMaster", invForm.getDealerCode(), "I");
            invForm.setInvNo(invoiceNo);
            SpInventSaleMaster spInven = new SpInventSaleMaster();
            spInven.setInvoiceNo(invoiceNo);
            spInven.setDealerCode(invForm.getDealerCode());
            spInven.setCustomerName(invForm.getCusName());
            spInven.setContactno(invForm.getContactNo());
            spInven.setInvoicetype("CounterSale");

            //Avinash Discount Logic 21-03-2016
            finalDis = Math.floor(Double.parseDouble(invForm.getFinalamount()));
            discountAmt = Double.parseDouble(invForm.getFinalamount()) - finalDis;

            //spInven.setInvoiceValue(Double.parseDouble(invForm.getFinalamount()));
            spInven.setCreditValue(Double.parseDouble(invForm.getCreditValue()));
            spInven.setTypeRefno("NA");
            spInven.setInvoiceDate(df.parse(invForm.getInvDate()));
            spInven.setPartsValue(Double.parseDouble(invForm.getPartValue()));
            spInven.setLubesValue(Double.parseDouble(invForm.getLubesValue()));
            spInven.setDiscountOther(Double.parseDouble(invForm.getOtherDiscount()) + discountAmt);
            spInven.setDiscountParts(0);
            spInven.setDiscountLubes(0);
            spInven.setCreatedBy(userId);
            spInven.setCreatedOn(new Date());
            spInven.setCustomerID(new BigInteger(invForm.getCustomerId()));
            spInven.setSaleTypeCode(invForm.getSaleTaxTypeID());
            spInven.setInvoiceValue(Math.floor(Double.parseDouble(invForm.getFinalamount())));
            spInven.setGstNo(invForm.getGstNo());
            session.saveOrUpdate(spInven);

            for (int i = 0; i < invForm.getPartNo().length; i++) {
                percentAmt = 0.0;
                discountAmmount = "0.0";
                if (!invForm.getPartNo()[i].equals("")) {
                    String check=new MethodUtility().checkInDb(invForm.getPartNo()[i],"partNo","CatPart");
                    if (check.equals("notexist")) {
                        invForm.setResult("HSNCODE");
                        return invForm;
                    }
                    SpInventSaleDetailsPK spInvPart = new SpInventSaleDetailsPK(invoiceNo, invForm.getPartNo()[i]);
                    SpInventSaleDetails spInvDetail = new SpInventSaleDetails(spInvPart);
                    spInvDetail.setBillID(Integer.parseInt(invForm.getBillIdArr()[i].split("@@")[0]));
                    // spInvDetail.setDiscount(Double.parseDouble(invForm.getBillIdArr()[i].split("@@")[1]));
                    //spInvDetail.setDiscount(Double.parseDouble(invForm.getDisAmmperPart()[i]));
                    //Avinash 15-04-2015
                    percentAmt = Double.parseDouble(invForm.getQuantity()[i]) * Double.parseDouble(invForm.getUnitprice()[i]); //calculate   Quantity * Unitprice
                    discountAmmount = "" + ((Double.parseDouble(invForm.getPercentDis()[i]) / 100) * percentAmt);
                    if (discountAmmount.equals("")) {
                        discountAmmount = "0.0";
                    }
                    spInvDetail.setDiscount(Double.parseDouble(discountAmmount));
                    //END
                    spInvDetail.setPartdesc(invForm.getPartDesc()[i]);
                    spInvDetail.setFinalAmount(Double.parseDouble(invForm.getAmount()[i]));
                    spInvDetail.setQty(Double.parseDouble(invForm.getQuantity()[i]));
                    spInvDetail.setUnitvalue(Double.parseDouble(invForm.getUnitprice()[i]));
                    if (invForm.getPartType()[i].equalsIgnoreCase("LUBES")) {
                        //spInvDetail.setPartcategory("SPARES");
                        spInvDetail.setPartcategory("LUBES");
                    } else {
                        //spInvDetail.setPartcategory(invForm.getPartType()[i]);
                        spInvDetail.setPartcategory("SPARES");
                    }
                    session.saveOrUpdate(spInvDetail);

                    MethodUtility.inventoryLedgerEntry(userId, invForm.getDealerCode(), invForm.getPartNo()[i], Double.parseDouble(invForm.getQuantity()[i]), "Counter Sale", session);
                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }

                }
            }
            session.getTransaction().commit();
            session.beginTransaction();
            session.createSQLQuery("exec SP_updateTaxInvoice :invoiceNo").setParameter("invoiceNo", invoiceNo).executeUpdate();
            session.getTransaction().commit();

            invForm.setResult("success");
        } catch (Exception e) {
            invForm.setResult("failure");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invForm;
    }

    public String getPriceByPartNo(String partno, String dealerCode, String priceListCode) {
        String tempDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            // Query query = hrbsession.createQuery("select cp.p1, cp.partType,pm.price ,sp.currentQty "
//                    + " from CatPart cp ,SpCurrentInventoryView sp "
//                    + " left join cp.spPriceMasterList pm   "
//                    + " where sp.partNo=cp.partNo and cp.partNo=:partNo and sp.dealerCode=:dealerCode ");
            //Avinash pandey Domestic/Export logic
            Query query = hrbsession.createQuery("select cp.p1, cp.partType,sp.mrp ,sp.currentQty "
                    + " from CatPart cp ,SpCurrentInventoryView sp "
                    // + " left join cp.spPriceMasterList pm   "
                    + " where sp.partNo=cp.partNo and cp.partNo=:partNo and sp.dealerCode=:dealerCode and  sp.pricelistCode='" + priceListCode + "' ");

            query.setParameter("partNo", partno);
            query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();
            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (o[2] == null ? "" : o[2]) + "@@" + (o[3] == null ? "" : o[3].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempDesc;
    }

    /**
     * update Bin Location
     */
    public String updateBinLocation(String partNo, String BinLocation, String dealerCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure";
        try {
            session.beginTransaction();
            SpInventoryPK spinvpk = new SpInventoryPK(dealerCode, partNo);
            SpInventory spinv = (SpInventory) session.load(SpInventory.class, spinvpk);
            spinv.setBinLocation(BinLocation);
            session.saveOrUpdate(spinv);
            session.getTransaction().commit();
            result = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<inventoryForm> getCounSaleInvoiceList(String dealerCode) {
        ArrayList<inventoryForm> dataList = null;
        inventoryForm inForm = null;
        String hql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<inventoryForm>();
            hql = "select bm.invoiceNo, bm.dealerCode from SpInventSaleMaster bm where bm.invoicetype='CounterSale' and bm.dealerCode ='" + dealerCode + "'"; //'" + stateId + "' , bm.typeRefno
            Query query = session.createQuery(hql);
            List<String> list = (List<String>) query.list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                inForm = new inventoryForm();
                inForm.setInvNo(obj[0] == null ? "" : obj[0].toString().trim());
                inForm.setDealerCode(obj[1] == null ? "" : obj[1].toString().trim());
                // inForm.set(obj[2] == null ? "" : obj[2].toString().trim());
                dataList.add(inForm);
            }
            //System.out.println(" cscs  222 " +dataList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<inventoryForm> getCounSaleDetails(inventoryForm invForm, String dealerCode, String invNo) {
        ArrayList<inventoryForm> dataList = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        inventoryForm inForm = null;
        String hql = null;
        String datahql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            datahql = "select bm.invoiceNo, bm.dealerCode ,bm.invoiceDate,bm.customerName ,bm.customerID,bm.saleTypeCode from SpInventSaleMaster bm where bm.invoicetype='CounterSale' and bm.invoiceNo=:invNo"; //and not exists (select sr.invoiceNo from  SpInventSreturn sr  where sr.invoiceNo=:invNo)";
            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("invNo", invNo);
            //  List<String> list = (List<String>) query.list();
            Iterator datait = dataquery.list().iterator();
            while (datait.hasNext()) {
                Object obj[] = (Object[]) datait.next();
                invForm.setInvNo(obj[0] == null ? "" : obj[0].toString().trim());
                invForm.setDealerCode(obj[1] == null ? "" : obj[1].toString().trim());
                String claimDateString = obj[2] == null ? "" : obj[2].toString().trim();
                if (!claimDateString.equals("")) {
                    invForm.setInvDate(outputFormat.format(inputFormat.parse(claimDateString)));
                }
                invForm.setCusName(obj[3] == null ? "" : obj[3].toString().trim());
                invForm.setCustomerId(obj[4] == null ? "0" : obj[4].toString().trim());
                invForm.setSaleTaxTypeID(obj[5] == null ? "0" : obj[5].toString().trim());

            }
            dataList = new ArrayList<inventoryForm>();
            hql = "select bm.spInventSaleDetailsPK.invoiceNo,bm.spInventSaleDetailsPK.partno, "
                    + "bm.partdesc,bm.qty,bm.billID,bm.discount,bm.finalAmount ,bm.unitvalue " + //  bm.unitvalue, bm.partcategory
                    " from SpInventSaleDetails bm where bm.spInventSaleDetailsPK.invoiceNo=:invNo ";//and not exists (select sr.invoiceNo from  SpInventSreturn sr  where sr.invoiceNo=:invNo)"; //'" + stateId + "' , bm.typeRefno

            Query query = session.createQuery(hql);
            query.setParameter("invNo", invNo);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                inForm = new inventoryForm();
                inForm.setInvNo(obj[0] == null ? "" : obj[0].toString().trim());
                inForm.setPartno(obj[1] == null ? "" : obj[1].toString().trim());
                inForm.setPart_desc(obj[2] == null ? "" : obj[2].toString().trim());

                double t = Double.parseDouble(obj[3].toString().trim());
                if (t == Math.ceil(t)) {
                    inForm.setQty("" + ((int) t));
                } else {
                    inForm.setQty(obj[3] == null ? "" : obj[3].toString().trim());
                }
                inForm.setBillId(obj[4] == null ? "" : obj[4].toString().trim());

                double d = Double.parseDouble(obj[5].toString().trim());
                if (d == Math.ceil(d)) {
                    inForm.setDiscount("" + ((int) d));
                } else {
                    inForm.setDiscount(obj[5] == null ? "" : obj[5].toString().trim());
                }
                //inForm.setDiscount(obj[5] == null ? "" : obj[5].toString().trim());
                inForm.setFinalamount(obj[6] == null ? "" : obj[6].toString().trim());
                inForm.setUnitValue(obj[7] == null ? "" : obj[7].toString().trim());
                dataList.add(inForm);
            }
            //System.out.println(" cscs  det " +dataList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public inventoryForm saveSalesReturn(inventoryForm invForm, String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String result = "fail";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session.beginTransaction();
            String saleReturnNo = new MethodUtility().getNumber(session, "SpInventSreturn", invForm.getDealerCode(), "R");
            invForm.setSaleReturnNo(saleReturnNo);
            SpInventSreturn saleReturn = new SpInventSreturn();
            saleReturn.setReturnNo(saleReturnNo);
            saleReturn.setDealerCode(invForm.getDealerCode());
            saleReturn.setInvoiceNo(invForm.getInvNo());
            saleReturn.setReturnBy(invForm.getReturnBy());
            saleReturn.setReturnDate(df.parse(invForm.getReturnDate()));
            saleReturn.setCreatedBy(userId);
            saleReturn.setCreatedOn(new Date());
            saleReturn.setTotalValue(Double.parseDouble(invForm.getFinalamount()));
            saleReturn.setCustomerID(new BigInteger(invForm.getCustomerId()));
            session.save(saleReturn);


            for (int i = 0; i < invForm.getPartNo().length; i++) {
                if (!invForm.getPartNo()[i].equals("")) {             //prashant

                    SpInventSreturnDetailsPK spInvPart = new SpInventSreturnDetailsPK(saleReturnNo, invForm.getPartNo()[i]);
                    SpInventSreturnDetails spInvDetail = new SpInventSreturnDetails(spInvPart);
                    spInvDetail.setPartdesc(invForm.getPartDesc()[i]);
                    spInvDetail.setInvoiceQty(Double.parseDouble(invForm.getQuantity()[i]));
                    spInvDetail.setRecdQty(Double.parseDouble(invForm.getReceivedQty()[i]));
                    spInvDetail.setUnitvalue(Double.parseDouble(invForm.getUnitprice()[i]));
                    session.save(spInvDetail);
                    MethodUtility.inventoryLedgerEntry(userId, invForm.getDealerCode(), invForm.getPartNo()[i], Double.parseDouble(invForm.getReceivedQty()[i]), "Counter Sales Return", session);
                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            }
            session.getTransaction().commit();
            invForm.setResult("success");
            //result = "success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invForm;
    }

    public String checkInvoice(String invNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String val = "notexist";
        try {
            String hql = "select sr.invoiceNo from  SpInventSreturn sr where sr.invoiceNo=:invNo";
            Query query = session.createQuery(hql);
            query.setParameter("invNo", invNo);
            List result = query.list();
            // System.out.println("" + result.toString());
            if (result.size() > 0) {
                val = "exist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    public ArrayList<inventoryForm> inventoryLedgerReport(inventoryForm invForm, Vector userFunctionalities, String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> inventoryList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<String> dealerList = new ArrayList<String>();

        try {
            String Subsql = "";
            String dateSubQur = "";
            String hql = "";
            if (!invForm.getPartnum().equals("")) {
                Subsql = " and  spl.spInventoryLedgerPK.partNo like '%" + invForm.getPartnum() + "%' ";
            }
            if ("1".equals(invForm.getRange())) {
                dateSubQur = " and  (spl.spInventoryLedgerPK.transactionDate between isnull(?,spl.spInventoryLedgerPK.transactionDate) and isnull(?,spl.spInventoryLedgerPK.transactionDate) )";
            }
            hql = "select spl.spInventoryLedgerPK.partNo,cp.p1,spl.transactionDescription,spl.spInventoryLedgerPK.transactionDate,"
                    + " spl.inward,spl.outward,dl.dealerCode,dl.dealerName,dl.location, "
                    + " (select case when (sum(sp.inward)-sum(sp.outward)) is NULL then 0 else (sum(sp.inward)-sum(sp.outward)) end"
                    + " from SpInventoryLedger sp "
                    + " where sp.spInventoryLedgerPK.dealerCode=spl.spInventoryLedgerPK.dealerCode and "
                    + " sp.spInventoryLedgerPK.partNo=spl.spInventoryLedgerPK.partNo and "
                    + " sp.spInventoryLedgerPK.transactionDate<spl.spInventoryLedgerPK.transactionDate ) as OPENING"
                    + " from SpInventoryLedger spl,CatPart cp,Dealervslocationcode dl"
                    + " where spl.spInventoryLedgerPK.partNo=cp.partNo and spl.spInventoryLedgerPK.dealerCode=dl.dealerCode ";
            // + " (spl.spInventoryLedgerPK.transactionDate between isnull(?,spl.spInventoryLedgerPK.transactionDate) and isnull(?,spl.spInventoryLedgerPK.transactionDate)) "
            //   + " and spl.spInventoryLedgerPK.dealerCode=isnull(?,spl.spInventoryLedgerPK.dealerCode)";
            //  + Subsql + dateSubQur +" order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc";

            Query query = null;
            query = session.createQuery(hql + Subsql + dateSubQur + " and :dealerList LIKE ('%'+spl.spInventoryLedgerPK.dealerCode+'%') order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc");
            if (invForm.getDealerCode() != null && !invForm.getDealerCode().equals("") && !invForm.getDealerCode().equalsIgnoreCase("ALL")) {
                dealerList.add(invForm.getDealerCode());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*            if (userFunctionalities.contains("101")) {
            hql = hql + Subsql + dateSubQur + " and spl.spInventoryLedgerPK.dealerCode='" + invForm.getDealerCode() + "' order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + Subsql + dateSubQur + " and and spl.spInventoryLedgerPK.dealerCode IN(:dealerList) order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc";
            if (invForm.getDealerCode() != null && !invForm.getDealerCode().equals("") && !invForm.getDealerCode().equalsIgnoreCase("ALL")) {
            dealerList.add(invForm.getDealerCode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + invForm.getUserid() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
            if (invForm.getDealerCode() != null && !invForm.getDealerCode().equals("") && !invForm.getDealerCode().equalsIgnoreCase("ALL")) {
            hql = hql + Subsql + dateSubQur + " and spl.spInventoryLedgerPK.dealerCode='" + invForm.getDealerCode() + "' order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc";
            System.out.println(hql);
            } else {
            hql = hql + Subsql + dateSubQur + " order by spl.spInventoryLedgerPK.dealerCode, spl.spInventoryLedgerPK.transactionDate desc";
            }
            query = session.createQuery(hql);
            }
             *
             */
            //Query query = session.createQuery(hql);
            //  query.setString(0,invForm.getDealerCode());
            if ("1".equals(invForm.getRange())) {
                query.setString(0, invForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(invForm.getFromdate() + " 00:00")));
                query.setString(1, invForm.getTodate().equals("") == true ? null : df.format(sdf.parse(invForm.getTodate() + " 23:59")));
            }

            double t = 0;
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                inventoryForm inf = new inventoryForm();
                inf.setPartno(obj[0] == null ? "" : obj[0].toString().trim());
                inf.setPart_desc(obj[1] == null ? "" : obj[1].toString().trim());
                inf.setTransdate_desc(obj[2] == null ? "" : obj[2].toString().trim());
                inf.setTransdate(obj[3] == null ? "" : sdf1.format(df.parse(obj[3].toString().trim())));
                inf.setInWard_qty(obj[4] == null ? "0" : obj[4].toString().trim());
                t = Double.parseDouble(inf.getInWard_qty());
                if (t == Math.ceil(t)) {
                    inf.setInWard_qty("" + ((int) t));
                }
                inf.setOutWard_qty(obj[5] == null ? "0" : obj[5].toString().trim());
                t = Double.parseDouble(inf.getOutWard_qty());
                if (t == Math.ceil(t)) {
                    inf.setOutWard_qty("" + ((int) t));
                }
                inf.setDealerCode(obj[6] == null ? "" : obj[6].toString().trim());
                inf.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());
                inf.setDealer_location(obj[8] == null ? "" : obj[8].toString().trim());
                inf.setOpeningStock(obj[9] == null ? "" : obj[9].toString().trim());
                t = Double.parseDouble(inf.getOpeningStock()) + Double.parseDouble(inf.getInWard_qty()) - Double.parseDouble(inf.getOutWard_qty());
                if (t == Math.ceil(t)) {
                    inf.setClosingStock("" + ((int) t));
                } else {
                    inf.setClosingStock("" + t);
                }
                inventoryList.add(inf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inventoryList;
    }

    /*
     * validate excel for Upload bin Location
     */
    public static String isBinLocationExcelValidated(File xlsfile) throws Exception {
        ArrayList<String> part_no = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "failure1", partno = "", binLoc = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        try {
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("BIN  LOCATION"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'QTY' Missing. Template error.";
            }
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, No Records Available.";
            }
            totalrows = (sheet.getRows());
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                    for (column = 0; column < 3; column++) {

                        if (column == 0) {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partno.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be blank. ";
                            }
                            if (partno.length() > 50) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be greater than 50 characters.";
                            }
                            if (partno.equals("-") || partno.equals("/")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Please enter valid Part No. ";
                            }
                            r = isRegularExpression(partno);
                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Part No.";

                            }
                            if (part_no.contains(partno)) {
                                return "Error In Row " + (row + 1) + " , Column " + (column + 1) + ". Part No cannot be entered twice.";
                            }
                            if (!part_no.contains(partno)) {
                                part_no.add(partno);
                            }
                        } else if (column == 1) {
                            binLoc = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (binLoc.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Bin Location can not be blank.";
                            }
                            if (binLoc.length() > 70) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Bin Location can not be greater than 70 characters.";
                            }
                            r = isRegularExpressBin(binLoc);
                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Bin Location.";

                            }
                        }
                    }
                } else {
                    result = "success1";
                    break;
                }

                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;
    }

    /*
     * Update binlocation in db using Excel Upload
     */
    public String binLocationExcelUpdate(File xlsfile, String dealercode, String cntxpath) throws Exception {
        Session session = null;
        int row = 1, column = 0;
        String result = "Part No. has not been updated.";
        ;
        //ArrayList<String> message = new ArrayList<String>();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        String binLoc = "";
        boolean bin = false;
        int partUpdated = 0;
        SpInventory spi = null;
        SpInventoryPK spik = null;
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            result = new ArrayList<ArrayList<String>>();

            int i = 0;
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END"))) {
                partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                binLoc = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                column++;
                spik = new SpInventoryPK();
                spik.setDealerCode(dealercode);
                spik.setPartNo(partno);
                spi = (SpInventory) session.get(SpInventory.class, spik);
                if (spi != null) {
                    spi.setBinLocation(binLoc);
                    spi.setModifiedOn(new Date(new java.util.Date().getTime()));
                    spi.setModifiedBy(dealercode);
                    session.saveOrUpdate(spi);
                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                    i++;
                    partUpdated++;
                }

                column = 0;
                row++;
            }
            session.getTransaction().commit();
            if (partUpdated == 1) {
                result = "Part No has been updated successfully.";
            } else if (partUpdated > 1) {
                result = "Part No(s) have been updated successfully.";
            } else {
                result = "Part No. has not been updated.";
            }
//            result.add(message);
        } catch (Exception e) {
            result = "Part No. has not been updated.";
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<ArrayList<String>> reOrderLevelInsertion(File xlsfile, String dealercode, String cntxpath) throws Exception {
        Session hrbsession = null;
        int row = 1, column = 0;
        ArrayList<ArrayList<String>> result = null;
        ArrayList<String> message = new ArrayList<String>();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        Query query = null;
        int minqty = 0;
        int maxqty = 0;
        int reorderqty = 0;
        int partnoAdded = 0;
        ArrayList partList = null;
        SpInventReorderLevel spi = null;
        SpInventReorderLevelPK spik = null;
        hrbsession = HibernateUtil.getSessionFactory().openSession();
        hrbsession.beginTransaction();
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);

            result = new ArrayList<ArrayList<String>>();
            String hql = "delete from SpInventReorderLevel spo where spo.spInventReorderLevelPK.dealerCode='" + dealercode + "'";
            query = hrbsession.createQuery(hql);
            query.executeUpdate();
            hrbsession.flush();
            Query partQuery = hrbsession.createQuery("select partNo from CatPart ");
            partList = (ArrayList) partQuery.list();
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END"))) {
                spi = new SpInventReorderLevel();
                spik = new SpInventReorderLevelPK();
                partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                minqty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                maxqty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                reorderqty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                if ((partList.contains(partno)) && (reorderqty >= minqty) && (reorderqty <= maxqty)) {
                    // if((reorderqty >=minqty) && (reorderqty<= maxqty)){
                    spik.setDealerCode(dealercode);
                    spik.setPartNo(partno);
                    spi.setSpInventReorderLevelPK(spik);
                    spi.setMinLevel(minqty);
                    spi.setMaxLevel(maxqty);
                    spi.setReorderLevel(reorderqty);
                    spi.setCreatedOn(new Date(new java.util.Date().getTime()));
                    spi.setCreatedBy(dealercode);
                    hrbsession.save(spi);
                    partnoAdded++;
                    column = 0;
                    row++;
                    //   }
                } else {
                    column = 0;
                    row++;
                }
            }
            hrbsession.getTransaction().commit();
            if (partnoAdded == 1) {
                message.add(0, "Part No has been added successfully.");
            } else if (partnoAdded > 1) {
                message.add(0, "Part No(s) have been added successfully.");
            } else {
                message.add(0, "Part No. has not been added , because of part uploaded in Excel does not exist in Part Master or </br> Reorder Qty not in the Range of Min & Max Qty .");
            }
            result.add(message);
        } catch (Exception e) {

            e.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<inventoryForm> getReorderLevelData(inventoryForm invForm, Vector userFunctionalities) {
        ArrayList<inventoryForm> dataList = null;
        inventoryForm inForm = null;
        String hql = "";
        String hql1 = "";
        String subhql = "";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            dataList = new ArrayList<inventoryForm>();

            if (!invForm.getPartnum().equals("")) {
                subhql = " and spo.spInventReorderLevelPK.partNo like '%" + invForm.getPartnum() + "%' ";
            }

            hql = "select spo.spInventReorderLevelPK.partNo,spo.spInventReorderLevelPK.dealerCode, spo.minLevel , "
                    + " spo.maxLevel, spo.reorderLevel ,cp.p1 from SpInventReorderLevel  spo,CatPart cp"
                    + " where cp.partNo=spo.spInventReorderLevelPK.partNo and "
                    + " spo.spInventReorderLevelPK.dealerCode=isnull(?,spo.spInventReorderLevelPK.dealerCode) " + subhql + "   order by spo.spInventReorderLevelPK.partNo ";

//                  if (userFunctionalities.contains("101")) {
//                    hql = hql +" and vd.dealerCode=" + sForm.getDealercode()  + "  order by jm.seqNo";
//                    query = session.createQuery(hql);
//                }

            Query query = session.createQuery(hql);
            if (invForm.getDealerCode() != null && !invForm.getDealerCode().equals("")) {
                query.setString(0, invForm.getDealerCode());


                //invForm.setDealerCode(dealerCode);
                List<String> list = (List<String>) query.list();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Object obj[] = (Object[]) it.next();
                    inForm = new inventoryForm();
                    inForm.setPartno(obj[0] == null ? "" : obj[0].toString().trim());
                    inForm.setDealerCode(obj[1] == null ? "" : obj[1].toString().trim());
                    inForm.setMinLevel(obj[2] == null ? "" : obj[2].toString().trim());
                    inForm.setMaxLevel(obj[3] == null ? "" : obj[3].toString().trim());
                    inForm.setReorderLevel(obj[4] == null ? "" : obj[4].toString().trim());
                    inForm.setPart_desc(obj[5] == null ? "" : obj[5].toString().trim());
                    dataList.add(inForm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String updateReorderLevel(String partNo, inventoryForm inForm, String dealerCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure";
        try {
            session.beginTransaction();
            SpInventReorderLevelPK spinvpk = new SpInventReorderLevelPK(dealerCode, partNo);
            SpInventReorderLevel spinv = (SpInventReorderLevel) session.load(SpInventReorderLevel.class, spinvpk);
            spinv.setMinLevel(Integer.parseInt(inForm.getMinLevel()));
            spinv.setMaxLevel(Integer.parseInt(inForm.getMaxLevel()));
            spinv.setReorderLevel(Integer.parseInt(inForm.getReorderLevel()));
            session.saveOrUpdate(spinv);
            session.getTransaction().commit();
            result = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String reOrderLevelExcelValidated(File xlsfile) throws Exception {

        ArrayList<String> part_no = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "failure1", partno = "", minqty = "", maxqty = "", reorderqty = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        try {
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("MIN QTY"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'MIN QTY' Missing. Template error.";
            }
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, No Records Available.";
            }
            totalrows = (sheet.getRows());
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                    for (column = 0; column < 3; column++) {

                        if (column == 0) {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partno.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be blank. ";
                            }
                            if (partno.length() > 50) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be greater than 50 characters.";
                            }
                            if (partno.equals("-") || partno.equals("/")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Please enter valid Part No. ";
                            }
                            r = isRegularExpression(partno);
                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Part No.";

                            }
                            if (part_no.contains(partno)) {
                                return "Error In Row " + (row + 1) + " , Column " + (column + 1) + ". Part No cannot be entered twice.";
                            }
                            if (!part_no.contains(partno)) {
                                part_no.add(partno);
                            }
                        } else if (column == 1) {

                            minqty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (minqty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Min Quantity can not be blank.";
                            } else {
                                r = containsOnlyNumbers(minqty);
                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Min Quantity.";
                                }
                            }
                        } else if (column == 2) {

                            maxqty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (maxqty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".Max Quantity can not be blank.";
                            } else {
                                r = containsOnlyNumbers(maxqty);
                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Max Quantity.";
                                }
                            }
                        } else if (column == 3) {

                            reorderqty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (reorderqty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".Reorder Quantity can not be blank.";
                            } else {
                                r = containsOnlyNumbers(reorderqty);
                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Reorder Quantity.";
                                }
                            }
                        }
                    }
                } else {
                    result = "success1";
                    break;
                }

                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";


        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;
    }

    public ArrayList<inventoryForm> getReorderPartToCart(inventoryForm invForm, Vector userFunctionalities, String priceListCode) {
        ArrayList<inventoryForm> dataList = null;
        inventoryForm inForm = null;
        String hql = "";
        String hql1 = "";
        String subhql = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<inventoryForm>();
            if (!invForm.getPartnum().equals("")) {
//                subhql = " and spo.spInventReorderLevelPK.partNo like '%" + invForm.getPartnum() + "%' ";
                subhql = " and spo.part_No like '%" + invForm.getPartnum() + "%' ";
            }
//            hql = "select spo.spInventReorderLevelPK.partNo,spo.spInventReorderLevelPK.dealerCode, spo.minLevel , "
//                    + " spo.maxLevel, spo.reorderLevel ,cp.p1 ,spcv.quantity,spcv.currentQty,spcv.wipQty"
//                    + " from SpInventReorderLevel  spo,CatPart cp,SpCurrentInventoryView spcv"
//                    + " where cp.partNo=spo.spInventReorderLevelPK.partNo and "
//                    + " spcv.partNo=spo.spInventReorderLevelPK.partNo and "
//                    + " spcv.dealerCode=spo.spInventReorderLevelPK.dealerCode "
//                    + " and spcv.currentQty < spo.reorderLevel "
//                    + " and spo.spInventReorderLevelPK.dealerCode=isnull(?,spo.spInventReorderLevelPK.dealerCode) " + subhql + "   order by spo.spInventReorderLevelPK.partNo ";

            hql = "select distinct spo.part_No,spo.dealer_Code, spo.min_Level , "
                    + " spo.max_Level, spo.reorder_Level ,cp.p1 ,spcv.quantity, spcv.current_Qty, spcv.wip_Qty"
                    + " from Sp_Invent_Reorder_Level  spo INNER JOIN Cat_Part cp ON cp.part_No = spo.part_No "
                    + " LEFT JOIN Sp_Current_Inventory_View spcv"
                    + " ON spo.part_No=spcv.part_No and spo.DEALER_CODE=spcv.DEALER_CODE "
                    + " where spcv.current_Qty < spo.reorder_Level "
                    + " and spo.dealer_Code=isnull(?,spo.dealer_Code) and (spcv.PRICELIST_CODE is null or spcv.PRICELIST_CODE='" + priceListCode + "') " + subhql + " order by spo.part_No ";

//            Query query = session.createQuery(hql);
            Query query = session.createSQLQuery(hql).addScalar("part_No", new StringType()).addScalar("dealer_Code", new StringType()).addScalar("min_Level", new IntegerType()).addScalar("max_Level", new IntegerType()).addScalar("reorder_Level", new IntegerType()).addScalar("p1", new StringType()).addScalar("quantity", new IntegerType()).addScalar("current_Qty", new IntegerType()).addScalar("wip_Qty", new IntegerType());

            if (invForm.getDealerCode() != null && !invForm.getDealerCode().equals("")) {
                query.setString(0, invForm.getDealerCode());
                List<String> list = (List<String>) query.list();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Object obj[] = (Object[]) it.next();
                    inForm = new inventoryForm();
                    inForm.setPartno(obj[0] == null ? "" : obj[0].toString().trim());
                    inForm.setDealerCode(obj[1] == null ? "" : obj[1].toString().trim());
                    inForm.setMinLevel(obj[2] == null ? "" : obj[2].toString().trim());
                    inForm.setMaxLevel(obj[3] == null ? "" : obj[3].toString().trim());
                    inForm.setReorderLevel(obj[4] == null ? "" : obj[4].toString().trim());
                    inForm.setPart_desc(obj[5] == null ? "" : obj[5].toString().trim());
                    inForm.setQty(obj[6] == null ? "" : obj[6].toString().trim());
                    double ttt = obj[7] == null ? 0.0 : Double.parseDouble(obj[7].toString().trim());
                    if (ttt == Math.ceil(ttt)) {
                        inForm.setCurrQty("" + ((int) ttt));
                    } else {
                        inForm.setCurrQty(obj[7] == null ? "" : obj[7].toString().trim());
                    }
                    inForm.setWip_qty(obj[8] == null ? "" : obj[8].toString().trim());
                    inForm.setReplanishtoMax("" + (Integer.parseInt(obj[3].toString()) - ((int) ttt)));
                    dataList.add(inForm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    //  Create New Order(STD/VOR)
    public ArrayList<inventoryForm> partListbyOldPO(inventoryForm inForm, String priceListCode) {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        ArrayList partList = null;
        Double totalAmm = 0.0;
        try {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            Query partQuery = hrbsession.createQuery("select sd.spOrderDetailsPK.partNo ,sd.qty,sd.price from SpOrderDetails sd where sd.spOrderDetailsPK.custPoNo=:custPoNo");  //
            partQuery.setParameter("custPoNo", inForm.getPrevPoNo());
            Iterator itr = partQuery.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                String partNo = o[0] == null ? "" : o[0].toString();
                String qty = o[1] == null ? "" : o[1].toString();
                String partData = getPriceForOrderByPartNo(partNo, hrbsession, priceListCode);
                inventoryForm inf = new inventoryForm();
                inf.setPartno(partNo);
                inf.setQty("" + qty);
                inf.setPart_desc(partData.split("@@")[0]);
                inf.setPartTypeStr(partData.split("@@")[1]);
                inf.setUnitValue(partData.split("@@")[2]);
                inf.setMoq(partData.split("@@")[3]);
                inf.setService(partData.split("@@")[4]);
                inf.setAmountPerPrice("" + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2])));
                if (inForm.getTotalAmont() != null && !inForm.getTotalAmont().equals("")) {
                    totalAmm = Double.parseDouble(inForm.getTotalAmont());
                }
                totalAmm = totalAmm + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2]));
                inf.setTotalAmont(totalAmm.toString());
                inForm.setTotalAmont(totalAmm.toString());
                dataList.add(inf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return dataList;

    }

    public ArrayList<inventoryForm> addIntoList(File xlsfile, String dealercode, String cntxpath, inventoryForm invForm, String userId, String priceListCode) {

        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Session hrbsession = null;
        int row = 1, column = 0;
        ArrayList<inventoryForm> dataListNew = new ArrayList<inventoryForm>();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int qty = 0;
        int partnoAdded = 0;
        //ArrayList partList = null;
        Double totalAmm = 0.0;
        String partNoInCatPart = "", alternateparts="",background="";
        try {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            // Query partQuery = hrbsession.createQuery("select partNo from CatPart ");
            //  partList = (ArrayList) partQuery.list();

            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END"))) {
                partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                qty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                background="#FFFFFF";

                alternateparts = getAlternateByPartNo(partno, hrbsession, "");
                partNoInCatPart = getPartNoInCatPart(partno, hrbsession, priceListCode);
                partno=alternateparts.equals("")? partno :alternateparts;
                if (!partNoInCatPart.equals("")) {
                    if (partNoInCatPart.split("@@")[1].equals("N") || partNoInCatPart.split("@@")[2].equals("-")) {
                        //alternatePart = invDAO.getAlternatePartNo(partno, hrbSession, "");
                        if (alternateparts.equals("")) {
                            alternateparts = "notservicable";
                        }
                    }
                }
                background=alternateparts.equals("")? "#fffff" :"#F5F5DC";
                String partData = getPriceForOrderByPartNo(partno, hrbsession, priceListCode);
                if ((partData != null) && !partData.equals("")) {
                    inventoryForm inf = new inventoryForm();     //cp.p1,cp.partType,pm.price,cp.np2
                    inf.setPartno(partno.trim());
                    inf.setQty("" + qty);
                    inf.setPart_desc(partData.split("@@")[0]);
                    inf.setPartTypeStr(partData.split("@@")[1]);
                    inf.setUnitValue(partData.split("@@")[2]);
                    inf.setMoq(partData.split("@@")[3]);
                    inf.setService(partData.split("@@")[4]);
                    inf.setAmountPerPrice("" + (Double.parseDouble("" + qty) * Double.parseDouble(partData.split("@@")[2])));
                    totalAmm = totalAmm + (Double.parseDouble("" + qty) * Double.parseDouble(partData.split("@@")[2]));
                    inf.setTotalAmont(totalAmm.toString());
                    invForm.setTotalAmont(totalAmm.toString());
                    inf.setColorCode(background);
                    dataListNew.add(inf);
                    partnoAdded++;
                    column = 0;
                    row++;
                } else {
                    column = 0;
                    row++;
                }
            }

            // System.out.println(" list size is " + dataListNew.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataListNew;
    }

    public inventoryForm saveNewOrderByCart(inventoryForm invForm, String userId, String dealerCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String result = "fail";
        int count = 0;
        //SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        Query query = null;
        try {
            session.beginTransaction();

            String refNo = invForm.getRefNo();
            if (!refNo.contains(dealerCode)) {
                invForm.setResult("SessionMismatch");
            } else {
                if (invForm.getPoNoRadio().equalsIgnoreCase("newPo")) {
                    //String refNo = new MethodUtility().getNumber(session, "SpOrderMaster", invForm.getDealerCode(), "PO");

                    if (!isPONumberExist(session, refNo)) {
                        invForm.setRefNo(refNo);
                        SpOrderMaster spoMas = new SpOrderMaster();

                        spoMas.setCustPoNo(refNo);
                        spoMas.setOrdType(invForm.getOrderType());
                        spoMas.setDeliveryTerms(invForm.getDeliveryCode().split("@@")[0]);
                        spoMas.setCustCode(invForm.getDealerCode());
                        spoMas.setComments(invForm.getSpecInstr());
                        spoMas.setDeliveryAddress(invForm.getDeliveryAdd());

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(invForm.getDeliveryDate());
                        spoMas.setDeliveryDate(date);

                        if (invForm.getOrderType().equalsIgnoreCase("VOR")) {
                            spoMas.setChassisNo(invForm.getChassisNo());
                            spoMas.setEngineNo(invForm.getEngineNo());
                            spoMas.setModelNo(invForm.getModelNo());
                            spoMas.setJobCardNo(invForm.getFirNo());
                        }
                        spoMas.setCustPoDate(new Date());
                        spoMas.setCreatedBy(userId);
                        spoMas.setCreatedOn(new Date());
                        spoMas.setStockistId(invForm.getStockistId());
                        spoMas.setStockistName(invForm.getStockistName());
                        if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                            spoMas.setStatus("OPEN");
                        } else {
                            spoMas.setStatus("PENDING");
                        }
                        spoMas.setTotalValue(Double.parseDouble(invForm.getTotalAmont()));
                        spoMas.setErpUploadStatus("NO");
                        spoMas.setIsServerSync('N');
                        session.save(spoMas);
                        // session.saveOrUpdate(spoMas);

                        // Track processed part numbers to prevent duplicates
                        java.util.Set<String> processedPartNos = new java.util.HashSet<String>();
                        
                        for (int i = 0; i < invForm.getPartNo().length; i++) {

                            if (!invForm.getPartNo()[i].equals("")) {
                                String partNo = invForm.getPartNo()[i].trim().toUpperCase();
                                
                                // Skip if this part number has already been processed
                                if (processedPartNos.contains(partNo)) {
                                    continue;
                                }
                                
                                // Mark this part number as processed
                                processedPartNos.add(partNo);
                                
                                //if (poStatus.get(invForm.getPartNo()[i]) != null && !poStatus.get(invForm.getPartNo()[i]).equalsIgnoreCase("N")) {
                                SpOrderDetails spoDet = new SpOrderDetails();
                                SpOrderDetailsPK spoDetPk = new SpOrderDetailsPK();
                                spoDetPk.setCustPoNo(refNo);
                                spoDetPk.setPartNo(partNo);
                                spoDetPk.setPositionNo(count + 1);

                                spoDet.setSpOrderDetailsPK(spoDetPk);
                                spoDet.setQty(Integer.parseInt(invForm.getQuantity()[i]));
                                spoDet.setPrice(Double.parseDouble(invForm.getUnitprice()[i]));
                                spoDet.setIsServerSync('N');
                                if (!invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                                    spoDet.setErpPendingQty(spoDet.getQty());
                                }
                                spoDet.setColorId(null);
                                session.save(spoDet);
                                //  }
                                //session.saveOrUpdate(spoMas);
                                count++;
                            }

                        }
                    } else {
                        invForm.setResult("poExist");
                    }
                } else {
                    SpOrderMaster spdetforPrePO = (SpOrderMaster) session.load(SpOrderMaster.class, invForm.getRefNo());
                    spdetforPrePO.setOrdType(invForm.getOrderType());
                    spdetforPrePO.setDeliveryTerms(invForm.getDeliveryCode().split("@@")[0]);
                    spdetforPrePO.setCustCode(invForm.getDealerCode());
                    spdetforPrePO.setDeliveryAddress(invForm.getDeliveryAdd());

                    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//                Date date = sdf.parse(invForm.getDeliveryDate());               //prashant
//                spdetforPrePO.setDeliveryDate(date);
                    spdetforPrePO.setComments(invForm.getSpecInstr());

                    if (invForm.getOrderType().equalsIgnoreCase("VOR")) {
                        spdetforPrePO.setChassisNo(invForm.getChassisNo());
                        spdetforPrePO.setEngineNo(invForm.getEngineNo());
                        spdetforPrePO.setModelNo(invForm.getModelNo());
                        spdetforPrePO.setJobCardNo(invForm.getFirNo());     // prashant
                    }
                    spdetforPrePO.setCustPoDate(new Date());
                    spdetforPrePO.setCreatedBy(userId);
                    //spdetforPrePO.setCreatedOn(new Date());
                    spdetforPrePO.setStockistId(invForm.getStockistId());
                    spdetforPrePO.setStockistName(invForm.getStockistName());
                    if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                        spdetforPrePO.setStatus("OPEN");     // when PO No is replaced by Previous PO No
                    } else {
                        spdetforPrePO.setStatus("PENDING");
                    }
                    spdetforPrePO.setTotalValue(Double.parseDouble(invForm.getTotalAmont()));
                    spdetforPrePO.setErpUploadStatus("NO");
                    spdetforPrePO.setIsServerSync('N');
                    session.saveOrUpdate(spdetforPrePO);

                    String hql = "delete from SpOrderDetails where spOrderDetailsPK.custPoNo='" + invForm.getRefNo() + "'";
                    query = session.createQuery(hql);
                    query.executeUpdate();

                    // Track processed part numbers to prevent duplicates
                    java.util.Set<String> processedPartNos = new java.util.HashSet<String>();
                    
                    for (int i = 0; i < invForm.getPartNo().length; i++) {
                        if (!invForm.getPartNo()[i].equals("")) {
                            String partNo = invForm.getPartNo()[i].trim().toUpperCase();
                            
                            // Skip if this part number has already been processed
                            if (processedPartNos.contains(partNo)) {
                                continue;
                            }
                            
                            // Mark this part number as processed
                            processedPartNos.add(partNo);
                            
                            SpOrderDetails spoDet = new SpOrderDetails();
                            SpOrderDetailsPK spoDetPk = new SpOrderDetailsPK();
                            spoDetPk.setCustPoNo(invForm.getRefNo());
                            spoDetPk.setPartNo(partNo);
                            spoDetPk.setPositionNo(count + 1);
                            spoDet.setSpOrderDetailsPK(spoDetPk);
                            spoDet.setQty(Integer.parseInt(invForm.getQuantity()[i]));
                            if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                                spoDet.setStatus("NA"); //Cnahge by Avinash 01-06-2015 CLOSE
                            } else {
                                spoDet.setStatus("NA"); //Cnahge by Avinash 01-06-2015 CLOSE
                                spoDet.setErpPendingQty(spoDet.getQty());
                            }
                            spoDet.setPrice(Double.parseDouble(invForm.getUnitprice()[i]));
                            spoDet.setIsServerSync('N');
                            spoDet.setColorId(null);
                            session.save(spoDet);
                            count++;
                        }
                    }
                }

                if ((invForm.getResult() == null) || (!invForm.getResult().equalsIgnoreCase("poExist"))) {
                    session.getTransaction().commit();
                    invForm.setResult("success");
                }
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invForm;
    }

    public String newOrderExcelValidated(File xlsfile) throws Exception {
        ArrayList<String> part_no = null;


        boolean r = false;


        int row = 0;


        int column = 0;


        int totalrows = 0;
        String result = "failure1", partno = "", qty = "";
        Workbook workbook1 = null;
        Sheet sheet = null;


        try {
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.


            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";


            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'QTY' Missing. Template error.";


            }
            column = 0;
            row++;

            if ((sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, No Records Available.";


            }
            totalrows = (sheet.getRows());


            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                    for (column = 0; column
                            < 3; column++) {

                        if (column == 0) {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (partno.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be blank. ";


                            }
                            if (partno.length() > 50) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be greater than 50 characters.";


                            }
                            if (partno.equals("-") || partno.equals("/")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Please enter valid Part No. ";


                            }
                            r = isRegularExpression(partno);


                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Part No.";



                            }
                            if (part_no.contains(partno)) {
                                return "Error In Row " + (row + 1) + " , Column " + (column + 1) + ". Part No cannot be entered twice.";


                            }
                            if (!part_no.contains(partno)) {
                                part_no.add(partno);


                            }
                        } else if (column == 1) {

                            qty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (qty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".Quantity can not be blank.";


                            } else {
                                r = containsOnlyNumbers(qty);


                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Min Quantity.";


                                }
                            }
                        }
                    }
                } else {
                    result = "success1";


                    break;


                }
                row++;
                column = 0;


            } //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";


            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();


            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";




        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";


            return result;



        }
        return result;


    }

    public String getPriceForOrderByPartNo(String partno, Session hrbSession, String priceListCode) {
        String tempDesc = "";


        try {
//            Query query = hrbSession.createQuery("select cp.p1,cp.partType,pm.orderPrice,cp.np2 , cp.p4 "
//                    + " from CatPart cp left join cp.spPriceMasterList pm "
//                    + " where  cp.partNo=:partNo");   //cp.p4='Y'
            Query query = hrbSession.createQuery("select cp.p1,cp.partType,pm.orderPrice,cp.np2 , cp.p4,cp.partType "
                    + " from CatPart cp left join cp.spPriceMasterList pm "
                    + " where  cp.partNo=:partNo and (pm.spPriceMasterPK.pricelistCode is null or pm.spPriceMasterPK.pricelistCode='" + priceListCode + "') and cp.p4='Y'");   //

            query.setParameter("partNo", partno);
            Iterator itr = query.list().iterator();



            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (o[2] == null ? "" : o[2].toString()) + "@@" + (o[3] == null ? "1" : o[3].toString()) + "@@" + (o[4] == null ? "N" : o[4].toString() + "@@" + (o[5] == null ? "" : o[5].toString()));


            }
        } catch (Exception e) {
            e.printStackTrace();


        }

        return tempDesc;


    }

// ******** view order starts from here  *********
    public ArrayList<inventoryForm> getViewOrderList(inventoryForm iForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        String dateSubQur = "",subquery="";
        ArrayList<String> dealerList = new ArrayList<String>();


        try {
            if (iForm.getNewPoNo() != null && !iForm.getNewPoNo().equals("")) {
                Subsql = " and om.custPoNo like '%" + iForm.getNewPoNo() + "%'  ";


            }
            if (iForm.getOrderType() != null && !iForm.getOrderType().equals("")) {
                if (iForm.getOrderType().equalsIgnoreCase("All")) {
                    Subsql += " ";


                } else {
                    Subsql += " and om.ordType ='" + iForm.getOrderType() + "'  ";


                }
            }
            if (iForm.getStatus() != null && !iForm.getStatus().equals("")) {
                if (iForm.getStatus().equalsIgnoreCase("All")) {
                    Subsql += " ";


                } else {
                    Subsql += " and om.status ='" + iForm.getStatus() + "'  ";


                }
            }
            if ("1".equals(iForm.getRange())) {
                dateSubQur = "  and ( om.custPoDate between isnull(?,om.custPoDate) and isnull(?,om.custPoDate) )  ";


            }
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
                subquery="and :dealerList = (om.dealerCode) ";
            } else {
                subquery="and :dealerList LIKE ('%'+om.dealerCode+'%') ";

            }
            String hql = "Select om.custPoNo,om.ordType,om.custPoDate ,om.status,  om.dealerCode ,count(sd.spOrderDetailsPK.partNo) , "
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=om.dealerCode) as dealerName,(select dm.location from Dealervslocationcode dm where dm.dealerCode=om.dealerCode) as location, "
                    + " om.stockistId,om.stockistName, om.erpOrderNo,om.erpOrderDate,om.erpRemarks from SpOrderMaster om , SpOrderDetails sd where om.custPoNo=sd.spOrderDetailsPK.custPoNo   ";
            Query query = null;

            query = session.createQuery(hql + Subsql + dateSubQur + subquery + " group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.stockistId,om.stockistName , om.erpOrderNo,om.erpOrderDate,om.erpRemarks order by om.custPoNo");


            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
                dealerList.add(iForm.getDealerCode());
                query.setParameter("dealerList", iForm.getDealerCode());

            } else {
                 dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());
                 query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

            }
            
            //query.setParameterList("dealerList", dealerList);

            /*            if (userFunctionalities.contains("101")) {
            hql = hql + Subsql + dateSubQur + " and om.dealerCode='" + iForm.getDealerCode() + "' group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.stockistId,om.stockistName, om.erpOrderNo,om.erpOrderDate,om.erpRemarks  order by om.custPoNo";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + Subsql + dateSubQur + " and om.dealerCode IN(:dealerList) group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.stockistId,om.stockistName , om.erpOrderNo,om.erpOrderDate,om.erpRemarks order by om.custPoNo";
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {   //&& !iForm.getDealerCode().equalsIgnoreCase("ALL")
            dealerList.add(iForm.getDealerCode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + iForm.getUserid() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {   //&& !iForm.getDealerCode().equalsIgnoreCase("ALL")
            hql = hql + Subsql + dateSubQur + " and om.dealerCode='" + iForm.getDealerCode() + "' group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.stockistId,om.stockistName, om.erpOrderNo,om.erpOrderDate,om.erpRemarks  order by om.custPoNo";
            } else {
            hql = hql + Subsql + dateSubQur + " group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.stockistId,om.stockistName, om.erpOrderNo,om.erpOrderDate,om.erpRemarks  order by om.custPoNo";
            }
            query = session.createQuery(hql);
            }
             *
             */


            if ("1".equals(iForm.getRange())) {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));


            }
            List list = query.list();
            System.out.println("query.."+query);
            Iterator itr = list.iterator();


            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setNewPoNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setOrderType(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                invForm.setPoDate(enqobj[2] == null ? "-" : sdf1.format(df.parse(enqobj[2].toString().trim())));
                invForm.setStatus(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                invForm.setDealerCode(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setPartCount(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                invForm.setLocation(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                invForm.setStockistId(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setStockistName(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                invForm.setErpOrderNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                invForm.setErpOrderDate(enqobj[11] == null ? "-" : sdf1.format(df1.parse(enqobj[11].toString().trim())));
                invForm.setErpRemarks(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                orderList.add(invForm);


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return orderList;


    }

    public ArrayList<inventoryForm> getViewOrderDetail(inventoryForm invForm, String custPoNo) {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();

        String hql = "";
        String partHql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


        try {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            hql = "select om.custPoNo,om.ordType,om.custPoDate,om.engineNo," + //om.comments,
                    " om.chassisNo,om.modelNo,om.jobCardNo ,"
                    + " (select dm.deliveryDesc from MSWDeliveryCodeMaster dm where dm.deliveryCode=om.deliveryTerms)as deliveryDesc,om.totalValue," + //om.shipmentLotSingle,  om.deliveryAddress,
                    " om.status ,om.comments ,om.stockistId,om.stockistName,om.deliveryDate,om.erpOrderNo,om.erpOrderDate,om.erpRemarks,om.dealerCode"
                    + " from SpOrderMaster om  "
                    + " where "
                    + " om.custPoNo=:custPoNo";
            Query orderQuery = hrbsession.createQuery(hql);
            orderQuery.setParameter("custPoNo", custPoNo);
            Iterator itr = orderQuery.list().iterator();


            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                invForm.setNewPoNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setOrderType(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                invForm.setPoDate(enqobj[2] == null ? "-" : sdf.format(df.parse(enqobj[2].toString().trim())));
                invForm.setEngineNo(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                invForm.setChassisNo(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setModelNo(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setFirNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                invForm.setDeliveryDesc(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                invForm.setTotalAmont(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setStatus(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                invForm.setSpecInstr(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                invForm.setStockistId(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                invForm.setStockistName(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                invForm.setDeliveryDate(enqobj[13] == null ? "-" : sdf.format(df.parse(enqobj[13].toString().trim())));
                invForm.setErpOrderNo(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                invForm.setErpOrderDate(enqobj[15] == null ? "-" : sdf.format(df.parse(enqobj[15].toString().trim())));
                invForm.setErpRemarks(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                invForm.setDealerCode(enqobj[17] == null ? "-" : enqobj[17].toString().trim());


            }
            if (invForm.getErpOrderNo() != null && invForm.getErpOrderNo().length() != 0 && invForm.getDealerCode() != null && invForm.getDealerCode().length() != 0) {
                invForm.setSpOrderInvoices(getOrderInvoicesList(hrbsession, invForm.getErpOrderNo(), invForm.getDealerCode()));


            }
            partHql = "select sd.spOrderDetailsPK.partNo ,sd.qty,sd.price,sd.status ,(select cp.p1 from CatPart cp where cp.partNo=sd.spOrderDetailsPK.partNo ),sd.erpPendingQty"+
                     " ,sd.colorId,(select color_code  from SpOrderColorMST ocm  where ocm.color_id=sd.colorId)as color_code" +
                      " ,(select ocm.color_text  from SpOrderColorMST ocm  where ocm.color_id=sd.colorId) as color_text " +
                    " from SpOrderDetails sd  where sd.spOrderDetailsPK.custPoNo=:custPoNo ";
            Query partQuery = hrbsession.createQuery(partHql);
            partQuery.setParameter("custPoNo", custPoNo);
            Iterator partItr = partQuery.list().iterator();


            while (partItr.hasNext()) {
                Object enqobj[] = (Object[]) partItr.next();
                inventoryForm inForm = new inventoryForm();
                inForm.setPartno(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                inForm.setQty(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                inForm.setFinalamount(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                inForm.setAmountPerPrice("" + (Double.parseDouble(enqobj[1].toString()) * Double.parseDouble(enqobj[2].toString())));
                inForm.setPartStatus(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                inForm.setPart_desc(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                inForm.setErpPendingQty(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                inForm.setColorId(enqobj[6] == null ? 0 : Integer.parseInt(enqobj[6].toString().trim()));
                inForm.setColorCode(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                inForm.setColorCode(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                inForm.setColorText(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                dataList.add(inForm);


            }

        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return dataList;



    }

    public String getOrderDesc(String poNo, String dealerCode) {
        String vendorDesc = "";
        String hql = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {

            hql = "select om.deliveryTerms,"
                    + " (select dm.deliveryDesc from MSWDeliveryCodeMaster dm where dm.deliveryCode=om.deliveryTerms)as deliveryDesc, om.comments,"
                    + " om.ordType,om.chassisNo,om.modelNo,om.engineNo,om.firNo "
                    + " from SpOrderMaster om where om.custPoNo=:custPoNo";
            Query query = hrbsession.createQuery(hql);
            query.setParameter("custPoNo", poNo);
            //query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();


                if (o[3].toString().equalsIgnoreCase("VOR")) {
                    vendorDesc = (o[0] == null ? "" : o[0].toString()) + "@@"
                            + (o[1] == null ? "" : o[1].toString()) + "@@"
                            + (o[2] == null ? "" : o[2].toString()) + "@@"
                            + (o[3] == null ? "" : o[3].toString()) + "@@"
                            + (o[4] == null ? "" : o[4].toString()) + "@@"
                            + (o[5] == null ? "" : o[5].toString()) + "@@"
                            + (o[6] == null ? "" : o[6].toString()) + "@@"
                            + (o[7] == null ? "" : o[7].toString());


                } else {
                    vendorDesc = (o[0] == null ? "" : o[0].toString()) + "@@"
                            + (o[1] == null ? "" : o[1].toString()) + "@@"
                            + (o[2] == null ? "" : o[2].toString()) + "@@"
                            + (o[3] == null ? "" : o[3].toString());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return vendorDesc;


    }
// ******** view order ends here  *********

    public ArrayList<inventoryForm> profarmaInvoiceCounterSale(inventoryForm invForm, String userId) {
        ArrayList<inventoryForm> partList = new ArrayList<inventoryForm>();


        double percentAmt = 0.0;
        String discountAmmount = "";


        try {
            for (int i = 0; i
                    < invForm.getPartNo().length; i++) {
                percentAmt = 0.0;
                discountAmmount = "0.0";


                if (!invForm.getPartNo()[i].equals("")) {
                    inventoryForm inf = new inventoryForm();
                    inf.setPartno(invForm.getPartNo()[i]);
                    inf.setPart_desc(invForm.getPartDesc()[i]);


                    if (invForm.getPartType()[i].equalsIgnoreCase("PRT")) {
                        inf.setPartTypeStr("SPARES");


                    } else {
                        inf.setPartTypeStr(invForm.getPartType()[i]);


                    }
                    inf.setQty(invForm.getQuantity()[i]);
                    inf.setUnitValue(invForm.getUnitprice()[i]);
                    //Avinash 15-04-2015
                    percentAmt = Double.parseDouble(invForm.getQuantity()[i]) * Double.parseDouble(invForm.getUnitprice()[i]); //calculate   Quantity * Unitprice
                    discountAmmount = "" + ((Double.parseDouble(invForm.getPercentDis()[i]) / 100) * percentAmt);
                    inf.setDiscount(discountAmmount);
                    //END
                    inf.setBillId(invForm.getBillIdArr()[i].split("@@")[2]);
                    inf.setAmountPerPrice(invForm.getAmount()[i]);
                    partList.add(inf);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
        }
        return partList;


    }

//   ******** view order Invoice starts from here  *********
    public ArrayList<inventoryForm> getViewOrderInvoiceList(inventoryForm iForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String Subsql = "";
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();


        try {
            if (iForm.getInvNo() != null && !iForm.getInvNo().equals("")) {
                Subsql = "  spi.spOrderInvoicesPK.invoiceNo like '%" + iForm.getInvNo() + "%'  and ";


            }
            if (iForm.getInvOrderNo() != null && !iForm.getInvOrderNo().equals("")) {
                Subsql += " spi.spOrderInvoicesPK.erpOrderNo like '%" + iForm.getInvOrderNo() + "%' and  ";


            }
            if ("1".equals(iForm.getRange())) {
                dateSubQur = "  ( spi.invoiceDate between isnull(?,spi.invoiceDate) and isnull(?,spi.invoiceDate) ) and ";


            }

            String hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo," + //spi.spOrderInvoicesPK.erpOrderNo,
                    " spi.invoiceDate ,spi.dealerCode ,spi.shipmentDate,spi.lrNo,spi.transporterName,spi.permitNo , "
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,(select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location "
                    + " ,spi.totalInvoiceAmount from SpOrderInvoices spi where spi.status='DISPATCHED' and ";
            Query query = null;

            query = session.createQuery(hql + Subsql + dateSubQur + "  :dealerList LIKE ('%'+spi.dealerCode+'%') order by spi.invoiceDate desc ");


            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
                dealerList.add(iForm.getDealerCode());


            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());


            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*            if (userFunctionalities.contains("101")) {
            hql = hql + Subsql + dateSubQur + "  spi.dealerCode='" + iForm.getDealerCode() + "'   order by spi.invoiceDate desc  ";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + Subsql + dateSubQur + "  spi.dealerCode IN(:dealerList) order by spi.invoiceDate desc ";
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
            dealerList.add(iForm.getDealerCode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + iForm.getUserid() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
            hql = hql + Subsql + dateSubQur + "  spi.dealerCode='" + iForm.getDealerCode() + "'   order by spi.invoiceDate desc ";
            } else {
            if (!Subsql.equals("") || !dateSubQur.equals("")) {
            hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo," //spi.spOrderInvoicesPK.erpOrderNo,
            + " spi.invoiceDate ,spi.dealerCode ,spi.shipmentDate,spi.lrNo,spi.transporterName,spi.permitNo , "
            + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,(select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location "
            + " ,spi.totalInvoiceAmount from SpOrderInvoices spi where " + Subsql + dateSubQur + " order by spi.invoiceDate desc  ";
            } else {
            hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo," //,spi.spOrderInvoicesPK.erpOrderNo
            + " spi.invoiceDate ,spi.dealerCode ,spi.shipmentDate,spi.lrNo,spi.transporterName,spi.permitNo , "
            + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,(select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location "
            + " ,spi.totalInvoiceAmount from SpOrderInvoices spi order by spi.invoiceDate desc ";
            }
            }
            query = session.createQuery(hql);
            }
             *
             */


            if ("1".equals(iForm.getRange())) {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));


            }
            List list = query.list();
            Iterator itr = list.iterator();


            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                // invForm.setInvOrderNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                // invForm.setPartOrderNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                invForm.setInvDate(enqobj[1] == null ? "-" : sdf1.format(df.parse(enqobj[1].toString().trim())));
                invForm.setDealerCode(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                invForm.setShipmentDate(enqobj[3] == null ? "-" : sdf1.format(df.parse(enqobj[3].toString().trim())));
                invForm.setLrNo(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setTransporterName(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setPermitNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                // invForm.setStatus(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setDealerName(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                invForm.setLocation(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setFinalamount(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                orderList.add(invForm);


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return orderList;


    }

    public ArrayList<inventoryForm> getViewOrderInvoiceDetail(inventoryForm invForm, String invNo) {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        String hql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Double qty = 0.0;
        Double amount = 0.0;
        Double totalAmount = 0.0;
        Double amountDouble = 0.0;

        try {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo,spi.spOrderInvoicesPK.erpOrderNo,"
                    + " spi.spOrderInvoicesPK.erpPartOrderNo,spi.invoiceDate ,spi.dealerCode ,"
                    + " spi.shipmentDate,spi.lrNo,spi.transporterName,spi.permitNo ,spi.status ,"
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
                    + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location,"
                    + " spi.totalInvoiceAmount, "
                    + " spi.spOrderInvoicesPK.orderedPart,spi.shippedPartNo,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ),spi.qtyShipped,spi.invoicedRate,spi.spOrderInvoicesPK.erpOrderNo"
                    + " from SpOrderInvoices spi where spi.spOrderInvoicesPK.invoiceNo=:invNo and spi.dealerCode=:dealerCode";
            Query orderQuery = hrbsession.createQuery(hql);
            orderQuery.setParameter("invNo", invNo);
            orderQuery.setParameter("dealerCode", invForm.getDealerCode());
            Iterator itr = orderQuery.list().iterator();


            boolean isHeaderPopulated = false;


            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();


                if (!isHeaderPopulated) {
                    invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                    invForm.setInvOrderNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                    invForm.setPartOrderNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                    invForm.setInvDate(enqobj[3] == null ? "-" : sdf.format(df.parse(enqobj[3].toString().trim())));
                    invForm.setDealerCode(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                    invForm.setShipmentDate(enqobj[5] == null ? "-" : sdf.format(df.parse(enqobj[5].toString().trim())));
                    invForm.setLrNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                    invForm.setTransporterName(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                    invForm.setPermitNo(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                    invForm.setStatus(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                    invForm.setDealerName(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                    invForm.setLocation(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                    invForm.setFinalamount(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                    isHeaderPopulated = true;


                }
                inventoryForm inForm = new inventoryForm();
                inForm.setPartno(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                inForm.setOrderedPartNo(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                inForm.setPart_desc(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                inForm.setQty(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                inForm.setUnitValue(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
                inForm.setErpOrderNo(enqobj[18] == null ? "-" : enqobj[18].toString().trim());
                qty = Double.parseDouble(enqobj[16].toString());
                amount = Double.parseDouble(enqobj[17].toString());
                amountDouble = Double.parseDouble("" + qty * amount);
                inForm.setAmountPerPrice("" + amountDouble);
                totalAmount = totalAmount + qty * amount;
                invForm.setTotalAmont("" + totalAmount);
                dataList.add(inForm);


            } //            partHql = "select spi.shippedPartNo,spi.orderedPart,spi.shippedPartNo," +
            //                    "(select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ),spi.qtyShipped,spi.invoicedRate," +
            //                    " spi.totalInvoiceAmount, (spi.qtyShipped * spi.invoicedRate),"
            //                    + " spi.totalInvoiceAmount,spi.spOrderInvoicesPK.erpOrderNo "
            //                    + " from SpOrderInvoices spi  where spi.spOrderInvoicesPK.invoiceNo=:invNo ";
            //            Query partQuery = hrbsession.createQuery(partHql);
            //            partQuery.setParameter("invNo", invNo);
            //            Iterator partItr = partQuery.list().iterator();
            //            while (partItr.hasNext()) {
            //                Object enqobj[] = (Object[]) partItr.next();
            //                inventoryForm inForm = new inventoryForm();
            //                inForm.setPartno(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
            //                inForm.setQty(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
            //                inForm.setUnitValue(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
            //                inForm.setAmountPerPrice("" + (Double.parseDouble(enqobj[1].toString()) * Double.parseDouble(enqobj[2].toString())));
            //                inForm.setPartStatus(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
            //                inForm.setPart_desc(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
            //                inForm.setFinalamount(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
            //                inForm.setInvOrderNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
            //                dataList.add(inForm);
            //            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return dataList;



    }

    public void getPendigInvoiceList(inventoryForm inForm) {
        String sql = "";
        inventoryForm invForm = null;
        Session session = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            sql = "select distinct spi.spOrderInvoicesPK.invoiceNo from SpOrderInvoices spi "
                    + " where not exists (select gr.invoiceNo from SpOrderInvGrn gr where gr.dealerCode=spi.dealerCode and gr.invoiceNo = spi.spOrderInvoicesPK.invoiceNo)"
                    + " and spi.dealerCode=:dealerCode ";
            Query query = session.createQuery(sql);
            query.setParameter("dealerCode", inForm.getDealerCode());
            List<String> list = (List<String>) query.list();
            Iterator it = list.iterator();


            while (it.hasNext()) {
                String enqobj = (String) it.next();
                invForm = new inventoryForm();


                if (enqobj != null && !enqobj.equals("0")) {
                    invForm.setInvNo(enqobj.toString().trim());
                    dataList.add(invForm);


                }

            }
            inForm.setPenInviceList(dataList);


        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
    }

    public void getInvoiceData(inventoryForm inForm) {
        String hql = "";
        String parthql = "";
        Session session = null;
        inventoryForm invForm = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            hql = "select spi.spOrderInvoicesPK.invoiceNo,spi.invoiceDate from SpOrderInvoices spi "
                    + "where spi.dealerCode=:dealerCode and spi.spOrderInvoicesPK.invoiceNo=:invNo";
            Query query = session.createQuery(hql);
            query.setParameter("dealerCode", inForm.getDealerCode());
            query.setParameter("invNo", inForm.getInvNo());
            List list = query.list();
            Iterator itr = list.iterator();


            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                inForm.setInvNo(obj[0] == null ? "" : obj[0].toString().trim());
                inForm.setInvDate(obj[1] == null ? "" : obj[1].toString().equals("") == true ? "" : sdf.format(df.parse(obj[1].toString().trim())));


            }
            parthql = "select distinct spi.shippedPartNo,(select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ) ,"
                    + " sum(spi.qtyShipped) ,spi.invoicedRate, "
                    + " (select cp.np1 from CatPart cp where cp.partNo=spi.shippedPartNo ) "
                    //+ " ,spi.status, spi.spOrderInvoicesPK.erpOrderNo "
                    + " from SpOrderInvoices spi where spi.spOrderInvoicesPK.invoiceNo=:invNo and spi.dealerCode=:dealerCode group by spi.shippedPartNo,spi.invoicedRate";
            Query partquery = session.createQuery(parthql);
            partquery.setParameter("invNo", inForm.getInvNo());
            partquery.setParameter("dealerCode", inForm.getDealerCode());
            Iterator partItr = partquery.list().iterator();


            while (partItr.hasNext()) {
                Object enqobj[] = (Object[]) partItr.next();
                invForm = new inventoryForm();
                invForm.setPartno(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setPart_desc(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                invForm.setQty(enqobj[2] == null ? "-" : MethodUtility.checkPoint(enqobj[2].toString().trim()));
                invForm.setUnitValue(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                invForm.setNp1(enqobj[4] == null ? 1 : Integer.parseInt(enqobj[4].toString()));
                //invForm.setInvOrderNo(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                dataList.add(invForm);


            }
            inForm.setGrnPartList(dataList);


        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
    }

    public inventoryForm saveGRN(inventoryForm inForm) throws SQLException {
        Session session = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Boolean flag = false;


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List partNo = new ArrayList<String>();
            List partDesc = new ArrayList<String>();
            List unitprice = new ArrayList<String>();
            List qty = new ArrayList<String>();
            List receivedQty = new ArrayList<String>();
            List np1Array = new ArrayList<String>();


            Double unitP = null;
            Double qantity = null;
            Double recdQantity = null;

            if (inForm.getReceivedQty() != null) {
                for (int i = 0; i < inForm.getReceivedQty().length; i++) {

                    if (!partNo.contains(inForm.getPartNo()[i])) {
                        partNo.add(inForm.getPartNo()[i]);
                        partDesc.add(inForm.getPartDesc()[i]);
                        unitprice.add(inForm.getUnitprice()[i]);
                        qty.add(inForm.getQuantity()[i]);
                        receivedQty.add(inForm.getReceivedQty()[i]);
                        np1Array.add(inForm.getNp1Array()[i]);
                    } else {
                        qantity = Double.parseDouble("" + qty.get(partNo.indexOf(inForm.getPartNo()[i])));
                        unitP = Double.parseDouble("" + unitprice.get(partNo.indexOf(inForm.getPartNo()[i])));

                       // System.out.println("unitP "+unitP);
                       // System.out.println("qantity "+qantity);


                        recdQantity = Double.parseDouble("" + receivedQty.get(partNo.indexOf(inForm.getPartNo()[i])));

                        unitP = ((qantity * unitP) + (Double.parseDouble(inForm.getQuantity()[i]) * Double.parseDouble(inForm.getUnitprice()[i]))) / (Double.parseDouble(inForm.getQuantity()[i]) + qantity);


                          //   System.out.println("New Qty "+Double.parseDouble(inForm.getQuantity()[i]));
                           //        System.out.println("New Price "+Double.parseDouble(inForm.getUnitprice()[i]));
                           //   System.out.println("Final unitP "+unitP);
                        
                        qantity = Double.parseDouble(inForm.getQuantity()[i]) + qantity;
                        recdQantity = Double.parseDouble(inForm.getReceivedQty()[i]) + recdQantity;

                        qty.set(partNo.indexOf(inForm.getPartNo()[i]), "" + qantity);
                        unitprice.set(partNo.indexOf(inForm.getPartNo()[i]), "" + unitP);
                        receivedQty.set(partNo.indexOf(inForm.getPartNo()[i]), "" + recdQantity);

                    }
                }
            }

            String grnNo = new MethodUtility().getNumber(session, "SpOrderInvGrn", inForm.getDealerCode(), "GRN");
            inForm.setGrnNo(grnNo);
            SpOrderInvGrn invGrn = new SpOrderInvGrn();
            invGrn.setGrNo(grnNo);
            invGrn.setGrDate(new Date(new java.util.Date().getTime()));
            invGrn.setInvoiceNo(inForm.getInvNo());
            invGrn.setInvoiceDate(df.parse(inForm.getInvDate()));
            invGrn.setReceivedBy(inForm.getReceivedBy());
            invGrn.setReceivedOn(df.parse(inForm.getReceivedOn()));
            invGrn.setCreatedBy(inForm.getDealerCode());
            invGrn.setDealerCode(inForm.getDealerCode());
            invGrn.setCreatedOn(new Date(new java.util.Date().getTime()));
            session.save(invGrn);
            SpOrderInvGrnDetails invGrnDet = new SpOrderInvGrnDetails();
            SpOrderInvGrnDetailsPK grnDetPk = new SpOrderInvGrnDetailsPK();


            if (inForm.getReceivedQty() != null) {
                for (int i = 0; i < partNo.size(); i++) {
                    grnDetPk.setGrNo(grnNo);
                    grnDetPk.setPartno(partNo.get(i).toString());
                    invGrnDet.setSpOrderInvGrnDetailsPK(grnDetPk);
                    invGrnDet.setPartdesc(partDesc.get(i).toString());
                    invGrnDet.setUnitvalue(Double.parseDouble(unitprice.get(i).toString()));
                    invGrnDet.setInvoiceQty(Double.parseDouble(qty.get(i).toString()));
                    invGrnDet.setReceivedQty(Double.parseDouble(receivedQty.get(i).toString()));
                    session.save(invGrnDet);
                    MethodUtility.inventoryLedgerEntry(inForm.getUserid(), inForm.getDealerCode(), partNo.get(i).toString(), (Double.parseDouble(receivedQty.get(i).toString()) * Double.parseDouble(np1Array.get(i).toString())), "Part GRN", session);


                    //if (i % 2 == 0) {
                        session.flush();
                        session.clear();
                   // }
                }
            }
            session.getTransaction().commit();
            inForm.setResult("success");



        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return inForm;


    }

    public String getCustPoNo(String CUST_PO_NO) {             //prashant
        String vendorDesc = "";
        StringBuilder sb = new StringBuilder();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            Query query = hrbsession.createQuery("select om.jobCardNo,om.chassisNo,om.modelNo,om.engineNo,om.deliveryAddress,om.deliveryDate,om.comments,om.deliveryTerms,om.ordType"
                    + " from SpOrderMaster om where om.custPoNo=:custPoNo");

            query.setParameter("custPoNo", CUST_PO_NO);


            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();


                if (obj != null) {
                    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    //vendorDesc=obj[0].toString()+"@@"+obj[1].toString();
                    sb.append(obj[0]);
                    sb.append("@@" + obj[1]);
                    sb.append("@@" + obj[2]);
                    sb.append("@@" + obj[3]);
                    sb.append("@@" + obj[4]);


                    if (obj[5] != null) {
                        sb.append("@@" + sdf.format(sdf1.parse(obj[5].toString())));


                    }
                    sb.append("@@" + obj[6]);
                    sb.append("@@" + obj[7]);
                    sb.append("@@" + obj[8]);

                    //vendorDesc = obj[0].toString() + "@@" + obj[1].toString() + "@@" + obj[2].toString() + "@@" + obj[3].toString() + "@@" + obj[4].toString() + "@@" + sdf.format(sdf1.parse(obj[5].toString()));
                    //  System.out.println("vendorDesc=" + vendorDesc);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return sb.toString();


    }

    public String getNewPo(String dealerCode) {             //prashant
        String vendorDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {

            Query query = hrbsession.createQuery("select dm.address from Dealervslocationcode dm "
                    + " where dm.dealerCode=:dealerCode");

            query.setParameter("dealerCode", dealerCode);
            vendorDesc = query.list().get(0) == null ? "" : query.list().get(0).toString();



        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return vendorDesc;


    }

    public String getDealAddress(String dealerCode) {
        String vendorDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            Query query = hrbsession.createQuery("select dm.address from Dealervslocationcode dm "
                    + " where dm.dealerCode=:dealerCode ");
            query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object o = (Object[]) itr.next();
                vendorDesc = vendorDesc + "@@" + o.toString();


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return vendorDesc;


    }

    public String getChassisDetails(String vinNo, String dealerCode) {
        String vendorDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            Query query = hrbsession.createQuery("select vc.modelCode,vc.engineNo " //vc.vinNo,
                    + " from Vehicledetails vc "
                    + " where vc.vinNo=:vinNo and vc.dealerCode=:dealerCode ");

            query.setParameter("vinNo", vinNo);
            query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                vendorDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString());


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return vendorDesc;


    }

    public String getJobCardDetails(String jobCardNo, String dealerCode) {
        String jobcard = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            Query query = hrbsession.createQuery("select vc.vinNo, vc.modelCode, vc.engineNo,jcd.jobCardNo,jcd.status" //,jcd.jobCardNo
                    + " from Vehicledetails vc, Jobcarddetails jcd where vc.vinid=jcd.vinid "
                    + " and jcd.jobCardNo=:jobCardNo and vc.dealerCode=:dealerCode");

            query.setParameter("jobCardNo", jobCardNo);
            query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                jobcard = (o[0] == null ? "" : o[0].toString()) + "@@"
                        + (o[1] == null ? "" : o[1].toString()) + "@@"
                        + (o[2] == null ? "" : o[2].toString()) + "@@"
                        + (o[4] == null ? "" : o[4].toString());


            }

        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return jobcard;


    } //    public String duplicate(String jobCardNo) {
    //        String dupli = "";
    //        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
    //        try {
    //            Query query = hrbsession.createQuery("select dm.address from Dealervslocationcode dmselect vc.modelCode,vc.engineNo " //vc.vinNo,
    //                    + " from Vehicledetails vc "
    //                    + " where vc.vinNo=:vinNo and vc.dealerCode=:dealerCode ");
    //
    //            query.setParameter("vinNo", vinNo);
    //            query.setParameter("dealerCode", dealerCode);
    //            Iterator itr = query.list().iterator();
    //            if (itr.hasNext()) {
    //                Object o[] = (Object[]) itr.next();
    //                vendorDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString());
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        } finally {
    //            try {
    //                if (hrbsession != null) {
    //                    hrbsession.close();
    //                    hrbsession = null;
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return vendorDesc;
    //    }

    private boolean isPONumberExist(Session hrbSession, String refNo) {
        String query = "select a.custPoNo from SpOrderMaster a where a.custPoNo=:poNo";


        boolean poNumberStatus = true;



        try {

            Query hrbQuery = hrbSession.createQuery(query);
            hrbQuery.setParameter("poNo", refNo);
            Collection result = hrbQuery.list();


            if ((result == null) || (result.size() == 0)) {
                poNumberStatus = false;


            } else {
                poNumberStatus = true;


            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return poNumberStatus;


    }

//    public JSONArray validatePartNumber(String poNumber[]) {
//        Session hSession = HibernateUtil.getSessionFactory().openSession();
//        List poNumberList = Arrays.asList(poNumber);
//        JSONArray rows = new JSONArray();
//        JSONObject row = null;
////        Map<String, String> partNoStatusContainer = new HashMap<String, String>();
//        String query = "select a.partNo,a.p4 from CatPart a where a.partNo in (:partNo)";
//        try {
//            Query hQuery = hSession.createQuery(query);
//            hQuery.setParameterList("partNo", poNumberList);
//            Iterator iterator = hQuery.list().iterator();
//            while (iterator.hasNext()) {
//                row = new JSONObject();
//                Object o[] = (Object[]) iterator.next();
//                if (o[1] != null) {
//                        row.put(o[0].toString(), o[1].toString());
//                        //partNoStatusContainer.put(o[0].toString(), o[1].toString());
//                } else {
//                        row.put(o[0].toString(), "N");
//                        //partNoStatusContainer.put(o[0].toString(), "N");
//                      }
//                rows.add(row);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                if (hSession != null) {
//                    hSession.close();
//                    hSession = null;
//                }
//            } catch (Exception e) {
//            e.printStackTrace();
//            }
//        }
//        return rows;
//    }
    public List<String[]> getOrderInvoicesList(Session hSession, String erpOrderNo, String dealerCode) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String query = "select a.spOrderInvoicesPK.invoiceNo, a.invoiceDate,a.shippedPartNo,a.qtyShipped,a.totalInvoiceAmount,a.lrNo,"
                + "a.shipmentDate,a.transporterName,a.permitNo,(select b.p1 from CatPart b where b.partNo = a.shippedPartNo) as p1,a.spOrderInvoicesPK.orderedPart,a.status,a.invoicedRate,a.dealerAcceptance, a.remarks from SpOrderInvoices a where a.spOrderInvoicesPK.erpOrderNo=:orderNo and a.dealerCode=:dealerCode";

//        String query = "select a.spOrderInvoicesPK.invoiceNo,a.invoiceDate,a.shippedPartNo,a.qtyShipped,a.totalInvoiceAmount,a.lrNo,"+
//              "a.shipmentDate,a.transporterName,a.permitNo from SpOrderInvoices a left join CatPart b ON  (a.shippedPartNo = b.partNo)  where  a.spOrderInvoicesPK.erpOrderNo=:orderNo";

        //String query = " SELECT a.INVOICE_NO,a.INVOICE_DATE,a.ORDERED_PART,a.SHIPPED_PART_NO,b.p1,a.QTY_SHIPPED,a.LR_NO,a.SHIPMENT_DATE,a.TRANSPORTER_NAME,a.PERMIT_NO FROM SP_ORDER_INVOICES a "+
        //        " LEFT OUTER JOIN CAT_PART b ON a.SHIPPED_PART_NO = b.part_no where a.ERP_ORDER_NO ='"+erpOrderNo+"'";
        List<String[]> orderInvoiceList = new ArrayList<String[]>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        try {
            Query hQuery = hSession.createQuery(query);//.addEntity(InvoicePartDetail.class
            hQuery.setParameter("orderNo", erpOrderNo);
            hQuery.setParameter("dealerCode", dealerCode);
            String[] result = new String[15];
            //  hQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            Iterator iterator = hQuery.list().iterator();


            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                result = new String[15];
                result[

0] = o[0] == null ? "-" : o[0].toString();
                result[

1] = o[1] == null ? "-" : (sdf.format(o[1])).toString();
                result[

2] = o[2] == null ? "-" : o[2].toString();
                result[

3] = o[3] == null ? "-" : o[3].toString();
                result[

4] = o[4] == null ? "-" : o[4].toString();
                result[

5] = o[5] == null ? "-" : o[5].toString();
                result[

6] = o[6] == null ? "-" : (sdf.format(o[6])).toString();
                result[

7] = o[7] == null ? "-" : o[7].toString();
                result[

8] = o[8] == null ? "-" : o[8].toString();
                result[

9] = o[9] == null ? "-" : o[9].toString();
                result[

10] = o[10] == null ? "-" : o[10].toString();
                result[

11] = o[11] == null ? "-" : o[11].toString();


                if (o[12].toString() != null && o[12].toString().trim().length() > 0) {
                    if (o[12].toString().indexOf(".") != -1) {
                        if (Integer.parseInt(o[12].toString().substring(o[12].toString().indexOf(".") + 1)) == 0) {
                            o[12] = o[12].toString().substring(0, o[12].toString().indexOf("."));


                        } else {
                            o[12] = decimalFormat.format(o[12]);


                        }
                    }
                }
                result[12] = o[12] == null ? "-" : o[12].toString();
                result[

13] = o[13] == null ? "-" : o[13].toString();
                result[

14] = o[14] == null ? "-" : o[14].toString();
                orderInvoiceList.add(result);


            }

        } catch (Exception e) {
            e.printStackTrace();


        }
        return orderInvoiceList;


    }

    public String getDeliveryTerms(String deliveryCode, Session hrbSession) {
        String deliveryDesc = "";


        try {
            Query query = hrbSession.createQuery("select cp.deliveryDesc  from MSWDeliveryCodeMaster cp  where  cp.deliveryCode=:deliveryCode");   //cp.p4='Y'
            query.setParameter("deliveryCode", deliveryCode);
            deliveryDesc = query.list().get(0).toString();


        } catch (Exception e) {
            e.printStackTrace();


        }
        return deliveryDesc;


    }

    public List<inventoryForm> getBackOrderList(String dealerCode, inventoryForm iForm,String user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<inventoryForm> pendingQtyList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        inventoryForm invForm = null;
        Double grandTotal = 0.00;
        ArrayList al = new ArrayList();
        String sQry = "";
        if (!iForm.getOrderType().equals("ALL") && !iForm.getOrderType().equals("")) {
            sQry += " and a.ORD_TYPE=?";
        }
        if (!iForm.getStatus().equals("ALL") && !iForm.getStatus().equals("")) {
            sQry += " and a.status=?";
        }

        if ("1".equals(iForm.getRange())) {
            sQry += " and (a.CUST_PO_DATE between isnull(?,a.CUST_PO_DATE) and isnull(?,a.CUST_PO_DATE) ) ";
        }

//        String query = "select (select dealerName from Dealervslocationcode d where d.dealerCode=a.dealerCode )as DealerName,a.custPoNo,a.custPoDate,a.ordType,a.erpOrderNo,"
//                + "b.spOrderDetailsPK.partNo,(select c.p1 from CatPart c where c.partNo = b.spOrderDetailsPK.partNo),b.qty,b.erpPendingQty,b.price "
//                + "from SpOrderMaster a,SpOrderDetails b "
//                + "where a.custPoNo = b.spOrderDetailsPK.custPoNo and b.erpPendingQty <>0 and b.erpPendingQty <>'' and a.dealerCode=:dealerCode";
        String query = "  select (select dealerName from UM_DealerMaster d where d.dealerCode=a.DEALER_CODE )as DealerName,a.CUST_PO_NO,a.CUST_PO_DATE,a.ORD_TYPE,a.ERP_ORDER_NO,"
                + " b.PART_NO,(select c.p1 from CAT_PART c where c.part_no = b.PART_NO) as p1,b.qty,b.ERP_PENDING_QTY,b.price,a.status,a.JOB_CARD_NO,jd.jobCardDate "
                + " from SP_ORDER_MASTER a inner join SP_ORDER_DETAILS b on  a.CUST_PO_NO = b.CUST_PO_NO"
                + " left join SW_jobcarddetails jd on a.JOB_CARD_NO=jd.jobCardNo"
                + " where  b.ERP_PENDING_QTY <>0 and b.ERP_PENDING_QTY <>'' and a.DEALER_CODE=? "+sQry;


        try {
            if (dealerCode.equals("ALL")) {
                //String q = "select dealerCode from Dealervslocationcode";
                String q = "Select dealer_code from FN_GetDealersDetailsUnderUser('"+user_id+"') order by DEALER_NAME";
                Query qry = session.createSQLQuery(q).addScalar("dealer_code", StringType.INSTANCE);
                Iterator it = qry.list().iterator();
                int i = 0;
                while (it.hasNext()) {
                    al.add(it.next().toString());
                    i++;
                }

            } else {
                al.add(dealerCode);

            }
            Query hPenQtyQuery = session.createSQLQuery(query).addScalar("DealerName", StringType.INSTANCE).addScalar("CUST_PO_NO", StringType.INSTANCE)
                    .addScalar("CUST_PO_DATE", DateType.INSTANCE).addScalar("ORD_TYPE", StringType.INSTANCE).addScalar("ERP_ORDER_NO", StringType.INSTANCE)
                    .addScalar("PART_NO",StringType.INSTANCE).addScalar("p1",StringType.INSTANCE).addScalar("qty",IntegerType.INSTANCE).addScalar("ERP_PENDING_QTY",IntegerType.INSTANCE)
                    .addScalar("price",FloatType.INSTANCE).addScalar("status",StringType.INSTANCE).addScalar("JOB_CARD_NO",StringType.INSTANCE).addScalar("jobCardDate",DateType.INSTANCE);


            for (int i = 0; i < al.size(); i++) {
                int cnt = 0;
                hPenQtyQuery.setString(cnt++, al.get(i).toString());
                if (!iForm.getOrderType().equals("ALL") && !iForm.getOrderType().equals("")) {
                    hPenQtyQuery.setString(cnt++, iForm.getOrderType());
                }
                if (!iForm.getStatus().equals("ALL") && !iForm.getStatus().equals("")) {
                    hPenQtyQuery.setString(cnt++, iForm.getStatus());
                }
                if ("1".equals(iForm.getRange())) {
                    hPenQtyQuery.setString(cnt++, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                    hPenQtyQuery.setString(cnt++, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
                }
                Iterator iterator = hPenQtyQuery.list().iterator();



                if (iterator.hasNext()) {
                    do {
                        invForm = new inventoryForm();
                        Object[] object = (Object[]) iterator.next();
                        invForm.setDealerName(object[0] == null ? "-" : object[0].toString());
                        invForm.setRefNo(object[1] == null ? "-" : object[1].toString());
                        invForm.setCustomPoDate(object[2] == null ? "-" : dateFormate.format(object[2]).toString());
                        invForm.setOrderType(object[3] == null ? "-" : object[3].toString());
                        invForm.setErpOrderNo(object[4] == null ? "-" : object[4].toString());
                        invForm.setPartno(object[5] == null ? "-" : object[5].toString());
                        invForm.setPart_desc(object[6] == null ? "-" : object[6].toString());
                        invForm.setQty(object[7] == null ? "-" : object[7].toString());
                        invForm.setErpPendingQty(object[8] == null ? "-" : object[8].toString());
                        invForm.setPrice(object[9] == null ? "-" : object[9].toString());
                        invForm.setStatus(object[10] == null ? "-" : object[10].toString());
                        invForm.setJobCardNo(object[11] == null ? "-" : object[11].toString());
                        invForm.setJobCardDate(object[12] == null ? "-" :  dateFormate.format(object[12]).toString());
                        invForm.setFinalamount(decimalFormat.format(Integer.parseInt(object[8].toString()) * Double.parseDouble(object[9].toString())));
                        grandTotal = grandTotal + Double.parseDouble(invForm.getFinalamount());
                        invForm.setDealerCode(al.get(i).toString());
                        pendingQtyList.add(invForm);


                    } while (iterator.hasNext());
                    invForm.setTotalAmont("" + decimalFormat.format(grandTotal));
                    grandTotal = 0.00;


                }


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return pendingQtyList;



    }

    public ArrayList<inventoryForm> getBillingDetailsExport(inventoryForm iForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Double qty = 0.0;
        Double amount = 0.0;
        Double totalAmount = 0.0;
        String Subsql = "";


        try {
            if (iForm.getInvNo() != null && !iForm.getInvNo().equals("")) {
                Subsql = " and spi.spOrderInvoicesPK.invoiceNo like '%" + iForm.getInvNo() + "%'  ";


            }
            if (iForm.getInvOrderNo() != null && !iForm.getInvOrderNo().equals("")) {
                Subsql += " and spi.spOrderInvoicesPK.erpOrderNo like '%" + iForm.getInvOrderNo() + "%'  ";


            }
            if ("1".equals(iForm.getRange())) {
                Subsql += " and (spi.invoiceDate between isnull(?,spi.invoiceDate) and isnull(?,spi.invoiceDate) ) ";



            }

            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
                Subsql += " and spi.dealerCode='" + iForm.getDealerCode() + "'";


            }

            String hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo,spi.invoiceDate ,spi.totalInvoiceAmount,spi.shipmentDate , spi.dealerCode ,"
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
                    + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location ,"
                    + " som.erpOrderDate,spi.transporterName,"
                    + " spi.lrNo,spi.permitNo,"
                    + " spi.spOrderInvoicesPK.erpOrderNo,som.ordType,spi.spOrderInvoicesPK.orderedPart,"
                    + " (select cp.partType from CatPart cp where cp.partNo=spi.shippedPartNo ) as parttype ,"
                    + " spi.shippedPartNo,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ) as partdesc,spi.qtyShipped,"
                    + " spi.invoicedRate"
                    + " from SpOrderInvoices spi ,SpOrderMaster som where som.erpOrderNo=spi.spOrderInvoicesPK.erpOrderNo ";

            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());

            hql = hql + "  and :dealerList LIKE ('%'+spi.dealerCode+'%') and spi.status='DISPATCHED' " + Subsql + "   order by spi.invoiceDate desc ";
            Query query = session.createQuery(hql);


            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
                dealerList.add(iForm.getDealerCode());


            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*  if (userFunctionalities.contains("101")) {
            hql = hql + Subsql + dateSubQur + "  spi.dealerCode='" + iForm.getDealerCode() + "'   order by spi.invoiceDate desc  ";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + Subsql + dateSubQur + "  spi.dealerCode IN(:dealerList) order by spi.invoiceDate desc ";
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
            dealerList.add(iForm.getDealerCode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + iForm.getUserid() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {
            hql = hql + Subsql + dateSubQur + "  and spi.dealerCode='" + iForm.getDealerCode() + "'   order by spi.invoiceDate desc ";
            } else {
            if (!Subsql.equals("") || !dateSubQur.equals("")) {
            hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo,spi.invoiceDate ,spi.totalInvoiceAmount,spi.shipmentDate , spi.dealerCode ,"
            + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
            + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location ,"
            + " som.erpOrderDate,spi.transporterName,"
            + " spi.lrNo,spi.permitNo,"
            + " spi.spOrderInvoicesPK.erpOrderNo,som.ordType,spi.spOrderInvoicesPK.orderedPart,"
            + " (select cp.partType from CatPart cp where cp.partNo=spi.shippedPartNo ) as parttype ,"
            + " spi.shippedPartNo,"
            + " (select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ) as partdesc,spi.qtyShipped,"
            + " spi.invoicedRate"
            + " from SpOrderInvoices spi ,SpOrderMaster som where som.erpOrderNo=spi.spOrderInvoicesPK.erpOrderNo"
            + " " + Subsql + dateSubQur + " order by spi.invoiceDate desc";

            } else {
            hql = "Select distinct spi.spOrderInvoicesPK.invoiceNo,spi.invoiceDate ,spi.totalInvoiceAmount,spi.shipmentDate , spi.dealerCode ,"
            + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
            + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location ,"
            + " som.erpOrderDate,spi.transporterName,"
            + " spi.lrNo,spi.permitNo,"
            + " spi.spOrderInvoicesPK.erpOrderNo,som.ordType,spi.spOrderInvoicesPK.orderedPart,"
            + " (select cp.partType from CatPart cp where cp.partNo=spi.shippedPartNo ) as parttype ,"
            + " spi.shippedPartNo,"
            + " (select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo ) as partdesc,spi.qtyShipped,"
            + " spi.invoicedRate"
            + " from SpOrderInvoices spi ,SpOrderMaster som where som.erpOrderNo=spi.spOrderInvoicesPK.erpOrderNo";
            }
            }
            query = session.createQuery(hql);


            }
             */
            //System.out.println(hql);


            if ("1".equals(iForm.getRange())) {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));


            }
            List list = query.list();
            Iterator itr = list.iterator();


            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setInvDate(enqobj[1] == null ? "-" : sdf1.format(df.parse(enqobj[1].toString().trim())));
                invForm.setFinalamount(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                invForm.setShipmentDate(enqobj[3] == null ? "-" : sdf1.format(df.parse(enqobj[3].toString().trim())));
                invForm.setDealerCode(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setDealerName(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setLocation(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                invForm.setErpOrderDate(enqobj[7] == null ? "-" : sdf1.format(df.parse(enqobj[7].toString().trim())));
                invForm.setTransporterName(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setLrNo(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                invForm.setPermitNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                invForm.setErpOrderNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                invForm.setOrderType(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                invForm.setOrderedPartNo(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                invForm.setPartTypeStr(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                invForm.setShippedPartNo(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                invForm.setPartDescription(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                invForm.setQty(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
                invForm.setUnitValue(enqobj[18] == null ? "-" : enqobj[18].toString().trim());
                qty = Double.parseDouble(enqobj[17].toString());
                amount = Double.parseDouble(enqobj[18].toString());
                invForm.setAmountPerPrice("" + qty * amount);
                totalAmount = totalAmount + qty * amount;
                invForm.setTotalAmont("" + totalAmount);
                orderList.add(invForm);


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return orderList;


    }

    public String getSellingPercentageByPartNo(String partno, String dealerCode, String priceListCode, Double sellingPercentage) {
        String tempDesc = "", unitPrice = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            // Query query = hrbsession.createQuery("select cp.p1, cp.partType,pm.price ,sp.currentQty "
//                    + " from CatPart cp ,SpCurrentInventoryView sp "
//                    + " left join cp.spPriceMasterList pm   "
//                    + " where sp.partNo=cp.partNo and cp.partNo=:partNo and sp.dealerCode=:dealerCode ");
            //Avinash pandey Domestic/Export logic
           /* Query query = hrbsession.createQuery("select cp.p1, cp.partType,pm.price ,sp.currentQty "
            + " from CatPart cp ,SpCurrentInventoryView sp "
            + " left join cp.spPriceMasterList pm   "
            + " where sp.partNo=cp.partNo and cp.partNo=:partNo and sp.dealerCode=:dealerCode and (sp.pricelistCode is null or sp.pricelistCode='" + priceListCode + "') ");
             */

            Query query = hrbsession.createQuery("select cp.p1, cp.partType,sp.mrp ,sp.currentQty ,cp.np8"
                    + " from CatPart cp ,SpCurrentInventoryView sp "
                    // + " left join cp.spPriceMasterList pm   "
                    + " where sp.partNo=cp.partNo and cp.partNo=:partNo and sp.dealerCode=:dealerCode and  sp.pricelistCode='" + priceListCode + "' ");


            query.setParameter("partNo", partno);
            query.setParameter("dealerCode", dealerCode);
            Iterator itr = query.list().iterator();


            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                sellingPercentage = ((sellingPercentage / 100) * Double.parseDouble(o[2].toString()));
                unitPrice = (sellingPercentage + Double.parseDouble(o[2].toString())) + "";
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (unitPrice == null ? "" : unitPrice) + "@@" + (o[3] == null ? "" : o[3].toString())+ "@@" + (o[4] == null ? "" : o[4].toString());


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return tempDesc;


    }

    public static String isStockExcelValidated(File xlsfile, Connection conn) throws Exception {

        boolean r = false;


        int row = 0;


        int column = 0;


        int totalrows = 0;
        String result = "failure1", partno = "", qty = "", salePur = "", unitPrice = "", remarks = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Session session = null;


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);


            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";


            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'QTY' Missing. Template error.";


            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("SALE/PURCHASE"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SALE/PURCHASE' Missing. Template error.";


            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("UNIT PRICE"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'UNIT PRICE' Missing. Template error.";


            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("REMARKS"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'REMARKS' Missing. Template error.";


            }
            column++;
            column = 0;
            row++;

            if ((sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, No Records Available.";


            }
            totalrows = (sheet.getRows());


            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END"))) {
                    for (column = 0; column
                            < 5; column++) {
                        if (column == 0) {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            Query query = session.createQuery("select partNo from CatPart where partNo=:partNo ");
                            query.setParameter("partNo", partno);
                            List list = query.list();
                            Iterator itr = list.iterator();


                            if (!itr.hasNext()) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".  Part No. not Exists. ";


                            }
                        } else if (column == 1) {
                            qty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (qty.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Quantity can not be blank.";


                            } else {
                                r = containsOnlyNumbers(qty);


                                if (r != true) {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Quantity.";


                                }
                            }
                        } else if (column == 2) {
                            salePur = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (salePur.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Sale/ Purchase can not be blank.";


                            }
                            if (!salePur.equalsIgnoreCase("SALE") && !salePur.equalsIgnoreCase("PURCHASE")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only 'SALE' or 'PURCHASE' values are allowed.";


                            }
                            r = isRegularExpressBin(salePur);


                            if (r == true) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed.";


                            }

                        } else if (column == 3) {
                            unitPrice = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (unitPrice.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Unit price can not be blank.";


                            }

                        } else if (column == 4) {
                            remarks = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();


                            if (remarks.equals("")) {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Remarks can not be blank.";


                            }
                        }
                    }
                } else {
                    result = "success";


                    break;


                }
                row++;
                column = 0;


            } //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("END"))) {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";


            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();


            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";



        } catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";


            return result;


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    public Vector insertStockAjustmentFromExcel(File xlsfile, Connection conn, String dealerCode, String flag) {

        int row = 1, column = 0;
        Session hrbsession = null;
        Transaction tx = null;
        SpStockAdjDetails spsd = null;
        SpStockAdjHDR spsh = null;
        Workbook workbook1 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Sheet sheet = null;
        String partno = "";


        float qty;
        String salePurchase = "";
        String adjNo = "";


        float unitPrice;
        String remarks = "";
        Date today = new Date();


        float totalvlaue;


        int noOfParts = 0;


        boolean txFlag = false;
        Query query = null;


        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            adjNo = new MethodUtility().getNumber(hrbsession, "SpStockAdjHDR", dealerCode, "S");
            tx = hrbsession.beginTransaction();
            spsh = new SpStockAdjHDR();
            spsh.setStkAdjNo(adjNo);
            spsh.setDealerCode(dealerCode);
            spsh.setStkAdjDate(today);
            spsh.setCreatedOn(today);
            spsh.setCreatedBy(dealerCode);



            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                partno = sheet.getCell(column, row).getContents().trim();
                partno = partno.toUpperCase();
                column++;

                qty =
                        Float.parseFloat(sheet.getCell(column, row).getContents().toUpperCase());
                column++;

                salePurchase =
                        sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;

                unitPrice =
                        Float.parseFloat(sheet.getCell(column, row).getContents().trim().toUpperCase());
                column++;

                remarks =
                        sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;

                spsd =
                        new SpStockAdjDetails();
                spsd.setStkAdjNo(adjNo);
                spsd.setPartNo(partno);
                spsd.setQty(qty);


                if (salePurchase.equalsIgnoreCase("sale")) {
                    spsd.setSalePurchase("SALE");
                    totalvlaue = unitPrice * qty;
                    spsh.setTotalSaleValue(spsh.getTotalSaleValue() + totalvlaue);


                } else {
                    spsd.setSalePurchase("PURCHASE");
                    totalvlaue = unitPrice * qty;
                    spsh.setTotalPuschaseValue(spsh.getTotalPuschaseValue() + totalvlaue);


                }
                spsd.setUnitPrice(unitPrice);
                spsd.setRemarks(remarks);

                hrbsession.save(spsd);
                noOfParts++;

                column =
                        0;
                row++;

            }


            hrbsession.save(spsh);
            tx.commit();
            txFlag = true;

            tx = hrbsession.beginTransaction();
            Query qtr = hrbsession.createSQLQuery("exec SP_STOCK_ADJUSTMENT :STK_ADJ_NO");
            qtr.setParameter("STK_ADJ_NO", adjNo);
            qtr.executeUpdate();
            tx.commit();



            if (noOfParts == 1) {
                message.add(0, "" + flag + " has been Updated Successfully.");
                message.add(1, "Add More");



            } else if (noOfParts > 1) {
                message.add(0, "" + flag + " have been uploaded Successfully.");
                message.add(1, "Add More.");



            } else {
                message.add(0, "No " + flag + " have not been uploaded.");
                message.add(1, "Try Again.");


            }
        } catch (Exception e) {
            if (txFlag) {
                try {
                    tx.rollback();
                    tx = hrbsession.beginTransaction();
                    String hql = "delete from SpStockAdjHDR where stkAdjNo='" + adjNo + "'";
                    query = hrbsession.createQuery(hql);
                    query.executeUpdate();
                    hql = "delete from SpStockAdjDetails where stkAdjNo='" + adjNo + "'";
                    query = hrbsession.createQuery(hql);
                    query.executeUpdate();
                    tx.commit();
                    hrbsession.close();



                } catch (Exception ex) {
                    ex.printStackTrace();


                }
            }
            message.add(0, "No " + flag + " have not been uploaded. Error Occured. Please contact administrator.");
            message.add(1, "Try Again.");
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        result.add(message);


        return result;


    }

// ******************** To get cancelled pending lines for acceptance *********************** (BY APURV)
    public ArrayList<inventoryForm> getPendingCancelLines(inventoryForm iForm, String dealerCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");



        try {

            String hql = "Select om.custPoNo as Dealer_PO_No, om.custPoDate as PO_Date , oi.spOrderInvoicesPK.erpOrderNo as Stockist_Order_No,"
                    + " oi.spOrderInvoicesPK.orderedPart, cp.p1 as Part_Desc, oi.qtyShipped as Cancelled_Qty, "
                    + " oi.spOrderInvoicesPK.erpPartOrderNo, oi.spOrderInvoicesPK.invoiceNo, oi.remarks from SpOrderInvoices oi, SpOrderMaster om, "
                    + " CatPart cp where oi.spOrderInvoicesPK.erpOrderNo=om.erpOrderNo and oi.dealerCode=om.dealerCode and oi.spOrderInvoicesPK.orderedPart=cp.partNo "
                    + " and oi.status='CANCELLED' and oi.dealerAcceptance='PENDING' and oi.dealerCode= :dealerCode ";

            Query query = null;
            query = session.createQuery(hql);
            query.setParameter("dealerCode", dealerCode);

            List list = query.list();
            Iterator itr = list.iterator();


            while (itr.hasNext()) {
                Object obj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setNewPoNo(obj[0] == null ? "-" : obj[0].toString().trim());
                invForm.setPoDate(obj[1] == null ? "-" : sdf1.format(df.parse(obj[1].toString().trim())));
                invForm.setStockistId(obj[2] == null ? "-" : obj[2].toString().trim());
                invForm.setPartno(obj[3] == null ? "-" : obj[3].toString().trim());
                invForm.setPart_desc(obj[4] == null ? "-" : obj[4].toString().trim());
                invForm.setQty(obj[5] == null ? "-" : obj[5].toString().trim());
                invForm.setErpOrderNo(obj[6] == null ? "-" : obj[6].toString().trim());
                invForm.setInvNo(obj[7] == null ? "-" : obj[7].toString().trim());
                invForm.setErpRemarks(obj[8] == null || obj[8].toString().equalsIgnoreCase("null") ? "-" : obj[8].toString().trim());
                dataList.add(invForm);


            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return dataList;


    }

// ******************** To Accept Pending Lines *********************** (BY APURV)
    public String acceptPendingLines(LinkedList<String> acceptanceList, String user_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "";


        try {

            session.beginTransaction();


            for (int i = 0; i
                    < acceptanceList.size(); i++) {
                Criteria cr = session.createCriteria(SpOrderInvoices.class);
                cr.add(Restrictions.eq("spOrderInvoicesPK.erpOrderNo", acceptanceList.get(i).split("@@")[1]));
                cr.add(Restrictions.eq("spOrderInvoicesPK.erpPartOrderNo", acceptanceList.get(i).split("@@")[2]));
                cr.add(Restrictions.eq("spOrderInvoicesPK.invoiceNo", acceptanceList.get(i).split("@@")[3]));
                cr.add(Restrictions.eq("spOrderInvoicesPK.orderedPart", acceptanceList.get(i).split("@@")[4]));

                Iterator itr = cr.list().iterator();




                if (itr.hasNext()) {
                    SpOrderInvoices sc = (SpOrderInvoices) itr.next();
                    sc.setDealerAcceptance(acceptanceList.get(i).split("@@")[0]);
                    sc.setAcceptedBy(user_id);
                    sc.setAcceptedOn(new Date());
                    //  sc.setDealerRemarks("NA");

                    session.save(sc);
                }
            }

            session.getTransaction().commit();
            result = "SUCCESS@@Pending lines have been accepted successfully.";


        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            result = "FAILURE@@Pending Lines could not be accepted. Please contact system administrator.";


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    public LinkedHashSet<LabelValueBean> getCategoryList() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select custcategoryID, custCategory from CustomerCategoryMaster where isActive='Y' order by custCategory";


        try {
            LabelValueBean lv = null;

            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();



            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[1].toString() == null ? "" : o[1].toString().trim();
                id = o[0].toString() == null ? "" : o[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);


            }
        } catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    public String getcustNameByCustcategoryID(inventoryForm inForm) {
        String custName = "", showlabel = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            Query query = hrbsession.createQuery("select distinct customerID, customerName,customerCode,customerLocation from UmCustomerMaster where custCategoryID=:custCategoryID and dealerCode=:dealerCode order by customerName,customerCode");

            query.setParameter("custCategoryID", Integer.parseInt(inForm.getCustcategoryID()));
            query.setParameter("dealerCode", inForm.getDealerCode());
            Iterator itr = query.list().iterator();


            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();

                showlabel = (o[1] == null ? "-" : o[1].toString()).concat(" [").concat(o[2] == null ? "-" : o[2].toString()).concat("]").concat(" [").concat(o[3] == null ? "-" : o[3].toString()).concat("] ");
                custName = custName + (o[0] == null ? "0" : o[0].toString()) + "$$" + showlabel + "||";



            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return custName;


    }

    public void getcustNameById(inventoryForm iForm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object[] obj = null;


        try {

            Query hQuery = session.createSQLQuery("select distinct*  from FN_GetCustomerDetail(?,?,?,?,?,?,?)");
            hQuery.setString(0, iForm.getCustomerId() == null ? "0" : iForm.getCustomerId().toString());
            hQuery.setString(1, iForm.getCustcategoryID() == null ? "0" : iForm.getCustcategoryID().toString());
            hQuery.setString(2, iForm.getDealerCode() == null ? "" : iForm.getDealerCode().toString());
            hQuery.setString(3, "");
            hQuery.setString(4, "");
            hQuery.setString(5, "");
            hQuery.setString(6, iForm.getCustomerCode() == null ? "" : iForm.getCustomerCode().toString());

//            query.setParameter("custCategoryID", Integer.parseInt(iForm.getCustcategoryID()));
//            query.setParameter("dealerCode", iForm.getDealerCode());
//            query.setParameter("customerID", new BigInteger(iForm.getCustomerId()));
            List list = hQuery.list();


            if (list != null && !list.isEmpty()) {
                Iterator itr = list.iterator();


                if (itr.hasNext()) {
                    obj = (Object[]) itr.next();
                    iForm.setCustomerId(obj[0] == null ? "0" : obj[0].toString());
                    iForm.setCustomerCode(obj[2] == null ? "-" : obj[2].toString());
                    iForm.setCustomerName(obj[3] == null ? "-" : obj[3].toString());
                    iForm.setCustomerLocation(obj[4] == null ? "-" : obj[4].toString());
                    iForm.setContactNo(obj[10] == null ? "-" : obj[10].toString());
                    iForm.setCreditLimit(Double.parseDouble(obj[15] == null ? "0.0" : obj[15].toString()));
                    iForm.setDiscountPercentage(Float.parseFloat(obj[13] == null ? "0.0" : obj[13].toString()));
                    iForm.setAvailableCreditLimit(Double.parseDouble(obj[21] == null ? "0.0" : obj[21].toString()));
                    iForm.setPaymentDue(Double.parseDouble(obj[20] == null ? "0.0" : obj[20].toString()));
                    iForm.setDealerCode(obj[22] == null ? "" : obj[22].toString());
                    iForm.setCustCategory(obj[23] == null ? "" : obj[23].toString());
                    iForm.setTotalDiscountPercentage(Float.parseFloat(obj[24] == null ? "0.0" : obj[24].toString()));
                    iForm.setGstNo(obj[36] == null ? "" : obj[36].toString());


                }
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();


        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;


                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
    public LinkedHashSet<LabelValueBean> getTaxSaleTypeList() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select saleTypeCode, saleTypeDesc from TaxSaleTypeMaster where isActive='Y' order by saleTypeDesc";


        try {
            LabelValueBean lv = null;

            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();



            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[1].toString() == null ? "" : o[1].toString().trim();
                id = o[0].toString() == null ? "" : o[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);


            }
        } catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();


        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }
     public String getPartNoInCatPart(String partno, Session hrbSession, String priceListCode)
    {
        String tempDesc = "";
        Query query = null;
        try
        {

            query = hrbSession.createQuery("select p.partNo,p.p4,(select distinct s.price from SpPriceMaster s where s.spPriceMasterPK.item=:partNo and s.spPriceMasterPK.pricelistCode=:pricelistCode and s.expDate is null) as price from CatPart p where p.partNo=:partNo");
            query.setParameter("partNo", partno);
            query.setParameter("pricelistCode", priceListCode);
            List paintList = query.list();
            Iterator itr = paintList.iterator();

            if (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                tempDesc = obj[0].toString() + "@@" + (obj[1] == null ? "N" : obj[1].toString()) + "@@" + (obj[2] == null ? "-" : obj[2].toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tempDesc;
    }

    public String getAlternatePartNo(String partno, Session hrbSession, String type)
    {
        String partList = "";
        String altPartList = "";
        String setNo = "";
        Integer i = 1, j = 0;
        Query query = null;
        try
        {

            query = hrbSession.createSQLQuery("Select top 1  a.ALTERNATE_PART_NO,a.SET_NO,p.p1 from CAT_ALTERNATE_PART_MASTER a,CAT_PART p where a.PART_NO=p.part_no and a.PART_NO='" + partno + "' order by a.SET_NO desc").addScalar("ALTERNATE_PART_NO", StringType.INSTANCE).addScalar("SET_NO", StringType.INSTANCE).addScalar("p1", StringType.INSTANCE);
            List alternateParts = query.list();
            Iterator itr = alternateParts.iterator();
            while (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                if (type.equals("PI"))
                {
                    altPartList += obj[0].toString() + "@@";
                }
                else
                {
                    if (!setNo.equals(obj[1].toString()))
                    {
                        if (!partList.isEmpty())
                        {
                            //altPartList = altPartList + "\n" + (i++) + ".";
                            altPartList = altPartList + "\n";
                            partList = partList.substring(1);
                            if (partList.split(",").length > 1)
                            {
                                altPartList = altPartList + "Combination of " + partList.replace(",", " & ");
                            }
                            else
                            {
                                altPartList = altPartList + "" + partList;
                            }
                            partList = "";
                        }
                        setNo = obj[1].toString();
                    }
                    partList = partList + "," + obj[0].toString();
                    j++;
                    if (j == alternateParts.size())
                    {
                        if (!partList.isEmpty())
                        {
                            //altPartList = altPartList + "\n" + (i++) +
                            altPartList = altPartList + "\n";
                            partList = partList.substring(1);
                            if (partList.split(",").length > 1)
                            {
                                altPartList = altPartList + "Combination of " + partList.replace(",", " & ");
                            }
                            else
                            {
                                altPartList = altPartList + "" + partList;
                            }
                        }
                    }
                    // alternatePartNos += obj[0].toString() + "="+obj[1].toString()+"%%";
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return altPartList;
    }


    public String getAlternateByPartNo(String mainPartno, Session hrbSession, String type) {
        String altPartList = "";
        Query query = null;
        Connection conn = null;
        CallableStatement cs=null;
        ResultSet rs = null;
        try {
             conn = new dbConnection().getConnection();
//            query = hrbSession.createSQLQuery("Select distinct  a.ALTERNATE_PART_NO,a.SET_NO,p.p1 from CAT_ALTERNATE_PART_MASTER a,CAT_PART p where a.PART_NO=p.part_no and a.PART_NO='" + partno + "' order by a.SET_NO desc").addScalar("ALTERNATE_PART_NO", StringType.INSTANCE).addScalar("SET_NO", StringType.INSTANCE).addScalar("p1", StringType.INSTANCE);
//            List alternateParts = query.list();
//            Iterator itr = alternateParts.iterator();
//            if (itr.hasNext()) {
//                Object[] obj = (Object[]) itr.next();
//                altPartList = obj[0].toString();
//
//            }
            cs = conn.prepareCall("{call SP_GetLastAlternarePart (?)}");
            cs.setString(1, mainPartno.trim());
            rs = cs.executeQuery();
            if (rs.next()) {
                altPartList=rs.getString("LatestAltPart").equals("") ? "" : rs.getString("LatestAltPart").toString().trim();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return altPartList;
    }

    public String getCheckRestrictedPart(String partNo, Session hrbSession) {
        String partAvil = "";
        Query query=null;

        try {
            query = hrbSession.createSQLQuery("SELECT *  FROM SW_RESTRICTED_PART(NOLOCK) WHERE  PART_NO=:partNo").addScalar("PART_NO", StringType.INSTANCE);
            query.setParameter("partNo", partNo);
            partAvil = query.list().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partAvil;
    }
    public String getCheckSarathiPart(String partNo, Session hrbSession) {
        String partAvil = "";
        Query query=null;

        try {
            query = hrbSession.createSQLQuery("SELECT count(PART_NO) FROM SARATHI_PartNo(NOLOCK) WHERE PART_NO=:partNo");
            query.setParameter("partNo", partNo);
            partAvil = query.list().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partAvil;
    }

	public LinkedHashSet<LabelValueBean> getJobCardList(String dealerCode) {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();

		try {
			// Use stored procedure to get VOR job card list
			// This provides better performance and centralized logic
			SQLQuery qry = sess.createSQLQuery(
					"{call SP_GetVORJobCardList(?)}");
			qry.setParameter(0, dealerCode);
			qry.addScalar("JobCardNo", org.hibernate.type.StringType.INSTANCE);

			List<String> list = qry.list();

			for (String jobcard : list) {
				if (jobcard != null) {
					jobcard = jobcard.trim();
					LabelValueBean lv = new LabelValueBean(jobcard, jobcard);
					result.add(lv);
				}
			}
		} catch (Exception ae) {
			ae.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

	public ArrayList<inventoryForm> partListbyJobCardEstimate(inventoryForm inForm, String priceListCode) {
		Session hrbsession = null;
		ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
		Double totalAmm = 0.0;
		List<String> restrictedParts;
		try {
			hrbsession = HibernateUtil.getSessionFactory().openSession();
			
			// Use stored procedure to get parts with order quantity calculation
			// Stored procedure returns: part_no, order_qty, inventory_qty
			SQLQuery partQuery = hrbsession.createSQLQuery(
					"{call SP_GetVORPartsForOrder(?, ?)}");
			partQuery.setParameter(0, inForm.getFirNo());  // @JobCardNo
			partQuery.setParameter(1, priceListCode);       // @PriceListCode
			
			// Map result set columns from stored procedure (only 3 columns returned)
			partQuery.addScalar("part_no", org.hibernate.type.StringType.INSTANCE);
			partQuery.addScalar("order_qty", org.hibernate.type.StringType.INSTANCE);
			partQuery.addScalar("inventory_qty", org.hibernate.type.StringType.INSTANCE);

			Iterator itr = partQuery.list().iterator();
			while (itr.hasNext()) {
				Object o[] = (Object[]) itr.next();
				String partNo = o[0] == null ? "" : o[0].toString();
				String orderQty = o[1] == null ? "0" : o[1].toString();
				String inventoryQty = o[2] == null ? "0" : o[2].toString();
				
				// Skip if order quantity is invalid
				Double orderQtyDouble = 0.0;
				try {
					orderQtyDouble = Double.parseDouble(orderQty);
					if (orderQtyDouble <= 0) continue;
				} catch (NumberFormatException e) {
					continue;
				}
				
				// Get additional part details using existing method
				String partData = getPriceForOrderByPartNo(partNo, hrbsession, priceListCode);
				if (partData == null || partData.equals("")) {
					continue; // Skip if part data not found
				}
				
				// Parse part data: partDesc@@partType@@unitValue@@moq@@service
				String[] partDataArray = partData.split("@@");
				String partDesc = partDataArray.length > 0 ? partDataArray[0] : "";
				String partType = partDataArray.length > 1 ? partDataArray[1] : "";
				String unitValue = partDataArray.length > 2 ? partDataArray[2] : "0";
				String moqFromDb = partDataArray.length > 3 ? partDataArray[3] : "1";
				String service = partDataArray.length > 4 ? partDataArray[4] : "N";
				
				// MOQ logic: For VOR orders, set MOQ to "1" (no restriction)
				// For non-VOR orders, use actual MOQ from database
				String moq = "1"; // Default: no MOQ restriction
				if (inForm.getOrderType() != null && !inForm.getOrderType().equalsIgnoreCase("VOR")) {
					moq = (moqFromDb == null || moqFromDb.equals("")) ? "1" : moqFromDb;
				}
				
				// Skip if unit value is invalid
				Double unitValueDouble = 0.0;
				try {
					unitValueDouble = Double.parseDouble(unitValue);
					if (unitValueDouble <= 0) continue;
				} catch (NumberFormatException e) {
					continue;
				}
				
				System.out.println("partNo " + partNo);
				System.out.println("orderQty " + orderQty);
				
				inventoryForm inf = new inventoryForm();
				inf.setPartno(partNo);
				inf.setQty(orderQty);
				inf.setPart_desc(partDesc);
				inf.setPartTypeStr(partType);
				inf.setUnitValue(unitValue);
				inf.setMoq(moq);
				inf.setService(service);
				
				// Calculate amount per price: order_qty * unitValue
				Double amountPerPrice = orderQtyDouble * unitValueDouble;
				inf.setAmountPerPrice(amountPerPrice.toString());
				
				// Calculate total amount
				if (inForm.getTotalAmont() != null && !inForm.getTotalAmont().equals("")) {
					totalAmm = Double.parseDouble(inForm.getTotalAmont());
				}
				totalAmm = totalAmm + amountPerPrice;
				inf.setTotalAmont(totalAmm.toString());
				inForm.setTotalAmont(totalAmm.toString());
				dataList.add(inf);
			}

			// Get restricted parts list
			partQuery = null;
			partQuery = hrbsession.createSQLQuery("Select part_no from VOR_RESTRICTED_PART(nolock)");
			partQuery.addScalar("part_no", org.hibernate.type.StringType.INSTANCE);
			restrictedParts = partQuery.list();
			inForm.setVorRestrictedParts(restrictedParts);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (hrbsession != null) {
				hrbsession.close();
			}
		}
		return dataList;
	}

}