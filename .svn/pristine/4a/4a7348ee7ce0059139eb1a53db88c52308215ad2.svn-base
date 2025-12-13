import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RfcApi } from '../../url-utils/retro-fitment-campaign-url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { AutoCompleteResult, PartDetails } from '../../domain/retro-fitment-campaign.domain';

@Injectable()
export class RetroFitmentCampaignMaterialDetailsWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  autoCompletePartNumber(partNumber: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(RfcApi.autoCompletePartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
  getPartDetailsByPartNumber(partNumber: string): Observable<PartDetails> {
    return this.httpClient.get<BaseDto<PartDetails>>(RfcApi.getPartDetailsByPartNumber, {
      params: new HttpParams().set('partNumber', partNumber)
    }).pipe(map(res => res.result))
  }
  getAutoCompleteJobCode(jobCodeNo: string): Observable<AutoCompleteResult> {
    return this.httpClient.get<BaseDto<AutoCompleteResult>>(RfcApi.getAutoCompleteJobCode, {
      params: new HttpParams().set('jobCodeNo', jobCodeNo)
    }).pipe(map(res => res.result))
  }
}
