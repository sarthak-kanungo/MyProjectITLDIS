import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ReInstallationSearchPageStore } from './re-installtion-search-page.store';
import { ReInstallationSearchPagePresenter } from './re-installation-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { FilterReInstallation } from '../../domain/re-installation-domain';
import { ReInstallationSearchPageWebService } from './re-installation-search-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-re-installation-search-page',
  templateUrl: './re-installation-search-page.component.html',
  styleUrls: ['./re-installation-search-page.component.scss'],
  providers: [ReInstallationSearchPageStore, ReInstallationSearchPagePresenter, ReInstallationSearchPageWebService]
})
export class ReInstallationSearchPageComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup
  reInstallationSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object
  clearSearchRow = new BehaviorSubject<string>("");
  editNgModel: any = "";
  machineSeriesNgModel: any = "";
  serviceStaffNameNgModel: any = "";
  reInstallationNumberNgModel: any = "";
  reInstallationDateNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key='reinstallPage';
  constructor(
    private reInstallationSearchPagePresenter: ReInstallationSearchPagePresenter,
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private reInstallationSearchPageWebService: ReInstallationSearchPageWebService,
    private reinstallRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private tostr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.reInstallationSearchPagePresenter.reInstallationSearchForm
    this.reInstallationSearchForm = this.reInstallationSearchPagePresenter.detailsForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.reInstallationSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilterRE');
    if (this.reInstallationSearchForm.get('series').value==null && this.reInstallationSearchForm.get('serviceStaffName').value==null && this.reInstallationSearchForm.get('reInstallationNo').value==null && this.reInstallationSearchForm.get('fromDate').value==null && this.reInstallationSearchForm.get('toDate').value==null) {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.reInstallationSearchForm.get('fromDate').patchValue(backDate);
    this.reInstallationSearchForm.get('toDate').patchValue(new Date());
    this.onClickSearchInstallationDetails()
    }
    else{
      localStorage.getItem(this.key)
      this.onClickSearchInstallationDetails()
    }
  }
  ngAfterViewInit() {
    // this.onClickSearchInstallationDetails()
  }
  onClickSearchInstallationDetails() {

    this.editNgModel = "";
    this.machineSeriesNgModel = "";
    this.serviceStaffNameNgModel = "";
    this.reInstallationNumberNgModel = "";
    this.reInstallationDateNgModel = "";
    if (this.checkValidDatesInput()) {
      if (this.reInstallationSearchForm.get('series').value || this.reInstallationSearchForm.get('serviceStaffName').value || this.reInstallationSearchForm.get('reInstallationNo').value || this.reInstallationSearchForm.get('fromDate').value || this.reInstallationSearchForm.get('toDate').value) {
        this.reInstallationSearchPageWebService.searchRi(this.buildObjForSearchInstallation()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
          this.totalTableElements = response['count']
        })
      }
      else if (this.reInstallationSearchForm.get('fromDate').value == null && this.reInstallationSearchForm.get('toDate').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
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
    this.searchFlag = true;
  }

  private buildObjForSearchInstallation() {
    const filtObj = {} as FilterReInstallation
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      filtObj.page = this.page
      filtObj.size = this.size
    }
    else {
      filtObj.page = this.page
      filtObj.size = this.size
    }
    let key = 'searchFilterRE';
    localStorage.setItem(this.key, JSON.stringify(this.reInstallationSearchForm.value))
    // filtObj.page = this.page
    // filtObj.size = this.size
    filtObj.serviceStaffName = this.reInstallationSearchForm.value ? this.reInstallationSearchForm.value.serviceStaffName : null
    filtObj.series = this.reInstallationSearchForm.value ? this.reInstallationSearchForm.value.series : null
    filtObj.reInstallationNo = this.reInstallationSearchForm.value ? this.reInstallationSearchForm.value.reInstallationNo : null
    filtObj.fromDate = this.reInstallationSearchForm.value.fromDate ? this.dateService.getDateIntoYYYYMMDD(this.reInstallationSearchForm.value.fromDate) : null
    filtObj.toDate = this.reInstallationSearchForm.value.toDate ? this.dateService.getDateIntoYYYYMMDD(this.reInstallationSearchForm.value.toDate) : null
    console.log(filtObj);
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.reInstallationSearchForm.patchValue(event)
    if (event.reInstallationNo) {
      this.reInstallationSearchForm.get('reInstallationNo').patchValue(event.reInstallationNo.replace(searchValue, newValue));
      event.reInstallationNo = event.reInstallationNo.replace(searchValue, newValue)
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.reInstallationSearchForm.value

    fltEnqObj.fromDate = this.reInstallationSearchForm.getRawValue() ? this.reInstallationSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.reInstallationSearchForm.getRawValue() ? this.reInstallationSearchForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
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
  onClickClearInstallationDetails() {
    this.reInstallationSearchForm.reset()
    // this.onClickSearchInstallationDetails()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  }

  actionOnTableRecord(recordData: any) {
    let id=btoa(recordData.record.id)
    let filterdata=btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'reinstallationnumber') {
      this.router.navigate(['view', id], { relativeTo: this.reinstallRt, queryParams: { filterData: filterdata } })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.reinstallRt, queryParams: { filterData: filterdata } })
    }
  }
  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearchInstallationDetails()
  }
  reInstallationDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}