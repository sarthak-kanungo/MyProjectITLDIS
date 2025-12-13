import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerMasterApi } from '../../url.utils/customer-master-url.utils';
import { CropsGrownDropdown, SoilTypeDropdown } from '../../domain/customer-master-domain';
import { BaseDto } from 'BaseDto';

@Injectable()
export class LandDetailsService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getSoilTypeDropdown(): Observable<BaseDto<Array<SoilTypeDropdown>>> {
    return this.httpClient.get<BaseDto<Array<SoilTypeDropdown>>>(CustomerMasterApi.soilTypeDropdown)
  }

  getCropsGrownDropdown(): Observable<BaseDto<Array<CropsGrownDropdown>>> {
    return this.httpClient.get<BaseDto<Array<CropsGrownDropdown>>>(CustomerMasterApi.cropsGrownDropdown)
  }
}
