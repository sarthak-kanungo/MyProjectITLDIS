import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { PartQuotationApi } from '../../url-util/part-quotation-url';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { ViewPartQuotation, SaveAndSubmitQuotation } from '../../domain/part-quotation-domain';
import { map } from 'rxjs/operators';

import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class PartQuotationPageWebService {
  private readonly printQuotationUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printQuotationurl}`;
  constructor(
    private httpClient : HttpClient
  ) { }

  saveQuotation(formData: SaveAndSubmitQuotation) {
    return this.httpClient.post(PartQuotationApi.saveQuotation, formData)
  }

  getQuotationById(id: string): Observable<ViewPartQuotation> {
    return this.httpClient.get<BaseDto<ViewPartQuotation>>(`${PartQuotationApi.getQuotationById}/${id}`
     ).pipe(map(dto => dto.result))
  }
  printQuotation(quoNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printQuotationUrl, {
          params: new HttpParams().set('quotationNumber', quoNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
