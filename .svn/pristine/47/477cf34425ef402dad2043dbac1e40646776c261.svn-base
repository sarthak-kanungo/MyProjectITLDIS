import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BranchCodeDetails, CategoryList, PartyCode, SearchPartyMaster, SearchPartyResponse, Title } from '../../domain/party-master-domain';
import { PartyMasterApi } from '../../url.utils/party-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class PartyDetailsService {

  constructor(
    private httpClient: HttpClient
  ) { }

  // getCategory(): Observable<Array<CategoryList>>{
  //   return this.httpClient.get<BaseDto<Array<CategoryList>>>(PartyMasterApi.categoryDropdown)
  //   .pipe(map(dto => dto.result))
  // }
   getCategory(): Observable<CategoryList[]>{
    return this.httpClient.get<CategoryList[]>(PartyMasterApi.categoryDropdown)
  }
  // getPartyCode(partyCode): Observable<PartyCode> {
  //   return this.httpClient.get<PartyCode>(PartyMasterApi.partyCodeAuto)
  // }
  getPartyCode(partyCode: string){
    return this.httpClient.get(PartyMasterApi.partyCodeAuto,
       { params: new HttpParams().set('partyCode', partyCode) })
  }
  getPartyName(partyName: string, categoryId:number){
    return this.httpClient.get(PartyMasterApi.partyNameAuto,
       { params: new HttpParams().set('partyName', partyName).set('categoryId', categoryId.toString()) })
  }
  getTitle(): Observable<Title> {
    return this.httpClient.get<Title>(PartyMasterApi.titleDropdown)

  }
  searchPartyMaster(searchDetail: SearchPartyMaster): Observable<BaseDto<Array<SearchPartyResponse>>> {
    return this.httpClient.post<BaseDto<Array<SearchPartyResponse>>>(PartyMasterApi.searchPartyFilter, searchDetail)
  }
  // getBranchCode(branchCode: string){
  //   return this.httpClient.get(PartyMasterApi.branchCodeAuto,
  //      { params: new HttpParams().set('branchCode', branchCode) })
  // }
  getBranchCode(branchCode: string){
    return this.httpClient.get(PartyMasterApi.branchCodeAuto, {
      params: new HttpParams().set('branchCode', branchCode)
    })
  }

}
