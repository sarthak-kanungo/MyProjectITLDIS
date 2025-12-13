import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { PartDetails } from './item-pop-up.domain';
import { PartIssueUrl } from '../../url-util/part-issue.url';
@Injectable()
export class ItemPopUpWebService {

  constructor(private httpClient: HttpClient) { }
  
  getAvailableStockByItemNumber(itemNo:string, category:string){
    return this.httpClient.get<BaseDto<Array<PartDetails>>>(PartIssueUrl.getAvailableStockByItemNumber, {
      params: new HttpParams()
        .set('itemNo', itemNo)
        .set('category', category)
    }).pipe(map(dto => {
      return dto.result
    }))
  }
  getAvailableStockForPartIssue(itemNo:string, apiId:string){
    let params = new HttpParams();
    params = params.append('itemNo',itemNo)
    if (apiId) {
      params = params.append('apiId',apiId)
    }
    return this.httpClient.get<BaseDto<Array<PartDetails>>>(PartIssueUrl.getAvailableStockForPartIssue, {
      params
    }).pipe(map(dto => dto.result))
  }
}
