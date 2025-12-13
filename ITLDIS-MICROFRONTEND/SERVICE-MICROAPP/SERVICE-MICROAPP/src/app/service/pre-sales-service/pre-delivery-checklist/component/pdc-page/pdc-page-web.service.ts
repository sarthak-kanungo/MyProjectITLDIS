import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ViewPdc, CheckpointListByModel, SaveAndSubmitPdc } from '../../domain/pdc-domain';
import { map } from 'rxjs/operators';
import { PdcApi } from '../../url-util/pdc-url';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PdcPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  saveAndSubmitPdc(formData: SaveAndSubmitPdc) {
    return this.httpClient.post(PdcApi.savePdc, formData)
  }

  getPdcById(id: string): Observable<ViewPdc> {
    return this.httpClient.get<BaseDto<ViewPdc>>(`${PdcApi.getPdcById}/${id}`
     ).pipe(map(dto => dto.result))
  }

  getChassisDetailsByChassisNoInJobCard(chassisNo: string, preSalesFlag: string, jobCardFlag: string){
    return this.httpClient.get(PdcApi.getChassisDetailsByChassisNoInJobCard, {
      params: new HttpParams()
        .append('chassisNo', chassisNo)
        .append('preSalesFlag', preSalesFlag)
        .append('jobCardFlag', jobCardFlag)
    })
  }

  
  printPreSalesServicePdc(pdcNo:string, printStatus:string){
    return this.httpClient.get<Blob>(PdcApi.printPreSalesServicePdc, {
        params: new HttpParams().set('pdcNo', pdcNo)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }

}
