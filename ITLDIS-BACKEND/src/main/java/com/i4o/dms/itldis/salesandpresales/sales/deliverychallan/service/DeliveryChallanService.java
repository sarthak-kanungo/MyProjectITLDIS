package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.service;

import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.ApproveDcDto;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.DcCancelRequestDto;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryChallanService {

	DeliveryChallan saveDc(DeliveryChallan deliveryChallan);

    void cancelDc(DcCancelRequestDto dcCancelRequestDto);

    /*@Transactional
    void approveDc(ApproveDcDto approveDcDto);*/
}
