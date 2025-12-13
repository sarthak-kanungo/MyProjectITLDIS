import { Injectable } from '@angular/core';
import { Adapter } from '../../../../core/adapter';
import { InvoiceSearchFilter, InvoiceSearchForm } from './invoice-search-filter';
import { UtilsService } from '../../../../utils/utils.service';
import { DateService } from '../../../../root-service/date.service';

@Injectable()
export class InvoiceSearchFilterAdaptorService implements Adapter<InvoiceSearchFilter> {
  constructor(
    private utilsService: UtilsService,
    private dateService: DateService
  ) { }
  adapt<R>(item: any, keyMap?: any): InvoiceSearchFilter | R {
    throw new Error("Method not implemented.");
  }
  saveAdapt<R>(record: InvoiceSearchForm | R): InvoiceSearchFilter {
    return this.adaptToInvoiceSearchFilter(record as InvoiceSearchForm)
  }
  private adaptToInvoiceSearchFilter(searchValue: InvoiceSearchForm) {
    searchValue = this.utilsService.removeEmptyKey<InvoiceSearchForm>(searchValue);
    if (searchValue.fromDate) {
      searchValue.fromDate = this.dateService.getFormattedDate(searchValue.fromDate);
    }
    if (searchValue.toDate) {
      searchValue.toDate = this.dateService.getFormattedDate(searchValue.toDate);
    }
    return searchValue;
  }

}
