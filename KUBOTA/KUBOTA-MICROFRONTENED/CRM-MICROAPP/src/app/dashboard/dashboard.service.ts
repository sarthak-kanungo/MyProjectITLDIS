import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DateService } from '../root-service/date.service';
import { BaseDto, UserFunctionality } from '../webservice-config/basedto';
import { urlService } from '../webservice-config/baseurl';

@Injectable()
export class DashboardService {
  private static readonly branchUrl = `${environment.baseUrl}${urlService.api}${urlService.service}/report/getBranchesUnderUser`
  private static readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/marketingActivityProposal/getDealerCodeAutoComplete`
  private static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
  private static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
  private static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
  private static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
  private static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
  private static readonly autoCompleteSearchChassisNo = `${environment.baseUrl}/${environment.api}${urlService.service}/jobcard/autoCompleteSearchChassisNo`;
  private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelByHODept`
  private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}/salesandpresales/marketingActivityProposal/getOrgLevelHierForParent`    
  private static readonly serviceReportUrl = `${environment.baseUrl}${urlService.api}/dashboard/serviceReport`;
  private static readonly salesReportUrl = `${environment.baseUrl}${urlService.api}/dashboard/salesReport`;
  private static readonly warrantyReportUrl = `${environment.baseUrl}${urlService.api}/dashboard/warrantyReport`;
  private static readonly marketingReportUrl = `${environment.baseUrl}${urlService.api}/dashboard/marketingReport`;
  private static readonly enquiryStatusReportUrl = `${environment.baseUrl}${urlService.api}/dashboard/enquiryStatusReport`;
  
  private static readonly getFunctionalityMappedToUserUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.kubotauser }${ urlService.getFunctionalityMappedToUser }`;
 
  constructor(private httpClient: HttpClient,
    private dateService: DateService) { }

  autoCompleteSearchChassisNo(chassisNo: string){
    return this.httpClient.get(DashboardService.autoCompleteSearchChassisNo, {
      params: new HttpParams().set('chassisNo', chassisNo)
    });
  }

  getAllProduct() {
    return this.httpClient.get(DashboardService.getAllProduct)
  }

  getSeriesByProduct(product: string){
    return this.httpClient.get(DashboardService.getSeriesByProduct,
      {
        params: new HttpParams()
          .append('product', product)
      })
  }

  getModelBySeries(series: string){
    return this.httpClient.get(DashboardService.getModelBySeries,
      {
        params: new HttpParams()
          .append('series', series)
      })
  }

  getSubModelByModel(model: string){
    return this.httpClient.get(DashboardService.getSubModelByModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariantBySubModel(subModel: string){
    return this.httpClient.get(DashboardService.getVariantBySubModel,
      {
        params: new HttpParams()
          .append('subModel', subModel)
      })
  }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }
  
  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(DashboardService.dealerCodeUrl, {
        params: new HttpParams().set('dealerCode', dealerCode)
    })
  }
  getBranchCodeList(){
    return this.httpClient.get(DashboardService.branchUrl)
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

  getSearchRecords(searchObj, dashboardType:string){
    if(dashboardType=='Service')
      return this.httpClient.post(DashboardService.serviceReportUrl, searchObj);
    if(dashboardType=='Sales')
      return this.httpClient.post(DashboardService.salesReportUrl, searchObj);
    if(dashboardType=='Warranty')
      return this.httpClient.post(DashboardService.warrantyReportUrl, searchObj);  
    if(dashboardType=='Marketing')
      return this.httpClient.post(DashboardService.marketingReportUrl, searchObj); 
    if(dashboardType=='EnquiryStatus')
      return this.httpClient.post(DashboardService.enquiryStatusReportUrl, searchObj);    
  }

  getFunctionalityMappedToUser(loginId){
    return this.httpClient.get<BaseDto<UserFunctionality[]>>(`${DashboardService.getFunctionalityMappedToUserUrl}/${loginId}`);
  }
}
