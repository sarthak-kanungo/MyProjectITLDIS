import { Injectable } from '@angular/core';
import { PscApi } from '../../url-util/psc.url';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { AutoPopulateByChasisNumber, CheckListByChassisNo, AutoChasisNumber } from '../../domain/psc-domain';
import { map } from 'rxjs/operators';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class PscWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getDetailsByChassisNo(chassisNo: string): Observable<AutoPopulateByChasisNumber> {
    return this.httpClient.get<BaseDto<AutoPopulateByChasisNumber>>(PscApi.getDetailsByChassisNo, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

  getAllCheckpoints(chassis:string): Observable<Array<CheckListByChassisNo>> {
    //return this.httpClient.get<BaseDto<Array<CheckListByChassisNo>>>(PscApi.getAllCheckpoints).pipe(map(dto => dto.result))
     
      return this.httpClient.get<BaseDto<Array<CheckListByChassisNo>>>(PscApi.getAllCheckpoints,
              { params : new HttpParams()
                  .append('transType', 'PSC')
                  .append('chassis', chassis)
              }).pipe(map(dto=>dto.result))
       
  }

  getChassisNo(chassisNo: string): Observable<Array<AutoChasisNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoChasisNumber>>>(PscApi.autoCompleteChassisNumber, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }


}
