import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { branchTransfer } from '../url-utils/branch-transfer-issue-urls';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { PartIssue } from 'src/app/spares/workshop-sales/part-issue/domain/part-issue.domain';
import { searchBranchTransferIssue } from '../component/branch-transfer-issue-page/branch-transfer-domain';

@Injectable({
  providedIn: 'root'
})
export class BranchTransferIssueServiceService {

  constructor(private http:HttpClient) { 
    
  }
  getIssueToBranch(){
    return this.http.get<BaseDto<any>>(branchTransfer.getissueToBranchDeatils);
  }

  getIssueingBranch(){
    return this.http.get<BaseDto<any>>(branchTransfer.getIssueingBranch);
  }

  getSystemDate(){
    return this.http.get<BaseDto<any>>(branchTransfer.getSystemDateUrl);
  }
  getIndentNos(reqFromBranchId:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.getIndentNos, {
      params: new HttpParams().set('reqFromBranchId', reqFromBranchId)
    }).pipe(map(res => res.result))
  }

  getIndentItemsDetailsByIds(indentIds:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.getIndentItemsDetailsByIds, {
      params: new HttpParams().set('indentIds', indentIds)
    }).pipe(map(res => res.result))
  }
  saveBTIssue(partIssue: PartIssue) {
    return this.http.post<BaseDto<object>>(branchTransfer.saveBTIssue, partIssue);
  }
  getIssueNumberForView(issueNo: string): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.viewBTIssue, {
      params: new HttpParams().append('issueNo', issueNo)
    }).pipe(map(res => res.result))
  }
  searchBTIssue(issueData: searchBranchTransferIssue): Observable<BaseDto<Array<searchBranchTransferIssue>>> {
    return this.http.post<BaseDto<Array<searchBranchTransferIssue>>>(branchTransfer.searchBTIssue, issueData)
  }
  autoGenIssueNumber(issueNo:string):Observable<any>{
    return this.http.get<BaseDto<any>>(branchTransfer.autoGetIssueNo,{
      params :new HttpParams().set('issueNo',issueNo)
    }).pipe(map(res=>res.result))
  }
  
  viewPdf(issueId:string,issueNo:string, printStatus:string){
    return this.http.get<Blob>(branchTransfer.printBTIssueReport, {
        params: new HttpParams()
        .set('issueId', issueId)
        .set('issueNo', issueNo)
        .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
}

