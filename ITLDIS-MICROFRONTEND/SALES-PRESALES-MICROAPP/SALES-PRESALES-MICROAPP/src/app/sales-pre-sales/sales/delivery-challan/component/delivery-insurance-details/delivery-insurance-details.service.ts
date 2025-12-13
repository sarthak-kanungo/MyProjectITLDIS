import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class DeliveryInsuranceDetailsService {
  private readonly searchByInsuranceCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getInsuranceCodeAutoComplete}`;
  private readonly getCompanyDetailsFromCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getInsuranceDetailsByCode}`;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient
  ) { }

  createDcInsuranceDetailsForm() {
    return this.fb.group({
      insuranceCompanyMaster: [null, Validators.compose([])],
      companyName: [{ value: null, disabled: true }, Validators.compose([])],
      address1: [{ value: null, disabled: true }, Validators.compose([])],
      address2: [{ value: null, disabled: true }, Validators.compose([])],
      address3: [{ value: null, disabled: true }, Validators.compose([])],
      pinCode: [{ value: null, disabled: true }, Validators.compose([])],
      locality: [{ value: null, disabled: true }, Validators.compose([])],
      tehsil: [{ value: null, disabled: true }, Validators.compose([])],
      city: [{ value: null, disabled: true }, Validators.compose([])],
      state: [{ value: null, disabled: true }, Validators.compose([])],
      country: [{ value: null, disabled: true }, Validators.compose([])],
      std: [{ value: null, disabled: true }, Validators.compose([])],
      telephoneNumber: [{ value: null, disabled: true }, Validators.compose([])],
      email: [{ value: null, disabled: true }, Validators.compose([])],
      policyStartDate: [null, Validators.compose([])],
      policyExpiryDate: [null, Validators.compose([])],
    })
  }

  searchByInsuranceCompanyCode(code: string) {
    return this.httpClient.get(this.searchByInsuranceCodeUrl,
      {
        params: new HttpParams().set('companyCode', code)
      });
  }
  getCompanyDetailsFromCode(companyCode: string) {
    return this.httpClient.get(this.getCompanyDetailsFromCodeUrl, {
      params: new HttpParams().set('companyCode', companyCode)
    });
  }
}
