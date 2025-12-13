import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparesGrnUrl } from '../../url-util/spares-grn.url';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';

@Injectable()
export class SparesGrnItemDetailApiService {

  constructor(private httpClient: HttpClient) { }
  searchBinLocationByStoreId(storeId: number, binLocation: string,itemNo: string): Observable<SelectList[]> {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getBinLocationByStoreId, {
      params: new HttpParams()
        .set('storeId', storeId.toString())
        .set('binLocation', binLocation)
        .set('itemNo', itemNo)
    }).pipe(
      map(res => res.result)
    );
  }
}
