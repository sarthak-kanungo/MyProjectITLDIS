import { Component, OnInit, AfterViewInit, ChangeDetectorRef,ChangeDetectionStrategy } from '@angular/core';
import { DateAdapter, MatDatepickerInput, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DataTable, NgswSearchTableService, ColumnSearch, TableHeading } from 'ngsw-search-table';
import { SearchAllotmentDeAllotmentService } from './search-allotment-de-allotment.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { DateService } from '../../../../../root-service/date.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ObjectUtil } from '../../../../../utils/object-util';
import { IFrameMessageSource, UrlSegment, IFrameService } from '../../../../../root-service/iFrame.service';
import { HttpClient } from '@angular/common/http';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-search-allotment-de-allotment',
  templateUrl: './search-allotment-de-allotment.component.html',
  styleUrls: ['./search-allotment-de-allotment.component.scss'],
  providers: [EnquiryCommonService,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchAllotmentDeAllotmentService
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SearchAllotmentDeAllotmentComponent implements OnInit, AfterViewInit {
  productTypeList = [] as SelectList[];
  seriesTypeList = [] as SelectList[];
  modelTypeList = [] as Array<SelectList>
  dataTable: DataTable;
  actionButtons = [];
  searchform = false
  selected = 10;
  clearSearchRow = new BehaviorSubject<string>("");
  options = false
  totalTableElements
  submodelList = [] as Array<SelectList>;
  searchAllotment: FormGroup
  advanceSearch: FormGroup
  enquiryCodeList = [] as SelectList[];
  variantList = [] as SelectList[];
  itemNoList: string[];
  loginUser: import("LoginDto").StorageLoginUser;
  allotmentNumberList: SelectList[];
  chassisNumberList: SelectList[];
  totalSearchRecordCount: any;
  page: any = 0;
  size: any = 10;
  searchValue: any;
  engineNumberList: SelectList[];
  clickOnTableFields: Array<TableHeading> = [{ title: 'allotmentNumber', isClickable: true }, { title: 'action', isClickable: true }];
  serverDate: string;
  searchFilter: any;
  serverCurrentDate: Date;
  searchFlag: boolean = true;

  allotmentNumberNgModel: any = "";
  fromdateNgModel: any = "";
  statusNgModel: any = "";
  enquiryNumberNgModel: any = "";
  dealerTransferNoNgModel: any = "";
  customerNameNgModel: any = "";
  customerMobileNumberNgModel: any = "";
  itemNumberNgModel: any = "";
  searchFilterValues: any;
  searchFilterValuesAdvance: any;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key = 'sadea'
  constructor(
    private fb: FormBuilder,
    private searchAllotmentDeAllotmentService: SearchAllotmentDeAllotmentService,
    private localStorage: LocalStorageService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private router: Router,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService,
    private toastr: ToastrService,
    private _changeDetectorRef: ChangeDetectorRef,
  ) { 
  
  }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    
    this.createSearchAllotment();
    this.createadvancesearch();
    this.searchAllotment.get('allotmentNumber').value 
    this.loginUser = this.localStorage.getLoginUser();
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchAllotment.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem(this.key);
    if ((this.searchAllotment.get('allotmentNumber').value == null || this.searchAllotment.get('allotmentNumber').value == '') && (this.searchAllotment.get('enquiryNumber').value == null || this.searchAllotment.get('enquiryNumber').value == '') && (this.searchAllotment.get('fromDate').value == null || this.searchAllotment.get('fromDate').value == '') && (this.searchAllotment.get('toDate').value == null || this.searchAllotment.get('toDate').value == '')) {
     
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchAllotment.patchValue({fromDate:backDate, toDate:new Date()});
      // this.searchAllotment.get('toDate').patchValue(new Date());
      this.searchMachineAllotment();
    } else {
      localStorage.getItem(this.key)
      this.searchMachineAllotment();
    }
    this.searchFilterValuesAdvance = localStorage.getItem('searchFilterAdvance')
    this.searchFilterValuesAdvance = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValuesAdvance)))
  
    if (this.searchFilterValuesAdvance != null || this.searchFilterValuesAdvance != undefined) {
      this.advanceSearch.patchValue(this.searchFilterValues)
    }

    localStorage.removeItem('searchFilterAdvance');
    this.getAllProduct();
    this.getDateFromServer();
    // this.getAllModel();
    // this.getAllSubModel();
    // this.getAllVariant();
    // this.searchMachineAllotment();
    
   
  }
  
  ngAfterViewInit() {
    this._changeDetectorRef.detectChanges();
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate) {
        this.maxDate = this.newdate;
      }
      else {
        this.maxDate = maxDate;
        if (this.searchAllotment.get('toDate').value > this.maxDate)
          this.searchAllotment.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  private getDateFromServer() {
    this.searchAllotmentDeAllotmentService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.serverDate = dateRes.result;
        this.serverCurrentDate = new Date(dateRes.result);
      }
    });
  }
  createSearchAllotment() {
    this.searchAllotment = this.fb.group({
      allotmentNumber: ['', Validators.compose([])],
      enquiryNumber: ['', Validators.compose([])],
      fromDate: ['', Validators.compose([])],
      toDate: ['', Validators.compose([])],
    });
    this.searchAllotment.get('enquiryNumber').valueChanges.subscribe(value => {
      if (!!value || `${value}` === '0') {
        this.autoEnquiryCode(value)
      }
    })
    this.searchAllotment.get('allotmentNumber').valueChanges.subscribe(value => {
      if (!!value || `${value}` === '0') {
        this.searchByAllotmentNumber(value);
      }
    })
  }

  createadvancesearch() {
    this.advanceSearch = this.fb.group({
      product: ['', Validators.compose([])],
      series: ['', Validators.compose([])],
      model: ['', Validators.compose([])],
      subModel: ['', Validators.compose([])],
      variant: ['', Validators.compose([])],
      itemNo: ['', Validators.compose([])],
      chassisNo: ['', Validators.compose([])],
      engineNo: ['', Validators.compose([])],
    })
    this.advanceSearch.get('itemNo').valueChanges.subscribe(value => {
      if (!!value || `${value}` === '0') {
        this.searchItemNo(value);
      }
    });
    this.advanceSearch.get('chassisNo').valueChanges.subscribe(value => {
      if (!!value || `${value}` === '0') {
        this.searchChassisNumber(value);
      }
    });
    this.advanceSearch.get('engineNo').valueChanges.subscribe(value => {
      if (!!value || `${value}` === '0') {
        this.searchEngineNumber(value);
      }
    })

  }
  autoEnquiryCode(value) {
    /*this.searchAllotmentDeAllotmentService.searchEnquiryCode(value).subscribe(response => {
      console.log('response', response);
      this.enquiryCodeList = response.result
    })*/

    this.enquiryCommonService.searchEnquiryCode(value, 'ALLOTMENT_SEARCH').subscribe(response => {
      this.enquiryCodeList = response['result']
    })
  }
  getAllProduct() {
    this.searchAllotmentDeAllotmentService.dropdownGetAllProductType().subscribe(response => {
      this.productTypeList = response.result
    })
  }
  getAllSeries(event) {
    const product = this.advanceSearch.get('product').value;
    this.searchAllotmentDeAllotmentService.getSeriesByProduct(product).subscribe(response => {
      this.seriesTypeList = response.result
    })
  }
  getAllModel(event) {
    const series = this.advanceSearch.get('series').value;
    this.searchAllotmentDeAllotmentService.getModelBySeries(series).subscribe(response => {
      this.modelTypeList = response.result;
    })
  }
  getAllSubModel(event) {
    const model = this.advanceSearch.get('model').value;
    this.searchAllotmentDeAllotmentService.getSubModelByModel(model).subscribe(response => {
      this.submodelList = response.result;
    })
  }
  getAllVariant(event) {
    const subModel = this.advanceSearch.get('subModel').value;
    this.searchAllotmentDeAllotmentService.getAllVariant(subModel).subscribe(response => {
      this.variantList = response.result;
    })
  }
  searchItemNo(itemNo: string) {
    this.searchAllotmentDeAllotmentService.searchItemNo(itemNo).subscribe(itemNoRes => {
      this.itemNoList = itemNoRes.result;
    })
  }
  private searchByAllotmentNumber(allotmentNumber: string) {
    this.searchAllotmentDeAllotmentService.searchByAllotmentNumber(allotmentNumber, this.loginUser.id.toString()).subscribe(itemNoRes => {
      this.allotmentNumberList = itemNoRes.result;
    })
  }
  private searchChassisNumber(chassisNumber) {
    this.searchAllotmentDeAllotmentService.searchChassisNumbers(chassisNumber).subscribe(chassisNumberRes => {
      this.chassisNumberList = chassisNumberRes.result;
    })
  }
  private searchEngineNumber(engineNumber) {
    this.searchAllotmentDeAllotmentService.searchByEngineNumber(engineNumber, this.loginUser.id.toString()).subscribe(engineNumberRes => {
      this.engineNumberList = engineNumberRes.result;
    })
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    if (recordData.btnAction === 'action') {
      this.router.navigate(['deallot/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'allotmentNumber') {
      this.router.navigate(['view/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
  }
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchMachineAllotment();
  }
  searchMachineAllotment() {
    this.allotmentNumberNgModel = "";
    this.fromdateNgModel = "";
    this.statusNgModel = "";
    this.enquiryNumberNgModel = "";
    this.customerNameNgModel = "";
    this.customerMobileNumberNgModel = "";
    this.itemNumberNgModel = "";
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
    localStorage.setItem(this.key, JSON.stringify(this.searchAllotment.value))
    let key1 = 'searchFilterAdvance';
    localStorage.setItem(key1, JSON.stringify(this.advanceSearch.value))
    const searchField = {
      ...this.searchAllotment.getRawValue(),
      ...this.advanceSearch.getRawValue(),
      ...{
        page: this.page,
        size: this.size,
        // userId: this.loginUser.id,
       
      }
    };
    if (searchField['fromDate'] && searchField['toDate']) {
      searchField['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['fromDate']);
      searchField['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['toDate'])
    }

    this.searchFilter = ObjectUtil.removeNulls(searchField);
    if ((searchField['fromDate'] === null || searchField['fromDate'] === "" || searchField['fromDate'] === undefined)) {
      if ((searchField['toDate'] === null || searchField['toDate'] === "" || searchField['toDate'] === undefined)) {
        if (this.searchAllotment.get('allotmentNumber').value || this.searchAllotment.get('enquiryNumber').value || this.searchAllotment.get('fromDate').value || this.searchAllotment.get('toDate').value) {
          this.searchAllotmentDeAllotmentService.searchMachineAllotment(searchField).subscribe(searchRes => {
            this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
            this.totalSearchRecordCount = searchRes['count'];
            this._changeDetectorRef.markForCheck();
          })
        }
        else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    } else if ((searchField['fromDate'] !== null || searchField['fromDate'] !== "" || searchField['fromDate'] !== undefined)) {
      if ((searchField['toDate'] === null || searchField['toDate'] === "" || searchField['toDate'] === undefined)) {
        this.toastr.error("Please Select Date Range");
      } else {
        this.searchAllotmentDeAllotmentService.searchMachineAllotment(searchField).subscribe(searchRes => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
          this.totalSearchRecordCount = searchRes['count'];
        })

      }
    }

  }

  etSearchDateValueChange(searchDate, ColumnKey) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.searchAllotment
    this.searchAllotment.patchValue(event);
    this.advanceSearch.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchAllotment.patchValue({
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
    });
    if (event.fromDate) {
      this.searchAllotment.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.searchAllotment.get('toDate').patchValue(new Date(event.toDate))
    }
    this.searchMachineAllotment();
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
  /* added by vinay*/
  clearForm() {
    this.searchAllotment.reset()
    // this.searchMachineAllotment()
    this.dataTable = null
    // this.createSearchAllotment()
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  /* added by vinay*/
}
