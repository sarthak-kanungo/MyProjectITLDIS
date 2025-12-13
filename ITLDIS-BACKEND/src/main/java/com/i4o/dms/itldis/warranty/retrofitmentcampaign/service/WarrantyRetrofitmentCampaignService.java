package com.i4o.dms.itldis.warranty.retrofitmentcampaign.service;

import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaign;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
public interface WarrantyRetrofitmentCampaignService {

    String[] PreDefinedColumns = new String[]{
           "ChassisNo"
    };
    
    void saveExcelChassisDetail(MultipartFile multipartFile) throws Exception;
    
	ApiResponse saveRetrofitmentCampaign(
			@RequestPart(value = "warrantyRetrofitmentCampaign") WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign,
			@RequestPart(value = "multipartFile") MultipartFile multipartFile,
			@RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList
			)throws InvalidColumnException, IOException;
}
