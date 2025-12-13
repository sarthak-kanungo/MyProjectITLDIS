import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { searchBranchTransferIssue } from '../branch-transfer-issue-page/branch-transfer-domain';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { BranchTransferIssueServiceService } from '../../service/branch-transfer-issue-service.service';

@Component({
  selector: 'app-search-branch-transfer-issue',
  templateUrl: './search-branch-transfer-issue.component.html',
  styleUrls: ['./search-branch-transfer-issue.component.css']
})
export class SearchBranchTransferIssueComponent implements OnInit {
  searchBranchTransferIssue:FormGroup
  today = new Date();
  minDate: Date;
  maxDate: Date
  page:number=0
  size:number=0
  searchFlag:boolean=false
  dataTable: DataTable;
  totalTableElements: number=0;
  actionButtons
  searchValue: ColumnSearch;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter:any
  statusList: string[] = [
    'Pending', 'Submit',
  ];
  issueNoNgModel:''
  reqFromBranchNgModel:''
  reqByNgModel:''
  reqToBranchNgModel:''
  issueData:any
  constructor(
    private fb:FormBuilder,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private toaster:ToastrService,
    private service:BranchTransferIssueServiceService,
    private tableDataService:NgswSearchTableService
    ) { }

  ngOnInit() {
    // this.formValueChanges()
    
   this.searchBranchTransferIssue=this.fb.group({
    requestNo:[{value:null,disabled:false}],
    issueNo:[{value:null,disabled:false}],
    fromDate:[{value:null,disabled:false}],
    toDate:[{value:null,disabled:false}],
    status:[{value:null,disabled:false}]
   })
   if (this.searchBranchTransferIssue.get('requestNo').value==null || this.searchBranchTransferIssue.get('issueNo').value==null || this.searchBranchTransferIssue.get('fromDate').value==null || this.searchBranchTransferIssue.get('toDate').value==null) {
     
    this.maxDate = this.today
    let backDate = new Date();
     backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.searchBranchTransferIssue.get('fromDate').patchValue(backDate);
    this.searchBranchTransferIssue.get('toDate').patchValue(new Date());
   
   
  }
  this.formValueChanges()
  }
  private formValueChanges() {
    this.searchBranchTransferIssue.get('issueNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoGenerateissueNo(val);
      }
    });
  }
  private autoGenerateissueNo(txt: string) {
    this.service.autoGenIssueNumber(txt).subscribe(res => {
      this.issueData = res;
    });
  }
  displayRequestNo(obj){
  
   return obj?obj:obj.issueNo=undefined
   
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

     const filterObj = this.searchBranchTransferIssue.value as searchBranchTransferIssue
    filterObj.page = this.page
    filterObj.size = this.size
  
       filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
       filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null
   
  
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchBranchTransferIssue.get('issueNo').value || this.searchBranchTransferIssue.get('status').value || this.searchBranchTransferIssue.get('fromDate').value || this.searchBranchTransferIssue.get('toDate').value) {
        this.searchIssueForm(filterObj);
      }
      
      else if (this.searchBranchTransferIssue.get('fromDate').value === null && this.searchBranchTransferIssue.get('toDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");
        
      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  private searchIssueForm(issueData:searchBranchTransferIssue){
    this.service.searchBTIssue(issueData).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });

  }
checkValidDatesInput() {
    const fltEnqObj = this.searchBranchTransferIssue.value

     fltEnqObj.fromDate = this.searchBranchTransferIssue.getRawValue() ? this.searchBranchTransferIssue.value.fromDate : null
     fltEnqObj.toDate = this.searchBranchTransferIssue.getRawValue() ? this.searchBranchTransferIssue.value.toDate : null

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
    console.log(event.btnAction)
    if(event.btnAction==='issueNo'){
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { issueNo: event['record']['issueNo'] } });
    }
    if(event.btnAction==='action'){
      this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { issueNo: event['record']['issueNo'] } });
    }

  }
  pageLoadCount:number=0
  pageChange(event:any){
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    if(this.pageLoadCount > 0){
     this.searchData()
    }
    this.pageLoadCount = 1;

  }

  clearForm(){
    this.searchBranchTransferIssue.reset()
    this.dataTable=null
  }

  onUrlChange(event:any){

  }
  initialQueryParams(event:any){

  }
  fromDateSelected(event:any){
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchBranchTransferIssue.get('toDate').value > this.maxDate)
        this.searchBranchTransferIssue.get('toDate').patchValue(this.maxDate);
    }
  }
}
