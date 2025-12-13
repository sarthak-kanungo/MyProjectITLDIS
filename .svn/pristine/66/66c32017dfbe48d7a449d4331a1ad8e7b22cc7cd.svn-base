import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class CurrentEnquiryFollowupService {
  private  currentFolloeUpsForm: FormGroup;
  
  constructor(
    private fb: FormBuilder
  ) { }

  createcurrentFolloeUpsForm() {
    this.currentFolloeUpsForm = this.fb.group({
      tentativePurchaseDate: [null, Validators.compose([Validators.required])],
      remarks:[null, Validators.compose([Validators.required])],
      updationDate:[{value:new Date(), disabled:true}],
      nextFollowUpDate:[null, Validators.compose([Validators.required])],
      followUpType:[null, Validators.compose([Validators.required])],
      currentFollowUpDate:[{value:new Date(), disabled:false}],
    })
    return this.currentFolloeUpsForm
  }
}
