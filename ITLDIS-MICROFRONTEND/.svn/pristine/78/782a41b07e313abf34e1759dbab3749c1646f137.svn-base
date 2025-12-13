import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';
import { searchHotline } from '../../domain/hotline-report.domain';
import { hotlineReport } from '../hotline-report/hotline-service';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-hotline-report-search-page',
  templateUrl: './hotline-report-search-page.component.html',
  styleUrls: ['./hotline-report-search-page.component.scss'],
  providers: [hotlineReport]
})
export class HotlineReportSearchPageComponent implements OnInit {
 hotlineSearchForm:FormGroup
 toDate:Date
 newDate=new Date()
 fromDate:any;
 actionButtons = [];
 searchFlag:boolean=true
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  page:number=0;
  size:number=10
  key='hotline'
  searchFilter: any;
  pageLoadCount:number=0
  clearSearchRow = new BehaviorSubject<string>("");
  loginUserType: import("LoginDto").StorageLoginUser;
  constructor(private fb:FormBuilder,
    private toaster:ToastrService,
    private service:hotlineReport,
    private tableDataService: NgswSearchTableService,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private loginService:LocalStorageService
    ) { }

  ngOnInit() {
    this.hotlineSearchForm=this.fb.group({
      hotlineNo:[{value:'',disabled:false}],
      status:[{value:'',disabled:false}],
      startDate:[{value:'',disabled:false}],
      endDate:[{value:'',disabled:false}]
    })
    this.toDate = this.newDate
    let backDate = new Date();
    backDate.setMonth(this.newDate.getMonth() - 1);
    this.fromDate = backDate;
    this.hotlineSearchForm.get('startDate').patchValue(backDate);
    this.hotlineSearchForm.get('endDate').patchValue(new Date());

     this.loginUserType = this.loginService.getLoginUser();
     console.log(this.loginUserType,'loginUser Type')
     
     if(this.loginUserType.userType=='kubota'){
          console.log('kubota')
     }
  }
  private searchHotline(holineData:searchHotline){
    this.service.searchHotlineReport(holineData).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });

  }
  search() {
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
    const filterObj = this.hotlineSearchForm.value as searchHotline
    filterObj.page = this.page
    filterObj.size = this.size
    filterObj.startDate = filterObj.startDate ? ObjectUtil.convertDate(filterObj.startDate) : null
    filterObj.endDate = filterObj.endDate ? ObjectUtil.convertDate(filterObj.endDate) : null
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.hotlineSearchForm.value))
  
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.hotlineSearchForm.get('hotlineNo').value || this.hotlineSearchForm.get('status').value || this.hotlineSearchForm.get('startDate').value || this.hotlineSearchForm.get('endDate').value) {
        this.searchHotline(filterObj);
      }
      else if (this.hotlineSearchForm.get('startDate').value == null && this.hotlineSearchForm.get('endDate').value == null) {
        this.toaster.error("Please fill atleast one input field.");
      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }
  }
  tableAction(event:any) {
  if(event.btnAction==='hotlineReportNo'){
    this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { hotlineNo: event['record']['hotlineReportNo'] } });
  }
  if(event.btnAction==='action'){
    this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { hotlineNo: event['record']['hotlineReportNo'] } });
  }

  }
  checkValidDatesInput() {
    const fltEnqObj = this.hotlineSearchForm.value

    fltEnqObj.startDate = this.hotlineSearchForm.getRawValue() ? this.hotlineSearchForm.value.startDate : null
    fltEnqObj.endDate = this.hotlineSearchForm.getRawValue() ? this.hotlineSearchForm.value.endDate : null

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
  pageSizeChange(event:any){
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    if(this.pageLoadCount > 0){
     this.search()
    }
    this.pageLoadCount = 1;
  }
  reset(){
    this.hotlineSearchForm.reset()
    this.dataTable=null
  }
  etSearchDateValueChange(searchDate: string, columnKey: string) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, columnKey);
  }

}
