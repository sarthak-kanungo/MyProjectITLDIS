import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';
import { searchViewDetails } from './search-model';
import { ViewTransitService } from '../../service/view-transit.service';

@Component({
  selector: 'app-search-view-trasit-detail-page',
  templateUrl: './search-view-trasit-detail-page.component.html',
  styleUrls: ['./search-view-trasit-detail-page.component.css']
})
export class SearchViewTrasitDetailPageComponent implements OnInit {
  seachTransit:FormGroup
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
  invoiceNoNgModel:''
  invoiceAmountNgModel:''
  transportModeNgModel:''
  transporterNgModel:''
  lrNoNgModel:''
  noOfBoxesNgModel:''
  statusNgModel:''

  constructor(private fb:FormBuilder,
    private toaster:ToastrService,
    private service:ViewTransitService,
    private tableDataService:NgswSearchTableService
    ) { }

  ngOnInit() {
    
    this.seachTransit = this.fb.group({
      issueNo: [{ value: null, disabled: false }],
      recieptNo: [{ value: null, disabled: false }],
      grnStatus: [{ value: null, disabled: false }],
      fromInvoiceDate: [{ value: null, disabled: false }],
      toInvoiceDate: [{ value: null, disabled: false }]
    })
    
    if ( this.seachTransit.get('grnStatus').value == null && this.seachTransit.get('fromInvoiceDate').value == null && this.seachTransit.get('toInvoiceDate').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.seachTransit.get('fromInvoiceDate').patchValue(backDate);
      this.seachTransit.get('toInvoiceDate').patchValue(new Date());

    }
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

    const filterObj = this.seachTransit.value as searchViewDetails
    filterObj.page = this.page
    filterObj.size = this.size

    filterObj.fromInvoiceDate = filterObj.fromInvoiceDate ? ObjectUtil.convertDate(filterObj.fromInvoiceDate) : null
    filterObj.toInvoiceDate = filterObj.toInvoiceDate ? ObjectUtil.convertDate(filterObj.toInvoiceDate) : null


    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if ( this.seachTransit.get('grnStatus').value || this.seachTransit.get('fromInvoiceDate').value || this.seachTransit.get('toInvoiceDate').value) {
        this.searchViewDetails(filterObj);
      }

      else if (this.seachTransit.get('fromInvoiceDate').value === null && this.seachTransit.get('toInvoiceDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");

      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  private searchViewDetails(viewDetails: searchViewDetails) {
    this.service.searchViewDetails(viewDetails).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalSearchRecordCount = res.count;
    }, err => {
      console.log('err', err);
    });

  }
  checkValidDatesInput() {
    const fltEnqObj = this.seachTransit.value

    fltEnqObj.fromInvoiceDate = this.seachTransit.getRawValue() ? this.seachTransit.value.fromInvoiceDate : null
    fltEnqObj.toInvoiceDate = this.seachTransit.getRawValue() ? this.seachTransit.value.toInvoiceDate : null

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
    this.seachTransit.reset()
    this.dataTable = null
  }

  actionOnTableRecord(event: any) {
  //   console.log(event.btnAction)
  //   if (event.btnAction === 'receiptNo') {
  //     this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { receiptNo: event['record']['receiptNo'] } });
  //   }
  //   if (event.btnAction === 'action') {
  //     this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { receiptNo: event['record']['receiptNo'] } });
  //   }

   }
   onUrlChange(event:any){

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
  initialQueryParams(event:any){

  }

  invoiceDateChanges(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  expectedDateChanges(searchDate,ColumnKey){
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  

}
