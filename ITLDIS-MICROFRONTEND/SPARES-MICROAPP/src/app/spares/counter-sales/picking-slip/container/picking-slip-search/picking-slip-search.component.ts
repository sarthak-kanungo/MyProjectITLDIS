import { Component, OnInit } from '@angular/core';
import { PickingSlipSearchService } from './picking-slip-search.service';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';

@Component({
  selector: 'app-picking-slip-search',
  templateUrl: './picking-slip-search.component.html',
  styleUrls: ['./picking-slip-search.component.scss']
})
export class PickingSlipSearchComponent implements OnInit {
  dataTable: DataTable;
  actionButtons = [];
  totalTableElements;

  constructor(private pickingSlipSearchService: PickingSlipSearchService, private tableDataService: TableDataService) { }

  ngOnInit() {
    this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    this.actionButtons.push(this.tableDataService.addActionButton('Edit', 'edit', 'Edit'));
    this.searchPickingSlip(0, 10);
  }
  searchPickingSlip(page: number,size: number){
    this.pickingSlipSearchService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }

}
