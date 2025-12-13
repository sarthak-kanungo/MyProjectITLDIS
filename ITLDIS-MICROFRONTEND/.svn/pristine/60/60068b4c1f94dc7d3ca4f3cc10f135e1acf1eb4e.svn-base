import { Component, OnInit } from '@angular/core';
import { InvoiceSearchFilterAdaptorService } from '../../model/invoice-search-filter-adaptor.service';
import { InvoiceSearchApiService } from './invoice-search-api.service';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';

@Component({
  selector: 'app-invoice-search',
  templateUrl: './invoice-search.component.html',
  styleUrls: ['./invoice-search.component.scss'],
  providers: [
    InvoiceSearchFilterAdaptorService,
    InvoiceSearchApiService
  ]
})
export class InvoiceSearchComponent implements OnInit {

  dataTable: DataTable;
  totalSearchRecordCount: number;
  constructor(
    private ngswSearchTableService: NgswSearchTableService,
    private searchInvoiceApiService: InvoiceSearchApiService
  ) { }

  ngOnInit() {
  }
  getSearchResultTable(searchValue: InvoiceSearchForm) {
    this.searchInvoiceApiService.searchInvoice(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }

}
