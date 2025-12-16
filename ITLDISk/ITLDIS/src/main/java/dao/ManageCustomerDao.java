/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HibernateMapping.UMCustomerPayments;
import HibernateMapping.UmCustomerMaster;
import HibernateUtil.HibernateUtil;
import beans.ManageCustomerForm;

/**
 *
 * @author yogasmita.patel
 */
public class ManageCustomerDao {

    String result;

    public String saveCustomerDetails(ManageCustomerForm custForm, String user_id) {
        result = "success";
        Session session = null;
        // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            UmCustomerMaster customer = null;
            Query query = session.createQuery("select customerCode from UmCustomerMaster where customerCode=:custCode and DealerCode=:dealerCode");
            query.setParameter("custCode", custForm.getCustomerCode());
            query.setParameter("dealerCode", user_id);
            List list = query.list();
            Iterator itr = list.iterator();
            if (!itr.hasNext()) {
                customer = new UmCustomerMaster();
                customer.setCustomerCode(custForm.getCustomerCode());
                customer.setCustomerName(custForm.getCustomerName());
                customer.setCustCategoryID(custForm.getCustCategoryID());
                customer.setDealerCode(user_id);
                customer.setCustomerCountry(custForm.getCustomerCountry());
                customer.setCountryID(custForm.getCountryID());
                customer.setCustomerState(custForm.getCustomerState());
                customer.setStateID(custForm.getStateID());
                customer.setCustomerDistrict(custForm.getCustomerDistrict());
                customer.setDistrictID(custForm.getDistrictID());
                customer.setCustomerTehsil(custForm.getCustomerTehsil());
                customer.setTehsilID(custForm.getTehsilID());
                customer.setCustomerLocation(custForm.getCustomerLocation());
                customer.setLocationVillageID(custForm.getLocationVillageID());
                customer.setCustomerBlock(custForm.getCustomerBlock());
                customer.setBlockID(custForm.getBlockID());
                customer.setContactPerson(custForm.getContactPerson());
                customer.setContactEmail(custForm.getContactEmail());
                if (custForm.getContactDOB() != null && !"".equals(custForm.getContactDOB())) {
                    customer.setContactDOB(sdf1.parse(custForm.getContactDOB()));
                } else {
                    custForm.setContactDOB(null);
                }

                customer.setContactMobile(custForm.getContactMobile());
                customer.setCreatedBy(user_id);
                customer.setCreatedOn(new Date());
                customer.setCreditLimit(custForm.getCreditLimit());
                customer.setCustomerTarget(custForm.getCustomerTarget());
                customer.setDiscountPercentage(custForm.getDiscountPercentage());
                customer.setTransporterInUse(custForm.getTransporterInUse());
                customer.setPanNo(custForm.getPanNo() == null ? "" : custForm.getPanNo().toString());
                customer.setTinNo(custForm.getTinNo() == null ? "" : custForm.getTinNo().toString());
                customer.setPincode(custForm.getPincode() == null ? "0" : custForm.getPincode().toString());
                customer.setTms_CustID("0");
                customer.setIsActive('Y');
                customer.setModifiedBy(null);
                customer.setModifiedOn(null);
                customer.setGstNo(custForm.getGstNo() == null ? "" : custForm.getGstNo().toString());
                // BigInteger customerId = (BigInteger)
                session.save(customer);
                session.getTransaction().commit();
            } else {
                result = "failure";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
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

    public List<ManageCustomerForm> getCustomerDetailsList(ManageCustomerForm custForm, String dealerCode) {
        Session session = null;
        Query hQuery = null;
        List<ManageCustomerForm> customerList = new ArrayList<ManageCustomerForm>();
        ManageCustomerForm customer;
        Object[] obj;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            hQuery = session.createSQLQuery("select top(50) *  from FN_GetCustomerDetail(?,?,?,?,?,?,?) order by PaymentDue desc");

            hQuery.setString(0, custForm.getCustomerId() == null ? "0" : custForm.getCustomerId().toString());
            hQuery.setString(1, custForm.getCustCategory() == null ? "0" : custForm.getCustCategory().toString());
            hQuery.setString(2, dealerCode == null ? "" : dealerCode.toString());
            hQuery.setString(3, custForm.getSearchName() == null ? "" : custForm.getSearchName().toString());
            hQuery.setString(4, custForm.getSearchLoc() == null ? "" : custForm.getSearchLoc().toString());
            hQuery.setString(5, custForm.getSearchCont() == null ? "" : custForm.getSearchCont().toString());
            hQuery.setString(6, custForm.getSearchCode() == null ? "" : custForm.getSearchCode().toString());


            List list = hQuery.list();
            Iterator itr = list.iterator();

            while (itr.hasNext()) {
                obj = (Object[]) itr.next();
                customer = new ManageCustomerForm();
                customer.setCustomerId(obj[0] == null ? "0" : obj[0].toString());
                customer.setCustCategoryID(Integer.parseInt(obj[1] == null ? "-" : obj[1].toString()));
                customer.setCustomerCode(obj[2] == null ? "-" : obj[2].toString());
                customer.setCustomerName(obj[3] == null ? "-" : obj[3].toString());
                customer.setCustomerLocation(obj[4] == null ? "-" : obj[4].toString());

                customer.setContactMobile(obj[10] == null ? "-" : obj[10].toString());
                customer.setIsActive(obj[17] == null ? "N" : obj[17].toString());

                customer.setCreditLimit(Double.parseDouble(obj[15] == null ? "0.0" : obj[15].toString()));
                customer.setDiscountPercentage(Float.parseFloat(obj[13] == null ? "0.0" : obj[13].toString()));
                customer.setAvailableCreditLimit(Double.parseDouble(obj[21] == null ? "0.0" : obj[21].toString()));
                customer.setPaymentDue(Double.parseDouble(obj[20] == null ? "0.0" : obj[20].toString()));
                customer.setDealerCode(obj[22] == null ? "" : obj[22].toString());
                customer.setCustCategory(obj[23] == null ? "" : obj[23].toString());
                customer.setTotalDiscountPercentage(Float.parseFloat(obj[24] == null ? "0.0" : obj[24].toString()));
                customer.setSonalikaId(obj[25] == null ? "" : obj[25].toString());
                customerList.add(customer);
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
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
        return customerList;
    }

    public void getCustomerDetailsForEdit(ManageCustomerForm custForm, String user_id, String customerId) {
        result = "success";
        Session session = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("DD/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            UmCustomerMaster customer = (UmCustomerMaster) session.load(UmCustomerMaster.class, new BigInteger(customerId));
            custForm.setCustomerId(customerId);
            custForm.setCustomerName(customer.getCustomerName());
            custForm.setCustomerCode(customer.getCustomerCode());
            custForm.setCustCategoryID(customer.getCustCategoryID());
            custForm.setDealerCode(customer.getDealerCode());
            custForm.setCustomerCountry(customer.getCustomerCountry());
            custForm.setCountryID(customer.getCountryID());
            custForm.setCustomerState(customer.getCustomerState());
            custForm.setStateID(customer.getStateID());
            custForm.setCustomerDistrict(customer.getCustomerDistrict());
            custForm.setDistrictID(customer.getDistrictID());
            custForm.setCustomerTehsil(customer.getCustomerTehsil());
            custForm.setTehsilID(customer.getTehsilID());
            custForm.setCustomerLocation(customer.getCustomerLocation());
            custForm.setLocationVillageID(customer.getLocationVillageID());
            custForm.setCustomerBlock(customer.getCustomerBlock());
            custForm.setBlockID(customer.getBlockID());
            custForm.setContactPerson(customer.getContactPerson());
            custForm.setContactEmail(customer.getContactEmail());
            if (customer.getContactDOB() != null && !customer.getContactDOB().toString().equals("")) {
                custForm.setContactDOB(sdf1.format(sdf.parse(customer.getContactDOB().toString())));
            } else {
                custForm.setContactDOB(null);
            }
            custForm.setContactMobile(customer.getContactMobile());
            custForm.setCreatedBy(customer.getCreatedBy());
            custForm.setCreatedOn(customer.getCreatedOn().toString());
            custForm.setCreditLimit(customer.getCreditLimit());
            custForm.setCustomerTarget(customer.getCustomerTarget());
            custForm.setDiscountPercentage(customer.getDiscountPercentage());
            if (customer.getTransporterInUse() == null || customer.getTransporterInUse().equals("null") || customer.getTransporterInUse().equals("")) {
                custForm.setTransporterInUse("-");
            } else {
                custForm.setTransporterInUse(customer.getTransporterInUse());
            }
            custForm.setTms_CustID(customer.getTms_CustID() == null ? "0" : customer.getTms_CustID().toString());
            custForm.setIsActive(customer.getIsActive().toString());
            custForm.setPincode(customer.getPincode() == null ? "0" : customer.getPincode().toString());
            custForm.setPanNo(customer.getPanNo() == null ? "" : customer.getPanNo().toString());
            custForm.setTinNo(customer.getTinNo() == null ? "" : customer.getTinNo().toString());
            custForm.setModifiedBy(null);
            custForm.setModifiedOn(null);
            custForm.setGstNo(customer.getGstNo() == null ? "" : customer.getGstNo().toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
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

    public String updateCustomerDetails(ManageCustomerForm custForm, String user_id) {
        result = "success";
        Session session = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            UmCustomerMaster customer = (UmCustomerMaster) session.load(UmCustomerMaster.class, new BigInteger(custForm.getCustomerId().trim()));
            Query query = session.createQuery("select customerCode from UmCustomerMaster where customerCode=:custCode and DealerCode=:dealerCode and CustomerID!=:custId");
            query.setParameter("custCode", custForm.getCustomerCode());
            query.setParameter("dealerCode", user_id);
            query.setParameter("custId", custForm.getCustomerId());
            List list = query.list();
            Iterator itr = list.iterator();
            if (!itr.hasNext()) {
                customer.setCustomerName(custForm.getCustomerName());
                customer.setCustomerCode(custForm.getCustomerCode());
                customer.setCustCategoryID(custForm.getCustCategoryID());
                customer.setCustomerCountry(custForm.getCustomerCountry());
                customer.setCountryID(custForm.getCountryID());
                customer.setCustomerState(custForm.getCustomerState());
                customer.setStateID(custForm.getStateID());
                customer.setCustomerDistrict(custForm.getCustomerDistrict());
                customer.setDistrictID(custForm.getDistrictID());
                customer.setCustomerTehsil(custForm.getCustomerTehsil());
                customer.setTehsilID(custForm.getTehsilID());
                customer.setCustomerLocation(custForm.getCustomerLocation());
                customer.setLocationVillageID(custForm.getLocationVillageID());
                customer.setCustomerBlock(custForm.getCustomerBlock());
                customer.setBlockID(custForm.getBlockID());
                customer.setContactPerson(custForm.getContactPerson());
                customer.setContactEmail(custForm.getContactEmail());
                if (custForm.getContactDOB() != null && !"".equals(custForm.getContactDOB())) {
                    customer.setContactDOB(sdf1.parse(custForm.getContactDOB()));
                } else {
                    custForm.setContactDOB(null);
                }
                // customer.setContactDOB(new Date(sdf.format(sdf1.parse(custForm.getContactDOB()))));
                customer.setContactMobile(custForm.getContactMobile());
                customer.setCreditLimit(custForm.getCreditLimit());
                customer.setCustomerTarget(custForm.getCustomerTarget());
                customer.setDiscountPercentage(custForm.getDiscountPercentage());
                customer.setTransporterInUse(custForm.getTransporterInUse());
                customer.setPincode(custForm.getPincode() == null ? "0" : custForm.getPincode().toString());
                customer.setPanNo(custForm.getPanNo() == null ? "" : custForm.getPanNo().toString());
                customer.setTinNo(custForm.getTinNo() == null ? "" : custForm.getTinNo().toString());
//                custForm.setPincode(customer.getPincode() == null ? "0" : customer.getPincode().toString());
//                custForm.setPanNo(customer.getPanNo() == null ? "" : customer.getPanNo().toString());
//                custForm.setTinNo(customer.getTinNo() == null ? "" : customer.getTinNo().toString());
                customer.setIsActive('Y');
                customer.setModifiedBy(user_id);
                customer.setModifiedOn(new Date());
                customer.setGstNo(custForm.getGstNo() == null ? "" : custForm.getGstNo().toString());
                session.update(customer);
                session.getTransaction().commit();
            } else {
                result = "failure";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            if (session != null && session.isOpen()) {
                session.close();
            }
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

    public String saveReceivedPayment(ManageCustomerForm form, String userId) {
        Session session = null;
        result = "failure";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            UMCustomerPayments payment = new UMCustomerPayments();
                payment.setCustomerID(new BigInteger(form.getCustomerId()));
            payment.setPaymentModeID(Integer.parseInt(form.getPaymentMode()));
            payment.setPaymentDate(new Date(sdf.format(sdf1.parse(form.getPaymentDate()))));
            payment.setPaymentReceived(form.getAmount());
            payment.setCreatedBy(userId);
            payment.setCreatedOn(new Date());
            payment.setRemarks(form.getRemarks());

            session.save(payment);
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

    public List<ManageCustomerForm> getReceivedPaymentList(ManageCustomerForm form) {
        Session session = null;
        Object[] obj = null;
        ManageCustomerForm mForm;
        List<ManageCustomerForm> paymentList = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String subQuery = "";
        try {
            if ((form.getFromdate() != null && form.getTodate() != null) && (form.getFromdate().length() > 0 && form.getTodate().length() > 0)) {

                subQuery = " and cp.paymentDate between '" + sdf.format(sdf1.parse(form.getFromdate())) + "' and '" + sdf.format(sdf1.parse(form.getTodate())) + "'";
            }
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query hql = session.createQuery("select cp.paymentID,cp.paymentDate,cp.paymentReceived,cp.remarks,cp.createdOn,"
                    + " um.customerName,um.customerCode,mp.payementMode "
                    + " from UMCustomerPayments cp,MSWPaymentModes mp,UmCustomerMaster um"
                    + " where cp.customerID=um.customerID and cp.paymentModeID=mp.paymentModeID and"
                    + " cp.customerID=:customerID" + subQuery + " order by cp.paymentDate desc");
            hql.setParameter("customerID", new BigInteger(form.getCustomerId().trim()));
            List list = hql.list();
            if (list != null && !list.isEmpty()) {
                paymentList = new ArrayList<ManageCustomerForm>();
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    obj = (Object[]) itr.next();
                    mForm = new ManageCustomerForm();
                    mForm.setPaymentId(obj[0] == null ? 0 : Integer.parseInt(obj[0].toString()));
                    mForm.setPaymentDate(obj[1] == null ? "-" : sdf1.format(sdf.parse(obj[1].toString())));
                    mForm.setAmount(obj[2] == null ? 0 : Double.parseDouble(obj[2].toString()));
                    mForm.setRemarks(obj[3] == null ? "-" : obj[3].toString());
                    mForm.setCreatedOn(obj[4] == null ? "-" : sdf1.format(sdf.parse(obj[4].toString())));
                    mForm.setCustomerName(obj[5] == null ? "-" : obj[5].toString());
                    mForm.setCustomerCode(obj[6] == null ? "-" : obj[6].toString());
                    mForm.setPaymentMode(obj[7] == null ? "-" : obj[7].toString());
                    paymentList.add(mForm);
                }
                form.setCustomerId(form.getCustomerId().trim());
            }
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
        return paymentList;
    }

    public String getStandardDiscount(Integer custCategoryID, String dealerCode) {
        Session session = null;
        String discountPercentage = "0";
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = null;
            Object obj;
            query = session.createQuery("select discountPercentage "
                    + "from CustomerCategoryDiscount where custcategoryID=:custcategoryID and dealerCode=:dealerCode");
            query.setParameter("custcategoryID", custCategoryID);
            query.setParameter("dealerCode", dealerCode);
            Iterator it = query.list().iterator();
            if (it.hasNext()) {
                obj = (Object) it.next();
                discountPercentage = (obj == null ? "0" : obj.toString().trim());
            }

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
        return discountPercentage;
    }

    public static String getListOnSelect(String getColName, String whereColumn, String whereColVal, String orderBy) {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        StringBuilder strb = new StringBuilder("");
        try {
            whereColVal = whereColVal.replaceAll("@", " ");

            Query query = sess.createQuery("select distinct " + getColName + " from TMSGeographyMaster where " + whereColumn + " = :whereColVal order by " + orderBy + " ");
            query.setParameter("whereColVal", Integer.parseInt(whereColVal.length() > 0 ? whereColVal : "0"));
            Iterator it = query.list().iterator();

            while (it.hasNext()) {
                Object[] obj = (Object[]) it.next();
                strb.append(obj[0] == null ? "0" : obj[0].toString().trim());
                strb.append("@");
                if (obj[1] == null || obj[1].equals("")) {
                    strb.append("NA");
                } else {
                    strb.append(obj[1].toString().trim());
                }
                strb.append("@");
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
}
