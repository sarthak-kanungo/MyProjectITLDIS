import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PdcSearchPageStore } from './pdc-search-page.store';
import { PdcSearchPagePresenter } from './pdc-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { FilterPdcSearch } from '../../domain/pdc-domain';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { PdcSearchPageWebService } from './pdc-search-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-pdc-search-page',
  templateUrl: './pdc-search-page.component.html',
  styleUrls: ['./pdc-search-page.component.scss'],
  providers: [PdcSearchPageStore, PdcSearchPagePresenter, PdcSearchPageWebService]
})
export class PdcSearchPageComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup
  pdcSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object
  clearSearchRow = new BehaviorSubject<string>("");
  editModel: any = "";
  pdcNoNgModel: any = "";
  pdcDateNgModel: any = "";
  chassisNoNgModel: any = "";
  engineNoNgModel: any = "";
  modelNgModel: any = "";
  nameNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key = 'pdc';
  constructor(
    private router: Router,
    private pdcSearchPagePresenter: PdcSearchPagePresenter,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private pdcSearchPageWebService: PdcSearchPageWebService,
    private pdcRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private tostr: ToastrService
  ) { }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.pdcSearchPagePresenter.pdcSearchForm
    this.pdcSearchForm = this.pdcSearchPagePresenter.detailsForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.pdcSearchForm != null) {
      this.pdcSearchForm.patchValue(this.searchFilterValues)
     localStorage.getItem(this.key)
      this.onClickSearchPdcDetails()
    }
    else {
      
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.pdcSearchForm.get('pdcFromDate').patchValue(backDate);
      this.pdcSearchForm.get('pdcToDate').patchValue(new Date());
      this.onClickSearchPdcDetails()
    }
    localStorage.removeItem('searchFilter');
    if (this.pdcSearchForm.get('chassisNo').value == null || this.pdcSearchForm.get('chassisNo').value == '' && this.pdcSearchForm.get('pdcFromDate').value == null || this.pdcSearchForm.get('pdcFromDate').value == '' && this.pdcSearchForm.get('pdcToDate').value == null || this.pdcSearchForm.get('pdcToDate').value == '') {
    
    }
    // else {
    //   localStorage.getItem(this.key)
    //   this.onClickSearchPdcDetails()
    // }
  }

  ngAfterViewInit() {
    // this.onClickSearchPdcDetails()
  }

  onClickSearchPdcDetails() {

    this.editModel = "";
    this.pdcNoNgModel = "";
    this.pdcDateNgModel = "";
    this.chassisNoNgModel = "";
    this.engineNoNgModel = "";
    this.modelNgModel = "";
    this.nameNgModel = "";
    if (this.checkValidDatesInput()) {
      if (this.pdcSearchForm.get('chassisNo').value || this.pdcSearchForm.get('pdcFromDate').value || this.pdcSearchForm.get('pdcToDate').value) {
        localStorage.setItem(this.key, JSON.stringify(this.pdcSearchForm.value))
        this.pdcSearchPageWebService.getPdcList(this.buildObjForSearchPdc()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
          this.totalTableElements = response.count
        })
      }
      else if (this.pdcSearchForm.get('pdcFromDate').value == null && this.pdcSearchForm.get('pdcToDate').value == null) {
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
  checkValidDatesInput() {
    const fltEnqObj = this.pdcSearchForm.value

    fltEnqObj.fromDate = this.pdcSearchForm.getRawValue() ? this.pdcSearchForm.value.pdcFromDate : null
    fltEnqObj.toDate = this.pdcSearchForm.getRawValue() ? this.pdcSearchForm.value.pdcToDate : null

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
  onClickClearPdcDetails() {
    this.pdcSearchForm.reset()
    // this.onClickSearchPdcDetails()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  private buildObjForSearchPdc() {
    const filtObj = {} as FilterPdcSearch
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
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.pdcSearchForm.value))
    // filtObj.page = this.page
    // filtObj.size = this.size
    filtObj.chassisNo = this.pdcSearchForm.value ? this.pdcSearchForm.value.chassisNo : null
    filtObj.pdcFromDate = this.pdcSearchForm.value.pdcFromDate ? this.dateService.getDateIntoYYYYMMDD(this.pdcSearchForm.value.pdcFromDate) : null
    filtObj.pdcToDate = this.pdcSearchForm.value.pdcToDate ? this.dateService.getDateIntoYYYYMMDD(this.pdcSearchForm.value.pdcToDate) : null
    filtObj.orgId = this.pdcSearchForm.value.orgHierarchyId ? this.pdcSearchForm.value.orgHierarchyId :null;
 
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.pdcSearchForm.patchValue(event)
    if (event.chassisNo) {
      this.pdcSearchForm.get('chassisNo').patchValue(event.chassisNo.replace(searchValue, newValue));
      event.chassisNo = event.chassisNo.replace(searchValue, newValue)
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'pdcFromDate' || element === 'pdcToDate')) {
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
    let id = btoa(recordData.record.id)
    let filterdata = btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'pdcno') {
      this.router.navigate(['view', id], { relativeTo: this.pdcRt })
    }
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.pdcRt })
    }
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearchPdcDetails()
  }

  pdcDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}
