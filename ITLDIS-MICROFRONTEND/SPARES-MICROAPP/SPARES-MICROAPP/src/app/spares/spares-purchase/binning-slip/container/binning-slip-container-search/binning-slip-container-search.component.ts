import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { BinningSlipContainerService } from '../binning-slip-container.service';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';

@Component({
  selector: 'app-binning-slip-container-search',
  templateUrl: './binning-slip-container-search.component.html',
  styleUrls: ['./binning-slip-container-search.component.scss']
})
export class BinningSlipContainerSearchComponent implements OnInit {

  dataTable: DataTable;
  totalTableElements;

  constructor(
    private binningSlipContainerService: BinningSlipContainerService, 
    private tableDataService: TableDataService
    ) { }

  ngOnInit() {
    
    this.binningSlip(0, 10);
  }
  binningSlip(page: number,size: number){
    this.binningSlipContainerService.getTableData(page,size).subscribe(res => {
      console.log('res', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res['response']);
      console.log('this.dataTable', this.dataTable);
    })
  }

}
