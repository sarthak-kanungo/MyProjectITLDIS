package com.i4o.dms.kubota.salesandpresales.grn.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.accpac.domain.AccPacInvoice;
import com.i4o.dms.kubota.accpac.repository.AccPacInvoiceRepository;
import com.i4o.dms.kubota.salesandpresales.grn.dto.AccPacInvoiceDto;
import com.i4o.dms.kubota.security.service.UserAuthentication;

@Service
public class GrnServiceImpl implements GrnService {
    @Autowired
    private AccPacInvoiceRepository accPacInvoiceRepository;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Override
    public AccPacInvoiceDto getAccPacInvoice(String invoiceNumber, String grnType){
    	AccPacInvoiceDto invoiceDto = new AccPacInvoiceDto();
    	List<Map<String, Object>> map = accPacInvoiceRepository.getAccPacInvoiceDetails(invoiceNumber, grnType, userAuthentication.getBranchId());
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(grnType.equals("Machine Transfer Request")){
        	Map<String, Object> obj = map.get(0);
        	invoiceDto.setInvoiceNumber(obj.get("accpacInvoiceNo").toString());
        	try {
				invoiceDto.setInvoiceDate(df.parse(obj.get("invoiceDate").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        invoiceDto.setId(((BigInteger)obj.get("invoiceId")).longValue());
	        invoiceDto.setShipTo(obj.get("shipTo").toString());
	        invoiceDto.setBillTo(obj.get("billTo").toString());
	        invoiceDto.setLrNo(obj.get("lrNo").toString());
	        
        }else{
	        AccPacInvoice accPacInvoice = accPacInvoiceRepository.findByInvoiceNumber(invoiceNumber);
	        invoiceDto.setAdditionalAmount(accPacInvoice.getAdditionalAmount());
	        invoiceDto.setAdditionalCgstAmount(accPacInvoice.getAdditionalCgstAmount());
	        invoiceDto.setTotalAdditionalAmount(accPacInvoice.getTotalAdditionalAmount());
	        invoiceDto.setShipTo(accPacInvoice.getShipTo());
	        invoiceDto.setLrNo(accPacInvoice.getLrNo());
	        invoiceDto.setInvoiceTotalValue(accPacInvoice.getInvoiceTotalValue());
	        invoiceDto.setInvoiceNumber(accPacInvoice.getInvoiceNumber());
	        invoiceDto.setInvoiceDate(accPacInvoice.getInvoiceDate());
	        invoiceDto.setId(accPacInvoice.getId());
	        invoiceDto.setBillTo(accPacInvoice.getBillTo());
	        invoiceDto.setAdditionalSgstAmount(accPacInvoice.getAdditionalSgstAmount());
	        invoiceDto.setAdditionalIgstAmount(accPacInvoice.getAdditionalIgstAmount());
        }
        invoiceDto.setAccPacInvoicePartDetails(map);
        return invoiceDto;
    }
}
