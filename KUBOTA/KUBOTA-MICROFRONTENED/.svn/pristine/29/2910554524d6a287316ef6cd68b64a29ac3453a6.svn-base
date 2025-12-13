import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MrcUrl } from '../machine-receipt-checking/url-util/machine-receipt-checking-urls';

@Injectable()
export class MachineFormfService {

  static readonly searchurl:string = `${MrcUrl.apiController}/machineFormFSearch`;
  static readonly chassisNourl:string = `${MrcUrl.apiController}/getChassisNo`;

  constructor(private httpClient: HttpClient) {

   }

   machineFormFSearch(searchObj){
      return this.httpClient.post(MachineFormfService.searchurl, searchObj);
   }

   getChassisNo(searchValue){
    return this.httpClient.get(MachineFormfService.chassisNourl, {
      params : new HttpParams().set('searchValue', searchValue)
    });
   }
}
