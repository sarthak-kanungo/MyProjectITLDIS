import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SparePartRequisitionSearchFilter, SparePartRequisitionSearchResult } from '../../domain/part-requisition.domain';
import { PartRequisitionUrl } from '../../url-util/part-requisition.url';
import { BaseDto } from 'BaseDto';
import { DateService } from 'src/app/root-service/date.service';

@Injectable()
export class PartRequisitionSearchPageApiService {

  constructor(private httpClient: HttpClient,
    private dateService: DateService) { }
  searchPartRequisition(search: Object){
    this.removeNullFromObjectAndFormatDate(search);
    console.log('vinay--',search);
    return this.httpClient.post<BaseDto<SparePartRequisitionSearchResult[]>>(PartRequisitionUrl.searchPartRequisition, search);
  }


  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "")) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }
}
