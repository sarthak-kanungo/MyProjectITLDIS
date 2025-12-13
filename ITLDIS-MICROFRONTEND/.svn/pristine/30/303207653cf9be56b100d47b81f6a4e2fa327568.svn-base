import { Component, OnInit } from '@angular/core';
import { SearchNewPartsClaimService } from './search-new-parts-claim.service';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';

@Component({
  selector: 'app-new-parts-claim-search',
  templateUrl: './new-parts-claim-search.component.html',
  styleUrls: ['./new-parts-claim-search.component.scss']
})
export class NewPartsClaimSearchComponent implements OnInit {

  dataTable : DataTable;
  actionButtons = [];
  totalTableElements;
  claims : string[] = ['Draft','Pending for Approved','Partially Approved','Approved'];

  constructor(private searchNewPartsClaimService: SearchNewPartsClaimService, private tableDataService: TableDataService) { }

  ngOnInit() {
    this.actionButtons.push(this.tableDataService.addActionButton('view', 'visibility', 'view'));
    this.actionButtons.push(this.tableDataService.addActionButton('Edit', 'edit', 'Edit'));
    this.searchOrderPlanningSheet(0, 10);
  }
  searchOrderPlanningSheet(page: number,size: number){
    this.searchNewPartsClaimService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }

}
