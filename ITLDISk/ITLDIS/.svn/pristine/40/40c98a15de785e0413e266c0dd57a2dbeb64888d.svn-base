/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.DBQueryConstant;
import com.common.MethodUtility;

import HibernateMapping.Dealervslocationcode;
import HibernateMapping.InsChecklist;
import HibernateMapping.InsChecklistMaster;
import HibernateMapping.InsChecklistPK;
import HibernateMapping.InsTravelcardDetails;
import HibernateMapping.InsTravelcardDetailsPK;
import HibernateMapping.Insenquiries;
import HibernateMapping.InsenquiriesPK;
import HibernateMapping.InstallationDetails;
import HibernateMapping.MSWDmechanicmaster;
import HibernateMapping.TmsChassisvsinsmaster;
import HibernateMapping.Vehicledetails;
import HibernateUtil.HibernateUtil;
import beans.ContentFormBean;
import beans.SubContentFormBean;
import beans.serviceForm;
import dbConnection.dbConnection;

/**
 *
 * @author megha.pandya
 */
public class installDAO implements DBQueryConstant
{

    Connection conn = null;
    String dbPATH = new dbConnection().dbPathAuth;
    int rowsUpdate = 0;

    public installDAO(Connection conn)
    {
        this.conn = conn;
    }

    public installDAO()
    {
    }

    /**
     * getting vin no List for installation created on 26/05/14 by Megha
     */
    public LinkedList<serviceForm> GetVinNoList(String SearchValue, String dealercode, Vector userFunctionalities, String user_id,serviceForm serviceForm)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedList<serviceForm> chassisList = new LinkedList();
        String Subsql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String SearchQuery = SearchValue == null ? "" : SearchValue;
        ArrayList<String> dealerList=new ArrayList<String>();
        try {
            if (!SearchQuery.equals("")) {
                Subsql = " and  v.vinNo like '%" + SearchQuery + "%' ";
            }
            String hql= "select v.vinNo,v.modelFamily,v.modelCode,v.customerName,v.mobilePh,u.dealerName,"
                    + "u.dealerCode,u.location,u.locationCode,v.deliveryDate from Vehicledetails v, "
                    + "Dealervslocationcode u where v.dealerCode=u.dealerCode and v.pdiStatus='Y' "
                    + "and v.insStatus='N'and v.deliveryDate IS NOT NULL ";

            query = hrbsession.createQuery(hql + " and :dealerList LIKE ('%'+v.dealerCode+'%') " + Subsql);

            if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(serviceForm.getDealercode());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(hrbsession,user_id);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

    /*        if (userFunctionalities.contains("101"))
            {
                query = hrbsession.createQuery("select v.vinNo,v.modelFamily,v.modelCode,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,u.locationCode,v.deliveryDate"
                        + " from Vehicledetails v, Dealervslocationcode u where "
                        + " v.dealerCode=u.dealerCode and v.pdiStatus='Y' and v.insStatus='N' and v.deliveryDate IS NOT NULL and v.dealerCode=:dealerCode " + Subsql);
                query.setParameter("dealerCode", dealercode);
            } else if (userFunctionalities.contains("102"))
            {
                ArrayList<String> dealerList=new ArrayList<String>();
                query = hrbsession.createQuery("select v.vinNo,v.modelFamily,v.modelCode,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,u.locationCode,v.deliveryDate"
                        + " from Vehicledetails v, Dealervslocationcode u where "
                        + " v.dealerCode=u.dealerCode and v.pdiStatus='Y' and v.insStatus='N' and v.deliveryDate IS NOT NULL and v.dealerCode IN(:dealerList) " + Subsql);
                //ArrayList<String> dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user_id + "'");
                //query.setParameterList("dealerList", dealerList);
               //Avinash Pandey 17-11-2015
                if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
                    dealerList.add(serviceForm.getDealercode());  //dealercode
                } else {
                    dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user_id + "'");
                }
                query.setParameterList("dealerList", dealerList);

            } else if (userFunctionalities.contains("103")) {
                if(serviceForm.getDealercode()!=null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")){
                query = hrbsession.createQuery("select v.vinNo,v.modelFamily,v.modelCode,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,u.locationCode,v.deliveryDate"
                        + " from Vehicledetails v, Dealervslocationcode u where  v.deliveryDate IS NOT NULL and"
                        + " v.dealerCode=u.dealerCode and v.pdiStatus='Y' and v.insStatus='N' and v.dealerCode=:dealerCode " + Subsql);
                query.setParameter("dealerCode",serviceForm.getDealercode());  //dealercode
                }else{
                    query = hrbsession.createQuery("select v.vinNo,v.modelFamily,v.modelCode,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,u.locationCode,v.deliveryDate"
                        + " from Vehicledetails v, Dealervslocationcode u where  v.deliveryDate IS NOT NULL and"
                        + " v.dealerCode=u.dealerCode and v.pdiStatus='Y' and v.insStatus='N' " + Subsql);
                }
            }    
     */
            itr = query.list().iterator();
            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                serviceForm sf = new serviceForm();
                sf.setVinNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                sf.setModelFamily(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                sf.setModelCode(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                sf.setCustomerName(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                sf.setMobilePhone(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                sf.setDealerName(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                sf.setDealercode(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                sf.setLocationName(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                sf.setDeliveryDate(enqobj[9] == null ? "" : sdf.format(enqobj[9]));

                chassisList.add(sf);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return chassisList;
    }

    /**
     * getting vin no details for installation created on 26/05/14 by Megha
     */
    public String GetVinNoDetails(serviceForm sf)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try
        {

            query = hrbsession.createQuery(GetVinNoDetail4INS + sf.getVinNo() + "'");
            itr = query.list().iterator();
            if (itr.hasNext())
            {
                /*
                select ti.engineNo,ti.dealerCode,ti.regNo,ti.DeliveryDate,ti.customerName" +
                " from Vehicledetails ti where  ti.pDIStatus='Y' and ti.iNSStatus='N' and ti.vinno = '"
                 */
                Vehicledetails vd = (Vehicledetails) itr.next();
                sf.setSaleDate(vd.getDealerInvoiceDate() == null ? "" : sdf.format(vd.getDealerInvoiceDate()));
                sf.setEngineNumber(vd.getEngineNo() == null ? "" : vd.getEngineNo().trim());
                sf.setDealerJobCardNo(vd.getDealerCode() == null ? "" : vd.getDealerCode().trim());
                sf.setRegistrationNo(vd.getRegNo() == null ? "" : vd.getRegNo().trim());
                sf.setDealerinvoiceno(vd.getDealerInvoiceNo() == null ? "" : vd.getDealerInvoiceNo().trim());
                sf.setModelFamily(vd.getModelFamily() == null ? "" : vd.getModelFamily().trim());//
                sf.setModelCode(vd.getModelCode() == null ? "" : vd.getModelCode().trim());//
                sf.setCustomerName(vd.getCustomerName() == null ? "" : vd.getCustomerName());//
                sf.setSizeoflandhold(vd.getSizeLandHolding() == null ? "" : vd.getSizeLandHolding());
                sf.setMaincrops(vd.getMainCrops() == null ? "" : vd.getMainCrops());
                sf.setOccupation(vd.getOccupation() == null ? "" : vd.getOccupation());
                sf.setMobilePhone(vd.getMobilePh() == null ? "" : Long.toString(vd.getMobilePh()));//
                sf.setState(vd.getState() == null ? "" : vd.getState());
                sf.setPinCode(vd.getPincode() == null ? "" : Long.toString(vd.getPincode()));
                sf.setDistrict(vd.getDistrict() == null ? "" : vd.getDistrict());
                sf.setTehsil(vd.getTehsil() == null ? "" : vd.getTehsil());
                sf.setVillage(vd.getVillageName() == null ? "" : vd.getVillageName());
                sf.setDeliveryDate(vd.getDeliveryDate() == null ? "" : sdf.format(vd.getDeliveryDate()));


                sf.setVinid(vd.getVinid());

            } else
            {
                result = "";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();

                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * getting Customer details for installation based on vin no created on 27/05/14 by Megha
     */
    public serviceForm GetCustomerDetails(serviceForm servForm)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        try
        {

            query = hrbsession.createQuery(GetInsCustInfo + servForm.getVinNo() + "'");
            itr = query.list().iterator();
            if (itr.hasNext())
            {
                TmsChassisvsinsmaster tm = (TmsChassisvsinsmaster) itr.next();
                servForm.setCustomerName(tm.getCustomerName() == null ? "" : tm.getCustomerName().trim());
                servForm.setVillage(tm.getVillageName() == null ? "" : tm.getVillageName().trim());
                //servForm.setTaluka(tm.gett==null?"":rs.getString(3).trim());
                servForm.setTehsil(tm.getTehsil() == null ? "" : tm.getTehsil().trim());
                servForm.setDistrict(tm.getDistrict() == null ? "" : tm.getDistrict().trim());
                servForm.setState(tm.getState() == null ? "" : tm.getState().trim());
                servForm.setCountry(tm.getCountry() == null ? "" : tm.getCountry().trim());
                servForm.setPinCode(tm.getPincode() == null ? "" : Long.toString(tm.getPincode()));
                servForm.setMobilePhone(tm.getMobilePh() == null ? "" : Long.toString(tm.getMobilePh()));
                servForm.setLandline(tm.getLandlineNo() == null ? "" : tm.getLandlineNo().trim());
                servForm.setEmailId(tm.getEmailId() == null ? "" : tm.getEmailId().trim());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();

                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return servForm;
    }

    /**
     * Generate Installation id created on 27/05/14 by megha
     * install_id need to change query for it
     */
    public String GenerateInsId(String dealercode) throws SQLException
    {
        String location = "";
        String installation_Number = "";
        long install_id = 0;
        int month = 0;
        String mon = "";
        String yr = "";
       int year = 0;
        String branchcode = "";

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try
        {
        Query query = null;
        Iterator itr = null;
        Dealervslocationcode dc = null;
        query = hrbsession.createQuery(GetLocationCode);

        query.setString(0, dealercode);

        itr = query.list().iterator();

        if (itr.hasNext())
        {

            dc = (Dealervslocationcode) itr.next();
            location = dc.getLocationCode();
//            branchcode = dc.getBranchCode();

        } else
        {

            return "notexist";
        }


       // cal = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;

        if (month <= 9)
        {
            mon = "0" + month;
        }
        yr = Integer.toString(year).substring(0, Integer.toString(year).length());

        query = hrbsession.createQuery(GetMaxInsId + dealercode + "'");
        itr = query.list().iterator();
        //rs = pmst.executeQuery();
        if (itr.hasNext())
        {
            install_id = (Long) itr.next();
//            System.out.println(install_id+"ins_id:"+ rs.getLong(1));
        } else
        {
            install_id = 0;
        }
        install_id = install_id + 1;


//        if (install_id > 999 && install_id <= 9999) {
//
//            id = Long.toString(install_id);
//
//
//        }
//
//        if (install_id > 99 && install_id <= 999) {
//
//            id = "0" + Long.toString(install_id);
//
//        }
//
//        if (install_id > 9 && install_id <= 99) {
//
//            id = "00" + Long.toString(install_id);
//
//        }
//
//        if (install_id > 0 && install_id <= 9) {
//
//            id = "000" + Long.toString(install_id);
//
//        }

        installation_Number = "INS/" + location + "/" + dealercode.toUpperCase() + "/" + branchcode + "/" + mon + "/" + yr + "/" + install_id + "@" + install_id;

        }
        catch(Exception e)
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
                    hrbsession=null;
                }


            } catch (Exception e)
            {
               e.printStackTrace();

            }
        }
        return installation_Number;
    }

    /**
     * Get content List for installation created on 27/05/14 by Megha
     */
    public void getContentList(serviceForm serviceForm, int Ins_Id)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null;
        Iterator itr = null;
        Iterator itr1 = null;

        ContentFormBean cf = null;
        LinkedList<ContentFormBean> ContentList = new LinkedList();
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;

        try
        {
            //  stmt = conn.createStatement();

            query = hrbsession.createQuery(CheckRowsInInsChecklist + Ins_Id + "'");
            itr = query.list().iterator();

            if (itr.hasNext())
            {
//                getView_FormContent(serviceForm);
            } else
            {
                query1 = hrbsession.createQuery(GetContentList4Ins);
                itr1 = query1.list().iterator();

                while (itr1.hasNext())
                {
                    InsChecklistMaster im = (InsChecklistMaster) itr1.next();
                    contentId = im.getContentid();
                    cf = new ContentFormBean();
                    cf.setContentId(contentId);
                    cf.setContentDesc(im.getContentdesc());
                    ContentList.add(cf);
                }
                serviceForm.setContentList(ContentList);
            }

        } catch (Exception ae)
        {
            ae.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {                   
                    hrbsession.close();
                }


            } catch (Exception e)
            {
                e.printStackTrace();               
            }
        }
    }

    /**
     *  Insert for standard check 4 install created on 27/05/14 by Megha
     */
    public boolean addStandardChecksData4Ins(Session hrbsession, LinkedList<ContentFormBean> COntentList, String insno) throws SQLException
    {

        // Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query  query1 = null;
        InsChecklist icl = null;
    //    String result = "FAILUER@@Standard Check not Added";
        boolean sflag = false;
        try
        {
         //   dataList = new ArrayList<String>();

            InsChecklistPK inpk = new InsChecklistPK();

            query1 = hrbsession.createQuery("delete from InsChecklist ic where ic.id.iNSNo=?");
            query1.setString(0, insno);
            query1.executeUpdate();

            for (int i = 0; i < COntentList.size(); i++)
            {
                //InsChecklistPK inpk=(InsChecklistPK)hrbsession.load(InsChecklistPK.class, insno);
                inpk = new InsChecklistPK();
                inpk.setINSNo(insno);
                inpk.setContentid(COntentList.get(i).getContentId());
                icl = new InsChecklist();
                icl.setInsChecklistPK(inpk);
                icl.setCheckedStatus(COntentList.get(i).getStatus());

                hrbsession.save(icl);
            }
       //     result = "SUCCESS@@Standard Check has been Successfully Added";
            sflag = true;
//            }

        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return sflag;//result;
    }

    public String addInstallationDetails(serviceForm sf, String User_Id, LinkedList<ContentFormBean> COntentList, LinkedList<ContentFormBean> TravelCardList, LinkedList<serviceForm> EnquiriesList) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;//, query1 = null;
        Iterator itr = null;
       // Iterator itr1 = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String result = "";
        //int Ins_Id=0;
        String insno = "";

        boolean Iflag = false, Sflag = false, Uflag = false, Tflag = false, Eflag = false;
        try
        {

            query = hrbsession.createQuery("from InstallationDetails where vinNo=:vinNo and dealercode=:dealercode and deliveryDate=:deliveryDate");
            query.setString("vinNo", sf.getVinNo());
            query.setString("dealercode", sf.getDealercode());
            query.setDate("deliveryDate", sdf.parse(sf.getDeliveryDate()));
            itr = query.list().iterator();
            if (itr.hasNext())
            {
                result = "EXIST@@existfailure@@" + sf.getVinNo() + " ";
            } else
            {
                hrbsession.beginTransaction();
                InstallationDetails id = new InstallationDetails();
                MSWDmechanicmaster mm = null;

//                System.out.println("id:"+User_Id+"-"+ sf.getVinNo()+"-"+sf.getJobCardDate()+"-"+sf.getJobLocation()+"-"+sf.getCustomerName());
                String Ins_Number = new MethodUtility().getNumber(hrbsession, "InstallationDetails", sf.getDealercode(), "INS");//GenerateInsId(sf.getDealercode());

                String filename = "";

                String dest = "";
                FormFile jpg = null;
                File jpgfile = null;
                boolean isFileUploaded = false;
                sf.setDestinationpath("");

                if (sf.getUploadphoto() != null && !sf.getUploadphoto().equals(""))
                {


                    File dest_folder = null;
                    dest_folder = new File(sf.getRealpath() + "/DOCUMENTS/INSTALLATION/");
                    if (!dest_folder.exists())
                    {
                        dest_folder.mkdirs();
                    }
                    dest = sf.getRealpath() + "/DOCUMENTS/INSTALLATION/";
                    jpg = sf.getUploadphoto();
                    filename = jpg.getFileName();

                    if (jpg.getFileName() != null && !jpg.getFileName().equals(""))
                    {
                    if((jpg.getFileSize()/1024)>500){
                     result="FAILURE@@FILESIZE@@"+ sf.getVinNo() + " ";
                     return result;
                    }
                    }
                    if (jpg.getFileName() != null && !jpg.getFileName().equals(""))
                    {
                        int lastvalue = jpg.getFileName().lastIndexOf(".");
                        String fileext = jpg.getFileName().substring(lastvalue, jpg.getFileName().length());
                        jpgfile = new File(dest + "/" + filename);
                        if(fileext.equalsIgnoreCase(".JPEG")){
                            fileext = ".JPG";
                        }

                        filename = Ins_Number.replaceAll("/", "-") + (fileext.toLowerCase());

                        isFileUploaded = uploadjpg(filename, dest, jpg, sf.getRealpath());
                        sf.setDestinationpath(dest);
                    }
                }
                id.setINSNo(Ins_Number);
                id.setVinNo(sf.getVinNo());
                id.setInsDate(sdf.parse(sf.getInsDate()));//Ins_Date

                id.setBuyerName(sf.getCustomerName());//Representative/visitor name
                //id.setBuyerName(sf.getBuyerName()==""?null:sf.getBuyerName());
                id.setEngineNo(sf.getEngineNumber());
                id.setDealercode( sf.getDealercode());
                //id.setInvoiceNo(sf.getInvoiceno()==""?null:sf.getInvoiceno());
                id.setVisitDate(sf.getJobCardDate() == "" ? null : sdf.parse(sf.getJobCardDate()));//visit date
                id.setDeliveryDate(sf.getDeliveryDate() == "" ? null : sdf.parse(sf.getDeliveryDate()));
//                pstmt.setString(12, sf.getHmeRadio()==""?null:sf.getHmeRadio());
                id.setHmr(sf.getHmr() == "" ? 0 : Integer.parseInt(sf.getHmr()));
                id.setMajorApplications(sf.getMajorApplications());
                id.setAccessories(sf.getAccessories());

                id.setContactName(sf.getContactname());
                id.setContactNo(sf.getContactno());
                id.setRelationshipCustomer(sf.getRelationshipwithcust());
                
                if (sf.getFamilyMembers() != null && !sf.getFamilyMembers().equals(""))
                {
                    id.setFamilyMembers(Integer.parseInt(sf.getFamilyMembers()));
                }
                else
                    id.setFamilyMembers(0);

                id.setOthertractorDetail(sf.getOthertractorDetail());
                id.setPaymentMode(sf.getPaymentMode());
                id.setBankName(sf.getBankName());
//                pstmt.setDouble(20, sf.getPayeeMobilePhone()==""?0:Double.valueOf(sf.getPayeeMobilePhone()));
//                pstmt.setInt(21, sf.getPayeePinCode()==""?0:Integer.parseInt(sf.getPayeePinCode()));sf.getBuyerPrice()==""?0:Double.valueOf(sf.getBuyerPrice())

                if (sf.getRepName() != null && !sf.getRepName().equals(""))
                {
                    mm = (MSWDmechanicmaster) hrbsession.load(MSWDmechanicmaster.class, sf.getRepName());
                    id.setMechanicDealerKey(mm);
                }
                id.setCreatedBy(User_Id);
                id.setIsServerSync('N');
                id.setCreatedOn(new java.util.Date());
                id.setRemarks(sf.getRemarks());
                id.setIsTMSSync('N');
             //   System.out.println("sf.getDestinationpath()" + sf.getDestinationpath());

                if (sf.getDestinationpath() != null || !sf.getDestinationpath().equals(""))
                {
                    id.setPhotographFileName(filename);
                }
                hrbsession.save(id);
                //rs = pstmt.getGeneratedKeys();
                insno = id.getINSNo();
//                   System.out.println(rs.getString(1)+"--"+sf.getVinNo()+"-"+ Ins_Id+"--");

                Iflag = true;
                Sflag = addStandardChecksData4Ins(hrbsession, COntentList, insno);
                Tflag = addTravelCardData4Ins(hrbsession, sf.getVinNo(), insno, TravelCardList);
                Eflag = addInstallationEnquiry(hrbsession, sf.getVinNo(), insno, EnquiriesList);
                Uflag = updateInsStatus(hrbsession, sf);

                if (Iflag && Sflag && Uflag && Tflag && Eflag)
                {
                    hrbsession.getTransaction().commit();
                    result = "SUCCESS@@installationsuccess@@" + sf.getVinNo() + " ";
                } else
                {
                    result = "FAILURE@@installationfailure@@" + sf.getVinNo() + " ";
                    //RollBack4Ins(sf.getVinNo(),insno);
                }
            }
        } catch (Exception e)
        {
           hrbsession.getTransaction().rollback();
            result = "FAILURE@@installationfailure@@" + sf.getVinNo() + " ";
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {                   
                    hrbsession.close();
                }
            } catch (Exception e)
            {
                result = "FAILURE@@Installation has not been Added for Chassis No. " + sf.getVinNo() + " ";
                //RollBack4Ins(sf.getVinNo(),insno);
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *  Update for Ins Status created on 30/05/14 by Megha
     */
    public boolean updateInsStatus(Session hrbsession, serviceForm sf) throws SQLException
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");       
        boolean Uflag = false;
        try
        {

            Vehicledetails vd = (Vehicledetails) hrbsession.load(Vehicledetails.class, sf.getVinid());
            //query = conn.prepareStatement(Update_InsStatus);
            vd.setSerBookNo(sf.getServiceBookletNo());
            vd.setCustomerName(sf.getCustomerName());
            vd.setSizeLandHolding(sf.getSizeoflandhold());
            vd.setMainCrops(sf.getMaincrops());
            vd.setOccupation(sf.getOccupation());
            vd.setVillageName(sf.getVillage());
            vd.setDistrict(sf.getDistrict());
            vd.setTehsil(sf.getTehsil());
            vd.setLastUpdatedDate(new java.util.Date());
            if(sf.getPinCode()!=null && !sf.getPinCode().equals(""))
            vd.setPincode(Long.parseLong(sf.getPinCode()));
            else
            vd.setPincode(0l);
            vd.setState(sf.getState());
            vd.setDealerInvoiceNo(sf.getDealerinvoiceno());
            vd.setDealerInvoiceDate(sdf.parse(sf.getSaleDate()));
            if (sf.getMobilePhone() != null && !sf.getMobilePhone().equals(""))
            {
                vd.setMobilePh(Long.parseLong(sf.getMobilePhone()));
            }
            else
            {
            vd.setMobilePh(0l);
            }
            vd.setContactName(sf.getContactname());
            vd.setContactNo(sf.getContactno());
            vd.setRelationshipCustomer(sf.getRelationshipwithcust());
            vd.setInsStatus('Y');
            vd.setIsServerSync('N');
           
            hrbsession.saveOrUpdate(vd);

            Uflag = true;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Uflag;
    }

    /**
     *  Delete/RollBack for Insdetails/Checklist/tmsStatus created on 30/05/14 by Megha
     */
    public boolean RollBack4Ins(String vinNo, String insno) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null, query3 = null;
        boolean Uflag = false;
        try
        {
            hrbsession.beginTransaction();

            //delete command for installation_details
            query = hrbsession.createQuery("delete from InstallationDetails where iNSNo=?");
            query.setString(0, insno);
            query.executeUpdate();
            // InsChecklistPK
            //delete command for ins_checklist
            query1 = hrbsession.createQuery("delete from InsChecklist ic where ic.id.iNSNo=?");
            query1.setString(0, insno);
            query1.executeUpdate();

            //delete command for ins_travelcard_details
            query2 = hrbsession.createQuery("delete from InsTravelcardDetails where INS_No=?");
            query2.setString(0, insno);
            query2.executeUpdate();

            //update command for tms_chassisvsinsmaster
            query3 = hrbsession.createQuery("UPDATE TmsChassisvsinsmaster  SET  iNSStatus='N' WHERE vinNo=?");
            query3.setString(0, vinNo);
            query3.executeUpdate();

            hrbsession.getTransaction().commit();

            Uflag = true;

        } catch (Exception e)
        {
            hrbsession.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {                  
                    hrbsession.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return Uflag;
    }

    /**
     *  Get travelcard/Tractor detail for Ins created on 30/05/14 by Megha
     */
    public LinkedList<ContentFormBean> GetTravelDetailIns(String vinNo) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
       LinkedList<ContentFormBean> travelcardPartList = null;
        try
        {
            query = hrbsession.createQuery("select pt.pditravelcarddetailsPK.partName,max(pt.partVendorName),max(pt.partSerialNo)"
                    + " from Pditravelcarddetails pt, PdiDetails pd "
                    + " where pt.pditravelcarddetailsPK.pDINo=pd.pDINo and pd.vinNo=:vinNo group by pt.pditravelcarddetailsPK.partName");
            query.setParameter("vinNo", vinNo);
            itr = query.list().iterator();
            travelcardPartList = new LinkedList<ContentFormBean>();
            while (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                ContentFormBean cf = new ContentFormBean();
//                Pditravelcarddetails tm = (Pditravelcarddetails) itr.next();
                cf.setContentDesc(obj[0] == null ? "" : obj[0].toString().trim());
                cf.setSerialno(obj[2] == null ? "" : obj[2].toString().trim());
                cf.setObservation(obj[1] == null ? "" : obj[1].toString().trim());
                travelcardPartList.add(cf);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {                   
                    hrbsession.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return travelcardPartList;
    }

    public LinkedList<serviceForm> GetEnquriesIns(String insno) throws SQLException
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LinkedList<serviceForm> enquiriesList = new LinkedList<serviceForm>();
        try
        {
            Query query = hrbsession.createQuery("  from Insenquiries  ts where ts.id.iNSNo=:insno");
            query.setParameter("insno", insno);
            Iterator itr = query.list().iterator();

            Insenquiries is = null;//TmsTravelcarddetails
            InsenquiriesPK tpk = null;//TmsTravelcarddetails

            while (itr.hasNext())
            {
                is = (Insenquiries) itr.next();
                serviceForm cf = new serviceForm();
                tpk = is.getInsenquiriesPK();
                cf.setCustName(tpk.getCustomerName());
                cf.setVillagename(is.getVillage());
                cf.setMobilePh(is.getContactNo());
                cf.setFatherName(is.getFatherName());
                cf.setRelationwithcustomer(is.getCustomerRelationship());
                enquiriesList.add(cf);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();

                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return enquiriesList;
    }

    /**
     *  Insert TravelCard/Tractor details for Ins created on 30/05/14 by Megha
     */
    public boolean addTravelCardData4Ins(Session hrbsession, String vinNo, String insno, LinkedList<ContentFormBean> TravelcardList) throws SQLException
    {
        boolean Tflag = false;
        try
        {
            //delete command for installation_details
            //          pstmt = conn.prepareStatement(Insert_travelCardData);

            InsTravelcardDetails itd = null;
            InsTravelcardDetailsPK itdpk = null;//iNSNo
//ins_travelcard_details(ins_id, vinNo, PartNo, PartVendorName, PartSerialNo)
            for (int i = 0; i < TravelcardList.size(); i++)
            {
              //  System.out.println("TravelcardList.size()" + TravelcardList.size());
              //  System.out.println("partno" + TravelcardList.get(i).getContentDesc());

                // itd.setVinNo(vinNo);
                itd = new InsTravelcardDetails();
                itdpk = new InsTravelcardDetailsPK();
                itdpk.setINSNo(insno);
                itdpk.setPartNo(TravelcardList.get(i).getContentDesc());
                itd.setInsTravelcardDetailsPK(itdpk);
                itd.setPartVendorName(TravelcardList.get(i).getObservation());
                itd.setPartSerialNo(TravelcardList.get(i).getStatus());
                // itd.setPartSerialNo((i+1)+"");
                hrbsession.save(itd);
            }
            Tflag = true;

        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return Tflag;
    }

    public boolean addInstallationEnquiry(Session hrbsession, String vinNo, String insno, LinkedList<serviceForm> EnquiryList) throws SQLException
    {
        boolean Eflag = false;
        try
        {
            Insenquiries itd = null;
            InsenquiriesPK itdpk = null;

            for (int i = 0; i < EnquiryList.size(); i++)
            {
               // System.out.println("TravelcardList.size()" + EnquiryList.size());
                //System.out.println("partno" + EnquiryList.get(i).getContentDesc());

                // itd.setVinNo(vinNo);
                itd = new Insenquiries();
                itdpk = new InsenquiriesPK();
                itdpk.setINSNo(insno);
                itdpk.setCustomerName(EnquiryList.get(i).getCustName());
                itd.setContactNo(EnquiryList.get(i).getMobilePh());
                itd.setCustomerRelationship(EnquiryList.get(i).getRelationwithcustomer());
                itd.setFatherName(EnquiryList.get(i).getFatherName());
                itd.setVillage(EnquiryList.get(i).getVillagename());
                itd.setInsenquiriesPK(itdpk);

                hrbsession.save(itd);
            }


            Eflag = true;

        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return Eflag;
    }

    /**
     * for collecting list from any table
     */
    public LinkedHashSet<LabelValueBean> getCommonLabelValue(String tableName, String fieldId, String fieldName, String filter, String whereCase)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null, query1 = null, query2 = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";

        if (whereCase.equals(""))
        {
            whereCase = "where isActive='Y'";
        }

        if (tableName.equals("eventmaster"))
        {

            whereCase = " where  MONTH(StartDate) = MONTH(CURRENT_DATE) and dealercode='" + whereCase + "'";
        }

        String hql = "select distinct " + fieldId + "," + fieldName + " FROM " + tableName + " " + whereCase + " order by " + filter + "";

//        System.out.println("query==" + query);
        try
        {
            LabelValueBean lv = null;
            query1 = hrbsession.createQuery(hql);
            itr = query1.list().iterator();
            while (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
//                if(tableName.equals("MSWDmechanicmaster"))
//                name = o[1].toString() == null ? "" : o[1].toString().trim()+" ("+o[2].toString().trim()+")";
//                else
                name = o[1].toString() == null ? "" : o[1].toString().trim();

                id = o[0].toString() == null ? "" : o[0].toString().trim();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        } catch (Exception ae)
        {
            ae.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean uploadjpg(String fileName, String filePath, FormFile uploadFile, String realPath)
    {
        boolean isUploaded = false;
        //   System.out.println("filePath inside function" + filePath);
        //   System.out.println("uploadFile" + uploadFile);
        if (!fileName.equals(""))
        {

            File currentToolDb = new File(filePath, fileName);

            try
            {
                FileOutputStream fileOutStream = new FileOutputStream(currentToolDb);
                fileOutStream.write(uploadFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
                isUploaded = true;
            } catch (Exception e)
            {
                e.printStackTrace();
                isUploaded = false;
            }
        }
        return isUploaded;
    }

    public LinkedList<serviceForm> GetInsNoList(String SearchValue, String dealercode, Vector userFunctionalities, String user_id, serviceForm serviceForm)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedList<serviceForm> chassisList = new LinkedList();
        String Subsql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String SearchQuery = SearchValue == null ? "" : SearchValue;
        ArrayList<String> dealerList=new ArrayList<String>();        
        try {
            if (!SearchQuery.equals("")) {
                Subsql = " and  id.vinNo like '%" + SearchQuery + "%' ";
            }
//            if (!serviceForm.getFromdate().equals("")) {
//                Subsql += " and id.insDate >= ? ";
//            }
//            if (!serviceForm.getTodate().equals("")) {
//                Subsql += " and id.insDate <= ? ";
//            }
            if ("1".equals(serviceForm.getRange())) {
                Subsql += " and id.insDate >= ? and id.insDate <= ?";
            }
            String hql = "select id.iNSNo ,id.insDate,v.vinNo,v.modelFamily,v.customerName,v.mobilePh,"
                    + "u.dealerName,u.dealerCode,u.location,v.deliveryDate from InstallationDetails id,"
                    + "Vehicledetails v,Dealervslocationcode u where id.dealercode=v.dealerCode "
                    + "and v.dealerCode=u.dealerCode and v.vinNo=id.vinNo and v.insStatus='Y' "
                    + Subsql + " and :dealerList LIKE ('%'+id.dealercode+'%')  order by id.insDate desc";

            query = hrbsession.createQuery(hql);
            if (serviceForm.getDealercode() != null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")) {
                dealerList.add(serviceForm.getDealercode());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(hrbsession,user_id);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

   /*         if (userFunctionalities.contains("101")){
                query = hrbsession.createQuery("select id.iNSNo ,id.insDate,v.vinNo,v.modelFamily,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,v.deliveryDate"
                        + " from InstallationDetails id,Vehicledetails v,Dealervslocationcode u where "
                        + " id.dealercode=v.dealerCode and v.dealerCode=u.dealerCode and v.vinNo=id.vinNo  "
                        + " and v.insStatus='Y' " + Subsql
                        + " and id.dealercode=:dealerCode  order by id.insDate desc");
                query.setParameter("dealerCode", dealercode);
            } else if (userFunctionalities.contains("102"))
            {
                ArrayList<String> dealerList=new ArrayList<String>();
                query = hrbsession.createQuery("select id.iNSNo ,id.insDate,v.vinNo,v.modelFamily,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,v.deliveryDate"
                        + " from InstallationDetails id,Vehicledetails v,Dealervslocationcode u where "
                        + "id.dealercode=v.dealerCode and v.dealerCode=u.dealerCode and v.vinNo=id.vinNo and v.insStatus='Y'  "
                        + Subsql + "  and v.dealerCode IN (:dealerList)  order by id.insDate desc");
             if(serviceForm.getDealercode()!=null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")){
                 dealerList.add(serviceForm.getDealercode());  //dealercode
             }else{
                 dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + user_id + "'");
             } query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103"))
            {
                if(serviceForm.getDealercode()!=null && !serviceForm.getDealercode().equals("") && !serviceForm.getDealercode().equalsIgnoreCase("ALL")){
                query = hrbsession.createQuery("select id.iNSNo ,id.insDate,v.vinNo,v.modelFamily,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,v.deliveryDate"
                        + " from InstallationDetails id,Vehicledetails v,Dealervslocationcode u where "
                        + "id.dealercode=v.dealerCode and v.dealerCode=u.dealerCode and v.vinNo=id.vinNo and v.insStatus='Y'  "
                        + Subsql + " and id.dealercode=:dealerCode order by id.insDate desc");
                query.setParameter("dealerCode",serviceForm.getDealercode());  //dealercode
                }else{
                    query = hrbsession.createQuery("select id.iNSNo ,id.insDate,v.vinNo,v.modelFamily,v.customerName,v.mobilePh,u.dealerName,u.dealerCode,u.location,v.deliveryDate"
                        + " from InstallationDetails id,Vehicledetails v,Dealervslocationcode u where "
                        + "id.dealercode=v.dealerCode and v.dealerCode=u.dealerCode and v.vinNo=id.vinNo and v.insStatus='Y'  "
                        + Subsql + " order by id.insDate desc");
                }
            }    
    */
//            int cnt = 0;
//            if (!serviceForm.getFromdate().equals(""))
//            {
//                query.setString(cnt, df.format(sdf.parse(serviceForm.getFromdate())));
//                cnt++;
//            }
//            if (!serviceForm.getTodate().equals(""))
//            {
//                query.setString(cnt, df.format(sdf.parse(serviceForm.getTodate())));
//            }
            if ("1".equals(serviceForm.getRange())) {
                query.setString(0, df.format(sdf.parse(serviceForm.getFromdate())));
                query.setString(1, df.format(sdf.parse(serviceForm.getTodate())));
            }
            if (query != null && query.list().size() > 0) {
                itr = query.list().iterator();
                while (itr.hasNext()) {

                    Object enqobj[] = (Object[]) itr.next();
                    serviceForm sf = new serviceForm();
                    sf.setInsNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                    sf.setInsDate(enqobj[1] == null ? "" : sdf.format(df.parse(enqobj[1].toString().trim())));
                    sf.setChassisNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                    sf.setModelFamily(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                    sf.setCustomerName(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                    sf.setMobilePhone(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                    sf.setDealerName(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                    sf.setDealercode(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                    sf.setLocationName(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                    sf.setDeliveryDate(enqobj[9] == null ? "" : sdf.format(enqobj[9]));

                    chassisList.add(sf);
                }
            }

        } catch (Exception e) {
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
        return chassisList;
    }

    public serviceForm getSingleINSData(serviceForm pf, String insno) throws SQLException
    {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        InstallationDetails insData = null;
        try
        {
            Query query = hrbsession.createQuery("select v.vinNo,v.modelFamily,v.modelFamilyDesc,v.modelCode,v.modelCodeDesc,v.DealerInvoiceNo,v.ItlInvoiceDate, "
                    + " v.serBookNo,v.regNo,v.customerName,v.mobilePh,v.villageName,v.tehsil,v.district,v.pincode,v.occupation,v.sizeLandHolding,"
                    + " v.mainCrops,v.state,id.visitDate,id.deliveryDate,id.engineNo,id.hmr,id.familyMembers,id.buyerName,"
                    + " id.remarks,id.mechanicDealerKey,id.bankName,id.paymentMode,id.othertractorDetail,id.iNSNo,id.insDate,id.accessories,"
                    + " id.majorApplications,u.dealerCode,u.dealerName,u.locationCode,u.location,v.DealerInvoiceDate,id.photographFileName,id.contactName,id.contactNo,id.relationshipCustomer ,(select ms.mechanicName from MSWDmechanicmaster ms where ms.mechanicDealerKey =id.mechanicDealerKey ) as mechanicName "
                    + " from InstallationDetails id,Vehicledetails v,Dealervslocationcode u "
                    + " where id.vinNo=v.vinNo and u.dealerCode=id.dealercode and v.insStatus='Y' and id.iNSNo=:insno");
            query.setParameter("insno", insno);
            Iterator itr = query.list().iterator();

            if (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();

                pf.setVinNo(obj[0] == null ||(obj[0].toString().equals("")) ? "-" : obj[0].toString().trim());
                pf.setModelFamily(obj[1] == null ||(obj[1].toString().equals("")) ? "-" : obj[1].toString().trim());
                pf.setModelFamilyDesc(obj[2] == null || (obj[2].toString().equals(""))? "-" : obj[2].toString().trim());
                pf.setModelCode(obj[3] == null ||(obj[3].toString().equals("")) ? "-" : obj[3].toString().trim());
                pf.setModelCodeDesc(obj[4] == null ||(obj[4].toString().equals("")) ? "-" : obj[4].toString().trim());
                pf.setDealerinvoiceno(obj[5] == null ||(obj[5].toString().equals("")) ? "-" : obj[5].toString().trim());
                pf.setInvoicedate(obj[6] == null ||(obj[6].toString().equals("")) ? "-" : obj[6].toString().trim());//
                pf.setServiceBookletNo(obj[7] == null ||(obj[7].toString().equals("")) ? "-" : obj[7].toString().trim());
                pf.setRegistrationNo(obj[8] == null ||(obj[8].toString().equals("")) ? "-" : obj[8].toString().trim());
                pf.setCustomerName(obj[9] == null ||(obj[9].toString().equals("")) ? "-" : obj[9].toString().trim());
                pf.setMobilePhone(obj[10] == null ||(obj[10].toString().equals("")) ? "-" : obj[10].toString().trim());
                pf.setVillage(obj[11] == null ||(obj[11].toString().equals("")) ? "-" : obj[11].toString().trim());
                pf.setTehsil(obj[12] == null ||(obj[12].toString().equals("")) ? "-" : obj[12].toString().trim());
                pf.setDistrict(obj[13] == null ||(obj[13].toString().equals("")) ? "-" : obj[13].toString().trim());
                pf.setPinCode(obj[14] == null ||(obj[14].toString().equals("")) ? "-" : obj[14].toString().trim());
                pf.setOccupation(obj[15] == null ||(obj[15].toString().equals("")) ? "-" : obj[15].toString().trim());
                pf.setSizeoflandhold(obj[16] == null ||(obj[16].toString().equals("")) ? "-" : obj[16].toString().trim());
                pf.setMaincrops(obj[17] == null ||(obj[17].toString().equals("")) ? "-" : obj[17].toString().trim());
                pf.setState(obj[18] == null ||(obj[18].toString().equals("")) ? "-" : obj[18].toString().trim());

                //pf.setJobCardDate(obj[19] == null ? "" : obj[19].toString().trim().substring(0, obj[19].toString().trim().indexOf(" ")));//
                //pf.setInsDate(pf.getJobCardDate().equals("") ? "" : sdf.format(df.parse(pf.getJobCardDate())));
                //pf.setDelieveryDate(obj[20] == null ? "" : obj[20].toString().trim().substring(0, obj[20].toString().trim().indexOf(" ")));//
                //pf.setInsDate(pf.getDelieveryDate().equals("") ? "" : sdf.format(df.parse(pf.getDelieveryDate())));
                pf.setVisitDate(obj[19] == null ||(obj[19].toString().equals("")) ? "-" : sdf.format(df.parse(obj[19].toString().trim())));
                pf.setDeliveryDate(obj[20] == null ||(obj[20].toString().equals("")) ? "-" : sdf.format(df.parse(obj[20].toString().trim())));


                //pf.setJobCardDate(obj[19] == null ? "" : obj[19].toString().trim());
                pf.setEngineNumber(obj[21] == null ||  (obj[21].toString().equals("")) ? "-" : obj[21].toString().trim());
                pf.setHmr(obj[22] == null || (obj[22].toString().equals("")) ? "-" : obj[22].toString().trim());
                pf.setFamilyMembers(obj[23] == null ||(obj[23].toString().equals("")) ? "-" : obj[23].toString().trim());
                pf.setBuyerName(obj[24] == null ||(obj[24].toString().equals("")) ? "-" : obj[24].toString().trim());
                
                pf.setRemarks(obj[25] == null ||(obj[25].toString().equals("")) ? "-" : obj[25].toString().trim());

                MSWDmechanicmaster msw = (MSWDmechanicmaster) obj[26];

                pf.setMechanicName(msw.getMechanicDealerKey());

                pf.setBankName(obj[27] == null ||(obj[27].toString().equals("")) ? "-" : obj[27].toString().trim());
                pf.setPaymentMode(obj[28] == null ||(obj[28].toString().equals("")) ? "-" : obj[28].toString().trim());
                pf.setOthertractorDetail(obj[29] == null ||(obj[29].toString().equals("")) ? "-" : obj[29].toString().trim());
                pf.setInsNo(obj[30] == null ||(obj[30].toString().equals("")) ? "-" : obj[30].toString().trim());
                //pf.setInsDate(obj[32] == null ? "" : obj[32].toString().trim());
                pf.setInsDate(obj[31] == null ||(obj[31].toString().equals("")) ? "-" : sdf.format(df.parse(obj[31].toString().trim())));
                pf.setAccessories(obj[32] == null ||(obj[32].toString().equals("")) ? "-" : obj[32].toString().trim());
                pf.setMajorApplications(obj[33] == null ||(obj[33].toString().equals("")) ? "-" : obj[33].toString().trim());
                pf.setDealercode(obj[34] == null ||(obj[34].toString().equals("")) ? "-" : obj[34].toString().trim());
                pf.setDealerName(obj[35] == null ||(obj[35].toString().equals("")) ? "-" : obj[35].toString().trim());
                //   pf.setLocationcode[37]
                pf.setLocationName(obj[37] == null ||(obj[37].toString().equals("")) ? "-" : obj[37].toString().trim());
                pf.setSaleDate(obj[38] == null ||(obj[38].toString().equals("")) ? "-" : sdf.format(df.parse(obj[38].toString().trim())));
                pf.setUploadins(obj[39] == null ? "" : obj[39].toString().trim());
              //  System.out.println("   upload ins is "+obj[39]);
                pf.setContactname(obj[40] == null ||(obj[40].toString().equals("")) ? "-" : obj[40].toString().trim());
                pf.setContactno(obj[41] == null ||(obj[41].toString().equals("")) ? "-" : obj[41].toString().trim());
                pf.setRelationshipwithcust(obj[42] == null ||(obj[42].toString().equals("")) ? "-" : obj[42].toString().trim());
                pf.setRepname(obj[43] == null ||(obj[43].toString().equals("")) ? "-" : obj[43].toString().trim());
            }

            //pf.setJobType("1")



        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();

                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return pf;
    }

    public LinkedList<ContentFormBean> getSingleINSDatatractorlist(serviceForm pf, String insno) throws SQLException
    {


        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LinkedList<ContentFormBean> travelcardPartList = new LinkedList();
        try
        {

            Query query = hrbsession.createQuery(" select td.id.iNSNo,td.id.partNo,td.partVendorName,td.partSerialNo  from InsTravelcardDetails td where td.id.iNSNo=:insno order by td.id.partNo");
            query.setParameter("insno", insno);
            Iterator itr = query.list().iterator();

            while (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                ContentFormBean cf = new ContentFormBean();
                cf.setContentDesc(obj[1].toString().trim()); //part name
             //   System.out.println("...1..." + obj[1].toString().trim());
                cf.setSerialno(obj[3].toString().trim());
                cf.setObservation(obj[2].toString().trim());
              //  System.out.println("...3..." + obj[2].toString().trim());
                travelcardPartList.add(cf);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();

                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return travelcardPartList;
    }

    public void getContentListview(serviceForm serviceForm)
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query1 = null;

        Iterator itr1 = null;

        ContentFormBean cf = null;
        LinkedList<ContentFormBean> ContentList = new LinkedList();
        Map<ContentFormBean, ArrayList<SubContentFormBean>> treeMap = new TreeMap();
        int contentId = 0;

        try
        {
            query1 = hrbsession.createQuery("select ic.id.contentid,ic.checkedStatus from InsChecklist ic where ic.id.iNSNo='" + serviceForm.getInsNo() + "' ");  //and ic.checkedStatus='Y'
            itr1 = query1.list().iterator();

            while (itr1.hasNext())
            {
                Object[] obj = (Object[]) itr1.next();
                InsChecklistMaster im = (InsChecklistMaster) hrbsession.load(InsChecklistMaster.class, Integer.parseInt(obj[0].toString()));
                contentId = im.getContentid();
                cf = new ContentFormBean();
                cf.setContentId(contentId);
                cf.setContentDesc(im.getContentdesc());
                cf.setStatus(obj[1].toString());
                ContentList.add(cf);
            }
            serviceForm.setContentList(ContentList);


        } catch (Exception ae)
        {
            ae.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {
                   
                    hrbsession.close();
                }
            } catch (Exception e)
            {
                try
                {
                    e.printStackTrace();
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String updateInsImage(serviceForm sf, String insNo, String realPath) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // String result = "fail";
        File dest_folder = null;
        String dest = "";
        String result="fail"; boolean isDeleted=false;
        String insImage = "";String extension1 = "";
        FormFile doc = null;
        try {

            session.beginTransaction();

            if (sf.getUploadphoto() != null) {
                insImage = sf.getUploadphoto().getFileName();
            }
            long siz=sf.getUploadphoto().getFileSize();
             //System.out.println(" size is byte >> "+siz);
             if(siz<=512000){

            if (insImage != null) {
                int i = insImage.lastIndexOf('.');
                if (i >= 0) { extension1 = insImage.substring(i + 1); } }
            if (!insImage.equals("")) {
                dest_folder = new File(realPath + "\\Documents\\Installation\\");
               if (!dest_folder.exists())
                    {
                        dest_folder.mkdirs();
                    }
//                if (dest_folder.exists()) {
//                    dest_folder.delete();  }
            }
            dest = realPath + "\\Documents\\Installation\\";
            doc = sf.getUploadphoto();

             String newImageName = insNo.replace("/", "-").concat(".").concat(extension1);

                 if(newImageName.indexOf(".jpeg")!=-1){
                    newImageName = (newImageName.substring(0, newImageName.indexOf(".jpeg"))+".jpg");
                 }else if(newImageName.indexOf(".JPEG")!=-1){
                    newImageName = (newImageName.substring(0, newImageName.indexOf(".JPEG"))+".jpg");
                 }else if(newImageName.indexOf(".JPG")!=-1){
                     newImageName = (newImageName.substring(0, newImageName.indexOf(".JPG"))+".jpg");
                 }

            String s1 = realPath + "/DOCUMENTS/INSTALLATION/" + newImageName;
            File f1 = new File(s1);
            if (f1.exists()) {
             //String delete_dest=dest+"newImageName";
              File file = new File(s1);
               isDeleted = file.delete();
            }
            else{
                isDeleted=true;
            }

                if (isDeleted) {
                    if (!insImage.equals("")) {
                        MethodUtility.uploadFile(newImageName, dest, doc, realPath);
                    }
                }

           InstallationDetails insdata=(InstallationDetails)session.get(InstallationDetails.class,insNo);
           insdata.setPhotographFileName(newImageName);
           session.saveOrUpdate(insdata);
           result="success";
            }
            
        }
           catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try {
                if(session!=null)
                {
                session.close();
                session=null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
