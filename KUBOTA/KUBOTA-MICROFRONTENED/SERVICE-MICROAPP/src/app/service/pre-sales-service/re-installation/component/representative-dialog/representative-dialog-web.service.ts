import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReInstallationApi } from '../../url-util/re-installation-urls';
import { Observable } from 'rxjs';
import { RepresentativeTypesDropDown } from '../../domain/re-installation-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class RepresentativeDialogWebService {

  constructor(
    private httpClient : HttpClient
  ) { }

  representativeTypeDropdown(): Observable<Array<RepresentativeTypesDropDown>> {
    return this.httpClient.get<BaseDto<Array<RepresentativeTypesDropDown>>>(`${ReInstallationApi.representativeTypeDropdown}`
     ).pipe(map(dto => dto.result))
  }
}
