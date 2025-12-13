import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { MrcViewResult, FinalMrc } from '../../domain/machine-receipt-checking.domain';

import { map } from 'rxjs/operators';

@Injectable()
export class MrcPageService {

  constructor(private httpClient: HttpClient) { }

  getMrcViewdata(id: number): Observable<MrcViewResult> {
    return this.httpClient.get<BaseDto<MrcViewResult>>(`${MrcUrl.getMrcViewData}/${id}`).pipe(map(dto => dto.result))
  }

  postSaveMRCDetails(mrcSendData: FinalMrc) {
    console.log("mrcSendData ", mrcSendData);
    let formData: FormData = new FormData();
    formData.append('serviceMrc', new Blob([JSON.stringify(mrcSendData.serviceMrc)], { type: 'application/json' }))
    if (mrcSendData.multipartFileList) {
      mrcSendData.multipartFileList.forEach(element => {
        // console.log("element ", element);
        formData.append('multipartFileList', element['file'])
      });
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(MrcUrl.addMrcDetails, formData, { headers })
  }


  printPreSalesServiceMrc(mrcNo:string, printStatus:string){
    return this.httpClient.get<Blob>(MrcUrl.printPreSalesServiceMrc, {
        params: new HttpParams().set('mrcNo', mrcNo)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
  printForm(mrcNo:string, formType:string, requestType:string, printStatus:string){
    return this.httpClient.get<Blob>(MrcUrl.printForm, {
        params: new HttpParams().set('mrcNo', mrcNo)
                                .set('formType', formType)
                                .set('requestType', requestType)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
}
