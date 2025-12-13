import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { UtilsService } from '../../../../../utils/utils.service';
import { BaseDto } from 'BaseDto';
import { DepartmentName, DesignationAuto, SearchDesignation } from './search-designation';

@Injectable({
  providedIn: 'root'
})

export class SearchDesignationService {
  private readonly searchDesignationUrl = `${environment.baseUrl}${urlService.api}${urlService.designation}${urlService.searchByDesignation}`
  // GET /api/designation/searchByDesignation
  private readonly getDepartmentNamesListUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.departmentNameDropdown}`
  // GET /api/designation/getHierarchy
  private readonly searchDepartmentMasterUrl = `${environment.baseUrl}${urlService.api}${urlService.designation}${urlService.searchDesignation}`
  // POST /api/designation/searchDesignation
  private readonly changeActiveStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.designation}${urlService.changeActiveStatus}`
  // GET /api/designation/changeActiveStatus
  constructor(private httpClient: HttpClient,
    private utilsService: UtilsService) { }

  searchDesignation(designation: string): Observable<BaseDto<Array<DesignationAuto>>> {
    return this.httpClient.get<BaseDto<Array<DesignationAuto>>>(this.searchDesignationUrl, {
      params: new HttpParams().set('designation', designation)
    })
  }
  
  getDepartmentName(): Observable<BaseDto<Array<DepartmentName>>> {
    return this.httpClient.get<BaseDto<Array<DepartmentName>>>(this.getDepartmentNamesListUrl)
  }

  searchDesignationMaster(searchValue: SearchDesignation) {
    searchValue = this.utilsService.removeEmptyKey<SearchDesignation>(searchValue);
    return this.httpClient.post(this.searchDepartmentMasterUrl, searchValue)
  }
  changeActiveStatus(id) {
    return this.httpClient.get(this.changeActiveStatusUrl, {
      params: new HttpParams().set('id', id)
    });
  }
}
