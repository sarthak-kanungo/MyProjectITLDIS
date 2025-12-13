import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DateService } from 'src/app/root-service/date.service';
import { AssigneUserToBranchUrl } from '../../url-util/assign-user-to-branch-url';

@Injectable({
  providedIn: 'root'
})
export class SearchAssignUserToBranchService {

  constructor(private httpClient:HttpClient,
    private dateService: DateService) { }

    searchAssignUserToBranchTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(AssigneUserToBranchUrl.searchAssignUserToBranch, searchData)
  }
}
