import { Injectable } from '@angular/core';
import { HttpClient, HttpParams,HttpResponse } from '@angular/common/http';
import { SearchBtBt } from '../../domain/bin-to-bin-transfer';
import { BtBtApi } from '../../url-utils/btbt-urls';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class BtbtSearchPageWebService {
   private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}/printBinToBin`;
    
  constructor(public httpClient: HttpClient) { }
  searchBtBt(formData: SearchBtBt) {
    return this.httpClient.post(BtBtApi.searchBinTransfer, formData)
  }
  
  searchTransferNumber(transferNo:string) {
      return this.httpClient.get(BtBtApi.searchAutoTransferNumber, {
          params : new HttpParams()
                      .set("transferNo",transferNo)
      }).pipe(map(dto => dto['result']))
  }
  downloadExcelReport(searchObject:SearchBtBt) : Observable<HttpResponse<Blob>> {
      return this.httpClient.post<Blob>(BtBtApi.downloadExcelReport,searchObject, { 
          observe: 'response', responseType: 'blob' as 'json' }
      );  
  }
  printPO(printStatus:string, transferNo:string){
      return this.httpClient.get<Blob>(this.printUrl, {
          params: new HttpParams().set('transferNo', transferNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
