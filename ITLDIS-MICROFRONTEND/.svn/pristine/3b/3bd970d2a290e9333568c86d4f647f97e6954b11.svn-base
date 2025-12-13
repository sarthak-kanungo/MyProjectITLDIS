import { Injectable } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { DateService } from 'src/app/root-service/date.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

import { TrainingnominationPageStore } from './training-nomination-create-page.store';


@Injectable()
export class TrainingnominationPagePresenter {
    private _operation: string;
    deletedRow = []
    disableTshirt:boolean=false;
    constructor(
        private trainingNominationStore: TrainingnominationPageStore,
        private localStorageService: LocalStorageService,
        private dateService: DateService,
        ) {}
    
      set operation(type: string) {
        this._operation = type;
      }
      get operation() {
        return this._operation;
      }
      get trainingnominationForm(): FormGroup {
        return this.trainingNominationStore.allForm.get("trainingnominationForm") as FormGroup;
      }
     
      get loginUser() {
        return this.localStorageService.getLoginUser();
      }
      get nominationDetailForm(): FormArray {
        return this.trainingNominationStore.allForm.get('nominationDetailForm') as FormArray;
    }
    patchRow(row?:any){
      const fg = this.trainingNominationStore.nominationDetailForm();
      if(row!=null)
      console.log(row.designation)
      if(row){
          fg.patchValue(row);
      }
      this.nominationDetailForm.push(fg);
      return fg;
    }
    
  deleteRow() {
    const itemDetails = this.nominationDetailForm;
    const selected = itemDetails.controls.filter(element => element.value.isSelect);
    console.log("selected ", selected);
    selected.forEach(ele => {
        console.log("ele ", ele.value);
        if (ele.value.rowId) {
            this.deletedRow.push(ele.value);
        }
    })
    console.log("this.deletedParts ", this.deletedRow);
    const nonSelected = itemDetails.controls.filter(element => !element.value.isSelect);
    console.log("nonSelected ", nonSelected);
    itemDetails.clear()
    nonSelected.forEach(el => itemDetails.push(el))
  }

  patchNomineeHeader(header){
   
    console.log('header : ',header);
    this.trainingnominationForm.get('programNumber').patchValue(header.programNumber)
    this.trainingnominationForm.get('programNumber').disable()
    this.trainingnominationForm.get('typeofTraining').patchValue(header.trainingTypeDesc)
    this.trainingnominationForm.get('trainingModule').patchValue(header.trainingModuleDesc)
    this.trainingnominationForm.get('trainingLocation').patchValue(header.trainingLocDesc)
    this.trainingnominationForm.get('dealerCode_Name').patchValue(header.dealerName)
    this.trainingnominationForm.get('startDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(header.startDate))
    this.trainingnominationForm.get('endDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(header.endDate))
    this.trainingnominationForm.get('remarks').patchValue(header.remarks)
    if(this.trainingnominationForm.controls['typeofTraining'].value==='Dealer CCE Training'){
      console.log('aa')
      this.disableTshirt=true
    }else{
      console.log('js')
      this.disableTshirt=false
    }
    console.log(this.trainingnominationForm.controls['typeofTraining'].value)
    if(header.nominationId!=null){
     this.trainingnominationForm.get('nominationId').patchValue(header.nominationId)
    }
   
    
    
  }
 
   
}