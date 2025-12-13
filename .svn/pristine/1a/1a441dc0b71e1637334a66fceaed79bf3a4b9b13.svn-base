import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { branchTransfer } from '../url-utils/branch-transfer-reciept';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { PartReciept } from '../component/branch-transfer-reciept-page/branch-transfer-reciept-create-domain';
import { searchRecieptData } from '../component/branch-transfer-reciept-search-page/reciept-search-model';

@Injectable({
  providedIn: 'root'
})
export class BranchTransferRecieptService {

  constructor(private http:HttpClient) { }
  autoGenerateRecieptNo(issueNo:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.autoGetRecieptNo, {
      params: new HttpParams().set('issueNo', issueNo)
    }).pipe(map(res => res.result))
  }
  getSystemDate(){
    return this.http.get<BaseDto<any>>(branchTransfer.getSystemDateUrl);
  }
  

  
  getIssueingBranchName(issueId:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.getIssueingBranchName, {
      params: new HttpParams().set('issueId', issueId)
    }).pipe(map(res => res.result))
  }

  getReceivingBranch(){
    return this.http.get<BaseDto<any>>(branchTransfer.getReceivingBranch)
  }
  getRecieptDetails(issueId:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.getReceiptItemDetails, {
      params: new HttpParams().set('issueId', issueId)
    }).pipe(map(res => res.result))
  }
  saveBTReciept(partReciept: PartReciept) {
    return this.http.post<BaseDto<object>>(branchTransfer.saveBTReciept, partReciept);
  }
  searchRecieptData(recieptData: searchRecieptData): Observable<BaseDto<Array<searchRecieptData>>> {
    return this.http.post<BaseDto<Array<searchRecieptData>>>(branchTransfer.searchBTReceipt, recieptData)
  }
 
  getRecieptView(receiptNo: string): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.viewBTReceipt, {
      params: new HttpParams().append('receiptNo', receiptNo)
    }).pipe(map(res => res.result))
  }
  viewPdf(receiptId:string,receiptNo:string, printStatus:string){
    return this.http.get<Blob>(branchTransfer.printBTReceiptReport, {
        params: new HttpParams()
        .set('receiptId', receiptId)
        .set('receiptNo', receiptNo)
        .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
  autoGeneateRecieptNoSearch(receiptNo:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.autoPopulateReceiptNo, {
      params: new HttpParams().set('receiptNo', receiptNo)
    }).pipe(map(res => res.result))
  }
  autoGenerateIssueNoSearch(issueNo:any): Observable<any> {
    return this.http.get<BaseDto<any>>(branchTransfer.autoPopulateIssueNo, {
      params: new HttpParams().set('issueNo', issueNo)
    }).pipe(map(res => res.result))
  }
}
