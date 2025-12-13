import { Injectable } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpParams, HttpClient, HttpResponse } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { DateService } from '../../../../../root-service/date.service';
import { SearchDcModal } from 'search-delivery-challan';
import { Observable } from 'rxjs';

@Injectable()
export class SearchDeliveryChallanService {
  private readonly searchByDcNoUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDeliveryChallanNumberAutoComplete}`;
  private readonly searchByChassisNoUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcChassisNumberAutoComplete}`;
  private readonly searchByCustomerNameUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcCustomerNameAutoComplete}`;
  private readonly searchByMobileNoUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcCustomerMobileNumberAutoComplete}`;
  private readonly searchByEnquiryNoUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcEnquiryNumberAutoComplete}`;
  private readonly searchByItemNoUrl = `${environment.baseUrl}${urlService.api}${ urlService.master }${ urlService.product }${ urlService.machineMaster }${ urlService.autoCompleteItems }`;
  private readonly searchByEngineNoUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcEngineNumberAutoComplete}`;
  private readonly searchDeliveryChallanUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.dcSearch}`;

  private readonly getAllEnquiryTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getEnquiryType}`;
  private readonly getAllDcStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcStatusDropDown}`;
  private readonly getAllProductsUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;
  private readonly getAllSeriesUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSeries}`;
  private readonly getAllModelsUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllModel}`;
  private readonly getAllSubModelsUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllSubModel}`;
  private readonly getAllVariantsUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`;

  // private readonly dcDownloadExcelReport = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.dcDownloadExcelReport}`;

  private readonly dcDownloadExcelReport = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.dcDownloadExcelReport}`;

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  createSearchDeliveryChallanForm() {
    return this.formBuilder.group({
      deliveryChallanNumber: [null, Validators.compose([])],
      chassisNumber: [null, Validators.compose([])],
      customerName: [null, Validators.compose([])],
      customerMobileNumber: [null, Validators.compose([])],
      dcFromDate: [null, Validators.compose([])],
      dcToDate: [null, Validators.compose([])],
      dealer:[],
      orgHierLevel1 : [],
      orgHierLevel2 : [],
      orgHierLevel3 : [],
      orgHierLevel4 : [],
      orgHierLevel5 : [],
      enquiryNumber: [null, Validators.compose([])],
      enquiryType: [null, Validators.compose([])],
      dcStatus: [null, Validators.compose([])],
      product: [null, Validators.compose([])],
      series: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      subModel: [null, Validators.compose([])],
      variant: [null, Validators.compose([])],
      itemNumber: [null, Validators.compose([])],
      engineNumber: [null, Validators.compose([])],
    })
  }

  searchByDcNo(searchKey: string) {
    return this.httpClient.get(this.searchByDcNoUrl,
      { params: new HttpParams().set('dcNumber', searchKey) })
  }
  searchByChassisNo(searchKey: string) {
    return this.httpClient.get(this.searchByChassisNoUrl,
      { params: new HttpParams().set('chassisNo', searchKey) })
  }
  searchByCustomerName(searchKey: string) {
    return this.httpClient.get(this.searchByCustomerNameUrl,
      { params: new HttpParams().set('customerName', searchKey) })
  }
  searchByMobileNo(searchKey: string) {
    return this.httpClient.get(this.searchByMobileNoUrl,
      { params: new HttpParams().set('customerMobileNumber', searchKey) })
  }
  searchByEnquiryNo(searchKey: string) {
    return this.httpClient.get(this.searchByEnquiryNoUrl,
      { params: new HttpParams().set('enquiryNumber', searchKey) })
  }
  searchByItemNo(searchKey: string) {
    return this.httpClient.get(this.searchByItemNoUrl,
      { params: new HttpParams().set('itemNo', searchKey)
                    .set('productGroup','')
                    .set('functionality','DC_SEARCH')
      }
    )
  }
  searchByEngineNo(searchKey: string) {
    return this.httpClient.get(this.searchByEngineNoUrl,
      { params: new HttpParams().set('engineNumber', searchKey) })
  }
  searchDeliveryChallan(dcSearchData: object) {
    console.log("dcSearchData ", dcSearchData);
    Object.keys(dcSearchData).forEach(key => {
      if (dcSearchData[key] !== null) {
        if ((key === 'dcFromDate') || (key === 'dcToDate')) {
          dcSearchData[key] = this.dateService.getDateIntoYYYYMMDD(dcSearchData[key]);
        }
      }
    });
    return this.httpClient.post(this.searchDeliveryChallanUrl, dcSearchData)
  }
  getAllEnquiryType() {
    return this.httpClient.get(this.getAllEnquiryTypeUrl)
  }
  getAllDcStatus() {
    return this.httpClient.get(this.getAllDcStatusUrl)
  }
  getAllProducts() {
    return this.httpClient.get(this.getAllProductsUrl)
  }
  getAllSeries() {
    return this.httpClient.get(this.getAllSeriesUrl)
  }
  getAllModels() {
    return this.httpClient.get(this.getAllModelsUrl)
  }
  getAllSubModels() {
    return this.httpClient.get(this.getAllSubModelsUrl)
  }
  getAllVariants() {
    return this.httpClient.get(this.getAllVariantsUrl)
  }

  downloadDcExcelReport(searchObject:SearchDcModal) : Observable<HttpResponse<Blob>> {
    return this.httpClient.post<Blob>(this.dcDownloadExcelReport,searchObject, { 
        observe: 'response', responseType: 'blob' as 'json' }
    );  
}
}
