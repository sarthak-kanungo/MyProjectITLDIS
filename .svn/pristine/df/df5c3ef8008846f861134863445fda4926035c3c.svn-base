import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { urlService } from '../../../webservice-config/baseurl';

@Injectable()
export class SchemesCommonService {
  private readonly schemeTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.incentiveScheme}${urlService.schemeTypeDropdown}`
  constructor(
    private http : HttpClient
  ) { }

  dropdownSchemeType(){
    return this.http.get(`${this.schemeTypeURL}`)
   }
}
