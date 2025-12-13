import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerType } from '../domain/part-quotation-domain';
import { PartQuotationApi } from '../url-util/part-quotation-url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SparePoApi } from 'src/app/spares/spares-purchase/spares-purchase-order/url-utils/spares-purchse-order-urls';

@Injectable()
export class PartQuotationCommonWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getSpareCustomerTypeDropdown(documentType: string): Observable<Array<CustomerType>> {
    return this.httpClient.get<BaseDto<Array<CustomerType>>>(PartQuotationApi.getSpareCustomerTypeDropdown, {
      params: new HttpParams()
        .append('documentType', documentType)
    }).pipe(map(dto => dto.result))
  }

  public uploadExcelPQItemDetail(uploadableFile:any){
     let formData: FormData = new FormData();
     formData.append('file', uploadableFile['file']);
     formData.append('state', uploadableFile['state']);
     formData.append('discountRate', uploadableFile['discountRate'])
     formData.append('existingItems', uploadableFile['existingItems']);
    
     const headers = new HttpHeaders();
     headers.set('Content-Type', null);
     headers.set('Accept', 'multipart/form-data');

     return this.httpClient.post(PartQuotationApi.uploadExcelQuotation, formData, {
         headers: headers
     });
  }

}
