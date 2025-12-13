package com.i4o.dms.kubota.salesandpresales.sales.invoice.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.domain.SalesInvoice;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.domain.SalesInvoiceCancelApproval;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.domain.SalesInvoiceCancelRequest;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.dto.CancelInvoiceDto;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.repository.InvoiceCancellationRepository;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.repository.InvoiceRepository;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.repository.SalesInvoiceCancelApprovalRepository;
import com.i4o.dms.kubota.salesandpresales.sales.invoice.service.SalesInvoiceService;
import com.i4o.dms.kubota.security.service.UserAuthentication;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private SalesInvoiceCancelApprovalRepository salesInvoiceCancelApprovalRepository;

    @Autowired
    private DesignationHierarchyRepository designationHierarchyRepository;
    
    @Autowired
    private InvoiceCancellationRepository invoiceCancellationRepository;
    
    @Override
    @Transactional
    public String cancelInvoice(CancelInvoiceDto invoiceDto) {

        try {
           SalesInvoice salesInvoice = invoiceRepository.getOne(invoiceDto.getInvoiceId());
           salesInvoice.setInvoiceStatus("Cancellation Under Approval");
           invoiceRepository.save(salesInvoice);
           /* salesInvoice.setInvoiceCancellationReason(invoiceDto.getInvoiceCancellationReason());
            salesInvoice.setOtherReason(invoiceDto.getReason());
            salesInvoice.setRemarks(invoiceDto.getRemarks());
            salesInvoice.setInvoiceCancellationType(invoiceDto.getCancellationType());*/
            //salesInvoice.setBrand(invoiceDto.getBrand());
            //salesInvoice.setModel(invoiceDto.getModel());
        	
        	 SalesInvoiceCancelRequest cancelRequest = new SalesInvoiceCancelRequest();
             cancelRequest.setInvoiceId(invoiceDto.getInvoiceId());
             cancelRequest.setCreatedBy(userAuthentication.getLoginId());
             cancelRequest.setCreatedDate(new Date());
             cancelRequest.setInvCancellationType(invoiceDto.getCancellationType());
             cancelRequest.setInvCancelRemark(invoiceDto.getRemarks());
             cancelRequest.setInvCancellationReason(invoiceDto.getInvoiceCancellationReason());
             cancelRequest.setInvCancellationOtherReason(invoiceDto.getReason());
             cancelRequest.setInvCancelDate(new Date());
             cancelRequest.setRequestDate(new Date());
             cancelRequest.setCancelApprovalStatus("Waiting For Approval");
             invoiceCancellationRepository.save(cancelRequest);

             List<SalesInvoiceCancelApproval> approvals = new ArrayList<>();
             salesInvoiceCancelApprovalRepository
                    .getCancelApprovalHierarchy(userAuthentication.getDealerId())
                    .forEach(designationHierarchy -> {
                    	SalesInvoiceCancelApproval approval = new SalesInvoiceCancelApproval();
                    	approval.setInvoiceCancelReqId(cancelRequest.getId());
                        approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                        approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                        approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                        approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                        approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                        approval.setRejectedFlag('N');
                        approvals.add(approval);
                    });
            salesInvoiceCancelApprovalRepository.saveAll(approvals);
            return "Your cancel request successfully submitted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
