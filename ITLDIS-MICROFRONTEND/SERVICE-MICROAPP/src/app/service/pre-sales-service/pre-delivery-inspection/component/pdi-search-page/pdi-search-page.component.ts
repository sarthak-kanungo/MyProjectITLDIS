import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SearchPdiStore } from './search-pdi-page.store';
import { Router, ActivatedRoute } from '@angular/router';
import { PdiSearchPresenter } from './search-pdi-presenter';
import { PdiSearchPageWebService } from './pdi-search-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-page',
  templateUrl: './pdi-search-page.component.html',
  styleUrls: ['./pdi-search-page.component.scss'],
  providers: [SearchPdiStore, PdiSearchPresenter, PdiSearchPageWebService]
})
export class PdiSearchPageComponent implements OnInit, AfterViewInit {
  pdiSearchPageForm: FormGroup
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  public totalTableElements: number;
  searchValue: ColumnSearch;
  pdiDetailsSearchForm: FormGroup
  searchFilter;
  clearSearchRow = new BehaviorSubject<string>("");
  actionNgModel: any = "";
  Chassis_NumberNgModel: any = "";
  PDI_NumberNgModel: any = "";
  PDI_DateNgModel: any = "";
  Engine_NumberNgModel: any = "";
  KAI_Invoice_NumberNgModel: any = "";
  Machine_ModelNgModel: any = "";
  DMS_GRN_NumberNgModel: any = "";
  dMSGRNDateNgModel: any = "";
  PDI_StatusNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  newdate = new Date()
  maxDate: Date
  minDate: Date
  key='pdi'
  constructor(
    private pdiSearchPresenter: PdiSearchPresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private pdiSearchPageService: PdiSearchPageWebService,
    private activatedRoute: ActivatedRoute,
    private dateService: DateService,
    private iFrameService: IFrameService,
    private tostr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.pdiSearchPageForm = this.pdiSearchPresenter.searchPdiForm
    this.pdiDetailsSearchForm = this.pdiSearchPageForm.controls.basicPdiSearch as FormGroup
    if (this.searchFilterValues != null || this.searchFilterValues != undefined &&   this.pdiDetailsSearchForm!=null) {
      this.pdiDetailsSearchForm.patchValue(this.searchFilterValues)
      localStorage.getItem(this.key)
      // this.searchPreDeliveryForm();
    }
    else{
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.pdiDetailsSearchForm.get('dmsgrnFromDate').patchValue(backDate);
      this.pdiDetailsSearchForm.get('dmsgrnToDate').patchValue(new Date());
      this.pdiDetailsSearchForm.get('pdiDateFrom').patchValue(backDate);
      this.pdiDetailsSearchForm.get('pdiDateTo').patchValue(new Date())
    }
    localStorage.removeItem('searchFilter');
  }
  searchPreDeliveryForm() {
    this.actionNgModel = "";
    this.Chassis_NumberNgModel = "";
    this.PDI_NumberNgModel = "";
    this.PDI_DateNgModel = "";
    this.Engine_NumberNgModel = "";
    this.KAI_Invoice_NumberNgModel = "";
    this.Machine_ModelNgModel = "";
    this.DMS_GRN_NumberNgModel = "";
    this.dMSGRNDateNgModel = "";

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

    // this.searchFlag = true;
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.pdiDetailsSearchForm.value))
    let pdiSearchDto: object = {} as object;
    pdiSearchDto['chassisNo'] = this.pdiDetailsSearchForm.get('chassisNo').value ? this.pdiDetailsSearchForm.get('chassisNo').value.code : null
    pdiSearchDto['pdiId'] = this.pdiDetailsSearchForm.get('chassisNo').value ? this.pdiDetailsSearchForm.get('chassisNo').value.id : null
    pdiSearchDto['kaiInvoiceNumber'] = this.pdiDetailsSearchForm.get('kaiInvoiceNo').value ? this.pdiDetailsSearchForm.get('kaiInvoiceNo').value.value : null
    pdiSearchDto['dmsGrnNumber'] = this.pdiDetailsSearchForm.get('dmsgrnNo').value ? this.pdiDetailsSearchForm.get('dmsgrnNo').value.value : null
    pdiSearchDto['pdiFromDate'] = this.pdiDetailsSearchForm.get('pdiDateFrom').value ? this.dateService.getDateIntoYYYYMMDD(this.pdiDetailsSearchForm.value.pdiDateFrom) : null
    pdiSearchDto['pdiToDate'] = this.pdiDetailsSearchForm.get('pdiDateTo').value ? this.dateService.getDateIntoYYYYMMDD(this.pdiDetailsSearchForm.value.pdiDateTo) : null
    pdiSearchDto['dmsGrnFromDate'] = this.pdiDetailsSearchForm.get('dmsgrnFromDate').value ? this.dateService.getDateIntoYYYYMMDD(this.pdiDetailsSearchForm.value.dmsgrnFromDate) : null
    pdiSearchDto['dmsGrnToDate'] = this.pdiDetailsSearchForm.get('dmsgrnToDate').value ? this.dateService.getDateIntoYYYYMMDD(this.pdiDetailsSearchForm.value.dmsgrnToDate) : null
    pdiSearchDto['page'] = this.page;
    pdiSearchDto['size'] = this.size;
    pdiSearchDto['orgId'] = this.pdiDetailsSearchForm.value.orgHierarchyId ? this.pdiDetailsSearchForm.value.orgHierarchyId :null;
 
    this.searchFilter = ObjectUtil.removeNulls(pdiSearchDto);
    if (this.checkValidDatesInput()) {
      if (this.pdiDetailsSearchForm.get('chassisNo').value || this.pdiDetailsSearchForm.get('kaiInvoiceNo').value || this.pdiDetailsSearchForm.get('dmsgrnNo').value || this.pdiDetailsSearchForm.get('dmsgrnFromDate').value || this.pdiDetailsSearchForm.get('dmsgrnToDate').value || this.pdiDetailsSearchForm.get('pdiDateFrom').value || this.pdiDetailsSearchForm.get('pdiDateTo').value) {
        this.pdiSearchPageService.searchPdiTable(pdiSearchDto).subscribe(res => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
          this.totalTableElements = res['count'];
        })
      }
      else if (this.pdiDetailsSearchForm.get('dmsgrnFromDate').value == null && this.pdiDetailsSearchForm.get('dmsgrnToDate').value == null && this.pdiDetailsSearchForm.get('pdiDateFrom').value == null && this.pdiDetailsSearchForm.get('pdiDateTo').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
  }
  pageLoadCount:number=0
  pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchPreDeliveryForm();
    }
    this.pageLoadCount = 1;
    
  }
  ngAfterViewInit() {
    
  }
  createPdi() {
    this.router.navigate(['../create'], {
      relativeTo: this.activatedRoute
    })
  }
  tableAction(event: object) {
    if (event['btnAction'].toLowerCase() === 'action') {
      let id = btoa(event['record']['id'])
      let searchfilter = btoa(JSON.stringify(this.searchFilter))
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      });
    }
    if (event['btnAction'].toLowerCase() === 'chassis_number') {
      let id = btoa(event['record']['id'])
      let searchfilter = btoa(JSON.stringify(this.searchFilter))
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      });
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.pdiDetailsSearchForm.value

    fltEnqObj.fromDate = this.pdiDetailsSearchForm.getRawValue() ? this.pdiDetailsSearchForm.value.dmsgrnFromDate : null
    fltEnqObj.toDate = this.pdiDetailsSearchForm.getRawValue() ? this.pdiDetailsSearchForm.value.dmsgrnToDate : null
    fltEnqObj.fromDate1 = this.pdiDetailsSearchForm.getRawValue() ? this.pdiDetailsSearchForm.value.pdiDateFrom : null
    fltEnqObj.toDate1 = this.pdiDetailsSearchForm.getRawValue() ? this.pdiDetailsSearchForm.value.pdiDateTo : null

    let fromdates = ['fromDate', 'fromDate1'];
    let toDates = ['toDate', 'toDate1'];
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
    }
    else {
      return true;
    }
  }
  onClickClearPdiDetails() {
    this.pdiDetailsSearchForm.reset()
    // this.searchPreDeliveryForm()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  /**
* ----------Following is state management code------------
*/

  initialQueryParams(event) {
    const searchValue = /%2F/g;
    const newValue = '/';
    this.pdiSearchPageForm.get('basicPdiSearch').patchValue(event);

    if (event.pdiId) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('chassisNo').patchValue({ code: event.chassisNo, id: event.pdiId });
    }
    if (event.kaiInvoiceNumber) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('kaiInvoiceNo').patchValue(event.kaiInvoiceNumber);
    }
    if (event.dmsGrnNumber) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('dmsgrnNo').patchValue(event.dmsGrnNumber);
    }
    if (event.pdiFromDate) {

      this.pdiSearchPageForm.controls.basicPdiSearch.get('pdiDateFrom').patchValue(event.pdiFromDate);
    }
    if (event.pdiToDate) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('pdiDateTo').patchValue(event.pdiToDate);
    }
    if (event.dmsGrnFromDate) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('dmsgrnFromDate').patchValue(event.dmsGrnFromDate);
    }
    if (event.dmsGrnToDate) {
      this.pdiSearchPageForm.controls.basicPdiSearch.get('dmsgrnToDate').patchValue(event.dmsGrnToDate);
    }
    this.page = event['page'];
    this.size = event['size'];



    this.searchPreDeliveryForm();
  }

  onUrlChange(event) {
    
    if (!event) {
      return;
    }

    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }
}
