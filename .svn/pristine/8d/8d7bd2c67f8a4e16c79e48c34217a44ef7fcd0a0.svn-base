import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { BaseDto } from "BaseDto";
import { MobileNo } from './domain/direct-survey-domain';
import { DirectSurveyApi } from './url-util/direct-survey-urls';
import { map } from "rxjs/operators";
import { CustomerCareExeCallUrls } from '../delear-customer-care-ex-call/url-util/delear-customer-care-ex-call-urls';

@Injectable()
export class DirectSurveyService {

  public fetchCustomerId:BehaviorSubject<string> = new BehaviorSubject<string>('');

  public fetchChassisNoAndCustomerId:BehaviorSubject<any> = new BehaviorSubject<any>(null);

  public fetchVinId:BehaviorSubject<number> = new BehaviorSubject<number>(null);

  public viewProfile:BehaviorSubject<Array<any>> = new BehaviorSubject<Array<any>>(null);
  public viewFactorInfluenced:BehaviorSubject<Array<any>> = new BehaviorSubject<Array<any>>(null);
  public viewCropSelected:BehaviorSubject<Array<any>> = new BehaviorSubject<Array<any>>(null);

  public viewMachine:BehaviorSubject<Array<any>> = new BehaviorSubject<Array<any>>(null);
  public viewCall:BehaviorSubject<Array<any>> = new BehaviorSubject<Array<any>>(null);


  constructor(private http: HttpClient) {}

  /* For Header */
  getSurveyType():Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getSurveyType)
  }

  getSurveyStatus():Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getSurveyStatus)
  }


  autoSoldToDealer(dealer: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DirectSurveyApi.getSoldToDealerList, {
      params: new HttpParams().append('dealerCode', dealer)
    }).pipe(map(res=>res.result))
  }

    /* For Customer Details */

  getSatisfactionLevel():Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getSatisfactionLevel)
  }

  
  getMobileNumber(mobileNumber: string): Observable<Array<MobileNo>> {
    return this.http
      .get<BaseDto<Array<MobileNo>>>(DirectSurveyApi.getMobileNumber, {
        params: new HttpParams().append("mobileNumber", mobileNumber),
      })
      .pipe(map((dto) => dto.result));
  }
  getDataByMobileNo(mobileNumber: string): Observable<any> {
    return this.http.get<BaseDto<any>>(DirectSurveyApi.getDataByMobileNo, {
        params: new HttpParams().append("mobileNumber", mobileNumber),
      })
      .pipe(map( (dto) => {if(dto!=null)return dto.result} ));
  }

  submitDirectSurvey(data : any, file:any) : Observable<any>{
    const formData: FormData = new FormData();
    const headers = new HttpHeaders();
    formData.append('surveyDto', new Blob([JSON.stringify(data)], { type: 'application/json' }))
    if (file.complaintRecording) {
      file.complaintRecording.forEach(element => {
          formData.append('complaintRecording', element['file'])
      });
    }
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.http.post<any> (DirectSurveyApi.saveDirectSurvey,formData,{headers})
  } 

  submitCallAttemptData(data : any, file:any) : Observable<any>{
    const formData: FormData = new FormData();
    const headers = new HttpHeaders();
    formData.append('callDetails', new Blob([JSON.stringify(data)], { type: 'application/json' }))
    if (file.callRecording) {
      file.callRecording.forEach(element => {
          formData.append('callRecording', element['file'])
      });
    }
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.http.post<any> (DirectSurveyApi.submitCallAttempt,formData,{headers})
  }

  viewEditSurvey(surveyNo:string){
    // return this.http.get<Array<any>>(DirectSurveyApi.getViewEditData, {
    //   params: new HttpParams().set('surveyNo',surveyNo)
    // })
    return this.http.get(DirectSurveyApi.getViewEditData, {
      params : new HttpParams().set("surveyNo",surveyNo)
    })
  }

  getMachineDetails(customerId:any,chassisNo:any){
    return this.http.get(DirectSurveyApi.getSurveyMachineDetails, {
      params : new HttpParams().set("customerId",customerId).set('chassisNo',chassisNo)
    })
  }

  getServiceHistory(customerId:any, vinId:any){
    return this.http.get(CustomerCareExeCallUrls.getCustomerServiceHistory, {
      params : new HttpParams().set("customerId",customerId).set('vinId',vinId)
    })
  }

  getDirectSurveyDetails(surveyType:string,vinId:any,customerId:any){
    return this.http.get(DirectSurveyApi.getDirectSurveyDetails, {
      params : new HttpParams().set("surveyType",surveyType).set("vinId",vinId).set("customerId",customerId)
    })
  }

  getLookupByCode(code){
    return this.http.get(CustomerCareExeCallUrls.lookupurl, {
      params : new HttpParams().set("code",code)
    })
  }

  getManuFacturer():Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getManuFacturer)
  }

  getMajorCropsGrown():Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getMajorCropGrown)
  }


  /* For Customer Details -- Start */
  getSurveyQuestion(surveyMstId,surveyTypeId,vinId):Observable<Array<any>>{
      return this.http.get<Array<any>>(DirectSurveyApi.getSurveyQuestion,{
        params: new HttpParams().set('surveyMstId',surveyMstId).set('surveyTypeId',surveyTypeId).set('vinId',vinId)
      })
  }

  getAgeOfMachine(vinId:any):Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getAgeOfMachine,{
      params: new HttpParams().set('vinId',vinId)
    })
  }

  getMeterHours(vinId:any):Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getMeterHours,{
      params: new HttpParams().set('vinId',vinId)
    })
  }

  getSurveyAnswer(questionId:any):Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getSurveyAnswer,{
      params: new HttpParams().set('questionId',questionId)
    })
  }

  getSubAnswer(ansId:any):Observable<Array<any>>{
    return this.http.get<Array<any>>(DirectSurveyApi.getSubAnswer, {
      params: new HttpParams().set('ansId',ansId)
    })
  }

  /* end */

  villageAuto(village: string,state:string, district:string): Observable<any> {
    return this.http.get<BaseDto<any>>(DirectSurveyApi.getVillageAuto, {
      params: new HttpParams().append('village', village).set('state',state).set('district',district)
    }).pipe(map(res=>res.result))
  }


  /* JC view */

  viewJobCard(jobCardNo: any){
    return this.http.get(DirectSurveyApi.viewJobCard, {
      params : new HttpParams().set("jobCardNo",jobCardNo)
    })
  }

   /* PCR view */
   viewPCR(pcrNo: any){
    return this.http.get(DirectSurveyApi.viewPcr, {
      params : new HttpParams().set("pcrNo",pcrNo)
    })
  }

  viewPCRHistory(id: any){
    return this.http.get(`${DirectSurveyApi.serviceHistory}/${id}`)
  }

    /* WCR view */

    viewWCR(wcrNo: any){
      return this.http.get(DirectSurveyApi.viewWcr, {
        params : new HttpParams().set("wcrNo",wcrNo)
      })
    }

    viewWCRHistory(id: any){
      return this.http.get(`${DirectSurveyApi.wcrServiceHistory}/${id}`)
    }

    getCallHistory(id: any): Observable<any> {
      return this.http.get<BaseDto<any>>(DirectSurveyApi.getCallHistory, {
        params: new HttpParams().append('reminderId', id)
      }).pipe(map(res=>res.result))
    }

    public updateRecordingFile(updatedata:any,file:any){
      let formData: FormData = new FormData();

      formData.append('updateFile', new Blob([JSON.stringify(updatedata)], { type: 'application/json' }))
      // formData.append('callAttemptId', updatedata['callAttemptId']);
      // formData.append('surveyRecordingId', updatedata['surveyRecordingId']);
      formData.append('updateRecording', file);
      
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');
      return this.http.post<any> (DirectSurveyApi.updateCallrecording,formData,{headers})
    }

}
