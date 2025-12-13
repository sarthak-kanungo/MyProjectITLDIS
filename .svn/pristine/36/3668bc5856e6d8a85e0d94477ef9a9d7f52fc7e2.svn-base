import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { BehaviorSubject, Observable } from 'rxjs';
import { CustomerCareExeCallUrls } from '../url-util/delear-customer-care-ex-call-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class DelearCustomerCareExCallService {

  public fetchCustomerMachineDtlSubject:BehaviorSubject<object> = new BehaviorSubject<object>({customerId:null,vinId:null});
  public fetchServiceCallHistorySubject:BehaviorSubject<object> = new BehaviorSubject<object>({customerId:null,vinId:null});

  public jcPcrWcr:BehaviorSubject<string> = new BehaviorSubject<string>('');
  
  constructor(private httpClient:HttpClient) { }

  getDealerDetails():Observable<BaseDto<Object>>{
    return this.httpClient.get(CustomerCareExeCallUrls.dealerDetails) as Observable<BaseDto<Object>>;
  }

  // getLookupByCode(code){
  //   return this.httpClient.get(CustomerCareExeCallUrls.lookupurl, {
  //     params : new HttpParams().set("code",code)
  //   })
  // }

  getVillageListAutoSearch(searchValue, districtId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.villageautosearchurl, {
      params : new HttpParams().set("code",searchValue)
        .set("districtId",districtId)
    })
  }
  getPinDtl(cityId:any, pincodeId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getPincodeDetailurl, {
      params : new HttpParams().set("pincodeId",pincodeId)
        .set("cityId",cityId)
    })
  }

  getAutoCompleteMobileNumber(searchValue:any){
    return this.httpClient.get(CustomerCareExeCallUrls.autocompleteMobileNumber, {
      params : new HttpParams().set("mobileNumber",searchValue)
    })
  }

  getAutoCompletesSBMobileNumber(searchValue:any){
    return this.httpClient.get(CustomerCareExeCallUrls.autoSBSearchCustomerMobileNo, {
      params : new HttpParams().set("mobileNumber",searchValue)
    })
  }
  getCustomerDtl(customerCode:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getCustomerDetails, {
      params : new HttpParams().set("customerCode",customerCode)
    })
  }

  getMachineDtl(customerId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getCustomerMachineDetails, {
      params : new HttpParams().set("customerId",customerId)
    })
  }
  
  getServiceHistory(customerId:any, vinId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getCustomerServiceHistory, {
      params : new HttpParams().set("customerId",customerId)
                               .set("vinId",vinId)
    })
  }

  getCallHistory(customerId:any, vinId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getCustomerCallHistory, {
      params : new HttpParams().set("customerId",customerId)
                               .set("vinId",vinId)
    })
  }

  saveCallDetails(saveData:any){
    return this.httpClient.post(CustomerCareExeCallUrls.saveCallDetails, saveData);
  }

  searchCallDetails(filterData:any){
    return this.httpClient.post(CustomerCareExeCallUrls.searchCallDetails, filterData);
  }

  getViewData(crmNo:any,callTypeId:any){
    return this.httpClient.get(CustomerCareExeCallUrls.viewCrmData, {
      params : new HttpParams().set("crmNo",crmNo).set("callTypeId",callTypeId)
      
    })
  }

  getCallTypeList() {
    return this.httpClient.get(CustomerCareExeCallUrls.getCallType)
  }

  getCallStatusList() {
    return this.httpClient.get(CustomerCareExeCallUrls.getCallStatus)
  }

  getSystemDate(){
    return this.httpClient.get(CustomerCareExeCallUrls.getSystemDateUrl)
  }

  getModel(model: string): Observable<Array<any>> {
    return this.httpClient.get<BaseDto<Array<any>>>(CustomerCareExeCallUrls.getModel,
      {
        params: new HttpParams()
          .append('model', model)
      }).pipe(map(dto => dto.result))
  }
  getSubModel(model: string): Observable<BaseDto<Array<any>>> {
    return this.httpClient.get<BaseDto<Array<any>>>(CustomerCareExeCallUrls.getSubModel,
      {
        params: new HttpParams()
          .append('model', model)
      })
  }

  getVariant(model: string, subModel: string): Observable<Array<any>> {
    return this.httpClient.get<BaseDto<Array<any>>>(CustomerCareExeCallUrls.getVariant,
      {
        params: new HttpParams()
          .append('model', model)
          .append('subModel', subModel)
      }).pipe(map(dto => dto.result))
  }
  getQuesionnaire(typeOfCallId:string):Observable<BaseDto<Array<any>>>{
    return this.httpClient.get<BaseDto<Array<any>>>(CustomerCareExeCallUrls.getQuesionnaire,
      {
        params: new HttpParams()
          .append('typeOfCallId', typeOfCallId)
      })
  }

  getCCECallHistory(chassisNo:any,typeOfCall:any){
    return this.httpClient.get(CustomerCareExeCallUrls.getCallHistory, {
      params : new HttpParams().set("typeOfCall",typeOfCall).set("chassisNo",chassisNo)
    })
  }

  autoCompleteMobileNo(mobileNo:any){
    return this.httpClient.get(CustomerCareExeCallUrls.searchMobileNo,{
      params : new HttpParams().set("mobileNumber",mobileNo)
    })
  }

  autoCompleteCustomerName(name:any){
    return this.httpClient.get(CustomerCareExeCallUrls.searchCustomerName,{
      params : new HttpParams().set("customerName",name)
    })
  }
  autoSBSearchCustomerName(customerName:any){
    return this.httpClient.get(CustomerCareExeCallUrls.autoSBSearchCustomerName,{
      params : new HttpParams().set("customerName",customerName)
    })
  }

  autoSearchChassisNumber(chassisNumber:any){
    return this.httpClient.get(CustomerCareExeCallUrls.autoSearchChassisNumber,{
      params : new HttpParams().set("chassisNumber",chassisNumber)
    })
  }

  autoCompleteServiceDueType(text:any){
    return this.httpClient.get(CustomerCareExeCallUrls.searchServiceDueType,{
      params : new HttpParams().set("currentServiceDueType",text)
    })
  }

  getServiceBookingRecords(searchObj){
    return this.httpClient.post(CustomerCareExeCallUrls.getServiceBookingRecords, searchObj);
  }

   searchJobCardList(jobCardData:any){
    return this.httpClient.post(CustomerCareExeCallUrls.getJobCardList, jobCardData);
  }


  getDcList(dcList:any){
    return this.httpClient.post(CustomerCareExeCallUrls.getDcList, dcList);
  }

  


}
