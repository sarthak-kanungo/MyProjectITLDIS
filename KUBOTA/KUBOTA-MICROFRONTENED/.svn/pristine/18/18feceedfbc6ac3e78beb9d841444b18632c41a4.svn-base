import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { VehicleRegistrationSearch, FilterSearchVehicleRegistration } from 'VehicleRegistration';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class SearchVehicleRegistrationService {
  private vehicleRegistrationNoForm:FormGroup
  private readonly chassisNoURL = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.chassisNoAuto}`
  private readonly customerCodeURL = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.customerCodeAuto}`
  private readonly getSearchDataUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.getSalesSearch}`
  constructor(private http:HttpClient,private httpClient: HttpClient,private fb: FormBuilder) { }

  getChassisNo(chassisNo){
    return this.http.get(`${this.chassisNoURL}`,{
      params:new HttpParams().set('chassisNo',chassisNo)
    })
  }

  getCustomerCode(customerCode){
    return this.http.get(`${this.customerCodeURL}`,{
      params:new HttpParams().set('customerCode',customerCode)
    })
  }

  getSearchData(sendData: FilterSearchVehicleRegistration):Observable<BaseDto<Array<VehicleRegistrationSearch>>> {
    return this.httpClient.get<BaseDto<Array<VehicleRegistrationSearch>>>(this.getSearchDataUrl, {
      params: this.prepareHttpParams(sendData)
    })
  }

  private prepareHttpParams(filter: FilterSearchVehicleRegistration) {
    console.log(filter)
    let httpParams = new HttpParams()
    console.log(httpParams)
    return httpParams
  }

  
}
