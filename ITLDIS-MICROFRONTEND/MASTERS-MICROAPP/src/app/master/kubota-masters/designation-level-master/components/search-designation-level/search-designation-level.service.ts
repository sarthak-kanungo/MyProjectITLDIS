import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { UtilsService } from '../../../../../utils/utils.service';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DesignationLevelAuto, SearchDesignationLevel } from 'Designation Level';
import { BaseDto } from 'BaseDto';

@Injectable({
  providedIn: 'root'
})
export class SearchDesignationLevelService {
  private readonly searchByDesignationLevelUrl = `${environment.baseUrl}${urlService.api}${urlService.designationlevel}${urlService.searchByDesignationLevel}`
  // GET /api/designation/searchByDesignation
  private readonly searchDesignationLevelUrl = `${environment.baseUrl}${urlService.api}${urlService.designationlevel}${urlService.searchDesignationLevel}`
  // POST /api/designation/searchDesignation
  private readonly changeActiveLevelStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.designationlevel}${urlService.changeActiveStatus}`
  // GET /api/designation/changeActiveStatus

  constructor(
    private httpClient: HttpClient,
    private utilsService: UtilsService) { }


    searchDesignationLevel(designationlevel: string): Observable<BaseDto<Array<DesignationLevelAuto>>> {
      return this.httpClient.get<BaseDto<Array<DesignationLevelAuto>>>(this.searchByDesignationLevelUrl, {
        params: new HttpParams().set('designationlevel', designationlevel)
      })
    }

  searchDesignationLevelMaster(searchValue: SearchDesignationLevel) {
    searchValue = this.utilsService.removeEmptyKey<SearchDesignationLevel>(searchValue);
    return this.httpClient.post(this.searchDesignationLevelUrl, searchValue)
  }

  changeActiveStatus(id) {
    return this.httpClient.get(this.changeActiveLevelStatusUrl, {
      params: new HttpParams().set('id', id)
    });
  }

}
