import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GoodwillApi } from '../../url-utils/goodwill-url';
import { GoodwillSearch, GoodwillList } from '../../domain/goodwill.domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
 
@Injectable()
export class GoodwillSearchPageWebService {
  constructor(
    private httpClient: HttpClient
  ) { }

  searchWarrantyGoodwill(goodwill: GoodwillSearch): Observable<BaseDto<Array<GoodwillList>>> {
    return this.httpClient.post<BaseDto<Array<GoodwillList>>>(GoodwillApi.searchWarrantyGoodwill, goodwill);
  }
}
