import { Injectable, EventEmitter } from '@angular/core';
import { Subject, Observable, Subscriber } from 'rxjs';
import { ColumnRecord } from './editable-table-config';

@Injectable({
  providedIn: 'root'
})
export class EtDataHandlerService {

  private sendColumnNameRecord: Subscriber<string>;
  public sendColumnNameRecord$ = new Subject<string>();
  public sendColumnRecord$ = new Subject<ColumnRecord>();
  private validateTable$ = new EventEmitter<boolean>();
  private getTableRecord$ = new EventEmitter<object>();
  private getColumnNameToCollectColumnRecord$ = new Observable<string>(subscriber => {
    this.sendColumnNameRecord = subscriber;
  });
  private sendColumnRecord: Subscriber<any>;
  constructor() { }

  getColumnRecord(formControlName: string) {
    // console.log('formControlName', formControlName);
    this.sendColumnNameRecord$.next(formControlName);
    // return this.sendColumnRecord$;
  }
  getColumnNameToCollectColumnRecord() {
    return new Observable<string>(subscriber => {
      this.sendColumnNameRecord = subscriber;
    });
  }
  emitColumnRecord(columnRecordList: Array<any>, formControlName: string) {
    // console.log('columnRecordList', columnRecordList);
    setTimeout(() => {
      this.sendColumnRecord$.next({ recordList: columnRecordList, formControlName });
    }, 1);
  }
  validateTable() {
    return this.validateTable$;
  }
  getValidTableRecord(): EventEmitter<object> {
    this.validateTable$.emit(true);
    return this.getTableRecord$;
  }
  sendTableRecord(record: object) {
    setTimeout(() => {
      this.getTableRecord$.emit(record);
    }, 100);
  }
}
