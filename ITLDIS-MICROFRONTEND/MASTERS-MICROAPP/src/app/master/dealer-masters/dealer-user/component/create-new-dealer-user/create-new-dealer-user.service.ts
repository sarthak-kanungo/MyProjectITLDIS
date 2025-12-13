import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SubmitNewDealerDto } from '../../domain/create-dealer-user-domain';
import { CreateNewDealerUser } from '../../url-util/create-new-dealer-user';

@Injectable({
  providedIn: 'root'
})
export class CreateNewDealerUserService {

  constructor(private httpClient : HttpClient) { }

  dealerAuto(dealer: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.getDealerDealer, {
      params: new HttpParams().append('dealerCode', dealer)
    }).pipe(map(res=>res.result))
  }

  autoEmployeeCode(employeeCode: string,dealerId:any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.employeeCodeForNewDealer, {
      params: new HttpParams().set('employeeCode', employeeCode).set('dealerId',dealerId)
    }).pipe(map(res=>res.result))
  }

  submitCreateNewUser(formData : SubmitNewDealerDto) : Observable<SubmitNewDealerDto>{
    return this.httpClient.post<SubmitNewDealerDto> (CreateNewDealerUser.submitCreateNewUserForm,formData)
  } 

  updateDealerUser(formData : SubmitNewDealerDto) : Observable<SubmitNewDealerDto>{
    return this.httpClient.post<SubmitNewDealerDto> (CreateNewDealerUser.updateDealerUser,formData)
  } 
  dealerFunction(dealerId:any,hoOrDealer: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.dealerRole, {
      params: new HttpParams().set('dealerId',dealerId).set('hoOrDealer', hoOrDealer)
    }).pipe(map(res=>res.result))
  }


  tocheckDuplicateUser(userId: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.chechDuplicateUserId, {
      params: new HttpParams().append('userName', userId)
    }).pipe(map(res=>res.result))
  }

  viewdealerDetails(empId: string): Observable<SubmitNewDealerDto> {
    return this.httpClient.get<BaseDto<SubmitNewDealerDto>>(CreateNewDealerUser.viewDealerDetails, {
      params: new HttpParams().set('empId', empId)
    }).pipe(map(dto => dto.result))
  }

  
}
