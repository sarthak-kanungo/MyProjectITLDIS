import { Injectable } from '@angular/core';
import { Adapter } from '../../../../core/adapter';
import { InsuranceDetail } from './insurance-detail';

@Injectable()
export class InsuranceDetailAdaptorService implements Adapter<InsuranceDetail> {
  adapt<R>(item: any, keyMap?: any): InsuranceDetail | R {
    const insuranceDetail = new InsuranceDetail();
    insuranceDetail.id = item.id || null
    insuranceDetail.companyCode = item.companyCode || null
    insuranceDetail.companyName = item.companyName || null
    insuranceDetail.address1 = item.address1 || null
    insuranceDetail.address2 = item.address2 || null
    insuranceDetail.address3 = item.address3 || null
    insuranceDetail.pinCode = item.pinCode || null
    insuranceDetail.locality = item.locality || null
    insuranceDetail.tehsil = item.tehsil || null
    insuranceDetail.city = item.city || null
    insuranceDetail.state = item.state || null
    insuranceDetail.country = item.country || null
    insuranceDetail.std = item.std || null
    insuranceDetail.telephoneNumber = item.telephoneNumber || null
    insuranceDetail.email = item.email || null
    console.log('insuranceDetail', insuranceDetail);

    return insuranceDetail;
  }
  saveAdapt?<R>(record: R): InsuranceDetail {
    throw new Error("Method not implemented.");
  }

  constructor() { }
}
