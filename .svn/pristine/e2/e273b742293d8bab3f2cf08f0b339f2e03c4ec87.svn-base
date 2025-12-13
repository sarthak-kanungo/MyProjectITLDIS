import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InvoiceSearchFilterAdaptorService } from '../../model/invoice-search-filter-adaptor.service';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class InvoiceSearchApiService {

  private readonly searchInvoiceUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.searchInvoice }`;
  constructor(
    private httpClient: HttpClient,
    private invoiceSearchFilterAdaptorService: InvoiceSearchFilterAdaptorService
  ) { }
  searchInvoice(searchValue: InvoiceSearchForm) {
    const search = this.invoiceSearchFilterAdaptorService.saveAdapt(searchValue);
    return this.httpClient.post(this.searchInvoiceUrl, search);
  }
}
