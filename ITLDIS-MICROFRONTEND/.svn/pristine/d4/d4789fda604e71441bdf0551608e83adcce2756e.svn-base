import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { DeliveryChallanSearchService } from './delivery-challan-search.service';

@Component({
  selector: 'app-delivery-challan-search-container',
  templateUrl: './delivery-challan-search-container.component.html',
  styleUrls: ['./delivery-challan-search-container.component.scss']
})
export class DeliveryChallanSearchContainerComponent implements OnInit {
  dataTable: DataTable;
 // actionButtons = [];
  totalTableElements;
  constructor(
    private deliveryChallanSearchService: DeliveryChallanSearchService, 
    private tableDataService: TableDataService) { }

  ngOnInit() {
    //this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    //this.actionButtons.push(this.tableDataService.addActionButton('Edit', 'edit', 'Edit'));
    this.searchdeliveryChallan(0, 10);
  }
  searchdeliveryChallan(page: number,size: number){
    this.deliveryChallanSearchService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }

}
