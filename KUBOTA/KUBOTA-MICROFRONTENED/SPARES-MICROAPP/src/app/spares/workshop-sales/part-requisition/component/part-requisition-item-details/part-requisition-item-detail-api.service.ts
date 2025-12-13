import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { PartRequisitionUrl } from '../../url-util/part-requisition.url';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';

@Injectable()
export class PartRequisitionItemDetailApiService {

  constructor(
    private httpClient: HttpClient,
  ) { }
  searchSparesPartItemNo(itemNo: string, ids:string): Observable<SelectList[]> {
    return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.searchSparesPartItemNo, {
      params: new HttpParams().set('itemNo', itemNo)
          .set('existingIds', ids)
    }).pipe(
      map(res => res.result)
    )
  }
  getSparesPartItemNoDetails(itemNo: string): Observable<object> {
    return this.httpClient.get<BaseDto<object>>(PartRequisitionUrl.getSparesPartItemNoDetails, {
      params: new HttpParams().set('itemNo', itemNo)
    }).pipe(
      map(res => res.result)
    )
  }
}
