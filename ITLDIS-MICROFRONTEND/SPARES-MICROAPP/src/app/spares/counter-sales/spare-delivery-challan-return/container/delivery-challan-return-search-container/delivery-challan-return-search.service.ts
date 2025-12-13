import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DeliveryChallanReturnSearchService {

  constructor(private httpClient: HttpClient) { }
  getTableData(page: number,size: number) {
   


    return this.httpClient.get(`./assets/row_jsons/searchdeliveryChallanReturn.json`,{
      params: new HttpParams().set('page',page.toString()).set('size',size.toString())
    })
  }
}
