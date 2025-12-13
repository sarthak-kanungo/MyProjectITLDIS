import { Component, OnInit, AfterViewInit, AfterContentInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgswSearchTableService, DataTable, ColumnSearch, TableHeading } from 'ngsw-search-table';
import { FormGroup } from '@angular/forms';
import { SparePartReturnSearchFilter } from '../../domain/part-return.domain';
import { ObjectUtil } from '../../../../../utils/object-util';
import { PartReturnSearchPageApiService } from './part-return-search-page-api.service';
import { PartReturnSearchPagePresenter } from './part-return-search-page.presenter';
import { IFrameMessageSource, UrlSegment, IFrameService } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-part-return-search-page',
  templateUrl: './part-return-search-page.component.html',
  styles: [``],
  providers: [PartReturnSearchPageApiService, PartReturnSearchPagePresenter]
})
export class PartReturnSearchPageComponent implements OnInit, AfterViewInit, AfterContentInit {

  dataTable: DataTable;
  totalTableElements: number;
  actionButtons
  searchValue: ColumnSearch;
  clickOnTableFields: Array<TableHeading> = [{ title: 'returnNo', isClickable: true }, { title: 'edit', isClickable: true }];
  partReturnSearchForm: FormGroup;
  searchFilter = {} as SparePartReturnSearchFilter;
  searchFilterValues: any;
  returnNoNgModel: any
  returnDateNgModel: any
  reasonForReturnNgModel: any
  requisitionNoNgModel: any
  requisitionPurposeNgModel: any
  jobCardNoNgModel: any
  jobCardDateNgModel: any
  partsIssuedByNgModel: any
  requisitionDateNgModel: any
  clearSearchRow = new BehaviorSubject<string>("");
  today = new Date();
  minDate: Date;
  maxDate: Date
  key='prsp';
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private partReturnSearchPagePresenter: PartReturnSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private iFrameService: IFrameService,
    private partReturnSearchPageApiService: PartReturnSearchPageApiService,
    private toastr: ToastrService
  ) { }
  ngAfterContentInit() {
    this.clearSearchField()

  }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.partReturnSearchForm = this.partReturnSearchPagePresenter.partReturnSearchForm;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.partReturnSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.partReturnSearchForm.get('partReturnNo').value==null && this.partReturnSearchForm.get('reasonForReturn').value==null && this.partReturnSearchForm.get('jobCardNo').value==null && this.partReturnSearchForm.get('requisitionNo').value==null && this.partReturnSearchForm.get('requisitionPurpose').value==null && this.partReturnSearchForm.get('requisitionFromDate').value==null && this.partReturnSearchForm.get('requisitionToDate').value==null && this.partReturnSearchForm.get('issueFromDate').value==null && this.partReturnSearchForm.get('issueToDate').value==null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.partReturnSearchForm.get('requisitionFromDate').patchValue(backDate);
      this.partReturnSearchForm.get('requisitionToDate').patchValue(new Date());
      this.partReturnSearchForm.get('issueFromDate').patchValue(backDate);
      this.partReturnSearchForm.get('issueToDate').patchValue(new Date());
      this.searchPartReturn();
    } else {
      localStorage.getItem(this.key)
      this.searchPartReturn();
    }
  }
  ngAfterViewInit(): void {

  }
  pageChangeOfSearchTable(event) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.partReturnSearchForm.patchValue(event);
    this.searchPartReturn();
  }
  actionOnTableRecord(recordData) {
    let id = btoa(recordData.record.id)
    if (recordData.btnAction === 'returnNo') {
      this.router.navigate(['view/', id], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['update/', id], { relativeTo: this.activatedRoute })
    }
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  columnLengthChanges(tableColumn: string) {
    this.partReturnSearchForm.patchValue({ tableColumn });
    this.searchPartReturn();
  }
  searchPartReturn() {
    this.clearSearchField()
    if (this.partReturnSearchForm.invalid) {
      return;
    }
    if (this.dataTable != undefined) {
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
    const searchPartReturn: SparePartReturnSearchFilter = this.partReturnSearchForm.getRawValue();
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.partReturnSearchForm.value))
    console.log('searchPartReturn: ', searchPartReturn);
    ObjectUtil.removeNulls(searchPartReturn);
    if (this.checkValidDatesInput()) {
      console.log('searchPartReturn: ', this.partReturnSearchForm.value.returnNo);
      if (this.partReturnSearchForm.get('partReturnNo').value || this.partReturnSearchForm.get('reasonForReturn').value || this.partReturnSearchForm.get('jobCardNo').value || this.partReturnSearchForm.get('requisitionNo').value || this.partReturnSearchForm.get('requisitionPurpose').value || this.partReturnSearchForm.get('requisitionFromDate').value || this.partReturnSearchForm.get('requisitionToDate').value || this.partReturnSearchForm.get('issueFromDate').value || this.partReturnSearchForm.get('issueToDate').value) {
        this.searchFilter = searchPartReturn;
        this.partReturnSearchPageApiService.searchPartReturn(searchPartReturn).subscribe(searchRes => {
          console.log('searchRes---> ', searchRes);
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes.result);
          this.totalTableElements = searchRes.count;
        })
      }
      else if (this.partReturnSearchForm.get('requisitionFromDate').value == null && this.partReturnSearchForm.get('requisitionToDate').value == null && this.partReturnSearchForm.get('issueFromDate').value == null && this.partReturnSearchForm.get('issueToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.partReturnSearchForm.value

    fltEnqObj.fromDate = this.partReturnSearchForm.getRawValue() ? this.partReturnSearchForm.value.requisitionFromDate : null
    fltEnqObj.toDate = this.partReturnSearchForm.getRawValue() ? this.partReturnSearchForm.value.requisitionToDate : null

    fltEnqObj.fromDate1 = this.partReturnSearchForm.getRawValue() ? this.partReturnSearchForm.value.jobCardFromDate : null
    fltEnqObj.toDate2 = this.partReturnSearchForm.getRawValue() ? this.partReturnSearchForm.value.jobCardToDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'fromDate1'];
    let toDates = ['toDate', 'toDate2'];
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
  resetSearchForm() {
    const { page, size, tableColumn } = this.partReturnSearchForm.getRawValue();
    this.partReturnSearchForm.reset();
    this.partReturnSearchForm.patchValue({ page, size, tableColumn });
    const searchPartReturn = this.partReturnSearchForm.getRawValue();
    ObjectUtil.removeNulls(searchPartReturn);
    this.clearSearchField()
    this.searchFilter = searchPartReturn;
    // this.searchPartReturn();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.partReturnSearchForm.patchValue(event);
    this.partReturnSearchForm.patchValue({
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
      issueFromDate: event.issueFromDate ? new Date(event.issueFromDate) : null,
      issueToDate: event.issueToDate ? new Date(event.issueToDate) : null
    });
    if (event.fromDate) {
      this.partReturnSearchForm.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.partReturnSearchForm.get('toDate').patchValue(new Date(event.toDate))
    }
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  clearSearchField() {
    this.returnNoNgModel = null
    this.returnDateNgModel = null
    this.reasonForReturnNgModel = null
    this.requisitionNoNgModel = null
    this.requisitionPurposeNgModel = null
    this.jobCardNoNgModel = null
    this.jobCardDateNgModel = null
    this.partsIssuedByNgModel = null
    this.requisitionDateNgModel = null
  }
}
