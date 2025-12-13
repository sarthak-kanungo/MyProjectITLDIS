import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseDto } from "BaseDto";
import { BehaviorSubject, Observable } from "rxjs";
import { DateService } from "src/app/root-service/date.service";
import { urlService } from "src/app/webservice-config/baseurl";
import { environment } from "src/environments/environment";
import { SalesReportApi } from "../url-utils/sales-report-url";

@Injectable()
  export class SalesReportService {
    private static readonly branchUrl = `${environment.baseUrl}${urlService.api}/service/report/getBranchesUnderUser`
    private static readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/marketingActivityProposal/getDealerCodeAutoComplete`
    private static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
    private static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
    private static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
    private static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
    private static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
    private static readonly autoCompleteSearchChassisNo = `${environment.baseUrl}/${environment.api}/service/jobcard/autoCompleteSearchChassisNo`;
    private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelByHODept`
    private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelHierForParent`    
   
    clearFormSubject:BehaviorSubject<string> = new BehaviorSubject<string>('');
    constructor(private httpClient: HttpClient,
      private dateService: DateService  
    ) { }

    searchMachineMasterReport(searchDetail): Observable<BaseDto<Array<any>>> {
        return this.httpClient.post<BaseDto<Array<any>>>(SalesReportApi.searchMachineMasterReport, searchDetail)
      }

    downloadMachineMasterReport(filter): Observable<HttpResponse<Blob>>{
      return this.httpClient.post<Blob>(SalesReportApi.downloadMachineMasterReport, filter,
          {observe: 'response', responseType: 'blob' as 'json' }
        )
    }

    autoCompleteSearchChassisNo(chassisNo: string){
      return this.httpClient.get(SalesReportService.autoCompleteSearchChassisNo, {
        params: new HttpParams().set('chassisNo', chassisNo)
      });
    }
  
    getAllProduct() {
      return this.httpClient.get(SalesReportService.getAllProduct)
    }
  
    getSeriesByProduct(product: string){
      return this.httpClient.get(SalesReportService.getSeriesByProduct,
        {
          params: new HttpParams()
            .append('product', product)
        })
    }
  
    getModelBySeries(series: string){
      return this.httpClient.get(SalesReportService.getModelBySeries,
        {
          params: new HttpParams()
            .append('series', series)
        })
    }
  
    getSubModelByModel(model: string){
      return this.httpClient.get(SalesReportService.getSubModelByModel,
        {
          params: new HttpParams()
            .append('model', model)
        })
    }
  
    getVariantBySubModel(subModel: string){
      return this.httpClient.get(SalesReportService.getVariantBySubModel,
        {
          params: new HttpParams()
            .append('subModel', subModel)
        })
    }
  
    getDealerCodeList(dealerCode:string){
      return this.httpClient.get(SalesReportService.dealerCodeUrl, {
          params: new HttpParams().set('dealerCode', dealerCode)
      })
    }
    getBranchCodeList(){
      return this.httpClient.get(SalesReportService.branchUrl)
    }
  
    getLevelByDepartment(departmentCode:any) {
      return this.httpClient.get(this.getLevelDropUrl, {
        params: new HttpParams().set('deptCode', departmentCode)
      })
    }
  
    getHierarchyByLevel(levelId:any,orgHierId){
      return this.httpClient.get(this.getHierDropUrl, {
        params: new HttpParams().set('levelId', levelId).set('hierId',orgHierId),
        
      })
    }
  
    getSystemGeneratedDate() {
      return this.dateService.getSystemGeneratedDate(this.httpClient)
    }
  }