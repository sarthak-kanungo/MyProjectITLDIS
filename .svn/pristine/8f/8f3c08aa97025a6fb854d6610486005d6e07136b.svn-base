import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { RfcApi } from "../../url-utils/retro-fitment-campaign-url";
import { SearchWarrantyRfc } from "../../domain/retro-fitment-campaign.domain";
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RetroFitmentCampaignSearchPageWebService {
  constructor(private httpClient: HttpClient) {}

  searchRetrofitmentCampaign(warrantyRfc: SearchWarrantyRfc): Observable<BaseDto<Array<SearchWarrantyRfc>>> {
    return this.httpClient.post<BaseDto<Array<SearchWarrantyRfc>>>(RfcApi.searchRetrofitmentCampaign, warrantyRfc)
  }
}
