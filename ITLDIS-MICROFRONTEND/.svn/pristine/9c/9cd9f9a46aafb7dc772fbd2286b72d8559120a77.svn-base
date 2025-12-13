import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { UtilsService } from '../../../../../utils/utils.service';
import { SearchMaster, DropDownDepartmentNames, DropDownDealers, DropDownCode, DepartmentCodeAuto } from 'SearchDepartment';
import { Observable } from 'rxjs';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';

@Injectable({
  providedIn: 'root'
})
export class SearchDepartmentService {
  private readonly searchDepartmentMasterUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.searchDepartment}`
  // POST /api/department/searchDepartment

  private readonly searchDepartmentCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.searchByDepartmentCode}`
  // GET /api/department/searchByDepartmentCode
  private readonly getDepartmentNamesListUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.departmentNameDropdown}`
  // GET /api/department/departmentNameDropdown
  private readonly getDealersListUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.linkedToDealerDropdown}`
  // GET /api/department/linkedToDealerDropdown
  private readonly getCodeListUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.dealerCodeDropdown}`
  // GET /api/department/dealerCodeDropdown
  private readonly changeActiveStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.changeActiveStatus}`
  // GET /api/department/changeActiveStatus
  constructor(private httpClient: HttpClient,
    private utilsService: UtilsService) { }

  searchDepartmentMaster(searchValue: SearchMaster) {
    searchValue = this.utilsService.removeEmptyKey<SearchMaster>(searchValue);
    return this.httpClient.post(this.searchDepartmentMasterUrl, searchValue)
  }

  searchDepartmentCode(departmentCode: string): Observable<BaseDto<Array<DepartmentCodeAuto>>> {
    return this.httpClient.get<BaseDto<Array<DepartmentCodeAuto>>>(this.searchDepartmentCodeUrl, {
      params: new HttpParams().set('departmentCode', departmentCode)
    })
  }

  getDepartmentNamesList(): Observable<BaseDto<Array<DropDownDepartmentNames>>> {
    return this.httpClient.get<BaseDto<Array<DropDownDepartmentNames>>>(this.getDepartmentNamesListUrl)
  }
  getDealersList(): Observable<BaseDto<Array<DropDownDealers>>> {
    return this.httpClient.get<BaseDto<Array<DropDownDealers>>>(this.getDealersListUrl)
  }
  getCodeList(): Observable<BaseDto<Array<DropDownCode>>> {
    return this.httpClient.get<BaseDto<Array<DropDownCode>>>(this.getCodeListUrl)
  }
  changeActiveStatus(id) {
    return this.httpClient.get(this.changeActiveStatusUrl,  {
      params: new HttpParams().set('id', id)
    });
  }
}
