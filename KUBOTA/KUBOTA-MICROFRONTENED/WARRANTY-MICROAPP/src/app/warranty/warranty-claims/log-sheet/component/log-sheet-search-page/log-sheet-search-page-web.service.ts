import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { LogSheetApi } from '../../url-utils/log-sheet-urls';
import { SearchWarrantyLogSheet, SearchWarrantyLogSheetList } from '../../domain/log-sheet.domain';
import { Observable } from 'rxjs/Observable';
import { BaseDto } from 'BaseDto';

@Injectable()
export class LogSheetSearchPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public searchWarrantyLogSheet(warrantyLogsheet: SearchWarrantyLogSheet) : Observable<BaseDto<Array<SearchWarrantyLogSheetList>>> {
    return this.httpClient.post<BaseDto<Array<SearchWarrantyLogSheetList>>>(LogSheetApi.logsheetSearch, warrantyLogsheet)
    
  }

  public closeLogsheet(id){
    return this.httpClient.get(LogSheetApi.logsheetClose, {
      params : new HttpParams().set('id',id)
    })
  }
   downloadExportExcel(warrantyLogsheet: SearchWarrantyLogSheet){
    return this.httpClient.post<Blob>(LogSheetApi.downloadExcel, warrantyLogsheet,
    {observe: 'response', responseType: 'blob' as 'json' });
  }
}
