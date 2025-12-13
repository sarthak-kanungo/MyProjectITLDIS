import { Component, OnInit, AfterViewInit } from '@angular/core';
import { PartQuotationSearchPageStore } from './part-quotation-search-page.store';
import { PartQuotationSearchPagePresenter } from './part-quotation-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { FilterQuotationSearch } from '../../domain/part-quotation-domain';
import { DateService } from '../../../../../root-service/date.service';
import { PartQuotationSearchPageWebService } from './part-quotation-search-page-web.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';


@Component({
  selector: 'app-part-quotation-search-page',
  templateUrl: './part-quotation-search-page.component.html',
  styleUrls: ['./part-quotation-search-page.component.scss'],
  providers: [PartQuotationSearchPageStore, PartQuotationSearchPagePresenter, PartQuotationSearchPageWebService]
})
export class PartQuotationSearchPageComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup
  partQuotationSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object = {};
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  EditNgModel: any = "";
  Quotation_NumberNgModel: any = "";
  Quotation_DateNgModel: any = "";
  Customer_TypeNgModel: any = "";
  Customer_NameNgModel: any = "";
  Mobile_NumberNgModel: any = "";
  Customer_Address1NgModel: any = "";
  Customer_Address2NgModel: any = "";
  CountryNgModel: any = "";
  StateNgModel: any = "";
  DistrictNgModel: any = "";
  TehsilNgModel: any = "";
  CityNgModel: any = "";
  Discount_TypeNgModel: any = "";
  Discount_RateNgModel: any = "";
  Discount_ValueNgModel: any = "";
  Total_Basic_ValueNgModel: any = "";
  searchFilterValues: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  key='pqsp';

  constructor(
    private partQuotationSearchPagePresenter: PartQuotationSearchPagePresenter,
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private quotationRt: ActivatedRoute,
    private partQuotationSearchPageWebService: PartQuotationSearchPageWebService,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    localStorage.removeItem('searchFilter');
    localStorage.removeItem('searchFilterSo');
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchForm = this.partQuotationSearchPagePresenter.partQuotationSearchForm
    this.partQuotationSearchForm = this.partQuotationSearchPagePresenter.detailsForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.partQuotationSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilterQuo');
    if (this.partQuotationSearchForm.get('quotationNumber').value==null && this.partQuotationSearchForm.get('customerName').value==null && this.partQuotationSearchForm.get('customerType').value==null && this.partQuotationSearchForm.get('quotationFromDate').value==null && this.partQuotationSearchForm.get('quotationToDate').value==null) {
    this.maxDate= this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth()-1);
    this.minDate = backDate;
    this.partQuotationSearchForm.get('quotationFromDate').patchValue(backDate);
    this.partQuotationSearchForm.get('quotationToDate').patchValue(new Date());
    this.onClickSearchPartQuotationDetails()
    }
    else{
      localStorage.getItem(this.key)
      this.onClickSearchPartQuotationDetails()
    }
  }

  ngAfterViewInit() {
    
  }

  onClickSearchPartQuotationDetails() {
    this.resetAllSearchValue()
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
      if (this.partQuotationSearchForm.get('quotationNumber').value || this.partQuotationSearchForm.get('customerName').value || this.partQuotationSearchForm.get('customerType').value || this.partQuotationSearchForm.get('quotationFromDate').value || this.partQuotationSearchForm.get('quotationToDate').value) {
        this.partQuotationSearchPageWebService.getQuotationSearch(this.buildObjForSearchPartQuotation()).subscribe(response => {
          this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
          this.totalTableElements = response['count']
        })
      }
      else if (this.partQuotationSearchForm.get('quotationFromDate').value == null && this.partQuotationSearchForm.get('quotationToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
  }

  private buildObjForSearchPartQuotation() {

    const filtObj = {} as FilterQuotationSearch

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

    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.quotationId = this.partQuotationSearchForm.value.quotationNumber ? this.partQuotationSearchForm.value.quotationNumber.id : null
    filtObj.customerName = this.partQuotationSearchForm.value ? this.partQuotationSearchForm.value.customerName : null
    filtObj.customerType = this.partQuotationSearchForm.value ? this.partQuotationSearchForm.value.customerType : null
    filtObj.quotationFromDate = this.partQuotationSearchForm.value.quotationFromDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationFromDate) : null
    filtObj.quotationToDate = this.partQuotationSearchForm.value.quotationToDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationToDate) : null
    console.log(filtObj);
    const temp = this.partQuotationSearchForm.value as FilterQuotationSearch
    let key = 'searchFilterQuo';
    localStorage.setItem(this.key, JSON.stringify(this.partQuotationSearchForm.value))
    temp.page = this.page
    temp.size = this.size
    temp.quotationId = this.partQuotationSearchForm.value.quotationNumber ? this.partQuotationSearchForm.value.quotationNumber.id : null
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    console.log('oiuoi', this.filterData);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.partQuotationSearchForm.patchValue(event)
    if (event.customerName) {
      this.partQuotationSearchForm.get('customerName').patchValue(event.customerName.replace(searchValue, newValue));
      event.customerName = event.customerName.replace(searchValue, newValue)
    }
    if (event.customerName) {
      this.partQuotationSearchForm.get('customerName').patchValue(event.customerName.replace(searchValue, newValue));
      event.customerName = event.customerName.replace(searchValue, newValue)
    }

    if (event.quotationNumber) {
      this.partQuotationSearchForm.get('quotationNumber').patchValue({ quotationNumber: event.quotationNumber.replace(searchValue, newValue), id: event.quotationId });
      event.quotationNumber = event.quotationNumber.replace(searchValue, newValue)
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'quotationFromDate' || element === 'quotationToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

        if (searchObject[element] && element === 'quotationNumber') {
          searchObject[element] = searchObject['quotationNumber']['quotationNumber']
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

  onClickClearPartQuotationDetails() {
    this.partQuotationSearchForm.reset()
    // this.onClickSearchPartQuotationDetails()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData.record.id)
    let filterdata = btoa(JSON.stringify(this.filterData))
    if (recordData.btnAction.toLowerCase() === 'quotation_number') {
      this.router.navigate(['view', id], { relativeTo: this.quotationRt, queryParams: { filterData: filterdata } })
    }
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['edit', id], { relativeTo: this.quotationRt, queryParams: { filterData: filterdata } })
    }
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearchPartQuotationDetails()
  }
  quotationDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  checkValidDatesInput() {
    const filtObj = {} as FilterQuotationSearch

    filtObj.quotationFromDate = this.partQuotationSearchForm.value.quotationFromDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationFromDate) : null
    filtObj.quotationToDate = this.partQuotationSearchForm.value.quotationToDate ? this.dateService.getDateIntoYYYYMMDD(this.partQuotationSearchForm.value.quotationToDate) : null

    console.log("Date Selected: " + filtObj['quotationFromDate'] + filtObj['quotationToDate']);

    if ((filtObj.quotationFromDate === null || filtObj.quotationFromDate === "" || filtObj.quotationFromDate === undefined)) {
      if ((filtObj.quotationToDate === null || filtObj.quotationToDate === "" || filtObj.quotationToDate === undefined)) {
        return true;
      } else {
        return false;
      }
    } else {
      if ((filtObj.quotationToDate === null || filtObj.quotationToDate === "" || filtObj.quotationToDate === undefined)) {
        return false;
      } else {
        return true;
      }
    }
  }
  resetAllSearchValue() {
    this.EditNgModel = "";
    this.Quotation_NumberNgModel = "";
    this.Quotation_DateNgModel = "";
    this.Customer_TypeNgModel = "";
    this.Customer_NameNgModel = "";
    this.Mobile_NumberNgModel = "";
    this.Customer_Address1NgModel = "";
    this.Customer_Address2NgModel = "";
    this.CountryNgModel = "";
    this.StateNgModel = "";
    this.DistrictNgModel = "";
    this.TehsilNgModel = "";
    this.CityNgModel = "";
    this.Discount_TypeNgModel = "";
    this.Discount_RateNgModel = "";
    this.Discount_ValueNgModel = "";
    this.Total_Basic_ValueNgModel = "";
  }
}