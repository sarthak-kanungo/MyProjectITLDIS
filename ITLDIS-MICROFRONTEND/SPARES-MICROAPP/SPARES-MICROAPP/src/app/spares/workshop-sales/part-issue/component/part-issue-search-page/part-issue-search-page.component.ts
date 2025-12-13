import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PartIssueSearchPagePresenter } from './part-issue-search-page.presenter';
import { NgswSearchTableService, ColumnSearch, DataTable, TableHeading } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { SparePartIssueSearchFilter } from '../../domain/part-issue.domain';
import { ObjectUtil } from '../../../../../utils/object-util';
import { PartIssueSearchPageApiService } from './part-issue-search-page-api.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-part-issue-search-page',
  templateUrl: './part-issue-search-page.component.html',
  styles: [``],
  providers: [PartIssueSearchPagePresenter, PartIssueSearchPageApiService]
})
export class PartIssueSearchPageComponent implements OnInit, AfterViewInit {

  dataTable: DataTable;
  totalTableElements: number;
  actionButtons
  searchValue: ColumnSearch;
  clickOnTableFields: Array<TableHeading> = [{ title: 'partIssueNo', isClickable: true }, { title: 'edit', isClickable: true }];
  partIssueSearchForm: FormGroup;
  searchFilter: SparePartIssueSearchFilter;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");
  partIssueNoNgModel: any
  requisitionPurposeNgModel: any
  requestedByNgModel: any
  requisitionNoNgModel: any
  requisitionDateNgModel: any
  jobCardNoNgModel: any
  jobCardDateNgModel: any
  issueDateNgModel: any
  partsIssuedByNgModel: any
  issueToNgModel: any
  today = new Date();
  minDate: Date;
  maxDate: Date
  key = 'pisp';
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private partIssueSearchPagePresenter: PartIssueSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private partIssueSearchPageApiService: PartIssueSearchPageApiService,
    private iFrameService: IFrameService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.partIssueSearchForm = this.partIssueSearchPagePresenter.partIssueSearchForm;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.partIssueSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.partIssueSearchForm.get('partIssueNo').value == null && this.partIssueSearchForm.get('jobCardNo').value == null && this.partIssueSearchForm.get('requisitionNo').value == null && this.partIssueSearchForm.get('requisitionPurpose').value == null && this.partIssueSearchForm.get('requisitionFromDate').value == null && this.partIssueSearchForm.get('requisitionToDate').value == null && this.partIssueSearchForm.get('issueFromDate').value == null && this.partIssueSearchForm.get('issueToDate').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.partIssueSearchForm.get('requisitionFromDate').patchValue(backDate);
      this.partIssueSearchForm.get('requisitionToDate').patchValue(new Date());
      this.partIssueSearchForm.get('issueFromDate').patchValue(backDate);
      this.partIssueSearchForm.get('issueToDate').patchValue(new Date());
      this.searchPartIssue();
    } else {
      localStorage.getItem(this.key)
      this.searchPartIssue();
    }
  }
  ngAfterViewInit(): void {

  }
  actionOnTableRecord(recordData) {
    let id = btoa(recordData.record.id)
    if (recordData.btnAction === 'partIssueNo') {
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
    this.partIssueSearchForm.patchValue({ tableColumn });
    this.searchPartIssue();
  }
  pageChangeOfSearchTable(event) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.partIssueSearchForm.patchValue(event);
    this.searchPartIssue();
  }
  searchPartIssue() {
    this.clearSearchFields();
    if (this.partIssueSearchForm.invalid) {
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
    const searchPartIssue: SparePartIssueSearchFilter = this.partIssueSearchForm.getRawValue();
    let key = 'searchFilter';
    
    localStorage.setItem(this.key, JSON.stringify(this.partIssueSearchForm.value))
    ObjectUtil.removeNulls(searchPartIssue);
    if (this.checkValidDatesInput()) {
      if (this.partIssueSearchForm.get('partIssueNo').value!=null || this.partIssueSearchForm.get('jobCardNo').value!==null || this.partIssueSearchForm.get('requisitionNo').value!=null || this.partIssueSearchForm.get('requisitionPurpose').value!=null || this.partIssueSearchForm.get('requisitionFromDate').value!=null || this.partIssueSearchForm.get('requisitionToDate').value!=null || this.partIssueSearchForm.get('issueFromDate').value!=null || this.partIssueSearchForm.get('issueToDate').value!=null) {
        this.searchFilter = searchPartIssue;
        this.partIssueSearchPageApiService.searchPartIssue(searchPartIssue).subscribe(searchRes => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes.result);
          this.totalTableElements = searchRes.count;
        })
      }
      else if (this.partIssueSearchForm.get('requisitionFromDate').value == null && this.partIssueSearchForm.get('requisitionToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
  }

  checkValidDatesInput() {
    const fltEnqObj = this.partIssueSearchForm.value

    fltEnqObj.fromDate = this.partIssueSearchForm.getRawValue() ? this.partIssueSearchForm.value.requisitionFromDate : null
    fltEnqObj.toDate = this.partIssueSearchForm.getRawValue() ? this.partIssueSearchForm.value.requisitionToDate : null

    fltEnqObj.fromDate1 = this.partIssueSearchForm.getRawValue() ? this.partIssueSearchForm.value.issueFromDate : null
    fltEnqObj.toDate2 = this.partIssueSearchForm.getRawValue() ? this.partIssueSearchForm.value.issueToDate : null

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
    const { page, size, tableColumn } = this.partIssueSearchForm.getRawValue();
    this.partIssueSearchForm.reset();
    this.partIssueSearchForm.patchValue({ page, size, tableColumn });
    const searchPartIssue = this.partIssueSearchForm.getRawValue();
    ObjectUtil.removeNulls(searchPartIssue);
    this.searchFilter = searchPartIssue;
    this.clearSearchFields();
    // this.searchPartIssue()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.partIssueSearchForm.patchValue(event);
    this.partIssueSearchForm.patchValue({
      issueFromDate: event.issueFromDate ? new Date(event.issueFromDate) : null,
      issueToDate: event.issueToDate ? new Date(event.issueToDate) : null,
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
    });
    if (event.fromDate) {
      this.partIssueSearchForm.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.partIssueSearchForm.get('toDate').patchValue(new Date(event.toDate))
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

  clearSearchFields() {
    this.partIssueNoNgModel = "";
    this.requisitionPurposeNgModel = "";
    this.requestedByNgModel = "";
    this.requisitionNoNgModel = "";
    this.requisitionDateNgModel = "";
    this.jobCardNoNgModel = "";
    this.jobCardDateNgModel = "";
    this.issueDateNgModel = "";
    this.partsIssuedByNgModel = "";
    this.issueToNgModel = "";
  }

}
