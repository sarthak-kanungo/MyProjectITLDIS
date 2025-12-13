import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CreateNewDealerUser } from '../../url-util/create-new-dealer-user';

@Injectable({
  providedIn: 'root'
})
export class FunctionService {

  constructor(private httpClient : HttpClient) { }

  // dealerFunction(dealerId:any,hoOrDealer: any): Observable<any> {
  //   return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.dealerRole, {
  //     params: new HttpParams().set('dealerId',dealerId).set('hoOrDealer', hoOrDealer)
  //   }).pipe(map(res=>res.result))
  // }
}
