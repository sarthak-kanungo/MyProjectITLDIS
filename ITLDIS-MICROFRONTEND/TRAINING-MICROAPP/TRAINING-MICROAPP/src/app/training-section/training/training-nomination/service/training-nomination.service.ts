import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TrainingNomineeApi } from '../url-utils/training-nomination-url';
import { BaseDto } from 'BaseDto';
import { NominationSearchHeader, TncSeachList } from '../domain/training-nomination.domain';
import { TpcSeachList } from '../../training-programme-calender/domain/tpc-domain';
@Injectable({
  providedIn: 'root'
})
export class TrainingnominationService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  getProgramNomineeProgDtl(programeNo:string){
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getProgramNomineeProgDtl,{
      params: new HttpParams().set('programeNo', programeNo)
    })
  }
  getProgramNomineeProgHdr(programeNo:string){
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getProgramNomineeProgHdr,{
      params: new HttpParams().set('programeNo', programeNo)
    })
  }

  saveNominationForm(nomination :any) : Observable<any>{
    return this.httpClient.post<any> (TrainingNomineeApi.saveNomination,nomination)
  } 
  updateNominationForm(updateNomination :any) : Observable<any>{
    return this.httpClient.post<any> (TrainingNomineeApi.updateNomination,updateNomination)
  } 

  nomineeDesignation(designation:string):Observable<Array<any>>{
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getNomineeDesignation,{
      params:new HttpParams().set('designation',designation)
    })
  }

  getAutoEmpName(designationId:any, searchName:string, typeofTraining:string, trainingModule:string):Observable<any>{
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getAutoEmpName,{
      params: new HttpParams().set('designationId', designationId).set('searchName',searchName)
        .set('typeofTraining',typeofTraining).set('trainingModule', trainingModule,)
    })
  }

  getNomineeEmpDetails(programId:any,empId:any){
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getNomineeEmpDetails,{
      params: new HttpParams().set('programId', programId).set('employeeId', empId)
    })
  }

  
  getAutoNominationNo(nominationNo:string){
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getAutoNominationNo,{
      params: new HttpParams().set('nominationNo', nominationNo)
    })
  }

  getDataforViewEdit(programId:any){
    return this.httpClient.get<Array<any>>(TrainingNomineeApi.getViewEditData,{
      params: new HttpParams().set('programId', programId)
    })
  }

  public tncSearch(searchNominee: NominationSearchHeader): Observable<BaseDto<Array<TncSeachList>>> {
    return this.httpClient.post<BaseDto<Array<TpcSeachList>>>(TrainingNomineeApi.nomineeSearch, searchNominee)
  }

}
