import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { FollowUpType, EnquirySource, RetailConversionActivityType, PurposeOfPurchase, ActivationType, SalePerson, EnquiryType, ActivityNumber, GenerationActivationType } from '../domains/enquiry';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class GeneralInfoWebService {

  constructor(
    private http: HttpClient
  ) { }

  getFollowUpType(): Observable<BaseDto<Array<FollowUpType>>> {
    return this.http.get<BaseDto<Array<FollowUpType>>>(EnquiryApi.getFollowUpType)
  }

  getSource(): Observable<BaseDto<Array<EnquirySource>>> {
    return this.http.get<BaseDto<Array<EnquirySource>>>(EnquiryApi.getSource)
  }

  getRetailConversionActivityType(): Observable<Array<RetailConversionActivityType>> {
    return this.http.get<BaseDto<Array<RetailConversionActivityType>>>(EnquiryApi.getRetailConversionActivityType).pipe(map(dto => dto.result))
  }

  getPurposeOfPurchase(): Observable<BaseDto<Array<PurposeOfPurchase>>> {
    return this.http.get<BaseDto<Array<PurposeOfPurchase>>>(EnquiryApi.getPurposeOfPurchase)
  }

  getActivationType(): Observable<BaseDto<Array<ActivationType>>> {
    return this.http.get<BaseDto<Array<ActivationType>>>(EnquiryApi.getConversionActivityType)
  }

  getGenerationActivityType(): Observable<BaseDto<Array<GenerationActivationType>>> {
    return this.http.get<BaseDto<Array<GenerationActivationType>>>(EnquiryApi.getGenerationActivityType)
  }

  getSalePerson(): Observable<BaseDto<Array<SalePerson>>> {
    return this.http.get<BaseDto<Array<SalePerson>>>(EnquiryApi.getSalesPerson)
  }

  setEnquiryType(enquiryDate: string, tentativePurchaseDate: string): Observable<EnquiryType> {
    return this.http.get<BaseDto<EnquiryType>>(EnquiryApi.setEnquiryType,
      {
        params: new HttpParams()
            .append('enquiryDate', enquiryDate)
            .append('tentativePurchaseDate', tentativePurchaseDate)
    }).pipe(map(dto => dto.result))
  }

  searchMarketingActivityNumber(activityNumber: string, activityPurpose: string, source: string, activityType: string, enqnumber:string): Observable<Array<ActivityNumber>> {
    return this.http.get<BaseDto<Array<ActivityNumber>>>(EnquiryApi.searchMarketingActivityNumber,
      {
        params: new HttpParams()
            .append('activityNumber', activityNumber)
            .append('activityPurpose', activityPurpose)
            .append('source', source)
            .append('activityType', activityType)
            .append('enqnumber',enqnumber)
    }).pipe(map(dto => dto.result))
  }

}
