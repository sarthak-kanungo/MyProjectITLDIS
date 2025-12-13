import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { GoodwillApi } from '../../url-utils/goodwill-url';
import { SubmitGoodwill, GoodwillViewMain,ApproveQuantity } from '../../domain/goodwill.domain';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class GoodwillCreatePageWebService {
    
    private readonly printGoodwillUrl = `${environment.baseUrl}${urlService.api}${urlService.warranty}${urlService.reports}/printGoodwill`;
  
 constructor(
    private httpClient: HttpClient,
  ) { }

  saveGoodwill(goodwillDetail: SubmitGoodwill) {
    const formData: FormData = new FormData();
    formData.append('warrantyGoodwill', new Blob([JSON.stringify(goodwillDetail.warrantyGoodwill)], { type: 'application/json' }))
    if (goodwillDetail.multipartFileList) {
      goodwillDetail.multipartFileList.forEach(element => {
            formData.append('multipartFileList', element['file'])
      });
    } else {
      goodwillDetail.multipartFileList = null;
    }
    const headers = new HttpHeaders();
    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');
    return this.httpClient.post(GoodwillApi.saveGoodwill, formData, {headers});
  }

  warrantyGoodwillView(goodwillNo: string): Observable<GoodwillViewMain> {
    return this.httpClient.get<BaseDto<GoodwillViewMain>>(GoodwillApi.warrantyGoodwillView, {
      params: new HttpParams().append('goodwillNo', goodwillNo)
    }).pipe(map(res => res.result));
  }
  approveWarrantyGoodwill(approveData: ApproveQuantity) {
      return this.httpClient.post(GoodwillApi.approveWarrantyGoodwill, approveData);
    }

  printGoodwill(goodwillNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printGoodwillUrl, {
          params: new HttpParams().set('goodwillNo', goodwillNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
