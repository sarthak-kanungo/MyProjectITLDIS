import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddressTypeDropdown, PinCodeAuto, LocalityDropdown, AutoCompPinCode, PincodeDetail } from '../../domain/customer-master-domain';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { BaseDto } from 'BaseDto'
import { map } from "rxjs/operators";

@Injectable()
export class AddressDetailsService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getAddressTypeDropdown(): Observable<AddressTypeDropdown> {
    return this.httpClient.get<AddressTypeDropdown>(CustomerMasterApi.addressTypeDropdown)
  }
  getPinCodeAuto(): Observable<PinCodeAuto> {
    return this.httpClient.get<PinCodeAuto>(CustomerMasterApi.pinCodeAuto)
  }
  getLocalityDropdown(): Observable<LocalityDropdown> {
    return this.httpClient.get<LocalityDropdown>(CustomerMasterApi.localityDropdown)
  }
  
  autoCompleteTehsilCityPincode(districtId: number, code: string): Observable<Array<AutoCompPinCode>> {
      return this.httpClient.get<BaseDto<Array<AutoCompPinCode>>>( CustomerMasterApi.autoCompleteTehsilCityPincode,
          {
            params: new HttpParams()
              .append("districtId", districtId.toString())
              .append("code", code),
          }
        )
        .pipe(map((dto) => dto.result));
  }
  getPincodeDetail(pincodeId: number, cityId: number): Observable<PincodeDetail> {
      return this.httpClient.get<BaseDto<PincodeDetail>>(CustomerMasterApi.getPincodeDetail, {
          params: new HttpParams()
            .append("cityId", cityId.toString())
            .append("pinCode", pincodeId.toString())
        })
        .pipe(map((dto) => dto.result));
   }
}
