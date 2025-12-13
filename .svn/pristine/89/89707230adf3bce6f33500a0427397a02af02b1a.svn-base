import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { CurrentStockaApi } from '../../url-utils/current-stock-urls';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CurrentStockService {

  // private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}/printBinToBin`;
  private readonly searchItemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemNumberModelProductSeries}`;
  private readonly getDetailsByItemIdUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getDetailsByItemId}`;

  constructor(public httpClient: HttpClient) { }
  searchCurrentStock(formData: any) {
    return this.httpClient.post(CurrentStockaApi.searchCurrentStock, formData)
  }

  downloadExcelReport(searchObject: any): Observable<HttpResponse<Blob>> {
    return this.httpClient.post<Blob>(CurrentStockaApi.downloadExcelReport, searchObject, {
      observe: 'response', responseType: 'blob' as 'json'
    }
    );
  }
  searchItemNo(search) {
    return this.httpClient.get(`${this.searchItemNoUrl}`, {
      params: new HttpParams().set('search', search)
    });
  }

  getDetailsByItemId(itemId) {
    return this.httpClient.get(`${this.getDetailsByItemIdUrl}/${itemId}`, {
    })
  }
}
