import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { EnquiryApi } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/url-utils/enquiry-urls';

@Injectable({
  providedIn: 'root'
})
export class ExchangeInventorySaleService {

  constructor( private httpClient: HttpClient,
    private dateService: DateService) { }

  getEnquirySearchById(id: any) {
    return this.httpClient.get(`${EnquiryApi.exchangeInventoryController}/${id}`)
  }
}
