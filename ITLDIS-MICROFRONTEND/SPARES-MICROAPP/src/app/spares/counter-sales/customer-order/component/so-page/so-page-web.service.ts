import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SaveSalesOrder, SoPatchJson } from '../../domain/so.domain';
import { SoApi } from '../../url-utils/so-urls';
import { Observable,BehaviorSubject } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { DateService } from 'src/app/root-service/date.service';

@Injectable()
export class SoPageWebService {
    private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}`;
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }
  postSaveSoDetails(formData: SaveSalesOrder) {
    return this.httpClient.post(SoApi.saveSpareSalesOrder, formData)
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }
  // getQuotationById(id): Observable<SoPatchJson> {
  //   const getSalesOrderById = `${SoApi.getSalesOrderById}/spares/quotation/getSalesOrderById/${id}`
  //   return this.httpClient.get<BaseDto<SoPatchJson>>(getSalesOrderById).pipe(map(dto => dto.result))
  // }
  getSoViewdata(id: number): Observable<SoPatchJson> {
    return this.httpClient.get<BaseDto<SoPatchJson>>(`${SoApi.getSalesOrderById}/${id}`).pipe(map(dto => {
      dto.result.partDetailsForSalesOrder[0]['pickListNo'] = '12213';
    return  dto.result
    }))
  }
  deletePart(partId: string) {
    console.log("partId ", partId);
    return this.httpClient.get(SoApi.deletePart, {
      params: new HttpParams()
        .set('partId', partId)
    })
  }
  print(soNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printUrl+"/printSalesOrder", {
          params: new HttpParams().set('soNumber', soNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
