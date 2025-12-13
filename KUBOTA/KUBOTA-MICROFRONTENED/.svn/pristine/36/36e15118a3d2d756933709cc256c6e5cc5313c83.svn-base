import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgswSearchTableService, DataTable, ColumnSearch, TableHeading } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { indentPresenter } from '../branch-transfer-page/branch-indent-presenter';
import { indentStore } from '../branch-transfer-page/branch-indent-store';
import { BranchIndentServiceService } from '../../service/branch-indent-service.service';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { serachBranchIndent } from '../domain/domaim';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-search-branch-transfer-indent',
  templateUrl: './search-branch-transfer-indent.component.html',
  styleUrls: ['./search-branch-transfer-indent.component.css'],
  providers: [indentPresenter,indentStore]
})
export class SearchBranchTransferIndentComponent implements OnInit {
  searchBranchTransfer:FormGroup
  today = new Date();
  minDate: Date;
  maxDate: Date
  dataTable: DataTable;
  totalTableElements: number;
  actionButtons
  searchValue: ColumnSearch;
  clearSearchRow = new BehaviorSubject<string>("");
  key='bri'
  searchFilterValues: any;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  allStatus:any;
  searchFilter:any
  indentData:any
  constructor(private fb:FormBuilder,
    private presenter:indentPresenter,
    private service:BranchIndentServiceService,
    private toaster:ToastrService,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private tableDataService: NgswSearchTableService
    ) {
    this.searchBranchTransfer=this.fb.group({
      indentNo:[{value:null,disabled:false}],
      fromDate:[{value:null,disabled:false}],
      toDate:[{value:null,disabled:false}],
      status:[{value:null,disabled:false}],
     
    })
   }

 

  displayRequestNo(){

  }
  private formValueChanges() {
    this.searchBranchTransfer.get('indentNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoGetIndentNo(val);
      }
    });
  }
  private autoGetIndentNo(txt: string) {
    this.service.autoGetIndentNo(txt).subscribe(res => {
      this.indentData = res;
    });
  }
  
  fromDateSelected1(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchBranchTransfer.get('toDate').value > this.maxDate)
        this.searchBranchTransfer.get('toDate').patchValue(this.maxDate);
    }
  }
  clearForm(){
    this.searchBranchTransfer.reset()
    this.dataTable=null
  }
  searchData(){
    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
    else {
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;
    const filterObj = this.searchBranchTransfer.value as serachBranchIndent
    filterObj.page = this.page
    filterObj.size = this.size
     filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
     filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchBranchTransfer.value))
  
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchBranchTransfer.get('indentNo').value || this.searchBranchTransfer.get('status').value || this.searchBranchTransfer.get('fromDate').value || this.searchBranchTransfer.get('toDate').value) {
        this.searchIndentForm(filterObj);
      }
      
      else if (this.searchBranchTransfer.get('fromDate').value === null && this.searchBranchTransfer.get('toDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");
        // console.log(this.searchBranchTransfer.get('startDate'))
      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  
  private searchIndentForm(indentData:serachBranchIndent){
    this.service.searchBTIndent(indentData).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });

  }

  checkValidDatesInput() {
    const fltEnqObj = this.searchBranchTransfer.value

    fltEnqObj.fromDate = this.searchBranchTransfer.getRawValue() ? this.searchBranchTransfer.value.fromDate : null
    fltEnqObj.toDate = this.searchBranchTransfer.getRawValue() ? this.searchBranchTransfer.value.toDate : null

    // console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }
    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }
  }
  actionOnTableRecord(event:any){
    if(event.btnAction==='indentNo'){
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { indentNo: event['record']['indentNo'] } });
    }
    if(event.btnAction==='action'){
      this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { indentNo: event['record']['indentNo'] } });
    }

  }
  pageLoadCount:number=0
  pageChange(event:any){
    // debugger
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    if(this.pageLoadCount > 0){
     this.searchData()
    }
    this.pageLoadCount = 1;

  }
  ngOnInit() {
    this.formValueChanges()
    this.getAllStatus()
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchBranchTransfer.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
     if (this.searchBranchTransfer.get('indentNo').value==null || this.searchBranchTransfer.get('fromDate').value==null || this.searchBranchTransfer.get('toDate').value==null) { 
      this.maxDate = this.today
      let backDate = new Date();
       backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.searchBranchTransfer.get('fromDate').patchValue(backDate);
      this.searchBranchTransfer.get('toDate').patchValue(new Date());
      // this.searchData();
     
    }
    
  }
  getAllStatus(){
    this.service.getAllStatus().subscribe(status=>{
      this.allStatus=status['result']
      // console.log(this.allStatus)
    })
  }
  initialQueryParams(event:any){

  }
  onUrlChange(event:any){

  }

}
