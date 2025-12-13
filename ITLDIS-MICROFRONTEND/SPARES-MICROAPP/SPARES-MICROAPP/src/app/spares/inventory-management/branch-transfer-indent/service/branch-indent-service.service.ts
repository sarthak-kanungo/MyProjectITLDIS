import { HttpClient, HttpClientModule, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';

import { map } from 'rxjs/operators';
import { branchTransfer } from '../url-utils/branch-transfer-indent-urls';
import { AutoCompleteResult, PartData, indentDeatils, indentForms, serachBranchIndent, submitForm, viewData } from '../component/domain/domaim';
import { environment } from 'src/environments/environment';
import { urlService } from 'src/app/webservice-config/baseurl';

@Injectable({
  providedIn: 'root'
})
export class BranchIndentServiceService {
  private readonly downloadTemplate = `${environment.baseUrl}${urlService.api}/common/syslookup/downloadTemplate`
  constructor(private httpClients: HttpClient) { }

  getSystemDateUrl(): Observable<string> {
    return this.httpClients.get<BaseDto<string>>(branchTransfer.getSystemDateUrl).pipe(map(res => res.result))
    
  }
  getReqToBranchDeatilsById(){
    return this.httpClients.get<BaseDto<any>>(branchTransfer.getReqToBranchDeatilsById);
}
  getAllStatus(){
   return this.httpClients.get<BaseDto<any>>(branchTransfer.getAllStatus);
 }
 getSubBranch(){
  return this.httpClients.get<BaseDto<any>>(branchTransfer.getSubBranch);
}
autoCompleteItemNumber(itemNumber: string): Observable<AutoCompleteResult> {
  return this.httpClients.get<BaseDto<AutoCompleteResult>>(branchTransfer.autoCompleteItemNumber, {
    params: new HttpParams().set('itemNumber', itemNumber)
  }).pipe(map(res => res.result))
}
getSpareItemDetails(itemNo: string,suppByBranchId:string): Observable<PartData> {
  return this.httpClients.get<BaseDto<PartData>>(branchTransfer.getSpareItemDetails, {
    params: new HttpParams().set('itemNo', itemNo).set('suppByBranchId', suppByBranchId)
  }).pipe(map(res => res.result))
}
autoGetIndentNo(indentNo:string):Observable<indentDeatils>{
  return this.httpClients.get<BaseDto<indentDeatils>>(branchTransfer.autoGetIndentNo,{
    params :new HttpParams().set('indentNo',indentNo)
  }).pipe(map(res=>res.result))
}
downloadTemplates(filename) : Observable<HttpResponse<Blob>> {
    
  return this.httpClients.get<Blob>(`${this.downloadTemplate}`, { 
      params: new HttpParams().set("filename",filename),
      observe: 'response', responseType: 'blob' as 'json' });
}
public uploadExcelPoItemDetail(uploadableFile:any){
  let formData: FormData = new FormData();
  formData.append('file', uploadableFile['file']);
  formData.append('suppByBranchId',uploadableFile['suppByBranchId'])
  formData.append('existingItems',uploadableFile['existingItems'])
  
  const headers = new HttpHeaders();
  headers.set('Content-Type', null);
  headers.set('Accept', 'multipart/form-data');

  return this.httpClients.post(branchTransfer.apiController+"/"+ "indent/excelUpload", formData, {
      headers: headers
  });
}
saveBTIndent(indentForm:submitForm ) {
  return this.httpClients.post<BaseDto<object>>(branchTransfer.saveBTIndent, indentForm);
}
searchBTIndent(indentData: serachBranchIndent): Observable<BaseDto<Array<serachBranchIndent>>> {
  return this.httpClients.post<BaseDto<Array<serachBranchIndent>>>(branchTransfer.searchBTIndent, indentData)
}
viewBTIndentByReqNo(indentNo: string): Observable<viewData> {
  return this.httpClients.get<BaseDto<viewData>>(branchTransfer.viewBTIndentByReqNo, {
    params: new HttpParams().append('indentNo', indentNo)
  }).pipe(map(res => res.result))
}
viewPdf(reqId:string,reqNo:string, printStatus:string){
  return this.httpClients.get<Blob>(branchTransfer.printBTIndentReport, {
      params: new HttpParams()
      .set('reqId', reqId)
      .set('reqNo', reqNo)
      .set('printStatus', printStatus),
     observe: 'response', responseType: 'blob' as 'json'                         
  });
}
}
