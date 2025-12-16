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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

import com.common.MethodUtility;

import HibernateMapping.SPOrderHeaderEXP;
import HibernateMapping.SP_ORD_PI_DTL_EXP;
import HibernateMapping.SP_ORD_PI_EXP;
import HibernateMapping.SpOrdCancelledExp;
import HibernateMapping.SpOrdPIInvoiceDTLEXP;
import HibernateMapping.SpOrdPIInvoiceDTLEXPPK;
import HibernateMapping.SpOrdPIInvoiceHDREXP;
import HibernateMapping.SpOrderDetailsEXP;
import HibernateMapping.SpOrderInvGrn;
import HibernateMapping.SpOrderInvGrnDetails;
import HibernateMapping.SpOrderInvGrnDetailsPK;
import HibernateUtil.HibernateUtil;
import beans.inventoryForm;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author Avinash.Pandey
 */
public class inventoryEXPDAO
{

    public String getNewPo(String dealerCode)
    {
        String vendorDesc = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try
        {
            Query query = hrbsession.createQuery("select dm.address from Dealervslocationcode dm "
                    + " where dm.dealerCode=:dealerCode");
            query.setParameter("dealerCode", dealerCode);
            vendorDesc = "" + query.list().get(0);

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
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return vendorDesc;
    }

    //  Create New Order(STD/VOR)
    public ArrayList<inventoryForm> partListbyOldPO(inventoryForm inForm, String priceListCode)
    {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        Double totalAmm = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            Query partQuery = hrbsession.createQuery("select sd.partNo ,sd.qty,sd.price from SpOrderDetailsEXP sd where sd.custPoNo=:custPoNo");  //
            partQuery.setParameter("custPoNo", inForm.getPrevPoNo());
            Iterator itr = partQuery.list().iterator();
            while (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
                String partNo = o[0] == null ? "" : o[0].toString();
                String qty = o[1] == null ? "" : o[1].toString();
                String partData = getPriceForOrderByPartNo(partNo, hrbsession, priceListCode);
                inventoryForm inf = new inventoryForm();

                if (!partData.equals(""))
                {
                    inf.setPartno(partNo);
                    inf.setQty("" + qty);
                    inf.setPart_desc(partData.split("@@")[0]);
                    inf.setPartTypeStr(partData.split("@@")[1]);
                    inf.setUnitValue(partData.split("@@")[2]);
                    inf.setMoq(partData.split("@@")[3]);
                    inf.setService(partData.split("@@")[4]);
                    inf.setAmountPerPrice("" + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2])));
                    if (inForm.getTotalAmont() != null && !inForm.getTotalAmont().equals(""))
                    {
                        totalAmm = Double.parseDouble(inForm.getTotalAmont());
                    }
                    totalAmm = totalAmm + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2]));
                    inf.setTotalAmont(df.format(totalAmm));
                    inForm.setTotalAmont(df.format(totalAmm));
                    dataList.add(inf);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
			if(hrbsession != null && hrbsession.isOpen()) {
				hrbsession.close();
			}
		}
        return dataList;

    }

    public ArrayList<inventoryForm> addIntoList(File xlsfile, String dealercode, String cntxpath, inventoryForm invForm, String userId, String priceListCode)
    {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Session hrbsession = null;
        int row = 1, column = 0;
        ArrayList<inventoryForm> dataListNew = new ArrayList<inventoryForm>();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int qty = 0;
        int partnoAdded = 0;
        ArrayList partList = null;
        Double totalAmm = 0.0;
        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);


            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                qty = Integer.parseInt(sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim());
                column++;
                String partData = getPriceForOrderByPartNo(partno, hrbsession, priceListCode);
                if ((partData != null) && !partData.equals(""))
                {
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
                    dataListNew.add(inf);
                    partnoAdded++;
                    column = 0;
                    row++;
                }
                else
                {
                    inventoryForm inf = new inventoryForm();     
                    inf.setPartno(partno.trim());
                    inf.setQty("" + qty);
                    inf.setPart_desc("");
                    inf.setPartTypeStr("");
                    inf.setUnitValue("0");
                    inf.setMoq("1");
                    inf.setService("");
                    inf.setAmountPerPrice("");
                    dataListNew.add(inf);
                    partnoAdded++;
                    column = 0;
                    row++;
                }
            }

            // System.out.println(" list size is " + dataListNew.size());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return dataListNew;
    }

    public inventoryForm saveNewOrderByCart(inventoryForm invForm, String userId)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String result = "fail";
        int count = 0;
        double totalValue = 0.0;
        Query query = null;
        Transaction tx = null;
        try
        {
            tx=session.beginTransaction();


            if (invForm.getPoNoRadio().equalsIgnoreCase("newPo"))
            {
                //String refNo = new MethodUtility().getNumber(session, "SpOrderMaster", invForm.getDealerCode(), "PO");
                String refNo = invForm.getRefNo();

                if (!isPONumberExist(session, refNo))
                {
                    invForm.setRefNo(refNo);
                    SPOrderHeaderEXP spoMas = new SPOrderHeaderEXP();
                    spoMas.setCurrency(invForm.getCurrency());
                    spoMas.setPriceListCode(invForm.getPriceListCode());
                    spoMas.setCustPoNo(refNo);
                    spoMas.setOrdType(invForm.getOrderType());
                    spoMas.setDealerCode(invForm.getDealerCode());
                    spoMas.setDealerRefNo(invForm.getDealerRefNo());
                    spoMas.setComments(invForm.getSpecInstr());
                    if (invForm.getOrderType().equalsIgnoreCase("VOR"))
                    {
                        spoMas.setChassisNo(invForm.getChassisNo());
                        spoMas.setEngineNo(invForm.getEngineNo());
                        spoMas.setModelNo(invForm.getModelNo());
                        spoMas.setJobCardNo(invForm.getFirNo());
                    }
                    spoMas.setDeliveryTerms(invForm.getDeliveryCode());
                    spoMas.setPaymentTerms(invForm.getPaymentTerms());
                    spoMas.setIncoTerms(invForm.getIncoTerms());
                    spoMas.setConsigneeCode(invForm.getConsigneeCode());
                    spoMas.setConsigneeName(invForm.getConsigneeName());
                    spoMas.setConsigneeAddress(invForm.getConsigneeAddress());
                    spoMas.setConsigneeCountry(invForm.getConsigneeCountry());
                    spoMas.setDischargePort(invForm.getDischargePort());
                    spoMas.setDestinationPlace(invForm.getDestinationPlace());
                    spoMas.setPrecarriageBy(invForm.getPrecarriageBy());
                    spoMas.setPlacePreCarrier(invForm.getPlacePreCarrier());
                    spoMas.setTotalValue(0.0);
                    spoMas.setCustPoDate(new Date());
                    spoMas.setHoProcessStatus("PENDING");
                    spoMas.setCreatedOn(new Date());
                    if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft"))
                    {
                        spoMas.setStatus("OPEN");
                    }
                    else
                    {
                        spoMas.setStatus("CLOSE");
                    }
                    spoMas.setIsDocumentUpload("N");
                    spoMas.setDocumentName(invForm.getDocumentName());
                    spoMas.setCreatedBy(userId);
                    spoMas.setHoRemarks(null);
                    spoMas.setLastUpdatedOn(null);
                    spoMas.setIsServerSync('N');
                    session.save(spoMas);
                    for (int i = 0; i < invForm.getPartNo().length; i++)
                    {

                        if (!invForm.getPartNo()[i].equals(""))
                        {
                            SpOrderDetailsEXP spoDet = new SpOrderDetailsEXP();
                            spoDet.setCustPoNo(refNo);
                            spoDet.setPositionNo(count + 1);
                            spoDet.setPartNo(invForm.getPartNo()[i]);
                            spoDet.setQty(Integer.parseInt(invForm.getQuantity()[i]));
                            spoDet.setPrice(Double.parseDouble(invForm.getUnitprice()[i]));
                            spoDet.setIsServerSync('N');
                            if (!invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft"))
                            {
                                spoDet.setPendingQty(spoDet.getQty());
                                spoDet.setLastUpdatedOn(new Date());
                            }
                            spoDet.setPiQty(0);
                            spoDet.setStatus("PENDING");
                            session.save(spoDet);
                            totalValue += spoDet.getQty() * spoDet.getPrice();
                            count++;
                        }
                    }
                    spoMas.setTotalValue(totalValue);
                }
                else
                {
                    invForm.setResult("poExist");
                }
            }
            else
            {
                SPOrderHeaderEXP spdetforPrePO = (SPOrderHeaderEXP) session.load(SPOrderHeaderEXP.class, invForm.getRefNo());
                spdetforPrePO.setCurrency(invForm.getCurrency());
                spdetforPrePO.setPriceListCode(invForm.getPriceListCode());
                spdetforPrePO.setOrdType(invForm.getOrderType());
                spdetforPrePO.setDealerCode(invForm.getDealerCode());
                spdetforPrePO.setDealerRefNo(invForm.getDealerRefNo());
                spdetforPrePO.setComments(invForm.getSpecInstr());
                if (invForm.getOrderType().equalsIgnoreCase("VOR"))
                {
                    spdetforPrePO.setChassisNo(invForm.getChassisNo());
                    spdetforPrePO.setEngineNo(invForm.getEngineNo());
                    spdetforPrePO.setModelNo(invForm.getModelNo());
                    spdetforPrePO.setJobCardNo(invForm.getFirNo());     // prashant
                }
                spdetforPrePO.setDeliveryTerms(invForm.getDeliveryCode());
                spdetforPrePO.setPaymentTerms(invForm.getPaymentTerms());
                spdetforPrePO.setIncoTerms(invForm.getIncoTerms());
                spdetforPrePO.setConsigneeCode(invForm.getConsigneeCode());
                spdetforPrePO.setConsigneeName(invForm.getConsigneeName());
                spdetforPrePO.setConsigneeAddress(invForm.getConsigneeAddress());
                spdetforPrePO.setConsigneeCountry(invForm.getConsigneeCountry());
                spdetforPrePO.setDischargePort(invForm.getDischargePort());
                spdetforPrePO.setDestinationPlace(invForm.getDestinationPlace());
                spdetforPrePO.setPrecarriageBy(invForm.getPrecarriageBy());
                spdetforPrePO.setPlacePreCarrier(invForm.getPlacePreCarrier());
                spdetforPrePO.setCreatedBy(userId);
                spdetforPrePO.setHoRemarks(null);
                spdetforPrePO.setDocumentName(invForm.getDocumentName());
                spdetforPrePO.setIsDocumentUpload(invForm.getIsDocumentUpload());
                spdetforPrePO.setCustPoDate(new Date());
                spdetforPrePO.setHoProcessStatus("PENDING");
                if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft"))
                {
                    spdetforPrePO.setStatus("OPEN");
                }
                else
                {
                    spdetforPrePO.setStatus("CLOSE");

                }
                spdetforPrePO.setTotalValue(0.0);
                spdetforPrePO.setLastUpdatedOn(null);
                spdetforPrePO.setIsServerSync('N');
                session.saveOrUpdate(spdetforPrePO);

                String hql = "delete from SpOrderDetailsEXP where custPoNo='" + invForm.getRefNo() + "'";
                query = session.createQuery(hql);
                query.executeUpdate();

                for (int i = 0; i < invForm.getPartNo().length; i++)
                {
                    if (!invForm.getPartNo()[i].equals(""))
                    {
                        SpOrderDetailsEXP spoDet = new SpOrderDetailsEXP();
                        spoDet.setCustPoNo(invForm.getRefNo());
                        spoDet.setPartNo(invForm.getPartNo()[i].trim());
                        spoDet.setPositionNo(count + 1);
                        spoDet.setQty(Integer.parseInt(invForm.getQuantity()[i]));
                        if (!invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft"))
                        {
                            spoDet.setPendingQty(spoDet.getQty());
                            spoDet.setLastUpdatedOn(new Date());
                        }
                        spoDet.setStatus("PENDING");
                        spoDet.setPrice(Double.parseDouble(invForm.getUnitprice()[i]));
                        spoDet.setIsServerSync('N');
                        spoDet.setPiQty(0);
                        session.save(spoDet);
                        totalValue += spoDet.getQty() * spoDet.getPrice();
                        count++;
                    }
                }
                spdetforPrePO.setTotalValue(totalValue);
            }

            System.out.println("Result:- "+invForm.getResult());
            System.out.print("If case : ");
            System.out.print((invForm.getResult() == null) || (!invForm.getResult().equalsIgnoreCase("poExist")));
            
            if ((invForm.getResult() == null) || (!invForm.getResult().equalsIgnoreCase("poExist")))
            {                                          
                if (!invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft"))
                {
                    Query qtr = session.createSQLQuery("exec SP_INSERT_EMAIL 1, :refNo");
                    qtr.setParameter("refNo", invForm.getRefNo());
                    qtr.executeUpdate();
                }
                 tx.commit();
                 System.out.println("SucessResult");
                invForm.setResult("success");
            }

        }
        catch (Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return invForm;
    }

    public inventoryForm getCustPoNo(inventoryForm invForm)
    {             //prashant

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();

        try
        {
            Query query = hrbsession.createQuery("select oh.ordType,oh.jobCardNo,oh.chassisNo,oh.modelNo,oh.engineNo,oh.dealerRefNo,oh.deliveryTerms,"
                    + " oh.paymentTerms,oh.incoTerms,oh.consigneeCode,oh.consigneeName ,oh.consigneeAddress,oh.consigneeCountry,"
                    + " oh.dischargePort,oh.destinationPlace,oh.documentName,oh.comments,oh.dealerCode,oh.totalValue,oh.status,"
                    + " oh.precarriageBy,oh.placePreCarrier from SPOrderHeaderEXP oh where oh.custPoNo=:custPoNo");

            query.setParameter("custPoNo", invForm.getPrevPoNo());
            Iterator itr = query.list().iterator();
            if (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                if (obj != null)
                {
//                    invForm = new inventoryForm();
                    invForm.setOrderType(obj[0] == null ? "" : obj[0].toString());
                    if (invForm.getOrderType().equalsIgnoreCase("VOR"))
                    {
                        invForm.setChassisNo(obj[2] == null ? "" : obj[2].toString());
                        invForm.setEngineNo(obj[4] == null ? "" : obj[4].toString());
                        invForm.setModelNo(obj[3] == null ? "" : obj[3].toString());
                        invForm.setFirNo(obj[1] == null ? "" : obj[1].toString());
                    }
                    invForm.setDealerCode(obj[17] == null ? "" : obj[17].toString());
                    invForm.setDealerRefNo(obj[5] == null ? "" : obj[5].toString());
                    invForm.setSpecInstr(obj[16] == null ? "" : obj[16].toString());

                    invForm.setDeliveryTerms(obj[6] == null ? "" : obj[6].toString());
                    invForm.setPaymentTerms(obj[7] == null ? "" : obj[7].toString());
                    invForm.setIncoTerms(obj[8] == null ? "" : obj[8].toString());
                    invForm.setConsigneeCode(obj[9] == null ? "" : obj[9].toString());
                    invForm.setConsigneeName(obj[10] == null ? "" : obj[10].toString());
                    invForm.setConsigneeAddress(obj[11] == null ? "" : obj[11].toString());
                    invForm.setConsigneeCountry(obj[12] == null ? "" : obj[12].toString());
                    invForm.setDischargePort(obj[13] == null ? "" : obj[13].toString());
                    invForm.setDestinationPlace(obj[14] == null ? "" : obj[14].toString());
                    invForm.setPrecarriageBy(obj[20] == null ? "" : obj[20].toString());
                    invForm.setPlacePreCarrier(obj[21] == null ? "" : obj[21].toString());
                    invForm.setDocumentName(obj[15] == null ? "" : obj[15].toString());
                    invForm.setStatus(obj[19] == null ? "" : obj[19].toString());
                    invForm.setTotalAmont(obj[18] == null ? "" : obj[18].toString());

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
                if (hrbsession != null)
                {
                    hrbsession.close();
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return invForm;
    }

    private boolean isPONumberExist(Session hrbSession, String refNo)
    {
        String query = "select a.custPoNo from SPOrderHeaderEXP a where a.custPoNo=:poNo";
        boolean poNumberStatus = true;

        try
        {

            Query hrbQuery = hrbSession.createQuery(query);
            hrbQuery.setParameter("poNo", refNo);
            Collection result = hrbQuery.list();
            if ((result == null) || (result.size() == 0))
            {
                poNumberStatus = false;
            }
            else
            {
                poNumberStatus = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return poNumberStatus;
    }

    public String deliveryTermsList(String deliveryTermCode, Session hrbSession)
    {
        String deliveryTermDesc = "";
        try
        {
            Query query = hrbSession.createQuery("select cp.deliveryTermDesc  from MSPDeliveryTermsEXPMaster cp  where  cp.deliveryTermCode=:deliveryTermCode");   //cp.p4='Y'
            query.setParameter("deliveryTermCode", deliveryTermCode);
            deliveryTermDesc = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return deliveryTermDesc;
    }

    public String paymentTermsList(String paymentTermCode, Session hrbSession)
    {
        String paymentTermDesc = "";
        try
        {
            Query query = hrbSession.createQuery("select cp.paymentTermDesc  from MSPPaymentTermsEXPMaster cp  where  cp.paymentTermCode=:paymentTermCode");   //cp.p4='Y'
            query.setParameter("paymentTermCode", paymentTermCode);
            paymentTermDesc = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return paymentTermDesc;
    }

    public String incoTermsList(String incoTermCode, Session hrbSession)
    {
        String incoTermDesc = "";
        try
        {
            Query query = hrbSession.createQuery("select cp.incoTermDesc  from MSPIncoTermsEXPMaster cp  where  cp.incoTermCode=:incoTermCode");   //cp.p4='Y'
            query.setParameter("incoTermCode", incoTermCode);
            incoTermDesc = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return incoTermDesc;
    }

    public String countriesEXPList(String destinationCountry, Session hrbSession)
    {
        String countryName = "";
        try
        {
            Query query = hrbSession.createQuery("select cp.countryName  from MSPCountriesEXPMaster cp  where  cp.countryCode=:countryCode");   //cp.p4='Y'
            query.setParameter("destinationCountry", destinationCountry);
            countryName = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return countryName;
    }

    public String dischargePortList(String dischargePort, Session hrbSession)
    {
        String dischargePortName = "";
        try
        {
            Query query = hrbSession.createQuery("select cp.dischargePortName  from MSPDischargePortEXPMaster cp  where  cp.dischargeCountryCode=:dischargeCountryCode");   //cp.p4='Y'
            query.setParameter("dischargeCountryCode", dischargePort);
            dischargePortName = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dischargePortName;
    }

    public String getPriceForOrderByPartNo(String partno, Session hrbSession, String priceListCode)
    {
        String tempDesc = "", moq = "1";
        try
        {
            Query query = hrbSession.createQuery("select cp.p1,cp.partType,pm.orderPrice,cp.np2 , cp.p4 "
                    + " from CatPart cp left join cp.spPriceMasterList pm "
                    + " where  cp.partNo=:partNo and (pm.spPriceMasterPK.pricelistCode is null or pm.spPriceMasterPK.pricelistCode='" + priceListCode + "')");   //cp.p4='Y'

            query.setParameter("partNo", partno);
            Iterator itr = query.list().iterator();

            if (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
                moq = "1";
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (o[2] == null ? "0" : o[2].toString()) + "@@" + (moq == null ? "1" : moq) + "@@" + (o[4] == null ? "N" : o[4].toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tempDesc;
    }
    public String getPartNo(String partno, Session hrbSession)
    {
        String tempDesc = "", moq = "1";
        try
        {
            Query query = hrbSession.createQuery("select cp.p1,cp.partType,cp.np2,cp.p4"
                    + " from CatPart cp where  cp.partNo=:partNo");   //cp.p4='Y'

            query.setParameter("partNo", partno);
            Iterator itr = query.list().iterator();

            if (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
                moq = "1";
                tempDesc = (o[0] == null ? "" : o[0].toString()) + "@@" + (o[1] == null ? "" : o[1].toString()) + "@@" + (o[2] == null ? "0" : o[2].toString()) + "@@" + (moq == null ? "1" : moq);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tempDesc;
    }

    public String getPaintedPartNos(String partno, Session hrbSession)
    {
        String tempDesc = "";
        Query query = null;
        try
        {

            query = hrbSession.createSQLQuery("select PAINT_CODE from CAT_PAINT_CODE_MASTER where PART_NO='" + partno + "'").addScalar("PAINT_CODE", StringType.INSTANCE);
            List paintList = query.list();
            Iterator itr = paintList.iterator();

            while (itr.hasNext())
            {
                tempDesc = tempDesc + itr.next().toString() + "@@";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return tempDesc;
    }

    public String getPartNoInCatPart(String partno, Session hrbSession, String priceListCode)
    {
        String tempDesc = "";
        Query query = null;
        try
        {

            query = hrbSession.createQuery("select p.partNo,p.p4,(select s.price from SpPriceMaster s where s.spPriceMasterPK.item=:partNo and s.spPriceMasterPK.pricelistCode=:pricelistCode) as price from CatPart p where p.partNo=:partNo");
            query.setParameter("partNo", partno);
            query.setParameter("pricelistCode", priceListCode);
            List paintList = query.list();
            Iterator itr = paintList.iterator();

            if (itr.hasNext())
            {
                Object[] obj = (Object[]) itr.next();
                tempDesc = obj[0].toString() + "%%" + (obj[1] == null ? "N" : obj[1].toString()) + "%%" + (obj[2] == null ? "-" : obj[2].toString());
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

            query = hrbSession.createSQLQuery("Select a.ALTERNATE_PART_NO,a.SET_NO,p.p1 from CAT_ALTERNATE_PART_MASTER a,CAT_PART p where a.PART_NO=p.part_no and a.PART_NO='" + partno + "' order by a.SET_NO").addScalar("ALTERNATE_PART_NO", StringType.INSTANCE).addScalar("SET_NO", StringType.INSTANCE).addScalar("p1", StringType.INSTANCE);
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
                            altPartList = altPartList + "\n" + (i++) + ".";
                            partList = partList.substring(1);
                            if (partList.split(",").length > 1)
                            {
                                altPartList = altPartList + "Combination of " + partList.replace(",", " & ");
                            }
                            else
                            {
                                altPartList = altPartList + "  " + partList;
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
                            altPartList = altPartList + "\n" + (i++) + ".";
                            partList = partList.substring(1);
                            if (partList.split(",").length > 1)
                            {
                                altPartList = altPartList + "Combination of " + partList.replace(",", " & ");
                            }
                            else
                            {
                                altPartList = altPartList + "  " + partList;
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

    public boolean uploadXls(String fileName, String filePath, FormFile uploadFile, String realPath)
    {
        boolean isUploaded = false;
        if (!fileName.equals(""))
        {
            fileName.toUpperCase().replaceAll(".XLS", ".xls");
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

    public String newOrderExcelValidated(File xlsfile) throws Exception
    {
        ArrayList<String> part_no = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "failure1", partno = "", qty = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        try
        {
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NO")))
            {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PART NO' Missing. Template error.";
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY")))
            {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'QTY' Missing. Template error.";
            }
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                return "Error, No Records Available.";
            }
            totalrows = (sheet.getRows());
            while (row < totalrows)
            {
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
                {
                    for (column = 0; column < 3; column++)
                    {

                        if (column == 0)
                        {
                            partno = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partno.equals(""))
                            {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be blank. ";
                            }
                            if (partno.length() > 50)
                            {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part No. can not be greater than 50 characters.";
                            }
                            if (partno.equals("-") || partno.equals("/"))
                            {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Please enter valid Part No. ";
                            }
                            r = isRegularExpression(partno);
                            if (r == true)
                            {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Special Symbols are not allowed in Part No.";

                            }
                            if (part_no.contains(partno))
                            {
                                return "Error In Row " + (row + 1) + " , Column " + (column + 1) + ". Part No cannot be entered twice.";
                            }
                            if (!part_no.contains(partno))
                            {
                                part_no.add(partno);
                            }
                        }
                        else if (column == 1)
                        {

                            qty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (qty.equals(""))
                            {
                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".Quantity can not be blank.";
                            }
                            else
                            {
                                r = containsOnlyNumbers(qty);
                                if (r != true)
                                {
                                    return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Min Quantity.";
                                }
                            }
                        }
                    }
                }
                else
                {
                    result = "success1";
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.";
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
            return "Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.";


        } //if excel is currupted or wrong path specified.
        catch (Exception e)
        {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;
    }

    public static boolean containsOnlyNumbers(String str)
    {

        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
        {
            return false;
        }

        for (int i = 0; i < str.length(); i++)
        {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isRegularExpression(String s)
    {
        final char[] regExpChars = new char[]
        {
            '*', '+', '[', ']', '.', '^', '&', '\\', '$', '~', '`', '\'', '!', '|', '/', '#', '(', ')', ',', '<', '>',
            '?', '{', '}', '=', '@', '%', '"'
        };
        boolean result = false;
        if ((s != null) && (s.length() > 0))
        {
            for (int i = 0; i < regExpChars.length; i++)
            {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result)
                {
                    break;
                }
            }
        }
        if ((s != null) && (s.length() > 0))
        {
            for (int i = 0; i < regExpChars.length; i++)
            {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result)
                {
                    break;
                }
            }
        }
        return result;
    }

    public static LinkedHashSet<LabelValueBean> getPortOfDischarge(String deliveryCode, String consigneeCountry)
    {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try
        {

            Query qry = sess.createQuery("select mdp.dischargePortCode,mdp.dischargePortName from MSPDischargePortEXPMaster mdp ,MSPDeliveryTermsEXPMaster mc"
                    + " where mdp.deliveryCode= mc.deliveryTermCode and mdp.deliveryCode=:deliveryCode and mdp.dischargeCountryCode=:dischargeCountryCode");
            qry.setParameter("deliveryCode", deliveryCode);
            qry.setParameter("dischargeCountryCode", consigneeCountry);
            List<String> l = (List<String>) qry.list();

            Iterator it = l.iterator();

            while (it.hasNext())
            {
                LabelValueBean lv = null;

                Object o[] = (Object[]) it.next();
                name = o[1] == null ? "" : o[1].toString();
                id = o[0] == null ? "" : o[0].toString();

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

    public String getBuyerDetails(String dealerCode)
    {
        String buyerDetail = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try
        {
            Query query = hrbsession.createQuery("select dm.address,cm.countryCode,dm.dealerName,cm.countryName "
                    + " from Dealervslocationcode dm,MSPCountriesEXPMaster cm "
                    + " where  dm.dealerCode=:dealerCode and dm.dealerCategory=:dealerCategory and cm.countryCode=dm.countryCode");

            query.setParameter("dealerCode", dealerCode);
            query.setParameter("dealerCategory", "EXPORT");
            Iterator itr = query.list().iterator();
            if (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
                buyerDetail = (o[0] == null || o[0].equals("") ? "-" : o[0].toString()) + "@@"
                        + (o[1] == null|| o[1].equals("") ? "-" : o[1].toString()) + "@@"
                        + (o[2] == null|| o[2].equals("") ? "-" : o[2].toString()) + "@@"
                        + (dealerCode == null|| dealerCode.equals("-") ? "" : dealerCode.toString()) + "@@"
                        + (o[3] == null|| o[3].equals("") ? "-" : o[3].toString());
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
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return buyerDetail;
    }

    public static LinkedHashSet<LabelValueBean> getConsigneeDetails()
    {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        LinkedHashSet<LabelValueBean> consigneeName = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        try
        {

            Query qry = sess.createQuery("select dm.dealerCode,dm.dealerName from Dealervslocationcode dm  where  dm.dealerCategory=:dealerCategory");
            qry.setParameter("dealerCategory", "EXPORT");
            List<String> l = (List<String>) qry.list();

            Iterator it = l.iterator();

            while (it.hasNext())
            {
                LabelValueBean lv = null;

                Object o[] = (Object[]) it.next();
                name = o[1] == null ? "" : o[1].toString();
                id = o[0] == null ? "" : o[0].toString();

                lv = new LabelValueBean(name, id);
                consigneeName.add(lv);
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
        return consigneeName;
    }

    public String getConsigneeDetailByConsigneeName(String consigneeName)
    {
        String consigneeDetail = "";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try
        {
            Query query = hrbsession.createQuery("select dm.address,dm.countryCode,dm.location ,dm.dealerName,cm.countryName"
                    + " from Dealervslocationcode dm ,MSPCountriesEXPMaster cm "
                    + " where  cm.countryCode=dm.countryCode and  dm.dealerCode=:dealerCode");

            query.setParameter("dealerCode", consigneeName);
            Iterator itr = query.list().iterator();
            if (itr.hasNext())
            {
                Object o[] = (Object[]) itr.next();
                consigneeDetail = (o[0] == null || o[0].equals("") ? "-" : o[0].toString()) + "@@"
                        + (o[1] == null || o[1].equals("") ? "-" : o[1].toString()) + "@@"
                        + (o[2] == null || o[2].equals("") ? "-" : o[2].toString()) + "@@"
                        + (o[3] == null || o[3].equals("") ? "-" : o[3].toString()) + "@@"
                        + (o[4] == null || o[4].equals("") ? "-" : o[4].toString());
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
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return consigneeDetail;
    }

    public String documentDeatils(String prevPoNo, Session hrbSession)
    {
        String documentName = "";
        try
        {
            Query query = hrbSession.createQuery("select oh.documentName  from SPOrderHeaderEXP oh where oh.custPoNo=:custPoNo");   //cp.p4='Y'
            query.setParameter("custPoNo", prevPoNo);
            documentName = query.list().get(0).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return documentName;
    }

    public ArrayList<inventoryForm> getViewOrderList(inventoryForm iForm, Vector userFunctionalities)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String Subsql = "";
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try
        {
            if (iForm.getNewPoNo() != null && !iForm.getNewPoNo().equals(""))
            {
                Subsql = " and om.custPoNo like '%" + iForm.getNewPoNo() + "%'  ";
            }
            if (iForm.getOrderType() != null && !iForm.getOrderType().equals(""))
            {
                if (iForm.getOrderType().equalsIgnoreCase("All"))
                {
                    Subsql += " ";
                }
                else
                {
                    Subsql += " and om.ordType ='" + iForm.getOrderType() + "'  ";
                }
            }
            if (iForm.getStatus() != null && !iForm.getStatus().equals(""))
            {
                if (iForm.getStatus().equalsIgnoreCase("All"))
                {
                    Subsql += " ";
                }
                else
                {
                    Subsql += " and om.status ='" + iForm.getStatus() + "'  ";
                }
            }
            if ("1".equals(iForm.getRange()))
            {
                dateSubQur = "  and ( om.custPoDate between isnull(?,om.custPoDate) and isnull(?,om.custPoDate) )  ";
            }
            String groupBy = " group by om.custPoNo,om.ordType,om.custPoDate ,om.status,om.dealerCode,om.consigneeCode,om.consigneeName,om.consigneeCountry,om.dischargePort,om.destinationPlace, om.createdOn,om.hoRemarks  order by om.custPoDate desc";
            String hql = "Select om.custPoNo,om.ordType,om.custPoDate ,om.status,  om.dealerCode ,count(sd.partNo) , "
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=om.dealerCode) as dealerName,(select dm.location from Dealervslocationcode dm where dm.dealerCode=om.dealerCode) as location, "
                    + " om.consigneeCode,om.consigneeName, (select mc.countryName from MSPCountriesEXPMaster mc where mc.countryCode= om.consigneeCountry) as countryName ,(select dp.dischargePortName from MSPDischargePortEXPMaster dp where dp.dischargePortCode=om.dischargePort) as dischargePortName, om.destinationPlace,om.hoRemarks from SPOrderHeaderEXP om , SpOrderDetailsEXP sd where om.custPoNo=sd.custPoNo   ";
            Query query = null;

            query = session.createQuery(hql + Subsql + dateSubQur + " and om.dealerCode IN (:dealerList)" + groupBy);
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL"))
            {
                dealerList.add(iForm.getDealerCode());
            }
            else
            {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());
            }
            query.setParameterList("dealerList", dealerList);

//            if (userFunctionalities.contains("101")) {
//                hql = hql + Subsql + dateSubQur + " and om.dealerCode='" + iForm.getDealerCode() + "' ";
//                query = session.createQuery(hql + groupBy);
//            } else if (userFunctionalities.contains("102")) {
//                ArrayList<String> dealerList = new ArrayList<String>();
//                hql = hql + Subsql + dateSubQur + " and om.dealerCode IN(:dealerList) ";
//                if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {   //&& !iForm.getDealerCode().equalsIgnoreCase("ALL")
//                    dealerList.add(iForm.getDealerCode());
//                } else {
//                    dealerList = (ArrayList<String>) MethodUtility.getCommonListHiber("UmUserDealerMatrix", "dealerCode", "dealerCode", " where e.loginId='" + iForm.getUserid() + "'");
//                }
//                query = session.createQuery(hql + groupBy);
//                query.setParameterList("dealerList", dealerList);
//            } else if (userFunctionalities.contains("103")) {
//                if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL")) {   //&& !iForm.getDealerCode().equalsIgnoreCase("ALL")
//                    hql = hql + Subsql + dateSubQur + " and om.dealerCode='" + iForm.getDealerCode() + "'";
//                } else {
//                    hql = hql + Subsql + dateSubQur;
//                }
//                query = session.createQuery(hql + groupBy);
//            }
            if ("1".equals(iForm.getRange()))
            {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
            }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext())
            {
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
                invForm.setConsigneeCode(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                invForm.setConsigneeName(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                invForm.setConsigneeCountry(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                invForm.setDischargePort(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                invForm.setDestinationPlace(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                invForm.setErpRemarks(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                orderList.add(invForm);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return orderList;
    }

    public ArrayList<inventoryForm> getViewOrderDetail(inventoryForm invForm, String custPoNo)
    {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();

        String hql = "";
        String partHql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            hql = "select om.custPoNo,om.ordType,om.custPoDate,om.engineNo," + //om.comments,
                    " om.chassisNo,om.modelNo,om.jobCardNo ,"
                    + " (select dm.deliveryTermDesc from MSPDeliveryTermsEXPMaster dm where dm.deliveryTermCode=om.deliveryTerms)as deliveryDesc,om.totalValue," + //om.shipmentLotSingle,  om.deliveryAddress,
                    " om.status ,om.comments ,om.consigneeCode,om.consigneeName,(select mc.countryName from MSPCountriesEXPMaster mc where mc.countryCode= om.consigneeCountry) as countryName,om.destinationPlace,om.hoRemarks,om.dealerCode"
                    + " ,om.dealerRefNo,om.comments,(select mpt.paymentTermDesc from MSPPaymentTermsEXPMaster mpt where mpt.paymentTermCode=om.paymentTerms) as paymentTerms,"
                    + " (select it.incoTermDesc from MSPIncoTermsEXPMaster it where it.incoTermCode=om.incoTerms )as incoTerms,(select dp.dischargePortName from MSPDischargePortEXPMaster dp where dp.dischargePortCode=om.dischargePort) as dischargePortName,"
                    + " om.precarriageBy,om.placePreCarrier,om.priceListCode,om.currency from SPOrderHeaderEXP om  "
                    + " where "
                    + " om.custPoNo=:custPoNo";
            Query orderQuery = hrbsession.createQuery(hql);
            orderQuery.setParameter("custPoNo", custPoNo);
            Iterator itr = orderQuery.list().iterator();
            while (itr.hasNext())
            {
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
                invForm.setConsigneeCode(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                invForm.setConsigneeName(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                invForm.setConsigneeCountry(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                invForm.setDestinationPlace(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                invForm.setErpRemarks(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                invForm.setDealerCode(enqobj[16] == null ? "-" : enqobj[16].toString().trim());

                invForm.setDealerRefNo(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
                invForm.setComments(enqobj[18] == null ? "-" : enqobj[18].toString().trim());
                invForm.setPaymentTerms(enqobj[19] == null ? "-" : enqobj[19].toString().trim());
                invForm.setIncoTerms(enqobj[20] == null ? "-" : enqobj[20].toString().trim());
                invForm.setDischargePort(enqobj[21] == null ? "-" : enqobj[21].toString().trim());
                invForm.setPrecarriageBy(enqobj[22] == null ? "-" : enqobj[22].toString().trim());
                invForm.setPlacePreCarrier(enqobj[23] == null ? "-" : enqobj[23].toString().trim());
                invForm.setPriceListCode(enqobj[24] == null ? "-" : enqobj[24].toString().trim());
                invForm.setCurrency(enqobj[25] == null ? "-" : enqobj[25].toString().trim());
            }
            if (invForm.getErpOrderNo() != null && invForm.getErpOrderNo().length() != 0 && invForm.getDealerCode() != null && invForm.getDealerCode().length() != 0)
            {
                invForm.setSpOrderInvoices(getOrderInvoicesList(hrbsession, invForm.getErpOrderNo(), invForm.getDealerCode()));
            }
            partHql = "select sd.partNo ,sd.qty,sd.price,sd.status ,(select cp.p1 from CatPart cp where cp.partNo=sd.partNo ),pendingQty"//,sd.erpPendingQty
                    + " from SpOrderDetailsEXP sd  where sd.custPoNo=:custPoNo ";
            Query partQuery = hrbsession.createQuery(partHql);
            partQuery.setParameter("custPoNo", custPoNo);
            Iterator partItr = partQuery.list().iterator();
            while (partItr.hasNext())
            {
                Object enqobj[] = (Object[]) partItr.next();
                inventoryForm inForm = new inventoryForm();
                inForm.setPartno(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                inForm.setQty(enqobj[1] == null ? "-" : enqobj[1].toString().trim());
                inForm.setFinalamount(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                inForm.setAmountPerPrice("" + (Double.parseDouble(enqobj[1].toString()) * Double.parseDouble(enqobj[2].toString())));
                inForm.setPartStatus(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                inForm.setPart_desc(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                inForm.setErpPendingQty(enqobj[5] == null ? "-" : enqobj[5].toString().trim());                                
                dataList.add(inForm);
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
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return dataList;

    }

    public List<String[]> getOrderInvoicesList(Session hSession, String erpOrderNo, String dealerCode)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String query = "select a.spOrderInvoicesPK.invoiceNo, a.invoiceDate,a.shippedPartNo,a.qtyShipped,a.totalInvoiceAmount,a.lrNo,"
                + "a.shipmentDate,a.transporterName,a.permitNo,(select b.p1 from CatPart b where b.partNo = a.shippedPartNo) as p1,a.spOrderInvoicesPK.orderedPart,a.status,a.invoicedRate,a.dealerAcceptance, a.remarks from SpOrderInvoices a where a.spOrderInvoicesPK.erpOrderNo=:orderNo and a.dealerCode=:dealerCode";

        List<String[]> orderInvoiceList = new ArrayList<String[]>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Query hQuery = hSession.createQuery(query);
            hQuery.setParameter("orderNo", erpOrderNo);
            hQuery.setParameter("dealerCode", dealerCode);
            String[] result = new String[15];
            Iterator iterator = hQuery.list().iterator();
            while (iterator.hasNext())
            {
                Object[] o = (Object[]) iterator.next();
                result = new String[15];
                result[0] = o[0] == null ? "-" : o[0].toString();
                result[1] = o[1] == null ? "-" : (sdf.format(o[1])).toString();
                result[2] = o[2] == null ? "-" : o[2].toString();
                result[3] = o[3] == null ? "-" : o[3].toString();
                result[4] = o[4] == null ? "-" : o[4].toString();
                result[5] = o[5] == null ? "-" : o[5].toString();
                result[6] = o[6] == null ? "-" : (sdf.format(o[6])).toString();
                result[7] = o[7] == null ? "-" : o[7].toString();
                result[8] = o[8] == null ? "-" : o[8].toString();
                result[9] = o[9] == null ? "-" : o[9].toString();
                result[10] = o[10] == null ? "-" : o[10].toString();
                result[11] = o[11] == null ? "-" : o[11].toString();
                if (o[12].toString() != null && o[12].toString().trim().length() > 0)
                {
                    if (o[12].toString().indexOf(".") != -1)
                    {
                        if (Integer.parseInt(o[12].toString().substring(o[12].toString().indexOf(".") + 1)) == 0)
                        {
                            o[12] = o[12].toString().substring(0, o[12].toString().indexOf("."));
                        }
                        else
                        {
                            o[12] = decimalFormat.format(o[12]);
                        }
                    }
                }
                result[12] = o[12] == null ? "-" : o[12].toString();
                result[13] = o[13] == null ? "-" : o[13].toString();
                result[14] = o[14] == null ? "-" : o[14].toString();
                orderInvoiceList.add(result);
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
                if (hSession != null)
                {
                    hSession.close();
                    hSession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return orderInvoiceList;
    }

    //getViewPiDetail
    public ArrayList<inventoryForm> getViewPiDetail(inventoryForm invForm, String custPoNo)
    {            //, String custPoNo
        Session hrbsession = null;
        ArrayList<inventoryForm> piList = new ArrayList<inventoryForm>();
        String hql = "";
        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            hql = "select distinct pid.orderPartNo,(select p.p1 from CatPart p where p.partNo=ord.partNo) as partDesc, "
                    + "ord.custPoNo,ord.qty, pid.subStatus,pid.leadTime, pid.avlPartNo,"
                    + "(select cp.p1 from CatPart cp where cp.partNo=pid.avlPartNo) as avlPartDesc, pid.finalPiQty, pid.price, "
                    + "pid.sp_ORD_PI_EXP.piNo, ord.pendingQty,pid.avlQty,pid.finalPiQty, pid.bookesQty, pid.pendingQty, ord.partNo"
                    + " from SP_ORD_PI_DTL_EXP pid, "
                    + "SpOrderDetailsEXP ord where pid.poDetailId=ord.poDetailId and ord.custPoNo= :custPoNo order by pid.sp_ORD_PI_EXP.piNo";

            Query query = hrbsession.createQuery(hql);
            query.setParameter("custPoNo", custPoNo);
            Iterator itr = query.list().iterator();
            while (itr.hasNext())
            {
                Object[] data = (Object[]) itr.next();
                inventoryForm iform = new inventoryForm();
                iform.setPiNo(data[10] == null ? "" : data[10].toString());
                iform.setPartNoPI(data[0] == null ? "" : data[0].toString());
                iform.setPartDescPI(data[1] == null ? "" : data[1].toString());
                //iform.setPoNoPI(data[2] == null ? "" : data[2].toString());
                iform.setOrderedQtyPI(data[3] == null ? 0 : Integer.parseInt(data[3].toString()));
                iform.setStatusPI(data[4] == null ? "" : data[4].toString());
                iform.setLeadTimePI(data[5] == null ? "" : data[5].toString());
                iform.setAvailablePartNoPI(data[6] == null ? "" : data[6].toString());
                iform.setAvlPartDescPI(data[7] == null ? "" : data[7].toString());
                iform.setPiQtyPI(data[8] == null ? 0 : Integer.parseInt(data[8].toString()));
                iform.setPricePI(data[9] == null ? 0 : Double.parseDouble(data[9].toString()));
                iform.setBookedQtyPI(data[14] == null ? 0 : Integer.parseInt(data[14].toString()));
                iform.setPendingQtyPI(data[15] == null ? 0 : Integer.parseInt(data[15].toString()));
                piList.add(iform);
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
                    hrbsession = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return piList;
    }

    public ArrayList<inventoryForm> getViewOrderInvoiceList(inventoryForm iForm, Vector userFunctionalities)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String Subsql = "";
        String dateSubQur = "";
        ArrayList<String> dealerList = new ArrayList<String>();
        try
        {
            if (iForm.getInvNo() != null && iForm.getInvNo().length() > 0)
            {
                Subsql = " and spi.invoiceNo like '%" + iForm.getInvNo().trim() + "%' ";
            }
            if (iForm.getInvOrderNo() != null && iForm.getInvOrderNo().length() > 0)
            {
                Subsql += " and spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo like '%" + iForm.getInvOrderNo().trim() + "%'  ";
            }
            if (iForm.getPiNo() != null && iForm.getPiNo().length() > 0)
            {
                Subsql += " and spd.spOrdPIInvoiceDTLEXPPK.dmsPINo like '%" + iForm.getPiNo().trim() + "%'  ";
            }
            if ("1".equals(iForm.getRange()))
            {
                dateSubQur = " and ( spi.invoiceDate between isnull(?,spi.invoiceDate) and isnull(?,spi.invoiceDate) ) ";
            }
            String hql = "Select distinct spi.invoiceNo, spi.invoiceDate ,spi.dealerCode , "
                    + " (select dm.dealerName from UmDmsDealerMaster dm where dm.dealerCode=spi.dealerCode and dm.dealerCategory='EXPORT') as dealerName,"
                    + " (select dm.location from UmDmsDealerMaster dm where dm.dealerCode=spi.dealerCode and dm.dealerCategory='EXPORT') as location "
                    + " ,spi.totalInvoiceAmount,spi.awbBolNo,spi.awbBolDate,spi.dispatchMode " 
                    + " from SpOrdPIInvoiceHDREXP spi,SpOrdPIInvoiceDTLEXP spd where spd.spOrdPIInvoiceDTLEXPPK.invoiceId=spi.invoiceId and spd.status='DISPATCHED' ";

            Query query = null;
            query = session.createQuery(hql + dateSubQur + " and :dealerList LIKE ('%'+spi.dealerCode+'%') " + Subsql + "  order by spi.invoiceDate desc ");
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL"))
            {
                dealerList.add(iForm.getDealerCode());
            }
            else
            {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());
            }
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);

            if ("1".equals(iForm.getRange()))
            {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
            }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext())
            {
                Object enqobj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setInvDate(enqobj[1] == null ? "-" : sdf1.format(df.parse(enqobj[1].toString().trim())));
                invForm.setDealerCode(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                invForm.setDealerName(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                invForm.setLocation(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setFinalamount(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setAwbBolNo(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                invForm.setAwbBolDate(enqobj[7] == null ? "-" : sdf1.format(df.parse(enqobj[7].toString().trim())));
                invForm.setDispatchMode(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                orderList.add(invForm);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return orderList;
    }

    public ArrayList<inventoryForm> getBillingDetailsExport(inventoryForm iForm, Vector userFunctionalities)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList<inventoryForm> orderList = new ArrayList<inventoryForm>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        //Double qty = 0.0;
      //  Double amount = 0.0;
      //  Double totalAmount = 0.0;
        String Subsql = "";
        String dateSubQur = "";
        try
        {
            if (iForm.getInvNo() != null && !iForm.getInvNo().equals(""))
            {
                Subsql = " and spi.invoiceNo like '%" + iForm.getInvNo() + "%' ";
            }
            if (iForm.getInvOrderNo() != null && !iForm.getInvOrderNo().equals(""))
            {
                Subsql += " and spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo like '%" + iForm.getInvOrderNo() + "%' ";
            }
            if ("1".equals(iForm.getRange()))
            {
                dateSubQur = " and ( spi.invoiceDate between isnull(?,spi.invoiceDate) and isnull(?,spi.invoiceDate) )  ";
            }
            ArrayList<String> dealerList = new ArrayList<String>();


            String hql = "Select distinct spd.invoiceNo,spi.invoiceDate ,spi.totalInvoiceAmount, spi.dealerCode ,"
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
                    + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location ,"
                    + " spi.dispatchMode,spi.awbBolNo,spi.awbBolDate,"
                    + " spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo,spd.orderedPart,"
                    + " (select cp.partType from CatPart cp where cp.partNo=spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo ) as parttype ,"
                    + " spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo ) as partdesc,spd.qtyShipped,"
                    + " spi.invoicedRate,spi.invCurrency"
                    + " from SpOrdPIInvoiceHDREXP spi ,SpOrdPIInvoiceDTLEXP spd where spd.spOrdPIInvoiceDTLEXPPK.invoiceId=spi.invoiceId ";
            Query query = null;

            hql = hql + Subsql + dateSubQur + " and :dealerList LIKE ('%'+spi.dealerCode+'%') order by spi.invoiceDate desc ";
            if (iForm.getDealerCode() != null && !iForm.getDealerCode().equals("") && !iForm.getDealerCode().equalsIgnoreCase("ALL"))
            {
                dealerList.add(iForm.getDealerCode());
            }
            else
            {
                dealerList = (ArrayList<String>) MethodUtility.getDealerCodeUnderUser(session, iForm.getUserid());
            }
            query = session.createQuery(hql);
            query.setParameter("dealerList", MethodUtility.appendDealerList(dealerList));
            //query.setParameterList("dealerList", dealerList);


            //System.out.println(hql);
            if ("1".equals(iForm.getRange()))
            {
                query.setString(0, iForm.getFromdate().equals("") == true ? null : df.format(sdf.parse(iForm.getFromdate() + " 00:00")));
                query.setString(1, iForm.getTodate().equals("") == true ? null : df.format(sdf.parse(iForm.getTodate() + " 23:59")));
            }
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext())
            {
                Object enqobj[] = (Object[]) itr.next();
                inventoryForm invForm = new inventoryForm();
                invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                invForm.setInvDate(enqobj[1] == null ? "-" : sdf1.format(df.parse(enqobj[1].toString().trim())));
                invForm.setFinalamount(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                invForm.setDealerCode(enqobj[3] == null ? "-" : enqobj[3].toString().trim());
                invForm.setDealerName(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                invForm.setLocation(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                invForm.setDispatchMode(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                invForm.setAwbBolNo(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                invForm.setAwbBolDate(enqobj[8] == null ? "-" : sdf1.format(df.parse(enqobj[8].toString().trim())));
                invForm.setErpOrderNo(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                invForm.setOrderedPartNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                invForm.setShippedPartNo(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                invForm.setPartDescription(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                invForm.setQty(enqobj[13] == null ? "-" : enqobj[14].toString().trim());
                invForm.setInvoicedRate(Float.parseFloat(enqobj[15].toString().trim()));
                invForm.setCurrency(enqobj[16] == null ? "" : enqobj[16].toString());
//                invForm.setErpOrderDate(enqobj[7] == null ? "-" : sdf1.format(df.parse(enqobj[7].toString().trim())));
//                invForm.setTransporterName(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
//                invForm.setLrNo(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
//                invForm.setPermitNo(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
//
//                invForm.setOrderType(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
//
//                invForm.setPartTypeStr(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
//
//                invForm.setPartDescription(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
//                invForm.setQty(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
//                invForm.setUnitValue(enqobj[18] == null ? "-" : enqobj[18].toString().trim());
//                qty = Double.parseDouble(enqobj[17].toString());
//                amount = Double.parseDouble(enqobj[18].toString());
//                invForm.setAmountPerPrice("" + qty * amount);
//                totalAmount = totalAmount + qty * amount;
//                invForm.setTotalAmont("" + totalAmount);
                orderList.add(invForm);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return orderList;
    }

    public ArrayList<inventoryForm> getViewOrderInvoiceDetail(inventoryForm invForm, String invNo)
    {
        Session hrbsession = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        String hql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Double qty = 0.0;
        Double amount = 0.0;
        Double totalAmount = 0.0;
        try
        {
             hrbsession = HibernateUtil.getSessionFactory().openSession();
            hql = "Select distinct spi.invoiceNo,spi.invoiceId,spd.spOrdPIInvoiceDTLEXPPK.erpPartOrderNo,spi.invoiceDate ,spi.dealerCode ,spd.status ,"
                    + " (select dm.dealerName from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as dealerName,"
                    + " (select dm.location from Dealervslocationcode dm where dm.dealerCode=spi.dealerCode) as location,"
                    + " spi.totalInvoiceAmount, spd.orderedPart,spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo,"
                    + " (select cp.p1 from CatPart cp where cp.partNo=spd.spOrdPIInvoiceDTLEXPPK.shippedPartNo ) as partDesc,spd.qtyShipped,spd.invoicedRate,spd.spOrdPIInvoiceDTLEXPPK.erpOrderNo,"
                    + " spd.spOrdPIInvoiceDTLEXPPK.dmsPINo, spd.remarks,spi.dispatchMode,spi.awbBolNo,spi.awbBolDate,spi.inspCharge,spi.localTPT,spi.insurnceCharge,spi.freightCharge,spi.otherCharge,spi.invCurrency"
                    + " from SpOrdPIInvoiceHDREXP spi,SpOrdPIInvoiceDTLEXP spd where spd.spOrdPIInvoiceDTLEXPPK.invoiceId=spi.invoiceId and spi.invoiceNo=:invNo and spi.dealerCode=:dealerCode and spd.status='DISPATCHED'";
            Query orderQuery = hrbsession.createQuery(hql);
            orderQuery.setParameter("invNo", invNo);
            orderQuery.setParameter("dealerCode", invForm.getDealerCode());
            Iterator itr = orderQuery.list().iterator();
            boolean isHeaderPopulated = false;
            while (itr.hasNext())
            {
                Object enqobj[] = (Object[]) itr.next();
                if (!isHeaderPopulated)
                {
                    invForm.setInvNo(enqobj[0] == null ? "-" : enqobj[0].toString().trim());
                    invForm.setPartOrderNo(enqobj[2] == null ? "-" : enqobj[2].toString().trim());
                    invForm.setInvDate(enqobj[3] == null ? "-" : sdf.format(df.parse(enqobj[3].toString().trim())));
                    invForm.setDealerCode(enqobj[4] == null ? "-" : enqobj[4].toString().trim());
                    invForm.setStatus(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                    invForm.setDealerName(enqobj[6] == null ? "-" : enqobj[6].toString().trim());
                    invForm.setLocation(enqobj[7] == null ? "-" : enqobj[7].toString().trim());
                    invForm.setFinalamount(enqobj[8] == null ? "-" : enqobj[8].toString().trim());
                    invForm.setDispatchMode(enqobj[17] == null ? "-" : enqobj[17].toString().trim());
                    invForm.setAwbBolNo(enqobj[18] == null ? "-" : enqobj[18].toString().trim());
                    invForm.setAwbBolDate(enqobj[19] == null ? "-" : sdf.format(df.parse(enqobj[19].toString().trim())));
                    invForm.setInspCharge(enqobj[20] == null ? 0.0f : Float.parseFloat(enqobj[20].toString().trim()));
                    invForm.setLocalTPT(enqobj[21] == null ? 0.0f : Float.parseFloat(enqobj[21].toString().trim()));
                    invForm.setInsurnceCharge(enqobj[22] == null ? 0.0f : Float.parseFloat(enqobj[22].toString().trim()));
                    invForm.setFreightCharge(enqobj[23] == null ? 0.0f : Float.parseFloat(enqobj[23].toString().trim()));
                    invForm.setOtherCharge(enqobj[24] == null ? 0.0f : Float.parseFloat(enqobj[24].toString().trim()));
                    invForm.setCurrency(enqobj[25] == null ? "" : enqobj[25].toString());
                    isHeaderPopulated = true;
                }
                inventoryForm inForm = new inventoryForm();
                inForm.setStatus(enqobj[5] == null ? "-" : enqobj[5].toString().trim());
                inForm.setOrderedPartNo(enqobj[9] == null ? "-" : enqobj[9].toString().trim());
                inForm.setPartno(enqobj[10] == null ? "-" : enqobj[10].toString().trim());
                inForm.setPart_desc(enqobj[11] == null ? "-" : enqobj[11].toString().trim());
                inForm.setQty(enqobj[12] == null ? "-" : enqobj[12].toString().trim());
                inForm.setUnitValue(enqobj[13] == null ? "-" : enqobj[13].toString().trim());
                inForm.setErpOrderNo(enqobj[14] == null ? "-" : enqobj[14].toString().trim());
                inForm.setPiNo(enqobj[15] == null ? "-" : enqobj[15].toString().trim());
                inForm.setRemarks(enqobj[16] == null ? "-" : enqobj[16].toString().trim());
                qty = Double.parseDouble(enqobj[12].toString());
                amount = Double.parseDouble(enqobj[13].toString());
                inForm.setAmountPerPrice("" + qty * amount);
                totalAmount = totalAmount + qty * amount;
                invForm.setTotalAmont("" + totalAmount);
                dataList.add(inForm);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return dataList;

    }

    public List<inventoryForm> getPiOrderForSAP()
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<inventoryForm> PiOrderForSAPList = new ArrayList<inventoryForm>();
        inventoryForm invForm = null;

        try
        {
            Query qtr = session.createSQLQuery("exec SP_PI_DOWNLOAD_EXP");
            List result = qtr.list();
            Iterator iterator = result.iterator();
            while (iterator.hasNext())
            {
                invForm = new inventoryForm();
                Object[] object = (Object[]) iterator.next();

                invForm.setSrNo(object[0] == null ? 0 : Integer.parseInt(object[0].toString()));
                invForm.setQuotType(object[1] == null ? "-" : object[1].toString());
                invForm.setSaleOrg(object[2] == null ? "-" : object[2].toString());
                invForm.setDisChannel(object[3] == null ? "-" : object[3].toString());
                invForm.setDivison(object[4] == null ? "-" : object[4].toString());
                invForm.setSoldToParty(object[5] == null ? "-" : object[5].toString());
                invForm.setShipToParty(object[6] == null ? "-" : object[6].toString());
                invForm.setNewPoNo(object[7] == null ? "-" : object[7].toString());
                invForm.setPoDate(object[8] == null ? "-" : object[8].toString());
                invForm.setValidTo(object[9] == null ? "-" : object[9].toString());
                invForm.setIncoTerms1(object[10] == null ? "-" : object[10].toString());
                invForm.setIncoTerms2(object[11] == null ? "-" : object[11].toString());
                invForm.setPaymentTerms(object[12] == null ? "-" : object[12].toString());
                invForm.setDispatchMode(object[14] == null ? "-" : object[14].toString());
                invForm.setDischargePort(object[15] == null ? "-" : object[15].toString());
                invForm.setPortOfLoading(object[16] == null ? "-" : object[16].toString());
                invForm.setFinalDestCountry(object[17] == null ? "-" : object[17].toString());
                invForm.setPartCode(object[18] == null ? "-" : object[18].toString());
                invForm.setOrderQty(object[19] == null ? 0 : Integer.parseInt(object[19].toString()));
                invForm.setPlant(object[20] == null ? "-" : object[20].toString());
                invForm.setStorageLoc(object[21] == null ? "-" : object[21].toString());
                invForm.setInspCharge(object[22] == null ? 0.0f : Float.parseFloat(object[22].toString()));
                invForm.setLocalTPT(object[23] == null ? 0.0f : Float.parseFloat(object[23].toString()));
                invForm.setInsurnceCharge(object[24] == null ? 0.0f : Float.parseFloat(object[24].toString()));
                invForm.setFreightCharge(object[25] == null ? 0.0f : Float.parseFloat(object[25].toString()));
                invForm.setOtherCharge(object[26] == null ? 0.0f : Float.parseFloat(object[26].toString()));
                invForm.setoRefNo(object[27] == null ? "" : object[27].toString());
                invForm.settRefNo(object[28] == null ? "" : object[28].toString());
                invForm.setSapORefNo(object[29] == null ? "" : object[29].toString());
                invForm.setSapTRefNo(object[30] == null ? "" : object[30].toString());
                invForm.setSapPartCode(object[31] == null ? "" : object[31].toString());
                invForm.setSapQty(object[32] == null ? "" : object[32].toString());

                PiOrderForSAPList.add(invForm);
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
        return PiOrderForSAPList;
    }

    public File uploadXlsxImage(String fileName, String filePath, String ecatPATH) throws IOException, InterruptedException
    {
        File xlsfile = null, checkExcelData = null;
        Process p = Runtime.getRuntime().exec("cscript \"" + ecatPATH + "VBScript/oxl.vbs\" \"" + filePath + "\\" + fileName + "\"");
        if (p != null)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
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

    public ArrayList isSAPExcelValidated(File xlsfile, Connection conn) throws Exception
    {

        int row = 0;
        int column = 0;
        int totalrows = 0;
        boolean r = false;
        String result = "failure1", oRefNo = "", sapORefNo = "", sapPartCode = "";
        String tRefNo = "", sapTRefNo = "", sapQty = "";
        ArrayList resultList = new ArrayList();
        ArrayList dataList = new ArrayList();
        Workbook workbook1 = null;
        Sheet sheet = null;
        try
        {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (!(sheet.getCell(column, row).getContents().trim().equals("Order S.No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Order S.No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Quotation Type")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Quotation Type' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Sale Organization")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Sale Organization' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Distribution Channel")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Distribution Channel' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Divison")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Divison' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Sold To Party")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Sold To Party' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Ship To Party")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Ship To Party' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PO NO. / PI NO.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PO NO. / PI NO.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PO Date")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PO Date' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Valid To")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Valid To' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inco Terms 1")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inco Terms 1' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inco Terms 2")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inco Terms 2' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Payment Terms")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Payment Terms' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Mode of Dispatch")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Mode of Dispatch' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Port of Discharge")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Port of Discharge' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Port of Loading")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Port of Loading' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Final Destination")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Final Destination' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Part Code")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Part Code' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Order Qty")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Order Qty' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Plant")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Plant' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Storage Location")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Storage Location' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inspection Charges")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inspection Charges' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Local Transportation")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Local Transportation' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Insurance")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Insurance' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Sea / Air Freight")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Sea / Air Freight' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Others (if any)")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Others (if any)' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("O Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'O Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("T Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'T Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP O Ref. No.")))
            {

                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP O Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP T Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP T Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP Part Code")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP Part Code' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP Qty")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP Qty' Missing. Template error.");
                return resultList;
            }

            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, No Records Available.");
                return resultList;
            }
            totalrows = (sheet.getRows());
            while (row < totalrows)
            {
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
                {
                    for (column = 26; column < 33; column++)
                    {

                        if (column == 26)
                        {
                            oRefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (oRefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". O Ref. No. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(oRefNo);
                            }

                        }
                        else if (column == 27)
                        {
                            tRefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (tRefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". T Ref. No. can not be blank.");
                                return resultList;
                            }
                            else if (!containsOnlyNumbers(tRefNo))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'tRefNo'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(tRefNo);
                            }
                        }
                        else if (column == 28)
                        {
                            sapORefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapORefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Sap O Ref No. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapORefNo);
                            }
                        }
                        else if (column == 29)
                        {
                            sapTRefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapTRefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP T Ref. No. can not be blank.");
                                return resultList;
                            }
                            else if (!containsOnlyNumbers(sapTRefNo))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'sapTRefNo'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapTRefNo);
                            }
                        }
                        else if (column == 30)
                        {
                            sapPartCode = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapPartCode.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP Part Code can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapPartCode);
                            }
                        }
                        else if (column == 31)
                        {
                            sapQty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapQty.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP Qty can not be blank.");
                                return resultList;
                            }
                            else if (!containsOnlyNumbers(sapQty))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'sapQty'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapQty);
                            }
                        }
                    }
                }
                else
                {
                    resultList.add("success");
                    resultList.add(dataList);
                    System.out.println("dataList:" + dataList);
                    //////////////////// to do here
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.");
                return resultList;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
            resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.");
            return resultList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultList.add("readingerr");
            return resultList;
        }
        return resultList;
    }

    public ArrayList updateSAPFromExcel(ArrayList dataList, Connection conn, String dealerCode)
    {

        int row = 1;// column = 0;
        Session hrbsession = null;
        Transaction tx = null;
        ArrayList result = new ArrayList();
        Date today = new Date();
        //  int noOfParts = 0;
        SP_ORD_PI_EXP sp = null;
        SP_ORD_PI_DTL_EXP spd = null;
        String oRefNo = "", sapORefNo = "", sapPartCode = "";
        int tRefNo = 0;
        int sapTRefNo = 0, sapQty = 0;

        try
        {

            hrbsession = HibernateUtil.getSessionFactory().openSession();
            tx = hrbsession.beginTransaction();
            String tempOrdRefNo = "";

            for (int i = 0; i < dataList.size(); i = i + 6)
            {
                oRefNo = ("" + dataList.get(i)).toUpperCase();
                tRefNo = Integer.parseInt("" + dataList.get(i + 1));
                sapORefNo = ("" + dataList.get(i + 2)).toUpperCase();
                sapTRefNo = Integer.parseInt("" + dataList.get(i + 3));
                sapPartCode = ("" + dataList.get(i + 4)).toUpperCase();
                if (sapPartCode.endsWith("FG"))
                {
                    sapPartCode = sapPartCode.substring(0, sapPartCode.length() - 2);
                }
                sapQty = Integer.parseInt("" + dataList.get(i + 5));


                if (!tempOrdRefNo.equalsIgnoreCase(oRefNo))
                {

                    sp = (SP_ORD_PI_EXP) hrbsession.get(SP_ORD_PI_EXP.class, oRefNo);
                    if (sp != null)
                    {
                        sp.setStatus("ORDER REGISTERED");
                        sp.setErpOrderNo(sapORefNo);
                        sp.setErpOrderDate(today);
                        sp.setErpUploadStatus("YES");
                        sp.setErpProcessStatus("REGISTERED");
                        sp.setLastupdatedOn(today);
                        sp.setIsServerSync('N');
                        hrbsession.saveOrUpdate(sp);
                    }
                    tempOrdRefNo = oRefNo;
                }

                spd = (SP_ORD_PI_DTL_EXP) hrbsession.get(SP_ORD_PI_DTL_EXP.class, BigInteger.valueOf(tRefNo));
                if (spd != null && oRefNo.equalsIgnoreCase(spd.getSp_ORD_PI_EXP().getPiNo()))
                {
                    spd.setBookesQty(sapQty);
                    spd.setPendingQty(sapQty);
                    spd.setPosNo(sapTRefNo);
                    spd.setLastupdatedOn(today);
                    spd.setIsServerSync('N');
                    spd.setAvlPartNo(sapPartCode);
                    hrbsession.saveOrUpdate(spd);

                }
                //   noOfParts++;
            }


            row++;


            tx.commit();
            result.add(0, "PI has been Updated Successfully.");
            result.add(1, "Add More.");



        }
        catch (Exception e)
        {
            result.add(0, "PI has not been updated. Error Occured. Please contact administrator.");
            result.add(1, "Try Again.");
            e.printStackTrace();
        }finally {
        	 if (hrbsession != null && hrbsession.isOpen()) {
                 hrbsession.close();
             }
		}
        return result;
    }

    public ArrayList isSAPCommExcelValidated(File xlsfile, Connection conn) throws Exception
    {

        int row = 0;
        int column = 0;
        int totalrows = 0;
        boolean r = false;
        // SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        //  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String invDate = "", poPiNo = "", soldToParty = "", sapORefNo = "", sapTRefNo = "", modeofDispatch = "";
        String awbBolNo = "", awbBolDate = "", ciNo = "";
        String ciDate = "", partCodeInv = "", currancy = "", qtyInv = "";
        String price = "", inspCharges = "", localTrans = "", insurance = "", freight = "", others = "", invAmount = "";

        ArrayList resultList = new ArrayList();
        ArrayList dataList = new ArrayList();
        Workbook workbook1 = null;
        Sheet sheet = null;
        try
        {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inv. Date")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inv. Date' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PO NO. / PI NO.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'PO NO. / PI NO.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Sold To Party")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Sold To Party' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP O Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP O Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP T Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP T Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Mode of Dispatch")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Mode of Dispatch' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("AWB/BOL No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'AWB/BOL No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("AWB/BOL Date")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'AWB/BOL Date' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("CI No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'CI No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("CI Date")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'CI Date' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Part Code Inv.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Part Code Inv.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Qty. Inv.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Qty. Inv.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Price")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Price' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inspection Charges")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inspection Charges' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Local Transportation")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Local Transportation' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Insurance")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Insurance' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Sea / Air Freight")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Sea / Air Freight' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Others (if any)")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Others (if any)' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Inv. Amount")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Inv. Amount' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Currancy")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Currancy' Missing. Template error.");
                return resultList;
            }
            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, No Records Available.");
                return resultList;
            }
            totalrows = (sheet.getRows());

            while (row < totalrows)
            {
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
                {
                    for (column = 0; column < 20; column++)
                    {
                        if (column == 0)
                        {
                            invDate = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (invDate.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Inv. Date can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(invDate);
                            }

                        }
                        else if (column == 1)
                        {
                            poPiNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (poPiNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". PO NO. / PI NO. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(poPiNo);
                            }
                        }
                        else if (column == 2)
                        {
                            soldToParty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (soldToParty.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Sold To Party can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(soldToParty);
                            }
                        }
                        else if (column == 3)
                        {
                            sapORefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapORefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP O Ref. No. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapORefNo);
                            }
                        }
                        else if (column == 4)
                        {
                            sapTRefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapTRefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP T Ref. No. can not be blank.");
                                return resultList;
                            }
                            else if (!containsOnlyNumbers(sapTRefNo))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'sapQty'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapTRefNo);
                            }
                        }
                        else if (column == 5)
                        {
                            modeofDispatch = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            dataList.add(modeofDispatch);

                        }
                        else if (column == 6)
                        {
                            awbBolNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            dataList.add(awbBolNo);

                        }
                        else if (column == 7)
                        {
                            awbBolDate = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            dataList.add(awbBolDate);

                        }
                        else if (column == 8)
                        {
                            ciNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (ciNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". CI No. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(ciNo);
                            }
                        }
                        else if (column == 9)
                        {
                            ciDate = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (ciDate.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". CI Date can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(ciDate);
                            }
                        }
                        else if (column == 10)
                        {
                            partCodeInv = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partCodeInv.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Part Code Inv. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(partCodeInv);
                            }
                        }
                        else if (column == 11)
                        {
                            qtyInv = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (qtyInv.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Qty. Inv. can not be blank.");
                                return resultList;
                            }
                            else if (!qtyInv.matches("[-+]?[0-9]*\\.?[0-9]+"))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in Qty. Inv.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(qtyInv);
                            }
                        }
                        else if (column == 12)
                        {
                            price = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (price.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Price can not be blank.");
                                return resultList;
                            }
                            else if (!price.matches("[-+]?[0-9]*\\.?[0-9]+"))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'Price'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(price);
                            }
                        }
                        else if (column == 13)
                        {
                            inspCharges = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (inspCharges.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Inspection Charges can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(inspCharges);
                            }
                        }
                        else if (column == 14)
                        {
                            localTrans = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (localTrans.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Local Transportation can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(localTrans);
                            }
                        }
                        else if (column == 15)
                        {
                            insurance = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (insurance.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Insurance can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(insurance);
                            }
                        }
                        else if (column == 16)
                        {
                            freight = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (freight.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Sea / Air Freight can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(freight);
                            }
                        }
                        else if (column == 17)
                        {
                            others = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (others.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Others (if any) can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(others);
                            }
                        }
                        else if (column == 18)
                        {
                            invAmount = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (invAmount.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Inv. Amount can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(invAmount);
                            }
                        }
                        else if (column == 19)
                        {
                            currancy = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (currancy.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Currancy can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(currancy);
                            }
                        }
                    }
                }
                else
                {
                    resultList.add("success");
                    resultList.add(dataList);
                    //////////////////// to do here
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.");
                return resultList;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
            resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.");
            return resultList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultList.add("readingerr");
            return resultList;
        }
        return resultList;
    }

    public ArrayList updateSAPCommFromExcel(ArrayList dataList, Connection conn, String dealerCode)
    {

        int row = 1;// column = 0;
        Session hrbsession = null;
        Transaction tx = null;
        ArrayList result = new ArrayList();
        //  Date today = new Date();
        //int noOfParts = 0;
        //   SpPiInvoiceEXP spInv = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        //   SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy");
        //  String invDate = "";
        String poPiNo = "", soldToParty = "", sapORefNo = "", modeofDispatch = "", awbBolNo = "";
        String awbBolDate = "", ciNo = "", ciDate = "", partCodeInv = "", currancy = "";
        int qtyInv = 0;
        int sapTRefNo = 0;
        float price = 0.0f, inspCharges = 0.0f, localTrans = 0.0f, insurance = 0.0f, freight = 0.0f, others = 0.0f;
        BigDecimal invAmount;
        SpOrdPIInvoiceHDREXP invHDR = null;
        SpOrdPIInvoiceDTLEXP invDTL = null;
        SP_ORD_PI_DTL_EXP piDTL = null;
        Set<String> invNoSet = new HashSet<String>();
        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            tx = hrbsession.beginTransaction();
            String tempOrdRefNo = "";

            for (int i = 0; i < dataList.size(); i = i + 20)
            {
                //   invDate = ("" + sdf1.format(df1.parse(dataList.get(i).toString().trim())));
                poPiNo = ("" + dataList.get(i + 1)).toUpperCase();
                sapORefNo = ("" + dataList.get(i + 3));

                sapTRefNo = Integer.parseInt(("" + dataList.get(i + 4)));

                partCodeInv = ("" + dataList.get(i + 10)).toUpperCase();
                if (partCodeInv.endsWith("FG"))
                {
                    partCodeInv = partCodeInv.substring(0, partCodeInv.length() - 2);
                }

                qtyInv = Math.round(Float.parseFloat("" + dataList.get(i + 11)));
                price = Float.parseFloat("" + dataList.get(i + 12));


                soldToParty = ("" + dataList.get(i + 2)).toUpperCase();
                ciNo = ("" + dataList.get(i + 8));
                ciDate = ("" + sdf1.format(df1.parse(dataList.get(i + 9).toString().trim())));


                if (!tempOrdRefNo.equalsIgnoreCase(ciNo))
                {
                    modeofDispatch = ("" + dataList.get(i + 5));
                    awbBolNo = ("" + dataList.get(i + 6));
                    if (!awbBolDate.equals(""))
                    {
                        try
                        {
                            awbBolDate = ("" + sdf1.format(df1.parse(dataList.get(i + 7).toString().trim())));
                        }
                        catch (Exception ee)
                        {
                            awbBolDate = "";
                        }
                    }

                    Criteria cr = hrbsession.createCriteria(SpOrdPIInvoiceHDREXP.class);
                    cr.add(Restrictions.eq("invoiceNo", ciNo));
                    cr.add(Restrictions.eq("invoiceDate", sdf1.parse(ciDate)));
                    cr.add(Restrictions.eq("dealerCode", soldToParty));

                    if (cr.uniqueResult() != null)
                    {
                        invHDR = (SpOrdPIInvoiceHDREXP) cr.uniqueResult();
                        invHDR.setAwbBolNo(awbBolNo);
                        if (!awbBolDate.equals(""))
                        {
                            invHDR.setAwbBolDate(sdf1.parse(awbBolDate));
                        }
                        invHDR.setIsServerSync('N');

                        hrbsession.saveOrUpdate(invHDR);

                    }
                    else
                    {
                        inspCharges = Float.parseFloat("" + dataList.get(i + 13));
                        localTrans = Float.parseFloat("" + dataList.get(i + 14));
                        insurance = Float.parseFloat("" + dataList.get(i + 15));
                        freight = Float.parseFloat("" + dataList.get(i + 16));
                        others = Float.parseFloat("" + dataList.get(i + 17));
                        invAmount = new BigDecimal(dataList.get(i + 18).toString());
                        currancy = ("" + dataList.get(i + 19));

                        invHDR = new SpOrdPIInvoiceHDREXP();
                        invHDR.setInvoiceNo(ciNo);
                        invHDR.setInvoiceDate(sdf1.parse(ciDate));
                        invHDR.setTotalInvoiceAmount(invAmount);
                        invHDR.setDealerCode(soldToParty);
                        invHDR.setDispatchMode(modeofDispatch);
                        invHDR.setAwbBolNo(awbBolNo);
                        if (!awbBolDate.equals(""))
                        {
                            invHDR.setAwbBolDate(sdf1.parse(awbBolDate));
                        }
                        invHDR.setInvCurrency(currancy);
                        invHDR.setInspCharge(inspCharges);
                        invHDR.setLocalTPT(localTrans);
                        invHDR.setInsurnceCharge(insurance);
                        invHDR.setFreightCharge(freight);
                        invHDR.setOtherCharge(others);
                        invHDR.setIsServerSync('N');

                        hrbsession.save(invHDR);
                        invNoSet.add(ciNo);
                    }
                    tempOrdRefNo = ciNo;
                }

                SpOrdPIInvoiceDTLEXPPK ordInvPK = new SpOrdPIInvoiceDTLEXPPK();
                ordInvPK.setInvoiceId(invHDR.getInvoiceId());
                ordInvPK.setErpOrderNo(sapORefNo);
                ordInvPK.setErpPartOrderNo(sapTRefNo);
                ordInvPK.setDmsPINo(poPiNo);
                ordInvPK.setShippedPartNo(partCodeInv);

                invDTL = (SpOrdPIInvoiceDTLEXP) hrbsession.get(SpOrdPIInvoiceDTLEXP.class, ordInvPK);
                if (invDTL != null)
                {
                    invDTL.setQtyShipped(qtyInv);
                    invDTL.setInvoicedRate(price);
                    invDTL.setIsServerSync('N');
                    invDTL.setStatus("DISPATCHED");

                    /*   /// logic to get ordered part
                    Criteria cr = hrbsession.createCriteria(SP_ORD_PI_DTL_EXP.class);
                    cr.createCriteria("sp_ORD_PI_EXP").add(Restrictions.eq("piNo", poPiNo));
                    cr.add(Restrictions.eq("posNo", sapTRefNo));

                    if (cr.uniqueResult() != null)
                    {
                    piDTL = (SP_ORD_PI_DTL_EXP) cr.uniqueResult();
                    invDTL.setOrderedPart(piDTL.getAvlPartNo());
                    }
                    else
                    {
                    invDTL.setOrderedPart("-");
                    }*/

                    hrbsession.saveOrUpdate(invDTL);

                }
                else
                {

                    invDTL = new SpOrdPIInvoiceDTLEXP();

                    invDTL.setSpOrdPIInvoiceDTLEXPPK(ordInvPK);
                    invDTL.setQtyShipped(qtyInv);
                    invDTL.setInvoicedRate(price);
                    invDTL.setIsServerSync('N');
                    invDTL.setStatus("DISPATCHED");

                    /// logic to get ordered part
                    Criteria cr = hrbsession.createCriteria(SP_ORD_PI_DTL_EXP.class);
                    cr.createCriteria("sp_ORD_PI_EXP").add(Restrictions.eq("piNo", poPiNo));
                    cr.add(Restrictions.eq("posNo", sapTRefNo));

                    if (cr.uniqueResult() != null)
                    {
                        piDTL = (SP_ORD_PI_DTL_EXP) cr.uniqueResult();
                        invDTL.setOrderedPart(piDTL.getAvlPartNo());
                    }
                    else
                    {
                        invDTL.setOrderedPart("-");
                    }

                    hrbsession.save(invDTL);

                }                           
            }

            row++;

            tx.commit();

            for(String invNo : invNoSet){
                Query qtr = hrbsession.createSQLQuery("exec SP_INSERT_EMAIL 4, :refNo");
                qtr.setParameter("refNo", invNo);
                qtr.executeUpdate();
            }

            hrbsession.beginTransaction();
            hrbsession.createSQLQuery("exec SP_updateBackOrder_EXP").executeUpdate();
            hrbsession.getTransaction().commit();

            result.add(0, "Invoice has been Updated Successfully.");
            result.add(1, "Add More.");

        }
        catch (Exception e)
        {
            result.add(0, "Invoice has not been updated. Error Occured. Please contact administrator.");
            result.add(1, "Try Again.");
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return result;
    }

    public ArrayList isCancelledOrderExcelValidated(File xlsfile, Connection conn) throws Exception
    {

        int row = 0;
        int column = 0;
        int totalrows = 0;
        String sapORefNo = "", sapTRefNo = "", poPiNo = "", cancelledPart = "", cancelledQty = "", remarks = "";
        ArrayList resultList = new ArrayList();
        ArrayList dataList = new ArrayList();
        Workbook workbook1 = null;
        Sheet sheet = null;
        try
        {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);

            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP O Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP O Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SAP T Ref. No.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'SAP T Ref. No.' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PO NO. / PI NO.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Quotation Type' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Cancelled Part")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Cancelled Part' Missing. Template error.");
                return resultList;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Cancelled Qty.")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Cancelled Qty.' Missing. Template error.");
                return resultList;

            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Remarks")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'Remarks' Missing. Template error.");
                return resultList;
            }
            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, No Records Available.");
                return resultList;
            }
            totalrows = (sheet.getRows());

            while (row < totalrows)
            {
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
                {
                    for (column = 0; column < 6; column++)
                    {
                        if (column == 0)
                        {
                            sapORefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapORefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP O Ref. No. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapORefNo);
                            }
                        }
                        else if (column == 1)
                        {
                            sapTRefNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (sapTRefNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". SAP T Ref. No. can not be blank.");
                                return resultList;
                            }
                            else if (!containsOnlyNumbers(sapTRefNo))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'sapQty'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(sapTRefNo);
                            }
                        }
                        else if (column == 2)
                        {
                            poPiNo = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (poPiNo.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". PO NO. / PI NO. can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(poPiNo);
                            }
                        }
                        else if (column == 3)
                        {
                            cancelledPart = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (cancelledPart.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Cancelled Part can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(cancelledPart);
                            }
                        }
                        else if (column == 4)
                        {
                            cancelledQty = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (cancelledQty.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Cancelled Qty. can not be blank.");
                                return resultList;
                            }
                            else if (!cancelledQty.matches("[-+]?[0-9]*\\.?[0-9]+"))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Only Numbers are allowed in 'sapQty'.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(cancelledQty);
                            }
                        }
                        else if (column == 5)
                        {
                            remarks = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (remarks.equals(""))
                            {
                                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + ". Remarks can not be blank.");
                                return resultList;
                            }
                            else
                            {
                                dataList.add(remarks);
                            }
                        }
                    }
                }
                else
                {
                    resultList.add("success");
                    resultList.add(dataList);
                    //////////////////// to do here
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("END")))
            {
                resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " 'END' Missing.");
                return resultList;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
            resultList.add("Error, Row " + (row + 1) + ", Column " + (column + 1) + " TEMPLATE MISMATCH or 'END' Missing.");
            return resultList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultList.add("readingerr");
            return resultList;
        }
        return resultList;
    }

    public ArrayList updateCancelledOrderFromExcel(ArrayList dataList, Connection conn, String dealerCode)
    {
        Session hrbsession = null;
        Transaction tx = null;
        ArrayList result = new ArrayList();

        SpOrdCancelledExp spCan = null;
        String sapORefNo = "",  poPiNo = "", cancelledPart = "", remarks = "";
        int sapTRefNo = 0;
        int cancelledQty = 0;

        try
        {
            hrbsession = HibernateUtil.getSessionFactory().openSession();
            tx = hrbsession.beginTransaction();

            for (int i = 0; i < dataList.size(); i = i + 6)
            {
                sapORefNo = ("" + dataList.get(i));
                sapTRefNo = Integer.parseInt(("" + dataList.get(i + 1)));
                poPiNo = ("" + dataList.get(i + 2)).toUpperCase();
                cancelledPart = ("" + dataList.get(i + 3)).toUpperCase();
                if (cancelledPart.endsWith("FG"))
                {
                    cancelledPart = cancelledPart.substring(0, cancelledPart.length() - 2);
                }
                cancelledQty = Math.round(Float.parseFloat("" + dataList.get(i + 4)));
                remarks = ("" + dataList.get(i + 5));

                Criteria cr = hrbsession.createCriteria(SpOrdCancelledExp.class);
                cr.add(Restrictions.eq("erpOrderNo", sapORefNo));
                cr.add(Restrictions.eq("erpPartOrderNo", sapTRefNo));
                cr.add(Restrictions.eq("dmsPiNo", poPiNo));
                cr.add(Restrictions.eq("cancelledPart", cancelledPart));

                if (cr.uniqueResult() != null)
                {
                    spCan = (SpOrdCancelledExp) cr.uniqueResult();
                    spCan.setQtyCancelled(cancelledQty);
                    if ("null".equalsIgnoreCase(remarks))
                    {
                        remarks = "";
                    }
                    spCan.setRemarks(remarks);
                    hrbsession.update(spCan);

                }
                else
                {
                    spCan = new SpOrdCancelledExp();
                    spCan.setErpOrderNo(sapORefNo);
                    spCan.setErpPartOrderNo(sapTRefNo);
                    spCan.setDmsPiNo(poPiNo);
                    spCan.setCancelledPart(cancelledPart);
                    spCan.setQtyCancelled(cancelledQty);
                    spCan.setRemarks(remarks);
                    spCan.setStatus("CANCELLED");
                    spCan.setIsServerSync('N');
                    hrbsession.save(spCan);
                }
            }

            tx.commit();

            hrbsession.beginTransaction();
            hrbsession.createSQLQuery("exec SP_updateBackOrder_EXP").executeUpdate();
            hrbsession.getTransaction().commit();

            result.add(0, "Cancelled Order has been Added/Updated Successfully.");
            result.add(1, "Add More.");



        }
        catch (Exception e)
        {
            result.add(0, "Cancelled Order has not been Added/Updated. Error Occured. Please contact administrator.");
            result.add(1, "Try Again.");
            e.printStackTrace();
        }finally {
			if(hrbsession != null) {
				hrbsession.close();
			}
		}
        return result;
    }
    public void getPendigInvoiceList(inventoryForm inForm) {
        String sql = "";
        inventoryForm invForm = null;
        Session session = null;
        ArrayList<inventoryForm> dataList = new ArrayList<inventoryForm>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            sql = "select distinct spi.invoiceNo from SpOrdPIInvoiceHDREXP spi "
                    + " where not exists (select gr.invoiceNo from SpOrderInvGrn gr where gr.dealerCode=spi.dealerCode and gr.invoiceNo = spi.invoiceNo)"
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
        }finally {
			if(session != null) {
				session.close();
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
            hql = "select spi.invoiceNo,spi.invoiceDate from SpOrdPIInvoiceHDREXP spi "
                    + "where spi.dealerCode=:dealerCode and spi.invoiceNo=:invNo";
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
            parthql = "select distinct sid.spOrdPIInvoiceDTLEXPPK.shippedPartNo,(select cp.p1 from CatPart cp where cp.partNo=sid.spOrdPIInvoiceDTLEXPPK.shippedPartNo )  ,"
                    + " sum(sid.qtyShipped) ,sid.invoicedRate, "
                    + " (select cp.np1 from CatPart cp where cp.partNo=sid.spOrdPIInvoiceDTLEXPPK.shippedPartNo )  ,spi.invCurrency "
                    + " from SpOrdPIInvoiceHDREXP spi,SpOrdPIInvoiceDTLEXP sid where sid.spOrdPIInvoiceDTLEXPPK.invoiceId=spi.invoiceId and spi.invoiceNo=:invNo and spi.dealerCode=:dealerCode group by sid.spOrdPIInvoiceDTLEXPPK.shippedPartNo,sid.invoicedRate,spi.invCurrency";
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
                invForm.setCurrency(enqobj[5] == null ? "" : enqobj[5].toString().trim());
                dataList.add(invForm);
            }
            inForm.setGrnPartList(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			if(session != null) {
				session.close();
			}
		}
    }

    public inventoryForm saveGRNEXP(inventoryForm inForm) throws SQLException {
        Session session = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Boolean flag = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            if (inForm.getReceivedQty() != null) {
                for (int i = 0; i < inForm.getReceivedQty().length; i++) {
                    if (inForm.getReceivedQty().length > 1 && i < inForm.getReceivedQty().length - 1) {
                        for (int j = 0; j < inForm.getReceivedQty().length; j++) {
                            if (inForm.getPartNo()[i].equals(inForm.getPartNo()[j])) {
                                if (!inForm.getUnitprice()[i].equals(inForm.getUnitprice()[j])) {
                                    inForm.setResult("PartPriceMismatch");
                                    flag = true;
                                }
                            }
                        }
                    }
                }
            }
            if (!flag) {
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
                    for (int i = 0; i < inForm.getReceivedQty().length; i++) {
                        grnDetPk.setGrNo(grnNo);
                        grnDetPk.setPartno(inForm.getPartNo()[i]);
                        invGrnDet.setSpOrderInvGrnDetailsPK(grnDetPk);
                        invGrnDet.setPartdesc(inForm.getPartDesc()[i]);
                        invGrnDet.setUnitvalue(Double.parseDouble(inForm.getUnitprice()[i]));
                        invGrnDet.setInvoiceQty(Double.parseDouble(inForm.getQuantity()[i]));
                        invGrnDet.setReceivedQty(Double.parseDouble(inForm.getReceivedQty()[i]));
                        session.save(invGrnDet);
                        MethodUtility.inventoryLedgerEntry(inForm.getUserid(), inForm.getDealerCode(), inForm.getPartNo()[i], (Double.parseDouble(inForm.getReceivedQty()[i]) * Double.parseDouble(inForm.getNp1Array()[i])), "Part GRN", session);
                        if (i % 2 == 0) {
                            session.flush();
                            session.clear();
                        }
                    }
                }
                session.getTransaction().commit();
                inForm.setResult("success");
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return inForm;
    }

}
