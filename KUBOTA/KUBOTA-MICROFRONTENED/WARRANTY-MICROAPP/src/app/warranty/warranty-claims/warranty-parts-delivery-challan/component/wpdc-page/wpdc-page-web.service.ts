import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { WpdcApi } from '../../url-utils/warranty-parts-delivery-challan-url'
import { Observable } from 'rxjs/Observable';
import { WarrantyDeliveryChallan, WpdcSubmit, WpdcView, WpdcJSON, WpdcViewMaster } from '../../domain/warranty-parts-delivery-challan.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class WpdcPageWebService {
  private readonly printWpdc = `${environment.baseUrl}${urlService.api}${urlService.warranty}${urlService.reports}/printWpdc`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getClaimPartInDc(ids: string): Observable<Array<WarrantyDeliveryChallan>> {
    return this.httpClient.get<BaseDto<Array<WarrantyDeliveryChallan>>>(WpdcApi.getClaimPartInDc, {
      params: new HttpParams().append('ids', ids)
    }).pipe(map(res => res.result))
  }
  viewWarrantyDeliveryChallan(dcNo: string): Observable<WpdcViewMaster> {
    return this.httpClient.get<BaseDto<WpdcViewMaster>>(WpdcApi.viewWarrantyDeliveryChallan, {
      params: new HttpParams().append('dcNo', dcNo)
    }).pipe(map(res => res.result))
  }
  saveDeliveryChallan(dcDetail: WpdcJSON) {
    return this.httpClient.post(WpdcApi.saveDeliveryChallan, dcDetail);
  }
  updateWarrantyDeliveryChallan(dcDetail: WpdcJSON) {
    return this.httpClient.post(WpdcApi.updateWarrantyDeliveryChallan, dcDetail);
  }

  wpdcPrint(wpdcNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printWpdc, {
          params: new HttpParams().set('wpdcNo', wpdcNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
