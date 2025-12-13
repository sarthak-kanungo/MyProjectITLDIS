import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class EnquiryProductInterestedService {
  private productInterestedForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  createproductInterestedForm() {
    this.productInterestedForm = this.fb.group({
      itemNo: [null, Validators.compose([])],
      itemDescription: [{ value: null, disabled: true }],
      series: [{ value: null, disabled: true }],
      product: [{ value: null, disabled: true }],
      exchangeBrand: [null],
      exchangeModel: [null],
      exchangeModelYear: [null],
      estimatedExchangePrice: [0.0, Validators.compose([])],
      variant: [null, Validators.compose([Validators.required])],
      subModel: [null, Validators.compose([Validators.required])],
      model: [null, Validators.compose([Validators.required])],
      exchangeRequired: [null, Validators.compose([Validators.required])],
    });
    return this.productInterestedForm
  }

  keyPress(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }


  ViewproductInterestedForm() {
    this.productInterestedForm = this.fb.group({
      itemNo: this.fb.control({ value: null, disabled: false }),
      itemDescription: this.fb.control({ value: null, disabled: true }),
      series: this.fb.control({ value: null, disabled: true }),
      product: this.fb.control({ value: null, disabled: true }),
      exchangeBrand: this.fb.control(null),
      exchangeModel: this.fb.control(null),
      estimatedExchangePrice: this.fb.control(0.0),
      variant: this.fb.control({ value: null, disabled: true }),
      subModel: this.fb.control({ value: null, disabled: true }),
      model: this.fb.control({ value: null, disabled: true }),
      exchangeRequired: this.fb.control({ value: null, disabled: false }),
    });
    return this.productInterestedForm
  }

  ViewMobileproductInterestedForm() {
    this.productInterestedForm = this.fb.group({
      itemNo: this.fb.control({ value: null, disabled: false }),
      itemDescription: this.fb.control({ value: null, disabled: true }),
      series: this.fb.control({ value: null, disabled: true }),
      product: this.fb.control({ value: null, disabled: true }),
      exchangeBrand: this.fb.control({ value: null, disabled: false }),
      exchangeModel: this.fb.control({ value: null, disabled: false }),
      estimatedExchangePrice: this.fb.control({ value: 0.0, disabled: false }),
      variant: this.fb.control({ value: null, disabled: true }),
      subModel: this.fb.control({ value: null, disabled: false }),
      model: this.fb.control({ value: null, disabled: true }),
      exchangeRequired: this.fb.control(null, Validators.compose([Validators.required])),
    });
    return this.productInterestedForm
  }
}
