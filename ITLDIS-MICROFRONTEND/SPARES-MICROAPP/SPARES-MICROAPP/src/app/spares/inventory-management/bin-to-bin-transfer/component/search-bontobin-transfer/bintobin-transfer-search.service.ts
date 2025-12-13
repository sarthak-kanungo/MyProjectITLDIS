import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class BintobinTransferSearchService {


  constructor(private httpClient: HttpClient) { }
  getTableData(page: number,size: number) {
    return this.httpClient.get(`./assets/row_jsons/searchBinToBinTransfer.json`,{
      params: new HttpParams().set('page',page.toString()).set('size',size.toString())
    })
  }
}
