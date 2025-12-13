import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { PartQuotationApi } from '../../url-util/part-quotation-url';
import { map } from 'rxjs/operators';
import { AutoCompleteMobileNo, CustomerDetails, Country, AutoState, AutoDistrict, AutoTehsil, AutoPostOffice, AutoPinCode, AutoCity, AutoRetailer, RetailerOrMachinePatchJson } from '../../domain/part-quotation-domain';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class PartQuotationWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  autoCompleteMobileNumber(mobileNumber: string): Observable<Array<AutoCompleteMobileNo>> {
    return this.httpClient.get<BaseDto<Array<AutoCompleteMobileNo>>>(PartQuotationApi.autocompleteMobileNumber, {
      params: new HttpParams()
        .append('mobileNumber', mobileNumber)
    }).pipe(map(dto => dto.result))
  }

  getCustomerDetails(customerCode: string): Observable<CustomerDetails> {
    return this.httpClient.get<BaseDto<CustomerDetails>>(PartQuotationApi.getCustomerDetails, {
      params: new HttpParams()
        .append('customerCode', customerCode)
    }).pipe(map(dto => dto.result))
  }

  getCountry(): Observable<Country> {
    return this.httpClient.get<BaseDto<Country>>(PartQuotationApi.getCountry).pipe(map(dto => dto.result))
  }

  getStateAutocomplete(countryId: number, stateName: string): Observable<Array<AutoState>> {
    return this.httpClient.get<BaseDto<Array<AutoState>>>(PartQuotationApi.getStateAutocomplete, {
      params: new HttpParams()
        .append('countryId', countryId.toString())
        .append('stateName', stateName)
    }).pipe(map(dto => dto.result))
  }

  getDistrictAutocomplete(stateId: number, districtName: string): Observable<Array<AutoDistrict>> {
    return this.httpClient.get<BaseDto<Array<AutoDistrict>>>(PartQuotationApi.getDistrictAutocomplete, {
      params: new HttpParams()
        .append('stateId', stateId.toString())
        .append('districtName', districtName)
    }).pipe(map(dto => dto.result))
  }

  getTehsilAutocomplete(districtId: number, tehsilName: string): Observable<Array<AutoTehsil>> {
    return this.httpClient.get<BaseDto<Array<AutoTehsil>>>(PartQuotationApi.getTehsilAutocomplete, {
      params: new HttpParams()
        .append('districtId', districtId.toString())
        .append('tehsilName', tehsilName)
    }).pipe(map(dto => dto.result))
  }

  getCityAutocomplete(tehsilId: number, cityName: string): Observable<Array<AutoCity>> {
    return this.httpClient.get<BaseDto<Array<AutoCity>>>(PartQuotationApi.getCityAutocomplete, {
      params: new HttpParams()
        .append('tehsilId', tehsilId.toString())
        .append('cityName', cityName)
    }).pipe(map(dto => dto.result))
  }
  getPinCodeAutocomplete(cityId: number, pincode: string): Observable<Array<AutoPinCode>> {
    return this.httpClient.get<BaseDto<Array<AutoPinCode>>>(PartQuotationApi.getSparesPinCodeAutocomplete, {
      params: new HttpParams()
        .append('cityId', cityId.toString())
        .append('pincode', pincode)
    }).pipe(map(dto => dto.result))
  }
  getPostOfficeAutocomplete(pinCodeId: number, postOffice: string): Observable<Array<AutoPostOffice>> {
    return this.httpClient.get<BaseDto<Array<AutoPostOffice>>>(PartQuotationApi.getPostOfficeAutocomplete, {
      params: new HttpParams()
        .append('pinCodeId', pinCodeId.toString())
        .append('postOffice', postOffice)
    }).pipe(map(dto => dto.result))
  }

  getRetailerOrMechanicAutocomplete(searchKey: string, customerType: string): Observable<Array<AutoRetailer>> {
    return this.httpClient.get<BaseDto<Array<AutoRetailer>>>(PartQuotationApi.getRetailerOrMechanicAutocomplete, {
      params: new HttpParams()
        .append('searchKey', searchKey)
        .append('customerType', customerType)
    }).pipe(map(dto => dto.result))
  }

  getRetailerOrMechanicDetails(id: string): Observable<RetailerOrMachinePatchJson> {
    return this.httpClient.get<BaseDto<RetailerOrMachinePatchJson>>(PartQuotationApi.getRetailerOrMechanicDetails, {
      params: new HttpParams()
        .append('id', id)
    }).pipe(map(dto => dto.result))
  }

}
