import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ModelDropDown, YearOfPurchaseAuto, SerialNoAuto, ManufacturerDropdown } from '../../domain/customer-master-domain';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { BaseDto } from 'BaseDto';

@Injectable()
export class LastPurchaseDetailsService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getManufacturerDropdown(): Observable<BaseDto<Array<ManufacturerDropdown>>> {
    return this.httpClient.get<BaseDto<Array<ManufacturerDropdown>>>(CustomerMasterApi.manufacturerDropdown)
  }
  
  getModelAuto(): Observable<BaseDto<Array<ModelDropDown>>> {
    return this.httpClient.get<BaseDto<Array<ModelDropDown>>>(CustomerMasterApi.modelAuto, {
        params : new HttpParams().set("model", '')
    })
  }
 /* getYearOfPurchaseAuto(): Observable<YearOfPurchaseAuto> {
    return this.httpClient.get<YearOfPurchaseAuto>(CustomerMasterApi.yearOfPurchaseAuto)
  }
  getSerialNoAuto(): Observable<SerialNoAuto> {
    return this.httpClient.get<SerialNoAuto>(CustomerMasterApi.serialNoAuto)
  }*/
}
