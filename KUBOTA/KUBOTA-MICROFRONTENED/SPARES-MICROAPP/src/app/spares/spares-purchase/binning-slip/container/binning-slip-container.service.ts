import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BinningSlipContainerService {

  constructor(private httpClient: HttpClient) { }
  getTableData(page: number,size: number) {
    return this.httpClient.get(`./assets/row_jsons/backOrderCancellationRequest.json`,{
      params: new HttpParams().set('page',page.toString()).set('size',size.toString())
    })
  }
}
