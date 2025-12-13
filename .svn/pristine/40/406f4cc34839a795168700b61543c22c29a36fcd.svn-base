import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { CustomValidators } from '../../../../../utils/custom-validators';
@Injectable()
export class TrainingProgrammeCalenderPageStore {
    form: FormGroup;
    constructor(private fb: FormBuilder) {
        this.form = this.fb.group({
          trainingProgCalenderForm: this.trainingProgCalenderForm(),
          nominationForm: new FormArray([])
        });
      }
    
      private trainingProgCalenderForm() {
        const tpcForm = this.fb.group({
          programNumber: [{ value: null, disabled: false }],
          department: [{ value: null, disabled: true }],
          programDate: [{ value: null, disabled: true }],
          programLocation: [{ value: null, disabled: false }],
          location: [{ value: null, disabled: true }],
          typeofTraining: [{ value: null, disabled: false }, Validators.required],
          trainingModule: [{ value: null, disabled: false }, Validators.required],
          applicableStates: [{ value: null, disabled: false }],
          dealerName: [{ value: null, disabled: false }],
          lastNominationDate: [{ value: null, disabled: false }, Validators.required],
          startDate: [{ value: null, disabled: false }, Validators.required],
          endDate: [{ value: null, disabled: false }, Validators.required],
          holidayDate: [{ value: null, disabled: false }],
          noNominees: [{ value: null, disabled: false }, Validators.compose([CustomValidators.numberOnly,Validators.required])],
          remarks: [{ value: null, disabled: false }],
          maxNoOfNominees: [{ value: null, disabled: false }, Validators.compose([CustomValidators.numberOnly,Validators.required])]
        });
        return tpcForm;
      }
      public nominationDetailForm() {
        const implementForm = this.fb.group({
            programNominationDTLId:[],
            nominationNo: [{value: null, disabled: true}],
            nominationDate: [{value: null, disabled: true}],
            dealerName: [{value: null, disabled: true}],
            state : [{value: null, disabled: true}],
            dealerLocation: [{value: null, disabled: true}],
            designation: [{value: null, disabled: true}],
            empCode: [{value: null, disabled: true}],
            name: [{value: null, disabled: true}],
            empContactNumber: [{value: null, disabled: true}],
            confirmNomination: [{value: null, disabled: false}, Validators.required],
            attended: [{value: null, disabled: false}],
            tShirtSize: [{value: null, disabled: true}],
            postTest: [{value: null, disabled: false}]
        });
        return implementForm;
    }
      get allForm() {
        if (this.form) {
          return this.form as FormGroup;
        }
        /* this.createForm();
        return this.form as FormGroup; */
      }
   
}