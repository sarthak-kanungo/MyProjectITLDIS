import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DepartmentNameDropdown } from '../../domain/dealer-department-master-domain';
import { DealerDepartmentMasterApi } from '../../url.utils/dealer-department-master-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { DateService } from 'src/app/root-service/date.service';

@Injectable()
export class SearchDealerDepartmentService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  searchDepartmentCode(deptCod: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerDepartmentMasterApi.departmentCodeAndName, {
      params: new HttpParams().append('deptCodeAndName', deptCod)
    }).pipe(map(res=>res.result))
  }
  
  searchDepartmentName(): Observable<DepartmentNameDropdown> {
    return this.httpClient.get<DepartmentNameDropdown>(DealerDepartmentMasterApi.departmentNameDropdown)
  }

  searchDeptForSearchPageTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      }
    })
    return this.httpClient.post(DealerDepartmentMasterApi.departmentSearchPage, searchData)
  }

  changeActiveStatus(id:any) {
    return this.httpClient.get(DealerDepartmentMasterApi.changeActiveStatus,  {
      params: new HttpParams().set('id', id)
    });
  }
}
