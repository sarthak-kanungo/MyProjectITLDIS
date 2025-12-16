package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;

import com.common.DBQueryConstant;
import com.common.MethodUtility;

import HibernateMapping.Dealervslocationcode;
import HibernateMapping.ItlContentMaster;
import HibernateMapping.ItlSubContentMaster;
import HibernateMapping.Jobcarddetails;
import HibernateMapping.MSWDmechanicmaster;
import HibernateMapping.MSWINSPDITractorDetails;
import HibernateMapping.PdiChecklist;
import HibernateMapping.PdiChecklistPK;
import HibernateMapping.PdiDetails;
import HibernateMapping.Pditravelcarddetails;
import HibernateMapping.PditravelcarddetailsPK;
import HibernateMapping.Vehicledetails;
import HibernateUtil.HibernateUtil;
import beans.ContentFormBean;
import beans.SubContentFormBean;
import beans.pdiForm;
import dbConnection.dbConnection;

/**
 *
 * @author vandana.singla
 */
public class pdiDAO implements DBQueryConstant {

    Connection conn = null;
    String dbPATH = new dbConnection().dbPathAuth;
    int rowsUpdate = 0;

    public pdiDAO(Connection conn) {
        this.conn = conn;
    }

    public pdiDAO() {
    }

    public String check_in_Db(String no_new, String table, String column, String SubQuery) {
        String val = "notexist";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        try {


            query = hrbsession.createQuery("select * from " + table + " where " + (column) + "='" + no_new + "'" + SubQuery);
            itr = query.list().iterator();
            if (itr.hasNext()) {
                val = "exist";
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
        return val;
    }

    /**
     * *******************************************************************************
     * @return String Common method
     * *******************************************************************************
     */
    public LinkedHashSet<LabelValueBean> getCommonLabelValue(String tableName, String fieldId, String fieldName, String filter, String whereCase) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();


        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        if (whereCase.equals("")) {
            whereCase = "where isActive='Y'";
        }

        if (tableName.equals("eventmaster")) {

            whereCase = " where  MONTH(StartDate) = MONTH(CURRENT_DATE) and dealercode='" + whereCase + "'";
        }

        //String query = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";

        String hql = "select  " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";



        Query query = hrbsession.createQuery(hql);

        List list = query.list();

        Iterator itr = list.iterator();


       // System.out.println("query==" + query);
        try {
            LabelValueBean lv = null;

            while (itr.hasNext()) {

                Object o[] = (Object[]) itr.next();
                String name1 = (String) o[1];
                String name0 = o[0].toString();

                name = name1 == null ? "" : name1.trim();
                id = name0 == null ? "" : name0.trim();
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

    public pdiForm getJobCard_vehicale_DetailFor_singleJobcard(pdiForm pf, String vinNo) throws SQLException {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String hql = " FROM Vehicledetails  where vinid='" + pf.getDealerCode() + "-" + vinNo + "'";

            Query query = hrbsession.createQuery(hql);

            List<Vehicledetails> list = query.list();

            Iterator<Vehicledetails> itr = list.iterator();

            Vehicledetails vd = null;

            if (itr.hasNext()) {
                vd = (Vehicledetails) itr.next();
                pf.setDealerCode(vd.getDealerCode());
                pf.setModelCode(vd.getModelCode());
                pf.setModelFamily(vd.getModelFamily());
                pf.setModelCodeDesc(vd.getModelCodeDesc());
                pf.setModelFamilyDesc(vd.getModelFamilyDesc());
                pf.setEngineNumber(vd.getEngineNo());
                pf.setVinId(vd.getVinid());
                pf.setInvoiceDate(vd.getItlInvoiceDate() == null ? "" : vd.getItlInvoiceDate().toString().trim().substring(0, vd.getItlInvoiceDate().toString().trim().indexOf(" ")));
                pf.setInvoiceDate(pf.getInvoiceDate().equals("") ? "" : sdf.format(df.parse(pf.getInvoiceDate())));
                pf.setInvoiceNo(vd.getItlInvoiceNo());

            }
            pf.setJobType("1");                      //  pf.setJobType(rs.getString(1));
            Query query1 = hrbsession.createQuery("select h.elementValue from HesConstants h where h.elementId=:elementId");
            query1.setInteger("elementId", 10);
            List l = query1.list();
            pf.setNoOfDays(Integer.parseInt(l.get(0).toString()));

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
        return pf;
    }

    public HashMap<String, ArrayList<String>> GetTravelDetailIns(String vinNo) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null, query3 = null;
        Iterator itr = null;
        Iterator itr1 = null;
        boolean Uflag = false;

        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ArrayList<String> tempList = null;
        try {
            query = hrbsession.createQuery(" from MSWINSPDITractorDetails where isActive='Y' order by partName ");
            itr = query.list().iterator();

            while (itr.hasNext()) {
                MSWINSPDITractorDetails tm = (MSWINSPDITractorDetails) itr.next();//TmsTravelcarddetails
                ArrayList<String> makeName = new ArrayList();
                if (map.containsKey(tm.getPartName())) {
                    makeName = map.get(tm.getPartName());
                }
                makeName.add(tm.getMakeName());
                Collections.sort(makeName);
                map.put(tm.getPartName(), makeName);
            }
//            System.out.println("map size:"+map.size());
            map = sortByComparatorString(map, true);
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
        return map;
    }

    public boolean checkPDI(String vinNo, String dealercode) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        boolean bool = false;       
        try {
            if (vinNo != null) {
                //query = hrbsession.createQuery("from PdiDetails where vinNo='" + vinNo + "' and dealercode='" + dealercode + "'");
                query = hrbsession.createSQLQuery("select ISNULL(pd.PDI_No,'') PDI_DONE,vd.PDI_STATUS "
                        + "from SW_vehicledetails vd left join SW_pdi_details pd on vd.dealerCode=pd.dealerCode "
                        + "and vd.vinNo=pd.vinNo where vd.vinNo='" + vinNo + "' and vd.Dealercode ='" + dealercode + "'").addScalar("PDI_DONE", StringType.INSTANCE).addScalar("PDI_STATUS", StringType.INSTANCE);

                itr = query.list().iterator();
                if (itr.hasNext()) {                    
                    Object obj[] = (Object[]) itr.next();                   
                    if (!obj[0].toString().equalsIgnoreCase("")) {
                        bool = true;
                    }
                }
            }
            // System.out.println("ckechk pdi" + bool);
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
        return bool;
    }

    public boolean checkPDIView(pdiForm pf) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        boolean bool = false;
        try {
            if (pf.getVinNo() != null) {

          //      System.out.println("vinno in check pdi" + pf.getVinNo());

                String hql = " FROM  Vehicledetails where vinNo='" + pf.getVinNo() + "' and dealerCode='" + pf.getDealerCode() + "'";

                query = hrbsession.createQuery(hql);

                itr = query.list().iterator();

                if (itr.hasNext()) {
                    bool = true;

                }
            }
         //   System.out.println("ckechk pdi" + bool);
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
        return bool;
    }

    public ArrayList<pdiForm> getChassisList(pdiForm pdiForm, String SearchValue, String user, String dealercode, Vector userFunctionalities) throws SQLException {

        String hql = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<pdiForm> dataList = null;
        String Subsql = "";
        String SearchQuery = SearchValue == null ? "" : SearchValue;
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            dataList = new ArrayList<pdiForm>();
            Query query = null;
            if (!SearchQuery.equals("")) {
                Subsql = " and  v.vinNo like '%" + SearchQuery + "%' ";
            }
            String query_string = "select v.vinNo,v.modelFamily,v.modelCode,u.dealerName,u.dealerCode,u.location,u.locationCode,"
                    + "v.pdiPendingDate  from Vehicledetails v, Dealervslocationcode u where "
                    + " v.dealerCode=u.dealerCode and v.pdiStatus='N'";
             //String query_string = "select v.vinNo,v.modelFamily,v.modelCode,u.dealerName,u.dealerCode,u.location,u.locationCode,"
                   // + "v.pdiPendingDate  from Vehicledetails v, Dealervslocationcode u where "
                   // + " v.dealerCode=u.dealerCode and v.pdiStatus='N' and v.insStatus='N'";// changes by deepak 29-10-2015

            query = hrbsession.createQuery(query_string + " and :dealerList LIKE ('%'+v.dealerCode+'%') " + Subsql + " order by v.pdiPendingDate");
            if (pdiForm.getDealerCode() != null && !pdiForm.getDealerCode().equals("") && !pdiForm.getDealerCode().equalsIgnoreCase("ALL")) {
                dealerList.add(pdiForm.getDealerCode());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(hrbsession,user);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

   /*         if (userFunctionalities.contains("101")) {
                query = hrbsession.createQuery(query_string + " and v.dealerCode=:dealerCode " + Subsql + " order by v.pdiPendingDate");
                query.setParameter("dealerCode", dealercode);
            } else if (userFunctionalities.contains("102")) {
                ArrayList<String> dealerList = new ArrayList<String>();
                query = hrbsession.createQuery(query_string + " and v.dealerCode IN(:dealerList) " + Subsql + " order by v.pdiPendingDate");
                //ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user + "'");
                if (pdiForm.getDealerCode() != null && !pdiForm.getDealerCode().equals("") && !pdiForm.getDealerCode().equalsIgnoreCase("ALL")) {
                    dealerList.add(pdiForm.getDealerCode());
                } else {
                    dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user + "'");
                }
                query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                if (pdiForm.getDealerCode() != null && !pdiForm.getDealerCode().equals("") && !pdiForm.getDealerCode().equalsIgnoreCase("ALL")) {
                    query = hrbsession.createQuery(query_string + Subsql + "  and v.dealerCode=:dealerCode order by v.pdiPendingDate");
                    query.setParameter("dealerCode", pdiForm.getDealerCode());
                } else {
                    query = hrbsession.createQuery(query_string + Subsql + " order by v.pdiPendingDate");
                }                
            }
    * 
    */
            //DATEDIFF(DAY, v.pdiPendingDate, GETDATE())
            List<List> list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    pdiForm = new pdiForm();
                    pdiForm.setVinNo(obj[0] == null ? "" : obj[0].toString().trim());
                    pdiForm.setModelFamily(obj[1] == null ? "" : obj[1].toString().trim());
                    pdiForm.setModelCode(obj[2] == null ? "" : obj[2].toString().trim());
                    pdiForm.setDealerName(obj[3] == null ? "" : obj[3].toString().trim());
                    pdiForm.setDealerCode(obj[4] == null ? "" : obj[4].toString().trim());
                    pdiForm.setLocationName(obj[5] == null ? "" : obj[5].toString().trim());
                    pdiForm.setDealerLocation(obj[6] == null ? "" : obj[6].toString().trim());
                    if (obj[7] != null) {
                        Date pendingdate = (Date) obj[7];
                        long diff = (System.currentTimeMillis() - pendingdate.getTime()) / (1000 * 60 * 60 * 24);
                        pdiForm.setPdiPendingDays(diff);//obj[7] == null ? 0 :Integer.parseInt(obj[7].toString().trim()));
                    }
                    dataList.add(pdiForm);
                }
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

    public void getPDIFormContent(pdiForm pdiForm) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ContentFormBean cf = null;
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;
        String subContentId = null;


        String formName = pdiForm.getJobType();


        ArrayList<SubContentFormBean> subContentFormList = new ArrayList();

        SubContentFormBean dataBean = null;//new SubContentFormBean();
        ArrayList<SubContentFormBean> tempList = null;
        try {
            getForm_id(pdiForm, formName);
            //  System.out.println("form id" + pdiForm.getFormId());
            if (pdiForm.getFormId() == null) {
                pdiForm.setFormId("0");
            }

            Query query = hrbsession.createQuery("select fc.contentId,cm.contentDesc,fc.subContentId,scm.subContentDesc from ItlFormContent fc"
                    + " ,ItlContentMaster  as cm,ItlSubContentMaster  as scm where cm.contentId = fc.contentId"
                    + "  and scm.subContentId = fc.subContentId"
                    + "  and fc.formId=:FormId order by fc.orderSeq");
            query.setParameter("FormId", Integer.parseInt(pdiForm.getFormId()));

            List list = query.list();
            Iterator itr = list.iterator();

            while (itr.hasNext()) {

                Object o[] = (Object[]) itr.next();

//                System.out.println("o[0]==" + o[0].toString());
//                System.out.println("o[1]==" + o[1].toString());
//                System.out.println("o[2]==" + o[2].toString());
//                System.out.println("o[3]==" + o[3].toString());

                ItlContentMaster icm = (ItlContentMaster) o[0];

                contentId = Integer.parseInt(icm.getContentId().toString());//rs.getInt("CONTENT_ID");//itl_content_master,itl_sub_content_master,itl_form_content
                cf = new ContentFormBean();
                cf.setContentId(contentId);
                cf.setContentDesc(o[1] == null ? "" : o[1].toString().trim());

                ItlSubContentMaster isc = (ItlSubContentMaster) o[2];
                subContentId = (o[2] == null ? "" : isc.getSubContentId().toString());//"" + rs.getInt("SUB_CONTENT_ID");
                //=Long.toString(sconid);
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
                        dataBean.setSubContentDesc(o[3].toString().trim());
                    }
//                    dataBean.setTextBoxOption(rs.getString("TEXTBOX_OPTION"));

                    subContentFormList.add(dataBean);
                }

            }
            pdiForm.setDataMap(treeMap);

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

    public void getForm_id(pdiForm pdiForm, String formName) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        //  System.out.println("modelcode and jobtype" + pdiForm.getModelCode() + "and" + pdiForm.getJobType());
        try {


            Query query = hrbsession.createQuery("select fm.formMasterPK.formid FROM FormMaster fm where fm.formMasterPK.jobtypeid=:jobTypeId and fm.formMasterPK.modelcode=:modelCode");
            query.setParameter("jobTypeId", Integer.parseInt(pdiForm.getJobType()));
            query.setParameter("modelCode", pdiForm.getModelCode());
            List list = query.list();
            if (query != null && list.size() > 0) {
                pdiForm.setFormId("" + list.get(0));
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
    }

    public String addPDIVehileInformation(pdiForm pf, String userid) throws SQLException {
        String results = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;

        String sql = "";
        Calendar cal = null;
        String PDINo = "";
        int insertF = 0;
        int month = 0;
        int year = 0;
       // System.out.println("enter in dao");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;
        PDINo = "PDI/" + userid + "/" + month + "/" + year + "/";

        PdiDetails pd = new PdiDetails();


        try {


            hrbsession.beginTransaction();
            query = hrbsession.createQuery("select count(*) from PdiDetails where dealercode='" + pf.getDealerCode() + "'");
            itr = query.list().iterator();
            if (itr.hasNext()) {
                PDINo = PDINo + (Integer.parseInt((String) itr.next()) + 1);
            }

            //PDI_No,Dealercode,DealerName,Dealer_location,vinNo,EngineNo,InvoiceDate,PDI_Date,TractorReceieveDate,Is_ServerSync,LastUpdateddate) values(?,?,?,?,?,?,?,?,?,?,?) ";

            //pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            pd.setPDINo(PDINo);
            pd.setDealercode(pf.getDealerCode());

            //  pd.setDealerName("");
            //  pd.setDealerlocation( pf.getDealerLocation());
            pd.setVinNo(pf.getVinNo());
            pd.setEngineNo(pf.getEngineNumber());
            pd.setInvoiceDate(sdf.parse(pf.getInvoiceDate()));
            pd.setPDIDate(sdf.parse(pf.getPdiDate()));
            pd.setTractorReceivedDate(sdf.parse(pf.getJobCardDate()));
            pd.setIsServerSync('N');
            pd.setLastSyncDate(new java.util.Date());
            hrbsession.save(pd);
            hrbsession.getTransaction().commit();

            //pstmt1.setTimestamp(10, new java.sql.Timestamp(sdf.parse(pf.getJobCardDateStr()).getTime()));


            //    pf.setPdiId(pd.getId());

            results = "Success@@Vehicle Information has been Successfully Added";
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Vehicle Information, Please contact system Administrator.";
            hrbsession.getTransaction().rollback();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
                results = "Failure@@Unable to add Vehicle Information, Please contact system Administrator.";
            }
        }
        return results;
    }

    public String addPDIStandardChecksData(pdiForm pf, TreeMap<Integer, ArrayList<String>> tMap, String userid, String dealercode, LinkedList<ContentFormBean> TravelCardList) throws SQLException {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        ArrayList<String> dataList = null;

        String result = "";

        String PDINo = "";

        java.sql.Timestamp defaulttime = new Timestamp(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        PDINo = new MethodUtility().getNumber(hrbsession, "PdiDetails", dealercode, "PDI");
        try {
            Query query = hrbsession.createQuery(" from PdiDetails where vinNo=:vinNo and dealercode=:dealercode and invoiceDate=:invoiceDate");
            query.setString("vinNo", pf.getVinNo());
            query.setString("dealercode", dealercode);
            query.setDate("invoiceDate", sdf.parse(pf.getInvoiceDate()));
            hrbsession.beginTransaction();
            List list1 = query.list();
            Iterator itr1 = list1.iterator();

            if (itr1.hasNext()) {
                result = "EXIST@@Already inserted PDI for this chassis No";
            } else {

                //  System.out.println("***"+PDINo);
                PdiDetails pdi = new PdiDetails();
                pdi.setPDINo(PDINo);
                pdi.setDealercode(dealercode);
                // pdi.setDealerName(userid);
                // pdi.setDealerlocation(pf.getDealerLocation());
                //
                pdi.setVinNo(pf.getVinNo());

                pdi.setEngineNo(pf.getEngineNumber());
                if (pf.getInvoiceDate() == null || pf.getInvoiceDate().isEmpty()) {
                    pdi.setInvoiceDate(defaulttime);
                } else {
                    pdi.setInvoiceDate(sdf.parse(pf.getInvoiceDate()));

                }
                if (pf.getPdiDate() == null || pf.getPdiDate().isEmpty()) {
                    pdi.setPDIDate(defaulttime);

                } else {
                    pdi.setPDIDate(sdf.parse(pf.getPdiDate()));

                }
                if (pf.getJobCardDate() == null || pf.getJobCardDate().isEmpty()) {
                    pdi.setTractorReceivedDate(defaulttime);

                } else {
                    pdi.setTractorReceivedDate(sdf.parse(pf.getJobCardDate()));

                }
                pdi.setRemarks(pf.getRemark());
                pdi.setItlInvoiceNo(pf.getInvoiceNo());
                pf.getRepname();//pdi.se
                if (pf.getRepname() != null && !pf.getRepname().equals("")) {
                    MSWDmechanicmaster mm = (MSWDmechanicmaster) hrbsession.get(MSWDmechanicmaster.class, pf.getRepname());
                    pdi.setMechanicDealerKey(mm);
                }

                pdi.setIsServerSync('N');
                pdi.setIsTMSSync('N');
                pdi.setCreatedBy(userid);
                pdi.setCreatedOn(new java.util.Date());
                Vehicledetails vd = (Vehicledetails) hrbsession.load(Vehicledetails.class, pf.getVinId());
                pdi.setVinid(vd);
                hrbsession.save(pdi);

                dataList = new ArrayList<String>();
                //   System.out.println("pdi id" + pf.getPdiId());
                PdiChecklist pcl = null;
                PdiChecklistPK pck = null;

                //List<PdiChecklist>

                for (Map.Entry<Integer, ArrayList<String>> entryMap : tMap.entrySet()) {
                    dataList = entryMap.getValue();

                    for (int d = 0; d < dataList.size(); d += 4) {
                        pcl = new PdiChecklist();
                        pck = new PdiChecklistPK();
                        // icm = (ItlContentMaster) hrbsession.load(ItlContentMaster.class,  Long.parseLong(entryMap.getKey()+""));
                        // pstmt.setString(3, dataList.get(d));
                        // ism = (ItlSubContentMaster) hrbsession.load(ItlSubContentMaster.class, Long.parseLong(dataList.get(d)));
                        pck.setPDINo(PDINo);
                        pck.setContentId(entryMap.getKey());
                        pck.setSubContentId(Long.parseLong(dataList.get(d)));
                        pcl.setPdiChecklistPK(pck);
                        pcl.setOkStatus(dataList.get(d + 1));
                        pcl.setRemarks(dataList.get(d + 2));
                        pcl.setActionTaken(dataList.get(d + 3));
                        hrbsession.save(pcl);
                    }
                }
                boolean Tflag = addTravelCardData4pdi(hrbsession, pf.getVinNo(), PDINo, TravelCardList);

                if (Tflag) {
                    vd.setPdiStatus('Y');
                    vd.setItlInvoiceNo(pf.getInvoiceNo());
                   // vd.setInsStatus('N');// changes by deepak 29-10-2015
                    vd.setIsServerSync('N');
                    vd.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                    hrbsession.saveOrUpdate(vd);

                    hrbsession.getTransaction().commit();

                    result = "SUCCESS@@PDI for Chassis No " + pf.getVinNo() + " has been Successfully Added";
                } else {
                    result = "FAILURE@@Unable to add PDI Information, Please contact system Administrator.";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "FAILURE@@Unable to add PDI Information, Please contact system Administrator.";
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

    public boolean addTravelCardData4pdi(Session hrbsession, String vinNo, String pdino, LinkedList<ContentFormBean> TravelcardList) throws SQLException {
        boolean Tflag = false;
        try {

            //delete command for installation_details
            //          pstmt = conn.prepareStatement(Insert_travelCardData);

            Pditravelcarddetails itd = null;
            PditravelcarddetailsPK itdpk = null;//iNSNo
//ins_travelcard_details(ins_id, vinNo, PartNo, PartVendorName, PartSerialNo)
            for (int i = 0; i < TravelcardList.size(); i++) {
              //  System.out.println("TravelcardList.size()" + TravelcardList.size());
              //  System.out.println("partno" + TravelcardList.get(i).getContentDesc());

                // itd.setVinNo(vinNo);
                itd = new Pditravelcarddetails();
                itdpk = new PditravelcarddetailsPK();
                itdpk.setPDINo(pdino);
                itdpk.setPartName(TravelcardList.get(i).getContentDesc());
                itd.setPditravelcarddetailsPK(itdpk);
                itd.setPartVendorName(TravelcardList.get(i).getObservation());
                itd.setPartSerialNo(TravelcardList.get(i).getStatus());
                // itd.setPartSerialNo((i+1)+"");
                hrbsession.save(itd);
            }


            Tflag = true;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return Tflag;
    }

    public String getPDINumber(String userid, String dealercode) throws SQLException {
    	
        Session hrbsession = null;
        try {
        //Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        	hrbsession = HibernateUtil.getSessionFactory().openSession();

        Calendar cal = null;
        String location = "";
        String pdi = "";
        long pdiid = 0;
        int month = 0;
        String mon = "";
        String yr = "";
        String id = "";
        int year = 0;
        String branchcode = "";

        Dealervslocationcode dv = null;

        Query query = hrbsession.createQuery("from Dealervslocationcode where dealerCode=?");//where DealerCode=? ";//dealervslocationcode(Id, DealerCode, LocationCode)

        query.setParameter(0, dealercode);

        List list = query.list();

        Iterator itr = list.iterator();

        if (itr.hasNext()) {
            dv = (Dealervslocationcode) itr.next();
            location = dv.getLocationCode();
//            branchcode = dv.getBranchCode();
        } else {
            return "notexist";
        }


        cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;

        if (month <= 9) {
            mon = "0" + month;
        }
        yr = Integer.toString(year).substring(0, Integer.toString(year).length());

        Query query1 = hrbsession.createQuery("select count(*) from PdiDetails where YEAR(getDate())= YEAR(createdOn) and dealerCode='" + dealercode + "'");
        Iterator itr1 = query1.list().iterator();
        if (itr1.hasNext()) {
            pdiid = Long.parseLong(itr1.next().toString());
            pdiid = pdiid + 1;
        } else {
            pdiid = Long.parseLong("1");
        }
        pdi = "PDI/" + location + "/" + dealercode + "/" + branchcode + "/" + mon + "/" + yr + "/" + pdiid;


        return pdi;
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
    }

    public String GetVinNoList(String searchname, String dealercode) {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        StringBuilder data = new StringBuilder("<listdata>");
        try {


            String hql = " from Vehicledetails  where  pdiStatus='Y' and DealerCode='" + dealercode + "' and vinNo like '%" + searchname + "%'";

            Query query = hrbsession.createQuery(hql);

            List<Vehicledetails> list = query.list();

            Iterator<Vehicledetails> itr = list.iterator();

            Vehicledetails vd = null;

            int counter = 0;

            while (itr.hasNext()) {

                vd = (Vehicledetails) itr.next();

                // rs = st.executeQuery(GetVinNoList_PDI + User_Id + "' and ti.vinNo like '%" + searchname + "%'");
                // System.out.println("" + GetVinNoList_PDI + User_Id + "' and ti.vinNo like '%" + searchname + "%'");

                counter++;
                data.append(vd.getVinNo().trim() + "|");
            }
            data.append("</listdata>");
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
        return data.toString();

    }

    public pdiForm getSinglePDIData(pdiForm pf, String pdiNo) throws SQLException {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PdiDetails pd = null;
        try {
//            String hql = " FROM  Vehicledetails where vinNo='" + vinNo + "' and dealerCode='"+pf.getDealerCode()+"'";
            Query query = hrbsession.createQuery("select v.vinNo,v.engineNo,v.modelFamily,v.modelCode,v.modelFamilyDesc,v.modelCodeDesc,"
                    + " p.pDINo,p.invoiceDate,p.tractorReceivedDate,p.pDIDate,p.itlInvoiceNo,u.dealerName,u.dealerCode,p.remarks,u.location,u.locationCode,(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =p.mechanicDealerKey ) as mechanicName  "
                    + " from Vehicledetails v, Dealervslocationcode u,PdiDetails p where "
                    + " v.vinNo=p.vinNo and v.dealerCode=u.dealerCode and v.pdiStatus='Y' and p.pDINo=:pdiNo");
            query.setParameter("pdiNo", pdiNo);
//            List<Vehicledetails> list = query.list();
            Iterator itr = query.list().iterator();
//            Vehicledetails vd = null;
            if (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
             //   System.out.println("" + obj);
//               vd = (Vehicledetails) itr.next();
                pf.setVinNo(obj[0] == null ? "-" : obj[0].toString().trim());
                pf.setEngineNumber(obj[1] == null ? "-" : obj[1].toString().trim());
                pf.setModelFamily(obj[2] == null ? "-" : obj[2].toString().trim());
                pf.setModelCode(obj[3] == null ? "-" : obj[3].toString().trim());
                pf.setModelFamilyDesc(obj[4] == null ? "-" : obj[4].toString().trim());
                pf.setModelCodeDesc(obj[5] == null ? "-" : obj[5].toString().trim());
                pf.setPdiNo(obj[6] == null ? "-" : obj[6].toString().trim());
                pf.setInvoiceDate(obj[7] == null ? "" : obj[7].toString().trim().substring(0, obj[7].toString().trim().indexOf(" ")));
                pf.setInvoiceDate(pf.getInvoiceDate().equals("") ? "-" : sdf.format(df.parse(pf.getInvoiceDate())));
                pf.setJobCardDate(obj[8] == null ? "" : obj[8].toString().trim().substring(0, obj[8].toString().trim().indexOf(" ")));
                pf.setJobCardDate(pf.getJobCardDate().equals("") ? "-" : sdf.format(df.parse(pf.getJobCardDate())));
                pf.setPdiDate(obj[9] == null ? "" : obj[9].toString().trim().substring(0, obj[9].toString().trim().indexOf(" ")));
                pf.setPdiDate(pf.getPdiDate().equals("") ? "-" : sdf.format(df.parse(pf.getPdiDate())));
                pf.setInvoiceNo(obj[10] == null ? "-" : obj[10].toString().trim());
                pf.setDealerName(obj[11] == null ? "-" : obj[11].toString().trim());
                pf.setDealerCode(obj[12] == null ? "-" : obj[12].toString().trim());
                pf.setRemark(obj[13] == null ? "-" : obj[13].toString().trim());
                pf.setLocationName(obj[14] == null ? "-" : obj[14].toString().trim());
                pf.setRepname(obj[16] == null ? "-" : obj[16].toString().trim());

//               Collection<PdiDetails> pdc = vd.getPdiDetailsCollection();
//                if(pdc!=null && pdc.iterator().hasNext()){
//                PdiDetails pdi = (PdiDetails) pdc.iterator().next();
//                }
            }
            Query query1 = hrbsession.createQuery("select distinct pd.pDINo from PdiDetails pd ,PdiChecklist pc where pd.pDINo=pc.pdiChecklistPK.pDINo and pc.okStatus='NOT OK' and pd.pDINo=:pdiNo");
            query1.setParameter("pdiNo", pf.getPdiNo());
            List l = query1.list();
            if (!l.isEmpty()) {
                String chk = MethodUtility.check_in_Db_HQL(pf.getVinId(), "Jobcarddetails", "vinid", " and m.jobTypeId=1", hrbsession);
                if (chk.equalsIgnoreCase("notexist")) {
                    pf.setStatus("true");
                }
            }

            String jobCardDetailQuery = "Select a.jobCardNo from Jobcarddetails a where a.jobTypeId='1' and a.vinno=:vinNo and a.dealerCode=:dealerCode";
            Query jobCardQuery = hrbsession.createQuery(jobCardDetailQuery);
            jobCardQuery.setParameter("vinNo", pf.getVinNo());
            jobCardQuery.setParameter("dealerCode", pf.getDealerCode());
            //jobCardQuery.setParameter("JobTypeId", "1");
            Iterator it = jobCardQuery.list().iterator();
            pf.setCreateJobCard(false);
            while (it.hasNext()) {
                pf.setCreateJobCard(true);
                Object jobCardNo = (Object) it.next();
                if (jobCardNo != null) {
                    pf.setJobCardNo(jobCardNo.toString());
                }

            }
            pf.setJobType("1");

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
        return pf;
    }

    public LinkedList<ContentFormBean> getSingleINSDatatractorlist(pdiForm pf, String pdino) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        LinkedList<ContentFormBean> travelcardPartList = new LinkedList();
        try {

            Query query = hrbsession.createQuery(" select td.id.pDINo,td.id.partName,td.partVendorName,td.partSerialNo  from Pditravelcarddetails td where td.id.pDINo=:pDINo order by td.id.partName");
            query.setParameter("pDINo", pdino);
            Iterator itr = query.list().iterator();

            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                ContentFormBean cf = new ContentFormBean();
                cf.setContentDesc(obj[1].toString().trim()); //part name
                //System.out.println("...1..." + obj[1].toString().trim());
                cf.setSerialno(obj[3].toString().trim());
                cf.setObservation(obj[2].toString().trim());
                //System.out.println("...3..." + obj[2].toString().trim());
                travelcardPartList.add(cf);
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
        return travelcardPartList;
    }

    public void getView_FormContent(pdiForm pdiForm) {

        ContentFormBean cf = null;
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        String subContentId = null;
        String formName = pdiForm.getJobType();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<SubContentFormBean> subContentFormList = new ArrayList();

        SubContentFormBean dataBean = null;//new SubContentFormBean();
        ArrayList<SubContentFormBean> tempList = null;
        try {
            // stmt = conn.createStatement();
            getForm_id(pdiForm, formName);

            String hql = "select distinct pc.id.contentId,cm.contentDesc,pc.id.subContentId,scm.subContentDesc,pc.okStatus, "
                    + " pc.actionTaken,pc.remarks from PdiChecklist pc ,ItlContentMaster cm ,ItlSubContentMaster scm ,PdiDetails pd where "
                    + " cm.contentId = pc.id.contentId and"
                    + " scm.subContentId = pc.id.subContentId and"
                    + "  pd.pDINo=pc.id.pDINo and"
                    + " pd.pDINo='" + pdiForm.getPdiNo() + "'";// and pd.dealercode='"+pdiForm.getDealerCode()+"'";

//GetFormContent4ViewPDI

            Query query = hrbsession.createQuery(hql);

            List list = query.list();

            Iterator itr = list.iterator();

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

            pdiForm.setDataMap(treeMap);

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


    }

    /**
     * PDI List 4 View created on 19/09/14 by Megha
     */
    public ArrayList<pdiForm> getPDIList4View(String searchvalue, pdiForm pdiForm, String user, Vector userFunctionalities, String flag) throws SQLException //String dealercode,
    {

        String hql = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        ArrayList<pdiForm> dataList = null;
        String Subsql = "", statusSubQur = "", dateSubQur = "";
        String SearchQuery = searchvalue == null ? "" : searchvalue;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            dataList = new ArrayList<pdiForm>();
            Query query = null;
            if (!SearchQuery.equals("")) {
                Subsql = " and  v.vinNo like '%" + SearchQuery + "%' ";
            }
            if (pdiForm.getStatus().equalsIgnoreCase("ok")) {
                statusSubQur = " and p.pDINo not in ( select distinct pd.pDINo from PdiDetails pd ,PdiChecklist pc where pd.pDINo=pc.pdiChecklistPK.pDINo and pc.okStatus='NOT OK') ";
            } else if (pdiForm.getStatus().equalsIgnoreCase("Atleast One NotOk")) {
                statusSubQur = " and p.pDINo in ( select distinct pd.pDINo from PdiDetails pd ,PdiChecklist pc where pd.pDINo=pc.pdiChecklistPK.pDINo and pc.okStatus='NOT OK') ";
            }
            //if(!("".equals(pdiForm.getRange())) && pdiForm.getRange().equals("1")){
            if ("1".equals(pdiForm.getRange())) {
                dateSubQur = " and ( p.pDIDate between isnull(?,p.pDIDate) and isnull(?,p.pDIDate) )";
            }

            String query_string = "select v.vinNo,v.modelFamily,v.modelCode,u.dealerName,u.dealerCode,u.location,u.locationCode,p.pDINo,p.pDIDate,p.ref_pdi_no"
                    + " from PdiDetails p,Vehicledetails v, Dealervslocationcode u where "
                    + " v.vinNo=p.vinNo and v.dealerCode=p.dealercode and p.dealercode=u.dealerCode and v.pdiStatus='Y'   ";  //and (p.pDIDate between isnull(?,p.pDIDate) and isnull(?,p.pDIDate)

                query = hrbsession.createQuery(query_string + Subsql + statusSubQur + dateSubQur + " and :dealerList LIKE ('%'+p.dealercode+'%') order by p.pDIDate desc");
                 if (pdiForm.getDealerCode() != null && !pdiForm.getDealerCode().equals("") && !pdiForm.getDealerCode().equalsIgnoreCase("ALL")) {
                    dealerList.add(pdiForm.getDealerCode());
                } else {
                    dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(hrbsession,user);
                }
                query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
                //query.setParameterList("dealerList", dealerList);


            if ("1".equals(pdiForm.getRange())) {
                query.setString(0, pdiForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(pdiForm.getFromdate())));
                query.setString(1, pdiForm.getToDate().equals("") == true ? null : df.format(sdf.parse(pdiForm.getToDate())));
            }

            List<List> list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    pdiForm = new pdiForm();
                    pdiForm.setVinNo(obj[0] == null ? "" : obj[0].toString().trim());
                    pdiForm.setModelFamily(obj[1] == null ? "" : obj[1].toString().trim());
                    pdiForm.setModelCode(obj[2] == null ? "" : obj[2].toString().trim());
                    pdiForm.setDealerName(obj[3] == null ? "" : obj[3].toString().trim());
                    pdiForm.setDealerCode(obj[4] == null ? "" : obj[4].toString().trim());
                    pdiForm.setLocationName(obj[5] == null ? "" : obj[5].toString().trim());
                    pdiForm.setDealerLocation(obj[6] == null ? "" : obj[6].toString().trim());
                    pdiForm.setPdiNo(obj[7] == null ? "" : obj[7].toString().trim());
                    pdiForm.setPdiDate(obj[8] == null ? "" : obj[8].toString().trim().substring(0, obj[8].toString().trim().indexOf(" ")));
                    pdiForm.setPdiDate(pdiForm.getPdiDate().equals("") ? "" : sdf.format(df.parse(pdiForm.getPdiDate())));
                    pdiForm.setRefPDIno(obj[9] == null ? "" : obj[9].toString().trim());
                    dataList.add(pdiForm);

                }
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

    /**
     * comparing to map class for sorting
     */
    private HashMap<String, ArrayList<String>> sortByComparatorString(HashMap<String, ArrayList<String>> unsortMap, final boolean order) {

        List<Map.Entry<String, ArrayList<String>>> list = new LinkedList<Map.Entry<String, ArrayList<String>>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<String>>>() {

            public int compare(Map.Entry<String, ArrayList<String>> s1,
                    Map.Entry<String, ArrayList<String>> s2) {
                if (order) {
                    return s1.getKey().compareTo(s2.getKey());
                } else {
                    return s2.getKey().compareTo(s1.getKey());

                }
            }
        });

        HashMap<String, ArrayList<String>> sortedMap = new LinkedHashMap<String, ArrayList<String>>();
        for (Map.Entry<String, ArrayList<String>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public String createJobCard(pdiForm pf, String userid, String dealercode) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String jobcardNo = "", chk = "";
        try {
            hrbsession.beginTransaction();
            Vehicledetails vd = (Vehicledetails) hrbsession.load(Vehicledetails.class, pf.getVinId());
            jobcardNo = new MethodUtility().getNumber(hrbsession, "Jobcarddetails", pf.getDealerCode(), "J");
            chk = MethodUtility.check_in_Db_HQL(jobcardNo, "Jobcarddetails", "jobCardNo", " ", hrbsession);  //and m.jobTypeId=1
            if (chk.equals("notexist")) {
                Jobcarddetails createJC = new Jobcarddetails();
                createJC.setJobCardNo(jobcardNo);
                createJC.setProductCatId(1);
                createJC.setJobCardDate(new Date());
                createJC.setJobCardPriority("Low");
                createJC.setEventId(0);             //NA
                createJC.setJobTypeId(1);           //PDI
                createJC.setHMRWorking('Y');
                createJC.setHmr(Long.parseLong("" + 2));
                createJC.setServiceTypeId(3);
                createJC.setLocationId(1);          //DealerShip
                createJC.setNextService("YES");
                createJC.setJobCardDate(sdf.parse(pf.getPdiDate()));  //Tractor in Date (As PDI date)
                createJC.setDealerJCNo("-");
                createJC.setVinno(pf.getVinNo());
                createJC.setVinid(pf.getVinId());
                createJC.setEngineno(vd.getEngineNo());
                createJC.setVinDetailsType(vd.getVinDetailsType());
                createJC.setStatus("OPEN");
                createJC.setDealerCode(dealercode);
                createJC.setIsVinValidated("YES");
                createJC.setCreatedBy(userid);
                createJC.setWarrantyStatus("Y");
                createJC.setWtyClaimStatus("NOT REQUIRED");
                createJC.setCreatedOn(new Date());
                createJC.setTmsRefNo(jobcardNo);
                hrbsession.save(createJC);
                hrbsession.getTransaction().commit();
                result = "Job Card Created";
            } else {
                result = "job Card No Already exist.";
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
        return result;
    }

    public String getDealerName(String dealerCode) throws SQLException {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        String dealerName = "ALL";

        try {
            if (!dealerCode.equalsIgnoreCase("ALL")) {
                Query query = hrbsession.createQuery("SELECT dealerName FROM Dealervslocationcode WHERE dealerCode = :dealerCode");
                query.setParameter("dealerCode", dealerCode);

                dealerName = query.list().get(0).toString();
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
        return dealerName;
    }

    public List<String[]> getDealerListOnFunc(pdiForm pf, String user_id) {

        String[] dealerList = null;
        List<String[]> list=new ArrayList<String[]>();
        try {
            conn = new dbConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from FN_GetDealersDetailsUnderUser(?)");
            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            dealerList = new String[rs.getRow()];            
            while (rs.next()) {
                dealerList = new String[3];
                dealerList[0] = rs.getString(1);
                dealerList[1] = rs.getString(2);
                dealerList[2] = rs.getString(3);
                list.add(dealerList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

//    public List<String> getDealerCodeUnderUser(String user_id) {
//
//        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
//        List<String> dealerList = null;
//        try {
//            dealerList = hrbsession.createSQLQuery("Select * from FN_GetDealersUnderUser(?)").setString(0, user_id).list();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                if (hrbsession != null) {
//                    hrbsession.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return dealerList;
//    }
//
//    public List<String> getDealersDetailsUnderUser(String user_id) {
//
//        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
//        List<String> dealerList = null;
//        try {
//            dealerList = hrbsession.createSQLQuery("Select * from FN_GetDealersDetailsUnderUser(?)").setString(0, user_id).list();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                if (hrbsession != null) {
//                    hrbsession.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return dealerList;
//    }
}
