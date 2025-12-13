/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.json.JSONObject;

import com.common.DigitalSignatureUtil;
import com.common.FindTextCoordinates;
import com.common.MethodUtility;

import HibernateMapping.Jobcarddetails;
import HibernateMapping.SWwarrantypackingmaster;
import HibernateMapping.Warrantyclaim;
import HibernateMapping.Warrantyclaimdetails;
import HibernateUtil.HibernateUtil;
import beans.WarrantyForm;
import dbConnection.dbConnection;
import viewEcat.comEcat.ComUtils;
import viewEcat.comEcat.PageTemplate;
import java.io.FileInputStream;
import org.apache.http.entity.ContentType;


/**
 *
 * @author kundan.rastogi
 */
public class WarrantyDAO {
	serviceDAO obj = new serviceDAO();
	
	/*
    public ArrayList<WarrantyForm> getJobCardList(WarrantyForm warrantyForm, Vector userFunctionalities,String user_id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
     //   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");

        String hql = "";
        Integer cnt = Integer.parseInt(obj.getHesConstantValue(12));
        ArrayList<String> dealerList=  new ArrayList<String>();
        try {
            // if (userFunctionalities.contains("702")) {
//        	hql = "Select jc.jobCardNo,jc.payeeName,jc.vinno,jc.tmsRefNo,jc.jobCardDate,jc.vehicleOutDate "
//                    + "from Jobcarddetails jc where jc.wtyClaimStatus='PENDING' and isVinValidated='YES' and jc.iswarrantyreq='Y' and jc.status='BILLED' and jc.dealerCode='" + warrantyForm.getDealer_code() + "'  " +
//                    "and jc.vehicleOutDate >= getdate()-"+cnt+" order by jc.jobCardNo desc"; //-30
        	String dealerCode = warrantyForm.getDealer_code();
        	StringBuilder qry = new StringBuilder("Select jc.jobCardNo, jc.payeeName, jc.vinno, jc.tmsRefNo, jc.jobCardDate, jc.vehicleOutDate " +
        	                                        "from Jobcarddetails jc " +
        	                                        "where jc.wtyClaimStatus = 'PENDING' " +
        	                                        "and jc.isVinValidated = 'YES' " +
        	                                        "and jc.iswarrantyreq = 'Y' " +
        	                                        "and jc.status = 'BILLED'"+
        	                                        " and :dealerList LIKE ('%'+jc.dealerCode+'%') ");

        	/*
        	if (dealerCode != null && !dealerCode.isEmpty() && !dealerCode.equals("null")) {
        		qry.append("and jc.dealerCode = '").append(dealerCode).append("' ");
        	}
        	
        	
        	if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
                dealerList.add(warrantyForm.getDealer_code());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, user_id);
            }
        	  	
        	qry.append("and jc.vehicleOutDate >= getdate()-").append(cnt).append(" ");
        	qry.append("order by jc.jobCardNo desc");
           
        	String finalQuery = qry.toString();	
            Query query = session.createQuery(finalQuery);
            query.setParameter("dealerList", dealerList);
          
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm1 = new WarrantyForm();
                warrantyForm1.setJobCardNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm1.setPayeeName(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm1.setVinNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                warrantyForm1.setTmsRefNo(enqobj[3] == null ? "NA" : enqobj[3].toString().trim());
                String jobcardDateString = enqobj[4] == null ? "" : enqobj[4].toString().trim();
                if (!jobcardDateString.equals("")) {
                    Date wdate = inputFormat.parse(jobcardDateString);
                    String wd = outputFormat.format(wdate);
                    warrantyForm1.setJobCardDate(wd);
                }
                if (enqobj[5] != null) {
                	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                	String dateString = enqobj[5].toString().trim();
                	LocalDateTime repDate = LocalDateTime.parse(dateString, inputFormatter);

                    // Add 120 days to the parsed date
                    LocalDateTime newDate = repDate.plusDays(cnt);

                    // Format the new date into the specified format
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    String dateAfter = newDate.format(outputFormatter);

                	
                	
//                    Date repDate=inputFormat.parse(enqobj[5].toString().trim());
//                    Calendar cal = Calendar.getInstance();  
//                    cal.setTime(repDate);  
                  //  cal.add(Calendar.DAY_OF_MONTH, 30);  
//                    cal.add(Calendar.DAY_OF_MONTH, cnt);
//                    String dateAfter = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
                    //String dateAfter ="20230809";
                    
                    warrantyForm1.setReplacementDate(dateAfter);  //job card complition date (vehical out date )
                }
                dataList.add(warrantyForm1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }
    */
	
	public ArrayList<WarrantyForm> getJobCardList(WarrantyForm warrantyForm, Vector userFunctionalities, String user_id) throws SQLException {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
	    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");

	    String hql = "";
	    Integer cnt = Integer.parseInt(obj.getHesConstantValue(12));
	    ArrayList<String> dealerList = new ArrayList<String>();
	    
	    try {
	        // Set the dealerList based on the conditions
	        if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
	            dealerList.add(warrantyForm.getDealer_code());
	        } else {
	            dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, user_id);
	        }

	        StringBuilder qry = new StringBuilder("Select jc.jobCardNo, jc.payeeName, jc.vinno, jc.tmsRefNo, jc.jobCardDate, jc.vehicleOutDate " +
	                                             "from Jobcarddetails jc " +
	                                             "where jc.wtyClaimStatus = 'PENDING' " +
	                                             "and jc.isVinValidated = 'YES' " +
	                                             "and jc.iswarrantyreq = 'Y' " +
	                                             "and jc.status = 'BILLED' ");

	        // Use IN clause for dealerList
	        if (!dealerList.isEmpty()) {
	            qry.append("and jc.dealerCode IN (:dealerList) ");
	        }

	        qry.append("and jc.vehicleOutDate >= getdate()-").append(cnt).append(" ");
	        qry.append("order by jc.jobCardNo desc");

	        String finalQuery = qry.toString();
	        Query query = session.createQuery(finalQuery);
	        query.setParameterList("dealerList", dealerList);

	        Iterator it = query.list().iterator();
	        while (it.hasNext()) {
	            Object[] enqobj = (Object[]) it.next();
	            WarrantyForm warrantyForm1 = new WarrantyForm();
	            warrantyForm1.setJobCardNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
	            warrantyForm1.setPayeeName(enqobj[1] == null ? "" : enqobj[1].toString().trim());
	            warrantyForm1.setVinNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
	            warrantyForm1.setTmsRefNo(enqobj[3] == null ? "NA" : enqobj[3].toString().trim());
	            String jobcardDateString = enqobj[4] == null ? "" : enqobj[4].toString().trim();
	            if (!jobcardDateString.equals("")) {
	                Date wdate = inputFormat.parse(jobcardDateString);
	                String wd = outputFormat.format(wdate);
	                warrantyForm1.setJobCardDate(wd);
	            }
	            if (enqobj[5] != null) {
	                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	                String dateString = enqobj[5].toString().trim();
	                LocalDateTime repDate = LocalDateTime.parse(dateString, inputFormatter);

	                // Add 120 days to the parsed date
	                LocalDateTime newDate = repDate.plusDays(cnt);

	                // Format the new date into the specified format
	                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	                String dateAfter = newDate.format(outputFormatter);

	                warrantyForm1.setReplacementDate(dateAfter);  // job card completion date (vehicle out date)
	            }
	            dataList.add(warrantyForm1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (session != null) {
	                session.close();
	                session = null;
	            }
	        } catch (Exception ee) {
	            ee.printStackTrace();
	        }
	    }

	    return dataList;
	}

	

    public void getJobCardDetail(WarrantyForm warrantyForm, String jobCardNo) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
       // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String hql = "";
        try {
            hql = " Select jc.jobCardNo,jc.payeeName,jc.vinno,jc.tmsRefNo,jc.jobCardDate, "
                    + "jc.dealerCode, jc.engineno,jc.vinid,vd.customerName,vd.modelCode,vd.modelCodeDesc"
                    + ",vd.modelFamily,vd.modelFamilyDesc ,vd.deliveryDate,jc.hmr ,jc.vehicleOutDate,(select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=jc.dealerCode) as dealerName ,vd.mobilePh ," + //sale date replace with delivery date
                    " vd.villageName  from Jobcarddetails jc ,Vehicledetails vd where "
                    + " jc.vinid=vd.vinid and jc.jobCardNo='" + jobCardNo + "'";

            Query query = session.createQuery(hql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                warrantyForm.setJobCardNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm.setPayeeName(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm.setVinNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[3] == null ? "NA" : enqobj[3].toString().trim());
                String jobcardDateString = enqobj[4] == null ? "" : enqobj[4].toString().trim();
                Date wdate = inputFormat.parse(jobcardDateString);
                String wd = outputFormat.format(wdate);
                warrantyForm.setJobCardDate(wd);
                warrantyForm.setDealer_code(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setEngineNo(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                warrantyForm.setVinid(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                warrantyForm.setCustomerName(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                warrantyForm.setModelNo(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                warrantyForm.setModelDesc(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                warrantyForm.setModelFamily(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                warrantyForm.setModelFamilyDesc(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                warrantyForm.setDeliveryDate(enqobj[13] == null ? "" : outputFormat.format(inputFormat.parse(enqobj[13].toString().trim())));  //salesDate
                
                System.out.println(enqobj[0] +" job card no");
                System.out.println(enqobj[14] +" hmr hours");
                
                warrantyForm.setHours(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                if (enqobj[15] != null) {
                    warrantyForm.setReplacementDate(enqobj[15] == null ? "" : outputFormat.format(inputFormat.parse(enqobj[15].toString().trim())));  //job card complition date (vehical out date )
                }
                warrantyForm.setDealerName(enqobj[16] == null ? "" : enqobj[16].toString().trim());
                warrantyForm.setContactNo(enqobj[17] == null ? "" : enqobj[17].toString().trim());
                warrantyForm.setCustomerAddress(enqobj[18] == null ? "" : enqobj[18].toString().trim());
                warrantyForm.setCurrentDate(outputFormat.format(new Date()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }

    }

    public ArrayList<WarrantyForm> getJobCardPartDetail(WarrantyForm wf, String jobCardNo,String flag, Connection con) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        ResultSet rs = null;
        String hql = "";
        DecimalFormat df = new DecimalFormat("#.####");
        try {

            CallableStatement cs = con.prepareCall("{call SP_getWarrantyPartsInJobCard(?,?)}");
            cs.setString(1, jobCardNo.trim());
            cs.setString(2, flag);
            rs = cs.executeQuery();
            while (rs.next()) {
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setComplaintNo(rs.getString(1) == null ? "" : rs.getString(1).trim());
                warrantyForm.setCompDesc(rs.getString(2) == null ? "" : rs.getString(2).trim());
                warrantyForm.setDefectCode(rs.getString(3) == null ? "" : rs.getString(3).trim());
                warrantyForm.setBillID(rs.getString(4) == null ? "" : rs.getString(4).trim());
                warrantyForm.setCausalOrConseq(rs.getString(5) == null ? "" : rs.getString(5).trim());
                warrantyForm.setDiscInPerc(rs.getString(6) == null ? "" : rs.getString(6).trim());
                warrantyForm.setJobSpareID(rs.getString(7) == null ? "" : rs.getString(7).trim());
                warrantyForm.setFinalAmount(rs.getString(8) == null ? "0" : rs.getString(8).trim());
                warrantyForm.setPartNo(rs.getString(9) == null ? "" : rs.getString(9).trim());
                warrantyForm.setPartDesc(rs.getString(10) == null ? "" : rs.getString(10).trim().toUpperCase());
                warrantyForm.setQty(rs.getString(11) == null ? "0" : rs.getString(11).trim());
                warrantyForm.setUnitPrice(rs.getString(12) == null ? "0" : rs.getString(12).toString().trim());
                warrantyForm.setUnit(rs.getString(13) == null ? "0" : rs.getString(13).trim());
                warrantyForm.setPartCategory(rs.getString(14) == null ? "" : rs.getString(14).trim());
                warrantyForm.setCmpNo(rs.getString(15) == null ? "" : rs.getString(15).trim());
                warrantyForm.setHsnCode(rs.getString(16) == null ? "" : rs.getString(16).toString().trim());
                warrantyForm.setDealerDiscount(rs.getString(17) == null ? "" : df.format(Double.parseDouble(rs.getString(17).toString().trim())));
                warrantyForm.setTaxableValue(rs.getString(18) == null ? "0" : df.format(Double.parseDouble(rs.getString(18).toString().trim())));
                warrantyForm.setCgstRate(rs.getString(19) == null ? "0" : rs.getString(19).toString().trim());
                warrantyForm.setCgstAmt(rs.getString(20) == null ? "0" : df.format(Double.parseDouble(rs.getString(20).toString().trim())));
                warrantyForm.setSgstRate(rs.getString(21) == null ? "0" : rs.getString(21).toString().trim());
                warrantyForm.setSgstAmt(rs.getString(22) == null ? "0" : df.format(Double.parseDouble(rs.getString(22).toString().trim())));
                warrantyForm.setUgstRate(rs.getString(23) == null ? "0" : rs.getString(23).toString().trim());
                warrantyForm.setUgstAmt(rs.getString(24) == null ? "0" : df.format(Double.parseDouble(rs.getString(24).toString().trim())));
                warrantyForm.setIgstRate(rs.getString(25) == null ? "0" : rs.getString(25).toString().trim());
                warrantyForm.setIgstAmt(rs.getString(26) == null ? "0" : df.format(Double.parseDouble(rs.getString(26).toString().trim())));
                warrantyForm.setClaimValue(rs.getString("TotalClaimValue") == null || rs.getString("TotalClaimValue").equals("")  ? "0" : df.format(Double.parseDouble(rs.getString("TotalClaimValue").toString().trim())));
                warrantyForm.setTotalTaxableValue(rs.getString("TOTALTAXABLEVALUE") == null || rs.getString("TOTALTAXABLEVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("TOTALTAXABLEVALUE").toString().trim())));
                warrantyForm.setTotalTaxValue(rs.getString("TOTALTAXVALUE") == null || rs.getString("TOTALTAXVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("TOTALTAXVALUE").toString().trim())));
                warrantyForm.setTotalInvoiceAmount(rs.getString("TOTALINVOICEAMOUNT") == null || rs.getString("TOTALINVOICEAMOUNT").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("TOTALINVOICEAMOUNT").toString().trim())));
                warrantyForm.setTotalClaimValue(rs.getString("TOTALPARTSVALUE") == null || rs.getString("TOTALPARTSVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("TOTALPARTSVALUE").toString().trim())));
                // warrantyForm.setDealerName(enqobj[16] == null ? "" : enqobj[16].toString().trim());

                dataList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<WarrantyForm> getTaxDetail(String dealerCode, String claimNo, Connection con, WarrantyForm wForm,String jobCardNo) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        ResultSet rs = null;
        wForm.setFlag("false");
        try {

            CallableStatement cs = con.prepareCall("{call SP_getWarrantyTaxes(?,?,?)}");
            cs.setString(1, dealerCode);
            cs.setString(2, claimNo);
            cs.setString(3, jobCardNo.trim());
            rs = cs.executeQuery();
            while (rs.next()) {

                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setTyprId(rs.getString(1) == null ? "" : rs.getString(1).trim());
                warrantyForm.setTypeDescription(rs.getString(2) == null ? "" : rs.getString(2).trim());
                warrantyForm.setStateID(rs.getString(3) == null ? "" : rs.getString(3).trim());
                warrantyForm.setTypePercentage(rs.getString(4) == null ? "" : rs.getString(4).trim());
                dataList.add(warrantyForm);
                if (rs.getString(2).equals("Less")) {
                    wForm.setFlag("true");
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
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return dataList;
    }
/*
    public WarrantyForm saveWarranty(WarrantyForm wf, String userId, String realPath) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // String result = "fail";
        File dest_folder = null;
        String dest = "";
       // String extension1 = "";
        String extension2 = "";
      //  String courierCopyFileName = "";
        String failedPartFileName = "";
        FormFile doc = null;
        try {
            String warrClaimNo = new MethodUtility().getNumber(session, "Warrantyclaim", wf.getDealer_code(), "W");
            wf.setWarrantyClaimNo(warrClaimNo);
            session.beginTransaction();

//            if (wf.getCourierCopy() != null) {
//                courierCopyFileName = wf.getCourierCopy().getFileName();
//            }
            if (wf.getFailedPart() != null) {
                failedPartFileName = wf.getFailedPart().getFileName();
            }
//            if (courierCopyFileName != null) {
//                int i = courierCopyFileName.lastIndexOf('.');
//                if (i >= 0) {
//                    extension1 = courierCopyFileName.substring(i + 1);
//                    //  System.out.println("extension is  " + extension1);
//                }
//            }
            if (failedPartFileName != null) {
                int i = failedPartFileName.lastIndexOf('.');
                if (i >= 0) {
                    extension2 = failedPartFileName.substring(i + 1);
                    // System.out.println("extension is  " + extension2);
                }
            }
//            if (!courierCopyFileName.equals("")) {
//                dest_folder = new File(realPath + "\\Documents\\WarrantyClaim\\");
//                if (dest_folder.exists()) {
//                    dest_folder.delete();
//                }
//            }
//            dest = realPath + "\\Documents\\Warranty\\";
//            doc = wf.getCourierCopy();
            //   courierCopyFileName = wf.getCourierCopy().getFileName();
//            String newcourierCopyFileName = warrClaimNo.replace("/", "-").concat("-").concat("CourierCopy").concat(".").concat(extension1);


//            if (!courierCopyFileName.equals("")) {
//                MethodUtility.uploadFile(newcourierCopyFileName, dest, doc, realPath);
//            }
            if (!failedPartFileName.equals("")) {
                dest_folder = new File(realPath + "\\Documents\\WarrantyClaim\\");
                if (dest_folder.exists()) {
                    dest_folder.delete();
                }
            }
            dest = realPath + "\\Documents\\Warranty\\";
            doc = wf.getFailedPart();
            //  failedPartFileName = wf.getFailedPart().getFileName();
            String newfailedPartFileName = warrClaimNo.replace("/", "-").concat("-").concat("FailedPart").concat(".").concat(extension2);
            if (!failedPartFileName.equals("")) {
                MethodUtility.uploadFile(newfailedPartFileName, dest, doc, realPath);
            }
            Warrantyclaim wc = new Warrantyclaim();
            wc.setWarrantyClaimNo(warrClaimNo);
            //wc.setCourierCopyFile(wf.getCourierCopy().getFileName());
            //wc.setFailedPartsFileAttached(wf.getFailedPart().getFileName());

//            if (wf.getCourierCopy() != null) {
//                wc.setCourierCopyFile(newcourierCopyFileName);
//            } else {
//                wc.setCourierCopyFile(null);
//            }
            if (wf.getFailedPart() != null) {
                wc.setFailedPartsFileAttached(newfailedPartFileName);
            } else {
                wc.setFailedPartsFileAttached(null);
            }
            if (!wf.getClaimDate().equals("")) {
                wc.setClaimDate(df.parse(wf.getClaimDate()));
            }
            else
            {
                wc.setClaimDate(new Date());
            }
            wc.setClaimStatus("PENDING");
            //  wc.setCourierName(wf.getCourierName());
            //  wc.setCourierNo(wf.getCourierNo());
            wc.setDealerCode(wf.getDealer_code());
            if (!wf.getDeliveryDate().equals("")) {
                wc.setDeliverySaleDate(df.parse(wf.getDeliveryDate()));
            }

            //wc.setDispatchedBy(wf.getDispatchBy());
            // if (!wf.getDispatchBy().equals("BY Hand")) {
            //      wf.setCourierNo(wf.getCourierNo());
            //      wf.setCourierName(wf.getCourierName());
            //   }
            //   if (!wf.getDispatchDate().equals("")) {
            //       wc.setDispatchedDate(df.parse(wf.getDispatchDate()));
            //  }
            wc.setEngineno(wf.getEngineNo());
            if (!wf.getFailureDate().equals("")) {
                wc.setFailureDate(df.parse(wf.getFailureDate()));
            }
            if (!wf.getHours().equals("")) {
                wc.setHmr(Long.parseLong(wf.getHours()));
            }
            wc.setIsServerSync('N');
            if (!wf.getJobCardDate().equals("")) {
                wc.setJobCardDate(df.parse(wf.getJobCardDate()));
            }
            wc.setJobCardNo(wf.getJobCardNo());
            wc.setLastModifiedBy(userId);
            wc.setLastModifiedOn(new Date());
            wc.setCreatedBy(userId);
            wc.setCreatedOn(new Date());
            wc.setLastSyncDate(null);
            //   wc.setPartDispatchedFor(wf.getDispatchFor());
            if (!wf.getReplacementDate().equals("")) {
                wc.setReplacementDate(df.parse(wf.getReplacementDate()));
            }
            wc.setTmsRefNo(wf.getTmsRefNo());
            wc.setVinid(wf.getVinid());
            wc.setVinno(wf.getVinNo());
            if (!wf.getTotalClaimValue().equals("")) {
                wc.setTotalAmount(new BigDecimal(wf.getTotalClaimValue()));
            }
            if (!wf.getClaimValueAfterDiscount().equals("")) {   ///// IT IS GRAND TOTAL.....variable nomenclature used is wrong
                wc.setTotalClaimAmount(new BigDecimal(wf.getClaimValueAfterDiscount()));
            }
//            wc.setLessAmount(new BigDecimal(wf.getLessAmmountArr()[0]));
//            wc.setCstvat(new BigDecimal(wf.getLessAmmountArr()[1]));
            wc.setHandlingCharges(new BigDecimal(wf.getLessAmmountArr()[0]));
            wc.setGstOnHandling(new BigDecimal(wf.getLessAmmountArr()[1]));
            wc.setInsuranceCharges(new BigDecimal(wf.getLessAmmountArr()[2]));
            wc.setGstOnInsurance(new BigDecimal((wf.getLessAmmountArr()[3] == null || wf.getLessAmmountArr()[3].equals("")) ? "0" : wf.getLessAmmountArr()[3]));

                session.save(wc);
            if (wf.getJobSpareIDArr() != null) {

                for (int i = 0; i < wf.getJobSpareIDArr().length; i++) {
                    Warrantyclaimdetails wcd = new Warrantyclaimdetails();
                    wcd.setWarrantyClaimNo(warrClaimNo);
                    wcd.setCausalOrConseq(wf.getCausalOrConseqArr()[i]);
                    wcd.setClaimSpareID(wf.getJobSpareIDArr()[i]);
                    wcd.setCmpNo(wf.getCmpNoArr()[i]);
                    wcd.setPartCategory(wf.getPartCategoryArr()[i]);
                    wcd.setPartDesc(wf.getPartDescArr()[i].toUpperCase());
                    wcd.setPartNo(wf.getPartNoArr()[i]);
                    wcd.setQty(Double.parseDouble(wf.getQtyArr()[i]));
                    wcd.setUnitPrice(new BigDecimal(wf.getUnitPriceArr()[i]));
                    wcd.setClaimAmount(Double.parseDouble(wf.getClaimValueArr()[i]));
                    wcd.setVendorCode(wf.getVenderCodeArr()[i]);
                    wcd.setDispatchStatus("PENDING");
                    wcd.setHsnCode(wf.getHsnNoArr()[i]);
                    wcd.setDealerDisc(new BigDecimal(wf.getDealerDiscountArr()[i]));
                    wcd.setClaimTaxableValue(new BigDecimal(wf.getTaxableValueArr()[i]));
                    wcd.setClaimCGSTRate(new BigDecimal(wf.getCgstRateArr()[i]));
                    wcd.setClaimCGSTAmt(new BigDecimal(wf.getCgstAmtArr()[i]));
                    wcd.setClaimSGSTRate(new BigDecimal(wf.getSgstRateArr()[i]));
                    wcd.setClaimSGSTAmt(new BigDecimal(wf.getSgstAmtArr()[i]));
                    wcd.setClaimUGSTRate(new BigDecimal(wf.getUgstRateArr()[i]));
                    wcd.setClaimUGSTAmt(new BigDecimal(wf.getUgstAmtArr()[i]));
                    wcd.setClaimIGSTRate(new BigDecimal(wf.getIgstRateArr()[i]));
                    wcd.setClaimIGSTAmt(new BigDecimal(wf.getIgstAmtArr()[i]));
                    wcd.setTotalClaimValue(new BigDecimal(wf.getClaimValueArr()[i]));
                    session.save(wcd);
                }
            }

            Query jcQuery = session.createQuery("select jobCardNo from Jobcarddetails where vinNo=:vinNo");
            jcQuery.setParameter("vinNo", wf.getVinNo());
            List jcList = (List) jcQuery.list();
            //System.out.println("list is  " + jcList.get(0));
           // String jcNo = jcList.get(0).toString();

            Jobcarddetails jcdetail = (Jobcarddetails) session.load(Jobcarddetails.class, wf.getJobCardNo()); //jcNo
            jcdetail.setWtyClaimStatus("CREATED");
            session.saveOrUpdate(jcdetail);


            session.getTransaction().commit();
            wf.setResult("success");
            // result = "success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return wf;
    }*/
    
    public WarrantyForm saveWarranty(WarrantyForm wf, String userId, String realPath,String taxType) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        File dest_folder = null;
        String dest = "";
        FormFile[] failedParts = {wf.getFailedPart1(), wf.getFailedPart2(), wf.getFailedPart3(), wf.getFailedPart4(), wf.getFailedPart5()};
        String[] extensions = new String[5];
        String[] failedPartFileNames = new String[5];
        FormFile[] docs = new FormFile[5];
        Transaction transaction = null;
        try {
            String warrClaimNo = new MethodUtility().getNumber(session, "Warrantyclaim", wf.getDealer_code(), "W");
            wf.setWarrantyClaimNo(warrClaimNo);
           // session.beginTransaction();
            transaction = session.beginTransaction();
            
            for (int j = 0; j < 5; j++) {
                if (failedParts[j] != null) {
                    failedPartFileNames[j] = failedParts[j].getFileName();
                    int i = failedPartFileNames[j].lastIndexOf('.');
                    if (i >= 0) {
                        extensions[j] = failedPartFileNames[j].substring(i + 1);
                    }
                    docs[j] = failedParts[j];
                }
            }
			
			dest_folder = new File(realPath + "\\Documents\\WarrantyClaim\\");
			if (dest_folder.exists()) {
				dest_folder.delete();
			}
			dest = realPath + "\\Documents\\Warranty\\";

			for (int j = 0; j < 5; j++) {
				if (docs[j] != null && !failedPartFileNames[j].equals("")) {
					String newFailedPartFileName = warrClaimNo.replace("/", "-").concat("-")
							.concat("FailedPart" + (j + 1)).concat(".").concat(extensions[j]);
					MethodUtility.uploadFile(newFailedPartFileName, dest, docs[j], realPath);
				}
			}
            Warrantyclaim wc = new Warrantyclaim();
            wc.setWarrantyClaimNo(warrClaimNo);
            
            for (int j = 0; j < 5; j++) {
                if (failedParts[j] != null && extensions[j] != null) {
                    String newFailedPartFileName = warrClaimNo.replace("/", "-").concat("-").concat("FailedPart" + (j+1)).concat(".").concat(extensions[j]);
                    switch (j) {
                        case 0: wc.setFailedPartsFileAttached1(newFailedPartFileName); break;
                        case 1: wc.setFailedPartsFileAttached2(newFailedPartFileName); break;
                        case 2: wc.setFailedPartsFileAttached3(newFailedPartFileName); break;
                        case 3: wc.setFailedPartsFileAttached4(newFailedPartFileName); break;
                        case 4: wc.setFailedPartsFileAttached5(newFailedPartFileName); break;
                    }
                } else {
                    switch (j) {
                        case 0: wc.setFailedPartsFileAttached1(null); break;
                        case 1: wc.setFailedPartsFileAttached2(null); break;
                        case 2: wc.setFailedPartsFileAttached3(null); break;
                        case 3: wc.setFailedPartsFileAttached4(null); break;
                        case 4: wc.setFailedPartsFileAttached5(null); break;
                    }
                }
            }
            if (!wf.getClaimDate().equals("")) {
                wc.setClaimDate(df.parse(wf.getClaimDate()));
            } else {
                wc.setClaimDate(new Date());
            }
            wc.setClaimStatus("PENDING");
            
            wc.setDealerCode(wf.getDealer_code());
            if (!wf.getDeliveryDate().equals("")) {
                wc.setDeliverySaleDate(df.parse(wf.getDeliveryDate()));
            }

            
            wc.setEngineno(wf.getEngineNo());
            if (!wf.getFailureDate().equals("")) {
                wc.setFailureDate(df.parse(wf.getFailureDate()));
            }
            if (!wf.getHours().equals("")) {
                wc.setHmr(Long.parseLong(wf.getHours()));
            }
            wc.setIsServerSync('N');
            if (!wf.getJobCardDate().equals("")) {
                wc.setJobCardDate(df.parse(wf.getJobCardDate()));
            }
            wc.setJobCardNo(wf.getJobCardNo());
            wc.setLastModifiedBy(userId);
            wc.setLastModifiedOn(new Date());
            wc.setCreatedBy(userId);
            wc.setCreatedOn(new Date());
            wc.setLastSyncDate(null);
            
            if (!wf.getReplacementDate().equals("")) {
                wc.setReplacementDate(df.parse(wf.getReplacementDate()));
            }
            wc.setTmsRefNo(wf.getTmsRefNo());
            wc.setVinid(wf.getVinid());
            wc.setVinno(wf.getVinNo());
            if (!wf.getTotalClaimValue().equals("")) {
                wc.setTotalAmount(new BigDecimal(wf.getTotalClaimValue()));
            }
            if (!wf.getClaimValueAfterDiscount().equals("")) {   
                wc.setTotalClaimAmount(new BigDecimal(wf.getClaimValueAfterDiscount()));
            }

            wc.setHandlingCharges(new BigDecimal(wf.getLessAmmountArr()[0]));
            wc.setGstOnHandling(new BigDecimal(wf.getLessAmmountArr()[1]));
            wc.setInsuranceCharges(new BigDecimal(wf.getLessAmmountArr()[2]));
            wc.setGstOnInsurance(new BigDecimal((wf.getLessAmmountArr()[3] == null || wf.getLessAmmountArr()[3].equals("")) ? "0" : wf.getLessAmmountArr()[3]));
            
            System.out.println(wc);
            
            BigDecimal handlingCharges = wc.getHandlingCharges();
            BigDecimal gstOnHandling = wc.getGstOnHandling();
            BigDecimal insuranceCharges = wc.getInsuranceCharges();
            BigDecimal gstOnInsurance = wc.getGstOnInsurance();
            boolean isValidHandlingAndLabourCharges = false;
            
            
            
                session.save(wc);
                
                
                isValidHandlingAndLabourCharges =  (handlingCharges.compareTo(BigDecimal.ZERO) > 0 && gstOnHandling.compareTo(BigDecimal.ZERO) > 0)
                        && (insuranceCharges.compareTo(BigDecimal.ZERO) > 0 && gstOnInsurance.compareTo(BigDecimal.ZERO) > 0);

                        if (isValidHandlingAndLabourCharges) {
            				
            			} else {
            				//session.getTransaction().rollback();
            				transaction.rollback();
            				System.out.println("Handling and Labour Charges is not maintained. Kindly contact administrator");
            				wf.setResult("FAILURE");
            				return wf;
            			}
                
            if (wf.getJobSpareIDArr() != null) {
            	
            	/*  commented by Aman 21-03-2025

                for (int i = 0; i < wf.getJobSpareIDArr().length; i++) {
                    Warrantyclaimdetails wcd = new Warrantyclaimdetails();
                    wcd.setWarrantyClaimNo(warrClaimNo);
                    wcd.setCausalOrConseq(wf.getCausalOrConseqArr()[i]);
                    wcd.setClaimSpareID(wf.getJobSpareIDArr()[i]);
                    wcd.setCmpNo(wf.getCmpNoArr()[i]);
                    wcd.setPartCategory(wf.getPartCategoryArr()[i]);
                    wcd.setPartDesc(wf.getPartDescArr()[i].toUpperCase());
                    wcd.setPartNo(wf.getPartNoArr()[i]);
                    wcd.setQty(Double.parseDouble(wf.getQtyArr()[i]));
                    wcd.setUnitPrice(new BigDecimal(wf.getUnitPriceArr()[i]));
                    wcd.setClaimAmount(Double.parseDouble(wf.getClaimValueArr()[i]));
                    wcd.setVendorCode(wf.getVenderCodeArr()[i]);
                    wcd.setDispatchStatus("PENDING");
                    wcd.setHsnCode(wf.getHsnNoArr()[i]);
                    wcd.setDealerDisc(new BigDecimal(wf.getDealerDiscountArr()[i]));
                    wcd.setClaimTaxableValue(new BigDecimal(wf.getTaxableValueArr()[i]));
                    wcd.setClaimCGSTRate(new BigDecimal(wf.getCgstRateArr()[i]));
                    wcd.setClaimCGSTAmt(new BigDecimal(wf.getCgstAmtArr()[i]));
                    wcd.setClaimSGSTRate(new BigDecimal(wf.getSgstRateArr()[i]));
                    wcd.setClaimSGSTAmt(new BigDecimal(wf.getSgstAmtArr()[i]));
                    wcd.setClaimUGSTRate(new BigDecimal(wf.getUgstRateArr()[i]));
                    wcd.setClaimUGSTAmt(new BigDecimal(wf.getUgstAmtArr()[i]));
                    wcd.setClaimIGSTRate(new BigDecimal(wf.getIgstRateArr()[i]));
                    wcd.setClaimIGSTAmt(new BigDecimal(wf.getIgstAmtArr()[i]));
                    wcd.setTotalClaimValue(new BigDecimal(wf.getClaimValueArr()[i]));
                    
                    System.out.println(wcd);
                    
                    session.save(wcd);
                }
                
                */
            	
            	// code for prevent saving zero tax
            	
				for (int i = 0; i < wf.getJobSpareIDArr().length; i++) {
					Warrantyclaimdetails wcd = new Warrantyclaimdetails();
					wcd.setWarrantyClaimNo(warrClaimNo);
					wcd.setCausalOrConseq(wf.getCausalOrConseqArr()[i]);
					wcd.setClaimSpareID(wf.getJobSpareIDArr()[i]);
					wcd.setCmpNo(wf.getCmpNoArr()[i]);
					wcd.setPartCategory(wf.getPartCategoryArr()[i]);
					wcd.setPartDesc(wf.getPartDescArr()[i].toUpperCase());
					wcd.setPartNo(wf.getPartNoArr()[i]);
					wcd.setQty(Double.parseDouble(wf.getQtyArr()[i]));
					wcd.setUnitPrice(new BigDecimal(wf.getUnitPriceArr()[i]));
					wcd.setClaimAmount(Double.parseDouble(wf.getClaimValueArr()[i]));
					wcd.setVendorCode(wf.getVenderCodeArr()[i]);
					wcd.setDispatchStatus("PENDING");
					wcd.setHsnCode(wf.getHsnNoArr()[i]);
					wcd.setDealerDisc(new BigDecimal(wf.getDealerDiscountArr()[i]));
					wcd.setClaimTaxableValue(new BigDecimal(wf.getTaxableValueArr()[i]));

					boolean isValidTax = false;

					BigDecimal cgstRate = new BigDecimal(wf.getCgstRateArr()[i]);
					BigDecimal cgstAmt = new BigDecimal(wf.getCgstAmtArr()[i]);

					BigDecimal sgstRate = new BigDecimal(wf.getSgstRateArr()[i]);
					BigDecimal sgstAmt = new BigDecimal(wf.getSgstAmtArr()[i]);

					BigDecimal ugstRate = new BigDecimal(wf.getUgstRateArr()[i]);
					BigDecimal ugstAmt = new BigDecimal(wf.getUgstAmtArr()[i]);

					BigDecimal igstRate = new BigDecimal(wf.getIgstRateArr()[i]);
					BigDecimal igstAmt = new BigDecimal(wf.getIgstAmtArr()[i]);

					wcd.setClaimCGSTRate(cgstRate);
					wcd.setClaimCGSTAmt(new BigDecimal(wf.getCgstAmtArr()[i]));
					wcd.setClaimSGSTRate(sgstRate);
					wcd.setClaimSGSTAmt(new BigDecimal(wf.getSgstAmtArr()[i]));
					wcd.setClaimUGSTRate(ugstRate);
					wcd.setClaimUGSTAmt(new BigDecimal(wf.getUgstAmtArr()[i]));
					wcd.setClaimIGSTRate(igstRate);
					wcd.setClaimIGSTAmt(new BigDecimal(wf.getIgstAmtArr()[i]));

					wcd.setTotalClaimValue(new BigDecimal(wf.getClaimValueArr()[i]));

					if ("SGST".equalsIgnoreCase(taxType)) {
						isValidTax = (sgstRate.compareTo(BigDecimal.ZERO) > 0 && sgstAmt.compareTo(BigDecimal.ZERO) > 0)
								&& (cgstRate.compareTo(BigDecimal.ZERO) > 0 && cgstAmt.compareTo(BigDecimal.ZERO) > 0)
								&& (igstRate.compareTo(BigDecimal.ZERO) ==  0 && igstAmt.compareTo(BigDecimal.ZERO) == 0);
					} else if ("IGST".equalsIgnoreCase(taxType)) {
						isValidTax = (igstRate.compareTo(BigDecimal.ZERO) > 0
								&& igstAmt.compareTo(BigDecimal.ZERO) > 0) &&
								(sgstRate.compareTo(BigDecimal.ZERO) == 0 && sgstAmt.compareTo(BigDecimal.ZERO) == 0)
								&& (cgstRate.compareTo(BigDecimal.ZERO) == 0 && cgstAmt.compareTo(BigDecimal.ZERO) == 0);
					}
					
					System.out.println(wcd);
					System.out.println("isValidTax "+isValidTax);


					if (isValidTax) {
						session.save(wcd);
					} else {
						wf.setResult("FAILURE");
					//	session.getTransaction().rollback();
						transaction.rollback();
						System.out.println("GST SLAB is not Maintained. Kindly contact administrator");
						return wf;
					}

				}

            }

            Query jcQuery = session.createQuery("select jobCardNo from Jobcarddetails where vinNo=:vinNo");
            jcQuery.setParameter("vinNo", wf.getVinNo());

            Jobcarddetails jcdetail = (Jobcarddetails) session.load(Jobcarddetails.class, wf.getJobCardNo()); //jcNo
            jcdetail.setWtyClaimStatus("CREATED");
            session.saveOrUpdate(jcdetail);


       //     session.getTransaction().commit();
            transaction.commit();
            wf.setResult("success");
        } catch (Exception e) {
        //    session.getTransaction().rollback();
        	transaction.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return wf;
    }

    public ArrayList<WarrantyForm> getWarrantyClaimList(WarrantyForm wf, Vector userFunctionalities, String user_id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String hql = "";
        String subHql = ""; String dateSubQur = "";String claimStatusHql="";
        SQLQuery query = null;
        String vinNo = wf.getVinNo() == null ? "" : wf.getVinNo();
        String claimNo = wf.getWarrantyClaimNo() == null ? "" : wf.getWarrantyClaimNo();
        String claimStatus = wf.getClaimStatus() == null ? "" : wf.getClaimStatus();
        String fromDate = wf.getFromDate() == null ? "" : wf.getFromDate();
        String toDate = wf.getToDate() == null ? "" : wf.getToDate();
        wf.setClaimStatus(claimStatus);
        try {
            
            if (!vinNo.equals("")) {
                subHql = "wc.vinno like '%" + wf.getVinNo() + "%'   "+" ";
            }
            if (!claimNo.equals("")) {
            	if (!subHql.isEmpty()) subHql += "AND ";
                subHql += " wc.WarrantyClaimNo like '%" + wf.getWarrantyClaimNo() + "%'   "+" ";
            }
            
            if(!claimStatus.equalsIgnoreCase("TAX INVOICE RECEIVED") && !claimStatus.equalsIgnoreCase("CREDITED") && !claimStatus.equals("")) {
            	claimStatusHql = "wc.ClaimStatus="+ "'"+wf.getClaimStatus()+"' ";
            }
            
            if(claimStatus.equalsIgnoreCase("CREDITED") && !claimStatus.equals("")) {
            	claimStatusHql = "wc.SAP_SyncStatus='CREDITED' ";
            }
            
            if(claimStatus.equalsIgnoreCase("TAX INVOICE RECEIVED") && !claimStatus.equals("")) {
            	claimStatusHql = "wc.taxInvoiceStatus='RECEIVED' ";
            }
            
            
            if("1".equals(wf.getRange()) ){
               dateSubQur=" ( wc.ClaimDate between isnull(?, wc.ClaimDate) and isnull(?, wc.ClaimDate) )";
            }
            
            String whereClause ="";
            
            if(!dateSubQur.equals("") || !claimStatusHql.equals("") || !subHql.equals("")) {
            	whereClause=" where ";
            }
            
			if (userFunctionalities.contains("703")) {

				hql = " select wc.WarrantyClaimNo as warrantyClaimNo, wc.JobCardNo as jobCardNo, wc.TMS_REF_NO as tmsRefNo, "
						+ " wc.JobCardDate as jobCardDate, wc.ClaimDate as claimDate,  wc.vinno as vinno, dm.DealerName as dealerName,"
						+ " wc.DealerCode as dealerCode, "
						+ "wc.ClaimStatus AS claimStatus,"
						+ "dm.Location as location, wc.HMR as hmr,"
						+ " ISNULL(FORMAT(swcu.Invoice_Date, 'dd-MMM-yyyy'), '') AS invoice_date,ISNULL(swcu.Invoice_No,'') AS Invoice_No,wc.SIGN_STATUS,wc.uuid  from SW_warranty_claim wc "
						+ " inner join (Select DEALER_CODE from FN_GetDealersUnderUser('" + user_id	+ "')) DEALER on wc.DealerCode =DEALER.DEALER_CODE "
						+ " inner join UM_DealerMaster dm on dm.DealerCode=wc.DealerCode "
						+ " left join SW_warrantyClaimInvoiceUpdate swcu on wc.WarrantyClaimNo=swcu.WarrantyClaimNo"
						+ whereClause;//+claimStatusHql + subHql + dateSubQur;
				

			    // Add conditions dynamically with AND (if applicable)
			    boolean isConditionAdded = false; // To track if a condition has already been added

			    // Add claimStatusHql if it's not empty
			    if (!claimStatusHql.isEmpty()) {
			        hql += claimStatusHql;
			        isConditionAdded = true;  // Mark that a condition has been added
			    }

			    // Add AND before subHql if another condition was already added
			    if (isConditionAdded && !subHql.isEmpty()) {
			        hql += " AND ";
			    }
			    if (!subHql.isEmpty()) {
			        hql += subHql;
			        isConditionAdded = true; // Mark again that a condition has been added
			    }

			    // Add AND before dateSubQur if any condition has been added already
			    if (isConditionAdded && !dateSubQur.isEmpty()) {
			        hql += " AND ";
			    }
			    if (!dateSubQur.isEmpty()) {
			        hql += dateSubQur;
			    }
				
				if (!userFunctionalities.contains("101") && wf.getDealerName() != null && !wf.getDealerName().isEmpty()) {
					hql += " AND  dm.DealerName = '" + wf.getDealerName() + "' ";
				}
				    hql +=  " order by wc.ClaimDate desc ";

				query = session.createSQLQuery(hql);

			} else {

				hql = " select wc.WarrantyClaimNo as warrantyClaimNo, wc.JobCardNo as jobCardNo, wc.TMS_REF_NO as tmsRefNo, "
						+ " wc.JobCardDate as jobCardDate, wc.ClaimDate as claimDate,  wc.vinno as vinno,  dm.DealerName as dealerName,"
						+ " wc.DealerCode as dealerCode, "
						+ "wc.ClaimStatus AS claimStatus,"
						+ " dm.Location  as location, wc.HMR as hmr, "
						+ " ISNULL(FORMAT(swcu.Invoice_Date, 'dd-MMM-yyyy'), '') AS invoice_date,ISNULL(swcu.Invoice_No,'') AS Invoice_No,wc.SIGN_STATUS,wc.uuid  from SW_warranty_claim wc "
						+ "	inner join UM_DealerMaster dm on dm.DealerCode=wc.DealerCode "
						+ " left join SW_warrantyClaimInvoiceUpdate swcu on wc.WarrantyClaimNo=swcu.WarrantyClaimNo"
						+ whereClause
						+ " and wc.DealerCode ='"+wf.getDealer_code()+ "'"
						+ " order by wc.ClaimDate desc ";
				
				if (userFunctionalities.contains("101") && wf.getDealer_code() != null && !wf.getDealer_code().isEmpty()) {
					hql += " AND  wc.DealerCode = '" + wf.getDealer_code() + "' ";
				}
				    hql +=  " order by wc.ClaimDate desc ";
				
				boolean isConditionAdded = false; // To track if a condition has already been added

			    // Add claimStatusHql if it's not empty
			    if (!claimStatusHql.isEmpty()) {
			        hql += claimStatusHql;
			        isConditionAdded = true;  // Mark that a condition has been added
			    }

			    // Add AND before subHql if another condition was already added
			    if (isConditionAdded && !subHql.isEmpty()) {
			        hql += " AND ";
			    }
			    if (!subHql.isEmpty()) {
			        hql += subHql;
			        isConditionAdded = true; // Mark again that a condition has been added
			    }

			    // Add AND before dateSubQur if any condition has been added already
			    if (isConditionAdded && !dateSubQur.isEmpty()) {
			        hql += " AND ";
			    }
			    if (!dateSubQur.isEmpty()) {
			        hql += dateSubQur;
			    }
				
				query = session.createSQLQuery(hql);
			}

            
      //      query.setString(0, claimStatus.equals("") == true ? null : claimStatus);
			if ("1".equals(wf.getRange())) {
				query.setString(0, fromDate.equals("") == true ? null : df.format(sdf.parse(fromDate)));
				query.setString(1, toDate.equals("") == true ? null : df.format(sdf.parse(toDate)));
			}
            List<Object[]>list=query
                        .addScalar("warrantyClaimNo",StandardBasicTypes.STRING)
                        .addScalar("jobCardNo",StandardBasicTypes.STRING)
                        .addScalar("tmsRefNo",StandardBasicTypes.STRING)
                        .addScalar("jobCardDate",StandardBasicTypes.STRING)
                        .addScalar("claimDate",StandardBasicTypes.STRING)
                        .addScalar("vinno",StandardBasicTypes.STRING)
                        .addScalar("dealerName",StandardBasicTypes.STRING)
                        .addScalar("dealerCode",StandardBasicTypes.STRING)
                        .addScalar("claimStatus",StandardBasicTypes.STRING)
                        .addScalar("location",StandardBasicTypes.STRING)
                        .addScalar("hmr",StandardBasicTypes.STRING)
                        .addScalar("invoice_date",StandardBasicTypes.STRING)
                        .addScalar("Invoice_No",StandardBasicTypes.STRING)
                        .addScalar("SIGN_STATUS",StandardBasicTypes.STRING)
                        .addScalar("UUID",StandardBasicTypes.STRING)
                        .list();
            Iterator it =list.iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm.setJobCardNo(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                String jobcardDateString = enqobj[3] == null ? "" : enqobj[3].toString().trim();
                if (!jobcardDateString.equals("")) {
                    Date wdate = inputFormat.parse(jobcardDateString);
                    String wd = outputFormat.format(wdate);
                    warrantyForm.setJobCardDate(wd);
                }
                String claimDateString = enqobj[4] == null ? "" : enqobj[4].toString().trim();
                if (!claimDateString.equals("")) {
                    warrantyForm.setClaimDate(outputFormat.format(inputFormat.parse(claimDateString)));
                }
                warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setDealerName(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                warrantyForm.setDealer_code(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                warrantyForm.setClaimStatus(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                warrantyForm.setLocation(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                warrantyForm.setHmr(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                warrantyForm.setTaxInvoiceDate(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                warrantyForm.setTaxInvoiceNo(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                warrantyForm.setDigitalSignStatus(enqobj[13] == null ? "" : enqobj[13].toString().trim());
                warrantyForm.setUuid(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                dataList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<WarrantyForm> getPendingWarrantyClaimList(WarrantyForm wf, Vector userFunctionalities, String user_id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
       // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String subHql = ""; String dateSubQur = "";
        String claimNo = wf.getWarrantyClaimNo() == null ? "" : wf.getWarrantyClaimNo();
        String fromDate = wf.getFromDate() == null ? "" : wf.getFromDate();
        String toDate = wf.getToDate() == null ? "" : wf.getToDate();
        String vinNo = wf.getVinNo() == null ? "" : wf.getVinNo();
        String hql = "";
        Query query = null;
        ArrayList<String> dealerList=  new ArrayList<String>();
        try {
             if (!claimNo.equals("")) {
                subHql += " and wc.warrantyClaimNo like '%" + wf.getWarrantyClaimNo() + "%'   ";
            }
            if (!vinNo.equals("")) {
                subHql += " and wc.vinno like '%" + vinNo + "%'   ";
            }

            if("1".equals(wf.getRange()) ){
               dateSubQur=" and ( wc.claimDate between isnull(?, wc.claimDate) and isnull(?,wc.claimDate) )";
            }
            hql = "Select wc.warrantyClaimNo,wc.jobCardNo,wc.tmsRefNo,wc.jobCardDate ,wc.claimDate,wc.vinno,(select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=wc.dealerCode) as dealerName, wc.dealerCode,wc.claimStatus, "
                    + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=wc.dealerCode) as location from Warrantyclaim wc where "
                    + " wc.claimStatus ='RECEIVED' "
                    + " " + subHql + "  " + dateSubQur + "" //"and wc.dealerCode IN(:dealerList)" +
                    + " and :dealerList LIKE ('%'+wc.dealerCode+'%') "
                    + " order by wc.claimDate DESC,wc.warrantyClaimNo";
            query = session.createQuery(hql);
            if (wf.getDealer_code() != null && !wf.getDealer_code().equals("") && !wf.getDealer_code().equalsIgnoreCase("ALL")) {
                dealerList.add(wf.getDealer_code());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, user_id);
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
           
             if ("1".equals(wf.getRange())) {
                query.setString(0, fromDate.equals("") == true ? null : df.format(sdf.parse(fromDate)));
                query.setString(1, toDate.equals("") == true ? null : df.format(sdf.parse(toDate)));
            } 
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm.setJobCardNo(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                String jobcardDateString = enqobj[3] == null ? "" : enqobj[3].toString().trim();
                if (!jobcardDateString.equals("")) {
                    Date wdate = inputFormat.parse(jobcardDateString);
                    String wd = outputFormat.format(wdate);
                    warrantyForm.setJobCardDate(wd);
                }
                String claimDateString = enqobj[4] == null ? "" : enqobj[4].toString().trim();
                if (!claimDateString.equals("")) {
                    warrantyForm.setClaimDate(outputFormat.format(inputFormat.parse(claimDateString)));
                }
                warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setDealerName(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                warrantyForm.setDealer_code(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                warrantyForm.setClaimStatus(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                warrantyForm.setLocation(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                dataList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public void getWarrantyClaimDetail(WarrantyForm warrantyForm, String warClaimNo) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
      //  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String hql = "";
        String hql1 = "";
        String vinNo = "";
     //   String hql2 = "";
        try {
            hql = " select wc.warrantyClaimNo,wc.claimDate,wc.tmsRefNo,wc.vinno,wc.vinid,wc.engineno ,"
                    + "wc.hmr,wc.deliverySaleDate,wc.replacementDate,wc.failureDate, " //wc.partDispatchedFor,
                    + "wc.dealerCode," //wc.dispatchedBy,wc.dispatchedDate,wc.courierNo,wc.courierName,
                    + "(select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=wc.dealerCode) as dealerName,"
                    + "vd.customerName,vd.modelCode,vd.modelCodeDesc,"
                    + "vd.modelFamily,vd.modelFamilyDesc ,vd.mobilePh ,vd.villageName ,wc.jobCardNo,wc.jobCardDate,wc.failedPartsFileAttached1, " //,vd.saleDate    ----wc.courierCopyFile ,
                    + "wc.failedPartsFileAttached2,wc.failedPartsFileAttached3,wc.failedPartsFileAttached4,wc.failedPartsFileAttached5, "
                    + " vd.tehsil,vd.district,vd.state,vd.pincode,dm.location,dm.address,dm.disttName,dm.stateName,dm.contactNo ,"
                    + " wc.sapCreditNo, wc.sapCreditDate, wc.sapCreditAmount,wc.claimStatus,wc.sapWarrantyClaim from Warrantyclaim wc ,Vehicledetails vd,Dealervslocationcode dm "
                    + " where wc.vinid=vd.vinid and dm.dealerCode=wc.dealerCode and wc.warrantyClaimNo='" + warClaimNo + "'";
            Query query = session.createQuery(hql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                if (enqobj[1] != null) {
                    warrantyForm.setClaimDate(outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
                }
                vinNo = enqobj[2] == null ? "" : enqobj[3].toString().trim();
                warrantyForm.setTmsRefNo(enqobj[3] == null ? "" : enqobj[2].toString().trim());
                warrantyForm.setVinNo(enqobj[2] == null ? "" : enqobj[3].toString().trim());
                warrantyForm.setVinid(enqobj[7] == null ? "" : enqobj[4].toString().trim());
                warrantyForm.setEngineNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setHours(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                if (enqobj[7] != null) {
                    warrantyForm.setDeliveryDate(outputFormat.format(inputFormat.parse(enqobj[7].toString().trim())));  //salesDate
                }
                if (enqobj[8] != null) {
                    warrantyForm.setReplacementDate(outputFormat.format(inputFormat.parse(enqobj[8].toString().trim())));  //job card complition date (vehical out date )
                }
                if (enqobj[9] != null) {
                    warrantyForm.setFailureDate(outputFormat.format(inputFormat.parse(enqobj[9].toString().trim())));
                }
                // warrantyForm.setDispatchFor(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                //  warrantyForm.setDispatchBy(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                //  warrantyForm.setDispatchDate(outputFormat.format(inputFormat.parse(enqobj[12].toString().trim())));
                //   warrantyForm.setCourierNo(enqobj[13] == null ? "" : enqobj[13].toString().trim());
                //   warrantyForm.setCourierName(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                warrantyForm.setDealer_code(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                warrantyForm.setDealerName(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                warrantyForm.setCustomerName(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                warrantyForm.setModelNo(enqobj[13] == null ? "" : enqobj[13].toString().trim());
                warrantyForm.setModelDesc(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                warrantyForm.setModelFamily(enqobj[15] == null ? "" : enqobj[15].toString().trim());
                warrantyForm.setModelFamilyDesc(enqobj[16] == null ? "" : enqobj[16].toString().trim());
                warrantyForm.setContactNo(enqobj[17] == null ? "" : enqobj[17].toString().trim());
                warrantyForm.setCustomerAddress(enqobj[18] == null ? "" : enqobj[18].toString().trim());
                warrantyForm.setJobCardNo(enqobj[19] == null ? "" : enqobj[19].toString().trim());
                if (enqobj[20] != null) {
                    warrantyForm.setJobCardDate(outputFormat.format(inputFormat.parse(enqobj[20].toString().trim())));
                }
                warrantyForm.setFailedPartFileName1(enqobj[21] == null ? "" : enqobj[21].toString().trim());
                warrantyForm.setFailedPartFileName2(enqobj[22] == null ? "" : enqobj[22].toString().trim());
                warrantyForm.setFailedPartFileName3(enqobj[23] == null ? "" : enqobj[23].toString().trim());
                warrantyForm.setFailedPartFileName4(enqobj[24] == null ? "" : enqobj[24].toString().trim());
                warrantyForm.setFailedPartFileName5(enqobj[25] == null ? "" : enqobj[25].toString().trim());
                //   warrantyForm.setCourierCopyFileName(enqobj[27] == null ? "" : enqobj[27].toString().trim());
                warrantyForm.setCusTehsil(enqobj[26] == null ? "" : enqobj[26].toString().trim());
                warrantyForm.setCusDistrict(enqobj[27] == null ? "" : enqobj[27].toString().trim());
                warrantyForm.setCusState(enqobj[28] == null ? "" : enqobj[28].toString().trim());
                warrantyForm.setCusPincode(enqobj[29] == null ? "" : enqobj[29].toString().trim());
                warrantyForm.setDealerLocation(enqobj[30] == null ? "" : enqobj[30].toString().trim());
                warrantyForm.setDealerAddress(enqobj[31] == null ? "" : enqobj[31].toString().trim());
                warrantyForm.setDealerDistName(enqobj[32] == null ? "" : enqobj[32].toString().trim());
                warrantyForm.setDealerStateName(enqobj[33] == null ? "" : enqobj[33].toString().trim());
                warrantyForm.setDealerContactNo(enqobj[34] == null ? "" : enqobj[34].toString().trim());

                warrantyForm.setSapCreditNo(enqobj[35] == null ? "-" : enqobj[35].toString().trim());
                warrantyForm.setSapCreditDate(enqobj[36] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[36].toString().trim())));
                warrantyForm.setSapCreditAmount(enqobj[37] == null ? "-" : enqobj[37].toString().trim());
                warrantyForm.setClaimStatus(enqobj[38] == null ? "-" : enqobj[38].toString().trim());
                warrantyForm.setSapWarrantyClaimNo(enqobj[39] == null ? "-" : enqobj[39].toString().trim());

            }

            hql1 = "select ins.hmr,ins.insDate,pdi.pDIDate from  InstallationDetails ins,PdiDetails pdi "
                    + " where pdi.vinNo=ins.vinNo and pdi.vinNo='" + vinNo + "'  ";
            Query query1 = session.createQuery(hql1);
            Iterator it1 = query1.list().iterator();
            while (it1.hasNext()) {
                Object enqobj[] = (Object[]) it1.next();
                warrantyForm.setHmr(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                if (enqobj[1] != null) {
                    warrantyForm.setInsDate(outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
                }
                if (enqobj[2] != null) {
                    warrantyForm.setPdiDate(outputFormat.format(inputFormat.parse(enqobj[2].toString().trim())));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }

    }

    public ArrayList<WarrantyForm> getPartDetail(WarrantyForm wf, String warClaimNo) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();

        String hql = "";
        String hql1 = "";
        try {

            hql = "select wcd.cmpNo,(select compDesc from Complaintcodemaster where compCode=jcd.defectCode)as compDesc, "
                    + " jcd.defectCode, wcd.causalOrConseq,wcd.partNo,wcd.partDesc,wcd.qty,wcd.unitPrice ,"
                    + " wcd.vendorCode,wcd.claimAmount ,wcd.qtyApproved,wcd.approvedAmount,"
                    + " (select rejectionDesc from SAPRejectionCodeMaster where rejectionCode=wcd.rejectionCode)as rejectionDesc,wcd.rejectionRemarks ,"
                    + " wcd.packingNo,wcd.boxNo,wcd.qtyReceived,wcd.dispatchedQty,wcd.dispatchStatus,wcd.hsnCode,wcd.DealerDisc,"
                    + " wcd.claimTaxableValue,wcd.claimCGSTRate,wcd.claimCGSTAmt,wcd.claimSGSTRate,wcd.claimSGSTAmt,wcd.claimUGSTRate,wcd.claimUGSTAmt,wcd.claimIGSTRate,wcd.claimIGSTAmt,"
                    + " wcd.approvedTaxableValue,wcd.approvedCGSTRate,wcd.approvedCGSTAmt,wcd.approvedSGSTRate,wcd.approvedSGSTAmt,wcd.approvedUGSTRate,wcd.approvedUGSTAmt,wcd.approvedIGSTRate,wcd.approvedIGSTAmt"
                    + " from Warrantyclaimdetails wcd,Jobcomplaintdetails jcd "
                    + " where  wcd.cmpNo=jcd.cmpNo and wcd.warrantyClaimNo='" + warClaimNo + "'";

            Query query = session.createQuery(hql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setCmpNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm.setCompDesc(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm.setDefectCode(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                warrantyForm.setCausalOrConseq(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                warrantyForm.setPartNo(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                warrantyForm.setPartDesc(enqobj[5] == null ? "" : enqobj[5].toString().trim().toUpperCase());
                double ttt = enqobj[6] == null ? 0.0 :Double.parseDouble(enqobj[6].toString().trim());
                if (ttt == Math.ceil(ttt)) {
                    warrantyForm.setQty("" + ((int) ttt));
                } else {
                    warrantyForm.setQty(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                }
                //warrantyForm.setQty(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                warrantyForm.setUnitPrice(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                warrantyForm.setVenderCode(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                warrantyForm.setClaimValue(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                double aq = enqobj[10] == null ? 0.0 :Double.parseDouble(enqobj[10].toString().trim());
                if (aq == Math.ceil(aq)) {
                    warrantyForm.setApproveQty("" + ((int) aq));
                } else {
                    warrantyForm.setApproveQty(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                }
               // warrantyForm.setApproveQty(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                warrantyForm.setApproveAmm(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                warrantyForm.setRejectionDesc(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                warrantyForm.setRemark(enqobj[13] == null ? "" : enqobj[13].toString().trim());

                warrantyForm.setPackingNo(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                warrantyForm.setBoxNo(enqobj[15] == null ? "" : enqobj[15].toString().trim());
                double t = enqobj[16] == null ? 0.0 :Double.parseDouble(enqobj[16].toString().trim());
                if (t== Math.ceil(t)) {
                    warrantyForm.setReceivedQty("" + ((int) t));
                } else {
                    warrantyForm.setReceivedQty(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                }
                // warrantyForm.setReceivedQty(enqobj[16] == null ? "" : enqobj[16].toString().trim());
                double tt = enqobj[17] == null ? 0.0 :Double.parseDouble(enqobj[17].toString().trim());
                if (tt == Math.ceil(tt)) {
                    warrantyForm.setDispatchQty("" + ((int) tt));
                } else {
                    warrantyForm.setDispatchQty(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
                }
                //  warrantyForm.setDispatchQty(enqobj[17] == null ? "" : enqobj[17].toString().trim());
                warrantyForm.setDispatchStatus(enqobj[18] == null ? "" : enqobj[18].toString().trim());
                warrantyForm.setHsnCode(enqobj[19] == null ? "" : enqobj[19].toString().trim());
                warrantyForm.setDealerDiscount(enqobj[20] == null ? "" : enqobj[20].toString().trim());
                if (wf.getClaimStatus().equalsIgnoreCase("PENDING") || wf.getClaimStatus().equalsIgnoreCase("PACKED") || wf.getClaimStatus().equalsIgnoreCase("RECEIVED") || wf.getClaimStatus().equalsIgnoreCase("DISPATCHED")) {
                    warrantyForm.setTaxableValue(enqobj[21] == null ? "" : enqobj[21].toString().trim());
                    warrantyForm.setCgstRate(enqobj[22] == null ? "" : enqobj[22].toString().trim());
                    warrantyForm.setCgstAmt(enqobj[23] == null ? "" : enqobj[23].toString().trim());
                    warrantyForm.setSgstRate(enqobj[24] == null ? "" : enqobj[24].toString().trim());
                    warrantyForm.setSgstAmt(enqobj[25] == null ? "" : enqobj[25].toString().trim());
                    warrantyForm.setUgstRate(enqobj[26] == null ? "" : enqobj[26].toString().trim());
                    warrantyForm.setUgstAmt(enqobj[27] == null ? "" : enqobj[27].toString().trim());
                    warrantyForm.setIgstRate(enqobj[28] == null ? "" : enqobj[28].toString().trim());
                    warrantyForm.setIgstAmt(enqobj[29] == null ? "" : enqobj[29].toString().trim());
                } else {
                    warrantyForm.setTaxableValue(enqobj[30] == null ? "" : enqobj[30].toString().trim());
                    warrantyForm.setCgstRate(enqobj[31] == null ? "" : enqobj[31].toString().trim());
                    warrantyForm.setCgstAmt(enqobj[32] == null ? "" : enqobj[32].toString().trim());
                    warrantyForm.setSgstRate(enqobj[33] == null ? "" : enqobj[33].toString().trim());
                    warrantyForm.setSgstAmt(enqobj[34] == null ? "" : enqobj[34].toString().trim());
                    warrantyForm.setUgstRate(enqobj[35] == null ? "" : enqobj[35].toString().trim());
                    warrantyForm.setUgstAmt(enqobj[36] == null ? "" : enqobj[36].toString().trim());
                    warrantyForm.setIgstRate(enqobj[37] == null ? "" : enqobj[37].toString().trim());
                    warrantyForm.setIgstAmt(enqobj[38] == null ? "" : enqobj[38].toString().trim());
                }
                dataList.add(warrantyForm);
            }

            hql1 = "select wc.totalApprovedAmount ,wc.approvedLessAmount,wc.approvedCSTVAT,wc.approvedHandlingCharges,wc.approvedAmount,wc.claimStatus,"
                    + " wc.totalAmount,wc.lessAmount,wc.cstvat,wc.handlingCharges,wc.totalClaimAmount,wc.gstOnHandling,wc.insuranceCharges,wc.gstOnInsurance, "
                    + " wc.approvedgstOnHandling,wc.approvedinsuranceCharges,wc.approvedgstOnInsurance"
                    + " from Warrantyclaim wc where wc.warrantyClaimNo='" + warClaimNo + "'";

            Query query1 = session.createQuery(hql1);
            Iterator it1 = query1.list().iterator();
            while (it1.hasNext()) {
                Object enqobj[] = (Object[]) it1.next();
                wf.setClaimStatus(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                if (enqobj[5].toString().equalsIgnoreCase("PENDING") || enqobj[5].toString().equalsIgnoreCase("PACKED") || enqobj[5].toString().equalsIgnoreCase("RECEIVED") || enqobj[5].toString().equalsIgnoreCase("DISPATCHED")) {
                    wf.setTotalApproveAmmount(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                    wf.setApplessAmm(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                    wf.setAppCstVat(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                    wf.setAppHanCharge(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                    wf.setApproveAmm(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                    wf.setGstOnHandling(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                    wf.setInsuranceCharges(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                    wf.setGstOnInsurance(enqobj[13] == null ? "" : enqobj[13].toString().trim());
                    wf.setFlagTemp("Claimed");


                } else {
                    wf.setTotalApproveAmmount(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                    wf.setApplessAmm(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                    wf.setAppCstVat(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                    wf.setAppHanCharge(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                    wf.setApproveAmm(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                    wf.setGstOnHandling(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                    wf.setInsuranceCharges(enqobj[15] == null ? "" : enqobj[15].toString().trim());
                    wf.setGstOnInsurance(enqobj[16] == null ? "" : enqobj[16].toString().trim());
                    wf.setFlagTemp("Approved");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<WarrantyForm> getServiceHistoryList(WarrantyForm wForm) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HashMap<String, String> hm = new HashMap<String, String>();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        String hql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //hql = "select jobTypeID,jobTypeDesc from Jobtypemaster where freeService='Y' order by SeqNo,jobTypeDesc";
            Query query = session.createSQLQuery("{call SP_getServiceHistoryList (:vinNo) }");//,:jobCardNo
            query.setParameter("vinNo", wForm.getVinNo());
            //query.setParameter("jobCardNo", wForm.getJobCardNo());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {

                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setJobTypeId(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm.setJobTypeDesc(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm.setHmr(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                warrantyForm.setJobCardDate(enqobj[3] == null ? "" : df.format(sdf.parse(enqobj[3].toString().trim())));

                dataList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public void getClaimAndComplaintList(WarrantyForm warrantyForm, String jobCardNo) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> partdataList = null;
        WarrantyForm wf = null;
        Map<WarrantyForm, ArrayList<WarrantyForm>> treeMap = new TreeMap();
        String cmpNo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        String hql = "";
        String partHql = "";
        try {

            hql = "select compd.custVerbatim,compd.foremanObsv ,compd.appCode,app.appDesc,"
                    + " compd.aggCode,agg.aggDesc, compd.subassemblyCode,sasm.subAssemblyDesc, "
                    + " compd.defectCode,cc.compDesc, compd.causeCode ,compd.subAggCode, sagg.subAggDesc, "
                    + " compd.cmpNo " //cas.causalDesc ,
                    + " , wc.handlingCharges,wc.gstOnHandling,wc.insuranceCharges,wc.gstOnInsurance,wc.totalAmount "
                    + " from Jobcomplaintdetails compd, Jobcarddetails jc,Warrantyclaim wc  "
                    + " , Aggregatemaster agg,ApplicationMaster app,Subassemblymaster sasm,"
                    + " Subaggregatemaster sagg,Complaintcodemaster cc,JobcardSparesActualcharges jac ,Billmaster bm " //Causalcodemaster cas ,
                    + " where compd.jobCardNo=jc.jobCardNo  "
                    + " and wc.jobCardNo=compd.jobCardNo "
                    + " and agg.aggCode=compd.aggCode "
                    + " and app.appCode=compd.appCode "
                    + " and  sasm.subAssemblyCode=compd.subassemblyCode "
                    + " and sagg.subAggCode=compd.subAggCode  "
                    + " and cc.compCode=compd.defectCode  and jac.jobCardNo=jc.jobCardNo and  bm.billID=jac.billID and bm.warrantyType='Yes' and compd.jobCardNo=jac.jobCardNo and compd.cmpNo=jac.cmpNo  " //jac.billID='2'
                    + "  and compd.jobCardNo='" + jobCardNo + "' order by compd.cmpNo desc"; //and cas.causalCode=compd.causeCode

            Query query = session.createQuery(hql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {

                Object enqobj[] = (Object[]) it.next();
                wf = new WarrantyForm();
                wf.setCusVerbatim(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                wf.setFormanObser(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                wf.setAppCode(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                wf.setAppDesc(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                wf.setAggCode(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                wf.setAggDisc(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                wf.setSubAssemCode(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                wf.setSubAssemDesc(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                wf.setComplaintCode(enqobj[8] == null ? "" : enqobj[8].toString().trim());
                wf.setComplaintDesc(enqobj[9] == null ? "" : enqobj[9].toString().trim());
                wf.setCasulCode(enqobj[10] == null ? "" : enqobj[10].toString().trim());
                // wf.setCasulDesc(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                wf.setSubAggCode(enqobj[11] == null ? "" : enqobj[11].toString().trim());
                wf.setSubAggDesc(enqobj[12] == null ? "" : enqobj[12].toString().trim());
                wf.setCmpNo(enqobj[13] == null ? "" : enqobj[13].toString().trim());
                cmpNo = enqobj[13] == null ? "" : enqobj[13].toString().trim();
                wf.setAppHanCharge(enqobj[14] == null ? "" : enqobj[14].toString().trim());
                wf.setGstOnHandling(enqobj[15] == null ? "0" : enqobj[15].toString().trim());
                wf.setInsuranceCharges(enqobj[16] == null ? "" : enqobj[16].toString().trim());
                //if (df1.format(sdf.parse(warrantyForm.getClaimDate())).compareTo(df1.format(df.parse(PageTemplate.warrantyClaimDateDefault))) < 0) {
                   // wf.setGstOnInsurance("0.0");
                //}else{
                   wf.setGstOnInsurance(enqobj[17] == null ? "0" : enqobj[17].toString().trim());
                //}

                wf.setApproveAmm(enqobj[18] == null ? "0" : enqobj[18].toString().trim());


                partHql = "select wcd.causalOrConseq,wcd.partNo,wcd.partDesc,wcd.qty,wcd.unitPrice,wcd.claimAmount ,wcd.claimSpareID, "
                        + " wcd.packingNo,wcd.dispatchStatus,wcd.dispatchedQty,wcd.qtyReceived, "
                        + " wcd.hsnCode,wcd.DealerDisc, wcd.claimTaxableValue,wcd.claimCGSTRate,wcd.claimCGSTAmt,wcd.claimSGSTRate,wcd.claimSGSTAmt,wcd.claimUGSTRate,wcd.claimUGSTAmt,wcd.claimIGSTRate,wcd.claimIGSTAmt"
                        + " from Warrantyclaimdetails  wcd ,Jobcomplaintdetails jcd  "
                        + " where wcd.cmpNo=jcd.cmpNo  and wcd.cmpNo='" + cmpNo + "' ";   //and wcd.dispatchStatus='Yes'
                partdataList = new ArrayList<WarrantyForm>();
                Query queryPart = session.createQuery(partHql);
                Iterator itPart = queryPart.list().iterator();
                while (itPart.hasNext()) {

                    Object partobj[] = (Object[]) itPart.next();
                    WarrantyForm wForm = new WarrantyForm();
                    wForm.setCausalOrConseq(partobj[0] == null ? "" : partobj[0].toString().trim());
                    wForm.setPartNo(partobj[1] == null ? "" : partobj[1].toString().trim());
                    wForm.setPartDesc(partobj[2] == null ? "" : partobj[2].toString().trim().toUpperCase());
                   // wForm.setQty(partobj[3] == null ? "" : partobj[3].toString().trim());

                    double t = Double.parseDouble(partobj[3].toString().trim());
                    if (t == Math.ceil(t)) { wForm.setQty("" + ((int) t));
                    } else {   wForm.setQty(partobj[3] == null ? "" : partobj[3].toString().trim());   }

                    wForm.setUnitPrice(partobj[4] == null ? "" : partobj[4].toString().trim());
                    wForm.setAmount((partobj[4] == null || partobj[3] == null) ? "0" : "" + t * Double.parseDouble(partobj[4].toString().trim()));
                    wForm.setClaimValue(partobj[5] == null ? "" : partobj[5].toString().trim());
                    wForm.setJobSpareID(partobj[6] == null ? "" : partobj[6].toString().trim());
                    wForm.setPackingNo(partobj[7] == null ? "- -" : partobj[7].toString().trim());
                    wForm.setDispatchStatus(partobj[8] == null ? "" : partobj[8].toString().trim());
                  // wForm.setDispatchQty(partobj[9] == null ? "0" : partobj[9].toString().trim());
                    if(partobj[8].toString().trim().equalsIgnoreCase("Yes")){
                     double tt = Double.parseDouble(partobj[9].toString().trim());
                    if (tt == Math.ceil(tt)) { wForm.setDispatchQty("" + ((int) tt));
                    } else {   wForm.setDispatchQty(partobj[9] == null ? "" : partobj[9].toString().trim());   }
                    //wForm.setReceivedQty(partobj[10] == null ? "0" : partobj[10].toString().trim());
                     double ttt = Double.parseDouble(partobj[10].toString().trim());
                    if (ttt == Math.ceil(ttt)) { wForm.setReceivedQty("" + ((int) ttt));
                    } else {   wForm.setReceivedQty(partobj[10] == null ? "" : partobj[10].toString().trim());   }
                    }
                    else{
                        wForm.setReceivedQty(partobj[10] == null ? "0" : partobj[10].toString().trim());
                        wForm.setDispatchQty(partobj[10] == null ? "0" : partobj[10].toString().trim());
                    }
                    wForm.setHsnCode(partobj[11] == null ? "0" : partobj[11].toString().trim());
                    wForm.setDealerDiscount(partobj[12] == null ? "0" : partobj[12].toString().trim());
                    wForm.setTaxableValue(partobj[13] == null ? "0" : partobj[13].toString().trim());
                    wForm.setCgstRate(partobj[14] == null ? "0" : partobj[14].toString().trim());
                    wForm.setCgstAmt(partobj[15] == null ? "0" : partobj[15].toString().trim());
                    wForm.setSgstRate(partobj[16] == null ? "0" : partobj[16].toString().trim());
                    wForm.setSgstAmt(partobj[17] == null ? "0" : partobj[17].toString().trim());
                    wForm.setUgstRate(partobj[18] == null ? "0" : partobj[18].toString().trim());
                    wForm.setUgstAmt(partobj[19] == null ? "0" : partobj[19].toString().trim());
                    wForm.setIgstRate(partobj[20] == null ? "0" : partobj[20].toString().trim());
                    wForm.setIgstAmt(partobj[21] == null ? "0" : partobj[21].toString().trim());
                    partdataList.add(wForm);

                }
                treeMap.put(wf, partdataList);
            }
            warrantyForm.setDataMap(treeMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }

    }

    public String saveApproveWarranty(WarrantyForm wf, String userId,String taxType) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decf = new DecimalFormat("#.00");
        String result = "fail";
        double totalQty = 0;
        double qtyApproved = 0;
        double unitPrice = 0, amount = 0, dealeDiscPer = 0, dealeDiscVal = 0, taxableVal = 0;
        double cgstRate = 0, cgstAmt = 0, sgstRate = 0, sgstAmt = 0, ugstRate = 0, ugstAmt = 0, igstRate = 0, igstAmt = 0;
        double approveAmmount = 0, grandTotal = 0, approveAmmountAfterDiscount = 0,grandTotalGst=0;
        double handlingCharge = 0, gstOnHandlingCharge = 0, insuranceCharge = 0, gstOnInsuranceCharge = 0;
        double totalrejectedQty = 0;
        double totalapproveQty = 0;
        try {
            session.beginTransaction();
            
            System.out.println("printing form data");
            System.out.println(wf);

            if (wf.getCmpNoArr() != null) {

              //  for (int j = 0; j < wf.getCmpNoArr().length; j++) {

                    if (wf.getJobSpareIDArr() != null) {
                        for (int i = 0; i < wf.getJobSpareIDArr().length; i++) {
                            Warrantyclaimdetails wcdetail = (Warrantyclaimdetails) session.load(Warrantyclaimdetails.class, wf.getJobSpareIDArr()[i]);
                            qtyApproved = Double.parseDouble(wf.getQtyApprovedArr()[i]);
                                unitPrice = Double.parseDouble(wf.getUnitPriceArr()[i]);
                                amount = qtyApproved * unitPrice;
                                dealeDiscPer = Double.parseDouble(wf.getDealerDiscountArr()[i]);
                                dealeDiscVal = amount * dealeDiscPer / 100;
                                taxableVal =  Double.parseDouble(decf.format(amount - dealeDiscVal));

                                cgstRate = Double.parseDouble(wf.getCgstRateArr()[i]);
                                cgstAmt = Double.parseDouble(decf.format(taxableVal * cgstRate / 100));
                                sgstRate = Double.parseDouble(wf.getSgstRateArr()[i]);
                                sgstAmt = Double.parseDouble(decf.format(taxableVal * sgstRate / 100));
                                ugstRate = Double.parseDouble(wf.getUgstRateArr()[i]);
                                ugstAmt = Double.parseDouble(decf.format(taxableVal * ugstRate / 100));
                                igstRate = Double.parseDouble(wf.getIgstRateArr()[i]);
                                igstAmt = Double.parseDouble(decf.format(taxableVal * igstRate / 100));
                                
                                System.out.println("Print after line no 1711");
                                
                                System.out.println("CGST Rate: " + cgstRate);
                                System.out.println("CGST Amount: " + cgstAmt);

                                System.out.println("SGST Rate: " + sgstRate);
                                System.out.println("SGST Amount: " + sgstAmt);

                                System.out.println("UGST Rate: " + ugstRate);
                                System.out.println("UGST Amount: " + ugstAmt);

                                System.out.println("IGST Rate: " + igstRate);
                                System.out.println("IGST Amount: " + igstAmt);


                                approveAmmount = Double.parseDouble(decf.format(taxableVal + cgstAmt + sgstAmt + ugstAmt + igstAmt));
                                grandTotal += taxableVal;
                                grandTotalGst += approveAmmount;

                                wcdetail.setQtyApproved(qtyApproved);
                                wcdetail.setQtyRejected(Double.parseDouble(wf.getQtyRejectedArr()[i]));
                                wcdetail.setApprovedAmount(approveAmmount);//Double.parseDouble(wf.getApproveAmmountArr()[i])
                                wcdetail.setApprovalStatus("Y");
                                wcdetail.setRejectionCode(wf.getRejectionCode()[i]);
                                wcdetail.setRejectionRemarks((wf.getRemarks()[i]== null || wf.getRemarks()[i].equals("")) ? "" :wf.getRemarks()[i]);
                                wcdetail.setScrapValue((wf.getScrapValue()[i]== null || wf.getScrapValue()[i].equals("")) ? "" :wf.getScrapValue()[i]);
                                wcdetail.setVendorCodeAdmin((wf.getVendorCodeAdmin()[i] == null || wf.getVendorCodeAdmin()[i].equals("")) ? "" :wf.getVendorCodeAdmin()[i]);


                                wcdetail.setHsnCode(wf.getHsnNoArr()[i]);
                                wcdetail.setDealerDisc(new BigDecimal(wf.getDealerDiscountArr()[i]));
                                wcdetail.setApprovedTaxableValue(new BigDecimal(String.valueOf(taxableVal)));//wf.getTaxableValueArr()[i]
                                wcdetail.setApprovedCGSTRate(new BigDecimal(String.valueOf(cgstRate)));
                                wcdetail.setApprovedCGSTAmt(new BigDecimal(String.valueOf(cgstAmt)));
                                wcdetail.setApprovedSGSTRate(new BigDecimal(String.valueOf(sgstRate)));
                                wcdetail.setApprovedSGSTAmt(new BigDecimal(String.valueOf(sgstAmt)));
                                wcdetail.setApprovedUGSTRate(new BigDecimal(String.valueOf(ugstRate)));
                                wcdetail.setApprovedUGSTAmt(new BigDecimal(String.valueOf(ugstAmt)));
                                wcdetail.setApprovedIGSTRate(new BigDecimal(String.valueOf(igstRate)));
                                wcdetail.setApprovedIGSTAmt(new BigDecimal(String.valueOf(igstAmt)));
                                wcdetail.setCausalOrConseq(wf.getCausalOrConseqArr()[i]);
                                String partCodeFailed = (wf.getPartCodeFailed()[i] == null || wf.getPartCodeFailed()[i].equals("")) ? "" :wf.getPartCodeFailed()[i];
                                wcdetail.setPartNoFailed(partCodeFailed);
                                
                                boolean isValidTax = false;
                                BigDecimal cgstRate1 = new BigDecimal(wf.getCgstRateArr()[i]);
            					BigDecimal cgstAmt1 = new BigDecimal(wf.getCgstAmtArr()[i]);

            					BigDecimal sgstRate1 = new BigDecimal(wf.getSgstRateArr()[i]);
            					BigDecimal sgstAmt1 = new BigDecimal(wf.getSgstAmtArr()[i]);

            					BigDecimal ugstRate1 = new BigDecimal(wf.getUgstRateArr()[i]);
            					BigDecimal ugstAmt1 = new BigDecimal(wf.getUgstAmtArr()[i]);

            					BigDecimal igstRate1 = new BigDecimal(wf.getIgstRateArr()[i]);
            					BigDecimal igstAmt1 = new BigDecimal(wf.getIgstAmtArr()[i]);
            					
            					System.out.println("Print after line no 1768");
            					
            					System.out.println("BigDecimal CGST Rate: " + cgstRate1);
            					System.out.println("BigDecimal CGST Amount: " + cgstAmt1);

            					System.out.println("BigDecimal SGST Rate: " + sgstRate1);
            					System.out.println("BigDecimal SGST Amount: " + sgstAmt1);

            					System.out.println("BigDecimal UGST Rate: " + ugstRate1);
            					System.out.println("BigDecimal UGST Amount: " + ugstAmt1);

            					System.out.println("BigDecimal IGST Rate: " + igstRate1);
            					System.out.println("BigDecimal IGST Amount: " + igstAmt1);
            					
            					System.out.println("taxType "+taxType);


                                
                  //              session.saveOrUpdate(wcdetail);
            					
            					BigDecimal qtyApprovedBigDecimal = BigDecimal.valueOf(qtyApproved);
            					if (qtyApprovedBigDecimal.compareTo(BigDecimal.ZERO) > 0) {
            					    
            					
            					
                                if ("SGST".equalsIgnoreCase(taxType)) {
            						isValidTax = (sgstRate1.compareTo(BigDecimal.ZERO) > 0 && sgstAmt1.compareTo(BigDecimal.ZERO) > 0)
            								&& (cgstRate1.compareTo(BigDecimal.ZERO) > 0 && cgstAmt1.compareTo(BigDecimal.ZERO) > 0)
            								&& (igstRate1.compareTo(BigDecimal.ZERO) ==  0 && igstAmt1.compareTo(BigDecimal.ZERO) == 0);
            					} else if ("IGST".equalsIgnoreCase(taxType)) {
            						isValidTax = (igstRate1.compareTo(BigDecimal.ZERO) > 0
            								&& igstAmt1.compareTo(BigDecimal.ZERO) > 0) &&
            								(sgstRate1.compareTo(BigDecimal.ZERO) == 0 && sgstAmt1.compareTo(BigDecimal.ZERO) == 0)
            								&& (cgstRate1.compareTo(BigDecimal.ZERO) == 0 && cgstAmt1.compareTo(BigDecimal.ZERO) == 0);
            					}
                                
                                System.out.println("isValidTax "+isValidTax);
                                
                                System.out.println(wcdetail);

            					if (isValidTax) {
            						System.out.println(wcdetail);
            						session.saveOrUpdate(wcdetail);
            					} else {
            						wf.setResult("FAILURE");
            						session.getTransaction().rollback();
            						System.out.println("GST SLAB is not Maintained. Kindly contact administrator");
            						return "failure";
            					}
            					
            					}
            					session.saveOrUpdate(wcdetail);

                            }

                    }
               // }
                    
                    System.out.println("grandTotal "+grandTotal);
                    System.out.println("handlingCharge "+wf.getDiscount()[0]);
                    

                    /*
                handlingCharge = Double.parseDouble(decf.format(grandTotal * Double.parseDouble(wf.getDiscount()[0]) / 100));
                gstOnHandlingCharge = Double.parseDouble(decf.format(handlingCharge * Double.parseDouble(wf.getDiscount()[1]) / 100));
                */
                    
                    handlingCharge = Double.parseDouble(wf.getLessAmmountArr()[0]);
                    gstOnHandlingCharge = Double.parseDouble((wf.getLessAmmountArr()[1] == null || wf.getLessAmmountArr()[1].equals("")) ? "0" : wf.getLessAmmountArr()[1]);
                    
                    
                    

                insuranceCharge = Double.parseDouble(wf.getLessAmmountArr()[2]);
                gstOnInsuranceCharge = Double.parseDouble((wf.getLessAmmountArr()[3] == null || wf.getLessAmmountArr()[3].equals("")) ? "0" : wf.getLessAmmountArr()[3]);
                approveAmmountAfterDiscount = Double.parseDouble(decf.format(grandTotalGst + handlingCharge + gstOnHandlingCharge + insuranceCharge + gstOnInsuranceCharge));
                
                
                System.out.println("print at 1864");
                System.out.println("handlingCharge "+handlingCharge);
                System.out.println("gstOnHandlingCharge "+gstOnHandlingCharge);
                System.out.println("insuranceCharge "+insuranceCharge);
                System.out.println("gstOnInsuranceCharge "+gstOnInsuranceCharge);
                

                Warrantyclaim wclaim = (Warrantyclaim) session.load(Warrantyclaim.class, wf.getWarrantyClaimNo());
                wclaim.setTotalApprovedAmount(new BigDecimal(String.valueOf(approveAmmountAfterDiscount)));//new BigDecimal(wf.getSumTotalApproveAmmount())
//                wclaim.setApprovedLessAmount(new BigDecimal(wf.getLessAmmountArr()[0]));
//                wclaim.setApprovedCSTVAT(new BigDecimal(wf.getLessAmmountArr()[1]));
                wclaim.setApprovedHandlingCharges(new BigDecimal(String.valueOf(handlingCharge)));
                wclaim.setApprovedgstOnHandling(new BigDecimal(String.valueOf(gstOnHandlingCharge)));
               wclaim.setApprovedinsuranceCharges(new BigDecimal(String.valueOf(insuranceCharge)));
                wclaim.setApprovedgstOnInsurance(new BigDecimal(String.valueOf(gstOnInsuranceCharge)));
                wclaim.setApprovedAmount(new BigDecimal(String.valueOf(grandTotalGst)));//wf.getApproveAmmountAfterDiscount()
                wclaim.setApprovedOn(new Date(new java.util.Date().getTime()));
                wclaim.setApprovedBy(userId);
                for (int j = 0; j < wf.getCmpNoArr().length; j++) {
                    if (wf.getJobSpareIDArr() != null) {
                        for (int i = 0; i < wf.getJobSpareIDArr().length; i++) {
                            totalQty += Double.parseDouble(wf.getQtyArr()[i]);
                            totalrejectedQty += Double.parseDouble(wf.getQtyRejectedArr()[i]);
                            totalapproveQty += Double.parseDouble(wf.getQtyApprovedArr()[i]);
                        }
                    }
                }
                if (totalrejectedQty > 0 && totalrejectedQty < totalQty) {
                    wclaim.setClaimStatus("PARTIAL APPROVED");
                    wclaim.setSapSyncStatus("PENDING");
                }
                if (totalrejectedQty > 0 && totalrejectedQty == totalQty) {
                    wclaim.setClaimStatus("REJECTED");
                    wclaim.setSapSyncStatus("CREDITED");
                }
                if (totalrejectedQty == 0 && totalapproveQty == totalQty) {
                    wclaim.setClaimStatus("APPROVED");
                    wclaim.setSapSyncStatus("PENDING");
                }
                
                
                boolean isValidHandlingAndLabourCharges = false;
                /*
                BigDecimal handlingCharges = wclaim.getHandlingCharges();
                BigDecimal gstOnHandling = wclaim.getGstOnHandling();
                BigDecimal insuranceCharges = wclaim.getInsuranceCharges();
                BigDecimal gstOnInsurance = wclaim.getGstOnInsurance();
                
                
                System.out.println("wclaim status "+wclaim.getClaimStatus());
                
                System.out.println("print before set");
                
                System.out.println("handlingCharges "+String.valueOf(handlingCharge));
                System.out.println("gstOnHandling "+String.valueOf(gstOnHandling));
                System.out.println("insuranceCharges "+String.valueOf(insuranceCharges));
                System.out.println("gstOnInsurance "+String.valueOf(gstOnInsurance));
                
                System.out.println("print before set with big decimal");
                
               
                System.out.println("handlingCharges "+new BigDecimal(String.valueOf(handlingCharge)));
                System.out.println("gstOnHandling "+new BigDecimal(String.valueOf(gstOnHandling)));
                System.out.println("insuranceCharges "+new BigDecimal(String.valueOf(insuranceCharges)));
                System.out.println("gstOnInsurance "+new BigDecimal(String.valueOf(gstOnInsurance)));
                */
                wclaim.setApprovedHandlingCharges(new BigDecimal(String.valueOf(handlingCharge)));
                wclaim.setApprovedgstOnHandling(new BigDecimal(String.valueOf(gstOnHandlingCharge)));
                wclaim.setApprovedinsuranceCharges(new BigDecimal(String.valueOf(insuranceCharge)));
                wclaim.setApprovedgstOnInsurance(new BigDecimal(String.valueOf(gstOnInsuranceCharge)));
                
                System.out.println(wclaim);
                System.out.println("wclaim.getApprovedHandlingCharges() "+wclaim.getApprovedHandlingCharges());
                
                System.out.println("wclaim.getApprovedgstOnHandling() "+wclaim.getApprovedgstOnHandling());
                
                
                System.out.println("wclaim.getApprovedinsuranceCharges() "+wclaim.getApprovedinsuranceCharges());
                System.out.println("wclaim.getApprovedgstOnInsurance() "+wclaim.getApprovedgstOnInsurance());
                
                session.saveOrUpdate(wclaim);
                
                if(!wclaim.getClaimStatus().equals("REJECTED")) {
                isValidHandlingAndLabourCharges =  (handlingCharge > 0 && gstOnHandlingCharge> 0)
                        && (insuranceCharge > 0 && gstOnInsuranceCharge > 0);
                
                        if (isValidHandlingAndLabourCharges) {
            				System.out.println("Handling and Labour Charges are valid");
            			} else {
            				session.getTransaction().rollback();
            				System.out.println("Handling and Labour Charges is not maintained. Kindly contact administrator");
            				wf.setResult("FAILURE");
            				return "failure";
            			}
                }

               session.getTransaction().commit();
                
            //    session.getTransaction().rollback();
                result = "success";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return result;
    }
    
    public String getDealerCode(String jcNo) {
        String deCode = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query jcQuery = session.createQuery("select dealerCode from Jobcarddetails where jobCardNo=:jcNo");
            jcQuery.setParameter("jcNo", jcNo);
            List jcList = (List) jcQuery.list();
            deCode = jcList.get(0).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }

        return deCode;
    }

    public String getDealerCodeByWcNo(String wcNo) {
        String deCode = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query jcQuery = session.createQuery("select dealerCode from Warrantyclaim where warrantyClaimNo=:wcNo");
            jcQuery.setParameter("wcNo", wcNo);
            List jcList = (List) jcQuery.list();
            deCode = jcList.get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deCode;
    }

    public ArrayList<WarrantyForm> getPendingDispatchList(WarrantyForm warrantyForm, Vector userFunctionalities) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
       // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        Integer cnt = Integer.parseInt(obj.getHesConstantValue(13));

        String hql = "";
        try {

        	hql = "Select wc.warrantyClaimNo,wc.jobCardNo,wc.tmsRefNo,wc.vinno,wc.claimStatus,wc.claimDate,wc.vinno "
                    + " from Warrantyclaim wc where "
                    + " wc.claimStatus='PENDING' and wc.dealerCode='" + warrantyForm.getDealer_code() + "' " +
                    " and wc.claimDate >= getdate()-"+cnt+" order by wc.warrantyClaimNo desc";
        	
        	Query query = session.createQuery(hql);
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm warrantyForm1 = new WarrantyForm();
                warrantyForm1.setWarrantyClaimNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                warrantyForm1.setJobCardNo(enqobj[1] == null ? "" : enqobj[1].toString().trim());
                warrantyForm1.setTmsRefNo(enqobj[2] == null ? "NA" : enqobj[2].toString().trim());
                warrantyForm1.setVinNo(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                warrantyForm1.setClaimStatus(enqobj[4] == null ? "" : enqobj[4].toString().trim());
                String claimDateString = enqobj[5] == null ? "" : enqobj[5].toString().trim();
                if (!claimDateString.equals("")) {
                    Date wdate = inputFormat.parse(claimDateString);
                    String wd = outputFormat.format(wdate);
                    warrantyForm1.setClaimDate(wd);
                }
                warrantyForm1.setVinNo(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                dataList.add(warrantyForm1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<WarrantyForm> getPendingDispatch(WarrantyForm wf, String userId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
     //   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
     //   String result = "fail";
    //    String hql = "";
        String hqlPart = "";

        try {
            session.beginTransaction();

            if (wf.getPackArr() != null) {
                for (int i = 0; i < wf.getPackArr().length; i++) {
                    int j = Integer.parseInt(wf.getPackArr()[i]);
                    hqlPart = " Select  wc.warrantyClaimNo,wc.claimDate,wc.jobCardNo, wcd.partNo,wcd.partDesc,wcd.qty,wcd.partCategory, wc.vinno "
                            + " from Warrantyclaim wc, Warrantyclaimdetails wcd where wc.warrantyClaimNo=wcd.warrantyClaimNo and wcd.dispatchStatus='PENDING' and wc.warrantyClaimNo=:wClaimNo ";

                    Query query = session.createQuery(hqlPart);
                    query.setParameter("wClaimNo", wf.getWarClaimNoArr()[j - 1]);

                    Iterator it = query.list().iterator();
                    while (it.hasNext()) {
                        Object enqobj[] = (Object[]) it.next();
                        WarrantyForm warrantyForm1 = new WarrantyForm();
                        warrantyForm1.setWarrantyClaimNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                        String claimDateString = enqobj[1] == null ? "" : enqobj[1].toString().trim();
                        if (!claimDateString.equals("")) {
                            Date wdate = inputFormat.parse(claimDateString);
                            String wd = outputFormat.format(wdate);
                            warrantyForm1.setClaimDate(wd);
                        }
                        warrantyForm1.setJobCardNo(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                        warrantyForm1.setPartNo(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                        warrantyForm1.setPartDesc(enqobj[4] == null ? "" : enqobj[4].toString().trim().toUpperCase());
                        // warrantyForm1.setQty(enqobj[5] == null ? "" : enqobj[5].toString().trim());

                        double d = Double.parseDouble(enqobj[5].toString().trim());
                        if (d == Math.ceil(d)) {
                            warrantyForm1.setQty("" + ((int) d));
                        } else {
                            warrantyForm1.setQty(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                        }
                        warrantyForm1.setPartCategory(enqobj[6] == null ? "" : enqobj[6].toString().trim());
                        warrantyForm1.setVinNo(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                        dataList.add(warrantyForm1);
                    }
                }
                //System.out.println(" size dataList " +dataList.size());
            }


        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public String getClaimSpareIdByWcNo(String wcNo) {
        String deCode = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query jcQuery = session.createQuery("select dealerCode from Warrantyclaim where warrantyClaimNo=:wcNo");
            jcQuery.setParameter("wcNo", wcNo);
            List jcList = (List) jcQuery.list();
            deCode = jcList.get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return deCode;
    }

    public WarrantyForm savePartDispatch(WarrantyForm wf, String userId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String hql = "";
        String packingNo = "";
        String hqlpart = "";

        try {
            session.beginTransaction();
            ArrayList packedClaimArr = new ArrayList();
            ArrayList unpackedClaimArr = new ArrayList();

            if (wf.getWarClaimNoArr() != null) {
                for (int i = 0; i < wf.getWarClaimNoArr().length; i++) {
                    if (wf.getDispatchReqArr()[i].equalsIgnoreCase("Yes")) {
                        if (!packedClaimArr.contains(wf.getWarClaimNoArr()[i])) {
                            packedClaimArr.add(wf.getWarClaimNoArr()[i]);
                        }
                    } else {
                        if (!unpackedClaimArr.contains(wf.getWarClaimNoArr()[i])) {
                            unpackedClaimArr.add(wf.getWarClaimNoArr()[i]);
                        }
                    }
                }


                if (packedClaimArr.size() > 0) {
                    packingNo = new MethodUtility().getNumber(session, "SWwarrantypackingmaster", wf.getDealer_code(), "PL");
                    wf.setPackingNo(packingNo);
                    SWwarrantypackingmaster swPackMaster = new SWwarrantypackingmaster();
                    swPackMaster.setPackingNo(packingNo);
                    swPackMaster.setPackingDate(df.parse(df.format(new Date())));
                    swPackMaster.setCreatedBy(userId);
                    swPackMaster.setDealerCode(wf.getDealer_code());
                    swPackMaster.setPackingStatus("PACKED");
                    swPackMaster.setCreatedOn(new Date());
                    swPackMaster.setPackingRemarks(wf.getRemark());
                    swPackMaster.setIsServerSync('N');
                    swPackMaster.setStoreNoOfPackages(0);
                    String deliveryChallanNoForWayBill =  new MethodUtility().getDeliveryChallanNoForWayBill(session,"SWwarrantypackingmaster",wf.getDealer_code(),"DC");
                    swPackMaster.setDeliveryChallanForEwayBill(deliveryChallanNoForWayBill);
                    session.save(swPackMaster);
                } else {
                    wf.setPackingNo("notPacked");
                }


                for (int i = 0; i < wf.getWarClaimNoArr().length; i++) {
                    if (wf.getDispatchReqArr()[i].equalsIgnoreCase("Yes")) {
                        hql = "update Warrantyclaimdetails wc set wc.dispatchStatus=:dispatchStatus ,wc.packingNo=:packingNo , wc.dispatchedQty=:dispatchedQty , wc.boxNo=:boxNo where wc.warrantyClaimNo=:warrantyClaimNo and wc.partNo=:partNo ";
                        Query query = session.createQuery(hql);
                        query.setParameter("dispatchStatus", wf.getDispatchReqArr()[i].toUpperCase());
                        query.setParameter("packingNo", packingNo);
                        query.setParameter("dispatchedQty", Double.parseDouble(wf.getDispatchQtyArr()[i]));
                        query.setParameter("boxNo", wf.getBoxNoArr()[i]);
                        query.setParameter("warrantyClaimNo", wf.getWarClaimNoArr()[i]);
                        query.setParameter("partNo", wf.getPartNoArr()[i]);
                        query.executeUpdate();

//                    hqlWC = "update Warrantyclaim wc set wc.claimStatus='PACKED' where wc.warrantyClaimNo=:warrantyClaimNo";
//                    Query query1 = session.createQuery(hqlWC);
//                    query1.setParameter("warrantyClaimNo", wf.getWarClaimNoArr()[i]);
//                    int j = query1.executeUpdate();
                    } else {
                        hqlpart = "update Warrantyclaimdetails wc set wc.dispatchStatus=:dispatchStatus where wc.warrantyClaimNo=:warrantyClaimNo and wc.partNo=:partNo ";
                        Query queryPart = session.createQuery(hqlpart);
                        queryPart.setParameter("dispatchStatus", wf.getDispatchReqArr()[i]);
                        queryPart.setParameter("warrantyClaimNo", wf.getWarClaimNoArr()[i]);
                        queryPart.setParameter("partNo", wf.getPartNoArr()[i]);
                        queryPart.executeUpdate();
                    }
                }

                unpackedClaimArr.removeAll(packedClaimArr);

                for (int k = 0; k < packedClaimArr.size(); k++) {
                    Warrantyclaim warnDetail = (Warrantyclaim) session.load(Warrantyclaim.class, ""+packedClaimArr.get(k));
                    warnDetail.setClaimStatus("PACKED");
                    session.saveOrUpdate(warnDetail);
                }

                for (int k = 0; k < unpackedClaimArr.size(); k++) {
                    Warrantyclaim warnDetail = (Warrantyclaim) session.load(Warrantyclaim.class,""+unpackedClaimArr.get(k));
                    //if (warnDetail.getClaimStatus().equalsIgnoreCase("PENDING")) {
                        warnDetail.setClaimStatus("RECEIVED");
                        session.saveOrUpdate(warnDetail);
                   // }
                }
            }
            else
            {
                 wf.setPackingNo("notPacked");
            }

            session.getTransaction().commit();
            wf.setResult("success");
            //  result = "success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return wf;
    }

    /**
     * getPackedWarrantyList
     */
    public ArrayList<WarrantyForm> getPackedWarrantyList(String DealerCode, String UserId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dispachedList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Integer cnt = Integer.parseInt(obj.getHesConstantValue(14));
        try {
        	Query query = session.createQuery("select sw.packingNo,sw.packingDate,(select  count(distinct warrantyClaimNo) from Warrantyclaimdetails wd where wd.packingNo=sw.packingNo) as noOfclaims "
                    + " from SWwarrantypackingmaster sw where sw.packingStatus='PACKED' and sw.dealerCode=:dealerCode " +
                    " and sw.packingDate >= getdate()-"+cnt+" order by sw.packingDate desc");
        	query.setString("dealerCode", DealerCode);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                WarrantyForm wrntyForm = new WarrantyForm();
                Object[] obj = (Object[]) itr.next();
                WarrantyForm wf = new WarrantyForm();
                wf.setPackingNo(obj[0] == null ? "" : obj[0].toString().trim());
                wf.setPackingDate(obj[1] == null ? "" : sdf.format(df.parse(obj[1].toString().trim())));
                wf.setNoOfClaims(obj[2] == null ? 0 : Integer.parseInt(obj[2].toString().trim()));
                dispachedList.add(wf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dispachedList;
    }

    /**
     * getDishpatchedData
     */
    public WarrantyForm getDishpatchedData(String packingNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        WarrantyForm wf = new WarrantyForm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Query query = session.createQuery("select distinct wd.warrantyClaimNo,pm.packingNo,pm.packingDate from SWwarrantypackingmaster pm,Warrantyclaimdetails wd "
                    + "where pm.packingNo = wd.packingNo and pm.packingStatus='PACKED' and wd.packingNo=:packingNo");
            query.setString("packingNo", packingNo);
            List list = query.list();
            Iterator itr = list.iterator();
            String claimnos = "";
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                wf.setPackingNo(obj[1] == null ? "" : obj[1].toString().trim());
                wf.setPackingDate(obj[2] == null ? "" : sdf.format(df.parse(obj[2].toString().trim())));
                claimnos = claimnos + (obj[0] == null ? "" : (obj[0].toString().trim() + ", "));
            }
            wf.setWarrantyClaimNo(claimnos.substring(0, claimnos.length() - 2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return wf;
    }

    /**
     * set Dishpatched Data
     */
    public String setDishpatchedData(WarrantyForm wf, String realPath) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "FAILURE";
        String dest = "";
        String extension1 = "";
        String courierCopyFileName = null;
        String newcourierCopyFileName="";
        FormFile upoadfile = null;
        boolean uploadFileflag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      //  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (wf.getCourierCopy() != null) {
                courierCopyFileName = wf.getCourierCopy().getFileName();
            }
            if (courierCopyFileName != null) {
                int i = courierCopyFileName.lastIndexOf('.');
                if (i > 0) {
                    extension1 = courierCopyFileName.substring(i + 1);
                    System.out.println("extension is  " + extension1);
                }
                 dest = realPath + "\\Documents\\Warranty\\";
                 upoadfile = wf.getCourierCopy();
                 courierCopyFileName = wf.getCourierCopy().getFileName();
                 newcourierCopyFileName = wf.getPackingNo().replace("/", "-").concat("-").concat("CourierCopy").concat(".").concat(extension1);
            }

            session.beginTransaction();
            SWwarrantypackingmaster packingmaster = (SWwarrantypackingmaster) session.load(SWwarrantypackingmaster.class, wf.getPackingNo());
            packingmaster.setDispatchedDate(sdf.parse(wf.getDispatchDate()));
            packingmaster.setDispatchedThrough(wf.getDispatchBy());
            packingmaster.setPartsDispatchedFor(wf.getDispatchFor());
            packingmaster.setCourierNo(wf.getCourierNo());
            packingmaster.setCourierName(wf.getCourierName());
            packingmaster.setCourierCopyFile(newcourierCopyFileName);
            packingmaster.setPackingStatus("DISPATCHED");
            packingmaster.setLastModifiedBy(wf.getUserId());
            packingmaster.setLastModifiedOn(new Date(new java.util.Date().getTime()));
            packingmaster.setIsServerSync('N');
            packingmaster.setStoreNoOfPackages(wf.getStoreNoOfPackages());
            session.saveOrUpdate(packingmaster);
            String[] claimNos = wf.getWarrantyClaimNo().split(",");
            for(int i=0;i<claimNos.length;i++){
              Warrantyclaim warrantyclaim =(Warrantyclaim) session.load(Warrantyclaim.class, claimNos[i].trim());
              if(warrantyclaim.getClaimStatus().equalsIgnoreCase("PACKED"))
              {
                  warrantyclaim.setClaimStatus("DISPATCHED");
                  warrantyclaim.setIsServerSync('N');
                  warrantyclaim.setLastModifiedBy(wf.getUserId());
                  warrantyclaim.setLastModifiedOn(new Date(new java.util.Date().getTime()));
                  session.saveOrUpdate(packingmaster);
              }
            }
            session.getTransaction().commit();
            result = "SUCCESS";

            if (courierCopyFileName!=null && result.equals("SUCCESS")) {
                uploadFileflag = MethodUtility.uploadFile(newcourierCopyFileName, dest, upoadfile, realPath);
            }
            if (courierCopyFileName!=null && !uploadFileflag) {
                session.getTransaction().rollback();
                result = "FAILURE";
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<WarrantyForm> getPendingForAcknowList(WarrantyForm warrantyForm, Vector userFunctionalities) throws SQLException {  //Vector userFunctionalities

        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hql = "";
        String Subsql = "";
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
            if (!warrantyForm.getPackingNo().equals("")) {
                Subsql = " and  wp.packingNo like '%" + warrantyForm.getPackingNo() + "%' ";
            }
            if ("1".equals(warrantyForm.getRange())) {
                dateSubQur = " and (wp.packingDate between isnull(?,wp.packingDate) and isnull(?,wp.packingDate)) ";
            }
            hql = "Select wp.packingNo,wp.packingDate,wp.partsDispatchedFor,wp.dispatchedThrough,wp.courierNo,wp.courierName,wp.dispatchedDate "
                    + " from SWwarrantypackingmaster wp where wp.packingStatus='DISPATCHED' "
                    + "  " + Subsql + dateSubQur + " ";  //      and wp.dealerCode=:dealerCode  order by wp.packingNo desc//and (wp.packingDate between isnull(?,wp.packingDate) and isnull(?,wp.packingDate))
            //  Query query = session.createQuery(hql);

            Query query = null;

            query = session.createQuery(hql +
                   // " and wp.dealerCode IN(:dealerList)" +
                    " and :dealerList LIKE ('%'+wp.dealerCode+'%') "+
                    "  order by wp.packingNo desc");
            if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
                dealerList.add(warrantyForm.getDealer_code());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session,warrantyForm.getUserId());
            }
           // query.setParameterList("dealerList", dealerList);
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

 /*           if (userFunctionalities.contains("101")) {
                hql = hql + "and wp.dealerCode=:dealerCode  order by wp.packingNo desc";
                query = session.createQuery(hql);
                query.setString("dealerCode", warrantyForm.getDealer_code());

            } else if (userFunctionalities.contains("102")) {
                ArrayList<String> dealerList = new ArrayList<String>();
                hql = hql + " and wp.dealerCode IN(:dealerList)  order by wp.packingNo desc";
                if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
                    dealerList.add(warrantyForm.getDealer_code());
                } else {
                    dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + warrantyForm.getUserId() + "'");
                }
                query = session.createQuery(hql);
                query.setParameterList("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
                    hql = hql + " and wp.dealerCode='" + warrantyForm.getDealer_code() + "'  order by wp.packingNo desc";
                } else {
                    hql = hql + Subsql + "  order by wp.packingNo desc";
                }
                query = session.createQuery(hql);
            }
  *
  */
            if ("1".equals(warrantyForm.getRange())) {
                query.setDate(0, warrantyForm.getFromDate().equals("") == true ? null : sdf.parse(warrantyForm.getFromDate()));
                query.setDate(1, warrantyForm.getToDate().equals("") == true ? null : sdf.parse(warrantyForm.getToDate()));
            }
            //  query.setString("dealerCode", warrantyForm.getDealer_code());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm wf = new WarrantyForm();

                wf.setPackingNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
                wf.setPackingDate(enqobj[1] == null ? "" : outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
                wf.setDispatchFor(enqobj[2] == null ? "" : enqobj[2].toString().trim());
                wf.setDispatchBy(enqobj[3] == null ? "" : enqobj[3].toString().trim());
                wf.setCourierNo(enqobj[4] == null ? "NA" : enqobj[4].toString().trim());
                wf.setCourierName(enqobj[5] == null ? "NA" : enqobj[5].toString().trim());
                wf.setDispatchDate(enqobj[6] == null ? "" : outputFormat.format(inputFormat.parse(enqobj[6].toString().trim())));

                dataList.add(wf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public ArrayList<WarrantyForm> getPackingDataForAcknow(WarrantyForm warrantyForm,String flag) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");

        String hql = "";
        String packHql = "";
        try {

            packHql = "Select wp.packingNo,wp.packingDate,wp.partsDispatchedFor ,"
                    + " wp.dispatchedThrough,wp.dispatchedDate,wp.courierNo,wp.courierName,wp.packingStatus, "
                    + " wp.courierCopyFile,wp.receivedBy,wp.receivedOn,wp.dealerCode,wp.storeNoOfPackages "
                    + " from SWwarrantypackingmaster wp where wp.packingNo=:packingNo ";

            Query queryPack = session.createQuery(packHql);
            queryPack.setString("packingNo", warrantyForm.getPackingNo());
            Iterator itPack = queryPack.list().iterator();
            while (itPack.hasNext()) {
                Object enqobj[] = (Object[]) itPack.next();
                warrantyForm.setPackingNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                warrantyForm.setPackingDate(enqobj[1] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
                warrantyForm.setDispatchFor(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                warrantyForm.setDispatchBy(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                warrantyForm.setDispatchDate(enqobj[4] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[4].toString().trim())));
                warrantyForm.setCourierNo(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                warrantyForm.setCourierName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                warrantyForm.setCourierCopyFileName(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                warrantyForm.setReceivedBy(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                warrantyForm.setReceivedDate(enqobj[10] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[10].toString().trim())));
                warrantyForm.setDealer_code(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                warrantyForm.setStoreNoOfPackages(enqobj[12] == null ? 1 : Integer.parseInt(enqobj[12].toString()));

            }

            hql = "Select wc.warrantyClaimNo,wc.partNo,wc.partDesc,wc.qty,wc.packingNo,wc.dispatchedQty,wc.boxNo,wc.qtyReceived  "
                    + " from SWwarrantypackingmaster wp,Warrantyclaimdetails wc where "
                    + " wp.packingNo=wc.packingNo and wp.packingNo=:packingNo order by wp.packingNo desc";
            Query query = session.createQuery(hql);
            query.setString("packingNo", warrantyForm.getPackingNo());
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                Object enqobj[] = (Object[]) it.next();
                WarrantyForm wf = new WarrantyForm();

                wf.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                wf.setPartNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                wf.setPartDesc(enqobj[2] == null ? "-" : enqobj[2].toString().trim().toUpperCase());

                //  wf.setQty(enqobj[3] == null ? "" : enqobj[3].toString().trim());

                double t = enqobj[3] == null ? 0.0 :Double.parseDouble(enqobj[3].toString().trim());
                if (t == Math.ceil(t)) {
                    wf.setQty("" + ((int) t));
                } else {
                    wf.setQty(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                }



                wf.setPackingNo(enqobj[4] == null ? "-" : enqobj[4].toString().trim());

                double tt = enqobj[5] == null ? 0.0 :Double.parseDouble(enqobj[5].toString().trim());
                if (tt == Math.ceil(tt)) {
                    wf.setDispatchQty("" + ((int) tt));
                } else {
                    wf.setDispatchQty(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                }

                // wf.setDispatchQty(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                wf.setBoxNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());

              if(flag.equals("ack")){
                wf.setReceivedQty(enqobj[7] == null ? "" : enqobj[7].toString().trim());
                }
              else
                {
                    double ttt = enqobj[7] == null ? 0.0 :Double.parseDouble(enqobj[7].toString().trim());
                    if(ttt==Math.ceil(ttt)){
                        wf.setReceivedQty(""+((int)ttt));
                    }
                    else{
                       wf.setReceivedQty(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                    }
                }

                dataList.add(wf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }

    public WarrantyForm savePackingAcknow(WarrantyForm wf, String userId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
      //  ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String hql = "";

     //   int counter = 0;
        try {
            session.beginTransaction();
            ArrayList packedClaimArr = new ArrayList();

            if (wf.getWarClaimNoArr() != null) {
                for (int i = 0; i < wf.getWarClaimNoArr().length; i++) {

                    hql = "update Warrantyclaimdetails wc set wc.qtyReceived=:qtyReceived where wc.warrantyClaimNo=:warrantyClaimNo and wc.partNo=:partNo and wc.packingNo=:packingNo ";
                    Query query = session.createQuery(hql);
                    query.setParameter("qtyReceived", Double.parseDouble(wf.getReceivedQtyArr()[i]));
                    query.setParameter("warrantyClaimNo", wf.getWarClaimNoArr()[i]);
                    query.setParameter("partNo", wf.getPartNoArr()[i]);
                    query.setParameter("packingNo", wf.getPackingNo());
                    int jj = query.executeUpdate();

                     if (!packedClaimArr.contains(wf.getWarClaimNoArr()[i])) {
                            packedClaimArr.add(wf.getWarClaimNoArr()[i]);
                        }
                }

                for(int k=0;k<packedClaimArr.size();k++)
                {
                 Warrantyclaim warnDetail = (Warrantyclaim) session.load(Warrantyclaim.class, ""+packedClaimArr.get(k));
                    warnDetail.setClaimStatus("RECEIVED");
                    session.saveOrUpdate(warnDetail);
                }

                SWwarrantypackingmaster packingDetail = (SWwarrantypackingmaster) session.load(SWwarrantypackingmaster.class, wf.getPackingNo());
                packingDetail.setPackingStatus("RECEIVED");
                packingDetail.setReceivedBy(userId);
                packingDetail.setReceivedOn(df.parse(df.format(new Date())));
                packingDetail.setReceiverRemarks(wf.getRemark());
                session.saveOrUpdate(packingDetail);
            }


            session.getTransaction().commit();
            wf.setResult("success");
            //  result = "success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return wf;
    }

    /**
     * get view PackingList
     */
    public ArrayList<WarrantyForm> getviewPackingList(WarrantyForm wForm,Vector userFunctionalities ) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dispachedList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = ""; String dateSubQur="";
        ArrayList<String> dealerList=new ArrayList<String>();
        try {
             if (wForm.getPackingNo()!=null && !wForm.getPackingNo().equals("")) {
            	 System.out.println("Line 2340");
                Subsql = " and  sw.packingNo like '%" + wForm.getPackingNo() + "%' ";
            }
            if("1".equals(wForm.getRange()) ){
            	System.out.println("Line 2344");
               dateSubQur=" and (sw.packingDate between isnull(?,sw.packingDate) and isnull(?,sw.packingDate)) ";
            }
            String hql="from SWwarrantypackingmaster sw" +
                    " where sw.packingStatus=isnull(?,sw.packingStatus) " + Subsql + dateSubQur + " ";
            Query query = null;

            query = session.createQuery(hql +" " +
                    //"and sw.dealerCode IN(:dealerList)"
                    " and :dealerList LIKE ('%'+sw.dealerCode+'%') "
                    + Subsql + "  order by sw.packingDate desc,sw.dealerCode");
            if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
            	System.out.println("Line 2356");
            	dealerList.add(wForm.getDealer_code());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session,wForm.getUserId());
            }
          //  query.setParameterList("dealerList", dealerList);

            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

             query.setString(0, wForm.getPackingStatus());
             if("1".equals(wForm.getRange()) ){
            	 System.out.println("Line 2367");
            query.setDate(1, wForm.getFromDate().equals("") == true ? null : sdf.parse(wForm.getFromDate()));
            query.setDate(2, wForm.getToDate().equals("") == true ? null : sdf.parse(wForm.getToDate()));
            }
            
             System.out.println("Query "+query);
             
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                SWwarrantypackingmaster obj = (SWwarrantypackingmaster) itr.next();
                WarrantyForm wf = new WarrantyForm();
                wf.setPackingNo(obj.getPackingNo() == null ? "-" : obj.getPackingNo());
                wf.setPackingDate(obj.getPackingDate() == null ? "-" : sdf.format(obj.getPackingDate()));
                wf.setDispatchFor(obj.getPartsDispatchedFor() == null ? "-" : obj.getPartsDispatchedFor());
                wf.setDispatchBy(obj.getDispatchedThrough() == null ? "-" : obj.getDispatchedThrough());
                wf.setCourierNo(obj.getCourierNo() == null ? "-" : obj.getCourierNo());
                wf.setCourierName(obj.getCourierName() == null ? "-" : obj.getCourierName());
                wf.setCourierCopyFileName(obj.getCourierCopyFile() == null ? "-" :obj.getCourierCopyFile().equals("")==true?"-": obj.getCourierCopyFile());
                wf.setPackingStatus(obj.getPackingStatus() == null ? "-" : obj.getPackingStatus());
                
                Query qry = session.createSQLQuery("EXEC CheckClaimStatusByPackingNo :PackingNo");
                qry.setParameter("PackingNo", wf.getPackingNo());
     
                String result = (String) qry.uniqueResult();

                if ("TRUE".equalsIgnoreCase(result)) {
                    wf.setIsApproved("Y");
                } else {
                	wf.setIsApproved("N");
                }
                
                dispachedList.add(wf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dispachedList;
    }

    /**
     * get pendingSAPList
     */
    public ArrayList<WarrantyForm> getpendingSAPList1(WarrantyForm wForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> pendingSAPList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try {
        	/*
        	
        	if (wForm.getWarrantyClaimNo() != null && !wForm.getWarrantyClaimNo().equals("")) {
        	    Subsql = " and wc.warrantyClaimNo like '%" + wForm.getWarrantyClaimNo() + "%' ";
        	}
        	
        	Date fromDate = null;
            Date toDate = null;
            String dateSubsql = "";
        	
        	if (wForm.getRange() != null && wForm.getRange().equals("1")) {
                if (wForm.getFromDate() != null && !wForm.getFromDate().isEmpty()) {
                    fromDate = sdf.parse(wForm.getFromDate()); // Assuming wForm.getFromDate() is in dd/MM/yyyy format
                    dateSubsql += " and wc.claimDate >= :fromDate ";
                }

                if (wForm.getToDate() != null && !wForm.getToDate().isEmpty()) {
                    toDate = sdf.parse(wForm.getToDate()); // Assuming wForm.getToDate() is in dd/MM/yyyy format
                    dateSubsql += " and wc.claimDate <= :toDate ";
                }
            }
        	

        	String hql = "Select wc.warrantyClaimNo, wc.jobCardNo, wc.tmsRefNo, wc.jobCardDate, wc.claimDate, wc.vinno, " +
        	             "(select dm.dealerName from Dealervslocationcode dm where dm.dealerCode = wc.dealerCode) as dealerName, " +
        	             "wc.dealerCode, wc.claimStatus, " +
        	             "(select dm.location from Dealervslocationcode dm where dm.dealerCode = wc.dealerCode) as location, " +
        	             "wc.sapWarrantyClaim, " +
        	             "(select distinct wcd.packingNo from Warrantyclaimdetails wcd where wcd.warrantyClaimNo = wc.warrantyClaimNo) as packingNo from Warrantyclaim wc "
        	            + "where wc.sapSyncStatus <> 'CREDITED' " + //PENDING
        	             "and wc.claimDate> :hideDataBefore " +
        	             Subsql +  dateSubsql +
        	             " and :dealerList LIKE ('%' || wc.dealerCode || '%') " +
        	             "order by wc.claimDate desc";

        	Query query = session.createQuery(hql);

        	if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
        	    dealerList = new ArrayList<>();
        	    dealerList.add(wForm.getDealer_code());
        	} else {
        	    dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, wForm.getUserId());
        	}

        	// Assuming MethodUtility.appendDealerList returns a string formatted as "code1,code2,code3"
        	String dealerListString = MethodUtility.appendDealerList(dealerList);
        	query.setParameter("dealerList", dealerListString);
        	query.setParameter("hideDataBefore", new SimpleDateFormat("yyyy-MM-dd").parse(ComUtils.getPropertyValue("hideDataBefore")));
        	
        	 if (wForm.getRange() != null && wForm.getRange().equals("1")) {
                 if (fromDate != null) {
                     query.setParameter("fromDate", fromDate); // Pass Date object directly
                 }
                 if (toDate != null) {
                     query.setParameter("toDate", toDate); // Pass Date object directly
                 }
             }
        	
        	*/
        	
        	String query = "EXEC GET_PENDING_SAP_LIST ?, ?, ?, ?, ?";
        	session.createSQLQuery(query).setParameter("hideDataBefore", new SimpleDateFormat("yyyy-MM-dd").parse(ComUtils.getPropertyValue("hideDataBefore")))
        	.setParameter("fromDate", wForm.getToDate()).setParameter("toDate", wForm.getToDate())
        	.setParameter("dealerCode", wForm.getDealer_code()).setParameter("claimNo", wForm.getWarrantyClaimNo());
        	
        	List list = null;
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                warrantyForm.setJobCardNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                warrantyForm.setJobCardDate(enqobj[3] == null ? "-" : sdf.format(df.parse(enqobj[3].toString().trim())));
                warrantyForm.setClaimDate(enqobj[4] == null ? "-" : sdf.format(df.parse(enqobj[4].toString().trim())));
                warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                warrantyForm.setDealer_code(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                warrantyForm.setClaimStatus(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                warrantyForm.setLocation(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                warrantyForm.setSapWarrantyClaimNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                warrantyForm.setPackingNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                pendingSAPList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return pendingSAPList;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<WarrantyForm> getpendingSAPList(WarrantyForm wForm, Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> pendingSAPList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> dealerList = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
           
            if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
                dealerList.add(wForm.getDealer_code());
            } else {
                // Fetch dealer codes under user
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, wForm.getUserId());
            }

            String dealerListString = MethodUtility.appendDealerList(dealerList);

            // Create the stored procedure query
            String query = "EXEC GET_PENDING_SAP_LIST :hideDataBefore, :fromDate, :toDate, :dealerCode, :claimNo";

			
         // Ensure correct date parsing
            Date hideDataBefore = new SimpleDateFormat("yyyy-MM-dd").parse(ComUtils.getPropertyValue("hideDataBefore"));
            Date parsedDate = null;
            String toDate = null;
            String fromDate = null;
            
            if(wForm.getFromDate() != null && wForm.getFromDate() != "") {
            	 parsedDate = sdf.parse(wForm.getFromDate());  // Parsing the date from the original format
                 fromDate = df.format(parsedDate);
            }
            
            if(wForm.getFromDate() != null && wForm.getToDate() != "") {
            	 parsedDate = sdf.parse(wForm.getToDate());  // Parsing the date from the original format
                 toDate = df.format(parsedDate);
            }
            
            
            List<Object[]> list = session.createSQLQuery(query)
                    .addScalar("warrantyClaimNo", StringType.INSTANCE)  // Adjust types as needed
                    .addScalar("jobCardNo", StringType.INSTANCE)
                    .addScalar("TMS_REF_NO", StringType.INSTANCE)
                    .addScalar("jobCardDate", StringType.INSTANCE)
                    .addScalar("claimDate", StringType.INSTANCE)
                    .addScalar("vinno", StringType.INSTANCE)
                    .addScalar("dealerName", StringType.INSTANCE)
                    .addScalar("dealerCode", StringType.INSTANCE)
                    .addScalar("claimStatus", StringType.INSTANCE)
                    .addScalar("location", StringType.INSTANCE)
                    .addScalar("sap_WarrantyClaim", StringType.INSTANCE)
                    .addScalar("packingNo", StringType.INSTANCE)
                    .setParameter("hideDataBefore", hideDataBefore)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate)
                    .setParameter("dealerCode", wForm.getDealer_code())
                    .setParameter("claimNo", wForm.getWarrantyClaimNo())
                    .list();


            Iterator<Object[]> itr = list.iterator();
            while (itr.hasNext()) {
                Object[] enqobj = itr.next();
                WarrantyForm warrantyForm = new WarrantyForm();

                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                warrantyForm.setJobCardNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                warrantyForm.setJobCardDate(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                warrantyForm.setClaimDate(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                warrantyForm.setDealer_code(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                warrantyForm.setClaimStatus(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                warrantyForm.setLocation(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                warrantyForm.setSapWarrantyClaimNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                warrantyForm.setPackingNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());

                pendingSAPList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close session safely
            if (session != null) {
                session.close();
            }
        }
        return pendingSAPList;
    }


    public String getDescByVendorCode(String vendorCode, String dealerCode) {
        String vendorDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbsession.createQuery("select vc.vendorCode,vc.vendorDesc "
                    + " from MSWvendormaster vc "
                    + " where vc.vendorCode=:vendorCode ");
            query.setParameter("vendorCode", vendorCode);
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




     public ArrayList<WarrantyForm> getViewCreditNoteList_backup(WarrantyForm wForm,Vector userFunctionalities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> pendingSAPList = new ArrayList<WarrantyForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";  String dateSubQur = "";
        ArrayList<String> dealerList=new ArrayList<String>();
        try {
            if (wForm.getWarrantyClaimNo()!=null && !wForm.getWarrantyClaimNo().equals("")) {
                Subsql = " and wc.warrantyClaimNo like '%" + wForm.getWarrantyClaimNo() + "%' ";
            }
            if (wForm.getSapCreditNo()!=null && !wForm.getSapCreditNo().equals("")) {
                Subsql += " and wc.sapCreditNo like '%" + wForm.getSapCreditNo() + "%' ";
            }
             if("1".equals(wForm.getRange()) ){
               dateSubQur=" and ( wc.sapCreditDate between isnull(?,wc.sapCreditDate) and isnull(?,wc.sapCreditDate) )";
            }

            String hql="Select wc.warrantyClaimNo,wc.jobCardNo,wc.tmsRefNo,wc.jobCardDate ,wc.claimDate,wc.vinno,(select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=wc.dealerCode) as dealerName, wc.dealerCode,wc.claimStatus, "
                        + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=wc.dealerCode) as location ,"
                        + " CASE WHEN wc.claimStatus = 'REJECTED' THEN '' ELSE wc.sapSyncStatus END AS sapSyncStatus,"
                        + " wc.sapCreditNo,wc.sapCreditDate,wc.sapCreditAmount,wc.totalClaimAmount,wc.totalApprovedAmount,wc.sapWarrantyClaim,"
       	                + " (select distinct wcd.packingNo from Warrantyclaimdetails wcd where wcd.warrantyClaimNo = wc.warrantyClaimNo) as packingNo " 
                        + " from Warrantyclaim wc where wc.claimDate> :hideDataBefore and  wc.claimStatus in ('REJECTED','APPROVED','PARTIAL APPROVED') ";
                       // + " and wc.sapSyncStatus ='CREDITED' ";
//            Query query = null;
//          
//            query = session.createQuery(hql + Subsql + dateSubQur +
//                   // " and wc.dealerCode IN(:dealerList)" +
//                    " and :dealerList LIKE ('%'+wc.dealerCode+'%') "+
//                    " order by wc.warrantyClaimNo");
            
            
            String finalQuery = hql + Subsql + dateSubQur;

            // Checking if dealer list is not empty and adding the condition
            if (!dealerList.isEmpty()) {
                finalQuery += " and wc.dealerCode in (:dealerList) ";
            }

            finalQuery += " order by wc.warrantyClaimNo";
            Query query = session.createQuery(finalQuery);
            
            if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
                dealerList.add(wForm.getDealer_code());
            } else {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session,wForm.getUserId());
            }
            //query.setParameterList("dealerList", dealerList);
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            query.setParameter("hideDataBefore", new SimpleDateFormat("yyyy-MM-dd").parse(ComUtils.getPropertyValue("hideDataBefore")));

/*             if (userFunctionalities.contains("101")) {
                    hql = hql +Subsql + dateSubQur  +" and wc.dealerCode='" + wForm.getDealer_code() + "' order by wc.warrantyClaimNo";
                    query = session.createQuery(hql);
                }
             else if (userFunctionalities.contains("102")) {
                 ArrayList<String> dealerList=new ArrayList<String>();
                 hql = hql + Subsql + dateSubQur + " and wc.dealerCode IN(:dealerList) order by wc.warrantyClaimNo";
                 if(wForm.getDealer_code()!=null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")){
                        dealerList.add(wForm.getDealer_code());
                    }
                    else{
                       dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + wForm.getUserId() + "'");
                    }
                    query = session.createQuery(hql);
                    query.setParameterList("dealerList", dealerList);
                } else if (userFunctionalities.contains("103")) {
                    if(wForm.getDealer_code()!=null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")){
                        hql = hql + Subsql + dateSubQur+ " and wc.dealerCode='" + wForm.getDealer_code()+"'  order by wc.warrantyClaimNo";
                    }
                    else{
                        hql = hql + Subsql +  "  order by wc.warrantyClaimNo";
                    }
                    query = session.createQuery(hql);
                }
 *
 */
             if("1".equals(wForm.getRange()) ){
            query.setString(0, wForm.getFromDate().equals("") == true ? null : df.format(sdf.parse(wForm.getFromDate()+ " 00:00")));
            query.setString(1, wForm.getToDate().equals("") == true ? null : df.format(sdf.parse(wForm.getToDate()+ " 23:59")));
               }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object enqobj[] = (Object[]) itr.next();
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                warrantyForm.setJobCardNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                warrantyForm.setTmsRefNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                warrantyForm.setJobCardDate(enqobj[3] == null ? "-" : sdf.format(df.parse(enqobj[3].toString().trim())));
                warrantyForm.setClaimDate(enqobj[4] == null ? "-" :  sdf.format(df.parse(enqobj[4].toString().trim())));
                warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                warrantyForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                warrantyForm.setDealer_code(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                warrantyForm.setClaimStatus(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                warrantyForm.setLocation(enqobj[9] == null ? "-" : enqobj[9].toString().trim());

                warrantyForm.setSapSyncStatus(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                warrantyForm.setSapCreditNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                warrantyForm.setSapCreditDate(enqobj[12] == null ? "-" : sdf.format(df.parse(enqobj[12].toString().trim())));
                warrantyForm.setSapCreditAmount(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                warrantyForm.setTotalClaimValue(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                warrantyForm.setTotalApproveAmmount(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                warrantyForm.setSapWarrantyClaimNo(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                warrantyForm.setPackingNo(enqobj[17] == null ? "-" : enqobj[17].toString().trim());

                pendingSAPList.add(warrantyForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return pendingSAPList;
    }
     
     public ArrayList<WarrantyForm> getViewCreditNoteList(WarrantyForm wForm, Vector userFunctionalities) {
         // Initialize Hibernate session
         Session session = HibernateUtil.getSessionFactory().openSession();
         ArrayList<WarrantyForm> pendingSAPList = new ArrayList<>();
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         StringBuilder subSql = new StringBuilder();
         String dateSubQuery = "";
         ArrayList<String> dealerList = new ArrayList<>();

         // Populate the dealerList based on conditions
         if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
             dealerList.add(wForm.getDealer_code());
         } else {
             // Ensure `MethodUtility.getDealerCodeUnderUser()` returns `ArrayList<String>`
             dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, wForm.getUserId());
         }

         try {
             // Dynamically build the `Subsql` condition based on `WarrantyForm` fields
             if (wForm.getWarrantyClaimNo() != null && !wForm.getWarrantyClaimNo().isEmpty()) {
                 subSql.append(" and wc.warrantyClaimNo like :warrantyClaimNo ");
             }
             if (wForm.getSapCreditNo() != null && !wForm.getSapCreditNo().isEmpty()) {
                 subSql.append(" and wc.sapCreditNo like :sapCreditNo ");
             }
             // Add date range filter based on user input
             if ("1".equals(wForm.getRange())) {
                 dateSubQuery = " and (wc.sapCreditDate between coalesce(:startDate, wc.sapCreditDate) and coalesce(:endDate, wc.sapCreditDate))";
             }

             // Main HQL query
             String hql = "Select wc.warrantyClaimNo, wc.jobCardNo, wc.tmsRefNo, wc.jobCardDate, wc.claimDate, wc.vinno, " +
                     " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode = wc.dealerCode) as dealerName, " +
                     " wc.dealerCode, wc.claimStatus, " +
                     " (select dm.location from Dealervslocationcode dm where dm.dealerCode = wc.dealerCode) as location, "
                     + "wc.sapSyncStatus"
                     +" ,wc.sapCreditNo, wc.sapCreditDate, wc.sapCreditAmount, wc.totalClaimAmount, wc.totalApprovedAmount, wc.sapWarrantyClaim, " +
                     " (select distinct wcd.packingNo from Warrantyclaimdetails wcd where wcd.warrantyClaimNo = wc.warrantyClaimNo) as packingNo " +
                     " from Warrantyclaim wc where wc.claimDate > :hideDataBefore " +
                     " and wc.sapSyncStatus in ('CREDITED')";

             // Append dynamic conditions to the HQL query
             StringBuilder finalQuery = new StringBuilder(hql).append(subSql).append(dateSubQuery);

             // Append dealer list condition if not empty
             if (!dealerList.isEmpty()) {
                 finalQuery.append(" and wc.dealerCode in (:dealerList) ");
             }

             // Append order clause
             finalQuery.append(" order by wc.claimDate desc");

             // Create query object
             Query query = session.createQuery(finalQuery.toString());

             // Set parameters dynamically based on user input
             query.setParameter("hideDataBefore", new SimpleDateFormat("yyyy-MM-dd").parse(ComUtils.getPropertyValue("hideDataBefore")));

             // Set dealer list correctly using setParameterList
             if (!dealerList.isEmpty()) {
                 query.setParameterList("dealerList", dealerList);
             }

             if (wForm.getWarrantyClaimNo() != null && !wForm.getWarrantyClaimNo().isEmpty()) {
                 query.setParameter("warrantyClaimNo", "%" + wForm.getWarrantyClaimNo() + "%");
             }
             if (wForm.getSapCreditNo() != null && !wForm.getSapCreditNo().isEmpty()) {
                 query.setParameter("sapCreditNo", "%" + wForm.getSapCreditNo() + "%");
             }
             if ("1".equals(wForm.getRange())) {
                 query.setParameter("startDate", sdf.parse(wForm.getFromDate())); // Ensure `getStartDate()` returns date in `dd/MM/yyyy`
                 query.setParameter("endDate", sdf.parse(wForm.getToDate())); // Ensure `getEndDate()` returns date in `dd/MM/yyyy`
             }

             // Execute the query and retrieve results
             List list = query.list();
             Iterator itr = list.iterator();
             while (itr.hasNext()) {
                 Object[] enqobj = (Object[]) itr.next();
                 WarrantyForm warrantyForm = new WarrantyForm();
                 warrantyForm.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                 warrantyForm.setJobCardNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                 warrantyForm.setTmsRefNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                 warrantyForm.setJobCardDate(enqobj[3] == null ? "-" : sdf.format(df.parse(enqobj[3].toString().trim())));
                 warrantyForm.setClaimDate(enqobj[4] == null ? "-" : sdf.format(df.parse(enqobj[4].toString().trim())));
                 warrantyForm.setVinNo(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                 warrantyForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                 warrantyForm.setDealer_code(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                 warrantyForm.setClaimStatus(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                 warrantyForm.setLocation(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                 warrantyForm.setSapSyncStatus(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                 warrantyForm.setSapCreditNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                 warrantyForm.setSapCreditDate(enqobj[12] == null ? "-" : sdf.format(df.parse(enqobj[12].toString().trim())));
                 warrantyForm.setSapCreditAmount(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                 warrantyForm.setTotalClaimValue(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                 warrantyForm.setTotalApproveAmmount(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                 warrantyForm.setSapWarrantyClaimNo(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                 warrantyForm.setPackingNo(enqobj[17] == null ? "-" : enqobj[17].toString().trim());

                 pendingSAPList.add(warrantyForm);
             }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             if (session != null) {
                 session.close();
             }
         }
         return pendingSAPList;
     }
     
     public ArrayList<String> getWarrantyMaterialData(String packingNo) throws SQLException {
    	    Session session = HibernateUtil.getSessionFactory().openSession();
    	    ArrayList<String> dataList = new ArrayList<String>();
    	    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
    	    DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");

    	    try {
    	        // Stored procedure call
    	        String storedProc = "EXEC WarrantyMaterialView :packingNo";
    	        Query query = session.createSQLQuery(storedProc)
    	            .addScalar("dealerCode", StandardBasicTypes.STRING)     // Assuming it's a string
    	            .addScalar("dealerName", StandardBasicTypes.STRING)     // Assuming it's a string
    	            .addScalar("location", StandardBasicTypes.STRING)       // Assuming it's a string
    	            .addScalar("warrantyClaimNo", StandardBasicTypes.STRING) // Assuming it's a string
    	            .addScalar("part_no", StandardBasicTypes.STRING)         // Assuming it's a string
    	            .addScalar("part_Desc", StandardBasicTypes.STRING)       // Assuming it's a string
    	            .addScalar("engineno", StandardBasicTypes.STRING)       // Assuming it's a string
    	            .addScalar("vinno", StandardBasicTypes.STRING)          // Assuming it's a string
    	            .addScalar("failureDate", StandardBasicTypes.TIMESTAMP) // Assuming it's a timestamp
    	            .addScalar("failureHrs", StandardBasicTypes.STRING)     // Assuming it's a string
    	            .addScalar("failureDesc", StandardBasicTypes.STRING);   // Assuming it's a string

    	        // Setting the parameter for packingNo
    	        query.setParameter("packingNo", packingNo);

    	        // Execute the query and process the results
    	        List<Object[]> results = query.list();
    	        for (Object[] row : results) {
    	            dataList.add(row[0] == null ? "-" : row[0].toString().trim());  // dealerCode
    	            dataList.add(row[1] == null ? "-" : row[1].toString().trim());  // dealerName
    	            dataList.add(row[2] == null ? "-" : row[2].toString().trim());  // location
    	            dataList.add(row[3] == null ? "-" : row[3].toString().trim());  // warrantyClaimNo
    	            dataList.add(row[4] == null ? "-" : row[4].toString().trim());  // partNo
    	            dataList.add(row[5] == null ? "-" : row[5].toString().trim());  // partDesc
    	            dataList.add(row[6] == null ? "-" : row[6].toString().trim());  // engineNo
    	            dataList.add(row[7] == null ? "-" : row[7].toString().trim());  // vinNo
    	            dataList.add(row[8] == null ? "-" : outputFormat.format(inputFormat.parse(row[8].toString().trim())));  // failureDate
    	            dataList.add(row[9] == null ? "-" : row[9].toString().trim());  // failureHrs
    	            dataList.add(row[10] == null ? "-" : row[10].toString().trim());  // failureDesc
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        if (session != null) {
    	            session.close();
    	        }
    	    }
    	    return dataList;
    	}

    public List<WarrantyForm> getViewWarrantyClaimReport(WarrantyForm wf, Vector userFunctionalities, String user_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<WarrantyForm> claimReportList = new ArrayList<WarrantyForm>();
        WarrantyForm warForm = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        

        try {
            Query qry = session.createSQLQuery("EXEC PROC_WarrantyClaimReport :claimNo,:claimStatus,:claimFromDate,:claimToDate,:packingStatus,:dealerCode,:user_id").addScalar("WarrantyClaimNo", StringType.INSTANCE).addScalar("ClaimDate", StringType.INSTANCE).addScalar("JobCardNo", StringType.INSTANCE)
                    .addScalar("TMS_REF_NO", StringType.INSTANCE).addScalar("vinno", StringType.INSTANCE).addScalar("HMR", StringType.INSTANCE).addScalar("DealerCode", StringType.INSTANCE).addScalar("dealerName", StringType.INSTANCE).addScalar("location", StringType.INSTANCE).addScalar("ClaimStatus", StringType.INSTANCE)
                    .addScalar("PackingNo", StringType.INSTANCE).addScalar("PackingDate", StringType.INSTANCE).addScalar("PartsDispatchedFor", StringType.INSTANCE).addScalar("DispatchedThrough", StringType.INSTANCE).addScalar("DispatchedDate", StringType.INSTANCE).addScalar("CourierNo", StringType.INSTANCE).addScalar("acknowlegdedate", StringType.INSTANCE)
                    .addScalar("approvedDate", StringType.INSTANCE).addScalar("SAP_WarrantyClaim", StringType.INSTANCE)
                    .addScalar("jobcardCloseDate", StringType.INSTANCE).addScalar("StateName", StringType.INSTANCE).addScalar("ClaimStatus", StringType.INSTANCE).addScalar("Invoice_No", StringType.INSTANCE).addScalar("Invoice_Date", StringType.INSTANCE)
                    .addScalar("taxInvoiceUploadDate", StringType.INSTANCE).addScalar("taxInvoiceReceivedDate", StringType.INSTANCE)
                    .addScalar("SAP_CreditNo", StringType.INSTANCE).addScalar("SAP_CreditDate", StringType.INSTANCE)
                    .addScalar("SAP_CreditAmount", StringType.INSTANCE).addScalar("jobCardClosureDateToClaimGenrationDate", StringType.INSTANCE).addScalar("claimGenrationDateToClaimDispatchedDate", StringType.INSTANCE)
                   .addScalar("claimReceivedDateToClaimApprovedDate", StringType.INSTANCE).addScalar("claimApprovedDateToTaxInvoiceSubmittedDate", StringType.INSTANCE).addScalar("claimInvoiceSubmittedDateToCreditNoteDate", StringType.INSTANCE)
                   .addScalar("TaxInvoiceStatus", StringType.INSTANCE);
            //Query qry = session.createSQLQuery("EXEC PROC_WarrantyClaimReport :claimNo,:claimStatus,:claimFromDate,:claimToDate,:packingStatus,:dealerCode");
            qry.setParameter("claimNo", wf.getWarrantyClaimNo());
            qry.setParameter("claimStatus", wf.getClaimStatus());
            qry.setParameter("claimFromDate", wf.getFromDate().equals("") == true ? "" : df.format(sdf.parse(wf.getFromDate()+ " 00:00")));
            qry.setParameter("claimToDate", wf.getToDate().equals("") == true ? "" : df.format(sdf.parse(wf.getToDate()+ " 23:59")));
            qry.setParameter("packingStatus", wf.getPackingStatus());
            qry.setParameter("dealerCode", wf.getDealer_code());
            qry.setParameter("user_id", user_id);
            List result = qry.list();
            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                warForm = new WarrantyForm();
                Object[] object = (Object[]) iterator.next();

                warForm.setWarrantyClaimNo(object[0] == null ? "-" : object[0].toString());
                warForm.setClaimDate(object[1] == null ? "-" : sdf.format(df.parse(object[1].toString().trim())));//outputFormat.format(inputFormat.parse(enqobj[8].toString().trim()))
                warForm.setJobCardNo(object[2] == null ? "-" : object[2].toString());
                warForm.setTmsRefNo(object[3] == null ? "-" : object[3].toString());
                warForm.setVinNo(object[4] == null ? "-" : object[4].toString());
                warForm.setHmr(object[5] == null ? "-" : object[5].toString());
                warForm.setDealer_code(object[6] == null ? "-" : object[6].toString());
                warForm.setDealerName(object[7] == null ? "-" : object[7].toString());
                warForm.setLocation(object[8] == null ? "-" : object[8].toString());
                warForm.setClaimStatus(object[9] == null ? "-" : object[9].toString());
                warForm.setPackingNo(object[10] == null ? "-" : object[10].toString());
                warForm.setPackingDate(object[11] == null ? "-" : sdf.format(df.parse(object[11].toString().trim())));
                warForm.setDispatchFor(object[12] == null ? "-" : object[12].toString());
                warForm.setDispatchBy(object[13] == null ? "-" : object[13].toString());
                warForm.setDispatchDate(object[14] == null ? "-" : sdf.format(df.parse(object[14].toString().trim())));
                warForm.setCourierNo(object[15] == null ? "-" : object[15].toString());
                warForm.setAcknowlegdeDate(object[16] == null ? "-" : sdf.format(df.parse(object[16].toString().trim())));
                warForm.setApprovedDate(object[17] == null ? "-" : sdf.format(df.parse(object[17].toString().trim())));
                warForm.setSapWarrantyClaimNo(object[18] == null ? "-" : object[18].toString());
                warForm.setJobcardCloseDate(object[19] == null ? "-" : sdf.format(df.parse(object[19].toString().trim())));
                warForm.setStateName(object[20] == null ? "-" : object[20].toString());
                warForm.setClaimStatus(object[21] == null ? "-" : object[21].toString());
                warForm.setInvNo(object[22] == null ? "-" : object[22].toString());
                warForm.setInvDate(object[23] == null ? "-" : sdf.format(df.parse(object[23].toString().trim())));
                warForm.setTaxInvoiceUploadDate(object[24] == null ? "-" : sdf.format(df.parse(object[24].toString().trim())));
                warForm.setTaxInvoiceReceivedDate(object[25] == null ? "-" : sdf.format(df.parse(object[25].toString().trim())));
                warForm.setSapCreditNo(object[26] == null ? "-" : object[26].toString());
                warForm.setSapCreditDate(object[27] == null ? "-" : sdf.format(df.parse(object[27].toString().trim())));
                warForm.setSapCreditAmount(object[28] == null ? "-" : object[28].toString());
                warForm.setJobCardClosureDateToClaimGenrationDate(object[29] == null ? "-" : object[29].toString());
                warForm.setClaimGenrationDateToClaimDispatchedDate(object[30] == null ? "-" : object[30].toString());
                warForm.setClaimReceivedDateToClaimApprovedDate(object[31] == null ? "-" : object[31].toString());
                warForm.setClaimApprovedDateToTaxInvoiceSubmittedDate(object[32] == null ? "-" : object[32].toString());
                warForm.setClaimInvoiceSubmittedDateToCreditNoteDate(object[33] == null ? "-" : object[33].toString());
                warForm.setTaxInvoiceStatus(object[34] == null ? "-" : object[34].toString());

                claimReportList.add(warForm);
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
        return claimReportList;
    }

    public List<WarrantyForm> getBajajExtendedWarrantyInvList(WarrantyForm wf, Vector userFunctionalities, String user_id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<WarrantyForm> claimReportList = new ArrayList<WarrantyForm>();
        WarrantyForm warForm = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Query qry = session.createSQLQuery("EXEC SP_BajajExtWtyInvViewDetails :vinno,:dealerCode,:userId,:invoiceFromDate,:invoiceToDate,:extClaimNo")
                    .addScalar("ExtWarrantyClaimNo", StringType.INSTANCE)
                    .addScalar("ClaimDate", StringType.INSTANCE)
                    .addScalar("JobCardNo", StringType.INSTANCE)
                    .addScalar("vinno", StringType.INSTANCE)
                    .addScalar("HMR", StringType.INSTANCE)
                    .addScalar("DealerCode", StringType.INSTANCE)
                    .addScalar("dealerName", StringType.INSTANCE)
                    .addScalar("location", StringType.INSTANCE)
                    .addScalar("StateName", StringType.INSTANCE)
                    .addScalar("EW_REF_NO", StringType.INSTANCE)
                    .addScalar("BAJAJ_POLICY_NO", StringType.INSTANCE)
                    .addScalar("BAJAJ_POLICY_DATE", StringType.INSTANCE)
                    .addScalar("invoice_no", StringType.INSTANCE)
                    .addScalar("Invoice_Date", StringType.INSTANCE)
                    .addScalar("TotalClaimAmount", StringType.INSTANCE)
                    .addScalar("AggDesc", StringType.INSTANCE)
                    .addScalar("SubAggDesc", StringType.INSTANCE);
            qry.setParameter("vinno", wf.getVinNo());
            qry.setParameter("dealerCode", wf.getDealer_code());
            qry.setParameter("userId", wf.getUserId());
            if ("1".equals(wf.getRange())) {
                qry.setParameter("invoiceFromDate", wf.getFromDate().equals("") == true ? "" : df.format(sdf.parse(wf.getFromDate() + " 00:00")));
                qry.setParameter("invoiceToDate", wf.getToDate().equals("") == true ? "" : df.format(sdf.parse(wf.getToDate() + " 23:59")));
            } else {
                qry.setParameter("invoiceFromDate", "");
                qry.setParameter("invoiceToDate", "");
            }
            qry.setParameter("extClaimNo", wf.getWarrantyClaimNo());

            List result = qry.list();
            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                warForm = new WarrantyForm();
                Object[] object = (Object[]) iterator.next();

                warForm.setWarrantyClaimNo(object[0] == null ? "-" : object[0].toString());
                warForm.setClaimDate(object[1] == null ? "-" : sdf.format(df.parse(object[1].toString().trim())));
                warForm.setJobCardNo(object[2] == null ? "-" : object[2].toString());
                warForm.setVinNo(object[3] == null ? "-" : object[3].toString());
                warForm.setHmr(object[4] == null ? "-" : object[4].toString());
                warForm.setDealer_code(object[5] == null ? "-" : object[5].toString());
                warForm.setDealerName(object[6] == null ? "-" : object[6].toString());
                warForm.setLocation(object[7] == null ? "-" : object[7].toString());
                warForm.setStateName(object[8] == null ? "-" : object[8].toString());
                warForm.setExtWarrantyBookletNo(object[9] == null ? "-" : object[9].toString());
                warForm.setBajajPolicyNo(object[10] == null ? "-" : object[10].toString());
                warForm.settBajajPolicyDate(object[11] == null ? "-" : sdf.format(df.parse(object[11].toString().trim())));
                warForm.setInvNo(object[12] == null ? "-" : object[12].toString());
                warForm.setInvDate(object[13] == null ? "-" : sdf.format(df.parse(object[13].toString().trim())));
                warForm.setClaimInvoicedAmount(object[14] == null ? "-" : object[14].toString());
                warForm.setAggDisc(object[15] == null ? "-" : object[15].toString());
                warForm.setSubAggDesc(object[16] == null ? "-" : object[16].toString());



                claimReportList.add(warForm);
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
        return claimReportList;
    }
    public String getLabourCharge(BigDecimal taxable,String claimDate) {
        String labourCharges = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            if (PageTemplate.warrantyClaimDateDefault.compareTo(df.format(sdf.parse(claimDate))) <= 0) {
                Query query = hrbsession.createQuery("select lm.labourCharges "
                        + " from MSWLabourmaster lm "
                        + " where lm.fromAmount<=:taxable  and lm.toAmount>=:taxable1 ");
                query.setBigDecimal("taxable", taxable);
                query.setParameter("taxable1", taxable);
                Iterator itr = query.list().iterator();
                if (itr.hasNext()) {
                    labourCharges = itr.next().toString();
                }
            } else {
                labourCharges = "0";
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
        return labourCharges;
    }
    public ArrayList<WarrantyForm> getWarrantyViewHandlingDetail(WarrantyForm wf, String jobCardNo,String flag, Connection con) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
        ResultSet rs = null;
        String hql = "";
        DecimalFormat df = new DecimalFormat("#.####");
        int i=0;
        String handCharge="",gstAmt="";
        try {

            CallableStatement cs = con.prepareCall("{call SP_WCPrintInvDTLview(?,?)}");
            cs.setString(1, jobCardNo);
            cs.setString(2, flag);
            rs = cs.executeQuery();
            while (rs.next()) {
                WarrantyForm warrantyForm = new WarrantyForm();
                warrantyForm.setPartDesc(rs.getString("PART_DESC") == null ? "" : rs.getString("PART_DESC").trim().toUpperCase());
                warrantyForm.setHsnCode(rs.getString("HSNCODE") == null ? "" : rs.getString("HSNCODE").toString().trim());
                warrantyForm.setTaxableValue(rs.getString("APPROVEDTAXABLEVALUE") == null ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDTAXABLEVALUE").toString().trim())));
                warrantyForm.setCgstRate(rs.getString("APPROVEDCGSTRATE") == null ? "0" : rs.getString("APPROVEDCGSTRATE").toString().trim());
                warrantyForm.setCgstAmt(rs.getString("APPROVEDCGSTAMT") == null ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDCGSTAMT").toString().trim())));
                warrantyForm.setSgstRate(rs.getString("APPROVEDSGSTRATE") == null ? "0" : rs.getString("APPROVEDSGSTRATE").toString().trim());
                warrantyForm.setSgstAmt(rs.getString("APPROVEDSGSTAMT") == null ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDSGSTAMT").toString().trim())));
                warrantyForm.setUgstRate(rs.getString("APPROVEDUGSTRATE") == null ? "0" : rs.getString("APPROVEDUGSTRATE").toString().trim());
                warrantyForm.setUgstAmt(rs.getString("APPROVEDUGSTAMT") == null ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDUGSTAMT").toString().trim())));
                warrantyForm.setIgstRate(rs.getString("APPROVEDIGSTRATE") == null ? "0" : rs.getString("APPROVEDIGSTRATE").toString().trim());
                warrantyForm.setIgstAmt(rs.getString("APPROVEDIGSTAMT") == null ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDIGSTAMT").toString().trim())));
                warrantyForm.setClaimValue(rs.getString("TotalClaimValue") == null || rs.getString("TotalClaimValue").equals("")  ? "0" : df.format(Double.parseDouble(rs.getString("TotalClaimValue").toString().trim())));
                warrantyForm.setTotalTaxableValue(rs.getString("APPROVEDTOTALTAXABLEVALUE") == null || rs.getString("APPROVEDTOTALTAXABLEVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDTOTALTAXABLEVALUE").toString().trim())));
                warrantyForm.setTotalTaxValue(rs.getString("APPROVEDTOTALTAXVALUE") == null || rs.getString("APPROVEDTOTALTAXVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDTOTALTAXVALUE").toString().trim())));
                warrantyForm.setTotalInvoiceAmount(rs.getString("APPROVEDTOTALINVOICEAMOUNT") == null || rs.getString("APPROVEDTOTALINVOICEAMOUNT").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDTOTALINVOICEAMOUNT").toString().trim())));
               
                handCharge = rs.getString("APPROVEDTAXABLEVALUE") == null || rs.getString("APPROVEDTAXABLEVALUE").equals("") ? "0" : df.format(Double.parseDouble(rs.getString("APPROVEDTAXABLEVALUE").toString().trim()));
                gstAmt = df.format(Double.parseDouble(rs.getString("APPROVEDCGSTAMT").toString().trim())
                        + Double.parseDouble(rs.getString("APPROVEDSGSTAMT").toString().trim())
                        + Double.parseDouble(rs.getString("APPROVEDUGSTAMT").toString().trim())
                        + Double.parseDouble(rs.getString("APPROVEDIGSTAMT").toString().trim()));
                if (i == 0) {
                    warrantyForm.setAppHanCharge(handCharge);
                    warrantyForm.setGstOnHandling(gstAmt);
                } else if (i == 1) {
                    warrantyForm.setInsuranceCharges(handCharge);
                    warrantyForm.setGstOnInsurance(gstAmt);
                }
                // warrantyForm.setDealerName(enqobj[16] == null ? "" : enqobj[16].toString().trim());

                dataList.add(warrantyForm);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(session!=null)
                {
                    session.close();
                    session = null;
                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return dataList;
    }
    public void getClaimInvoiceNo(WarrantyForm warrantyForm, Vector userFunctionalities, String userId) {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");

        try {
            query = hrbsession.createSQLQuery("select distinct INVOICE_NO,INVOICE_DATE,WarrantyClaimNo from SW_warrantyClaimInvoiceUpdate where WarrantyClaimNo='" + warrantyForm.getWarrantyClaimNo() + "'").addScalar("INVOICE_NO", StringType.INSTANCE).addScalar("INVOICE_DATE", StringType.INSTANCE).addScalar("WarrantyClaimNo", StringType.INSTANCE);
            Iterator itr = query.list().iterator();
            if (itr.hasNext()) {
                Object obj[] = (Object[]) itr.next();
                warrantyForm.setInvNo(obj[0] == null ? "" : obj[0].toString().trim());
                warrantyForm.setInvDate(obj[1] == null ? "" : outputFormat.format(inputFormat.parse(obj[1].toString().trim())));
                warrantyForm.setWarrantyClaimNo(obj[2] == null ? "" : obj[2].toString().trim());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (hrbsession != null) {
                try {
                    if (hrbsession != null) {
                        hrbsession.close();
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
    public String saveWrrantyInvoice(String dealerCode, WarrantyForm warrantyForm, String updatedBy)throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String messg = "";
        try {
            java.util.Date today = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(today);
            today = sdf.parse(s);
            session.beginTransaction();

           
            java.util.Date inviceDate = sdf1.parse(warrantyForm.getInvDate());
            SQLQuery query = session.createSQLQuery("Select distinct * from SW_warrantyClaimInvoiceUpdate where WarrantyClaimNo='"+warrantyForm.getWarrantyClaimNo().trim()+"'");
            List<Integer> list1 = query.list();
            if (list1 != null && list1.size() > 0) {
            Query update = session.createSQLQuery("UPDATE SW_WARRANTYCLAIMINVOICEUPDATE SET INVOICE_NO=:INVOICE_NO,INVOICE_DATE=:INVOICE_DATE,CreatedBy=:CreatedBy,CreatedOn=:CreatedOn where WarrantyClaimNo=:WarrantyClaimNo");
                update.setString("INVOICE_NO", warrantyForm.getInvNo().trim());
                update.setDate("INVOICE_DATE", inviceDate);
                update.setString("CreatedBy", dealerCode.trim());
                update.setDate("CreatedOn", today);
                update.setString("WarrantyClaimNo", warrantyForm.getWarrantyClaimNo().trim());
                update.executeUpdate();
                messg = "Updated";
            } else {
                Query insert = session.createSQLQuery("INSERT INTO SW_WARRANTYCLAIMINVOICEUPDATE (WARRANTYCLAIMNO, INVOICE_NO,INVOICE_DATE,CreatedBy,CreatedOn) VALUES (:WarrantyClaimNo,:INVOICE_NO,:INVOICE_DATE,:CreatedBy,:CreatedOn)");
                insert.setString("WarrantyClaimNo", warrantyForm.getWarrantyClaimNo().trim());
                insert.setString("INVOICE_NO", warrantyForm.getInvNo().trim());
                insert.setDate("INVOICE_DATE", inviceDate);
                insert.setString("CreatedBy", dealerCode.trim());
                insert.setDate("CreatedOn", new java.util.Date());
                insert.executeUpdate();
                messg = "Created";
            }
            
                session.getTransaction().commit();
            
        } catch (Exception ae) {
            ae.printStackTrace();
            messg = "failure";
            session.getTransaction().rollback();
        } 
            finally {
            if (session != null) {
                try {
                    if (session != null) {
                        session.close();
                    }
                } catch (Exception ee) {
                    messg = "failure";
                    ee.printStackTrace();
                }
            }
        }
        return messg;
    }
    
    public List<WarrantyForm> getDealerCodeWithName(String userId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<WarrantyForm> dataList = new ArrayList<>();
        try {
        	String sql = "Select * from FN_GetDealersDetailsUnderUser(:userId) order by DEALER_NAME";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("userId", userId);

			query.addScalar("DEALER_CODE", StandardBasicTypes.STRING).addScalar("DEALER_NAME", StandardBasicTypes.STRING).addScalar("LOCATION", StandardBasicTypes.STRING);
			List<Object[]> list = query.list();
	        dataList = list.stream().map(dlobj -> {
	            WarrantyForm warrantyForm = new WarrantyForm();
	            warrantyForm.setDealer_code(dlobj[0] == null ? "" : dlobj[0].toString().trim());
	            warrantyForm.setDealerName(dlobj[1] == null ? "" : dlobj[1].toString().trim());
	            warrantyForm.setLocation(dlobj[2] == null ? "" : dlobj[2].toString().trim());
	            return warrantyForm;
	        }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
		finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        return dataList;
    }
    
   
	public ArrayList<WarrantyForm> getMassPackingDataForAcknow(WarrantyForm warrantyForm, String flag)
				throws SQLException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");

			String hql = "";
			String packHql = "";
			try {

				packHql = "Select wp.packingNo,wp.packingDate,wp.partsDispatchedFor ,"
						+ " wp.dispatchedThrough,wp.dispatchedDate,wp.courierNo,wp.courierName,wp.packingStatus, "
						+ " wp.courierCopyFile,wp.receivedBy,wp.receivedOn,wp.dealerCode,wp.storeNoOfPackages "
						+ " from SWwarrantypackingmaster wp where wp.packingNo=:packingNo ";

				Query queryPack = session.createQuery(packHql);
				queryPack.setString("packingNo", warrantyForm.getPackingNo());
				Iterator itPack = queryPack.list().iterator();
				while (itPack.hasNext()) {
					Object enqobj[] = (Object[]) itPack.next();
					warrantyForm.setPackingNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
					warrantyForm.setPackingDate(enqobj[1] == null ? "-"
							: outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
					warrantyForm.setDispatchFor(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
					warrantyForm.setDispatchBy(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
					warrantyForm.setDispatchDate(enqobj[4] == null ? "-"
							: outputFormat.format(inputFormat.parse(enqobj[4].toString().trim())));
					warrantyForm.setCourierNo(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
					warrantyForm.setCourierName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
					warrantyForm.setCourierCopyFileName(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
					warrantyForm.setReceivedBy(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
					warrantyForm.setReceivedDate(enqobj[10] == null ? "-"
							: outputFormat.format(inputFormat.parse(enqobj[10].toString().trim())));
					warrantyForm.setDealer_code(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
					warrantyForm.setStoreNoOfPackages(enqobj[12] == null ? 1 : Integer.parseInt(enqobj[12].toString()));

				}

				hql = "Select wc.warrantyClaimNo,wc.partNo,wc.partDesc,wc.qty,wc.packingNo,wc.dispatchedQty,wc.boxNo,wc.qtyReceived  "
						+ " from SWwarrantypackingmaster wp,Warrantyclaimdetails wc where "
						+ " wp.packingNo=wc.packingNo and wp.packingNo=:packingNo order by wp.packingNo desc";
				Query query = session.createQuery(hql);
				query.setString("packingNo", warrantyForm.getPackingNo());
				Iterator it = query.list().iterator();
				while (it.hasNext()) {
					Object enqobj[] = (Object[]) it.next();
					WarrantyForm wf = new WarrantyForm();

					wf.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
					wf.setPartNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
					wf.setPartDesc(enqobj[2] == null ? "-" : enqobj[2].toString().trim().toUpperCase());

					// wf.setQty(enqobj[3] == null ? "" : enqobj[3].toString().trim());

					double t = enqobj[3] == null ? 0.0 : Double.parseDouble(enqobj[3].toString().trim());
					if (t == Math.ceil(t)) {
						wf.setQty("" + ((int) t));
					} else {
						wf.setQty(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
					}

					wf.setPackingNo(enqobj[4] == null ? "-" : enqobj[4].toString().trim());

					double tt = enqobj[5] == null ? 0.0 : Double.parseDouble(enqobj[5].toString().trim());
					if (tt == Math.ceil(tt)) {
						wf.setDispatchQty("" + ((int) tt));
					} else {
						wf.setDispatchQty(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
					}

					// wf.setDispatchQty(enqobj[5] == null ? "" : enqobj[5].toString().trim());
					wf.setBoxNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());

					if (flag.equals("ack")) {
						wf.setReceivedQty(enqobj[7] == null ? "" : enqobj[7].toString().trim());
					} else {
						double ttt = enqobj[7] == null ? 0.0 : Double.parseDouble(enqobj[7].toString().trim());
						if (ttt == Math.ceil(ttt)) {
							wf.setReceivedQty("" + ((int) ttt));
						} else {
							wf.setReceivedQty(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
						}
					}

					dataList.add(wf);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (session != null) {
						session.close();
						session = null;
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
			return dataList;
		}

	
	public List<String> getWarrantyClaimNoList(String fromDate, String toDate, String dealerCode) {
	    Session session = null;
	    List<String> dataList = new ArrayList<>();
	    
	    // Define date format for parsing
	    DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Input date format
	    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Output date format for display or SQL

	    // Convert input dates to Date type
	    Date fromDateParsed = null;
	    Date toDateParsed = null;
	    String fromDateFormatted = null;
	    String toDateFormatted = null;

	    try {
	        fromDateParsed = inputFormat.parse(fromDate); // Use input format for parsing
	        toDateParsed = inputFormat.parse(toDate);

	        // Convert dates to desired format
	        fromDateFormatted = outputFormat.format(fromDateParsed);
	        toDateFormatted = outputFormat.format(toDateParsed);

	    } catch (ParseException e) {
	        e.printStackTrace();
	        return dataList; // Return empty list if date parsing fails
	    }
	    
	    System.out.println(fromDateFormatted + " " + toDateFormatted);

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        String sql = "SELECT WarrantyClaimNo FROM SW_warranty_claim(nolock) WHERE DealerCode = :dealerCode AND SAP_CreditDate BETWEEN :fromDate AND :toDate and ClaimStatus='APPROVED'";
	        
	        Query query = session.createSQLQuery(sql).addScalar("WarrantyClaimNo", StandardBasicTypes.STRING);
	        query.setParameter("dealerCode", dealerCode);
	        query.setParameter("fromDate", fromDateFormatted);
	        query.setParameter("toDate", toDateFormatted);

	        // Execute query and retrieve results as List<Object[]>
	        dataList = query.list();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return dataList;
	}
	
	public ArrayList<WarrantyForm> getWarrantyClaimStatusList(WarrantyForm warrantyForm, Vector userFunctionalities,String user_id) throws SQLException {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

		try {
			String dealerCode = warrantyForm.getDealer_code();


		    // Handle fromDate parsing with the expected input format
		    String fromDate = warrantyForm.getFromDate().equals("") ? null : warrantyForm.getFromDate();
		    String toDate = warrantyForm.getToDate().equals("") ? null : warrantyForm.getToDate();

		    // If fromDate is not null, parse it into the required format
		    if (fromDate != null) {
		        Date parsedFromDate = inputFormat.parse(fromDate);
		        fromDate = outputFormat.format(parsedFromDate);  // Format into yyyy/MM/dd
		    }

		    // If toDate is not null, parse it into the required format
		    if (toDate != null) {
		        Date parsedToDate = inputFormat.parse(toDate);
		        toDate = outputFormat.format(parsedToDate);  // Format into yyyy/MM/dd
		    }

			// Build the SQL query
			StringBuilder sql = new StringBuilder("EXEC WARRANTY_CLAIM_STATUS_DASHBOARD_VIEW :dealerCode, :fromDate, :toDate,:user_id");
			fromDate = fromDate == null ? "" : fromDate;
			toDate = toDate == null ? "" : toDate;
			

			Query query = session.createSQLQuery(sql.toString())
					.setParameter("dealerCode", dealerCode.equals("") ? "ALL" : dealerCode)
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate)
					.setParameter("user_id", user_id);

			List<Object[]> results = query.list();  // Fetch all results
			if (!results.isEmpty()) {
			    for (Object[] result : results) {  // Iterate over each result
			        WarrantyForm wf = new WarrantyForm();  // Create a new WarrantyForm object for each result

			        // Set the fields of WarrantyForm using the values from the result array
			        wf.setNoOfClaimsPendingForCreation(result[1].toString());
			        wf.setNoOfClaimsCreated(result[2].toString());
			        wf.setNoOfClaimsPacked(result[3].toString());
			        wf.setNoOfClaimsDispatched(result[4].toString());
			        wf.setNoOfClaimsReceivedToWtyTeam(result[5].toString());
			        wf.setNoOfClaimsApproved(result[6].toString());
			        wf.setNoOfClaimsTaxInvoiceReceivedToWtyTeam(result[7].toString());
			        wf.setNoOfClaimsCreditNoteIssued(result[8].toString());
			        dataList.add(wf);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return dataList;

	}

	public ArrayList<WarrantyForm> getTaxInvoiceAcknowledgeList(WarrantyForm warrantyForm, Vector userFunctionalities, String user_id) {
		   Session session = HibernateUtil.getSessionFactory().openSession();
	        ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy ");
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String hql = "";
	        String Subsql = "";
	        String dateSubQur = "";
	        ArrayList<String> dealerList = new ArrayList<String>();
	        try {
	            if (warrantyForm.getPackingNo() != null && !warrantyForm.getPackingNo().equals("")) {
	                Subsql = " and  wp.packingNo like '%" + warrantyForm.getPackingNo() + "%' ";
	            }
	            if ("1".equals(warrantyForm.getRange())) {
	                dateSubQur = " and (wp.taxInvoiceFileDate between isnull(?,wp.taxInvoiceFileDate) and isnull(?,taxInvoiceFileDate)) ";
	            }
	            hql = "Select wp.packingNo,wp.courierNo,wp.courierName "
	                    + " from SWwarrantypackingmaster wp where wp.taxInvoiceStatus !='RECEIVED' and wp.taxInvoiceStatus='SUBMITTED' "
	                    + "  " + Subsql + dateSubQur + " ";  

	            Query query = null;

	            query = session.createQuery(hql +
	                   // " and wp.dealerCode IN(:dealerList)" +
	                    " and :dealerList LIKE ('%'+wp.dealerCode+'%') "+
	                    "  order by wp.packingNo desc");
	            if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().equals("") && !warrantyForm.getDealer_code().equalsIgnoreCase("ALL")) {
	                dealerList.add(warrantyForm.getDealer_code());
	            } else {
	            	dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session,warrantyForm.getUserId());
	            }
	       //     query.setParameterList("dealerList", dealerList);
	            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

	
	            if ("1".equals(warrantyForm.getRange())) {
	                query.setDate(0, warrantyForm.getFromDate().equals("") == true ? null : sdf.parse(warrantyForm.getFromDate()));
	                query.setDate(1, warrantyForm.getToDate().equals("") == true ? null : sdf.parse(warrantyForm.getToDate()));
	            }
	            //  query.setString("dealerCode", warrantyForm.getDealer_code());
	            Iterator it = query.list().iterator();
	            while (it.hasNext()) {
	                Object enqobj[] = (Object[]) it.next();
	                WarrantyForm wf = new WarrantyForm();

	                wf.setPackingNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
	                wf.setCourierNo(enqobj[1] == null ? "NA" : enqobj[1].toString().trim());
	                wf.setCourierName(enqobj[2] == null ? "NA" : enqobj[2].toString().trim());
	                
	                Query qry = session.createSQLQuery("EXEC checkInvoicesIsUploadedForPackingNo :PackingNo");
		            qry.setParameter("PackingNo", wf.getPackingNo());
		            String result = (String) qry.uniqueResult();

		            wf.setIsApproved("TRUE".equalsIgnoreCase(result) ? "Y" : "N");
		            
		            if(wf.getIsApproved().equals("Y"))
		            	dataList.add(wf);
	 
	              
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	            try
	            {
	                if(session!=null)
	                {
	                    session.close();
	                    session = null;
	                }
	            }
	            catch(Exception ee)
	            {
	                ee.printStackTrace();
	            }
	        }
	        return dataList;
	}

	public String uploadTaxInvoice(WarrantyForm wf, String packingNo, String realPath, FormFile uploadedFile) {
	    Session session = null;
	    org.hibernate.Transaction transaction = null;
	    String result = "FAILURE";
	    
	    try {
	        
	        String filename = "";
            String dest = "";
            FormFile pdf = null;
            File pdfFile = null;
            boolean isFileUploaded = false;
            wf.setDestinationpath("");

            if (wf.getUploadTaxInvoice() != null && !wf.getUploadTaxInvoice().equals(""))
            {

                File dest_folder = null;
                dest_folder = new File(wf.getRealPath() + "/DOCUMENTS/WARRANTY/TAX INVOICE/");
                if (!dest_folder.exists())
                {
                    dest_folder.mkdirs();
                }
                dest = wf.getRealPath() + "/DOCUMENTS/WARRANTY/TAX INVOICE/";
                pdf = wf.getUploadTaxInvoice();
                filename = pdf.getFileName();
               
             // Ensure the file has a proper extension
                int lastIndex = filename.lastIndexOf(".");
                String fileext = lastIndex > 0 ? filename.substring(lastIndex) : ".pdf"; // Default to .pdf if no extension
                filename = packingNo.replace("/", "-") + fileext.toLowerCase();
                
                File existingFile = new File(dest + filename);
                if (existingFile.exists()) {
                    // Delete the existing file
                    existingFile.delete();
                    System.out.println("Existing file deleted: " + filename);
                }

                // Attempt to upload the file
                isFileUploaded = uploadFile(filename, dest, pdf, realPath);
                if (isFileUploaded) {
                    wf.setDestinationpath(dest + filename); // Set the destination path
                }
            }
	        

            session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction(); 

	        SWwarrantypackingmaster pmDate = (SWwarrantypackingmaster) session.get(SWwarrantypackingmaster.class, packingNo);

	        if (pmDate != null) {
	        	pmDate.setTaxInvoiceFile(filename);
	            pmDate.setTaxInvoiceStatus("SUBMITTED");
	            pmDate.setTaxInvoiceFileDate(new Date()); 
	            session.update(pmDate); 
	            transaction.commit(); 
	            return "SUCCESS";
	        } 
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); 
	        }
	        e.printStackTrace();
	        return "ERROR";
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }

	    return result;
	}

	
	public ArrayList<WarrantyForm> getTaxInvoiceDataForAcknow(WarrantyForm warrantyForm) throws SQLException {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    ArrayList<WarrantyForm> dataList = new ArrayList<>();
	    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Format date as 11-Dec-2024

	    try {
	        // HQL Query to fetch packing details
	        String packHql = "SELECT wp.packingNo, wp.packingDate, wp.partsDispatchedFor, wp.dispatchedThrough, "
	                       + "wp.dispatchedDate, wp.courierNo, wp.courierName, wp.packingStatus, wp.courierCopyFile, "
	                       + "wp.receivedBy, wp.receivedOn, wp.dealerCode, wp.storeNoOfPackages "
	                       + "FROM SWwarrantypackingmaster wp WHERE wp.packingNo = :packingNo";

	        Query queryPack = session.createQuery(packHql);
	        queryPack.setParameter("packingNo", warrantyForm.getPackingNo());

	        Iterator<?> itPack = queryPack.list().iterator();
	        while (itPack.hasNext()) {
	            Object[] enqobj = (Object[]) itPack.next();

	            warrantyForm.setPackingNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
	            warrantyForm.setPackingDate(enqobj[1] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[1].toString().trim())));
	            warrantyForm.setDispatchFor(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
	            warrantyForm.setDispatchBy(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
	            warrantyForm.setDispatchDate(enqobj[4] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[4].toString().trim())));
	            warrantyForm.setCourierNo(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
	            warrantyForm.setCourierName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
	            warrantyForm.setCourierCopyFileName(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
	            warrantyForm.setReceivedBy(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
	            warrantyForm.setReceivedDate(enqobj[10] == null ? "-" : outputFormat.format(inputFormat.parse(enqobj[10].toString().trim())));
	            warrantyForm.setDealer_code(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
	            warrantyForm.setStoreNoOfPackages(enqobj[12] == null ? 1 : Integer.parseInt(enqobj[12].toString()));
	            dataList.add(warrantyForm);
	        }	     
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return dataList;
	}

	public String updateStatusOfTaxInvoices(String warrantyClaims, String taxInvoiceStatus, String packingNo) {
	    String res = "FAILURE";
	    Session session = null;
	    org.hibernate.Transaction transaction = null;

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction(); 
	        String query =  "UPDATE SW_warranty_claim SET taxInvoiceStatus='RECEIVED',taxInvoiceStatusDate=getdate() WHERE WarrantyClaimNo =:wc";
	        
	        List<String> warrantyClaimsList = Arrays.asList(warrantyClaims.split(","));
	        for(String wc : warrantyClaimsList) {
	        	session.createSQLQuery(query).setParameter("wc",wc).executeUpdate();
	        }
	        
	        SWwarrantypackingmaster pmDate = (SWwarrantypackingmaster) session.get(SWwarrantypackingmaster.class, packingNo);

	        if (pmDate != null) {
	            pmDate.setTaxInvoiceStatus(taxInvoiceStatus);
	            pmDate.setTaxInvoiceAcknowledgeDate(new Date()); 
	            session.update(pmDate); 
	            transaction.commit(); 
	            res = "SUCCESS";
	        } else {
	            System.out.println("No record found with packingNo: " + packingNo);
	            res = "NO_RECORD_FOUND";
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); 
	        }
	        e.printStackTrace();
	        res = "ERROR";
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	    return res;
	}
	
	public boolean uploadFile(String fileName, String filePath, FormFile uploadFile, String realPath)
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
	
	
	public ArrayList<WarrantyForm> getviewTaxInvoiceUploadListBackup(WarrantyForm wForm, Vector userFunctionalities) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        ArrayList<WarrantyForm> dispachedList = new ArrayList<WarrantyForm>();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        String Subsql = ""; String dateSubQur="";
	        ArrayList<String> dealerList=new ArrayList<String>();
	        try {
	             if (wForm.getPackingNo()!=null && !wForm.getPackingNo().equals("")) {
	                Subsql = " and  sw.packingNo like '%" + wForm.getPackingNo() + "%' ";
	            }
	            if("1".equals(wForm.getRange()) ){
	               dateSubQur=" and (sw.packingDate between isnull(?,sw.packingDate) and isnull(?,sw.packingDate)) ";
	            }
	            String hql="from SWwarrantypackingmaster sw" +
	                    " where (sw.taxInvoiceStatus != 'RECEIVED' or sw.taxInvoiceStatus is null) " + Subsql + dateSubQur;
	                    ;
	            Query query = null;

	            query = session.createQuery(hql +" " +
	                    //"and sw.dealerCode IN(:dealerList)"
	                    " and :dealerList LIKE ('%'+sw.dealerCode+'%') "
	                    + Subsql + "  order by sw.packingDate desc,sw.dealerCode");
	            if (wForm.getDealer_code() != null && !wForm.getDealer_code().equals("") && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
	                dealerList.add(wForm.getDealer_code());
	            } else {
	                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session,wForm.getUserId());
	            }
	          //  query.setParameterList("dealerList", dealerList);

	            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

	             query.setString(0, wForm.getTaxInvoiceStatus());
	             if("1".equals(wForm.getRange()) ){
	            query.setDate(1, wForm.getFromDate().equals("") == true ? null : sdf.parse(wForm.getFromDate()));
	            query.setDate(2, wForm.getToDate().equals("") == true ? null : sdf.parse(wForm.getToDate()));
	            }
	            List list = query.list();
	            Iterator itr = list.iterator();
	            while (itr.hasNext()) {
	                SWwarrantypackingmaster obj = (SWwarrantypackingmaster) itr.next();
	                WarrantyForm wf = new WarrantyForm();
	                wf.setPackingNo(obj.getPackingNo() == null ? "-" : obj.getPackingNo());
	                wf.setPackingDate(obj.getPackingDate() == null ? "-" : sdf.format(obj.getPackingDate()));
	                wf.setDispatchFor(obj.getPartsDispatchedFor() == null ? "-" : obj.getPartsDispatchedFor());
	                wf.setDispatchBy(obj.getDispatchedThrough() == null ? "-" : obj.getDispatchedThrough());
	                wf.setCourierNo(obj.getCourierNo() == null ? "-" : obj.getCourierNo());
	                wf.setCourierName(obj.getCourierName() == null ? "-" : obj.getCourierName());
	                wf.setCourierCopyFileName(obj.getCourierCopyFile() == null ? "-" :obj.getCourierCopyFile().equals("")==true?"-": obj.getCourierCopyFile());
	                wf.setTaxInvoiceStatus(obj.getTaxInvoiceStatus() == null ? "-" : obj.getTaxInvoiceStatus());
	                
	                Query qry = session.createSQLQuery("EXEC CheckClaimStatusByPackingNo :PackingNo");
	                qry.setParameter("PackingNo", wf.getPackingNo());
	     
	                String result = (String) qry.uniqueResult();

	                if ("TRUE".equalsIgnoreCase(result)) {
	                    wf.setIsApproved("Y");
	                } else {
	                	wf.setIsApproved("N");
	                }
	                
	                dispachedList.add(wf);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally
	        {
	            try
	            {
	                if(session!=null)
	                {
	                    session.close();
	                    session = null;
	                }
	            }
	            catch(Exception ee)
	            {
	                ee.printStackTrace();
	            }
	        }
	        return dispachedList;
	}
	
	public ArrayList<WarrantyForm> getviewTaxInvoiceUploadList(WarrantyForm wForm, Vector userFunctionalities) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    ArrayList<WarrantyForm> dispachedList = new ArrayList<>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String subSql = "";
	    String dateSubQuery = "";
	    String statusSubQuery = "";
	    ArrayList<String> dealerList = new ArrayList<>();

	    try {
	        // Building sub-query for packing number if available
	        if (wForm.getPackingNo() != null && !wForm.getPackingNo().isEmpty()) {
	            subSql = " and sw.packingNo like :packingNo ";
	        }

	        // Handling date range if range is "1"
	        if ("1".equals(wForm.getRange())) {
	            dateSubQuery = " and (sw.packingDate between :fromDate and :toDate) ";
	        }

	        // Adding condition for taxInvoiceStatus if provided
	        if (wForm.getTaxInvoiceStatus() != null && !wForm.getTaxInvoiceStatus().isEmpty()) {
	            statusSubQuery = " and sw.taxInvoiceStatus = :taxInvoiceStatus ";
	        }

	        // Main HQL query
	        String hql = "from SWwarrantypackingmaster sw " +
	                "where (sw.taxInvoiceStatus != 'RECEIVED' or sw.taxInvoiceStatus is null) " +
	                subSql + dateSubQuery + statusSubQuery +
	                " and :dealerList LIKE ('%' + sw.dealerCode + '%')" +
	                "order by sw.packingDate desc, sw.dealerCode";

	        Query query = session.createQuery(hql);

	        // Set parameter for packing number if provided
	        if (wForm.getPackingNo() != null && !wForm.getPackingNo().isEmpty()) {
	            query.setParameter("packingNo", "%" + wForm.getPackingNo() + "%");
	        }

	        // Set parameter for taxInvoiceStatus if provided
	        if (wForm.getTaxInvoiceStatus() != null && !wForm.getTaxInvoiceStatus().isEmpty()) {
	            query.setParameter("taxInvoiceStatus", wForm.getTaxInvoiceStatus());
	        }

	        // Set dealer list
	        if (wForm.getDealer_code() != null && !wForm.getDealer_code().isEmpty() && !wForm.getDealer_code().equalsIgnoreCase("ALL")) {
	            dealerList.add(wForm.getDealer_code());
	        } else {
	            dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, wForm.getUserId());
	        }
	        query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));

	        // Set date range parameters if range is "1"
	        if ("1".equals(wForm.getRange())) {
	            query.setParameter("fromDate", wForm.getFromDate().isEmpty() ? null : sdf.parse(wForm.getFromDate()));
	            query.setParameter("toDate", wForm.getToDate().isEmpty() ? null : sdf.parse(wForm.getToDate()));
	        }

	        // Fetching results
	        List<SWwarrantypackingmaster> list = query.list();
	        for (SWwarrantypackingmaster obj : list) {
	            WarrantyForm wf = new WarrantyForm();
	            wf.setPackingNo(obj.getPackingNo() == null ? "-" : obj.getPackingNo());
	            wf.setPackingDate(obj.getPackingDate() == null ? "-" : sdf.format(obj.getPackingDate()));
	            wf.setDispatchFor(obj.getPartsDispatchedFor() == null ? "-" : obj.getPartsDispatchedFor());
	            wf.setDispatchBy(obj.getDispatchedThrough() == null ? "-" : obj.getDispatchedThrough());
	            wf.setCourierNo(obj.getCourierNo() == null ? "-" : obj.getCourierNo());
	            wf.setCourierName(obj.getCourierName() == null ? "-" : obj.getCourierName());
	            wf.setCourierCopyFileName(obj.getCourierCopyFile() == null || obj.getCourierCopyFile().isEmpty() ? "-" : obj.getCourierCopyFile());
	            wf.setTaxInvoiceStatus(obj.getTaxInvoiceStatus() == null ? "-" : obj.getTaxInvoiceStatus());

	            // Execute CheckClaimStatusCredited stored procedure for each item
	            Query qry = session.createSQLQuery("EXEC CheckClaimStatusCredited :PackingNo");
	            qry.setParameter("PackingNo", wf.getPackingNo());
	            String result = (String) qry.uniqueResult();

	            wf.setIsApproved("TRUE".equalsIgnoreCase(result) ? "Y" : "N");
	            
	            if(wf.getIsApproved().equals("Y"))
	            dispachedList.add(wf);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return dispachedList;
	}


	
	public ArrayList<WarrantyForm> getTaxInvoiceStatusList(WarrantyForm warrantyForm, Vector userFunctionalities, String user_id) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    ArrayList<WarrantyForm> dataList = new ArrayList<WarrantyForm>();
	    String originalDate = "08/11/2024";
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
  
		Date fromDate = null;
		Date toDate = null;
		String formattedFromDate = "",formattedToDate="";
		if (!warrantyForm.getFromDate().isEmpty() && !warrantyForm.getToDate().isEmpty() && warrantyForm.getFromDate() != null && warrantyForm.getToDate() != null) {
			try {
				fromDate = inputFormat.parse(warrantyForm.getFromDate());
				toDate = inputFormat.parse(warrantyForm.getToDate());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

         formattedFromDate = outputFormat.format(fromDate);
         formattedToDate = outputFormat.format(toDate);
		}
	    
	    try {
	        // Build base query
	        StringBuilder sb = new StringBuilder();
	        sb.append("select PackingNo, CourierNo, CourierName, taxInvoiceStatus from SW_warranty_packing_master(nolock) ");

	        // Track if we need to add "where" or "and"
	        boolean hasWhereClause = false;
	   //     warrantyForm.setTaxInvoiceStatus("RECEIVED");

	        // Filter by dealer code if provided
	        if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().isEmpty() && !warrantyForm.getDealer_code().equals("ALL")) {
	            sb.append("where DealerCode = :dealerCode ");
	            hasWhereClause = true;
	        }

	        // Filter by tax invoice status if provided
	        if (warrantyForm.getTaxInvoiceStatus() != null && !warrantyForm.getTaxInvoiceStatus().isEmpty()) {
	            if (hasWhereClause) {
	                sb.append("and ");
	            } else {
	                sb.append("where ");
	                hasWhereClause = true;
	            }
	            sb.append("taxInvoiceStatus = :taxInvoiceStatus ");
	        }

	        // Filter by PackingNo if provided
	        if (warrantyForm.getPackingNo() != null && !warrantyForm.getPackingNo().isEmpty()) {
	            if (hasWhereClause) {
	                sb.append("and ");
	            } else {
	                sb.append("where ");
	                hasWhereClause = true;
	            }
	            sb.append("PackingNo = :packingNo ");
	        }

	        // Filter by date range if provided
	        if (!warrantyForm.getFromDate().isEmpty() && !warrantyForm.getToDate().isEmpty() && warrantyForm.getFromDate() != null && warrantyForm.getToDate() != null) {
	            if (hasWhereClause) {
	                sb.append("and ");
	            } else {
	                sb.append("where ");
	            }
	            sb.append("taxInvoiceAcknowledgeDate between :fromDate and :toDate ");
	        }
	        
	        sb.append("order by dealercode, taxInvoiceAcknowledgeDate desc");

	        // Create the query
	        System.out.println("Query for getTaxInvoiceStatusList :"+ sb.toString());
	        Query query = session.createSQLQuery(sb.toString())
	                .addScalar("PackingNo", StandardBasicTypes.STRING)
	                .addScalar("CourierNo", StandardBasicTypes.STRING)
	                .addScalar("CourierName", StandardBasicTypes.STRING)
	                .addScalar("taxInvoiceStatus", StandardBasicTypes.STRING);
	        
	        

	        // Set parameters
	        if (warrantyForm.getDealer_code() != null && !warrantyForm.getDealer_code().isEmpty() && !warrantyForm.getDealer_code().equals("ALL")) {
	            query.setParameter("dealerCode", warrantyForm.getDealer_code());
	        }
	        if (warrantyForm.getTaxInvoiceStatus() != null && !warrantyForm.getTaxInvoiceStatus().isEmpty()) {
	            query.setParameter("taxInvoiceStatus", warrantyForm.getTaxInvoiceStatus());
	        }
	        if (warrantyForm.getPackingNo() != null && !warrantyForm.getPackingNo().isEmpty()) {
	            query.setParameter("packingNo", warrantyForm.getPackingNo());
	        }
	        if (!warrantyForm.getFromDate().isEmpty() && !warrantyForm.getToDate().isEmpty() && warrantyForm.getFromDate() != null && warrantyForm.getToDate() != null) {
	            query.setParameter("fromDate", formattedFromDate);
	            query.setParameter("toDate", formattedToDate);
	        }

	        // Execute query and iterate over results to populate dataList
	        Iterator it = query.list().iterator();
	        while (it.hasNext()) {
	            Object[] enqobj = (Object[]) it.next();
	            WarrantyForm wf = new WarrantyForm();
	            wf.setPackingNo(enqobj[0] == null ? "" : enqobj[0].toString().trim());
	            wf.setCourierNo(enqobj[1] == null ? "NA" : enqobj[1].toString().trim());
	            wf.setCourierName(enqobj[2] == null ? "NA" : enqobj[2].toString().trim());
	            wf.setTaxInvoiceStatus(enqobj[3] == null ? "" : enqobj[3].toString().trim());
	            dataList.add(wf);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return dataList;
	}


	public ArrayList<WarrantyForm> getClaimInvoiceListForPackingNo(String packingNo) {

	    ArrayList<WarrantyForm> dataList = new ArrayList<>();
	    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Format date as 11-Dec-2024

	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();

	        String sqlQuery = "EXEC TaxInvoiceAcknowledgeForPackingNo :packingNo";

	        Query queryPack = session.createSQLQuery(sqlQuery)
	                .addScalar("WarrantyClaimNo", StandardBasicTypes.STRING)
	                .addScalar("Invoice_No", StandardBasicTypes.STRING)
	                .addScalar("Invoice_Date", StandardBasicTypes.TIMESTAMP)
	                .addScalar("taxInvoiceStatus", StandardBasicTypes.STRING)
	                .addScalar("ClaimDate", StandardBasicTypes.TIMESTAMP);
	        
	        queryPack.setParameter("packingNo", packingNo);

	        List<?> results = queryPack.list();
	        for (Object result : results) {
	            Object[] enqobj = (Object[]) result;

	            WarrantyForm form = new WarrantyForm();
	            form.setWarrantyClaimNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
	            form.setInvNo(enqobj[1] == null ? "-" : enqobj[1].toString().trim());

	            // Safely parse date if available
	            if (enqobj[2] != null) {
	                try {
	                    form.setInvDate(outputFormat.format(inputFormat.parse(enqobj[2].toString().trim())));
	                } catch (ParseException e) {
	                    form.setInvDate("-"); // Set as '-' if date parsing fails
	                }
	            } else {
	                form.setInvDate("-");
	            }
	            
	            form.setTaxInvoiceStatus(enqobj[3] == null ? "" : enqobj[3].toString().trim());
	            
	            
	            if (enqobj[4] != null) {
	                try {
	                    form.setClaimDate(outputFormat.format(inputFormat.parse(enqobj[4].toString().trim())));
	                } catch (ParseException e) {
	                    form.setClaimDate("-"); // Set as '-' if date parsing fails
	                }
	            } else {
	                form.setClaimDate("-");
	            }
	            

	            dataList.add(form);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return dataList;
	}


	public String getTaxInvoiceFileName(String packingNo) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		String fileName = "";

		try {
			SWwarrantypackingmaster pmDate = (SWwarrantypackingmaster) session.get(SWwarrantypackingmaster.class,
					packingNo);
			

			if (pmDate != null) {
				fileName = pmDate.getTaxInvoiceFile();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return fileName;
	}


	public List<String> getWarrantyClaimsOnPackingNo(String packingNo) {
		Session session = null;
		List<String> dataList = new ArrayList<>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "SELECT distinct WarrantyClaimNo FROM SW_warranty_claim_details(nolock) WHERE PackingNo=:packingNo";

			Query query = session.createSQLQuery(sql).addScalar("WarrantyClaimNo", StandardBasicTypes.STRING);
			query.setParameter("packingNo", packingNo);

			dataList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return dataList;
	}



	public String getTaxType(String dealerCode, Connection conn) {

	    Session session = null;
	    String taxType = null;
	    System.out.println("dealerCode "+dealerCode);

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        String sql = "EXEC GetTaxTypeByDealerCode ?";

	        Query query = session.createSQLQuery(sql)
	            .addScalar("DealerCode", StandardBasicTypes.STRING)
	            .addScalar("TaxType", StandardBasicTypes.STRING);

	        query.setParameter(0, dealerCode);

	        Object[] result = (Object[]) query.uniqueResult();

	        if (result != null && result.length > 1) {
	            taxType = (String) result[1];
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
        
	    System.out.println("Inside getTaxType at line 4634");
	    System.out.println("taxType "+taxType);
	    return taxType;
	}
	
	public String uploadSignedTaxInvoiceInDB(WarrantyForm wf, String claimNo, String uuid, String realPath, File signedPdfFile) {
	    Session session = null;
	    Transaction transaction = null;
	    try {
	        if (signedPdfFile == null || !signedPdfFile.exists()) return "ERROR";

	        String filename = signedPdfFile.getName();
	        wf.setDestinationpath(signedPdfFile.getAbsolutePath());

	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        Warrantyclaim wc = (Warrantyclaim) session.get(Warrantyclaim.class, claimNo);
	        if (wc != null) {
	            wc.setUploadSignedpdfName(filename);
	            wc.setUploadSignedpdfStatus("UPLOADED");
	            wc.setUploadSignedpdfDate(new Date());
	            wc.setUuid(uuid);
	            session.update(wc);
	            transaction.commit();
	            return "SUCCESS";
	        }

	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session != null) session.close();
	    }
	    return "FAILURE";
	}

	
	public Map<String, String> UploadSignedFile(String apiKey, String emailInitiator, String fileUploadUrl, String filePath,String callbackURL) throws Exception {
	    Map<String, String> responseMap = new HashMap<>();
	    
	    Connection conn = new dbConnection().getConnection();

	    if (filePath == null || filePath.trim().isEmpty()) {
	        responseMap.put("result", "error");
	        responseMap.put("message", "Signed PDF file path is empty or null.");
	        return responseMap;
	    }
	    
	    File file = new File(filePath);
	    if (!file.exists() || !file.isFile()) {
	        responseMap.put("result", "error");
	        responseMap.put("message", "Signed PDF file does not exist.");
	        return responseMap;
	    }
	    
	    String ts = DigitalSignatureUtil.getCurrentTimestamp();
	    String cs = DigitalSignatureUtil.generateChecksum(apiKey, ts);
	    
        System.out.println("filePath "+filePath);
        System.err.println("filePath "+filePath);	

	    String signLocation = FindTextCoordinates.getSignatureLocation(filePath, "Authorised Signatory",conn);
	 
	    if (signLocation == null || signLocation.trim().isEmpty()) {
	        responseMap.put("result", "error");
	        responseMap.put("message", "Signed location not found in PDF.");
	        return responseMap;
	    }
	    
	    System.out.println("signLocation "+signLocation);	
	    System.err.println("signLocation "+signLocation);	
	    
	    
	    System.out.println("callbackURL "+callbackURL);	
	    System.err.println("callbackURL "+callbackURL);	
	    
	    responseMap.put("signLocation", signLocation);
	    if (signLocation.isEmpty()) return responseMap;

	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost uploadPost = new HttpPost(fileUploadUrl);

	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    builder.addBinaryBody("uploadfile", file, ContentType.create("application/pdf"), file.getName());
	    builder.addTextBody("initiator", emailInitiator, ContentType.TEXT_PLAIN);
	    builder.addTextBody("signloc", signLocation, ContentType.TEXT_PLAIN);
	    builder.addTextBody("callbackurl", callbackURL, ContentType.TEXT_PLAIN);

	    uploadPost.setEntity(builder.build());
	    uploadPost.setHeader("ts", ts);
	    uploadPost.setHeader("cs", cs);

	    try (CloseableHttpResponse response = httpClient.execute(uploadPost)) {
	        int responseCode = response.getStatusLine().getStatusCode();
	        System.out.println("HTTP Response Code: " + responseCode);

	        String responseStr = EntityUtils.toString(response.getEntity());
	        JSONObject responseJson = new JSONObject(responseStr);

	        responseMap.put("status", responseJson.optString("status"));
	        responseMap.put("message", responseJson.optString("message"));
	        responseMap.put("widget", responseJson.optString("widget"));
	        responseMap.put("uuid", responseJson.optString("uuid"));
	        
	        System.out.println("status " +responseJson.optString("status"));
	        System.out.println("message " +  responseJson.optString("message"));
	        System.out.println("widget " + responseJson.optString("widget"));
	        System.out.println("uuid " + responseJson.optString("uuid"));
	        

	        if ("0".equals(responseJson.optString("status")) && responseJson.optString("uuid") != null) {
	            responseMap.put("result", "success");
	        } else {
	            responseMap.put("result", "error");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        responseMap.put("result", "error");
	        responseMap.put("error_message", e.getMessage());
	    } finally {
	        httpClient.close();
	        if(conn != null)
	        	conn.close();
	    }

	    return responseMap;
	}

	public Map<String, String> fetchAndSaveSignedFile(String uuid, String apiKey, String realPath, String contextPath, String fileFetchUrl) {
	    Map<String, String> responseMap = new HashMap<>();
	    Session session = null;
	    Transaction transaction = null;
	    Connection dbConn = null;
	    boolean flag = false;

	    try {
	        System.out.println("Starting fetchAndSaveSignedFile for UUID: " + uuid);

	        // Step 1: Generate timestamp and checksum
	        String ts = DigitalSignatureUtil.getCurrentTimestamp();
	        String cs = DigitalSignatureUtil.generateChecksum(apiKey, ts);
	        System.out.println("Timestamp: " + ts);
	        System.out.println("Checksum: " + cs);

	        // Step 2: Make the request
	        String urlStr = fileFetchUrl + "?uuid=" + uuid;
	        URL url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("ts", ts);
	        conn.setRequestProperty("cs", cs);

	        int responseCode = conn.getResponseCode();
	        String contentType = conn.getContentType();
	        System.out.println("Response Code: " + responseCode);
	        System.out.println("Content-Type: " + contentType);

	        if (responseCode == 200 && contentType != null && contentType.contains("application/pdf")) {
	            // Step 3: Read binary and save PDF
	            System.out.println("Reading PDF bytes...");

	            byte[] pdfBytes;
	            try (InputStream is = conn.getInputStream(); ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
	                byte[] data = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = is.read(data)) != -1) {
	                    buffer.write(data, 0, bytesRead);
	                }
	                pdfBytes = buffer.toByteArray();
	            }

	            String pdfHeader = new String(pdfBytes, 0, Math.min(20, pdfBytes.length));
	            System.out.println("PDF header: " + pdfHeader);

	            String saveDirectory = realPath + File.separator + "DOCUMENTS" + File.separator + "DIGITAL_SIGNATURE" + File.separator;
	            File dir = new File(saveDirectory);
	            if (!dir.exists()) dir.mkdirs();

	            String savePath = saveDirectory + uuid + ".pdf";
	            System.out.println("Saving PDF to: " + savePath);

	            try (FileOutputStream fos = new FileOutputStream(savePath)) {
	                fos.write(pdfBytes);
	            }

	            System.out.println("PDF saved successfully. File size: " + pdfBytes.length + " bytes");
	            
	            
	            String searchFilePath = realPath + File.separator + "DOCUMENTS" + File.separator + "DIGITAL_SIGNATURE" + File.separator;

	            String fileName = uuid + ".pdf"; 
	            File file = new File(searchFilePath, fileName);
	            System.out.println("searchFilePath "+searchFilePath);
	            System.out.println("fileName "+fileName);
	            if (file.exists() && file.isFile()) {
	                System.out.println("File found: " + file.getAbsolutePath());
	                System.out.println("digitally signed pdf successfully "+uuid);
	                flag = true;
	            } else {
	                System.out.println("File not found in: " + searchFilePath);
	            }
	            
	            // Step 4: Update DB
	            if(flag) {
	            
	            session = HibernateUtil.getSessionFactory().openSession();
	            transaction = session.beginTransaction();

	            String updateQuery = "UPDATE SW_WARRANTY_CLAIM SET SIGN_STATUS = 'SIGNED' WHERE UUID = :uuid";
	            int updated = session.createSQLQuery(updateQuery)
	                    .setParameter("uuid", uuid)
	                    .executeUpdate();

	            transaction.commit();
	            System.out.println("Database updated. Rows affected: " + updated);
	            }

	            responseMap.put("result", "success");
	            responseMap.put("status", "success");
	            responseMap.put("message", "PDF saved and DB updated.");

	        } else {
	            // Step 5: Handle errors (like HTML/JSON error pages)
	            System.err.println("Non-200 or non-PDF response. Handling error...");

	            InputStream errorStream = conn.getErrorStream();
	            StringBuilder errorText = new StringBuilder();
	            if (errorStream != null) {
	                try (BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream))) {
	                    String line;
	                    while ((line = reader.readLine()) != null) {
	                        errorText.append(line);
	                    }
	                }
	            }

	            String errorResponse = errorText.toString();
	            System.out.println("Error Response Body: " + errorResponse);

	            if (errorResponse.trim().startsWith("{")) {
	                try {
	                    JSONObject json = new JSONObject(errorResponse);
	                    responseMap.put("status", json.optString("status", "error"));
	                    responseMap.put("message", json.optString("message", "Unknown error"));
	                } catch (Exception je) {
	                    System.err.println("Failed to parse error JSON: " + je.getMessage());
	                    responseMap.put("status", "error");
	                    responseMap.put("message", "Malformed JSON response");
	                }
	            } else {
	                responseMap.put("status", "error");
	                responseMap.put("message", errorResponse);
	            }
	            responseMap.put("result", "error");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        if (transaction != null) {
	        	if(flag)
	        	transaction.rollback();
	        }
	        responseMap.put("status", "error");
	        responseMap.put("message", e.getMessage());
	        responseMap.put("result", "error");
	    } finally {
	        if (session != null) session.close();
	        if (dbConn != null) {
	            try {
	                dbConn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    System.out.println("Returning response map: " + responseMap);
	    return responseMap;
	}






}

