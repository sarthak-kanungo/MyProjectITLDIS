import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { BintobinTransferSearchService } from './bintobin-transfer-search.service';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';

@Component({
  selector: 'app-search-bontobin-transfer',
  templateUrl: './search-bontobin-transfer.component.html',
  styleUrls: ['./search-bontobin-transfer.component.scss']
})
export class SearchBontobinTransferComponent implements OnInit {
  dataTable : DataTable;
  actionButtons = [];
  totalTableElements;

  constructor(private bintobinTransferSearchService: BintobinTransferSearchService, private tableDataService: TableDataService) { }

  ngOnInit() {
    this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    this.actionButtons.push(this.tableDataService.addActionButton('Edit', 'edit', 'Edit'));
    this.searchOrderPlanningSheet(0, 10);
  }
  searchOrderPlanningSheet(page: number,size: number){
    this.bintobinTransferSearchService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }
}
