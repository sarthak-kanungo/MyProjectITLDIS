import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DateService } from 'src/app/root-service/date.service';
import { CreateNewDealerUser } from '../../url-util/create-new-dealer-user';

@Injectable({
  providedIn: 'root'
})
export class DealerSearchService {

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
    return this.httpClient.post(CreateNewDealerUser.searchDealer, searchData)
  }
}
