import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { RetailFinanceObj } from 'RetailFinance';


@Injectable()
export class RetailFinanceContainerService {
  private readonly cashLoanURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getCashLoan}`;
  private readonly finalStatusURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getFinalStatus}`;
  retailFinanceObj = {} as RetailFinanceObj
  constructor(
    private http: HttpClient
  ) { }

  dropdowncshLoan(){
    return this.http.get(`${this.cashLoanURL}`)
  }

  dropdownfinalStatus(){
    return this.http.get(`${this.finalStatusURL}`)
  }

}
