import { Injectable } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

import { CustomValidators } from '../../../../../utils/custom-validators';
import { TrainingattendanceSheetPageStore } from './attendance-sheet-create-page.store';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';


@Injectable()
export class TrainingattendanceSheetPagePresenter {
  private _operation: string;
  deletedRow = []
  aomunt:any
  count:any
  collectFiles: UploadableFile[];
  constructor(
    private trainingattendanceSheetStore: TrainingattendanceSheetPageStore,
    private localStorageService: LocalStorageService
  ) { }

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    
    return this._operation;
  }
  get allForm(): FormGroup{
    return this.trainingattendanceSheetStore.allForm
  }
  get trainingattendanceSheetForm(): FormGroup {
    return this.trainingattendanceSheetStore.allForm.get("trainingattendanceSheetForm") as FormGroup;
  }

  get loginUser() {
    return this.localStorageService.getLoginUser();
  }
  get attendanceDtl(): FormArray {
    return this.trainingattendanceSheetStore.allForm.get('attendanceDtl') as FormArray;
  }
  get attendanceSheetDays(): FormArray {
    return this.trainingattendanceSheetStore.allForm.get('attendanceSheetDays') as FormArray;
  }
  
  patchAttance(row?:any){
    const fg= this.trainingattendanceSheetStore.attendanceSheetDetailForm()
    if (row) {
      fg.patchValue(row);
    }
    this.attendanceDtl.push(fg);
    return fg;
  }
 
  patchRow(row?: any) {
    const fg= this.trainingattendanceSheetStore.allForm.get('attendanceSheetDays')  as FormArray
    if (row) {
      fg.patchValue(row);
    }
    this.attendanceSheetDays.push(fg);
  }
  
  collectPCRFiles(files: UploadableFile[]) {
    this.collectFiles = files;
  }


  calculateAverageIndex(){
  
    const attendenceDtl = this.trainingattendanceSheetStore.allForm.get('attendanceDtl') as FormArray;
let amount = 0;
let counts=0;
attendenceDtl.controls.forEach(count=>{
 const preTestValue=count.get('preTest').value;
 const postTestValue=count.get('postTest').value;
 if(preTestValue!==null && preTestValue!==''){

   counts++

 }
 
})
// console.log(counts,'counts')
attendenceDtl.controls.forEach(element => {
  const growthIndexControl = element.get('growthIndex');
  const postTestControl = element.get('postTest');

  if (postTestControl.value !== null) {
    // Calculate growthIndex only if postTest is not null
    const growthIndexValue = parseFloat(growthIndexControl.value) || 0;
    // amount += growthIndexValue;
    amount = (amount + growthIndexValue);
  } else {
    // If postTest becomes null, reset growthIndex to 0
    growthIndexControl.setValue(0);
  }
});
 this.aomunt = amount / counts;
// console.log(this.aomunt,)

 

  }
}