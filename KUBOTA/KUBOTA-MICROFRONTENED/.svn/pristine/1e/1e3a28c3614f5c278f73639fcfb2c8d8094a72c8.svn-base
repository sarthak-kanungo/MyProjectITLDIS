import { Injectable } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

import { CustomValidators } from '../../../../../utils/custom-validators';
import { TrainingattendanceTrainingReportPageStore } from './attendance-training-report-create-page.store';


@Injectable()
export class TrainingattendanceTrainingReportPagePresenter {
  private _operation: string;
  deletedRow = []
  selectedPhotos: File[]
  constructor(
    private TrainingattendanceTrainingReportStore: TrainingattendanceTrainingReportPageStore,
    private localStorageService: LocalStorageService
  ) { }

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }
  get TrainingattendanceTrainingReportForm(): FormGroup {
    return this.TrainingattendanceTrainingReportStore.allForm.get("TrainingattendanceTrainingReportForm") as FormGroup;
  }

  get loginUser() {
    return this.localStorageService.getLoginUser();
  }
  get attendanceSheetDetailForm(): FormArray {
    return this.TrainingattendanceTrainingReportStore.allForm.get('attendanceSheetDetailForm') as FormArray;
  }
 
}