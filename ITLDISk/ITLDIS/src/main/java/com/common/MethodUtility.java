/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;

import HibernateMapping.SpInventory;
import HibernateMapping.SpInventoryLedger;
import HibernateMapping.SpInventoryLedgerPK;
import HibernateMapping.SpInventoryPK;
import HibernateMapping.Warrantyclaim;
import HibernateUtil.HibernateUtil;
import dbConnection.dbConnection;

/**
 *
 * @author manish.kishore
 */
public class MethodUtility
{

    /**
     * ************************************************************************************
     * Method to get Constant value from Constant Id from database.
     * <br><br><b>Steps:</b>
     * <br>1. Select 'ELEMENT_VALUE' from 'HES_CONSTANTS' table for the element
     * id.
     * <br>2. return the value.
     * <br><br><b>Author</b> - Sonia
     *
     * @param elem_id Constant Id
     * @return String
     ***************************************************************************************
     */
    public String getSPASConstants(Connection con, int elem_id)
    {
        String val = "";
        Statement st = null;
        ResultSet rs = null;

        try
        {
            st = con.createStatement();
            rs = st.executeQuery("select ELEMENT_VALUE from GEN_hes_constants where ELEMENT_ID=" + elem_id + "");
            if (rs.next())
            {
                val = rs.getString(1);
            }
        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (st != null)
                {
                    st.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return val;
    }

    public String GetDateandTimevalue(String datevalue, String parameter)
    {
       // System.out.println("datevalue==" + datevalue);
        String Return_Value = "";
        if (parameter.equalsIgnoreCase("hr"))
        {
            Return_Value = datevalue.substring(datevalue.indexOf(" ") + 1, datevalue.indexOf(":"));
        }
        else if (parameter.equalsIgnoreCase("min"))
        {
            Return_Value = datevalue.substring(datevalue.indexOf(":") + 1, datevalue.indexOf(":") + 3);
        }
        else if (parameter.equalsIgnoreCase("date"))
        {
            Return_Value = datevalue.substring(0, datevalue.indexOf(" "));
        }
        return Return_Value;
    }

    public String GetDateandTimevalueforVehicle(String datevalue, String parameter)
    {
        String Return_Value = "";
        if (parameter.equalsIgnoreCase("hr"))
        {
            Return_Value = datevalue.substring(datevalue.indexOf(" ") + 1, datevalue.indexOf(":"));
        }
        else if (parameter.equalsIgnoreCase("min"))
        {
            Return_Value = datevalue.substring(datevalue.indexOf(":") + 1, datevalue.indexOf(":") + 3);
        }
        else if (parameter.equalsIgnoreCase("date"))
        {
            Return_Value = datevalue.substring(0, datevalue.indexOf(" "));
        }
        return Return_Value;
    }

    public String getNumber(Session hrbsession, String tablename, String dealercode, String prefix)
    {

        Calendar cal = null;
        String location = "";
        String branchcode = "";
        String number = "";
        long uniqueid = 0;
        int month = 0;
        String mon = "";
        String yr = "";
        int year = 0;
        Query query, query1 = null;
        Iterator itr = null;
        String hql = "select locationCode from Dealervslocationcode where dealerCode=? ";//dealervslocationcode(Id, DealerCode, LocationCode)

        query = hrbsession.createQuery(hql);
        query.setString(0, dealercode);
        itr = query.list().iterator();
        if (itr.hasNext())
        {
            location = (String) itr.next();
        }
        else
        {
            return "notexist";
        }

        cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;

        if (month <= 9)
        {
            mon = "0" + month;
        }
        else
        {
            mon = month + "";
        }
        yr = Integer.toString(year).substring(0, Integer.toString(year).length());

        String hql3 = "select count(*) from " + tablename + " where Year(getDate())= Year(createdOn) and dealerCode='" + dealercode + "'";
        query1 = hrbsession.createQuery(hql3);
        itr = query1.list().iterator();

        if (itr.hasNext())
        {
            uniqueid = (Long) itr.next();
        }
      //  System.out.println("uniqueid==" + uniqueid);
        uniqueid = uniqueid + 1;

        //number = prefix+"/" +location +"/"+ dealercode.toUpperCase() + "/" + branchcode + "/" + mon + "/" + yr + "/" + uniqueid;
        number = prefix + "/" + location + "/" + dealercode.toUpperCase() + "/" + mon + "/" + yr + "/" + uniqueid;

        return number;
    }

    /**
     * check in db comman method for HQL created on 19/09/14 by Megha
     */
    public static String check_in_Db_HQL(String no_new, String tablemappingClass, String column, String SubQuery, Session session)
    {
//     Session session = HibernateUtil.getSessionFactory().openSession();
        String val = "notexist";
        try
        {
            String hql = " FROM " + tablemappingClass + " m  WHERE m." + column + " = :no_new " + SubQuery;
            Query query = session.createQuery(hql);
            query.setParameter("no_new", no_new);
            List result = query.list();
          //  System.out.println("" + result.toString());
            if (result.size() > 0)
            {
                val = "exist";
            }

        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * getCommonLabelValue using Hibernate created on 19/09/14 by Megha
     */
    public static LinkedHashSet<LabelValueBean> getCommonLabelValueHiber(String entytyName, String fieldId, String fieldName, String filter, String whereCase)
    {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try
        {
            if (whereCase.equals(""))
            {
                whereCase = "where e.isActive='Y'";
            }
            Query qry = sess.createQuery("select e." + fieldId + ",e." + fieldName + " from " + entytyName + " e " + whereCase + " order by e." + filter + "");

            List<String> l = (List<String>) qry.list();

            Iterator it = l.iterator();

            while (it.hasNext())
            {
                LabelValueBean lv = null;

                Object o[] = (Object[]) it.next();
                String name1 = (String) o[1];
                String name0 = o[0].toString();

                name = name1 == null ? "" : name1.trim();
                id = name0 == null ? "" : name0.trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }

        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                sess.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * getCommonlist using Hibernate created on 19/09/14 by Megha
     */
    public static ArrayList<String> getCommonListHiber(String entytyName, String fieldId, String filter, String whereCase)
    {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        ArrayList<String> result = new ArrayList<String>();
        String name = "", id = "";
        try
        {
            Query qry = sess.createQuery("select e." + fieldId + " from " + entytyName + " e " + whereCase + " order by e." + filter + "");
            List<String> l = (List<String>) qry.list();
            if (qry.list().size() > 0)
            {
                result.addAll(l);
            }

        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                sess.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static ArrayList<String> getCommonDistinctListHiber(String entytyName, String fieldId, String filter, String whereCase)
    {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        ArrayList<String> result = new ArrayList<String>();
        try
        {
            Query qry = sess.createQuery("select DISTINCT e." + fieldId + " from " + entytyName + " e " + whereCase + " order by e." + filter + "");
            List<String> l = (List<String>) qry.list();
            if (qry.list().size() > 0)
            {
                result.addAll(l);
            }
        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                sess.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    //*** common method for upload file *** warranty
    public static boolean uploadFile(String fileName, String filePath, FormFile uploadFile, String realPath)
    {
        boolean isUploaded = false;
        if (!fileName.equals(""))
        {
            //fileName.toUpperCase().replaceAll(".XLS", ".xls");
            File currentToolDb = new File(filePath, fileName);

            try
            {
                FileOutputStream fileOutStream = new FileOutputStream(currentToolDb);
                fileOutStream.write(uploadFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
                isUploaded = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                isUploaded = false;
            }
        }
        return isUploaded;
    }

    /**
     *  inventoryLedgerEntry for manage inventory copy from thirth
     */
    public static void inventoryLedgerEntry(String userId, String dealerCode, String partno, Double qty, String txntype, Session session) throws Exception
    {

        Query query = null;
        Iterator itr = null;
        double closingstock = 0.0;
        double CurrentStock = 0.0;
        boolean partExistflag = true;

        SpInventoryLedger inventoryLedger = null;
        SpInventory inventory = null;

        String hql = "select si.quantity from SpInventory si where si.spInventoryPK.dealerCode=:dealerCode and si.spInventoryPK.partNo=:partNo";
        query = session.createQuery(hql);
        query.setParameter("dealerCode", dealerCode);
        query.setParameter("partNo", partno);

        List<Double> list = query.list();
        itr = list.iterator();
        if (!itr.hasNext())
        {
            closingstock = 0.0;
            partExistflag = false;
        }
        else
        {
            closingstock = (Double) itr.next();
            partExistflag = true;
        }
        inventoryLedger = new SpInventoryLedger();
        SpInventoryLedgerPK inventoryLedgerPK = new SpInventoryLedgerPK(dealerCode, partno, new Date());
        inventoryLedger.setSpInventoryLedgerPK(inventoryLedgerPK);
        inventoryLedger.setTransactionDescription(txntype);
        inventoryLedger.setLastModifiedBy(userId);
        inventoryLedger.setLastModifiedOn(new Date());
        if (txntype.equalsIgnoreCase("PART GRN") || txntype.equalsIgnoreCase("COUNTER SALES RETURN") || txntype.equalsIgnoreCase("Add Inventory"))
        {
            CurrentStock = closingstock + qty;
            inventoryLedger.setInward(qty);
            inventoryLedger.setOutward(0);
//                    inventoryLedger.setOpeningStk(closingstock);
//                    inventoryLedger.setClosingStk(CurrentStock);

        }
        else
        {
            CurrentStock = closingstock - qty;
            inventoryLedger.setInward(0);
            inventoryLedger.setOutward(qty);
//                    inventoryLedger.setOpeningStk(closingstock);
//                    inventoryLedger.setClosingStk(CurrentStock);
 //           System.out.println("qty:" + qty + "-currStock:" + CurrentStock + "-closeing stock:" + closingstock + "-");
        }
        session.save(inventoryLedger);

        if (!partExistflag)
        {
            inventory = new SpInventory();
            SpInventoryPK inventoryPK = new SpInventoryPK(dealerCode, partno);
            inventory.setSpInventoryPK(inventoryPK);
            inventory.setQuantity(CurrentStock);
            inventory.setCreatedOn(new Date());
            inventory.setCreatedBy(dealerCode);
            inventory.setModifiedBy(userId);
            inventory.setModifiedOn(new Date());
            session.save(inventory);
        }
        else
        {
            SpInventoryPK inventoryPK = new SpInventoryPK(dealerCode, partno);
            inventory = (SpInventory) session.load(SpInventory.class, inventoryPK);
            inventory.setQuantity(CurrentStock);
            inventory.setModifiedBy(userId);
            inventory.setModifiedOn(new Date());
            session.update(inventory);
        }

    }

    /**
     * getHesConstantValue 
     */
    public static String getHesConstantValue(int id)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        String hql = null;
        String constantValue = "";
        try
        {
            hql = "select elementValue from HesConstants where elementId=?";
            Query query = hrbsession.createQuery(hql);
            query.setInteger(0, id);
            itr = query.list().iterator();
            if (itr.hasNext())
            {
                constantValue = (String) itr.next();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (hrbsession != null)
                {
                    hrbsession.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return constantValue;
    }

    public static String getfilesize(String filename)
    {
        String value = "";
        try
        {
            File file = new File(filename);
            if (file.exists())
            {
              //  System.out.println("file" + file.length() / 1024);
                value = file.length() / 1024 + "";
            }
            else
            {
                value = "notexist";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * get dealerList with Location
     */
    
    public static ArrayList<ArrayList<String>> getListwithJoinQuery(String tableName, String columnNames, String orderBy, String whereclause, int noOfColumn)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try
        {
            Query query = session.createQuery("select " + columnNames + " from " + tableName + whereclause + " order by " + orderBy);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                String name = "";
                ArrayList<String> labels = new ArrayList<String>();
                for (int i = 0; i < noOfColumn; i++)
                {
                    name = name + (obj[i] == null ? "" : obj[i].toString().trim());
                    labels.add(name);
                }
//                System.out.println("--"+labels.toString());
                result.add(labels);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (session != null)
                {
                    session.close();
                    session = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writingTxt(String wClaimNo, Session session, File file) throws Exception
    {
        try
        {

            //File file = new File("D:/WarrantyTxt/" +wClaimNo+".txt");
            FileOutputStream is = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = null;
            int count = 0;
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            DateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
            session.beginTransaction();

            String hql = "select wc.jobCardNo,wc.warrantyClaimNo,wc.vinno,wc.dealerCode, "
                    + " wc.deliverySaleDate,wc.failureDate ,wc.replacementDate ,"
                    + " wc.hmr,"
                    + " (select wcp.courierNo from SWwarrantypackingmaster wcp where wcp.packingNo=wcd.packingNo )as courierNo , " // " wcp.courierNo," +
                    + " (select wcp.courierName from SWwarrantypackingmaster wcp where wcp.packingNo=wcd.packingNo )as courierName ," // " wcp.courierName, " +
                    + " wcd.partNo,am.aggDesc ,wcd.qty,wcd.qtyApproved,wcd.qtyRejected,wcd.rejectionCode, wcd.scrapValue ," //jcd.aggCode
                    + " wcd.vendorCodeAdmin,wcd.rejectionRemarks  "
                    + " from Warrantyclaim wc,Warrantyclaimdetails wcd ,Jobcomplaintdetails jcd ,Aggregatemaster am " //SWwarrantypackingmaster wcp
                    + " where wc.claimStatus in ('PARTIAL APPROVED' ,'APPROVED','REJECTED') "
                    + " and wc.sapSyncStatus='PENDING' "
                    + " and wc.warrantyClaimNo=wcd.warrantyClaimNo "
                    + " and wcd.cmpNo=jcd.cmpNo and am.aggCode=jcd.aggCode" // " and wcd.packingNo=wcp.packingNo " +
                    + " and wc.warrantyClaimNo = '" + wClaimNo + "'   ";

            Query query = session.createQuery(hql);
            //  query.setString(wClaimNo, wClaimNo);
            w = new BufferedWriter(osw);
            if (count == 0)
            {
                w.write("TMS NUMBER");
                w.write("\r\t");
                w.write("DMS NUMBER");
                w.write("\r\t");
                w.write("CHASSIS NUMBER");
                w.write("\r\t");
                w.write("DEALER CODE");
                w.write("\r\t");
                w.write("DATE OF SALE");
                w.write("\r\t");
                w.write("DATE OF FAILURE");
                w.write("\r\t");
                w.write("DATE OF REWORK");
                w.write("\r\t");
                w.write("WORKING HOURS");
                w.write("\r\t");
                w.write("G.R NUMBER");
                w.write("\r\t");
                w.write("TRANSPORT");
                w.write("\r\t");
                w.write("MATERIAL CODE");
                w.write("\r\t");
                w.write("MATERIAL GROUP");
                w.write("\r\t");
                w.write("QTY RECIEVED");
                w.write("\r\t");
                w.write("QTY ACCEPTEED");
                w.write("\r\t");
                w.write("QTY REJECTED");
                w.write("\r\t");
                w.write("REJECTED CODE");
                w.write("\r\t");
                w.write("SCRAP");
                w.write("\r\t");
                w.write("VENDOR ");
                w.write("\r\t");
                w.write("REMARKS");
                w.write("\r\t");
                w.write("\r\n");
            }
            Iterator itr = query.list().iterator();
            while (itr.hasNext())
            {
                Object ob[] = (Object[]) itr.next();

                w.write((ob[0] == null ? "" : ob[0].toString()) + "\r\t"
                        + (ob[1] == null ? "" : ob[1].toString()) + "\r\t"
                        + (ob[2] == null ? "" : ob[2].toString().toUpperCase()) + "\r\t"
                        + (ob[3] == null ? "" : ob[3].toString()) + "\r\t"
                        + (ob[4] == null ? "" : (outputFormat.format(inputFormat.parse(ob[4].toString())))) + "\r\t"
                        + (ob[5] == null ? "" : (outputFormat.format(inputFormat.parse(ob[5].toString())))) + "\r\t"
                        + (ob[6] == null ? "" : (outputFormat.format(inputFormat.parse(ob[6].toString())))) + "\r\t"
                        + (ob[7] == null ? "" : ob[7].toString()) + "\r\t"
                        + (ob[8] == null ? "" : ob[8].toString()) + "\r\t"
                        + (ob[9] == null ? "" : ob[9].toString()) + "\r\t"
                        + (ob[10] == null ? "" : ob[10].toString().toUpperCase()) + "\r\t"
                        + (ob[11] == null ? "" : ob[11].toString()) + "\r\t"
                        + (MethodUtility.checkPoint(ob[12] == null ? "" : ob[12].toString())) + "\r\t"
                        + (MethodUtility.checkPoint(ob[13] == null ? "" : ob[13].toString())) + "\r\t"
                        + (MethodUtility.checkPoint(ob[14] == null ? "" : ob[14].toString())) + "\r\t"
                        + (ob[15] == null ? "" : ob[15].toString()) + "\r\t"
                        + (ob[16] == null ? "" : ob[16].toString()) + "\r\t"
                        + (ob[17] == null ? "" : ob[17].toString()) + "\r\t");
                w.write("\r\n");
                w.flush();
                count++;
            }
            w.close();

            Boolean isUploadedOnFTP = fileUploadOnFTPServer(file);
            if (isUploadedOnFTP)
            {
                Warrantyclaim warnDetail = (Warrantyclaim) session.get(Warrantyclaim.class, wClaimNo);
                if (warnDetail.getSapSyncStatus().equalsIgnoreCase("PENDING"))
                {
                    warnDetail.setSapSyncStatus("DONE"); //sapSyncStatus
                }
                warnDetail.setSapSyncDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                session.saveOrUpdate(warnDetail);
                session.getTransaction().commit();
            }
            else
            {
                System.err.println("File not uploaded on FTP Server..");
            }

        }
        catch (IOException e)
        {
            System.err.println("Problem writing to the file Test.txt");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }

    public static String checkPoint(String value)
    {
        String val = "";
        if (!value.equals(""))
        {
            double t = Double.parseDouble(value);
            if (t == Math.ceil(t))
            {
                val = "" + ((int) t);
            }
            else
            {
                val = "" + t;
            }
        }
        return val;
    }

    public static void writingTxtJDBC(String wClaimNo, File file) throws Exception
    {
        dbConnection db = new dbConnection();
        Connection con = null;
        ResultSet rs = null;
        FileOutputStream is = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = null;
        int count = 0;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        PreparedStatement pstmt = null;

        try
        {

            con = db.getConnection_DM();
            Statement st = con.createStatement();
            rs = st.executeQuery("select wc.JobCardNo,wc.WarrantyClaimNo,wc.vinno,wc.DealerCode,"
                    + " wc.DeliverySaleDate,wc.FailureDate,wc.ReplacementDate,wc.HMR, "
                    + " wcp.CourierNo,wcp.CourierName,wcd.part_no,am.AggDesc , "
                    + " wcd.qty,wcd.QtyApproved,wcd.QtyRejected ,wcd.RejectionCode , "
                    + " wcd.ScrapValue,wcd.VendorCodeAdmin,wcd.RejectionRemarks "
                    + " from SW_warranty_claim wc  "
                    + " inner join  SW_warranty_claim_details wcd on wc.WarrantyClaimNo=wcd.WarrantyClaimNo "
                    + " inner join SW_jobcomplaintdetails jcd on wcd.CMP_NO=jcd.CMP_NO "
                    + " inner join MSW_aggregatemaster am on am.AggCode=jcd.AggCode "
                    + " left join  SW_warranty_packing_master wcp on wcd.PackingNo=wcp.PackingNo "
                    + " where wc.ClaimStatus in ('PARTIAL APPROVED' ,'APPROVED','REJECTED')"
                    + " and wc.SAP_SyncStatus='PENDING' "
                    + " and wc.WarrantyClaimNo='" + wClaimNo + "' ");

            w = new BufferedWriter(osw);
            if (count == 0)
            {
                w.write("TMS NUMBER");
                w.write("\r\t");
                w.write("DMS NUMBER");
                w.write("\r\t");
                w.write("CHASSIS NUMBER");
                w.write("\r\t");
                w.write("DEALER CODE");
                w.write("\r\t");
                w.write("DATE OF SALE");
                w.write("\r\t");
                w.write("DATE OF FAILURE");
                w.write("\r\t");
                w.write("DATE OF REWORK");
                w.write("\r\t");
                w.write("WORKING HOURS");
                w.write("\r\t");
                w.write("G.R NUMBER");
                w.write("\r\t");
                w.write("TRANSPORT");
                w.write("\r\t");
                w.write("MATERIAL CODE");
                w.write("\r\t");
                w.write("MATERIAL GROUP");
                w.write("\r\t");
                w.write("QTY RECIEVED");
                w.write("\r\t");
                w.write("QTY ACCEPTEED");
                w.write("\r\t");
                w.write("QTY REJECTED");
                w.write("\r\t");
                w.write("REJECTED CODE");
                w.write("\r\t");
                w.write("SCRAP");
                w.write("\r\t");
                w.write("VENDOR ");
                w.write("\r\t");
                w.write("REMARKS");
                w.write("\r\t");
                w.write("\r\n");
            }
            while (rs.next())
            {
                w.write((rs.getString(1) == null ? "" : rs.getString(1)) + "\r\t"
                        + (rs.getString(2) == null ? "" : rs.getString(2)) + "\r\t"
                        + (rs.getString(3) == null ? "" : rs.getString(3).toUpperCase()) + "\r\t"
                        + (rs.getString(4) == null ? "" : rs.getString(4)) + "\r\t"
                        + (rs.getString(5) == null ? "" : (outputFormat.format(inputFormat.parse(rs.getString(5))))) + "\r\t"
                        + (rs.getString(6) == null ? "" : (outputFormat.format(inputFormat.parse(rs.getString(6))))) + "\r\t"
                        + (rs.getString(7) == null ? "" : (outputFormat.format(inputFormat.parse(rs.getString(7))))) + "\r\t"
                        + (rs.getString(8) == null ? "" : rs.getString(8)) + "\r\t"
                        + (rs.getString(9) == null ? "" : rs.getString(9)) + "\r\t"
                        + (rs.getString(10) == null ? "" : rs.getString(10)) + "\r\t"
                        + (rs.getString(11) == null ? "" : rs.getString(11).toUpperCase()) + "\r\t"
                        + (rs.getString(12) == null ? "" : rs.getString(12)) + "\r\t"
                        + MethodUtility.checkPoint((rs.getString(13) == null ? "" : rs.getString(13))) + "\r\t"
                        + MethodUtility.checkPoint((rs.getString(14) == null ? "" : rs.getString(14))) + "\r\t"
                        + MethodUtility.checkPoint((rs.getString(15) == null ? "" : rs.getString(15))) + "\r\t"
                        + (rs.getString(16) == null ? "" : rs.getString(16)) + "\r\t"
                        + (rs.getString(17) == null ? "" : rs.getString(17)) + "\r\t"
                        + (rs.getString(18) == null ? "" : rs.getString(18)) + "\r\t");
                w.write("\r\n");
                w.flush();
                count++;
            }
            w.close();


            pstmt = con.prepareStatement("update SW_warranty_claim set SAP_SyncStatus=? ,SAP_SyncDate =?  where WarrantyClaimNo=? ");
            pstmt.setString(1, "DONE");
            pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setString(3, wClaimNo);

            pstmt.executeUpdate();
            st.close();
            con.commit();




        }
        catch (Exception e)
        {
            System.err.println("Problem writing to the file Test.txt");
            e.printStackTrace();
            con.rollback();

        }
        finally
        {
            con.close();
        }

    }

    public static Boolean fileUploadOnFTPServer(File file)
    {

        boolean done = false;
        String server = dbConnection.ftp_server_ip;                   // String server ="59.160.98.134";
        int port = Integer.parseInt(dbConnection.ftp_server_port);    // int port = 21;
        String user = dbConnection.ftp_server_username;                // String user = "general";
        String pass = dbConnection.ftp_server_password;                // String pass = "gen@1234";
        String remoteFile = dbConnection.ftp_file_path;

        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(server, port);
            //System.out.println("connected with FTP");
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String remoteFilePath = remoteFile.concat("/") + file;
            InputStream inputStream = new FileInputStream(file);  //localFile
            // System.out.println("Start uploading file");
            done = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();
            if (done)
            {
                System.out.println("The file is uploaded successfully using FTP.");
            }
        }
        catch (IOException ex)
        {
                System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (ftpClient.isConnected())
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return done;
    }

//code for read file from FTP server and update warranty claim (sap credit no/credit ammount/sap_credit_date )
    public static void main(String[] args) throws SQLException
    {

        String server = dbConnection.ftp_server_ip;                   // String server ="59.160.98.134";
        int port = Integer.parseInt(dbConnection.ftp_server_port);    // int port = 21;

        String credit_new = dbConnection.credit_new_path;
        String credit_success = dbConnection.credit_success_path;
        String credit_failure = dbConnection.credit_failure_path;

        FTPClient ftpClient = new FTPClient();
       // FTPClient ftpClientSuccess = new FTPClient();
      //  FTPClient ftpClientFailure = new FTPClient();
        try
        {

            ftpClient.connect(server, port);
            ftpClient.login(dbConnection.ftp_credit_new_username, dbConnection.ftp_credit_new_password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);


            String str_file_dir = credit_new.concat("/");
            FTPFile listFiles[] = ftpClient.listFiles("/" + credit_new);

            String remoteFilePath = "";
            String credit_new_file_path = "";
            File credit_new_file;
            OutputStream outputStream1;
            FileInputStream fstream;
            DataInputStream in;
            BufferedReader br;
            boolean delflag = false;
            Boolean isUpdatedWaranty = false;
            Boolean done = false;
          //  System.out.println(listFiles);

            if (listFiles != null && listFiles.length > 0)
            {
                for (int i = 0; i < listFiles.length; i++)
                {
                 //   System.out.println(" txt name "+listFiles[i].getName());
                    if (!(listFiles[i].getName().toLowerCase().endsWith(".txt")) )
                    {
                        continue;
                    }
                    File temF = new File(str_file_dir + listFiles[i]);
                    if (!temF.isDirectory())
                    {
                        delflag = false;
                        remoteFilePath = credit_new.concat("/");
                        credit_new_file_path = remoteFilePath + listFiles[i].getName();
                        credit_new_file = new File(remoteFilePath);
                        outputStream1 = new BufferedOutputStream(new FileOutputStream(credit_new_file));
                        if (credit_new_file.exists())
                        {
                            ftpClient.retrieveFile(credit_new_file_path, outputStream1);
                        }
                        outputStream1.close();
                        fstream = new FileInputStream(credit_new_file);
                        //FileInputStream fstream = new FileInputStream("D:\\Download_from_FTP\\xyz.txt");
                        in = new DataInputStream(fstream);
                        br = new BufferedReader(new InputStreamReader(in));
                        //    String strLine;
                        //    int line = 1;
                        isUpdatedWaranty = false;
                        isUpdatedWaranty = updateWaranty(br);  //rows

                        in.close();

                        if (isUpdatedWaranty)
                        {
                            String cr_success = credit_success.concat("/") + listFiles[i].getName();
                            InputStream inputStream = new FileInputStream(credit_new_file);  //localFile

                            ftpClient.logout();
                            ftpClient.disconnect();
                            ftpClient.connect(server, port);
                            ftpClient.login(dbConnection.ftp_credit_success_username, dbConnection.ftp_credit_success_password);
                            ftpClient.enterLocalPassiveMode();
                            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                            done = ftpClient.storeFile(cr_success, inputStream);
                            inputStream.close();
                            if (done)
                            {
                                delflag = true;
                                System.out.println("The file is uploaded successfully on CREDIT_SUCCESS.");
                            }
                        }
                        else
                        {
                            String cr_fail = credit_failure.concat("/") + listFiles[i].getName();
                            InputStream inputStream = new FileInputStream(credit_new_file);  //localFile
                           
                           
                            ftpClient.logout();
                            ftpClient.disconnect();
                            ftpClient.connect(server, port);
                            ftpClient.login(dbConnection.ftp_credit_failure_username, dbConnection.ftp_credit_failure_password);
                            ftpClient.enterLocalPassiveMode();
                            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                           done = ftpClient.storeFile(cr_fail, inputStream);
                            inputStream.close();
                            if (done)
                            {
                                delflag = true;
                                System.out.println("The file is uploaded successfully on CREDIT_FAIL");
                            }
                        }

                        ftpClient.logout();
                        ftpClient.disconnect();
                        ftpClient.connect(server, port);
                       ftpClient.login(dbConnection.ftp_credit_new_username, dbConnection.ftp_credit_new_password);
                       ftpClient.enterLocalPassiveMode();
                       ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                        if(delflag)
                        {                        
                            ftpClient.deleteFile(credit_new_file_path);
                        }

                    }
                }
            }
            else
            {
                System.out.println("TXT File Not exist in on CREDIT_New Folder On FTP Server");
            }
        }
        catch (IOException ex)
        {
                System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (ftpClient.isConnected())
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }                
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static Boolean updateWaranty(BufferedReader br) throws SQLException
    {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        dbConnection db = new dbConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        Boolean isUpdated = false;
        String strLine = "";
        int line = 1;
        String headingTxt = "DMS NUMBER	            TMS NUMBER	             CUSTOMER No.	        CREDIT NOTE	  DATE	   AMOUNT CREDITED";
        try
        {
            con = db.getConnection_DM();
            pstmt = con.prepareStatement("update SW_warranty_claim set SAP_CreditNo=? ,SAP_CreditDate =? ,SAP_CreditAmount=? ,SAP_SyncStatus=? ,Is_ServerSync =? where WarrantyClaimNo=? ");
            while ((strLine = br.readLine()) != null)
            {
                if (line == 1 && !strLine.equals(headingTxt))
                {
                    System.err.println("Invalid Txt File ");
                    //break;
                }
                if (line != 1)
                {
                    String[] rows = strLine.split("\n");
                    for (int i = 0; i < rows.length; i++)
                    {
                        if (rows[i] != null && !rows[i].equals(""))
                        {
                            String[] data = rows[i].split("\t");
                            // if( !(data[1] == null || data[1].equals("")) &&  !(data[5] == null || data[5].equals(""))  ){
                            if (data.length == headingTxt.split("\t").length)
                            {
                               // System.out.println(data[3]+"---"+new java.sql.Date(df.parse(data[4]).getTime())+"---"+data[5]+"---"+data[0]);
                                pstmt.setString(1, data[3]);        //SAP_CreditNo
                                pstmt.setDate(2, new java.sql.Date(df.parse(data[4]).getTime()));   //SAP_CreditDate
                                pstmt.setString(3, data[5]);        //SAP_CreditAmount
                                pstmt.setString(4, "CREDITED");     //SAP_SyncStatus
                                pstmt.setString(5, "N");            //Is_ServerSync
                                pstmt.setString(6, data[0]);        //WarrantyClaimNo
                                pstmt.addBatch();
                            }
                        }
                    }
                }
                line++;
            }
            int ii[] = pstmt.executeBatch();
            if (ii.length != 0)
            {
                con.commit();
                isUpdated = true;
            }
            if (br != null)
            {
                br.close();
            }

        }
        catch (Exception e)
        {
            System.err.println("SAP CREDIT Note not updated. ");
            e.printStackTrace();
            isUpdated = false;
            con.rollback();
        }
        finally
        {
            if (pstmt != null)
            {
                pstmt.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return isUpdated;
    }

    public static void copyFile(File source, File dest) throws IOException {

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    public static List<String> getDealerCodeUnderUser(Session hrbsession, String user_id) {
      
        List<String> dealerList = null;
        try {
            dealerList = hrbsession.createSQLQuery("Select * from FN_GetDealersUnderUser(?)").setString(0, user_id).list();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return dealerList;
    }

    public static List getDealersDetailsUnderUser( String user_id) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        List<String> dealerList = null;
        try {
            dealerList = hrbsession.createSQLQuery("Select * from FN_GetDealersDetailsUnderUser(?) order by DEALER_NAME").setString(0, user_id).list();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally
        {
         if(hrbsession!=null)
         {
             try
             {
                 hrbsession.close();
             }
             catch(Exception ee)
             {
                 ee.printStackTrace();
             }
         }
        }

        return dealerList;
    }

    public static ArrayList<Object[]> getEmailData() {

        Session hrbsession = null;
        ArrayList<Object[]> emailList = new ArrayList<Object[]>();
        String hql = "";        
        try {
            hrbsession = HibernateUtil.getSessionFactory().openSession();

            hql = "select fromMailId, toMailId, ccMailId, bccMailId, mailText, fileVector, createdOn, " 
                  +" createdBy, sentOn, remarks, mailSubject,id from UmEmailMaster where status='PENDING'";

            Query query = hrbsession.createQuery(hql);
            List result = query.list();
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                emailList.add((Object[])itr.next());
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
        return emailList;
    }

    public static LinkedHashSet<LabelValueBean> getCustomerCommonList(String fieldId, String fieldName){

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try{
            Query qry = sess.createQuery("select distinct " + fieldId + "," + fieldName + " from TMSGeographyMaster ");
            List<String> l = (List<String>) qry.list();
            Iterator it = l.iterator();
            while (it.hasNext()){
                LabelValueBean lv = null;
                Object obj[] = (Object[]) it.next();
                String name1 = (String) obj[1];
                String name0 = obj[0].toString();

                name = name1 == null ? "" : name1.trim();
                id = name0 == null ? "" : name0.trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae){
            ae.printStackTrace();
        }
        finally{
            try{
                sess.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static LinkedHashSet<LabelValueBean> getCustomerGeographyList(String getColName, String whereColumn, String whereColVal, String orderBy){

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String lableName="";
        try{

            LabelValueBean lv = null;
            Query query = sess.createQuery("select distinct " + getColName + " from TMSGeographyMaster where " + whereColumn + " = :whereColVal order by "+orderBy+" ");
            query.setParameter("whereColVal", Integer.parseInt(whereColVal.length() > 0 ? whereColVal : "0"));
            Iterator it = query.list().iterator();

            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                if (obj[1] == null || obj[1].equals("")) {
                    lableName="NA";
                } else {
                    lableName=obj[1].toString().trim();
                }
                lv = new LabelValueBean(lableName,obj[0] == null ? "0" : obj[0].toString().trim());
                result.add(lv);
            }

        }
        catch (Exception ae){
            ae.printStackTrace();
        }
        finally{
            try{
                sess.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    
     public static List getDealersCountryUnderUser( String user_id) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        List<String> countryList = null;
        try {
            countryList = hrbsession.createSQLQuery("Select * from FN_GetDealersCountryUnderUser(?) order by CountryName").setString(0, user_id).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
         if(hrbsession!=null) {
             try{
                 hrbsession.close();
             }
             catch(Exception ee){
                 ee.printStackTrace();
             }
         }
        }
        return countryList;
    }

    public String getNumberEW(Session hrbsession, String tablename, String dealercode, String prefix) {

        Calendar cal = null;
        String location = "";
        String number = "";
        long uniqueid = 0;
        int month = 0;
        String mon = "";
        String yr = "";
        int year = 0;
        Query query, query1 = null;
        Iterator itr = null;
        String hql = "select locationCode from Dealervslocationcode where dealerCode=? ";

        query = hrbsession.createQuery(hql);
        query.setString(0, dealercode);
        itr = query.list().iterator();
        if (itr.hasNext()) {
            location = (String) itr.next();
        } else {
            return "notexist";
        }
        cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;
        if (month <= 9) {
            mon = "0" + month;
        } else {
            mon = month + "";
        }
        yr = Integer.toString(year).substring(0, Integer.toString(year).length());
        String hql3 = "select count(*) from " + tablename + " where Year(getDate())= Year(createdOn) and dealerCode='" + dealercode + "'";
        query1 = hrbsession.createQuery(hql3);
        itr = query1.list().iterator();
        if (itr.hasNext()) {
            uniqueid = (Long) itr.next();
        }
        uniqueid = uniqueid + 1;
        number = prefix + "/" + location + "/" + dealercode.toUpperCase() + "/" + mon + "/" + yr + "/" + uniqueid;

        return number;
    }
     public static String getStateIdByDealer(String no_new, String tablemappingClass, String column,String column1, String SubQuery, Session session) {
        String val = "";
        Iterator itr = null;
        try {
            String hql = " select m." + column + "  FROM " + tablemappingClass + " m  WHERE m." + column1 + " = :no_new " + SubQuery;
            Query query = session.createQuery(hql);
            query.setParameter("no_new", no_new);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                val = (String) itr.next();
            } else {
                val = "notexist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    public static String appendDealerList(List<String>dealerList){
        StringBuffer sb=new StringBuffer();
            if(dealerList!=null && !dealerList.isEmpty()){
                for(String str:dealerList)
                    sb.append(str).append(",");
            }

        return sb.toString();
    }

     public String checkInDb(String dealercode, String column1, String tablename) {
        String val = "notexist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = " FROM " + tablename + " where " + (column1) + "='" + dealercode + "' and np8 is not null";
            Query query = hrbsession.createQuery(hql);
            List results = query.list();

            Iterator itr = results.iterator();

            if (itr.hasNext()) {
                val = "exist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
               hrbsession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }
     
     public String getInvoiceNumberEW(Session hrbsession, String tablename, String dealercode, String prefix) {

         Calendar cal = null;
         String location = "";
         String number = "";
         long uniqueid = 0;
        
         Query query, query1 = null;
         Iterator itr = null;
         String hql = "select locationCode from Dealervslocationcode where dealerCode=? ";

         query = hrbsession.createQuery(hql);
         query.setString(0, dealercode);
         itr = query.list().iterator();
         if (itr.hasNext()) {
             location = (String) itr.next();
         } else {
             return "notexist";
         }
        
         String hql3 = "select count(*) from " + tablename + " where Year(getDate())= Year(createdOn) and dealerCode='" + dealercode + "'";
         query1 = hrbsession.createQuery(hql3);
         itr = query1.list().iterator();
         if (itr.hasNext()) {
             uniqueid = (Long) itr.next();
         }
         uniqueid = uniqueid + 1;
         number = prefix + "/" + location + "/" + dealercode.toUpperCase() +  "/" + uniqueid;

         return number;
     }
     
     public String getDeliveryChallanNoForWayBill(Session hrbsession, String tablename, String dealercode, String prefix) {

         Calendar cal = null;
         String location = "";
         String number = "";
         long uniqueid = 0;
        
         Query query1 = null;
         Query query = null;
         Iterator itr = null;
         
         
         String hql = "select locationCode from Dealervslocationcode where dealerCode=? ";

         query = hrbsession.createQuery(hql);
         query.setString(0, dealercode);
         itr = query.list().iterator();
         if (itr.hasNext())
         {
             location = (String) itr.next();
         }
         else
         {
             return "notexist";
         }
         
         itr = null;
         String hql3 = "select count(*) from " + tablename + " where Year(getDate())= Year(createdOn) and dealerCode='" + dealercode + "'";
         query1 = hrbsession.createQuery(hql3);
         itr = query1.list().iterator();
         if (itr.hasNext()) {
             uniqueid = (Long) itr.next();
         }
         uniqueid = uniqueid + 1;
         number = dealercode.toUpperCase() +  "/" + uniqueid;
         return number;
     }
     
     public String get12DigitInvoiceNoForSpareInvoice(Session hrbsession, String tablename, String dealercode, String prefix) {

         Calendar cal = null;
         String location = "";
         String number = "";
         long uniqueid = 0;
        
         Query query1 = null;
         Iterator itr = null;
         
         Query query = null;
         String hql = "select locationCode from Dealervslocationcode where dealerCode=? ";

         query = hrbsession.createQuery(hql);
         query.setString(0, dealercode);
         itr = query.list().iterator();
         if (itr.hasNext())
         {
             location = (String) itr.next();
         }
         else
         {
             return "notexist";
         }
         
         itr = null;
         
         String hql3 = "select count(*) from " + tablename + " where Year(getDate())= Year(createdOn) and dealerCode='" + dealercode + "'";
         query1 = hrbsession.createQuery(hql3);
         itr = query1.list().iterator();
         if (itr.hasNext()) {
             uniqueid = (Long) itr.next();
         }
         uniqueid = uniqueid + 1;
         int currentYear = LocalDate.now().getYear();
         int lastTwoDigits = currentYear % 100;
         String leadingZeroesWithUniqueId = String.format("%05d", uniqueid);
         number = prefix + "/" + lastTwoDigits +  "/" + leadingZeroesWithUniqueId;
         return number;
     }
     
     public static boolean uploadFileForTaxInvoice(String fileName, String filePath, FormFile uploadFile, String realPath) {
    	    boolean isUploaded = false;
    	    if (fileName != null && !fileName.isEmpty()) {
    	        File targetFile = new File(filePath, fileName);

    	        try (FileOutputStream fileOutStream = new FileOutputStream(targetFile)) {
    	            byte[] fileData = uploadFile.getFileData();

    	            // Validate that fileData is not null and has data
    	            if (fileData != null && fileData.length > 0) {
    	                fileOutStream.write(fileData);
    	                fileOutStream.flush();
    	                isUploaded = true;
    	            } else {
    	                System.err.println("File data is empty or null.");
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	            isUploaded = false;
    	        }
    	    } else {
    	        System.err.println("File name is null or empty.");
    	    }
    	    return isUploaded;
    	}

     public static LinkedHashSet<LabelValueBean> getOrderTypes(Integer flag) {

    	    Session sess = HibernateUtil.getSessionFactory().openSession();
    	    LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();

    	    try {
    	        // Using EXEC
    	        String sql = "EXEC SP_GetOrderTypes :flag";

    	        Query query = sess.createSQLQuery(sql);
    	        query.setParameter("flag", flag); 

    	        List<Object[]> list = query.list();

    	        for (Object[] row : list) {

    	            String orderTypeCode = (String) row[0];      // OrderTypeCode
    	            String orderTypeName = (String) row[1];      // OrderTypeScreenName

    	            result.add(new LabelValueBean(orderTypeName, orderTypeCode));
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        sess.close();
    	    }

    	    return result;
    	}


 
}
