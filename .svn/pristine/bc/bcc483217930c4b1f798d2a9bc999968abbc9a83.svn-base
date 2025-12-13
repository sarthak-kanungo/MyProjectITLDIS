import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchDto } from '../../domain/dealer-employee-domain';
import { DealerEmployeeApi } from '../../url-util/dealer-employee-urls';
import { DateService } from 'src/app/root-service/date.service';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';


@Injectable()
export class EmployeeMasterSearchPageService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  searchEmployeeMasterTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(DealerEmployeeApi.searchDealerEmployee, searchData)
  }

  employeeNameAuto(firstName: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(DealerEmployeeApi.employeeNameAuto, {
      params: new HttpParams().append('firstName', firstName)
    }).pipe(map(res=>res.result))
  }

  
}
