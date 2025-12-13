import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { dealerTerritoryApi } from '../url-utils/dealer-territory';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { submitForm } from '../component/create-dealer-territory-mapping/dealer-territory-model';

@Injectable({
  providedIn: 'root'
})
export class DealerTerritoryMappingService {

  constructor(
    private httpClient:HttpClient,
   
  ) { }

 

 
  getDealerLists(dealer: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.getDealerList, {
    params: new HttpParams().append('dealer', dealer),
    }).pipe(map(res=>res.result))
  }

   territoryNo(territoryNo: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.autoSearchTerritoryNo, {
      params: new HttpParams().append('territoryNo', territoryNo)
    }).pipe(map(res => res.result));
  }
  getAutoSerchBranch(branchName: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.autoSearchBranchName, {
      params: new HttpParams().append('branchName', branchName)
    }).pipe(map(res => res.result));
  }



  getBranchList(dealerId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.getBranch, {
      params: new HttpParams().append('dealerId', dealerId)
    }).pipe(map(res => res.result));
  }
  getCountry() {
    return this.httpClient.get(dealerTerritoryApi.getCountry)
  }
  getStateList(dealerId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.getState, {
      params: new HttpParams().append('dealerId', dealerId)
    }).pipe(map(res => res.result));
  }
  getDistrict(stateId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.getDistrict, {
      params: new HttpParams().append('stateId', stateId)
    }).pipe(map(res => res.result));
  }
  getTehsil(districtId: any): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.getTehsil, {
      params: new HttpParams().append('districtId', districtId)
    }).pipe(map(res => res.result));
  }
  saveTerritoryData(territoryData:submitForm ) {
    return this.httpClient.post<BaseDto<any>>(dealerTerritoryApi.saveTerritoryMapping, territoryData);
  }

  searchData(searchData: object) {

    return this.httpClient.post(dealerTerritoryApi.search, searchData)
  }
 
  viewData(territoryNo: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(dealerTerritoryApi.view, {
      params: new HttpParams().append('territoryNo', territoryNo)
    }).pipe(map(res => res.result))
  }
}
