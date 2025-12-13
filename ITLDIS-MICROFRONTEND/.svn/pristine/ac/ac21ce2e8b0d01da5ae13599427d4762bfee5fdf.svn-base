import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { ProspectDetailsObj, SaveProspectDetailsObj } from 'ProspectDetails';

@Injectable()
export class ProspectDetailsContainerService {
  private readonly prospectTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getProspectType}`;
  private readonly mobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getMobileNumber}`;
  private readonly searchbyMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getDataByMobileNo}`
  private readonly searchPinCodeURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getPinCode}`;
  private readonly searchByPinCodeURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getByPinCode}`;
  private readonly postOfficeUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getPostOffice}`;
  private readonly searchProspectCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.searchProspectCode}`;
  private readonly searchDataByProspectCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.customerMaster}${urlService.searchProspectMasterByProspectCode}`;
  private readonly checkItemNumberModelInEnquiryURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}/checkItemNumberModelInEnquiry`;
  //http://192.168.15.109:8383/api/customerMaster/searchProspectCode?prospectCode=p
  //http://192.168.15.109:8383/api/customerMaster/searchProspectMasterByProspectCode?prospectCode=PRO%2F2019%2F0001
  prospectDetailsObj = {} as ProspectDetailsObj;
  saveProspectDetailsObj = {} as SaveProspectDetailsObj
  constructor(
    private http: HttpClient
  ) { }

  dropdownprospectType(){
    return this.http.get(`${this.prospectTypeURL}`)
  }


  mobileNoData(mobileNumber) {
    return this.http.get(`${this.mobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    })
  }

  searchbyMobileNo(mobileNumber) {
    return this.http.get(`${this.searchbyMobileNoUrl}`, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    })
  }

  searchPinCode(pinCode) {
    return this.http.get(`${this.searchPinCodeURL}`, {
      params: new HttpParams().set('pinCode', pinCode)
    })
  }

  searchByPinCode(pinCode, cityId) {
    return this.http.get(`${this.searchByPinCodeURL}`, {
      params: new HttpParams().set('pinCode', pinCode).set('cityId', cityId)
    })
  }

  searchProspectCode(prospectCode){
    return this.http.get(`${this.searchProspectCodeUrl}`, {
      params: new HttpParams().set('prospectCode', prospectCode)
    })
  }


  searchDataByProspectCode(prospectCode){
    return this.http.get(`${this.searchDataByProspectCodeUrl}`, {
      params: new HttpParams().set('prospectCode', prospectCode)
    })
  }

  checkItemNumberModelInEnquiry(model, mobileNumber){
    return this.http.get(`${this.checkItemNumberModelInEnquiryURL}`, {
      params: new HttpParams().set('model', model).set('mobileNumber', mobileNumber)
    })
  }

  postOfficeData(pinCode){
    return this.http.get(`${this.postOfficeUrl}`, {
      params: new HttpParams().set('pinCode', pinCode)
    })
  }


}
