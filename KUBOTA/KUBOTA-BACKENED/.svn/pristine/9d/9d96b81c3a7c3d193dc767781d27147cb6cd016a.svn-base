package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.service;

import com.i4o.dms.kubota.salesandpresales.enquiry.dto.EnquiryByActivityDto;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.ActivityReportEnquiryDetails;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReport;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto.ReportViewResponse;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.repository.MarketingActivityReportRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarketingActivityReportImpl implements MarketingActivityReportService {

    @Autowired
    private MarketingActivityReportRepo marketingActivityReportRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public ReportViewResponse getActivityReportById(Long id) {
        return null;
    }

    @Override
    public List<ActivityReportEnquiryDetails> getEntityDetails(String activityNumber, MarketingActivityReport report) {
        List<ActivityReportEnquiryDetails> enquiryDetails = new ArrayList<>();
        List<EnquiryByActivityDto> enquiryByActivityDtos = enquiryRepo.getEnquiryByActivityNumber(activityNumber);
        logger.info("activity number=====>" + activityNumber);

        enquiryByActivityDtos.forEach(e -> {
            //logger.info("dto list=====>" + e.getIsContacted());
            ActivityReportEnquiryDetails enquiryDetail = new ActivityReportEnquiryDetails();
            enquiryDetail.setEnquiry(enquiryRepo.findById(e.getId()).get());
            enquiryDetail.setMarketingActivityReportId(report);
            try {
                Date tentitiveDate = new SimpleDateFormat("dd-MM-yyyy").parse(e.getTentativePurchaseDate());
                enquiryDetail.setTentativeDateOfPurchase(tentitiveDate);

            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            enquiryDetail.setIsContacted(e.getIsContacted());
            enquiryDetails.add(enquiryDetail);
        });
        logger.info("enquiry size=====>" + enquiryDetails.size());

        return enquiryDetails;
    }
}
