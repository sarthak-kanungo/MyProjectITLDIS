import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { CustomValidators } from '../../../../../utils/custom-validators';
@Injectable()
export class TrainingattendanceSheetPageStore {
  form: FormGroup;
  private _operation: string;
  
  constructor(
    private fb: FormBuilder,

    ) {
       
    this.form = this.fb.group({
      trainingattendanceSheetForm: this.trainingattendanceSheetForm(),
      attendanceDtl: this.fb.array([]),
      attendanceSheetDays: new FormArray([])
    });
  }
  
  private trainingattendanceSheetForm() {
    const trainingattendanceSheetForm = this.fb.group({
      programNumber: [{ value: null, disabled: false }],
      programDate: [{ value: null, disabled: true }],
      typeofTraining: [{ value: null, disabled: true }],
      trainingModule: [{ value: null, disabled: true }],
      trainer1: [{ value: null, disabled: false }, Validators.required],
      trainer2: [{ value: null, disabled: false }, Validators.required],
      trainingLocation: [{ value: null, disabled: true }],
      dealerLocation: [{ value: null, disabled: true }],
      chassisNo: [{ value: null, disabled: false }],
      startDate: [{ value: null, disabled: true }],
      endDate: [{ value: null, disabled: true }],
      remarks: [{ value: null, disabled: true }],
    });
    return trainingattendanceSheetForm;
  }
  public attendanceSheetDetailForm(data?) {
    
    const attendanceDtl = this.fb.group({
      isSelected: [{ value: false, disabled: false }],
      dealerName: [{ value: null, disabled: false }],
      state: [{ value: null, disabled: false }],
      location: [{ value: null, disabled: false }],
      employeeCode: [{ value: null, disabled: false }],
      employeeName: [{ value: null, disabled: false }],
      photos: [{value:null,disabled:false}],
      designation: [{ value: null, disabled: false }],
      days: [],
      programNominationDTLId: null,
      trainingDate:[],
      nomineeAttendanceDtlId:[],
      preTest:[{value:null,disabled:false}],
      postTest:[{value:null,disabled:false}],
      growthIndex:[{value:null,disabled:true}],
      avgGrowthIndex:[{value:null,disabled:true}],
      dealerId:[{value:null,disabled:true}],
      employeeID:[{value:null,disabled:true}],
      
    });
    return attendanceDtl;
  }

  public attendanceSheetDays(data?) {
    const attendanceSheetDays = this.fb.group({
      day: []
    })
    return attendanceSheetDays
  }
  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    /* this.createForm();
    return this.form as FormGroup; */
  }

}