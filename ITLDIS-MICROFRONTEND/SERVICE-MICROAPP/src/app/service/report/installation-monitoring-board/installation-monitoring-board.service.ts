import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class InstallationMonitoringBoardService {
  private static readonly searchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/searchIMB`;
  private static readonly exportUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/exportIMB`;
 
  constructor(private httpClient: HttpClient) { }

  getIMBSearchRecords(searchObj){
    return this.httpClient.post(InstallationMonitoringBoardService.searchUrl, searchObj);
  }

  exportIMBSearchRecords(searchObj){
    return this.httpClient.post<Blob>(InstallationMonitoringBoardService.exportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }
}
