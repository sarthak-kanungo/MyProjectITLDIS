import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SparePoApi } from '../../url-utils/spares-purchse-order-urls';
import { map } from 'rxjs/operators';
import { Platform } from '@angular/cdk/platform';
import { SelectList } from '../../../../../core/model/select-list.model';
import { LoginFormService } from '../../../../../root-service/login-form.service';

@Injectable()
export class SparesPurchaseOrderWebService {
  private dealerCode: string;

  constructor(
    private httpClient: HttpClient,
    private loginService: LoginFormService,
    private platForm: Platform
  ) {
    if (this.platForm.isBrowser) {
      this.dealerCode = this.loginService.getLoginUserDealerCode();
    }
  }
  public getVendorNameList(searchKey: string){
      return this.httpClient.get(SparePoApi.getVendorNameAutoComplete, {
          params: new HttpParams().set('vendorName', searchKey)
        }).pipe(map(res => res['result']))
  }
  
  public getSupplierNameList(searchKey: string) {
    return this.httpClient.get(SparePoApi.getSupplierNameAutoComplete, {
      params: new HttpParams().set('supplierName', searchKey)
    }).pipe(map(res => res['result']))
  }
  // public getOrderPlanningList(searchKey: string) {
  //   return this.httpClient.get(SparePoApi.getOrderPlanningComplete, {
  //     params: new HttpParams().set('planningNumber', searchKey)
  //   }).pipe(map(res => res['result']))
  // }
  public getJobCardAutoList(searchKey: string) {
    return  this.httpClient.get(SparePoApi.getJobCardAutoComplete, {
      params: new HttpParams().set('jobCardNo', searchKey)
    }).pipe(map(res => res['result']));
  }
  public getOrderTypes() {
    return this.httpClient.get(SparePoApi.getOrderTypeDropdown)
  }
  public getModeOfTransport() {
    return this.httpClient.get(SparePoApi.getTransferModeDropdown)
  }
  public getTransportNames() {
    return this.httpClient.get(SparePoApi.getTransporterDropdown)
  }
  public getDealerOutstandingDetails(poId:any) {
    if(poId === undefined) {
        return this.httpClient.get(SparePoApi.getDealerOutStandingDetails)  
    }  
    return this.httpClient.get(SparePoApi.getDealerOutStandingDetails, {
      params: new HttpParams().set('poId', poId)
    })
    // .pipe(map(res => [res['result']]))
  }
  public getSupplierType() {
    return this.httpClient.get(SparePoApi.getSupplierTypeDropdown)
  }
  public getOrderTypesFromSupplierType(supplierType: string) {
    return this.httpClient.get(SparePoApi.getOrderTypeBySupplierType, {
      params: new HttpParams().set('supplierType', supplierType)
    })
  }
  public getPriceTypeForCoDealer() {
    return this.httpClient.get(SparePoApi.getSparePriceTypeDropdown)
  }
  public getDealerCodeAutocompleteList(searhKey: string) {
    return this.httpClient.get(SparePoApi.getDealerCodeAutocomplete, {
      params: new HttpParams().set('dealerCode', searhKey)
    }).pipe(map(res => res['result']));
  }
  public getJobcardDetailsFromJobCardId(jobCardId: string) {
    return this.httpClient.get(SparePoApi.getItemDetailsByJobCardId, {
      params: new HttpParams().set('jobCardId', jobCardId)
    })
  }
  // Order Planning Sheet By Ankit
  public getOrderPlanningSheetNo(opsNo:string){
    return  this.httpClient.get(SparePoApi.autoSearchOpNo, {
      params: new HttpParams().set('opsNo', opsNo)
    }).pipe(map(res => res['result']));
  }
  // get Item Details for OP sheet Number
  public getOrderPlanningSheetDetails(opsId:string,priceType:string,existingItems:any){
    return  this.httpClient.get(SparePoApi.getItemDetailsBaseonOp, {
      params: new HttpParams().set('opsId', opsId).set('priceType',priceType).set('existingItems',existingItems)
    }).pipe(map(res => res['result']));
  }
  getOPSitemsDeatail
  public compareOrderTypeFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1['orderType'] === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2['orderType'];
    }
    return c1 && c2 ? c1['orderType'] === c2['orderType'] : c1 === c2;
  }
}
