import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MrcSearchPage } from './mrc-search-page.presenter';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch } from 'ngsw-search-table';
import { MrcSearchPageStore } from './mrc-search-page.strore';
import { SearchMrc } from '../../domain/machine-receipt-checking.domain';
import { NgswSearchTableService } from 'ngsw-search-table';
import { MrcSearchPageService } from './mrc-search-page.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, UrlSegment, IFrameMessageSource } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-mrc-search-page',
  templateUrl: './mrc-search-page.component.html',
  styleUrls: ['./mrc-search-page.component.scss'],
  providers: [MrcSearchPage, MrcSearchPageStore, MrcSearchPageService]
})
export class MrcSearchPageComponent implements OnInit {
  mrcSearchResultForm: FormGroup
  mrcSearchForm: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  //public filterData: object;
  clearSearchRow = new BehaviorSubject<string>("");
  MRC_NumberNgModel: any = "";
  KAI_Invoice_NumberNgModel: any = "";
  Total_Item_In_InvoiceNgModel: any = "";
  MRC_PendingNgModel: any = "";
  Chassis_NumberNgModel: any = "";
  MRC_DateNgModel: any = "";
  LR_DateNgModel: any = "";
  LR_NumberNgModel: any = "";
  Engine_NumberNgModel: any = "";
  invoiceDateNgModel: any = "";
  searchFlag: boolean = true;
  searchFilterValues: any;
  key = 'mrc';
  minDate: Date;
  newdate = new Date()
  pageLoadCount:number=0
  maxDate: Date
  constructor(
    private presenter: MrcSearchPage,
    private mrcSearchPageService: MrcSearchPageService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private iFrameService: IFrameService,
    private tostr: ToastrService,
  ) { }
  ngAfterViewInit() {

    // call of search table web service.
    // this.searchData()
  }
  ngOnInit() {
    
    // window.onbeforeunload = () => {
    //   localStorage.removeItem(this.key);
    // }

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.mrcSearchResultForm = this.presenter.mrcSearch;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.mrcSearchResultForm != null) {
      this.mrcSearchResultForm.patchValue(this.searchFilterValues)
      localStorage.getItem(this.key)
      // this.searchData()
    }
    else{
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.mrcSearchResultForm.get('mrcDateFrom').patchValue(backDate);
      this.mrcSearchResultForm.get('mrcDateTo').patchValue(new Date());
      // this.searchData()
    }
    localStorage.removeItem(this.key);
    // this.mrcSearchForm = this.mrcSearchResultForm.controls.value as FormGroup
    // console.log("this.mrcSearchForm ", this.mrcSearchForm);
    // this.actionButtons.push(this.ngswSearchTableService.addActionButton('edit', 'edit', 'edit'));
    
  }
  searchData() {

    this.MRC_NumberNgModel = "";
    this.KAI_Invoice_NumberNgModel = "";
    this.Total_Item_In_InvoiceNgModel = "";
    this.MRC_PendingNgModel = "";
    this.Chassis_NumberNgModel = "";
    this.MRC_DateNgModel = "";
    this.LR_DateNgModel = "";
    this.LR_NumberNgModel = "";
    this.Engine_NumberNgModel = "";
    this.invoiceDateNgModel = "";
    let mrcDateFrom: string;
    if (this.mrcSearchResultForm.get('mrcDateFrom').value) {
      mrcDateFrom = this.dateService.getDateIntoYYYYMMDD(this.mrcSearchResultForm.get('mrcDateFrom').value)
    }
    let mrcDateTo: string;
    if (this.mrcSearchResultForm.get('mrcDateTo').value) {
      mrcDateTo = this.dateService.getDateIntoYYYYMMDD(this.mrcSearchResultForm.get('mrcDateTo').value)
    }
    let invoiceDateFrom: string;
    if (this.mrcSearchResultForm.get('invoiceDateFrom').value) {
      invoiceDateFrom = this.dateService.getDateIntoYYYYMMDD(this.mrcSearchResultForm.get('invoiceDateFrom').value)
    }
    let invoiceDateTo: string;
    if (this.mrcSearchResultForm.get('invoiceDateTo').value) {
      invoiceDateTo = this.dateService.getDateIntoYYYYMMDD(this.mrcSearchResultForm.get('invoiceDateTo').value)
    }

    if(invoiceDateFrom && invoiceDateTo){

    }else{
      if(invoiceDateFrom || invoiceDateTo){
        this.tostr.error('Please Select Invoice Date Range.');
        return false;
      }
    }
    const sendSearchValue = {} as SearchMrc;
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
    
    localStorage.setItem(this.key, JSON.stringify(this.mrcSearchResultForm.value))

      sendSearchValue.mrcNo = this.mrcSearchResultForm.get('mrcNo').value ? this.mrcSearchResultForm.get('mrcNo').value : null,
      sendSearchValue.invoiceNo = this.mrcSearchResultForm.get('kaiInvoiceNo').value ? this.mrcSearchResultForm.get('kaiInvoiceNo').value : null,
      sendSearchValue.mrcFromDate = mrcDateFrom,
      sendSearchValue.mrcToDate = mrcDateTo,
      // sendSearchValue.invoiceFromDate = mrcDateFrom,
      // sendSearchValue.invoiceToDate = mrcDateTo,
      sendSearchValue.page = this.page,
      sendSearchValue.size = this.size;
      sendSearchValue.orgId = this.mrcSearchResultForm.value.orgHierarchyId ? this.mrcSearchResultForm.value.orgHierarchyId :null;
 
    // const temp = this.mrcSearchResultForm.getRawValue();
    // temp['page'] = this.page
    // temp['size'] = this.size
    // temp['id'] = sendSearchValue.mrcId
    //this.filterData = this.removeNullFromObjectAndFormatDate(temp);
   
    if (this.checkValidDatesInput()) {
      if (this.mrcSearchResultForm.get('mrcNo').value || this.mrcSearchResultForm.get('mrcDateFrom').value || this.mrcSearchResultForm.get('mrcDateTo').value
      || this.mrcSearchResultForm.get('invoiceDateFrom').value || this.mrcSearchResultForm.get('invoiceDateTo').value ||
      this.mrcSearchResultForm.get('kaiInvoiceNo').value) {
        localStorage.setItem(this.key, JSON.stringify(this.mrcSearchResultForm.value))
        this.setSearchResultTable({
          ...sendSearchValue
        });
      }
      else if (this.mrcSearchResultForm.get('mrcDateFrom').value == null && this.mrcSearchResultForm.get('mrcDateTo').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }

  }
  // initialQueryParams(event) {
  //   if (event) {
  //     this.filterData = event as object;
  //   }
  // }
  // onUrlChange(urlAndParamsObject: UrlSegment) {
  //   if (urlAndParamsObject) {
  //     this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, urlAndParamsObject as UrlSegment);
  //   }
  // }
  checkValidDatesInput() {
    const fltEnqObj = this.mrcSearchResultForm.value

    fltEnqObj.fromDate = this.mrcSearchResultForm.getRawValue() ? this.mrcSearchResultForm.value.mrcDateFrom : null
    fltEnqObj.toDate = this.mrcSearchResultForm.getRawValue() ? this.mrcSearchResultForm.value.mrcDateTo : null

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

  // initialQueryParams(event) {
  //   console.log('initialQueryParams event: ', event);
  //   const searchValue = /%2F/g;
  //   const newValue = '/';
  //   this.page = event.page
  //   this.size = event.size
  //   this.mrcSearchResultForm.patchValue(event);
  //   if (event.mrcNo) {
  //     this.mrcSearchResultForm.get('mrcNo').patchValue({ code: event.mrcNo, id: event.id });
  //   }
  //   this.searchData();
  // }

  // onUrlChange(event: any) {
  //   console.log('onUrlChange event: ', event);
  //   if (!event) {
  //     return;
  //   }
  //   const { queryParam = null, url = '' } = { ...event };
  //   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  // }

  setSearchResultTable(searchValue: SearchMrc) {
    // console.log("searchValue ", searchValue);
    this.mrcSearchPageService.searchMrc(searchValue).subscribe(searchRes => {
      // console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  clearForm() {
    
    this.MRC_NumberNgModel = "";
    this.KAI_Invoice_NumberNgModel = "";
    this.Total_Item_In_InvoiceNgModel = "";
    this.MRC_PendingNgModel = "";
    this.Chassis_NumberNgModel = "";
    this.MRC_DateNgModel = "";
    this.LR_DateNgModel = "";
    this.LR_NumberNgModel = "";
    this.Engine_NumberNgModel = "";
    this.invoiceDateNgModel = "";

    this.mrcSearchResultForm.reset();
    // this.searchData();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  pageChangeOfSearchTable(event: any) {
    // console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchData();
    }
    this.pageLoadCount = 1;
    
  }
  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData.record.id)
    //let filterdata = btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['../edit', id], { relativeTo: this.activatedRoute });
    }
    if (recordData.btnAction.toLowerCase() === 'mrc_number') {


      this.router.navigate(['view', id], { relativeTo: this.activatedRoute })
    }
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'mrcDateFrom' || element === 'mrcDateTo')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && element === 'mrcNo') {
          searchObject[element] = searchObject['mrcNo']['code']
        }
      });
      return searchObject;
    }
  }

  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }

}
