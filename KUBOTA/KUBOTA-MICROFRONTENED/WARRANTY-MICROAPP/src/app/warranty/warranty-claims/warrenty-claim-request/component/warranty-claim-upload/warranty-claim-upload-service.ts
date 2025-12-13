import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedDataService {
  private sharedData: any;
    sharedDatas: any;
    setInvoiceData: any;
    setInvoiceDates: any;
  

  constructor() {
    
  }

  setData(data: any) {
    this.sharedData = data;
  }
  setDatas(data: any) {
    
    this.sharedDatas = data;
   
  }

  getData(): any {
    return this.sharedData;
  }
  getDatas(): any {
    return this.sharedDatas;
  }  
}

