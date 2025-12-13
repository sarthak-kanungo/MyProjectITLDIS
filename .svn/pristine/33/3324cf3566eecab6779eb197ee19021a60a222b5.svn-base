import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class ImplementsAndAccessoriesService {
  private readonly searchByItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getItemNumberAutoComplete}`;
  private readonly getItemDetailsFromItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getItemDetailsByItemNumber}`;

  constructor(
    private httpClient: HttpClient
  ) { }

  searchByItemNumber(itemNumber: string) {
    return this.httpClient.get(this.searchByItemNumberUrl, {
      params: new HttpParams().set('itemNumber', itemNumber)
    })
  }
  getItemDetailsFromItemNumber(itemNo: string) {
    return this.httpClient.get(this.getItemDetailsFromItemNumberUrl, {
      params: new HttpParams().set('itemNumber', itemNo)
    })
  }
}
