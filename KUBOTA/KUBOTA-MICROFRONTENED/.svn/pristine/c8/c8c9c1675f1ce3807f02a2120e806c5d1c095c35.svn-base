import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RfcApi } from '../../url-utils/retro-fitment-campaign-url';
import { Observable } from 'rxjs/Observable';
import { RfcSearchStatus, AutoCompleteResult } from '../../domain/retro-fitment-campaign.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class RetroFitmentCampaignSearchWebService {

  constructor(
    private httpClient: HttpClient,
  ) { }

  searchRetrofitmentStatus(): Observable<RfcSearchStatus> {
    return this.httpClient.get<BaseDto<RfcSearchStatus>>(RfcApi.searchRetrofitmentStatus).pipe(map(res => res.result));
  }

  searchRetrofitmentNo(retrofitmentNo: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(RfcApi.searchRetrofitmentNo, {
      params: new HttpParams().append('retrofitmentNo', retrofitmentNo)
    }).pipe(map(res => res.result))
  }
  
  
}
