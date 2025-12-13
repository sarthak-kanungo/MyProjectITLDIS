import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ItemNumberAuto } from '../domain/pl.domain';
import { PickListApi } from '../pickList-urls';
import { map } from 'rxjs/operators';
import { PartDetails } from './item-pop-up.domain';

@Injectable()
export class ItemPopUpWebService {

  constructor(private httpClient: HttpClient) { }
  // searchByItemNumber(searchKey: string, itemId: string): Observable<Array<ItemNumberAuto>> {
  //   return this.httpClient.get<BaseDto<Array<ItemNumberAuto>>>(SoApi.autocompletePartNo, {
  //     params: new HttpParams()
  //       .set('itemNumber', searchKey)
  //       // .set('itemId',  itemId)
  //   }).pipe(map(dto => dto.result))
  // }
  // getSparePartDetails(searchKey: string, state: string): Observable<Array<PartDetails>> {
  //   return this.httpClient.get<BaseDto<Array<PartDetails>>>(SoApi.getItemDetailsByItemNumber, {
  //     params: new HttpParams()
  //       .set('itemNumber', searchKey)
  //       .set('state', state)
  //   }).pipe(map(dto => dto.result))
  // }
  getAvailableStockByItemNumber(itemNo:string){
    return this.httpClient.get<BaseDto<Array<PartDetails>>>(PickListApi.getAvailableStockByItemNumber, {
      params: new HttpParams()
        .set('itemNo', itemNo)
    }).pipe(map(dto => {
      // const spyStock = {...dto.result[0]};
      // const spyStock1 = {...dto.result[0]};
      // spyStock.unitPrice = 100;
      // spyStock1.unitPrice = 200;
      // dto.result.push(spyStock)
      // dto.result.push(spyStock1)
      return dto.result
    }))
  }
  getAvailableStockForPartIssue(itemNo:string, apiId:string){
    let params = new HttpParams();
    params = params.append('itemNo',itemNo)
    if (apiId) {
      params = params.append('apiId',apiId)
    }
    return this.httpClient.get<BaseDto<Array<PartDetails>>>(PickListApi.getAvailableStockForPartIssue, {
      params
    }).pipe(map(dto => dto.result))
  }
}
