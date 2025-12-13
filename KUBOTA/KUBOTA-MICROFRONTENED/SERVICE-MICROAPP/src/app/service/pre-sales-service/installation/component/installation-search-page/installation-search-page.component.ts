import { Component, OnInit, AfterViewInit } from '@angular/core';
import { InstallationSearchPageStore } from './installation-search-page.store';
import { InstallationSearchPagePresenter } from './installation-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FilterInstallationSearch } from '../../domain/installation-domain';
import { InstallationSearchPageWebService } from './installation-search-page-web.service';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameMessageSource, UrlSegment, IFrameService } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-installation-search-page',
  templateUrl: './installation-search-page.component.html',
  styleUrls: ['./installation-search-page.component.scss'],
  providers: [InstallationSearchPageStore, InstallationSearchPagePresenter, InstallationSearchPageWebService]
})
export class InstallationSearchPageComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup
   installationSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  pageLoadCount:number=0
  public filterData: object
  clearSearchRow = new BehaviorSubject<string>("");
  editNgModel: any = "";
  chassisNoNgModel: any = "";
  installationTypeNgModel: any = "";
  installationNumberNgModel: any = "";
  installationDateNgModel: any = "";
  engineNoNgModel: any = "";
  modelNgModel: any = "";
  serviceStaffNameNgModel: any = "";
  representativeTypeNgModel: any = "";
  customerRepNameNgModel: any = "";
  customerNameNgModel: any = "";
  csbNoNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  key= 'installationPage';
  constructor(
    private installationSearchPagePresenter: InstallationSearchPagePresenter,
    private router: Router,
    private dateService: DateService,
    private installationSearchPageWebService: InstallationSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private installRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private tostr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.installationSearchPagePresenter.installationSearchForm
    this.installationSearchForm = this.installationSearchPagePresenter.detailsForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.installationSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilterIn');
    if (this.installationSearchForm.get('chassisNo').value==null && this.installationSearchForm.get('dealerCode').value==null && this.installationSearchForm.get('installationNo').value==null && this.installationSearchForm.get('installationType').value==null && this.installationSearchForm.get('fromDate').value==null && this.installationSearchForm.get('toDate').value==null) {
      this.maxDate= this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth()-1);
      this.minDate = backDate;
      this.installationSearchForm.get('fromDate').patchValue(backDate);
      this.installationSearchForm.get('toDate').patchValue(new Date());
      // this.onClickSearchInstallationDetails()
    }
    else{
      // this.onClickSearchInstallationDetails()
    }
  }

  ngAfterViewInit() {
    // this.onClickSearchInstallationDetails()
  }

  onClickSearchInstallationDetails() {
    let dealerId:string
    if (this.installationSearchForm.get('dealerShip').value) {
      dealerId=this.installationSearchForm.get('dealerShip').value.name
      
    }
    this.editNgModel = "";
    this.chassisNoNgModel = "";
    this.installationTypeNgModel = "";
    this.installationNumberNgModel = "";
    this.installationDateNgModel = "";
    this.engineNoNgModel = "";
    this.modelNgModel = "";
    this.serviceStaffNameNgModel = "";
    this.representativeTypeNgModel = "";
    this.customerRepNameNgModel = "";
    this.customerNameNgModel = "";
    this.csbNoNgModel = "";
    if (this.checkValidDatesInput()) {
      if (this.installationSearchForm.get('chassisNo').value || this.installationSearchForm.get('installationNo').value || this.installationSearchForm.get('installationType').value || this.installationSearchForm.get('fromDate').value || this.installationSearchForm.get('toDate').value|| this.installationSearchForm.get('dealerShip').value) {
        localStorage.setItem(this.key, JSON.stringify(this.installationSearchForm.value))
        this.installationSearchPageWebService.searchDi(this.buildObjForSearchInstallation()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
          this.totalTableElements = response.count
        })
      }
      else if (this.installationSearchForm.get('fromDate').value == null && this.installationSearchForm.get('toDate').value == null) {
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
  onClickClearInstallationDetails() {
    this.installationSearchForm.reset()
    // this.onClickSearchInstallationDetails()
    this.dataTable = null
    localStorage.removeItem(this.key)
  }
  checkValidDatesInput() {
    const fltEnqObj = this.installationSearchForm.value

    fltEnqObj.fromDate = this.installationSearchForm.getRawValue() ? this.installationSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.installationSearchForm.getRawValue() ? this.installationSearchForm.value.toDate : null

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
  private buildObjForSearchInstallation() {
    const filtObj = {} as FilterInstallationSearch
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
    let key = 'searchFilterIn';
    localStorage.setItem(this.key, JSON.stringify(this.installationSearchForm.value))
    // filtObj.page = this.page
    // filtObj.size = this.size
    filtObj['dealerId'] = this.installationSearchForm.value.dealerShip ? this.installationSearchForm.value.dealerShip['id'] : null
    filtObj.chassisNo = this.installationSearchForm.value ? this.installationSearchForm.value.chassisNo : null
    filtObj.installationNo = this.installationSearchForm.value ? this.installationSearchForm.value.installationNo : null
    filtObj.installationType = this.installationSearchForm.value.installationType ? this.installationSearchForm.value.installationType : null
    filtObj.fromDate = this.installationSearchForm.value.fromDate ? this.dateService.getDateIntoYYYYMMDD(this.installationSearchForm.value.fromDate) : null
    filtObj.toDate = this.installationSearchForm.value.toDate ? this.dateService.getDateIntoYYYYMMDD(this.installationSearchForm.value.toDate) : null
    console.log(filtObj,'filer');
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.installationSearchForm.patchValue(event)
    if (event.installationNo) {
      this.installationSearchForm.get('installationNo').patchValue(event.installationNo.replace(searchValue, newValue));
      event.installationNo = event.installationNo.replace(searchValue, newValue)
    }
  }

  actionOnTableRecord(recordData: any) {
    console.log("recordData ", recordData);
    let id= btoa(recordData.record.id)
    let filterdata=btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'installationnumber') {
      this.router.navigate(['view', id], { relativeTo: this.installRt, queryParams: { filterData: filterdata } })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.installRt, queryParams: { filterData: filterdata } })
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
  pageChange(event: any) {
    console.log("event ", event);
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.onClickSearchInstallationDetails()
    }
    this.pageLoadCount = 1;
    
  }
  installationDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}