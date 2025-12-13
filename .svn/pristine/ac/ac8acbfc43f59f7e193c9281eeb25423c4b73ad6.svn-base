import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeptDropdownForDesignation, SearchDepartmentAuto } from '../../domain/dealer-designation-master-domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { DealerDesignationMasterApi } from '../../url.utils/dealer-designation-master-urls';

@Injectable()
export class SearchDesignaionMasterService {
  viewDetailsData:any

  constructor(
    private httpClient: HttpClient
  ) { }

  getSearchDepartmentAuto(): Observable<any> {
    return this.httpClient.get<any>(DealerDesignationMasterApi.searchDealerDepartment)
  }

  searchDesignation(designationAuto: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerDesignationMasterApi.searchDesignation, {
      params: new HttpParams().append('designation', designationAuto)
    }).pipe(map(res=>res.result))
  }

  searchDesignationForSearchPageTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      }
    })
    return this.httpClient.post(DealerDesignationMasterApi.designationSearchPage, searchData)
  }

  changeActiveStatus(id:any) {
    return this.httpClient.get(DealerDesignationMasterApi.changeActiveStatus,  {
      params: new HttpParams().set('id', id)
    });
  }
}
