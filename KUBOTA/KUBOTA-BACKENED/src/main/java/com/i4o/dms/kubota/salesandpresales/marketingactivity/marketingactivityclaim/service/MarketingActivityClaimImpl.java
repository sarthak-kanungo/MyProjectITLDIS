package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.service;

import com.i4o.dms.kubota.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import com.i4o.dms.kubota.masters.salesandpresales.enquirysource.repository.EnquirySourceMasterRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaim;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto.*;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.repository.MarketingActivityClaimRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalRepo;
import com.i4o.dms.kubota.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketingActivityClaimImpl implements MarketingActivityClaimService {

    @Autowired
    private MarketingActivityClaimRepo marketingActivityClaimRepo;

    @Autowired
    private StorageService storageService;
    
    @Autowired
    private EnquirySourceMasterRepo enquirySourceMasterRepo;

    @Autowired
    private MarketingActivityProposalRepo marketingActivityProposalRepo;

    @Override
    public MarketingActivityClaimDetailsByActivityNumberDto getMarketingActivityClaimDetailsByActivityNumber(String activityNumber) {

        MarketingActivityDetailsForClaimDto claimDto=marketingActivityClaimRepo.getMarketingActivityClaimDetailsByActivityNumber(activityNumber);
        List<MarketingActivityClaimHeadDto> headDto=marketingActivityClaimRepo.getMarketingActivityClaimHeadDto(activityNumber);
        List<MarketingActivityReportImagesDto>  reportImagesDtos=marketingActivityClaimRepo.getMarketingReportImagesForClaim(activityNumber);

        MarketingActivityClaimDetailsByActivityNumberDto dto=new MarketingActivityClaimDetailsByActivityNumberDto();
        dto.setActivityCreationDate(claimDto.getActivityCreationDate());
        dto.setActivityType(claimDto.getActivityType());
        dto.setNumberOfDays(claimDto.getNumberOfDays());
        dto.setFromDate(claimDto.getFromDate());
        dto.setToDate(claimDto.getToDate());
        dto.setActualFromDate(claimDto.getActualFromDate());
        dto.setActualToDate(claimDto.getActualToDate());
        dto.setLocation(claimDto.getLocation());
        dto.setTotalEnquiries(claimDto.getTotalEnquiries());
        dto.setHotEnquiry(claimDto.getHotEnquiry());
        dto.setWarmEnquiry(claimDto.getWarmEnquiry());
        dto.setColdEnquiry(claimDto.getColdEnquiry());
        dto.setTotalBookings(claimDto.getTotalBookings());
        dto.setTotalRetails(claimDto.getTotalRetails());
        dto.setActivityNumber(activityNumber);
        List<MarketingActivityClaimHeadDtoClass> heads=new ArrayList<>();
        headDto.forEach(h->{
            MarketingActivityClaimHeadDtoClass headDtoClass=new MarketingActivityClaimHeadDtoClass();
            headDtoClass.setId(h.getId());
            headDtoClass.setHeadName(h.getHeadName());
            headDtoClass.setApprovedAmount(h.getApprovedAmount());
            headDtoClass.setAmount(h.getAmount());
            heads.add(headDtoClass);
        });
        dto.setHeads(heads);
        List<MarketingReportImagesClassDto> reportImages=new ArrayList<>();
        reportImagesDtos.forEach(r->{
            MarketingReportImagesClassDto reportImagesClassDto=new MarketingReportImagesClassDto();
            reportImagesClassDto.setId(r.getId());
            reportImagesClassDto.setPhotoPath(r.getPhotoPath());
            reportImages.add(reportImagesClassDto);
        });
        dto.setReportImages(reportImages);

        return dto;
    }

    @Override
    public MarketingActivityClaimDetailsByActivityNumberDto getClaimById(Long claimId) {

        MarketingActivityClaim claim=marketingActivityClaimRepo.findById(claimId).get();

        MarketingActivityProposal proposal=marketingActivityProposalRepo.findById(claim.getMarketingActivityProposal().getActivityProposalId()).get();

        MarketingActivityDetailsForClaimDto claimDto=marketingActivityClaimRepo.getMarketingActivityClaimDetailsByActivityNumber(proposal.getActivityNumber());

        List<MarketingActivityClaimHeadDto> headDto=marketingActivityClaimRepo.getMarketingActivityClaimHeadDto(proposal.getActivityNumber());

        List<MarketingActivityReportImagesDto>  reportImagesDtos=marketingActivityClaimRepo.getMarketingReportImagesForClaim(proposal.getActivityNumber());

        MarketingActivityClaimDetailsByActivityNumberDto dto=new MarketingActivityClaimDetailsByActivityNumberDto();

       EnquirySourceMaster e = enquirySourceMasterRepo.findById(Long.valueOf(proposal.getActivityType())).get();
        
        dto.setClaimNumber(claim.getClaimNumber());
        dto.setActivityNumber(proposal.getActivityNumber());
        dto.setActivityCreationDate(claimDto.getActivityCreationDate());
        dto.setActivityType(e.getSourceName());
        dto.setNumberOfDays(claimDto.getNumberOfDays());
        dto.setFromDate(claimDto.getFromDate());
        dto.setToDate(claimDto.getToDate());
        dto.setActualFromDate(claimDto.getActualFromDate());
        dto.setActualToDate(claimDto.getActualToDate());

        dto.setLocation(claimDto.getLocation());
        dto.setTotalEnquiries(claimDto.getTotalEnquiries());
        dto.setHotEnquiry(claimDto.getHotEnquiry());
        dto.setWarmEnquiry(claimDto.getWarmEnquiry());
        dto.setColdEnquiry(claimDto.getColdEnquiry());
        dto.setTotalBookings(claimDto.getTotalBookings());
        dto.setTotalRetails(claimDto.getTotalRetails());

        dto.setCostPerBooking(claim.getCostPerBooking());
        dto.setCostPerEnquiry(claim.getCostPerEnquiry());
        dto.setCostPerHotEnquiry(claim.getCostPerHotEnquiry());
        dto.setCostPerRetail(claim.getCostPerRetail());

        dto.setClaimId(claim.getClaimId());
        dto.setClaimDate(claim.getClaimDate());
        dto.setActivityEffectiveness(claim.getActivityEffectiveness());

        List<MarketingActivityClaimHeadDtoClass> heads=new ArrayList<>();
        headDto.forEach(h->{
            MarketingActivityClaimHeadDtoClass headDtoClass=new MarketingActivityClaimHeadDtoClass();
            headDtoClass.setId(h.getId());
            headDtoClass.setHeadName(h.getHeadName());
            headDtoClass.setApprovedAmount(h.getApprovedAmount());
            headDtoClass.setAmount(h.getAmount());
            headDtoClass.setActualClaimAmount(h.getActualClaimAmount());
            headDtoClass.setHeadImage(h.getHeadImage());
            heads.add(headDtoClass);
        });
        dto.setHeads(heads);
        List<MarketingReportImagesClassDto> reportImages=new ArrayList<>();
        reportImagesDtos.forEach(r->{
            MarketingReportImagesClassDto reportImagesClassDto=new MarketingReportImagesClassDto();
            reportImagesClassDto.setId(r.getId());
            reportImagesClassDto.setPhotoPath(r.getPhotoPath());
            reportImages.add(reportImagesClassDto);
        });
        dto.setReportImages(reportImages);

        return dto;
    }
}
