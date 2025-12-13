import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PartyMasterApi } from '../../url.utils/party-master-urls';
import { SubmitDto } from '../../domain/party-master-domain';
import { Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment.prod';

@Injectable()
export class PartyMasterPageService {

  private readonly postOfficeUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getPostOffice}`;
  private readonly searchPinCodeURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getPinCode}`;
  private readonly searchByPinCodeURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getByPinCode}`;
  constructor(
    private httpClient: HttpClient
  ) { }

  submitPartyMaster(formData: SubmitDto): Observable<SubmitDto> {
    return this.httpClient.post<SubmitDto>(PartyMasterApi.submitData, formData)
  }

  updatePartyMaster(formData: SubmitDto): Observable<SubmitDto> {
    return this.httpClient.post<SubmitDto>(PartyMasterApi.updateData, formData)
  }

  getByIdPartyMaster(id: any) {
    return this.httpClient.get(`${PartyMasterApi.searchPartyFilter}/${id}`)
  }
  searchPinCode(pinCode) {
    return this.httpClient.get(`${this.searchPinCodeURL}`, {
      params: new HttpParams().set('pinCode', pinCode)
    })
  }

  searchByPinCode(pinCode, cityId) {
    return this.httpClient.get(`${this.searchByPinCodeURL}`, {
      params: new HttpParams().set('pinCode', pinCode).set('cityId', cityId)
    })
  }

  postOfficeData(pinCode){
    return this.httpClient.get(`${this.postOfficeUrl}`, {
      params: new HttpParams().set('pinCode', pinCode)
    })
  }
}
