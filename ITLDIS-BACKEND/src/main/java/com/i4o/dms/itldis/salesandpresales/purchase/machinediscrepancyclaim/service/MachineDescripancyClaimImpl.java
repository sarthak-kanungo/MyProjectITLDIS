package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.service;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository.MachineDescripancyComplaintRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineComplaintClaimMapping;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaim;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDescripancyClaimDtoClass;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDescripancyClaimSaveDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDiscrepancyClaimViewDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.MachineComplaintClaimMappingRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.MachineDiscrepancyClaimRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.i4o.dms.itldis.utils.Constants.DRAFT;
import static com.i4o.dms.itldis.utils.Constants.RELEASE;

@Service
public class MachineDescripancyClaimImpl implements MachineDescripancyClaimService {

    @Autowired
    private MachineDescripancyComplaintRepository machineDescripancyComplaintRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private MachineDiscrepancyClaimRepository machineDiscrepancyClaimRepository;

    @Autowired
    private MachineComplaintClaimMappingRepo complaintClaimMappingRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Override
    public List<MachineDescripancyClaimDtoClass> getMachineDescripancyClaim(Long id) {
        List<MachineDescripancyComplaint> machineDescripancyComplaints = machineDescripancyComplaintRepository.findByDealerEmployeeMasterId(id);
        List<MachineDescripancyClaimDtoClass> descripancyClaimDtoClasses = new ArrayList<>();
        machineDescripancyComplaints.forEach(m -> {
            MachineDescripancyClaimDtoClass dtoClass = new MachineDescripancyClaimDtoClass();
            dtoClass.setComplaintId(m.getComplaintId());
            dtoClass.setComplaintStatus(m.getComplaintStatus());
            dtoClass.setDraftMode(m.getDraftMode());
            dtoClass.setComplaintNumber(m.getComplaintNumber());
            dtoClass.setComplaintId(m.getComplaintId());
            dtoClass.setComplaintDate(m.getComplaintDate());
            dtoClass.setItemNo(m.getGrnMachineDetails().getItemNo());
            dtoClass.setItemDescription(m.getGrnMachineDetails().getItemDescription());
            dtoClass.setChassisNo(m.getGrnMachineDetails().getChassisNo());
            dtoClass.setMachineDescripancyComplaintDetails(m.getMachineDescripancyComplaintDetails());
            descripancyClaimDtoClasses.add(dtoClass);
        });
        return descripancyClaimDtoClasses;
    }

    @Override
    public List<MachineDescripancyClaimDtoClass> getSavedMachineDescripancyComplaintDetails(Long id) {
        List<MachineDescripancyComplaint> machineDescripancyComplaints = machineDescripancyComplaintRepository.findByDealerEmployeeMasterId(id);
        List<MachineDescripancyClaimDtoClass> descripancyClaimDtoClasses = new ArrayList<>();
        machineDescripancyComplaints.forEach(m -> {
            MachineDescripancyClaimDtoClass dtoClass = new MachineDescripancyClaimDtoClass();
            dtoClass.setComplaintId(m.getComplaintId());
            dtoClass.setComplaintStatus(m.getComplaintStatus());
            dtoClass.setDraftMode(m.getDraftMode());
            dtoClass.setComplaintId(m.getComplaintId());
            dtoClass.setComplaintDate(m.getComplaintDate());
            dtoClass.setItemNo(m.getGrnMachineDetails().getItemNo());
            dtoClass.setItemDescription(m.getGrnMachineDetails().getItemDescription());
            dtoClass.setChassisNo(m.getGrnMachineDetails().getChassisNo());
            dtoClass.setMachineDescripancyComplaintDetails(m.getMachineDescripancyComplaintDetails());
            descripancyClaimDtoClasses.add(dtoClass);
        });
        return descripancyClaimDtoClasses;
    }

    @Transactional
    @Override
    public MachineDiscrepancyClaim saveMachineDescripancyClaim(MachineDescripancyClaimSaveDto machineDescripancyClaimSaveDto) {

        MachineDiscrepancyClaim discrepancyClaim = new MachineDiscrepancyClaim();

        //discrepancyClaim.setKubotaEmployeeMaster(kubotaEmployeeRepository.findById(machineDescripancyClaimSaveDto.getCreatedBy()).get());
        discrepancyClaim.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        discrepancyClaim.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        discrepancyClaim.setRemarks(machineDescripancyClaimSaveDto.getRemarks());

        if (machineDescripancyClaimSaveDto.getClaimId() != null) {
            discrepancyClaim.setClaimId(machineDescripancyClaimSaveDto.getClaimId());
        }
        if (machineDescripancyClaimSaveDto.isDraftMode()) {
            discrepancyClaim.setClaimStatus(DRAFT);
        } else {
            discrepancyClaim.setClaimStatus(RELEASE);
        }
        discrepancyClaim.setDraftMode(machineDescripancyClaimSaveDto.isDraftMode());
        //save discrepancy claim
        MachineDiscrepancyClaim savedDiscrepancyClaim = machineDiscrepancyClaimRepository.save(discrepancyClaim);


        machineDescripancyClaimSaveDto.getMachineDescripancyClaim().forEach(list -> {
            MachineComplaintClaimMapping complaintClaimMapping = new MachineComplaintClaimMapping();
            MachineDescripancyComplaint descripancyComplaint = machineDescripancyComplaintRepository.findById(list.getComplaintId()).get();
            complaintClaimMapping.setMachineDescripancyComplaint(descripancyComplaint);
            complaintClaimMapping.setMachineDiscrepancyClaim(savedDiscrepancyClaim);
            //save claim complaint mapping
            complaintClaimMappingRepo.save(complaintClaimMapping);
            //update complaint details
            descripancyComplaint.setMachineDescripancyComplaintDetails(list.getMachineDescripancyComplaintDetails());
            machineDescripancyComplaintRepository.save(descripancyComplaint);

        });
        return discrepancyClaim;
    }

    @Override
    public MachineDiscrepancyClaimViewDto getMachineDiscrepancyById(Long claimId) {
        MachineDiscrepancyClaim claim = machineDiscrepancyClaimRepository.findById(claimId).get();
        List<MachineComplaintClaimMapping> mapping = complaintClaimMappingRepo.findByMachineDiscrepancyClaim(claim);
        MachineDiscrepancyClaimViewDto viewDto = new MachineDiscrepancyClaimViewDto();
        List<MachineDescripancyClaimDtoClass> saveListDtos = getMachineDescripancyClaim(claim.getDealerEmployeeMaster().getId());


        /*  mapping.forEach(m -> {
            MachineDescripancyClaimDtoClass saveListDto = new MachineDescripancyClaimDtoClass();
            saveListDto.setComplaintId(m.getMachineDescripancyComplaint().getComplaintId());
            saveListDto.setMachineDescripancyComplaintDetails(m.getMachineDescripancyComplaint().getMachineDescripancyComplaintDetails());
            saveListDtos.add(saveListDto);
        });*/
        viewDto.setClaimId(claim.getClaimId());
        viewDto.setClaimNumber(claim.getClaimNumber());
        viewDto.setClaimStatus(claim.getClaimStatus());
        viewDto.setClaimDate(claim.getClaimDate());
        viewDto.setRemarks(claim.getRemarks());
        viewDto.setCreatedBy(claim.getDealerEmployeeMaster().getId());
        viewDto.setMachineDescripancyClaim(getMachineDescripancyClaim(claim.getDealerEmployeeMaster().getId()));
        return viewDto;
    }
}
