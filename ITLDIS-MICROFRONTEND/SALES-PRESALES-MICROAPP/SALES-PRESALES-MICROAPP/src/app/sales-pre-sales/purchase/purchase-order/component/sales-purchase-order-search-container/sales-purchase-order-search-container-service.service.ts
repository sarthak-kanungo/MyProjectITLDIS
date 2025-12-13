import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';

@Injectable()
export class SalesPurchaseOrderSearchContainerServiceService {

  private readonly getPoNumberAutocompleteUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.searchPoNumber}`;
  private readonly searchPoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.searchBy}`;
  private readonly getItemNumberAutocompleteUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getItemNo}`;
  private readonly getAllSubmodelsListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSubModel}`;
  private readonly getAllProductsListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;
  private readonly getAllVariantsListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`;
  private readonly getAllSeriesListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSeries}`;
  private readonly getAllModelsListUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllModel}`;
  private readonly getAllDealerListUrl = `${environment.baseUrl}${urlService.api}${urlService.purchaseOrderType}${urlService}`;
  private readonly getAllPoStatusListUrl = `${environment.baseUrl}${urlService.api}${urlService.purchaseOrderStatus}`;
  private readonly getAllPoTypesListUrl = `${environment.baseUrl}${urlService.api}${urlService.purchaseOrderType}`;
  private readonly getAllDepoListUrl = `${environment.baseUrl}${urlService.api}${urlService.depot}`;
  
  private readonly downloadPoExcelReport = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.downloadPoExcelReport}`;

  constructor(
    private httpClient: HttpClient,
    public dateService: DateService
  ) { }

  searchPo(searchObject: object) {
    let params = new HttpParams();
    Object.keys(searchObject).forEach(key => {
    //   if (!!searchObject[key] || `${searchObject[key]}` === '0') {
    //     if ((key === 'fromDate') || (key === 'toDate')) {
    //       params = params.set(key, this.dateService.getDateIntoDDMMYYYY(searchObject[key]));
    //     }
    //     else
          params = params.set(key, searchObject[key])
    //   }

    });
    return this.httpClient.get(this.searchPoUrl, { params: params });
  }
  getPoNumberAutocompleteList(searchKey: string) {
    return this.httpClient.get(this.getPoNumberAutocompleteUrl, { params: new HttpParams().set('poNumber', searchKey) });
  }
  getItemNumberAutocompleteList(searchKey: string) {
    return this.httpClient.get(this.getItemNumberAutocompleteUrl, { params: new HttpParams().set('itemNo', searchKey) });
  }
  getAllPoTypesList() {
    return this.httpClient.get(this.getAllPoTypesListUrl)
  }
  getAllDepoList() {
    return this.httpClient.get(this.getAllDepoListUrl)
  }
  getAllPoStatusList() {
    return this.httpClient.get(this.getAllPoStatusListUrl)
  }
  getAllDealerList() {
    return this.httpClient.get(this.getAllDealerListUrl)
  }
  getAllProductsList() {
    return this.httpClient.get(this.getAllProductsListUrl)
  }
  getAllSeriesList() {
    return this.httpClient.get(this.getAllSeriesListUrl)
  }
  getAllModelsList() {
    return this.httpClient.get(this.getAllModelsListUrl)
  }
  getAllSubmodelsList() {
    return this.httpClient.get(this.getAllSubmodelsListUrl)
  }
  getAllVariantsList() {
    return this.httpClient.get(this.getAllVariantsListUrl)
  }
  // convertDateToDDMMYYYY(simpleDate: Date) {
  //   let day = new Date(simpleDate).getDate();
  //   let month = new Date(simpleDate).getMonth() + 1;
  //   let year = new Date(simpleDate).getFullYear();
  //   let finalDate = `${day}-${month}-${year}`;
  //   return finalDate;
  // }

  downloadPOExcelReport(filter): Observable<HttpResponse<Blob>>{
    return this.httpClient.post<Blob>(this.downloadPoExcelReport, filter,
       {observe: 'response', responseType: 'blob' as 'json' }
    )
  }
}
