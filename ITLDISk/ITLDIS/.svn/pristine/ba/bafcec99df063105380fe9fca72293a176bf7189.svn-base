package dao;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;

import com.common.DBQueryConstant;
import com.common.MailSender;
import com.common.MethodUtility;
import com.itextpdf.text.pdf.PdfWriter;

//import Acme.Serve.servlet.http.HttpSession;
import HibernateMapping.Actualslabourcharges;
import HibernateMapping.Actualsothercharges;
import HibernateMapping.ApplicationMaster;
import HibernateMapping.Baymaster;
import HibernateMapping.Billmaster;
import HibernateMapping.Causalcodemaster;
import HibernateMapping.Complaintcodemaster;
import HibernateMapping.Consequencemaster;
import HibernateMapping.Dealervslocationcode;
import HibernateMapping.EWMLoaderDetail;
import HibernateMapping.Estimatelabourcharges;
import HibernateMapping.Estimateothercharges;
import HibernateMapping.ITLEWMLoaderDetail;
import HibernateMapping.ItlContentMaster;
import HibernateMapping.ItlSubContentMaster;
import HibernateMapping.JobcardSparesActualcharges;
import HibernateMapping.JobcardSparesCharges;
import HibernateMapping.Jobcarddetails;
import HibernateMapping.Jobcardstatusmaster;
import HibernateMapping.Jobchecklist;
import HibernateMapping.JobchecklistPK;
import HibernateMapping.Jobcomplaintdetails;
import HibernateMapping.Joblocationmaster;
import HibernateMapping.Jobtypemaster;
import HibernateMapping.MSWDmechanicmaster;
import HibernateMapping.Nextservicemaster;
import HibernateMapping.Otherjobworkmaster;
import HibernateMapping.Ownerdrivenmaster;
import HibernateMapping.SWVehicleServiceFollowup;
import HibernateMapping.SWVehicleServiceSchedule;
import HibernateMapping.Servicetypemaster;
import HibernateMapping.SpInventSaleDetails;
import HibernateMapping.SpInventSaleDetailsPK;
import HibernateMapping.SpInventSaleMaster;
import HibernateMapping.SpOrderInvGrn;
import HibernateMapping.SpOrderInvGrnDetails;
import HibernateMapping.TMSDelayReasonMaster;
import HibernateMapping.Vehicledetails;
import HibernateUtil.HibernateUtil;
import HibernateUtil.Hibernate_Util;
import beans.ContentFormBean;
import beans.SubContentFormBean;
import beans.serviceForm;
import dbConnection.dbConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 *
 * @author vandana.singla
 */
public class serviceDAO implements DBQueryConstant
{

    Connection conn = null;
    String dbPATH = new dbConnection().dbPathAuth;
    int rowsUpdate = 0;
    DecimalFormat df = new DecimalFormat("#0.00");

    public serviceDAO(Connection conn)
    {
        this.conn = conn;
    }

    public serviceDAO()
    {
    }

    public String check_in_Db(String no_new, String table, String column, String SubQuery, Session hrbsession)
    {
        String val = "notexist";

        // Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;

            Query query = hrbsession.createQuery(hql);

            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                val = "exist";
            }

        }
        catch (Exception ae) {

            ae.printStackTrace();
        }
        /*   finally
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
        }*/
        return val;
    }

    public String check_and_getfrom_Db(String retColumnName, String no_new, String table, String column, String SubQuery, Session hrbsession)
    {
        String val = "notexist";

        try {

            String hql = "select distinct " + retColumnName + " from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;

            Query query = hrbsession.createQuery(hql);

            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                val = (String) itr.next();
                // val="exist";
            }

        }
        catch (Exception ae) {

            ae.printStackTrace();
        }

        return val;
    }

    /**
     * *******************************************************************************
     * @return String Common method
     * *******************************************************************************
     */
    public LinkedHashSet<LabelValueBean> getJobType()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct jobTypeID,jobTypeDesc FROM Jobtypemaster where isActive='Y' order by seqNo";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Jobtypemaster jm = (Jobtypemaster) itr.next();
                name = jm.getJobTypeDesc() == null ? "" : jm.getJobTypeDesc();
                id = Integer.toString(jm.getJobTypeID()) == null ? "" : Integer.toString(jm.getJobTypeID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getJobLocation()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct locationID,locationDesc  FROM Joblocationmaster where isActive='Y' order by seqNo";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Joblocationmaster jm = (Joblocationmaster) itr.next();
                name = jm.getLocationDesc() == null ? "" : jm.getLocationDesc();
                id = Long.toString(jm.getLocationID()) == null ? "" : Long.toString(jm.getLocationID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getNextService()
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct nextServiceID,nextServiceDesc FROM Nextservicemaster where isActive='Y' order by seqNo";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Nextservicemaster nm = (Nextservicemaster) itr.next();
                name = nm.getNextServiceDesc() == null ? "" : nm.getNextServiceDesc();
                id = Long.toString(nm.getNextServiceID()) == null ? "" : Long.toString(nm.getNextServiceID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getJobCardStatus()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct statusID,statusDesc FROM Jobcardstatusmaster where isActive='Y' order by seqNo";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(id);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Jobcardstatusmaster jm = (Jobcardstatusmaster) itr.next();
                name = jm.getStatusDesc() == null ? "" : jm.getStatusDesc();
                id = Long.toString(jm.getStatusID()) == null ? "" : Long.toString(jm.getStatusID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getOwnerDriven()
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        Query query = null;
        Iterator itr = null;
        String name = "", id = "";

        String hql = "select distinct ownerDrivenID,ownerDrivenDesc FROM Ownerdrivenmaster where isActive='Y' order by seqNo";

        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Ownerdrivenmaster om = (Ownerdrivenmaster) itr.next();
                name = om.getOwnerDrivenDesc() == null ? "" : om.getOwnerDrivenDesc();
                id = Long.toString(om.getOwnerDrivenID()) == null ? "" : Long.toString(om.getOwnerDrivenID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getServiceType()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct serviceTypeID,serviceTypeDesc FROM Servicetypemaster where isActive='Y' order by seqNo";
        try {
            LabelValueBean lv = null;

            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Servicetypemaster sm = (Servicetypemaster) itr.next();
                name = sm.getServiceTypeDesc() == null ? "" : sm.getServiceTypeDesc();
                id = Integer.toString(sm.getServiceTypeID()) == null ? "" : Integer.toString(sm.getServiceTypeID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getApplicationCode()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "";
        String id = "";
        String hql = "select distinct appCode,appDesc FROM ApplicationMaster where isActive='Y' order by appDesc";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {

                ApplicationMaster am = (ApplicationMaster) itr.next();
                name = am.getAppDesc() == null ? "" : am.getAppDesc();
                id = am.getAppCode() == null ? "" : am.getAppCode();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getComplaintCode()
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";



        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery("select distinct compCode,compDesc from Complaintcodemaster where  isActive='Y' order by compDesc ");
            itr = query.list().iterator();
            while (itr.hasNext()) {

                Complaintcodemaster cm = (Complaintcodemaster) itr.next();
                name = cm.getCompDesc() == null ? "" : cm.getCompDesc();
                id = cm.getCompCode() == null ? "" : cm.getCompCode();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public LinkedHashSet<LabelValueBean> getCausalCode()
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        //sqlQuery = "select distinct CausalCode,CausalDesc from CausalCodeMaster where isActive='Y' order by CausalDesc ";
        try {
            LabelValueBean lv = null;

            query = hrbsession.createQuery(" from Causalcodemaster where isActive='Y' order by causalDesc ");
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Causalcodemaster cm = (Causalcodemaster) itr.next();
                name = cm.getCausalDesc() == null ? "" : cm.getCausalDesc();
                id = cm.getCausalCode() == null ? "" : cm.getCausalCode();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public LinkedHashSet<LabelValueBean> getConsequenceCode()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String sqlQuery = "";
        sqlQuery = "select distinct consequenceCode,consequenceDesc from Consequencemaster where isActive='Y' order by consequenceDesc ";

        try {
            LabelValueBean lv = null;

            query = hrbsession.createQuery(sqlQuery);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Consequencemaster cm = (Consequencemaster) itr.next();
                name = cm.getConsequenceDesc() == null ? "" : cm.getConsequenceDesc();
                id = cm.getConsequenceCode() == null ? "" : cm.getConsequenceCode();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public LinkedHashSet<LabelValueBean> getComplaintJobCardList()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        try {
            LabelValueBean lv = null;

            query = hrbsession.createQuery("select distinct ccm.compCode ,ccm.compDesc FROM Actioncodemaster am,Complaintcodemaster ccm  where ccm.compCode=am.compCode order by ccm.compDesc");
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[1].toString() == null ? "" : o[1].toString().trim();
                id = o[0].toString() == null ? "" : o[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getBillCode(String jobType)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "", flag = "", discount = "";
        String hqlquery = " FROM Billmaster where isActive='Y' order by BillDesc";
        Iterator itr = null;
        try {
            LabelValueBean lv = null;
            Query query = hrbsession.createQuery(hqlquery);
            itr = query.list().iterator();

            while (itr.hasNext()) {

                Billmaster bill = (Billmaster) itr.next();
                name = bill.getBillDesc() == null ? "" : bill.getBillDesc().trim();
                id = bill.getBillID() == null ? "" : Long.toString(bill.getBillID());
                flag = bill.getWarrantyType() == null ? "" : bill.getWarrantyType().trim();
                discount = Float.toString(bill.getDiscount());
                if (jobType!=null && jobType.equalsIgnoreCase("35")) {
                    if (!"2".equals(id)) {
                    lv = new LabelValueBean(name, id + "@@" + flag + "@@" + discount);
                    }
                    result.add(lv);
                } else if (!"6".equals(id)) {
                    lv = new LabelValueBean(name, id + "@@" + flag + "@@" + discount);
                    result.add(lv);
                }
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getOtherjobworkmaster()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "";
        String id = "";
        Query query = null;
        Iterator itr = null;
        // String query = "select distinct WorkDesc,WorkDesc FROM otherjobworkmaster where isActive='Y' order by seqNo";
        try {

            LabelValueBean lv = null;
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery(query);
            query = hrbsession.createQuery("select distinct workDesc,workDesc FROM Otherjobworkmaster where isActive='Y' order by seqNo");
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Otherjobworkmaster ow = (Otherjobworkmaster) itr.next();
                name = ow.getWorkDesc() == null ? "" : ow.getWorkDesc().trim();
                id = Long.toString(ow.getWorkID()) == null ? "" : Long.toString(ow.getWorkID());
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getComplaintCodeOnCompDetails(serviceForm sf)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hqlquery = "select distinct cd.cmpNo,cd.custVerbatim FROM Jobcomplaintdetails cd,Complaintcodemaster cm where cd.defectCode=cm.compCode and cm.isActive='Y' and jobCardNo='" + sf.getJobCardNo() + "' order by cd.custVerbatim";
        Iterator itr = null;

        try {
            LabelValueBean lv = null;
            Query query = hrbsession.createQuery(hqlquery);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[1] == null ? "" : o[1].toString().trim();
                id = o[0] == null ? "" : o[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getActionTakenOnCompCode(serviceForm sf)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        // System.out.println("complaint code" + sf.getComplaintCode_str());
        Iterator itr = null;

        // String query ="select WageValue  from wagemaster where dealerCode=? and isActive='Y' and ((StartDate <='" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "' and endDate>'" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "' and StartDate is not NULL) or (StartDate <='" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "'  and endDate is NULL))"


        try {

            Query query = hrbsession.createQuery("select  actionCode,actionDesc,serviceHrs   from Actioncodemaster where compCode='" + sf.getComplaintCode_str() + "' and isActive='Y' ");
            itr = query.list().iterator();
            LabelValueBean lv = null;
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                //System.out.println("labour desc" + rs.getString(2));
                name = o[1].toString() == null ? "" : o[1].toString().trim();
                id = o[0].toString() == null ? "" : o[0].toString().trim() + "@@" + o[2].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getCommonLabelValues(String tableName, String fieldId, String fieldName, String filter, String whereCase)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();



        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        if (whereCase.equals("")) {
            whereCase = "where isActive='Y'";
        }

        if (tableName.equals("Eventmaster")) {
            String constantvalue = getHesConstantValue(11);
            int days = Integer.parseInt(constantvalue);
            days = days + 1;
            //whereCase = " where  MONTH(startDate) = MONTH(GETDATE()) and dealercode='" + whereCase + "'";
            whereCase = " where startDate between DATEADD(dd,-" + days + ",GETDATE())  AND DATEADD(dd,0,GETDATE())  and dealerCode='" + whereCase + "'";

        }
        String hql = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";
        Query query = hrbsession.createQuery(hql);
        try {
            Iterator itr = query.list().iterator();
            LabelValueBean lv = null;
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[1] == null ? "" : o[1].toString().trim();
                id = o[0] == null ? "" : o[0].toString().trim();

                if (tableName.equals("Eventmaster")) {
                    id = o[0] == null ? "" : o[0].toString().trim() + "@@";
                    id = id + (o[2] == null ? "" : sdf.format(o[2])) + "@@";
                    id = id + (o[3] == null ? "" : sdf.format(o[3]));
                }

                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

	public String addComplaintDetailJobCard(serviceForm sf, String user_id, String op) throws SQLException {

		Session hrbsession = HibernateUtil.getSessionFactory().openSession();

		String results = "";

		Calendar cal = null;

		String CmpNo = "";
		int month = 0;

		String subsggcode = "";
		Query query1, query2;
		Iterator itr = null;
		Jobcarddetails jc = null;
		Jobcomplaintdetails jd = null;
		List<String> biPartComplaints = new ArrayList<>();

		try {

			cal = Calendar.getInstance();

			month = Calendar.getInstance().get(Calendar.MONTH);
			month = month + 1;
			hrbsession.beginTransaction();
			CmpNo = sf.getJobCardNo();
			if (op.trim().equals("update") && sf.getJobCardNo() != null && (!sf.getJobCardNo().equals(""))) {

				query2 = hrbsession
						.createQuery("delete FROM  Jobcomplaintdetails WHERE jobCardNo='" + sf.getJobCardNo() + "'");
				query2.executeUpdate();

			}

			query1 = hrbsession.createQuery("FROM  Jobcarddetails WHERE jobCardNo='" + sf.getJobCardNo() + "'");
			itr = query1.list().iterator();

			if (itr.hasNext()) {

				jc = (Jobcarddetails) itr.next();

			}
			cal = Calendar.getInstance();
			month = cal.get(Calendar.MONTH) + 1;

			for (int i = 0; i < sf.getCompVerbatim().length; i++) {

				if (sf.getSubaggCode()[i] != null && !sf.getSubaggCode()[i].isEmpty()) {
					subsggcode = sf.getSubaggCode()[i].trim();
				}

				if (sf.getJobCardNo() != null && (!sf.getJobCardNo().equals(""))
						&& (!sf.getCompVerbatim()[i].equals("")) && (!sf.getApplicationCode()[i].equals(""))
						&& (!sf.getAggCode()[i].equals(""))) {

					jd = new Jobcomplaintdetails();

					jd.setJobCardNo(sf.getJobCardNo());
					jd.setCustVerbatim(sf.getCompVerbatim()[i].trim());
					jd.setSubAggCode(subsggcode);
					jd.setForemanObsv(sf.getForemanObservation()[i]);
					jd.setAppCode(sf.getApplicationCode()[i].trim());
					jd.setAggCode(sf.getAggCode()[i].trim());
					jd.setCmpNo(CmpNo + "-" + (i + 1));
					jd.setSubassemblyCode(sf.getSubassemblyCode()[i]);
					jd.setDefectCode(sf.getCompCode()[i].trim());

					String biPart = sf.getBiPartCode()[i] != null ? sf.getBiPartCode()[i].trim() : "";
					jd.setBiPart(biPart);

					if ("YES".equalsIgnoreCase(biPart)) {
						biPartComplaints.add(CmpNo + "-" + (i + 1));
					}

					jd.setAtmCase(sf.getAtmCaseCode()[i].trim() == null ? "" : sf.getAtmCaseCode()[i].trim());
					hrbsession.save(jd);

				}
			}

			// 📨 Trigger mail BEFORE committing
			String mailResult = null;
			if (!biPartComplaints.isEmpty()) {

				// Fetch dealerCode and jobcardDate
				String query3 = "select CAST(dealercode AS VARCHAR) AS dealercode, "
						+ "CONVERT(VARCHAR(10), JobCardDate, 120) AS jobcardDate "
						+ "from SW_jobcarddetails(nolock) where jobcardno = :jobcardNo";

				Object[] result = (Object[]) hrbsession.createSQLQuery(query3)
						.addScalar("dealercode", org.hibernate.type.StringType.INSTANCE)
						.addScalar("jobcardDate", org.hibernate.type.StringType.INSTANCE)
						.setParameter("jobcardNo", sf.getJobCardNo()).uniqueResult();

				String dealerCode = result != null && result[0] != null ? result[0].toString() : "";
				String jobcardDate = result != null && result[1] != null ? result[1].toString() : "";

				// Fetch dealerName
				String query4 = "select CAST(dealerName AS VARCHAR) AS dealerName, stateName, Location "
				        + "from tms_dealerMaster (nolock) where dealerCode = :dealerCode";

				Object[] resultSet = (Object[]) hrbsession.createSQLQuery(query4)
				        .addScalar("dealerName", org.hibernate.type.StringType.INSTANCE)
				        .addScalar("stateName", org.hibernate.type.StringType.INSTANCE)
				        .addScalar("Location", org.hibernate.type.StringType.INSTANCE)
				        .setParameter("dealerCode", dealerCode)
				        .uniqueResult();

				String dealerName = (String) resultSet[0];
				String stateName  = (String) resultSet[1];
				String location   = (String) resultSet[2];

				// Fetch email TO and CC
				String query5 = "exec PROC_GetDealerEmails :dealerCode";

				Object[] emailResult = (Object[]) hrbsession.createSQLQuery(query5)
						.addScalar("toList", org.hibernate.type.StringType.INSTANCE)
						.addScalar("ccList", org.hibernate.type.StringType.INSTANCE)
						.setParameter("dealerCode", dealerCode).uniqueResult();

				String toList = emailResult != null && emailResult[0] != null ? emailResult[0].toString() : "";
				String ccList = emailResult != null && emailResult[1] != null ? emailResult[1].toString() : "";

				// Combine complaint numbers
				String complaintNumbers = String.join(", ", biPartComplaints);

				// Send mail
				mailResult = MailSender.sendBiPartMail(sf.getJobCardNo(), complaintNumbers, dealerCode, dealerName,
						jobcardDate, toList, ccList,stateName,location);
			}


			if (mailResult != null && mailResult.equalsIgnoreCase("failed")) {
				hrbsession.getTransaction().rollback();
				results = "Failure@@compFailure";
			}

			else {
				hrbsession.getTransaction().commit();
				results = "Success@@compSuccess";
			}

		} catch (Exception ae) {
			hrbsession.getTransaction().rollback();
			ae.printStackTrace();

			results = "Failure@@compFailure";// Unable to add Complaint, Please contact system Administrator.";
		} finally {
			try {

				if (hrbsession != null) {
					hrbsession.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
				results = "Failure@@compFailure";// Unable to add Complaint, Please contact system Administrator.";
			}
		}
		return results;
	}

        public static String countIndex(int number)
    {
        String index = "";
        if (number < 10) {
            index = "000" + number;
        }
        else if (number < 100) {
            index = "00" + number;
        }
        else if (number < 1000) {
            index = "0" + number;
        }
        else {
            index = "" + number;
        }

        return index;
    }

    public String getComponentList(String partno, String comptype, String column, String tablename, String partType)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        String sql = null;
        StringBuilder data = new StringBuilder("<listdata>");
        try {

            if (comptype.equals("LUBES")) {
                sql = "Select distinct " + column + " from CatPart where " + partType + " like ('%" + comptype + "%') and " + column + " LIKE ('%" + partno + "%')  order by " + column + "";
            
            }
            else {
                sql = "Select distinct " + column + " from CatPart where " + partType + " <> 'LUBES' and " + column + " LIKE ('%" + partno + "%')  order by " + column + "";
            	
            }


            query = hrbsession.createQuery(sql);
            query.setMaxResults(10);
            itr = query.list().iterator();

            // System.out.println("sqlquery" + sql);

            int counter = 0;

            while (itr.hasNext()) {
                counter++;
                data.append((String) itr.next() + "|");
            }
            data.append("</listdata>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data.toString();

    }
   


    public String getPart_in_db(String partDesc)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        String sql = null;
        String tempNo = "";
        try {

            sql = "Select distinct partNo,p1 from CatPart where p1='" + partDesc.trim() + "' order by p1";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempNo = (String) o[0];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempNo;

    }

    public String getPartno_in_db(String partNo, String cat)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        String sql = null;
        String tempNo = "";
        try {
            if (cat != null && !cat.equals("LUBES")) {
                sql = "Select distinct partNo,p1 from CatPart where partNo='" + partNo.trim() + "' and partType<>'LUBES' order by partNo";
            }
            else {
                sql = "Select distinct partNo,p1 from CatPart where partNo='" + partNo.trim() + "' and partType='" + cat + "'  order by partNo";
            }
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempNo = (String) o[0];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempNo;

    }

    public String getPriceByPartNo(String partno, String column, String TablePartprice, String TablePartmaster, String part_price, String partNumber, String priceListCode)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;

        String sql = null;
        String tempDesc = "";
        try {

            sql = "Select distinct pp." + part_price + ",pm." + column +",pm.np2" + " from " + TablePartprice + " pp," + TablePartmaster + " pm  "
                    + " where pp.id.item = pm." + partNumber + " and pp.id.item='" + partno + "' and pp.id.pricelistCode='" + priceListCode + "'";// group by  pm.partNo, pp." + part_price + ",pm." + column + "";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempDesc = o[0].toString() + "@@" + o[1].toString() +"@@" + o[2].toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempDesc;

    }

    public String getPartNoCheck(String partno)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        String sql = null;
        String partnumber = "notexists";
        try {
            sql = "Select distinct partNo from CatPart where partNo='" + partno + "'";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                if (itr.next() != null) {
                    partnumber = "exists";
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return partnumber;

    }

    public void getParameterActionTaken(serviceForm sf)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        String hql = null;
        String constantValue = "";
        try {

            hql = "select elementValue from HesConstants where elementId=1";
            Query query = hrbsession.createQuery(hql);
            itr = query.list().iterator();

            if (itr.hasNext()) {
                constantValue = (String) itr.next();
                sf.setConstantValue(constantValue);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getHesConstantValue(int id)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        String hql = null;
        String constantValue = "";
        try {

            hql = "select elementValue from HesConstants where elementId=?";
            Query query = hrbsession.createQuery(hql);
            query.setInteger(0, id);
            itr = query.list().iterator();

            if (itr.hasNext()) {
                constantValue = (String) itr.next();

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return constantValue;
    }

    public void getFormContent(serviceForm serviceForm)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ContentFormBean cf = null;
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;
        String subContentId = null;
        String formName = serviceForm.getJobType();

        String hql = "";
        ArrayList<SubContentFormBean> subContentFormList = new ArrayList();
        SubContentFormBean dataBean = null;//new SubContentFormBean();
        ItlSubContentMaster isct = null;
        ArrayList<SubContentFormBean> tempList = null;
        Iterator itr = null;
        Query query = null;
        try {

            getForm_id(serviceForm, formName);
            /*
            select * from jobchecklist
            join jobcarddetails  on jobcarddetails.JobId = jobchecklist.jobCardId
            where jobcarddetails.JobCardNo

             */
            query = hrbsession.createQuery(CheckRowsInjobchecklist + serviceForm.getJobCardNo() + "'");
            itr = query.list().iterator();
            if (itr.hasNext()) {
                getView_FormContent(serviceForm);

            }
            else if (serviceForm.getJobType().equals("1") || serviceForm.getJobType().equals("41")) {

                hql = "select distinct pc.id.contentId,cm.contentDesc,pc.id.subContentId,scm.subContentDesc,pc.okStatus, "
                        + " pc.actionTaken,pc.remarks from PdiChecklist pc ,ItlContentMaster cm ,ItlSubContentMaster scm ,PdiDetails pd where "
                        + " cm.contentId = pc.id.contentId and"
                        + " scm.subContentId = pc.id.subContentId and"
                        + " pd.pDINo=pc.id.pDINo and"
                        + " pd.vinNo='" + serviceForm.getVinNo() + "' and pd.dealercode='" + serviceForm.getDealercode() + "'";

                query = hrbsession.createQuery(hql);
                itr = query.list().iterator();
                while (itr.hasNext()) {

                    Object o[] = (Object[]) itr.next();
                    cf = new ContentFormBean();
                    //  icm = (ItlContentMaster) o[0];//
                    cf.setContentId(Integer.parseInt("" + o[0]));
                    cf.setContentDesc(o[1].toString());

                    //  ism = (ItlSubContentMaster) o[2];//
                    subContentId = "" + o[2];
                    tempList = treeMap.put(cf, subContentFormList);
                    if (tempList == null) {
                        subContentFormList = new ArrayList();
                        treeMap.put(cf, subContentFormList);
                    }
                    if (subContentId != null) {
                        dataBean = new SubContentFormBean();
                        //dataBean.setContentIdTemp(String.valueOf(contentIdTemp));

                        dataBean.setSubContentId(subContentId);

                        if (o[3] != null) {
                            dataBean.setSubContentDesc(o[3].toString());//
                        }
                        if (o[4] != null) {
                            dataBean.setTextBoxOption(o[4].toString().trim());//
                        }
                        if (o[5] != null) {
                            dataBean.setActiontaken(o[5].toString().trim());//
                        }
                        if (o[6] != null) {
                            dataBean.setObservations(o[6].toString().trim());//
                        }                    //dataBean.setStatus(rs.getString("finalStatus").trim());
//                    dataBean.setTextBoxOption(rs.getString("TEXTBOX_OPTION"));

                        subContentFormList.add(dataBean);
                    }

                }
                serviceForm.setDataMap(treeMap);




            }
            else {


                hql = " select distinct fc.contentId,cm.contentDesc,fc.subContentId,scm.subContentDesc,fc.orderSeq from "
                        + " ItlFormContent fc, ItlSubContentMaster  as scm , ItlContentMaster  as cm "
                        + " where scm.subContentId = fc.subContentId  and  cm.contentId = fc.contentId  and fc.formId=" + serviceForm.getFormId() + " order by fc.orderSeq ";



                query = hrbsession.createQuery(hql);

                itr = query.list().iterator();

                while (itr.hasNext()) {

                    Object o[] = (Object[]) itr.next();
                    ItlContentMaster it = (ItlContentMaster) o[0];
                    contentId = Integer.parseInt("" + it.getContentId());
                    cf = new ContentFormBean();
                    cf.setContentId(contentId);
                    cf.setContentDesc(o[1].toString().trim());
                    isct = (ItlSubContentMaster) o[2];
                    subContentId = Long.toString(isct.getSubContentId());//(o[2].toString().trim());
                    //cf.setContentDesc(rs.getString("CONTENT_DESC").trim());
                    //subContentId = "" + rs.getInt("SUB_CONTENT_ID");

                    tempList = treeMap.put(cf, subContentFormList);
                    if (tempList == null) {
                        subContentFormList = new ArrayList();
                        treeMap.put(cf, subContentFormList);
                    }
                    if (subContentId != null) {
                        dataBean = new SubContentFormBean();

                        dataBean.setSubContentId(subContentId);

                        dataBean.setSubContentDesc(o[3].toString().trim());// SUB_CONTENT_DESC").trim());

                        subContentFormList.add(dataBean);
                    }
                }
                serviceForm.setDataMap(treeMap);
            }

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                try {
                    e.printStackTrace();
                }
                catch (Exception ex) {
                    //Logger.getLogger(ServiceProcessDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void getForm_id(serviceForm serviceForm, String formName)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        int fid = 0;
        try {

            query = hrbsession.createQuery("SELECT distinct fm.id.formid FROM FormMaster fm where fm.id.jobtypeid=" + serviceForm.getJobType() + " and fm.id.modelcode='" + serviceForm.getModelCode() + "'");

            itr = query.list().iterator();
            if (itr.hasNext()) {
                fid = (Integer) itr.next();

                serviceForm.setFormId(Integer.toString(fid));
            }

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //get vehicle_History created on 15/05/14 by Megha
    public ArrayList<serviceForm> getvehicle_History(serviceForm serviceForm, String vinNo) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        ArrayList<serviceForm> dataList = null;
        String sql = null, Subsql = "";
        Jobcarddetails jc = null;
        Criteria cr = hrbsession.createCriteria(Jobcarddetails.class);
        cr.add(Restrictions.eq("jobCardNo", serviceForm.getJobCardNo()));
        itr = cr.list().iterator();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if (itr.hasNext()) {
            jc = (Jobcarddetails) itr.next();
            vinNo = jc.getVinno();
        }

        try {
            dataList = new ArrayList<serviceForm>();


            sql = Get_vehicle_History_mysql + vinNo + "' and jobCardNo <> '" + serviceForm.getJobCardNo() + "' order by jobCardDate";

//            System.out.println("query:"+sql);
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                serviceForm = new serviceForm();
                //jd.jobCardDate,jd.dealerCode,jd.hmr,jd.jobCardNo,vd.customerName,vd.mobilePh
                serviceForm.setJobCardDate(o[0] == null ? "" : sdf.format(sdf1.parse(o[0].toString())));
                serviceForm.setServiceDoneBy(o[1] == null ? "" : o[1].toString());

                serviceForm.setHmr("" + o[2].toString());
                serviceForm.setJobCardNo(o[3] == null ? "" : o[3].toString());
                serviceForm.setCustomerName(o[4] == null ? "" : o[4].toString());
                serviceForm.setMobilePhone(o[5] == null ? "" : o[5].toString());
                serviceForm.setStatus(o[6] == null ? "" : o[6].toString());
                serviceForm.setHmeRadio(o[7] == null ? "" : o[7].toString());
                serviceForm.setJobTypeDesc(o[8] == null ? "" : o[8].toString());
                dataList.add(serviceForm);
//                System.out.println("form:"+serviceForm.getServiceDoneBy());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String getJobCardNumber(Session hrbsession, String userid, String dealercode) throws SQLException
    {
        //   Calendar cal = null;
        String location = "";
        String branchcode = "";
        String jobcard = "";
        long jobcardid = 0;
        int month = 0;
        String mon = "";
        String yr = "";
        String id = "";
        int year = 0;
        Query query, query1 = null;
        Iterator itr = null;
        String hql = "select locationCode,branchCode from Dealervslocationcode where dealerCode=? ";//dealervslocationcode(Id, DealerCode, LocationCode)

        query = hrbsession.createQuery(hql);
        query.setString(0, dealercode);
        itr = query.list().iterator();
        if (itr.hasNext()) {
            Object o[] = (Object[]) itr.next();
            location = o[0].toString().trim();
            branchcode = o[1].toString().trim();

        }
        else {

            return "notexist";
        }


        // cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;

        if (month <= 9) {

            mon = "0" + month;

        }
        yr = Integer.toString(year).substring(0, Integer.toString(year).length());

        String hql3 = "select count(*) from Jobcarddetails where MONTH(getDate())= MONTH(createdon) and dealerCode='" + dealercode + "'";
        query1 = hrbsession.createQuery(hql3);

        itr = query1.list().iterator();

        // System.out.println("inside the jobnumber");

        if (itr.hasNext()) {
            jobcardid = (Long) itr.next();
        }
        // System.out.println("jobcardid==" + jobcardid);
        jobcardid = jobcardid + 1;

        if (jobcardid > 999 && jobcardid <= 9999) {

            id = Long.toString(jobcardid);
        }

        if (jobcardid > 99 && jobcardid <= 999) {

            id = "0" + Long.toString(jobcardid);

        }

        if (jobcardid > 9 && jobcardid <= 99) {

            id = "00" + Long.toString(jobcardid);

        }

        if (jobcardid > 0 && jobcardid <= 9) {

            id = "000" + Long.toString(jobcardid);

        }

        jobcard = location + "/J/" + dealercode.toUpperCase() + "/" + branchcode + "/" + mon + "/" + yr + "/" + id;


        return jobcard;
    }

//addVehileInformation
    public String addVehileInformation(serviceForm sf, String userid, String check) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String results = "";
        String jobcard = "";
        String checkstatus = "";
        String checkstatus1 = "";
        String checkstatus3 = "";
        String checkexist = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Query query3 = null;
        Iterator itr4 = null;
        Jobcarddetails jcd = new Jobcarddetails();
        try {
            hrbsession.beginTransaction();
            checkstatus = check_in_Db(sf.getVinNo(), "Jobcarddetails", "vinNo", " and status IN ('OPEN','CLOSE') and dealerCode='" + sf.getDealercode() + "'", hrbsession);
            if (checkstatus.equals("exist")) {
                return "Failure@@vehicleInfoFailure4exist";//Job Card for chassis No. " + sf.getVinNo() + " is already opened. Kindly close it first and then create the new Job Card";
            }


            checkexist = check_and_getfrom_Db("vinDetailsType", sf.getDealercode() + "-" + sf.getVinNo(), "Vehicledetails", "vinid", "", hrbsession);

            if (!"notexist".equals(checkexist)) {
                // for PDI Job Card
                // checkstatus2 = check_in_Db(sf.getJobType(), "Jobtypemaster jt", "jt.jobTypeID", " and jt.jobTypeID='1'");
                //if (checkstatus2.equals("exist"))
                if ("1".equals(sf.getJobType())) {
                    sf.setJctype("PDI");

                    checkstatus3 = check_in_Db(sf.getVinNo(), "PdiDetails pd ", "pd.vinNo", " and dealerCode='" + sf.getDealercode() + "'", hrbsession);
                    if (!checkstatus3.equals("exist")) {
                        return "Failure@@vehicleInfoFailure4pdicreated";  //Job Card for PDI cannot be created as either PDI is not done or done by other Dealer
                    }

                    checkstatus1 = check_in_Db(sf.getVinNo(), "Jobcarddetails jc ", "jc.vinno", " and jc.jobTypeId='1'", hrbsession);

                    if (checkstatus1.equals("exist")) {
                        return "Failure@@vehicleInfoFailure4jobType";  //Job Card Cannot be created as PDI Job Card already exists for Chassis No. :
                    }
//                }
//
//                if (sf.getJobType().equals("1")) //&& !sf.getJobType().equals("2")
//                {

                    checkstatus1 = check_in_Db(sf.getVinNo(), "Vehicledetails vd ", "vd.vinNo", " and vd.pdiStatus='Y' and vd.insStatus='Y'", hrbsession);

                    if (checkstatus1.equals("exist")) {
                        return "Failure@@jobcardFailure4withpdiins";  //PDI Job Card cannot be created as other Job Card already exists for given Chassis No.
                    }

                }

            }


            if ("1".equals(sf.getJobType()) || "41".equals(sf.getJobType())) {
                sf.setWarrantyApplicable("Y");
                sf.setItlExtWarrantyApplicable("N");
            }
            
            else if(sf.getDeliveryDate() != null && !sf.getDeliveryDate().equals("")) {
            	
            	String res  = checkITLExtWtyPolicyStatus(sf.getVinNo(),sf.getDeliveryDate(),sf.getHmr());
            	System.out.println(sf.getVinNo() +" "+ res);
        		String[] resArr = res.split("@@");
        		
        		if(resArr[0] != null && !resArr[0].equals("") && resArr[0].equals("YES")){
        			sf.setWarrantyApplicable("Y");
        			sf.setItlExtWarrantyApplicable("N");
        			
        		}
        		
        		else if(resArr[0] != null && !resArr[0].equals("") && (resArr[0].equals("ITL EW Registered") || resArr[0].equals("ITL EW"))) {
        			sf.setWarrantyApplicable("Y");
        			sf.setItlExtWarrantyApplicable(resArr[0]);
        		}
        		
        		else {
        			sf.setWarrantyApplicable("N");
        			sf.setItlExtWarrantyApplicable("N");
        		}
        		
        		System.out.println(sf.getWarrantyApplicable() +" " + sf.getItlExtWarrantyApplicable() +" " +sf.getVinNo());
            	
            }
      
            jobcard = new MethodUtility().getNumber(hrbsession, "Jobcarddetails", sf.getDealercode(), "J");
            if (jobcard.equals("notexist")) {
                return "Failure@@vehicleInfoFailure4locCode";//Location code for your Dealership does not exists. Kindly contact Administrator";
            }

            if ("notexist".equals(checkexist)) {
                checkstatus1 = check_in_Db(sf.getJobType(), "Jobtypemaster jt", "jt.jobTypeID", " and jt.freeService='Y'", hrbsession);

                if (checkstatus1.equals("exist") ) {
                	System.out.println("Line 1924");
                    return "Failure@@freeServiceCheckManual";  //Job Card for free service Cannot be created in case of Manual Chassis No. :
                }


                Vehicledetails vd = new Vehicledetails();
                vd.setVinNo(sf.getVinNo());
                vd.setEngineNo(sf.getEngineNumber());

                if (sf.getDeliveryDate() == null || sf.getDeliveryDate().equals("")) {
                    vd.setDeliveryDate(null);
                }
                else {
                    vd.setDeliveryDate(sdf.parse(sf.getDeliveryDate()));
                }

                vd.setVinid(sf.getDealercode() + "-" + sf.getVinNo());
                vd.setRegNo(sf.getRegistrationNo());
                vd.setSerBookNo(sf.getServiceBookletNo());
                vd.setKeyIdentification(sf.getKeyIdentificationNo());
                vd.setOwnerDriven(sf.getOwnerDriven());
                vd.setModelFamily(sf.getModelFamily());
                vd.setModelCode(sf.getModelCode());
                vd.setModelFamilyDesc(sf.getModelFamilyDesc());
                vd.setModelCodeDesc(sf.getModelCodeDesc());
                vd.setDealerCode(sf.getDealercode());
                vd.setNextService(sf.getNextService());
                vd.setIsServerSync('N');
                vd.setPdiStatus('Y');
                vd.setInsStatus('Y');
                vd.setVinDetailsType("MANUAL");
                sf.setVin_details_type("MANUAL");
                hrbsession.save(vd);

                if (sf.getProductionCategory().equals("")) {
                    jcd.setProductCatId(null);
                }
                else {
                    jcd.setProductCatId(Integer.parseInt(sf.getProductionCategory()));
                }

                jcd.setHMRWorking(sf.getHmeRadio().charAt(0));

                if (sf.getHmr().equals("")) {
                    jcd.setHmr(null);
                }
                else {
                    jcd.setHmr(Long.parseLong(sf.getHmr()));
                }
                jcd.setEventId(Integer.parseInt(sf.getEvent()));
                jcd.setLocationId(Integer.parseInt(sf.getJobLocation()));

                if (sf.getWarrantyApplicable() == null || sf.getWarrantyApplicable().trim().equals("")) {
                    jcd.setWarrantyStatus("N");
                }
                else {
                    jcd.setWarrantyStatus(sf.getWarrantyApplicable());
                }
                jcd.setServiceTypeId(Integer.parseInt(sf.getServiceType()));
                jcd.setJobCardNo(jobcard);
                jcd.setDealerJCNo(sf.getDealerJobCardNo());
                jcd.setStatus("OPEN");
                jcd.setVinid(sf.getDealercode() + "-" + sf.getVinNo());

                if (sf.getJobCardDateStr() == null || sf.getJobCardDateStr().equals("")) {
                    jcd.setJobCardDate(null);
                }
                else {
                    jcd.setJobCardDate(sdf1.parse(sf.getJobCardDateStr() + " " + sf.getCurrentEstimateTime() + ":" + sf.getCurrentEstimateHrs()));
                }


                jcd.setCreatedOn(new java.util.Date());


                if (sf.getJobType().equals("")) {
                    jcd.setJobTypeId(null);
                }
                else {
                    jcd.setJobTypeId(Integer.parseInt(sf.getJobType()));
                }

                jcd.setCreatedBy(userid);
                jcd.setDealerCode(sf.getDealercode());

                if (sf.getVinNo() == null || sf.getVinNo().equals("")) {
                    jcd.setVinno(null);
                }
                else {
                    jcd.setVinno(sf.getVinNo());
                }

                jcd.setEngineno(sf.getEngineNumber());
                jcd.setNextService(sf.getNextService());
                jcd.setCouponNo(sf.getCouponno());
                jcd.setJobCardPriority(sf.getJobcardpriority());
                jcd.setVinDetailsType("MANUAL");
                sf.setVin_details_type("MANUAL");
                jcd.setIsVinValidated("NO");

                jcd.setWtyClaimStatus("NOT REQUIRED");
               
                
                if(sf.getWarrantyApplicable() == "N") {
                	jcd.setIswarrantyreq('N');
                	jcd.setItlEwStatus("N");
                }
              
                else {
                	jcd.setIswarrantyreq('Y');
                	jcd.setItlEwStatus(sf.getItlExtWarrantyApplicable());
                }

                jcd.setIsServerSync('N');
                jcd.setTmsRefNo(jobcard);
                jcd.setDeTmsApproval("PENDING");
                jcd.setVorJobCard(sf.getVorJobcard());

                //   vin_details_type = "MANUAL";
                results = jobcard;
                hrbsession.save(jcd);

            }
            else {
            	System.out.println("Line 2038");
            	System.out.println(sf.getJobType() +" and  " + sf.getJctype());

                //for not PdI and ITL EXT WTY
                if (sf.getJctype() != null && !sf.getJctype().equals("JPDI") && sf.getJobType() != null && !sf.getJobType().equals("40")) {
                    checkstatus1 = check_in_Db(sf.getJobType(), "Jobtypemaster jt", "jt.jobTypeID", " and jt.freeService='Y' ", hrbsession);

                    if (checkstatus1.equals("exist")) {
                        checkstatus1 = check_in_Db(sf.getJobType(), "SWVehicleServiceSchedule vs", "vs.jobTypeId", " and vs.vinNo='" + sf.getVinNo() + "' and vs.status='PENDING' ", hrbsession);

                        if (!checkstatus1.equals("exist")) {
                        	System.out.println("Line 2046");
                            return "Failure@@freeServiceCheck";  // free service lapsed or already done for Chassis No. :
                        }
                    }



                    query3 = hrbsession.createQuery("from Vehicledetails where vinid=?");
                    query3.setString(0, sf.getDealercode() + "-" + sf.getVinNo());
                    itr4 = query3.list().iterator();

                    if (itr4 != null && itr4.hasNext()) {
                        Vehicledetails vds = (Vehicledetails) itr4.next();
                        jcd.setPayeeName(vds.getCustomerName());//rs1.getString(
                        jcd.setVillage(vds.getVillageName());
                        jcd.setTehsil(vds.getTehsil());
                        jcd.setDistrict(vds.getDistrict());
                        jcd.setPincode(vds.getPincode());
                        jcd.setState(vds.getState());
                        jcd.setCountry(vds.getCountry());
                        jcd.setMobilePhone(vds.getMobilePh());
                        jcd.setLandline(vds.getLandlineNo());
                        jcd.setEmailid(vds.getEmailId());
                        jcd.setVinid(sf.getDealercode() + "-" + sf.getVinNo());
                    }

                }


                if (sf.getProductionCategory().equals("")) {
                    jcd.setProductCatId(null);
                }
                else {
                    jcd.setProductCatId(Integer.parseInt(sf.getProductionCategory()));
                }
                jcd.setHMRWorking(sf.getHmeRadio().charAt(0));
                if (sf.getHmr().equals("")) {
                    jcd.setHmr(null);
                }
                else {
                    jcd.setHmr(Long.parseLong(sf.getHmr()));
                }
                jcd.setEventId(Integer.parseInt(sf.getEvent()));
                jcd.setLocationId(Integer.parseInt(sf.getJobLocation()));

                if (sf.getWarrantyApplicable() == null || sf.getWarrantyApplicable().trim().equals("")) {
                    jcd.setWarrantyStatus("N");
                }
                else {
                    jcd.setWarrantyStatus(sf.getWarrantyApplicable());
                }

                jcd.setServiceTypeId(Integer.parseInt(sf.getServiceType()));
                jcd.setJobCardNo(jobcard);
                jcd.setDealerJCNo(sf.getDealerJobCardNo());
                jcd.setVorJobCard(sf.getVorJobcard());

                if (sf.getJobCardDateStr() == null || sf.getJobCardDateStr().equals("")) {
                    jcd.setJobCardDate(null);
                }
                else {
                    jcd.setJobCardDate(sdf1.parse(sf.getJobCardDateStr() + " " + sf.getCurrentEstimateTime() + ":" + sf.getCurrentEstimateHrs()));
                }

                jcd.setCreatedOn(new java.util.Date());

                if (sf.getJobType().equals("")) {
                    jcd.setJobTypeId(null);
                }
                else {
                    jcd.setJobTypeId(Integer.parseInt(sf.getJobType()));
                }

                jcd.setDealerCode(sf.getDealercode());
                jcd.setCreatedBy(userid);


                if (sf.getVinNo() == null || sf.getVinNo().equals("")) {
                    jcd.setVinno(null);
                }
                else {
                    jcd.setVinno(sf.getVinNo());
                }

                jcd.setEngineno(sf.getEngineNumber());
                jcd.setNextService(sf.getNextService());
                jcd.setCouponNo(sf.getCouponno());
                jcd.setJobCardPriority(sf.getJobcardpriority());
                if ("DB".equalsIgnoreCase(checkexist)) {
                    jcd.setVinDetailsType("DB");
                    sf.setVin_details_type("DB");
                    jcd.setIsVinValidated("YES");
                }
                else {
                    jcd.setVinDetailsType("MANUAL");
                    sf.setVin_details_type("MANUAL");
                    jcd.setIsVinValidated("NO");
                }

                jcd.setStatus("OPEN");
                jcd.setIsServerSync('N');
                jcd.setDeTmsApproval("PENDING");
                jcd.setTmsRefNo(jobcard);
                jcd.setWtyClaimStatus("NOT REQUIRED");
                
                
				if (sf.getWarrantyApplicable() == "N") {
					jcd.setIswarrantyreq('N');
					jcd.setItlEwStatus("N");
				}

				else {
					jcd.setIswarrantyreq('Y');
					jcd.setItlEwStatus(sf.getItlExtWarrantyApplicable());
				}
          
                jcd.setVinid(sf.getDealercode() + "-" + sf.getVinNo());
                
                System.out.println(jcd.getIswarrantyreq() +" " + jcd.getItlEwStatus()  +" " + sf.getVinNo());
                hrbsession.save(jcd);

                //}
            }

            hrbsession.getTransaction().commit();

            results = "Success@@vehicleInfoSuccess@@" + jobcard;
        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
            results = "Failure@@vehicleInfoFailure";
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@vehicleInfoFailure";
            }
        }
        return results;
    }

    public String UpdateVehileInformation(serviceForm sf, String userid) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
//        Query query = null;
//        Iterator itr = null;


        String result = "";
        // String vin_details_type = "";
        //  SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

//        String sql1 = "select vinDetailsType from Jobcarddetails where jobcardno='" + sf.getJobCardNo() + "'";
//        query = hrbsession.createQuery(sql1);
//        itr = query.list().iterator();


        hrbsession.beginTransaction();
        Vehicledetails vd = (Vehicledetails) hrbsession.load(Vehicledetails.class, sf.getVinid());
        Jobcarddetails jd = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, sf.getJobCardNo());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            vd.setOwnerDriven(sf.getOwnerDriven());
            vd.setNextService(sf.getNextService());
            vd.setLastUpdatedDate(new java.util.Date());

            if (!vd.getVinDetailsType().equalsIgnoreCase("DB")) {
                vd.setDeliveryDate(sdf.parse(sf.getDeliveryDate()));
                vd.setModelFamily(sf.getModelFamily());
                vd.setModelFamilyDesc(sf.getModelFamilyDesc());
                vd.setModelCode(sf.getModelCode());
                vd.setModelCodeDesc(sf.getModelCodeDesc());
            }

            hrbsession.update(vd);

            //////////// Logic for warranty applicability   ///////////////////

      //      sf.setWarrantyApplicable("N");

            //  System.out.println(""+sf.getDeliveryDate());
            //  System.out.println(""+sf.getJobCardDateStr());
            //  System.out.println(""+sf.getWarrantyApplicable());

            if ("1".equals("" + jd.getJobTypeId()) || "1".equals(sf.getJobType())  || "41".equals(sf.getJobType())) {
               sf.setWarrantyApplicable("Y");
               sf.setItlExtWarrantyApplicable("N");
            }
            
            else {
            	if(sf.getDeliveryDate() != null && !sf.getDeliveryDate().equals("")) {
            		String deliveryDate = sf.getDeliveryDate();
            		String hmr = sf.getHmr();
            		String vinNo = vd.getVinNo();
            		String res  = checkITLExtWtyPolicyStatus(vinNo,deliveryDate,hmr);
            		String[] resArr = res.split("@@");
            		
            		if(resArr[0] != null && !resArr[0].equals("") && resArr[0].equals("YES")){
            			sf.setWarrantyApplicable("Y");
            			sf.setItlExtWarrantyApplicable("N");
            			
            		}
            		
            		else if(resArr[0] != null && !resArr[0].equals("") && (resArr[0].equals("ITL EW Registered") || resArr[0].equals("ITL EW"))) {
            			sf.setWarrantyApplicable("Y");
            			sf.setItlExtWarrantyApplicable(resArr[0]);
            		}
            		
            		else {
            			sf.setWarrantyApplicable("N");
            			sf.setItlExtWarrantyApplicable("N");
            		}
            	}
            	
            }
            
            
            jd.setHMRWorking(sf.getHmeRadio().charAt(0));

            if (sf.getHmr() != null && sf.getHmr().equals("")) {
                jd.setHmr(null);
            }
            else {
                jd.setHmr(Long.parseLong(sf.getHmr()));
            }

            jd.setEventId(Integer.parseInt(sf.getEvent()));
            jd.setLocationId(Integer.parseInt(sf.getJobLocation()));

            if (sf.getWarrantyApplicable() == null || sf.getWarrantyApplicable().trim().equals("")) {
                jd.setWarrantyStatus("N");
            }
            else {
                jd.setWarrantyStatus(sf.getWarrantyApplicable());
            }
            
            jd.setItlEwStatus(sf.getItlExtWarrantyApplicable());
            
            // jd.setWarrantyStatus(sf.getWarrantyApplicable());
            jd.setServiceTypeId(Integer.parseInt(sf.getServiceType()));

            jd.setDealerJCNo(sf.getDealerJobCardNo());

            

            jd.setStatus(sf.getJobCardStatus());
            jd.setDealerCode(sf.getDealercode());
            jd.setEngineno(sf.getEngineNumber());
            jd.setNextService(sf.getNextService());
            jd.setCouponNo(sf.getCouponno());
            jd.setJobCardPriority(sf.getJobcardpriority());
            jd.setStatus("OPEN");
            jd.setLastModifiedBy(userid);
            jd.setLastModifiedOn(new java.util.Date());
            jd.setVorJobCard(sf.getVorJobcard());
            
            hrbsession.update(jd);
            // System.out.println("getJobCardNo==" + sf.getJobCardNo());

            hrbsession.getTransaction().commit();


            result = "Success@@vehicleInfoUpdateSuccess@@" + sf.getJobCardNo();// Vehicle Information has been Successfully Updated for Job Card No.  " + sf.getJobCardNo() + "@@" + sf.getJobCardNo() + "@@" + vin_details_type;

        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();

            result = "Failure@@vehicleInfoUpdateFailure";//Unable to Update Vehicle Information, Please Contact System Administrator.";
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@vehicleInfoUpdateFailure";//Unable to add Vehicle Information, Please Contact System Administrator.";


            }
        }
        return result;


    }

    public String addCustomerPayeeInformation(serviceForm sf, String userid, String jobno, String vinno) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String results = "";

        try {

            hrbsession.beginTransaction();


            /* cal = Calendar.getInstance();
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            month = month + 1;
             */
            Jobcarddetails jc = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, sf.getJobCardNo());

            Vehicledetails vd = (Vehicledetails) hrbsession.load(Vehicledetails.class, sf.getVinid());



            //   hql1 = "update VehicleDetails set CustomerName=?,VillageName=?,Taluka=?,Tehsil=?,District=?,Pincode=?,State=?,Country=?,MobilePh=?,LandlineNo=?,emailId=?,sizeLandHolding=?, mainCrops=?, occupation=? where vinno=?";
//
            //  hql2 = "update jobcarddetails set PayeeName=?,village=?,Taluka=?,Tehsil=?,District=?,Pincode=?,State=?,Country=?,MobilePhone=?,Landline=?,emailid=? where  JobCardNo=?";

            //pstmt1 = conn.prepareStatement(hql2);

            if (sf.getVin_details_type().equals("MANUAL")) {
                // pstmt = conn.prepareStatement(hql1);
                vd.setCustomerName(sf.getCustomerName());
                vd.setVillageName(sf.getVillage());

                vd.setTehsil(sf.getTehsil());
                vd.setDistrict(sf.getDistrict());
                if (sf.getPinCode() != null || !sf.getPinCode().equals("")) {
                    vd.setPincode(Long.parseLong(sf.getPinCode()));
                }
                else {
                    vd.setPincode(null);
                }
                vd.setState(sf.getState());
                vd.setCountry(sf.getCountry());
                vd.setMobilePh(Long.parseLong(sf.getMobilePhone()));
                vd.setLandlineNo(sf.getLandline());
                vd.setEmailId(sf.getEmailId());
                vd.setSizeLandHolding(sf.getSizeoflandhold());
                vd.setMainCrops(sf.getMaincrops());
                vd.setOccupation(sf.getOccupation());
                vd.setVinNo(vinno);
                vd.setLastUpdatedDate(new java.util.Date());
                hrbsession.save(vd);

            }




//rollback

//JobCardid,PayeeName,village,Taluka,Tehsil,District,Pincode,State,Country,MobilePhone,Landline,emailid





            jc.setPayeeName(sf.getPayeeName());
            jc.setVillage(sf.getPayeeVillage());

            jc.setTehsil(sf.getPayeeTehsil());
            jc.setDistrict(sf.getPayeeDistrict());
            if (!sf.getPayeePinCode().equals("")) {
                jc.setPincode(Long.parseLong(sf.getPayeePinCode()));
            }
            else {
                jc.setPincode(null);
            }
            jc.setState(sf.getPayeeState());
            jc.setCountry(sf.getPayeeCountry());
            jc.setMobilePhone(Long.parseLong(sf.getPayeeMobilePhone()));
            jc.setLandline(sf.getPayeeLandline());
            jc.setEmailid(sf.getPayeeEmailId());
            jc.setJobCardNo(jobno);
            jc.setLastModifiedBy(userid);
            jc.setLastModifiedOn(new java.util.Date());
            hrbsession.save(jc);

            hrbsession.getTransaction().commit();




            results = "Success@@custInfoSuccess";// Payee Information has been Successfully Added for Job card NO is " + jobno;
        }
        catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();
            results = "Failure@@custInfoFailure";//Unable to add Payee Information, Please Contact System Administrator.";
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@custInfoFailure";//Unable to add Payee Information, Please Contact System Administrator.";
            }
        }
        return results;
    }

    //get all Job card details created on 15/05/14 by Megha
    public ArrayList<serviceForm> getJobCardDetails(serviceForm serviceForm, String SearchValue, String ColumnName, String user_id, Vector userFunctionalities) throws SQLException
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String Subsql = "";
        String dateSubQur = "", statusQur = "";
        String SearchQuery = SearchValue == null ? "" : SearchValue;
        ArrayList<serviceForm> jobcardList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ArrayList<String> dealerList = new ArrayList<String>();

        try {
            if (!SearchQuery.equals("")) {
                Subsql = " and jd." + ColumnName + " like '%" + SearchQuery + "%'";
            }
            if ("1".equals(serviceForm.getRange())) {
                dateSubQur = " and (jd.jobCardDate between isnull(?,jd.jobCardDate) and isnull(?,jd.jobCardDate) )";
            }
            Query query = null;
            if (!serviceForm.getJobCardStatus().equalsIgnoreCase("ALL")) {
                statusQur = " and jd.status=:status ";
            }
            String hql = "select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,"
                    + "u.location,u.locationCode ,jd.status,jd.mobilePhone from Jobcarddetails jd, "
                    + "Dealervslocationcode u where jd.dealerCode=u.dealerCode  "
                    + Subsql + dateSubQur + " and :dealerList LIKE ('%'+jd.dealerCode+'%') "
                    + statusQur + " order by jd.jobCardDate desc";

            query = session.createQuery(hql);
            if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(serviceForm.getDealercode());
            }
            else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, user_id);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
           // query.setParameterList("dealerList", dealerList);

            /*           if (userFunctionalities.contains("101")) {
            query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
            + ",jd.status,jd.mobilePhone from Jobcarddetails jd, Dealervslocationcode u "
            + " where  " //(jd.jobCardDate between isnull(?,jd.jobCardDate) and isnull(?,jd.jobCardDate)) and
            + " jd.dealerCode=u.dealerCode  " + Subsql + dateSubQur + " and jd.dealerCode=:dealerCode  " + statusQur + " order by jd.jobCardDate desc");
            query.setParameter("dealerCode", serviceForm.getDealercode());
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
            + ",jd.status,jd.mobilePhone from Jobcarddetails jd, Dealervslocationcode u "
            + " where  " //(jd.jobCardDate between isnull(?,jd.jobCardDate) and isnull(?,jd.jobCardDate))
            + " jd.dealerCode=u.dealerCode  " + Subsql + dateSubQur + " and jd.dealerCode IN(:dealerList)  " + statusQur + "  order by jd.jobCardDate desc");
            if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
            dealerList.add(serviceForm.getDealercode());
            query.setParameterList("dealerList", dealerList);
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user_id + "'");
            query.setParameterList("dealerList", dealerList);
            }
            } else if (userFunctionalities.contains("103")) {
            if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
            query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
            + ",jd.status,jd.mobilePhone from Jobcarddetails jd, Dealervslocationcode u  where  jd.dealerCode=u.dealerCode "
            + "   " + Subsql + dateSubQur + "and jd.dealerCode=:dealerCode  " + statusQur + " order by jd.jobCardDate desc");    //(jd.jobCardDate between isnull(?,jd.jobCardDate) and isnull(?,jd.jobCardDate))
            query.setParameter("dealerCode", serviceForm.getDealercode());
            } else {
            query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
            + ",jd.status,jd.mobilePhone from Jobcarddetails jd, Dealervslocationcode u  where  jd.dealerCode=u.dealerCode "
            + "   " + Subsql + dateSubQur + "  " + statusQur + "  order by jd.jobCardDate desc");    //(jd.jobCardDate between isnull(?,jd.jobCardDate) and isnull(?,jd.jobCardDate))
            }
            }
             *
             */
            if (!serviceForm.getJobCardStatus().equalsIgnoreCase("ALL")) //Avinash 21-07-2015
            {
                query.setParameter("status", serviceForm.getJobCardStatus());
            }
            if ("1".equals(serviceForm.getRange())) {
                query.setString(0, serviceForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(serviceForm.getFromdate() + " 00:00")));
                query.setString(1, serviceForm.getTodate().equals("") == true ? null : df.format(sdf.parse(serviceForm.getTodate() + " 23:59")));
            }
            List list = query.list();
            //System.out.println(query);
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            df = new SimpleDateFormat("yyyy-MM-dd");
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
                    serForm.setJobCardDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setJobCardDate(serForm.getJobCardDate().equals("") ? "" : sdf.format(df.parse(serForm.getJobCardDate())));
                    serForm.setVinNo(obj[2] == null ? "" : obj[2].toString().trim());
                    serForm.setPayeeName(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setDealercode(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealerName(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setLocationName(obj[6] == null ? "" : obj[6].toString().trim());
                    serForm.setJobCardStatus(obj[8] == null ? "" : obj[8].toString().trim());
                    serForm.setMobilePhone(obj[9] == null ? "" : obj[9].toString().trim());
                    jobcardList.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jobcardList;
    }

    //
    public LinkedHashSet<LabelValueBean> getBayCode()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "FROM Baymaster where isActive='Y'  order by bayName";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();

            while (itr.hasNext()) {

                Baymaster bay = (Baymaster) itr.next();

                name = bay.getBayName() == null ? "" : bay.getBayName();
                id = bay.getBayCode() == null ? "" : bay.getBayCode();
                //flag = rs.getString(3) == null ? "" : rs.getString(3).trim();
                // discount = rs.getString(4);
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Mechanic
    public LinkedHashSet<LabelValueBean> getMechanicCode(String dealercode)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        MSWDmechanicmaster mm = null;


        String name = "", id = "";
        //String hql = " select distinct MSWDmechanicmaster.mechanicName, MSWDmechanicmasterPK.mechanicCode FROM MSWDmechanicmaster where isActive='Y' and workertype='Mechanic' order by mechanicName";
        String hql = " FROM MSWDmechanicmaster where isActive='Y' and  dealerCode='" + dealercode + "' order by mechanicName"; //workertype='Mechanic' and
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {

                //Object []o=(Object[])itr.next();
                mm = (MSWDmechanicmaster) itr.next();
                name = mm.getMechanicName() == null ? "" : mm.getMechanicName() + " (" + mm.getMechanicCode() + ")";
                id = mm.getMechanicDealerKey() == null ? "" : mm.getMechanicDealerKey();
                //flag = rs.getString(3) == null ? "" : rs.getString(3).trim();
                // discount = rs.getString(4);
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getInspectionBy(String dealercode)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        MSWDmechanicmaster mm = null;


        //String hql = " select distinct MSWDmechanicmaster.mechanicName, MSWDmechanicmasterPK.mechanicCode FROM MSWDmechanicmaster where isActive='Y' and workertype='Mechanic' order by mechanicName";
        // String hql = " FROM MSWDmechanicmaster where isActive='Y' and workertype='Foreman'  and dealerCode='" + dealercode + "'  order by mechanicName";
        String hql = " FROM MSWDmechanicmaster where isActive='Y' and dealerCode='" + dealercode + "'  order by mechanicName";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                //Object []o=(Object[])itr.next();
                mm = (MSWDmechanicmaster) itr.next();
                name = mm.getMechanicName() == null ? "" : mm.getMechanicName() + " (" + mm.getMechanicCode() + ")";
                id = mm.getMechanicDealerKey() == null ? "" : mm.getMechanicDealerKey();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String addStatus(serviceForm sf, String userid, String actiontype) throws SQLException
    {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
//        String jobcardno = "";
//        java.sql.Date date = null;
//        Calendar cal = null;
//        String sql1 = "";
//
//        int insertF = 0;

        // System.out.println("enter in dao");

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // java.sql.Timestamp campaigndate = new java.sql.Timestamp(sdf.parse(commForm.getCampaigndate()).getTime());

        try {

            query = hrbsession.createQuery("SELECT  jobCardNo FROM  Jobcarddetails WHERE jobCardNo='" + sf.getJobCardNo() + "'");
            itr = query.list().iterator();
            hrbsession.beginTransaction();
            String temp = "";
            if (itr.hasNext()) {

                //       jobcardno = itr.next().toString();
                Jobcarddetails jcs = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, sf.getJobCardNo());

                if (sf.getWorkStarted() != null && !sf.getWorkStarted().equals("")) {
                    temp = sf.getWorkStarted() + " " + sf.getWorkStartedHrs() + ":" + sf.getWorkStartedMins();
                    jcs.setWorkStartDate(sdf1.parse(temp));
                }
                else {
                    jcs.setWorkStartDate(null);
                }
                //pstmt.setTimestamp(2, new java.sql.Timestamp(sdf.parse(temp).getTime()));
                if (sf.getWorkFinished() != null && !sf.getWorkFinished().equals("")) {
                    temp = sf.getWorkFinished() + " " + sf.getWorkFinishedHrs() + ":" + sf.getWorkFinishedMins();
                    jcs.setWorkFinishDate(sdf1.parse(temp));
                }
                else {
                    jcs.setWorkFinishDate(null);
                }
                //pstmt.setTimestamp(3, new java.sql.Timestamp(sdf.parse(temp).getTime()));
                //JobCardId,WorkStartDate,WorkFinishDate,VehicleOutDate,BayDesc,MechanicName,InspectedBy
                if (sf.getVehicleOut() != null && !sf.getVehicleOut().equals("")) {
                    temp = sf.getVehicleOut() + " " + sf.getVehicleOutHrs() + ":" + sf.getVehicleOutMins();
                    jcs.setVehicleOutDate(sdf1.parse(temp));
                }
                else {
                    jcs.setVehicleOutDate(null);
                }
                //jcs.setTimestamp(4, new java.sql.Timestamp(sdf.parse(temp).getTime()));
                jcs.setBayDesc(sf.getBayCode() == null ? "" : sf.getBayCode().trim());
                jcs.setMechanicName(sf.getMechanicName() == null ? "" : sf.getMechanicName().trim());
                jcs.setInspectedBy(sf.getInspectionBy() == null ? "" : sf.getInspectionBy().trim());
                jcs.setLastModifiedBy(userid);
                jcs.setLastModifiedOn(new java.util.Date());
                jcs.setOpenRejectRemarks(sf.getRemarks() == null ? "" : sf.getRemarks().trim());
                jcs.setReasonForDealy(sf.getReasonForDealy() == null ? "" : sf.getReasonForDealy().trim());
                if (actiontype.equals("saveandclose")) {
                    jcs.setStatus("CLOSE");
                }
                hrbsession.update(jcs);
                hrbsession.createSQLQuery("exec SP_UpdateJobCardDailyConsumption :jobCardNo, :userId").setParameter("jobCardNo", sf.getJobCardNo()).setParameter("userId", userid).executeUpdate();
                hrbsession.getTransaction().commit();
                results = "Success@@statusSuccess";//Status Time Information and Mechanic Information has been Successfully Added";
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();
            results = "Failure@@statusFailure";//Unable to add Status Time Information and Mechanic Information, Please contact system Administrator.";
        }
        finally {
            try {

                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;

                }

            }
            catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@statusFailure";//Unable to add Status Time Information and Mechanic Information, Please contact system Administrator.";
            }
        }
        return results;
    }

    public serviceForm getJobCard_vehicale_DetailFor_singleJobcard(serviceForm sf, String jobCardNo, String vinNo) throws SQLException
    {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        String sql = null;

        try {


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");


            // System.out.println("hhhhhhhhhhh");

            if (dbPATH.equalsIgnoreCase("MYSQL")) {
                System.out.println("inside");
                sql = Get_vehicle_JobcardDetail_for_givenJobcardNo_Mysql + jobCardNo + "'";
            }
            else {
                System.out.println("outside");
                sql = Get_vehicle_JobcardDetail_for_givenJobcardNo_Sql + jobCardNo + "'";
            }
            System.out.println("query:" + sql);

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            // rs = stmt.executeQuery(sql);
            if (itr.hasNext()) {

                Object o[] = (Object[]) itr.next();
                System.out.println("Length of O "+ o.length);
                sf.setProductionCategory("" + o[0].toString());
                sf.setVinNo(o[1] == null ? "" : o[1].toString());
                sf.setEngineNumber(o[2] == null ? "" : o[2].toString());
                sf.setDeliveryDate(o[3] == null ? "" : sdf1.format(sdf.parse(o[3].toString())));
                sf.setRegistrationNo(o[4] == null ? "" : o[4].toString());
                sf.setServiceBookletNo(o[5] == null ? "" : o[5].toString());
                sf.setKeyIdentificationNo(o[6] == null ? "" : o[6].toString());
                sf.setOwnerDriven(o[7] == null ? "" : o[7].toString());
                sf.setJobCardNo(o[8] == null ? "" : o[8].toString());
                sf.setDealerJobCardNo(o[9] == null ? "" : o[9].toString());
                sf.setJobType("" + o[10] == null ? "" : o[10].toString());
                sf.setHmeRadio(o[11] == null ? "" : o[11].toString());
                sf.setHmr(o[12] == null ? "" : o[12].toString());
                sf.setJobLocation(o[13] == null ? "" : o[13].toString());
                sf.setNextService(o[14] == null ? "" : o[14].toString());
                sf.setWarrantyApplicable(o[15] == null ? "" : o[15].toString());
                sf.setModelFamily(o[16] == null ? "" : o[16].toString());
                sf.setModelCode(o[17] == null ? "" : o[17].toString());
                sf.setModelFamilyDesc(o[18] == null ? "" : o[18].toString());
                sf.setModelCodeDesc(o[19] == null ? "" : o[19].toString());
                sf.setServiceType(o[20] == null ? "" : o[20].toString());
                sf.setEvent(o[21] == null ? "" : o[21].toString());

                sf.setJobCardDate(o[22] == null ? "" : sdf1.format(sdf.parse(o[22].toString())));
                sf.setStatus(o[23] == null ? "" : o[23].toString());
                sf.setDealerJobCardNo(o[24] == null ? "" : o[24].toString());
                sf.setCouponno(o[25] == null ? "" : o[25].toString());
                sf.setJobcardpriority(o[26] == null ? "" : o[26].toString());
                sf.setVin_details_type(o[27] == null ? "" : o[27].toString());

                //  System.out.println("vinid=="+o[29].toString());

                sf.setVinid(o[29] == null ? "" : o[29].toString());
                //GetDateandTimevalue(String datevalue,String parameter)
                if (o[28] != null) {
                    sf.setCurrentEstimateHrs(new MethodUtility().GetDateandTimevalueforVehicle(o[28].toString().trim(), "min"));
                    sf.setCurrentEstimateTime(new MethodUtility().GetDateandTimevalueforVehicle(o[28].toString().trim(), "hr"));
                }
                sf.setItlExtWarrantyApplicable(o[31] == null ? "" : o[31].toString());
                sf.setVorJobcard(o[32] == null ? "" : o[32].toString().trim());
              
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }

    //w.months,w.Hrs
    public String getWarrantyModeldetail(String modelcode, String saledate) throws Exception
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String hql1 = null, result = "notexist";
        Query query = null;
        Iterator itr = null;
        try {
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String check = check_in_Db(modelcode, "WarrantyModel", "modelCode", "", hrbsession);
            // System.out.println("checkvalue==" + check);
            if (check.equals("notexist")) {
                return "notexist";
            }
            hql1 = "select months,hrs from WarrantyModel where modelCode=? and ((wtyStartDate <='" + (new java.sql.Timestamp(sdf.parse(saledate).getTime())) + "' and wtyEndDate>'" + (saledate== null ? "9999-01-01" :new java.sql.Timestamp(sdf.parse(saledate).getTime())) + "' and wtyStartDate is not NULL) or (wtyStartDate <='" + (new java.sql.Timestamp(sdf.parse(saledate).getTime())) + "'  and wtyEndDate is NULL))";
            // hql1 = "select months,hrs from WarrantyModel where modelCode=? ";
            query = hrbsession.createQuery(hql1);
            query.setString(0, modelcode);
            itr = query.list().iterator();
            // System.out.println("inside he warrentmodel");

            if (itr.hasNext()) {
                //System.out.println("inside the itr");
                Object o[] = (Object[]) itr.next();
                result = o[0].toString().trim() + "@@" + o[1].toString().trim();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getVinDetails(String vinno, String dealercode) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        String hql = null, result = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {

            hql = "from Vehicledetails where vinNo=? and dealerCode=? ";

            Query query = hrbsession.createQuery(hql);

            query.setString(0, vinno);
            query.setString(1, dealercode);

            Iterator itr = query.list().iterator();

            // System.out.println("vinno" + vinno);

            if (itr.hasNext()) {
                Vehicledetails vd = (Vehicledetails) itr.next();
                //saledate = vd.getSaleDate().toString();//rs.getDate("SaleDate").toString();
                //  modelcode = vd.getModelCode();//rs.getString("ModelCode");

                //result = rs.getString("EngineNo") + "@@" + sdf.format(rs.getDate("SaleDate")) + "@@" + rs.getString("RegNo") + "@@" + rs.getString("SerBookNo") + "@@" + rs.getString("KeyIdentification") + "@@" + rs.getString("OwnerDriven") + "@@" + rs.getString("ModelFamily") + "@@" + rs.getString("ModelCode") + "@@" + rs.getString("ModelFamilyDesc") + "@@" + rs.getString("ModelDesc") + "@@" + rs.getString("DealerCode") + "@@" + rs.getString("NextService");
                result = (vd.getEngineNo() == null ? "" : vd.getEngineNo()) + "@@";
                result = result + (vd.getDeliveryDate() == null ? "" : sdf.format(vd.getDeliveryDate())) + "@@";
                result = result + (vd.getRegNo() == null ? "" : vd.getRegNo()) + "@@";
                result = result + (vd.getSerBookNo() == null ? "" : vd.getSerBookNo()) + "@@";
                result = result + (vd.getKeyIdentification() == null ? "" : vd.getKeyIdentification()) + "@@";
                result = result + (vd.getOwnerDriven() == null ? "" : vd.getOwnerDriven()) + "@@";
                result = result + (vd.getModelFamily() == null ? "" : vd.getModelFamily()) + "@@";
                result = result + (vd.getModelCode() == null ? "" : vd.getModelCode()) + "@@";
                result = result + (vd.getModelFamilyDesc() == null ? "" : vd.getModelFamilyDesc()) + "@@";
                result = result + (vd.getModelCodeDesc() == null ? "" : vd.getModelCodeDesc()) + "@@";
                result = result + (vd.getDealerCode() == null ? "" : vd.getDealerCode()) + "@@";
                result = result + (vd.getNextService() == null ? "" : vd.getNextService()) + "@@";
                result = result + (vd.getVinid() == null ? "" : vd.getVinid()) + "@@";
                result = result + (vd.getVinDetailsType() == null ? "" : vd.getVinDetailsType());
                // System.out.println("result==" + result);
            }
            else {
                result = "";
            }

            //   monthhrs=getWarrantyModeldetail(vinno,modelcode,saledate,conn);

            //  result=result+"@@"+monthhrs;


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //getVimDetailsFromServer
    /*
    public String getVimDetailsFromServer(String vinno, Connection conn1) throws SQLException {


    int i = 0, j = 0;
    PreparedStatement stmt4 = null;//
    String sql = null, result = "";
    String sql1 = "", sql3 = "";
    String sql2 = "";


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {


    sql = " select vinNo,EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService,CustomerName,VillageName,Taluka,Tehsil,District,Pincode,State,Country,MobilePh,LandlineNo,emailId,Is_ServerSync,sizeLandHolding,mainCrops,occupation,PDI_STATUS,INS_STATUS from vehicledetails where vinNo=? ";
    System.out.println("vinno" + vinno);
    stmt = conn1.prepareStatement(sql);
    stmt.setString(1, vinno);
    rs = stmt.executeQuery();



    System.out.println("query:" + sql);
    if (rs.next()) {
    i = 1;
    result = rs.getString("EngineNo") + "@@" + sdf.format(rs.getDate("SaleDate")) + "@@" + rs.getString("RegNo") + "@@" + rs.getString("SerBookNo") + "@@" + rs.getString("KeyIdentification") + "@@" + rs.getString("OwnerDriven") + "@@" + rs.getString("ModelFamily") + "@@" + rs.getString("ModelCode") + "@@" + rs.getString("ModelFamilyDesc") + "@@" + rs.getString("ModelDesc") + "@@" + rs.getString("DealerCode") + "@@" + rs.getString("NextService");
    } else {

    return "notexist";
    }

    //check for local db if exist
    sql3 = "select * from vehicledetails where vinno='" + vinno + "'";
    stmt4 = conn.prepareStatement(sql3);
    rs2 = stmt4.executeQuery();
    if (rs2.next()) {
    stmt4.executeUpdate("delete FROM  vehicledetails WHERE vinNo='" + vinno + "'");
    j = 1;
    }


    //local
    sql = "insert into vehicledetails (vinNo,EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,ModelCode,ModelFamilyDesc,ModelDesc,CustomerName,VillageName,Taluka,Tehsil,District,Pincode,State,Country,MobilePh,LandlineNo,emailId,Is_ServerSync,DealerCode,NextService,sizeLandHolding,mainCrops,occupation,PDI_STATUS,INS_STATUS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //server
    sql1 = "select JobId,JobTypeId,ProductCatId,HMRWorking,HMR,EventId,LocationId,NextService,Is_warranty_req,ServiceTypeId,PromiseDate,ReqCustDate,EstDate,JobCardNo,DealerJCNo,LastUpdatedDate,Status,Is_ServerSync,CreatedBy,JobCardDate,CurrentEstimateTime,CurrentEstimateHrs,DealerCode,PayeeName,village,Taluka,Tehsil,District,Pincode,State,Country,MobilePhone,Landline,emailid,createdon,vinno,engineno from JOBCARDDETAILS where vinno='" + vinno + "'";

    //local
    sql2 = "insert into jobcarddetails( JobId,JobTypeId,ProductCatId,HMRWorking,HMR,EventId,LocationId,NextService,Is_warranty_req,ServiceTypeId,PromiseDate,ReqCustDate,EstDate,JobCardNo,DealerJCNo,LastUpdatedDate,Status,Is_ServerSync,CreatedBy,JobCardDate,CurrentEstimateTime,CurrentEstimateHrs,DealerCode,PayeeName,village,Taluka,Tehsil,District,Pincode,State,Country,MobilePhone,Landline,emailid,createdon,vinno,engineno)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //

    if (i == 1) {

    rs = stmt.executeQuery();
    if (rs.next()) {
    System.out.println("inside 1");
    stmt1 = conn.prepareStatement(sql);
    stmt1.setString(1, rs.getString("vinNo"));
    stmt1.setString(2, rs.getString("EngineNo"));
    stmt1.setString(3, rs.getString("SaleDate"));
    stmt1.setString(4, rs.getString("RegNo"));
    stmt1.setString(5, rs.getString("SerBookNo"));
    stmt1.setString(6, rs.getString("KeyIdentification"));
    stmt1.setString(7, rs.getString("OwnerDriven"));
    stmt1.setString(8, rs.getString("ModelFamily"));
    stmt1.setString(9, rs.getString("ModelCode"));
    stmt1.setString(10, rs.getString("ModelFamilyDesc"));
    stmt1.setString(11, rs.getString("ModelDesc"));
    stmt1.setString(12, rs.getString("CustomerName"));
    stmt1.setString(13, rs.getString("VillageName"));
    stmt1.setString(14, rs.getString("Taluka"));
    stmt1.setString(15, rs.getString("Tehsil"));
    stmt1.setString(16, rs.getString("District"));
    stmt1.setString(17, rs.getString("Pincode"));
    stmt1.setString(18, rs.getString("State"));
    stmt1.setString(19, rs.getString("Country"));
    stmt1.setString(20, rs.getString("MobilePh"));
    stmt1.setString(21, rs.getString("LandlineNo"));
    stmt1.setString(22, rs.getString("emailId"));
    stmt1.setString(23, "Y");
    stmt1.setString(24, rs.getString("DealerCode"));
    stmt1.setString(25, rs.getString("NextService"));
    stmt1.setString(26, rs.getString("sizeLandHolding"));//sizeLandHolding,mainCrops,occupation
    stmt1.setString(27, rs.getString("mainCrops"));
    stmt1.setString(28, rs.getString("occupation"));
    stmt1.setString(29, rs.getString("PDI_STATUS"));
    stmt1.setString(30, rs.getString("INS_STATUS"));//PDI_STATUS,INS_STATUS

    //;
    //stmt1.executeUpdate();
    System.out.println("lasthere==" + stmt1.executeUpdate());
    }


    stmt2 = conn1.prepareStatement(sql1);
    rs1 = stmt2.executeQuery();
    stmt3 = conn.prepareStatement(sql2);

    while (rs1.next()) {

    String check = check_in_Db(rs1.getString("JobCardNo"), "jobcarddetails", "JobCardNo", "");

    if (!check.equals("exist")) {
    System.out.println("inside 2");
    stmt3.setString(1, rs1.getString("JobId"));
    stmt3.setString(2, rs1.getString("JobTypeId"));
    stmt3.setString(3, rs1.getString("ProductCatId"));
    stmt3.setString(4, rs1.getString("HMRWorking"));
    stmt3.setString(5, rs1.getString("HMR"));
    stmt3.setString(6, rs1.getString("EventId"));
    stmt3.setString(7, rs1.getString("LocationId"));
    stmt3.setString(8, rs1.getString("NextService"));
    stmt3.setString(9, rs1.getString("Is_warranty_req"));
    stmt3.setString(10, rs1.getString("ServiceTypeId"));
    stmt3.setString(11, rs1.getString("PromiseDate"));
    stmt3.setString(12, rs1.getString("ReqCustDate"));
    stmt3.setString(13, rs1.getString("EstDate"));
    stmt3.setString(14, rs1.getString("JobCardNo"));
    stmt3.setString(15, rs1.getString("DealerJCNo"));
    stmt3.setString(16, rs1.getString("LastUpdatedDate"));
    stmt3.setString(17, rs1.getString("Status"));
    stmt3.setString(18, "Y");
    stmt3.setString(19, rs1.getString("CreatedBy"));
    stmt3.setString(20, rs1.getString("JobCardDate"));
    stmt3.setString(21, rs1.getString("CurrentEstimateTime"));
    stmt3.setString(22, rs1.getString("CurrentEstimateHrs"));
    stmt3.setString(23, rs1.getString("DealerCode"));
    stmt3.setString(24, rs1.getString("PayeeName"));
    stmt3.setString(25, rs1.getString("village"));
    stmt3.setString(26, rs1.getString("Taluka"));
    stmt3.setString(27, rs1.getString("Tehsil"));
    stmt3.setString(28, rs1.getString("District"));
    stmt3.setString(29, rs1.getString("Pincode"));
    stmt3.setString(30, rs1.getString("State"));
    stmt3.setString(31, rs1.getString("Country"));
    stmt3.setString(32, rs1.getString("MobilePhone"));
    stmt3.setString(33, rs1.getString("Landline"));
    stmt3.setString(34, rs1.getString("emailid"));
    stmt3.setString(35, rs1.getString("createdon"));
    stmt3.setString(36, rs1.getString("vinno"));
    stmt3.setString(37, rs1.getString("engineno"));
    stmt3.setString(38, "Y");
    System.out.println("lasthere2==" + stmt3.executeUpdate());
    }
    }
    conn.commit();
    }





    } catch (Exception e) {
    e.printStackTrace();
    } finally {
    try {
    if (rs != null) {
    rs.close();
    rs = null;
    }
    if (stmt != null) {
    stmt.close();
    stmt = null;
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
    }
    return result;
    }
     */
    public String getVimDetailsFromServer(String vinno, Connection conn1, String dealerCode) throws SQLException
    {
        Session hrbsessionforlive = Hibernate_Util.getSessionFactory().openSession();
        Session hrbsessionforlocal = HibernateUtil.getSessionFactory().openSession();
        int i = 0, j = 0;
        // String vinid = "";
        String hql = "", result = "";//, hql1 = "", hql3 = "", check = "";
        Iterator itr = null;//, itr1, itr2 = null;
        Query query;//, query1, query2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            hql = " from Vehicledetails where vinNo=? ";
            query = hrbsessionforlive.createQuery(hql);
            query.setString(0, vinno);
            itr = query.list().iterator();
            hrbsessionforlocal.beginTransaction();
            hrbsessionforlive.createSQLQuery("exec SP_getVehicleCustomerID :customerCode,:dealerCode").setParameter("customerCode", vinno).setParameter("dealerCode", dealerCode);
            //qtr.executeUpdate();

            if (itr.hasNext()) {
                i = 1;
                Vehicledetails vds = (Vehicledetails) itr.next();
                // System.out.println("vd.getModelCodeDesc()" + vd.getModelCodeDesc());
                result = (vds.getEngineNo() == null ? "" : vds.getEngineNo()) + "@@";
                result = result + (vds.getDeliveryDate() == null ? "" : sdf.format(vds.getDeliveryDate())) + "@@";
                result = result + (vds.getRegNo() == null ? "" : vds.getRegNo()) + "@@";
                result = result + (vds.getSerBookNo() == null ? "" : vds.getSerBookNo()) + "@@";
                result = result + (vds.getKeyIdentification() == null ? "" : vds.getKeyIdentification()) + "@@";
                result = result + (vds.getOwnerDriven() == null ? "" : vds.getOwnerDriven()) + "@@";
                result = result + (vds.getModelFamily() == null ? "" : vds.getModelFamily()) + "@@";
                result = result + (vds.getModelCode() == null ? "" : vds.getModelCode()) + "@@";
                result = result + (vds.getModelFamilyDesc() == null ? "" : vds.getModelFamilyDesc()) + "@@";
                result = result + (vds.getModelCodeDesc() == null ? "" : vds.getModelCodeDesc()) + "@@";
                result = result + (vds.getDealerCode() == null ? "" : vds.getDealerCode()) + "@@";
                result = result + (vds.getNextService() == null ? "" : vds.getNextService()) + "@@";
                result = result + (vds.getVinid() == null ? (dealerCode + "-" + vds.getVinNo()) : (dealerCode + "-" + vds.getVinNo())) + "@@";
                result = result + (vds.getVinDetailsType() == null ? "MANUAL" : vds.getVinDetailsType());

                Vehicledetails vd = new Vehicledetails();//abc
                vd.setVinNo(vds.getVinNo());
                vd.setEngineNo(vds.getEngineNo());
                vd.setDeliveryDate(vds.getDeliveryDate());
                vd.setRegNo(vds.getRegNo());
                vd.setSerBookNo(vds.getSerBookNo());
                vd.setKeyIdentification(vds.getKeyIdentification());

                vd.setOwnerDriven(vds.getKeyIdentification());

                vd.setModelFamily(vds.getModelFamily());
                vd.setModelCode(vds.getModelCode());
                vd.setModelFamilyDesc(vds.getModelFamilyDesc());
                vd.setModelCodeDesc(vds.getModelCodeDesc());
                vd.setCustomerName(vds.getCustomerName());
                vd.setVillageName(vds.getVillageName());
                vd.setTehsil(vds.getTehsil());
                vd.setDistrict(vds.getDistrict());
                vd.setPincode(vds.getPincode());
                vd.setState(vds.getState());
                vd.setCountry(vds.getCountry());
                vd.setMobilePh(vds.getMobilePh());
                vd.setLandlineNo(vds.getLandlineNo());
                vd.setEmailId(vds.getEmailId());
                vd.setIsServerSync('Y');//Is_ServerSync
                vd.setDealerCode(dealerCode);
                vd.setVinDetailsType(vds.getVinDetailsType());
                vd.setNextService(vds.getNextService());
                vd.setSizeLandHolding(vds.getSizeLandHolding());//sizeLandHolding,mainCrops,occupation
                vd.setMainCrops(vds.getMainCrops());
                vd.setOccupation(vds.getOccupation());

                vd.setPdiPendingDate(vds.getPdiPendingDate());
                vd.setLastUpdatedDate(vds.getLastUpdatedDate());
                vd.setItlInvoiceDate(vds.getItlInvoiceDate());
                vd.setItlInvoiceNo(vds.getItlInvoiceNo());
                vd.setDealerInvoiceNo(vds.getDealerInvoiceNo());
                vd.setDeliveryDate(vds.getDeliveryDate());

                vd.setVinid(dealerCode + "-" + vds.getVinNo());
                if (vds.getDealerCode().equalsIgnoreCase(dealerCode)) {
                    vd.setPdiStatus(vds.getPdiStatus());
                    vd.setInsStatus(vds.getInsStatus());//PDI_STATUS,INS_STATUS
                }
                else {
                    vd.setPdiStatus('Y');
                    vd.setInsStatus('Y');//PDI_STATUS,INS_STATUS
                }


                hrbsessionforlocal.save(vd);
                hrbsessionforlocal.getTransaction().commit();
                //   vinid = vd.getVinid();
                //  }
            }
            else {
                return "notexist";
            }

        }
        catch (Exception e) {
            hrbsessionforlocal.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsessionforlocal != null) {
                    hrbsessionforlocal.close();

                }
                if (hrbsessionforlive != null) {
                    hrbsessionforlive.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getVinNoList(String searchname, String jcType, String user_id, String dealercode)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        /// String result = "";
        boolean flag = false;
        String hql = null, hql1 = null, hql2 = null;
        StringBuilder data = new StringBuilder("<listdata>");
        try {

            if (jcType != null && jcType.equalsIgnoreCase("PDI")) {
                hql = "Select distinct vinNo from Vehicledetails where vinNO like '%" + searchname + "%' and dealerCode='" + dealercode + "'";// and pdiStatus='Y'";
            }
            else if (jcType != null && jcType.equalsIgnoreCase("JPDI")) {  //jobcard for PDI

                hql = "Select distinct vinNo from Vehicledetails where vinNO like '%" + searchname + "%' and dealerCode='" + dealercode + "'";// and pdiStatus='Y' and insStatus='N'";

            }
            else if (jcType != null && jcType.equalsIgnoreCase("VPDI")) {  //view for PDI
                hql = "Select distinct vinNo from Vehicledetails where vinNO like '%" + searchname + "%' and dealerCode='" + dealercode + "'";// and pdiStatus='Y'";
            }
            else {
                hql = "Select distinct vinNo from Vehicledetails where vinNO like '%" + searchname + "%' and dealerCode='" + dealercode + "'";// and pdiStatus='Y' and insStatus='Y'";
            }


            int counter = 0;

            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();
            while (itr.hasNext()) {

                counter++;

                data.append(itr.next().toString().trim() + "|");
            }


            if (counter == 0) {

                hql = "Select distinct vinNo from Vehicledetails where vinNo ='" + searchname + "' and dealerCode='" + dealercode + "'";
                Query query2 = hrbsession.createQuery(hql);
                Iterator itr2 = query2.list().iterator();
                if (itr2.hasNext()) {
                    counter++;
                    data.append("S");
                    flag = true;
                }
            }




            //for other dealer chassis
            if (flag == false && counter == 0) {

                // System.out.println("inside1");

                hql = "from Vehicledetails where vinNo=? and nextService='Y'";

                Query query3 = hrbsession.createQuery(hql);
                query3.setString(0, searchname);
                Iterator itr3 = query3.list().iterator();

                //searchname
                if (itr3.hasNext()) {
                    // System.out.println("inside2");
                    hql2 = "Select distinct vinNo from Vehicledetails where vinNo ='" + searchname + "'";
                    Query query1 = hrbsession.createQuery(hql2);
                    Iterator itr1 = query1.list().iterator();
                    if (itr1.hasNext()) {
                        counter++;
                        data.append(itr.next().toString().trim() + "|");

                    }
                }
            }



            data.append("</listdata>");



        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data.toString();

    }

    // Get the details of Customer for Vehicle No
    public serviceForm getCustomerDetailsVinNo(serviceForm sf, String vinID) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {


            Query query = hrbsession.createQuery(get_custDetailsVinNo);
            query.setString(0, vinID);
            Iterator itr = query.list().iterator();
            //System.out.println("query:" + get_custDetailsVinNo);
            if (itr.hasNext()) {
                Vehicledetails vd = (Vehicledetails) itr.next();
                sf.setCustomerName(vd.getCustomerName());
                sf.setVillage(vd.getVillageName());
                sf.setTehsil(vd.getTehsil());
                sf.setDistrict(vd.getDistrict());
                sf.setPinCode(Long.toString(vd.getPincode()));
                sf.setState(vd.getState());
                sf.setCountry(vd.getCountry());
                sf.setMobilePhone(Long.toString(vd.getMobilePh()));
                sf.setLandline(vd.getLandlineNo());
                sf.setEmailId(vd.getEmailId());
                sf.setSizeoflandhold(vd.getSizeLandHolding());//sizeLandHolding     mainCrops     occupation
                sf.setMaincrops(vd.getMainCrops());
                sf.setOccupation(vd.getOccupation());
                sf.setVinNo(vd.getVinNo());
                sf.setVinid(vinID);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }
// get CustomerDetail for given JobcardNo created on 19/05/14 by Megha

    public serviceForm getCustomerDetail4JobcardNo(serviceForm sf, String jobCardNo, String vinid) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        //    String hql = null;

        Vehicledetails vd = null;
        Criteria criteria = null;
        Iterator itr = null;
        //    Iterator itr1 = null;
        Jobcarddetails jc = null;
        //      Dealervslocationcode dl = null;


        try {

            // System.out.println("vinid==" + vinid);
            vd = (Vehicledetails) hrbsession.get(Vehicledetails.class, vinid);

            // System.out.println("vd" + vd);

            criteria = hrbsession.createCriteria(Jobcarddetails.class);

            criteria.add(Restrictions.eq("jobCardNo", jobCardNo));

            itr = criteria.list().iterator();

            if (itr.hasNext()) {
                jc = (Jobcarddetails) itr.next();

                sf.setPayeeName(jc.getPayeeName());
                sf.setPayeeVillage(jc.getVillage());
                sf.setPayeeTehsil(jc.getTehsil());
                sf.setStatus(jc.getStatus());
                sf.setPayeeDistrict(jc.getDistrict());
                sf.setPayeePinCode(jc.getPincode() == null ? "" : Long.toString(jc.getPincode()));
                sf.setPayeeState(jc.getState());
                sf.setPayeeCountry(jc.getCountry());
                sf.setPayeeMobilePhone(jc.getMobilePhone() == null ? "" : Long.toString(jc.getMobilePhone()));
                if (jc.getLandline() != null) {
                    sf.setPayeeLandline(jc.getLandline() == null ? "" : jc.getLandline());
                }
                sf.setPayeeEmailId(jc.getEmailid());

            }
            if (vd != null) {
                sf.setCustomerName(vd.getCustomerName());
                sf.setVillage(vd.getVillageName());
                sf.setTehsil(vd.getTehsil());
                sf.setDistrict(vd.getDistrict());
                sf.setPinCode(vd.getPincode() == null ? "" : Long.toString(vd.getPincode()));
                sf.setState(vd.getState());
                sf.setCountry(vd.getCountry());
                sf.setMobilePhone(vd.getMobilePh() == null ? "" : Long.toString(vd.getMobilePh()));
                sf.setLandline(vd.getLandlineNo());
                sf.setEmailId(vd.getEmailId());
                sf.setJobCardNo(jobCardNo);
                sf.setVinNo(vd.getVinNo());
                sf.setSizeoflandhold(vd.getSizeLandHolding());//sizeLandHolding     mainCrops     occupation
                sf.setMaincrops(vd.getMainCrops());
                sf.setOccupation(vd.getOccupation());
                sf.setVinNo(vd.getVinNo());
            }
            /*
            jd.PayeeName,jd.village,jd.Taluka,jd.Tehsil,jd.District,jd.Pincode,jd.State
            jd.Country,jd.MobilePhone,jd.Landline,jd.emailid,vd.CustomerName,vd.VillageName,
            vd.Taluka,vd.Tehsil,vd.District,vd.Pincode,vd.State,vd.Country,vd.MobilePh,vd.LandlineNo,
            vd.emailId,vd.sizeLandHolding,vd.mainCrops,vd.occupation
            from jobcarddetails jd " +
            JOIN vehicledetails as vd ON jd.vinNo = vd.vinNo" +
            where jd.JobCardNo='";


             */
            //stmt = conn.createStatement();

//
//            hql = Get_CustomerDetail4JobcardNo + jobCardNo + "'";
//            Query query =hrbsession.createQuery(hql);
//            itr=query.list().iterator();
//            if (itr.hasNext()) {

//                sf.setPayeeName(rs.getString(1));
//                sf.setPayeeVillage(rs.getString(2));
//                sf.setPayeeTaluka(rs.getString(3));
//                sf.setPayeeTehsil(rs.getString(4));
//                sf.setPayeeDistrict(rs.getString(5));
//                sf.setPayeePinCode(rs.getString(6));
//                sf.setPayeeState(rs.getString(7));
//                sf.setPayeeCountry(rs.getString(8));
//                sf.setPayeeMobilePhone(rs.getString(9));
//                sf.setPayeeLandline(rs.getString(10));
//                sf.setPayeeEmailId(rs.getString(11));


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }

    //getCustomerDetail4JobcardNoByDealer
    public serviceForm getCustomerDetail4JobcardNoByDealer(serviceForm sf, String userid, String dealercode) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            Iterator itr1 = null;
            Dealervslocationcode dl = null;
            //Query query = hrbsession.createQuery(Get_CustomerDetail4JobcardNoByDealer + dealercode + "'");
            Criteria cr = hrbsession.createCriteria(Dealervslocationcode.class);
            cr.add(Restrictions.eq("dealerCode", sf.getDealercode()));
            itr1 = cr.list().iterator();
            if (itr1.hasNext()) {
                dl = (Dealervslocationcode) itr1.next();

                sf.setPayeeName(dl.getDealerName());
                sf.setPayeePinCode("");
                sf.setPayeeState(dl.getStateName());
                sf.setPayeeCountry("");
                sf.setPayeeMobilePhone(dl.getContactNo());
                sf.setPayeeEmailId("");
                sf.setCustomerName(dl.getDealerName());
                sf.setPinCode("");
                sf.setState(dl.getStateName());
                sf.setCountry("");
                sf.setMobilePhone(dl.getContactNo());
                sf.setEmailId("");

            }



        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }

// get header detail for given JobcardNo created on 19/05/14 by Megha
    public serviceForm getHeaderDetail4JobcardNo(serviceForm sf, String jobCardNo) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String hql = null;
        try {

            hql = Get_headerDetail4jobcardno + jobCardNo + "'";
            //jd.jobCardNo,vd.vinNo,jp.JobTypeDesc,pm.productCategory,jd.iswarrantyreq,jd.engineno,jd.jobTypeId,vd.modelCode
            //            System.out.println("query:" + sql);
            Query query = hrbsession.createQuery(hql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                System.out.println("Length of Array "+o.length);
                sf.setJobCardNo(o[0].toString());
                sf.setVinNo(o[1].toString());
                sf.setJobTypeDesc(o[2] == null ? "" : o[2].toString());
                sf.setProductionCategory_Desc(o[3] == null ? "" : o[3].toString());
                sf.setWarrantyApplicable(o[4] == null ? "N" : o[4].toString());
                sf.setEngineNumber(o[5] == null ? "" : o[5].toString());
                sf.setJobType(o[6] == null ? "" : o[6].toString());
                sf.setModelCode(o[7] == null ? "" : o[7].toString());
                sf.setVinid(o[8] == null ? "" : o[8].toString());
                sf.setStatus(o[9] == null ? "" : o[9].toString());
                sf.setPayeeName(o[10] == null ? "" : o[10].toString());
                sf.setPayeeMobilePhone(o[11] == null ? "" : o[11].toString());
                sf.setTotalPartsValue(o[12] == null ? "0.0" : o[12].toString());//totalPartsValue ,totalLubesValue ,totalOtherCharges,totalLabourCharges
                sf.setTotalLubesValue(o[13] == null ? "0.0" : o[13].toString());
                sf.setTotalOtherCharges(o[14] == null ? "0.0" : o[14].toString());
                sf.setTotalLabourCharges(o[15] == null ? "0.0" : o[15].toString());
                sf.setTotalactuals(o[16] == null ? "0.0" : o[16].toString());

                if (o[17] != null) {
                    //sdf.parse(o[17].toString());

                    sf.setHrs(new MethodUtility().GetDateandTimevalue(sdf.format(sdf.parse(o[17].toString())), "hr"));
                    sf.setMin(new MethodUtility().GetDateandTimevalue(sdf.format(sdf.parse(o[17].toString())), "min"));
                    sf.setJobCardDate(o[17] == null ? "" : sdf1.format(sdf.parse(o[17].toString())));
                }

                sf.setCustomerName(o[18] == null ? "" : o[18].toString());
                sf.setMobilePhone(o[19] == null ? "" : o[19].toString());
                sf.setTotaldiscount(o[20] == null ? "0.0" : o[20].toString());
                sf.setDealerName(o[21] == null ? "" : o[21].toString());
                sf.setDealercode(o[22] == null ? "" : o[22].toString());
                sf.setModelFamilyDesc(o[23] == null ? "" : o[23].toString());
                if (o[24] != null) {
                    sf.setComplaintDate(sdf1.format(sdf.parse(o[24].toString())));
                }
                else {
                    sf.setComplaintDate("");
                }
                
                System.out.println("details for testing");
                
                if(o[25] != null)
                System.out.println("o[25] " +o[25].toString());
                
                else
                	System.out.println("it is null");
                
//                if(o[26] != null)
//                    System.out.println("o[26] " +o[26].toString());
//                    
//                    else
//                    	System.out.println("it is null");
//                
                sf.setItlExtWarrantyApplicable(o[25] == null ? "" : o[25].toString());
                sf.setVorJobcard(o[26] == null ? "" : o[26].toString().trim());

                ////jd.ttlActualPartsAmt,jd.ttlActualLubesAmt,jd.ttlActualOtherChargesAmt,jd.ttlActualLabourChargesAmt
                //sf.setJobId(o[9].toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }
// Get status Detail for given jobCardNo created on 20/05/14 by Megha

    public serviceForm getStatusDetail4jobCardNo(serviceForm sf, String jobCardNo) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        //Query query = null;
       // Iterator itr = null;
        //String hql1 = null, result = "";

        //String sql = null;
        sf.setJobcardview("true");
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            //stmt = conn.createStatement();
//            if (dbPATH.equalsIgnoreCase("MYSQL")) {
//                sql = Get_statusDetail4jobCardNo_mysql + jobCardNo + "'";
//            } else {
//                sql = Get_statusDetail4jobCardNo_sql + jobCardNo + "'";
//            }

            Jobcarddetails jd = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, jobCardNo);

            sf.setWorkStarted(jd.getWorkStartDate() == null ? "" : sdf.format(jd.getWorkStartDate()));
            sf.setWorkFinished(jd.getWorkFinishDate() == null ? "" : sdf.format(jd.getWorkFinishDate()));//
            //serviceForm.setJobCardDate(jc.getJobCardDate() == null ? "" : sdf.format(jc.getJobCardDate()));//
            sf.setVehicleOut(jd.getVehicleOutDate() == null ? "" : sdf.format(jd.getVehicleOutDate()));
            sf.setBayCode(jd.getBayDesc() == null ? "" : jd.getBayDesc().toString());
            sf.setMechanicName(jd.getMechanicName() == null ? "" : jd.getMechanicName().trim());
            sf.setInspectionBy(jd.getInspectedBy() == null ? "" : jd.getInspectedBy().trim());
            sf.setRemarks(jd.getOpenRejectRemarks() == null ? "" : jd.getOpenRejectRemarks().trim());
            sf.setReasonForDealy(jd.getReasonForDealy() == null ? "" : jd.getReasonForDealy().trim());
//            System.out.println(sf.getMechanicName()+"-"+sf.getBayCode()+"-"+sf.getInspectionBy()+sf.getWorkStarted()+"-"+sf.getWorkFinished()+"-"+sf.getVehicleOut());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }

    public serviceForm getComplaintDetail4JobcardNo(serviceForm sf, String jobCardNo) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String hql = null, hql1 = null;
        long compID = 0;
        ArrayList<String> compVerbatim = new ArrayList<String>();
        String applicationCode[] = new String[10];
        String aggCode[] = new String[10];
        //   String causalCode[] = new String[10];
        String subaggCode[] = new String[10];
        String subassembly[] = new String[10];
        String compCode[] = new String[10];
        String foremanObservation[] = new String[10];
        String atmCaseCode[] = new String[10];
        String biPartCode[] = new String[10];

        
        ArrayList<LinkedHashSet<LabelValueBean>> subagg = new ArrayList<LinkedHashSet<LabelValueBean>>();
        ArrayList<LinkedHashSet<LabelValueBean>> defectcode = new ArrayList<LinkedHashSet<LabelValueBean>>();
        ArrayList<LinkedHashSet<LabelValueBean>> subassemblyarr = new ArrayList<LinkedHashSet<LabelValueBean>>();
        //   ArrayList<LinkedHashSet<LabelValueBean>> cause = new ArrayList<LinkedHashSet<LabelValueBean>>();
        ArrayList<LinkedHashSet<LabelValueBean>> conseq = new ArrayList<LinkedHashSet<LabelValueBean>>();
        ArrayList<LinkedHashSet<LabelValueBean>> atmCase = new ArrayList<LinkedHashSet<LabelValueBean>>();
        ArrayList<LinkedHashSet<LabelValueBean>> biPart = new ArrayList<LinkedHashSet<LabelValueBean>>();
        String compid[] = new String[10];

        try {


            hql = Get_ComplaintDetail4JobcardNo + jobCardNo + "'";

            Query query = hrbsession.createQuery(hql);

            Iterator itr = query.list().iterator();

            int x = 0;
            while (itr.hasNext()) {
                int i = x++;

                Jobcomplaintdetails jcd = (Jobcomplaintdetails) itr.next();

//              compVerbatim.add(rs.getString("CustVerbatim"));

                compVerbatim.add(jcd.getCustVerbatim());
                //     applicationCode[i] = rs.getString("AppCode");
                applicationCode[i] = jcd.getAppCode();
                //     aggCode[i] = rs.getString("AggCode");
                aggCode[i] = jcd.getAggCode();

                //causalCode[i] = rs.getString("CauseCode");
                //       causalCode[i] = jcd.getCauseCode();

                //     subaggCode[i] = rs.getString("SubAggCode");
                subaggCode[i] = jcd.getSubAggCode();

                //     vendorCode[i] = rs.getString("VendorCode");
                subassembly[i] = jcd.getSubassemblyCode();

                //     foremanObservation[i] = rs.getString("foremanObsv");
                foremanObservation[i] = jcd.getForemanObsv();

                //     compCode[i] = rs.getString("DefectCode");
                compCode[i] = jcd.getDefectCode();

                //     compid[i] = rs.getString("CMP_ID");
                compid[i] = jcd.getCmpNo();

                // System.out.println("cmp id in dao" + compid[i]);

                //  System.out.println("application code" + applicationCode[i]);
                compID = jcd.getCmpId();
                
                
                
                atmCaseCode[i] = jcd.getAtmCase();
                biPartCode[i] = jcd.getBiPart();
                

                //rs1 = stmt1.executeQuery(hql1);
                defectcode.add(new masterDAO().getCommonCodeById(subassembly[i], "subassembly_Code"));//Agg_Code
                subagg.add(new masterDAO().getCommonCodeById(aggCode[i], "Agg_Code"));//subAgg_Code
                subassemblyarr.add(new masterDAO().getCommonCodeById(subaggCode[i], "subAgg_Code"));//subassembly_Code
                //    cause.add(new masterDAO().getCommonCodeById(compCode[i], "Comp_Code"));//Comp_Code
                //      conseq.add(new masterDAO().getCommonCodeById(causalCode[i], "Causal_Code"));//Causal_Code


                hql1 = Get_ComplaintDetailConsequences4JobcardNo + jcd.getCmpNo() + "'";

                Query query1 = hrbsession.createQuery(hql1);

                            }

            sf.setCompid(Long.toString(compID));

            // System.out.println("jocardno " + jobCardNo);
            String[] compVerbatim1 = compVerbatim.toArray(new String[compVerbatim.size()]);
            sf.setCompVerbatim(compVerbatim1);
            sf.setApplicationCode(applicationCode);
            sf.setAggCode(aggCode);
            // sf.setCausalCode(causalCode);
            sf.setSubaggCode(subaggCode);
            sf.setSubassembly(subassembly);
            sf.setCompCode(compCode);
            sf.setCmpid(compid);
            sf.setJobCardNo(jobCardNo);
            sf.setAtmCaseCode(atmCaseCode);
            sf.setBiPartCode(biPartCode);

            sf.setSubagg(subagg.toArray(new LinkedHashSet[subagg.size()]));
            sf.setDefectcode(defectcode.toArray(new LinkedHashSet[defectcode.size()]));
            sf.setSubassmblylabel(subassemblyarr.toArray(new LinkedHashSet[subassemblyarr.size()]));
            //   sf.setCause(cause.toArray(new LinkedHashSet[cause.size()]));
            sf.setConseq(conseq.toArray(new LinkedHashSet[conseq.size()]));
            sf.setForemanObservation(foremanObservation);
            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf;
    }

    
// Get status Detail for given jobCardNo created on 20/05/14 by Megha
    public String getEstimateDetail4jobCardNo(serviceForm sf, String jobCardNo, String priceListCode) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String hql = null;

        ArrayList<serviceForm> partList, lubesList = null;
        ArrayList<serviceForm> labourchargeList = null;
        ArrayList<serviceForm> otherchargeList = null;
        double partvalue = 0, lubvalue = 0, labourchg = 0, otherchg = 0, estchg = 0, amt = 0;
        String result = "No Data";
        Query query = null, query1 = null, query2 = null,queryForMOQ=null;
        String pricevalue = "";
        //Iterator itr=null;
        ArrayList partnolist = new ArrayList();
        ArrayList qtylist = new ArrayList();
        partList = new ArrayList<serviceForm>();
        lubesList = new ArrayList<serviceForm>();
        Iterator itr = null, itr1 = null, itr2 = null, itr3 = null, itr4 = null, itr5 = null, itr6 = null, itr7 = null;
        try {
//jd.jobCardNo,jd.promiseDate,jd.reqCustDate,jd.estDate,js.partCategory,
            itr4 = hrbsession.createQuery("select ttlEstimatePartsAmt FROM  Jobcarddetails WHERE jobCardNo='" + jobCardNo + "'").list().iterator();

            if (itr4.hasNext()) {
                Double val = (Double) itr4.next();
                if (val == null) {
                    itr5 = hrbsession.createQuery("select jobTypeId,modelcode,partNo,qty FROM  Standardjobpartmaster WHERE modelcode='" + sf.getModelCode() + "' and jobTypeId='" + sf.getJobType() + "'").list().iterator();

                    while (itr5.hasNext()) {
                        Object o[] = (Object[]) itr5.next();
                        partnolist.add(o[2] == null ? "" : o[2].toString());
                        qtylist.add(o[3] == null ? "" : o[3].toString());
                    }

                    if (!partnolist.isEmpty()) {
                        for (int k = 0; k < partnolist.size(); k++) {
                            itr6 = hrbsession.createQuery("select partType FROM  CatPart WHERE partNo='" + partnolist.get(k).toString() + "'").list().iterator();
                            itr7 = hrbsession.createQuery("select price FROM  SpPriceMaster WHERE id.item='" + partnolist.get(k).toString() + "'  and id.pricelistCode='" + priceListCode + "'").list().iterator();


                            serviceForm servForm = new serviceForm();
                            if (itr6.hasNext()) {
                                Object o[] = (Object[]) itr6.next();
                                servForm.setPart_category(o[0] == null ? "" : o[0].toString());
                                servForm.setPartNo_str(partnolist.get(k) == null ? "" : partnolist.get(k).toString());
                                servForm.setPartDesc_str(o[1] == null ? "" : o[1].toString());
                                servForm.setQty_str(qtylist.get(k) == null ? "" : qtylist.get(k).toString());
                                System.out.println("jobCardNo "+jobCardNo);
                                System.out.println(servForm.getPartNo_str() +" "+ sf.getDealercode());
                                String inventoryQty = getInventoryQty(servForm.getPartNo_str(), sf.getDealercode());
                                servForm.setInventoryqty(getInventoryQty(servForm.getPartNo_str(), sf.getDealercode()));
                            }
                            servForm.setUnitprice_str("0");
                            servForm.setDbqty("0");
                            servForm.setAmount_str("0.0");
                            servForm.setFinalamt_str("0.0");
                            if (itr7.hasNext()) {
                                pricevalue = itr7.next().toString();
                                servForm.setUnitprice_str(pricevalue == null ? "" : pricevalue);
                                amt = Double.parseDouble(pricevalue) * Double.parseDouble(qtylist.get(k).toString());
                                servForm.setAmount_str(Double.toString(amt));
                                servForm.setFinalamt_str(Double.toString(amt));
                            }

                            if (servForm.getPart_category().equalsIgnoreCase("LUBES")) {

                                lubesList.add(servForm);


                            }
                            else {
                                if (qtylist.get(k).toString().contains(".")) {
                                    servForm.setQty_str(qtylist.get(k) == null ? "" : qtylist.get(k).toString().substring(0, qtylist.get(k).toString().indexOf(".")));
                                }
                                System.out.println("jobCardNo "+jobCardNo);
                                System.out.println(servForm.getPartNo_str() +" "+ sf.getDealercode());
                                String inventoryQty = getInventoryQty(servForm.getPartNo_str(), sf.getDealercode());
                                servForm.setInventoryqty(servForm.getInventoryqty().substring(0, servForm.getInventoryqty().indexOf(".")));
                                partList.add(servForm);

                            }

                        }

                    }
                }
            }




            itr3 = hrbsession.createQuery("select jd.jobCardNo,jd.promiseDate,jd.reqCustDate,jd.estDate from Jobcarddetails jd where jd.jobCardNo='" + jobCardNo + "'").list().iterator();
            if (itr3.hasNext()) {
                Object o[] = (Object[]) itr3.next();
                serviceForm servForm = new serviceForm();
                servForm.setJobId(o[0] == null ? "" : o[0].toString());
                sf.setPromisedstr(o[1] == null ? "" : o[1].toString());
                sf.setRequiredBYCustomerstr(o[2] == null ? "" : o[2].toString());
                sf.setCurrentEstimatestr(o[3] == null ? "" : o[3].toString());

            }

            if (dbPATH.equalsIgnoreCase("MYSQL")) {
                hql = Get_Estimate_partDetail4jobcardNo_mysql + jobCardNo + "'";


            }
            else {
                hql = Get_Estimate_partDetail4jobcardNo_sql + jobCardNo + "'";
            }



            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                serviceForm servForm = new serviceForm();
//                servForm.setJobId(o[0] == null ? "" : o[0].toString());
//                sf.setPromisedstr(o[1] == null ? "" : o[1].toString());
//                sf.setRequiredBYCustomerstr(o[2] == null ? "" : o[2].toString());
//                sf.setCurrentEstimatestr(o[3] == null ? "" : o[3].toString());

//s.partCategory,js.partNo ,js.partDesc,js.unitPrice,js.qty,js.amount,js.billID,js.discInPerc,
//js.finalAmount,js.cmpNo,js.causalOrConseq,jd.ttlEstimatePartsAmt,jd.ttlEstimateLubesAmt,
//jd.ttlEstimateLabourChargesAmt,jd.ttlEstimateOtherChargesAmt
                servForm.setPart_category(o[0] == null ? "" : o[0].toString());
                servForm.setPartNo_str(o[1] == null ? "" : o[1].toString());
                servForm.setPartDesc_str(o[2] == null ? "" : o[2].toString());
                servForm.setUnitprice_str(o[3] == null ? "" : o[3].toString());
                servForm.setQty_str(o[4] == null ? "" : o[4].toString());
                servForm.setDbqty("0");
                servForm.setAmount_str(o[5] == null ? "" : o[5].toString());
                servForm.setBillto_str(o[6] == null ? "" : o[6].toString());
                servForm.setDiscount_str(o[7] == null ? "" : o[7].toString());
                servForm.setFinalamt_str(o[8] == null ? "" : o[8].toString());
                
                
                //added by aman 23-04-2025
                String sql = "EXEC GetMoqByPartCode ?";
				String moq = null;

				queryForMOQ = hrbsession.createSQLQuery(sql).addScalar("MOQ", StandardBasicTypes.STRING);
				queryForMOQ.setParameter(0, o[1] == null ? "" : o[1].toString());
                Object res = queryForMOQ.uniqueResult();

				if (res != null) 
					moq = res.toString(); 
                
				servForm.setMoq(moq);
				boolean output = isPartNoInLubesPart(o[1] == null ? "" : o[1].toString());
				if (output) {
				    servForm.setIsLubePartInLitre("Y");
				} else {
				    servForm.setIsLubePartInLitre("N");
				}
	
                //js.ttlEstimatePartsAmt,js.ttlEstimateLubesAmt,js.ttlEstimateLabourChargesAmt,js.ttlEstimateOtherChargesAmt" +
                servForm.setInventoryqty(getInventoryQty(servForm.getPartNo_str(), sf.getDealercode()));
                if (servForm.getPart_category().equalsIgnoreCase("LUBES")) {

                    lubesList.add(servForm);
                    lubvalue += Double.valueOf(servForm.getFinalamt_str());

                }
                else {
                    if (o[4].toString().contains(".")) {
                        servForm.setQty_str(o[4] == null ? "" : o[4].toString().substring(0, o[4].toString().indexOf(".")));
                    }
                    servForm.setInventoryqty(servForm.getInventoryqty().substring(0, servForm.getInventoryqty().indexOf(".")));
                    partList.add(servForm);
                    partvalue += Double.valueOf(servForm.getFinalamt_str());
                }
                result = "Have data";
//               System.out.println(servForm.getAmount_str() + "" + servForm.getBillto_str() + "-" + servForm.getDiscount_str() + "-" + servForm.getFinalamt_str());
            }
            sf.setTotalPartsValue("" + df.format(partvalue));
            sf.setTotalLubesValue("" + df.format(lubvalue));
            sf.setPartList(partList);
            sf.setLubesList(lubesList);
//            System.out.println(result+"---"+sf.getMechanicName()+"-"+sf.getBayCode()+"-"+sf.getInspectionBy()+sf.getWorkStarted()+"-"+sf.getWorkFinished()+"-"+sf.getVehicleOut());
            labourchargeList = new ArrayList<serviceForm>();


            query = hrbsession.createQuery(Get_EstimateLabourCharges4jobCardNo + sf.getJobCardNo() + "'");
            itr1 = query.list().iterator();






            query1 = hrbsession.createQuery(getestimateServicehrs);


            double hrs = 0.0;
            String servicehrs = "";
            while (itr1.hasNext()) {
//for service hrs
                Object[] o = (Object[]) itr1.next();
                query1.setString(0, o[1].toString().trim());
                query1.setString(1, o[0].toString().trim());
                query1.setString(2, sf.getJobCardNo());
                // rs1 = pmst.executeQuery();
                itr2 = query1.list().iterator();
                if (itr2.hasNext()) {
                    hrs = Double.parseDouble(itr2.next().toString());
                    servicehrs = Double.toString(hrs);
                }

                // System.out.println("servicehrs" + servicehrs);
                serviceForm servForm = new serviceForm();
                servForm.setComplaintCode_str(o[3].toString().trim() + "@@" + o[0].toString().trim());
                servForm.setActionTaken_str(o[1].toString().trim() + "@@" + servicehrs);

                servForm.setLabourChargesAmount_str(o[2].toString().trim());
                labourchargeList.add(servForm);
                labourchg += Double.valueOf(servForm.getLabourChargesAmount_str());
                // System.out.println(o[1].toString().trim() + "" + servForm.getActionTaken_str() + "-cmp" + servForm.getComplaintCode_str() + "-" + servForm.getLabourChargesAmount_str());
            }
            sf.setLabourchargeList(labourchargeList);
            sf.setTotalLabourCharges("" + labourchg);
            otherchargeList = new ArrayList<serviceForm>();
            query1 = hrbsession.createQuery(Get_EstimateOtherCharges4jobCardNo + sf.getJobCardNo() + "'");
            //rs = stmt.executeQuery(Get_EstimateOtherCharges4jobCardNo + sf.getJobCardNo() + "'");
            itr1 = query1.list().iterator();
            while (itr1.hasNext()) {

                /*
                "select eo.workCode,eo.workDescription,eo.workAmount" +
                " from jobcarddetails jd" +
                " join estimateothercharges as eo  ON jd.JobId = eo.JobId" +
                " where jd.JobCardNo='"
                 */
                Object[] o = (Object[]) itr1.next();
                serviceForm servForm = new serviceForm();
                servForm.setWorkCode_str(o[0].toString().trim());
                servForm.setWorkDescription_str(o[1].toString().trim());
                servForm.setWorkAmount_str(o[2].toString().trim());
                otherchargeList.add(servForm);
                otherchg += Double.valueOf(servForm.getWorkAmount_str());
//                System.out.println(rs.getString(1)+"-"+servForm.getWorkAmount_str()+"-"+servForm.getWorkDescription_str()+"-"+servForm.getWorkCode_str());
            }
            sf.setOtherchargeList(otherchargeList);
            sf.setTotalOtherCharges("" + otherchg);
            sf.setTotalEstimate("" + df.format((partvalue + lubvalue + labourchg + otherchg)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    // By anand 22 may

    public void checkStandardPart(serviceForm sf)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String hql = null;
        ArrayList<String> partno = new ArrayList<String>();
        ArrayList<String> partdesc = new ArrayList<String>();
        ArrayList<String> partCat = new ArrayList<String>();
        ArrayList<String> unitprice = new ArrayList<String>();
        ArrayList<String> qty = new ArrayList<String>();
        ArrayList<String> partPriceAmount = new ArrayList<String>();
        ArrayList<String> lubesNo = new ArrayList<String>();
        ArrayList<String> lubesDesc = new ArrayList<String>();
        ArrayList<String> lubesUnitPrice = new ArrayList<String>();
        ArrayList<String> lubesQuantityS = new ArrayList<String>();
        ArrayList<String> lubesPriceAmount = new ArrayList<String>();


        ArrayList<String> partCat1 = new ArrayList<String>();
        // System.out.println("model no" + sf.getModelCode() + sf.getJobType());
        String constantValue = "";
        Query query = null, query1 = null;
        Iterator itr = null, itr1 = null;
        try {

            //hql = "select distinct(JobId) from Jobcarddetails jc  join jobcard_spares_charges jsc on jc.JobId=jsc.job_id where jc.JobCardNo='" + sf.getJobCardNo() + "'";
            hql = "select distinct(jc.jobCardNo) from Jobcarddetails jc , JobcardSparesCharges jsc where jc.jobCardNo='" + sf.getJobCardNo() + "' and jc.jobCardNo=jsc.jobCardNo";
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                sf.setStndprt("NO");
            }
            else {
                sf.setStndprt("YES");


                //hql = "SELECT jcd.JobTypeId,vd.ModelCode FROM jobcarddetails jcd join vehicledetails vd on jcd.vinno=vd.vinNo where jcd.JobCardNo='" + sf.getJobCardNo() + "'";
                hql = "SELECT distinct jcd.jobTypeId,vd.modelCode FROM Jobcarddetails jcd , Vehicledetails vd  where jcd.jobCardNo='" + sf.getJobCardNo() + "' and jcd.vinno=vd.vinNo ";
                itr1 = query.list().iterator();

                if (itr1.hasNext()) {
                    Object o[] = (Object[]) itr1.next();
                    sf.setJobType(o[0].toString());
                    sf.setModelCode(o[1].toString());
                }
                // System.out.println("model code and job type" + sf.getModelCode() + " " + sf.getJobType());

                //hql = "SELECT sj.partNo,sj.partDesc,sj.partCategory,sj.unitPrice,sj.qty FROM Standardjobpartmaster sj join jobtypemaster jt on sj.JobTypeId=jt.JobTypeID where sj.JobTypeId='" + sf.getJobType() + "' and sj.modelcode='" + sf.getModelCode() + "';";
                hql = "SELECT sj.partNo,sj.partDesc,sj.partCategory,sj.unitPrice,sj.qty FROM Standardjobpartmaster sj , jobtypemaster jt  where sj.jobTypeId='" + sf.getJobType() + "' and sj.modelcode='" + sf.getModelCode() + "' and sj.jobTypeId=jt.jobTypeID";
                itr1 = query.list().iterator();
                while (itr1.hasNext()) {
                    Object o[] = (Object[]) itr1.next();
                    if (o[2] != null && o[2].toString().trim().equals("SPARES")) {
                        // System.out.println("SPARES");
                        partno.add(o[0].toString());
                        partdesc.add(o[1].toString());
                        unitprice.add(o[2].toString());
                        qty.add(o[4].toString());
                        partPriceAmount.add(Float.toString(Float.parseFloat(o[3].toString()) * Float.parseFloat(o[4].toString())));
                        partCat.add(o[2].toString());
                    }
                    else {
                        //System.out.println("LUBES");
                        lubesNo.add(o[0].toString());
                        lubesDesc.add(o[1].toString());
                        lubesUnitPrice.add(o[3].toString());
                        lubesQuantityS.add(o[4].toString());
                        lubesPriceAmount.add(Float.toString(Float.parseFloat(o[3].toString()) * Float.parseFloat(o[4].toString())));
                        partCat1.add(o[2].toString());
                    }
                }
                sf.setPartNo(partno.toArray(new String[partno.size()]));
                sf.setPartDesc(partdesc.toArray(new String[partdesc.size()]));
                sf.setComptype(partCat.toArray(new String[partCat.size()]));
                sf.setLubesComptype(partCat1.toArray(new String[partCat1.size()]));

                sf.setUnitPrice(unitprice.toArray(new String[unitprice.size()]));
                sf.setQuantityS(qty.toArray(new String[qty.size()]));
                sf.setPartPriceAmount(partPriceAmount.toArray(new String[partPriceAmount.size()]));
                sf.setLubesNo(lubesNo.toArray(new String[lubesNo.size()]));
                sf.setLubesDesc(lubesDesc.toArray(new String[lubesDesc.size()]));
                sf.setLubesUnitPrice(lubesUnitPrice.toArray(new String[lubesUnitPrice.size()]));
                sf.setLubesQuantityS(lubesQuantityS.toArray(new String[lubesQuantityS.size()]));
                sf.setLubesPriceAmount(lubesPriceAmount.toArray(new String[lubesPriceAmount.size()]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     *Method to display all the OPEN job cards pending for close
     *
     */
    public ArrayList<serviceForm> getJobCardOpenList1(serviceForm serviceForm, String userid, String dealercode) throws SQLException
    {
		Session hrbsession = HibernateUtil.getSessionFactory().openSession();
		Query query = null;
		Iterator itr = null;
		Query q = null;
		ArrayList<serviceForm> dataList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// SELECT jobCardNo,vinno,jobCardDate, jobCardPriority,
		// status,isWarrantyReq,payeeName, mobilePhone FROM Jobcarddetails where
		// dealerCode=? or createdBy=?

		try {
			dataList = new ArrayList<serviceForm>();
			/*
			 query = hrbsession.createQuery("FROM Jobcarddetails where status='CLOSE' and
			 (dealerCode=? or createdBy=?)");
			 query = hrbsession.createQuery("FROM Jobcarddetails jd left join
			 SpInventSaleMaster sm on sm.typeRefno=jd.jobCardNo where status='CLOSE' and
			 (jd.dealerCode=? or jd.createdBy=?)");
			 query.setString(0, dealercode);
			 query.setString(1, userid);
			 rs = stmt.executeQuery("SELECT JobCardNo,vinno,JobCardDate, jobCardPriority,
			 status,Is_warranty_req,PayeeName, MobilePhone FROM jobcarddetails WHERE
			 Status='OPEN'");
			 itr = query.list().iterator();
			 */
			
			/*
			String sql = "select sj.JobCardNo, sj.vinno, sj.JobCardDate, sj.jobCardPriority, sj.Status, sj.Is_warranty_req, sj.PayeeName, sj.MobilePhone, sj.JobTypeId from SW_jobcarddetails(nolock) sj left join SP_INVENT_SALE_MASTER(nolock) SM ON SJ.JobCardNo= SM.Type_Ref_no"
					+ " where  SM.Type_Ref_no is null and  Status='CLOSE' and sj.DealerCode=:dealerCode";
					
			*/
			
			String sql = 
				    "SELECT sj.JobCardNo, sj.vinno, sj.JobCardDate, sj.jobCardPriority, " +
				    "sj.Status, sj.Is_warranty_req, sj.PayeeName, sj.MobilePhone, sj.JobTypeId " +
				    "FROM SW_jobcarddetails (NOLOCK) sj " +
				    "WHERE sj.Status = 'CLOSE' " +
				    "AND sj.DealerCode = :dealerCode " +
				    "AND NOT EXISTS ( " +
				    "   SELECT 1 FROM SP_INVENT_SALE_MASTER (NOLOCK) sm " +
				    "   WHERE sm.Type_Ref_no = sj.JobCardNo " +
				    ")";
			
			
			q = hrbsession.createSQLQuery(sql).addScalar("JobCardNo", new StringType())
					.addScalar("vinno", new StringType()).addScalar("JobCardDate", new DateType()). 
					addScalar("jobCardPriority", new StringType()) 
					.addScalar("Status", new StringType()).addScalar("Is_warranty_req", new StringType())
					.addScalar("PayeeName", new StringType()).addScalar("MobilePhone", new StringType())
					.addScalar("JobTypeId", new IntegerType()) 
					.setParameter("dealerCode", dealercode);

			List<Object[]> res = q.list();
			System.out.println(res.size());
			for(Object[] o : res) {
				 serviceForm = new serviceForm();
	                serviceForm.setJobCardNo((String)o[0]);
	                serviceForm.setVinNo((String)o[1]);
	                Date jobCardDate = (Date) o[2]; 
	                String formattedDate = sdf.format(jobCardDate);
	                serviceForm.setJobCardDate(formattedDate);
	                serviceForm.setJobcardpriority((String)o[3]);
	                serviceForm.setStatus((String)o[4]);
	                serviceForm.setWarrantyApplicable((String)o[5]);
	                serviceForm.setPayeeName((String)o[6]);
	                serviceForm.setMobilePhone((String)o[7]);
	                serviceForm.setJobId(Integer.toString((int) o[8]));
	                dataList.add(serviceForm);	
			}
            
			/*
            while (itr.hasNext()) {
                Jobcarddetails jc = (Jobcarddetails) itr.next();

                //Object o[]=(Object[])itr.next();
                serviceForm = new serviceForm();
                serviceForm.setJobCardNo(jc.getJobCardNo() == null ? "" : jc.getJobCardNo().trim());
                serviceForm.setVinNo(jc.getVinno() == null ? "" : jc.getVinno().trim());
                serviceForm.setJobCardDate(jc.getJobCardDate() == null ? "" : sdf.format(jc.getJobCardDate()));
                serviceForm.setJobcardpriority(jc.getJobCardPriority() == null ? "" : jc.getJobCardPriority());
                serviceForm.setStatus(jc.getStatus() == null ? "" : jc.getStatus());
                serviceForm.setWarrantyApplicable(jc.getIswarrantyreq() + "" == null ? "" : jc.getIswarrantyreq() + "");
                serviceForm.setPayeeName(jc.getPayeeName() == null ? "" : jc.getPayeeName());
                serviceForm.setMobilePhone(jc.getMobilePhone() == null ? "" : Long.toString(jc.getMobilePhone()));
                serviceForm.setJobTypeId(jc.getJobTypeId() == null ? "" : String.valueOf(jc.getJobTypeId()));
                serviceForm.setJobId(Integer.toString(jc.getJobid()));
                dataList.add(serviceForm);
//                System.out.println(""+serviceForm.getJobCardNo());
            }
            */
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    
    public ArrayList<serviceForm> getJobCardOpenList(serviceForm serviceForm, String userid, String dealercode) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> dataList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // ✅ Execute stored procedure with dealerCode parameter
            String sql = "EXEC GetClosedJobCardsWithoutSale :DealerCode";

            Query q = hrbsession.createSQLQuery(sql)
                    .addScalar("JobCardNo", new StringType())
                    .addScalar("vinno", new StringType())
                    .addScalar("JobCardDate", new DateType())
                    .addScalar("jobCardPriority", new StringType())
                    .addScalar("Status", new StringType())
                    .addScalar("Is_warranty_req", new StringType())
                    .addScalar("PayeeName", new StringType())
                    .addScalar("MobilePhone", new StringType())
                    .addScalar("JobTypeId", new IntegerType())
                    .setParameter("DealerCode", dealercode);

            List<Object[]> res = q.list();
           
            for (Object[] o : res) {
                serviceForm sf = new serviceForm();

                sf.setJobCardNo((String) o[0]);
                sf.setVinNo((String) o[1]);
                Date jobCardDate = (Date) o[2];
                sf.setJobCardDate(jobCardDate != null ? sdf.format(jobCardDate) : "");
                sf.setJobcardpriority((String) o[3]);
                sf.setStatus((String) o[4]);
                sf.setWarrantyApplicable((String) o[5]);
                sf.setPayeeName((String) o[6]);
                sf.setMobilePhone((String) o[7]);
                sf.setJobId(o[8] != null ? Integer.toString((int) o[8]) : "");

                dataList.add(sf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
        }

        return dataList;
    }


    /*

    public String addStandardChecksData(serviceForm sf, TreeMap<Integer, ArrayList<String>> tMap) throws SQLException {
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    ArrayList<String> dataList = null;
    String result = "";

    try {
    dataList = new ArrayList<String>();
    stmt = conn.createStatement();

    rs = stmt.executeQuery(getJobId + sf.getJobCardNo() + "'");
    if (rs.next()) {
    sf.setJobId("" + rs.getInt(1));
    rs = stmt.executeQuery(CheckRowsInjobchecklist + sf.getJobCardNo() + "'");
    if (rs.next()) {
    pstmt = conn.prepareStatement("delete from jobchecklist where jobCardId=?");
    pstmt.setInt(1, Integer.parseInt(sf.getJobId()));
    pstmt.execute();
    }
    pstmt = conn.prepareStatement(insert_jobchecklist);
    for (Map.Entry<Integer, ArrayList<String>> entryMap : tMap.entrySet()) {
    dataList = entryMap.getValue();

    for (int d = 0; d < dataList.size(); d += 5) {

    pstmt.setInt(1, Integer.parseInt(sf.getJobId()));
    pstmt.setInt(2, entryMap.getKey());
    pstmt.setString(3, dataList.get(d));
    pstmt.setString(4, dataList.get(d + 1));
    pstmt.setString(5, dataList.get(d + 2));
    pstmt.setString(6, dataList.get(d + 3));
    pstmt.setString(7, dataList.get(d + 4));
    pstmt.addBatch();
    }
    }
    pstmt.executeBatch();
    conn.commit();
    result = "SUCCESS@@Standard Check has been Successfully Added";
    } else {
    result = "Failure@@Standard Check not Added";
    }
    } catch (Exception e) {
    e.printStackTrace();
    } finally {
    try {
    if (rs != null) {
    rs.close();
    rs = null;
    }
    if (stmt != null) {
    stmt.close();
    stmt = null;
    }
    if (pstmt != null) {
    pstmt.close();
    pstmt = null;
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
    }
    return result;
    }

     */
    // Insert for standard check created on 23/05/14 by Megha
    public String addStandardChecksData(serviceForm sf, TreeMap<Integer, ArrayList<String>> tMap) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null;
        Iterator itr = null, itr1 = null, itr2 = null;
        ArrayList<String> dataList = null;
        String result = "";
        int jid = 0;
        Jobchecklist jcl = null;
        JobchecklistPK jpk = null;
        try {
            hrbsession.beginTransaction();
            dataList = new ArrayList<String>();

            query1 = hrbsession.createQuery("delete from Jobchecklist where jobCardNo=?");
            query1.setString(0, sf.getJobCardNo());
            query1.executeUpdate();


            for (Map.Entry<Integer, ArrayList<String>> entryMap : tMap.entrySet()) {
                dataList = entryMap.getValue();

                for (int d = 0; d < dataList.size(); d += 4) {
                    jpk = new JobchecklistPK();
                    jcl = new Jobchecklist();

                    jpk.setJobCardNo(sf.getJobCardNo());
                    jpk.setContentId(entryMap.getKey());

                    // System.out.println("dataList.get(d)==" + dataList.get(d));

                    jpk.setSubcontentId(Integer.parseInt(dataList.get(d)));
                    jcl.setJobchecklistPK(jpk);
                    jcl.setOkStatus(dataList.get(d + 1));
                    jcl.setRemarks(dataList.get(d + 2));
                    jcl.setActionTaken(dataList.get(d + 3));
                    jcl.setFinalStatus("COMPLETE");
                    //hrbsession.update(jcl);
                    hrbsession.save(jcl);
                    jid = 1;
                }
            }

            if (jid == 1) {
                hrbsession.getTransaction().commit();
                result = "SUCCESS@@stndChkSuccess";//Standard Check has been Successfully Added";
            }
            else {

                result = "Failure@@stndChkFailure";//Standard Check not Added";
            }

        }
        catch (Exception e) {
            hrbsession.getTransaction().rollback();
            result = "Failure@@stndChkFailure";//Standard Check not Added";
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //view form content of standard check on 23/05/14 by Megha
    public void getView_FormContent(serviceForm serviceForm)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null;
        Iterator itr = null, itr1 = null, itr2 = null;
        ContentFormBean cf = null;
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;
        String subContentId = null;
        String formName = serviceForm.getJobType();
        int contentIdTemp = 0;

        ArrayList<SubContentFormBean> subContentFormList = new ArrayList();

        SubContentFormBean dataBean = null;//new SubContentFormBean();
        ArrayList<SubContentFormBean> tempList = null;
        try {


            getForm_id(serviceForm, formName);
            query = hrbsession.createQuery(GetFormContent4View + serviceForm.getJobCardNo() + "'");
            itr = query.list().iterator();
            while (itr.hasNext()) {

                Object o[] = (Object[]) itr.next();
                contentId = Integer.parseInt(o[0].toString());//rs.getInt("contentId");
                cf = new ContentFormBean();

                cf.setContentId(contentId);
                cf.setContentDesc(o[1].toString().trim());
                subContentId = "" + o[2].toString();
                tempList = treeMap.put(cf, subContentFormList);
                if (tempList == null) {
                    subContentFormList = new ArrayList();
                    treeMap.put(cf, subContentFormList);
                }
                if (subContentId != null) {
                    dataBean = new SubContentFormBean();

                    //dataBean.setContentIdTemp(String.valueOf(contentIdTemp));
                    dataBean.setSubContentId(subContentId);
                    dataBean.setSubContentDesc(o[3].toString().trim());
                    dataBean.setTextBoxOption(o[4].toString().trim());
                    dataBean.setActiontaken(o[5].toString().trim());
                    dataBean.setObservations(o[6].toString().trim());
                    dataBean.setStatus(o[7].toString().trim());
//                    dataBean.setTextBoxOption(rs.getString("TEXTBOX_OPTION"));
                    subContentFormList.add(dataBean);
                }

            }
            serviceForm.setDataMap(treeMap);

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }


            }
            catch (Exception e) {
                try {
                    e.printStackTrace();
                }
                catch (Exception ex) {
                    //Logger.getLogger(ServiceProcessDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getServiceHrsById(String tempStr, String flag, String dealercode)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null;
        Iterator itr = null, itr1 = null, itr2 = null;
        String result = "0.0";

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //System.out.println("select WageValue  from wagemaster where dealerCode='ho' and  ((StartDate <='" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "' and endDate>'" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "' and StartDate is not NULL) or (StartDate <='" + (new java.sql.Timestamp(new java.util.Date().getTime())) + "'  and endDate is NULL))");
            query = hrbsession.createQuery("select wageValue  from Wagemaster where dealerCode=? and isActive='Y'");
            query.setString(0, dealercode);
            //rs = pstmt.executeQuery();
            itr = query.list().iterator();

            if (itr.hasNext()) {
                result = ((Double) itr.next()).toString();
            }
            //  System.out.println("result" + result);
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }


            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//setJobCardNoStatus
    public String setJobCardNoStatus(String jobCardNo, String jobStatus, String userId, String Remarks)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        String result = "";

        try {
            hrbsession.beginTransaction();
            Jobcarddetails jc = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, jobCardNo);
            jc.setStatus(jobStatus);
            jc.setOpenRejectRemarks(Remarks);
            jc.setIsServerSync('N');
            jc.setLastModifiedBy(userId);
            jc.setLastModifiedOn(new Date(new java.util.Date().getTime()));
            hrbsession.getTransaction().commit();
            result = "SUCCESS";

        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<String> getFileListForJobCard(String path)
    {
        ArrayList<String> listFile = new ArrayList<String>();
        File f = new File(path);

        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    listFile.add(file.getName());
                    // System.out.println("list file" + file.getName());
                }
            }
        }
        return listFile;
    }

    public String getActualDetail4jobCardNo(serviceForm sf, String jobCardNo) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        String sql = null;
        ArrayList<serviceForm> partList, lubesList = null;
        ArrayList<serviceForm> labourchargeList = null;// labourchargeList = new ArrayList<serviceForm>();
        ArrayList<serviceForm> otherchargeList = null;
        Query query = null, query1 = null, query2 = null;
        Iterator itr = null, itr1 = null, itr2 = null, itr3 = null;
        double partvalue = 0, lubvalue = 0, labourchg = 0, otherchg = 0, estchg = 0;
        String result = "No Data";
        try {

            hrbsession.beginTransaction();


            sql = Get_Actuals_partDetail4jobcardNo_mysql + jobCardNo + "'";

            query = hrbsession.createQuery(sql);

//            System.out.println("query:" + sql);
            partList = new ArrayList<serviceForm>();
            lubesList = new ArrayList<serviceForm>();
            //rs = stmt.executeQuery(sql);
            itr3 = hrbsession.createQuery("select jd.jobCardNo,jd.promiseDate,jd.reqCustDate,jd.actualDate,jd.ttlDiscount,jd.ttlActualAmount from Jobcarddetails jd where jd.jobCardNo='" + jobCardNo + "'").list().iterator();
            if (itr3.hasNext()) {
                Object o[] = (Object[]) itr3.next();
                serviceForm servForm = new serviceForm();
                servForm.setJobId(o[0] == null ? "" : o[0].toString());
                sf.setPromisedstr(o[1] == null ? "" : o[1].toString());
                sf.setRequiredBYCustomerstr(o[2] == null ? "" : o[2].toString());
                sf.setCurrentEstimatestr(o[3] == null ? "" : o[3].toString());
                sf.setTotaldiscount(o[4] == null ? "" : sf.getTotaldiscount());

            }

            itr = query.list().iterator();
            /*
            jd.jobId, DATE_FORMAT(jd.promiseDate ,'%d/%m/%Y %H:%i')," +
            " DATE_FORMAT(jd.reqCustDate ,'%d/%m/%Y %H:%i')," +
            " DATE_FORMAT(jd.actualDate  ,'%d/%m/%Y %H:%i')," +
            " js.partCategory,js.partNo ,js.partDesc,js.unitPrice,js.qty,js.amount,js.unit,js.discInPerc,js.finalAmt" +
             */
            while (itr.hasNext()) {
                serviceForm servForm = new serviceForm();

                Object o[] = (Object[]) itr.next();


                servForm.setJobId("" + o[0].toString());
                sf.setPromisedstr(o[1] == null ? "" : o[1].toString());
                sf.setRequiredBYCustomerstr(o[2] == null ? "" : o[2].toString());
                sf.setCurrentEstimatestr(o[3] == null ? "" : o[3].toString());
                servForm.setPart_category(o[4] == null ? "" : o[4].toString());
                servForm.setPartNo_str(o[5] == null ? "" : o[5].toString());
                servForm.setPartDesc_str(o[6] == null ? "" : o[6].toString().toUpperCase());
                servForm.setUnitprice_str(o[7] == null ? "" : o[7].toString());
                servForm.setQty_str(o[8] == null ? "" : o[8].toString());
                servForm.setDbqty(o[8] == null ? "" : o[8].toString());
                servForm.setAmount_str(o[9] == null ? "" : o[9].toString());
                servForm.setBillto_str(o[10] == null ? "" : o[10].toString());
                servForm.setDiscount_str(o[11] == null ? "" : o[11].toString());
                servForm.setFinalamt_str(o[12] == null ? "" : o[12].toString());
                servForm.setComplaintCode_str(o[13] == null ? "" : o[13].toString());
                servForm.setCasualconseq(o[14] == null ? "" : o[14].toString());
               
              
                servForm.setInventoryqty(getInventoryQty(servForm.getPartNo_str(), sf.getDealercode()));

                if (servForm.getPart_category().equalsIgnoreCase("LUBES")) {
                	lubesList.add(servForm);
                    lubvalue += Double.valueOf(servForm.getFinalamt_str());
                }
                else {
                    if (o[8].toString().contains(".")) {
                        servForm.setQty_str(o[8] == null ? "" : o[8].toString().substring(0, o[8].toString().indexOf(".")));
                    }
                    servForm.setInventoryqty(servForm.getInventoryqty().substring(0, servForm.getInventoryqty().indexOf(".")));
                    servForm.setModifiedPartsUsed_str(o[15] == null ? "" : o[15].toString());
                    servForm.setVendorName_str(o[16] == null ? "" : o[16].toString());
                    partList.add(servForm);
                    partvalue += Double.valueOf(servForm.getFinalamt_str());
                }
                result = "Have data";
//                System.out.println(servForm.getAmount_str() + "" + servForm.getBillto_str() + "-" + servForm.getDiscount_str() + "-" + servForm.getFinalamt_str());
            }
            sf.setTotalPartsValue("" + df.format(partvalue));
            sf.setTotalLubesValue("" + df.format(lubvalue));
            sf.setPartList(partList);
            sf.setLubesList(lubesList);
//            System.out.println(result+"---"+sf.getMechanicName()+"-"+sf.getBayCode()+"-"+sf.getInspectionBy()+sf.getWorkStarted()+"-"+sf.getWorkFinished()+"-"+sf.getVehicleOut());
            labourchargeList = new ArrayList<serviceForm>();
            query1 = hrbsession.createQuery(Get_ActualLabourCharges4jobCardNo + sf.getJobCardNo() + "'");
            itr1 = query1.list().iterator();
            //rs = stmt.executeQuery(Get_ActualLabourCharges4jobCardNo + sf.getJobCardNo() + "'");
            //pmst = conn.prepareStatement(getServicehrs);
            query2 = hrbsession.createQuery(getServicehrs);
            String servicehrs = "";
            float hrs = 0.0f;
            while (itr1.hasNext()) {
//for service hrs
                Object obj[] = (Object[]) itr1.next();
                query2.setString(0, obj[1].toString());
                query2.setString(1, obj[0].toString());
                query2.setString(2, sf.getJobCardNo());
                itr2 = query2.list().iterator();
                //rs1 = pmst.executeQuery();
                if (itr2.hasNext()) {
                    hrs = (Float) itr2.next();
                    servicehrs = Float.toString(hrs);
                }

                // System.out.println("servicehrs" + servicehrs);
                serviceForm servForm = new serviceForm();
                servForm.setComplaintCode_str(obj[3].toString() + "@@" + obj[0].toString());
                servForm.setActionTaken_str(obj[1].toString() + "@@" + servicehrs);
                servForm.setLabourChargesAmount_str(obj[2].toString());
                labourchargeList.add(servForm);
                labourchg += Double.valueOf(servForm.getLabourChargesAmount_str());
                // System.out.println(obj[0].toString() + "" + servForm.getActionTaken_str() + "-cmp" + servForm.getComplaintCode_str() + "-" + servForm.getLabourChargesAmount_str());
                //

                result = "Have data";
            }
            sf.setLabourchargeList(labourchargeList);
            sf.setTotalLabourCharges("" + labourchg);
            otherchargeList = new ArrayList<serviceForm>();
            query1 = hrbsession.createQuery(Get_ActualOtherCharges4jobCardNo + sf.getJobCardNo() + "'");
            itr1 = query1.list().iterator();
            while (itr1.hasNext()) {
                Object obj[] = (Object[]) itr1.next();
                serviceForm servForm = new serviceForm();
                servForm.setWorkCode_str(obj[0].toString());
                servForm.setWorkDescription_str(obj[1].toString());
                servForm.setWorkAmount_str(obj[2].toString());
                otherchargeList.add(servForm);
                otherchg += Double.valueOf(servForm.getWorkAmount_str());
//                System.out.println(rs.getString(1)+"-"+servForm.getWorkAmount_str()+"-"+servForm.getWorkDescription_str()+"-"+servForm.getWorkCode_str());
                result = "Have data";
            }
            sf.setOtherchargeList(otherchargeList);
            sf.setTotalOtherCharges("" + otherchg);

            sf.setTotalEstimate("" + df.format((partvalue + lubvalue + labourchg + otherchg - (Double.parseDouble(sf.getTotaldiscount())))));
        }
        catch (Exception e) {
            hrbsession.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//
    public String isExistActual(serviceForm sf)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        //    String jid = "";
        String jobcardno = sf.getJobCardNo();
        String result = "notexist";
        Query query2 = null, query3 = null;
        //   Query query = null;
        //    Iterator itr = null;
        try {
            
            result = check_in_Db(jobcardno, "JobcardSparesActualcharges", "jobCardNo", "", hrbsession);//newno table col subq
            
            
            if (result.equals("notexist")) {
                query2 = hrbsession.createQuery("delete FROM  Actualsothercharges WHERE jobCardNo='" + jobcardno + "'");
                query2.executeUpdate();
                query3 = hrbsession.createQuery("delete FROM  Actualslabourcharges WHERE jobCardNo='" + jobcardno + "'");
                query3.executeUpdate();
                }
            

            if (result.equals("notexist")) {
                result = check_in_Db(jobcardno, "Actualslabourcharges", "jobCardNo", "", hrbsession);//newno table col subq
            }
            //
            if (result.equals("notexist")) {
                result = check_in_Db(jobcardno, "Actualsothercharges", "jobCardNo", "", hrbsession);//newno table col subq
            }

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
// By Anand 19 May

    public String addEstimate(serviceForm sf, String userid) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String results = "";
        //  java.sql.Date date = null;

        String str1 = "";

        // int insertF = 0;
        Query query = null, query2 = null, query3 = null, query4 = null;
        // Iterator itr = null;
        String jobcardno = "";
        //  System.out.println("enter in dao");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {

            jobcardno = sf.getJobCardNo();


            hrbsession.beginTransaction();
            
            
           
            
            query2 = hrbsession.createQuery("delete FROM  Estimateothercharges WHERE jobCardNo='" + jobcardno + "'");
            query2.executeUpdate();
            query3 = hrbsession.createQuery("delete FROM  Estimatelabourcharges WHERE jobCardNo='" + jobcardno + "'");
            query3.executeUpdate();
            query4 = hrbsession.createQuery("delete FROM  JobcardSparesCharges WHERE jobCardNo='" + jobcardno + "'");
            query4.executeUpdate();
            
           

            JobcardSparesCharges jsc = null;
            Estimatelabourcharges elc = null;
            Estimateothercharges eoc = null;

            Jobcarddetails jd = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, sf.getJobCardNo());//


            if (sf.getComptype() != null) {
                for (int i = 0; i < sf.getComptype().length; i++) {

                if ((!sf.getPartNo()[i].equals("")) && (!sf.getPartDesc()[i].equals("")) && (!sf.getUnitPrice()[i].equals("")) && (!sf.getUnitPrice()[i].equals("0")) && (!sf.getQuantityS()[i].equals("")) && (!sf.getQuantityS()[i].equals("0.0")) && (!sf.getQuantityS()[i].equals("0")) && (!sf.getPartPriceAmount()[i].equals("")) && (!sf.getBillCode()[i].equals("")) && (!sf.getWarranty()[i].equals("")) && (!sf.getFinalAmount()[i].equals(""))) {


                    jsc = new JobcardSparesCharges();
                    jsc.setJobCardNo(sf.getJobCardNo());
                    jsc.setJobSpareID(sf.getJobCardNo() + "-" + sf.getPartNo()[i].trim());//jobcardno-partno
                    jsc.setPartNo(sf.getPartNo()[i].trim());
                    jsc.setPartDesc(sf.getPartDesc()[i].trim().toUpperCase());//
                    jsc.setUnitPrice(Double.parseDouble(sf.getUnitPrice()[i].trim()));
                    jsc.setQty(Double.parseDouble(sf.getQuantityS()[i].trim()));
                    jsc.setPartCategory("SPARES");
                    jsc.setAmount(Double.parseDouble(sf.getPartPriceAmount()[i].trim()));
                    if (sf.getBillCode()[i].trim() != null && !sf.getBillCode()[i].trim().equals("")) {
                        str1 = sf.getBillCode()[i].trim().split("@@")[0];
                        jsc.setBillID(str1 == null ? 0 : Integer.parseInt(str1));
                    }
                    jsc.setDiscInPerc(Double.parseDouble(sf.getWarranty()[i].trim()));
                    jsc.setFinalAmount(Double.parseDouble(sf.getFinalAmount()[i].trim()));
                    //  jsc.setCmpNo(sf.getComplaint_Code()[i]);
                    // jsc.setCausalOrConseq(sf.getCausal_Code()[i]);

                    hrbsession.save(jsc);
                }
            }
            }
            
            if (sf.getLubesComptype() != null) {
                for (int i = 0; i < sf.getLubesComptype().length; i++) {


                if ((!sf.getLubesNo()[i].equals("")) && (!sf.getLubesDesc()[i].equals("")) && (!sf.getLubesUnitPrice()[i].equals("")) && (!sf.getLubesUnitPrice()[i].equals("0")) && (!sf.getLubesQuantityS()[i].equals("")) && (!sf.getLubesQuantityS()[i].equals("0.0")) && (!sf.getLubesQuantityS()[i].equals("0")) && (!sf.getLubesPriceAmount()[i].equals("")) && (!sf.getLubesBillCode()[i].equals("")) && (!sf.getLubesWarranty()[i].equals("")) && (!sf.getLubesFinalAmount()[i].equals(""))) {
                    jsc = new JobcardSparesCharges();
                    jsc.setJobSpareID(sf.getJobCardNo() + "-" + sf.getLubesNo()[i].trim());//jobcardno-partno
                    jsc.setPartNo(sf.getLubesNo()[i].trim());
                    jsc.setJobCardNo(sf.getJobCardNo());
                    jsc.setQty(Double.parseDouble(sf.getLubesQuantityS()[i].trim()));
                    jsc.setPartDesc(sf.getLubesDesc()[i].trim().toUpperCase());
                    jsc.setUnitPrice(Double.parseDouble(sf.getLubesUnitPrice()[i].trim()));
             //       jsc.setQty(Double.parseDouble(sf.getLubesQuantityS()[i].trim()));
                    jsc.setPartCategory("LUBES");
                    jsc.setAmount(Double.parseDouble(sf.getLubesPriceAmount()[i].trim()));
                    //jsc.setUnit(sf.getLubesBillCode()[i].trim());
                    jsc.setDiscInPerc(Double.parseDouble(sf.getLubesWarranty()[i].trim()));
                    jsc.setFinalAmount(Double.parseDouble(sf.getLubesFinalAmount()[i].trim()));
                    if (sf.getLubesBillCode()[i].trim() != null && !sf.getLubesBillCode()[i].trim().equals("")) {
                        str1 = sf.getLubesBillCode()[i].trim().split("@@")[0];
                        jsc.setBillID(str1 == null ? 0 : Integer.parseInt(str1));


                    }
                    hrbsession.save(jsc);

                }
            }
            }

            // System.out.println("sf.getWorkCode().length==" + sf.getComplaintCode().length);
            if (sf.getComplaintCode() != null) {
                for (int i = 0; i < sf.getComplaintCode().length; i++) {



                    String val = sf.getActionCode()[i];
                    if (val != null && !val.equals("")) {
                        if (val.indexOf("@@") > -1) {
                            String value[] = val.split("@@");
                            val = value[0];
                        }
                    }

                    if ((!sf.getComplaintCode()[i].equals("")) && (!val.equals("")) && (!sf.getLabourChargesAmount()[i].equals(""))) {

                        elc = new Estimatelabourcharges();
                        elc.setJobCardLabourID(sf.getJobCardNo() + "-L" + (i + 1));
                        elc.setCmpNo(sf.getComplaintCode()[i].trim());
                        elc.setActionTaken(val.trim());
                        elc.setJobCardNo(sf.getJobCardNo());
                        elc.setLabourChargesAmount(Double.parseDouble(sf.getLabourChargesAmount()[i].trim()));
                        hrbsession.save(elc);

                    }
                }
            }
            int count = 1;
            if (sf.getWorkCode() != null) {
                for (int i = 0; i < sf.getWorkCode().length; i++) {

                    if ((!sf.getWorkCode()[i].equals("")) && (!sf.getWorkDescription()[i].equals("")) && (!sf.getWorkAmount()[i].equals(""))) {

                        eoc = new Estimateothercharges();
                    eoc.setJobCardNo(sf.getJobCardNo());
                    //eoc.setJobWorkID(sf.getJobCardNo() + "-" + sf.getWorkCode()[i].trim());
                    eoc.setJobWorkID(sf.getJobCardNo() + "-" + count);
                    eoc.setWorkCode(sf.getWorkCode()[i].trim());

                        eoc.setWorkDescription(sf.getWorkDescription()[i].trim());
                        eoc.setWorkAmount(Float.parseFloat(sf.getWorkAmount()[i].trim()));
                        eoc.setDocName("");
                        hrbsession.save(eoc);
                        count++;
                    }
                }
            }



            if ((!sf.getTotalPartsValue().equals("")) && (!sf.getTotalLubesValue().equals("")) && (!sf.getTotalLabourCharges().equals("")) && (!sf.getTotalOtherCharges().equals(""))) {

                jd.setTtlEstimatePartsAmt(Double.parseDouble(sf.getTotalPartsValue().trim()));
                jd.setTtlEstimateLubesAmt(Double.parseDouble(sf.getTotalLubesValue().trim()));
                jd.setTtlEstimateLabourChargesAmt(Double.parseDouble(sf.getTotalLabourCharges().trim()));
                jd.setTtlEstimateOtherChargesAmt(Double.parseDouble(sf.getTotalOtherCharges().trim()));
            }
            if (sf.getPromised() != null && (!sf.getPromised().equals(""))) {
                jd.setPromiseDate(sdf.parse(sf.getPromised() + " " + sf.getPromisedTime() + ":" + sf.getPromisedHrs()));
            }
            if ((sf.getRequiredBYCustomer() != null && !sf.getRequiredBYCustomer().equals(""))) {
                jd.setReqCustDate(sdf.parse(sf.getRequiredBYCustomer() + " " + sf.getRequiredBYCustomerTime() + ":" + sf.getRequiredBYCustomerHrs()));
            }
            if (sf.getCurrentEstimate() != null && !sf.getCurrentEstimate().equals("")) {
                jd.setEstDate(sdf.parse(sf.getCurrentEstimate() + " " + sf.getCurrentEstimateTime() + ":" + sf.getCurrentEstimateHrs()));
            }

            jd.setLastModifiedBy(userid);
            jd.setLastModifiedOn(new java.util.Date());

            hrbsession.saveOrUpdate(jd);
            
            hrbsession.getTransaction().commit();
            results = "Success@@estimateSuccess";//Estimate Information has been Successfully Added";	
            
        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
            results = "Failure@@estimateFailure";//Unable to add Estimate Information, Please Contact System Administrator.";
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@estimateFailure";//Unable Estimate Information, Please Contact System Administrator.";
            }
        }
        return results;
    }

    public String addActual(serviceForm sf, String userid) throws SQLException
    {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query2 = null, query3 = null, query4 = null;
        Iterator itr = null;
        //  JobcardSparesActualcharges jsa = null;
        //  java.sql.Date date = null;
        //  Calendar cal = null;
        String str1 = "";//, sql2 = "", sql3 = "", sql4 = "", sql5 = "";
        String jobcardnos = "";
        // int insertF = 0;
        //    long billidvalue = 0;
        ArrayList billcomparelist = new ArrayList();
        //   System.out.println("enter in dao");
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        hrbsession.beginTransaction();
        Actualslabourcharges alc = null;
        Actualsothercharges aoc = null;
        int flag = 0;
        Billmaster bm = null;


        JobcardSparesActualcharges jsc = null;

        int ii = 0;
        try {

            Criteria cr = hrbsession.createCriteria(Billmaster.class);
            cr.add(Restrictions.eq("warrantyType", "Yes"));
            itr = cr.list().iterator();
            while (itr.hasNext()) {
                bm = (Billmaster) itr.next();
                //billidvalue = bm.getBillID();
                billcomparelist.add(bm.getBillID() + "");
                ii++;
            }




            Jobcarddetails jd = (Jobcarddetails) hrbsession.load(Jobcarddetails.class, sf.getJobCardNo());//
            query = hrbsession.createQuery("SELECT  jobCardNo FROM  Jobcarddetails WHERE jobCardNo='" + sf.getJobCardNo() + "'");

            itr = query.list().iterator();

            if (itr.hasNext()) {
                jobcardnos = (String) itr.next();
            }
            //rs.close();
            String existactualsentry = isExistActual(sf);



            // }
            //   query1 = hrbsession.createQuery("SELECT  jobCardNo FROM  JobcardSparesActualcharges WHERE jobCardNo='" + jobcardnos + "'");
            //   itr1 = query1.list().iterator();
            if (existactualsentry.equals("exist")) {


                query2 = hrbsession.createQuery("delete FROM  Actualsothercharges WHERE jobCardNo='" + jobcardnos + "'");
                query2.executeUpdate();
                query3 = hrbsession.createQuery("delete FROM  Actualslabourcharges WHERE jobCardNo='" + jobcardnos + "'");
                query3.executeUpdate();
                query4 = hrbsession.createQuery("delete FROM  JobcardSparesActualcharges WHERE jobCardNo='" + jobcardnos + "'");
                query4.executeUpdate();
                //    insertF = 1;
            }


            if (sf.getComptype() != null) {
                for (int i = 0; i < sf.getComptype().length; i++) {

                    if ((!sf.getPartNo()[i].equals("")) && (!sf.getPartDesc()[i].equals("")) && (!sf.getUnitPrice()[i].equals("")) && (!sf.getUnitPrice()[i].equals("0")) && (!sf.getQuantityS()[i].equals("")) && (!sf.getQuantityS()[i].equals("0.00")) && (!sf.getQuantityS()[i].equals("0.0")) && (!sf.getQuantityS()[i].equals("0")) && (!sf.getQuantityS()[i].equals("0.0")) && (!sf.getPartPriceAmount()[i].equals("")) && (!sf.getBillCode()[i].equals("")) && (!sf.getWarranty()[i].equals("")) && (!sf.getFinalAmount()[i].equals(""))) {


                        jsc = new JobcardSparesActualcharges();
                        jsc.setJobCardNo(sf.getJobCardNo());
                        jsc.setJobSpareID(sf.getJobCardNo() + "-" + sf.getPartNo()[i].trim());//jobcardno-partno
                        jsc.setPartNo(sf.getPartNo()[i].trim());
                        jsc.setPartDesc(sf.getPartDesc()[i].trim().toUpperCase());//
                        jsc.setUnitPrice(Double.parseDouble(sf.getUnitPrice()[i].trim()));
                        jsc.setQty(Double.parseDouble(sf.getQuantityS()[i].trim())); 
                        jsc.setPartCategory("SPARES");
                        jsc.setAmount(Double.parseDouble(sf.getPartPriceAmount()[i].trim()));
                        if (sf.getBillCode()[i].trim() != null && !sf.getBillCode()[i].trim().equals("")) {
                            str1 = sf.getBillCode()[i].trim().split("@@")[0];
                            jsc.setBillID(str1 == null ? 0 : Integer.parseInt(str1));
                            for (int k = 0; k < ii; k++) {
                                if (billcomparelist.get(k).toString().equals(jsc.getBillID() + "")) {
                                    flag = 1;
                                    jd.setWtyClaimStatus("REQUIRED");
                                    jd.setIswarrantyreq('Y');
                                }
                            }

                        }
                        
                       
                        jsc.setModifiedPartsUsed(sf.getModifiedPartsUsed()[i].trim());
                        jsc.setVendorName(sf.getVendorName()[i].trim());

                        jsc.setDiscInPerc(Double.parseDouble(sf.getWarranty()[i].trim()));
                        jsc.setFinalAmount(Double.parseDouble(sf.getFinalAmount()[i].trim()));

                        if (sf.getComplaint_Code() != null && sf.getComplaint_Code().length > i) {
                            jsc.setCmpNo(sf.getComplaint_Code()[i]);
                        }
                        if (sf.getCausal_Code() != null && sf.getCausal_Code().length > i) {
                            jsc.setCausalOrConseq(sf.getCausal_Code()[i]);
                        }
                        hrbsession.save(jsc);

                    }
                }
            }
            if (sf.getLubesComptype() != null) {
                for (int i = 0; i < sf.getLubesComptype().length; i++) {

                    if ((!sf.getLubesNo()[i].equals("")) && (!sf.getLubesDesc()[i].equals("")) && (!sf.getLubesUnitPrice()[i].equals("")) && (!sf.getLubesUnitPrice()[i].equals("0")) && (!sf.getLubesQuantityS()[i].equals("")) && (!sf.getLubesQuantityS()[i].equals("0.00")) && (!sf.getLubesQuantityS()[i].equals("0.0")) && (!sf.getLubesQuantityS()[i].equals("0")) && (!sf.getLubesPriceAmount()[i].equals("")) && (!sf.getLubesBillCode()[i].equals("")) && (!sf.getLubesWarranty()[i].equals("")) && (!sf.getLubesFinalAmount()[i].equals(""))) {
                        jsc = new JobcardSparesActualcharges();
                        jsc.setJobSpareID(sf.getJobCardNo() + "-" + sf.getLubesNo()[i].trim());//jobcardno-partno
                        jsc.setPartNo(sf.getLubesNo()[i].trim());
                        jsc.setJobCardNo(sf.getJobCardNo());

                        jsc.setPartDesc(sf.getLubesDesc()[i].trim().toUpperCase());
                        jsc.setUnitPrice(Double.parseDouble(sf.getLubesUnitPrice()[i].trim()));
                        jsc.setQty(Double.parseDouble(sf.getLubesQuantityS()[i].trim()));
                        jsc.setPartCategory("LUBES");
                        jsc.setAmount(Double.parseDouble(sf.getLubesPriceAmount()[i].trim()));
                        if (sf.getLubesBillCode()[i].trim() != null && !sf.getLubesBillCode()[i].trim().equals("")) {
                            str1 = sf.getLubesBillCode()[i].trim().split("@@")[0];
                            jsc.setBillID(str1 == null ? 0 : Integer.parseInt(str1));
                            for (int k = 0; k < ii; k++) {
                                if (billcomparelist.get(k).toString().equals(jsc.getBillID() + "")) {
                                    flag = 1;
                                    jd.setWtyClaimStatus("REQUIRED");
                                    jd.setIswarrantyreq('Y');
                                }
                            }

                        }
                        // jsc.setUnit(sf.getLubesBillCode()[i].trim());
                        jsc.setDiscInPerc(Double.parseDouble(sf.getLubesWarranty()[i].trim()));
                        jsc.setFinalAmount(Double.parseDouble(sf.getLubesFinalAmount()[i].trim()));
                        if (sf.getLubescomplaint_Code() != null && sf.getLubescomplaint_Code().length > i) {
                            jsc.setCmpNo(sf.getLubescomplaint_Code()[i]);
                        }
                        if (sf.getCausal_Code() != null && sf.getCausal_Code().length > i) {
                            jsc.setCausalOrConseq(sf.getLubescausal_Code()[i]);
                        }
                        hrbsession.save(jsc);

                    }
                }
            }
            if (sf.getComplaintCode() != null) {
                for (int i = 0; i < sf.getComplaintCode().length; i++) {

                    String val = sf.getActionCode()[i];
                    if (val != null && !val.equals("")) {
                        if (val.indexOf("@@") > -1) {
                            String value[] = val.split("@@");
                            val = value[0];
                        }
                    }
                    if ((!sf.getComplaintCode()[i].equals("")) && (!val.equals("")) && (!sf.getLabourChargesAmount()[i].equals(""))) {

                        alc = new Actualslabourcharges();
                        alc.setJobCardLabourID(sf.getJobCardNo() + "-L" + (i + 1));
                        alc.setCmpNo(sf.getComplaintCode()[i].trim());
                        alc.setActionTaken(val.trim());
                        alc.setJobCardNo(sf.getJobCardNo());
                        alc.setLabourChargesAmount(Double.parseDouble(sf.getLabourChargesAmount()[i].trim()));


                        hrbsession.save(alc);

                    }
                }
            }
            int count = 1;
            if (sf.getWorkCode() != null) {
                for (int i = 0; i < sf.getWorkCode().length; i++) {

                    if ((!sf.getWorkCode()[i].equals("")) && (!sf.getWorkDescription()[i].equals("")) && (!sf.getWorkAmount()[i].equals(""))) {


                        //pstmt.setInt(2, i + 1);
                        aoc = new Actualsothercharges();
                        aoc.setJobCardNo(sf.getJobCardNo());
                        //aoc.setJobWorkID(sf.getJobCardNo() + "-" + sf.getWorkCode()[i].trim()); multiple work assign Changes by Avinash
                        aoc.setJobWorkID(sf.getJobCardNo() + "-" + count);
                        aoc.setWorkCode(sf.getWorkCode()[i].trim());

                        aoc.setWorkDescription(sf.getWorkDescription()[i].trim());
                        aoc.setWorkAmount(Float.parseFloat(sf.getWorkAmount()[i].trim()));
                        aoc.setDocName("");
                        hrbsession.save(aoc);
                        count++;
                    }
                }
            }

            if ((!sf.getTotalPartsValue().equals("")) && (!sf.getTotalLubesValue().equals("")) && (!sf.getTotalLabourCharges().equals("")) && (!sf.getTotalOtherCharges().equals(""))) {
            }

            //System.out.println("inserted in job card details" + sf.getPromised() + " " + sf.getRequiredBYCustomer() + " " + sf.getCurrentEstimate());

            if ((!sf.getTotalPartsValue().equals("")) && (!sf.getTotalLubesValue().equals("")) && (!sf.getTotalLabourCharges().equals("")) && (!sf.getTotalOtherCharges().equals(""))) {

                jd.setTtlActualPartsAmt(Double.parseDouble(sf.getTotalPartsValue().trim()));
                jd.setTtlActualLubesAmt(Double.parseDouble(sf.getTotalLubesValue().trim()));
                jd.setTtlActualLabourChargesAmt(Double.parseDouble(sf.getTotalLabourCharges().trim()));
                jd.setTtlActualOtherChargesAmt(Double.parseDouble(sf.getTotalOtherCharges().trim()));
                if (sf.getTotaldiscount() != null && !sf.getTotaldiscount().equals("")) {
                    jd.setTtlDiscount(Double.parseDouble(sf.getTotaldiscount()));
                }
                else {
                    jd.setTtlDiscount(0.0);
                }
                jd.setTtlActualAmount(Double.parseDouble(sf.getTotalPartsValue().trim()) + Double.parseDouble(sf.getTotalLubesValue().trim()) + Double.parseDouble(sf.getTotalLabourCharges().trim()) + Double.parseDouble(sf.getTotalOtherCharges().trim()) - jd.getTtlDiscount());
            }

            if (sf.getPromised() != null && (!sf.getPromised().equals(""))) {
                jd.setPromiseDate(sdf1.parse(sf.getPromised() + " " + sf.getPromisedTime() + ":" + sf.getPromisedHrs()));
            }
            if (sf.getRequiredBYCustomer() != null && (!sf.getRequiredBYCustomer().equals(""))) {
                jd.setReqCustDate(sdf1.parse(sf.getRequiredBYCustomer() + " " + sf.getRequiredBYCustomerTime() + ":" + sf.getRequiredBYCustomerHrs()));
            }
            if (sf.getCurrentEstimate() != null && (!sf.getCurrentEstimate().equals(""))) {
                jd.setActualDate(sdf1.parse(sf.getCurrentEstimate() + " " + sf.getCurrentEstimateTime() + ":" + sf.getCurrentEstimateHrs()));
            }
            jd.setLastModifiedBy(userid);



            jd.setLastModifiedOn(new java.util.Date());
            if (flag == 0) {
                jd.setWtyClaimStatus("NOT REQUIRED");
                jd.setIswarrantyreq('N');
            }
            hrbsession.saveOrUpdate(jd);



            hrbsession.getTransaction().commit();

            results = "Success@@actualsSuccess";//Actuals Information has been Successfully Added";
        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();

            results = "Failure@@actualsFailure";//Unable to add Actuals Information, Please Contact System Administrator.";
        }
        finally {
            try {

                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@actualsFailure";//Unable Actuals Information, Please Contact System Administrator.";
            }
        }
        return results;
    }

    /**
     * getting DE pending 4 approval List created on 24/09/14 by Megha
     */
    /*  public LinkedList<serviceForm> getDE4ApprovalList(String userId, String dealercode, Vector userFunctionalities)
    {
    LinkedList<serviceForm> DE4ApprovalList = new LinkedList<serviceForm>();
    Session session = HibernateUtil.getSessionFactory().openSession();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
    Query query = null;
    if (userFunctionalities.contains("101"))
    {
    query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
    + " from Jobcarddetails jd, Dealervslocationcode u "
    + " where jd.dealerCode = u.dealerCode and jd.deTmsApproval='pending' and jd.status='BILLED' and jd.isVinValidated='YES' and jd.dealerCode=:dealerCode");
    query.setParameter("dealerCode", dealercode);
    }
    else if (userFunctionalities.contains("102"))
    {
    query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
    + " from Jobcarddetails jd, Dealervslocationcode u "
    + " where jd.dealerCode = u.dealerCode and jd.deTmsApproval='pending'  and jd.status='BILLED' and jd.isVinValidated='YES' and jd.dealerCode IN(:dealerList)");
    ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
    query.setParameterList("dealerList", dealerList);
    }
    else if (userFunctionalities.contains("103"))
    {
    query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
    + " from Jobcarddetails jd, Dealervslocationcode u "
    + " where jd.dealerCode = u.dealerCode and jd.deTmsApproval='pending' and jd.status='BILLED' and jd.isVinValidated='YES' ");
    }
    List list = query.list();
    if (query != null && list.size() > 0)
    {
    Iterator itr = list.iterator();
    while (itr.hasNext())
    {
    Object[] obj = (Object[]) itr.next();
    serviceForm serForm = new serviceForm();
    serForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
    serForm.setJobCardDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
    serForm.setJobCardDate(serForm.getJobCardDate().equals("") ? "" : sdf.format(df.parse(serForm.getJobCardDate())));
    serForm.setVinNo(obj[2] == null ? "" : obj[2].toString().trim());
    serForm.setPayeeName(obj[3] == null ? "" : obj[3].toString().trim());
    serForm.setDealercode(obj[4] == null ? "" : obj[4].toString().trim());
    serForm.setDealerName(obj[5] == null ? "" : obj[5].toString().trim());
    serForm.setLocationName(obj[6] == null ? "" : obj[6].toString().trim());
    DE4ApprovalList.add(serForm);
    }
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
    }
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }
    }
    return DE4ApprovalList;
    }
     */
    /**
     * getting Vinno List 4 validation created on 24/09/14 by Megha
     */
    public LinkedList<serviceForm> getVinNo4validate(String userId, String dealercode, Vector userFunctionalities)
    {
        LinkedList<serviceForm> vinNoList4Validation = new LinkedList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Query query = null;
            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);
            query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
                    + " from Jobcarddetails jd, Dealervslocationcode u "
                    + " where  jd.vinDetailsType = 'MANUAL' and jd.isVinValidated='NO' and jd.dealerCode=u.dealerCode and :dealerList LIKE ('%'+jd.dealerCode+'%') ");
//          query.setParameterList("dealerList", dealerList);
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

//            if (userFunctionalities.contains("101"))
//            {
//                query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
//                        + " from Jobcarddetails jd, Dealervslocationcode u "
//                        + " where jd.vinDetailsType = 'MANUAL' and jd.isVinValidated='NO' and jd.dealerCode=u.dealerCode and jd.dealerCode=:dealerCode");
//                query.setParameter("dealerCode", dealercode);
//            }
//            else if (userFunctionalities.contains("102"))
//            {
//                query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
//                        + " from Jobcarddetails jd, Dealervslocationcode u "
//                        + " where  jd.vinDetailsType = 'MANUAL' and jd.isVinValidated='NO' and jd.dealerCode=u.dealerCode and jd.dealerCode IN(:dealerList)");
//                //ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
//                query.setParameterList("dealerList", dealerList);
//            }
//            else if (userFunctionalities.contains("103"))
//            {
//                query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vinno,jd.payeeName,u.dealerCode,u.dealerName,u.location,u.locationCode"
//                        + " from Jobcarddetails jd, Dealervslocationcode u "
//                        + " where  jd.vinDetailsType = 'MANUAL' and jd.isVinValidated='NO' and jd.dealerCode=u.dealerCode");
//            }


            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
                    serForm.setJobCardDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setJobCardDate(serForm.getJobCardDate().equals("") ? "" : sdf.format(df.parse(serForm.getJobCardDate())));
                    serForm.setVinNo(obj[2] == null ? "" : obj[2].toString().trim());
                    serForm.setPayeeName(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setDealercode(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealerName(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setLocationName(obj[6] == null ? "" : obj[6].toString().trim());
                    vinNoList4Validation.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vinNoList4Validation;
    }

    /**
     * validateVinNoNupdate created on 25/09/14 by Megha
     */
    public String validateVinNoNupdate(String dealerCode, String vinNo, String jobcardNo, String oldvinNo)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        try {
            String vinid = dealerCode + "-" + vinNo;
            // System.out.println(oldvinNo + "*" + vinid + "*" + dealerCode + "*" + vinNo);
            result = MethodUtility.check_in_Db_HQL(vinNo, "Vehicledetails", "vinNo", " and m.vinid='" + vinid + "' and m.vinDetailsType='DB'", session);
            if (result.equalsIgnoreCase("exist")) {
                session.beginTransaction();
                Jobcarddetails jobcarddetails = (Jobcarddetails) session.load(Jobcarddetails.class, jobcardNo);
                jobcarddetails.setVinno(vinNo);
                jobcarddetails.setIsVinValidated("YES");
                jobcarddetails.setVinid(vinid);
                session.saveOrUpdate(jobcarddetails);

                String res = MethodUtility.check_in_Db_HQL(oldvinNo, "Jobcarddetails", "vinno", " ", session);
                if (res.equalsIgnoreCase("notexist")) {
                    Query query = session.createQuery("delete from Vehicledetails v where v.vinDetailsType='MANUAL' and v.vinNo=:vinNo");
                    query.setParameter("vinNo", oldvinNo);
                    query.executeUpdate();

                }
                session.getTransaction().commit();
                result = "Success@@Chassis No. " + vinNo + " validated successfully.";
            }
            else {
                result = "Failure@@Chassis No. " + vinNo + " not exist.";
            }

        }
        catch (Exception e) {
            session.getTransaction().rollback();
            result = "Failure@@Chassis No. " + vinNo + " not validated.Please contact to administrator";
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * view Jobcard details for given job card no created on 30/09/14 by Megha
     */
    public serviceForm viewJobcard(String dealerCode, String vinNo, String jobcardNo, serviceForm serForm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // serviceForm serForm = new serviceForm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Query query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vehicleOutDate,jd.workStartDate,"
                    + "jd.workFinishDate,jt.jobTypeDesc,st.serviceTypeDesc,jd.bayDesc,"
                    + "jd.couponNo,jd.vinno,(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =jd.inspectedBy ) as inspectedBy,"
                    + "(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =jd.mechanicName ) as mechanicName,"
                    + "jd.engineno,jd.payeeName,jd.mobilePhone,jd.village,jd.tehsil,jd.district,jd.state,jd.country,jd.pincode,"
                    + "jd.hmr,vd.customerName,vd.modelFamily,vd.mobilePh,vd.villageName,vd.tehsil,vd.district,vd.state,vd.country,vd.pincode,"
                    + "u.dealerCode,u.dealerName,lm.locationDesc,jd.createdOn,jd.iswarrantyreq,jd.ttlActualPartsAmt,jd.ttlActualLubesAmt,"
                    + "jd.ttlActualOtherChargesAmt,jd.ttlActualLabourChargesAmt,jd.ttlDiscount,jd.ttlActualAmount,jd.warrantyStatus,jd.hMRWorking,jt.jobTypeID,jd.openRejectRemarks,"
                    + " (select dm.delayReason from TMSDelayReasonMaster dm where dm.id=jd.reasonForDealy) as reasonForDealy,vd.deliveryDate,jd.complaintDate,jd.itlEwStatus,jd.vorJobCard"
                    + " from Jobcarddetails jd ,Vehicledetails vd, DealervslocationcodeAll u, Jobtypemaster jt, Servicetypemaster st,Joblocationmaster lm "
                    + " where jd.dealerCode=u.dealerCode and jd.vinno = vd.vinNo and vd.dealerCode=jd.dealerCode and jt.jobTypeID=jd.jobTypeId "
                    + " and st.serviceTypeID=jd.serviceTypeId and jd.locationId=lm.locationID and jd.jobCardNo=:jobcardNo");
            query.setParameter("jobcardNo", jobcardNo);
            
            System.out.println(query);

            List list = query.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {

                Object[] obj = (Object[]) itr.next();
                
                System.out.println("length of obj "+obj.length);
                
                serForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setJobCardDate(obj[1] == null ? "" : obj[1].toString().equals("") == true ? "" : sdf.format(dff.parse(obj[1].toString().trim())));
                serForm.setVehicleOut(obj[2] == null ? "" : obj[2].toString().equals("") == true ? "" : sdf.format(dff.parse(obj[2].toString().trim())));
                serForm.setWorkStarted(obj[3] == null ? "" : obj[3].toString().equals("") == true ? "" : sdf.format(dff.parse(obj[3].toString().trim())));
                serForm.setWorkFinished(obj[4] == null ? "" : obj[4].toString().equals("") == true ? "" : sdf.format(dff.parse(obj[4].toString().trim())));
                serForm.setJobTypeDesc(obj[5] == null ? "" : obj[5].toString().trim());
                serForm.setServiceType(obj[6] == null ? "" : obj[6].toString().trim());
                serForm.setBayCode(obj[7] == null ? "" : obj[7].toString().trim());
                serForm.setCouponno(obj[8] == null ? "" : obj[8].toString().trim());
                serForm.setVinNo(obj[9] == null ? "" : obj[9].toString().trim());
                serForm.setInspectionBy(obj[10] == null ? "" : obj[10].toString().trim());
                serForm.setMechanicName(obj[11] == null ? "" : obj[11].toString().trim());
                serForm.setEngineNumber(obj[12] == null ? "" : obj[12].toString().trim());
                serForm.setPayeeName(obj[13] == null ? "" : obj[13].toString().trim());
                serForm.setPayeeMobilePhone(obj[14] == null ? "" : obj[14].toString().trim());
                serForm.setPayeeVillage(obj[15] == null ? "" : obj[15].toString().trim());
                serForm.setPayeeTehsil(obj[16] == null ? "" : obj[16].toString().trim());
                serForm.setPayeeDistrict(obj[17] == null ? "" : obj[17].toString().trim());
                serForm.setPayeeState(obj[18] == null ? "" : obj[18].toString().trim());
                serForm.setPayeeCountry(obj[19] == null ? "" : obj[19].toString().trim());
                serForm.setPayeePinCode(obj[20] == null ? "" : obj[20].toString().trim());
                serForm.setHmr(obj[21] == null ? "" : obj[21].toString().trim());
                serForm.setCustomerName(obj[22] == null ? "" : obj[22].toString().trim());
                serForm.setModelFamily(obj[23] == null ? "" : obj[23].toString().trim());
                serForm.setMobilePhone(obj[24] == null ? "" : obj[24].toString().trim());
                serForm.setVillage(obj[25] == null ? "" : obj[25].toString().trim());
                serForm.setTehsil(obj[26] == null ? "" : obj[26].toString().trim());
                serForm.setDistrict(obj[27] == null ? "" : obj[27].toString().trim());
                serForm.setState(obj[28] == null ? "" : obj[28].toString().trim());
                serForm.setCountry(obj[29] == null ? "" : obj[29].toString().trim());
                serForm.setPinCode(obj[30] == null ? "" : obj[30].toString().trim());
                serForm.setDealercode(obj[31] == null ? "" : obj[31].toString().trim());
                serForm.setDealerName(obj[32] == null ? "" : obj[32].toString().trim());
                serForm.setLocationName(obj[33] == null ? "" : obj[33].toString().trim());
                serForm.setCreatedOn(obj[34] == null ? "" : obj[34].toString().equals("") == true ? "" : sdf.format(dff.parse(obj[34].toString().trim())));
                serForm.setIsWarReq(obj[35] == null ? "" : obj[35].toString().trim());
                serForm.setTotalPartsValue(obj[36] == null ? "0.0" : obj[36].toString().trim());//total of parts amount
                serForm.setTotalLubesValue(obj[37] == null ? "0.0" : obj[37].toString().trim());//total of lubes amount
                serForm.setTotalOtherCharges(obj[38] == null ? "0.0" : obj[38].toString().trim());//total of other amount
                serForm.setTotalLabourCharges(obj[39] == null ? "0.0" : obj[39].toString().trim());//total of labour amount
                serForm.setTotaldiscount(obj[40] == null ? "0.0" : obj[40].toString().trim());//other discount
                serForm.setTotalEstimate(obj[41] == null ? "0.0" : obj[41].toString().trim());//total of actual amounts
                serForm.setWarrantyApplicable(obj[42] == null ? "" : obj[42].toString().trim());//total of actual amounts
                serForm.setHmeRadio(obj[43] == null ? "" : obj[43].toString().trim());//total of actual amounts
                serForm.setJobType(obj[44] == null ? "" : obj[44].toString().trim());
                serForm.setRemarks(obj[45] == null ? "" : obj[45].toString().trim());
                serForm.setReasonForDealy(obj[46] == null ? "" : obj[46].toString().trim());
                serForm.setDeliveryDate(obj[47] == null ? "" : obj[47].toString().equals("") == true ? "" : sdf1.format(df1.parse(obj[47].toString().trim())));
                //serForm.setComplaintDate(obj[48] == null ? "" : obj[48].toString().equals("") == true ? "" : sdf1.format(df1.parse(obj[48].toString().trim())));
                //  System.out.println("DeliveryDate"+obj[47].toString());
                if (obj[48] != null && !obj[48].toString().equals("")) {
                    //  System.out.println("Complaint Date"+obj[48].toString());

                    serForm.setComplaintDate(sdf1.format(dff.parse(obj[48].toString())));
                }
                else {
                    serForm.setComplaintDate("");
                }
                
     //           System.out.println("obj[49] "+obj[49].toString().trim());
                
                serForm.setItlExtWarrantyApplicable(obj[49] == null ? "" : obj[49].toString().trim());
                serForm.setVorJobcard(obj[50] == null ? "" : obj[50].toString().trim());
                
        

                // System.out.println("*"+serForm.getTotalPartsValue()+"*"+serForm.getTotalLubesValue()+"*"+serForm.getTotalOtherCharges()+"*"+serForm.getTotalLabourCharges()+"**"+serForm.getTotaldiscount());

            }

            Query query1 = session.createQuery("select cd.cmpNo,cd.custVerbatim ,agm.aggDesc,sgm.subAggDesc,sam.subAssemblyDesc,cm.compDesc,"
                    + " am.appDesc,cd.foremanObsv from Jobcomplaintdetails cd ,Aggregatemaster agm , Subaggregatemaster sgm, Subassemblymaster sam,"
                    + " Complaintcodemaster cm, ApplicationMaster am"
                    + " where  cd.aggCode=agm.aggCode and cd.subAggCode = sgm.subAggCode and cd.subassemblyCode= sam.subAssemblyCode and"
                    + " cm.compCode=cd.defectCode  and cd.appCode=am.appCode and"
                    + " cd.jobCardNo=:jobCardNo");
            query1.setParameter("jobCardNo", jobcardNo);
            List l1 = query1.list();
            String[] cmpNo = new String[l1.size()];
            String[] cusVerb = new String[l1.size()];
            String[] aggDesc = new String[l1.size()];
            String[] subAssblyDesc = new String[l1.size()];
            String[] subAggDesc = new String[l1.size()];
            String[] compDesc = new String[l1.size()];
            //  String[] causeDesc = new String[l1.size()];
            String[] appDesc = new String[l1.size()];
//            String[] actionTaken =new String[l1.size()];String[] labourcharges =new String[l1.size()];
            String[] formanObservation = new String[l1.size()];
            ArrayList<ArrayList<String>> action_taken = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> labourCharges = new ArrayList<ArrayList<String>>();
            Iterator itr1 = l1.iterator();
            int i = 0;
            //   double labourchg = 0.0;
            while (itr1.hasNext()) {
                Object[] obj = (Object[]) itr1.next();
                cmpNo[i] = obj[0] == null ? "" : obj[0].toString().trim();
                cusVerb[i] = obj[1] == null ? "" : obj[1].toString().trim();
                aggDesc[i] = obj[2] == null ? "" : obj[2].toString().trim();
                subAggDesc[i] = obj[3] == null ? "" : obj[3].toString().trim();
                subAssblyDesc[i] = obj[4] == null ? "" : obj[4].toString().trim();
                compDesc[i] = obj[5] == null ? "" : obj[5].toString().trim();
                //causeDesc[i] = obj[6] == null ? "" : obj[6].toString().trim();
                appDesc[i] = obj[6] == null ? "" : obj[6].toString().trim();
                formanObservation[i] = obj[7] == null ? "" : obj[7].toString().trim();
//                actionTaken[i]=obj[9]==null?"":obj[9].toString().trim();
//                labourcharges[i]=obj[10]==null?"":obj[10].toString().trim();
                Query q1 = session.createQuery(" from Actualslabourcharges where cmpNo=:cmpNo");
                q1.setParameter("cmpNo", cmpNo[i]);
                List l = q1.list();
                Iterator it1 = l.iterator();
                int i1 = 0;
                ArrayList<String> actiontaken_list = new ArrayList<String>();
                ArrayList<String> labourchargList = new ArrayList<String>();
                while (it1.hasNext()) {
                    Actualslabourcharges actualslabourcharges = (Actualslabourcharges) it1.next();
                    actiontaken_list.add(actualslabourcharges.getActionTaken());

                    labourchargList.add("" + (actualslabourcharges.getLabourChargesAmount() == null ? "0.0" : actualslabourcharges.getLabourChargesAmount()));
                    // System.out.println(actiontaken_list.get(i1)+"****"+labourchargList.get(i1));

                    i1++;
                }
                if (i1 == 0) {
                    actiontaken_list.add("---");
                    labourchargList.add("0.0");
                }

                action_taken.add(actiontaken_list);
                labourCharges.add(labourchargList);
//                labourchg += Double.parseDouble(labourcharges[i]);
                // System.out.println(i+"****"+cmpNo[i]+"---"+cusVerb[i]+"---"+aggDesc[i]+"--"+subAssblyDesc[i]);
                i++;
            }
            if (i > 0) {
                serForm.setCmpid(cmpNo);
                serForm.setCompVerbatim(cusVerb);
                serForm.setAggCode(aggDesc);
                serForm.setSubaggCode(subAggDesc);
                serForm.setSubassembly(subAssblyDesc);
                serForm.setCompCode(compDesc);
                // serForm.setCausalCode(causeDesc);
                serForm.setApplicationCode(appDesc);
                serForm.setForemanObservation(formanObservation);
                serForm.setAction_taken(action_taken);
                serForm.setLabourCharges(labourCharges);
//                serForm.setLabourChargesAmount_str(""+labourchg);
            }

            Query query2 = session.createQuery("select pc.partNo,pc.partDesc,pc.partCategory,pc.qty,pc.unitPrice,pc.finalAmount,bm.billDesc,pc.discInPerc,pc.amount,pc.billID,cp.np8"
                    + " from JobcardSparesActualcharges pc,Billmaster bm,CatPart cp"
                    + " where pc.billID=bm.billID and pc.partNo=cp.partNo and pc.jobCardNo=:jobCardNo");
            query2.setParameter("jobCardNo", jobcardNo);
            List l2 = query2.list();
            String[] partNo = new String[l2.size()];
            String[] partDesc = new String[l2.size()];
            String[] partCat = new String[l2.size()];
            String[] qty = new String[l2.size()];
            String[] unitePrice = new String[l2.size()];
            String[] finalAmt = new String[l2.size()];
            String[] billDesc = new String[l2.size()];
            String[] discountPerc = new String[l2.size()];
            String[] discountAmt = new String[l2.size()];
            String[] billId = new String[l2.size()];
            String[] hsnCode = new String[l2.size()];
            int j = 0;
            Iterator itr2 = l2.iterator();
            double partamt = 0.0, lubeamt = 0.0, discount_part = 0.0, actualamt = 0.0;
            while (itr2.hasNext()) {
                actualamt = 0.0;
                Object[] obj = (Object[]) itr2.next();

                partNo[j] = obj[0] == null ? "" : obj[0].toString().trim();
                partDesc[j] = obj[1] == null ? "" : obj[1].toString().trim().toUpperCase();
                partCat[j] = obj[2] == null ? "" : obj[2].toString().trim();
                qty[j] = obj[3] == null ? "0" : obj[3].toString().trim();
                double t = Double.parseDouble(qty[j]);
                if (t == Math.ceil(t)) {
                    qty[j] = "" + ((int) t);
                }
                unitePrice[j] = obj[4] == null ? "0.0" : obj[4].toString().trim();
                finalAmt[j] = obj[5] == null ? "0.0" : obj[5].toString().trim();
                billDesc[j] = obj[6] == null ? "" : obj[6].toString().trim();
                discountPerc[j] = obj[7] == null ? "0.0" : obj[7].toString().trim();
                actualamt = Double.parseDouble(qty[j]) * Double.parseDouble(unitePrice[j]);//obj[8]==null?0.0:Double.parseDouble(obj[7].toString());
                discountAmt[j] = "" + ((Double.parseDouble(discountPerc[j]) / 100) * actualamt);
                billId[j] = obj[9] == null ? "" : obj[9].toString().trim();
                hsnCode[j]=obj[10] == null ? "" : obj[10].toString().trim();
                //  System.out.println("actual amt:"+actualamt+"dic amt:"+discountAmt[j]);

//                if(partCat[j].equalsIgnoreCase("SPARES"))
//                {
//                    partamt += Double.parseDouble(finalAmt[j]);
//                }
//                else{
//                    lubeamt +=  Double.parseDouble(finalAmt[j]);
//                }
                j++;
            }
            if (j > 0) {
                serForm.setPartNo(partNo);
                serForm.setPartDesc(partDesc);
                serForm.setComptype(partCat);
                serForm.setQuantityS(qty);
                serForm.setUnitPrice(unitePrice);
                serForm.setPartPriceAmount(finalAmt);
                serForm.setBillTo(billDesc);
//                serForm.setFinalamt_str(""+partamt);
//                serForm.setAmount_str(""+lubeamt);
                serForm.setDiscount_amt(discountAmt);
                serForm.setDiscount_perc(discountPerc);
                serForm.setBillCode(billId);
                serForm.setHsnCode(hsnCode);
            }

            Query query3 = session.createQuery("select om.workDesc,oc.workDescription,oc.workAmount "
                    + " from Actualsothercharges oc ,Otherjobworkmaster om"
                    + " where om.workID=oc.workCode and oc.jobCardNo=:jobCardNo");
            query3.setParameter("jobCardNo", jobcardNo);
            List l3 = query3.list();
            String[] worktype = new String[l3.size()];
            String[] workDesc = new String[l3.size()];
            String[] otherAmt = new String[l3.size()];
            int k = 0;
            Iterator itr3 = l3.iterator();
            //     double subTotal = 0.0;
            while (itr3.hasNext()) {
                Object[] obj = (Object[]) itr3.next();

                worktype[k] = obj[0] == null ? "" : obj[0].toString().trim();
                workDesc[k] = obj[1] == null ? "" : obj[1].toString().trim();
                otherAmt[k] = obj[2] == null ? "0.0" : obj[2].toString().trim();

//                subTotal +=  Double.parseDouble(otherAmt[k]);
                k++;
            }
            if (k > 0) {
                serForm.setWorkDescription(workDesc);
                serForm.setWorkCode(worktype);
                serForm.setWorkAmount(otherAmt);
//                serForm.setWorkAmount_str(""+subTotal);
            }
//            serForm.setContext(""+(labourchg+partamt+lubeamt+subTotal));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serForm;
    }

    public String approveJobCard(serviceForm sf, String user_id) throws SQLException
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "fail";
        try {
            session.beginTransaction();
            Jobcarddetails jcDetail = (Jobcarddetails) session.load(Jobcarddetails.class, sf.getJobCardNo());
            if (sf.getStatus().equalsIgnoreCase("Approved")) {
                if (sf.getIsWarReq().equalsIgnoreCase("Y")) {
                    jcDetail.setWtyClaimStatus("PENDING");
                }
            }
            jcDetail.setDeTmsApproval(sf.getStatus());
            jcDetail.setDeApprovedBy(user_id);
            jcDetail.setDeApprovedOn(new Date());
            if (!sf.getRemarks().equals("")) {
                jcDetail.setDeRemarks(sf.getRemarks());
            }
            else {
                jcDetail.setDeRemarks(null);
            }
            session.saveOrUpdate(jcDetail);
            session.getTransaction().commit();
            result = "success";
        }
        catch (Exception e) {

            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return result;
    }

    /**
     * generateInvoice creted on 13/10/14 by Megha
     */
    public String generateInvoice(serviceForm sf, String user_id) throws SQLException
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "FAILURE@@Unable to Generate Invoice";
        String invoiceNo = "";
        // double part_discount = 0.0, lubes_discount = 0.0;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            session.beginTransaction();

            Query qtr = session.createSQLQuery("exec SP_getVehicleCustomerID :customerCode,:dealerCode");
            qtr.setParameter("customerCode", sf.getVinNo());
            qtr.setParameter("dealerCode", sf.getDealercode());
            List customerId = qtr.list();
            if (customerId.isEmpty()) {
                return "FAILURECUST@@vehicleInfoFailure4locCode";
            }
            else {
                String check = MethodUtility.check_in_Db_HQL(sf.getJobCardNo(), "SpInventSaleMaster", "typeRefno", "", session);
                if (check.equalsIgnoreCase("notexist")) {
                    invoiceNo = new MethodUtility().getNumber(session, "SpInventSaleMaster", sf.getDealercode(), "I");
                    if (invoiceNo.equals("notexist")) {
                        return "FAILURE@@vehicleInfoFailure4locCode";
                    }
                    Jobcarddetails jcDetail = (Jobcarddetails) session.load(Jobcarddetails.class, sf.getJobCardNo());
                    jcDetail.setStatus(sf.getStatus());
                    jcDetail.setDeTmsApproval("APPROVED");
                     if ("REQUIRED".equalsIgnoreCase(jcDetail.getWtyClaimStatus()) && jcDetail.getIswarrantyreq() == 'Y' &&  sf.getJobType().equalsIgnoreCase("35")) {
                          jcDetail.setWtyClaimStatus("EXTPENDING");
                     }else  if ("REQUIRED".equalsIgnoreCase(jcDetail.getWtyClaimStatus()) && jcDetail.getIswarrantyreq() == 'Y') {
                        jcDetail.setWtyClaimStatus("PENDING");
                    }
                    jcDetail.setLastModifiedBy(user_id);
                    jcDetail.setLastModifiedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                    session.saveOrUpdate(jcDetail);

//            Inser into SpInventSaleDetails
                    boolean invoiceFlag = false;
                    if ((sf.getPartNo() != null) && (sf.getPartNo().length > 0)) {
                        for (int i = 0; i < sf.getPartNo().length; i++) {
                            String checkpartNo = new MethodUtility().checkInDb(sf.getPartNo()[i], "partNo", "CatPart");
                            if (checkpartNo.equals("notexist")) {
                                session.getTransaction().rollback();
                                return  "HSNCODE@@'" + sf.getPartNo()[i]+"'";

                            }
                            SpInventSaleDetailsPK spInvDetailsPK = new SpInventSaleDetailsPK(invoiceNo, sf.getPartNo()[i]);
                            SpInventSaleDetails spInvDetails = new SpInventSaleDetails(spInvDetailsPK);
                            spInvDetails.setPartdesc(sf.getPartDesc()[i].toUpperCase());
                            spInvDetails.setPartcategory(sf.getComptype()[i]);
                            spInvDetails.setQty(sf.getQuantityS()[i] == null ? 0.0 : Double.parseDouble(sf.getQuantityS()[i]));
                            spInvDetails.setUnitvalue(sf.getUnitPrice()[i] == null ? 0.0 : Double.parseDouble(sf.getUnitPrice()[i]));
                            spInvDetails.setBillID(sf.getBillCode()[i] == null ? 0 : Integer.parseInt(sf.getBillCode()[i]));
                            spInvDetails.setDiscount(sf.getDiscount_amt()[i] == null ? 0.0 : Double.parseDouble(sf.getDiscount_amt()[i]));
                            spInvDetails.setFinalAmount(sf.getPartPriceAmount()[i] == null ? 0.0 : Double.parseDouble(sf.getPartPriceAmount()[i]));
                            /*   if (sf.getComptype()[i].equalsIgnoreCase("SPARES")) {
                            part_discount += spInvDetails.getDiscount();
                            } else {
                            lubes_discount += spInvDetails.getDiscount();
                            }
                             */ session.save(spInvDetails);
                            MethodUtility.inventoryLedgerEntry(user_id, sf.getDealercode(), sf.getPartNo()[i], Double.parseDouble(sf.getQuantityS()[i]), "Part Invoice", session);
                            if (i % 50 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }


                        //insert into spInventSaleMaster
                        SpInventSaleMaster spInventSaleMaster = new SpInventSaleMaster(invoiceNo);
                        spInventSaleMaster.setDealerCode(sf.getDealercode());
                        spInventSaleMaster.setInvoicetype("JobCard");
                        spInventSaleMaster.setTypeRefno(sf.getJobCardNo());
                        spInventSaleMaster.setInvoiceDate(df.parse(sf.getInvoicedate()));
                        spInventSaleMaster.setCustomerName(sf.getPayeeName());
                        spInventSaleMaster.setContactno(sf.getPayeeMobilePhone());

                        // Double.parseDouble(sf.getTotalEstimate());
                        double totalValue = sf.getTotalEstimate() == null || sf.getTotalEstimate().equals("") ? 0.0 : Double.parseDouble(sf.getTotalEstimate());
                        // Discount is already considered in total value
                        totalValue = totalValue - (((sf.getTotalOtherCharges() == null || sf.getTotalOtherCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalOtherCharges())) + (sf.getTotalLabourCharges() == null || sf.getTotalLabourCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalLabourCharges()))));

                        spInventSaleMaster.setInvoiceValue(totalValue);
                        spInventSaleMaster.setPartsValue(sf.getTotalPartsValue() == null ? 0.0 : Double.parseDouble(sf.getTotalPartsValue()));
                        spInventSaleMaster.setLubesValue(sf.getTotalLubesValue() == null ? 0.0 : Double.parseDouble(sf.getTotalLubesValue()));
                        spInventSaleMaster.setOtherValue(0.0);
                        spInventSaleMaster.setDiscountParts(0);
                        spInventSaleMaster.setDiscountLubes(0);
                        spInventSaleMaster.setDiscountOther(sf.getTotaldiscount() == null || sf.getTotaldiscount().equals("") ? 0.0 : Double.parseDouble(sf.getTotaldiscount()));
                        spInventSaleMaster.setCreatedBy(user_id);
                        spInventSaleMaster.setCreatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                        spInventSaleMaster.setCreditValue(sf.getCreditValue() == null ? 0.0 : sf.getCreditValue().equals("") ? 0.0 : Double.parseDouble(sf.getCreditValue()));
                        spInventSaleMaster.setCustomerID(new BigInteger("" + customerId.get(0)));
                        spInventSaleMaster.setSaleTypeCode("SGST");
                        spInventSaleMaster.setGstNo(sf.getGstNo() == null ? "" : sf.getGstNo());
                        String invoiveNo12Digit = new MethodUtility().get12DigitInvoiceNoForSpareInvoice(session, "SpInventSaleMaster", sf.getDealercode(), "INV");
                        spInventSaleMaster.setInvoiceNo12Digits(invoiveNo12Digit);
                        session.save(spInventSaleMaster);

                        invoiceFlag = true;

                    }

                    String check1 = MethodUtility.check_in_Db_HQL(sf.getJobCardNo(), "Actualslabourcharges", "jobCardNo", "", session);
                    String check2 = MethodUtility.check_in_Db_HQL(sf.getJobCardNo(), "Actualsothercharges", "jobCardNo", "", session);

                    // Only if Labour and other charges exists
                    if (!invoiceFlag || check1.equalsIgnoreCase("exist") || check2.equalsIgnoreCase("exist")) {

                        String invoiceNoL = new MethodUtility().getNumber(session, "SpInventSaleMaster", sf.getDealercode(), "I");
                        if (invoiceNoL.equals("notexist")) {
                            return "FAILURE@@vehicleInfoFailure4locCode";
                        }

                        //insert into spInventSaleMaster
                        SpInventSaleMaster spInventSaleMasterL = new SpInventSaleMaster(invoiceNoL);
                        spInventSaleMasterL.setDealerCode(sf.getDealercode());
                        spInventSaleMasterL.setInvoicetype("JobCard");
                        spInventSaleMasterL.setTypeRefno(sf.getJobCardNo());
                        spInventSaleMasterL.setInvoiceDate(df.parse(sf.getInvoicedate()));
                        spInventSaleMasterL.setCustomerName(sf.getPayeeName());
                        spInventSaleMasterL.setContactno(sf.getPayeeMobilePhone());

                        // Double.parseDouble(sf.getTotalEstimate());

                          double totalValue = ((sf.getTotalOtherCharges() == null || sf.getTotalOtherCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalOtherCharges())) + (sf.getTotalLabourCharges() == null || sf.getTotalLabourCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalLabourCharges())));
                        // Discount is already considered in total value

                        if(!invoiceFlag)
                        {
                            totalValue = totalValue - (sf.getTotaldiscount() == null || sf.getTotaldiscount().equals("") ? 0.0 : Double.parseDouble(sf.getTotaldiscount()));
                        }

                        spInventSaleMasterL.setInvoiceValue(totalValue);
                        spInventSaleMasterL.setPartsValue(0.0);
                        spInventSaleMasterL.setLubesValue(0.0);
                        spInventSaleMasterL.setOtherValue(((sf.getTotalOtherCharges() == null || sf.getTotalOtherCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalOtherCharges())) + (sf.getTotalLabourCharges() == null || sf.getTotalLabourCharges().equals("") ? 0.0 : Double.parseDouble(sf.getTotalLabourCharges()))));
                        spInventSaleMasterL.setDiscountParts(0);
                        spInventSaleMasterL.setDiscountLubes(0);
                        if(invoiceFlag)
                        {
                            spInventSaleMasterL.setDiscountOther(0.0);
                        }
                        else
                        {
                          spInventSaleMasterL.setDiscountOther(sf.getTotaldiscount() == null || sf.getTotaldiscount().equals("") ? 0.0 : Double.parseDouble(sf.getTotaldiscount()));
                        }
                        spInventSaleMasterL.setCreatedBy(user_id);
                        spInventSaleMasterL.setCreatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                        if(invoiceFlag)
                        {
                         spInventSaleMasterL.setCreditValue(0.0);
                        }
                        else
                        {
                          spInventSaleMasterL.setCreditValue(sf.getCreditValue() == null ? 0.0 : sf.getCreditValue().equals("") ? 0.0 : Double.parseDouble(sf.getCreditValue()));
                        }
                        spInventSaleMasterL.setCustomerID(new BigInteger("" + customerId.get(0)));
                        spInventSaleMasterL.setSaleTypeCode("SGST");
                        spInventSaleMasterL.setGstNo(sf.getGstNo() == null ? "" : sf.getGstNo());
                        String invoiveNo12Digit = new MethodUtility().get12DigitInvoiceNoForSpareInvoice(session, "SpInventSaleMaster", sf.getDealercode(), "INV");
                        spInventSaleMasterL.setInvoiceNo12Digits(invoiveNo12Digit);
                        session.save(spInventSaleMasterL);
                    }

                    sf.setVinid(sf.getDealercode() + "-" + sf.getVinNo());
                    Vehicledetails vd = (Vehicledetails) session.load(Vehicledetails.class, sf.getVinid());

                    Criteria cr = session.createCriteria(SWVehicleServiceSchedule.class);
                    cr.add(Restrictions.eq("vinNo", vd.getVinNo()));
                    cr.add(Restrictions.eq("jobTypeId", Integer.parseInt(sf.getJobType())));
                    if (vd.getDeliveryDate() != null) {
                        cr.add(Restrictions.eq("deliveryDate", sdf.parse(sdf.format(vd.getDeliveryDate()))));
                    }

                    if (Integer.parseInt(sf.getJobType()) == 9) {
                        cr.add(Restrictions.eq("status", "PENDING"));
                    }

                    //cr.add(Restrictions.eq("sWVehicleServiceSchedulePK.deliveryDate", sdf.parse(sdf.format(vd.getDeliveryDate()))));
                    //cr.add(Restrictions.le("dueDate", new java.util.Date()));

                    Iterator itr1;

                    Iterator itr = cr.list().iterator();
                    if (itr.hasNext()) {
                        SWVehicleServiceSchedule sc = (SWVehicleServiceSchedule) itr.next();
                        sc.setServiceDealer(sf.getDealercode());
                        sc.setStatus("DONE");
                        sc.setJobCardNo(sf.getJobCardNo());
                        sc.setJobCardDate(df.parse(sf.getJobCardDate()));
                        session.saveOrUpdate(sc);

                        Criteria cr1 = session.createCriteria(SWVehicleServiceSchedule.class);
                        cr1.add(Restrictions.eq("status", "PENDING"));
                        cr1.add(Restrictions.eq("vinNo", vd.getVinNo()));
                        if (vd.getDeliveryDate() != null) {
                            cr1.add(Restrictions.eq("deliveryDate", sdf.parse(sdf.format(vd.getDeliveryDate()))));
                        }
                        cr1.add(Restrictions.lt("dueDate", sdf.parse(sdf.format(sc.getDueDate()))));// sdf.parse(sdf.format(new java.sql.Timestamp(new java.util.Date().getTime())))));
                        itr1 = cr1.list().iterator();
                        while (itr1.hasNext()) {
                            SWVehicleServiceSchedule sc1 = (SWVehicleServiceSchedule) itr1.next();
                            sc1.setServiceDealer(sf.getDealercode());
                            sc1.setStatus("NOT DONE");
                            //sc1.setJobCardNo(sf.getJobCardNo());
                            // sc1.setJobCardDate(df.parse(sf.getJobCardDate()));
                            session.saveOrUpdate(sc1);
                        }
                    }

                    if (vd.getVinDetailsType().equalsIgnoreCase("DB") && vd.getDeliveryDate() != null) {
                        Criteria cr2 = session.createCriteria(SWVehicleServiceSchedule.class);
                        cr2.add(Restrictions.eq("status", "PENDING"));
                        cr2.add(Restrictions.eq("vinNo", vd.getVinNo()));

                        itr1 = cr2.list().iterator();
                        if (!itr1.hasNext()) {
                            Calendar cal = Calendar.getInstance();
                            if (sf.getInvoicedate() != null) {
                                cal.setTime(df.parse(sf.getInvoicedate()));
                            }
                            cal.add(Calendar.MONTH, 3);

                            SWVehicleServiceSchedule serSch = new SWVehicleServiceSchedule();
                            serSch.setDeliveryDate(sdf.parse(sdf.format(vd.getDeliveryDate())));
                            serSch.setDueDate(cal.getTime()); // add 3 months
                            serSch.setFollowUpStatus("PENDING");
                            serSch.setJobTypeId(9); // Fix for Paid Service / Repair
                            serSch.setServiceDealer(sf.getDealercode());
                            serSch.setStatus("PENDING");
                            serSch.setVinNo(vd.getVinNo());
                            session.save(serSch);
                        }
                    }

                    session.getTransaction().commit();
                    result = "SUCCESS@@Invoice Generated@@" + invoiceNo;

                    session.beginTransaction();
                    session.createSQLQuery("exec SP_updateTaxInvoice :invoiceNo").setParameter("invoiceNo", invoiceNo).executeUpdate();
                    session.getTransaction().commit();
                    if (jcDetail.getIswarrantyreq() == 'Y' &&  sf.getJobType().equalsIgnoreCase("35")) {
                        session.beginTransaction();
                        session.createSQLQuery("exec SP_BajajExtWtyTaxInvoiceUpdate :invoiceNo").setParameter("invoiceNo", invoiceNo).executeUpdate();
                        session.getTransaction().commit();
                    }
                    
                    //update status of job card if invoice is generated  chnages done by aman 17-10-2024
                    int index  = result.indexOf("@");
                    String flag = result.substring(0, index);
                    if(flag != null && !flag.isEmpty() &&flag.equalsIgnoreCase("success")) {
                    	if(sf.getJobCardNo() != null && !sf.getJobCardNo().isEmpty()) {
                    		Jobcarddetails jc = (Jobcarddetails) session.load(Jobcarddetails.class, sf.getJobCardNo());
                            jc.setStatus("BILLED");
                            jc.setDeTmsApproval("APPROVED");
                            jc.setLastModifiedBy(user_id);
                            jc.setLastModifiedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                            session.saveOrUpdate(jc);
                    	}
                    	
                    }
                }
                else {
                    result = "EXIST@@Invoice EXIST";
                }
            }


        }
        catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return result;
    }

    public String getInventoryQty(String partno, String dealercode)
    {

        String val = "0.0";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "select distinct currentQty from SpCurrentInventoryView where dealerCode=:dealercode and partNo=:partno";//from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;
////dealerCode,partNo
            Query query = hrbsession.createQuery(hql);
            query.setString("dealercode", dealercode);
            query.setString("partno", partno);
            double qty = 0;
            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                val = itr.next().toString();
                qty = Double.parseDouble(val);
                if (qty < 0) {
                    val = "0.0";
                }

            }

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("inside function getInventoryQty");
        System.out.println(dealercode +" " + partno +" " + val);
        return val;
    }

    //checkWarrantyApplicable(
    public String checkWarrantyApplicable(String jobcard)
    {
        String val = "notexist";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "from JobcardSparesActualcharges where billID=2 and jobCardNo='" + jobcard + "'";//from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;

            Query query = hrbsession.createQuery(hql);

            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                val = "exist";
            }

        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {

                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    /**
     * getting Invoice No List 4 print invoice created on 03/11/14 by Megha
     */
    public ArrayList<serviceForm> getInvNoList4print(String userId, serviceForm srForm, Vector userFunctionalities)
    {
        ArrayList<serviceForm> invNoList4Print = new ArrayList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        String dateSubQur = "";
        String creditValQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            Query query = null;
            if (!srForm.getInvoiceno().equals("")) {
                Subsql = " and  sm.invoiceNo like '%" + srForm.getInvoiceno() + "%' ";
            }

            if ("1".equals(srForm.getRange())) {
                dateSubQur = " and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate) )";
            }
            if ("1".equals(srForm.getCreditAmount())) {
                creditValQur = " and sm.creditValue !='0' ";
            }

            String hql = "select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,"
                    + " sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,sm.invoiceValue,"
                    + " sm.creditValue from SpInventSaleMaster sm, Dealervslocationcode u "
                    + " where sm.dealerCode=u.dealerCode  ";
            //+ " and sm.dealerCode IN(:dealerList) order by sm.invoiceDate desc");

            query = session.createQuery(hql + Subsql + dateSubQur + creditValQur + " and :dealerList LIKE ('%'+sm.dealerCode+'%') order by sm.invoiceDate desc");
            if (srForm.getDealercode() != null && !srForm.getDealercode().equals("") && !srForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(srForm.getDealercode());
            }
            else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*       if (userFunctionalities.contains("101"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode, "
            + "sm.invoiceValue ,sm.creditValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode "
            + "  " + Subsql + dateSubQur + creditValQur + " and sm.dealerCode=:dealerCode order by sm.invoiceDate desc");  //(sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate)) and
            query.setParameter("dealerCode", srForm.getDealercode());
            }
            else if (userFunctionalities.contains("102"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
            + "sm.invoiceValue,sm.creditValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode "
            + "  " + Subsql + dateSubQur + creditValQur + "and sm.dealerCode IN(:dealerList) order by sm.invoiceDate desc");  //and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate))

            ArrayList<String> dealerList = new ArrayList<String>();
            //ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
            if (srForm.getDealercode() != null && !srForm.getDealercode().equals("") && !srForm.getDealercode().equalsIgnoreCase("ALL"))
            {
            dealerList.add(srForm.getDealercode());
            query.setParameterList("dealerList", dealerList);
            }
            else
            {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
            query.setParameterList("dealerList", dealerList);
            }
            }
            else if (userFunctionalities.contains("103"))
            {
            if (srForm.getDealercode() != null && !srForm.getDealercode().equals("") && !srForm.getDealercode().equalsIgnoreCase("ALL"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
            + "sm.invoiceValue,sm.creditValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " " + Subsql + dateSubQur + creditValQur + " and sm.dealerCode=:dealerCode order by sm.invoiceDate desc");  //and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate))
            query.setParameter("dealerCode", srForm.getDealercode());
            }
            else
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
            + "sm.invoiceValue ,sm.creditValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " " + Subsql + dateSubQur + creditValQur + " order by sm.invoiceDate desc");
            }
            }*/

            if ("1".equals(srForm.getRange())) {
                query.setDate(0, srForm.getFromdate().equals("") == true ? null : sdf.parse(srForm.getFromdate()));
                query.setDate(1, srForm.getTodate().equals("") == true ? null : sdf.parse(srForm.getTodate()));
            }
            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
//                    serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setInvoiceDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                    serForm.setCustomerName(obj[2] == null ? "" : obj[2].toString().trim());
                    serForm.setMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setRefNo(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealercode(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setDealerName(obj[6] == null ? "" : obj[6].toString().trim());
                    serForm.setLocationName(obj[7] == null ? "" : obj[7].toString().trim());
                    serForm.setTotalEstimate(obj[9] == null ? "" : obj[9].toString().trim());
                    serForm.setCreditValue(obj[10] == null ? "" : obj[10].toString().trim());
                    invNoList4Print.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invNoList4Print;
    }

    /**
     * print Invoice Data details  created on 03/11/14 by Megha
     */
    public serviceForm printInvoiceData(serviceForm serForm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // serviceForm serForm = new serviceForm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            Query query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,"
                    + "sm.createdOn,u.dealerCode,u.dealerName,u.locationCode,u.location,sm.partsValue,sm.lubesValue"
                    + ",sm.otherValue,sm.invoiceValue,sm.discountOther,sm.invoicetype,sm.creditValue"
                    + " from SpInventSaleMaster sm, Dealervslocationcode u"
                    + " where sm.dealerCode=u.dealerCode and sm.invoiceNo=:invoiceNo");
            query.setParameter("invoiceNo", serForm.getInvoiceno());

            List list = query.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {

                Object[] obj = (Object[]) itr.next();
                serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().equals("") == true ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                serForm.setPayeeName(obj[2] == null ? "" : obj[2].toString().trim());
                serForm.setPayeeMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                serForm.setRefNo(obj[4] == null ? "" : obj[4].toString().trim());
                serForm.setCreatedOn(obj[5] == null ? "" : obj[5].toString().equals("") == true ? "" : sdf.format(df.parse(obj[5].toString().trim())));
                serForm.setDealercode(obj[6] == null ? "" : obj[6].toString().trim());
                serForm.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());
                serForm.setLocationName(obj[9] == null ? "" : obj[9].toString().trim());
                serForm.setTotalPartsValue(obj[10] == null ? "0.0" : obj[10].toString().trim());//total of parts amount
                serForm.setTotalLubesValue(obj[11] == null ? "0.0" : obj[11].toString().trim());//total of lubes amount
                serForm.setTotalOtherCharges(obj[12] == null ? "0.0" : obj[12].toString().trim());//total of other amount
                serForm.setTotaldiscount(obj[14] == null ? "0.0" : obj[14].toString().trim());//other discount
                serForm.setTotalEstimate(obj[13] == null ? "0.0" : obj[13].toString().trim());//total of actual amounts
                serForm.setJctype(obj[15] == null ? "" : obj[15].toString().trim());
                serForm.setCreditValue(obj[16] == null ? "" : obj[16].toString().trim());
                // System.out.println("*"+serForm.getTotalPartsValue()+"*"+serForm.getTotalLubesValue()+"*"+serForm.getTotalOtherCharges()+"*"+serForm.getTotalLabourCharges()+"**"+serForm.getTotaldiscount());
            }

            // For part/lubes details
            Query query2 = session.createQuery("select sd.spInventSaleDetailsPK.partno,sd.partdesc,sd.partcategory,sd.qty,sd.unitvalue,sd.finalAmount,bm.billDesc,sd.discount,sd.billID "
                    + " from SpInventSaleDetails sd ,Billmaster bm"
                    + " where sd.billID=bm.billID and sd.spInventSaleDetailsPK.invoiceNo=:invoiceNo");
            query2.setParameter("invoiceNo", serForm.getInvoiceno());
            List l2 = query2.list();
            String[] partNo = new String[l2.size()];
            String[] partDesc = new String[l2.size()];
            String[] partCat = new String[l2.size()];
            String[] qty = new String[l2.size()];
            String[] unitePrice = new String[l2.size()];
            String[] finalAmt = new String[l2.size()];
            String[] billDesc = new String[l2.size()];
//            String[] discountPerc = new String[l2.size()];
            String[] discountAmt = new String[l2.size()];
            String[] billId = new String[l2.size()];
            int j = 0;
            Iterator itr2 = l2.iterator();
            double partamt = 0.0, lubeamt = 0.0, discount_part = 0.0, actualamt = 0.0;
            while (itr2.hasNext()) {
                actualamt = 0.0;
                Object[] obj = (Object[]) itr2.next();

                partNo[j] = obj[0] == null ? "" : obj[0].toString().trim();
                partDesc[j] = obj[1] == null ? "" : obj[1].toString().trim().toUpperCase();
                partCat[j] = obj[2] == null ? "" : obj[2].toString().trim();
                qty[j] = obj[3] == null ? "0" : obj[3].toString().trim();
                double t = Double.parseDouble(obj[3].toString().trim());
                if (t == Math.ceil(t)) {
                    qty[j] = "" + ((int) t);
                }
                else {
                }
                unitePrice[j] = obj[4] == null ? "0.0" : obj[4].toString().trim();
                finalAmt[j] = obj[5] == null ? "0.0" : obj[5].toString().trim();
                billDesc[j] = obj[6] == null ? "" : obj[6].toString().trim();
                discountAmt[j] = obj[7] == null ? "" : obj[7].toString().trim();
                billId[j] = obj[8] == null ? "" : obj[8].toString().trim();

                j++;
            }
            if (j > 0) {
                serForm.setPartNo(partNo);
                serForm.setPartDesc(partDesc);
                serForm.setComptype(partCat);
                serForm.setQuantityS(qty);
                serForm.setUnitPrice(unitePrice);
                serForm.setPartPriceAmount(finalAmt);
                serForm.setBillTo(billDesc);
                serForm.setDiscount_amt(discountAmt);
//                serForm.setDiscount_perc(discountPerc);
                serForm.setBillCode(billId);
            }
            if (serForm.getJctype().equalsIgnoreCase("JobCard")) {
                // For labour details
                Query query1 = session.createQuery("select cd.cmpNo,cd.custVerbatim ,cm.compDesc,cd.foremanObsv"
                        + " from Jobcomplaintdetails cd , Complaintcodemaster cm "
                        + " where cd.defectCode=cm.compCode and cd.jobCardNo=:jobCardNo");
                query1.setParameter("jobCardNo", serForm.getJobCardNo());
                List l1 = query1.list();
                String[] cmpNo = new String[l1.size()];
                String[] compDesc = new String[l1.size()];
                //  String[] formanObservation = new String[l1.size()];
                ArrayList<ArrayList<String>> action_taken = new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> labourCharges = new ArrayList<ArrayList<String>>();
                Iterator itr1 = l1.iterator();
                int i = 0;
                //  double labourchg = 0.0;
                while (itr1.hasNext()) {
                    Object[] obj = (Object[]) itr1.next();
                    cmpNo[i] = obj[0] == null ? "" : obj[0].toString().trim();
                    compDesc[i] = obj[2] == null ? "" : obj[2].toString().trim();
                    Query q1 = session.createQuery(" from Actualslabourcharges where cmpNo=:cmpNo");
                    q1.setParameter("cmpNo", cmpNo[i]);
                    List l = q1.list();
                    Iterator it1 = l.iterator();
                    int i1 = 0;
                    ArrayList<String> actiontaken_list = new ArrayList<String>();
                    ArrayList<String> labourchargList = new ArrayList<String>();
                    while (it1.hasNext()) {
                        Actualslabourcharges actualslabourcharges = (Actualslabourcharges) it1.next();
                        actiontaken_list.add(actualslabourcharges.getActionTaken());
                        labourchargList.add("" + (actualslabourcharges.getLabourChargesAmount() == null ? "0.0" : actualslabourcharges.getLabourChargesAmount()));
                        i1++;
                    }
                    if (i1 == 0) {
                        actiontaken_list.add("---");
                        labourchargList.add("0.0");
                    }
                    action_taken.add(actiontaken_list);
                    labourCharges.add(labourchargList);
                    i++;
                }
                if (i > 0) {
                    serForm.setCmpid(cmpNo);
                    serForm.setCompCode(compDesc);
                    serForm.setAction_taken(action_taken);
                    serForm.setLabourCharges(labourCharges);
                }
                // For other work details
                Query query3 = session.createQuery("select om.workDesc,oc.workDescription,oc.workAmount "
                        + " from Actualsothercharges oc ,Otherjobworkmaster om"
                        + " where om.workID=oc.workCode and oc.jobCardNo=:jobCardNo");
                query3.setParameter("jobCardNo", serForm.getJobCardNo());
                List l3 = query3.list();
                String[] worktype = new String[l3.size()];
                String[] workDesc = new String[l3.size()];
                String[] otherAmt = new String[l3.size()];
                int k = 0;
                Iterator itr3 = l3.iterator();
                double subTotal = 0.0;
                while (itr3.hasNext()) {
                    Object[] obj = (Object[]) itr3.next();

                    worktype[k] = obj[0] == null ? "" : obj[0].toString().trim();
                    workDesc[k] = obj[1] == null ? "" : obj[1].toString().trim();
                    otherAmt[k] = obj[2] == null ? "0.0" : obj[2].toString().trim();
                    subTotal += Double.parseDouble(otherAmt[k]);

                    k++;
                }
                if (k > 0) {
                    serForm.setWorkDescription(workDesc);
                    serForm.setWorkCode(worktype);
                    serForm.setWorkAmount(otherAmt);
                    serForm.setTotalLabourCharges("" + (Double.parseDouble(serForm.getTotalOtherCharges()) - subTotal));
                    serForm.setTotalOtherCharges("" + subTotal);
                }
                else {
                    serForm.setTotalLabourCharges(serForm.getTotalOtherCharges());
                    serForm.setTotalOtherCharges("0.0");
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serForm;
    }

    //get search Complain Data
    public void getsearchComplainData(serviceForm sf)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
//          Query query = session.createQuery("select am.aggDesc,sam.subAggDesc,sm.subAssemblyDesc,cm.compDesc from"+
//            " Complaintcodemaster cm ,Subassemblymaster sm,Subaggregatemaster sam,Aggregatemaster am"+
//            " where cm.subAssemblyCode=sm.subAssemblyCode and sm.subAggCode=sam.subAggCode and"+
//            " sam.aggCode = am.aggCode and cm.compDesc like '%"+sf.getConstantValue().trim().replaceAll("", "%")+"%'");
            String[] words = sf.getConstantValue().trim().split(" ");
            String orClause = "";
            for (int i = 0; i < words.length; i++) {
                orClause += "cm.CompDesc like '%" + words[i] + "%' or ";
            }
            orClause = orClause.substring(0, orClause.length() - 3);
            Query query1 = session.createSQLQuery("select am.AggDesc,sam.SubAggDesc,saa.SubAssemblyDesc, cm.CompDesc "
                    + " from "
                    + " MSW_complaintcode cm,MSW_aggregatemaster am,MSW_subaggregatemaster sam,"
                    + " MSW_subassemblymaster saa"
                    + " where cm.SubAssemblyCode=saa.SubAssemblyCode and saa.SubAggCode=sam.SubAggCode"
                    + " and sam.AggCode=am.AggCode  and cm.CompDesc like '%" + sf.getConstantValue().trim().replaceAll(" ", "%") + "%'"
                    + " UNION"
                    + " select am.AggDesc,sam.SubAggDesc,saa.SubAssemblyDesc, cm.CompDesc   "
                    + " from MSW_complaintcode cm,MSW_aggregatemaster am,MSW_subaggregatemaster sam,"
                    + " MSW_subassemblymaster saa "
                    + " where cm.SubAssemblyCode=saa.SubAssemblyCode and saa.SubAggCode=sam.SubAggCode "
                    + " and sam.AggCode=am.AggCode  and"
                    + " (" + orClause + ")").addScalar("AggDesc", StringType.INSTANCE).addScalar("SubAggDesc", StringType.INSTANCE).addScalar("SubAssemblyDesc", StringType.INSTANCE).addScalar("CompDesc", StringType.INSTANCE);
            List list = query1.list();
            //  System.out.println("list size:" + list.size());
            Iterator it = list.iterator();
            int i = 0;
            String[] aggCode = new String[list.size()];
            String[] subAggCode = new String[list.size()];
            String[] subAssCode = new String[list.size()];
            String[] compDesc = new String[list.size()];
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                aggCode[i] = (obj[0] == null ? "" : obj[0].toString().trim());
                subAggCode[i] = (obj[1] == null ? "" : obj[1].toString().trim());
                subAssCode[i] = (obj[2] == null ? "" : obj[2].toString().trim());
                compDesc[i] = (obj[3] == null ? "" : obj[3].toString().trim());
                i++;
            }
            if (i > 0) {
                sf.setAggCode(aggCode);
                sf.setSubaggCode(subAggCode);
                sf.setSubassembly(subAssCode);
                sf.setCompVerbatim(compDesc);
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<serviceForm> getJobCardHistory(serviceForm serviceForm, String dealercode, String user_id, Vector userFunctionalities) throws SQLException
    {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        ArrayList<serviceForm> dataList = new ArrayList<serviceForm>();
        String sql = "", Subsql = "", sql1 = "";
        Jobcarddetails jc = null;


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            if (userFunctionalities.contains("101"))
//            {
//                String check = check_in_Db(serviceForm.getVinNo(), "Vehicledetails", "vinNo", " and dealerCode='" + dealercode + "'");
//                if (!check.equals("notexist"))
//                {
//
//
//                    query = hrbsession.createQuery("select jc.jobCardNo,jc.jobCardDate,jc.payeeName,jc.mobilePhone,jc.hMRWorking,jc.hmr,jp.jobTypeDesc,jc.dealerCode,u.dealerName,u.location,jc.status  from Jobcarddetails jc,Jobtypemaster as jp,Dealervslocationcode u  where  jc.vinno='" + serviceForm.getVinNo() + "' and jc.jobTypeId = jp.jobTypeID and jc.dealerCode=u.dealerCode order by jc.dealerCode,jc.jobCardDate");
//                }
//            }
//            else if (userFunctionalities.contains("102"))
//            {
//                query = hrbsession.createQuery("select jc.jobCardNo,jc.jobCardDate,jc.payeeName,jc.mobilePhone,jc.hMRWorking,jc.hmr,jp.jobTypeDesc,jc.dealerCode,u.dealerName,u.location,jc.status  from Jobcarddetails jc,Jobtypemaster as jp, Dealervslocationcode u where  jc.vinno='" + serviceForm.getVinNo() + "' and jc.jobTypeId = jp.jobTypeID"
//                        + " and jc.dealerCode=u.dealerCode and jc.dealerCode IN(:dealerList)  order by jc.dealerCode,jc.jobCardDate");
//                ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user_id + "'");
//                query.setParameterList("dealerList", dealerList);
//            }
//            else if (userFunctionalities.contains("103"))
//            {
            query = hrbsession.createQuery("select jc.jobCardNo,jc.jobCardDate,jc.payeeName,jc.mobilePhone,jc.hMRWorking,jc.hmr,jp.jobTypeDesc,jc.dealerCode,u.dealerName,u.location,jc.status  from Jobcarddetails jc,Jobtypemaster as jp,DealervslocationcodeAll u where  jc.vinno='" + serviceForm.getVinNo() + "' and jc.jobTypeId = jp.jobTypeID  and jc.dealerCode=u.dealerCode and jc.dealerCode<>'1001013' order by jc.jobCardDate,jc.dealerCode");
            //}


            if (query != null && query.list().size() > 0) {
                itr = query.list().iterator();
                while (itr.hasNext()) {
                    serviceForm = new serviceForm();
                    Object obj[] = (Object[]) itr.next();
                    serviceForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
                    serviceForm.setJobCardDate(obj[1] == null ? "" : sdf.format(sdf1.parse(obj[1].toString())));//
                    serviceForm.setCustomerName(obj[2] == null ? "" : obj[2].toString().trim());
                    serviceForm.setMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                    serviceForm.setHmeRadio(obj[4] == null ? "" : obj[4].toString().trim());
                    serviceForm.setHmr(obj[5] == null ? "" : obj[5].toString().trim());
                    serviceForm.setJobTypeDesc(obj[6] == null ? "" : obj[6].toString().trim());
                    serviceForm.setDealercode(obj[7] == null ? "" : obj[7].toString().trim());
                    serviceForm.setDealerName(obj[8] == null ? "" : obj[8].toString().trim());
                    serviceForm.setLocationName(obj[9] == null ? "" : obj[9].toString().trim());
                    serviceForm.setStatus(obj[10] == null ? "" : obj[10].toString().trim());

                    dataList.add(serviceForm);
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String checkActualActiontaken(String jobcard)
    {
        String val = "notexist";
        Query query = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            query = hrbsession.createQuery("select count(*)  from Jobcomplaintdetails where jobCardNo='" + jobcard + "'");//from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;
            Long count1 = (Long) query.uniqueResult();

            query = hrbsession.createQuery("select count(*)  from Actualslabourcharges where jobCardNo='" + jobcard + "'");//from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;
            Long count2 = (Long) query.uniqueResult();

            if (count1 > count2) {
                val = "exist";
            }



        }
        catch (Exception ae) {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {

                    hrbsession.close();

                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    public ArrayList<serviceForm> invTransListByRefNo(String userId, serviceForm srForm, Vector userFunctionalities)
    {
        ArrayList<serviceForm> invNoList4Print = new ArrayList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        // ArrayList<String> dealerList = new ArrayList<String>();
        try {
            Query query = null;

            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);

            if (!srForm.getDealercode().equals("")) {
                Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }

            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
                    + "sm.invoiceValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
                    + " and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate)) and :dealerList LIKE ('%'+sm.dealerCode+'%')  and sm.invoicetype='JobCard'" + Subsql + " order by sm.invoiceDate desc");
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*        if (userFunctionalities.contains("101"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode, "
            + "sm.invoiceValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode and "
            + " (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate)) and sm.dealerCode=:dealerCode and sm.invoicetype='JobCard' " + Subsql + " order by sm.invoiceDate desc");
            query.setParameter("dealerCode", srForm.getDealercode());
            // query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("102"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
            + "sm.invoiceValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate)) and sm.dealerCode IN(:dealerList) and sm.invoicetype='JobCard'" + Subsql + " order by sm.invoiceDate desc");
            //  ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
            query.setParameterList("dealerList", dealerList);
            //  query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("103"))
            {
            if (!srForm.getDealercode().equals(""))
            {
            Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }
            query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,u.dealerCode,u.dealerName,u.location,u.locationCode,"
            + "sm.invoiceValue from SpInventSaleMaster sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode and sm.invoicetype='JobCard'"
            + " and (sm.invoiceDate between isnull(?,sm.invoiceDate) and isnull(?,sm.invoiceDate)) " + Subsql + " order by sm.invoiceDate desc");
            }
             */
            query.setDate(0, srForm.getFromdate().equals("") == true ? null : sdf.parse(srForm.getFromdate()));
            query.setDate(1, srForm.getTodate().equals("") == true ? null : sdf.parse(srForm.getTodate()));

            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
//                    serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setInvoiceDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                    serForm.setCustomerName(obj[2] == null ? "" : obj[2].toString().trim());
                    serForm.setMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setRefNo(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealercode(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setDealerName(obj[6] == null ? "" : obj[6].toString().trim());
                    serForm.setLocationName(obj[7] == null ? "" : obj[7].toString().trim());
                    serForm.setTotalEstimate(obj[9] == null ? "" : obj[9].toString().trim());
                    invNoList4Print.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invNoList4Print;
    }

    public ArrayList<serviceForm> counterSaleReturnList(String userId, serviceForm srForm, Vector userFunctionalities)
    {
        ArrayList<serviceForm> invNoList4Print = new ArrayList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        try {
            Query query = null;
            if (!srForm.getInvoiceno().equals("")) {
                Subsql = " and  sm.invoiceNo like '%" + srForm.getInvoiceno() + "%' ";
            }
            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);

            if ((srForm.getDealercode() != null) && !srForm.getDealercode().equals("")) {
                Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }

            query = session.createQuery("select sm.invoiceNo,sm.returnDate,sm.returnBy,sm.returnNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
                    + " from SpInventSreturn sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
                    + " and (sm.returnDate between isnull(?,sm.returnDate) and isnull(?,sm.returnDate)) and :dealerList LIKE ('%'+sm.dealerCode+'%') " + Subsql + " order by sm.returnDate desc");

            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);
            //  query.setParameter("invoiceType", srForm.getRefNo());



            /* if (userFunctionalities.contains("101"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.returnDate,sm.returnBy,sm.returnNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventSreturn sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode and "
            + " (sm.returnDate between isnull(?,sm.returnDate) and isnull(?,sm.returnDate)) and sm.dealerCode=:dealerCode  " + Subsql + " order by sm.returnDate desc");

            query.setParameter("dealerCode", srForm.getDealercode());
            // query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("102"))
            {
            query = session.createQuery("select sm.invoiceNo,sm.returnDate,sm.returnBy,sm.returnNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventSreturn sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " and (sm.returnDate between isnull(?,sm.returnDate) and isnull(?,sm.returnDate)) and sm.dealerCode IN(:dealerList) " + Subsql + " order by sm.returnDate desc");

            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
            query.setParameterList("dealerList", dealerList);
            //  query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("103"))
            {
            if (!srForm.getDealercode().equals(""))
            {
            Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }
            query = session.createQuery("select sm.invoiceNo,sm.returnDate,sm.returnBy,sm.returnNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventSreturn sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " and (sm.returnDate between isnull(?,sm.returnDate) and isnull(?,sm.returnDate)) " + Subsql + " order by sm.returnDate desc");

            }

             */
            query.setDate(0, srForm.getFromdate().equals("") == true ? null : sdf.parse(srForm.getFromdate()));
            query.setDate(1, srForm.getTodate().equals("") == true ? null : sdf.parse(srForm.getTodate()));

            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
//                    serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setInvoiceDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                    serForm.setCustomerName(obj[2] == null ? "" : obj[2].toString().trim());
                    // serForm.setMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setRefNo(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setTotalEstimate(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealercode(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setDealerName(obj[6] == null ? "" : obj[6].toString().trim());
                    serForm.setLocationName(obj[7] == null ? "" : obj[7].toString().trim());

                    invNoList4Print.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invNoList4Print;
    }

    public ArrayList<serviceForm> invOtherDealerList(String userId, serviceForm srForm, Vector userFunctionalities)
    {
        ArrayList<serviceForm> invNoList4Print = new ArrayList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        try {
            Query query = null;
            if (!srForm.getInvoiceno().equals("")) {
                Subsql = " and  sm.invoiceNo like '%" + srForm.getInvoiceno() + "%' ";
            }
            if (!srForm.getDealercode().equals("")) {
                Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }

            query = session.createQuery("select sm.inventNo,sm.billDate,sm.vendorName,sm.billNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
                    + " from SpInventOtherDealer sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
                    + " and (sm.billDate between isnull(?,sm.billDate) and isnull(?,sm.billDate)) and :dealerList LIKE ('%'+sm.dealerCode+'%') " + Subsql + " order by sm.billDate desc");

            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, userId);
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);
            //  query.setParameter("invoiceType", srForm.getRefNo());


            /* if (userFunctionalities.contains("101"))
            {
            query = session.createQuery("select sm.inventNo,sm.billDate,sm.vendorName,sm.billNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventOtherDealer sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode and "
            + " (sm.billDate between isnull(?,sm.billDate) and isnull(?,sm.billDate)) and sm.dealerCode=:dealerCode  " + Subsql + " order by sm.billDate desc");

            query.setParameter("dealerCode", srForm.getDealercode());
            // query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("102"))
            {
            query = session.createQuery("select sm.inventNo,sm.billDate,sm.vendorName,sm.billNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventOtherDealer sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " and (sm.billDate between isnull(?,sm.billDate) and isnull(?,sm.billDate)) and sm.dealerCode IN(:dealerList) " + Subsql + " order by sm.billDate desc");

            ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + userId + "'");
            query.setParameterList("dealerList", dealerList);
            //  query.setParameter("invoiceType", srForm.getRefNo());
            }
            else if (userFunctionalities.contains("103"))
            {
            if (!srForm.getDealercode().equals(""))
            {
            Subsql = " and sm.dealerCode='" + srForm.getDealercode() + "'";
            }

            query = session.createQuery("select sm.inventNo,sm.billDate,sm.vendorName,sm.billNo,sm.totalValue,u.dealerCode,u.dealerName,u.location,u.locationCode "
            + " from SpInventOtherDealer sm, Dealervslocationcode u where sm.dealerCode=u.dealerCode"
            + " and (sm.billDate between isnull(?,sm.billDate) and isnull(?,sm.billDate))  " + Subsql + " order by sm.billDate desc");

            // query.setParameter("dealerCode", srForm.getDealercode());
            }*/


            query.setDate(0, srForm.getFromdate().equals("") == true ? null : sdf.parse(srForm.getFromdate()));
            query.setDate(1, srForm.getTodate().equals("") == true ? null : sdf.parse(srForm.getTodate()));

            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
//                    serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().trim().substring(0, obj[1].toString().trim().indexOf(" ")));
                    serForm.setInvoiceDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                    serForm.setCustomerName(obj[2] == null ? "" : obj[2].toString().trim());
                    // serForm.setMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setRefNo(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setTotalEstimate(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealercode(obj[5] == null ? "" : obj[5].toString().trim());
                    serForm.setDealerName(obj[6] == null ? "" : obj[6].toString().trim());
                    serForm.setLocationName(obj[7] == null ? "" : obj[7].toString().trim());

                    invNoList4Print.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invNoList4Print;
    }

    public ArrayList<serviceForm> printConSaleReturn(serviceForm sForm)
    {
        ArrayList<serviceForm> dataList = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        DecimalFormat df = new DecimalFormat("0.00");
        serviceForm serForm = null;
        String hql = null;
        String datahql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            datahql = "select bm.returnNo, bm.invoiceNo, bm.dealerCode ,bm.returnDate,bm.returnBy,bm.totalValue  from"
                    + " SpInventSreturn bm where bm.returnNo=:returnNo";

            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("returnNo", sForm.getRefNo());
            Iterator datait = dataquery.list().iterator();
            while (datait.hasNext()) {
                Object obj[] = (Object[]) datait.next();
                sForm.setRefNo(obj[0] == null ? "" : obj[0].toString().trim());
                sForm.setInvoiceno(obj[1] == null ? "" : obj[1].toString().trim());
                sForm.setDealercode(obj[2] == null ? "" : obj[2].toString().trim());
                String returnDateString = obj[3] == null ? "" : obj[3].toString().trim();
                if (!returnDateString.equals("")) {
                    sForm.setInvoicedate(outputFormat.format(inputFormat.parse(returnDateString)));
                }
                //  System.out.println(" returnDateString  " + returnDateString);
                sForm.setCustomerName(obj[4] == null ? "" : obj[4].toString().trim());
                sForm.setTotalEstimate(obj[5] == null ? "" : obj[5].toString().trim());

            }
            dataList = new ArrayList<serviceForm>();
            hql = "select bm.spInventSreturnDetailsPK.returnNo,bm.spInventSreturnDetailsPK.partno, "
                    + "bm.partdesc,bm.invoiceQty,bm.unitvalue,bm.recdQty "
                    + " from SpInventSreturnDetails bm where bm.spInventSreturnDetailsPK.returnNo=:returnNo ";

            Query query = session.createQuery(hql);
            query.setParameter("returnNo", sForm.getRefNo());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                serForm = new serviceForm();
                serForm.setRefNo(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setPartNo_str(obj[1] == null ? "" : obj[1].toString().trim());
                serForm.setPartDesc_str(obj[2] == null ? "" : obj[2].toString().trim().toUpperCase());

                double t = Double.parseDouble(obj[3].toString().trim());
                if (t == Math.ceil(t)) {
                    serForm.setInvoiceQty("" + ((int) t));
                }
                else {
                    serForm.setInvoiceQty(obj[3] == null ? "" : obj[3].toString().trim());
                }
                serForm.setUnitprice_str(obj[4] == null ? "" : obj[4].toString().trim());
                double tt = Double.parseDouble(obj[5].toString().trim());
                if (tt == Math.ceil(tt)) {
                    serForm.setRecdQty("" + ((int) tt));
                }
                else {
                    serForm.setRecdQty(obj[5] == null ? "" : obj[5].toString().trim());
                }
                double val = tt * Double.parseDouble(obj[4].toString().trim());
                String formate = df.format(val);
                serForm.setTotalPartsValue(formate);
                dataList.add(serForm);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return dataList;
    }

    public ArrayList<serviceForm> printInvOtherDealer(serviceForm sForm)
    {
        ArrayList<serviceForm> dataList = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        DecimalFormat df = new DecimalFormat("0.00");
        serviceForm serForm = null;
        String hql = null;
        String datahql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            datahql = "select bm.inventNo, bm.billNo,bm.dealerCode,bm.billDate,bm.vendorName,bm.subTotal, "
                    + "bm.taxValue,bm.totalValue from SpInventOtherDealer bm where bm.inventNo=:inventNo";

            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("inventNo", sForm.getRefNo());
            Iterator datait = dataquery.list().iterator();
            while (datait.hasNext()) {

                Object obj[] = (Object[]) datait.next();
                sForm.setRefNo(obj[0] == null ? "" : obj[0].toString().trim());
                sForm.setBillto_str(obj[1] == null ? "" : obj[1].toString().trim());
                sForm.setDealercode(obj[2] == null ? "" : obj[2].toString().trim());
                String returnDateString = obj[3] == null ? "" : obj[3].toString().trim();
                if (!returnDateString.equals("")) {
                    sForm.setInvoicedate(outputFormat.format(inputFormat.parse(returnDateString)));
                }
                sForm.setCustomerName(obj[4] == null ? "" : obj[4].toString().trim());
                sForm.setSubTotal(obj[5] == null ? "" : obj[5].toString().trim());
                sForm.setTaxValue(obj[6] == null ? "" : obj[6].toString().trim());
                sForm.setTotalEstimate(obj[7] == null ? "" : obj[7].toString().trim());

            }
            dataList = new ArrayList<serviceForm>();
            hql = "select bm.spInventOtherDealerDetailsPK.inventNo,bm.spInventOtherDealerDetailsPK.partno, "
                    + "bm.partdesc,bm.qty,bm.unitvalue "
                    + " from SpInventOtherDealerDetails bm where bm.spInventOtherDealerDetailsPK.inventNo=:inventNo ";

            Query query = session.createQuery(hql);
            query.setParameter("inventNo", sForm.getRefNo());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                serForm = new serviceForm();
                serForm.setRefNo(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setPartNo_str(obj[1] == null ? "" : obj[1].toString().trim());
                serForm.setPartDesc_str(obj[2] == null ? "" : obj[2].toString().trim().toUpperCase());
                double t = Double.parseDouble(obj[3].toString().trim());
                if (t == Math.ceil(t)) {
                    serForm.setQty_str("" + ((int) t));
                }
                else {
                    serForm.setQty_str(obj[3] == null ? "" : obj[3].toString().trim());
                }
                serForm.setUnitprice_str(obj[4] == null ? "" : obj[4].toString().trim());

                double val = t * Double.parseDouble(obj[4].toString().trim());

                String formate = df.format(val);
                //double finalValue = (Double)df.parse(formate) ;
                serForm.setTotalPartsValue(formate);
                dataList.add(serForm);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return dataList;
    }

    /*
     * getsercviceReminder
     */
    public ArrayList<serviceForm> getsercviceReminder(serviceForm sForm, Vector userFunctionalities)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> serviceReminderList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            if ("1".equals(sForm.getRange())) {
                dateSubQur = " and (vs.dueDate between isnull(?,vs.dueDate) and isnull(?,vs.dueDate) ) ";
            }
            String hql = "select vd.vinNo,vd.modelCode,vd.modelCodeDesc,jm.jobTypeID,jm.jobTypeDesc,vs.dueDate,"
                    + " u.dealerCode,u.dealerName,u.locationCode,u.location,vd.customerName,vd.mobilePh,vs.followUpStatus,vs.lastFollowupDate,vs.scheduleID "
                    + " from SWVehicleServiceSchedule vs,Vehicledetails vd , Jobtypemaster jm ,Dealervslocationcode u"
                    + " where vs.vinNo=vd.vinNo and vs.deliveryDate=vd.deliveryDate "
                    + " and vs.jobTypeId=jm.jobTypeID and vd.dealerCode=u.dealerCode and vd.nextService='YES' "
                    + " and vs.status='PENDING' and jm.jobTypeID=isnull(?,jm.jobTypeID)  ";  //and (vs.dueDate between isnull(?,vs.dueDate) and isnull(?,vs.dueDate))

            Query query = null;

            query = session.createQuery(hql + dateSubQur + " and :dealerList LIKE ('%'+vd.dealerCode+'%') " + "  order by vs.dueDate");
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(sForm.getDealercode());
            }
            else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, sForm.getUserId());
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*           if (userFunctionalities.contains("101")) {
            hql = hql + dateSubQur + " and vd.dealerCode='" + sForm.getDealercode() + "'  order by vs.dueDate";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + dateSubQur + " and vd.dealerCode IN(:dealerList)" + "  order by vs.dueDate";
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase("ALL")) {
            dealerList.add(sForm.getDealercode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + sForm.getUserId() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);

            } else if (userFunctionalities.contains("103")) {
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase("ALL")) {
            hql = hql + dateSubQur + " and vd.dealerCode='" + sForm.getDealercode() + "'  order by vs.dueDate";
            } else {
            hql = hql + dateSubQur + "  order by vs.dueDate";
            }
            query = session.createQuery(hql);
            }
             *
             */
            query.setString(0, sForm.getJobType() == null ? null : sForm.getJobType().equals("") == true ? null : sForm.getJobType());
            if ("1".equals(sForm.getRange())) {
                query.setDate(1, sForm.getFromdate().equals("") == true ? null : sdf.parse(sForm.getFromdate()));
                query.setDate(2, sForm.getTodate().equals("") == true ? null : sdf.parse(sForm.getTodate()));
            }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                serviceForm servForm = new serviceForm();
                servForm.setVinNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                servForm.setModelCode(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                servForm.setModelCodeDesc(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                servForm.setJobType(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                servForm.setJobTypeDesc(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                servForm.setDueDate(enqobj[5] == null ? "-" : sdf.format(df.parse(enqobj[5].toString().trim())));
                servForm.setDealercode(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                servForm.setDealerName(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                servForm.setLocation(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                servForm.setLocationName(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                servForm.setCustomerName(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                servForm.setContactno(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                servForm.setFollowUpStatus(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                servForm.setLastFollowUpDate(enqobj[13] == null ? "-" : sdf.format(df.parse(enqobj[13].toString().trim())));
                servForm.setScheduleID(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                serviceReminderList.add(servForm);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return serviceReminderList;
    }

    public ArrayList<serviceForm> getServiceDoneLapse(serviceForm sForm, Vector userFunctionalities)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> dataList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        sForm.setStatus(sForm.getStatus());
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            if ("1".equals(sForm.getRange())) {
                dateSubQur = " and (vs.dueDate between isnull(?,vs.dueDate) and isnull(?,vs.dueDate))";
            }
            String hql = "select vd.vinNo,vd.modelCode,vd.modelCodeDesc,jm.jobTypeID,jm.jobTypeDesc,vs.dueDate,u.dealerCode,u.dealerName,u.locationCode,u.location,jc.jobCardNo,jc.jobCardDate,jc.hmr "
                    + " from SWVehicleServiceSchedule vs,Vehicledetails vd , Jobtypemaster jm ,Dealervslocationcode u,Jobcarddetails jc"
                    + " where vs.vinNo=vd.vinNo and vs.deliveryDate=vd.deliveryDate "
                    + " and vs.jobTypeId=jm.jobTypeID and vd.dealerCode=u.dealerCode and jc.vinno=vd.vinNo and vs.jobTypeId=jc.jobTypeId"
                    + " and vs.status=isnull(?,vs.status)  ";  //and (vs.dueDate between isnull(?,vs.dueDate) and isnull(?,vs.dueDate))

            Query query = null;

            query = session.createQuery(hql + dateSubQur + " and :dealerList LIKE ('%'+vd.dealerCode+'%') " + "  order by jm.seqNo");
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(sForm.getDealercode());
            }
            else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, sForm.getUserId());
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            /*          if (userFunctionalities.contains("101")) {
            hql = hql + dateSubQur + " and vd.dealerCode='" + sForm.getDealercode() + "'  order by jm.seqNo";
            query = session.createQuery(hql);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<String> dealerList = new ArrayList<String>();
            hql = hql + dateSubQur + " and vd.dealerCode IN(:dealerList)" + "  order by jm.seqNo";
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase(("ALL"))) {
            dealerList.add(sForm.getDealercode());
            } else {
            dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + sForm.getUserId() + "'");
            }
            query = session.createQuery(hql);
            query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
            if (sForm.getDealercode() != null && !sForm.getDealercode().equals("") && !sForm.getDealercode().equalsIgnoreCase(("ALL"))) {
            hql = hql + dateSubQur + " and vd.dealerCode='" + sForm.getDealercode() + "'  order by jm.seqNo";
            } else {
            hql = hql + dateSubQur + "  order by jm.seqNo";
            }
            query = session.createQuery(hql);
            }
             *
             */
            query.setString(0, sForm.getStatus() == null ? null : sForm.getStatus().equals("") == true ? null : sForm.getStatus());
            if ("1".equals(sForm.getRange())) {
                query.setDate(1, sForm.getFromdate().equals("") == true ? null : sdf.parse(sForm.getFromdate()));
                query.setDate(2, sForm.getTodate().equals("") == true ? null : sdf.parse(sForm.getTodate()));
            }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                serviceForm servForm = new serviceForm();
                servForm.setVinNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                servForm.setModelCode(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                servForm.setModelCodeDesc(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                servForm.setJobType(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                servForm.setJobTypeDesc(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                servForm.setDueDate(enqobj[5] == null ? "-" : sdf.format(df.parse(enqobj[5].toString().trim())));
                servForm.setDealercode(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                servForm.setDealerName(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                servForm.setLocation(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                servForm.setLocationName(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                servForm.setJobCardNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                servForm.setJobCardDate(enqobj[11] == null ? "-" : sdf.format(df.parse(enqobj[11].toString().trim())));
                servForm.setHmr(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                dataList.add(servForm);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return dataList;
    }

    /**
     * print estimatedata
     */
    public serviceForm printEstimate(serviceForm serForm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // serviceForm serForm = new serviceForm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            Query query = session.createQuery("select jd.jobCardNo,jd.jobCardDate,jd.vehicleOutDate,jd.workStartDate,"
                    + "jd.workFinishDate,jt.jobTypeDesc,st.serviceTypeDesc,jd.bayDesc,"
                    + "jd.couponNo,jd.vinno,(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =jd.inspectedBy ) as inspectedBy,"
                    + "(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =jd.mechanicName ) as mechanicName,"
                    + "jd.engineno,jd.payeeName,jd.mobilePhone,jd.village,jd.tehsil,jd.district,jd.state,jd.country,jd.pincode,"
                    + "jd.hmr,vd.customerName,vd.modelFamily,vd.mobilePh,vd.villageName,vd.tehsil,vd.district,vd.state,vd.country,vd.pincode,"
                    + "u.dealerCode,u.dealerName,lm.locationDesc,jd.createdOn,jd.iswarrantyreq,jd.ttlEstimatePartsAmt,jd.ttlEstimateLubesAmt,"
                    + "jd.ttlEstimateOtherChargesAmt,jd.ttlEstimateLabourChargesAmt,jd.ttlDiscount,jd.ttlActualAmount,jd.warrantyStatus,jd.hMRWorking,jd.itlEwStatus,jd.vorJobCard"
                    + " from Jobcarddetails jd ,Vehicledetails vd, Dealervslocationcode u, Jobtypemaster jt, Servicetypemaster st,Joblocationmaster lm "
                    + " where jd.dealerCode=u.dealerCode and jd.vinno = vd.vinNo and vd.dealerCode=jd.dealerCode and jt.jobTypeID=jd.jobTypeId "
                    + " and st.serviceTypeID=jd.serviceTypeId and jd.locationId=lm.locationID and jd.jobCardNo=:jobcardNo");
            query.setParameter("jobcardNo", serForm.getJobCardNo());

            List list = query.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {

                Object[] obj = (Object[]) itr.next();
                serForm.setJobCardNo(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setJobCardDate(obj[1] == null ? "" : obj[1].toString().equals("") == true ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                serForm.setVehicleOut(obj[2] == null ? "" : obj[2].toString().equals("") == true ? "" : sdf.format(df.parse(obj[2].toString().trim())));
                serForm.setWorkStarted(obj[3] == null ? "" : obj[3].toString().equals("") == true ? "" : sdf.format(df.parse(obj[3].toString().trim())));
                serForm.setWorkFinished(obj[4] == null ? "" : obj[4].toString().equals("") == true ? "" : sdf.format(df.parse(obj[4].toString().trim())));
                serForm.setJobTypeDesc(obj[5] == null ? "" : obj[5].toString().trim());
                serForm.setServiceType(obj[6] == null ? "" : obj[6].toString().trim());
                serForm.setBayCode(obj[7] == null ? "" : obj[7].toString().trim());
                serForm.setCouponno(obj[8] == null ? "" : obj[8].toString().trim());
                serForm.setVinNo(obj[9] == null ? "" : obj[9].toString().trim());
                serForm.setInspectionBy(obj[10] == null ? "" : obj[10].toString().trim());
                serForm.setMechanicName(obj[11] == null ? "" : obj[11].toString().trim());
                serForm.setEngineNumber(obj[12] == null ? "" : obj[12].toString().trim());
                serForm.setPayeeName(obj[13] == null ? "" : obj[13].toString().trim());
                serForm.setPayeeMobilePhone(obj[14] == null ? "" : obj[14].toString().trim());
                serForm.setPayeeVillage(obj[15] == null ? "" : obj[15].toString().trim());
                serForm.setPayeeTehsil(obj[16] == null ? "" : obj[16].toString().trim());
                serForm.setPayeeDistrict(obj[17] == null ? "" : obj[17].toString().trim());
                serForm.setPayeeState(obj[18] == null ? "" : obj[18].toString().trim());
                serForm.setPayeeCountry(obj[19] == null ? "" : obj[19].toString().trim());
                serForm.setPayeePinCode(obj[20] == null ? "" : obj[20].toString().trim());
                serForm.setHmr(obj[21] == null ? "" : obj[21].toString().trim());
                serForm.setCustomerName(obj[22] == null ? "" : obj[22].toString().trim());
                serForm.setModelFamily(obj[23] == null ? "" : obj[23].toString().trim());
                serForm.setMobilePhone(obj[24] == null ? "" : obj[24].toString().trim());
                serForm.setVillage(obj[25] == null ? "" : obj[25].toString().trim());
                serForm.setTehsil(obj[26] == null ? "" : obj[26].toString().trim());
                serForm.setDistrict(obj[27] == null ? "" : obj[27].toString().trim());
                serForm.setState(obj[28] == null ? "" : obj[28].toString().trim());
                serForm.setCountry(obj[29] == null ? "" : obj[29].toString().trim());
                serForm.setPinCode(obj[30] == null ? "" : obj[30].toString().trim());
                serForm.setDealercode(obj[31] == null ? "" : obj[31].toString().trim());
                serForm.setDealerName(obj[32] == null ? "" : obj[32].toString().trim());
                serForm.setLocationName(obj[33] == null ? "" : obj[33].toString().trim());
                serForm.setCreatedOn(obj[34] == null ? "" : obj[34].toString().equals("") == true ? "" : sdf.format(df.parse(obj[34].toString().trim())));
                serForm.setIsWarReq(obj[35] == null ? "" : obj[35].toString().trim());
                serForm.setTotalPartsValue(obj[36] == null ? "0.0" : obj[36].toString().trim());//total of parts amount
                serForm.setTotalLubesValue(obj[37] == null ? "0.0" : obj[37].toString().trim());//total of lubes amount
                serForm.setTotalOtherCharges(obj[38] == null ? "0.0" : obj[38].toString().trim());//total of other amount
                serForm.setTotalLabourCharges(obj[39] == null ? "0.0" : obj[39].toString().trim());//total of labour amount
                serForm.setTotaldiscount(obj[40] == null ? "0.0" : obj[40].toString().trim());//other discount
                serForm.setTotalEstimate(obj[41] == null ? "0.0" : obj[41].toString().trim());//total of actual amounts
                serForm.setWarrantyApplicable(obj[42] == null ? "" : obj[42].toString().trim());//total of actual amounts
                serForm.setHmeRadio(obj[43] == null ? "" : obj[43].toString().trim() + "");//total of actual amounts
                serForm.setItlExtWarrantyApplicable(obj[44] == null ? "" : obj[44].toString().trim() + "");
                
                serForm.setVorJobcard(obj[45] == null ? "" : obj[45].toString().trim() + "");
                
                // System.out.println("*"+serForm.getTotalPartsValue()+"*"+serForm.getTotalLubesValue()+"*"+serForm.getTotalOtherCharges()+"*"+serForm.getTotalLabourCharges()+"**"+serForm.getTotaldiscount());
                double ttlamount = Double.parseDouble(serForm.getTotalPartsValue()) + Double.parseDouble(serForm.getTotalLubesValue())
                        + Double.parseDouble(serForm.getTotalOtherCharges()) + Double.parseDouble(serForm.getTotalLabourCharges());
                serForm.setTotalEstimate("" + ttlamount);
            }

            Query query1 = session.createQuery("select cd.cmpNo,cd.custVerbatim ,agm.aggDesc,sgm.subAggDesc,sam.subAssemblyDesc,cm.compDesc,"
                    + " am.appDesc,cd.foremanObsv from Jobcomplaintdetails cd ,Aggregatemaster agm , Subaggregatemaster sgm, Subassemblymaster sam,"
                    + " Complaintcodemaster cm, ApplicationMaster am"
                    + " where  cd.aggCode=agm.aggCode and cd.subAggCode = sgm.subAggCode and cd.subassemblyCode= sam.subAssemblyCode and"
                    + " cm.compCode=cd.defectCode  and cd.appCode=am.appCode and"
                    + " cd.jobCardNo=:jobCardNo");
            query1.setParameter("jobCardNo", serForm.getJobCardNo());
            List l1 = query1.list();
            String[] cmpNo = new String[l1.size()];
            String[] cusVerb = new String[l1.size()];
            String[] aggDesc = new String[l1.size()];
            String[] subAssblyDesc = new String[l1.size()];
            String[] subAggDesc = new String[l1.size()];
            String[] compDesc = new String[l1.size()];

            String[] appDesc = new String[l1.size()];

            String[] formanObservation = new String[l1.size()];
            ArrayList<ArrayList<String>> action_taken = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> labourCharges = new ArrayList<ArrayList<String>>();
            Iterator itr1 = l1.iterator();
            int i = 0;
            double labourchg = 0.0;
            while (itr1.hasNext()) {
                Object[] obj = (Object[]) itr1.next();
                cmpNo[i] = obj[0] == null ? "" : obj[0].toString().trim();
                cusVerb[i] = obj[1] == null ? "" : obj[1].toString().trim();
                aggDesc[i] = obj[2] == null ? "" : obj[2].toString().trim();
                subAggDesc[i] = obj[3] == null ? "" : obj[3].toString().trim();
                subAssblyDesc[i] = obj[4] == null ? "" : obj[4].toString().trim();
                compDesc[i] = obj[5] == null ? "" : obj[5].toString().trim();
                appDesc[i] = obj[6] == null ? "" : obj[6].toString().trim();
                formanObservation[i] = obj[7] == null ? "" : obj[7].toString().trim();

                Query q1 = session.createQuery(" from Estimatelabourcharges where cmpNo=:cmpNo");
                q1.setParameter("cmpNo", cmpNo[i]);
                List l = q1.list();
                Iterator it1 = l.iterator();
                int i1 = 0;
                ArrayList<String> actiontaken_list = new ArrayList<String>();
                ArrayList<String> labourchargList = new ArrayList<String>();
                while (it1.hasNext()) {
                    Estimatelabourcharges estimatelabourcharges = (Estimatelabourcharges) it1.next();
                    actiontaken_list.add(estimatelabourcharges.getActionTaken());

                    labourchargList.add("" + (estimatelabourcharges.getLabourChargesAmount() == null ? "0.0" : estimatelabourcharges.getLabourChargesAmount()));
                    i1++;
                }
                if (i1 == 0) {
                    actiontaken_list.add("---");
                    labourchargList.add("0.0");
                }

                action_taken.add(actiontaken_list);
                labourCharges.add(labourchargList);
                i++;
            }
            if (i > 0) {
                serForm.setCmpid(cmpNo);
                serForm.setCompVerbatim(cusVerb);
                serForm.setAggCode(aggDesc);
                serForm.setSubaggCode(subAggDesc);
                serForm.setSubassembly(subAssblyDesc);
                serForm.setCompCode(compDesc);
                // serForm.setCausalCode(causeDesc);
                serForm.setApplicationCode(appDesc);
                serForm.setForemanObservation(formanObservation);
                serForm.setAction_taken(action_taken);
                serForm.setLabourCharges(labourCharges);
//                serForm.setLabourChargesAmount_str(""+labourchg);
            }

            Query query2 = session.createQuery("select pc.partNo,pc.partDesc,pc.partCategory,pc.qty,pc.unitPrice,pc.finalAmount,bm.billDesc,pc.discInPerc,pc.amount,pc.billID"
                    + " from JobcardSparesCharges pc,Billmaster bm"
                    + " where pc.billID=bm.billID and pc.jobCardNo=:jobCardNo");
            query2.setParameter("jobCardNo", serForm.getJobCardNo());
            List l2 = query2.list();
            String[] partNo = new String[l2.size()];
            String[] partDesc = new String[l2.size()];
            String[] partCat = new String[l2.size()];
            String[] qty = new String[l2.size()];
            String[] unitePrice = new String[l2.size()];
            String[] finalAmt = new String[l2.size()];
            String[] billDesc = new String[l2.size()];
            String[] discountPerc = new String[l2.size()];
            String[] discountAmt = new String[l2.size()];
            String[] billId = new String[l2.size()];
            int j = 0;
            Iterator itr2 = l2.iterator();
            //double partamt = 0.0, lubeamt = 0.0, discount_part = 0.0;
            double actualamt = 0.0;
            while (itr2.hasNext()) {
                actualamt = 0.0;
                Object[] obj = (Object[]) itr2.next();

                partNo[j] = obj[0] == null ? "" : obj[0].toString().trim();
                partDesc[j] = obj[1] == null ? "" : obj[1].toString().trim().toUpperCase();
                partCat[j] = obj[2] == null ? "" : obj[2].toString().trim();
                qty[j] = obj[3] == null ? "0" : obj[3].toString().trim();
                double t = Double.parseDouble(qty[j]);
                if (t == Math.ceil(t)) {
                    qty[j] = "" + ((int) t);
                }
                unitePrice[j] = obj[4] == null ? "0.0" : obj[4].toString().trim();
                finalAmt[j] = obj[5] == null ? "0.0" : obj[5].toString().trim();
                billDesc[j] = obj[6] == null ? "" : obj[6].toString().trim();
                discountPerc[j] = obj[7] == null ? "0.0" : obj[7].toString().trim();
                actualamt = Double.parseDouble(qty[j]) * Double.parseDouble(unitePrice[j]);//obj[8]==null?0.0:Double.parseDouble(obj[7].toString());
                discountAmt[j] = "" + ((Double.parseDouble(discountPerc[j]) / 100) * actualamt);
                billId[j] = obj[9] == null ? "" : obj[9].toString().trim();
                //  System.out.println("actual amt:"+actualamt+"dic amt:"+discountAmt[j]);

//                if(partCat[j].equalsIgnoreCase("SPARES"))
//                {
//                    partamt += Double.parseDouble(finalAmt[j]);
//                }
//                else{
//                    lubeamt +=  Double.parseDouble(finalAmt[j]);
//                }
                j++;
            }
            if (j > 0) {
                serForm.setPartNo(partNo);
                serForm.setPartDesc(partDesc);
                serForm.setComptype(partCat);
                serForm.setQuantityS(qty);
                serForm.setUnitPrice(unitePrice);
                serForm.setPartPriceAmount(finalAmt);
                serForm.setBillTo(billDesc);
//                serForm.setFinalamt_str(""+partamt);
//                serForm.setAmount_str(""+lubeamt);
                serForm.setDiscount_amt(discountAmt);
                serForm.setDiscount_perc(discountPerc);
                serForm.setBillCode(billId);
            }

            Query query3 = session.createQuery("select om.workDesc,oc.workDescription,oc.workAmount "
                    + " from Estimateothercharges oc ,Otherjobworkmaster om"
                    + " where om.workID=oc.workCode and oc.jobCardNo=:jobCardNo");
            query3.setParameter("jobCardNo", serForm.getJobCardNo());
            List l3 = query3.list();
            String[] worktype = new String[l3.size()];
            String[] workDesc = new String[l3.size()];
            String[] otherAmt = new String[l3.size()];
            int k = 0;
            Iterator itr3 = l3.iterator();
            //    double subTotal = 0.0;
            while (itr3.hasNext()) {
                Object[] obj = (Object[]) itr3.next();

                worktype[k] = obj[0] == null ? "" : obj[0].toString().trim();
                workDesc[k] = obj[1] == null ? "" : obj[1].toString().trim();
                otherAmt[k] = obj[2] == null ? "0.0" : obj[2].toString().trim();

//                subTotal +=  Double.parseDouble(otherAmt[k]);
                k++;
            }
            if (k > 0) {
                serForm.setWorkDescription(workDesc);
                serForm.setWorkCode(worktype);
                serForm.setWorkAmount(otherAmt);
//                serForm.setWorkAmount_str(""+subTotal);
            }
//            serForm.setContext(""+(labourchg+partamt+lubeamt+subTotal));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serForm;
    }

    public LinkedHashSet<LabelValueBean> getDealyReasonList()
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        TMSDelayReasonMaster mm = null;
        String name = "", id = "";
        String hql = " FROM TMSDelayReasonMaster where status='A'  order by delayReason";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                mm = (TMSDelayReasonMaster) itr.next();
                name = mm.getDelayReason() == null ? "" : mm.getDelayReason();
                id = mm.getId() == null ? "" : "" + mm.getId();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }

        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<SpOrderInvGrn> invoiceGrnList(String userId, serviceForm sForm, Vector userFunctionalities)
    {
        ArrayList<SpOrderInvGrn> dataList = new ArrayList<SpOrderInvGrn>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("0.00");
        String datahql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Calendar calendar = Calendar.getInstance();

        try {
            //calendar.setTime(sdf.parse(sForm.getTodate()));
            //calendar.add(Calendar.DATE, 1);

            datahql = "from SpOrderInvGrn a where (a.grDate between isnull(?,a.grDate) and isnull(?,a.grDate)) and a.dealerCode=:dealerCode";


            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("dealerCode", sForm.getDealercode());
            dataquery.setDate(0, sForm.getFromdate().equals("") == true ? null : sdf.parse(sForm.getFromdate()));
//            dataquery.setDate(1, sForm.getTodate().equals("") == true ? null : sdf.parse(sForm.getTodate()));
            dataquery.setDate(1, sForm.getTodate().equals("") == true ? null : sdf.parse(sForm.getTodate()));

            Iterator<SpOrderInvGrn> datait = dataquery.list().iterator();
            while (datait.hasNext()) {
                SpOrderInvGrn spOrderInvGrn = datait.next();
                spOrderInvGrn.setGrStrDate(sdf.format(spOrderInvGrn.getGrDate()));
                spOrderInvGrn.setReceivedOnStr(sdf.format(spOrderInvGrn.getReceivedOn()));
                spOrderInvGrn.setCreatedOnStr(sdf.format(spOrderInvGrn.getCreatedOn()));
                spOrderInvGrn.setInvDateStr(sdf.format(spOrderInvGrn.getInvoiceDate()));
                //spOrderInvGrn.setGrDate(sdf.parse("2015/06/12"));
                dataList.add(spOrderInvGrn);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public List<serviceForm> getInvoiceGrnDetailsList(String grnNo)
    {
        List<serviceForm> spOrderInvGrnDetails = new ArrayList<serviceForm>();
        serviceForm serviceForm = null;
        String datahql = null;
        double totalAmount = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            datahql = "from SpOrderInvGrnDetails a where a.spOrderInvGrnDetailsPK.grNo=:grNo";
            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("grNo", grnNo);
            Iterator<SpOrderInvGrnDetails> datait = dataquery.list().iterator();
            while (datait.hasNext()) {
                SpOrderInvGrnDetails spOrderInvGrnDetail = datait.next();
                serviceForm = new serviceForm();
                serviceForm.setPartNo_str(spOrderInvGrnDetail.getSpOrderInvGrnDetailsPK().getPartno());
                serviceForm.setPartDesc_str(spOrderInvGrnDetail.getPartdesc().toUpperCase());
                serviceForm.setInvoiceQty("" + spOrderInvGrnDetail.getInvoiceQty());
                serviceForm.setRecdQty("" + spOrderInvGrnDetail.getReceivedQty());
                serviceForm.setUnitprice_str("" + spOrderInvGrnDetail.getUnitvalue());
                serviceForm.setAmount_str(df.format((spOrderInvGrnDetail.getUnitvalue() * spOrderInvGrnDetail.getReceivedQty())));
                totalAmount = totalAmount + (spOrderInvGrnDetail.getUnitvalue() * spOrderInvGrnDetail.getReceivedQty());
                spOrderInvGrnDetails.add(serviceForm);
            }
            serviceForm.setTotalAmount(totalAmount);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spOrderInvGrnDetails;
    }

    public SpOrderInvGrn getInvoiceGrn(String grnNo)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SpOrderInvGrn spOrderInvGrn = null;
        try {
            spOrderInvGrn = (SpOrderInvGrn) session.get(SpOrderInvGrn.class, grnNo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spOrderInvGrn;
    }

    public ArrayList<serviceForm> invExportDealerList(String userId, serviceForm srForm, Vector userFunctionalities)
    {
        ArrayList<serviceForm> invNoList4Print = new ArrayList<serviceForm>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datahql = "";
        try {
            datahql = "select a.stkAdjNo,a.stkAdjDate,a.totalPuschaseValue,a.totalSaleValue,(select count(*) from SpStockAdjDetails where stkAdjNo=a.stkAdjNo) as noOfLines,a.dealerCode from SpStockAdjHDR a where (a.stkAdjDate between isnull(?,a.stkAdjDate) and isnull(?,a.stkAdjDate)) and a.dealerCode=:dealerCode";


            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("dealerCode", srForm.getDealercode());
            dataquery.setDate(0, srForm.getFromdate().equals("") == true ? null : sdf.parse(srForm.getFromdate()));
            dataquery.setDate(1, srForm.getTodate().equals("") == true ? null : sdf.parse(srForm.getTodate()));


            List list = dataquery.list();
            if (dataquery != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm serForm = new serviceForm();
                    serForm.setStkAdjNo(obj[0] == null ? "" : obj[0].toString().trim());
                    serForm.setStkAdjDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                    serForm.setTotalPuschaseValue(obj[2] == null ? "" : obj[2].toString().trim());
                    serForm.setTotalSaleValue(obj[3] == null ? "" : obj[3].toString().trim());
                    serForm.setNoOfLines(obj[4] == null ? "" : obj[4].toString().trim());
                    serForm.setDealercode(obj[5] == null ? "" : obj[5].toString().trim());

                    invNoList4Print.add(serForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {

                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invNoList4Print;
    }

    public ArrayList<serviceForm> printExportDetails(serviceForm sForm)
    {
        ArrayList<serviceForm> dataList = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        serviceForm serForm = null;
        String hql = null;
        String datahql = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            datahql = "select a.stkAdjNo,a.stkAdjDate,a.totalPuschaseValue,a.totalSaleValue,(select count(*) from SpStockAdjDetails where stkAdjNo=a.stkAdjNo) as noOfLines from SpStockAdjHDR a where  a.stkAdjNo=:stkAdjNo";

            Query dataquery = session.createQuery(datahql);
            dataquery.setParameter("stkAdjNo", sForm.getRefNo());

            List list = dataquery.list();

            Iterator datait = list.iterator();
            while (datait.hasNext()) {
                Object obj[] = (Object[]) datait.next();
                sForm.setStkAdjNo(obj[0] == null ? "" : obj[0].toString().trim());
                sForm.setStkAdjDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                sForm.setTotalPuschaseValue(obj[2] == null ? "" : obj[2].toString().trim());
                sForm.setTotalSaleValue(obj[3] == null ? "" : obj[3].toString().trim());
                sForm.setNoOfLines(obj[4] == null ? "" : obj[4].toString().trim());

            }
            dataList = new ArrayList<serviceForm>();
            hql = "select ssd.partNo,(select p1 from CatPart where partNo=ssd.partNo)as desc,ssd.qty,ssd.unitPrice,ssd.salePurchase,ssd.remarks from SpStockAdjDetails ssd where ssd.stkAdjNo=:stkAdjNo ";

            Query query = session.createQuery(hql);
            query.setParameter("stkAdjNo", sForm.getStkAdjNo());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                serForm = new serviceForm();
                serForm.setPartNoStr(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setPartDesc_str(obj[1] == null ? "" : obj[1].toString().trim());
                serForm.setQty_str(obj[2] == null ? "" : obj[2].toString().trim().toUpperCase());
                serForm.setUnitprice_str(obj[3] == null ? "" : obj[3].toString().trim().toUpperCase());
                serForm.setSalePurchage(obj[4] == null ? "" : obj[4].toString().trim().toUpperCase());
                serForm.setRemarks(obj[5] == null ? "" : obj[5].toString().trim().toUpperCase());

                dataList.add(serForm);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (session != null) {
                    session.close();
                }
        }
        return dataList;
    }

    public LinkedHashSet<LabelValueBean> getCommonLabelValueHiber(String entytyName, String fieldId, String fieldName, String filter, String whereCase)
    {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try {
            if (whereCase.equals("")) {
                whereCase = "where e.isActive='Y'";
            }

            Query qry = sess.createQuery("select e." + fieldId + ",e." + fieldName + " from " + entytyName + " e " + whereCase + " order by e." + filter + "");

            List<String> l = (List<String>) qry.list();

            Iterator it = l.iterator();

            while (it.hasNext()) {
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
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
               if (sess != null) {
                    sess.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<serviceForm> followUpHistoryList(String scheduleID, serviceForm sForm)
    { //String eProId
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> dataList = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            session.beginTransaction();
            dataList = new ArrayList<serviceForm>();
            String hql = "select vsf.followUpId,vsf.scheduleID,(select serviceTypeDesc from Servicetypemaster where serviceTypeID= vsf.serviceTypeID )as serviceTypeID,vsf.callDate,vsf.callDescription,vsf.doorstepServiceRequired,vsf.customerPromisedDate,vsf.nextFollowUpDate,vsf.status,vsf.createdBy,vsf.createdOn from SWVehicleServiceFollowup vsf where vsf.scheduleID=:scheduleID order by vsf.callDate DESC";
            Query query = session.createQuery(hql);
            query.setParameter("scheduleID", Double.parseDouble(scheduleID));
            List results = query.list();
            Iterator itr = results.iterator();
            while (itr.hasNext()) {
                Object obj[] = (Object[]) itr.next();
                sForm = new serviceForm();
                sForm.setFollowUpId(obj[0] == null ? "" : obj[0].toString().trim());
                sForm.setScheduleID(obj[1] == null ? "" : obj[1].toString().trim());
                sForm.setServiceType(obj[2] == null ? "" : obj[2].toString().trim());
                sForm.setCallDate(obj[3] == null ? "" : sdf1.format(df1.parse(obj[3].toString().trim())));
                sForm.setCallDescription(obj[4] == null ? "" : obj[4].toString().trim());
                sForm.setDoorstepServiceRequired(obj[5] == null ? "" : obj[5].toString().equals("Y") ? "Yes" : obj[5].toString().equals("N") ? "No" : "");
                sForm.setCustomerPromisedDate(obj[6] == null ? "" : sdf.format(df.parse(obj[6].toString().trim())));
                sForm.setNextFollowUpDate(obj[7] == null ? "" : sdf.format(df.parse(obj[7].toString().trim())));
                sForm.setStatus(obj[8] == null ? "" : obj[8].toString().trim());
                sForm.setCreatedBy(obj[9] == null ? "" : obj[9].toString().trim());
                sForm.setCreatedOn(obj[10] == null ? "" : sdf.format(df.parse(obj[10].toString().trim())));
                dataList.add(sForm);
            }
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                    session.close();
                }
        }
        return dataList;
    }

    public String addFollowUp(SWVehicleServiceFollowup ssf, serviceForm sForm)
    {
        String result = "fail";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();


            SWVehicleServiceFollowup sf = new SWVehicleServiceFollowup();
            sf.setScheduleID(Double.parseDouble(sForm.getScheduleID()));
            sf.setServiceTypeID(Integer.parseInt(sForm.getServiceType()));
//            sf.setCallDate(sdf.parse(sForm.getCallDate()));
            sf.setCallDate(new Date(new java.util.Date().getTime()));
            sf.setCallDescription(sForm.getCallDescription());
            sf.setDoorstepServiceRequired(sForm.getDoorstepServiceRequired());
            sf.setCustomerPromisedDate(sForm.getCustomerPromisedDate() == "" ? null : sdf.parse(sForm.getCustomerPromisedDate()));
            sf.setNextFollowUpDate(sForm.getNextFollowUpDate() == "" ? null : sdf.parse(sForm.getNextFollowUpDate()));
            sf.setStatus(sForm.getFollowUpStatus().toUpperCase());
            sf.setCreatedBy(ssf.getCreatedBy());
            sf.setCreatedOn(new Date(new java.util.Date().getTime()));
            session.save(sf);



            SWVehicleServiceSchedule ServiceSchedule = (SWVehicleServiceSchedule) session.get(SWVehicleServiceSchedule.class, new BigInteger(sForm.getScheduleID()));
            ServiceSchedule.setFollowUpStatus(sForm.getFollowUpStatus().toUpperCase());
            ServiceSchedule.setLastFollowupDate(sf.getCallDate());
            session.saveOrUpdate(ServiceSchedule);


            session.getTransaction().commit();
            result = "success";
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();

        }
        finally {
            if (session != null) {
                    session.close();
                }
        }
        return result;
    }

    public void getCustomerDetailById(serviceForm sf)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object[] obj = null;
        try {

            Query hQuery = session.createSQLQuery("select distinct*  from FN_GetCustomerDetail(0,0,?,'','','',?)");
            hQuery.setString(0, sf.getDealercode() == null ? "" : sf.getDealercode().toString());
            hQuery.setString(1, sf.getVinNo() == null ? "" : sf.getVinNo().toString());

            List list = hQuery.list();
            if (list != null && !list.isEmpty()) {
                Iterator itr = list.iterator();
                if (itr.hasNext()) {
                    obj = (Object[]) itr.next();
                    sf.setCustomerId(obj[0] == null ? "0" : obj[0].toString());
                    sf.setCustomerCode(obj[2] == null ? "-" : obj[2].toString());
                    sf.setCustomerName(obj[3] == null ? "-" : obj[3].toString());
                    sf.setCustomerLocation(obj[4] == null ? "-" : obj[4].toString());
                    sf.setContactMobile(obj[10] == null ? "-" : obj[10].toString());
                    sf.setCreditLimit(Double.parseDouble(obj[15] == null ? "0.0" : obj[15].toString()));
                    sf.setDiscountPercentage(Float.parseFloat(obj[13] == null ? "0.0" : obj[13].toString()));
                    sf.setAvailableCreditLimit(Double.parseDouble(obj[21] == null ? "0.0" : obj[21].toString()));
                    sf.setPaymentDue(Double.parseDouble(obj[20] == null ? "0.0" : obj[20].toString()));
                    sf.setDealerCode(obj[22] == null ? "" : obj[22].toString());
                    sf.setCustCategory(obj[23] == null ? "" : obj[23].toString());
                    sf.setTotalDiscountPercentage(Float.parseFloat(obj[24] == null ? "0.0" : obj[24].toString()));
                    sf.setGstNo(obj[36] == null ? "" : obj[36].toString());
                }
            }
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                    session.close();
                }
        }
    }

    /**
     * print Tax Invoice Data details  created on 17/03/16 by Avinash
     */
    public serviceForm printTaxInvoiceData(serviceForm serForm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            List<LabelValueBean> taxDetails = new ArrayList<LabelValueBean>();
            HashMap<String, List<LabelValueBean>> chMap = new HashMap<String, List<LabelValueBean>>();
            Query query = session.createQuery("select sm.invoiceNo,sm.invoiceDate,sm.customerName,sm.contactno,sm.typeRefno,"
                    + "sm.createdOn,u.dealerCode,u.dealerName,u.locationCode,u.location,sm.partsValue,sm.lubesValue"
                    + ",sm.otherValue,sm.invoiceValue,sm.discountOther,sm.invoicetype,sm.creditValue,"
                    + " um.customerName,um.tinNo,um.customerTehsil,um.customerDistrict,um.customerLocation"
                    + " from SpInventSaleMaster sm, Dealervslocationcode u,UmCustomerMaster um"
                    + " where sm.dealerCode=u.dealerCode and sm.customerID=um.customerID and sm.invoiceNo=:invoiceNo");
            query.setParameter("invoiceNo", serForm.getInvoiceno());

            List list = query.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                serForm.setInvoiceno(obj[0] == null ? "" : obj[0].toString().trim());
                serForm.setInvoiceDate(obj[1] == null ? "" : obj[1].toString().equals("") == true ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                serForm.setPayeeName(obj[2] == null ? "" : obj[2].toString().trim());
                serForm.setPayeeMobilePhone(obj[3] == null ? "" : obj[3].toString().trim());
                serForm.setRefNo(obj[4] == null ? "" : obj[4].toString().trim());
                serForm.setCreatedOn(obj[5] == null ? "" : obj[5].toString().equals("") == true ? "" : sdf.format(df.parse(obj[5].toString().trim())));
                serForm.setDealercode(obj[6] == null ? "" : obj[6].toString().trim());
                serForm.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());
                serForm.setLocationName(obj[9] == null ? "" : obj[9].toString().trim());
                serForm.setTotaldiscount(obj[14] == null ? "0.0" : obj[14].toString().trim());//other discount
                serForm.setCustomerName(obj[17] == null ? "-" : obj[17].toString().trim());//customerName
                serForm.setTinNo(obj[18] == null ? "-" : obj[18].toString().trim());//tin no
                serForm.setCustomerTehsil(obj[19] == null ? "-" : obj[19].toString().trim());//customerTehsil
                serForm.setCustomerDistrict(obj[20] == null ? "-" : obj[20].toString().trim());//customerDistrict
                serForm.setCustomerLocation(obj[21] == null ? "-" : obj[21].toString().trim());//customerLocation
            }
            int j = 0;
            int k = 0;
            Query query2 = session.createSQLQuery("EXEC SP_showTaxInvoice :invoiceNo");
            query2.setParameter("invoiceNo", serForm.getInvoiceno());
            Query query3 = session.createSQLQuery("EXEC SP_showTaxValuesInInvoice :invoiceNo");
            query3.setParameter("invoiceNo", serForm.getInvoiceno());
            List resultList = query2.list();
            List resultTaxList = query3.list();
            String[] part_order_seq = new String[resultList.size()];
            String[] partNo = new String[resultList.size()];
            String[] partDesc = new String[resultList.size()];
            String[] part_category = new String[resultList.size()];
            String[] commodityCode = new String[resultList.size()];
            String[] qty = new String[resultList.size()];
            String[] unitePrice = new String[resultList.size()];
            String[] discountAmt = new String[resultList.size()];
            String[] billDesc = new String[resultList.size()];
            String[] finalAmt = new String[resultList.size()];
            String[] charge_branch_id = new String[resultTaxList.size()];
            String[] chargeDesc = new String[resultTaxList.size()];
            String[] chargeAmount = new String[resultTaxList.size()];
            String[] charge_order = new String[resultTaxList.size()];
            Iterator itr2 = resultList.iterator();
            Iterator itr3 = resultTaxList.iterator();

            Object[] obj = null;
            while (itr2.hasNext()) {
                obj = (Object[]) itr2.next();
                part_order_seq[j] = obj[0] == null ? "" : obj[0].toString().trim();
                partNo[j] = obj[1] == null ? "" : obj[1].toString().trim();
                partDesc[j] = obj[2] == null ? "" : obj[2].toString().trim().toUpperCase();
                part_category[j] = obj[3] == null ? "" : obj[3].toString().trim();
                commodityCode[j] = obj[4] == null ? "" : obj[4].toString().trim();
                qty[j] = obj[5] == null ? "0" : obj[5].toString().trim();
                double t = Double.parseDouble(obj[5].toString().trim());
                if (t == Math.ceil(t)) {
                    qty[j] = "" + ((int) t);
                }
                unitePrice[j] = obj[6] == null ? "0.0" : obj[6].toString().trim();
                discountAmt[j] = obj[7] == null ? "" : obj[7].toString().trim();
                billDesc[j] = obj[8] == null ? "" : obj[8].toString().trim();
                finalAmt[j] = obj[9] == null ? "0.0" : obj[9].toString().trim();
                j++;

            }
            k = 0;
            String tempV = "";
            while (itr3.hasNext()) {
                obj = (Object[]) itr3.next();
                // commodityCode[j] = obj[0] == null ? "" : obj[0].toString().trim();
                charge_branch_id[k] = obj[1] == null ? "" : obj[1].toString().trim();
                chargeDesc[k] = obj[2] == null ? "" : obj[2].toString().trim();
                charge_order[k] = obj[3] == null ? "" : obj[3].toString().trim();
                chargeAmount[k] = obj[4] == null ? "" : obj[4].toString().trim();


                if (!obj[0].toString().equals(tempV) && tempV.length() > 0) {
                    chMap.put(tempV, taxDetails);
                    taxDetails = new ArrayList<LabelValueBean>();
                    tempV = obj[0].toString();
                }
                LabelValueBean lv = new LabelValueBean();
                lv.setLabel(obj[2].toString());
                lv.setValue(obj[4].toString());
                taxDetails.add(lv);
                tempV = obj[0].toString();
                k++;

            }
            chMap.put(tempV.toString(), taxDetails);
            if (j > 0) {

                serForm.setPart_order_seq(part_order_seq);
                serForm.setPartNo(partNo);
                serForm.setPartDesc(partDesc);
                serForm.setComptype(part_category);
                serForm.setCommodityCode(commodityCode);
                serForm.setQuantityS(qty);
                serForm.setUnitPrice(unitePrice);
                serForm.setDiscount_amt(discountAmt);
                serForm.setBillTo(billDesc);
                serForm.setPartPriceAmount(finalAmt);
                //serForm.setCommodityCode(commodityCode);
                serForm.setCharge_branch_id(charge_branch_id);
                serForm.setChargeDesc(chargeDesc);
                serForm.setCharge_order(charge_order);
                serForm.setChargeAmount(chargeAmount);
                serForm.setChMap(chMap);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serForm;
    }

    public String updateComplaintDateInJC(serviceForm sf, String userid) throws SQLException
    {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        //    String jobcardno = "";
        java.util.Date complaintDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        try {
            query = hrbsession.createQuery("SELECT  jobCardNo FROM  Jobcarddetails WHERE jobCardNo='" + sf.getJobCardNo() + "'");
            itr = query.list().iterator();
            hrbsession.beginTransaction();
            if (itr.hasNext()) {
                //   jobcardno = itr.next().toString();
                Jobcarddetails jcs = (Jobcarddetails) hrbsession.get(Jobcarddetails.class, sf.getJobCardNo());
                complaintDate = sdf.parse(sf.getComplaintDate());
                jcs.setComplaintDate(complaintDate);
                jcs.setLastModifiedBy(userid);
                jcs.setLastModifiedOn(new java.util.Date());
                hrbsession.update(jcs);
                hrbsession.getTransaction().commit();
                results = "Success@@statusSuccess";//Complaint Date has been Successfully Updated";
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();
            results = "Failure@@statusFailure";//Complaint Date has been Successfully Updated
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String saveExtWarranty(serviceForm sf, String userId) throws SQLException
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String result = "success";
        MSWDmechanicmaster mm = null;
        try {
            session.beginTransaction();
            EWMLoaderDetail ewm = new EWMLoaderDetail();

            ewm.setEwLoaderId(sf.getEwLoaderId());
            ewm.setCustomerTitle(sf.getTitle());
            ewm.setCustomerName(sf.getCustomerName());
            ewm.setAddress(sf.getCustomerLocation());
            ewm.setCity(sf.getCustomerDistrict());
            ewm.setState(sf.getCustomerState());
            ewm.setPincode(Long.parseLong(sf.getPinCode()));
            ewm.setMobile(Long.parseLong(sf.getMobileNo()));
            ewm.setTelNo(sf.getContactno());
            ewm.setEmail(sf.getEmailId());
            ewm.setExtWarrantyBookletNo(sf.getExtWarrantyBookletNo());
            ewm.setDateOfSaleOfCertificate(sdf1.parse(sf.getDateOfSaleOfCertificate()));
            ewm.setTypeName(sf.getTypeName());//
            ewm.setMakeName(sf.getMakeName());
            ewm.setModelName(sf.getModelCodeDesc());
            ewm.setFuelType(sf.getFuelType());
            ewm.setEngineNo(sf.getEngineNumber());
            ewm.setChassisNo(sf.getChassisNo());
            ewm.setVehicleRegNo(sf.getRegNo());
            ewm.setSaleDate(sdf1.parse(sf.getSaleDate()));

            ewm.setDeliveryDate(sdf1.parse(sf.getDeliveryDate()));
            ewm.setImdCode(sf.getImdCode());//
            ewm.setDealerCode(sf.getDealercode());
            ewm.setSumInsured(Long.parseLong(sf.getSumInsured()));
            ewm.setFloatType(sf.getFloatType());//
            ewm.setPpId(sf.getPpId());
            ewm.setHmr(sf.getHmrNo());
            ewm.setPolicyTermId(Integer.parseInt(sf.getPolicyType()));
            ewm.setPolicyTypeId(sf.getPolicyTypeId());
            ewm.setPremiumAmount(BigDecimal.valueOf(Double.parseDouble(sf.getCreditAmount())));
            ewm.setStatus("Pending");
            ewm.setCreatedBy(userId);
            ewm.setCreatedOn(new java.util.Date());
            ewm.setModifiedBy(null);
            ewm.setModifiedOn(null);
            if (sf.getMechanicName() != null && !sf.getMechanicName().equals(""))
                {
                    mm = (MSWDmechanicmaster) session.load(MSWDmechanicmaster.class, sf.getMechanicName());
                    ewm.setMechanicDealerKey(mm);
                }
            session.save(ewm);
            session.getTransaction().commit();
            result = "success";

        }
        catch (Exception e) {
            session.getTransaction().rollback();
            result = "failure";
            e.printStackTrace();

        }
        finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void getVehicleDetails(String vinNo, serviceForm sf)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Query query = hrbsession.createSQLQuery("select top(1) CustomerName,Tehsil, District, State,Pincode, MobilePh,"
                    + " LandlineNo, emailId, ModelFamilyDesc, ModelCodeDesc, EngineNo, vinNo, DeliveryDate "
                    + " from SW_vehicledetails where DeliveryDate is not null and vin_details_type = 'DB' and vinNo=:vinNo "
                    + " order by DeliveryDate desc ").addScalar("CustomerName", StringType.INSTANCE).addScalar("Tehsil", StringType.INSTANCE).addScalar("State", StringType.INSTANCE).addScalar("District", StringType.INSTANCE).addScalar("Pincode", StringType.INSTANCE).addScalar("MobilePh", StringType.INSTANCE).addScalar("LandlineNo", StringType.INSTANCE).addScalar("emailId", StringType.INSTANCE).addScalar("ModelFamilyDesc", StringType.INSTANCE).addScalar("ModelCodeDesc", StringType.INSTANCE).addScalar("EngineNo", StringType.INSTANCE).addScalar("vinNo", StringType.INSTANCE).addScalar("DeliveryDate", StringType.INSTANCE);

            query.setParameter("vinNo", vinNo);
            Iterator itr = query.list().iterator();
            if (itr.hasNext()) {
                Object obj[] = (Object[]) itr.next();

                sf.setCustomerName(obj[0] == null ? "" : obj[0].toString());
                sf.setCustomerLocation(obj[1] == null ? "" : obj[1].toString());
                sf.setCustomerDistrict(obj[3] == null ? "" : obj[3].toString());
                sf.setCustomerState(obj[2] == null ? "" : obj[2].toString());
                sf.setPinCode(obj[4] == null ? "" : obj[4].toString());
                sf.setMobileNo(obj[5] == null ? "" : obj[5].toString());
                sf.setContactno(obj[6] == null ? "" : obj[6].toString());
                sf.setEmailId(obj[7] == null ? "" : obj[7].toString());
                sf.setMakeName(obj[8] == null ? "" : obj[8].toString());
                sf.setModelCodeDesc(obj[9] == null ? "" : obj[9].toString());
                sf.setEngineNumber(obj[10] == null ? "" : obj[10].toString());
                sf.setChassisNo(obj[11] == null ? "" : obj[11].toString());
                sf.setSaleDate(obj[12] == null ? "" : sdf1.format(df.parse(obj[12].toString())));
                sf.setDeliveryDate(obj[12] == null ? "" : sdf1.format(df.parse(obj[12].toString())));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getPolicyType(serviceForm sf)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String id = "", desc = "";
        String hql = "select distinct POLICY_TERM_ID,POLICY_TERM_DESC from EWM_POLICY_TERM_MST where isActive='Y' order by POLICY_TERM_ID";
        try {
            LabelValueBean lv = null;
            query = hrbsession.createSQLQuery(hql).addScalar("POLICY_TERM_ID", StringType.INSTANCE).addScalar("POLICY_TERM_DESC", StringType.INSTANCE);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                id = (obj[0] == null ? "" : obj[0].toString().trim());
                desc = (obj[1] == null ? "" : obj[1].toString().trim());
                lv = new LabelValueBean(desc, id);
                result.add(lv);
            }
            sf.setPolicyTypeList(result);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getPremiumAmt(String policyType, String delDate,String dealerCode)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        Session session = HibernateUtil.getSessionFactory().openSession();
        String amt = "", policyId = "";
        try {
            Query qry = session.createSQLQuery("EXEC PROC_PolicyTermsCalculate :policyType, :delDate, :dealerCode");
            qry.setParameter("policyType", policyType);
            qry.setParameter("delDate", df.format(sdf1.parse(delDate.toString())));
            qry.setParameter("dealerCode", dealerCode);
            System.out.println("policyType  : "+policyType+"  delDate   : "+delDate+"  dealerCode :  "+dealerCode);
            List list = qry.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                amt = obj[0].toString().trim();
                policyId = obj[1].toString().trim();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return amt + "@@" + policyId;
    }

    public String isVehicleExist(String vinNo, int delDaysCount)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String vinFlag = "true";
        try {
            Query qry = session.createQuery("select vinNo from Vehicledetails where vinNo=:vinNo and vin_details_type = 'DB'");
            qry.setParameter("vinNo", vinNo);
            List result = qry.list();
            if ((result == null) || (result.size() == 0)) {
                vinFlag = "false";
            }
            else {
                vinFlag = "true";
            }
            Query qry1 = session.createQuery("select vinNo from Vehicledetails where (DATEDIFF(dd, DeliveryDate,getdate()) +1) <= :delDaysCount and vinNo=:vinNo and vin_details_type = 'DB'");
            qry1.setParameter("delDaysCount", delDaysCount);
            qry1.setParameter("vinNo", vinNo);
            List result1 = qry1.list();
            if ((result1 == null) || (result1.size() == 0)) {
                vinFlag += "@@false";
            }
            else {
                vinFlag += "@@true";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return vinFlag;
    }

    public String isChassisAlreadyExist(String vinNo)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String vinFlag = "true";
        try {
            Query qry = session.createQuery("select chassisNo from ITLEWMLoaderDetail where chassisNo=:vinNo");
            qry.setParameter("vinNo", vinNo);
            List result = qry.list();
            if ((result == null) || (result.size() == 0)) {
                vinFlag = "false";
            }
            else {
                vinFlag = "true";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return vinFlag;
    }

    public ArrayList<serviceForm> getViewExpWarrDetails(serviceForm serForm, String user_id, Vector userFunctionalities) throws SQLException
    {

        Session hsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> viewExpWarrList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Query qry = null;
        try {
            qry = hsession.createSQLQuery("exec PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo").addScalar("EW_REF_NO", StringType.INSTANCE).addScalar("DATE_OF_SALE_OF_CERTIFICATE", StringType.INSTANCE).addScalar("CHASSIS_NO", StringType.INSTANCE).addScalar("MODEL_NAME", StringType.INSTANCE).addScalar("CUSTOMER_NAME", StringType.INSTANCE).addScalar("POLICY_TERM_DESC", StringType.INSTANCE).addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DealerName", StringType.INSTANCE).addScalar("POLICY_TYPE", StringType.INSTANCE);

            qry.setParameter("chassisNo", serForm.getChassisNo());
            qry.setParameter("dealerCode", serForm.getDealercode());
            qry.setParameter("userId", user_id);
            qry.setParameter("fromDate", serForm.getFromdate().equalsIgnoreCase("") ? "" : df.format(sdf.parse(serForm.getFromdate())));
            qry.setParameter("toDate", serForm.getTodate().equalsIgnoreCase("") ? "" : df.format(sdf.parse(serForm.getTodate())));
            qry.setParameter("status", serForm.getStatus());
            qry.setParameter("ewRefNo", "");

            List list = qry.list();
            if (qry != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm sForm = new serviceForm();
                    sForm.setEwLoaderId(obj[0] == null ? "" : obj[0].toString().trim());
                    sForm.setDateOfSaleOfCertificate(obj[1] == null ? "" : sdf1.format(df.parse(obj[1].toString().trim())));
                    sForm.setChassisNo(obj[2] == null ? "" : obj[2].toString().trim());
                    sForm.setModelCodeDesc(obj[3] == null ? "" : obj[3].toString().trim());
                    sForm.setCustomerName(obj[4] == null ? "" : obj[4].toString().trim());
                    sForm.setPolicyTerm(obj[5] == null ? "" : obj[5].toString().trim());
                    sForm.setDealercode(obj[6] == null ? "" : obj[6].toString().trim());
                    sForm.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());
                    sForm.setPolicyType(obj[8] == null ? "" : obj[8].toString().trim());

                    viewExpWarrList.add(sForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hsession != null) {
                    hsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewExpWarrList;
    }

    public ArrayList<serviceForm> getExpWarrDetailsExport(serviceForm serForm, String user_id, Vector userFunctionalities) throws SQLException
    {

        Session hsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<serviceForm> viewExpWarrList = new ArrayList<serviceForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Query qry = null;
        try {
            qry = hsession.createSQLQuery("exec PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo").addScalar("EW_REF_NO", StringType.INSTANCE).addScalar("DATE_OF_SALE_OF_CERTIFICATE", StringType.INSTANCE).addScalar("CHASSIS_NO", StringType.INSTANCE).addScalar("MODEL_NAME", StringType.INSTANCE).addScalar("CUSTOMER_NAME", StringType.INSTANCE).addScalar("POLICY_TERM_DESC", StringType.INSTANCE).addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DealerName", StringType.INSTANCE).addScalar("POLICY_TYPE", StringType.INSTANCE).addScalar("ENGINE_NO", StringType.INSTANCE).addScalar("FUEL_TYPE", StringType.INSTANCE).addScalar("MAKE_NAME", StringType.INSTANCE).addScalar("VEHICLE_REG_NO", StringType.INSTANCE).addScalar("SALE_DATE", StringType.INSTANCE).addScalar("DELIVERY_DATE", StringType.INSTANCE).addScalar("HMR", StringType.INSTANCE).addScalar("CUSTOMER_TITLE", StringType.INSTANCE).addScalar("ADDRESS", StringType.INSTANCE).addScalar("CITY", StringType.INSTANCE).addScalar("STATE", StringType.INSTANCE).addScalar("PINCODE", StringType.INSTANCE).addScalar("MOBILE", StringType.INSTANCE).addScalar("TEL_NO", StringType.INSTANCE).addScalar("EMAIL", StringType.INSTANCE).addScalar("SUM_INSURED", StringType.INSTANCE).addScalar("PREMIUM_AMOUNT", StringType.INSTANCE).addScalar("BAJAJ_POLICY_NO", StringType.INSTANCE).addScalar("STATUS", StringType.INSTANCE).addScalar("EXT_WARRANTY_BOOKLET_NO", StringType.INSTANCE).addScalar("TYPE_NAME", StringType.INSTANCE).addScalar("IMD_CODE", StringType.INSTANCE).addScalar("FLOAT_TYPE", StringType.INSTANCE).addScalar("PPID", StringType.INSTANCE).addScalar("AMOUNT_TO_BAJAJ", StringType.INSTANCE).addScalar("BAJAJ_POLICY_DATE", StringType.INSTANCE).addScalar("MechanicName", StringType.INSTANCE).addScalar("MechanicDealerKey", StringType.INSTANCE);

            qry.setParameter("chassisNo", serForm.getChassisNo());
            qry.setParameter("dealerCode", serForm.getDealercode());
            qry.setParameter("userId", user_id);
            qry.setParameter("fromDate", serForm.getFromdate().equals("") ? "" : df.format(sdf.parse(serForm.getFromdate())));
            qry.setParameter("toDate", serForm.getTodate().equals("") ? "" : df.format(sdf.parse(serForm.getTodate())));
            qry.setParameter("status", serForm.getStatus());
            qry.setParameter("ewRefNo", "");

            List list = qry.list();
            if (qry != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    serviceForm sForm = new serviceForm();
                    sForm.setEwLoaderId(obj[0] == null ? "" : obj[0].toString().trim());
                    sForm.setDateOfSaleOfCertificate(obj[1] == null ? "" : sdf1.format(df.parse(obj[1].toString().trim())));
                    sForm.setChassisNo(obj[2] == null ? "" : obj[2].toString().trim());
                    sForm.setModelCodeDesc(obj[3] == null ? "" : obj[3].toString().trim());
                    sForm.setCustomerName(obj[4] == null ? "" : obj[4].toString().trim());
                    sForm.setPolicyType(obj[5] == null ? "" : obj[5].toString().trim());
                    sForm.setDealercode(obj[6] == null ? "" : obj[6].toString().trim());
                    sForm.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());

                    sForm.setPolicyTerm(obj[8] == null ? "" : obj[8].toString());
                    sForm.setEngineNumber(obj[9] == null ? "" : obj[9].toString());
                    sForm.setFuelType(obj[10] == null ? "" : obj[10].toString());
                    sForm.setMakeName(obj[11] == null ? "" : obj[11].toString());
                    sForm.setRegNo(obj[12] == null ? "" : obj[12].toString());
                    sForm.setSaleDate(obj[13] == null ? "" : sdf1.format(df.parse(obj[13].toString())));
                    sForm.setDeliveryDate(obj[14] == null ? "" : sdf1.format(df.parse(obj[14].toString())));
                    sForm.setHmrNo(obj[15] == null ? 0 : Long.parseLong(obj[15].toString()));
                    sForm.setTitle(obj[16] == null ? "" : obj[16].toString());
                    sForm.setCustomerLocation(obj[17] == null ? "" : obj[17].toString());
                    sForm.setCustomerDistrict(obj[18] == null ? "" : obj[18].toString());
                    sForm.setCustomerState(obj[19] == null ? "" : obj[19].toString());
                    sForm.setPinCode(obj[20] == null ? "" : obj[20].toString());
                    sForm.setMobileNo(obj[21] == null ? "" : obj[21].toString());
                    sForm.setContactno(obj[22] == null ? "" : obj[22].toString());
                    sForm.setEmailId(obj[23] == null ? "" : obj[23].toString());
                    sForm.setSumInsured(obj[24] == null ? "" : obj[24].toString());
                    sForm.setCreditAmount(obj[25] == null ? "" : obj[25].toString());
                    sForm.setBajajPolicyNo(obj[26] == null ? "" : obj[26].toString());
                    sForm.setStatus(obj[27] == null ? "" : obj[27].toString());
                    sForm.setExtWarrantyBookletNo(obj[28] == null ? "" : obj[28].toString());
                    sForm.setTypeName(obj[29] == null ? "" : obj[29].toString());
                    sForm.setImdCode(obj[30] == null ? "" : obj[30].toString());
                    sForm.setFloatType(obj[31] == null ? "" : obj[31].toString());
                    sForm.setPpId(obj[32] == null ? 0 : Long.parseLong(obj[32].toString()));
                    sForm.setAmtToBajaj(obj[33] == null ? 0 : Long.parseLong(obj[33].toString()));
                    sForm.setBajajPolicyDate(obj[34] == null ? "" : sdf1.format(df.parse(obj[34].toString())));
                    sForm.setMechanicName(obj[35] == null ? "" : obj[35].toString());
                    sForm.setMechanicDealerKey(obj[36] == null ? "" : obj[36].toString());

                    viewExpWarrList.add(sForm);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hsession != null) {
                    hsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewExpWarrList;
    }

    public List getExtWarrStatus()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        List<String> statusList = null;
        try {
            statusList = hrbsession.createSQLQuery("select distinct STATUS from EWM_LOADER_DETAIL").list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (hrbsession != null) {
                try {
                    if (hrbsession != null) {
                    hrbsession.close();
                }
                }
                catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        return statusList;
    }

    public void getExtWarrPopupView(serviceForm sf, String ewRefNo, String chassisNo, String user_id) throws SQLException
    {

        Session hsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Query qry = null;
        try {
        	qry = hsession.createSQLQuery("exec PROC_GetExtWarrantyView :chassisNo, :dealerCode, :userId, :fromDate, :toDate, :status, :ewRefNo").addScalar("DATE_OF_SALE_OF_CERTIFICATE", StringType.INSTANCE).addScalar("CHASSIS_NO", StringType.INSTANCE).addScalar("ENGINE_NO", StringType.INSTANCE).addScalar("FUEL_TYPE", StringType.INSTANCE).addScalar("MAKE_NAME", StringType.INSTANCE).addScalar("MODEL_NAME", StringType.INSTANCE).addScalar("VEHICLE_REG_NO", StringType.INSTANCE).addScalar("SALE_DATE", StringType.INSTANCE).addScalar("DELIVERY_DATE", StringType.INSTANCE).addScalar("HMR", StringType.INSTANCE).addScalar("CUSTOMER_TITLE", StringType.INSTANCE).addScalar("CUSTOMER_NAME", StringType.INSTANCE).addScalar("ADDRESS", StringType.INSTANCE).addScalar("CITY", StringType.INSTANCE).addScalar("STATE", StringType.INSTANCE).addScalar("PINCODE", StringType.INSTANCE).addScalar("MOBILE", StringType.INSTANCE).addScalar("TEL_NO", StringType.INSTANCE).addScalar("EMAIL", StringType.INSTANCE).addScalar("SUM_INSURED", StringType.INSTANCE).addScalar("POLICY_TERM_DESC", StringType.INSTANCE).addScalar("PREMIUM_AMOUNT", StringType.INSTANCE).addScalar("EW_REF_NO", StringType.INSTANCE).addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DealerName", StringType.INSTANCE).addScalar("BAJAJ_POLICY_NO", StringType.INSTANCE).addScalar("STATUS", StringType.INSTANCE).addScalar("Policy_term_Id", StringType.INSTANCE).addScalar("POLICY_TYPE_ID", StringType.INSTANCE).addScalar("BAJAJ_POLICY_DATE", StringType.INSTANCE).addScalar("MechanicName", StringType.INSTANCE).addScalar("MechanicDealerKey", StringType.INSTANCE).addScalar("CREATED_ON", StringType.INSTANCE);

            qry.setParameter("chassisNo", chassisNo);
            qry.setParameter("dealerCode", "");
            qry.setParameter("userId", "");
            qry.setParameter("fromDate", "");
            qry.setParameter("toDate", "");
            qry.setParameter("status", "");
            qry.setParameter("ewRefNo", ewRefNo);

            List list = qry.list();
            if (qry != null && list.size() > 0) {
                Iterator itr = list.iterator();
                if (itr.hasNext()) {
                    Object obj[] = (Object[]) itr.next();

                    sf.setDateOfSaleOfCertificate(obj[0] == null ? "" : sdf.format(df.parse(obj[0].toString())));
                    sf.setInvoiceDate(obj[0] == null ? "" : sdf1.format(df.parse(obj[0].toString())));
                    sf.setChassisNo(obj[1] == null ? "" : obj[1].toString());
                    sf.setEngineNumber(obj[2] == null ? "" : obj[2].toString());
                    sf.setFuelType(obj[3] == null ? "" : obj[3].toString());
                    sf.setMakeName(obj[4] == null ? "" : obj[4].toString());
                    sf.setModelCodeDesc(obj[5] == null ? "" : obj[5].toString());
                    sf.setRegNo(obj[6] == null ? "" : obj[6].toString());
                    sf.setSaleDate(obj[7] == null ? "" : sdf1.format(df.parse(obj[7].toString())));
                    sf.setDeliveryDate(obj[8] == null ? "" : sdf1.format(df.parse(obj[8].toString())));
                    sf.setHmrNo(obj[9] == null ? 0 : Long.parseLong(obj[9].toString()));
                    sf.setTitle(obj[10] == null ? "" : obj[10].toString());
                    sf.setCustomerName(obj[11] == null ? "" : obj[11].toString());
                    sf.setCustomerLocation(obj[12] == null ? "" : obj[12].toString());
                    sf.setCustomerDistrict(obj[13] == null ? "" : obj[13].toString());
                    sf.setCustomerState(obj[14] == null ? "" : obj[14].toString());
                    sf.setPinCode(obj[15] == null ? "" : obj[15].toString());
                    sf.setMobileNo(obj[16] == null ? "" : obj[16].toString());
                    sf.setContactno(obj[17] == null ? "" : obj[17].toString());
                    sf.setEmailId(obj[18] == null ? "" : obj[18].toString());
                    sf.setSumInsured(obj[19] == null ? "" : obj[19].toString());
                    sf.setPolicyType(obj[20] == null ? "" : obj[20].toString());
                    sf.setCreditAmount(obj[21] == null ? "" : obj[21].toString());
                    sf.setEwLoaderId(obj[22] == null ? "" : obj[22].toString());
                    sf.setDealerCode(obj[23] == null ? "" : obj[23].toString());
                    sf.setDealerName(obj[24] == null ? "" : obj[24].toString());
                    sf.setBajajPolicyNo(obj[25] == null ? "" : obj[25].toString());
                    sf.setStatus(obj[26] == null ? "" : obj[26].toString());
                    sf.setPolicyTermId(obj[27] == null ? 0 : Integer.parseInt(obj[27].toString()));
                    sf.setPolicyTypeId(obj[28] == null ? 0 : Integer.parseInt(obj[28].toString()));
                    sf.setBajajPolicyDate(obj[29] == null ? "" : sdf.format(df.parse(obj[29].toString())));
                    sf.setCallDate(obj[32] == null ? "" : sdf.format(df.parse(obj[32].toString())));
                    sf.setMechanicName(obj[30] == null ? "" : obj[30].toString());
                    sf.setMechanicDealerKey(obj[31] == null ? "" : obj[31].toString());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hsession != null) {
                    hsession.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String updateExtWarranty(serviceForm sf, String userId) throws SQLException
    {

        String results = "";
        Session hsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         MSWDmechanicmaster mm = null;
        try {
            hsession.beginTransaction();
            EWMLoaderDetail ewm = (EWMLoaderDetail) hsession.get(EWMLoaderDetail.class, sf.getEwLoaderId());

            ewm.setCustomerTitle(sf.getTitle());
            ewm.setCustomerName(sf.getCustomerName());
            ewm.setAddress(sf.getCustomerLocation());
            ewm.setCity(sf.getCustomerDistrict());
            ewm.setState(sf.getCustomerState());
            ewm.setPincode(Long.parseLong(sf.getPinCode()));
            ewm.setMobile(Long.parseLong(sf.getMobileNo()));
            ewm.setTelNo(sf.getContactno());
            ewm.setEmail(sf.getEmailId());
            ewm.setMakeName(sf.getMakeName());
            ewm.setModelName(sf.getModelCodeDesc());
            ewm.setFuelType(sf.getFuelType());
            ewm.setVehicleRegNo(sf.getRegNo());
            ewm.setSumInsured(Long.parseLong(sf.getSumInsured()));
            ewm.setHmr(sf.getHmrNo());
            ewm.setPolicyTermId(sf.getPolicyTermId());
            ewm.setPremiumAmount(BigDecimal.valueOf(Double.parseDouble(sf.getCreditAmount())));
            ewm.setStatus("Registered");
            ewm.setModifiedBy(userId);
            ewm.setModifiedOn(new java.util.Date());
            ewm.setBajajPolicyNo(sf.getBajajPolicyNo());
            ewm.setBajajPolicyDate(sdf.parse(sf.getBajajPolicyDate()));
            if (sf.getMechanicName() != null && !sf.getMechanicName().equals("")) {
                mm = (MSWDmechanicmaster) hsession.load(MSWDmechanicmaster.class, sf.getMechanicName());
                ewm.setMechanicDealerKey(mm);
            }
            hsession.update(ewm);
            hsession.getTransaction().commit();
            results = "success";

        }
        catch (Exception ae) {
            ae.printStackTrace();
            hsession.getTransaction().rollback();
            results = "failure";

        }
        finally {
            try {
                if (hsession != null) {
                    hsession.close();
                    hsession = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static int daysBetween(Date d1, Date d2)
    {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String checkExtWtyPolicyStatus(String chassisNo, String tractorindate) throws Exception
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        Query query = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            hrbsession.beginTransaction();
            query = hrbsession.createSQLQuery("exec PROC_ExtWtyBajajPolicyCheck :chassisNo,:tractorindate");
            query.setParameter("chassisNo", chassisNo);
            query.setParameter("tractorindate", df.format(sdf1.parse(tractorindate.toString())));
            result = query.list().get(0).toString().trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            result=e.getMessage();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getPartNoCheckBEWJobType(String partNo, String cat)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        String sql = null;
        String tempNo = "";
        try {
            if (cat != null && !cat.equals("LUBES")) {
                sql = "Select distinct partNo,p1 from CatPart where partNo='" + partNo.trim() + "' and partType<>'LUBES'  and np10='Y' order by partNo";
            }
            else {
                sql = "Select distinct partNo,p1 from CatPart where partNo='" + partNo.trim() + "' and partType='" + cat + "'  and np10='Y' order by partNo";
            }
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempNo = (String) o[0];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempNo;

    }
    public String getJobEligibiltyForTSN(String chassis, String hmr,String jobType) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        String tempValue = null;
        String tempDesc = null;
        try {
            Query qry = session.createSQLQuery("EXEC CK_JOB_ELIGIBILIY_FOR_TSN :tsn, :hmr, :jobType");
            qry.setParameter("tsn", chassis);
            qry.setParameter("hmr", hmr);
            qry.setParameter("jobType", jobType);
            System.out.println("hmr  : " + hmr + "  chassis   : " + chassis  +" jobType : "+jobType);
            List list = qry.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                tempValue = obj[0].toString().trim();
                tempDesc = obj[1].toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tempValue + "@@" + tempDesc;
    }
    
    public String checkComplaintStatus(String jobCardNo)
    {
        String val = "notexist";
        Query query = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

        	query = session.createSQLQuery("exec SP_JobCardComplaintMandatoryCheck :jobCardNo");
            query.setParameter("jobCardNo", jobCardNo);
            
            String result = query.list().get(0).toString().trim();
           
            if (result.equals("1")) {
                val = "exist";
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	if (session != null) {
                session.close();
            }
        }
        return val;
    }
    
    public void getStates(serviceForm sf) {
    	LinkedHashSet<LabelValueBean> stateList = new LinkedHashSet<LabelValueBean>();
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select state_id, StateDesc from CM_STATE");

            List<Object[]> resultList = query.list();
            for (Object[] row : resultList) {
                String stateCode = String.valueOf(row[0]);
                String stateDesc = String.valueOf(row[1]);
                stateList.add(new LabelValueBean(stateCode, stateDesc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        sf.setStates(stateList);
    }
    
    public LinkedHashSet<LabelValueBean> getDistricts(int stateId) {
        LinkedHashSet<LabelValueBean> districtList = new LinkedHashSet<LabelValueBean>();
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select district_id, DistrictDesc from cm_dist where state_id = :stateId");
            query.setParameter("stateId", stateId); // Correctly set the parameter
            List<Object[]> resultList = query.list();
            for (Object[] row : resultList) {
                String districtCode = row[0].toString(); // Assuming district_id is a string
                String districtDesc = row[1].toString();
                districtList.add(new LabelValueBean(districtCode, districtDesc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return districtList;
    }

    
    public LinkedHashSet<LabelValueBean> getCities(int district) {
    	LinkedHashSet<LabelValueBean> cityList = new LinkedHashSet<LabelValueBean>();
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select  city_id,CityDesc from CM_CITY where  district_id=:districtId");
            query.setParameter("districtId", district);
            List<Object[]> resultList = query.list();
            for (Object[] row : resultList) {
                String cityId = row[0].toString();
                String cityDesc = (String) row[1];
                cityList.add(new LabelValueBean(cityId, cityDesc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cityList;
    }
    
    public LinkedHashSet<LabelValueBean> getTehsil(int cityId) {
    	LinkedHashSet<LabelValueBean> tehsilList = new LinkedHashSet<LabelValueBean>();
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("select  tehsil_id,TehsilDesc from CM_TEHSIL where  city_id=:cityId");
            query.setParameter("cityId", cityId);
            List<Object[]> resultList = query.list();
            for (Object[] row : resultList) {
                String tehsilCode = row[0].toString();
                String tehsilDesc = (String) row[1];
                tehsilList.add(new LabelValueBean(tehsilCode, tehsilDesc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tehsilList;
    }

	public void getExtendedWarrantyDetails(String vinNo, serviceForm sf) {
		
		 Session hrbsession = HibernateUtil.getSessionFactory().openSession();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Query query = hrbsession.createSQLQuery("select top 1 CHASSIS_NO,ENGINE_NO,PRODCUT_CATEGORY,ITL_EXT_REG_DATE,HMR,EW_TYPE,"
	            		+ "CUSTOMER_NAME,VILLAGE,TEHSIL,DISTRICT,STATE,COUNTRY,PINCODE,MOBILE,LANDLINE,EMAIL,OF_LAND_HOLDING,MAIN_CROPS,"
	            		+ "OCCUPATION,FUEL_TYPE,SALE_DATE,ModelFamilyDesc"
	            		+ " from ITL_EWM_STG where CHASSIS_NO=:vinNo");

	            query.setParameter("vinNo", vinNo);
	            Iterator itr = query.list().iterator();
	            if (itr.hasNext()) {
	                Object obj[] = (Object[]) itr.next();
	                
	                sf.setChassisNo(obj[0] == null ? "" : obj[0].toString());
	                sf.setEngineNumber(obj[1] == null ? "" : obj[1].toString());
	                sf.setProductionCategory_Desc(obj[2] == null ? "" : obj[2].toString());
	                sf.setItlExtRegDate(obj[3] == null ? "" : sdf1.format(df.parse(obj[3].toString())));
	                sf.setHmrNo(obj[4] == null ? 0L : (obj[4] == null ? 0L : ((BigDecimal) obj[4]).longValue()));
	                sf.setEwType(obj[5] == null ? "" : obj[5].toString());
	                sf.setCustomerName(obj[6] == null ? "" : obj[6].toString());
	                sf.setVillage(obj[7] == null ? "" : obj[7].toString());
	                sf.setTehsil(obj[8] == null ? "" : obj[8].toString());
	                sf.setDistrict(obj[9] == null ? "" : obj[9].toString());
	                sf.setState(obj[10] == null ? "" : obj[10].toString());
	                sf.setCountry(obj[11] == null ? "" : obj[11].toString());
	                sf.setPincode(obj[12] == null ? "" : obj[12].toString());
	                sf.setMobileNo(obj[13] == null ? "" : obj[13].toString());
	                sf.setLandline(obj[14] == null ? "" : obj[14].toString());
	                sf.setEmailId(obj[15] == null ? "" : obj[15].toString());
	                sf.setSizeoflandhold(obj[16] == null ? "" : obj[16].toString());
	                sf.setMaincrops(obj[17] == null ? "" : obj[17].toString());
	                sf.setOccupation(obj[18] == null ? "" : obj[18].toString());
	                sf.setFuelType(obj[19] == null ? "" : obj[19].toString());
	                sf.setSaleDate(obj[20] == null ? "" : sdf1.format(df.parse(obj[20].toString())));
	                sf.setModelFamilyDesc(obj[21] == null ? "" : obj[21].toString());
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if (hrbsession != null) {
	                    hrbsession.close();
	                    hrbsession = null;
	                }
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		
	}

	public String saveItlExtWarranty(serviceForm sf, String userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
		String result = "success";
		MSWDmechanicmaster mm = null;
		try {

			Query query = session.createSQLQuery(
					"select  makeName,modelFamily,modelFamilyDesc,modelCode,modelCodeDesc,delivery_date,Service_Record_Engine,Gear_Oil_Change_Transmission,"
					+ "Engine,Clutch,Transmission,Brakes,Hydraulic,TPL,CheckExternal_Crack_Damage,Status,ITL_EmployeeId,FirstName from ITL_EWM_STG"
					+ " where chassis_no=:vinNo");
			query.setParameter("vinNo", sf.getChassisNo());
			List<Object[]> resultList = query.list();

			if (!resultList.isEmpty()) {
				Object[] row = resultList.get(0); // Assuming there is only one record per vinNo

				// Set values in sf object
				if (row[0] != null)
					sf.setMakeName((String) row[0]);
				if (row[1] != null)
					sf.setModelFamily((String) row[1]);
				if (row[2] != null)
					sf.setModelFamilyDesc((String) row[2]);
				if (row[3] != null)
					sf.setModelCode((String) row[3]);
				if (row[4] != null)
					sf.setModelCodeDesc((String) row[4]);
				if (row[5] != null)
					sf.setDeliveryDate(sdf.format((Date) row[5]));
				if (row[6] != null)
					sf.setServiceRecordEngine((String) row[6]);
				if (row[7] != null)
					sf.setGearOilChangeTransmission((String)row[7]);
				if (row[8] != null)
					sf.setEngine((String) row[8]);
				if (row[9] != null)
					sf.setClutch((String) row[9]);
				if (row[10] != null)
					sf.setTransmission((String) row[10]);
				if (row[11] != null)
					sf.setBrakes((String) row[11]);
				if (row[12] != null)
					sf.setHydraulic((String) row[12]);
				if (row[13] != null)
					sf.setTpl((String) row[13]);
				if (row[14] != null)
					sf.setCheckExternalCrackDamage((String) row[14]);
				if (row[15] != null)
					sf.setItlstatus((String)  row[15]);
				if (row[16] != null)
					sf.setItlEmployeeId((String)  row[16]);
				if (row[17] != null)
					sf.setFirstName((String)  row[17]);
			}

			session.beginTransaction();
			ITLEWMLoaderDetail ewm = new ITLEWMLoaderDetail();

			ewm.setItlewRefNo(Optional.ofNullable(sf.getEwLoaderId()).orElse(""));
			ewm.setDealerCode(Optional.ofNullable(sf.getDealercode()).orElse(""));
			ewm.setChassisNo(Optional.ofNullable(sf.getChassisNo()).orElse(""));
			
			Date deliveryDate = sdf.parse(sf.getDeliveryDate());
			ewm.setDeliveryDate(deliveryDate);
			
			ewm.setEngineNo(Optional.ofNullable(sf.getEngineNumber()).orElse(""));
			
			Date itlExtRegDate = sdf1.parse(sf.getItlExtRegDate());
			ewm.setItlExtRegDate(itlExtRegDate);
			ewm.setItlExtRegStatus("PENDING");
			ewm.setProductCategory(Optional.ofNullable(sf.getProductionCategory_Desc()).orElse(""));
			ewm.setModelFamilyDesc(Optional.ofNullable(sf.getModelFamilyDesc()).orElse(""));
			ewm.setModelFamily(Optional.ofNullable(sf.getModelFamilyDesc()).orElse(""));
			ewm.setModelCode(Optional.ofNullable(sf.getModelFamilyDesc()).orElse(""));
			ewm.setModelCodeDesc(Optional.ofNullable(sf.getModelFamilyDesc()).orElse(""));
			ewm.setMakeName(Optional.ofNullable(sf.getMakeName()).orElse(""));
			ewm.setFuelType(Optional.ofNullable(sf.getFuelType()).orElse(""));
			ewm.setTractorRegNo(Optional.ofNullable(sf.getRegNo()).orElse(""));
			ewm.setHmr(Optional.ofNullable(sf.getHmrNo()).orElse(0L));
			ewm.setEwType(Optional.ofNullable(sf.getEwType()).orElse(""));
			ewm.setEwRegistrationAmount(sf.getEwRegistrationAmount() != null
					? BigDecimal.valueOf(Double.parseDouble(sf.getEwRegistrationAmount()))
					: BigDecimal.ZERO);
			ewm.setCustomerName(Optional.ofNullable(sf.getCustomerName()).orElse(""));
			ewm.setState(Optional.ofNullable(sf.getState()).orElse(""));
			ewm.setDistrict(Optional.ofNullable(sf.getDistrict()).orElse(""));
			ewm.setTehsil(Optional.ofNullable(sf.getTehsil()).orElse(""));
			ewm.setVillage(Optional.ofNullable(sf.getVillage()).orElse(""));
			ewm.setPincode(Optional.ofNullable(sf.getPincode()).map(Long::parseLong).orElse(0L));

			String address = String.format("%s, %s, %s, %s - %s", Optional.ofNullable(sf.getVillage()).orElse(""),
					Optional.ofNullable(sf.getTehsil()).orElse(""), Optional.ofNullable(sf.getDistrict()).orElse(""),
					Optional.ofNullable(sf.getState()).orElse(""), Optional.ofNullable(sf.getPincode()).orElse(""));
			ewm.setAddress(address);

			ewm.setMobile(Optional.ofNullable(sf.getMobileNo()).map(Long::parseLong).orElse(0L));
			ewm.setLandline(Optional.ofNullable(sf.getLandline()).orElse(""));
			ewm.setEmail(Optional.ofNullable(sf.getEmailId()).orElse(""));
			ewm.setOfLandHolding(Optional.ofNullable(sf.getSizeoflandhold()).orElse(""));
			ewm.setMainCrops(Optional.ofNullable(sf.getMaincrops()).orElse(""));
			ewm.setOccupation(Optional.ofNullable(sf.getOccupation()).orElse(""));
			ewm.setPayeeName(Optional.ofNullable(sf.getPayeeName()).orElse(""));
			ewm.setPayeeState(Optional.ofNullable(sf.getPayeeState()).orElse(""));
			ewm.setPayeeDistrict(Optional.ofNullable(sf.getPayeeDistrict()).orElse(""));
			ewm.setPayeeCity(Optional.ofNullable(sf.getPayeeCity()).orElse(""));
			ewm.setPayeeTehsil(Optional.ofNullable(sf.getTehsil()).orElse(""));
			ewm.setPayeeVillage(Optional.ofNullable(sf.getPayeeVillage()).orElse(""));
			ewm.setPayeePincode(Optional.ofNullable(sf.getPayeePinCode()).map(Long::parseLong).orElse(0L));

			address = String.format("%s, %s, %s, %s - %s", Optional.ofNullable(sf.getPayeeVillage()).orElse(""),
					Optional.ofNullable(sf.getPayeeTehsil()).orElse(""),
					Optional.ofNullable(sf.getPayeeDistrict()).orElse(""),
					Optional.ofNullable(sf.getPayeeState()).orElse(""),
					Optional.ofNullable(sf.getPayeePinCode()).orElse(""));
			ewm.setPayeeAddress(address);

			ewm.setPayeeMobile(Optional.ofNullable(sf.getPayeeMobilePhone()).map(Long::parseLong).orElse(0L));
			ewm.setCreatedBy(sf.getDealercode());
			ewm.setCreatedOn(new Date());
			ewm.setModifiedBy(sf.getDealercode());
			ewm.setModifiedOn(new Date());
			String invoiceNo = new MethodUtility().getInvoiceNumberEW(session, "ITLEWMLoaderDetail", sf.getDealercode(), "ITLEWINV");
			ewm.setInvoiceNo(invoiceNo);
			ewm.setInvoiceDate(new Date());
			ewm.setServiceRecordEngine(sf.getServiceRecordEngine());
			ewm.setGearOilChangeTransmission(sf.getGearOilChangeTransmission());
			ewm.setEngine(sf.getEngine());
			ewm.setClutch(sf.getClutch());
			ewm.setTransmission(sf.getTransmission());
			ewm.setBrakes(sf.getBrakes());
			ewm.setHydraulic(sf.getHydraulic());
			ewm.setTpl(sf.getTpl());
			ewm.setCheckExternalCrackDamage(sf.getCheckExternalCrackDamage());
			ewm.setStatus(sf.getItlstatus());
			ewm.setItlEmployeeId(sf.getItlEmployeeId());
			ewm.setFirstName(sf.getFirstName());
			
			session.save(ewm);
			session.getTransaction().commit();
			result = "success";

		} catch (Exception e) {
			session.getTransaction().rollback();
			result = "failure";
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
		return result;
	}
	
	public String isChassisExistInItlExtWty(String vinNo) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		String vinFlag = "true";
		try {
			Query qry = session.createSQLQuery("select CHASSIS_NO from  ITL_EWM_STG where CHASSIS_NO=:vinNo");
			qry.setParameter("vinNo", vinNo);
			List result = qry.list();
			if ((result == null) || (result.size() == 0)) {
				vinFlag = "false";
			} else {
				vinFlag = "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return vinFlag;
	}
	
	 public ArrayList<serviceForm> getViewExpItlExtWarrDetails(serviceForm serForm, String user_id, Vector userFunctionalities) throws SQLException
	    {

	        Session hsession = HibernateUtil.getSessionFactory().openSession();
	        ArrayList<serviceForm> viewExpWarrList = new ArrayList<serviceForm>();
	        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Input format
	        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyy"); // Desired output format
	        String fromDateFormatted = "";
	        String toDateFormatted ="";
	        try {
				 fromDateFormatted = serForm.getFromdate().isEmpty() ? "" : outputFormat.format(inputFormat.parse(serForm.getFromdate()));
				 toDateFormatted = serForm.getTodate().isEmpty() ? "" : outputFormat.format(inputFormat.parse(serForm.getTodate()));

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        


	        Query qry = null;
	        try {
	            qry = hsession.createSQLQuery("exec PROC_GetItlExtWarrantyView :chassisNo, :dealerCode, :fromDate, :toDate, :status, :ewRefNo,:user_id").addScalar("ITLEW_REF_NO", StringType.INSTANCE).addScalar("ITL_EXT_REG_DATE", StringType.INSTANCE).addScalar("CHASSIS_NO", StringType.INSTANCE).addScalar("ModelFamilyDesc", StringType.INSTANCE).addScalar("CUSTOMER_NAME", StringType.INSTANCE).addScalar("ITL_EXT_REG_STATUS", StringType.INSTANCE).addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DEALER_NAME", StringType.INSTANCE).addScalar("ITL_EW_CERTIFICATE_NAME", StringType.INSTANCE).addScalar("ITL_EW_CERTIFICATE_NAME", StringType.INSTANCE).addScalar("ITL_EW_DEBIT_INVOICE", StringType.INSTANCE);

	            qry.setParameter("chassisNo", serForm.getChassisNo());
	            qry.setParameter("dealerCode", serForm.getDealercode());
	            qry.setParameter("fromDate", fromDateFormatted);
	            qry.setParameter("toDate", toDateFormatted);
	            qry.setParameter("status", serForm.getStatus());
	            qry.setParameter("ewRefNo", "");
	            qry.setParameter("user_id", user_id);

	            List list = qry.list();
	            if (qry != null && list.size() > 0) {
	                Iterator itr = list.iterator();
	                while (itr.hasNext()) {
	                    Object[] obj = (Object[]) itr.next();
	                    serviceForm sForm = new serviceForm();
	                    sForm.setEwLoaderId(obj[0] == null ? "" : obj[0].toString().trim());
	                    sForm.setDateOfSaleOfCertificate(obj[1] == null ? "" : obj[1].toString().trim());
	                    sForm.setChassisNo(obj[2] == null ? "" : obj[2].toString().trim());
	                    sForm.setModelCodeDesc(obj[3] == null ? "" : obj[3].toString().trim());
	                    sForm.setCustomerName(obj[4] == null ? "" : obj[4].toString().trim());
	                    sForm.setStatus(obj[5] == null ? "" : obj[5].toString().trim());
	                    sForm.setDealerCode(obj[6] == null ? "" : obj[6].toString().trim());
	                    sForm.setDealerName(obj[7] == null ? "" : obj[7].toString().trim());
	                    sForm.setGstInvoiceDocName(obj[8] == null ? "" : obj[8].toString().trim());
	                    sForm.setItlEwCertificateName(obj[9] == null ? "" : obj[9].toString().trim());
	                    sForm.setItlEwDebitInvoice(obj[10] == null ? "" : obj[10].toString().trim());
	                    viewExpWarrList.add(sForm);
	                }
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if (hsession != null) {
	                    hsession.close();
	                }
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return viewExpWarrList;
	    }
	 
	 public void getItlExtWarrPopupView(serviceForm sf, String ewRefNo, String chassisNo, String user_id) throws SQLException
	    {

	        Session hsession = HibernateUtil.getSessionFactory().openSession();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Query qry = null;
	        try {
	            qry = hsession.createSQLQuery("exec PROC_GetItlExtWarrantyView :chassisNo, :dealerCode, :fromDate, :toDate, :status, :ewRefNo").addScalar("ITLEW_REF_NO", StringType.INSTANCE).addScalar("DEALER_CODE", StringType.INSTANCE).addScalar("DEALER_NAME", StringType.INSTANCE).addScalar("ITL_EXT_REG_DATE", StringType.INSTANCE).addScalar("CHASSIS_NO", StringType.INSTANCE).addScalar("ENGINE_NO", StringType.INSTANCE).addScalar("FUEL_TYPE", StringType.INSTANCE).addScalar("MakeName", StringType.INSTANCE).addScalar("ModelFamilyDesc", StringType.INSTANCE).addScalar("TRACTOR_REG_NO", StringType.INSTANCE).addScalar("DELIVERY_DATE", StringType.INSTANCE).addScalar("HMR", StringType.INSTANCE).addScalar("CUSTOMER_NAME", StringType.INSTANCE).addScalar("ADDRESS", StringType.INSTANCE).addScalar("DISTRICT", StringType.INSTANCE).addScalar("STATE", StringType.INSTANCE).addScalar("PINCODE", StringType.INSTANCE).addScalar("MOBILE", StringType.INSTANCE).addScalar("LANDLINE", StringType.INSTANCE).addScalar("EMAIL", StringType.INSTANCE).addScalar("OF_LAND_HOLDING", StringType.INSTANCE).addScalar("MAIN_CROPS", StringType.INSTANCE).addScalar("OCCUPATION", StringType.INSTANCE).addScalar("PAYEE_NAME", StringType.INSTANCE).addScalar("PAYEE_ADDRESS", StringType.INSTANCE).addScalar("PAYEE_STATE", StringType.INSTANCE).addScalar("PAYEE_DISTRICT", StringType.INSTANCE).addScalar("PAYEE_TEHSIL", StringType.INSTANCE).addScalar("PAYEE_CITY", StringType.INSTANCE).addScalar("PAYEE_VILLAGE", StringType.INSTANCE).addScalar("PAYEE_PINCODE", StringType.INSTANCE).addScalar("PAYEE_MOBILE", StringType.INSTANCE).addScalar("GST_INVOICE_DOC_NAME", StringType.INSTANCE).addScalar("ITL_EW_CERTIFICATE_NAME", StringType.INSTANCE).addScalar("ITL_EW_DEBIT_INVOICE", StringType.INSTANCE).addScalar("EW_TYPE", StringType.INSTANCE).addScalar("EW_REGISTRATION_AMOUNT", StringType.INSTANCE).addScalar("ITL_POLICY_NO", StringType.INSTANCE).addScalar("ITL_POLICY_DATE", StringType.INSTANCE).addScalar("DATE_OF_SALE_OF_CERTIFICATE", StringType.INSTANCE).addScalar("ITL_EXT_REG_STATUS", StringType.INSTANCE).addScalar("VILLAGE", StringType.INSTANCE).addScalar("TEHSIL", StringType.INSTANCE);

	            qry.setParameter("chassisNo", chassisNo);
	            qry.setParameter("dealerCode", "");
	            qry.setParameter("fromDate", "");
	            qry.setParameter("toDate", "");
	            qry.setParameter("status", "");
	            qry.setParameter("ewRefNo", ewRefNo);
	      
	            List list = qry.list();
	            if (qry != null && list.size() > 0) {
	                Iterator itr = list.iterator();
	                if (itr.hasNext()) {
	                    Object obj[] = (Object[]) itr.next();

	                    sf.setEwLoaderId(obj[0] == null ? "" : obj[0].toString());
	                    sf.setDealerCode(obj[1] == null ? "" : obj[1].toString());
	                    sf.setDealerName(obj[2] == null ? "" : obj[2].toString());
	                    sf.setItlExtRegDate(obj[3] == null ? "" : obj[3].toString());
	                    sf.setChassisNo(obj[4] == null ? "" : obj[4].toString());
	                    sf.setEngineNumber(obj[5] == null ? "" : obj[5].toString());
	                    sf.setFuelType(obj[6] == null ? "" : obj[6].toString());
	                    sf.setMakeName(obj[7] == null ? "" : obj[7].toString());
	                    sf.setModelCodeDesc(obj[8] == null ? "" : obj[8].toString());
	                    sf.setRegNo(obj[9] == null ? "" : obj[9].toString());
	                    sf.setDeliveryDate(obj[10] == null ? "" : obj[10].toString());
	                    sf.setHmrNo((obj[11] == null || obj[11].toString().trim().isEmpty()) ? 0L : Long.parseLong(obj[11].toString().trim()));
	                    sf.setCustomerName(obj[12] == null ? "" : obj[12].toString());
	                    sf.setCustomerAddress(obj[13] == null ? "" : obj[13].toString());
	                    sf.setDistrict(obj[14] == null ? "" : obj[14].toString());
	                    sf.setState(obj[15] == null ? "" : obj[15].toString());
	                    sf.setPinCode(obj[16] == null ? "" : obj[16].toString());
	                    sf.setMobileNo(obj[17] == null ? "" : obj[17].toString());
	                    sf.setLandline(obj[18] == null ? "" : obj[18].toString());
	                    sf.setEmailId(obj[19] == null ? "" : obj[19].toString());
	                    sf.setSizeoflandhold(obj[20] == null ? "" : obj[20].toString());
	                    sf.setMaincrops(obj[21] == null ? "" : obj[21].toString());
	                    sf.setOccupation(obj[22] == null ? "" : obj[22].toString());
	                    sf.setPayeeName(obj[23] == null ? "" : obj[23].toString());
	                    sf.setPayeeAddress(obj[24] == null ? "" : obj[24].toString());
	                    sf.setPayeeState(obj[25] == null ? "" : obj[25].toString());
	                    sf.setPayeeDistrict(obj[26] == null ? "" : obj[26].toString());
	                    sf.setPayeeTehsil(obj[27] == null ? "" : obj[27].toString());
	                    sf.setPayeeCity(obj[28] == null ? "" : obj[28].toString());
	                    sf.setPayeeVillage(obj[29] == null ? "" : obj[29].toString());
	                    sf.setPayeePinCode(obj[30] == null ? "" : obj[30].toString());
	                    sf.setPayeeMobilePhone(obj[31] == null ? "" : obj[31].toString());
	                    sf.setGstInvoiceDocName(obj[32] == null ? "" : obj[32].toString());
	                    sf.setItlEwCertificateName(obj[33] == null ? "" : obj[33].toString());
	                    sf.setItlEwDebitInvoice(obj[34] == null ? "" : obj[34].toString());
	                    sf.setEwType(obj[35] == null ? "" : obj[35].toString());
	                    sf.setEwRegistrationAmount(obj[36] == null ? "" : obj[36].toString());
	                    sf.setItlPolicyNo(obj[37] == null ? "" : obj[37].toString());
	                    sf.setItlPolicyDate(obj[38] == null ? "" : obj[38].toString());
	                    sf.setStatus(obj[39] == null ? "" : obj[39].toString());
	                    sf.setVillage(obj[40] == null ? "" : obj[40].toString());
	                    sf.setTehsil(obj[41] == null ? "" : obj[41].toString());
	                 
	                }
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if (hsession != null) {
	                    hsession.close();
	                }
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 public String updateItlExtWarranty(serviceForm sf, String userId) throws SQLException
	    {

	        String results = "";
	        Session hsession = HibernateUtil.getSessionFactory().openSession();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	       
	        try {
	            hsession.beginTransaction();
	            ITLEWMLoaderDetail ewm = (ITLEWMLoaderDetail) hsession.get(ITLEWMLoaderDetail.class, sf.getEwLoaderId());

	           	ewm.setItlExtRegStatus("Registered");
	            ewm.setModifiedBy(userId);
	            ewm.setModifiedOn(new java.util.Date());
	            ewm.setItlPolicyNo(sf.getItlPolicyNo());
	            String itlPolicyDateStr = sf.getItlPolicyDate();
                Date parsedDate = sdf.parse(itlPolicyDateStr);
	            String formattedDate = df.format(parsedDate);
	            ewm.setItlPolicyDate(df.parse(formattedDate));
	        
	            hsession.update(ewm);
	            hsession.getTransaction().commit();
	            results = "success";

	        }
	        catch (Exception ae) {
	            ae.printStackTrace();
	            hsession.getTransaction().rollback();
	            results = "failure";

	        }
	        finally {
	            try {
	                if (hsession != null) {
	                    hsession.close();
	                    hsession = null;
	                }
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return results;
	    }
	 
	 public String checkITLExtWtyPolicyStatus(String chassisNo, String deliveryDate,String hmr) throws Exception
	    {
	        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
	        String result = "";
	        Query query = null;
	        try {
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	            Date parsedDate = sdf1.parse(deliveryDate);  // Parsing the date from the original format
	            String formattedDate = df.format(parsedDate);
	            
	            query = hrbsession.createSQLQuery("exec Check_Warranty_Applicability :tsn,:deliveryDate,:hmr");
	            query.setParameter("tsn", chassisNo);
	            query.setParameter("deliveryDate", formattedDate);
	            query.setParameter("hmr", hmr);
	            result = query.list().get(0).toString().trim();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            result=e.getMessage();
	        }
	        finally {
	            try {
	                if (hrbsession != null) {
	                    hrbsession.close();

	                }
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return result;
	    }
	 
	 public JasperPrint pdfGeneratorReport(HttpServletRequest request, String jaspername,
				HashMap<String, Object> jasperParameter, String filePath) {

			JasperPrint jasperPrint = null;
			Connection conn = null;
			try {

				System.out.println("filePath  " + filePath);
				conn = new dbConnection().getConnection();

				if (conn != null) {
					jasperPrint = JasperFillManager.fillReport(filePath, jasperParameter, conn);
				}
			} catch (Exception e) {
				jasperPrint = null;
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					jasperPrint = null;
					e.printStackTrace();
				}
			}
			return jasperPrint;
		}

	public void printReport(JasperPrint jasperPrint, String format, String printStatus, OutputStream outputStream,
				String reportName) throws Exception {

			if (format != null && format.equalsIgnoreCase("pdf")) {
				JRPdfExporter exporter = new JRPdfExporter();

				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				if (printStatus != null && printStatus.equals("true")) {
					configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
					configuration.setPdfJavaScript("this.print();");
				}
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			} else if (format != null && format.equalsIgnoreCase("xls")) {

				JRXlsxExporter exporter = new JRXlsxExporter();
				SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
				reportConfigXLS.setSheetNames(new String[] { "sheet1" });
				exporter.setConfiguration(reportConfigXLS);
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

				exporter.exportReport();

			}
		}
		
	public String isVehicleExistForItlExtWty(String vinNo, int delDaysCount) {
	    Session session = null;
	    String result = "false"; // Default to false

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();

	        // Query to check if the VIN exists in ITL_EWM_STG table
	        Query qry2 = session.createSQLQuery("SELECT CHASSIS_NO FROM ITL_EWM_STG WHERE CHASSIS_NO = :vinNo");
	        qry2.setParameter("vinNo", vinNo);
	        List result2 = qry2.list();

	        // Query to check if the VIN exists in ITLEWM_LOADER_DETAIL table
	        Query qry1 = session.createSQLQuery("SELECT CHASSIS_NO FROM ITLEWM_LOADER_DETAIL WHERE CHASSIS_NO = :vinNo");
	        qry1.setParameter("vinNo", vinNo);
	        List result1 = qry1.list();

	        // Check if records exist in both tables
	        if ((result2 != null && !result2.isEmpty()) && (result1 != null && !result1.isEmpty())) {
	            result = "true";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return result;
	}

	public String checkChassisInItlExtWty(String vinNo, String dealerCode) {
	    Session hrbsession = null;
	    String result = "N"; // Default to 'N' if no data is found
	    try {
	        hrbsession = HibernateUtil.getSessionFactory().openSession();
	        hrbsession.beginTransaction();
	        
	        // Corrected SQL Query
	        Query query = hrbsession.createSQLQuery("SELECT  1 FROM ITLEWM_LOADER_DETAIL WHERE DEALER_CODE = :dealerCode AND CHASSIS_NO = :vinNo");
	        query.setParameter("dealerCode", dealerCode);
	        query.setParameter("vinNo", vinNo);
	        
	        // Execute the query and check the result
	        if (query.uniqueResult() != null) {
	            result = "Y"; // Data found
	        }
	        
	        hrbsession.getTransaction().commit(); // Commit transaction if needed
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = e.getMessage(); // Return exception message if needed
	    } finally {
	        if (hrbsession != null && hrbsession.isOpen()) {
	            hrbsession.close();
	        }
	    }
	    return result;
	}

	public boolean isPartNoInLubesPart(String partno) {
		Session hrbsession = HibernateUtil.getSessionFactory().openSession();
	    boolean exists = false;
	    try {
	        // Use native SQL query instead of HQL
	        String sql = "SELECT COUNT(*) FROM Lubes_Part(nolock) WHERE PartNo = :partno";
	        Query query = hrbsession.createSQLQuery(sql);
	        query.setParameter("partno", partno);
	        
	        // Get the count as a Long value and check if greater than 0
	        Long count = ((Number) query.uniqueResult()).longValue();
	        
	        if (count > 0) {
	            exists = true;
	        }
	    } catch (Exception e) {
	        // Handle exception (e.g., log it)
	        e.printStackTrace();
	    } finally {
	        try {
	            if (hrbsession != null) {
	                hrbsession.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return exists;
	}

	public LinkedHashSet<LabelValueBean> getLabelValues() {
	    Session hrbsession = HibernateUtil.getSessionFactory().openSession();
	    LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();

	    try {
	        // Adding "Yes" and "No" options
	        result.add(new LabelValueBean("Yes", "Yes"));
	        result.add(new LabelValueBean("No", "No"));
	    } catch (Exception ae) {
	        ae.printStackTrace();
	    } finally {
	        try {
	            if (hrbsession != null) {
	                hrbsession.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}

	public String checkEstimateMandatory(String jobCardNo) {
		Session hrbsession = HibernateUtil.getSessionFactory().openSession();
		String result = "N";
		try {
			String proc = "EXEC SP_CheckEstimateMandatory :jobCardNo, :isMandatory";
			Query query = hrbsession.createSQLQuery(proc);
			query.setParameter("jobCardNo", jobCardNo);
			query.setParameter("isMandatory", "N");

			Object res = query.uniqueResult();
			if (res != null) {
				result = res.toString();
			}
		} catch (Exception e) {
			e.printStackTrace(); // don’t swallow exception
		} finally {
			if (hrbsession != null) {
				hrbsession.close();
			}
		}
		return result;
	}
	
	public LinkedHashSet<LabelValueBean> getBillCodeForLubes(String jobType)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "", flag = "", discount = "";
        String hqlquery = " FROM Billmaster where isActive='Y' and billDesc <> 'WTY' order by BillDesc";
        Iterator itr = null;
        try {
            LabelValueBean lv = null;
            Query query = hrbsession.createQuery(hqlquery);
            itr = query.list().iterator();

            while (itr.hasNext()) {

                Billmaster bill = (Billmaster) itr.next();
                name = bill.getBillDesc() == null ? "" : bill.getBillDesc().trim();
                id = bill.getBillID() == null ? "" : Long.toString(bill.getBillID());
                flag = bill.getWarrantyType() == null ? "" : bill.getWarrantyType().trim();
                discount = Float.toString(bill.getDiscount());
                if (jobType!=null && jobType.equalsIgnoreCase("35")) {
                    if (!"2".equals(id)) {
                    lv = new LabelValueBean(name, id + "@@" + flag + "@@" + discount);
                    }
                    result.add(lv);
                } else if (!"6".equals(id)) {
                    lv = new LabelValueBean(name, id + "@@" + flag + "@@" + discount);
                    result.add(lv);
                }
            }
        }
        catch (Exception ae) {
            ae.printStackTrace();
        }
        finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

	public String poCreatedForVOR(String jobCardNo) {
	    Session hrbsession = null;
	    String result = "N";
	    try {
	        hrbsession = HibernateUtil.getSessionFactory().openSession();

	        String sqlQuery = "SELECT CUST_PO_NO FROM SP_ORDER_MASTER (NOLOCK) WHERE job_card_no = :jobCardNo";

	        Query query = hrbsession.createSQLQuery(sqlQuery)
	                .addScalar("CUST_PO_NO", org.hibernate.type.StringType.INSTANCE);
	        query.setParameter("jobCardNo", jobCardNo);

	        List<String> list = query.list();

	        if (list != null && !list.isEmpty()) {
	            result = "Y"; // Record exists
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (hrbsession != null) {
	            hrbsession.close();
	        }
	    }
	    return result;
	}





}
