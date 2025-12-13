import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class invoiceVerifyService {
  private sharedData: any;
    sharedDatas: any;
  

  constructor(
    
  ) {
    
    
  }





  private sharedDataSubject = new BehaviorSubject<any>(null);
  sharedData$ = this.sharedDataSubject.asObservable();

  private sharedDatasSubject = new BehaviorSubject<any>(null);
  sharedDatas$ = this.sharedDatasSubject.asObservable();

  setInvoiceNo(data: any) {
   
    this.sharedDataSubject.next(data);
  }

  setInvoiceDate(data: any) {
    this.sharedDatasSubject.next(data);
  }

  getInvoiceNo(): any {
    return this.sharedDataSubject.getValue();
  }

  getInvoiceDate(): any {
    return this.sharedDatasSubject.getValue();
  }


  
}

