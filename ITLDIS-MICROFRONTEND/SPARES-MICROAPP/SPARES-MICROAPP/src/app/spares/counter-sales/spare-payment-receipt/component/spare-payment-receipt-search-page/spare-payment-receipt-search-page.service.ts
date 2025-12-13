import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchSparesPaymentReceipt } from 'SparePaymentReceiptModule';
import { SparesPaymentReceiptApi } from '../../url-utils/spare-payment-receipt-urls';
import { DateService } from '../../../../../root-service/date.service';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class SparePaymentReceiptSearchPageService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  public searchSparePaymentReceipt(formValues: SearchSparesPaymentReceipt): Observable<BaseDto<object[]>> {
    this.removeNullFromObjectAndFormatDate(formValues);
    return this.httpClient.post<BaseDto<object[]>>(SparesPaymentReceiptApi.searchPaymentReceiptForSpare, formValues)
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
