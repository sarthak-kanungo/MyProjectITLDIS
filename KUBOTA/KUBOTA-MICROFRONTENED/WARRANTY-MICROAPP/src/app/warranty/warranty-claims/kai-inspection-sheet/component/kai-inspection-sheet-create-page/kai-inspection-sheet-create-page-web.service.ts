import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { KaiInspectionSheetApi } from '../../url-utils/kai-inspection-sheet-url';
import { Observable } from 'rxjs/Observable';
import { KaiInspectionSheet, SubmitKaisheet } from '../../domain/kai-inspection-sheet.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class KaiInspectionSheetCreatePageWebService {
  private readonly printKAIInspectSheet = `${environment.baseUrl}${urlService.api}${urlService.warranty}${urlService.reports}/printKAIInspectSheet`;

  constructor(
    private httpClient: HttpClient,
  ) { }

  wcrDcForKaiInspectionSheet(wcrNo: string): Observable<KaiInspectionSheet> {
    return this.httpClient.get<BaseDto<KaiInspectionSheet>>(KaiInspectionSheetApi.wcrDcForKaiInspectionSheet, {
      params: new HttpParams().append('wcrNo', wcrNo)
    }).pipe(map(res => res.result))
  }
  viewKaiInspectionSheet(inspectionNo: string, wcrNo: string): Observable<KaiInspectionSheet> {
    return this.httpClient.get<BaseDto<KaiInspectionSheet>>(KaiInspectionSheetApi.viewKaiInspectionSheet, {
      params: new HttpParams().append('inspectionNo', inspectionNo).append('wcrNo', wcrNo)
    }).pipe(map(res => res.result))
  }
  saveKaiInspectionSheet(inspectionSheetData: SubmitKaisheet) {
    let formData: FormData = new FormData();
    formData.append('kaiInspectionSheet', new Blob([JSON.stringify(inspectionSheetData.kaiInspectionSheet)], { type: 'application/json' }))
    if (inspectionSheetData.multipartFileList) {
      inspectionSheetData.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(KaiInspectionSheetApi.saveKaiInspectionSheet, formData, {headers});
  }

  
  kaiInspecPrint(inspectionNo:string, printStatus:string){
    return this.httpClient.get<Blob>(this.printKAIInspectSheet, {
        params: new HttpParams().set('inspectionNo', inspectionNo)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }


}
