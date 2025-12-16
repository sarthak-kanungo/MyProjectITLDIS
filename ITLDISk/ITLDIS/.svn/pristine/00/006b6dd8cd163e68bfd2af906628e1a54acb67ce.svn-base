/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.piDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.MethodUtility;

import HibernateMapping.SPOrderHeaderEXP;
import HibernateMapping.SP_ORD_PI_DTL_EXP;
import HibernateMapping.SP_ORD_PI_EXP;
import HibernateMapping.SpOrdPiChrgExp;
import HibernateMapping.SpOrdPiChrgExpPK;
import HibernateMapping.SpOrderDetailsEXP;
import HibernateUtil.HibernateUtil;
import beans.piBean.PIFormBean;
import dao.inventoryEXPDAO;
import dbConnection.dbConnection;
import viewEcat.orderEcat.PriceDetails;

/**
 *
 * @author yogasmita.patel
 */
public class CreatePIDao {

    private Iterator itr = null;
    private Object[] data = null;

    public List<PIFormBean> getPOListExpoDealer(PIFormBean piForm) {
        List<PIFormBean> orderList = new ArrayList<PIFormBean>();
        PIFormBean obj = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbSession.createQuery("select h.custPoNo,h.ordType,h.custPoDate,h.consigneeCode,h.consigneeName,(select countryName from MSPCountriesEXPMaster where countryCode= h.consigneeCountry) as countryName,(select dischargePortName from MSPDischargePortEXPMaster where dischargePortCode=h.dischargePort) as dischargePortName,h.destinationPlace,h.totalValue,(select count(d.partNo)  from SpOrderDetailsEXP d where d.custPoNo=h.custPoNo)as lineItem from SPOrderHeaderEXP h where h.dealerCode=:dealerCode and h.ordType=:ordType and h.hoProcessStatus='PENDING' and h.status='CLOSE' order by h.custPoDate desc");
            query.setParameter("dealerCode", piForm.getDealerCode().trim());
            query.setParameter("ordType", piForm.getOrderType().trim());
            List result = query.list();

            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    obj = new PIFormBean();
                    obj.setPoNo(data[0] == null ? "" : data[0].toString());
                    obj.setOrderType(data[1] == null ? "" : data[1].toString());
                    obj.setCustPoDate(data[2] == null ? "" : sdf1.format(sdf.parse(data[2].toString().trim())));
                    obj.setConsigneeCode(data[3] == null ? "" : data[3].toString());
                    obj.setConsigneeName(data[4] == null ? "" : data[4].toString());
                    obj.setConsigneeCountry(data[5] == null ? "" : data[5].toString());
                    obj.setDischargePort(data[6] == null ? "" : data[6].toString());
                    obj.setDestinationPlace(data[7] == null ? "" : data[7].toString());
                    obj.setTotalPrice(data[8] == null ? 0.0 : Double.parseDouble(data[8].toString()));
                    obj.setLineItem(data[9] == null ? 0 : Integer.parseInt(data[9].toString()));
                    orderList.add(obj);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    public LinkedHashSet<LabelValueBean> getBuyerList(String dealerCategory) {
        LabelValueBean lv;
        LinkedHashSet<LabelValueBean> buyerList = new LinkedHashSet<LabelValueBean>();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbsession.createQuery("select dm.dealerCode,dm.dealerName"
                    + " from Dealervslocationcode dm"
                    + " where dm.dealerCategory=:dealerCategory order by dm.dealerName");
            query.setParameter("dealerCategory", dealerCategory);
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                if (o[1] != null && o[0] != null) {
                    if (!o[1].toString().equals("") && !o[0].toString().equals("")) {
                        lv = new LabelValueBean();
                        lv.setLabel(o[0].toString() + " [" + o[1].toString() + "]");
                        lv.setValue(o[0].toString());
                        buyerList.add(lv);
                    }
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
        return buyerList;
    }

    public List<PIFormBean> getPOListDetails(PIFormBean piForm, PriceDetails pObj) {
        List<SPOrderHeaderEXP> orderList = null;
        Boolean flag = false;
        List<PIFormBean> poDetailList = new ArrayList<PIFormBean>();
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            ArrayList<String> poList = new ArrayList<String>(Arrays.asList(piForm.getCheckedPOList()));
            if (poList.size() > 1) {
                flag = checkPOListForPI(poList, hrbSession);
            } else if (poList.size() == 1) {
                flag = true;
            }
            if (flag) {
                SPOrderHeaderEXP poDetails = (SPOrderHeaderEXP) hrbSession.load(SPOrderHeaderEXP.class, poList.get(0).trim());
                piForm.setUserId(poDetails.getCreatedBy());
                Query query = hrbSession.createQuery("select distinct d.partNo,p.p1,d.custPoNo,d.pendingQty,d.piQty,d.price,d.poDetailId,h.currency,h.priceListCode"
                        + " from SPOrderHeaderEXP h,SpOrderDetailsEXP d,CatPart p,SpPriceMaster sp "
                        + " where h.custPoNo=d.custPoNo and p.partNo=d.partNo and sp.catPart.partNo=p.partNo and"
                        + " sp.spPriceMasterPK.pricelistCode=h.priceListCode"
                        + " and h.custPoNo in(:poList) and h.dealerCode=:dealerCode and d.pendingQty > 0 order by d.custPoNo");
                query.setParameter("dealerCode", piForm.getDealerCode());
               // query.setParameter("priceListCode", pObj.getPriceListCode(poDetails.getCreatedBy()));
                query.setParameterList("poList", poList);
                orderList = query.list();
                PIFormBean piform;
                if (orderList != null && !orderList.isEmpty()) {
                    itr = orderList.iterator();
                    while (itr.hasNext()) {
                        data = (Object[]) itr.next();
                        piform = new PIFormBean();
                        piform.setPartNo(data[0] == null ? "" : data[0].toString());
                        piform.setPartDesc(data[1] == null ? "" : data[1].toString());
                        piform.setPoNo(data[2] == null ? "" : data[2].toString());
                        piform.setPendingQty(data[3] == null ? 0 : Integer.parseInt(data[3].toString()));
                        piform.setPiQty(data[4] == null ? 0 : Integer.parseInt(data[4].toString()));
                        piform.setPrice(data[5] == null ? 0 : Double.parseDouble(data[5].toString()));
                        piform.setPoId(data[6] == null ? 0 : Long.parseLong(data[6].toString()));
                        piForm.setCurrency(data[7] == null ? "-" : data[7].toString());
                        piForm.setPriceListCode(data[8] == null ? "-" : data[8].toString());
                        poDetailList.add(piform);
                    }
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poDetailList;
    }

    public Boolean checkPOListForPI(List<String> poList, Session hrbSession) {
        List<SPOrderHeaderEXP> orderList = null;
        Boolean flag = false;
        try {
            Query query = hrbSession.createQuery("select distinct ordType,dealerCode,engineNo,chassisNo,modelNo,jobCardNo,deliveryTerms,"
                    + "incoTerms,consigneeCode,dischargePort,destinationPlace FROM SPOrderHeaderEXP where custPoNo IN (:poList)");
            query.setParameterList("poList", poList);
            orderList = query.list();
            if (orderList != null) {
                flag = orderList.size() == 1 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public PIFormBean getOrderDetail(PIFormBean piForm, String custPoNo) {
        Session hrbsession = null;

        String hql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            hql = "select om.custPoNo,om.ordType,om.custPoDate,om.engineNo,"
                    + " om.chassisNo,om.modelNo,om.jobCardNo ,"
                    + " (select dm.deliveryTermDesc from MSPDeliveryTermsEXPMaster dm where dm.deliveryTermCode=om.deliveryTerms)as deliveryDesc,om.totalValue," + //om.shipmentLotSingle,  om.deliveryAddress,
                    " om.status ,om.comments ,om.consigneeCode,om.consigneeName,(select mc.countryName from MSPCountriesEXPMaster mc where mc.countryCode= om.consigneeCountry) as countryName,om.destinationPlace,om.hoRemarks,om.dealerCode"
                    + " ,om.dealerRefNo,om.comments,om.paymentTerms,(select it.incoTermDesc from MSPIncoTermsEXPMaster it where it.incoTermCode=om.incoTerms )as incoTerms,"
                    + " (select dp.dischargePortName from MSPDischargePortEXPMaster dp where dp.dischargePortCode=om.dischargePort) as dischargePortName,om.precarriageBy,om.placePreCarrier,om.createdBy from SPOrderHeaderEXP om  "
                    + " where om.custPoNo=:custPoNo";
            Query orderQuery = hrbsession.createQuery(hql);
            orderQuery.setParameter("custPoNo", custPoNo);
            itr = orderQuery.list().iterator();
            while (itr.hasNext()) {
                data = (Object[]) itr.next();
                piForm.setPoNo(data[0] == null ? "-" : data[0].toString().trim());
                piForm.setOrderType(data[1] == null ? "-" : data[1].toString().trim());
                piForm.setCustPoDate(data[2] == null ? "-" : sdf.format(df.parse(data[2].toString().trim())));
                piForm.setEngineNo(data[3] == null ? "-" : data[3].toString().trim());
                piForm.setChassisNo(data[4] == null ? "-" : data[4].toString().trim());
                piForm.setModelNo(data[5] == null ? "-" : data[5].toString().trim());
                piForm.setFirNo(data[6] == null ? "-" : data[6].toString().trim());
                piForm.setDeliveryCode(data[7] == null ? "-" : data[7].toString().trim());
                piForm.setTotalAmount(data[8] == null ? "-" : data[8].toString().trim());
                piForm.setStatus(data[9] == null ? "-" : data[9].toString().trim());
                piForm.setSpecInstr(data[10] == null ? "-" : data[10].toString().trim());
                piForm.setConsigneeCode(data[11] == null ? "-" : data[11].toString().trim());
                piForm.setConsigneeName(data[12] == null ? "-" : data[12].toString().trim());
                piForm.setConsigneeCountry(data[13] == null ? "-" : data[13].toString().trim());
                piForm.setDestinationPlace(data[14] == null ? "-" : data[14].toString().trim());
                piForm.setErpRemarks(data[15] == null ? "-" : data[15].toString().trim());
                piForm.setDealerCode(data[16] == null ? "-" : data[16].toString().trim());
                piForm.setDealerRefNo(data[17] == null ? "-" : data[17].toString().trim());
                piForm.setComments(data[18] == null ? "-" : data[18].toString().trim());
                piForm.setPaymentTerms(data[19] == null ? "-" : data[19].toString().trim());
                piForm.setIncoTerms(data[20] == null ? "-" : data[20].toString().trim());
                piForm.setDischargePort(data[21] == null ? "-" : data[21].toString().trim());
                piForm.setPrecarriageBy(data[22] == null ? "-" : data[22].toString().trim());
                piForm.setPlacePreCarrier(data[23] == null ? "-" : data[23].toString().trim());
                piForm.setUserId(data[24] == null ? "-" : data[24].toString().trim());
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
        return piForm;
    }

    public String savePIDetails(PIFormBean piForm, String userId) {
        String result = "failure";
        Session hrbSession = null;
        Transaction tx = null;
        try {
            hrbSession = HibernateUtil.getSessionFactory().openSession();
            tx = hrbSession.beginTransaction();
            String dealerCode = piForm.getPoNo().trim().split("/")[2];
            SPOrderHeaderEXP order = (SPOrderHeaderEXP) hrbSession.load(SPOrderHeaderEXP.class, piForm.getPoNo().trim());
            SP_ORD_PI_EXP piHdr = new SP_ORD_PI_EXP();
            String refNo = new MethodUtility().getNumber(hrbSession, "SP_ORD_PI_EXP", dealerCode, "PI");
            piHdr.setPiNo(refNo);
            piHdr.setPriceListCode(piForm.getPriceListCode());
            piHdr.setCurrency(piForm.getCurrency());
            piHdr.setPiDate(new Date());
            piHdr.setOrdtype(order.getOrdType());
            piHdr.setDealerCode(order.getDealerCode());
            piHdr.setRemarks(order.getHoRemarks()); //?
            piHdr.setEngineNo(order.getEngineNo());
            piHdr.setChassisNo(order.getChassisNo());
            piHdr.setModelNo(order.getModelNo());
            piHdr.setJobCardNo(order.getJobCardNo());
            piHdr.setDeliveryTerms(order.getDeliveryTerms());
            piHdr.setPaymentTerm(piForm.getPaymentTerms());
            piHdr.setIncoTerms(order.getIncoTerms());
            piHdr.setConsigneeddress(order.getConsigneeAddress());
            piHdr.setConsigneeCountry(order.getConsigneeCountry());
            piHdr.setConsigneeName(order.getConsigneeName());
            piHdr.setConsigneeCode(order.getConsigneeCode());
            piHdr.setDischargePort(order.getDischargePort());
            piHdr.setDestinationPlace(order.getDestinationPlace());
            piHdr.setCreatedOn(new Date());
            piHdr.setCreatedBy(userId);
            piHdr.setIsServerSync('N');
            piHdr.setLastSyncDate(null);
            piHdr.setErpProcessStatus(null);
            piHdr.setErpUploadStatus(null);
            piHdr.setStatus("PENDING AT BUYER");
            piHdr.setPartValue(0.0f);
            piHdr.setTotalValue(0.0f);
            piHdr.setLastupdatedOn(null);
            piHdr.setPrecarriageBy(piForm.getPrecarriageBy());
            piHdr.setPlacePreCarrier(piForm.getPlacePreCarrier());
            hrbSession.save(piHdr);

            SP_ORD_PI_DTL_EXP piDetl = null;
            List<SP_ORD_PI_DTL_EXP> detList = new ArrayList<SP_ORD_PI_DTL_EXP>();
            SpOrderDetailsEXP ordDtl = null;
            Integer tableLength = piForm.getOrderPartNoList().length;
            boolean rejectHOFlag = true;
            float partValue = 0.0f;
            float totalPartValue = 0.0f;
            for (int k = 0; k < tableLength; k++) {

                if((piForm.getPiQtyList()[k]!=0) || piForm.getStatusList()[k].equalsIgnoreCase("Not Available"))
                {
                piDetl = new SP_ORD_PI_DTL_EXP();
                ordDtl = (SpOrderDetailsEXP) hrbSession.load(SpOrderDetailsEXP.class, Integer.parseInt(String.valueOf(piForm.getPoIdList()[k])));

                piDetl.setPoDetailId(new BigInteger(String.valueOf(piForm.getPoIdList()[k])));
                piDetl.setSp_ORD_PI_EXP(piHdr);
                piDetl.setOrderPartNo(piForm.getOrderPartNoList()[k]);
                piDetl.setAvlPartNo("");
                if (piForm.getStatusList()[k].equalsIgnoreCase("Available with Lead Time")) {
                    piDetl.setLeadTime(piForm.getLeadTimeList()[k]);
                } else {
                    piDetl.setLeadTime("");
                }
                piDetl.setSubStatus(piForm.getStatusList()[k].toUpperCase());
                piDetl.setPrice(piForm.getPriceList()[k]);

                if (piDetl.getSubStatus().equalsIgnoreCase("NOT AVAILABLE")) {
                    piDetl.setStatus("NOT AVAILABLE");
                    piDetl.setBasePiQty(0);
                    piDetl.setFinalPiQty(0);
                    piDetl.setAvlQty(0);
                    ordDtl.setPendingQty(0);
                } else if (piDetl.getSubStatus().equalsIgnoreCase("ALTERNATE AVAILABLE")) {
                    piDetl.setStatus("AVAILABLE");
                    piDetl.setBasePiQty(piForm.getPiQtyList()[k]);
                    piDetl.setFinalPiQty(piForm.getPiQtyList()[k]);
                    piDetl.setAvlQty(piForm.getPiQtyList()[k]);
                    piDetl.setAvlPartNo(piForm.getAvlPartList()[k]);

                    ordDtl.setPiQty(ordDtl.getPiQty() + piForm.getPiQtyList()[k]);
                    ordDtl.setPendingQty(0);
                    rejectHOFlag = false;
                } else {
                    piDetl.setStatus("AVAILABLE");
                    piDetl.setBasePiQty(piForm.getPiQtyList()[k]);
                    piDetl.setFinalPiQty(piForm.getPiQtyList()[k]);
                    piDetl.setAvlQty(piForm.getPiQtyList()[k]);
                    ordDtl.setPiQty(ordDtl.getPiQty() + piForm.getPiQtyList()[k]);
                    ordDtl.setPendingQty(ordDtl.getPendingQty() - piForm.getPiQtyList()[k]);
                    rejectHOFlag = false;
                }
                partValue = (piForm.getPiQtyList()[k] == null ? 0 : piForm.getPiQtyList()[k]) * (piForm.getPriceList()[k] == null ? 0.0f : piForm.getPriceList()[k]);
                totalPartValue += partValue;
                piDetl.setBookesQty(null);
                piDetl.setLastupdatedOn(new Date());
                piDetl.setLastupdatedBy(userId);
                piDetl.setIsServerSync('N');
                piDetl.setLastSyncDate(null);
                piDetl.setErpLastUpdatedOn(null);
                detList.add(piDetl);
                hrbSession.update(ordDtl);
                hrbSession.save(piDetl);
            }
            }
            piHdr.setSp_ORD_PI_DTL_EXP(detList);
            piHdr.setPartValue(totalPartValue);
            hrbSession.update(piHdr);
            SpOrdPiChrgExp otherCharges = null;
            SpOrdPiChrgExpPK otherChargePk = null;
            float otherCharge = 0.0f;
            float totalOtherCharge = 0.0f;
            for (int j = 0; j < piForm.getOtherchargeType().length; j++) {
                if (piForm.getOtherChargeList()[j] != null && !piForm.getOtherChargeList()[j].equals("")) {
                    otherCharge = Float.parseFloat(piForm.getOtherChargeList()[j]);
                    if (otherCharge > 0) {
                        totalOtherCharge += otherCharge;
                        otherCharges = new SpOrdPiChrgExp();
                        otherChargePk = new SpOrdPiChrgExpPK();
                        otherChargePk.setPiNo(piHdr.getPiNo());
                        otherChargePk.setCostHeadId(piForm.getOtherchargeType()[j]);
                        otherCharges.setSpOrdPiChrgExpPK(otherChargePk);
                        otherCharges.setCostHeadValue(Float.parseFloat(piForm.getOtherChargeList()[j]));
                        hrbSession.save(otherCharges);
                    }
                }
            }
            piHdr.setTotalValue(piHdr.getPartValue() + totalOtherCharge);
            piHdr.setOtherCharges(totalOtherCharge);
            piHdr.setBuyerEditAllowed("Y");
            if (rejectHOFlag) {
                piHdr.setStatus("Rejected By HO".toUpperCase());
            }
            hrbSession.update(piHdr);
            updateOrderStatus(piForm, hrbSession);

            Query qtr = hrbSession.createSQLQuery("exec SP_INSERT_EMAIL 2, :refNo");
            qtr.setParameter("refNo", refNo);
            qtr.executeUpdate();
        
            tx.commit();
            result = "success";
            piForm.setPiNo(refNo);
        } catch (Exception e) {
            e.printStackTrace();
            result = "failure";
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LinkedHashSet<LabelValueBean> getOtherChargeList() {
        LabelValueBean lv;
        LinkedHashSet<LabelValueBean> otherChargeList = new LinkedHashSet<LabelValueBean>();
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbsession.createQuery("select mh.costHeadID,mh.costHeadName"
                    + " from MspCostHeadExp mh"
                    + " where mh.isActive = 'Y' order by mh.costHeadName");
            itr = query.list().iterator();

            while (itr.hasNext()) {
                Object o[] = (Object[]) itr.next();
                if (o[1] != null && o[0] != null) {
                    if (!o[1].toString().equals("") && !o[0].toString().equals("")) {
                        lv = new LabelValueBean();
                        lv.setLabel(o[1].toString());
                        lv.setValue(o[0].toString());
                        otherChargeList.add(lv);
                    }
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
        return otherChargeList;
    }

    public List<PIFormBean> getPIDetailForView(PIFormBean piForm) {
        List<PIFormBean> piList = new ArrayList<PIFormBean>();
        String subQuery = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Session hrbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            hrbSession.beginTransaction();
            if (piForm.getPiNo() != null && !piForm.getPiNo().equals("")) {
                subQuery += " and pih.piNo like :piNo";
            }
            if (piForm.getDealerCode() != null && !piForm.getDealerCode().equals("")) {
                subQuery += " and pih.dealerCode=:dealerCode";
            }
            if (piForm.getStatus() != null && !piForm.getStatus().equals("")) {
                subQuery += " and pih.status ='" + piForm.getStatus().trim() + "'";
            }
            if ((piForm.getFromDate() != null && !piForm.getFromDate().equals("")) && (piForm.getToDate() != null && !piForm.getToDate().equals(""))) {
                subQuery += " and pih.piDate between :fromDate and :todate";
            }

            Query query = hrbSession.createQuery("select pih.piNo,pih.ordtype,pih.piDate,pih.engineNo,pih.chassisNo,pih.modelNo,pih.jobCardNo ,"
                    + " (select dm.deliveryTermDesc from MSPDeliveryTermsEXPMaster dm where dm.deliveryTermCode=pih.deliveryTerms)as deliveryDesc,"
                    + " pih.totalValue,pih.status ,pih.consigneeCode,pih.consigneeName,"
                    + " (select mc.countryName from MSPCountriesEXPMaster mc where mc.countryCode= pih.consigneeCountry) as countryName,"
                    + " pih.destinationPlace,pih.remarks,pih.dealerCode,(select mpt.paymentTermDesc from MSPPaymentTermsEXPMaster mpt where mpt.paymentTermCode=pih.paymentTerm) as paymentTerm,"
                    + " (select it.incoTermDesc from MSPIncoTermsEXPMaster it where it.incoTermCode=pih.incoTerms )as incoTerms,"
                    + " (select dp.dischargePortName from MSPDischargePortEXPMaster dp where dp.dischargePortCode=pih.dischargePort) as dischargePortName,"
                    + " pih.precarriageBy,pih.placePreCarrier,(select count(d.orderPartNo) from SP_ORD_PI_DTL_EXP d where d.sp_ORD_PI_EXP.piNo=pih.piNo)as lineItem,pih.dealerCode,pih.buyerEditAllowed,pih.priceListCode,pih.currency,pih.erpOrderNo,pih.erpOrderDate"
                    + " from SP_ORD_PI_EXP pih where pih.ordtype like('" + piForm.getOrderType().trim() + "') " + subQuery + " order by pih.piDate desc");

            if (!subQuery.equals("")) {
                if (piForm.getPiNo() != null && !piForm.getPiNo().equals("")) {
                    query.setParameter("piNo", "%"+piForm.getPiNo().trim()+"%");
                }
                if (piForm.getDealerCode() != null && !piForm.getDealerCode().equals("")) {
                    query.setParameter("dealerCode", piForm.getDealerCode().trim());
                }
                if ((piForm.getFromDate() != null && !piForm.getFromDate().equals("")) && (piForm.getToDate() != null && !piForm.getToDate().equals(""))) {
                    query.setParameter("fromDate", sdf.parse(sdf.format(sdf1.parse(piForm.getFromDate() + " 00:00:00"))));
                    query.setParameter("todate", sdf.parse(sdf.format(sdf1.parse(piForm.getToDate() + " 23:59:59"))));
                }
            }
            List result = query.list();
            PIFormBean piform;
            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    piform = new PIFormBean();
                    piform.setPiNo(data[0] == null ? "" : data[0].toString());
                    piform.setOrderType(data[1] == null ? "" : data[1].toString());
                    piform.setPiDate(data[2] == null ? "" : sdf2.format(sdf.parse(data[2].toString().trim())));
                    piform.setEngineNo(data[3] == null ? "-" : data[3].toString().trim());
                    piform.setChassisNo(data[4] == null ? "-" : data[4].toString().trim());
                    piform.setModelNo(data[5] == null ? "-" : data[5].toString().trim());
                    piform.setFirNo(data[6] == null ? "-" : data[6].toString().trim());
                    piform.setDeliveryCode(data[7] == null ? "-" : data[7].toString().trim());
                    piform.setTotalAmount(data[8] == null ? "-" : data[8].toString().trim());
                    piform.setStatus(data[9] == null ? "-" : data[9].toString().trim());
                    piform.setConsigneeCode(data[10] == null ? "-" : data[10].toString().trim());
                    piform.setConsigneeName(data[11] == null ? "-" : data[11].toString().trim());
                    piform.setConsigneeCountry(data[12] == null ? "-" : data[12].toString().trim());
                    piform.setDestinationPlace(data[13] == null ? "-" : data[13].toString().trim());
                    piform.setErpRemarks(data[14] == null ? "-" : data[14].toString().trim());
                    piform.setDealerCode(data[15] == null ? "-" : data[15].toString().trim());
                    piform.setPaymentTerms(data[16] == null ? "-" : data[16].toString().trim());
                    piform.setIncoTerms(data[17] == null ? "-" : data[17].toString().trim());
                    piform.setDischargePort(data[18] == null ? "-" : data[18].toString().trim());
                    piform.setPrecarriageBy(data[19] == null ? "-" : data[19].toString().trim());
                    piform.setPlacePreCarrier(data[20] == null ? "-" : data[20].toString().trim());
                    piform.setLineItem(data[21] == null ? 0 : Integer.parseInt(data[21].toString().trim()));
                    piform.setDealerCode(data[22] == null ? "" : data[22].toString().trim());
                    piform.setBuyerEditAllowed(data[23] == null ? "N" : data[23].toString().trim());
                    piform.setPriceListCode(data[24] == null ? "-" : data[24].toString().trim());
                    piform.setCurrency(data[25] == null ? "-" : data[25].toString().trim());
                    piform.setErpOrderNo(data[26] == null ? "-" : data[26].toString().trim());
                    piform.setErpOrderDate(data[27] == null ? "" : sdf2.format(sdf.parse(data[27].toString().trim())));
                    piList.add(piform);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return piList;
    }

    public List<PIFormBean> getPIPartDetail(PIFormBean piForm) {
        List<PIFormBean> piList = new ArrayList<PIFormBean>();
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbSession.createQuery("select distinct ord.custPoNo,ord.partNo,(select p.p1 from CatPart p where p.partNo=ord.partNo) as partDesc,"
                    + " ord.qty,pid.basePiQty,pid.pendingQty,pid.orderPartNo,pid.avlPartNo,pid.leadTime,pid.avlQty,pid.finalPiQty,"
                    + " pid.subStatus,pid.bookesQty,pid.pendingQty,pid.basePiQty,pid.price,pid.id,pid.poDetailId,pid.avlQty,pid.finalPiQty,"
                    + " pid.avlPartNo,(select cp.p1 from CatPart cp where cp.partNo=pid.avlPartNo)as avlPartDesc,pih.priceListCode,pih.currency"
                    + " from SP_ORD_PI_DTL_EXP pid,SpOrderDetailsEXP ord,SP_ORD_PI_EXP pih where pid.poDetailId=ord.poDetailId and pih.piNo=pid.sp_ORD_PI_EXP.piNo and pid.sp_ORD_PI_EXP.ordtype=:ordType and pid.sp_ORD_PI_EXP.piNo=:piNo");

            query.setParameter("piNo", piForm.getPiNo().trim());
            query.setParameter("ordType", piForm.getOrderType().trim());
            List result = query.list();
            PIFormBean piform;
            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    piform = new PIFormBean();
                    piform.setPartNo(data[1] == null ? "" : data[1].toString());
                    piform.setPartDesc(data[2] == null ? "" : data[2].toString());
                    piform.setPoNo(data[0] == null ? "" : data[0].toString());
                    piform.setOrderedQty(data[3] == null ? 0 : Integer.parseInt(data[3].toString()));
                    piform.setPiQty(data[4] == null ? 0 : Integer.parseInt(data[4].toString()));
                    piform.setPendingQty(data[5] == null ? 0 : Integer.parseInt(data[5].toString()));
                    piform.setLeadTime(data[8] == null ? "" : data[8].toString());
                    piform.setStatus(data[11] == null ? "" : data[11].toString());
                    piform.setBookesQty(data[12] == null ? 0 : Integer.parseInt(data[12].toString()));
                    piform.setPrice(data[15] == null ? 0.0 : Double.parseDouble(data[15].toString()));
                    piform.setPiId(data[16] == null ? 0L : Long.parseLong(data[16].toString().trim()));
                    piform.setPoId(data[17] == null ? 0L : Long.parseLong(data[17].toString().trim()));
                    piform.setAcceptPiQty(data[18] == null ? 0 : Integer.parseInt(data[18].toString().trim()));
                    piform.setFinalPiQty(data[19] == null ? 0 : Integer.parseInt(data[19].toString().trim()));
                    piform.setAvailablePartNoPI(data[20] == null ? "" : data[20].toString());
                    piform.setAvlPartDescPI(data[21] == null ? "" : data[21].toString());
                    piForm.setPriceListCode(data[22] == null ? "" : data[22].toString());
                    piForm.setCurrency(data[23] == null ? "" : data[23].toString());
                    piForm.setPoId(piform.getPoId());
                    piList.add(piform);
                }
            }
            if(piForm.getPoId()!=null){
            SpOrderDetailsEXP orderDetail = (SpOrderDetailsEXP) hrbSession.load(SpOrderDetailsEXP.class, Integer.parseInt(piForm.getPoId().toString()));
            piForm.setUserId(orderDetail.getSpOrderHeaderEXP().getCreatedBy());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return piList;
    }

    public void getOtherChargeDetail(PIFormBean piForm) {
        List chargeList = new ArrayList();
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        TreeMap<LabelValueBean, Float> otherCharges = new TreeMap<LabelValueBean, Float>();
        LabelValueBean lv;
        int i = 0;
        try {
            LinkedHashSet<LabelValueBean> l = getOtherChargeList();
            Query query = hrbSession.createQuery("select ch.costHeadName,ord.spOrdPiChrgExpPK.costHeadId,ord.costHeadValue from SpOrdPiChrgExp ord,MspCostHeadExp ch where ord.spOrdPiChrgExpPK.costHeadId=ch.costHeadID and ord.spOrdPiChrgExpPK.piNo=:piNo");
            query.setParameter("piNo", piForm.getPiNo().trim());
            chargeList = query.list();

            itr = l.iterator();
            while (itr.hasNext()) {
                lv = (LabelValueBean) itr.next();
                otherCharges.put(lv, 0.0f);
            }
            if (chargeList != null && !chargeList.isEmpty()) {
                itr = chargeList.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    lv = new LabelValueBean(data[0].toString(), data[1].toString());
                    if (l.contains(lv)) {
                        otherCharges.put(lv, Float.parseFloat(data[2].toString()));
                    }
                }
            }
            piForm.setOtherCharges(otherCharges);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getAlternatePartNos(String partNo, String priceListCode) {
        Session hrbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        String alternatePartList = "";
        String alternatePartDetailList = "";
        String result = "";
        try {
            if (partNo != null && !partNo.equals("")) {
                hrbSession.beginTransaction();
                inventoryEXPDAO inDao = new inventoryEXPDAO();
                alternatePartList = inDao.getAlternatePartNo(partNo, hrbSession ,"PI");
                String[] partList = alternatePartList.split("@@");

                for (int i = 0; i < partList.length; i++) {
                    alternatePartDetailList = inDao.getPriceForOrderByPartNo(partList[i], hrbSession, priceListCode);
                    if (!alternatePartDetailList.equals("")) {
                        result = result + partList[i] + "@@" + alternatePartDetailList + "##";
                    }
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String updatePIDetails(PIFormBean piForm, Vector userFunctionalities) {
        Session hrbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String result = "fail";
        SP_ORD_PI_DTL_EXP piDtl = null;
        Float partValue = 0.0f;
        Float totalPartValue = 0.0f;
        try {
            tx = hrbSession.beginTransaction();

            SP_ORD_PI_EXP piHdr = (SP_ORD_PI_EXP) hrbSession.load(SP_ORD_PI_EXP.class, piForm.getPiNo().trim());
            if (userFunctionalities.contains("863") && piHdr.getBuyerEditAllowed().equals("Y") && piHdr.getStatus().equalsIgnoreCase("Pending At Buyer")) {
                if (piForm.getStatus().equalsIgnoreCase("Rejected")) {
                    piHdr.setStatus("Rejected By Buyer".toUpperCase());
                } else if (piForm.getStatus().equalsIgnoreCase("Cancel")) {
                } else if (piForm.getStatus().equalsIgnoreCase("Update")) {
                    piHdr.setBuyerEditAllowed("N");
                    for (int i = 0; i < piForm.getOrderPartNoList().length; i++) {
                        piDtl = (SP_ORD_PI_DTL_EXP) hrbSession.load(SP_ORD_PI_DTL_EXP.class, piForm.getPiIdList()[i]);
                        piDtl.setAvlQty(piForm.getPiQtyList()[i]);
                        piDtl.setFinalPiQty(piForm.getPiQtyList()[i]);
                        piDtl.setPrice(piForm.getPriceList()[i]);
                        hrbSession.update(piDtl);
                        partValue = (piForm.getPiQtyList()[i] == null ? 0 : piForm.getPiQtyList()[i]) * (piForm.getPriceList()[i] == null ? 0.0f : piForm.getPriceList()[i]);
                        totalPartValue += partValue;
                    }
                    piHdr.setPartValue(totalPartValue);
                    piHdr.setTotalValue(totalPartValue + piHdr.getOtherCharges());
                    piHdr.setStatus("Pending At HO".toUpperCase());                    
                } else if (piForm.getStatus().equalsIgnoreCase("Accepted")) {
                    piHdr.setBuyerEditAllowed("N");
                    piHdr.setStatus("Accepted By Buyer".toUpperCase());

                    Query qtr = hrbSession.createSQLQuery("exec SP_INSERT_EMAIL 3, :refNo");
                    qtr.setParameter("refNo", piForm.getPiNo());
                    qtr.executeUpdate();
                }
            } else if (userFunctionalities.contains("861") && piHdr.getStatus().equalsIgnoreCase("Pending At Buyer")) {
                if (piForm.getStatus().equalsIgnoreCase("Update")) {
                    updateOtherCharges(piForm, piHdr, hrbSession);
                    for (int i = 0; i < piForm.getOrderPartNoList().length; i++) {
                        piDtl = (SP_ORD_PI_DTL_EXP) hrbSession.load(SP_ORD_PI_DTL_EXP.class, piForm.getPiIdList()[i]);
                        piDtl.setFinalPiQty(piForm.getPiQtyList()[i]);
                        piDtl.setPrice(piForm.getPriceList()[i]);
                        piDtl.setSubStatus(piForm.getStatusList()[i].toUpperCase());
                        if (piDtl.getSubStatus().equals("NOT AVAILABLE")) {
                            piDtl.setStatus("NOT AVAILABLE");
                        } else {
                            piDtl.setStatus("AVAILABLE");
                        }
                        if (piDtl.getSubStatus().equals("AVAILABLE WITH LEAD TIME")) {
                            piDtl.setLeadTime(piForm.getLeadTimeList()[i]);
                        } else {
                            piDtl.setLeadTime("");
                        }
                        hrbSession.update(piDtl);
                        partValue = (piForm.getPiQtyList()[i] == null ? 0 : piForm.getPiQtyList()[i]) * (piForm.getPriceList()[i] == null ? 0.0f : piForm.getPriceList()[i]);
                        totalPartValue += partValue;
                    }
                    piHdr.setPartValue(totalPartValue);
                    piHdr.setTotalValue(totalPartValue + piHdr.getOtherCharges());
                } else {
                    piHdr.setStatus("Rejected By HO".toUpperCase());
                }
            } else if (userFunctionalities.contains("861") && piHdr.getStatus().equalsIgnoreCase("Pending At HO")) {
                if (piForm.getStatus().equalsIgnoreCase("Update")) {
                    updateOtherCharges(piForm, piHdr, hrbSession);
                    for (int i = 0; i < piForm.getOrderPartNoList().length; i++) {
                        piDtl = (SP_ORD_PI_DTL_EXP) hrbSession.load(SP_ORD_PI_DTL_EXP.class, piForm.getPiIdList()[i]);
                        piDtl.setFinalPiQty(piForm.getPiQtyList()[i]);
                        piDtl.setPrice(piForm.getPriceList()[i]);
                        piDtl.setSubStatus(piForm.getStatusList()[i].toUpperCase());
                        if (piDtl.getSubStatus().equals("NOT AVAILABLE")) {
                            piDtl.setStatus("NOT AVAILABLE");
                        } else {
                            piDtl.setStatus("AVAILABLE");
                        }
                        if (piDtl.getSubStatus().equals("AVAILABLE WITH LEAD TIME")) {
                            piDtl.setLeadTime(piForm.getLeadTimeList()[i]);
                        } else {
                            piDtl.setLeadTime("");
                        }
                        hrbSession.update(piDtl);
                        partValue = (piForm.getPiQtyList()[i] == null ? 0 : piForm.getPiQtyList()[i]) * (piForm.getPriceList()[i] == null ? 0.0f : piForm.getPriceList()[i]);
                        totalPartValue += partValue;
                    }
                    piHdr.setPartValue(totalPartValue);
                    piHdr.setTotalValue(totalPartValue + piHdr.getOtherCharges());
                    piHdr.setStatus("Pending At Buyer".toUpperCase());
                } else if (piForm.getStatus().equalsIgnoreCase("Rejected")) {
                    piHdr.setStatus("Rejected By HO".toUpperCase());
                }
            } else if (userFunctionalities.contains("863") && piHdr.getStatus().equalsIgnoreCase("Pending At Buyer") && piHdr.getBuyerEditAllowed().equals("N")) {
                if (piForm.getStatus().equalsIgnoreCase("Rejected")) {
                    piHdr.setStatus("Rejected By Buyer".toUpperCase());
                } else {
                    piHdr.setStatus("Accepted By Buyer".toUpperCase());
                }
            } else if (userFunctionalities.contains("861") && piHdr.getStatus().equalsIgnoreCase("Accepted By Buyer")) {
                if (piForm.getStatus().equalsIgnoreCase("GenerateOrder")) {
                    piHdr.setStatus("Sent to SAP".toUpperCase());
                  //  String newPiNo = piForm.getPiNo().replace("/", "-").concat(".txt");
                  //  File file = new File(newPiNo);
                  //  CreatePIDao.writingTxtFile_SAPSO(piForm.getPiNo(), hrbSession, file);                    
                } else if (piForm.getStatus().equalsIgnoreCase("Rejected")) {
                    piHdr.setStatus("Rejected By HO".toUpperCase());
                } else {
                    updateOtherCharges(piForm, piHdr, hrbSession);
                    for (int i = 0; i < piForm.getOrderPartNoList().length; i++) {
                        piDtl = (SP_ORD_PI_DTL_EXP) hrbSession.load(SP_ORD_PI_DTL_EXP.class, piForm.getPiIdList()[i]);
                        piDtl.setFinalPiQty(piForm.getPiQtyList()[i]);
                        piDtl.setPrice(piForm.getPriceList()[i]);
                        piDtl.setSubStatus(piForm.getStatusList()[i].toUpperCase());
                        if (piDtl.getSubStatus().equals("NOT AVAILABLE")) {
                            piDtl.setStatus("NOT AVAILABLE");
                        } else {
                            piDtl.setStatus("AVAILABLE");
                        }
                        if (piDtl.getSubStatus().equals("AVAILABLE WITH LEAD TIME")) {
                            piDtl.setLeadTime(piForm.getLeadTimeList()[i]);
                        } else {
                            piDtl.setLeadTime("");
                        }
                        hrbSession.update(piDtl);
                        partValue = (piForm.getPiQtyList()[i] == null ? 0 : piForm.getPiQtyList()[i]) * (piForm.getPriceList()[i] == null ? 0.0f : piForm.getPriceList()[i]);
                        totalPartValue += partValue;
                    }
                    piHdr.setPartValue(totalPartValue);
                    piHdr.setTotalValue(totalPartValue + piHdr.getOtherCharges());
                    piHdr.setStatus("Pending At Buyer".toUpperCase());
                }
            }
            hrbSession.update(piHdr);
            tx.commit();
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            try {
                if (hrbSession != null && hrbSession.isOpen()) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateOtherCharges(PIFormBean piForm, SP_ORD_PI_EXP piHdr, Session hrbSession) {
        SpOrdPiChrgExp otherCharges = null;
        SpOrdPiChrgExpPK otherChargePk = null;
        Query qry = hrbSession.createQuery("delete from SpOrdPiChrgExp s where s.spOrdPiChrgExpPK.piNo=:piNo");
        qry.setParameter("piNo", piForm.getPiNo().trim());
        qry.executeUpdate();

        float otherCharge = 0.0f;
        float totalOtherCharge = 0.0f;
        for (int j = 0; j < piForm.getOtherchargeType().length; j++) {
            if (piForm.getOtherChargeList()[j] != null && !piForm.getOtherChargeList()[j].equals("")) {
                otherCharge = Float.parseFloat(piForm.getOtherChargeList()[j]);
                if (otherCharge > 0) {
                    totalOtherCharge += otherCharge;
                    otherCharges = new SpOrdPiChrgExp();
                    otherChargePk = new SpOrdPiChrgExpPK();
                    otherChargePk.setPiNo(piHdr.getPiNo());
                    otherChargePk.setCostHeadId(piForm.getOtherchargeType()[j]);
                    otherCharges.setSpOrdPiChrgExpPK(otherChargePk);
                    otherCharges.setCostHeadValue(Float.parseFloat(piForm.getOtherChargeList()[j]));
                    hrbSession.save(otherCharges);
                }
            }
        }
        piHdr.setOtherCharges(totalOtherCharge);
        hrbSession.update(piHdr);
    }

    public void updateOrderStatus(PIFormBean piForm, Session hrbSession) {
        List poList = Arrays.asList(piForm.getPoDetailIdList());
        List<SpOrderDetailsEXP> ordDetailList;
        SpOrderDetailsEXP odetail;
        SPOrderHeaderEXP order;
        HashSet poNos = new HashSet(poList);
        Iterator t;
        boolean doneFlag = true;
        Iterator it = poNos.iterator();
        String poNo = "";
        while (it.hasNext()) {
            poNo = it.next().toString();
            order = (SPOrderHeaderEXP) hrbSession.load(SPOrderHeaderEXP.class, poNo.trim());
            ordDetailList = order.getSpOrderDetailsEXPList();
            t = ordDetailList.iterator();
            while (t.hasNext()) {
                odetail = (SpOrderDetailsEXP) t.next();
                if (odetail.getPendingQty() == 0) {
                    odetail.setStatus("PI DONE");
                } else {
                    doneFlag = false;
                }
                hrbSession.update(odetail);
            }

            if (doneFlag) {
                order.setStatus("PI DONE");
                hrbSession.update(order);
            }
        }
    }

    public List<PIFormBean> getListForPI(PIFormBean piForm) {
        List<PIFormBean> orderList = new ArrayList<PIFormBean>();
        PIFormBean obj = null;
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = hrbSession.createQuery("select umd.dealerName,umd.dealerCode, umd.location, h.ordType, "
                    + "count(h.custPoNo) as nOrder from Dealervslocationcode umd, SPOrderHeaderEXP h where "
                    + "umd.dealerCode=h.dealerCode and h.hoProcessStatus='PENDING' and h.status='CLOSE' "
                    + "group by umd.dealerName,umd.dealerCode, umd.location, h.ordType");

            List result = query.list();
            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    obj = new PIFormBean();
                    obj.setDealerName(data[0] == null ? "" : data[0].toString());
                    obj.setDealerCode(data[1] == null ? "" : data[1].toString());
                    obj.setLocation(data[2] == null ? "" : data[2].toString());
                    obj.setOrderType(data[3] == null ? "" : data[3].toString());
                    obj.setNoOfOrder(data[4] == null ? 0 : Integer.parseInt(data[4].toString()));
                    orderList.add(obj);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    public List<PIFormBean> getInvoiceDetailView(PIFormBean piForm) {
        List<PIFormBean> piList = new ArrayList<PIFormBean>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Session hrbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            hrbSession.beginTransaction();

//            Query query = hrbSession.createQuery("Select distinct spi.spPiInvoiceEXPPK.invoiceNo,"
//                    + "spi.spPiInvoiceEXPPK.erpOrderNo,spi.spPiInvoiceEXPPK.erpPartOrderNo,"
//                    + "spi.invoiceDate,spi.status,spi.totalInvoiceAmount, spi.orderedPart,spi.shippedPartNo,"
//                    + " (select cp.p1 from CatPart cp where cp.partNo=spi.shippedPartNo) as partDesc,"
//                    + " spi.qtyShipped,spi.invoicedRate,spi.spPiInvoiceEXPPK.erpOrderNo,"
//                    + " spi.dmsPINo, spi.remarks,spi.dispatchMode,spi.awbBolNo,spi.awbBolDate,"
//                    + " spi.inspCharge,spi.localTPT,spi.insurnceCharge,spi.freightCharge,spi.otherCharge"
//                    + " from SpPiInvoiceEXP spi where spi.dmsPINo=:piNo");

            Query query = hrbSession.createQuery("Select distinct spi.invoiceNo,"
                    + " spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo,spd.spOrdPIInvoiceDTLEXPPK.erpPartOrderNo,"
                    + " spi.invoiceDate,spd.status,spi.totalInvoiceAmount, spd.orderedPart,spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo) as partDesc,"
                    + " spd.qtyShipped,spd.invoicedRate,spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo,"
                    + " spd.spOrdPIInvoiceDTLEXPPK.dmsPINo, spd.remarks,spi.dispatchMode,spi.awbBolNo,spi.awbBolDate,"
                    + " spi.inspCharge,spi.localTPT,spi.insurnceCharge,spi.freightCharge,spi.otherCharge"
                    + " from  SpOrdPIInvoiceHDREXP spi,SpOrdPIInvoiceDTLEXP spd where spd.spOrdPIInvoiceDTLEXPPK.invoiceId=spi.invoiceId  and spd.spOrdPIInvoiceDTLEXPPK.dmsPINo=:piNo");

            query.setParameter("piNo", piForm.getPiNo().trim());
            List result = query.list();
            PIFormBean piform;
            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();

                    piForm.setInspChargeInv(data[17] == null ? 0 : Float.parseFloat(data[17].toString().trim()));
                    piForm.setLocalTPTInv(data[18] == null ? 0 : Float.parseFloat(data[18].toString().trim()));
                    piForm.setInsurnceChargeInv(data[19] == null ? 0 : Float.parseFloat(data[19].toString().trim()));
                    piForm.setFreightChargeInv(data[20] == null ? 0 : Float.parseFloat(data[20].toString().trim()));
                    piForm.setOtherChargeInv(data[21] == null ? 0 : Float.parseFloat(data[21].toString().trim()));
                    piForm.setFinalAmount(data[5] == null ? " " : data[5].toString().trim());

                    piform = new PIFormBean();
                    piform.setInvoiceNo(data[0] == null ? "" : data[0].toString());
                    piform.setInvoiceDate(data[3] == null ? "" : sdf2.format(sdf.parse(data[3].toString().trim())));
                    piform.setDispatchMode(data[14] == null ? "-" : data[14].toString().trim());
                    piform.setAwbBolNo(data[15] == null ? "-" : data[15].toString().trim());
                    piform.setAwbBolDate(data[16] == null ? "-" : sdf2.format(sdf.parse(data[16].toString().trim())));
                    piform.setDmsPINo(data[12] == null ? "-" : data[12].toString().trim());
                    piform.setErpOrderNo(data[1] == null ? "-" : data[1].toString().trim());
                    piform.setOrderedPart(data[6] == null ? "-" : data[6].toString().trim());
                    piform.setShippedPartNo(data[7] == null ? "-" : data[7].toString().trim());
                    piform.setPartDescInvoice(data[8] == null ? "-" : data[8].toString().trim());
                    piform.setStatusInvoice(data[4] == null ? "-" : data[4].toString().trim());
                    piform.setRemarkInvoice(data[13] == null ? "-" : data[13].toString().trim());
                    piform.setQtyShipped(data[9] == null ? 0 : Integer.parseInt(data[9].toString().trim()));
                    piform.setPartPriceInvoice(data[10] == null ? 0.0 : Double.parseDouble(data[10].toString()));

                    piList.add(piform);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return piList;
    }

    public static void writingTxtFile_SAPSO(String piNo, Session session, File file) throws Exception {

        FileOutputStream is = null;
        OutputStreamWriter osw = null;
        Writer w = null;
        try {
            is = new FileOutputStream(file);
            osw = new OutputStreamWriter(is);
            int count = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            String hql = "select pih.dealerCode,pih.consigneeCode,pih.piNo,pih.piDate, pih.incoTerms,"
                    + " (select it.incoTermDesc from MSPIncoTermsEXPMaster it where it.incoTermCode=pih.incoTerms) "
                    + " as incoTermsDesc, pih.paymentTerm,(select dm.deliveryTermDesc from MSPDeliveryTermsEXPMaster dm "
                    + " where pih.deliveryTerms=dm.deliveryTermCode) as Mode_Of_Dispatch, "
                    + " (select dp.dischargePortName from MSPDischargePortEXPMaster dp where pih.dischargePort=dp.dischargePortCode) as Dischrge_Port_name,"
                    + " pih.destinationPlace,d.orderPartNo, d.finalPiQty, d.poDetailId "
                    + " from SP_ORD_PI_EXP pih, SP_ORD_PI_DTL_EXP d "
                    + " where d.sp_ORD_PI_EXP.piNo=pih.piNo and pih.piNo = '" + piNo + "' ";

            Query query = session.createQuery(hql);
            w = new BufferedWriter(osw);
            if (count == 0) {
                w.write("Order S.No.");
                w.write("\t");
                w.write("Quotation Type");
                w.write("\t");
                w.write("Sale Organization");
                w.write("\t");
                w.write("Distribution Channel");
                w.write("\t");
                w.write("Divison");
                w.write("\t");
                w.write("Sold To Party");
                w.write("\t");
                w.write("Ship To Party");
                w.write("\t");
                w.write("PI NO.");
                w.write("\t");
                w.write("PI Date");
                w.write("\t");
                w.write("Valid To");
                w.write("\t");
                w.write("Inco Terms 1");
                w.write("\t");
                w.write("Inco Terms 2");
                w.write("\t");
                w.write("Payment Terms");
                w.write("\t");
                w.write("Mode of Dispatch");
                w.write("\t");
                w.write("Port Of Discharge");
                w.write("\t");
                w.write("Port Of Loading");
                w.write("\t");
                w.write("Final Destination");
                w.write("\t");
                w.write("Part Code");
                w.write("\t");
                w.write("Order Qty");
                w.write("\t");
                w.write("Plant");
                w.write("\t");
                w.write("Storage Location");
                w.write("\t");
                w.write("Inspection Charges");
                w.write("\t");
                w.write("Local Transportation");
                w.write("\t");
                w.write("Insurance");
                w.write("\t");
                w.write("Sea / Air Freight");
                w.write("\t");
                w.write("Others (if any)");
                w.write("\t");
                w.write("O Ref. No.");
                w.write("\t");
                w.write("T Ref. No.");
                w.write("\t");
                w.write("SAP O Ref. No.");
                w.write("\t");
                w.write("SAP T Ref. No.");
                w.write("\t");
                w.write("O Item");
                w.write("\t");
                w.write("O Qty");
                w.write("\t");
                w.write("\n");
            }
            Iterator itr = query.list().iterator();
            int counter = 1;
            while (itr.hasNext()) {
                Object ob[] = (Object[]) itr.next();
                w.write(counter++ + "\t"
                        + ("ZQTS") + "\t"
                        + ("ITL2") + "\t"
                        + ("DL") + "\t"
                        + ("SP") + "\t"
                        + (ob[0] == null ? "" : ob[0].toString()) + "\t"
                        + (ob[1] == null ? "" : ob[1].toString()) + "\t"
                        + (ob[2] == null ? "" : ob[2].toString().toUpperCase()) + "\t"
                        + (ob[3] == null ? "" : (sdf.format(sdf1.parse(ob[3].toString())))) + "\t"
                        + (sdf.format(new Date())) + "\t"
                        + (ob[4] == null ? "" : ob[4].toString()) + "\t"
                        + (ob[5] == null ? "" : ob[5].toString()) + "\t"
                        + (ob[6] == null ? "" : ob[6].toString()) + "\t"
                        + (ob[7] == null ? "" : ob[7].toString()) + "\t"
                        + (ob[8] == null ? "" : ob[8].toString()) + "\t"
                        + ("Any Indian Port") + "\t"
                        + (ob[9] == null ? "" : ob[9].toString()) + "\t"
                        + (ob[10] == null ? "" : ob[10].toString().toUpperCase()) + "\t"
                        + (ob[11] == null ? "" : Integer.parseInt(ob[11].toString())) + "\t"
                        + ("3000") + "\t"
                        + ("SXPD") + "\t"
                        + ("ZECT") + "\t"
                        + ("ZDTC") + "\t"
                        + ("ZINS") + "\t"
                        + ("ZOFF") + "\t"
                        + ("ZPKG") + "\t"
                        + (ob[2] == null ? "" : ob[2].toString().toUpperCase()) + "\t"
                        + (ob[12] == null ? "" : ob[12].toString().toUpperCase()) + "\t"
                        + "" + "\t"
                        + "" + "\t"
                        + "" + "\t"
                        + "" + "\t");
                w.write("\n");
                w.flush();
                count++;
            }
            fileUploadOnFTPServer_SAPSO(file);

        } finally {
            if (w != null) {
                w.close();
            }
            if (is != null) {
                is.close();
            }
            if (osw != null) {
                osw.close();
            }
        }
    }

    public static void fileUploadOnFTPServer_SAPSO(File file) throws Exception {

        boolean done = false;
        //String server = "55.160.98.134";                              
        String server = dbConnection.ftp_server_ip;                     // String server ="59.160.98.134";
        int port = Integer.parseInt(dbConnection.ftp_server_port);      // int port = 21;
        String user = dbConnection.ftp_server_username;                 // String user = "general";
        String pass = dbConnection.ftp_server_password;                 // String pass = "gen@1234";
        String remoteFile = dbConnection.sap_SO_Path;

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String remoteFilePath = remoteFile.concat("/") + file;
            InputStream inputStream = new FileInputStream(file);  //localFile
            done = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully using FTP.");
            }
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                System.out.println("Connection Error..");
                ex.printStackTrace();
            }
        }
    }
    public List<PIFormBean> getInvoiceCancelledDetailView(PIFormBean piForm) {
        List<PIFormBean> piCancelledList = new ArrayList<PIFormBean>();
        Session hrbSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            hrbSession.beginTransaction();
            Query query = hrbSession.createQuery("Select distinct spci.erpOrderNo,spci.erpPartOrderNo, spci.status,spci.cancelledPart,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spci.cancelledPart) as partDesc,"
                    + " spci.qtyCancelled, spci.dmsPiNo, spci.remarks from  SpOrdCancelledExp spci where spci.dmsPiNo=:piNo");

            query.setParameter("piNo", piForm.getPiNo().trim());
            List result = query.list();
            PIFormBean piform;
            if (result != null && !result.isEmpty()) {
                itr = result.iterator();
                while (itr.hasNext()) {
                    data = (Object[]) itr.next();
                    piform = new PIFormBean();
                    piform.setErpOrderNo(data[0] == null ? "-" : data[0].toString().trim());
                    piform.setOrderedPart(data[1] == null ? "-" : data[1].toString().trim());
                    piform.setStatusInvoice(data[2] == null ? "-" : data[2].toString().trim());
                    piform.setShippedPartNo(data[3] == null ? "-" : data[3].toString().trim());
                    piform.setPartDescInvoice(data[4] == null ? "-" : data[4].toString().trim());
                    piform.setQtyShipped(data[5] == null ? 0 : Integer.parseInt(data[5].toString().trim()));
                    piform.setDmsPINo(data[6] == null ? "-" : data[6].toString().trim());
                    piform.setRemarkInvoice(data[7] == null ? "-" : data[7].toString().trim());
                    piCancelledList.add(piform);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return piCancelledList;
    }
}
