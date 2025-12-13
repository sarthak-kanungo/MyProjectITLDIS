import { Component, OnInit } from '@angular/core';
import { BtBtSearchPagePresenter } from './btbt-search.presenter';
import { BtBtSearchStore } from './btbt-search.store';
import { FormGroup } from '@angular/forms';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { DateService } from '../../../../../root-service/date.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BtbtSearchPageWebService } from './btbt-search-page-web.service';
import { SearchBtBt } from '../../domain/bin-to-bin-transfer';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-btbt-search-page',
  templateUrl: './btbt-search-page.component.html',
  styleUrls: ['./btbt-search-page.component.css'],
  providers: [BtBtSearchPagePresenter, BtBtSearchStore, BtbtSearchPageWebService]
})
export class BtbtSearchPageComponent implements OnInit {

  btBtSearchResultForm: FormGroup
  btBtSearch: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  public filterData: object;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");

  transferNumberNgModel: any
  transferDateNgModel: any
  itemNoNgModel: any
  itemDescriptionNgModel: any
  fromStoreNgModel: any
  fromLocationNgModel: any
  mrpNgModel: any
  transferQtyNgModel: any
  toStoreNgModel: any
  toLocationNgModel: any
  today = new Date();
  minDate: Date;
  maxDate: Date
  key = 'btbt';
  constructor(
    private presenter: BtBtSearchPagePresenter,
    public btBtSearchPageWebService: BtbtSearchPageWebService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
  ) { }

  ngAfterViewInit() {

  }
  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.btBtSearchResultForm = this.presenter.btBtSearchForm
    this.btBtSearch = this.presenter.btBtSearch;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.btBtSearchResultForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.btBtSearch.get('binTransferNo').value == null && this.btBtSearch.get('binTransferFromDate').value == null && this.btBtSearch.get('binTransferToDate').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.btBtSearch.get('binTransferFromDate').patchValue(backDate);
      this.btBtSearch.get('binTransferToDate').patchValue(new Date());
      this.searchData();
    }
    else {
      localStorage.removeItem(this.key)
      this.searchData();
    }
  }
  clearForm() {
    // this.searchData();
    this.btBtSearchResultForm.reset();
    this.clearSearchFilter()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  searchData() {
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
    let orderFromDate: string
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.btBtSearch.value))
    localStorage.setItem(this.key, JSON.stringify(this.btBtSearchResultForm.value))

    if (this.btBtSearch.get('binTransferFromDate').value) {
      orderFromDate = this.dateService.getDateIntoYYYYMMDD(this.btBtSearch.get('binTransferFromDate').value);
    }
    let orderToDate: string
    if (this.btBtSearch.get('binTransferToDate').value) {
      orderToDate = this.dateService.getDateIntoYYYYMMDD(this.btBtSearch.get('binTransferToDate').value);
    };
    let sendSearchValue = {} as SearchBtBt;

    sendSearchValue.fromDate = orderFromDate,
      sendSearchValue.toDate = orderToDate,
      sendSearchValue.transferNumber = (this.btBtSearch.get('binTransferNo').value != null && typeof this.btBtSearch.get('binTransferNo').value === 'object' ? this.btBtSearch.get('binTransferNo').value.binTransferNo : this.btBtSearch.get('binTransferNo').value),
      sendSearchValue.page = this.page,
      sendSearchValue.size = this.size

    //sendSearchValue = this.removeNullFromObjectAndFormatDate(sendSearchValue);
    if (this.checkValidDatesInput()) {
      if (this.btBtSearch.get('binTransferNo').value || this.btBtSearch.get('binTransferFromDate').value || this.btBtSearch.get('binTransferToDate').value) {
        this.setSearchResultTable({
          ...sendSearchValue
        });
      }
      else if (this.btBtSearch.get('binTransferFromDate').value == null && this.btBtSearch.get('binTransferToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.btBtSearch.value

    fltEnqObj.fromDate = this.btBtSearch.getRawValue() ? this.btBtSearch.value.binTransferFromDate : null
    fltEnqObj.toDate = this.btBtSearch.getRawValue() ? this.btBtSearch.value.binTransferToDate : null

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
    this.btBtSearchResultForm.patchValue(event);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  private setSearchResultTable(searchValue: SearchBtBt) {
    this.btBtSearchPageWebService.searchBtBt(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  pageChangeOfSearchTable(event: any) {
    // console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false
    this.searchData();
  }
  actionOnTableRecord(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'print') {

      this.viewPdf('true', recordData.record.transferNumber);
    }
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
      });
      console.log("searchObject ", searchObject);
      return searchObject;
    }
  }

  downloadExcel(event) {
    const formValues = this.btBtSearch.getRawValue();
    let orderFromDate: string

    if (this.btBtSearch.get('binTransferFromDate').value) {
      orderFromDate = this.dateService.getDateIntoYYYYMMDD(this.btBtSearch.get('binTransferFromDate').value);
    }
    let orderToDate: string
    if (this.btBtSearch.get('binTransferToDate').value) {
      orderToDate = this.dateService.getDateIntoYYYYMMDD(this.btBtSearch.get('binTransferToDate').value);
    };
    let sendSearchValue = {} as SearchBtBt;
    sendSearchValue.page = 0;
    sendSearchValue.fromDate = orderFromDate;
    sendSearchValue.toDate = orderToDate;
    sendSearchValue.transferNumber = (this.btBtSearch.get('binTransferNo').value != null && typeof this.btBtSearch.get('binTransferNo').value === 'object' ? this.btBtSearch.get('binTransferNo').value.binTransferNo : this.btBtSearch.get('binTransferNo').value);


    this.downloadExcelReport(sendSearchValue);
  }


  private downloadExcelReport(searchObject) {
    this.btBtSearchPageWebService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('Content-Disposition');
        console.log('downloadExcelReport-->', headerContentDispostion)
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  viewPdf(printStatus: string, transferNo: string) {
    this.btBtSearchPageWebService.printPO(printStatus, transferNo).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  clearSearchFilter() {
    this.transferNumberNgModel = ""
    this.transferDateNgModel = ""
    this.itemNoNgModel = ""
    this.itemDescriptionNgModel = ""
    this.fromStoreNgModel = ""
    this.fromLocationNgModel = ""
    this.mrpNgModel = ""
    this.transferQtyNgModel = ""
    this.toStoreNgModel = ""
    this.toLocationNgModel = ""
  }
}
