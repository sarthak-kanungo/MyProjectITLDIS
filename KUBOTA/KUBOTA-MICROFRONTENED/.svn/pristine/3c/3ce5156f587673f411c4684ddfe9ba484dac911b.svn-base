import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class StoreSearchPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getStoreList(page : number, size : number) {
    return this.httpClient.get(`./assets/row_jsons/storeMaster.json`,{
        params: new HttpParams()
          .append('page', page.toString())
          .append('size', size.toString())
      })
  }
}

