import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ItemNumberAuto } from '../../domain/machine-receipt-checking.domain';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { map } from 'rxjs/operators';
@Injectable()
export class DefectShoratageDamageService {

  constructor(private httpClient: HttpClient) { }

  searchByItemNumber(searchKey: string, itemId: string): Observable<Array<ItemNumberAuto>> {
    console.log("searchKey ", searchKey);
    return this.httpClient.get<BaseDto<Array<ItemNumberAuto>>>(MrcUrl.searchByItemNumberUrl, {
      params: new HttpParams()
        .set('itemNumber', searchKey)
        .set('itemId', itemId)
    }).pipe(map(dto => dto.result))
  }
}
