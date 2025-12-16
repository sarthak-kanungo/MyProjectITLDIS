/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Criteria;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.EAMG.common.EAMG_MethodsUtility;
import com.common.DBQueryConstant;

import HibernateMapping.Actioncodemaster;
//import HibernateMapping.Aggregatemaster;
import HibernateMapping.Aggregatemaster;
import HibernateMapping.ApplicationMaster;
import HibernateMapping.Baymaster;
import HibernateMapping.Billmaster;
import HibernateMapping.CMTaxCategoryBranch;
import HibernateMapping.CMTaxCategoryBranchDetail;
import HibernateMapping.Causalcodemaster;
import HibernateMapping.Causalcodevsconsequencecode;
import HibernateMapping.CmChargeBranch;
import HibernateMapping.CmTaxCharg_OEM;
import HibernateMapping.Complaintcodemaster;
import HibernateMapping.Consequencemaster;
import HibernateMapping.CustomerCategoryDiscount;
import HibernateMapping.CustomerCategoryMaster;
import HibernateMapping.FormCheckList;
import HibernateMapping.FormMaster;
import HibernateMapping.FormMasterPK;
import HibernateMapping.ItlContentMaster;
import HibernateMapping.ItlFormContent;
import HibernateMapping.ItlSubContentMaster;
import HibernateMapping.Jobcardstatusmaster;
import HibernateMapping.Joblocationmaster;
import HibernateMapping.Jobtypemaster;
import HibernateMapping.MSWDmechanicmaster;
import HibernateMapping.MSWINSPDITractorDetails;
import HibernateMapping.MSWvendormaster;
import HibernateMapping.MSWwarrantytaxStatePercentage;
import HibernateMapping.MSWwarrantytaxStatePercentagePK;
import HibernateMapping.Nextservicemaster;
import HibernateMapping.Otherjobworkmaster;
import HibernateMapping.Ownerdrivenmaster;
import HibernateMapping.SAPRejectionCodeMaster;
import HibernateMapping.Servicetypemaster;
import HibernateMapping.Standardjobpartmaster;
//import HibernateMapping.Subaggregatemaster;
//import HibernateMapping.Subassemblymaster;
import HibernateMapping.Subaggregatemaster;
import HibernateMapping.Subassemblymaster;
import HibernateMapping.UmDmsUserDealerMatrix;
import HibernateMapping.Wagemaster;
import HibernateMapping.WarrantyModel;
import HibernateUtil.HibernateUtil;
import beans.ContentFormBean;
import beans.SubContentFormBean;
import beans.masterForm;
import dbConnection.dbConnection;
import jxl.Sheet;
import jxl.Workbook;

public class masterDAO implements DBQueryConstant {

    Connection conn = null;
    String dbPATH = new dbConnection().dbPathAuth;
    int rowsUpdate = 0;
    private Logger logger = Logger.getLogger(this.getClass());

    public masterDAO(Connection conn) {
        this.conn = conn;
    }

    public masterDAO() {
    }

    /**
     * *******************************************************************************
     * @return String Common method
     * *******************************************************************************
     */
    public String check_in_Db(String no_new, String table, String column, String SubQuery) {
        String val = "notexist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {


            String hql = " FROM " + table + " where " + (column) + "='" + no_new + "'" + SubQuery;
            Query query = hrbsession.createQuery(hql);
            List results = query.list();

            Iterator itr = results.iterator();
//            // System.out.println("check_in_Db:select * from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery);
            if (itr.hasNext()) {
                val = "exist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                hrbsession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    // *** check data from db method in hibernate
    public static String check_in_Db_HQL(String no_new, String tablemappingClass, String column, String SubQuery, Session session) {

        String val = "notexist";
        try {
            String hql = " FROM " + tablemappingClass + " m  WHERE m." + column + " = :no_new " + SubQuery;
            Query query = session.createQuery(hql);
            query.setParameter("no_new", no_new);
            List result = query.list();
            // // System.out.println("" + result.toString());
            if (result.size() > 0) {
                val = "exist";
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

    /**
     * *******************************************************************************
     * Common method
     *
     * @param jobType desc
     * @return Int Id
     *
     ********************************************************************************
     */
    public int getJobTypeId(String jobTypeDesc) {
        int jobTypeId = 0;

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Jobtypemaster jm = null;
        try {
            String hql = " FROM Jobtypemaster where jobTypeDesc='" + jobTypeDesc + "'";
            Query query = hrbsession.createQuery(hql);
            List results = query.list();

            Iterator itr = results.iterator();

            if (itr.hasNext()) {
                jm = (Jobtypemaster) itr.next();
                jobTypeId = jm.getJobTypeID();

            }
            // // System.out.println("jobTypeId==" + jobTypeId);

        } catch (Exception ae) {
            ae.printStackTrace();
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
        return jobTypeId;

    }

    /**
     * *******************************************************************************
     * Common method
     *
     * @param grpid
     * @return String
     *
     ********************************************************************************
     */
    public String getNameById(String id, String get, String from, String Where) {
        String name = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "select " + get + " from " + from + " where " + Where + "='" + id + "'";
            Query query = hrbsession.createQuery(hql);
            List results = query.list();
            Iterator itr = results.iterator();
            if (itr.hasNext()) {
                name = (String) itr.next();

            }

        } catch (Exception ae) {
            ae.printStackTrace();
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
        return name;
    }

    ///////////////////////////////////////***Job type MASTER***////////////////////////////////////////////////
    public ArrayList<masterForm> getJobTypeList(masterForm masterForm, String nameSrch) throws SQLException {

        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where jobTypeDesc like '%" + SearchQuery + "%' ";
            }
            sql = "from Jobtypemaster "
                    + "" + Subsql + " order by jobTypeDesc";

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Jobtypemaster jm = (Jobtypemaster) itr.next();
                masterForm = new masterForm();
                masterForm.setId(Integer.toString(jm.getJobTypeID()));
                masterForm.setFreeService(jm.getFreeService() + "");
                masterForm.setName(jm.getJobTypeDesc());
                masterForm.setStatus(jm.getIsActive() + "");
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();//closing session by annpurna
			}
		}
        return dataList;
    }

    public String addJobType(Jobtypemaster jtm) throws SQLException {
        String results = "";
        int id = 0;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Jobtypemaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            String check = check_in_Db(jtm.getJobTypeDesc(), "Jobtypemaster", "JobTypeDesc", "");
            if (check.equalsIgnoreCase("notexist")) {
                jtm.setSeqNo(id);
                hrbsession.save(jtm);
                hrbsession.getTransaction().commit();
                results = "Success@@Job Type Description'" + jtm.getJobTypeDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Job Type Description'" + jtm.getJobTypeDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Job Type Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateJobType(Jobtypemaster jtm, String status, String id, String type, String cTypeName, String flag) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Job Type.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        char newStatus = 'N';
        try {
            if (!type.equalsIgnoreCase("freeService") && !type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Jobtypemaster jtstatus = (Jobtypemaster) hrbsession.load(Jobtypemaster.class, Integer.parseInt(id));
                jtstatus.setIsActive(newStatus);
                jtstatus.setLastUpdatedDate(jtm.getLastUpdatedDate());
                hrbsession.saveOrUpdate(jtstatus);
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else if (type.equalsIgnoreCase("freeService")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Jobtypemaster jtstatus = (Jobtypemaster) hrbsession.load(Jobtypemaster.class, Integer.parseInt(id));
                jtstatus.setFreeService(newStatus);
                jtstatus.setLastUpdatedDate(jtm.getLastUpdatedDate());
                hrbsession.saveOrUpdate(jtstatus);
                hrbsession.getTransaction().commit();
                result = "Success@@Free Service has been updated Successfully.";
            } else {
                String check = check_in_Db(jtm.getJobTypeDesc(), "Jobtypemaster", "jobTypeDesc", " and jobTypeID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Jobtypemaster jtstatus = (Jobtypemaster) hrbsession.load(Jobtypemaster.class, Integer.parseInt(id));
                    jtstatus.setJobTypeDesc(jtm.getJobTypeDesc()); //.toUpperCase()
                    jtstatus.setLastUpdatedDate(jtm.getLastUpdatedDate());
                    hrbsession.saveOrUpdate(jtstatus);
                    hrbsession.getTransaction().commit();
                    result = "Success@@Job Type Description '" + jtm.getJobTypeDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Job Type Description '" + jtm.getJobTypeDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Job Type Description, Please contact system Administrator.";
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

        return result;
    }
    ///////////////////////////////////////***ServiceTypeMaster***////////////////////////////////////////////////

    public ArrayList<masterForm> getServiceTypeList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Servicetypemaster sm = null;
        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where serviceTypeDesc like '%" + SearchQuery + "%' ";
            }
            sql = " from Servicetypemaster "
                    + "" + Subsql + " order by serviceTypeDesc";

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                sm = (Servicetypemaster) itr.next();
                masterForm = new masterForm();
                masterForm.setId(Integer.toString(sm.getServiceTypeID()));
                masterForm.setName(sm.getServiceTypeDesc());
                masterForm.setStatus(sm.getIsActive() + "");
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
            }
        }
        return dataList;
    }

    public String addServiceType(Servicetypemaster stm) throws SQLException {
        String results = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        int id = 0;
        try {
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Servicetypemaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            String check = check_in_Db(stm.getServiceTypeDesc(), "Servicetypemaster", "serviceTypeDesc", "");
            if (check.equalsIgnoreCase("notexist")) {
                stm.setSeqNo(id);
                hrbsession.save(stm);
                hrbsession.getTransaction().commit();
                results = "Success@@Service Type Description '" + stm.getServiceTypeDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Service Type Description '" + stm.getServiceTypeDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Service Type Description, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Service Type Description, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateServiceType(Servicetypemaster stm, String status, String id, String type, String cTypeName) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Service Type.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        char newStatus = 'N';
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Servicetypemaster stmstatus = (Servicetypemaster) hrbsession.load(Servicetypemaster.class, Integer.parseInt(id));
                stmstatus.setIsActive(newStatus);
                stmstatus.setLastUpdatedDate(stm.getLastUpdatedDate());
                hrbsession.saveOrUpdate(stmstatus);
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";

            } else {

                String check = check_in_Db(stm.getServiceTypeDesc(), "Servicetypemaster", "serviceTypeDesc", " and serviceTypeID <> " + id + "");

                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Servicetypemaster stmstatus = (Servicetypemaster) hrbsession.load(Servicetypemaster.class, Integer.parseInt(id));
                    stmstatus.setServiceTypeDesc(stm.getServiceTypeDesc());
                    stmstatus.setLastUpdatedDate(stm.getLastUpdatedDate());
                    hrbsession.saveOrUpdate(stmstatus);
                    hrbsession.getTransaction().commit();
                    result = "Success@@Service Type Description '" + stm.getServiceTypeDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Service Type Description '" + stm.getServiceTypeDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Service Type Description, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Service Type Description, Please contact system Administrator.";
            }
        }
        return result;
    }
    ///////////////////////////////////////***MechanicMaster***////////////////////////////////////////////////

    public ArrayList<masterForm> getMechanicList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        MSWDmechanicmaster mm = null;

        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " and mechanicName like '%" + SearchQuery + "%' ";   //mechanicCode
            }

            //1                                 2                           3                           4                                       5                                6                                 7
            // sql = "select distinct mechanicName,isActive,workertype,MSWDmechanicmasterPK.mechanicCode from MSWDmechanicmaster " + Subsql + "   order by mechanicName";
            sql = " from MSWDmechanicmaster  where dealerCode='" + masterForm.getDealer_code() + "' " + Subsql + "   order by mechanicName ";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {

                //Object o[] = (Object[]) itr.next();
                mm = (MSWDmechanicmaster) itr.next();
                masterForm = new masterForm();
                //masterForm.setMechanic_Name(o[0]==null?"": o[0].toString());

                masterForm.setMechanic_Name(mm.getMechanicName() == null ? "" : mm.getMechanicName());
                masterForm.setStatus(mm.getIsActive() + "");
                masterForm.setWorker_type(mm.getWorkertype());

                masterForm.setMechanic_Code(mm.getMechanicCode());




                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addMechanicM(String mechanicCode, String mechanicName, String workertype, String userId, String dealercode) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        MSWDmechanicmaster mm = new MSWDmechanicmaster();
        String results = "";
        try {
            String check = check_in_Db(dealercode + "-" + mechanicCode, "MSWDmechanicmaster", "id.mechanicDealerKey", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                mm.setMechanicCode(mechanicCode.toUpperCase());
                mm.setDealerCode(dealercode);
                mm.setMechanicName(mechanicName);
                mm.setWorkertype(workertype);
                mm.setLastUpdatedDate(new java.util.Date());
                mm.setMechanicDealerKey(dealercode + "-" + mechanicCode);
                mm.setLastUpdatedBy(userId);
                mm.setIsActive('Y');
                hrbsession.save(mm);
                hrbsession.getTransaction().commit();
                results = "Success@@Mechanic Code '" + mechanicCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Mechanic Code '" + mechanicCode + "' Already Exists.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Mechanic, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Mechanic, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateMechanicM(String status, String id, String userId, String mechanicCode, String mechanicName, String type, String dealercode) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Mechanic.
         */
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        String result = "";

        String newStatus = "";
        MSWDmechanicmaster mm = new MSWDmechanicmaster();

        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                mm = (MSWDmechanicmaster) hrbsession.load(MSWDmechanicmaster.class, dealercode + "-" + mechanicCode);
                mm.setIsActive(newStatus.charAt(0));
                mm.setLastUpdatedDate(new java.util.Date());
                mm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(mm);
                hrbsession.getTransaction().commit();
                result = "Success@@Mechanic Code '" + mechanicCode + "' has been updated Successfully.";
            } else {
                mm = (MSWDmechanicmaster) hrbsession.load(MSWDmechanicmaster.class, dealercode + "-" + mechanicCode);
                mm.setMechanicCode(mechanicCode);
                mm.setDealerCode(dealercode);
                mm.setIsActive('Y');
                mm.setMechanicName(mechanicName);
                mm.setLastUpdatedDate(new java.util.Date());
                mm.setLastUpdatedBy(userId);
                hrbsession.getTransaction().commit();
                result = "Success@@Mechanic Code '" + mechanicCode + "' has been updated Successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Mechanic, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                result = "Failure@@Unable to update Mechanic, Please contact system Administrator.";
                e.printStackTrace();
            }
        }
        return result;
    }
    ///////////////////////////////////////***BayMaster***////////////////////////////////////////////////

    public ArrayList<masterForm> getBayList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Baymaster bm = null;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " where bayName like '%" + SearchQuery + "%' ";
            }
            sql = "from Baymaster " + Subsql + " order by bayName";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                bm = (Baymaster) itr.next();
                masterForm = new masterForm();
                masterForm.setBayCode(bm.getBayCode());
                masterForm.setBayName(bm.getBayName());
                masterForm.setStatus(bm.getIsActive() + "");
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addBayMaster(String bayCode, String bayName, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Baymaster bm = new Baymaster();
        try {
            String check = check_in_Db(bayCode, "Baymaster", "bayCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                bm.setBayCode(bayCode.toUpperCase());
                bm.setBayName(bayName);
                bm.setLastUpdatedDate(new java.util.Date());
                bm.setIsActive('Y');
                bm.setLastUpdatedBy(userId);
                hrbsession.save(bm);
                hrbsession.getTransaction().commit();
                results = "Success@@Bay Code '" + bayCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Bay Code '" + bayCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Bay, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Bay, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateBayMaster(String status, String id, String userId, String bayCode, String bayName, String type) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Mechanic.
         */
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        String result = "";
        String newStatus = "";
        Baymaster bm = null;

        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }

                bm = (Baymaster) hrbsession.load(Baymaster.class, bayCode);
                bm.setIsActive(newStatus.charAt(0));
                bm.setLastUpdatedDate(new java.util.Date());
                bm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(bm);

                result = "Success@@isActive has been updated Successfully.";
            } else {
                bm = (Baymaster) hrbsession.load(Baymaster.class, bayCode);
                bm.setLastUpdatedDate(new java.util.Date());
                bm.setBayName(bayName);
                bm.setBayCode(id);
                bm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(bm);

                result = "Success@@Bay Code '" + bayCode + "' has been updated Successfully.";
            }

            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Bay, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Bay, Please contact system Administrator.";
            }
        }
        return result;
    }

    ///////////////////////////////////////***WAGE MASTER***////////////////////////////////////////////////
    ///////////////////////////////////////***WAGE MASTER***////////////////////////////////////////////////
    public ArrayList<masterForm> getWageList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Wagemaster wm = null;
        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where wageName like '%" + SearchQuery + "%' ";
            }
            sql = "from Wagemaster  " + Subsql + " order by wageName";  //isActive='Y'
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                masterForm = new masterForm();
                wm = (Wagemaster) itr.next();
                // masterForm.setWageCode(wm.getWageCode());
                masterForm.setWageName(wm.getWageName());
                masterForm.setWageValue(wm.getWageValue());
                masterForm.setStatus(wm.getIsActive() + "");
                masterForm.setDealer_code(wm.getDealerCode());
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addWageMaster(String wageName, double wageValue, String userId, String dealaerCode) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Wagemaster wm = null;
        try {
//            String check = check_in_Db(wageCode, "WageMaster", "WageCode", "");
            //// System.out.println("inside dao");
            String check = check_in_Db(dealaerCode, "Wagemaster", "dealerCode", "");
            // String check2 = check_in_Db(dealaerCode, "Wagemaster", "wageCode", "");

            //  // System.out.println("check" + check);
            // if (check.equalsIgnoreCase("notexist") && check2.equalsIgnoreCase("notexist")) {
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                wm = new Wagemaster();
                // wm.setWageCode(wageCode);
                wm.setWageName(wageName);
                wm.setWageValue(wageValue);
                wm.setIsActive('Y');
                wm.setLastUpdatedDate(new java.util.Date());
                wm.setLastUpdatedBy(userId);
                wm.setDealerCode(dealaerCode);
                hrbsession.save(wm);
                hrbsession.getTransaction().commit();
                results = "Success@@Wage for Dealer Code '" + dealaerCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Wage for Dealer Code '" + dealaerCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Wage, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Wage, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateWageMaster(String status, String userId, String wageName, double wageValue, String type, String dealerCode) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Wage.
         */
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String result = "";

        String newStatus = "";
        try {
            hrbsession.beginTransaction();

            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                Wagemaster wm = (Wagemaster) hrbsession.load(Wagemaster.class, dealerCode);
                wm.setIsActive(newStatus.charAt(0));
                wm.setLastUpdatedDate(new java.util.Date());
                wm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(wm);
                result = "Success@@isActive has been updated Successfully.";
            } else {
                Wagemaster wm = (Wagemaster) hrbsession.load(Wagemaster.class, dealerCode);
                wm.setWageName(wageName);
                wm.setWageValue(wageValue);
                wm.setLastUpdatedBy(userId);
                wm.setLastUpdatedDate(new java.util.Date());
                hrbsession.saveOrUpdate(wm);
                result = "Success@@Wage for Dealer '" + dealerCode + "' has been updated Successfully.";
            }
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Wage, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Wage, Please contact system Administrator.";
            }
        }
        return result;
    }

    ///////////////////////////////////////***WAGE MASTER***////////////////////////////////////////////////
    public LinkedHashSet<LabelValueBean> getComplaintCode() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        Complaintcodemaster cm = null;
        LabelValueBean lv = null;
        try {
//<<<<<<< .mine
//            String sql = "from Complaintcodemaster where isActive='Y' order by compDesc";
//            query = hrbsession.createQuery(sql);
//            // System.out.println("sql"+sql);
//=======
            String sql = " from Complaintcodemaster where isActive='Y' order by compDesc";
            query = hrbsession.createQuery(sql);

            itr = query.list().iterator();

            while (itr.hasNext()) {
                cm = (Complaintcodemaster) itr.next();
                name = cm.getCompDesc() == null ? "" : cm.getCompDesc().trim();
                id = cm.getCompCode() == null ? "" : cm.getCompCode().trim();
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

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<masterForm> getActionList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query q = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " AND am.actionDesc  LIKE '%" + SearchQuery + "%' ";  //actionCode
            }
            sql = "SELECT cm.compCode, cm.compDesc, am.actionCode, am.actionDesc, am.serviceHrs,am.isActive "
                    + "FROM Complaintcodemaster cm, Actioncodemaster am "
                    + "WHERE cm.compCode=am.compCode " + Subsql + " order by am.actionDesc";
            q = hrbsession.createQuery(sql);
            itr = q.list().iterator();
            while (itr.hasNext()) {
                masterForm = new masterForm();
                Object o[] = (Object[]) itr.next();
                masterForm.setComplaintCode(o[0] == null ? "" : o[0].toString());
                masterForm.setCompDesc(o[1] == null ? "" : o[1].toString());
                masterForm.setActionCode(o[2] == null ? "" : o[2].toString());
                masterForm.setActionDesc(o[3] == null ? "" : o[3].toString());
                masterForm.setServiceHrs(o[4] == null ? 0.0f : Float.parseFloat(o[4].toString()));
                masterForm.setStatus(o[5] == null ? "" : o[5].toString());
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addActionMaster(String actionCode, String actionDesc, String compCode, float serviceHrs, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        int id = 0;
        try {
            String check = check_in_Db(actionCode, "Actioncodemaster", "actionCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                Actioncodemaster acm = new Actioncodemaster();
//                query = hrbsession.createQuery("select distinct compid from Complaintcodemaster where compCode ='" + compCode + "'");
//                itr = query.list().iterator();
//                if (itr.hasNext()) {
//                    id = (Integer) itr.next();
//                }
                // Complaintcodemaster cc = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, id);
                acm.setActionCode(actionCode.toUpperCase().trim());
                acm.setActionDesc(actionDesc.trim());
                //  acm.setCompCode(cc);
                acm.setCompCode(compCode);
                acm.setServiceHrs(serviceHrs);
                acm.setIsActive('Y');
                acm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                acm.setLastUpdatedBy(userId);
                hrbsession.save(acm);
                hrbsession.getTransaction().commit();
                results = "Success@@Action Code '" + actionCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Action Code '" + actionCode + "' Already Exists.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Action, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {
				hrbsession.close();//closing session by annpurna
			}
		}
        return results;
    }

    public String UpdateActionMaster(String acCodeId, String actionCode, String actionDesc, String status, String type, String compCode, float serviceHrs, String userId) throws SQLException {

        String result = "";
        char newStatus = 'N';
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        int id = 0;
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                Actioncodemaster acm = (Actioncodemaster) hrbsession.load(Actioncodemaster.class, actionCode);
                acm.setIsActive(newStatus);
                acm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                acm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(acm);
                hrbsession.getTransaction().commit();
                result = "Success@@Action Code '" + actionCode + "' has been updated Successfully.";
            } else {
                Actioncodemaster acm = (Actioncodemaster) hrbsession.load(Actioncodemaster.class, actionCode);
//                query = hrbsession.createQuery("select distinct compid from Complaintcodemaster where compCode ='" + compCode.split("@@")[0] + "'");
//                itr = query.list().iterator();
//                if (itr.hasNext()) {
//                    id = (Integer) itr.next();
//                }
                //   Complaintcodemaster cc = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, id);
                acm.setActionDesc(actionDesc);
                acm.setCompCode(compCode.split("@@")[0]);
                acm.setServiceHrs(serviceHrs);
                acm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                acm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(acm);
                hrbsession.getTransaction().commit();
                result = "Success@@Action Code '" + actionCode + "' has been updated Successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Action, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {//closing session by annpurna
				hrbsession.close();
			}
		}
        return result;
    }
    ////////////////////////////////***Aggregate Master***///////////////////////////////////////////////////

    public ArrayList<masterForm> getAggregateList(masterForm masterForm, String nameSrch) throws SQLException {
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where aggDesc like '%" + SearchQuery + "%' ";  //aggCode
            }
            sql = "from Aggregatemaster " + Subsql + " order by aggDesc";
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Aggregatemaster agr = (Aggregatemaster) itr.next();
                masterForm = new masterForm();
                masterForm.setAggCode(agr.getAggCode());
                masterForm.setAggDesc(agr.getAggDesc());
                masterForm.setStatus(agr.getIsActive() + "");
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {//closing sesssion by annpurna
				hrbsession.close();
			}
		}
        return dataList;
    }

    public String addAggregateMaster(String aggCode, String aggDesc, String userId) throws SQLException {
        String results = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(aggCode, "Aggregatemaster", "aggCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                Aggregatemaster agm = new Aggregatemaster();
                agm.setAggCode(aggCode.toUpperCase().trim());
                agm.setAggDesc(aggDesc.trim());
                agm.setIsActive('Y');
                agm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                agm.setLastUpdatedBy(userId);
                hrbsession.save(agm);
                hrbsession.getTransaction().commit();
                results = "Success@@Aggregate Code '" + aggCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Aggregate Code '" + aggCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Aggregate, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {//closing session by annpurna
				hrbsession.close();
			}
		}
        return results;
    }

    public String UpdateAggregateMaster(String status, String id, String userId, String aggCode, String aggDesc, String type, int agg_id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Aggregate.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String newStatus = "";
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                Aggregatemaster am = (Aggregatemaster) hrbsession.load(Aggregatemaster.class, aggCode);
                am.setIsActive(newStatus.charAt(0));
                am.setLastUpdatedDate(new java.util.Date());
                am.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(am);
                result = "Success@@Aggregate '" + aggCode + "' has been updated Successfully.";
            } else {
                Aggregatemaster am = (Aggregatemaster) hrbsession.load(Aggregatemaster.class, id);
                am.setAggDesc(aggDesc);
                am.setLastUpdatedDate(new java.util.Date());
                am.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(am);
                result = "Success@@Aggregate '" + id + "' has been updated Successfully.";
            }
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Aggregate, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Aggregate, Please contact system Administrator.";
            }
        }
        return result;
    }

    ////////////////////////////////***Application Master***///////////////////////////////////////////////////
    public ArrayList<masterForm> getApplicationList(masterForm masterForm, String nameSrch) throws SQLException {
        ArrayList<masterForm> dataList = new ArrayList();
        String hql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        List<ApplicationMaster> results = null;
        try {
            hrbsession.beginTransaction();

            if (!SearchQuery.equals("")) {
                Subsql = " where appDesc  like '%" + SearchQuery + "%' ";   //appCode
            }
            hql = " from ApplicationMaster " + Subsql + " order by appDesc";

            results = hrbsession.createQuery(hql).list();

            Iterator itr = results.iterator();

            while (itr.hasNext()) {

                ApplicationMaster app = (ApplicationMaster) itr.next();
                masterForm = new masterForm();
                masterForm.setApplicationCode(app.getAppCode());
                masterForm.setApplicationDesc(app.getAppDesc());
                masterForm.setStatus("" + app.getIsActive());
                // masterForm.setPrimary_id(app.getApp_Id());
                dataList.add(masterForm);
            }



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
        return dataList;
    }

    public String addApplicationMaster(masterForm mf, String appCode, String appDisc, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(appCode, "ApplicationMaster", "appCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                ApplicationMaster app = new ApplicationMaster();
                app.setAppCode(appCode.toUpperCase());
                app.setAppDesc(appDisc);
                app.setIsActive('Y');
                app.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                app.setLastUpdatedBy(userId);
                hrbsession.save(app);
                hrbsession.getTransaction().commit();
                results = "Success@@Application Code '" + appCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Application Code '" + appCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Application, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Application, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateApplicationMaster(String id, String appcode, String appDisc, String type, String status, String userId) {
        /*
         * this method is used to update status
         * corrosponding to particular Application.
         */
        String result = "";
        String newStatus = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                ApplicationMaster app = (ApplicationMaster) hrbsession.load(ApplicationMaster.class, appcode);
                app.setIsActive(newStatus.charAt(0));
                app.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                app.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(app);
                hrbsession.getTransaction().commit();
                result = "Success@@Application Code '" + appcode + "' has been updated Successfully.";
            } else {
                ApplicationMaster app = (ApplicationMaster) hrbsession.load(ApplicationMaster.class, id);
                app.setAppDesc(appDisc);
                app.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                app.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(app);
                hrbsession.getTransaction().commit();
                result = "Success@@Application Code '" + id + "' has been updated Successfully.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Application, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Application, Please contact system Administrator.";
            }
        }
        return result;
    }

////////////////////////////////***Complaint Code Master***///////////////////////////////////////////////////
    public LinkedHashSet<LabelValueBean> getAssemblyCode() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct subAssemblyCode,subAssemblyDesc FROM Subassemblymaster where isActive='Y' order by subassemblyDesc";
        try {
            LabelValueBean lv = null;

            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();

            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[0].toString() == null ? "" : o[0].toString().trim() + " [" + o[1].toString().trim() + "]";
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

    public LinkedHashSet<LabelValueBean> getAggregateCode() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct aggCode,aggDesc FROM Aggregatemaster where isActive='Y' order by aggDesc";
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

    public ArrayList<masterForm> getComplaintCodeList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " AND cm.compDesc  LIKE '%" + SearchQuery + "%' ";   //compCode
            }

            sql = "SELECT am.subAssemblyCode, am.subAssemblyDesc, cm.compCode, cm.compDesc,cm.isActive " // ,cm.compid , am.id," +  " cm.compid "
                    + "FROM Subassemblymaster am, Complaintcodemaster cm "
                    + "WHERE cm.subAssemblyCode=am.subAssemblyCode " + Subsql + " order by cm.compDesc";
            Query query = hrbsession.createQuery(sql);
            Iterator itr = query.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                masterForm = new masterForm();
                masterForm.setAssmCode(o[0].toString());
                masterForm.setAssmDesc(o[1].toString());
                masterForm.setCompCode(o[2].toString());
                masterForm.setCompDesc(o[3].toString());
                masterForm.setStatus(o[4].toString());
                //masterForm.setPrimary_id(Integer.parseInt(o[5].toString()));
                // masterForm.setAssmId(o[6].toString());
                //  masterForm.setCompid(o[6].toString());
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addComplaintCodeMaster(String compCode, String compDesc, String assmId, String userId) throws SQLException {
        String results = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            String check = check_in_Db(compCode, "Complaintcodemaster", "compCode", "");
            if (check.equalsIgnoreCase("notexist")) {

                hrbsession.beginTransaction();
//                Query query = hrbsession.createQuery("Select max(mt.compid) from Complaintcodemaster mt");
//                List list = (List) query.list();
//                // System.out.println("list is  " + list.get(0));
//                int compid = Integer.parseInt(list.get(0).toString());


                Complaintcodemaster ccm = new Complaintcodemaster();
                ccm.setCompCode(compCode.toUpperCase().trim());
                ccm.setCompDesc(compDesc);

                Subassemblymaster sm = (Subassemblymaster) hrbsession.load(Subassemblymaster.class, assmId);
                ccm.setSubAssemblyCode(sm);
                ccm.setIsActive('Y');
                ccm.setLastUpdatedDate(new java.util.Date());
                ccm.setLastUpdatedBy(userId);
                hrbsession.save(ccm);
                hrbsession.getTransaction().commit();
                results = "Success@@Defect Code '" + compCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Defect Code '" + compCode + "' Already Exists.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Defect Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Defect Code, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateComplaintCode(String status, String id, String userId, String compCode, String compDesc, String assmId, String type, int comp_id, String cid) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Aggregate.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        char newStatus = 'Y';
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                Complaintcodemaster ccm = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, compCode);  //Integer.parseInt(cid)
                ccm.setIsActive(newStatus);
                ccm.setLastUpdatedDate(new java.util.Date());
                ccm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(ccm);
                hrbsession.getTransaction().commit();
                result = "Success@@Defect Code '" + compCode + "' has been updated Successfully.";
            } else {
                Complaintcodemaster ccm = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, id); //Integer.parseInt(cid)
                ccm.setCompDesc(compDesc);
                Subassemblymaster sam = (Subassemblymaster) hrbsession.load(Subassemblymaster.class, assmId);
                ccm.setSubAssemblyCode(sam);
                ccm.setLastUpdatedDate(new java.util.Date());
                ccm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(ccm);
                hrbsession.getTransaction().commit();
                result = "Success@@Defect Code '" + id + "' has been updated Successfully.";
            }


        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Complaint, Please contact system Administrator.";
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
    ////////////////////////////////***Causal Code Master***///////////////////////////////////////////////////

    public LinkedHashSet<LabelValueBean> getCausalCode() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        Query query = null;
        Iterator itr = null;
        Complaintcodemaster cm = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        // // System.out.println("before1");
        String sql = " FROM Complaintcodemaster where isActive='Y' order by compDesc";
        try {
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            LabelValueBean lv = null;

            while (itr.hasNext()) {
                cm = (Complaintcodemaster) itr.next();

                name = cm.getCompDesc() == null ? "" : cm.getCompDesc().trim();
                id = cm.getCompCode() == null ? "" : cm.getCompCode().trim();
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

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<masterForm> getCausalCodeList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " AND ccm.causalDesc  LIKE '%" + SearchQuery + "%' ";   //causalCode
            }
            sql = "SELECT cm.compCode, cm.compDesc, ccm.causalCode, ccm.causalDesc,ccm.isActive "
                    + "FROM Causalcodemaster ccm, Complaintcodemaster cm "
                    + "WHERE cm.compCode=ccm.compCode " + Subsql + " order by ccm.causalDesc";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object[] o = (Object[]) itr.next();
                masterForm = new masterForm();
                masterForm.setCompCode(o[0] == null ? "" : o[0].toString());
                masterForm.setCompDesc(o[1] == null ? "" : o[1].toString());
                masterForm.setCausalCode(o[2] == null ? "" : o[2].toString());
                masterForm.setCausalDesc(o[3] == null ? "" : o[3].toString());
                masterForm.setStatus(o[4] == null ? "" : o[4].toString());
                //masterForm.setPrimary_id(o[5] == null ? 0 : Integer.parseInt(o[5].toString()));
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addCausalCodeMaster(String causalCode, String causalDesc, String compCode, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Causalcodemaster cm = new Causalcodemaster();

        try {
            String check = check_in_Db(causalCode, "Causalcodemaster", "causalCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                Complaintcodemaster compco = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, compCode);
                hrbsession.beginTransaction();
                cm.setCausalCode(causalCode.toUpperCase());
                cm.setCausalDesc(causalDesc);
                cm.setCompCode(compco);
                cm.setIsActive('Y');
                cm.setLastUpdatedDate(new java.util.Date());
                cm.setLastUpdatedBy(userId);
                hrbsession.save(cm);
                hrbsession.getTransaction().commit();
                results = "Success@@Cause Code '" + causalCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Cause Code '" + causalCode + "' Already Exists.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Cause Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Cause Code, Please contact system Administrator.";
            }
        }
        return results;

    }

    public String UpdateCausalCode(String status, String id, String userId, String causalCode, String causalDesc, String compCode, String type, int causal_id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Causal Code.
         */

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        String result = "";

        Causalcodemaster ccm = null;
        String newStatus = "";
        try {

            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                hrbsession.beginTransaction();
                ccm = (Causalcodemaster) hrbsession.load(Causalcodemaster.class, causalCode);
                ccm.setIsActive(newStatus.charAt(0));
                ccm.setLastUpdatedDate(new java.util.Date());
                ccm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(ccm);
                hrbsession.getTransaction().commit();
                result = "Success@@Cause Code '" + causalCode + "' has been updated Successfully.";
            } else {
                String oldCausalCode = getNameById(id, "causalCode", "Causalcodemaster", "causalCode");
                String check = "";
                if (!causalCode.equals("")) {
                    check = check_in_Db(causalCode, "Causalcodemaster", "causalCode", " and causalId <>'" + causal_id + "'");
                } else {
                    check = check_in_Db(id, "Causalcodemaster", "causalCode", " and causalId <>'" + causal_id + "'");
                }
                //String check = check_in_Db(causalCode, "CausalCodeMaster", "causalCode", " and causalCode <> " + causalCode + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    ccm = (Causalcodemaster) hrbsession.load(Causalcodemaster.class, oldCausalCode);
                    Complaintcodemaster compco = (Complaintcodemaster) hrbsession.load(Complaintcodemaster.class, compCode.split("@@")[0]);
                    if (!causalCode.equals("")) {
                        ccm.setCausalCode(causalCode.trim());
                    } else {
                        ccm.setCausalCode(oldCausalCode.trim());
                    }

                    ccm.setCausalDesc(causalDesc);
                    ccm.setCompCode(compco);   //compCode.split("@@")[0]
                    ccm.setLastUpdatedDate(new java.util.Date());
                    ccm.setLastUpdatedBy(userId);
                    hrbsession.saveOrUpdate(ccm);
                    hrbsession.getTransaction().commit();
                    if (!causalCode.equals("")) {
                        result = "Success@@Cause Code '" + causalCode + "' has been updated Successfully.";
                    } else {
                        result = "Success@@Cause Code '" + oldCausalCode + "' has been updated Successfully.";
                    }
                } else {
                    if (!causalCode.equals("")) {
                        result = "Failure@@Cause Code '" + causalCode + "' Already Exists.";
                    } else {
                        result = "Failure@@Cause Code '" + oldCausalCode + "' Already Exists.";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Causal, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Causal, Please contact system Administrator.";
            }
        }
        return result;

    }

    public LinkedHashSet<LabelValueBean> getCommonCodeById(String tempStr, String flag) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hqlQuery = "";

        // // System.out.println("flag====" + flag);

        if (flag.equals("Agg_Code")) {
            //sqlQuery = "select distinct compCode,compDesc from Complaintcodemaster where subassemblyCode='" + tempStr + "' and isActive='Y' order by compDesc ";
            hqlQuery = "select sub.id,sub.subAggDesc from Subaggregatemaster sub ,Aggregatemaster agg where agg.aggCode='" + tempStr + "' and agg.aggCode=sub.aggCode and sub.isActive='Y'";

        } else if (flag.equals("Comp_Code")) {
            hqlQuery = "select distinct cc.causalCode,cc.causalDesc from Causalcodemaster cc,Complaintcodemaster cm where cm.compCode='" + tempStr + "' and  cm.compCode=cc.compCode  and cc.isActive='Y' order by cc.causalDesc ";
        } else if (flag.equals("Causal_Code")) {
            hqlQuery = "select distinct ca.id,cm.consequenceDesc from Causalcodevsconsequencecode ca,Consequencemaster cm where  ca.consequenceCode=cm.consequenceCode and  ca.causalCode='" + tempStr + "' and cm.isActive='Y' order by cm.consequenceDesc ";
        } else if (flag.equals("Complaint")) {
            hqlQuery = "SELECT distinct actionCode ,actionDesc,serviceHrs FROM Actioncodemaster where compCode='" + tempStr + "' order by actionDesc";
            //sqlQuery = "select distinct LabourCodeId,LabourCodeDesc from labour_hrs_master where DefectCode='" + tempStr + "' and isActive='Y' order by ActionDesc ";
        } else if (flag.equals("subAgg_Code")) {
            //hqlQuery = "select sub.subAggCode,sub.subAggDesc from Subaggregatemaster sub ,Aggregatemaster agg where agg.aggCode='" + tempStr + "' and agg.agg_id=sub.aggId and sub.isActive='Y'";
            hqlQuery = "select subassm.subAssemblyCode,subassm.subAssemblyDesc from Subaggregatemaster subagg ,Subassemblymaster subassm where subagg.subAggCode='" + tempStr + "' and subagg.subAggCode=subassm.subAggCode and subassm.isActive='Y'";
        } else if (flag.equals("subassembly_Code")) {

            hqlQuery = "select distinct cm.compCode,cm.compDesc from Complaintcodemaster cm , Subassemblymaster sam where sam.id='" + tempStr + "' and cm.subAssemblyCode=sam.subAssemblyCode   and cm.isActive='Y' order by cm.compDesc ";
            ////sqlQuery = "select distinct compCode,compDesc from Complaintcodemaster where subassemblyCode='" + tempStr + "' and isActive='Y' order by compDesc ";hqlQuery = "select v.vendorCode,v.vendorDesc from Vendormaster v  where v.subAggCode='" + tempStr + "' and v.IsActive='Y' order by v.vendorDesc";
        }
        try {
            LabelValueBean lv = null;

            Query query = hrbsession.createQuery(hqlQuery);

            Iterator itr = query.list().iterator();

            while (itr.hasNext()) {

                Object o[] = (Object[]) itr.next();

                name = o[1].toString().trim() == null ? "" : o[1].toString().trim();

                id = o[0].toString().trim() == null ? "" : o[0].toString().trim();

                if (flag.equals("Complaint")) {
                    id = o[0].toString().trim() == null ? "" : o[0].toString().trim() + "@@" + o[2].toString().trim().trim();
                }

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

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String addCompJobCard(String[] compVerbatim, String[] aggCode, String user_id, String[] compCode, String[] causalCode, String[] consequenceCode) throws SQLException {
        String results = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        int comp_id = 0;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select Max(compId) from complaintJobCard");
            if (rs.next()) {
                comp_id = rs.getInt(1);
            }
            comp_id = comp_id + 1;
            pstmt = conn.prepareStatement("insert into complaintJobCard (compId,Complaint_Verbatim, Aggregate,Complaint_Code,Causal_Code,Consequences,CreatedBy,LastUpdatedDate,isActive) values(?,?,?,?,?,?,?,?,?)");
            for (int i = 0; i < compVerbatim.length; i++) {
                pstmt.setInt(1, comp_id);
                pstmt.setString(2, compVerbatim[i].trim());
                pstmt.setString(3, aggCode[i].trim());
                pstmt.setString(4, compCode[i].trim());
                pstmt.setString(5, causalCode[i].trim());
                pstmt.setString(6, consequenceCode[i].trim());
                pstmt.setString(7, user_id);
                pstmt.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstmt.setString(9, "Y");
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            results = "Success@@ Complaint has been Successfully Added.";
            conn.commit();
        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Complaint, Please contact system Administrator.";
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Complaint, Please contact system Administrator.";
            }
        }
        return results;
    }

    public LinkedHashSet<LabelValueBean> getComplaintJobCardList() {

        Statement stmt = null;
        ResultSet rs = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String query = "select distinct ccm.CompCode ,ccm.CompDesc FROM MSW_actioncodemaster am,MSW_ComplaintCode ccm"
                + "  where ccm.CompCode=am.CompCode order by ccm.CompDesc";
        try {
            LabelValueBean lv = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString(2) == null ? "" : rs.getString(2).trim();
                id = rs.getString(1) == null ? "" : rs.getString(1).trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        } catch (Exception ae) {
            ae.printStackTrace();
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

    public float getServiceHrsById(String tempStr, String flag) {
        Connection con = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        float serviceHrs = 0.0f;
        float result = 0.0f;

        try {
            con = new dbConnection().getConnection();

            pstmt = con.prepareStatement("select Hrs from labour_hrs_master where LabourCodeId=?");
            pstmt.setString(1, tempStr.trim());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                serviceHrs = (rs.getFloat(1) * 50);
                if (serviceHrs != 0.0) {
                    result = serviceHrs;
                }
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String addeActionJobCard(String[] complaintCode, String[] actionCode, String user_id, String[] serviceHrs) throws SQLException {
        String results = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        int action_id = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select Max(action_id) from actionJobcard");
            if (rs.next()) {
                action_id = rs.getInt(1);
            }
            action_id = action_id + 1;
            pstmt = conn.prepareStatement("insert into actionJobcard (action_id,Complaint,Action_Taken,Labour_Charges,CreatedBy,LastUpdatedDate,isActive) values(?,?,?,?,?,?,?)");
            for (int i = 0; i < complaintCode.length; i++) {
                pstmt.setInt(1, action_id);
                pstmt.setString(2, complaintCode[i].trim());
                pstmt.setString(3, actionCode[i].trim());
                pstmt.setString(4, serviceHrs[i].trim());
                pstmt.setString(5, user_id);
                pstmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstmt.setString(7, "Y");
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            results = "Success@@ Action has been Successfully Added.";
            conn.commit();
        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Action, Please contact system Administrator.";
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Action, Please contact system Administrator.";
            }
        }
        return results;
    }

    ///////////////////////////////////////***JOB CARD STATUS MASTER***////////////////////////////////////////////////
    public ArrayList<masterForm> getJobCardStatusList(masterForm masterForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Jobcardstatusmaster jm = null;
        ArrayList<masterForm> dataList = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;

        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " where StatusDesc like '%" + SearchQuery + "%' ";
            }
            sql = " from Jobcardstatusmaster "
                    + "" + Subsql + " order by statusDesc";

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                masterForm = new masterForm();
                jm = (Jobcardstatusmaster) itr.next();
                masterForm.setId(Long.toString(jm.getStatusID()));
                masterForm.setName(jm.getStatusDesc());
                masterForm.setStatus(jm.getIsActive() + "");
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(hrbsession != null) {//closing session by annpurna
        		hrbsession.close();
        	}
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
        return dataList;
    }

    public String addJobCardStatus(Jobcardstatusmaster jcs) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String results = "StatusExist";
        int id = 0;
        try {
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Jobcardstatusmaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            String check = check_in_Db(jcs.getStatusDesc(), "Jobcardstatusmaster", "statusDesc", "");
            if (check.equalsIgnoreCase("notexist")) {
                jcs.setSeqNo(id);
                hrbsession.save(jcs);
                hrbsession.getTransaction().commit();
                results = "Success@@Status Description'" + jcs.getStatusDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Status Description'" + jcs.getStatusDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Status Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateJobCardStatus(Jobcardstatusmaster jcs, String type, String status, String id, String statusDesc) {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        char newStatus = 'N';
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Jobcardstatusmaster jcstatus = (Jobcardstatusmaster) hrbsession.load(Jobcardstatusmaster.class, Long.parseLong(id));
                jcstatus.setIsActive(newStatus);
                jcstatus.setLastUpdatedDate(jcs.getLastUpdatedDate());
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else {


                String check = check_in_Db(jcs.getStatusDesc(), "Jobcardstatusmaster", "statusDesc", " and StatusID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Jobcardstatusmaster jcstatus = (Jobcardstatusmaster) hrbsession.load(Jobcardstatusmaster.class, Long.parseLong(id));
                    jcstatus.setStatusDesc(jcs.getStatusDesc());
                    jcstatus.setLastUpdatedDate(jcs.getLastUpdatedDate());
                    hrbsession.getTransaction().commit();
                    result = "Success@@Status Description '" + jcs.getStatusDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Status Description '" + jcs.getStatusDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Status Description, Please contact system Administrator.";
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
        return result;
    }

    ///////////////////////////////////////***JOB CARD STATUS MASTER***////////////////////////////////////////////////
    public ArrayList<masterForm> getJobLocationList(masterForm masterForm, String nameSrch) throws SQLException {
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            Query query = null;
            if (!SearchQuery.equals("")) {
                Subsql = " where j.locationDesc like '%" + SearchQuery + "%' ";
                query = session.createQuery("select j.locationID,j.locationDesc,j.isActive  from Joblocationmaster j " + Subsql + " order by j.locationDesc");
            } else {
                query = session.createQuery("select j.locationID,j.locationDesc,j.isActive  from Joblocationmaster j  order by j.locationDesc");
            }
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                masterForm = new masterForm();
                masterForm.setId(obj[0] == null ? "" : obj[0].toString().trim());
                masterForm.setName(obj[1] == null ? "" : obj[1].toString().trim());
                masterForm.setStatus(obj[2] == null ? "" : obj[2].toString().trim());
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String addJobLocation(Joblocationmaster jlm) throws SQLException {
        String results = "StatusExist";
        int id = 0;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Joblocationmaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            String check = check_in_Db(jlm.getLocationDesc(), "Joblocationmaster", "LocationDesc", "");
            if (check.equalsIgnoreCase("notexist")) {
                jlm.setSeqNo(id);
                hrbsession.save(jlm);
                hrbsession.getTransaction().commit();
                results = "Success@@Location Description '" + jlm.getLocationDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Location Description '" + jlm.getLocationDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Location Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateJobLocation(Joblocationmaster jlm, String status, String id, String type, String locationDesc) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Location Desc.
         */
        String result = "";
        char newStatus = 'N';
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Joblocationmaster jlstatus = (Joblocationmaster) hrbsession.load(Joblocationmaster.class, Long.parseLong(id));
                jlstatus.setIsActive(newStatus);
                jlstatus.setLastUpdatedDate(jlm.getLastUpdatedDate());
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else {
                String check = check_in_Db(jlm.getLocationDesc(), "Joblocationmaster", "LocationDesc", " and LocationID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Joblocationmaster jlstatus = (Joblocationmaster) hrbsession.load(Joblocationmaster.class, Long.parseLong(id));
                    jlstatus.setLocationDesc(jlm.getLocationDesc());
                    jlstatus.setLastUpdatedDate(jlm.getLastUpdatedDate());
                    hrbsession.getTransaction().commit();
                    result = "Success@@Status Description '" + jlm.getLocationDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Status Description '" + jlm.getLocationDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Location Description, Please contact system Administrator.";
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
        return result;
    }
///////////////////////////////////////***Next Service From MASTER***////////////////////////////////////////////////

    public ArrayList<masterForm> getNextServiceList(masterForm masterForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Nextservicemaster nm = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " where nextServiceDesc like '%" + SearchQuery + "%' ";
            }
            sql = " from Nextservicemaster "
                    + "" + Subsql + " order by nextServiceDesc";

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                nm = (Nextservicemaster) itr.next();
                masterForm = new masterForm();
                masterForm.setId(Long.toString(nm.getNextServiceID()));
                masterForm.setName(nm.getNextServiceDesc());
                masterForm.setStatus(nm.getIsActive() + "");
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    public String addNextService(Nextservicemaster nsm) throws SQLException {
        String results = "nextServiceExist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        int id = 0;
        try {
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Nextservicemaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            String check = check_in_Db(nsm.getNextServiceDesc(), "Nextservicemaster", "nextServiceDesc", "");
            if (check.equalsIgnoreCase("notexist")) {
                nsm.setSeqNo(id);
                hrbsession.save(nsm);
                hrbsession.getTransaction().commit();
                results = "Success@@Next Service Description '" + nsm.getNextServiceDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Next Service Description '" + nsm.getNextServiceDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Next Service Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateNextService(Nextservicemaster nsm, String status, String id, String type, String nextServiceDesc) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        char newStatus = 'N';

        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Nextservicemaster nsmstatus = (Nextservicemaster) hrbsession.load(Nextservicemaster.class, Long.parseLong(id));
                nsmstatus.setIsActive(newStatus);
                nsmstatus.setLastUpdatedDate(nsm.getLastUpdatedDate());
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";

            } else {
                String check = check_in_Db(nsm.getNextServiceDesc(), "Nextservicemaster", "nextServiceDesc", " and nextServiceDesc <> " + nextServiceDesc + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Nextservicemaster nsmstatus = (Nextservicemaster) hrbsession.load(Nextservicemaster.class, Long.parseLong(id));
                    nsmstatus.setNextServiceDesc(nsm.getNextServiceDesc());
                    nsmstatus.setLastUpdatedDate(nsm.getLastUpdatedDate());
                    hrbsession.getTransaction().commit();
                    result = "Success@@Next Service Description '" + nsm.getNextServiceDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Next Service Description '" + nsm.getNextServiceDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Next Service Description, Please contact system Administrator.";
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
        return result;
    }
///////////////////////////////////////***Owner Driven MASTER***////////////////////////////////////////////////

    public ArrayList<masterForm> getOwnerDrivenList(masterForm masterForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Ownerdrivenmaster om = null;

        try {
            dataList = new ArrayList<masterForm>();


            if (!SearchQuery.equals("")) {
                Subsql = " where ownerDrivenDesc like '%" + SearchQuery + "%' ";


            }
            sql = " from Ownerdrivenmaster "
                    + "" + Subsql + " order by ownerDrivenDesc";

            //  rs = stmt.executeQuery(sql);
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                masterForm = new masterForm();
                om = (Ownerdrivenmaster) itr.next();
                masterForm.setId(Long.toString(om.getOwnerDrivenID()));
                masterForm.setName(om.getOwnerDrivenDesc());
                masterForm.setStatus(om.getIsActive() + "");
                dataList.add(masterForm);


            }
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
        return dataList;


    }

    public String addOwnerDriven(Ownerdrivenmaster odm) throws SQLException {
        String results = "ownerDrivenExist";
        int id = 0;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(odm.getOwnerDrivenDesc(), "Ownerdrivenmaster", "ownerDrivenDesc", "");
            hrbsession.beginTransaction();
            Criteria cr = hrbsession.createCriteria(Ownerdrivenmaster.class);
            cr.setProjection(Projections.max("seqNo"));
            if (!cr.list().isEmpty()) {
                if (cr.list().get(0) != null) {
                    id = Integer.parseInt(cr.list().get(0).toString());
                }
                id = id + 1;
            }
            if (check.equalsIgnoreCase("notexist")) {
                odm.setSeqNo(id);
                hrbsession.save(odm);
                hrbsession.getTransaction().commit();
                results = "Success@@Owner Driven Description '" + odm.getOwnerDrivenDesc() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Owner Driven Description '" + odm.getOwnerDrivenDesc() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Owner Driven Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateOwnerDriven(Ownerdrivenmaster odm, String status, String id, String type, String ownerDrivenDesc) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        String result = "";
        char newStatus = 'N';
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                Ownerdrivenmaster odmstatus = (Ownerdrivenmaster) hrbsession.load(Ownerdrivenmaster.class, Long.parseLong(id));
                odmstatus.setIsActive(newStatus);
                odmstatus.setLastUpdatedDate(odm.getLastUpdatedDate());
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else {

                String check = check_in_Db(odm.getOwnerDrivenDesc(), "Ownerdrivenmaster", "ownerDrivenDesc", " and OwnerDrivenID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    Ownerdrivenmaster odmstatus = (Ownerdrivenmaster) hrbsession.load(Ownerdrivenmaster.class, Long.parseLong(id));
                    odmstatus.setOwnerDrivenDesc(odm.getOwnerDrivenDesc());
                    odmstatus.setLastUpdatedDate(odm.getLastUpdatedDate());
                    hrbsession.getTransaction().commit();
                    result = "Success@@Owner Driven Description '" + odm.getOwnerDrivenDesc() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Owner Driven Description '" + odm.getOwnerDrivenDesc() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Owner Driven Description, Please contact system Administrator.";
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
        return result;
    }
///////////////////////////////////////***BILL MASTER***////////////////////////////////////////////////

    public ArrayList<masterForm> getBillList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Query query = null;
        Iterator itr = null;
        Billmaster bm = null;


        try {
            dataList = new ArrayList<masterForm>();



            if (!SearchQuery.equals("")) {
                Subsql = " where billDesc like '%" + SearchQuery + "%' ";

            }
            sql = "from Billmaster "
                    + "" + Subsql + " order by billDesc";


            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();


            while (itr.hasNext()) {

                masterForm = new masterForm();
                bm = (Billmaster) itr.next();
                masterForm.setId(Long.toString(bm.getBillID()));
                masterForm.setName(bm.getBillDesc());
                masterForm.setStatus(bm.getIsActive() + "");
                masterForm.setPercentage(bm.getDiscount() == null ? "" : Float.toString(bm.getDiscount()));
                dataList.add(masterForm);

            }
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
        return dataList;

    }

    public String addBillMaster(String name, String percentage, String userId) throws SQLException {
        String results = "billExist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Billmaster bm = new Billmaster();
        try {
            String check = check_in_Db(name, "Billmaster", "billDesc", "");


            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                bm.setBillDesc(name);  //.toUpperCase()
                bm.setLastUpdatedDate(new java.util.Date());
                bm.setIsActive('Y');
                bm.setDiscount(Float.parseFloat(percentage));
                hrbsession.save(bm);
                hrbsession.getTransaction().commit();

                //pstmt = conn.prepareStatement("insert into MSW_billmaster (BillDesc,LastUpdatedDate,isActive,discount) values(?,?,?,?)");
//                pstmt.setString(1, name.toUpperCase());
//                pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
//                pstmt.setString(3, "Y");
//                pstmt.setFloat(4, Float.parseFloat(percentage));
//                rowsUpdate = pstmt.executeUpdate();
//
                results = "Success@@Bill Description'" + name + "' has been Successfully Added.";


            } else {
                results = "Failure@@Bill Description'" + name + "' Already Exists.";


            }

        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Bill Description, Please contact system Administrator.";

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
        return results;


    }

    public String UpdateBillMaster(String status, String id, String name, String type, String userId, String billDesc, String percentage) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */

        // // System.out.println("name" + name);
        // // System.out.println("percentage" + percentage);
        String result = "";
        String newStatus = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Query query = null;
        Billmaster bm = null;


        try {
            hrbsession.beginTransaction();
            bm = (Billmaster) hrbsession.load(Billmaster.class, Long.parseLong(id));
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";


                } else {
                    newStatus = "Y";


                }

                //pstmt = conn.prepareStatement("UPDATE MSW_billmaster SET isActive=?,LastUpdatedDate=? WHERE BillID=? ");




                bm.setIsActive(newStatus.trim().charAt(0));

                bm.setLastUpdatedDate(new java.util.Date());

                hrbsession.saveOrUpdate(bm);



                result = "Success@@isActive has been updated Successfully.";


            } else {
                // // System.out.println("id" + id);
                String check = check_in_Db(name, "Billmaster", "billDesc", " and billID <> " + id + "");


                if (check.equalsIgnoreCase("notexist")) {

                    bm.setBillDesc(name.trim());
                    bm.setLastUpdatedDate(new java.util.Date());
                    bm.setDiscount(Float.parseFloat(percentage));
                    hrbsession.saveOrUpdate(bm);

                    //pstmt = conn.prepareStatement("UPDATE MSW_billmaster SET BillDesc=?,LastUpdatedDate=?,discount=? WHERE BillID=? ");
//                    pstmt.setString(1, name.trim().toUpperCase());
//                    pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
//                    pstmt.setFloat(3, Float.parseFloat(percentage));
//                    pstmt.setString(4, id);

                    //  // System.out.println("here");

                    //   // System.out.println("rowsUpdate" + rowsUpdate);

                    result = "Success@@Bill Description '" + name + "' has been updated Successfully.";


                } else {
                    result = "Failure@@Bill Description '" + name + "' Already Exists.";


                }
            }
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Bill Description, Please contact system Administrator.";
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
        return result;


    }

///////////////////////////////////////***Other Job Work MASTER***////////////////////////////////////////////////
    public ArrayList<masterForm> getOtherJobWorkList(masterForm masterForm, String nameSrch) throws SQLException {
        Otherjobworkmaster om = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;



        try {
            dataList = new ArrayList<masterForm>();



            if (!SearchQuery.equals("")) {
                Subsql = " where workDesc like '%" + SearchQuery + "%' ";


            }
            sql = " from Otherjobworkmaster "
                    + "" + Subsql + " order by workDesc";

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();


            while (itr.hasNext()) {
                masterForm = new masterForm();
                om = (Otherjobworkmaster) itr.next();
                masterForm.setId(Long.toString(om.getWorkID()));
                masterForm.setName(om.getWorkDesc());
                masterForm.setStatus(om.getIsActive() + "");
                dataList.add(masterForm);


            }
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
        return dataList;


    }

    public String addOtherJobWork(String name, String userId) throws SQLException {
        String results = "otherWorkExist";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        String sql = "";
        int seqid = 0;
        Otherjobworkmaster om = null;

        try {
            String check = check_in_Db(name, "Otherjobworkmaster", "workDesc", "");


            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                sql = "select max(seqNo) from Otherjobworkmaster";
                query = hrbsession.createQuery(sql);
                itr = query.list().iterator();
                String s = "";

                if (itr.hasNext()) {

                    seqid = (Integer) itr.next();


                }
                om = new Otherjobworkmaster();
                om.setWorkDesc(name);
                om.setLastUpdatedDate(new java.util.Date());
                om.setIsActive('Y');
                om.setSeqNo(seqid + 1);
                hrbsession.save(om);
                hrbsession.getTransaction().commit();
//                pstmt = conn.prepareStatement("insert into MSW_otherjobworkmaster (WorkDesc,LastUpdatedDate,isActive,seqNo) values(?,?,?,?)");
//                pstmt.setString(1, name);
//                pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
//                pstmt.setString(3, "Y");
//                pstmt.setInt(4, (seqid + 1));
//                rowsUpdate = pstmt.executeUpdate();
                results = "Success@@Work Description'" + name + "' has been Successfully Added.";


            } else {
                results = "Failure@@Work Description'" + name + "' Already Exists.";


            }

        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Work Description, Please contact system Administrator.";
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
        return results;


    }

    public String UpdateOtherJobWork(String status, String id, String name, String type, String userId, String workDesc) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        String result = "";
        PreparedStatement pstmt = null;
        String newStatus = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Otherjobworkmaster om = null;
        try {
            hrbsession.beginTransaction();
            om = (Otherjobworkmaster) hrbsession.load(Otherjobworkmaster.class, Long.parseLong(id));
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";


                } else {
                    newStatus = "Y";


                }
                // pstmt = conn.prepareStatement("UPDATE MSW_otherjobworkmaster SET isActive=?,LastUpdatedDate=? WHERE WorkID=? ");



                if (status.equals("Y")) {
                    om.setIsActive(newStatus.charAt(0));
                    om.setLastUpdatedDate(new java.util.Date());
                    hrbsession.saveOrUpdate(om);
                    hrbsession.getTransaction().commit();
//                    pstmt.setString(1, newStatus.trim());
//                    pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
//                    pstmt.setString(3, id);
//                    rowsUpdate = pstmt.executeUpdate();


                } else {
                    om.setIsActive(newStatus.charAt(0));
                    om.setLastUpdatedDate(new java.util.Date());
                    hrbsession.saveOrUpdate(om);
                    hrbsession.getTransaction().commit();


                }
                result = "Success@@isActive has been updated Successfully.";


            } else {

                String check = check_in_Db(name, "Otherjobworkmaster", "workDesc", " and workID <> " + id + "");


                if (check.equalsIgnoreCase("notexist")) {
                    om.setWorkDesc(name);
                    om.setLastUpdatedDate(new java.util.Date());
                    hrbsession.saveOrUpdate(om);
                    hrbsession.getTransaction().commit();


//                    pstmt = conn.prepareStatement("UPDATE MSW_otherjobworkmaster SET WorkDesc=?,LastUpdatedDate=? WHERE WorkID=? ");
//                    pstmt.setString(1, name.trim());
//                    pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
//                    pstmt.setString(3, id);
//                    rowsUpdate = pstmt.executeUpdate();
                    result = "Success@@Work Description '" + name + "' has been updated Successfully.";


                } else {
                    result = "Failure@@Work Description '" + name + "' Already Exists.";


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Work Description, Please contact system Administrator.";
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
        return result;


    }

    public void getFunctionalityList(masterForm masterForm) throws SQLException {

        String sql = null;
        Connection con = null;
        ArrayList<LabelValueBean> functionListArray = null;
        LinkedHashSet<LabelValueBean> functionList = null;
        LabelValueBean lv = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String tmp = "";

        try {


            functionList = new LinkedHashSet();
            functionListArray = new ArrayList();
            //stmt = conn.createStatement();
            sql = "select functionalityName from GEN_hesfunctionality";
            // rs = stmt.executeQuery(sql);
            Query query = hrbsession.createSQLQuery(sql);
            Iterator itr = query.list().iterator();

            while (itr.hasNext()) {

                tmp = (String) itr.next();
                lv = new LabelValueBean(tmp, tmp);
                // functionList.add(lv);
                functionListArray.add(lv);


            } // masterForm.setFunctionlist(functionList);
            masterForm.setLabelList(functionListArray);


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

    public ArrayList<masterForm> gettableDesc(masterForm masterForm, String functionName) throws SQLException {
        Statement stmt = null, stmt1 = null;
        ResultSet rs = null, rs1 = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, sql1 = null;
        String tablename = "";



        try {
            dataList = new ArrayList<masterForm>();
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();

            sql = "select tableName from GEN_hesfunctionality where functionalityname='" + functionName + "'";
            rs = stmt.executeQuery(sql);


            if (rs.next()) {
                tablename = rs.getString(1);


            }
            masterForm.setTablename(tablename);


            if (tablename.equalsIgnoreCase("MSW_jobtypemaster")) {

                sql1 = "select seqNo,JobTypeDesc from MSW_jobtypemaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }

            }
            if (tablename.equalsIgnoreCase("MSW_servicetypemaster")) {

                sql1 = "select seqNo,ServiceTypeDesc from ServiceTypeMaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }

            }
            if (tablename.equalsIgnoreCase("MSW_jobcardstatusmaster")) {

                sql1 = "select seqNo,StatusDesc from MSW_jobcardstatusmaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }

            }
            if (tablename.equalsIgnoreCase("JobLocationMaster")) {

                sql1 = "select seqNo,LocationDesc from MSW_joblocationmaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }

            }
            if (tablename.equalsIgnoreCase("NextServiceMaster")) {

                sql1 = "select seqNo,NextServiceDesc from NextServiceMaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }

            }
            if (tablename.equalsIgnoreCase("OwnerDrivenMaster")) {

                sql1 = "select seqNo,OwnerDrivenDesc from OwnerDrivenMaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }
            }
            if (tablename.equalsIgnoreCase("MSW_otherjobworkmaster")) {

                sql1 = "select seqNo,WorkDesc from MSW_otherjobworkmaster where isActive='Y' order by seqNo asc";

                rs1 = stmt1.executeQuery(sql1);


                while (rs1.next()) {
                    masterForm = new masterForm();
                    masterForm.setTabseqid(rs1.getInt(1));
                    masterForm.setTabledesc(rs1.getString(2));
                    dataList.add(masterForm);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;


                }
                if (rs1 != null) {
                    rs1.close();
                    rs1 = null;


                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;


                }
                if (stmt1 != null) {
                    stmt1.close();
                    stmt1 = null;


                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return dataList;


    }

    public void updateTable(Vector tempVector, String tablename) throws Exception, SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Iterator itr = null;
        Criteria cr = null;
        Query q = null;
        // = null;
        try {
            hrbsession.beginTransaction();
            if (tablename.equalsIgnoreCase("Jobtypemaster")) {

                // pstmt = conn.prepareStatement("update Jobtypemaster set seqNo=? where jobTypeDesc=?");

                q = hrbsession.createQuery("select distinct jobTypeID from  Jobtypemaster where jobTypeDesc=?");

                int id = 0;


                for (int i = 0; i < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
                    //q.setInteger(1, i + 1);
                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Integer) itr.next();
                    }
                    Jobtypemaster jm = (Jobtypemaster) hrbsession.load(Jobtypemaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);


                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));




                }
            }
            if (tablename.equalsIgnoreCase("Servicetypemaster")) {

                // pstmt = conn.prepareStatement("update Servicetypemaster set SeqNo=? where ServiceTypeDesc=?");
                q = hrbsession.createQuery("select distinct serviceTypeID from  Servicetypemaster where serviceTypeDesc=?");

                int id = 0;


                for (int i = 0; i < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
                    // pstmt.setInt(1, i + 1);
                    // pstmt.setString(2, splitstring[1].trim());
                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
                    //   rowsUpdate = pstmt.executeUpdate();

                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Integer) itr.next();
                    }
                    Servicetypemaster jm = (Servicetypemaster) hrbsession.load(Servicetypemaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);


                }
            }
            if (tablename.equalsIgnoreCase("Jobcardstatusmaster")) {

                // pstmt = conn.prepareStatement("update Jobcardstatusmaster set SeqNo=? where StatusDesc=?");
                q = hrbsession.createQuery("select distinct statusID from  Jobcardstatusmaster where statusDesc=?");

                long id = 0;


                for (int i = 0; i < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
//                    pstmt.setInt(1, i + 1);
//                    pstmt.setString(2, splitstring[1].trim());
//                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
//                    rowsUpdate = pstmt.executeUpdate();
                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Long) itr.next();
                    }
                    Jobcardstatusmaster jm = (Jobcardstatusmaster) hrbsession.load(Jobcardstatusmaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);


                }
            }
            if (tablename.equalsIgnoreCase("Joblocationmaster")) {

                // pstmt = conn.prepareStatement("update Joblocationmaster set SeqNo=? where LocationDesc=?");
                q = hrbsession.createQuery("select distinct locationID from  Joblocationmaster where locationDesc=?");

                long id = 0;



                for (int i = 0; i < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
//                    pstmt.setInt(1, i + 1);
//                    pstmt.setString(2, splitstring[1].trim());
//                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
//                    rowsUpdate = pstmt.executeUpdate();

                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Long) itr.next();
                    }
                    Joblocationmaster jm = (Joblocationmaster) hrbsession.load(Joblocationmaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);

                }
            }
            if (tablename.equalsIgnoreCase("Nextservicemaster")) {

                //pstmt = conn.prepareStatement("update Nextservicemaster set SeqNo=? where NextServiceDesc=?");
                q = hrbsession.createQuery("select distinct nextServiceID from  Nextservicemaster where nextServiceDesc=?");

                long id = 0;



                for (int i = 0; i
                        < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
//                    pstmt.setInt(1, i + 1);
//                    pstmt.setString(2, splitstring[1].trim());
//                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
//                    rowsUpdate = pstmt.executeUpdate();

                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Long) itr.next();
                    }
                    Nextservicemaster jm = (Nextservicemaster) hrbsession.load(Nextservicemaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);

                }
            }

            if (tablename.equalsIgnoreCase("Ownerdrivenmaster")) {

                //  pstmt = conn.prepareStatement("update Ownerdrivenmaster set SeqNo=? where OwnerDrivenDesc=?");
                q = hrbsession.createQuery("select distinct ownerDrivenID from  Ownerdrivenmaster where ownerDrivenDesc=?");

                long id = 0;


                for (int i = 0; i < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
//                    pstmt.setInt(1, i + 1);
//                    pstmt.setString(2, splitstring[1].trim());
//                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
//                    rowsUpdate = pstmt.executeUpdate();

                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Long) itr.next();
                    }
                    Ownerdrivenmaster jm = (Ownerdrivenmaster) hrbsession.load(Ownerdrivenmaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);

                }

            }

            if (tablename.equalsIgnoreCase("Otherjobworkmaster")) {

                // pstmt = conn.prepareStatement("update Otherjobworkmaster set SeqNo=? where WorkDesc=?");
                q = hrbsession.createQuery("select distinct workID from  Otherjobworkmaster where WorkDesc=?");

                long id = 0;



                for (int i = 0; i
                        < tempVector.size(); i++) {
                    String[] splitstring = tempVector.elementAt(i).toString().split("@@");
//                    pstmt.setInt(1, i + 1);
//                    pstmt.setString(2, splitstring[1].trim());
//                    // pstmt.setInt(3, Integer.parseInt(splitstring[0]));
//                    rowsUpdate = pstmt.executeUpdate();

                    q.setString(0, splitstring[1].trim());

                    itr = q.list().iterator();
                    if (itr.hasNext()) {
                        id = (Long) itr.next();
                    }
                    Otherjobworkmaster jm = (Otherjobworkmaster) hrbsession.load(Otherjobworkmaster.class, id);
                    jm.setSeqNo(i + 1);
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("seqNo",i + 1));
                    // cr = hrbsession.createCriteria(Jobtypemaster.class).add(Restrictions.eq("jobTypeDesc",splitstring[1].trim()));

                    hrbsession.saveOrUpdate(jm);

                }
            }


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

////////////////////////////////***Consequence Master***///////////////////////////////////////////////////
    public ArrayList<masterForm> getConsequenceList(masterForm masterForm, String nameSrch) throws SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Consequencemaster cm = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;


        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " where consequenceDesc  like '%" + SearchQuery + "%' ";  //consequenceCode
            }
            sql = "from Consequencemaster " + Subsql + " order by consequenceDesc";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            while (itr.hasNext()) {
                cm = (Consequencemaster) itr.next();
                masterForm = new masterForm();
                masterForm.setConsequenceCode(cm.getConsequenceCode());
                masterForm.setConsequenceDesc(cm.getConsequenceDesc());
                masterForm.setStatus(cm.getIsActive() + "");
//                masterForm.setPrimary_id(Integer.parseInt(cm.getConsequenceid() + ""));
                dataList.add(masterForm);

            }
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
        return dataList;


    }

    public String addConsequenceMaster(String consequenceCode, String consequenceDesc, String userId) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Consequencemaster cm = null;
        String results = "";
        long id = 0;

        try {
            hrbsession.beginTransaction();
            String check = check_in_Db(consequenceCode, "Consequencemaster", "consequenceCode", "");
            if (check.equalsIgnoreCase("notexist")) {

//                Criteria cr = hrbsession.createCriteria(Consequencemaster.class);
//                cr.setProjection(Projections.max("consequenceid"));
//                if (!cr.list().isEmpty()) {
//                    if (cr.list().get(0) != null) {
//                        id = Long.parseLong(cr.list().get(0).toString());
//                    }
//                    id = id + 1;
//                }
                cm = new Consequencemaster();
                cm.setConsequenceCode(consequenceCode.trim());
                cm.setConsequenceDesc(consequenceDesc.trim());
                // cm.setConsequenceid(id);
                cm.setIsActive('Y');
                cm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                cm.setLastUpdatedBy(userId);
                hrbsession.save(cm);
                hrbsession.getTransaction().commit();
                results = "Success@@Consequence Code '" + consequenceCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Consequence Code '" + consequenceCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Consequence, Please contact system Administrator.";
        }finally {
        	if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
		}
        return results;
    }

    public String UpdateConsequenceMaster(String status, String id, String userId, String consequenceCode, String consequenceDesc, String type, int consequence_id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Application.
         */
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String newStatus = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Consequencemaster cm = null;


        try {
            hrbsession.beginTransaction();

            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";


                } else {
                    newStatus = "Y";


                }

                cm = (Consequencemaster) hrbsession.load(Consequencemaster.class, consequenceCode);
                cm.setIsActive(newStatus.charAt(0));
                cm.setLastUpdatedDate(new java.util.Date());
                cm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(cm);
                hrbsession.getTransaction().commit();
                result = "Success@@Application '" + consequenceCode + "' has been updated Successfully.";
            } else {
                String oldConsequenceCode = getNameById(id, "consequenceCode", "Consequencemaster", "consequenceCode");
                String check = "";
                if (!consequenceCode.equals("")) {
                    check = check_in_Db(consequenceCode, "Consequencemaster", "consequenceCode", " and consequence_id <>'" + consequence_id + "'");
                } else {
                    check = check_in_Db(id, "Consequencemaster", "consequenceCode", " and consequence_id <>'" + consequence_id + "'");
                }
                if (check.equalsIgnoreCase("notexist")) {


                    cm = (Consequencemaster) hrbsession.load(Consequencemaster.class, oldConsequenceCode);

                    cm.setConsequenceDesc(consequenceDesc);
                    cm.setLastUpdatedDate(new java.util.Date());
                    cm.setLastUpdatedBy(userId);
                    hrbsession.saveOrUpdate(cm);
                    hrbsession.getTransaction().commit();
                    if (!consequenceCode.equals("")) {
                        result = "Success@@Consequence '" + consequenceCode + "' has been updated Successfully.";


                    } else {
                        result = "Success@@Consequence '" + oldConsequenceCode + "' has been updated Successfully.";


                    }
                } else {
                    if (!consequenceCode.equals("")) {
                        result = "Failure@@Consequence '" + consequenceCode + "' Already Exists.";


                    } else {
                        result = "Failure@@Consequence '" + oldConsequenceCode + "' Already Exists.";


                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Consequence, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();

        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Consequence, Please contact system Administrator.";


            }
        }
        return result;


    }

    /**
     * *******************************************************************************
     * Author - avinash.pandey
     * <br>Method that returns a vector of ConsequenceCode and ConsequenceDesc
     *
     * @return String
     *
     ********************************************************************************
     */
    public String getAssignedConsequenceCode(masterForm mf) {
        String assigned_sql = "", available_sql = "";
        String MHparamStr = "";

        Consequencemaster cm = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;


        int id = 0;


        try {
//            available_sql = "select ConsequenceCode,ConsequenceDesc from MSW_consequencemaster where ConsequenceCode not in "
//                    + "(select ConsequenceCode from MSW_causalcodevsconsequencecode where CausalCode='" + mf.getCausalCode() + "') order by ConsequenceDesc";
//
//            assigned_sql = "select v.ConsequenceCode,i.ConsequenceDesc from MSW_causalcodevsconsequencecode v,MSW_consequencemaster i where"
//                    + " v.CausalCode='" + mf.getCausalCode() + "' and v.ConsequenceCode=i.ConsequenceCode order by i.ConsequenceDesc";

            available_sql = "select cm.consequenceCode,cm.consequenceDesc from Consequencemaster cm where cm.consequenceCode not in "
                    + "(select cvc.consequenceCode from Causalcodevsconsequencecode cvc where cvc.causalCode='" + mf.getCausalCode() + "') order by cm.consequenceDesc";

            assigned_sql = "select cvc.consequenceCode,cm.consequenceDesc from Causalcodevsconsequencecode cvc,Consequencemaster cm where"
                    + " cvc.causalCode='" + mf.getCausalCode() + "' and cvc.consequenceCode=cm.consequenceCode order by cm.consequenceDesc";


            query = hrbsession.createQuery(assigned_sql);
            itr = query.list().iterator();



            while (itr.hasNext()) {
                Object ob[] = (Object[]) itr.next();
                MHparamStr += ob[0].toString() + "|" + ob[1].toString() + "@@";


            }
            MHparamStr += "##";
            query = hrbsession.createQuery(available_sql);
            itr = query.list().iterator();

            //  rs = stmt.executeQuery(available_sql);


            while (itr.hasNext()) {
                Object ob[] = (Object[]) itr.next();
                MHparamStr += ob[0].toString() + "|" + ob[1].toString() + "@@";


            }

        } catch (Exception ae) {
            ae.printStackTrace();
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
        return MHparamStr;


    }

    /**
     * *******************************************************************************
     * Author - Avinash Pandey
     * <br>Method to insert item group vs item
     *
     * @param itemgroupID, item_Id[]
     * @return String
     *
     ********************************************************************************
     */
    public String setCausalcodeVSconsequenceCode(String causalCode, String[] consequencecode, String userId) {
        String results = "";


        boolean isUpdate = false;

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        //Iterator itr=null
        Causalcodevsconsequencecode cc = null;
        try {
            hrbsession.beginTransaction();
            query = hrbsession.createQuery("delete from Causalcodevsconsequencecode where causalCode=?");
            query.setString(0, causalCode);
            query.executeUpdate();


            if (consequencecode != null) {


//                pstmt = conn.prepareStatement("insert into MSW_causalcodevsconsequencecode values(?,?,?,?)");


                for (int i = 0; i < consequencecode.length; i++) {
                    cc = new Causalcodevsconsequencecode();
                    cc.setCausalCode(causalCode);
                    cc.setConsequenceCode(consequencecode[i]);
                    cc.setLastUpdatedDate(new java.util.Date());
                    cc.setCreatedBy(userId);
                    hrbsession.save(cc);
//                    pstmt.setString(1, causalCode);
//                    pstmt.setString(2, consequencecode[i]);
//                    pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
//                    pstmt.setString(4, userId);
//                    pstmt.addBatch();


                }


            }
            hrbsession.getTransaction().commit();
            results = "Success@@Consequence Code have been Successfully Assigned .";



        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to assign Consequence Code, Please contact system Administrator.";
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
        return results;


    }

    public LinkedHashSet<LabelValueBean> getContentList() {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ItlContentMaster im = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String sql = " FROM ItlContentMaster where isActive='Y' order by contentDesc";

        query = hrbsession.createQuery(sql);
        itr = query.list().iterator();

        try {
            LabelValueBean lv = null;
            while (itr.hasNext()) {
                im = (ItlContentMaster) itr.next();
                name = im.getContentDesc() == null ? "" : im.getContentDesc().trim();
                id = im.getContentId() == null ? "" : Long.toString(im.getContentId());
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
                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    /**
     * ************************CONTENT MASTER*************************************
     */
    public ArrayList<masterForm> getContentList(masterForm masterForm, String nameSrch) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Query query = null;
        Iterator itr = null;


        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where contentDesc like '%" + SearchQuery + "%' ";


            }
            sql = " from ItlContentMaster "
                    + "" + Subsql + " order by contentDesc";

            //rs = stmt.executeQuery(sql);

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                masterForm = new masterForm();
                ItlContentMaster cm = (ItlContentMaster) itr.next();
                masterForm.setId(Long.toString(cm.getContentId()));
                masterForm.setName(cm.getContentDesc());
                masterForm.setStatus(cm.getIsActive() + "");
                dataList.add(masterForm);


            }
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
        return dataList;


    }

    public String addContentMaster(String[] contentList, String userId) throws SQLException {
        String results = "contentExist";

        String contents[] = contentList.clone();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        //ItlContentMaster im = null;
        try {
            hrbsession.beginTransaction();


            for (int i = 0; i < contents.length; i++) {
                String check = check_in_Db_HQL(contents[i], "ItlContentMaster", "contentDesc", "", hrbsession);
                if (check.equalsIgnoreCase("notexist")) {
                    ItlContentMaster im = new ItlContentMaster();
                    im.setContentDesc(contents[i]);
                    im.setLastUpdatedDate(new java.util.Date());
                    im.setIsActive('Y');
                    im.setLastUpdatedBy(userId); //updated by
                    hrbsession.save(im);
                    results = "Success@@Content Description has been Successfully Added.";
                } else {
                    results = "Failure@@Content Description '" + contents[i] + "' Already Exists.";
                }
            }
            hrbsession.getTransaction().commit();
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Content Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateContentMaster(String status, String id, String name, String type, String userId) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        String result = "";
        PreparedStatement pstmt = null;
        String newStatus = "";
        ResultSet rs = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ItlContentMaster im = null;

        try {
            hrbsession.beginTransaction();
            im = (ItlContentMaster) hrbsession.load(ItlContentMaster.class, Long.parseLong(id));
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                if (status.equals("Y")) {
                    im.setIsActive(newStatus.charAt(0));
                    im.setLastUpdatedDate(new java.util.Date());
                    im.setLastUpdatedBy(userId);
                    hrbsession.saveOrUpdate(im);
                } else {
                    im.setIsActive(newStatus.charAt(0));
                    im.setLastUpdatedDate(new java.util.Date());
                    im.setLastUpdatedBy(userId);
                    hrbsession.saveOrUpdate(im);
                }
                result = "Success@@isActive has been updated Successfully.";
            } else {
                String check = check_in_Db(name, "ItlContentMaster", "contentDesc", " and contentId <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    im.setContentDesc(name.trim());
                    im.setLastUpdatedDate(new java.util.Date());
                    im.setLastUpdatedBy(userId);
                    hrbsession.saveOrUpdate(im);
                    result = "Success@@Content Description '" + name + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Content Description '" + name + "' Already Exists.";
                }
            }
            hrbsession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Bill Description, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                hrbsession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String uploadContentMaster(String filename, String user_id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Status Desc.
         */
        List usersList = new ArrayList();
        PreparedStatement pstmt = null;



        try {
            // Read excel file and store email ids in mailIdsArray
            InputStream input = new BufferedInputStream(new FileInputStream(filename));
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);

            Iterator rows = sheet.rowIterator();


            while (rows.hasNext()) {
                //RegistrationUser user= new RegistrationUser();
                HSSFRow row = (HSSFRow) rows.next();



                if (row.getCell(0) != null && !row.getCell(0).equals("Content Desc")) {

                    //  // System.out.println("excel data" + row.getCell(0));
                    pstmt = conn.prepareStatement("insert into MSW_checklist_content_master (Content_Desc,LastUpdatedDate,isActive,LastUpdatedBy) values(?,?,?,?)");
                    pstmt.setString(1, row.getCell(0).toString());
                    pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
                    pstmt.setString(3, "Y");
                    pstmt.setString(4, user_id);
                    rowsUpdate = pstmt.executeUpdate();


                }

            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return "uploadsuccess";


    }

    public ArrayList<masterForm> getSubContentList(masterForm masterForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;



        try {
            dataList = new ArrayList<masterForm>();
            stmt = conn.createStatement();


            if (!SearchQuery.equals("")) {
                Subsql = " and SUB_CONTENT_DESC like '%" + SearchQuery + "%' ";


            }
            sql = "select sc.SUB_CONTENT_ID,sc.SUB_CONTENT_DESC,sc.isActive,sc.CONTENT_ID,cm.CONTENT_DESC from MSW_checklist_subcontent_master as sc,MSW_checklist_content_master cm"
                    + " where sc.CONTENT_ID=cm.CONTENT_ID " + Subsql + "  order by sc.SUB_CONTENT_DESC";

            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                masterForm = new masterForm();
                masterForm.setId(rs.getString(1).trim());
                masterForm.setName(rs.getString(2).trim());
                masterForm.setStatus(rs.getString(3).trim());
                masterForm.setDescription(rs.getString(4).trim());
                masterForm.setContentdesc(rs.getString(5).trim());
                dataList.add(masterForm);


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
        return dataList;


    }

    public String addSubContentMaster(String[] subcontentList, String userId, String content_id) throws SQLException {
        String results = "";
        String result2 = "";
        String subcontentDescList[] = subcontentList.clone();//name.split(":");
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ItlSubContentMaster itlsub = null;
        ItlContentMaster itl = null;

        try {
            for (int i = 0; i < subcontentDescList.length; i++) {
                String check = check_in_Db(subcontentDescList[i], "ItlSubContentMaster", "subContentDesc", "and contentId =' " + content_id + "'");

                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    itlsub = new ItlSubContentMaster();
                    itlsub.setSubContentDesc(subcontentDescList[i]);
                    itlsub.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                    itlsub.setIsActive('Y');
                    itlsub.setLastUpdatedBy(userId);
                    itl = (ItlContentMaster) hrbsession.load(ItlContentMaster.class, Long.parseLong(content_id));
                    // itlsub.setSubContentId(Long.valueOf(content_id).longValue());
                    itlsub.setContentId(itl);

                    hrbsession.save(itlsub);
                    hrbsession.getTransaction().commit();
                    result2 = "Sub content description has been Successfully Added. ";
                } else {
                    results = results + " " + subcontentDescList[i];

                }
            }

            if (!results.equals("")) {
                results = "Content and subcontent already exist...";
            }
            results = "Success@@" + result2 + results;
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to update sub content, Please contact system Administrator.";
        }finally {
        	if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
		}
        return results;
    }

    public String UpdateSubContentMaster(String status, String id, String name, String type, String userId, String contentDesc, String content_id) throws SQLException {
        String result = "";
        ItlContentMaster itl = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        char newStatus = 'N';
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                ItlSubContentMaster nsmstatus = (ItlSubContentMaster) hrbsession.load(ItlSubContentMaster.class, Long.parseLong(id));
                nsmstatus.setIsActive(newStatus);
                nsmstatus.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                nsmstatus.setLastUpdatedBy(userId);
                itl = (ItlContentMaster) hrbsession.load(ItlContentMaster.class, Long.parseLong(content_id));
                nsmstatus.setContentId(itl);
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else {
                String check = check_in_Db(name, "ItlSubContentMaster", "subContentDesc", " and contentId = " + content_id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    ItlSubContentMaster nsmstatus = (ItlSubContentMaster) hrbsession.load(ItlSubContentMaster.class, Long.parseLong(id));
                    nsmstatus.setSubContentDesc(contentDesc.trim());
                    nsmstatus.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                    nsmstatus.setLastUpdatedBy(userId);
                    itl = (ItlContentMaster) hrbsession.load(ItlContentMaster.class, Long.parseLong(content_id));
                    nsmstatus.setContentId(itl);//adding content
                    hrbsession.update(nsmstatus);
                    hrbsession.getTransaction().commit();
                    result = "Success@@Sub content description has been updated Successfully.";
                } else {
                    result = "Failure@@Sub content description and Content already exit.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update sub content, Please contact administrator.";
        }finally {
        	if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();  
            }
		}
        return result;
    }

    public ArrayList<masterForm> getSubAssemblyList(masterForm masterForm, String nameSrch) throws SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;


        try {
            dataList = new ArrayList<masterForm>();



            if (!SearchQuery.equals("")) {
                Subsql = " AND sam.subAssemblyDesc LIKE '%" + SearchQuery + "%' ";   //subAssemblyCode


            }
            sql = Get_vendormasterdetail + Subsql + " order by sam.subAssemblyDesc";

            Query query = hrbsession.createQuery(sql);

            List list = query.list();
            Iterator itr = list.iterator();



            while (itr.hasNext()) {
                Object[] o = (Object[]) itr.next();

                masterForm = new masterForm();
                masterForm.setAggCode(o[0].toString());
                masterForm.setAggDesc(o[1].toString());
                masterForm.setSubAssemblyCode(o[2].toString());
                masterForm.setSubAssemblyDesc(o[3].toString());
                masterForm.setStatus(o[4].toString());
                // masterForm.setPrimary_id(Integer.parseInt(o[5].toString()));
                dataList.add(masterForm);


            }
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
        return dataList;


    }

    public String addSubAssemblyMaster(String subassemblyCode, String subassemblyDesc, String aggCode, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query hql = null;


        try {
            String check = check_in_Db_HQL(subassemblyCode, "Subassemblymaster", "subAssemblyCode", "", hrbsession);
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                Subassemblymaster sam = new Subassemblymaster();
                Subaggregatemaster sagg = (Subaggregatemaster) hrbsession.load(Subaggregatemaster.class, aggCode);
                sam.setIsActive('Y');
                sam.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                sam.setLastUpdatedBy(userId);
                sam.setSubAssemblyCode(subassemblyCode.toUpperCase());
                sam.setSubAssemblyDesc(subassemblyDesc);
                sam.setSubAggCode(sagg);
                hrbsession.save(sam);
                hrbsession.getTransaction().commit();
                results = "Success@@Subassembly Code '" + subassemblyCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Subassembly Code '" + subassemblyCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Subassembly Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Subassembly Code, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String UpdateSubAssemblyMaster(String status, String id, String userId, String subassemblyCode, String subassemblyDesc, String aggCode, String type, String subassembly_id, String assemblyid) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Causal Code.
         */
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        hrbsession.beginTransaction();
        String result = "";
        char newStatus = 'Y';
        Subaggregatemaster sgm = null;
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                Subassemblymaster sam = (Subassemblymaster) hrbsession.load(Subassemblymaster.class, subassemblyCode);
                sam.setIsActive(newStatus);
                sam.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                sam.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(sam);
                hrbsession.getTransaction().commit();
                result = "Success@@Subassembly Code '" + subassemblyCode + "' has been updated Successfully.";
            } else {
                Subassemblymaster sam = (Subassemblymaster) hrbsession.get(Subassemblymaster.class, id);
                Subaggregatemaster sagg = (Subaggregatemaster) hrbsession.load(Subaggregatemaster.class, aggCode.split("@@")[0]);
                sam.setSubAssemblyDesc(subassemblyDesc);
                sam.setSubAggCode(sagg);
                sam.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                sam.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(sam);
                hrbsession.getTransaction().commit();
                result = "Success@@Subassembly Code '" + id + "' has been updated Successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Subassembly, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Subassembly, Please contact system Administrator.";
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getAggCode() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Aggregatemaster am = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String sql = "select distinct aggCode,aggDesc FROM Aggregatemaster where isActive='Y' order by aggDesc";

        try {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                am = (Aggregatemaster) itr.next();
                name = am.getAggDesc() == null ? "" : am.getAggDesc().trim();
                // id = Integer.toString(am.getAggid());
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
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;


    }

    //getLabourCodeList created on 13/05/14 by Megha
    public ArrayList<masterForm> getLabourCodeList(masterForm masterForm, String nameSrch, String cmpcode) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        String CompcodeFilter = cmpcode == null ? "" : cmpcode;


        try {
            dataList = new ArrayList<masterForm>();
            // // System.out.println("labor code:" + SearchQuery + "-compcode:" + CompcodeFilter);
            stmt = conn.createStatement();


            if (!SearchQuery.equals("")) {
                Subsql = " AND lm.LabourCodeId LIKE '%" + SearchQuery + "%' ";


            }
            if (!CompcodeFilter.equalsIgnoreCase("")) {
                Subsql = Subsql + " AND cm.CompCode LIKE '" + SearchQuery + "'";


            }
            sql = Get_LABOUR_CODE + Subsql + " order by LabourCodeDesc";
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                masterForm = new masterForm();
                masterForm.setCompCode(rs.getString(1));
                masterForm.setCompDesc(rs.getString(2));
                masterForm.setLabourCode(rs.getString(3));
                masterForm.setLabourDesc(rs.getString(4));
                masterForm.setStatus(rs.getString(5));
                masterForm.setPrimary_id(rs.getInt(6));
                masterForm.setLabourhrs(rs.getInt(7));
                dataList.add(masterForm);


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
        return dataList;


    }

    //getDefectCode for labour master created on 13/05/14 by Megha
    public LinkedHashSet<LabelValueBean> getDefectCode() {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        Complaintcodemaster cm = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String sql = Get_ComplaintCode;
        query = hrbsession.createQuery(sql);
        itr = query.list().iterator();

        try {
            LabelValueBean lv = null;


            while (itr.hasNext()) {
                cm = (Complaintcodemaster) itr.next();
                name = cm.getCompDesc() == null ? "" : cm.getCompDesc().trim();
                id = cm.getCompCode() == null ? "" : cm.getCompCode().trim();
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



                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    //addLabourCodeMaster created on 14/05/14 by Megha
    public String addLabourCodeMaster(String labourCode, String labourDesc, String compCode, String userId, int labourHrs) throws SQLException {
        String results = "";
        PreparedStatement pstmt = null;


        try {
            String check = check_in_Db(labourCode, "labour_hrs_master", "LabourCodeId", "");
//            // System.out.println(""+labourHrs+"-"+labourCode+"-"+labourDesc+"-"+compCode);


            if (check.equalsIgnoreCase("notexist")) {
                pstmt = conn.prepareStatement(Insert_LaborCode);
                pstmt.setString(1, labourCode.trim());
                pstmt.setString(2, labourDesc.trim());
                pstmt.setString(3, compCode.trim());
                pstmt.setInt(4, labourHrs);
                pstmt.setString(5, "Y");
                rowsUpdate = pstmt.executeUpdate();
                results = "Success@@Labor Code '" + labourCode + "' has been Successfully Added.";


            } else {
                results = "Failure@@Labor Code '" + labourCode + "' Already Exists.";


            }
            if (rowsUpdate > 0) {
                conn.commit();


            } else {
                conn.rollback();


            }
        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Labor Code, Please contact system Administrator.";


        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;


                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Defect Code, Please contact system Administrator.";


            }
        }
        return results;


    }

    //UpdateLaborCode created on 14/05/14 by Megha
    public String UpdateLaborCode(String status, String id, String userId, String laborcode, String laborDesc, String compCode, String type, int labor_id, int labourHrs) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular labor code.
         */
        String result = "";
        PreparedStatement pstmt = null, pstmt1 = null;
        ResultSet rs = null;
        String newStatus = "";


        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";


                } else {
                    newStatus = "Y";


                }
                pstmt = conn.prepareStatement(Update_LaborCode);
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, labourHrs);
                pstmt.setString(3, laborcode);
                rowsUpdate = pstmt.executeUpdate();
                result = "Success@@Labor Code '" + laborcode + "' has been updated Successfully.";


            } else {
                //String check = check_in_Db(compCode, "ComplaintCodeMaster", "CompCode", " and CompCode <> " + compCode + "");
                String oldlaborCode = getNameById(id, "LabourCodeId", "labour_hrs_master", "LabourCodeId");
                String check = "";


                if (!laborcode.equals("")) {
                    check = check_in_Db(laborcode, "labour_hrs_master", "LabourCodeId", " and Id <>'" + labor_id + "'");


                } else {
                    check = check_in_Db(id, "labour_hrs_master", "LabourCodeId", " and Id <>'" + labor_id + "'");


                }

                if (check.equalsIgnoreCase("notexist")) {
                    pstmt = conn.prepareStatement("UPDATE labour_hrs_master SET LabourCodeId=?,LabourCodeDesc=?,DefectCode=?,Hrs=? WHERE LabourCodeId=? and Id=?");


                    if (!laborcode.equals("")) {
                        pstmt.setString(1, laborcode.trim());


                    } else {
                        pstmt.setString(1, oldlaborCode.trim());


                    }
                    pstmt.setString(2, laborDesc.trim());
                    pstmt.setString(3, compCode.split("@@")[0]);
                    pstmt.setInt(4, labourHrs);
                    pstmt.setString(5, oldlaborCode);
                    pstmt.setInt(6, labor_id);
                    rowsUpdate = pstmt.executeUpdate();


                    if (!laborcode.equals("")) {
                        result = "Success@@Labor Code '" + laborcode + "' has been updated Successfully.";


                    } else {
                        result = "Success@@Labor Code '" + oldlaborCode + "' has been updated Successfully.";


                    }

                } else {
                    if (!compCode.equals("")) {
                        result = "Failure@@Labor Code '" + laborcode + "' Already Exists.";


                    } else {
                        result = "Failure@@Labor Code '" + oldlaborCode + "' Already Exists.";


                    }
                }
            }
            if (rowsUpdate > 0) {
                conn.commit();


            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Complaint, Please contact system Administrator.";


        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;


                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Complaint, Please contact system Administrator.";


            }
        }
        return result;


    }

    public ArrayList<masterForm> getSubAggregateList(masterForm masterForm, String nameSrch, String aggcode) throws SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        try {
            dataList = new ArrayList<masterForm>();

            if (!SearchQuery.equals("")) {
                Subsql = " AND sm.subAggCode LIKE '%" + SearchQuery + "%' ";
            }
            sql = Get_SubAggregate_Code + Subsql + " order by sm.subAggDesc";
            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();
            /*
            
            SELECT am.aggCode,am.aggDesc,sm.subAggCode, sm.subAggDesc, sm.isActive,sm.id,am.aggid" +
            " FROM Subaggregatemaster sm, Aggregatemaster am " +
            " WHERE sm.aggId=am.aggid
            
             */
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                masterForm = new masterForm();
                masterForm.setAggCode(o[0] == null ? "" : o[0].toString());
                masterForm.setAggDesc(o[1] == null ? "" : o[1].toString());
                masterForm.setSubaggCode(o[2] == null ? "" : o[2].toString());
                masterForm.setSubaggDesc(o[3] == null ? "" : o[3].toString());
                masterForm.setStatus(o[4] == null ? "" : o[4].toString());
                //masterForm.setPrimary_id(o[5] == null ? 0 : Integer.parseInt(o[5].toString()));
                // masterForm.setId("" + o[6] == null ? "" : o[6].toString());
                dataList.add(masterForm);
            }
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
        return dataList;
    }

    //getAggregateWithIdCode created on 14/05/14 by Megha
    public LinkedHashSet<LabelValueBean> getAggregateCodeWithId() {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Statement stmt = null;
        ResultSet rs = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        StringBuilder temp = new StringBuilder();
        String sql = "select distinct aggCode,aggDesc FROM Aggregatemaster where isActive='Y' order by aggDesc";

        try {
            LabelValueBean lv = null;

            query = hrbsession.createQuery(sql);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Object[] am = (Object[]) itr.next();
//                temp.append(am[0] == null ? "" : am[0].toString().trim());
//                temp.append("[");
//                temp.append(am[1] == null ? "" : am[1].toString().trim());
//                temp.append("]");
//                name = temp.toString();
//                temp.delete(0, temp.length());
//                id = "" + am[2];


                id = am[0].toString() == null ? "" : am[0].toString().trim();
                name = am[1].toString() == null ? "" : am[1].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
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

                }

            } catch (Exception e) {
                e.printStackTrace();


            }
        }
        return result;


    }

    public String addSubAggregateMaster(String subaggCode, String subaggDesc, String aggid, String userId) throws SQLException {
        String results = "";
        PreparedStatement pstmt = null;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            String check = check_in_Db(subaggCode, "Subaggregatemaster", "subAggCode", "");
            if (check.equalsIgnoreCase("notexist")) {

                Subaggregatemaster sm = new Subaggregatemaster();
                Aggregatemaster am = (Aggregatemaster) hrbsession.load(Aggregatemaster.class, aggid);
                hrbsession.beginTransaction();
                sm.setSubAggCode(subaggCode.toUpperCase());
                sm.setSubAggDesc(subaggDesc);
                sm.setIsActive('Y');
                sm.setLastUpdateDate(new java.util.Date());
                sm.setLastUpdatedBy(userId);
                sm.setAggCode(am);
                hrbsession.save(sm);
                hrbsession.getTransaction().commit();
                results = "Success@@Sub-Aggregate Code '" + subaggCode + "' has been Successfully Added.";

            } else {
                results = "Failure@@Sub-Aggregate Code '" + subaggCode + "' Already Exists.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Sub-Aggregate Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Defect Code, Please contact system Administrator.";
            }
        }
        return results;


    }

    public String UpdateSubAggregateCode(String status, String oldsubagg_Id, String userId, String subaggcode, String subaggDesc, String aggCode, String type, int subagg_id) throws SQLException {

        String result = "";
        PreparedStatement pstmt = null, pstmt1 = null;
        ResultSet rs = null;
        Query hql = null;
        Session hrbsession = null;
        char newStatus = 'N';
        try {
            // System.out.println("aggCode " + aggCode);




            hrbsession = HibernateUtil.getSessionFactory().openSession();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }

                hrbsession.beginTransaction();

                // hql = hrbsession.createQuery("from Subaggregatemaster where subAggCode=?");
                ///   hql.setString(0, subaggcode);
                //   MSWsubaggregatemaster sam = (MSWsubaggregatemaster) hql.uniqueResult();

                Subaggregatemaster sam = (Subaggregatemaster) hrbsession.load(Subaggregatemaster.class, subaggcode);

                sam.setIsActive(newStatus);
                sam.setLastUpdateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                sam.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(sam);
                hrbsession.getTransaction().commit();
                result = "Success@@Sub-Aggregate Code '" + subaggcode + "' has been updated Successfully.";


            } else {
                hrbsession.beginTransaction();
                Subaggregatemaster sam = (Subaggregatemaster) hrbsession.load(Subaggregatemaster.class, oldsubagg_Id);
                Aggregatemaster am = (Aggregatemaster) hrbsession.load(Aggregatemaster.class, aggCode.split("@@")[0]);   //Integer.parseInt(aggCode.split("@@")[0])
                sam.setSubAggDesc(subaggDesc);
                sam.setAggCode(am);
                sam.setLastUpdateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                sam.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(sam);
                hrbsession.getTransaction().commit();
                result = "Success@@Sub-Aggregate Code '" + oldsubagg_Id + "' has been updated Successfully.";











                /*
                //String check = check_in_Db(compCode, "ComplaintCodeMaster", "CompCode", " and CompCode <> " + compCode + "");
                String oldsubaggCode = getNameById(oldsubagg_Id, "subAggCode", "Subaggregatemaster", "subAggCode");
                String check = "";
                
                
                if (!subaggcode.equals("")) {
                check = check_in_Db(subaggcode, "Subaggregatemaster", "subAggCode", " and id <>'" + subagg_id + "'");
                
                
                } else {
                check = check_in_Db(oldsubagg_Id, "Subaggregatemaster", "subAggCode", " and id <>'" + subagg_id + "'");
                
                
                } //                System.out.println(check+"-sub:"+subaggcode+"-"+oldsubaggCode);
                if (check.equalsIgnoreCase("notexist")) {
                //pstmt = conn.prepareStatement("UPDATE subaggregatemaster SET SubAggCode=?,SubAggDesc=?,AggId=? WHERE SubAggCode=? and Id=?");
                hrbsession.beginTransaction();
                MSWsubaggregatemaster sam = (MSWsubaggregatemaster) hrbsession.load(MSWsubaggregatemaster.class, subagg_id);
                System.out.println("aggCode"+aggCode);
                System.out.println("aggCode=="+aggCode.split("@@")[0]);
                
                MSWaggregatemaster am = (MSWaggregatemaster) hrbsession.load(MSWaggregatemaster.class, aggCode);   //Integer.parseInt(aggCode.split("@@")[0])
                
                
                
                if (!subaggcode.equals("")) {
                sam.setSubAggCode(subaggcode.trim());
                
                
                } else {
                //                        pstmt.setString(1, oldsubaggCode.trim());
                sam.setSubAggCode(oldsubaggCode.trim());
                
                }
                sam.setSubAggDesc(subaggDesc.trim());
                //sam.s(aggCode.split("@@")[0]);
                //   System.out.println("am " + am.getAggid());
                
                sam.setAggCode(am);
                sam.setLastUpdateDate(new java.util.Date());
                hrbsession.update(sam);
                hrbsession.getTransaction().commit();
                //                    pstmt.setString(2, subaggDesc.trim());
                //                   // pstmt.setString(3, aggCode.split("@@")[0]);
                //                    pstmt.setString(4, oldsubaggCode);
                //                    pstmt.setInt(5, subagg_id);
                //                    rowsUpdate = pstmt.executeUpdate();
                
                
                if (!subaggcode.equals("")) {
                result = "Success@@Sub-Aggregate Code '" + subaggcode + "' has been updated Successfully.";
                
                
                } else {
                result = "Success@@Sub-Aggregate Code '" + oldsubaggCode + "' has been updated Successfully.";
                
                
                }
                
                } else {
                if (!aggCode.equals("")) {
                result = "Failure@@Sub-Aggregate Code '" + subaggcode + "' Already Exists.";
                
                
                } else {
                result = "Failure@@Sub-Aggregate Code '" + oldsubaggCode + "' Already Exists.";
                
                
                }
                } */
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Complaint, Please contact system Administrator.";


        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;


                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Complaint, Please contact system Administrator.";


            }
        }
        return result;


    }

    //Comman method for LabelValueBean
//    public LinkedHashSet<LabelValueBean> getCommonLabelValue(String tableName, String fieldId, String fieldName, String filter, String whereCase) {
//
//
//        Statement stmt = null;
//        ResultSet rs = null;
//        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
//        String name = "", id = "";
//
//
//
//        if (whereCase.equals("")) {
//            whereCase = "where isActive='Y'";
//
//
//        }
//        String query = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";
//        System.out.println("" + query);
//
//
//        if (tableName.equals("MSW_eventmaster")) {
//
//            whereCase = whereCase + " and MONTH(StartDate) = MONTH(CURRENT_DATE)";
//
//
//        }
//        try {
//            LabelValueBean lv = null;
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(query);
//
//
//            while (rs.next()) {
//                name = rs.getString(2) == null ? "" : rs.getString(2).trim();
//                id = rs.getString(1) == null ? "" : rs.getString(1).trim();
//                lv = new LabelValueBean(name, id);
//                result.add(lv);
//
//
//            }
//        } catch (Exception ae) {
//            ae.printStackTrace();
//
//
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                    rs = null;
//
//
//                }
//                if (stmt != null) {
//                    stmt.close();
//                    stmt = null;
//
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//
//            }
//        }
//        return result;
//
//
//    }
    public LinkedHashSet<LabelValueBean> getCommonLabelValues(String tableName, String fieldId, String fieldName, String filter, String whereCase) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "", hql = "";



        if (whereCase.equalsIgnoreCase("nil")) {
            hql = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName;
        } else if (whereCase.equals("")) {
            whereCase = "where isActive='Y'";
            hql = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";
        } else if (!whereCase.equals("")) {
            hql = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";
        }
        // System.out.println("hql" + hql);
        Query query = hrbsession.createQuery(hql);
        // System.out.println("" + query);
        if (tableName.equals("Eventmaster")) {
            whereCase = whereCase + " and MONTH(startDate) = MONTH(CURRENT_DATE)";
        }
        try {
            LabelValueBean lv = null;

            List list = query.list();

            Iterator itr = list.iterator();



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

    //getDealerCode  created on 31/05/14 by Megha
    public LinkedHashSet<LabelValueBean> getDealerCode() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try {
            LabelValueBean lv = null;
            Query query = session.createQuery("select t.dealerCode,t.dealerName from Dealervslocationcode t where t.dealerCode not in ( select w.dealerCode from Wagemaster w )");
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                name = obj[1] == null ? "" : obj[1].toString().trim();
                id = obj[0] == null ? "" : obj[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getJobtypeList() {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String hql = "select distinct jobTypeID,jobTypeDesc FROM Jobtypemaster where isActive='Y' order by jobTypeDesc";
        try {
            LabelValueBean lv = null;

            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();

            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                name = o[0].toString() == null ? "" : o[0].toString().trim() + "[" + o[1].toString().trim() + "]";
                id = o[2].toString() == null ? "" : o[2].toString().trim();
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

    public void getFormCheckList(masterForm masterForm) {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        ContentFormBean cf = null;
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;
        int formid = 0;
        String subContentId = null;
        String sql = "";
        int contentIdTemp = 0;

        ArrayList<SubContentFormBean> subContentFormList = new ArrayList();

        SubContentFormBean dataBean = null;//new SubContentFormBean();
        ArrayList<SubContentFormBean> tempList = null;
        String[] data = masterForm.getStatus().split("@");
        masterForm.setJobType(Integer.parseInt(data[0]));
        masterForm.setModelCode(data[1]);
        // System.out.println(masterForm.getStatus() + "model family" + masterForm.getModelFamily() + " jobtype" + masterForm.getJobType());
        try {
            query = hrbsession.createQuery("select  f.id.formid from FormMaster f where f.id.modelcode=:modelCode and f.id.jobtypeid=:jobTypeId");
            query.setParameter("modelCode", masterForm.getModelFamily());
            query.setParameter("jobTypeId", masterForm.getJobType());
            Query qry = null;
            if (query.list().size() > 0) {
                formid = Integer.parseInt(query.list().get(0).toString());
                qry = hrbsession.getNamedQuery("call_FormCheckList");
                qry.setParameter("FormId", "" + formid);

            } else {
                qry = hrbsession.getNamedQuery("call_FormCheckList");
                qry.setParameter("FormId", "");
            }
            Iterator itr1 = qry.list().iterator();

            while (itr1.hasNext()) {
                FormCheckList view = (FormCheckList) itr1.next();
                //Object o[] = (Object[]) itr1.next();
                contentId = view.getContentId();
                cf = new ContentFormBean();

                cf.setContentId(contentId);
                cf.setContentDesc(view.getContentDesc());

                subContentId = "" + view.getSubContentId();

                tempList = treeMap.put(cf, subContentFormList);
                if (tempList == null) {
                    subContentFormList = new ArrayList();
                    treeMap.put(cf, subContentFormList);
                }
                if (subContentId != null) {
                    dataBean = new SubContentFormBean();
                    dataBean.setObservations("" + view.getOrderSeq());
                    dataBean.setSubContentId(subContentId);
                    dataBean.setSubContentDesc(view.getSubContentDesc());
                    dataBean.setTextBoxOption(view.getFormId() == null ? null : view.getFormId() + "");
                    subContentFormList.add(dataBean);
                }
            }
            masterForm.setDataMap(treeMap);

        } catch (MappingException ae) {
            ae.printStackTrace();


        } catch (Exception ae) {
            ae.printStackTrace();
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }

            } catch (Exception e) {
                try {
                    e.printStackTrace();


                } catch (Exception ex) {
                    //Logger.getLogger(ServiceProcessDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * get part and description list based on suggestion for part/lube master created on 5/6/14 by Megha
     */
    public String getComponentList(String partno, String comptype, String column, String tablename, String partType) {

        StringBuilder data = new StringBuilder("<listdata>");
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            //    st = conn.createStatement();
            String hql = "Select distinct  " + column + " from CatPart where " + partType + " like ('%" + comptype + "%') and " + column + " LIKE ('%" + partno + "%')  order by " + column + "";
            // sql = "Select distinct " + column + " from partmaster where " + partType + " like ('%" + comptype + "%') and " + column + " LIKE ('%" + partno + "%')  order by " + column + "";

//            // System.out.println("hql=="+hql);

            Query query = hrbsession.createQuery(hql);
            query.setMaxResults(10);
            Iterator itr = query.list().iterator();
            int counter = 0;
            // rs = st.executeQuery(sql);


            while (itr.hasNext()) {
                //Object o[] = (Object[]) itr.next();
                counter++;
                data.append(itr.next().toString().trim() + "|");


            }
            data.append("</listdata>");


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
        return data.toString();



    }

    /**
     * getPriceByPartNo for part/lube master created on 5/6/14 by Megha
     */
    public String getPriceByPartNo(String partno, String priceListCode) {

        String hql = null;
        String tempDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            Query query = hrbsession.createQuery("select cp.p1,cp.partType,pm.price from CatPart cp left join cp.spPriceMasterList pm where cp.partNo=:partNo and (pm.spPriceMasterPK.pricelistCode is NULL or pm.spPriceMasterPK.pricelistCode='" + priceListCode + "')");
            query.setParameter("partNo", partno);
            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (o[2] == null ? "" : o[2].toString());
            }
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
        return tempDesc;



    }

    /**
     * getPart_in_db for part/lube master created on 5/6/14 by Megha
     */
    public String getPart_in_db(String partDesc) {

        String hql = null;
        String tempNo = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            hql = "Select distinct partNo,p1 from CatPart where p1='" + partDesc.trim() + "' order by p1";
            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();
//            st = conn.createStatement();
//
//            rs = st.executeQuery(sql);


            if (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                // tempNo = rs.getString(1);
                tempNo = o[0].toString();


            }
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
        return tempNo;



    }

    /**
     * getPartNoCheck for part/lube master created on 5/6/14 by Megha
     */
    public String getPartNoCheck(String partno) {

        String hql = null;
        String partnumber = "notexists";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        try {
            // st = conn.createStatement();
            hql = "Select distinct partNo from CatPart where partNo='" + partno + "'";
            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();

            if (itr.hasNext()) {
                if (itr.next().toString() != null) {
                    partnumber = "exists";
                }
            }

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
        return partnumber;
    }

    /**
     *  Insert Part and Lubes details for Master created on 05/06/14 by Megha
     */
    public String addPartLubes4Master(masterForm mf) throws SQLException {

        PreparedStatement pstmt = null, pstmt1 = null;
        String result = "";
        String check = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Standardjobpartmaster sm = null;
        try {

            check = check_in_Db(mf.getJobtype().split("@@")[0], "Standardjobpartmaster", "jobTypeId", " and modelcode ='" + mf.getModelNo() + "'");
            hrbsession.beginTransaction();
            if (check.equalsIgnoreCase("exist")) {
                query = hrbsession.createQuery("delete  from Standardjobpartmaster where JobTypeId =? and modelcode=?");
                query.setInteger(0, Integer.parseInt(mf.getJobtype().split("@@")[0]));
                query.setString(1, mf.getModelNo());
                query.executeUpdate();
            }

            for (int i = 0; i < mf.getPart_No().length; i++) {
                sm = new Standardjobpartmaster();
//              // System.out.println(i+"no:"+mf.getPart_No()[i]+"-desc:"+mf.getPartDesc()[i]+"-Tp:"+mf.getComptype()[i]+"-UP:"+mf.getUnitPrice()[i]);
                if (!mf.getPart_No()[i].equals("")) {
                    sm.setJobTypeId(Integer.parseInt(mf.getJobtype().split("@@")[0]));
                    sm.setModelcode(mf.getModelNo());
                    sm.setPartNo(mf.getPart_No()[i]);
                    sm.setLastUpdatedBy(mf.getPartDesc()[i]);
                    sm.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
//                  sm.setUnitPrice(Double.parseDouble(mf.getUnitPrice()[i]));
                    sm.setQty(Integer.parseInt(mf.getQuantityS()[i]));
                    hrbsession.save(sm);

                }
            }


            hrbsession.getTransaction().commit();
            result = "SUCCESS@@Part/Lubes for Jobtype  " + mf.getJobtype().split("@@")[1] + " and Model Code " + mf.getModelNo() + " has been Successfully Added";



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
        return result;


    }

    public String addFormCheckListData(masterForm mf, TreeMap<Integer, ArrayList<String>> tMap, String userid) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<String> dataList = null;
        String result = "";
        int form_id = 0;
        int insertF = 0;
        try {
            dataList = new ArrayList<String>();
            session.beginTransaction();
            Query qry = session.createQuery("select f.id.formid from FormMaster f where f.id.modelcode=:modelCode and f.id.jobtypeid=:jobTypeId");
            qry.setParameter("modelCode", mf.getModelFamily());
            qry.setParameter("jobTypeId", mf.getJobType());
            if (qry.list().size() > 0) {
                form_id = Integer.parseInt(qry.list().get(0).toString()); //// System.out.println("formid3:"+form_id);
                Query qry1 = session.createQuery("select f.formId from ItlFormContent f where f.formId=:formId");
                qry1.setParameter("formId", form_id);
                if (qry1.list().size() > 0) {
                    Query query = session.createQuery("delete from ItlFormContent f where f.formId=:formId");
                    query.setParameter("formId", form_id);
                    int roweffected = query.executeUpdate();
                    // System.out.println("rowount:" + roweffected);

                }
            } else {
                form_id = addForm(mf.getModelFamily(), userid, mf.getJobType()); // // System.out.println("formid1:"+form_id);
            }
            int i = 1;
            for (Map.Entry<Integer, ArrayList<String>> entryMap : tMap.entrySet()) {
                dataList = entryMap.getValue();
                for (int d = 0; d < dataList.size(); d += 1) {
                    ItlFormContent itlFormContent = new ItlFormContent();
                    itlFormContent.setFormId(form_id);
                    itlFormContent.setContentId(new ItlContentMaster(Long.parseLong(entryMap.getKey().toString())));
                    itlFormContent.setSubContentId(new ItlSubContentMaster(Long.parseLong(dataList.get(d))));
                    itlFormContent.setOrderSeq(i++);
                    session.save(itlFormContent);
                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            }
            session.getTransaction().commit();
            result = "SUCCESS@@Form Data has been Successfully Added";
        } catch (Exception e) {

            e.printStackTrace();
            result = "FAILURE@@Unable to add Form Data, Please contact system Administrator.";
        } finally {
            session.close();
        }
        return result;


    }

    /**
     * Get data for standard job part master  for given jobtype and model
     */
    public ArrayList<masterForm> GetPartDetails4jobtype(String jobType, String Model) throws SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        Statement stmt = null;
        ArrayList<masterForm> partList = null;
        LinkedList<SubContentFormBean> PartDataList = new LinkedList();
        String result = "";
        int form_id = 0;
        int insertF = 0;
        try {
            partList = new ArrayList<masterForm>();
            //   pstmt = conn.prepareStatement(GetPartLubesData4jobtype);
            query = hrbsession.createQuery("select p.partNo,p.partType,p.p1,mp.qty from CatPart p , Standardjobpartmaster mp where "
                    + " p.partNo =mp.partNo and mp.jobTypeId=:jobType and mp.modelcode=:modelCode");
            query.setInteger("jobType", Integer.parseInt(jobType));
            query.setString("modelCode", Model);
            itr = query.list().iterator();
            while (itr.hasNext()) {
//                Standardjobpartmaster sm = (Standardjobpartmaster) itr.next();
                Object[] obj = (Object[]) itr.next();
                masterForm mf = new masterForm();
                mf.setPartNo(obj[0] == null ? "" : obj[0].toString().trim());
                mf.setPartCategory(obj[1] == null ? "" : obj[1].toString().trim().equalsIgnoreCase("prt") == true ? "SPARES" : "LUBES");
                mf.setPart_Desc(obj[2] == null ? "" : obj[2].toString().trim());
                mf.setQty("" + obj[3] == null ? "" : obj[3].toString().trim());
                partList.add(mf);

            }
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
        return partList;
    }

    public ArrayList<masterForm> getWarrantyModelList(masterForm masterForm, String nameSrch) throws SQLException {

        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " and modelCode like '%" + SearchQuery + "%'  order by modelCode";
            }
            sql = "FROM WarrantyModel WHERE wtyEndDate=null " + Subsql;
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                WarrantyModel wm = (WarrantyModel) itr.next();
                masterForm = new masterForm();
                masterForm.setModelId(wm.getId() + "");
                masterForm.setModelCode(wm.getModelCode());
                masterForm.setModelmonth(wm.getMonths() + "");
                masterForm.setModelhrs(wm.getHrs() + "");
                masterForm.setWty_start_date(sdf.format(wm.getWtyStartDate()));
                if (wm.getWtyEndDate() != null) {
                    masterForm.setWty_end_date(sdf.format(wm.getWtyEndDate()));
                }
                dataList.add(masterForm);
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

    //addWarrantyModel
    public String addWarrantyModel(masterForm masterForm) throws SQLException {
        String results = "contentExist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        int id = 0, flag = 0;
        WarrantyModel wm = null;

        String models = "";
        String check = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hrbsession.beginTransaction();

            Criteria cr = hrbsession.createCriteria(WarrantyModel.class);

            for (int i = 0; i < masterForm.getCode().length; i++) {

                check = check_in_Db(masterForm.getCode()[i].toUpperCase().trim(), "WarrantyModel", "modelCode", "");

                if (check.equals("exist")) {
                    flag++;

                    if (flag > 1) {
                        models = models + "," + masterForm.getCode()[i].toUpperCase();
                    } else {
                        models = masterForm.getCode()[i].toUpperCase();
                    }

                } else {
                    cr.setProjection(Projections.max("id"));
                    if (!cr.list().isEmpty()) {
                        if (cr.list().get(0) != null) {
                            id = Integer.parseInt(cr.list().get(0).toString());
                        }
                        id = id + 1;
                    }

                    wm = new WarrantyModel();
                    wm.setId(id);
                    wm.setModelCode(masterForm.getCode()[i].toUpperCase().trim());
                    wm.setMonths(Long.valueOf(masterForm.getMonth()[i]).longValue());
                    wm.setHrs(Integer.parseInt(masterForm.getHrs()[i]));
                    wm.setWtyStartDate(new java.sql.Timestamp(sdf.parse("01/01/2010").getTime()));
                    wm.setWtyEndDate(null);

                    hrbsession.save(wm);
                }

            }
            hrbsession.getTransaction().commit();
            if (flag >= 1) {
                results = "Success@@Warranty Model ( " + models + " ) already exist.";
            } else {
                results = "Success@@Warranty Model has been Successfully Added.";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Warranty Model, Please contact system Administrator.";
        }finally {
        	if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();  
            }
		}
        return results;
    }

    //UpdateWarrantyModel
    public String UpdateWarrantyModel(masterForm masterForm, String modelId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
//        int id = 0;
        WarrantyModel wm = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hrbsession.beginTransaction();
//            String sql1 = "update WarrantyModel set wtyEndDate=? where modelCode='" + masterForm.getModelCode().trim().toUpperCase() + "' and wtyEndDate is NULL";
//            Query query = hrbsession.createQuery(sql1);
//            query.setTimestamp(0, new java.sql.Timestamp(new java.util.Date().getTime() - 24 * 3600 * 1000));
//            System.out.println("here" + new java.sql.Timestamp(new java.util.Date().getTime() - 24 * 3600 * 1000));




//            Criteria cr = hrbsession.createCriteria(WarrantyModel.class);
//            cr.setProjection(Projections.max("id"));
//            if (!cr.list().isEmpty()) {
//                if (cr.list().get(0) != null) {
//                    id = Integer.parseInt(cr.list().get(0).toString());
//                }
//                id = id + 1;
//            }
            //  System.out.println("masterForm.getModelCode()==" + masterForm.getModelCode());
            wm = (WarrantyModel) hrbsession.load(WarrantyModel.class, Integer.parseInt(modelId));
            // wm.setId(id);
            wm.setModelCode(masterForm.getModelCode().toUpperCase().trim());
            wm.setMonths(Long.valueOf(masterForm.getModelmonth()).longValue());
            wm.setHrs(Integer.parseInt(masterForm.getModelhrs()));
//            wm.setWtyStartDate(new java.sql.Timestamp(sdf.parse("01/01/2010").getTime()));
            hrbsession.update(wm);
            hrbsession.getTransaction().commit();
            results = "Success@@Warranty Model " + masterForm.getModelCode() + " has been Successfully Updated.";

        } catch (Exception ae) {
            ae.printStackTrace();
            conn.rollback();
            results = "Failure@@Unable to add Warranty Model, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return results;

    }

    /**
     * add form in form master created on 16/09/14 by Megha
     */
    public int addForm(String modelCode, String userid, int jobTypeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int form_id = 0;
        Iterator itr = null;
        Query query1 = null;
        try {

            String hql = "select count(*) from FormMaster";
            query1 = session.createQuery(hql);

            itr = query1.list().iterator();

            // // System.out.println("inside the jobnumber");

            if (itr.hasNext()) {
                form_id = ((Long) itr.next()).intValue();
            }
            form_id = form_id + 1;
            session.beginTransaction();


            FormMaster formMaster = new FormMaster();
            FormMasterPK formMasterpk = new FormMasterPK();
            formMasterpk.setModelcode(modelCode);
            formMasterpk.setJobtypeid(jobTypeId);
            formMasterpk.setFormid(form_id);

            formMaster.setLastModifiedBy(userid);
            formMaster.setLastModifiedOn(new java.sql.Date(new java.util.Date().getTime()));
            formMaster.setFormMasterPK(formMasterpk);
            session.save(formMaster);
            session.getTransaction().commit();
            form_id = formMasterpk.getFormid();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return form_id;
    }

    public ArrayList<masterForm> getWarrantyTaxPercList(masterForm masterForm) throws SQLException {

        ArrayList<masterForm> dataList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        try {
            // System.out.println("***" + masterForm.getTaxTypeName() + "***" + masterForm.getStateName());
            dataList = new ArrayList<masterForm>();
            query = session.createQuery("select tsp.mSWwarrantytaxStatePercentagePK.typeID,tsp.mSWwarrantytaxStatePercentagePK.stateID,tsp.typePercentage,wtt.typeDescription,gs.stateName "
                    + " from MSWwarrantytaxStatePercentage tsp,MSWwarrantytaxtypes wtt,GENstates gs "
                    + " where tsp.mSWwarrantytaxStatePercentagePK.typeID=wtt.typeID and gs.stateID=tsp.mSWwarrantytaxStatePercentagePK.stateID  "
                    + " and tsp.mSWwarrantytaxStatePercentagePK.typeID = isnull(?,tsp.mSWwarrantytaxStatePercentagePK.typeID)"
                    + " and tsp.mSWwarrantytaxStatePercentagePK.stateID = isnull(?,tsp.mSWwarrantytaxStatePercentagePK.stateID)"
                    + " order by tsp.mSWwarrantytaxStatePercentagePK.stateID,tsp.mSWwarrantytaxStatePercentagePK.typeID ");

            query.setString(0, ((masterForm.getTaxTypeName() == null) ? null : masterForm.getTaxTypeName()));
            query.setString(1, ((masterForm.getStateName() == null) ? null : masterForm.getStateName()));
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                masterForm = new masterForm();

                masterForm.setTaxTypeId(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                masterForm.setStateId(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                masterForm.setPercentage(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                masterForm.setTaxTypeName(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                masterForm.setStateName(enqobj[4] == null ? "" : enqobj[4].toString().trim());

                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if (session != null && session.isOpen()) {
                session.close();  
            }
		}
        return dataList;
    }

    /**
     * add  Warranty tax Percentage created on 20/10/14 by Megha
     */
    public String addWarrantytaxPer(masterForm mf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure";
        try {
            Query qry = session.createQuery(" from MSWwarrantytaxStatePercentage wp where wp.mSWwarrantytaxStatePercentagePK.typeID=:typeId and "
                    + "wp.mSWwarrantytaxStatePercentagePK.stateID=:stateID");
            qry.setParameter("typeId", Integer.parseInt(mf.getTaxTypeId()));
            qry.setParameter("stateID", Integer.parseInt(mf.getStateId()));
            List list = qry.list();
            // System.out.println(mf.getTaxTypeId() + "--" + mf.getStateId() + "----size:" + qry.list().size());
            if (list.size() > 0 || !list.isEmpty()) {
                result = "Exist";
            } else {
                session.beginTransaction();
                MSWwarrantytaxStatePercentage warrantyTaxperc = new MSWwarrantytaxStatePercentage();
                warrantyTaxperc.setMSWwarrantytaxStatePercentagePK(new MSWwarrantytaxStatePercentagePK(Integer.parseInt(mf.getTaxTypeId()), Integer.parseInt(mf.getStateId())));
                warrantyTaxperc.setLastUpdatedBy(mf.getUserId());
                warrantyTaxperc.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
                warrantyTaxperc.setTypePercentage(Double.parseDouble(mf.getPercentage()));
                session.save(warrantyTaxperc);
                session.getTransaction().commit();
                result = "Success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * update Warranty tax Percentage created on 20/10/14 by Megha
     */
    public String updateWarrantytaxPer(masterForm mf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure@@Unable to Update Warranty Tax Percentage, Please Contact System Administrator.";
        try {
            session.beginTransaction();
            MSWwarrantytaxStatePercentagePK warrantyTaxpercPK = new MSWwarrantytaxStatePercentagePK(Integer.parseInt(mf.getTaxTypeId()), Integer.parseInt(mf.getStateId()));
            MSWwarrantytaxStatePercentage warrantyTaxperc = (MSWwarrantytaxStatePercentage) session.load(MSWwarrantytaxStatePercentage.class, warrantyTaxpercPK);
            warrantyTaxperc.setLastUpdatedBy(mf.getUserId());
            warrantyTaxperc.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
            warrantyTaxperc.setTypePercentage(Double.parseDouble(mf.getPercentage()));
            session.saveOrUpdate(warrantyTaxperc);
            session.getTransaction().commit();
            result = "Success@@Warranty Tax Percentage has been updated Successfully.";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * get pdi/ins parameter Vs makeList created on 31/10/14 by Megha
     */
    public ArrayList<masterForm> getpdi_insparamVsmakeList(masterForm mf) {
        ArrayList<masterForm> dataList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String subquery = "";
        try {
            if (!mf.getPartNo().equals("")) {
                if (!mf.getMakeShow().equals("")) {
                    subquery = " where partName like '%" + mf.getPartNo() + "%' and  makeName like '%" + mf.getMakeShow() + "%'";
                } else {
                    subquery = " where partName like '%" + mf.getPartNo() + "%' ";
                }
            } else {
                if (!mf.getMakeShow().equals("")) {
                    subquery = " where makeName like '%" + mf.getMakeShow() + "%' ";
                }
            }

            Query query = session.createQuery(" from MSWINSPDITractorDetails " + subquery);
            Iterator it = query.list().iterator();
            dataList = new ArrayList<masterForm>();
            while (it.hasNext()) {
                MSWINSPDITractorDetails mswpdiIns = (MSWINSPDITractorDetails) it.next();
                masterForm masForm = new masterForm();
                masForm.setId("" + mswpdiIns.getId());
                masForm.setPart_Desc(mswpdiIns.getPartName());
                masForm.setMake(mswpdiIns.getMakeName());
                masForm.setStatus("" + mswpdiIns.getIsActive());
                dataList.add(masForm);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    /**
     * add parameterVsmake for PDI/INS  created on 1/11/14 by Megha
     */
    public String addparameterVsmake(masterForm mf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure";
        try {
            Query qry = session.createQuery(" from MSWINSPDITractorDetails pm where pm.partName=:partName and pm.makeName=:make ");
            qry.setParameter("partName", mf.getPart_Desc());
            qry.setParameter("make", mf.getMake());
            List list = qry.list();
            if (list.size() > 0 || !list.isEmpty()) {
                result = "Exist";
            } else {
                session.beginTransaction();
                MSWINSPDITractorDetails partvsMake = new MSWINSPDITractorDetails();
                partvsMake.setIsActive('Y');
                partvsMake.setPartName(mf.getPart_Desc());
                partvsMake.setMakeName(mf.getMake());
                partvsMake.setLastUpdatedBy(mf.getUserId());
                partvsMake.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
                session.save(partvsMake);
                session.getTransaction().commit();
                result = "Success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * update parameterVsmake for PDI/INS  created on 1/11/14 by Megha
     **/
    public String updateparameterVsmake(masterForm mf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "Failure@@Unable to Update Parameter vs Make, Please Contact System Administrator.";
        try {
            session.beginTransaction();
            MSWINSPDITractorDetails partvsMake = (MSWINSPDITractorDetails) session.load(MSWINSPDITractorDetails.class, Integer.parseInt(mf.getId()));
            if (!mf.getStatus().equalsIgnoreCase("edit")) {
                if (mf.getStatus().equalsIgnoreCase("Y")) {
                    partvsMake.setIsActive('Y');
                } else {
                    partvsMake.setIsActive('N');
                }
                partvsMake.setLastUpdatedBy(mf.getUserId());
                partvsMake.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
                session.saveOrUpdate(partvsMake);
                result = "Success@@Parameter vs Make has been updated Successfully.";
            } else {
                Query qry = session.createQuery(" from MSWINSPDITractorDetails pm where pm.partName=:partName and pm.makeName=:make and id!=" + Integer.parseInt(mf.getId()));
                qry.setParameter("partName", mf.getPart_Desc());
                qry.setParameter("make", mf.getMake());
                List list = qry.list();
                if (list.size() > 0 || !list.isEmpty()) {
                    result = "Exist@@Parameter vs Make already exist for given Part Name and Make.";
                } else {
                    partvsMake.setPartName(mf.getPart_Desc());
                    partvsMake.setMakeName(mf.getMake());
                    partvsMake.setIsActive('Y');
                    partvsMake.setLastUpdatedBy(mf.getUserId());
                    partvsMake.setLastUpdatedOn(new Date(new java.util.Date().getTime()));
                    session.saveOrUpdate(partvsMake);
                    result = "Success@@Parameter vs Make has been updated Successfully.";
                }
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * get RejectionCode List
     */
    public ArrayList<masterForm> getRejectionCodeList(masterForm mf) {
        ArrayList<masterForm> dataList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String subquery = "";
        try {
            if (!mf.getRejDesc().equals("")) {
                subquery = " where rejectionDesc like '%" + mf.getRejDesc() + "%' ";
            }

            Query query = session.createQuery(" from SAPRejectionCodeMaster " + subquery + " order by rejectionDesc");
            Iterator it = query.list().iterator();
            dataList = new ArrayList<masterForm>();
            while (it.hasNext()) {
                SAPRejectionCodeMaster rejectionCodeMaster = (SAPRejectionCodeMaster) it.next();
                masterForm masForm = new masterForm();
                masForm.setRejCode("" + rejectionCodeMaster.getRejectionCode());
                masForm.setRejDesc(rejectionCodeMaster.getRejectionDesc());
                masForm.setStatus("" + rejectionCodeMaster.getIsActive());
                dataList.add(masForm);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    /**
     * add Rejection Code
     */
    public String addRejectionCode(String rejCode, String rejDesc, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SAPRejectionCodeMaster rm = new SAPRejectionCodeMaster();
        try {
            String check = check_in_Db(rejCode, "SAPRejectionCodeMaster", "rejectionCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                rm.setRejectionCode(rejCode.toUpperCase());
                rm.setRejectionDesc(rejDesc);
                rm.setLastUpdatedDate(new java.util.Date());
                rm.setIsActive('Y');
                rm.setLastUpdatedBy(userId);
                hrbsession.save(rm);
                hrbsession.getTransaction().commit();
                results = "Success@@Rejection Code '" + rejCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Rejection Code '" + rejCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Rejection Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Rejection Code, Please contact system Administrator.";
            }
        }
        return results;
    }

    /**
     * update Rejection Code
     */
    public String UpdateRejectionCode(String status, String type, String rejCode, String rejDesc, String userId) throws SQLException {
        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SAPRejectionCodeMaster rm;
        try {
            rm = (SAPRejectionCodeMaster) hrbsession.load(SAPRejectionCodeMaster.class, rejCode);
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    rm.setIsActive('N');
                } else {
                    rm.setIsActive('Y');
                }
                rm.setLastUpdatedDate(new java.util.Date());
                rm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(rm);

                results = "Success@@isActive has been updated Successfully.";
            } else {
                rm.setRejectionDesc(rejDesc);
                rm.setLastUpdatedDate(new java.util.Date());
                rm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(rm);

                results = "Success@@Rejection Code '" + rejCode + "' has been updated Successfully.";
            }

            hrbsession.getTransaction().commit();
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to update Rejection Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to update Rejection Code, Please contact system Administrator.";
            }
        }
        return results;
    }

    public LinkedHashSet<LabelValueBean> getExportUserTypeList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> userTypeList = new LinkedHashSet<LabelValueBean>();
        LabelValueBean lb = null;
        try {
            SQLQuery query = session.createSQLQuery("select USER_TYPE_ID,USER_TYPE FROM UM_spas101 where RoleCategory='EXPORT'").addScalar("USER_TYPE_ID", new IntegerType()).addScalar("USER_TYPE", new StringType());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                lb = new LabelValueBean();
                lb.setLabel(obj[1].toString());
                lb.setValue(obj[0].toString());
                userTypeList.add(lb);
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userTypeList;
    }

    public LinkedHashSet<LabelValueBean> getUserList(String userTypeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> userList = new LinkedHashSet<LabelValueBean>();
        LabelValueBean lb = null;
        try {
            SQLQuery query = session.createSQLQuery("select USER_ID,USER_NAME FROM UM_USER_CHECK where USER_TYPE_ID='" + userTypeId + "' and Status='A'").addScalar("USER_ID", new StringType()).addScalar("USER_NAME", new StringType());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                lb = new LabelValueBean();
                lb.setLabel(obj[1].toString());
                lb.setValue(obj[0].toString());
                userList.add(lb);
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public LinkedHashSet<LabelValueBean> getPriceList(String user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> priceList = new LinkedHashSet<LabelValueBean>();
        LabelValueBean lb = null;
        try {
            SQLQuery query = session.createSQLQuery("select distinct PRICELIST_CODE from SP_PRICELIST_CODE ORDER BY PRICELIST_CODE").addScalar("PRICELIST_CODE", new StringType());
            List<String> list1 = query.list();
            for (String s : list1) {
                lb = new LabelValueBean();
                lb.setLabel(s);
                lb.setValue(s);
                priceList.add(lb);
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return priceList;
    }

    public String getPrice(String user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String str = "";
        try {
            SQLQuery query = session.createSQLQuery("select PRICELIST_CODE FROM UM_USER_PRICELIST where USER_ID='" + user + "'").addScalar("PRICELIST_CODE", new StringType());
            List<String> list1 = query.list();
            for (String s : list1) {
                str = s;
                // str=str.substring(str.indexOf("_")+1, str.length());
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public String saveOrUpdateManageUserType(String user, String priceCode, String updatedBy) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String messg = "";
        try {
            session.beginTransaction();
            java.util.Date today = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(today);
            today = sdf.parse(s);
            SQLQuery query = session.createSQLQuery("select PRICELIST_CODE FROM UM_USER_PRICELIST where USER_ID='" + user + "'").addScalar("PRICELIST_CODE", new StringType());
            List<String> list1 = query.list();
            if (list1 != null && list1.size() > 0) {
                Query update = session.createSQLQuery("UPDATE UM_USER_PRICELIST SET PRICELIST_CODE=:PRICELIST_CODE,LAST_UPDATED_BY=:LAST_UPDATED_BY,LAST_UPDATED_ON=:LAST_UPDATED_ON where USER_ID=:USER_ID");
                update.setParameter("USER_ID", user);
                update.setParameter("PRICELIST_CODE", priceCode);
                update.setParameter("LAST_UPDATED_BY", updatedBy);
                update.setParameter("LAST_UPDATED_ON", today);
                update.executeUpdate();
                session.getTransaction().commit();
                messg = "Updated";
            } else {
                Query insert = session.createSQLQuery("INSERT INTO UM_USER_PRICELIST (USER_ID, PRICELIST_CODE,LAST_UPDATED_BY,LAST_UPDATED_ON) VALUES (:USER_ID,:PRICELIST_CODE,:LAST_UPDATED_BY,:LAST_UPDATED_ON)");
                insert.setParameter("USER_ID", user);
                insert.setParameter("PRICELIST_CODE", priceCode);
                insert.setParameter("LAST_UPDATED_BY", updatedBy);
                insert.setParameter("LAST_UPDATED_ON", new java.util.Date());
                insert.executeUpdate();
                session.getTransaction().commit();
                messg = "Created";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messg;
    }

    public Float getSellingPercentage(String dealerCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Float sellingPrice = new Float(0.0);
        try {
            SQLQuery query = session.createSQLQuery("select SELLING_PERCENTAGE FROM UM_USER_SELLING_PERCENTAGE where DEALER_CODE='" + dealerCode + "'").addScalar("SELLING_PERCENTAGE", new FloatType());
            List<Float> list = query.list();
            for (Float sp : list) {
                sellingPrice = sp;
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sellingPrice;
    }

    public String saveOrUpdateManageSellingPercentage(String dealerCode, Float percentage, String updatedBy) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // System.out.println("@@@@@@@"+percentage);
        String messg = "";
        try {
            java.util.Date today = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(today);
            today = sdf.parse(s);
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select SELLING_PERCENTAGE FROM UM_USER_SELLING_PERCENTAGE where DEALER_CODE='" + dealerCode + "'").addScalar("SELLING_PERCENTAGE", new IntegerType());
            List<Integer> list1 = query.list();
            if (list1 != null && list1.size() > 0) {
                Query update = session.createSQLQuery("UPDATE UM_USER_SELLING_PERCENTAGE SET SELLING_PERCENTAGE=:SELLING_PERCENTAGE,LAST_UPDATED_BY=:LAST_UPDATED_BY,LAST_UPDATED_ON=:LAST_UPDATED_ON where DEALER_CODE=:DEALER_CODE");
                update.setFloat("SELLING_PERCENTAGE", percentage);
                update.setString("DEALER_CODE", dealerCode);
                update.setString("LAST_UPDATED_BY", updatedBy);
                update.setDate("LAST_UPDATED_ON", today);
                update.executeUpdate();
                session.getTransaction().commit();
                messg = "Updated";
            } else {
                Query insert = session.createSQLQuery("INSERT INTO UM_USER_SELLING_PERCENTAGE (DEALER_CODE, SELLING_PERCENTAGE,LAST_UPDATED_BY,LAST_UPDATED_ON) VALUES (:DEALER_CODE,:SELLING_PERCENTAGE,:LAST_UPDATED_BY,:LAST_UPDATED_ON)");
                insert.setString("DEALER_CODE", dealerCode);
                insert.setFloat("SELLING_PERCENTAGE", percentage);
                insert.setString("LAST_UPDATED_BY", updatedBy);
                insert.setDate("LAST_UPDATED_ON", new java.util.Date());
                insert.executeUpdate();
                session.getTransaction().commit();
                messg = "Created";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messg;
    }

    public boolean isUserIdExist(String userId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean flag = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("select userId from DmsUserCheck where userId= :userId");
            query.setParameter("userId", userId);
            String user = query.list().get(0).toString();         //query.list().get(0)==null?"":
            if (!user.equals("")) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return flag;
    }

    public boolean isDealerExist(String dealerCode) {
        boolean flag = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("select dealerCode from UmDmsDealerMaster where dealerCode= :dealerCode");
            query.setParameter("dealerCode", dealerCode);
            String dealer = query.list().get(0).toString();
            if (!dealer.equals("")) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return flag;
    }

    public String validatedManageUserHierarchyExcel(File xlsfile, Connection conn) throws Exception {
        //EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        masterDAO mDao = new masterDAO();
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (!(sheet.getCell(column, row).getContents().trim().equals("MANAGE USER HIERARCHY TEMPLATE"))) {
                err = "Row " + row + ", Column " + column + ". 'MANAGE USER HIERARCHY TEMPLATE' Missing.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = "Row " + row + ", Column " + column + ". 'Row should not be blank.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("USER ID"))) {
                err = "Row " + row + ", Column " + column + ". 'USER ID' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("DEALER CODE"))) {
                err = "Row " + row + ", Column " + column + ". 'DEALER CODE' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TYPE"))) {
                err = "Row " + row + ", Column " + column + ". 'TYPE' Missing.";
                return err;
            }
            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = "Row " + row + ", Column " + column + ". No Records Available.";
                return err;
            }
            totalrows = sheet.getRows();
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    for (column = 0; column < 3; column++) {
                        if (column == 0) {
                            String userId = sheet.getCell(column, row).getContents().trim();
                            if (userId.equals("")) {
                                err = "Row " + row + ", Column " + column + ". User Id can not be blank. Specify 'end' if required.";
                                return err;
                            }
//                        } else if (column == 0) {
//                            String userId = sheet.getCell(column, row).getContents().trim();
                            if ((r = mDao.isUserIdExist(userId)) != true) {
                                err = "Row " + row + ", Column " + column + ". User Id '" + userId + "' doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 1) {
                            String dealerCode = sheet.getCell(column, row).getContents().trim();
                            if (dealerCode.equals("")) {
                                err = "Row " + row + ", Column " + column + ". Dealer Code can not be blank. Specify 'end' if required.";
                                return err;
                            }
//                        } else if (column == 1) {
//                            String dealerCode = sheet.getCell(column, row).getContents().trim();
                            if ((r = mDao.isDealerExist(dealerCode)) != true) {
                                err = "Row " + row + ", Column " + column + ". Dealer Code '" + dealerCode + "' doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 2) {
                            String funcType = sheet.getCell(column, row).getContents().trim();
                            if (funcType.equals("")) {
                                err = "Row " + row + ", Column " + column + ". Type can not be blank.";
                                return err;
                            }
                            if (funcType.length() > 15) {
                                return "Row " + row + ", Column " + column + ". Type can not be greater than 15 characters.";
                            }
                            if (!funcType.equalsIgnoreCase("ADD") && !funcType.equalsIgnoreCase("DELETE")) {
                                err = "Row " + row + ", Column " + column + ". Type can  be allowed ADD / DELETE.";
                                return err;
                            }
                            r = methodutil.checkSpecialSymbolDescription(funcType);
                            if (r != true) {
                                err = "Row " + row + ", Column " + column + ". Special Symbols are not allowed in Type.";
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = "Row " + row + ", Column " + column + ". 'end' tag Missing.";
                return err;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = "Row " + row + ", Column " + column + ". TEMPLATE MISMATCH or 'end' tag Missing.";
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;
        }
        return result;
    }

    public Vector insertManageUserHierarchyExcel(File xlsfile, Connection conn, String userid) throws Exception {
        //decalration of variables used.
        int row = 3, column = 0;
        Vector message = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        int noOfParts = 0;
        Session session = null;
        Transaction tx = null;
        UmDmsUserDealerMatrix umDms = null;
        Date today = new Date(new java.util.Date().getTime());

        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                String userId = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                String dealerCode = sheet.getCell(column, row).getContents().toUpperCase();
                column++;
                String categoryType = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                umDms = new UmDmsUserDealerMatrix();
                if (categoryType.equalsIgnoreCase("ADD")) {
                    umDms.setUserId(userId);
                    umDms.setDealerCode(dealerCode);
                    umDms.setLastUpdatedOn(today);
                    session.saveOrUpdate(umDms);

                } else if (categoryType.equalsIgnoreCase("DELETE")) {
                    umDms.setUserId(userId);
                    session.delete(umDms);
                }
                noOfParts++;
                column = 0;
                row++;
            }
            tx.commit();

            if (noOfParts == 1) {
                message.add(0, " User has been Added/Deleted Successfully.");
                message.add(1, "Add More");

            } else if (noOfParts > 1) {
                message.add(0, " Users have been Added/Deleted Successfully.");
                message.add(1, "Add More");
            }
//            } else {
//                message.add(0, "Already present, User has been Added/Deleted.");
//                message.add(1, "Try Again");
//            }
            message.add(2, "" + noOfParts);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }

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

    public ArrayList<masterForm> getCustCategoryList(masterForm masterForm, String nameSrch) throws SQLException {
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            Query query = null;
            if (!SearchQuery.equals("")) {
                Subsql = " where cmm.custCategory like '%" + SearchQuery + "%' ";
                query = session.createQuery("select cmm.custcategoryID,cmm.custCategory,cmm.isActive  from CustomerCategoryMaster cmm " + Subsql + " order by cmm.custCategory");
            } else {
                query = session.createQuery("select cmm.custcategoryID,cmm.custCategory,cmm.isActive  from CustomerCategoryMaster cmm  order by cmm.custCategory");
            }
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                masterForm = new masterForm();
                masterForm.setId(obj[0] == null ? "" : obj[0].toString().trim());
                masterForm.setName(obj[1] == null ? "" : obj[1].toString().trim());
                masterForm.setStatus(obj[2] == null ? "" : obj[2].toString().trim());
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String addCustCategory(CustomerCategoryMaster ccm) throws SQLException {
        String results = "StatusExist";
        int id = 0;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            String check = check_in_Db(ccm.getCustCategory(), "CustomerCategoryMaster", "custCategory", "");
            if (check.equalsIgnoreCase("notexist")) {
                //ccm.setSeqNo(id);
                hrbsession.save(ccm);
                hrbsession.getTransaction().commit();
                results = "Success@@Location Description '" + ccm.getCustCategory() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Location Description '" + ccm.getCustCategory() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Location Description, Please contact system Administrator.";
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
        return results;
    }

    public String UpdateCustCategory(CustomerCategoryMaster ccm, String status, String id, String type, String Desc) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Location Desc.
         */
        String result = "";
        char newStatus = 'N';
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = 'N';
                } else {
                    newStatus = 'Y';
                }
                hrbsession.beginTransaction();
                CustomerCategoryMaster ccstatus = (CustomerCategoryMaster) hrbsession.load(CustomerCategoryMaster.class, Integer.parseInt(id));
                ccstatus.setIsActive(newStatus);
                ccstatus.setModifiedOn(ccm.getModifiedOn());
                ccstatus.setModifiedBy(ccm.getModifiedBy());
                hrbsession.getTransaction().commit();
                result = "Success@@isActive has been updated Successfully.";
            } else {
                String check = check_in_Db(ccm.getCustCategory(), "CustomerCategoryMaster", "custCategory", " and custcategoryID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    hrbsession.beginTransaction();
                    CustomerCategoryMaster ccstatus = (CustomerCategoryMaster) hrbsession.load(CustomerCategoryMaster.class, Integer.parseInt(id));
                    ccstatus.setCustCategory(ccm.getCustCategory());
                    ccstatus.setModifiedOn(ccm.getModifiedOn());
                    ccstatus.setModifiedBy(ccm.getModifiedBy());
                    hrbsession.getTransaction().commit();
                    result = "Success@@Status Description '" + ccm.getCustCategory() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Status Description '" + ccm.getCustCategory() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Location Description, Please contact system Administrator.";
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
        return result;
    }

    public ArrayList<masterForm> getDiscountCategoryList(masterForm masterForm, String dealerCode) throws SQLException {
        ArrayList<masterForm> dataList = null;
        // String sql = null, Subsql = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            Query query = null;
            query = session.createSQLQuery("select  ccm.CustCategoryID,ccm.CustCategory,isnull(ccd.DiscountPercentage,0) "
                    + "from UM_CustomerCategoryMaster ccm left join UM_CustomerCategoryDiscount ccd on  "
                    + "ccm.CustCategoryID=ccd.CustCategoryID and ccd.DealerCode=:dealerCode where ccm.isActive='Y'  "
                    + "order by ccm.custCategory");
            query.setParameter("dealerCode", dealerCode);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                masterForm = new masterForm();
                masterForm.setId(obj[0] == null ? "" : obj[0].toString().trim());
                masterForm.setName(obj[1] == null ? "" : obj[1].toString().trim());

                masterForm.setDiscount(obj[2] == null ? Float.parseFloat("0") : Float.parseFloat(obj[2].toString().trim()));
                dataList.add(masterForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String addDiscountCategory(ArrayList ccdArr, String dealerCode) throws SQLException {
        String results = "StatusExist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();

            Query query = hrbsession.createQuery("delete from CustomerCategoryDiscount where dealerCode=?");
            query.setString(0, dealerCode);
            query.executeUpdate();

            CustomerCategoryDiscount ccd;
            for (int i = 0; i < ccdArr.size(); i++) {
                ccd = (CustomerCategoryDiscount) ccdArr.get(i);
                hrbsession.save(ccd);
            }

            hrbsession.getTransaction().commit();

            results = "Success@@Discount % has been Successfully Updated.";
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Location Description, Please contact system Administrator.";
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
        return results;
    }

    public ArrayList<masterForm> getTaxOEMList(masterForm masterForm, String nameSrch, String codeSrch)
            throws SQLException {
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        String searchCode = codeSrch == null ? "" : codeSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            // if (!SearchQuery.equals("")) {
            Subsql = " where taxChargeDesc like '%" + SearchQuery + "%' and taxChargeCode like '%" + searchCode + "%'";
            //  }
            sql = "from CmTaxCharg_OEM " + Subsql + " order by taxChargeCode";
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                CmTaxCharg_OEM oem = (CmTaxCharg_OEM) itr.next();
                masterForm = new masterForm();
                masterForm.setTaxCode(oem.getTaxChargeCode());
                masterForm.setTaxDesc(oem.getTaxChargeDesc());
                masterForm.setStatus(oem.getIsActive() + "");
                masterForm.setTaxChgOemId(oem.getTaxOemId());
                dataList.add(masterForm);
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

    public String addTaxChargeOemMaster(String taxCode, String taxDesc, String userId)
            throws SQLException {

        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(taxCode, "CmTaxCharg_OEM", "taxChargeCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                CmTaxCharg_OEM oem = new CmTaxCharg_OEM();
                oem.setTaxChargeCode(taxCode.toUpperCase().trim());
                oem.setTaxChargeDesc(taxDesc.trim());
                oem.setIsActive("Y");
                oem.setIsTaxOrCharge("T");
                oem.setCreatedBy(userId);
                oem.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                oem.setModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                oem.setModifiedBy(userId);
                hrbsession.save(oem);
                hrbsession.getTransaction().commit();
                results = "Success@@Tax Type Code '" + taxCode + "' has been Successfully Added.";

            } else {
                results = "Failure@@Tax Type Code '" + taxCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Tax Type, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return results;
    }

    public String UpdateTaxChargeOemMaster(String status, int id, String userId, String taxCode,
            String taxDesc, String type, int tax_id) throws SQLException {

        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String newStatus = "";
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";

                } else {
                    newStatus = "Y";
                }
                CmTaxCharg_OEM oem = (CmTaxCharg_OEM) hrbsession.load(CmTaxCharg_OEM.class, id);
                oem.setIsActive(newStatus);
                oem.setModifiedDate(new java.util.Date());
                oem.setModifiedBy(userId);
                hrbsession.saveOrUpdate(oem);
                result = "Success@@Tax Type Code '" + taxCode + "' has been updated Successfully.";

            } else {
                CmTaxCharg_OEM oem = (CmTaxCharg_OEM) hrbsession.load(CmTaxCharg_OEM.class, id);
                oem.setTaxChargeDesc(taxDesc);
                oem.setModifiedDate(new java.util.Date());
                oem.setModifiedBy(userId);
                hrbsession.saveOrUpdate(oem);
                result = "Success@@Tax Type Code'" + taxCode + "' has been updated Successfully.";
            }
            hrbsession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Tax Type Code, Please contact System Administrator.";
            hrbsession.getTransaction().rollback();

        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Tax Type Code, Please contact system Administrator.";
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getTaxTypeList() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> taxList = new LinkedHashSet<LabelValueBean>();
        LabelValueBean lb = null;
        String code = "", id = "", codedesc = "";
        try {
            Query query = session.createQuery("select distinct taxOemId,taxChargeCode,taxChargeDesc from "
                    + "CmTaxCharg_OEM where isActive='Y' order by taxChargeCode");

            List<String> l = (List<String>) query.list();
            Iterator itr = l.iterator();
            while (itr.hasNext()) {
                LabelValueBean lv = null;
                Object obj[] = (Object[]) itr.next();
                String name1 = (String) obj[1];
                String namedesc = (String) obj[2];
                String name0 = obj[0].toString();

                code = name1 == null ? "" : name1.trim();
                codedesc = namedesc == null ? "" : namedesc.trim();
                id = name0 == null ? "" : name0.trim();
                lv = new LabelValueBean(code + " [ " + codedesc + " ]", id);
                taxList.add(lv);
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taxList;
    }

    public ArrayList<masterForm> getChargeBranchList(masterForm masterForm, String nameSrch, String codeSrch)
            throws SQLException {

        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        String searchCode = codeSrch == null ? "" : codeSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            // if (!SearchQuery.equals("") || !(searchCode.equals(""))) {
            Subsql = " where chargeDesc like '%" + SearchQuery + "%' and chargeCode like '%" + searchCode + "%' ";
            //  }
            sql = "from CmChargeBranch " + Subsql + " order by chargeCode";
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                CmChargeBranch chg = (CmChargeBranch) itr.next();
                masterForm = new masterForm();
                masterForm.setChargeCode(chg.getChargeCode());
                masterForm.setChargeDesc(chg.getChargeDesc());
                masterForm.setChargeRate(chg.getChargeRatePerc());
                masterForm.setChargeBranchId(chg.getCharge_branch_id());
                dataList.add(masterForm);
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

    public String addChargeBranchMaster(masterForm mf) //String chargeCode, String chargeDesc, String userId, int taxTypeId, double chargeRate)
            throws SQLException {

        String results = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(mf.getChargeCode(), "CmChargeBranch", "chargeCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                CmChargeBranch chg = new CmChargeBranch();

                chg.setTaxcharge_oem_id(mf.getTaxChgOemId());
                chg.setChargeCode(mf.getChargeCode().toUpperCase().trim());
                chg.setChargeDesc(mf.getChargeDesc());
                chg.setChargeRatePerc(mf.getChargeRate());
                chg.setIsActualOrPerc("P");
                chg.setCreatedBy(mf.getDealer_code());
                chg.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                chg.setModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                chg.setModifiedBy(mf.getDealer_code());

                hrbsession.save(chg);
                hrbsession.getTransaction().commit();
                results = "Success@@Charge Type Code '" + mf.getChargeCode() + "' has been Successfully Added.";

            } else {
                results = "Failure@@Charge Type Code '" + mf.getChargeCode() + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Charge Type, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return results;
    }

    public String updateChargeBranchMaster(int id, String type, masterForm mf)
            throws SQLException {

        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hrbsession.beginTransaction();
            CmChargeBranch chg = (CmChargeBranch) hrbsession.load(CmChargeBranch.class, id);
            chg.setChargeDesc(mf.getChargeDesc());
            chg.setModifiedDate(new java.util.Date());
            chg.setModifiedBy(mf.getDealer_code());
            chg.setChargeRatePerc(mf.getChargeRate());
            hrbsession.saveOrUpdate(chg);

            result = "Success@@Charge Type Code '" + mf.getChargeCode() + "' has been updated Successfully.";
            hrbsession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Charge Type Code, Please contact System Administrator.";
            hrbsession.getTransaction().rollback();

        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Charge Type Code, Please contact system Administrator.";
            }
        }
        return result;
    }

    public ArrayList<masterForm> getTaxCtgryBranchList(masterForm masterForm)
            throws SQLException {

        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String searchCategoryCode = masterForm.getSearchCategoryCode() == null ? "" : masterForm.getSearchCategoryCode();
        String searchCategoryDesc = masterForm.getSearchCategoryDesc() == null ? "" : masterForm.getSearchCategoryDesc();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            //if (!searchCategoryCode.equals("") || !searchCategoryDesc.equals("")) {
            Subsql = " where taxCtgryCode like '%" + searchCategoryCode + "%' and taxCtgryDesc like '%" + searchCategoryDesc + "%'";
            //}
            sql = "from CMTaxCategoryBranch " + Subsql + " order by taxCtgryCode";
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                CMTaxCategoryBranch chg = (CMTaxCategoryBranch) itr.next();
                masterForm = new masterForm();
                masterForm.setTaxCtgryCode(chg.getTaxCtgryCode());
                masterForm.setTaxCtgryDesc(chg.getTaxCtgryDesc());
                masterForm.setBranch_id(chg.getBranch_id());
                masterForm.setTaxctgry_branch_id(chg.getTaxctgry_branch_id());
                masterForm.setIsActive(chg.getIsActive());
                dataList.add(masterForm);
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

    public LinkedHashSet<LabelValueBean> getTaxTypeBranchList() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> taxList = new LinkedHashSet<LabelValueBean>();
        LabelValueBean lb = null;
        String id = "", taxChargeCode = "", taxChargeDesc = "";
        try {
            Query query = session.createQuery("select taxOemId,taxChargeCode,taxChargeDesc from "
                    + "CmTaxCharg_OEM where IsActive='Y' order by taxChargeCode ");

            List<String> l = (List<String>) query.list();
            Iterator itr = l.iterator();
            while (itr.hasNext()) {
                LabelValueBean lv = null;
                Object obj[] = (Object[]) itr.next();
                String name0 = obj[0].toString();

                taxChargeCode = (String) obj[1] == null ? "" : (String) obj[1].toString();
                taxChargeDesc = (String) obj[2] == null ? "" : (String) obj[2].toString();
                id = name0 == null ? "" : name0.trim();
                lv = new LabelValueBean(taxChargeCode + " [" + taxChargeDesc + "] ", id);
                taxList.add(lv);
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taxList;
    }

    public String getTaxChargeByTaxTypeId(masterForm mForm) {
        String chargeCode = "", showlabel = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbsession.createQuery("select distinct charge_branch_id, chargeCode,chargeDesc from CmChargeBranch where taxcharge_oem_id=:taxchargeOemId order by chargeCode");

            query.setParameter("taxchargeOemId", mForm.getTaxChgOemId());
            Iterator itr = query.list().iterator();
            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();

                showlabel = (o[1] == null ? "-" : o[1].toString()).concat(" [").concat(o[2] == null ? "-" : o[2].toString()).concat("]");
                chargeCode = chargeCode + (o[0] == null ? "0" : o[0].toString()) + "$$" + showlabel + "||";

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
        return chargeCode;
    }

    public String saveTaxCategoriesDetail(masterForm mf, String user_id) {
        String result = "success";
        Session hrbsession = null;
        try {
            hrbsession = HibernateUtil.getSessionFactory().getCurrentSession();
            String check = check_in_Db(mf.getTaxCtgryCode(), "CMTaxCategoryBranch", "taxCtgryCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                CMTaxCategoryBranch chg = new CMTaxCategoryBranch();
//                 chg.setTaxctgry_branch_id(mf.getTaxctgry_branch_id());
                chg.setBranch_id(0);//mf.getBranch_id()
                chg.setTaxCtgryCode(mf.getTaxCtgryCode().toUpperCase());
                chg.setTaxCtgryDesc(mf.getTaxCtgryDesc());
                chg.setIsActive("Y");
                chg.setCreatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                chg.setCreatedBy(user_id);
                chg.setModifiedDate(null);
                chg.setModifiedBy(null);
                hrbsession.save(chg);
                for (int i = 0; i < mf.getDisplay_Order().length; i++) {
                    if (!mf.getTax_Type()[i].equals("")) {
                        //CMTaxCategoryBranch cMTaxCategoryBranch=new CMTaxCategoryBranch();
                        //cMTaxCategoryBranch.setTaxctgry_branch_id(mf.getTaxctgry_branch_id());
                        CMTaxCategoryBranchDetail cmtaxCtgryBrchDetail = new CMTaxCategoryBranchDetail();
                        cmtaxCtgryBrchDetail.setcMTaxCategoryBranch(chg);
                        cmtaxCtgryBrchDetail.setCharge_branch_id(mf.getCharge_branch_id()[i]);
                        cmtaxCtgryBrchDetail.setSaleType(null);
                        cmtaxCtgryBrchDetail.setIsPrimaryTax(mf.getIsPrimaryTax()[i]);
                        cmtaxCtgryBrchDetail.setDisplayOrder(mf.getDisplay_Order()[i]);
                        cmtaxCtgryBrchDetail.setCreatedOn(new java.sql.Timestamp(new java.util.Date().getTime()));
                        cmtaxCtgryBrchDetail.setCreatedBy(user_id);
                        cmtaxCtgryBrchDetail.setModifiedDate(null);
                        cmtaxCtgryBrchDetail.setModifiedBy(null);
                        hrbsession.save(cmtaxCtgryBrchDetail);
                        if (i % 20 == 0) {
                            hrbsession.flush();
                            hrbsession.clear();
                        }
                    }
                }
                hrbsession.getTransaction().commit();
            } else {
                result = "failure";
            }
        } catch (Exception e) {
            hrbsession.getTransaction().rollback();
            if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
            result = "failure1";
            e.printStackTrace();
        } finally {
            if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
        }
        return result;
    }

    public String deleteTaxCategoriesDetail(masterForm mf, String taxctgry_branch_id) {
        String result = "failure";
        Session hrbsession = null;
        try {
            hrbsession = HibernateUtil.getSessionFactory().getCurrentSession();
            hrbsession.beginTransaction();
            CMTaxCategoryBranch chg = (CMTaxCategoryBranch) hrbsession.load(CMTaxCategoryBranch.class, Integer.parseInt(taxctgry_branch_id));
            hrbsession.delete(chg);
            hrbsession.getTransaction().commit();
            result = "success";
        } catch (Exception e) {
            hrbsession.getTransaction().rollback();
            if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
            result = "failure1";
            e.printStackTrace();
        } finally {
            if (hrbsession != null && hrbsession.isOpen()) {
                hrbsession.close();
            }
        }
        return result;
    }

    public void getTaxCtgryBranchDetail(masterForm mForm, String user_id, Integer taxctgry_branch_id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            CMTaxCategoryBranchDetail cMTaxC = null;
            masterForm cForm = null;
            Integer charge_ranchId = 0;
            CmChargeBranch branch;
            CmTaxCharg_OEM oem;
            ArrayList<masterForm> listF = new ArrayList<masterForm>();
            CMTaxCategoryBranch cmTaxCategoryBranch = (CMTaxCategoryBranch) session.load(CMTaxCategoryBranch.class, new Integer(taxctgry_branch_id));
            mForm.setTaxctgry_branch_id(taxctgry_branch_id);
            mForm.setBranch_id(cmTaxCategoryBranch.getBranch_id());
            mForm.setTaxCtgryCode(cmTaxCategoryBranch.getTaxCtgryCode().toString());
            mForm.setTaxCtgryDesc(cmTaxCategoryBranch.getTaxCtgryDesc().toString());
            mForm.setCreatedBy(cmTaxCategoryBranch.getCreatedBy());
            mForm.setCreatedOn(cmTaxCategoryBranch.getCreatedOn());
            mForm.setIsActive(cmTaxCategoryBranch.getIsActive().toString());
            mForm.setModifiedBy(null);
            mForm.setModifiedOn(null);
            Collection<CMTaxCategoryBranchDetail> cMTaxCategoryBranchDetail = cmTaxCategoryBranch.getcMTaxCategoryBranchDetail();

            Iterator itr = cMTaxCategoryBranchDetail.iterator();
            while (itr.hasNext()) {
                cMTaxC = (CMTaxCategoryBranchDetail) itr.next();
                cForm = new masterForm();
                cForm.setTaxctgry_branch_id(taxctgry_branch_id);
                cForm.setChargeBranchId(cMTaxC.getCharge_branch_id());
                cForm.setSaleType(cMTaxC.getSaleType() == null ? "" : mForm.getSaleType());
                cForm.setIsPrimary_Tax(cMTaxC.getIsPrimaryTax());
                cForm.setDisplayOrder(cMTaxC.getDisplayOrder());
                charge_ranchId = cMTaxC.getCharge_branch_id();
                branch = (CmChargeBranch) session.get(CmChargeBranch.class, charge_ranchId);
                oem = (CmTaxCharg_OEM) session.get(CmTaxCharg_OEM.class, branch.getTaxcharge_oem_id());
                cForm.setTaxCode(oem.getTaxOemId().toString());
                cForm.setChargeDesc(branch.getChargeCode() + " (" + branch.getChargeDesc() + ")");
                listF.add(cForm);
            }
            mForm.setCmTaxDetails(listF);
        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public String UpdateTaxCtgryBranch(String status, Integer taxctgry_branch_id, String userId) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        String newStatus = "";
        try {
            hrbsession.beginTransaction();
            if (status.equals("Y")) {
                newStatus = "N";
            } else {
                newStatus = "Y";
            }
            CMTaxCategoryBranch cmTaxCategoryBranch = (CMTaxCategoryBranch) hrbsession.load(CMTaxCategoryBranch.class, new Integer(taxctgry_branch_id));
            cmTaxCategoryBranch.setIsActive(newStatus);
            cmTaxCategoryBranch.setCreatedBy(userId);
            cmTaxCategoryBranch.setCreatedOn(new java.util.Date());
            hrbsession.saveOrUpdate(cmTaxCategoryBranch);
            result = "Success@@isActive has been updated Successfully.";
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Tax Categories, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Wage, Please contact system Administrator.";
            }
        }
        return result;
    }

    public List<masterForm> getTaxAssignedList(masterForm custForm, String dealerCode, String dealerSearch, String stateSearch) {
        Session session = null;
        Query hQuery = null;
        List<masterForm> taxAssignedList = new ArrayList<masterForm>();
        masterForm mf;
        String Subsql = "";
        Object[] obj;
        String dealerSrch = dealerSearch == null ? "" : dealerSearch;
        String stateSrch = stateSearch == null ? "" : stateSearch;
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Subsql = " and cm.id.dealerCode like '%" + dealerSrch + "%' and um.stateName like '%" + stateSrch + "%' ";
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            String qry = "select cm.id.dealerCode,um.dealerName, cm.id.commodityCode,"
                    + " cm.id.effectiveDate ,tax.taxCtgryCode, tax.taxCtgryDesc "
                    + " from CmTaxMasterDealer cm, Dealervslocationcode um, CMTaxCategoryBranch tax"
                    + " where cm.id.dealerCode=um.dealerCode "
                    + " and tax.taxctgry_branch_id=cm.taxctgry_branch_id "
                    + Subsql + " order by cm.id.effectiveDate desc";

            hQuery = session.createQuery(qry);
            List list = hQuery.list();
            Iterator itr = list.iterator();

            while (itr.hasNext()) {
                obj = (Object[]) itr.next();
                mf = new masterForm();

                mf.setDealerCode(obj[0] == null ? "-" : obj[0].toString());
                mf.setDealer_code(obj[0] == null ? "-" : obj[0].toString().concat("[" + (obj[1] == null ? "NA" : obj[1].toString()) + "]"));

                mf.setCommodityCodeTax(obj[2] == null ? "-" : obj[2].toString());
                mf.setEffectiveDate(obj[3] == null ? "-" : dateFormate.format(obj[3]));
                mf.setTaxCategory(obj[5] == null ? "-" : obj[5].toString().concat("[" + (obj[4] == null ? "NA" : obj[4].toString()) + "]"));
                taxAssignedList.add(mf);
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return taxAssignedList;
    }

    public LinkedHashSet<LabelValueBean> getCommodityCodeList() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> taxList = new LinkedHashSet<LabelValueBean>();
        String code = "", id = "";
        try {
            //Query query = session.createQuery("select distinct cm.id.commodityCode,cm.id.commodityCode from CmTaxMasterDealer cm order by cm.id.commodityCode");
            Query query = session.createSQLQuery("select distinct CommodityCode from CM_TAXINVOICE_SEQ  order by CommodityCode");

            List<String> l = (List<String>) query.list();
            Iterator itr = l.iterator();
            while (itr.hasNext()) {
                LabelValueBean lv = null;
                Object obj = (Object) itr.next();
                String name1 = (String) obj;


                code = name1 == null ? "" : name1.trim();
                id = name1 == null ? "" : name1.trim();
                lv = new LabelValueBean(code, id);
                taxList.add(lv);
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taxList;
    }

    public LinkedHashSet<LabelValueBean> getCMTaxCategoryBranchList() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> taxList = new LinkedHashSet<LabelValueBean>();
        String code = "", id = "", codedesc = "";
        try {
            Query query = session.createQuery("select distinct taxctgry_branch_id,taxCtgryCode,taxCtgryDesc from "
                    + "CMTaxCategoryBranch where isActive='Y' order by taxCtgryCode");

            List<String> l = (List<String>) query.list();
            Iterator itr = l.iterator();
            while (itr.hasNext()) {
                LabelValueBean lv = null;
                Object obj[] = (Object[]) itr.next();
                String name1 = (String) obj[1];
                String namedesc = (String) obj[2];
                String name0 = obj[0].toString();

                code = name1 == null ? "" : name1.trim();
                codedesc = namedesc == null ? "" : namedesc.trim();
                id = name0 == null ? "" : name0.trim();
                lv = new LabelValueBean(code + " [ " + codedesc + " ]", id);
                taxList.add(lv);
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taxList;
    }

    public String saveTaxAssignedToDealer(masterForm masterForm, String userId) {

        Session session = null;
        String result = "failure";
        java.util.Date today = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String s = sdf.format(today);
            today = sdf.parse(s);

            Query query2 = session.createSQLQuery("EXEC  Insert_Taxes_Assigned_To_Dealer :CommodityCode,:StateName,:EffectiveDate,:taxctgry_branch_id,:CreatedBy,:CreatedOn,:ModifiedBy,:ModifiedOn");
            query2.setParameter("CommodityCode", masterForm.getCommodityCodeTax().trim());
            query2.setParameter("StateName", masterForm.getStateName().trim());
            query2.setParameter("EffectiveDate", sdf.parse(sdf.format(df.parse(masterForm.getEffectiveDate().trim()))));
            query2.setParameter("taxctgry_branch_id", Integer.parseInt(masterForm.getTaxCategory().trim()));
            query2.setParameter("CreatedBy", userId.toUpperCase());
            query2.setParameter("CreatedOn", today);
            query2.setParameter("ModifiedBy", null);
            query2.setParameter("ModifiedOn", null);
            query2.executeUpdate();
            session.getTransaction().commit();
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public String deleteTaxAssignedToDealer(masterForm mf) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String result = "failure";
        String hqlPart = "";

        try {
            session.beginTransaction();

            for (int i = 0; i < mf.getDealerCodeArr().length; i++) {
                if (!mf.getDealerCodeArr()[i].equals("")) {
                    hqlPart = "delete from CmTaxMasterDealer where commodityCode='"
                            + mf.getCommodityCodeArr()[i].trim() + "' and  dealerCode='"
                            + mf.getDealerCodeArr()[i].trim() + "' and  effectiveDate='"
                            + sdf.format(df.parse(mf.getEffectiveDateArr()[i].trim())) + "'";
                    Query query = session.createQuery(hqlPart);
                    query.executeUpdate();
                }

            }
            session.getTransaction().commit();
            result = "success";

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            result = "failure";
        } finally {
            session.close();
        }
        return result;
    }

    public ArrayList<masterForm> getpriceArrayList(String pricelistCode,String partNo,ArrayList<String> priceList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<masterForm> dispachedList = new ArrayList<masterForm>();

        try {
            String subqry = "";
            //if (!pricelistCode.equals("ALL")) {
                subqry = "where pm.spPriceMasterPK.pricelistCode=:pricelistCode and pm.catPart.partNo like ('%"+partNo+"%')";

            //}
//            else {
//                String picode = "";
//                for (int i = 0; i < priceList.size(); i++) {
//
//                    picode += (i == 0) ? "'"+priceList.get(i)+"'" : "," +"'"+ priceList.get(i)+"'";
//                }
//                 subqry = "where pm.spPriceMasterPK.pricelistCode in ("+picode+") ";
//            }
            Query query = session.createQuery("select pm.spPriceMasterPK.pricelistCode, pm.catPart.partNo,pm.catPart.p1,pm.price,pm.orderPrice "
                    + " from SpPriceMaster pm " + subqry + " order by pm.spPriceMasterPK.pricelistCode,pm.catPart.p1");
            //if (!pricelistCode.equals("ALL")) {
                query.setParameter("pricelistCode", pricelistCode);
            //}
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                masterForm mf = new masterForm();
                mf.setPriceList_code(obj[0] == null ? "" : obj[0].toString().trim());
                mf.setPartNo(obj[1] == null ? "" : obj[1].toString().trim());
                mf.setDescription(obj[2] == null ? "" : obj[2].toString().trim());
                mf.setPrice(obj[3] == null ? "0" : obj[3].toString());
                mf.setOrderPrice(obj[4] == null ? "0" : obj[4].toString());
                dispachedList.add(mf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dispachedList;
    }
    public ArrayList<masterForm> getpartArrayList(String partNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<masterForm> partList = new ArrayList<masterForm>();

        try {
           
            Query query = session.createQuery("select distinct cp.partNo,cp.p1,cp.partType,cp.p3,cp.p4,cp.np2 "
                    + " from CatPart cp where cp.partNo like('%"+partNo+"%')  order by cp.partNo");
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                masterForm mf = new masterForm();
                mf.setPartNo(obj[0] == null ? "" : obj[0].toString().trim());
                mf.setPart_Desc(obj[1] == null ? "" : obj[1].toString().trim());
                mf.setPartType(obj[2] == null ? "" : obj[2].toString().trim());
                mf.setRemark(obj[3] == null ? "" : obj[3].toString());
                mf.setServiceability(obj[4] == null ? "" : obj[4].toString());
                mf.setMoq(obj[5] == null ? "0" : obj[5].toString());
                partList.add(mf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return partList;
    }
    
    public ArrayList<masterForm> getVendorCodeList(masterForm masterForm, String nameSrch) throws SQLException {
        ArrayList<masterForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            dataList = new ArrayList<masterForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " where vendorCode like '%" + SearchQuery + "%' ";  //aggCode
            }
            sql = "from MSWvendormaster " + Subsql + " order by vendorCode";
            Query query = hrbsession.createQuery(sql);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
            	MSWvendormaster vm = (MSWvendormaster) itr.next();
                masterForm = new masterForm();
                masterForm.setVendorCode(vm.getVendorCode());
                masterForm.setVendorDesc(vm.getVendorDesc());
                masterForm.setStatus(vm.getIsActive() + "");
                dataList.add(masterForm);
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
    
    public String addVendorCodeMaster(String vendorCode, String vendorDesc, String userId) throws SQLException {
        String results = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            String check = check_in_Db(vendorCode, "MSWvendormaster", "vendorCode", "");
            if (check.equalsIgnoreCase("notexist")) {
                hrbsession.beginTransaction();
                MSWvendormaster vm = new MSWvendormaster();
                vm.setVendorCode(vendorCode.toUpperCase().trim());
                vm.setVendorDesc(vendorDesc.trim());
                vm.setIsActive('Y');
                vm.setLastUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                vm.setLastUpdatedBy(userId);
                hrbsession.save(vm);
                hrbsession.getTransaction().commit();
                results = "Success@@Vendor Code '" + vendorCode + "' has been Successfully Added.";
            } else {
                results = "Failure@@Vendor Code '" + vendorCode + "' Already Exists.";
            }
        } catch (Exception ae) {
            ae.printStackTrace();

            results = "Failure@@Unable to add Vendor Code, Please contact system Administrator.";
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return results;
    }

    public String UpdateVendorCodeMaster(String status, String id, String userId, String vendorCode, String vendorDesc, String type, int vendor_id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Aggregate.
         */
        String result = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String newStatus = "";
        try {
            hrbsession.beginTransaction();
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Y")) {
                    newStatus = "N";
                } else {
                    newStatus = "Y";
                }
                MSWvendormaster vm = (MSWvendormaster) hrbsession.load(MSWvendormaster.class, vendorCode);
                vm.setIsActive(newStatus.charAt(0));
                vm.setLastUpdatedDate(new java.util.Date());
                vm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(vm);
                result = "Success@@VendorCode '" + vendorCode + "' has been updated Successfully.";
            } else {
            	MSWvendormaster vm = (MSWvendormaster) hrbsession.load(MSWvendormaster.class, id);
                vm.setVendorDesc(vendorDesc);
                vm.setLastUpdatedDate(new java.util.Date());
                vm.setLastUpdatedBy(userId);
                hrbsession.saveOrUpdate(vm);
                result = "Success@@VendorCode '" + id + "' has been updated Successfully.";
            }
            hrbsession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Vendor Code, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "Failure@@Unable to update Vendor, Please contact system Administrator.";
            }
        }
        return result;
    }

}
