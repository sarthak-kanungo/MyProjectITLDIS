import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { TrainingApi } from '../url-utils/training-prog-calender-url';
import { TpcHeader, TpcSeachList, TpcSearchHeader } from '../domain/tpc-domain';
@Injectable({
  providedIn: 'root'
})
export class TrainingProgCalenderService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  getProgramLocation(type:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getProgramLocation,{
      params: new HttpParams().append('type',type)
    })                       ;
  }

  getTrainingType(departmentName:any, type:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getTrainingType,{
      params: new HttpParams().set('departmentName',departmentName).set('type',type)
    })                       ;
  }

  
  getTrainingModule(trainingTypeId:any, type:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getTrainingModule,{
      params: new HttpParams().set('trainingTypeId',trainingTypeId).set('type',type)
    })                       ;
  }

  getStates(countryId:any, stateName:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getStates,{
      params: new HttpParams().set('countryId',countryId).set('stateName',stateName)
    })                       ;
  }

  getDealerNames(stateNames:string):Observable<any>{
    return this.httpClient.get<any>(TrainingApi.getDealersName,{
      params: new HttpParams().set('allStateName',stateNames)
    })                       ;
  }

  saveTrainingProgramCalendar(header :any) : Observable<any>{
    return this.httpClient.post<any> (TrainingApi.saveTrainingProgramCalendar,header)
  } 

  public tpcSearch(searchTpc: TpcSearchHeader): Observable<BaseDto<Array<TpcSeachList>>> {
    return this.httpClient.post<BaseDto<Array<TpcSeachList>>>(TrainingApi.tpcSearch, searchTpc)
  }


  getDataforViewEdit(programId:any){
    return this.httpClient.get<Array<any>>(TrainingApi.getViewEditData,{
      params: new HttpParams().set('programId', programId)
    })
  }

  updateTrainingProgramCalendar(update :any) : Observable<any>{
    return this.httpClient.post<any> (TrainingApi.updateTrainingProgramCalendar,update)
  } 

  nomineesApproval(nomineesApproval :any) : Observable<any>{
    return this.httpClient.post<any> (TrainingApi.getNomineesApproval,nomineesApproval)
  } 

  getAutoProgramNo(programeNo:string,departmentName:any){
    return this.httpClient.get<Array<any>>(TrainingApi.getProgramNo,{
      params: new HttpParams().set('programeNo', programeNo).set('departmentName',departmentName)
    })
  }
  // getTrainingType(departmentName:any, type:string):Observable<any>{
  //   return this.httpClient.get<any>(TrainingApi.getTrainingType,{
  //     params: new HttpParams().set('departmentName',departmentName).set('type',type)
  //   })                       ;
  // }


  getNominationDetails(programeNo:any){
    return this.httpClient.get<Array<any>>(TrainingApi.getNomineeDetails,{
      params: new HttpParams().set('programeNo', programeNo)
    })
  }


}
