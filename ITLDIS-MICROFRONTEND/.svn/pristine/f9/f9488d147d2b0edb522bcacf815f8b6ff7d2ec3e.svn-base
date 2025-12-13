import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class DeliveryProspectDetailsService {

  prospectDetailsFrom: FormGroup;

  constructor(private fb: FormBuilder) { }

  createProspectDetailsForm() {
    this.prospectDetailsFrom = this.fb.group({
      id:[{ value: null, disabled: false }],  
      customerType: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      companyName: [{ value: null, disabled: true }, Validators.compose([])],
      customerCode: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      customerName: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      invoiceCustomerName: [null, Validators.compose([Validators.required])],

      dealerName: [{ value: null, disabled: true }, Validators.compose([])],
      dealerCode: [{ value: null, disabled: true }, Validators.compose([])],

      address1: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      address2: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      address3: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      pinCode: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      postOffice: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      // village: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      tehsil: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      district: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      city: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      state: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      mobileNumber: [{ value: null, disabled: false }, Validators.compose([Validators.required, CustomValidators.mobileNumber, CustomValidators.numberOnly, CustomValidators.maxLength(10)])],
      panNumber: [{ value: null, disabled: true }, Validators.compose([])],
      gstInNumber: [{ value: null, disabled: true }, Validators.compose([])]
    })
    return this.prospectDetailsFrom;
  }
}
