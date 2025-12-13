import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChannelFinanceLimitUploadService {
private readonly updateCFLUData=`${environment.baseUrl}${urlService.api}${urlService.kfmMasters}${urlService.channelFinanceLimitUpload}`

  constructor(private httpClient:HttpClient) { }

  
  updateExcelData(finalData) {   
    return this.httpClient.post(`${this.updateCFLUData}/uploadData`, finalData);
  }
}
