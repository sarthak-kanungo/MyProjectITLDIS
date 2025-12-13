import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PdiUrl } from '../../url-util/pdi-url';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class PdiSearchPageWebService {

  constructor(private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  searchPdiTable(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key", searchData[key]);
      // delete searchData['chassisNo']
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'dmsgrnToDate') || (key === 'dmsgrnFromDate') || (key === 'pdiDateFrom') || (key === 'pdiDateTo')) {
        searchData[key] = this.dateService.getDateIntoDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(PdiUrl.postPdiTableSearchUrl, searchData)
  }
}
