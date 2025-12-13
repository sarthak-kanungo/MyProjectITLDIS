import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatSelectChange, MatDatepickerInput } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup } from '@angular/forms';
import { SearchQuotationService } from './search-quotation.service';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { BaseDto } from 'BaseDto';
import { SourceDomain, ProductsDomain, SeariesDomain, ModelDomain, SubModelDomain, VariantDomain, ItemNoDomain2, QuotationSearchFilterDomain } from 'SearchQuotationsModule';
import { ColumnSearch, DataTable, TableHeading } from 'ngsw-search-table';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { DateService } from 'src/app/root-service/date.service';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-search-quotation',
  templateUrl: './search-quotation.component.html',
  styleUrls: ['./search-quotation.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchQuotationService
  ]
})
export class SearchQuotationComponent implements OnInit {

  recordPerPageList: Array<number> = [5, 10, 25, 50];
  searchform: boolean = false;
  @Input()
  totalTableRecords: number;
  actionButtons = [];
  selected = 10;
  clearSearchRow = new BehaviorSubject<string>("");
  clickOnTableFields: Array<TableHeading> = [{ title: 'quotationCode', isClickable: true }];

  @Input() enquiryStatusList: Array<Object>;
  @Input() salesPersonList: Array<Object>;
  @Input() searchSourceTypeList: BaseDto<Array<SourceDomain>>
  @Input() searchProductTypeList: BaseDto<Array<ProductsDomain>>
  @Input() searchSeariesTypeList: BaseDto<Array<SeariesDomain>>
  @Input() searchModelTypeList: BaseDto<Array<ModelDomain>>
  @Input() searchSubModelTypeList: BaseDto<Array<SubModelDomain>>
  @Input() searchVariantTypeList: BaseDto<Array<VariantDomain>>
  @Output() autoItemNO = new EventEmitter<string>()
  @Input() getItemNo: BaseDto<Array<ItemNoDomain2>>

  searchQuotation: FormGroup
  @Input() dataTable: DataTable
  @Output() onSearch: EventEmitter<object> = new EventEmitter<object>()

  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  modelId: number
  minDate: Date;
  newdate = new Date()
  maxDate: Date

  @Output() btnActionValue = new EventEmitter<string>();
  searchValue: ColumnSearch;
  page: number = 0;
  size: number = 10;
  @Output() quotationCodeChange = new EventEmitter<string>();
  @Output() productSelectionChange = new EventEmitter<string>();
  @Output() seriesSelectionChange = new EventEmitter<string>();
  @Output() modelSelectionChange = new EventEmitter<string>();
  @Output() subModelSelectionChange = new EventEmitter<string>();
  @Input() quotationCodeList = [];
  @Input() serverDate: string;
  searchFilter: any = {};
  searchFlag: boolean = true;

  quotationCodeNgModel: any = "";
  quotationDateNgModel: any = "";
  enquiryNumberNgModel: any = "";
  salesPersonNgModel: any = "";
  enquiryStatusNgModel: any = "";
  sourceNgModel: any = "";
  purposeOfPurchaseNgModel: any = "";
  tentativePurchaseDateNgModel: any = "";
  itemNumberNgModel: any = "";
  itemDescriptionNgModel: any = "";
  modelNgModel: any = "";
  subModelNgModel: any = "";
  variantNgModel: any = "";
  seriesNgModel: any = "";
  productNgModel: any = "";
  prospectCodeNgModel: any = "";
  prospectTypeNgModel: any = "";
  companyNameNgModel: any = "";
  firstNameNgModel: any = "";
  middleNameNgModel: any = "";
  lastNameNgModel: any = "";
  fatherNameNgModel: any = "";
  whatsAppNumberNgModel: any = "";
  alternateMobileNumberNgModel: any = "";
  telephoneNumberNgModel: any = "";
  emailIdNgModel: any = "";
  address1NgModel: any = "";
  address2NgModel: any = "";
  address3NgModel: any = "";
  pinCodeNgModel: any = "";
  postOfficeNgModel: any = "";
  cityNgModel: any = "";
  stateNgModel: any = "";
  countryNgModel: any = "";
  languageNgModel: any = "";
  dobNgModel: any = "";
  anniversaryDateNgModel: any = "";
  gstNumberNgModel: any = "";
  panNumberNgModel5: any = "";
  searchFilterValues: any;
  key = 'quptation';



  constructor(
    private searchQuotationService: SearchQuotationService,
    private iFrameService: IFrameService,
    private tableDataService: TableDataService,
    private toastr: ToastrService,
    private dateService: DateService) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    const fltObj = {} as QuotationSearchFilterDomain
    fltObj.page = this.page
    fltObj.size = this.size
    this.onSearch.emit(fltObj)
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchQuotation = this.searchQuotationService.createSearchQuotation();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchQuotation !=null) {
      this.searchQuotation.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    this.searchQuotation.controls.itemNo.valueChanges.subscribe(res => {
      console.log('res', res);
      if (!!res || `${res}` === '0') {
        this.autoItemNO.emit(res);
      }
    });
    this.searchQuotation.controls.quotationCode.valueChanges.subscribe(res => {
      if (!!res || `${res}` === '0') {
        this.quotationCodeChange.emit(res);
      }
    });
    if (this.searchQuotation.get('quotationCode').value == null && this.searchQuotation.get('sources').value==null && this.searchQuotation.get('enquiryStatus').value == null && this.searchQuotation.get('salesPerson').value == null && this.searchQuotation.get('itemNo').value == null && this.searchQuotation.get('variant').value == null && this.searchQuotation.get('model').value == null && this.searchQuotation.get('quotationFromDate').value == null && this.searchQuotation.get('quotationToDate').value == null && this.searchQuotation.get('products').value == null && this.searchQuotation.get('series').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchQuotation.get('quotationFromDate').patchValue(backDate);
      this.searchQuotation.get('quotationToDate').patchValue(new Date());
      this.setQuotationSearchResult();
    }
    else {
      localStorage.getItem(this.key)
      this.setQuotationSearchResult();
    }
  }
  ngAfterViewInit() {
    // this.setQuotationSearchResult();
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
        if (this.searchQuotation.get('quotationToDate').value > this.maxDate)
          this.searchQuotation.get('quotationToDate').patchValue(this.maxDate);
      }
    }
  }

  setQuotationSearchResult() {

    this.quotationCodeNgModel = "";
    this.quotationDateNgModel = "";
    this.enquiryNumberNgModel = "";
    this.salesPersonNgModel = "";
    this.enquiryStatusNgModel = "";
    this.sourceNgModel = "";
    this.purposeOfPurchaseNgModel = "";
    this.tentativePurchaseDateNgModel = "";
    this.itemNumberNgModel = "";
    this.itemDescriptionNgModel = "";
    this.modelNgModel = "";
    this.subModelNgModel = "";
    this.variantNgModel = "";
    this.seriesNgModel = "";
    this.productNgModel = "";
    this.prospectCodeNgModel = "";
    this.prospectTypeNgModel = "";
    this.companyNameNgModel = "";
    this.firstNameNgModel = "";
    this.middleNameNgModel = "";
    this.lastNameNgModel = "";
    this.fatherNameNgModel = "";
    this.whatsAppNumberNgModel = "";
    this.alternateMobileNumberNgModel = "";
    this.telephoneNumberNgModel = "";
    this.emailIdNgModel = "";
    this.address1NgModel = "";
    this.address2NgModel = "";
    this.address3NgModel = "";
    this.pinCodeNgModel = "";
    this.postOfficeNgModel = "";
    this.cityNgModel = "";
    this.stateNgModel = "";
    this.countryNgModel = "";
    this.languageNgModel = "";
    this.dobNgModel = "";
    this.anniversaryDateNgModel = "";
    this.gstNumberNgModel = "";
    this.panNumberNgModel5 = "";

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
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchQuotation.value))

    const searchField = {
      ...this.searchQuotation.getRawValue(),
      page: this.page,
      size: this.size,
    };
    if (searchField['quotationFromDate'] && searchField['quotationToDate']) {
      searchField['quotationFromDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['quotationFromDate']);
      searchField['quotationToDate'] = this.dateService.getDateIntoYYYYMMDD(searchField['quotationToDate'])
    }
    console.log('searchField:--2', searchField)
    ObjectUtil.removeNulls(searchField);
    console.log('searchField: ', searchField);

    this.searchFilter = searchField;
    if ((searchField['quotationFromDate'] === null || searchField['quotationFromDate'] === "" || searchField['quotationFromDate'] === undefined)) {
      if ((searchField['quotationToDate'] === null || searchField['quotationToDate'] === "" || searchField['quotationToDate'] === undefined)) {
        if (this.searchFlag == true && this.searchQuotation.get('quotationCode').value || this.searchQuotation.get('sources').value || this.searchQuotation.get('enquiryStatus').value || this.searchQuotation.get('salesPerson').value || this.searchQuotation.get('itemNo').value || this.searchQuotation.get('variant').value || this.searchQuotation.get('model').value || this.searchQuotation.get('quotationFromDate').value || this.searchQuotation.get('quotationToDate').value || this.searchQuotation.get('products').value || this.searchQuotation.get('series').value) {
          localStorage.setItem(this.key, JSON.stringify(this.searchQuotation.value))
          this.onSearch.emit(searchField)
        }
        else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    } else if ((searchField['quotationFromDate'] !== null || searchField['quotationFromDate'] !== "" || searchField['quotationFromDate'] !== undefined)) {
      if ((searchField['quotationToDate'] === null || searchField['quotationToDate'] === "" || searchField['quotationToDate'] === undefined)) {
        this.toastr.error("Please Select Date Range");
      } else {
        this.onSearch.emit(searchField)

      }
    }
  }

  clear() {
    this.searchQuotation.reset();
    // this.page = 0;
    // this.size = 10;
    // this.setQuotationSearchResult();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.btnActionValue.emit(recordData)
  }
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.setQuotationSearchResult();
  }
  // this.dateService.getDateIntoYYYYMMDD(filter[key])
  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
    console.log("selected Date_after: ", this.searchValue);
  }
  quotationNumberDisplayFn(quotation): string | undefined {
    return quotation ? quotation.quotationNumber : undefined;
  }
  productSelected(selectedProduct: MatSelectChange) {
    if (selectedProduct.value) {
      this.productSelectionChange.emit(selectedProduct.value);
    }
  }
  seriesSelected(selectedSeries: MatSelectChange) {
    if (selectedSeries.value) {
      this.seriesSelectionChange.emit(selectedSeries.value);
    }
  }
  modelSelected(selectedModel: MatSelectChange) {
    if (selectedModel.value) {
      this.modelSelectionChange.emit(selectedModel.value);
    }
  }
  subModelSelected(selectedModel: MatSelectChange) {
    if (selectedModel.value) {
      this.subModelSelectionChange.emit(selectedModel.value);
    }
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.searchQuotation.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchQuotation.patchValue({
      quotationFromDate: event.quotationFromDate ? new Date(event.quotationFromDate) : null,
      quotationToDate: event.quotationToDate ? new Date(event.quotationToDate) : null,
    });
    this.setQuotationSearchResult();
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
}
