import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class EnquiryCreationService {
  newEnquiryForm: FormGroup;

  constructor(
    private fb: FormBuilder,
  ) {
  }

  createnewEnquiryForm() {
    this.newEnquiryForm = this.fb.group({
     // enquiryNumber: [],
      referralPersonName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
      generationActivityNumber: [null],
     // conversionActivityNumber: [null],
      validationDate: [{ value: new Date(), disabled: true }],
      enquiryType: [{ value: null, disabled: true }],
     // enquiryStatus: [{ value: null, disabled: true }],
      updationDate: [{ value: null, disabled: true }],
      enquiryDate: [null, Validators.compose([Validators.required])],
     salesPerson: [null, Validators.compose([Validators.required])],
      followUpType: [null, Validators.compose([Validators.required])],
      source: [null, Validators.compose([Validators.required])],
      purposeOfPurchase: [null, Validators.compose([Validators.required])],
      generationActivityType: [null],
     // conversionActivityType: [null],
      //retailConversionActivity: [null, Validators.compose([Validators.required])],
      tentativePurchaseDate: [null, Validators.compose([Validators.required])],
      nextFollowUpDate: [null, Validators.compose([Validators.required])],
      remarks: [null, Validators.compose([Validators.required])],
    });
    return this.newEnquiryForm;

  }

  keyPress(event: any) {
    const pattern = /[a-zA-Z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  viewnewEnquiryForm() {
    this.newEnquiryForm = this.fb.group({
      enquiryNumber: this.fb.control({ value: null, disabled: true }),
      referralPersonName: this.fb.control(null),
      generationActivityNumber: this.fb.control(null),
      conversionActivityNumber: this.fb.control(null),
      validationDate: this.fb.control({ value: null, disabled: true }),
      enquiryType: this.fb.control({ value: null, disabled: true }),
      enquiryStatus: this.fb.control({ value: null, disabled: true }),
      updationDate: this.fb.control({ value: null, disabled: true }),
      enquiryDate: this.fb.control({ value: null, disabled: true }),
      salesPerson: this.fb.control({ value: null, disabled: true }),
      followUpType: this.fb.control({ value: null, disabled: true }),
      source: this.fb.control({ value: null, disabled: true }),
      purposeOfPurchase: this.fb.control({ value: null, disabled: true }),
      generationActivityType: this.fb.control(null),
      conversionActivityType: this.fb.control(null),
      retailConversionActivity: this.fb.control(null),
      tentativePurchaseDate: this.fb.control({ value: null, disabled: true }),
      nextFollowUpDate: this.fb.control({ value: null, disabled: true }),
      remarks: this.fb.control({ value: null, disabled: true }),
    });
    return this.newEnquiryForm;

  }

  viewMobilenewEnquiryForm() {
    this.newEnquiryForm = this.fb.group({
      enquiryNumber: this.fb.control({ value: null, disabled: true }),
      referralPersonName: this.fb.control({ value: null, disabled: false }),
      generationActivityNumber: this.fb.control({ value: null, disabled: false }),
     // conversionActivityNumber: this.fb.control({ value: null, disabled: false }),
      validationDate: this.fb.control({ value: null, disabled: true }),
      enquiryType: this.fb.control({ value: null, disabled: true }),
      enquiryStatus: this.fb.control({ value: null, disabled: true }),
      updationDate: this.fb.control({ value: null, disabled: true }),
      enquiryDate: this.fb.control({ value: null, disabled: true }),
     salesPerson: this.fb.control({ value: null, disabled: true }),
      followUpType: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
      source: this.fb.control({ value: null, disabled: true }),
      purposeOfPurchase: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
      generationActivityType: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
     // conversionActivityType: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
     // retailConversionActivity: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
      tentativePurchaseDate: this.fb.control({ value: null, disabled: false }),
      nextFollowUpDate: this.fb.control({ value: null, disabled: false }),
      remarks: this.fb.control({ value: null, disabled: false },Validators.compose([Validators.required])),
    });
    return this.newEnquiryForm;

  }

}
