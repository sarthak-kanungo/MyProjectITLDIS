import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { SelectList, SelectListAdapter } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { InsuranceDetailAdaptorService } from '../../model/insurance-detail-adaptor.service';

@Injectable()
export class InvoiceInsuranceDetailApiService {

  private readonly searchByInsuranceCodeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.deliveryChallan }${ urlService.getInsuranceCodeAutoComplete }`;
  private readonly getCompanyDetailsFromCodeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.deliveryChallan }${ urlService.getInsuranceDetailsByCode }`;
  constructor(
    private httpClient: HttpClient,
    private selectListAdapter: SelectListAdapter,
    private insuranceDetailAdaptorService: InsuranceDetailAdaptorService
  ) { }

  searchInsuranceCompanyCode(searchValue: string) {
    let keyMap = {
      id: 'id',
      value: 'companyCode'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.searchByInsuranceCodeUrl, {
      params: new HttpParams().set('companyCode', searchValue)
    }).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    )
  }
  getCompanyDetailsFromCode(companyCode: string) {
    return this.httpClient.get<BaseDto<any>>(this.getCompanyDetailsFromCodeUrl, {
      params: new HttpParams().set('companyCode', companyCode)
    }).pipe(
      map(res => {
        res.result = this.insuranceDetailAdaptorService.adapt(res.result);
        return res;
      })
    );
  }
}
