import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { BranchTransferRecieptService } from '../../service/branch-transfer-reciept.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { searchRecieptData } from './reciept-search-model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-branch-transfer-reciept-search-page',
  templateUrl: './branch-transfer-reciept-search-page.component.html',
  styleUrls: ['./branch-transfer-reciept-search-page.component.scss']
})
export class BranchTransferRecieptSearchPageComponent implements OnInit {
  searchForm: FormGroup
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  page: number = 0
  size: number = 0
  searchFlag: boolean = true
  dataTable: DataTable;
  totalTableElements: number=0;
  actionButtons
  searchValue: ColumnSearch;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter: any
  totalSearchRecordCount: number = 0

  receiptNoNgModel: any
  issuingBranchNgModel:any
  reqByNgModel:''
  receivingBranchNgModel:''
  recieptData: any;
  constructor(
    private fb: FormBuilder,
    private service: BranchTransferRecieptService,
    private toaster: ToastrService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }


  ngOnInit() {
    console.log(this.searchForm)
    this.searchForm = this.fb.group({
      issueNo: [{ value: null, disabled: false }],
      recieptNo: [{ value: null, disabled: false }],
      status: [{ value: null, disabled: false }],
      fromDate: [{ value: null, disabled: false }],
      toDate: [{ value: null, disabled: false }]
    })
    if (this.searchForm.get('issueNo').value == null && this.searchForm.get('recieptNo').value == null && this.searchForm.get('status').value == null && this.searchForm.get('fromDate').value == null && this.searchForm.get('toDate').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchForm.get('fromDate').patchValue(backDate);
      this.searchForm.get('toDate').patchValue(new Date());

    }
   
  }
  onUrlChange(event: any) {

  }
  searchData() {
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

    const filterObj = this.searchForm.value as searchRecieptData
    filterObj.page = this.page
    filterObj.size = this.size

    filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
    filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null


    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchForm.get('recieptNo').value||this.searchForm.get('issueNo').value || this.searchForm.get('status').value || this.searchForm.get('fromDate').value || this.searchForm.get('toDate').value) {
        this.searchRecieptForm(filterObj);
      }

      else if (this.searchForm.get('fromDate').value === null && this.searchForm.get('toDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");

      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  private searchRecieptForm(recieptData: searchRecieptData) {
    this.service.searchRecieptData(recieptData).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalSearchRecordCount = res.count;
    }, err => {
      console.log('err', err);
    });

  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchForm.value

    fltEnqObj.fromDate = this.searchForm.getRawValue() ? this.searchForm.value.fromDate : null
    fltEnqObj.toDate = this.searchForm.getRawValue() ? this.searchForm.value.toDate : null

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
  clearForm() {
    this.searchForm.reset()
    this.dataTable = null
  }

  actionOnTableRecord(event: any) {
    console.log(event.btnAction)
    if (event.btnAction === 'receiptNo') {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { receiptNo: event['record']['receiptNo'] } });
    }
    if (event.btnAction === 'action') {
      this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { receiptNo: event['record']['receiptNo'] } });
    }

  }
  pageLoadCount: number = 0
  pageChange(event: any) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false
    if (this.pageLoadCount > 0) {
      this.searchData()
    }
    this.pageLoadCount = 1;

  }
  
  initialQueryParams(event: any) {

  }
}

