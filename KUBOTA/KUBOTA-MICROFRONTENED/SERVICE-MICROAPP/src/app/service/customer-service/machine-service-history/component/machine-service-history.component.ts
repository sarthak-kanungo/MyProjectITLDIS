import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { MachineServiceHistoryService } from '../service/machine-service-history.service';

@Component({
  selector: 'app-machine-service-history',
  templateUrl: './machine-service-history.component.html',
  styleUrls: ['./machine-service-history.component.css']
})
export class MachineServiceHistoryComponent implements OnInit {
  searchForm : FormGroup;
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  filterData: object
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  key='MachineServiceHistory';
  searchFilterValues:Object;
  constructor(private fb : FormBuilder,
    private toastr: ToastrService,
    private machineHistoryService: MachineServiceHistoryService,
    private tableDataService: NgswSearchTableService) { }
  ngOnInit() {
    this.searchForm = this.fb.group({
      chassisNo : null
    });
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues);
    }
  }

  searchMachineServiceHistory(){
    if(this.searchFlag){
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;
    if(this.searchForm.valid){
      localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))
      let chassisNo = this.searchForm.controls.chassisNo.value;
      if(chassisNo==null || chassisNo==undefined){
        chassisNo=''
      }
      const obj = {'chassisNo': chassisNo, 'page':this.page, 'size':this.size}
      this.machineHistoryService.serviceHistorySearch(obj).subscribe(response => {
        this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
        this.totalTableElements = response['count']
      })
    }else{
      this.toastr.error('Enter chassis No','Mandatory Field')
    }
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.searchMachineServiceHistory();
  }

  onClickClear() {
    this.searchForm.reset()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
}
