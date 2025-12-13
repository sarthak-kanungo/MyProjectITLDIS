import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, AbstractControl } from '@angular/forms';
import { PscSearchPageStore } from './psc-search-page.store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { PscSearchPagePresenter } from './psc-search-page-presenter';
import { FilterSearchPsc } from '../../domain/psc-domain';
import { PscSearchPageWebService } from './psc-search-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-psc-search-page',
  templateUrl: './psc-search-page.component.html',
  styleUrls: ['./psc-search-page.component.scss'],
  providers: [PscSearchPageStore, PscSearchPagePresenter, PscSearchPageWebService]
})
export class PscSearchPageComponent implements OnInit, AfterViewInit {
  searchForm: FormGroup
  searchPscForm: AbstractControl
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object
  clearSearchRow = new BehaviorSubject<string>("");
  editNgModel: any = "";
  chassisNoNgModel: any = "";
  pscNoNgModel: any = "";
  pscDateNgModel: any = "";
  engineNoNgModel: any = "";
  machineModelNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key="psc";
  pageLoadCount:number=0
  constructor(
    private pscSearchPagePresenter: PscSearchPagePresenter,
    private router: Router,
    private pscSearchPageWebService: PscSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private pscRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private tostr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchForm = this.pscSearchPagePresenter.pscSearchForm
    this.searchPscForm = this.pscSearchPagePresenter.searchForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchPscForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.searchPscForm.get('chassisNo').value==null && this.searchPscForm.get('pscNo').value==null && this.searchPscForm.get('fromDate').value==null && this.searchPscForm.get('toDate').value==null) {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchPscForm.get('fromDate').patchValue(backDate);
    this.searchPscForm.get('toDate').patchValue(new Date());
    // this.onClickSearchPscDetails()
    }
    else{
      localStorage.getItem(this.key)
      // this.onClickSearchPscDetails()
    }
  }

  ngAfterViewInit() {
    // this.onClickSearchPscDetails()
  }

  onClickSearchPscDetails() {
    this.editNgModel = "";
    this.chassisNoNgModel = "";
    this.pscNoNgModel = "";
    this.pscDateNgModel = "";
    this.engineNoNgModel = "";
    this.machineModelNgModel = "";

    if (this.checkValidDatesInput()) {
      if (this.searchPscForm.get('chassisNo').value || this.searchPscForm.get('pscNo').value || this.searchPscForm.get('fromDate').value || this.searchPscForm.get('toDate').value) {
        this.pscSearchPageWebService.getPscList(this.buildObjForSearchPsc()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
          this.totalTableElements = response.count
        })
      }
      else if (this.searchPscForm.get('fromDate').value == null && this.searchPscForm.get('toDate').value == null) {
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

  onClickClearPscDetails() {
    this.searchForm.reset()
    // this.onClickSearchPscDetails()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchPscForm.value

    fltEnqObj.fromDate = this.searchPscForm.get('fromDate').value ? this.searchPscForm.value.fromDate : null
    fltEnqObj.toDate = this.searchPscForm.get('toDate').value ? this.searchPscForm.value.toDate : null

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
  private buildObjForSearchPsc() {
    const filtObj = {} as FilterSearchPsc
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
    // filtObj.page = this.page
    // filtObj.size = this.size
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchPscForm.value))
    filtObj.chassisNo = this.searchPscForm.value.chassisNo ? this.searchPscForm.value.chassisNo : null
    filtObj.pscNo = this.searchPscForm.value ? this.searchPscForm.value.pscNo : null
    filtObj.fromDate = this.searchPscForm.value.fromDate ? this.dateService.getDateIntoYYYYMMDD(this.searchPscForm.value.fromDate) : null
    filtObj.toDate = this.searchPscForm.value.toDate ? this.dateService.getDateIntoYYYYMMDD(this.searchPscForm.value.toDate) : null
    filtObj.orgId = this.searchPscForm.value.orgHierarchyId ? this.searchPscForm.value.orgHierarchyId :null;
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchPscForm.patchValue(event)
    if (event.chassisNo) {
      this.searchPscForm.get('chassisNo').patchValue(event.chassisNo.replace(searchValue, newValue));
      event.chassisNo = event.chassisNo.replace(searchValue, newValue)
    }
    if (event.pscNo) {
      this.searchPscForm.get('pscNo').patchValue(event.pscNo.replace(searchValue, newValue));
      event.pscNo = event.pscNo.replace(searchValue, newValue)
    }
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
    let filterdata= btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'pscno') {
      this.router.navigate(['view', id], { relativeTo: this.pscRt })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.pscRt })
    }
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.onClickSearchPscDetails()
    }
    this.pageLoadCount = 1;
    
  }

  pscDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}