import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { SearchSpareGrn } from '../../domain/spare-grn.domain';
import { SparesGrnUrl } from '../../url-util/spares-grn.url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SelectList } from '../../../../../core/model/select-list.model';
import { SparesGrnApiService } from '../spares-grn/spares-grn-api.service';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';

@Injectable()
export class SparesGrnSearchServiceApi {

  private sparesGrnApiService: SparesGrnApiService;
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) {
    this.sparesGrnApiService = new SparesGrnApiService(this.httpClient);
  }
  searchSpareGrn(searchField: SearchSpareGrn) {
    return this.httpClient.post<BaseDto<object[]>>(SparesGrnUrl.searchSpareGrn, searchField);
  }
  searchBySpareGrnNumber(grnNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.searchBySpareGrnNumber, {
      params: new HttpParams().set('grnNumber', grnNumber)
    }).pipe(map(res => res.result));
  }
  searchInvoiceNumberFromGrn(invoiceNumber: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.searchInvoiceNumberFromGrn, {
      params: new HttpParams().set('invoiceNumber', invoiceNumber)
    }).pipe(map(res => res.result));
  }
  getGrnStatus() {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getGrnStatus).pipe(
      map(res => res.result)
    );
  }
  getSupplierName(supplierType:string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.searchSupplierName,{
        params: new HttpParams().set('supplierType', supplierType)
            .set('supplierName','')
    }).pipe(
      map(res => res.result)
    );
  }
  getGrnType() {
    return this.sparesGrnApiService.getGrnType();
  }
  getSuppilerType(grnType: string) {
    return this.sparesGrnApiService.getSuppilerType(grnType);
  }
  getServerDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
  downloadExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(SparesGrnUrl.downloadReport, filter,
      {observe: 'response', responseType: 'blob' as 'json' }
    )
  }

}
