import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { SubmitRfc, Rfc } from '../../domain/retro-fitment-campaign.domain';
import { RfcApi } from '../../url-utils/retro-fitment-campaign-url'
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';
@Injectable()
export class RetroFitmentCampaignCreatePageWebService {
  private readonly downloadTemplate = `${environment.baseUrl}${urlService.api}/common/syslookup/downloadTemplate`
  constructor(
    private httpClient: HttpClient
  ) { }

  saveRetrofitmentCampaign(RfcData: SubmitRfc) {
    let formData: FormData = new FormData();
    formData.append('warrantyRetrofitmentCampaign', new Blob([JSON.stringify(RfcData.warrantyRetrofitmentCampaign)], { type: 'application/json' }))
    if (RfcData.multipartFileList) {
      RfcData.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    }
    if (RfcData.multipartFile) {
        formData.append('multipartFile', RfcData.multipartFile)
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(RfcApi.saveRetrofitmentCampaign, formData, {headers});
  }

  viewRetrofitmentCampaign(retrofitmentNo: string): Observable<Rfc> {
    return this.httpClient.get<BaseDto<Rfc>>(RfcApi.viewRetrofitmentCampaign, {
      params: new HttpParams().append('retrofitmentNo', retrofitmentNo)
    }).pipe(map(res => res.result))
  }
  getSystemDateUrl(): Observable<string> {
    return this.httpClient.get<BaseDto<string>>(PcrApi.getSystemDateUrl).pipe(map(res => res.result))
    
  }
   downloadTemplates(filename) : Observable<HttpResponse<Blob>> {
    
    return this.httpClient.get<Blob>(`${this.downloadTemplate}`, { 
        params: new HttpParams().set("filename",filename),
        observe: 'response', responseType: 'blob' as 'json' });
  }
  printReport(campaignId:string,campaignNo:string, printStatus:string){
    return this.httpClient.get<Blob>(RfcApi.printWarrantyRetrofitmentReport, {
        params: new HttpParams()
        .set('campaignId', campaignId)
        .set('campaignNo', campaignNo)
        .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
}
