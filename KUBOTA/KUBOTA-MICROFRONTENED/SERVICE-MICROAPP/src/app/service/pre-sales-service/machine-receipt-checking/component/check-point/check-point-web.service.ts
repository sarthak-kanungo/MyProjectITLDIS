import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { Tab, MrcCheckPoint } from '../../domain/machine-receipt-checking.domain';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { FormGroup, FormArray, FormBuilder } from '@angular/forms';
import * as uuid from 'uuid';
import { map } from 'rxjs/operators';

@Injectable()
export class CheckPointWebService {
  checkPointTableForm: FormGroup;
  private materialTableFormArray: FormArray;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient) { }

  getMCRCheckPoint(chassis:string): Observable<Array<MrcCheckPoint>> {
    return this.httpClient.get<BaseDto<Array<MrcCheckPoint>>>(MrcUrl.getCheckPoint,
            { params : new HttpParams()
                .append('transType', 'MRC')
                .append('chassis', chassis)
            }).pipe(map(dto=>dto.result))
  }
  createTableForm() {
    this.checkPointTableForm = this.fb.group({
      table: this.createCheckPointTable()
    });
    this.materialTableFormArray = this.checkPointTableForm.get('table') as FormArray;
    return this.checkPointTableForm;
  }
  createCheckPointTable() {
    return this.fb.group([])
  }
  createTableRow(data?: MrcCheckPoint): FormGroup {
    const fg = this.fb.group(this.getControlsConfigForTableFormArray(data));
    return fg
  }
  getControlsConfigForTableFormArray(data = null) {
    console.log(data)
    return {
      defaultTick: [{ value: data ? data.defaultTick : null, disabled: false }],
      checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: true }],
      aggregate: [{ value: data ? data.aggregate : null, disabled: true }],
      checkpointDesc: [{ value: data ? data.checkpointDesc : null, disabled: true }],
      specification: [{ value: data ? data.specification : null, disabled: true }],
      aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: true }],
      remarks: [{ value: data ? data.remarks : null, disabled: false }],
    }
  }
}
