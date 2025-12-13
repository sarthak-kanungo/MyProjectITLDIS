import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NgswSearchTableService } from 'projects/ngsw-search-table/src/lib/ngsw-search-table.service';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { IFrameMessageSource, IFrameService, UrlSegment } from 'src/app/root-service/iFrame.service';
import { DataTable } from 'src/app/ui/dynamic-table/dynamic-table.domain';
import { PlSearch } from '../domain/pl.domain';
import { PlSearchPagePresenter } from './pl-search-page.presenter';
import { PlSearchPageService } from './pl-search-page.service';
import { PlSearchPageStore } from './pl-search-page.store';

@Component({
  selector: 'app-picklist-search-page',
  templateUrl: './picklist-search-page.component.html',
  styleUrls: ['./picklist-search-page.component.css'],
  providers: [PlSearchPagePresenter, PlSearchPageStore, PlSearchPageService, NgswSearchTableService]
})
export class PicklistSearchPageComponent implements OnInit {

  plSearchForm: FormGroup
  plSearchResultForm: FormGroup
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  public filterData: object;
  totalSearchRecordCount: number;


  EditNgModel: any = "";
  pickListNumberNgModel: any = "";
  pickListDateNgModel: any = "";
  salesOrderNumberNgModel: any = "";
  salesOrderDateNgModel: any = "";
  customerNameNgModel: any = "";
  mobileNumberNgModel: any = "";
  clearSearchRow = new BehaviorSubject<string>("");
  cityNgModel: any = "";
  pickListStatusNgModel: any = "";
  searchFilterValues: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  key='picklist-search';

  constructor(private presenter: PlSearchPagePresenter,
    private dateService: DateService,
    private iFrameService: IFrameService,
    private plSearchPageService: PlSearchPageService,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.plSearchResultForm = this.presenter.plSearch
    this.plSearchForm = this.presenter.plSearch
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.plSearchResultForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.plSearchForm.get('customerOrderStatus').value==null && this.plSearchForm.get('picklistNumber').value==null || this.plSearchForm.get('picklistNumber').value=='' && this.plSearchForm.get('orderFromDate').value==null && this.plSearchForm.get('orderToDate').value==null) {
    this.maxDate= this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth()-1);
    this.minDate = backDate;
    this.plSearchForm.get('orderFromDate').patchValue(backDate);
    this.plSearchForm.get('orderToDate').patchValue(new Date());
    this.searchData();
    }
    else{
      localStorage.getItem(this.key)
      this.searchData();
    }
  }
  ngAfterViewInit() {
   
  }
  clearForm() {
    this.plSearchResultForm.reset();
    // this.searchData();
    this.dataTable = null
    this.resetAllSearchValue()
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  searchData() {
    this.resetAllSearchValue()
    let picklistNumber: number
    if (this.plSearchForm.get('picklistNumber').value) {
      picklistNumber = this.plSearchForm.get('picklistNumber').value.picklistNumber;
    }

    let orderFromDate: string
    if (this.plSearchForm.get('orderFromDate').value) {
      orderFromDate = this.dateService.getDateIntoYYYYMMDD(this.plSearchForm.get('orderFromDate').value);
    }

    let orderToDate: string
    if (this.plSearchForm.get('orderToDate').value) {
      orderToDate = this.dateService.getDateIntoYYYYMMDD(this.plSearchForm.get('orderToDate').value);
    };

    let sendSearchValue = {} as PlSearch;
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

    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      sendSearchValue.page = this.page
      sendSearchValue.size = this.size
    }
    else {
      sendSearchValue.page = this.page
      sendSearchValue.size = this.size
    }

    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.plSearchForm.value))
    sendSearchValue.orderFromDate = orderFromDate,
      sendSearchValue.orderToDate = orderToDate,
      sendSearchValue.orderStatus = this.plSearchForm.get('customerOrderStatus').value,
      sendSearchValue.picklistNumber = picklistNumber

    const temp = this.plSearchForm.getRawValue();

    temp['page'] = this.page
    temp['size'] = this.size

    this.filterData = this.removeNullFromObjectAndFormatDate(temp);
    if (this.checkValidDatesInput()) {
      if (this.plSearchForm.get('customerOrderStatus').value || this.plSearchForm.get('picklistNumber').value || this.plSearchForm.get('orderFromDate').value || this.plSearchForm.get('orderToDate').value) {
        this.setSearchResultTable({
          ...sendSearchValue
        });
      }
      else if (this.plSearchForm.get('orderFromDate').value == null && this.plSearchForm.get('orderToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
    this.searchFlag = true;

  }
  checkValidDatesInput() {
    const fltEnqObj = this.plSearchForm.value

    fltEnqObj.fromDate = this.plSearchForm.getRawValue() ? this.plSearchForm.value.orderFromDate : null
    fltEnqObj.toDate = this.plSearchForm.getRawValue() ? this.plSearchForm.value.orderToDate : null

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
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);

    const searchValue = /%2F/g;
    const newValue = '/';
    this.page = event.page
    this.size = event.size
    this.plSearchForm.patchValue(event);
    if (event.picklistNumber) {
      this.plSearchForm.get('picklistNumber').patchValue({ saleOrderNumber: event.picklistNumber.replace(searchValue, newValue) });
    }
    if (event.customerName) {
      this.plSearchForm.get('customerName').patchValue({ customerName: event.customerName });
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

  private setSearchResultTable(searchValue: PlSearch) {
    this.plSearchPageService.searchPl(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }

  pageChangeOfSearchTable(event: any) {
    // console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchData();
  }

  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData['record']['id'])
    let searchfilter = btoa(JSON.stringify(this.filterData))
    console.log("recordData ", recordData);
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { editId: id} });
    }
    if (recordData.btnAction.toLowerCase() === 'picklistnumber') {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { viewId: id} })
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }

        if (searchObject[element] && (element === 'orderFromDate' || element === 'orderToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && element === 'picklistNumber') {
          searchObject[element] = searchObject['picklistNumber']['picklistNumber']
        }
      });
      return searchObject;
    }
  }

  resetAllSearchValue() {
    this.EditNgModel = "";
    this.pickListNumberNgModel = "";
    this.pickListDateNgModel = "";
    this.salesOrderNumberNgModel = "";
    this.salesOrderDateNgModel = "";
    this.customerNameNgModel = "";
    this.mobileNumberNgModel = "";
    this.cityNgModel = "";
    this.pickListStatusNgModel = ""
  }

}
