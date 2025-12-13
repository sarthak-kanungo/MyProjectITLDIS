import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SparesSalesInvoiceApi } from '../../url-utils/spares-sales-invoice-api';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';

@Injectable()
export class SparesSalesInvoiceSearchPageWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  public searchSparesInvoice(formValues: object) {
    this.removeNullFromObjectAndFormatDate(formValues);
    return this.httpClient.post(SparesSalesInvoiceApi.searchSpareInvoice, formValues)
  }
  
  public cancelInvoice(remark:string, invoiceId:string, referenceType:string) {
    return this.httpClient.post(SparesSalesInvoiceApi.cancelInvoice, {'remark':remark,'id':invoiceId, 'referenceType':referenceType});
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "")) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }
  // public getSparesInvoiceCount(formValues: object) {
  //   return this.httpClient.post(SparesSalesInvoiceUrl.dbEntityController, formValues)
  // }

  downloadExcelReport(filter): Observable<HttpResponse<Blob>>{
      return this.httpClient.post<Blob>(SparesSalesInvoiceApi.downloadReportExcelUrl, filter,
        {observe: 'response', responseType: 'blob' as 'json' }
      )
  }
}
