import { Injectable } from '@angular/core';
import { Observable, Subject, Subscriber } from 'rxjs';
import { ViewQuotationDTO } from './create-quotation';

@Injectable()
export class QuotationCreateStoreService {
  private getInputValuePromise = {} as Promise<object>;
  private subject$ = new Subject<ViewQuotationDTO>();
  private resetForm$ = new Subject<boolean>();
  constructor() { }

  createPromiseOfQuotionaRecord(value: ViewQuotationDTO, error?) {
    console.log('createPromiseOfQuotionaRecord!!!!!!!!!!!!');
    this.getInputValuePromise = new Promise<object>(function (resolve, reject) {
      if (Object.keys(value).length > 0) {
        resolve(value);
        return;
      }
      if (!!error) {
        reject(error);
      }
    });
    this.subject$.next(value);
  }
  getQuotionaRecordForView() {
    console.log('getQuotionaRecordForView!!!!!!!!!!');
    return new Observable<ViewQuotationDTO>((subscriber) => {
      try {
        let subscription = this.subject$.subscribe(res => {
          subscriber.next(res);
          subscriber.complete();
          subscription.unsubscribe();
        }, (rejectVal) => {
          subscriber.error(rejectVal);
          subscription.unsubscribe();
        });
        return;
      } catch (error) {
        console.log('In error block of observable!!!!!!!!!!!!');
        subscriber.error(error);
      }
    });
  }
  resetForm() {
    this.resetForm$.next(true);
  }
  resetAll() {
    return this.resetForm$.asObservable()
  }
}
