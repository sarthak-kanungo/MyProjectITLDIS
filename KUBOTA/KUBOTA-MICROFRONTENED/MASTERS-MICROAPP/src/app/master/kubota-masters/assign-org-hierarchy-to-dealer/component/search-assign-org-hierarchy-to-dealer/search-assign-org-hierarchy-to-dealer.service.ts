import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DateService } from 'src/app/root-service/date.service';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { CreateNewAssignOrgHierarchyToDealer } from '../../url-util/create-new-assign-org-hierarchy-to-dealer';

@Injectable({
  providedIn: 'root'
})
export class SearchAssignOrgHierarchyToDealerService {

  private readonly searchAllAssignOrgHierarchyToDealer= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.searchAllAssignOrgHierarchyToDealer}`

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService) { }

  searchDealerTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(this.searchAllAssignOrgHierarchyToDealer, searchData)
  }
}
