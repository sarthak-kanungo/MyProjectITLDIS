import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseDto } from "BaseDto";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { AutoCustomerOrder } from "../domain/pl.domain";
import { PickListApi } from "../pickList-urls";

@Injectable()
export class PlSearchService {
    
  constructor(private httpClient: HttpClient
    ) { }

      getCustomerOrderNo(saleOrderNumber: string): Observable<Array<AutoCustomerOrder>> {
    return this.httpClient.get<BaseDto<Array<AutoCustomerOrder>>>(PickListApi.getSalesOrderAutocomplete, {
      params: new HttpParams()
        .append('saleOrderNumber', saleOrderNumber)
    }).pipe(map(dto => dto.result))
  }
}
