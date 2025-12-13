import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DateService } from 'src/app/root-service/date.service';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { SoApi } from '../../customer-order/url-utils/so-urls';
import { AutoCustomerOrder, PickListPatchJson, SavePickList} from '../domain/pl.domain';
import { PickListApi } from '../pickList-urls';


@Injectable({
  providedIn: 'root'
})
export class PickListServiceService {
  private readonly printPicklListurl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printPicklListurl}`;
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService,){ }

  getCustomerOrderNo(saleOrderNumber: string): Observable<Array<AutoCustomerOrder>> {
    return this.httpClient.get<BaseDto<Array<AutoCustomerOrder>>>(PickListApi.getCustomerOrderNoAutocomplete, {
      params: new HttpParams()
        .append('saleOrderNumber', saleOrderNumber)
    }).pipe(map(dto => dto.result))
  }


  getCustomerOrdeById(id: string): Observable<PickListPatchJson> {
    return this.httpClient.get<BaseDto<PickListPatchJson>>(`${PickListApi.getCustomerDetails}/${id}`).pipe(map(dto => dto.result))
  }
  
  savePicklist(finalData: SavePickList) {
    return this.httpClient.post(PickListApi.savePickList, finalData);
  }
  viewPickList(pickId: number) {
      return this.httpClient.get(PickListApi.viewPickList, {
          params : new HttpParams().set("id", pickId+"")
      });
    }

    printPicklList(picklistNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printPicklListurl, {
          params: new HttpParams().set('picklistNumber', picklistNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
