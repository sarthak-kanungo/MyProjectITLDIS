import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { DeliveryChallanReturnSearchService } from './delivery-challan-return-search.service';

@Component({
  selector: 'app-delivery-challan-return-search-container',
  templateUrl: './delivery-challan-return-search-container.component.html',
  styleUrls: ['./delivery-challan-return-search-container.component.scss']
})
export class DeliveryChallanReturnSearchContainerComponent implements OnInit {

  dataTable: DataTable;
  
  totalTableElements;
  constructor(
    private deliveryChallanSearchService: DeliveryChallanReturnSearchService, 
    private tableDataService: TableDataService) { }

  ngOnInit() {
    
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
