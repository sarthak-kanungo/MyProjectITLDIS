import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { DealerEmployeeApi } from "../../url-util/dealer-employee-urls";
import {
  CityDetails,
  CountryDetails,
  DistrictDetails,
  PinCodeAuto,
  PinCodeDetails,
  PostOfficeDetails,
  PostOfficeDropdown,
  StateDetails,
  TehsilDetails
} from "../../domain/dealer-employee-domain";
import { map } from "rxjs/operators";
import { BaseDto } from "BaseDto";

@Injectable()
export class EmployeeAddressService {
  constructor(private httpClient: HttpClient) {}

 /*  getPinCodeAuto(): Observable<PinCodeAuto> {
    return this.httpClient.get<PinCodeAuto>(DealerEmployeeApi.pinCodeAuto);
  }
  getPostOfficeDropdown(): Observable<PostOfficeDropdown> {
    return this.httpClient.get<PostOfficeDropdown>(
      DealerEmployeeApi.postOfficeDropdown
    );
  } */

  getCountry(): Observable<CountryDetails> {
    return this.httpClient.get<BaseDto<CountryDetails>>(DealerEmployeeApi.getCountry).pipe(map(dto => dto.result))
  }
  getState(countryId: number, stateName: string): Observable<Array<StateDetails>> {
    return this.httpClient.get<BaseDto<Array<StateDetails>>>(DealerEmployeeApi.getStateAutocomplete, {
      params: new HttpParams()
        .append('countryId', countryId.toString())
        .append('stateName', stateName)
    }).pipe(map(dto => dto.result))
  }

  getDistrict(stateId: number, districtName: string): Observable<Array<DistrictDetails>> {
    return this.httpClient.get<BaseDto<Array<DistrictDetails>>>(DealerEmployeeApi.getDistrictAutocomplete, {
      params: new HttpParams()
        .append('stateId', stateId.toString())
        .append('districtName', districtName)
    }).pipe(map(dto => dto.result))
  }

  getTehsil(districtId: number, tehsilName: string): Observable<Array<TehsilDetails>> {
    return this.httpClient.get<BaseDto<Array<TehsilDetails>>>(DealerEmployeeApi.getTehsilAutocomplete, {
      params: new HttpParams()
        .append('districtId', districtId.toString())
        .append('tehsilName', tehsilName)
    }).pipe(map(dto => dto.result))
  }

  getCity(tehsilId: number, cityName: string): Observable<Array<CityDetails>> {
    return this.httpClient.get<BaseDto<Array<CityDetails>>>(DealerEmployeeApi.getCityAutocomplete, {
      params: new HttpParams()
        .append('tehsilId', tehsilId.toString())
        .append('cityName', cityName)
    }).pipe(map(dto => dto.result))
  }

  getPinCode(cityId: number, pincode: string): Observable<Array<PinCodeDetails>> {
    return this.httpClient.get<BaseDto<Array<PinCodeDetails>>>(DealerEmployeeApi.getPinCodeAutocomplete, {
      params: new HttpParams()
        .append('cityId', cityId.toString())
        .append('pincode', pincode)
    }).pipe(map(dto => dto.result))
  }
  
  getPostOffice(pinCodeId: number, postOffice: string): Observable<Array<PostOfficeDetails>> {
    console.log('pinCodeId',pinCodeId)
    return this.httpClient.get<BaseDto<Array<PostOfficeDetails>>>(DealerEmployeeApi.getPostOfficeAutocomplete, {
      params: new HttpParams()
        .append('pinCodeId', pinCodeId.toString())
        .append('postOffice', postOffice)
    }).pipe(map(dto => dto.result))
  }



}
