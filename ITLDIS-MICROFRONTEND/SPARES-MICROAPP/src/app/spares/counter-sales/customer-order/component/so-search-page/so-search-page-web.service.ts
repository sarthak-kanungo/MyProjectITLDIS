import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UtilsService } from '../../../../../utils/utils.service';
import { SoSearch } from '../../domain/so.domain';
import { SoApi } from '../../url-utils/so-urls';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';

@Injectable()
export class SoSearchPageWebService {
  constructor(private httpClient:HttpClient,
    private utilsService: UtilsService,
    private dateService: DateService) { }

    searchSo(searchValue: SoSearch) {
    searchValue = this.utilsService.removeEmptyKey<SoSearch>(searchValue);
    return this.httpClient.post(SoApi.search, searchValue)
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "")) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }

    downloadExcelReport(filter): Observable<HttpResponse<Blob>>{
      return this.httpClient.post<Blob>(SoApi.spareSalesReportExcelUrl, filter,
        {observe: 'response', responseType: 'blob' as 'json' }
      )
}

}