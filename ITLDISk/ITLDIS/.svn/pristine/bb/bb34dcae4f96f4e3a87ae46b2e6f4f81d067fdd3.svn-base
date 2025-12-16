/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import HibernateUtil.HibernateUtil;
import beans.ReportForm;

/**
 *
 * @author satendra.aditya
 */
public class ReportDao {

    public void getJobTypes(String dealerCode, String fromDate, String toDate, ReportForm rptForm, Session session) {
        int jobCardTypeMTDTotal = 0;
        int jobCardTypeYTMTotal = 0;


        try {
            Query query = session.createSQLQuery("EXEC SP_DMIS_JobTypes :dealerCode,:fromDate,:toDate");
            query.setParameter("dealerCode", dealerCode);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List resultList = query.list();
            Iterator iterator = resultList.iterator();
            String[] jobTypesDescription = new String[resultList.size()];
            String[] monthCount = new String[resultList.size()];
            String[] yearCount = new String[resultList.size()];
            int i = 0;
            while (iterator.hasNext()) {
                Object[] rows = (Object[]) iterator.next();
                jobTypesDescription[i] = rows[0] == null ? "" : rows[0].toString().trim();
                monthCount[i] = rows[1] == null ? "" : rows[1].toString().trim();
                yearCount[i] = rows[2] == null ? "" : rows[2].toString().trim();
                if (rows[1] != null) {
                    jobCardTypeMTDTotal = jobCardTypeMTDTotal + Integer.parseInt(rows[1].toString().trim());
                }
                if (rows[2] != null) {
                    jobCardTypeYTMTotal = jobCardTypeYTMTotal + Integer.parseInt(rows[2].toString().trim());
                }

                i = i + 1;
            }
            rptForm.setJobTypeDesc(jobTypesDescription);
            rptForm.setMonthCount(monthCount);
            rptForm.setYearCount(yearCount);
            rptForm.setDmisJobTypeTablelColength(i + 1);
            rptForm.setJobCardTypeMTDTotal(jobCardTypeMTDTotal);
            rptForm.setJobCardTypeYTMTotal(jobCardTypeYTMTotal);
            getJobTypeInstallation(dealerCode, fromDate, toDate, rptForm, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getJobTypeInstallation(String dealerCode, String fromDate, String toDate, ReportForm rptForm, Session session) {
        try {
            Query query = session.createSQLQuery("EXEC SP_DMIS_Installations :dealerCode,:fromDate,:toDate");
            query.setParameter("dealerCode", dealerCode);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List resultList = query.list();
            Iterator iterator = resultList.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                Object[] rows = (Object[]) iterator.next();
                rptForm.setMonthDeliveryCnt(rows[0] == null ? "0" : rows[0].toString().trim());
                rptForm.setMonthInsCnt(rows[1] == null ? "0" : rows[1].toString().trim());
                rptForm.setMonthInvalidCnt(rows[2] == null ? "0" : rows[2].toString().trim());
                rptForm.setMonthInvalidPer(rows[3] == null ? "0" : rows[3].toString().trim());
                rptForm.setMonthPendingIns(rows[4] == null ? "0" : rows[4].toString().trim());
                rptForm.setMonthPendingPer(rows[5] == null ? "0" : rows[5].toString().trim());

                rptForm.setYearDeliveryCnt(rows[6] == null ? "0" : rows[6].toString().trim());
                rptForm.setYearInsCnt(rows[7] == null ? "0" : rows[7].toString().trim());
                rptForm.setYearInvalidCnt(rows[8] == null ? "0" : rows[8].toString().trim());
                rptForm.setYearInvalidPer(rows[9] == null ? "0" : rows[9].toString().trim());
                rptForm.setYearPendingIns(rows[10] == null ? "0" : rows[10].toString().trim());
                rptForm.setYearPendingPer(rows[11] == null ? "0" : rows[11].toString().trim());
            }
            getNoOfJobCardTractorAttendedAndLabourEarned(dealerCode, fromDate, toDate, rptForm, session);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getNoOfJobCardTractorAttendedAndLabourEarned(String dealerCode, String fromDate, String toDate, ReportForm rptForm, Session session) {
        double totalMonthCount = 0;
        double totalYearCount = 0;
        double totalMonthCountLabour = 0;
        double totalYearCountLabour = 0;

        try {
            Query query = session.createSQLQuery("EXEC SP_DMIS_JobCards :dealerCode,:fromDate,:toDate");
            query.setParameter("dealerCode", dealerCode);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List resultList = query.list();
            Iterator iterator = resultList.iterator();

            String[] jobCardColumnName = new String[resultList.size()];
            String[] jobCardMonthCount = new String[resultList.size()];
            String[] jobCardMonthLabour = new String[resultList.size()];
            String[] jobCadYearCount = new String[resultList.size()];
            String[] jobCardYearLabour = new String[resultList.size()];

            int i = 0;
            while (iterator.hasNext()) {
                Object[] rows = (Object[]) iterator.next();
                jobCardColumnName[i] = rows[0] == null ? "0" : rows[0].toString().trim();
                jobCardMonthCount[i] = rows[1] == null ? "0" : rows[1].toString().trim();
                jobCardMonthLabour[i] = rows[2] == null ? "0" : rows[2].toString().trim();
                jobCadYearCount[i] = rows[3] == null ? "0" : rows[3].toString().trim();
                jobCardYearLabour[i] = rows[4] == null ? "0" : rows[4].toString().trim();

                if (jobCardMonthCount[i] != null) {
                    totalMonthCount = totalMonthCount + Double.parseDouble(jobCardMonthCount[i].toString().trim());
                }

                if (jobCadYearCount[i] != null) {
                    totalYearCount = totalYearCount + Double.parseDouble(jobCadYearCount[i].toString().trim());
                }

                if (jobCardMonthLabour[i] != null) {
                    totalMonthCountLabour = totalMonthCountLabour + Double.parseDouble(jobCardMonthLabour[i].toString().trim());
                }
                if (jobCardYearLabour[i] != null) {
                    totalYearCountLabour = totalYearCountLabour + Double.parseDouble(jobCardYearLabour[i].toString().trim());
                }
                i = i + 1;

            }
            rptForm.setJobCardColumnName(jobCardColumnName);
            rptForm.setJobCardMonthCount(jobCardMonthCount);
            rptForm.setJobCardMonthLabour(jobCardMonthLabour);
            rptForm.setJobCadYearCount(jobCadYearCount);
            rptForm.setJobCardYearLabour(jobCardYearLabour);
            rptForm.setNoOfColumnOfJobCard(i + 1);
            rptForm.setTotalmonthCount_jobCard(totalMonthCount);
            rptForm.setTotalmonthCount_jobCard_labour(totalMonthCountLabour);
            rptForm.setTotalyearCount_jobCard(totalYearCount);
            rptForm.setTotalyearCount_jobCard_labour(totalYearCountLabour);
            getSparesLubes(dealerCode, fromDate, toDate, rptForm, session);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getSparesLubes(String dealerCode, String fromDate, String toDate, ReportForm rptForm, Session session) {
        try {
            Query query = session.createSQLQuery("EXEC SP_DMIS_SPARELUBE :dealerCode,:fromDate,:toDate");
            query.setParameter("dealerCode", dealerCode);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List resultList = query.list();
            rptForm.setListOfLubes(resultList);
            getWarrantyClaimed(dealerCode, fromDate, toDate, rptForm, session);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getWarrantyClaimed(String dealerCode, String fromDate, String toDate, ReportForm rptForm, Session session) {
        try {
            List listMonth = new ArrayList();
            List listYear = new ArrayList();
            Query query = session.createSQLQuery("EXEC SP_DMIS_Claims :dealerCode,:fromDate,:toDate");
            query.setParameter("dealerCode", dealerCode);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                listMonth.add(obj[0] == null ? "0" : obj[0].toString().trim());
                listMonth.add(obj[1] == null ? "0" : obj[1].toString().trim());
                listMonth.add(obj[2] == null ? "0" : obj[2].toString().trim());
                listMonth.add(obj[3] == null ? "0" : obj[3].toString().trim());
                listYear.add(obj[4] == null ? "0" : obj[4].toString().trim());
                listYear.add(obj[5] == null ? "0" : obj[5].toString().trim());
                listYear.add(obj[6] == null ? "0" : obj[6].toString().trim());
                listYear.add(obj[7] == null ? "0" : obj[7].toString().trim());
            }
            rptForm.setListOfWarrantyClaimMonth(listMonth);
            rptForm.setListOfWarrantyClaimYear(listYear);
            getDealerName(dealerCode, rptForm, session);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void getDealerName(String dealerCode, ReportForm rptForm, Session session) {
        try {
            Query query = session.createQuery("select a.dealerCode+' ['+a.dealerName+']' from Dealervslocationcode a where a.dealerCode=:dealerCode");
            query.setParameter("dealerCode", dealerCode);
            List dealerName = query.list();
            for (Object object : dealerName) {
                if (object != null) {
                    rptForm.setDealerNameWithCode(object.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public ArrayList<String> getHierarchyList(String user_id, String flag) {

        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<String> country = new ArrayList<String>();
        try {
            Query query = hrbSession.createSQLQuery("select distinct country from FN_GetHierrachyDetailsUnderUser(?) where FlAG=?");
            query.setString(0, user_id);
            query.setString(1, flag);
            List result = query.list();
            Iterator iterator = result.iterator();

            while (iterator.hasNext()) {
                Object rows = (Object) iterator.next();
                if (!country.contains(rows == null ? "" : rows.toString().trim())) {
                    country.add(rows == null ? "" : rows.toString().trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return country;
    }

    public static String getListOnSelect(String user_id, String getColumns, String whereColumn, String whereColVal, String flag) {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        StringBuilder strb = new StringBuilder("");
        try {

            whereColVal = whereColVal.replaceAll("@", " ");

            Query query = sess.createSQLQuery("select distinct " + getColumns
                    + " from FN_GetHierrachyDetailsUnderUser(?) where " + whereColumn + " = ? and FLAG=?");

            query.setString(0, user_id);
            query.setString(1, whereColVal);
            query.setString(2, flag);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();

                strb.append(obj[0] == null ? "NA" : obj[0].toString().trim());
                strb.append("@");
                if (obj.length > 2) {
                    strb.append("[" + (obj[1] == null ? "NA" : obj[1].toString().trim()) + "]" + "[" + (obj[2] == null ? "NA" : obj[2].toString().trim()) + "]");
                } else {
                    strb.append(obj[1] == null ? "NA" : obj[1].toString().trim());
                }
                strb.append("@");
            }
            if (!strb.toString().equals("")) {
                strb.deleteCharAt(strb.lastIndexOf("@"));
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                    sess = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strb.toString();
    }

    public ArrayList<String> getViewOrderInvoiceList(ReportForm iForm, Vector userFunctionalities, String radio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList<String> dealerList = new ArrayList<String>();
        DecimalFormat decf = new DecimalFormat("#.00");
        Double qty = 0.0;
        Double amount = 0.0;
        Query qry = null;
        try {
            if (radio.equals("part Wise")) {
                qry = session.createSQLQuery(" EXEC PROC_PartWiseSalesSummary :COUNTRYCODE, :DELAERCODE, :INVOICEFROMDATE, :INVOICETODATE, :PARTNO, :INVOICENO, :PINO").addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DEALERNAME", StringType.INSTANCE).addScalar("COUNTRY_NAME", StringType.INSTANCE).addScalar("SHIPPED_PART_NO", StringType.INSTANCE).addScalar("PART_DESC", StringType.INSTANCE).addScalar("QTY_SHIPPED", StringType.INSTANCE);
                //EXEC PROC_PartWiseSalesSummary '','','','','','ALL'

                qry.setParameter("COUNTRYCODE", iForm.getCountry().split("@@")[0] == null ? "" : iForm.getCountry().split("@@")[0]);
                qry.setParameter("DELAERCODE", iForm.getDealerCode() == null ? "" : iForm.getDealerCode());
                qry.setParameter("INVOICEFROMDATE", (iForm.getFromdate() == null || iForm.getFromdate().equals("")) ? "" : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                qry.setParameter("INVOICETODATE", (iForm.getTodate() == null || iForm.getTodate().equals("")) ? "" : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
                qry.setParameter("PARTNO", iForm.getPartnum());
                qry.setParameter("INVOICENO", iForm.getInvNo());
                qry.setParameter("PINO", iForm.getPiNo());

                List result = qry.list();
                Iterator itr = result.iterator();
                while (itr.hasNext()) {
                    Object[] object = (Object[]) itr.next();
                    dealerList.add(object[0] == null ? "-" : object[0].toString().trim());
                    dealerList.add(object[1] == null ? "-" : object[1].toString().trim());
                    dealerList.add(object[2] == null ? "-" : object[2].toString().trim());
                    dealerList.add(object[3] == null ? "-" : object[3].toString().trim());
                    dealerList.add(object[4] == null ? "-" : object[4].toString().trim());
                    dealerList.add(object[5] == null ? "-" : object[5].toString().trim());
                }
            } else {
                qry = session.createSQLQuery(" EXEC PROC_CustomerWiseSalesSummary :COUNTRYCODE, :DELAERCODE, :INVOICEFROMDATE, :INVOICETODATE, :PARTNO, :INVOICENO, :PINO").addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DEALERNAME", StringType.INSTANCE).addScalar("COUNTRY_NAME", StringType.INSTANCE).addScalar("INVOICE_NO", StringType.INSTANCE).addScalar("INVOICE_DATE", StringType.INSTANCE).addScalar("INV_CURRENCY", StringType.INSTANCE).addScalar("OTHCHARGES", StringType.INSTANCE).addScalar("TOTAL_INVOICE_AMOUNT", StringType.INSTANCE).addScalar("DISPATCH_MODE", StringType.INSTANCE).addScalar("AWB_BOL_NO", StringType.INSTANCE).addScalar("AWB_BOL_DATE", StringType.INSTANCE);


                qry.setParameter("COUNTRYCODE", iForm.getCountry().split("@@")[0] == null ? "" : iForm.getCountry().split("@@")[0]);
                qry.setParameter("DELAERCODE", iForm.getDealerCode() == null ? "" : iForm.getDealerCode());
                qry.setParameter("INVOICEFROMDATE", (iForm.getFromdate() == null || iForm.getFromdate().equals("")) ? "" : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                qry.setParameter("INVOICETODATE", (iForm.getTodate() == null || iForm.getTodate().equals("")) ? "" : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
                qry.setParameter("PARTNO", iForm.getPartnum());
                qry.setParameter("INVOICENO", iForm.getInvNo());
                qry.setParameter("PINO", iForm.getPiNo());

                List result = qry.list();
                Iterator itr = result.iterator();
                while (itr.hasNext()) {
                    Object[] object = (Object[]) itr.next();
                    dealerList.add(object[0] == null ? "-" : object[0].toString().trim());
                    dealerList.add(object[1] == null ? "-" : object[1].toString().trim());
                    dealerList.add(object[2] == null ? "-" : object[2].toString().trim());
                    dealerList.add(object[3] == null ? "-" : object[3].toString().trim());
                    dealerList.add(object[4] == null ? "-" : sdf1.format(df.parse(object[4].toString().trim())));
                    dealerList.add(object[5] == null ? "-" : object[5].toString().trim());
                    dealerList.add(object[7] == null ? "-" : object[7].toString().trim());
                    dealerList.add(object[6].toString().equals("0.0") ? "-" : decf.format(Double.parseDouble(object[6].toString())));
                    dealerList.add(object[8] == null ? "-" : object[8].toString().trim());
                    dealerList.add(object[9] == null ? "-" : object[9].toString().trim());
                    dealerList.add(object[10] == null ? "-" : object[10].toString().trim());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dealerList;
    }

    public ArrayList<String> getPedningPIConfirmation(ReportForm iForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList<String> dealerList = new ArrayList<String>();
        DecimalFormat decf = new DecimalFormat("#.00");
        Query qry = null;
        try {
            qry = session.createSQLQuery(" EXEC PROC_PendingPIAtBuyerReport :COUNTRYCODE, :DELAERCODE, :PIFROMDATE, :PITODATE, :PARTNO, :PINO").addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DEALERNAME", StringType.INSTANCE).addScalar("COUNTRY_NAME", StringType.INSTANCE).addScalar("CUST_PO_NO", StringType.INSTANCE).addScalar("PI_NO", StringType.INSTANCE).addScalar("PI_DATE", StringType.INSTANCE).addScalar("PART_NO", StringType.INSTANCE).addScalar("PARTDESC", StringType.INSTANCE).addScalar("LEAD_TIME", StringType.INSTANCE).addScalar("FINAL_PI_QTY", StringType.INSTANCE).addScalar("PI_CURRENCY", StringType.INSTANCE);


            qry.setParameter("COUNTRYCODE", iForm.getCountry().split("@@")[0] == null ? "" : iForm.getCountry().split("@@")[0]);
            qry.setParameter("DELAERCODE", iForm.getDealerCode() == null ? "" : iForm.getDealerCode());
            qry.setParameter("PIFROMDATE", (iForm.getFromdate() == null || iForm.getFromdate().equals("")) ? "" : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
            qry.setParameter("PITODATE", (iForm.getTodate() == null || iForm.getTodate().equals("")) ? "" : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
            qry.setParameter("PARTNO", iForm.getPartnum());
            qry.setParameter("PINO", iForm.getPiNo());

            List result = qry.list();
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Object[] object = (Object[]) itr.next();
                dealerList.add(object[0] == null ? "-" : object[0].toString().trim());//DEALER_CODE
                dealerList.add(object[1] == null ? "-" : object[1].toString().trim());//DEALERNAME
                dealerList.add(object[2] == null ? "-" : object[2].toString().trim());//COUNTRY_NAME
                dealerList.add(object[3] == null ? "-" : object[3].toString().trim());//CUST_PO_NO
                dealerList.add(object[4] == null ? "-" : object[4].toString().trim());//PI_NO
                dealerList.add(object[5] == null ? "-" : sdf1.format(df.parse(object[5].toString().trim())));//PI_DATE
                dealerList.add(object[6] == null ? "-" : object[6].toString().trim());//PART_NO
                dealerList.add(object[7] == null ? "-" : object[7].toString().trim());//PARTDESC
                dealerList.add(object[8] == null ? "-" : object[8].toString().trim());//LEAD_TIME
                dealerList.add(object[9] == null ? "-" : object[9].toString().trim());//QTY
                //dealerList.add(object[10] == null ? "-" : object[10].toString().trim()); //PIH.PI_CURRENCY
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dealerList;
    }

    public ArrayList<ReportForm> getViewOrderList(ReportForm rForm, Vector userFunctionalities, Connection con) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<ReportForm> orderList = new ArrayList<ReportForm>();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs = null;
//        String Procedure = rForm.getCce().equals("ordDet") ? "Sp_viewOrderDetailReport" : "Sp_viewInvoiceDetailReport";



        try {



            CallableStatement cs = con.prepareCall("{call Sp_viewOrderInvDetailReport(?,?,?,?,?,?,?,?)}");
            cs.setString(1, rForm.getOrderType());
            cs.setString(2, rForm.getStatus());
            cs.setString(3, rForm.getRange());
            cs.setDate(4, rForm.getFromdate().equals("") ? null : new java.sql.Date(sdf1.parse(rForm.getFromdate()).getTime()));
            cs.setDate(5, rForm.getTodate().equals("") ? null : new java.sql.Date(sdf1.parse(rForm.getTodate()).getTime()));
//            cs.setString(5, rForm.getTodate().equals("") ? "" : df.format(sdf.parse(rForm.getTodate() + " 23:59")));
            cs.setString(6, rForm.getDealerCode());
            cs.setString(7, rForm.getUserid());
            cs.setString(8, rForm.getCce());

            rs = cs.executeQuery();
            while (rs.next()) {

                ReportForm repForm = new ReportForm();
                repForm.setDealerCode(rs.getString("DEALER_CODE") == null ? "-" : rs.getString("DEALER_CODE"));
                repForm.setDealerName(rs.getString("dealerName") == null ? "-" : rs.getString("dealerName"));
                repForm.setLocation(rs.getString("location") == null ? "-" : rs.getString("location"));
                repForm.setStockistName(rs.getString("STOCKIST_NAME") == null ? "-" : rs.getString("STOCKIST_NAME"));
                repForm.setNewPoNo(rs.getString("CUST_PO_NO") == null ? "-" : rs.getString("CUST_PO_NO").trim());
                repForm.setPoDate(rs.getString("CUST_PO_DATE") == null ? "-" : sdf1.format(df.parse(rs.getString("CUST_PO_DATE").trim())));
                repForm.setOrderType(rs.getString("ORD_TYPE") == null ? "-" : rs.getString("ORD_TYPE").trim());
                repForm.setErpOrderNo(rs.getString("AD_ORDER_NO") == null ? "-" : rs.getString("AD_ORDER_NO"));
                repForm.setErpOrderDate(rs.getString("AD_ORDER_DATE") == null ? "-" : sdf1.format(df.parse(rs.getString("AD_ORDER_DATE").trim())));
                repForm.setErpRemarks(rs.getString("ERP_REMARKS") == null ? "-" : rs.getString("ERP_REMARKS"));
                repForm.setPartno(rs.getString("ORDERED_PART") == null ? "-" : rs.getString("ORDERED_PART"));
                repForm.setPart_desc(rs.getString("ORDERED_PART_DESC") == null ? "-" : rs.getString("ORDERED_PART_DESC"));
                repForm.setQty(rs.getString("ORDERED_QTY") == null ? "0" : rs.getString("ORDERED_QTY"));
                repForm.setBackOrdQty(rs.getString("BACK_ORDER_QTY") == null ? "0" : rs.getString("BACK_ORDER_QTY"));

                repForm.setInvNo(rs.getString("INVOICE_NO") == null ? "-" : rs.getString("INVOICE_NO").trim());
                repForm.setInvoiceDate(rs.getString("INVOICE_DATE") == null ? "-" : sdf1.format(df.parse(rs.getString("INVOICE_DATE").trim())));
                repForm.setTotalAmont(rs.getString("TOTAL_INVOICE_AMOUNT") == null ? "-" : rs.getString("TOTAL_INVOICE_AMOUNT"));

                repForm.setStatus(rs.getString("Ship_Status") == null ? "-" : rs.getString("Ship_Status"));
                repForm.setShippedpartno(rs.getString("SHIPPED_PART_NO") == null ? "-" : rs.getString("SHIPPED_PART_NO"));
                repForm.setShippedpartDesc(rs.getString("SHIPPED_PART_DESC") == null ? "-" : rs.getString("SHIPPED_PART_DESC"));
                repForm.setShippedQty(rs.getString("QTY_SHIPPED") == null ? "-" : rs.getString("QTY_SHIPPED"));

                repForm.setInvoiceRate(rs.getString("INVOICED_RATE") == null ? "0" : rs.getString("INVOICED_RATE"));
                repForm.setPartInvoiceValue(rs.getString("PART_INVOICE_VALUE") == null ? "-" : rs.getString("PART_INVOICE_VALUE"));

                repForm.setShipmentDate(rs.getString("SHIPMENT_DATE") == null ? "-" : sdf1.format(df1.parse(rs.getString("SHIPMENT_DATE").trim())));
                repForm.setLrNo(rs.getString("LR_NO") == null ? "-" : rs.getString("LR_NO"));
                repForm.setTransporterName(rs.getString("TRANSPORTER_NAME") == null ? "-" : rs.getString("TRANSPORTER_NAME"));
                repForm.setPermitNo(rs.getString("PERMIT_NO") == null ? "-" : rs.getString("PERMIT_NO"));
                repForm.setOrderStatus(rs.getString("ORD_Status") == null ? "-" : rs.getString("ORD_Status"));
                repForm.setJobCardNo(rs.getString("JOB_CARD_NO") == null ? "-" : rs.getString("JOB_CARD_NO"));
                repForm.setJobCardDate(rs.getString("JobCardDate") == null ? "-" : sdf1.format(df.parse(rs.getString("JobCardDate").trim())));
//                repForm.setQty(rs.getString("ORDERED_QTY") == null ? "0" : rs.getString("ORDERED_QTY"));
//                repForm.setStockistId(rs.getString("STOCKIST_ID") == null ? "-" : rs.getString("STOCKIST_ID"));

                orderList.add(repForm);


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

    public Map<String, List<Object[]>> gstrDetails(ReportForm gstrDetail) {
        Map<String, List<Object[]>> map = new LinkedHashMap<String, List<Object[]>>();
        Session session = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            if (gstrDetail.getOrderType() != null && gstrDetail.getOrderType().equals("GSTR-1")) {
                List<Object[]> l1 = session.createSQLQuery("Exec SP_GSTR_1_REPORT :dealerCode, :fromDate, :toDate, :type")
                                    .addScalar("GSTIN/UIN of Recipient", StringType.INSTANCE).addScalar("Invoice Number", StringType.INSTANCE)
                                    .addScalar("Invoice date", DateType.INSTANCE).addScalar("Invoice Value", BigDecimalType.INSTANCE)
                                    .addScalar("Place Of Supply", StringType.INSTANCE).addScalar("Reverse Charge", StringType.INSTANCE)
                                    .addScalar("Tax Rate", BigDecimalType.INSTANCE).addScalar("Invoice Type", StringType.INSTANCE)
                                    .addScalar("E-Commerce GSTIN", StringType.INSTANCE).addScalar("Rate", BigDecimalType.INSTANCE)
                                    .addScalar("Taxable Value", BigDecimalType.INSTANCE).addScalar("Cess Amount", BigDecimalType.INSTANCE)
                                    .addScalar("Dealer_Code", StringType.INSTANCE)
                                    .setParameter("dealerCode", gstrDetail.getDealerCode())
                                    .setParameter("fromDate", df.parse(gstrDetail.getFromDate()))
                                    .setParameter("toDate", df.parse(gstrDetail.getToDate()))
                                    .setParameter("type", "B2B").list();

                List<Object[]> l2 = session.createSQLQuery("Exec SP_GSTR_1_REPORT :dealerCode, :fromDate, :toDate, :type")
                                    .addScalar("HSN", StringType.INSTANCE).addScalar("Description", StringType.INSTANCE)
                                    .addScalar("UQC", StringType.INSTANCE).addScalar("Total Quantity", IntegerType.INSTANCE)
                                    .addScalar("Total Value", BigDecimalType.INSTANCE).addScalar("Taxable Value", BigDecimalType.INSTANCE)
                                    .addScalar("Integrated Tax Amount", BigDecimalType.INSTANCE).addScalar("Central Tax Amount", BigDecimalType.INSTANCE)
                                    .addScalar("State Tax Amount", BigDecimalType.INSTANCE).addScalar("Cess Amount", BigDecimalType.INSTANCE)
                                    .setParameter("dealerCode", gstrDetail.getDealerCode())
                                    .setParameter("fromDate", df.parse(gstrDetail.getFromDate()))
                                    .setParameter("toDate", df.parse(gstrDetail.getToDate()))
                                    .setParameter("type", "HSN").list();
                                     map.put("B2B", l1);
                                     map.put("HSN", l2);
            } else if (gstrDetail.getOrderType() != null && gstrDetail.getOrderType().equals("GSTR-2")) {
                List<Object[]> l1 = session.createSQLQuery("Exec SP_GSTR_2_REPORT :dealerCode, :fromDate, :toDate, :type")
                                    .addScalar("GSTIN/UIN of Recipient", StringType.INSTANCE).addScalar("Invoice Number", StringType.INSTANCE)
                                    .addScalar("Invoice date", DateType.INSTANCE).addScalar("Invoice Value", BigDecimalType.INSTANCE)
                                    .addScalar("Place Of Supply", StringType.INSTANCE).addScalar("Reverse Charge", StringType.INSTANCE)
                                    .addScalar("Tax Rate", BigDecimalType.INSTANCE).addScalar("Invoice Type", StringType.INSTANCE)
                                    .addScalar("E-Commerce GSTIN", StringType.INSTANCE).addScalar("Rate", BigDecimalType.INSTANCE)
                                    .addScalar("Taxable Value", BigDecimalType.INSTANCE).addScalar("Integrated Tax Paid", BigDecimalType.INSTANCE)
                                    .addScalar("Central Tax Paid", BigDecimalType.INSTANCE).addScalar("State/UT Tax Paid", BigDecimalType.INSTANCE)
                                    .addScalar("Cess Paid", BigDecimalType.INSTANCE).addScalar("Eligibility For ITC", StringType.INSTANCE)
                                    .addScalar("Availed ITC Integrated Tax", BigDecimalType.INSTANCE).addScalar("Availed ITC Central Tax", BigDecimalType.INSTANCE)
                                    .addScalar("Availed ITC State/UT Tax", BigDecimalType.INSTANCE).addScalar("Availed ITC Cess", BigDecimalType.INSTANCE)

                                    .setParameter("dealerCode", gstrDetail.getDealerCode())
                                    .setParameter("fromDate", df.parse(gstrDetail.getFromDate()))
                                    .setParameter("toDate", df.parse(gstrDetail.getToDate()))
                                    .setParameter("type", "B2B").list();

                List<Object[]> l2 = session.createSQLQuery("Exec SP_GSTR_2_REPORT :dealerCode, :fromDate, :toDate, :type")
                                    .addScalar("HSN", StringType.INSTANCE).addScalar("Description", StringType.INSTANCE)
                                    .addScalar("UQC", StringType.INSTANCE).addScalar("Total Quantity", IntegerType.INSTANCE)
                                    .addScalar("Total Value", BigDecimalType.INSTANCE).addScalar("Taxable Value", BigDecimalType.INSTANCE)
                                    .addScalar("Integrated Tax Amount", BigDecimalType.INSTANCE).addScalar("Central Tax Amount", BigDecimalType.INSTANCE)
                                    .addScalar("State Tax Amount", BigDecimalType.INSTANCE).addScalar("Cess Amount", BigDecimalType.INSTANCE)

                                    .setParameter("dealerCode", gstrDetail.getDealerCode())
                                    .setParameter("fromDate", df.parse(gstrDetail.getFromDate()))
                                    .setParameter("toDate", df.parse(gstrDetail.getToDate()))
                                    .setParameter("type", "HSN").list();
                                     map.put("B2B", l1);
                                     map.put("HSN", l2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return map;
    }



    public void GSTR_1Report(HttpServletResponse response, HttpServletRequest request, Map<String, List<Object[]>> data, String fileName, String filePath) throws ClassNotFoundException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

        //String filePath = request.getServletContext().getRealPath("/WEB-INF/template/GSTR-1_2018-19.xls");
        File fileOnServer = new File(filePath);
        FileInputStream fis = new FileInputStream(fileOnServer);

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet worksheet = workbook.getSheetAt(1);

        List<Object[]> b2b = data.get("B2B");

        int startRowIndex = 4;

        removeRow(worksheet, startRowIndex);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        if (b2b != null) {
            // Create body
            for (int i = 0; i < b2b.size(); i++) {
                Object[] obj = b2b.get(i);

                // Create a new row
                Row row = worksheet.createRow((Integer) startRowIndex);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue((String) obj[0]);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue((String) obj[1]);

                Cell cell2 = row.createCell(2);
                String datestr = df.format((Date) obj[2]);
                cell2.setCellValue(datestr);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(((BigDecimal) obj[3]).doubleValue());

                Cell cell4 = row.createCell(4);
                cell4.setCellValue((String) obj[4]);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue((String) obj[5]);

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(((BigDecimal) obj[6]).doubleValue());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue((String) obj[7]);

                Cell cell8 = row.createCell(8);
                cell8.setCellValue((String) obj[8]);

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(((BigDecimal) obj[9]).doubleValue());

                Cell cell10 = row.createCell(10);
                cell10.setCellValue(((BigDecimal) obj[10]).doubleValue());

                Cell cell11 = row.createCell(11);
                cell11.setCellValue(((BigDecimal) obj[11]).doubleValue());

                startRowIndex++;
            }
        }

        startRowIndex = 4;

        Sheet worksheet_hsn = workbook.getSheetAt(2);

        removeRow(worksheet_hsn, startRowIndex);

        List<Object[]> hsn = data.get("HSN");
        if (hsn != null) {
            // Create body
            for (int i = 0; i < hsn.size(); i++) {
                Object[] obj = hsn.get(i);

                // Create a new row
                Row row = worksheet_hsn.createRow((Integer) startRowIndex);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue((String) obj[0]);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue((String) obj[1]);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue((String) obj[2]);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue((Integer) obj[3]);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(((BigDecimal) obj[4]).doubleValue());

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(((BigDecimal) obj[5]).doubleValue());

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(((BigDecimal) obj[6]).doubleValue());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(((BigDecimal) obj[7]).doubleValue());

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(((BigDecimal) obj[8]).doubleValue());

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(((BigDecimal) obj[9]).doubleValue());

                startRowIndex++;
            }
        }
        // 6. Set the response properties
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        //7. Write to the output stream
        ServletOutputStream outputStream;

        outputStream = response.getOutputStream();
        // Write to the output stream
        worksheet.getWorkbook().write(outputStream);
        // Flush the stream
        outputStream.flush();


    }
    public void GSTR_2Report(HttpServletResponse response, HttpServletRequest request, Map<String, List<Object[]>> data, String fileName, String filePath) throws ClassNotFoundException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

        //String filePath = request.getServletContext().getRealPath("/WEB-INF/template/GSTR-1_2018-19.xls");
        File fileOnServer = new File(filePath);
        FileInputStream fis = new FileInputStream(fileOnServer);

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet worksheet = workbook.getSheetAt(1);

        List<Object[]> b2b = data.get("B2B");

        int startRowIndex = 4;

        removeRow(worksheet, startRowIndex);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        if (b2b != null) {
            // Create body
            for (int i = 0; i < b2b.size(); i++) {
                Object[] obj = b2b.get(i);

                // Create a new row
                Row row = worksheet.createRow((Integer) startRowIndex);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue((String) obj[0]);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue((String) obj[1]);

                Cell cell2 = row.createCell(2);
                String datestr = df.format((Date) obj[2]);
                cell2.setCellValue(datestr);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(((BigDecimal) obj[3]).doubleValue());

                Cell cell4 = row.createCell(4);
                cell4.setCellValue((String) obj[4]);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue((String) obj[5]);

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(((BigDecimal) obj[6]).doubleValue());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue((String) obj[7]);

                Cell cell8 = row.createCell(8);
                cell8.setCellValue((String) obj[8]);

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(((BigDecimal) obj[9]).doubleValue());

                Cell cell10 = row.createCell(10);
                cell10.setCellValue(((BigDecimal) obj[10]).doubleValue());

                Cell cell11 = row.createCell(11);
                cell11.setCellValue(((BigDecimal) obj[11]).doubleValue());

                startRowIndex++;
            }
        }

        startRowIndex = 4;

        Sheet worksheet_hsn = workbook.getSheetAt(2);

        removeRow(worksheet_hsn, startRowIndex);

        List<Object[]> hsn = data.get("HSN");
        if (hsn != null) {
            // Create body
            for (int i = 0; i < hsn.size(); i++) {
                Object[] obj = hsn.get(i);

                // Create a new row
                Row row = worksheet_hsn.createRow((Integer) startRowIndex);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue((String) obj[0]);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue((String) obj[1]);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue((String) obj[2]);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue((Integer) obj[3]);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(((BigDecimal) obj[4]).doubleValue());

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(((BigDecimal) obj[5]).doubleValue());

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(((BigDecimal) obj[6]).doubleValue());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(((BigDecimal) obj[7]).doubleValue());

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(((BigDecimal) obj[8]).doubleValue());

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(((BigDecimal) obj[9]).doubleValue());

                startRowIndex++;
            }
        }
        // 6. Set the response properties
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        //response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        //7. Write to the output stream
        ServletOutputStream outputStream;

        outputStream = response.getOutputStream();
        // Write to the output stream
        worksheet.getWorkbook().write(outputStream);
        // Flush the stream
        outputStream.flush();


    }

   public static void removeRow(Sheet sheet, int rowIndex) {
           int lastRowNum = sheet.getLastRowNum();
           if (rowIndex >= 0 && rowIndex < lastRowNum) {
               sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
           }
           if (rowIndex == lastRowNum) {
               Row removingRow = sheet.getRow(rowIndex);
               if (removingRow != null) {
                   sheet.removeRow(removingRow);
               }
           }
       }



}
