package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.service;

import com.i4o.dms.kubota.accpac.domain.AccpacChannelFinanceInvoice;
import com.i4o.dms.kubota.accpac.repository.InvoiceDetailsRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIndent;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIntentDetail;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.DealerBankDetails;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.ChannelFinanceInvoiceDto;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.ResponseInvoiceDto;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository.ChannelFinanceIndentRepo;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository.DealerBankDetailsRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ChannelFinanceIndentImpl implements ChannelFinanceIndentService, Comparator<ResponseInvoiceDto> {

    @Autowired
    private InvoiceDetailsRepo invoiceDetailsRepo;

    @Autowired
    private DealerBankDetailsRepo dealerBankDetailsRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private ChannelFinanceIndentRepo channelFinanceIndentRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public List<ResponseInvoiceDto> getInvoice(String dealerCode, double indentAmount,String bankName) throws ParseException {
        List<ResponseInvoiceDto> sortedResponseInvoiceDtos = new ArrayList<>();
        DealerBankDetails bankDetails = dealerBankDetailsRepo.getBankDetailsByDealerId(userAuthentication.getDealerId(), bankName);

        
        //sp_getInvoicesForChannelFinanceIndent dealer id, no of days
        List<AccpacChannelFinanceInvoice> invoiceDetail = invoiceDetailsRepo.findByDealerCodeAndStatusOrStatus(userAuthentication.getDealerId(), bankDetails.getNumberOfDays());

       // List<ChannelFinanceInvoiceDto> invoices = channelFinanceIndentRepo.getInvoices(bankDetails.getDealerBankDetailsId().getDealerCode());
        AtomicReference<Double> indentAmt = new AtomicReference<>(indentAmount);
        logger.info("bank==============>" + bankDetails);
        List<ResponseInvoiceDto> responseInvoiceDtos = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = format.parse(format.format(new Date()));

        invoiceDetail.forEach(i -> {
            ResponseInvoiceDto responseInvoiceDto = new ResponseInvoiceDto();
            responseInvoiceDto.setId(i.getId());
            responseInvoiceDto.setInvoiceNumber(i.getInvoiceNumber());
            responseInvoiceDto.setInvoiceDate(i.getInvoiceDate());
            responseInvoiceDto.setInvoiceAmount(i.getInvoiceAmount());
            responseInvoiceDto.setTotalUtilisedAmount(i.getTotalUtilisedAmount());
            long days = (currentDate.getTime()) - (i.getInvoiceDate().getTime());
            responseInvoiceDto.setAgeing(String.valueOf(TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS)));
            responseInvoiceDto.setStatus(i.getStatus());
            if ((TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS)) <= bankDetails.getNumberOfDays()) {
                responseInvoiceDtos.add(responseInvoiceDto);
            }
        });


      /*  if (Double.valueOf(i.getInvoiceAmount())-Double.valueOf(i.getUtilisedAmount())<indentAmt.get()){
            r.setChannelFinanceAmount(String.valueOf(Double.valueOf(i.getInvoiceAmount())-Double.valueOf(i.getUtilisedAmount())));
            indentAmt.set(indentAmt.get() - (Double.valueOf(i.getInvoiceAmount()) - Double.valueOf(i.getUtilisedAmount())));

        }*/


        responseInvoiceDtos.forEach(r -> {
            if (indentAmt.get() > 0.0) {
                logger.info("indent amount==============>" + indentAmt.get());
                logger.info("invoice amount==============>" + r.getInvoiceAmount());
                logger.info("total utilised amount==============>" + r.getTotalUtilisedAmount());
                r.setUtilisedAmount(r.getTotalUtilisedAmount());
                if (indentAmt.get() >= (r.getInvoiceAmount()-r.getTotalUtilisedAmount())) {
                        r.setChannelFinanceAmount(String.valueOf(r.getInvoiceAmount()-r.getTotalUtilisedAmount()));
                        indentAmt.set(indentAmt.get() -(r.getInvoiceAmount()-r.getTotalUtilisedAmount()));
                } else {
                    r.setChannelFinanceAmount(String.valueOf(indentAmt.get()));
                    indentAmt.set(indentAmt.get() - indentAmt.get());
                }


            } else {
                r.setChannelFinanceAmount("0.0");
            }
            sortedResponseInvoiceDtos.add(r);
        });


        //responseInvoiceDtos.sort(new ChannelFinanceIndentImpl());
      /*  responseInvoiceDtos.forEach(r -> {
            if (indentAmt.get() > 0.0) {
                logger.info("indent amount==============>" + indentAmt.get());
                logger.info("invoice amount==============>" + r.getInvoiceAmount());
                if (!invoices.isEmpty()) {
                    invoices.forEach(i -> {
                        logger.info("invoices utilised amount AND Id==============>" + i.getUtilisedAmount() + " & " + i.getId());
                        logger.info("invoices invoice amount AND Id==============>" + i.getInvoiceAmount() );

                        if ((indentAmt.get() + Double.valueOf(i.getUtilisedAmount())) >= Double.valueOf(i.getInvoiceAmount())) {
                            if (r.getInvoiceAmount().equals(Double.valueOf(i.getInvoiceAmount()))) {
                                r.setChannelFinanceAmount(i.getInvoiceAmount());
                                indentAmt.set(indentAmt.get() - (Double.valueOf(i.getInvoiceAmount()) - Double.valueOf(i.getUtilisedAmount())));
                                logger.info("new indentAmount==============>" + indentAmt.get());
                                logger.info("new CFI Amount==============>" + r.getChannelFinanceAmount());
                            }

                            }
                        //logger.info("accpac Partial invoice number==============>" +i.getInvoiceStatus());
                        //logger.info("invoice Partial invoice number==============>" + i.getInvoiceNumber());

                        else if (i.getAccpacstatus().equalsIgnoreCase("Partial") && i.getInvoiceNumber().equalsIgnoreCase(r.getInvoiceNumber()) && Double.valueOf(i.getUtilisedAmount())>0) {
                            logger.info("utilised amt==============>" + i.getUtilisedAmount());
                            if ((indentAmt.get() + Double.valueOf(i.getUtilisedAmount()) <= Double.valueOf(i.getInvoiceAmount()))) {
                                if (r.getInvoiceAmount().equals(Double.valueOf(i.getInvoiceAmount()))) {
                                    r.setChannelFinanceAmount(String.valueOf(indentAmt.get()));
                                    logger.info("Partial CFI amt==============>" + r.getChannelFinanceAmount());
                                    indentAmt.set((Double.valueOf(i.getInvoiceAmount()) - (indentAmt.get() + Double.valueOf(i.getUtilisedAmount()))) - indentAmt.get());
                                }

                            } else {
                                //r.setChannelFinanceAmount(String.valueOf((indentAmt.get() + Double.valueOf(i.getUtilisedAmount())) - r.getInvoiceAmount()));
                                if (r.getInvoiceAmount().equals(Double.valueOf(i.getInvoiceAmount()))) {
                                    r.setChannelFinanceAmount(String.valueOf(i.getInvoiceAmount()));
                                    indentAmt.set(indentAmt.get() - ((indentAmt.get() + Double.valueOf(i.getUtilisedAmount())) - Double.valueOf(i.getInvoiceAmount())));
                                }
                            }
                            //r.setChannelFinanceAmount(String.valueOf(indentAmt.get()));
                            logger.info("partial indent amount==============>" + indentAmt);
                            r.setUtilisedAmount(Double.valueOf(i.getUtilisedAmount()));
                            //logger.info("partial invoice amount==============>" + r.getInvoiceAmount());
                            //logger.info("utilised amount amount==============>" + i.getUtilisedAmount());
                        } else if (i.getAccpacstatus().equalsIgnoreCase("Pending") && i.getInvoiceNumber().equalsIgnoreCase(r.getInvoiceNumber())) {
                            logger.info("Pending case indent amt==============>" + indentAmt.get());
                            if (r.getInvoiceAmount().equals(Double.valueOf(i.getInvoiceAmount()))) {
                                r.setChannelFinanceAmount(String.valueOf(indentAmt.get()));
                                indentAmt.set(indentAmt.get() - indentAmt.get());
                            }
                        }
                    });
                }
                else {
                    if (indentAmt.get() >= r.getInvoiceAmount()) {
                        r.setChannelFinanceAmount(String.valueOf(r.getInvoiceAmount()));
                        indentAmt.set(indentAmt.get() - r.getInvoiceAmount());
                    }
                    else {
                        r.setChannelFinanceAmount(String.valueOf(indentAmt.get()));
                        indentAmt.set(indentAmt.get() - indentAmt.get());
                    }
                }

            } else {
                r.setChannelFinanceAmount("-");
            }
            sortedResponseInvoiceDtos.add(r);
        });*/
        return sortedResponseInvoiceDtos;
    }

    @Override
    public void saveChannelFinanceIndent(ChannelFinanceIndent channelFinanceIndent) throws ParseException {
        channelFinanceIndent.setIndentDate(new Date());
        channelFinanceIndent.setCreatedDate(new Date());
        //channelFinanceIndent.setCreatedBy(userAuthentication.getDealerId());
        
        //System.out.println("Created By: "+channelFinanceIndent.getCreatedBy()+"Created Date: "+ channelFinanceIndent.getCreatedDate());
        
        //ChannelFinanceIndent channelFinanceIndent1 = channelFinanceIndentRepo.save(channelFinanceIndent);
        
        channelFinanceIndentRepo.save(channelFinanceIndent);
        
       // List<ChannelFinanceIntentDetail> details = new ArrayList<>();
        DealerBankDetails bankDetails = dealerBankDetailsRepo.getBankDetailsByDealerId(userAuthentication.getDealerId(), channelFinanceIndent.getBankName());

       /* List<ResponseInvoiceDto> invoiceDtos = getInvoice(bankDetails.getDealerBankDetailsId().getDealerCode(),
                channelFinanceIndent1.getIndentAmount());*/

        bankDetails.setAvailableAmount(bankDetails.getAvailableAmount() - channelFinanceIndent.getIndentAmount());
        dealerBankDetailsRepo.save(bankDetails); //update available amount in dealer bank finance

        /*invoiceDtos.forEach(i -> {
            ChannelFinanceIntentDetail channelFinanceIntentDetail = new ChannelFinanceIntentDetail();
            channelFinanceIntentDetail.setInvoiceDate(i.getInvoiceDate());
            channelFinanceIntentDetail.setAgeing(i.getAgeing());
            AccpacChannelFinanceInvoice accpacChannelFinanceInvoice = invoiceDetailsRepo.findByInvoiceNumber(i.getInvoiceNumber());
            logger.info("channelFinanceIntentDetail ==============>" + i.getChannelFinanceAmount());

            if (!i.getChannelFinanceAmount().equalsIgnoreCase("-")) {
                channelFinanceIntentDetail.setInvoiceAmount(i.getInvoiceAmount());
                accpacChannelFinanceInvoice.setTotalUtilisedAmount(Double.valueOf(i.getChannelFinanceAmount()) + i.getUtilisedAmount());
                //channelFinanceIntentDetail.setUtilisedAmount(Double.valueOf(i.getChannelFinanceAmount())+i.getUtilisedAmount());
                logger.info("save ChannelFinanceAmount indent amount==============>" + Double.valueOf(i.getChannelFinanceAmount()));
                logger.info("save InvoiceAmount indent amount==============>" + i.getInvoiceAmount());
                logger.info("save utilised indent amount==============>" + i.getUtilisedAmount());

                if ((Double.valueOf(i.getChannelFinanceAmount())+i.getTotalUtilisedAmount())==i.getInvoiceAmount()) {
                    logger.info("save cleared indent amount==============>" + "cleared");
                    channelFinanceIntentDetail.setUtilisedAmount((Double.valueOf(i.getChannelFinanceAmount())+i.getTotalUtilisedAmount()));
                    channelFinanceIntentDetail.setStatus("Cleared");
                    accpacChannelFinanceInvoice.setStatus("Cleared");
                } else {
                    logger.info("save partial indent amount==============>" + "Partial");
                    //channelFinanceIntentDetail.setUtilisedAmount(Double.valueOf(i.getChannelFinanceAmount()) + i.getUtilisedAmount());
                    channelFinanceIntentDetail.setUtilisedAmount(Double.valueOf(i.getChannelFinanceAmount()));
                    channelFinanceIntentDetail.setStatus("Partial");
                    accpacChannelFinanceInvoice.setStatus("Partial");
                }
            }
            else if (Double.valueOf(i.getChannelFinanceAmount()) < i.getInvoiceAmount()) {
                channelFinanceIntentDetail.setStatus("Pending");
                channelFinanceIntentDetail.setInvoiceAmount(Double.valueOf(i.getChannelFinanceAmount()));
                accpacChannelFinanceInvoice.setStatus("Pending");

            }
            else {
                channelFinanceIntentDetail.setStatus(i.getStatus());
                channelFinanceIntentDetail.setInvoiceAmount(i.getInvoiceAmount());
            }
            //  channelFinanceIntentDetail.setBankName(channelFinanceIndent1.getBankName());
            //  channelFinanceIntentDetail.setFlexiAccountNumber(channelFinanceIndent1.getFlexiLoanAccountNumber());
            channelFinanceIntentDetail.setInvoiceNumber(i.getInvoiceNumber());
            channelFinanceIntentDetail.setChannelFinanceIndent(channelFinanceIndent1);
            details.add(channelFinanceIntentDetail);
            invoiceDetailsRepo.save(accpacChannelFinanceInvoice);
        });*/
        //channelFinanceIndent1.setChannelFinanceIntentDetail(details);
        //channelFinanceIndentRepo.save(channelFinanceIndent1);
    }

    @Override
    public int compare(ResponseInvoiceDto r1, ResponseInvoiceDto r2) {
        if (Integer.valueOf(r1.getAgeing()) >= Integer.valueOf(r2.getAgeing())) {
            return 1;
        } else {
            return -1;
        }
    }
}
