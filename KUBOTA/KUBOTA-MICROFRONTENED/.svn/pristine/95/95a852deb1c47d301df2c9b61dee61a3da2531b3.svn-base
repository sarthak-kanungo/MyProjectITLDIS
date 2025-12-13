import { Injectable } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

import { CustomValidators } from '../../../../../utils/custom-validators';
import { TrainingProgrammeCalenderPageStore } from './training-prog-calender-create-page.store';


@Injectable()
export class TrainingProgrammeCalenderPagePresenter {
    private _operation: string;
    constructor(
        private tpcStore: TrainingProgrammeCalenderPageStore,
        private localStorageService: LocalStorageService
        ) {}
    
      set operation(type: string) {
        this._operation = type;
      }
      get operation() {
        return this._operation;
      }
      get tpcForm(): FormGroup {
        return this.tpcStore.allForm.get("trainingProgCalenderForm") as FormGroup;
      }
     
      get loginUser() {
        return this.localStorageService.getLoginUser();
      }
      get nominationForm(): FormArray {
        return this.tpcStore.allForm.get('nominationForm') as FormArray;
    }
    patchRow(row?:any){
      const fg = this.tpcStore.nominationDetailForm();
      // const subQues=fg.get('subQuest') as FormArray
      // subQues.push(this.pageStore.buildSubQuestionArray())
      if(row){
          fg.patchValue(row);
      }
      this.nominationForm.push(fg);
      return fg;
  }
   
}