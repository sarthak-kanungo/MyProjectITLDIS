import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { RepresentativeTypes, DetailsByChassisNo, ServiceStaffName, InstallationCheckList, AutoChassisNumber } from '../../domain/installation-domain';
import { BaseDto } from 'BaseDto';
import { InstallationApi } from '../../url-util/installation-urls';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class BasicInstallationDetailsWebService {

  constructor(
    private httpClient : HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getRepresentativeTypeDropdown(): Observable<Array<RepresentativeTypes>> {
    return this.httpClient.get<BaseDto<Array<RepresentativeTypes>>>(`${InstallationApi.representativeTypeDropdown}`
     ).pipe(map(dto => dto.result))
  }

  getDetailsByChassisNo(chassisNo: string): Observable<BaseDto<DetailsByChassisNo>> {
    return this.httpClient.get<BaseDto<DetailsByChassisNo>>(InstallationApi.getDetailsByChassisNo, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    });//.pipe(map(dto => dto.result))
  }
  
  serviceStaffNameAuto(serviceStaffName: string): Observable<Array<ServiceStaffName>> {
    return this.httpClient.get<BaseDto<Array<ServiceStaffName>>>(InstallationApi.serviceStaffNameAuto, {
      params: new HttpParams()
        .append('serviceStaffName', serviceStaffName)
    }).pipe(map(dto => dto.result))
  }

  getAllDeliveryInstallationDetails(model: string, chassis:string): Observable<Array<InstallationCheckList>> {
    return this.httpClient.get<BaseDto<Array<InstallationCheckList>>>(InstallationApi.getAllDeliveryInstallationDetails, {
      params: new HttpParams()
        .append('model', model)
        .append('transType', 'DI')
        .append('chassis', chassis)
    }).pipe(map(dto => dto.result))
  }

  getAllFieldInstallationDetails(model: string, chassis:string): Observable<Array<InstallationCheckList>> {
    return this.httpClient.get<BaseDto<Array<InstallationCheckList>>>(InstallationApi.getAllFieldInstallationDetails, {
      params: new HttpParams()
        .append('model', model)
        .append('transType', 'FI')
        .append('chassis', chassis)
    }).pipe(map(dto => dto.result))
  }

  chassisNoAuto(chassisNo: string): Observable<Array<AutoChassisNumber>> {
    return this.httpClient.get<BaseDto<Array<AutoChassisNumber>>>(InstallationApi.chassisNoAuto, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }

}
