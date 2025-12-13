import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class MachineServiceHistoryService {

  static readonly serviceBookingSearch = `${environment.baseUrl}/${environment.api}/service/jobcard/machineServiceHistory`

  constructor(private httpClient:HttpClient) { }

  serviceHistorySearch(obj:Object){
    return this.httpClient.post(MachineServiceHistoryService.serviceBookingSearch,obj);
  }
}
