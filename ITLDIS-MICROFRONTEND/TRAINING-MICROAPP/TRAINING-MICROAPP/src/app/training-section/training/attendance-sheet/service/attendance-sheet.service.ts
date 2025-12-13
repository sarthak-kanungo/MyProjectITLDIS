import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TrainingApi } from '../url-utils/attendance-sheet-url';
import { TrainingASApi } from '../url-utils/attendance-sheet-exclude-url';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { AttendanceHeader, AttendanceHeaders } from '../domain/attendance-sheet.domain';
import { elementAt } from 'rxjs-compat/operator/elementAt';
@Injectable({
  providedIn: 'root'
})
export class TrainingattendanceSheetService {
  private fileList: Array<UploadableFile> = new Array()
  constructor(
    private httpClient: HttpClient,
  ) { }

  getDepartment():Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getempMasterdepartmentForOrgHier)                         ;
  }

  getattendanceDataforViewEdit(programId:any): Observable<any>{
    return this.httpClient.get<Array<any>>(TrainingASApi.getViewEditData,{
      params: new HttpParams().set('programId', programId)
    })
  }


  downloadCertificate(data:any):Observable<any>{
   return this.httpClient.post(TrainingASApi.downloadTrainingCertificate, data, {
      observe: 'response',
      responseType: 'blob' as 'json'
    })
    // return this.httpClient.post<Array<any>>(TrainingASApi.downloadTrainingCertificate,data)
  }

 
  getProgramNomineeProgDtl(programId:any){
    return this.httpClient.get<Array<any>>(TrainingASApi.getProgramAndNomineeDtl,{
      params: new HttpParams().set('programId', programId)
    })
  }
  getAttendanceTrainers(departmentName:any){
    return this.httpClient.get<Array<any>>(TrainingASApi.getAttendanceTrainers,{
      params: new HttpParams().set('departmentName', departmentName)
    })
  }
  submitNomineAttendanceSheet(HtrData:AttendanceHeaders, file: any) {

    let formData: FormData = new FormData();
    const htrDataCopy = { ...HtrData };
    if(htrDataCopy.multipartFileList){
      delete htrDataCopy.multipartFileList[0].previewUrl;
      delete htrDataCopy.multipartFileList[0].id
    }
    formData.append('tnAttendanceSheet', new Blob([JSON.stringify(htrDataCopy)], { type: 'application/json' }))
if (HtrData.multipartFileList) {
  HtrData.multipartFileList.forEach(element => {
    formData.append('tnAttendanceSheetDocs', element['file']);
  });
}

return this.httpClient.post(TrainingASApi.saveNomineAttendanceSheet, formData)
  }
  
  attendaceSheetSearch(data:any){
    return this.httpClient.post<any> (TrainingASApi.attendaceSheetSearch,data)
  }
  deleteFile(id: string) {
    this.fileList = this.fileList.filter(file => file.id !== id)
  }
  listUploadableFileslist() {
    return this.fileList
  }

  getNomineeAutoProgramNo(programeNo:string){
    return this.httpClient.get<Array<any>>(TrainingASApi.getProgramNoForNominee,{
      params: new HttpParams().set('programeNo', programeNo)
    })
  }

  updateNomineAttendanceSheet(data : any, file:any){
    const formData: FormData = new FormData();
    const headers = new HttpHeaders();
    formData.append('tnAttendanceSheetUpdate', new Blob([JSON.stringify(data)], { type: 'application/json' }))
    if (file.photos) {
      file.photos.forEach(element => {
          formData.append('tnAttendanceSheetDocs', element['file'])
      });
    }
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.httpClient.post<any> (TrainingASApi.updateNomineAttendanceSheet,formData,{headers})
  }
  
}
