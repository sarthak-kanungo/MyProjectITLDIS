import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { BehaviorSubject, Observable } from 'rxjs';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { TollFreeCallUrls } from '../url-util/toll-free-urls';

@Injectable({
  providedIn: 'root'
})
export class TollFreeService {

  public fetchCustomerMachineDtlSubject:BehaviorSubject<object> = new BehaviorSubject<object>({customerId:null,vinId:null});
  public fetchServiceCallHistorySubject:BehaviorSubject<object> = new BehaviorSubject<object>({customerId:null,vinId:null});
  
  public fetchDealerDtlSubject:BehaviorSubject<object> = new BehaviorSubject<object>(null);
  
  public jcPcrWcr:BehaviorSubject<string> = new BehaviorSubject<string>('');
  
  private readonly dealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}${urlService.dealerCodeUrl}`
  
  constructor(private httpClient: HttpClient) { }

  getDealerDetails():Observable<BaseDto<Object>>{
    return this.httpClient.get(TollFreeCallUrls.dealerDetails) as Observable<BaseDto<Object>>;
  }

  getLookupByCode(code){
    return this.httpClient.get(TollFreeCallUrls.lookupurl, {
      params : new HttpParams().set("code",code)
    })
  }

  getVillageListAutoSearch(searchValue, districtId:any){
    return this.httpClient.get(TollFreeCallUrls.villageautosearchurl, {
      params : new HttpParams().set("code",searchValue)
        .set("districtId",districtId)
    })
  }

  getDealerCodeList(dealerCode:string){
    return this.httpClient.get(`${this.dealerCodeUrl}`, {
        params: new HttpParams().set('dealerCode', dealerCode)
      })
  }


  getDistrictList(showAll?:string){
    if(typeof showAll == undefined){
      showAll = 'N';
    }
    return this.httpClient.get(TollFreeCallUrls.getDistrictList,{
      params : new HttpParams().set("showAll",showAll)
    })
  }

  getPinDtl(cityId:any, pincodeId:any){
    return this.httpClient.get(TollFreeCallUrls.getPincodeDetailurl, {
      params : new HttpParams().set("pincodeId",pincodeId)
        .set("cityId",cityId)
    })
  }

  getTsmDetails(pincode:any){
    return this.httpClient.get(TollFreeCallUrls.getTsmDetails, {
      params : new HttpParams().set("pincode",pincode)
    })
  }

  getDealerDetailsById(id:any){
    return this.httpClient.get(TollFreeCallUrls.getDealerDetails, {
      params : new HttpParams().set("id",id)
    })
  }

  getAutoCompleteMobileNumber(searchValue:any,requestFrom:any){
    return this.httpClient.get(TollFreeCallUrls.autocompleteMobileNumber, {
      params : new HttpParams().set("mobileNumber",searchValue).set("requestFrom",requestFrom)
    })
  }

  getCustomerDtl(customerCode:any){
    return this.httpClient.get(TollFreeCallUrls.getCustomerDetails, {
      params : new HttpParams().set("customerCode",customerCode)
    })
  }

  getMachineDtl(customerId:any){
    return this.httpClient.get(TollFreeCallUrls.getCustomerMachineDetails, {
      params : new HttpParams().set("customerId",customerId)
    })
  }
  
  getServiceHistory(customerId:any, vinId:any){
    return this.httpClient.get(TollFreeCallUrls.getCustomerServiceHistory, {
      params : new HttpParams().set("customerId",customerId)
      .set("vinId",vinId)
    })
  }

  getCallHistory(customerId:any, vinId:any){
    return this.httpClient.get(TollFreeCallUrls.getCustomerCallHistory, {
      params : new HttpParams().set("customerId",customerId).set("vinId",vinId)
    })
  }
  getDirectSurveyDetails(customerId:any, vinId:any){
    return this.httpClient.get(TollFreeCallUrls.getDirectSurveyDetails, {
      params : new HttpParams().set("customerId",customerId).set("vinId",vinId)
    })
  }
  saveCallDetails(saveData:any, file:any[]){

    const formData: FormData = new FormData();
    const headers = new HttpHeaders();
    formData.append('callData', new Blob([JSON.stringify(saveData)], { type: 'application/json' }))
    if (file) {
      file.forEach(element => {
          formData.append('complaintRecording', element['file'])
      });
    }

    headers.append('Content-Type', null);
    headers.append('Accept', 'multipart/form-data');

    return this.httpClient.post(TollFreeCallUrls.saveCallDetails, formData, {headers});
  }

  searchCallDetails(filterData:any){
    return this.httpClient.post(TollFreeCallUrls.searchCallDetails, filterData);
  }

  fetchCallDetails(callId:any){
    return this.httpClient.get(TollFreeCallUrls.fetchCallDetails, {
      params : new HttpParams().set("callId",callId)
    })
  }

  complaintReportingList(dealerId, department){
    return this.httpClient.get(TollFreeCallUrls.getComplaintReportingList, {
      params : new HttpParams().set("dealerId",dealerId).set("department", department)
    })
  }
  exportReport(searchObj){
    return this.httpClient.post(TollFreeCallUrls.tollFreeReportExportUrl, searchObj,
      {observe: 'response', responseType: 'blob' as 'json' });
  }
}
