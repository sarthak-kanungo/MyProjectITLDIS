import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PinCodeAuto, LocalityList, AutoCompPinCode, PincodeDetail, DealerInformation, PartyCode, PostOfficeDetails, PinCodeDetails, CityDetails, TehsilDetails, DistrictDetails, CountryDetails, StateDetails, LocalityDetails } from '../../domain/party-master-domain';
import { PartyMasterApi } from '../../url.utils/party-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { DealerEmployeeApi } from '../../../dealer-employee-master/url-util/dealer-employee-urls';

@Injectable()
export class PartyAddressService {

  constructor(
    private httpClient: HttpClient
  ) { }
  private static readonly moduleForMaster = 'master'
  private static readonly controllerPathForAreaMaster = 'areamaster'
  private static readonly moduleForDealerMaster = 'dealerMaster'

  static readonly apiControllerPathForArearMaster = `${environment.baseUrl}/${environment.api}/${PartyAddressService.moduleForMaster}/${PartyAddressService.controllerPathForAreaMaster}`
  static readonly getPinCode = `${PartyAddressService.apiControllerPathForArearMaster}/getPinCode`
  static readonly apiControllerPathForDealerMaster = `${environment.baseUrl}/${environment.api}/${PartyAddressService.moduleForDealerMaster}`
  static readonly autoCompleteTehsilCityPincode = `${PartyAddressService.apiControllerPathForDealerMaster}/autoCompleteTehsilCityPincode`
  static readonly getPincodeDetail = `${PartyAddressService.apiControllerPathForDealerMaster}/getPincodeDetail`
  static readonly getByPinCode = `${PartyAddressService.apiControllerPathForArearMaster}/getByPinCode`

  static readonly getDealerRegionInfo = `${PartyAddressService.apiControllerPathForDealerMaster}/getDealerRegionInfo`

  // getPinCode(): Observable<PinCodeAuto> {
  //   return this.httpClient.get<PinCodeAuto>(PartyMasterApi.pinCodeAuto)
  // }

  getLocalityList(): Observable<LocalityList> {
    return this.httpClient.get<LocalityList>(PartyMasterApi.localityDropdown)
  }
  
  keyPress(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyStd(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  keytel(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressFirstName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressMiddleName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressLastName(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressFatherName(event: any) {
    const pattern = /[a-zA-Z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressPanNumber(event: any) {
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  // autoCompleteTehsilCityPincode(
  //   districtId: number,
  //   code: string
  // ): Observable<Array<AutoCompPinCode>> {
  //   return this.httpClient
  //     .get<BaseDto<Array<AutoCompPinCode>>>(
  //       PartyAddressService.autoCompleteTehsilCityPincode,
  //       {
  //         params: new HttpParams()
  //           .append("districtId", districtId.toString())
  //           .append("code", code),
  //       }
  //     )
  //     .pipe(map((dto) => dto.result));
  // }

  // getPincodeDetail(
  //   pincodeId: number,
  //   cityId: number
  // ): Observable<PincodeDetail> {
  //   return this.httpClient
  //     .get<BaseDto<PincodeDetail>>(PartyAddressService.getPincodeDetail, {
  //       params: new HttpParams()
  //         .append("cityId", cityId.toString())
  //         .append("pincodeId", pincodeId.toString())
  //     })
  //     .pipe(map((dto) => dto.result));
  // }

  // getPinCode(pinCode: string): Observable<Array<AutoCompPinCode>> {
  //   return this.httpClient
  //     .get<BaseDto<Array<AutoCompPinCode>>>(PartyAddressService.getPinCode, {
  //       params: new HttpParams().append("pinCode", pinCode),
  //     })
  //     .pipe(map((dto) => dto.result));
  // }

  // getByPinCode(pinCode: string, cityId: number): Observable<any> {
  //   return this.httpClient
  //     .get<BaseDto<any>>(PartyAddressService.getByPinCode, {
  //       params: new HttpParams()
  //         .append("pinCode", pinCode)
  //         .append("cityId", cityId.toString()),
  //     })
  //     .pipe(map((dto) => dto.result));
  // }
  // getDealerRegionInfo(): Observable<Array<DealerInformation>> {
  //   return this.httpClient
  //     .get<BaseDto<Array<DealerInformation>>>(PartyAddressService.getDealerRegionInfo)
  //     .pipe(map((dto) => dto.result));
  // }

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

  getLocality(postOfficeName: string): Observable<Array<LocalityDetails>> {
    console.log('postOfficeName',postOfficeName)
    return this.httpClient.get<BaseDto<Array<LocalityDetails>>>(DealerEmployeeApi.getLocalityAutocomplete, {
      params: new HttpParams()
        .append('postOffice',postOfficeName)
    }).pipe(map(dto => dto.result))
  }
}
