import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class InvoiceSearchApiService {

  private readonly searchInvoiceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchInvoiceApproval }`;
  constructor(
    private httpClient: HttpClient
  ) { }
  searchInvoice(searchValue: InvoiceSearchForm) {
    return this.httpClient.get(this.searchInvoiceUrl, {params : 
        new HttpParams().set('Page', searchValue.page+"")
            .set('Size', searchValue.size+"")
    });
  }
}
