import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class AddEnquiryFollowupService {

 private enquiryFolloeUpsForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  createenquiryFolloeUpsForm() {
    this.enquiryFolloeUpsForm = this.fb.group({
      enquiryNumber:[{value:'', disabled:true}],
      enquiryDate:[{value:'', disabled:true}],
      enquiryStatus:[{value:'', disabled:true}],
      product:[{value:'', disabled:true}],
      series:[{value:'', disabled:true}],
      model:[{value:'', disabled:true}],
      subModel:[{value:'', disabled:true}],
      variant:[{value:'', disabled:true}],
      firstName:[{value:'', disabled:true}],
      mobileNumber:[{value:'', disabled:true}],
    });
    return this.enquiryFolloeUpsForm
  }

}
