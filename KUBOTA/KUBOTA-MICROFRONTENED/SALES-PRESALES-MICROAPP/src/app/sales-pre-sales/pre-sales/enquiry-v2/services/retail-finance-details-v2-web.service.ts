import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { map } from 'rxjs/operators';
import { CashLoan, FinalStatus, Financier } from '../domains/enquiry';

@Injectable()
export class RetailFinanceDetailsV2WebService {

  constructor(
    private http : HttpClient
  ) { }

  getCashLoan(): Observable<BaseDto<Array<CashLoan>>> {
    return this.http.get<BaseDto<Array<CashLoan>>>(EnquiryApi.getCashLoan)
  }

  getFinalStatus(): Observable<BaseDto<Array<FinalStatus>>> {
    return this.http.get<BaseDto<Array<FinalStatus>>>(EnquiryApi.getFinalStatus)
  }
  
  getFinancierList(value:string){
      return this.http.get<BaseDto<Array<Financier>>>(EnquiryApi.getFinancier, {
          params : new HttpParams().set("searchValue", value)
      })
  }
}
