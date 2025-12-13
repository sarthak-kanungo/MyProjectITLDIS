import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class SearchQuotationService {

  searchQuotationForm: FormGroup

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  createSearchQuotation() {
    this.searchQuotationForm = this.fb.group({
      sources: [null, Validators.compose([])],
      enquiryStatus: [null, Validators.compose([])],
      salesPerson: [null, Validators.compose([])],
      products: [null, Validators.compose([])],
      series: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      submodel: [null, Validators.compose([])],
      variant: [null, Validators.compose([])],
      quotationFromDate: [null, Validators.compose([])],
      quotationToDate: [{ value: null, disabled: false }, Validators.compose([])],
      quotationCode: [null, Validators.compose([])],
      itemNo: [null, Validators.compose([])]
    })
    return this.searchQuotationForm;
  }

}
