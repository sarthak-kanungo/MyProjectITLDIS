import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserToBranch } from '../../domain/assign-user-to-branch-domain';
import { AssigneUserToBranchUrl } from '../../url-util/assign-user-to-branch-url';

@Injectable({
  providedIn: 'root'
})
export class CreateNewAssignUserToBranchService {

  constructor(private httpClient: HttpClient) { }


  dealerToAssignBranch(dealervalue: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(AssigneUserToBranchUrl.getDealerToAssignBranch, {
      params: new HttpParams().append('dealervalue', dealervalue)
    }).pipe(map(res=>res.result))
  }
  
  userToAssignBranch(userIdVal: string,dealerId:any,isFor:string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(AssigneUserToBranchUrl.getUserIdToAssignBranch, {
      params: new HttpParams().set('userIdVal', userIdVal).set('dealerId',dealerId).set('isFor',isFor)
    }).pipe(map(res=>res.result))
  }

  getUserBranch(dealerId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(AssigneUserToBranchUrl.getBranchToAssignUser, {
      params: new HttpParams().set('dealerId', dealerId)
    }).pipe(map(res=>res.result))
  }

  submitBranchToUser(formData :any) : Observable<UserToBranch>{
    return this.httpClient.post<UserToBranch> (AssigneUserToBranchUrl.submitBranchToUser,formData)
  } 

  updateUserBranchAssignment(formData :any) : Observable<UserToBranch>{
    return this.httpClient.post<UserToBranch> (AssigneUserToBranchUrl.updateUserBranchAssignment,formData)
  } 

  getDealerIdAndName(empId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(AssigneUserToBranchUrl.getDealerIdAndName, {
      params: new HttpParams().set('empId', empId)
    }).pipe(map(res=>res.result))
  }
}
