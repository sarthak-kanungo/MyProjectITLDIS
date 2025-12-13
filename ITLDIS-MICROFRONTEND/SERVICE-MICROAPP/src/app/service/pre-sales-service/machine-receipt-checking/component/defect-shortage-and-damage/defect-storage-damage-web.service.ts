import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ItemNumberAuto } from '../../domain/machine-receipt-checking.domain';

@Injectable()
export class DefectStorageDamageWebService {

  constructor(private httpClient:HttpClient) { }

  searchByItemNumber(searchKey: string,itemId: string):Observable<BaseDto<Array<ItemNumberAuto>>> {
    return this.httpClient.get<BaseDto<Array<ItemNumberAuto>>>( MrcUrl.searchByItemNumberUrl , {
      params: new HttpParams()
        .set('itemNumber', searchKey)
        .set('itemId', itemId)
    })
  }
  
}
