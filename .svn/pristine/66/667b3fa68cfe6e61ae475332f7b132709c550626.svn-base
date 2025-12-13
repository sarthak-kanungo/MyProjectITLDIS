import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { CustomValidators } from '../../../../../utils/custom-validators';
@Injectable()
export class TrainingattendanceTrainingReportPageStore {
  form: FormGroup;
  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      TrainingattendanceTrainingReportForm: this.TrainingattendanceTrainingReportForm(),
      attendanceSheetDetailForm: new FormArray([])
    });
  }

  private TrainingattendanceTrainingReportForm() {
    const TrainingattendanceTrainingReportForm = this.fb.group({
      zone:[],
      region: [{ value: null, disabled: true }],
      state: [{ value: null, disabled: false }],
      tsmName: [{ value: null, disabled: false }],
      delearName: [{ value: null, disabled: false }],
      employeeStatus: [{ value: null, disabled: false }],
      typeofTraining: [{ value: null, disabled: false }],
      trainingModule: [{ value: null, disabled: false }],
      delearEmpDesignation: [{ value: null, disabled: false }],
      trainingstartDate: [{ value: null, disabled: false }],
      trainingendDate: [{ value: null, disabled: false }],
      // remarks: [{ value: null, disabled: true }],
    });
    return TrainingattendanceTrainingReportForm;
  }
  // public attendanceSheetDetailForm(data?) {
  //   const attendanceSheetDetailForm = this.fb.group({
  //     isSelected: [{ value: false, disabled: false }],
  //     dealerName: [{value: null, disabled: true}],
  //     state : [{value: null, disabled: true}],
  //     dealerLocation: [{value: null, disabled: true}],
  //     designation: [{ value: null, disabled: false }],
  //     empCode: [{ value: null, disabled: true }],
  //     name: [{ value: null, disabled: true }],
  //     day1: [{ value: null, disabled: false }],
  //     day2: [{ value: null, disabled: false }],
  //     photos:[null]
  //   });
  //   return attendanceSheetDetailForm;
  // }
  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    /* this.createForm();
    return this.form as FormGroup; */
  }

}