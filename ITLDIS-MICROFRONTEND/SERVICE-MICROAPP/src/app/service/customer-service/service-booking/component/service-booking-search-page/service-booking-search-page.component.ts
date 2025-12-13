import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ServiceBookingSearchPageStore } from './service-booking-search-page.store';
import { ServiceBookingSearchPagePresenter } from './service-booking-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ServiceBookingSearchPageWebService } from './service-booking-search-page-web.service';
import { FilterSearchServiceBooking } from '../../domain/service-booking-domain';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-service-booking-search-page',
  templateUrl: './service-booking-search-page.component.html',
  styleUrls: ['./service-booking-search-page.component.scss'],
  providers: [ServiceBookingSearchPageStore, ServiceBookingSearchPagePresenter, ServiceBookingSearchPageWebService]
})
export class ServiceBookingSearchPageComponent implements OnInit, AfterViewInit {

  isAdvanceSearch: boolean
  searchForm: FormGroup
  serviceBookingSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  editNgModel: any = "";
  bookingNoNgModel: any = "";
  bookingDateNgModel: any = "";
  statusNgModel: any = "";
  reasonForCancellationNgModel: any = "";
  chassisNoNgModel: any = "";
  mechanicNameNgModel: any = "";
  customerNameNgModel: any = "";
  engineNoNgModel: any = "";
  modelNgModel: any = "";
  dateOfInstallationNgModel: any = "";
  hoursNgModel: any = "";
  sourceOfBookingNgModel: any = "";
  serviceCategoryNgModel: any = "";
  placeOfServiceNgModel: any = "";
  serviceTypeNgModel: any = "";
  appointmentDateNgModel: any = "";
  appointmentTimeNgModel: any = "";
  remarksNgModel: any = "";
  addressNgModel: any = "";
  mobileNoNgModel: any = "";
  searchFilterValues: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key: string;
  userType:string
  constructor(
    private serviceBookingSearchPagePresenter: ServiceBookingSearchPagePresenter,
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private serviceBookingSearchPageWebService: ServiceBookingSearchPageWebService,
    private dateService: DateService,
    private bookingRt: ActivatedRoute,
    private iFrameService: IFrameService,
    private tostr: ToastrService,
    private loginService : LoginFormService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.userType = this.loginService.getLoginUserType();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.serviceBookingSearchPagePresenter.serviceBookingSearchForm
    this.serviceBookingSearchForm = this.serviceBookingSearchPagePresenter.detailsForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.serviceBookingSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.serviceBookingSearchForm.get('bookingNo').value==null && this.serviceBookingSearchForm.get('status').value==null && this.serviceBookingSearchForm.get('chassisNo').value==null && this.serviceBookingSearchForm.get('sourceOfBooking').value==null && this.serviceBookingSearchForm.get('bookingFromDate').value==null && this.serviceBookingSearchForm.get('bookingToDate').value==null) {

    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.serviceBookingSearchForm.get('bookingFromDate').patchValue(backDate);
    this.serviceBookingSearchForm.get('bookingToDate').patchValue(new Date());
    this.onClickSearchServiceBookingDetails()
    }
    else{
      localStorage.getItem(this.key)
      this.onClickSearchServiceBookingDetails()
    }

  }

  ngAfterViewInit() {
    // this.onClickSearchServiceBookingDetails()
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch
  }

  onClickSearchServiceBookingDetails() {

    this.editNgModel = "";
    this.bookingNoNgModel = "";
    this.bookingDateNgModel = "";
    this.statusNgModel = "";
    this.reasonForCancellationNgModel = "";
    this.chassisNoNgModel = "";
    this.mechanicNameNgModel = "";
    this.customerNameNgModel = "";
    this.engineNoNgModel = "";
    this.modelNgModel = "";
    this.dateOfInstallationNgModel = "";
    this.hoursNgModel = "";
    this.sourceOfBookingNgModel = "";
    this.serviceCategoryNgModel = "";
    this.placeOfServiceNgModel = "";
    this.serviceTypeNgModel = "";
    this.appointmentDateNgModel = "";
    this.appointmentTimeNgModel = "";
    this.remarksNgModel = "";
    this.addressNgModel = "";
    this.mobileNoNgModel = "";

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
    if (this.checkValidDatesInput()) {
      if (this.serviceBookingSearchForm.get('bookingNo').value || this.serviceBookingSearchForm.get('status').value || this.serviceBookingSearchForm.get('chassisNo').value || this.serviceBookingSearchForm.get('sourceOfBooking').value || this.serviceBookingSearchForm.get('bookingFromDate').value || this.serviceBookingSearchForm.get('bookingToDate').value) {
        this.serviceBookingSearchPageWebService.serviceBookingSearch(this.buildObjForSearchServiceBooking()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
          this.totalTableElements = response['count']
        })
      }
      else if (this.serviceBookingSearchForm.get('bookingFromDate').value == null && this.serviceBookingSearchForm.get('bookingToDate').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
  }
  private buildObjForSearchServiceBooking() {
    const filtObj = {} as FilterSearchServiceBooking
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
    localStorage.setItem(this.key, JSON.stringify(this.serviceBookingSearchForm.value))
    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.bookingNo = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.bookingNo : null
    filtObj.chassisNo = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.chassisNo : null
    filtObj.machineSubModel = this.serviceBookingSearchForm.value.machineSubModel ? this.serviceBookingSearchForm.value.machineSubModel.model : null
    filtObj.mechanicName = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.mechanicName : null
    filtObj.placeOfService = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.placeOfService : null
    filtObj.serviceCategory = this.serviceBookingSearchForm.value.serviceCategory ? this.serviceBookingSearchForm.value.serviceCategory.category : null
    filtObj.sourceOfBooking = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.sourceOfBooking : null
    filtObj.activityType = this.serviceBookingSearchForm.value.activityType ? this.serviceBookingSearchForm.value.activityType.activityType : null
    filtObj.activityNo = this.serviceBookingSearchForm.value.activityNo ? this.serviceBookingSearchForm.value.activityNo.activityNumber : null
    filtObj.serviceType = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.serviceType : null
    filtObj.status = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.status : null
    filtObj.engineNo = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.engineNo : null
    filtObj.bookingFromDate = this.serviceBookingSearchForm.value.bookingFromDate ? this.dateService.getDateIntoYYYYMMDD(this.serviceBookingSearchForm.value.bookingFromDate) : null
    filtObj.bookingToDate = this.serviceBookingSearchForm.value.bookingToDate ? this.dateService.getDateIntoYYYYMMDD(this.serviceBookingSearchForm.value.bookingToDate) : null
    filtObj.appointmentFromDate = this.serviceBookingSearchForm.value.appointmentFromDate ? this.dateService.getDateIntoYYYYMMDD(this.serviceBookingSearchForm.value.appointmentFromDate) : null
    filtObj.appointmentToDate = this.serviceBookingSearchForm.value.appointmentToDate ? this.dateService.getDateIntoYYYYMMDD(this.serviceBookingSearchForm.value.appointmentToDate) : null
    filtObj.totalCount = this.serviceBookingSearchForm.value ? this.serviceBookingSearchForm.value.totalCount : null

    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.serviceBookingSearchForm.patchValue(event)
    if (event.bookingNo) {
      this.serviceBookingSearchForm.get('bookingNo').patchValue(event.bookingNo.replace(searchValue, newValue));
      event.bookingNo = event.bookingNo.replace(searchValue, newValue)
    }
  }

  onClickClearServiceBookingDetails() {
    this.serviceBookingSearchForm.reset()
    // this.onClickSearchServiceBookingDetails()
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
        if (searchObject[element] && (element === 'bookingFromDate' || element === 'bookingToDate' || element === 'appointmentFromDate' || element === 'appointmentToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.serviceBookingSearchForm.value

    fltEnqObj.fromDate = this.serviceBookingSearchForm.getRawValue() ? this.serviceBookingSearchForm.value.bookingFromDate : null
    fltEnqObj.toDate = this.serviceBookingSearchForm.getRawValue() ? this.serviceBookingSearchForm.value.bookingToDate : null

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
    if (recordData.btnAction.toLowerCase() === 'bookingno') {
      this.router.navigate(['view', id], { relativeTo: this.bookingRt, queryParams: { filterData: filterdata } })
    }
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.bookingRt, queryParams: { filterData: filterdata } })
    }
  }
  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearchServiceBookingDetails()
  }
  serviceBookingDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }


}