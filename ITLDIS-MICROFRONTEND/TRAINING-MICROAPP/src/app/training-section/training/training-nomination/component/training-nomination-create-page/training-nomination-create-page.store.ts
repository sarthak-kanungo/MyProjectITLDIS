import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
@Injectable()
export class TrainingnominationPageStore {
  form: FormGroup;
  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      trainingnominationForm: this.trainingnominationForm(),
      nominationDetailForm: new FormArray([])
    });
  }

  private trainingnominationForm() {
    const trainingnominationForm = this.fb.group({
      nominationId:[],
      nominationNumber: [{ value: null, disabled: false }],
      nominationDate: [{ value: null, disabled: true }],
      programNumber: [{ value: null, disabled: false }, Validators.required],
      typeofTraining: [{ value: null, disabled: true }],
      trainingModule: [{ value: null, disabled: true }],
      trainingLocation: [{ value: null, disabled: true }],
      dealerCode_Name: [{ value: null, disabled: true }],
      startDate: [{ value: null, disabled: true }],
      endDate: [{ value: null, disabled: true }],
      remarks: [{ value: null, disabled: true }],
    });
    return trainingnominationForm;
  }
  public nominationDetailForm(data?) {
    const nominationDetailForm = this.fb.group({
      createdBy:[],
      createdDate:[],
      nominationDtlId:[],
      isSelect: [{ value: null, disabled: false }],
      active: [{ value: null, disabled: true }],
      status: [{ value: null, disabled: true }],
      designation: [{ value: null, disabled: false },Validators.required],
      employeeCode: [{ value: null, disabled: true }, Validators.required],
      name: [{ value: null, disabled: false }],
      attended: [{ value: false, disabled: true }],
      tShirtSize: [{value:'',disabled: false }],
      
      tShirtSizes: [{value:null,disabled: true }]
    });
    return nominationDetailForm;
  }
  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    /* this.createForm();
    return this.form as FormGroup; */
  }

}