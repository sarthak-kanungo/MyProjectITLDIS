import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class LostDropEnquiryService {
  private resultEnquiryForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }


  createresultEnquiryForm() {
    this.resultEnquiryForm = this.fb.group({
      result: ['', Validators.compose([])],
      lostDrop: ['', Validators.compose([])],
      brand: ['', Validators.compose([])],
      model: ['', Validators.compose([])],
      remarks: ['', Validators.compose([])],
      reason: ['', Validators.compose([])],
    })
    return this.resultEnquiryForm
  }
}
