import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerDetails, CustomerType, AutoCompleteCustomerNMobileNo, AutoQuotationNo, CountryDetails, StateDetails, DistrictDetails, TehsilDetails, CityDetails, PinCodeDetails, PostOfficeDetails, QuotationPatchJson, AutoCompleteDealerCode, DealerCodePatchJson, RetailerPatchJson, MachinePatchJson, AutoCompleteRetailer } from '../../domain/so.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SoApi } from '../../url-utils/so-urls';
import { DateService } from '../../../../../root-service/date.service';
import { SparePoApi } from 'src/app/spares/spares-purchase/spares-purchase-order/url-utils/spares-purchse-order-urls';

@Injectable()
export class CustomerOrderWebService {
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService,
  ) {
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }
  getCustomerType(documentType: string): Observable<Array<CustomerType>> {
    return this.httpClient.get<BaseDto<Array<CustomerType>>>(SoApi.getSpareCustomerTypeDropdown, {
      params: new HttpParams()
        .append('documentType', documentType)
    }).pipe(map(dto => dto.result))
  }
  getQuotationNo(quotationNumber: string): Observable<Array<AutoQuotationNo>> {
    return this.httpClient.get<BaseDto<Array<AutoQuotationNo>>>(SoApi.autocompleteQuotationNo, {
      params: new HttpParams()
        .append('quotationNumber', quotationNumber)
    }).pipe(map(dto => dto.result))
  }
  getDealerCodeAutocomplete(dealerCode: string): Observable<Array<AutoCompleteDealerCode>> {
    return this.httpClient.get<BaseDto<Array<AutoCompleteDealerCode>>>(SoApi.getDealerCodeAutocomplete, {
      params: new HttpParams()
        .append('dealerCode', dealerCode)
    }).pipe(map(dto => dto.result))
  }
  getDealerById(id: string): Observable<DealerCodePatchJson> {
    return this.httpClient.get<BaseDto<DealerCodePatchJson>>(SoApi.getDealerDetails, {
      params: new HttpParams()
        .append('id', id)
    }).pipe(map(dto => dto.result))
  }
  getRetailerOrMechanicDetails(id: string): Observable<RetailerPatchJson> {
    return this.httpClient.get<BaseDto<RetailerPatchJson>>(SoApi.getRetailerOrMechanicDetails, {
      params: new HttpParams()
        .append('id', id)
    }).pipe(map(dto => dto.result))
  }
  getMachineById(id: string): Observable<MachinePatchJson> {
    // const getQuotationByIdUrl = `${SoApi.getQuotationById}/spares/quotation/getQuotationById/${id}`
    return this.httpClient.get<BaseDto<MachinePatchJson>>(``).pipe(map(dto => dto.result))
  }
  getRetailerOrMechanicAutocomplete(searchKey: string, customerType: string): Observable<Array<AutoCompleteRetailer>> {
    return this.httpClient.get<BaseDto<Array<AutoCompleteRetailer>>>(SoApi.getRetailerOrMechanicAutocomplete, {
      params: new HttpParams()
        .append('searchKey', searchKey)
        .append('customerType', customerType)
    }).pipe(map(dto => dto.result))
  }
  autoCompleteCustomerNMobileN(mobileNumber: string): Observable<Array<AutoCompleteCustomerNMobileNo>> {
    return this.httpClient.get<BaseDto<Array<AutoCompleteCustomerNMobileNo>>>(SoApi.autoCompleteCustomerNMobileN, {
      params: new HttpParams()
        .append('mobileNumber', mobileNumber)
    }).pipe(map(dto => dto.result))
  }
  getCustomerDetails(customerCode: string): Observable<CustomerDetails> {
    console.log("customerCode ", customerCode);
    return this.httpClient.get<BaseDto<CustomerDetails>>(SoApi.getCustomerDetails, {
      params: new HttpParams()
        .append('customerCode', customerCode)
    }).pipe(map(dto => dto.result))
  }
  getQuotationById(id: string): Observable<QuotationPatchJson> {
    return this.httpClient.get<BaseDto<QuotationPatchJson>>(`${SoApi.getQuotationById}/${id}`).pipe(map(dto => dto.result))
  }
  getCountry(): Observable<CountryDetails> {
    return this.httpClient.get<BaseDto<CountryDetails>>(SoApi.getCountry).pipe(map(dto => dto.result))
  }
  getState(countryId: number, stateName: string): Observable<Array<StateDetails>> {
    return this.httpClient.get<BaseDto<Array<StateDetails>>>(SoApi.getStateAutocomplete, {
      params: new HttpParams()
        .append('countryId', countryId.toString())
        .append('stateName', stateName)
    }).pipe(map(dto => dto.result))
  }
  getDistrict(stateId: number, districtName: string): Observable<Array<DistrictDetails>> {
    return this.httpClient.get<BaseDto<Array<DistrictDetails>>>(SoApi.getDistrictAutocomplete, {
      params: new HttpParams()
        .append('stateId', stateId.toString())
        .append('districtName', districtName)
    }).pipe(map(dto => dto.result))
  }
  getTehsil(districtId: number, tehsilName: string): Observable<Array<TehsilDetails>> {
    return this.httpClient.get<BaseDto<Array<TehsilDetails>>>(SoApi.getTehsilAutocomplete, {
      params: new HttpParams()
        .append('districtId', districtId.toString())
        .append('tehsilName', tehsilName)
    }).pipe(map(dto => dto.result))
  }
  getCity(tehsilId: number, cityName: string): Observable<Array<CityDetails>> {
    return this.httpClient.get<BaseDto<Array<CityDetails>>>(SoApi.getCityAutocomplete, {
      params: new HttpParams()
        .append('tehsilId', tehsilId.toString())
        .append('cityName', cityName)
    }).pipe(map(dto => dto.result))
  }
  getPinCode(cityId: number, pincode: string): Observable<Array<PinCodeDetails>> {
    return this.httpClient.get<BaseDto<Array<PinCodeDetails>>>(SoApi.getPinCodeAutocomplete, {
      params: new HttpParams()
        .append('cityId', cityId.toString())
        .append('pincode', pincode)
    }).pipe(map(dto => dto.result))
  }
  getPostOffice(pinCodeId: number, postOffice: string): Observable<Array<PostOfficeDetails>> {
    console.log('pinCodeId',pinCodeId)
    return this.httpClient.get<BaseDto<Array<PostOfficeDetails>>>(SoApi.getPostOfficeAutocomplete, {
      params: new HttpParams()
        .append('pinCodeId', pinCodeId.toString())
        .append('postOffice', postOffice)
    }).pipe(map(dto => dto.result))
  }

  public uploadExcelPoItemDetail(uploadableFile:any){
      let formData: FormData = new FormData();
      formData.append('state', uploadableFile['state']);
      formData.append('file', uploadableFile['file']);
      formData.append('discountRate', uploadableFile['discountRate']+"");
      formData.append('existingItems', uploadableFile['existingItems']);
      
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');
console.log('formData',formData)
      return this.httpClient.post(SoApi.customerUploadExcel, formData, {
          headers: headers
      });
    }

}
