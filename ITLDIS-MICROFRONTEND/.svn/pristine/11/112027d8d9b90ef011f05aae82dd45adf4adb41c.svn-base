import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ProspectDetailsObj } from 'ProspectDetails';
import { EnquiryApi } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/url-utils/enquiry-urls';
import { ValidateMobileNumber, CustomValidators, ValidateMaxLength } from 'src/app/utils/custom-validators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { SalePopUpDetails } from './sale-pop-up.domain';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SalePopUpService {
  private saleExchangeFrom: FormGroup;
  private readonly searchbyMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getDataByMobileNo}`
  private readonly mobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getMobileNumber}`;
  // private readonly updateExchangeInvemtoryUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.exchangeInventory }${ urlService.updateExchangeInventory }`;
  private readonly updateExchangeInvemtoryUrl =`${environment.baseUrl}/${environment.api}/${urlService.module}/${urlService.exchangeInventroyController}/updateExchangeInventory`

  prospectDetailsObj = {} as ProspectDetailsObj;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient,
        public dialog: MatDialog,
  ) {}
  createSaleExchangeFrom() {
    this.saleExchangeFrom = this.fb.group({
      saleremarks: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      sellingprice: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      buyerContactNo: [{ value: null, disabled: false }, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{9}')])],
      buyerName: [{ value: null, disabled: false}, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
    });
    return this.saleExchangeFrom;
  }
  searchbyMobileNo(mobileNumber) {
    return this.httpClient.get(`${this.searchbyMobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    })
  }
  mobileNoData(mobileNumber) {
    return this.httpClient.get(`${this.mobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    })
  }
  updateExchangeInventory(data: SalePopUpDetails) {
    return this.httpClient.post(this.updateExchangeInvemtoryUrl, data);
  }
  ngOnDestroy() {
    this.dialog.closeAll();
  }
}
