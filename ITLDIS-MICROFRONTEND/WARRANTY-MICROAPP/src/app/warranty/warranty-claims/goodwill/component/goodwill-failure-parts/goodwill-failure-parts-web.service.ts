import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GoodwillApi } from '../../url-utils/goodwill-url';
import { Observable } from 'rxjs/Observable';
import { PriceType } from '../../domain/goodwill.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class GoodwillFailurePartsWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  dropDownPriceType(): Observable<PriceType> {
    return this.httpClient.get<BaseDto<PriceType>>(GoodwillApi.dropDownPriceType).pipe(map(res => res.result));
  }
}
