import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ServiceActivityReportSearchStore } from '../service-activity-report-search-page/service-activity-report-search-page.store';
import { ServiceActivityReportSearchPresenter } from '../service-activity-report-search-page/service-activity-report-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DateService } from '../../../../../root-service/date.service';
import { FilterSearchServiceActivityReport } from '../../domain/service-activity-report.domain';
import { ServiceActivityReportSearchPageWebService } from './service-activity-report-search-page-web.service';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-service-activity-report-search-page',
  templateUrl: './service-activity-report-search-page.component.html',
  styleUrls: ['./service-activity-report-search-page.component.scss'],
  providers: [ServiceActivityReportSearchStore, ServiceActivityReportSearchPresenter, ServiceActivityReportSearchPageWebService]
})
export class ServiceActivityReportSearchPageComponent implements OnInit, AfterViewInit {
  searchFlag:boolean=true
  isAdvanceSearch: boolean
  searchForm: FormGroup
  searchServiceActivityReportForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData : object
  searchFilterValues: any;
  pageLoadCount:number=0
  clearSearchRow = new BehaviorSubject<string>("");
  loginUserType:any;
  constructor(
    private serviceActivityReportSearchPresenter: ServiceActivityReportSearchPresenter,
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private actReportRt: ActivatedRoute,
    private dateService: DateService,
    private serviceActivityReportSearchPageWebService: ServiceActivityReportSearchPageWebService,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private loginervice:LoginFormService
  ) { 
    this.loginUserType = loginervice.getLoginUserType();
  }

  ngOnInit() {
    this.searchFilterValues=localStorage.getItem('searchFilterSAR')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.serviceActivityReportSearchPresenter.serviceActivityReportSearchForm
    this.searchServiceActivityReportForm = this.serviceActivityReportSearchPresenter.detailsForm
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.searchServiceActivityReportForm.patchValue(this.searchFilterValues)
    }
  }

  ngAfterViewInit(){
    // this.onClickSearchServiceActivityReport()
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch
  }

  onClickSearchServiceActivityReport() {
    let searchObject = this.buildObjForSearchServiceActivityReport();
    let key = 'searchFilterSAR';
    localStorage.setItem(key, JSON.stringify(searchObject));
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
    }else if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }

    if (searchObject.activityDateFrom && searchObject.activityDateTo) {
      
    }else if (searchObject.activityDateFrom || searchObject.activityDateTo) {
      this.toastr.error("Please Select Date Range");
      return;
    }

    delete searchObject['page'];
    delete searchObject['size'];
    if(Object.keys(searchObject).length==0){
      this.toastr.error("Please fill atleast one input field");
      return;
    }
    
    searchObject['page'] = this.page;
    searchObject['size'] = this.size;
    
    this.searchFlag = true;
    this.serviceActivityReportSearchPageWebService.serviceActivityReportSearch(searchObject).subscribe(response => {
      let result = response['result'];
      if(result && this.loginUserType.toLocaleLowerCase()=='dealer'){
        result.forEach(res => {
          delete res['dealerCode'];
          delete res['dealerName'];
          delete res['dealerState'];
        })
      }
      this.dataTable = this.tableDataService.convertIntoDataTable(result);
      this.totalTableElements = response['count']
    })
  }

  private buildObjForSearchServiceActivityReport() {
    const filtObj = {} as FilterSearchServiceActivityReport
    const searchServiceActivityReportForm = this.searchServiceActivityReportForm.value
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.searchServiceActivityReportForm.value))
    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.activityNumber = searchServiceActivityReportForm ? searchServiceActivityReportForm.activityNumber : null
    filtObj.activityStatus = searchServiceActivityReportForm ? searchServiceActivityReportForm.activityStatus : null
    filtObj.activityType = searchServiceActivityReportForm ? searchServiceActivityReportForm.activityType : null
    filtObj.activityDateFrom = searchServiceActivityReportForm.activityDateFrom ? this.dateService.getDateIntoYYYYMMDD(searchServiceActivityReportForm.activityDateFrom) : null
    filtObj.activityDateTo = searchServiceActivityReportForm.activityDateTo ? this.dateService.getDateIntoYYYYMMDD(searchServiceActivityReportForm.activityDateTo) : null
    filtObj.orgHierId = searchServiceActivityReportForm.orgHierarchyId?searchServiceActivityReportForm.orgHierarchyId:null;
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  // initialQueryParams(event) {
  //   console.log('initialQueryParams event: ', event)
  //   const searchValue = /%2F/g
  //   const newValue = '/'
  //   this.searchServiceActivityReportForm.patchValue(event)
  //   if (event.activityNumber) {
  //     this.searchServiceActivityReportForm.get('activityNumber').patchValue(event.activityNumber.replace(searchValue, newValue));
  //     event.activityNumber = event.activityNumber.replace(searchValue, newValue)
  //   }  
  // }

  onClickClearServiceActivityReport() {
    this.searchServiceActivityReportForm.reset()
    this.dataTable = null;
    this.clearSearchRow.next("");
  }


  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'activityDateFrom' || element === 'activityDateTo')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  // onUrlChange(event) {
  //   console.log('onUrlChange event: ', event);
  //   if (!event) {
  //     return;
  //   }
  //   const { queryParam = null, url = '' } = { ...event };
  //   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam } as UrlSegment);
  // }

  actionOnTableRecord(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'activitynumber') {
      this.router.navigate(['view', recordData.record.id], { relativeTo: this.actReportRt,  queryParams: {filterData: JSON.stringify(this.filterData) } })
    }
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.onClickSearchServiceActivityReport()
    }
    this.pageLoadCount = 1;
    // this.onClickSearchServiceActivityReport()
  }

  serviceActivityReportDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}
