import { Component, OnInit } from '@angular/core';
import { InvoiceSearchApiService } from './invoice-search-api.service';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';


@Component({
  selector: 'app-invoice-cancellation-approval-search',
  templateUrl: './invoice-cancellation-approval-search.component.html',
  styleUrls: ['./invoice-cancellation-approval-search.component.scss'],
  providers: [
    InvoiceSearchApiService
  ]
})

export class InvoiceCancellationApprovalSearchComponent implements OnInit {

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
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
}