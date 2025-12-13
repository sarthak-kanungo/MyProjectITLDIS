import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ViewJobCard, FinalSaveJobCard, ServiceJobCard, DropDownCategory } from '../../domain/job-card-domain';
import { UpdateJobCard } from '../../domain/job-card-update-domain';
import { element } from 'protractor';

@Injectable()
export class JobCardPageWebService {

  constructor(private httpClient: HttpClient) { }
  // saveJobCardPreSales(formBody: FinalSaveJobCard) {
  //   return this.httpClient.post(JobCardUrl.saveJobcard, formBody)
  // }

  saveJobCardPreSales(formBody: FinalSaveJobCard) {
    console.log('formBody', formBody)

    let formData: FormData = new FormData();
    formData.append('serviceJobCard', new Blob([JSON.stringify(formBody.serviceJobCard)], { type: 'application/json' }))
    if (formBody.fsCouponPage1) {
      formBody.fsCouponPage1.forEach(element => {
        console.log("element ", element);
        formData.append('fsCouponPage1', element['file'])
      });
    }
    if (formBody.fsCouponPage2) {
      formBody.fsCouponPage2.forEach(element => {
        console.log("element ", element);
        formData.append('fsCouponPage2', element['file'])
      });
    }
    if (formBody.hourMeterPhoto) {
      formBody.hourMeterPhoto.forEach(element => {
        console.log("element ", element);
        formData.append('hourMeterPhoto', element['file'])
      });
    }
    // if(formBody.)
    if (formBody.chassisPhoto) {
      formBody.chassisPhoto.forEach(element => {
        console.log("element ", element);
        formData.append('chassisPhoto', element['file'])
      });
    }
    if(formBody.signedJobcard){
      formBody.signedJobcard.forEach(element=>{
        console.log(element,'eeeeeeeeeeeeeeeeeeee')
        formData.append('signedJobcard',element['file'])
      })
    }
    if(formBody.retroAcknowledgementForm){
      formBody.retroAcknowledgementForm.forEach(element=>{
        console.log(element,'eeeeeeeeeeeeeeeeeeee')
        formData.append('retroAcknowledgementForm',element['file'])
      })
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(JobCardUrl.saveJobcard, formData, { headers })
  }
  cancelJobCard(id:string,remark:string){
      return this.httpClient.post(JobCardUrl.cancelJobcard, {'id':id,'cancelRemarks':remark});  
  }
  updateJobCardPreSales(formBody: FinalSaveJobCard) {
    console.log('formBody', formBody)

    let formData: FormData = new FormData();
    formData.append('serviceJobCard', new Blob([JSON.stringify(formBody.serviceJobCard)], { type: 'application/json' }))
    if (formBody.fsCouponPage1) {
      formBody.fsCouponPage1.forEach(element => {
        console.log("element ", element);
        formData.append('fsCouponPage1', element['file'])
      });
    }
    if (formBody.fsCouponPage2) {
      formBody.fsCouponPage2.forEach(element => {
        console.log("element ", element);
        formData.append('fsCouponPage2', element['file'])
      });
    }
    if (formBody.hourMeterPhoto) {
      formBody.hourMeterPhoto.forEach(element => {
        console.log("element ", element);
        formData.append('hourMeterPhoto', element['file'])
      });
    }
    if (formBody.chassisPhoto) {
      formBody.chassisPhoto.forEach(element => {
        console.log("element ", element);
        formData.append('chassisPhoto', element['file'])
      });
    }
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(JobCardUrl.updateJobcard, formData, { headers })
    // return this.httpClient.post(JobCardUrl.updateJobcard, formBody)
  }

  viewJobCard(jobCardNo: string): Observable<ViewJobCard> {
    return this.httpClient.get<BaseDto<ViewJobCard>>(JobCardUrl.viewJobCard, {
      params: new HttpParams().set('jobCardNo', jobCardNo)
    }).pipe(map(dto => dto.result))
  }
  preInvoiceService(id: any) {

    return this.httpClient.get(`${JobCardUrl.invoiceFlagSetURL}/${id}`)
  }

  checkCondition(id) {
    return this.httpClient.get(`${JobCardUrl.checkCondition}/${id}`)
  }
  serviceCategory(preSalesFlag: string): Observable<DropDownCategory[]> {
    return this.httpClient.get<BaseDto<DropDownCategory[]>>(JobCardUrl.getDropDownCategory, {
      params: new HttpParams().set('preSalesFlag', preSalesFlag)
    }).pipe(map(dto => dto.result))
  }
  getRetrofitmentDetails(chassisNo): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(JobCardUrl.getRetrofitmentDetails, {
      params: new HttpParams().set('chassisNo', chassisNo)
    }).pipe(map(dto => dto.result))
  }
}
