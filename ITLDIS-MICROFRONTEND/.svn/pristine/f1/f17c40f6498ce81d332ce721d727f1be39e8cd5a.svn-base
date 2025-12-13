import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { SalesInvoiceCancelSearchService } from './sales-invoice-cancel-search.service';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';

@Component({
  selector: 'app-sales-invoice-cancellation-container-search',
  templateUrl: './sales-invoice-cancellation-container-search.component.html',
  styleUrls: ['./sales-invoice-cancellation-container-search.component.scss']
})
export class SalesInvoiceCancellationContainerSearchComponent implements OnInit {

  dataTable: DataTable;
  actionButtons = [];
  totalTableElements;

  constructor(
    private salesInvoiceCancelSearchService: SalesInvoiceCancelSearchService, 
    private tableDataService: TableDataService)
     { }

  ngOnInit() {
    this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    this.actionButtons.push(this.tableDataService.addActionButton('Edit', 'edit', 'Edit'));
    this.searchSalesInvoiceCancel(0, 10);
  }
  searchSalesInvoiceCancel(page: number,size: number){
    this.salesInvoiceCancelSearchService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }

}