package com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.service;

import com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.repository.MachineDescripancyComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.i4o.dms.kubota.utils.Constants.DRAFT;
@Service
public class MachineDescripancyComplaintImpl implements MachineDescripancyComplaintService {

    @Autowired
    private MachineDescripancyComplaintRepository machineDescripancyComplaintRepository;

    @Override
    public MachineDescripancyComplaint saveMachineDescripancyComplaint(MachineDescripancyComplaint complaint) {
//        if (complaint.getDraftMode()) {
//            complaint.setComplaintStatus(DRAFT);
//        }
//        else {
//            complaint.setComplaintStatus("pending for approval");
//        }
        return machineDescripancyComplaintRepository.save(complaint);
    }
}
