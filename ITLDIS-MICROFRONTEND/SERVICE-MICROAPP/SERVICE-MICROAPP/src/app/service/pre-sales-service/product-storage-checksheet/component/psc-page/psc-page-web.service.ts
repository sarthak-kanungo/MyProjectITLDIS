import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PscApi } from '../../url-util/psc.url';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ViewPsc, SaveAndSubmitPsc } from '../../domain/psc-domain';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PscPageWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  saveAndSubmitPsc(formdata: SaveAndSubmitPsc){
    return this.httpClient.post(PscApi.savePsc, formdata)
  }

  getPscById(id: string): Observable<ViewPsc> {
    return this.httpClient.get<BaseDto<ViewPsc>>(`${PscApi.getPscById}/${id}`
     ).pipe(map(dto => dto.result))
  }

  printPreSalesServicePsc(pscNo:string, printStatus:string){
    return this.httpClient.get<Blob>(PscApi.printPreSalesServicePsc, {
        params: new HttpParams().set('pscNo', pscNo)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }

}
