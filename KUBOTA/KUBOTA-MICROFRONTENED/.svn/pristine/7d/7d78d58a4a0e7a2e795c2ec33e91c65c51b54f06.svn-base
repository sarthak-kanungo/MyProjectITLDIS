import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ItemNumberAuto, PartDetails } from '../../domain/so.domain';
import { SoApi } from '../../url-utils/so-urls';
import { map } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class ItemDetailTableApiService {

  constructor(
    private httpClient: HttpClient,
    private toastr: ToastrService,
  ) { }
  searchByItemNumber(searchKey: string, itemId: string): Observable<Array<ItemNumberAuto>> {
    return this.httpClient.get<BaseDto<Array<ItemNumberAuto>>>(SoApi.autocompletePartNo, {
      params: new HttpParams()
        .set('itemNumber', searchKey)
        .set('itemId', itemId)
    }).pipe(map(dto => dto.result))
  }
  getItemDetailsByItemNumber(searchKey: string, state: string): Observable<Array<PartDetails>> {
    console.log("state ", state);
    if (state == undefined) {
      this.toastr.error(`Please select state`)
    } else {
      return this.httpClient.get<BaseDto<Array<PartDetails>>>(SoApi.getItemDetailsByItemNumber, {
        params: new HttpParams()
          .set('itemNumber', searchKey)
          .set('state', state['state'])
      }).pipe(map(dto => dto.result))
    }
  }
}
