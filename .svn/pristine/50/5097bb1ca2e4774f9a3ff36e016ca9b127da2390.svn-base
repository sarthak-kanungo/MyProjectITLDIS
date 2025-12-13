import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class EnquirySearchCriteriaService {
  private enquiryListForm: FormGroup;
  private enquiryAdvancedSearch: FormGroup;

  constructor(
    private fb: FormBuilder,
   
  ) { }

  searchenquiryListForm() {
    this.enquiryListForm = this.fb.group({
      enquiryNumber: [''],
      enquiryType: [''],
      model: [''],
      salesPerson: [''],
      fromDate: [''],
      toDate: [''],
      source: [''],
      enquiryStatus: [''],
      retailConversionActivity: [''],
      product: [''],
      series: [''],
      subModel: [''],
      variant: [''],
      itemNo: [''],
      finance: [''],
      autoClose: [''],
      subsidy: [''],
      exchange: [''],
      nextFollowUpFromDate: [''],
      nextFollowUpToDate: [''],
      tentativePurchaseFromDate: [''],
      tentativePurchaseToDate: [''],
    });
    return this.enquiryListForm
  }

}
