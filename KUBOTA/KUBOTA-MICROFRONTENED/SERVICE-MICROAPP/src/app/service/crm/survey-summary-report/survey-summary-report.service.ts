import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { SurveySummaryReportApi } from './url-util/survey-summary-report-urls';
import { SearchPartyResponse } from './domain/survey-summary-report-domain';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class SurveySummaryReportService {
  clearFormSubject:BehaviorSubject<string> = new BehaviorSubject<string>('');
  private readonly getModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getModel}`;
  private readonly getSubModelUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSubModel}`;
  private readonly getDealer = `${environment.baseUrl}/${environment.api}/createUser/getDealer`;
  constructor(private httpClient : HttpClient) { }
    

  searchSurveySummaryReport(searchDetail): Observable<BaseDto<Array<SearchPartyResponse>>> {
    return this.httpClient.post<BaseDto<Array<SearchPartyResponse>>>(SurveySummaryReportApi.searchSurveySurmmaryReport, searchDetail)
  }
  getSurveyStatus(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.surveyStatus)
  }
  getSurveySatisfaction(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.surveySatisfaction)
  }
  getSurveyDate(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.surveyDate)
  }
  getSurveyName(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.surveyName)
  }
  // getAllocatedTerritory(): Observable<BaseDto<Array<any>>> {
  //   return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.allocatedTerritory)
  // }
  // areaLevelDropdown(): Observable<BaseDto<Array<any>>> {
  //   return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.areaLevelDropdown)
  // }
  dealerAuto(dealer: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(this.getDealer, {
      params: new HttpParams().append('dealer', dealer)
    }).pipe(map(res=>res.result))
  }

  chassisNoAuto(chassisNo: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(SurveySummaryReportApi.getChassisNo, {
      params: new HttpParams().append('chassisNo', chassisNo)
    }).pipe(map(res=>res.result))
  }

  getAllZones(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.allZone)
  }

  getRegionByZoneId(zoneId: number): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.regionByZone, {
      params: new HttpParams().set('zoneId', ''+zoneId)
    })
  }

  getAreaByRegionId(zoneId: number,regionId:number) : Observable<BaseDto<Array<any>>>{
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.regionByZone, {
      params: new HttpParams().set('zoneId', ''+zoneId).set('regionId',''+ regionId)
    })
  }

  getTerritoryByAreaId(zoneId: number,regionId:number,areaId:number): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.regionByZone, {
      params: new HttpParams().set('zoneId', ''+zoneId).
      set('regionId',''+ regionId).set('areaId',''+ areaId)
    })
  }

  searchModel(model) {
    return this.httpClient.get(`${this.getModelUrl}`, {
      params: new HttpParams().set('model', model)
    })
  }


  searchSubModel(model) {
    return this.httpClient.get(`${this.getSubModelUrl}`, {
      params: new HttpParams().set('model', model)
    })
  }

  surveyNoAuto(surveyNo: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(SurveySummaryReportApi.getSurveyNo, {
      params: new HttpParams().append('surveyNo', surveyNo)
    }).pipe(map(res=>res.result))
  }

  getQuestion(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.getQuestion)
  }
  getQASurveyName(): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(SurveySummaryReportApi.getQASurveyName)
  }
  downloadsurveyDetailsExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(SurveySummaryReportApi.downloadSurveyDetailsExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }

  searchSurveySummaryQAReport(searchDetail): Observable<BaseDto<Array<SearchPartyResponse>>> {
    return this.httpClient.post<BaseDto<Array<SearchPartyResponse>>>(SurveySummaryReportApi.surveySummaryQandAReport, searchDetail)
  }

  downloadsurveyQAExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(SurveySummaryReportApi.downloadSurveyQandAExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
