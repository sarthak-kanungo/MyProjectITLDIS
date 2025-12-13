import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PdiUrl } from '../../url-util/pdi-url';
import { Observable } from 'rxjs';
import { ChasisNumber, ChasisNumberData, ModelNumberData, Autocomplete } from '../../domain/pdi-domain';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class PdiWebService {

  constructor(private httpClient: HttpClient) { }

  searchByChassisNumber(searchKey: string): Observable<Array<Autocomplete>> {
    return this.httpClient.get<BaseDto<Array<Autocomplete>>>(PdiUrl.chassisNumberAuto, {
      params: new HttpParams().set('chassisNo', searchKey)
    }).pipe(map(dto => dto.result))
  }

  getDataFromChasisNumber(searchKey: string): Observable<ChasisNumberData> {
    return this.httpClient.get<BaseDto<ChasisNumberData>>(PdiUrl.getDataFromChasisNumberUrl, {
      params: new HttpParams().set('chassisNo', searchKey)
    }).pipe(map(dto => dto.result))
  }
  getCheckPoint(chassis:string): Observable<ModelNumberData> {
      return this.httpClient.get<BaseDto<ModelNumberData>>(PdiUrl.getDataFromModelURL,
              { params : new HttpParams()
                  .append('transType', 'PDI')
                  .append('chassis', chassis)
              }).pipe(map(dto=>dto.result))
  }

  getSystemDate() {
    return this.httpClient.get(PdiUrl.getSystemDateUrl)
  }

  printPreSalesServicePdi(pdiNo:string, printStatus:string){
    return this.httpClient.get<Blob>(PdiUrl.printPreSalesServicePdi, {
        params: new HttpParams().set('pdiNo', pdiNo)
                                .set('printStatus', printStatus),
       observe: 'response', responseType: 'blob' as 'json'                         
    });
  }
}
