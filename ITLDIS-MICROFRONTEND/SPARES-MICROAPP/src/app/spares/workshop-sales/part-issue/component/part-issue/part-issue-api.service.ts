import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PartIssueUrl } from '../../url-util/part-issue.url';
import { DropDownCategory } from '../../domain/part-issue.domain';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable()
export class PartIssueApiService {preSalesFlag

  constructor(private httpClient: HttpClient) { }
  getIssueType(){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.getIssueType).pipe(map(res=>res.result));
  }
  serviceCategory(): Observable<DropDownCategory[]> {
      return this.httpClient.get<BaseDto<DropDownCategory[]>>(PartIssueUrl.getDropDownCategory).pipe(map(dto => dto.result))
  }
  getRequisitionAgainstIssue(){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.getRequisitionAgainstIssue);
  }
  getIssueTo(){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.getIssueTo);
  }
  searchByDocumentForPartIssue(searchNo:string,issueType:string| SelectList){
    if (typeof issueType ==='object') {
      issueType = issueType.value as string;
    }
    return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.searchByDocumentForPartIssue,{
      params: new HttpParams()
      .set('searchNo',searchNo)
      .set('issueType',issueType)
    }).pipe(map(res=>res.result));
  }
  getPartRequisitionDetailsById(requisitionId: string, issueAgainst:string, apiId:number){
    return this.httpClient.get<BaseDto<object>>(PartIssueUrl.getPartRequisitionDetailsById,{
      params: new HttpParams()
      .set('requisitionId',`${requisitionId}`)
      .set('issueAgainst',`${issueAgainst}`)
      .set('apiId',`${apiId}`)
    }).pipe(map(res=>res.result));
  }
  searchApiNo(apiNo:string){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartIssueUrl.searchApiNo,{
      params: new HttpParams()
      .set('apiNo',`${apiNo}`)
    }).pipe(map(res=>res.result));
  }

  checkPastIssueAgainstJobcard(id:number){
    return this.httpClient.get(PartIssueUrl.checkLastIssueAgainstJobcard,{
      params : new HttpParams().set('id',id+"")
    })
  }
}
