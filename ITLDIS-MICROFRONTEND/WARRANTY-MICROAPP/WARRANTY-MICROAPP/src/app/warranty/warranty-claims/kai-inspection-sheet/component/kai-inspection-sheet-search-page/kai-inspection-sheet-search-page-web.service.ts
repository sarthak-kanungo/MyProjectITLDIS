import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { SearchInspectionSheet, SearchDetail } from '../../domain/kai-inspection-sheet.domain';
import { KaiInspectionSheetApi } from '../../url-utils/kai-inspection-sheet-url';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import {SearchPCRAutoComplete} from '../../../product-concern-report/domain/product-concern-report.domain';
import { map } from 'rxjs/operators';

@Injectable()
export class KaiInspectionSheetSearchPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  searchKaiInspectionSheet(searchDetail :SearchInspectionSheet): Observable<BaseDto<Array<SearchDetail>>> {
    return this.httpClient.post<BaseDto<Array<SearchDetail>>>(KaiInspectionSheetApi.searchKaiInspectionSheet, searchDetail);
  }
  
  autoCompleteInspectionNo(txt:string):Observable<SearchPCRAutoComplete>{
      return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(KaiInspectionSheetApi.autoCompleteInspectionNo, {
          params: new HttpParams().set('inspectionNo', txt)
        }).pipe(map(res => res.result))  
  }

  autoCompleteWcrNo(txt:string):Observable<SearchPCRAutoComplete>{
    return this.httpClient.get<BaseDto<SearchPCRAutoComplete>>(KaiInspectionSheetApi.autoCompleteWcrNo, {
        params: new HttpParams().set('wcrNo', txt)
      }).pipe(map(res => res.result))  
  }

}
