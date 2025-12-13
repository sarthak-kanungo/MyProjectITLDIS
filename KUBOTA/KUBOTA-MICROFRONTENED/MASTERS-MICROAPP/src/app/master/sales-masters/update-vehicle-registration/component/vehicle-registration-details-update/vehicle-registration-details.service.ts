import { Injectable } from '@angular/core';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SubmitVehicleRegistration } from 'VehicleRegistration';

@Injectable()
export class VehicleRegistrationDetailsService {
private readonly chassisNoURL = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.chassisNoAuto}`
private readonly autopopulateByChassisURL = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.getDetailsByChassisNo}`
//submit
submitVehicleRegistration ={} as SubmitVehicleRegistration
//submitURL
private readonly submitURL = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.addSales}`

  constructor(private http:HttpClient) { }

  getChassisNo(chassisNo){
    return this.http.get(`${this.chassisNoURL}`,{
      params:new HttpParams().set('chassisNo',chassisNo)
    })
  }

  getChassisAutopopulate(chassisNo){
    return this.http.get(`${this.autopopulateByChassisURL}`,{
      params:new HttpParams().set('chassisNo',chassisNo)
    })
  }
//submit
  postSubmitData(formData){
    return this.http.post(`${this.submitURL}`,formData)
  }

}
