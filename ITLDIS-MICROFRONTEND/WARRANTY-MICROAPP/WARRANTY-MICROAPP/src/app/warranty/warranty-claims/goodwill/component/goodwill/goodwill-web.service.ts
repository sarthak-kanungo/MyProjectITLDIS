import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GoodwillApi } from '../../url-utils/goodwill-url';
import { Observable } from 'rxjs/Observable';
import { Goodwilltype } from '../../domain/goodwill.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { AutoCompleteResult } from '../../../retro-fitment-campaign/domain/retro-fitment-campaign.domain';

@Injectable()
export class GoodwillWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  dropDownGoodwillType(): Observable<Goodwilltype> {
    return this.httpClient.get<BaseDto<Goodwilltype>>(GoodwillApi.dropDownGoodwillType).pipe(map(res => res.result));
  }
  dropDownStatus(){
      return this.httpClient.get(GoodwillApi.dropDownGoodwillStatus).pipe(map(res => res['result']));
  }
  
  searchAutoCompleteGoodwillNo(goodwillNo: string): Observable<AutoCompleteResult> {
      return this.httpClient.get<BaseDto<AutoCompleteResult>>(GoodwillApi.searchAutoCompleteGoodwillNo, {
          params: new HttpParams().append('goodwillNo',goodwillNo)
        }).pipe(map(res => res.result));
      }

}
