import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { WpdcSearch } from '../../domain/warranty-parts-delivery-challan.domain';
import { WpdcApi } from '../../url-utils/warranty-parts-delivery-challan-url';
import { Observable } from 'rxjs/Observable';
import { BaseDto } from 'BaseDto';
import { AutoCompleteResult } from '../../../retro-fitment-campaign/domain/retro-fitment-campaign.domain';
import { map } from 'rxjs/operators';

@Injectable()
export class WpdcSearchPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  deliveryChallanSearch(searchDetail: WpdcSearch): Observable<BaseDto<Array<WpdcSearch>>> {
    return this.httpClient.post<BaseDto<Array<WpdcSearch>>>(WpdcApi.deliveryChallanSearch,searchDetail);
  }
  searchAutoCompleteWcrNo(wcrNo: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(WpdcApi.searchAutoCompleteWcrNo, {
      params: new HttpParams().append('wcrNo',wcrNo)
    }).pipe(map(res => res.result))
  }
}
