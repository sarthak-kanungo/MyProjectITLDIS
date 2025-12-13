import { Component, OnInit, AfterViewInit } from '@angular/core';
import { WpdcSearchPageStore } from './wpdc-search-page.store';
import { WpdcSearchPagePresenter } from './wpdc-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { WpdcSearchPageWebService } from './wpdc-search-page-web.service';
import {
  DataTable,
  ColumnSearch,
  NgswSearchTableService,
  InfoForGetPagination
} from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { WpdcSearch } from '../../domain/warranty-parts-delivery-challan.domain';
import { ToastrService } from 'ngx-toastr';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
// import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-wpdc-search-page',
  templateUrl: './wpdc-search-page.component.html',
  styleUrls: ['./wpdc-search-page.component.scss'],
  providers: [WpdcSearchPageStore, WpdcSearchPagePresenter, WpdcSearchPageWebService]
})
export class WpdcSearchPageComponent implements OnInit, AfterViewInit {
  page = 0;
  size = 10;
  private searchDetail = {} as WpdcSearch;
  searchForm: FormGroup;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  filterData: WpdcSearch;
  searchFilter: any;
  searchFlag: boolean = true
  clearSearchRow = new BehaviorSubject<string>("");
  fromDate: Date;
  newdate= new Date()
  key='wpdc';
  pageLoadCount:number=0
  searchFilterValues: any;
  constructor(
    private wpdcSearchPagePresenter: WpdcSearchPagePresenter,
    private wpdcSearchPageWebService: WpdcSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private iFrameService: IFrameService,
    // private dataservice: DateService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchForm = this.wpdcSearchPagePresenter.wpdcSearchForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    if (this.searchForm.get('wcrNo').value==null && this.searchForm.get('dcNo').value==null && this.searchForm.get('dcFromDate').value==null && this.searchForm.get('dcToDate').value==null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth()-1);
      this.fromDate = backDate;
      this.searchForm.get('dcFromDate').patchValue(backDate);
      this.searchForm.get('dcToDate').patchValue(new Date());
      this.search();
    }else{
      localStorage.getItem(this.key)
      this.search();
    }
  }

  ngAfterViewInit() {
    this.searchForm.valueChanges.subscribe(val => {
      this.searchDetail = val;
    })
    // this.deliveryChallanSearch(this.searchDetail);
  }

  private deliveryChallanSearch(searchDetail: WpdcSearch) {
    this.wpdcSearchPageWebService.deliveryChallanSearch(searchDetail).subscribe(res => {
      console.log('res_Search', res);
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      this.tostr.error('Error while fetching data', 'Error');
    });
  }

  tableAction(event: object) {
    console.log('event_wpdc', event)
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'dcno') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute,
        queryParams: { dcNo: btoa(event['record']['dcNo']) }
      });
    }
    else if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'action') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute,
        queryParams: { dcNo: btoa(event['record']['dcNo']) }
      });
    }
  }
  pageSizeChange(event: InfoForGetPagination) {
    this.page = event['page'];
    this.size = event['size'];

    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.search();
    }
    this.pageLoadCount = 1;
    

    //this.deliveryChallanSearch(this.searchDetail);
  }
  etSearchDateValueChange(searchDate: string, columnKey: string) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, columnKey);
  }

  search() {
    if (this.dataTable != undefined || this.dataTable != null) {
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
    else {
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;
    const filterObj = this.searchForm.value as WpdcSearch
    filterObj.page = this.page
    filterObj.size = this.size
    filterObj.dcFromDate = filterObj.dcFromDate ? ObjectUtil.convertDate(filterObj.dcFromDate) : null
    filterObj.dcToDate = filterObj.dcToDate ? ObjectUtil.convertDate(filterObj.dcToDate) : null
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))

    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchFlag == true && this.searchForm.get('wcrNo').value || this.searchForm.get('dcNo').value || this.searchForm.get('dcFromDate').value || this.searchForm.get('dcToDate').value) {

        this.deliveryChallanSearch(filterObj);
      }
      else if (this.searchForm.get('dcFromDate').value == null && this.searchForm.get('dcToDate').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
  }
//commented by Kanhaiya
  // searchDetails() {
  //   if (this.searchForm.get('wcrNo').value != null) {
  //     this.searchDetail.wcrNo = this.searchForm.get('wcrNo').value.code;
  //   }
  //   if (this.searchForm.get('dcNo').value != null) {
  //     this.searchDetail.dcNo = this.searchForm.get('dcNo').value.code;
  //   }
  //   this.searchDetail.dcToDate = ObjectUtil.convertDate(this.searchForm.get('dcToDate').value)
  //   this.searchDetail.dcFromDate = ObjectUtil.convertDate(this.searchForm.get('dcFromDate').value);
  //   this.searchDetail.wcrToDate = ObjectUtil.convertDate(this.searchForm.get('wcrToDate').value)
  //   this.searchDetail.wcrFromDate = ObjectUtil.convertDate(this.searchForm.get('wcrFromDate').value);

  //   if (this.dataTable) {
  //     if (this.searchFlag == true) {
  //       this.page = 0;
  //       this.size = 10;
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //     else {
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //   }
  //   this.searchDetail.page = this.page;
  //   this.searchDetail.size = this.size;

  //   this.searchFlag = true;

  // }

  checkValidDatesInput() {
    const fltEnqObj = this.searchForm.value

    fltEnqObj.fromDate = this.searchForm.getRawValue() ? this.searchForm.value.dcFromDate : null
    fltEnqObj.toDate = this.searchForm.getRawValue() ? this.searchForm.value.dcToDate : null

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
  clear() {
    this.searchForm.reset();
    this.dataTable = null
    // this.searchDetail = this.searchForm.getRawValue();
    // this.searchDetail.page =  0;
    // this.searchDetail.size = this.size; 
    // this.searchDetail =  ObjectUtil.removeNulls(this.searchDetail);
    // this.deliveryChallanSearch(this.searchDetail);
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  /**
  * ----------Following is state management code------------
  */

  initialQueryParams(event: WpdcSearch) {
    console.log('initialQueryParams event: ', event);
    const searchValue = /%2F/g;
    const newValue = '/';
    this.searchForm.patchValue(event);

    // if (event.dcNo) {
    //   this.searchForm.get('dcNo').patchValue({ code: event.dcNo.replace(searchValue, newValue) });
    //   event.dcNo = event.dcNo.replace(searchValue, newValue);
    // }
    // if (event.wcrNo) {
    //   this.searchForm.get('wcrNo').patchValue({ code: event.wcrNo.replace(searchValue, newValue) });
    //   event.wcrNo = event.wcrNo.replace(searchValue, newValue);
    // }

    this.page = event.page;
    this.size = event.size;
    this.searchForm.get('page').patchValue(event.page);
    this.searchForm.get('size').patchValue(event.size);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.WARRANTY, { url } as UrlSegment);
  }
}
