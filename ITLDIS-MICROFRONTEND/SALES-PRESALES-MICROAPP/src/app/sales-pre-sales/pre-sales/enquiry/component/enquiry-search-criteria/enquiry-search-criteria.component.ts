import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { EnquirySearchCriteriaService } from './enquiry-search-criteria.service';
import { DropDownModel, DropDownSubModel, DropDownProduct, DropDownVariant, DropDownSeries, SearchEnquiryFilterDomain } from 'EnquirySearchCriteria';
import { DropDownSource, DropDownRetailConversionActivityType, EnquiryNoNameThesilDomain } from 'EnquiryCreation';
import { ItemNo } from 'EnquiryProductIntrested';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatDatepickerInput } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { Router, ActivatedRoute } from '@angular/router';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { BaseDto } from 'BaseDto';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownSalePerson } from 'TransferSearchCriteria';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-enquiry-search-criteria',
  templateUrl: './enquiry-search-criteria.component.html',
  styleUrls: ['./enquiry-search-criteria.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    EnquirySearchCriteriaService,
    NgswSearchTableService,
  ]
})
export class EnquirySearchCriteriaComponent implements OnInit {
  isEdit: boolean;
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  searchValue: ColumnSearch = {} as ColumnSearch
  appEnquiryFlag: boolean
  enquiryListForm: FormGroup;
  enquiryAdvancedSearch: FormGroup;
  searchform: boolean
  loginUserId: number;
  enquiryNumber: string
  bttonnAction: string
  states: string[] = ['Hot', 'Warm', 'Cold'];
  sales: string[] = [
    'Sales Person 1', 'Sales Person 2', 'Sales Person 3'
  ];
  enquries: string[] = [
    'Open', 'Close', 'Retail', 'Lost',
  ];
  finances: string[] = [
    'YES', 'NO',
  ];
  autoCloses: string[] = [
    'YES', 'NO',
  ];
  subsidies: string[] = [
    'YES', 'NO',
  ];
  exchanges: string[] = [
    'YES', 'NO',
  ];
  actionButtons = [];
  @Input() public set allDataForPatch(patchData) {
    console.log('patchData', patchData)
    if (patchData) {
      this.enquiryListForm.controls.product.patchValue(patchData.product)
      this.enquiryListForm.controls.product.disable()
      this.productSelected.emit(patchData.product)

      this.enquiryListForm.controls.series.patchValue(patchData.series)
      this.enquiryListForm.controls.series.disable()
      this.seriesSelected.emit(patchData.series)

      this.enquiryListForm.controls.model.patchValue(patchData.model)
      this.enquiryListForm.controls.model.disable()
      this.modelSelected.emit(patchData.model)

      this.enquiryListForm.controls.subModel.patchValue(patchData.subModel)
      this.enquiryListForm.controls.subModel.disable()
      this.enquiryListForm.controls.variant.patchValue(patchData.variant)
      this.enquiryListForm.controls.variant.disable()

    }
  }
  @Input() dropdownModel: BaseDto<Array<DropDownModel>>
  @Input() dropdownSubModel: BaseDto<Array<DropDownSubModel>>
  @Input() dropdownProduct: BaseDto<Array<DropDownProduct>>
  @Input() dropdownVariant: BaseDto<Array<DropDownVariant>>
  @Input() dropdownseries: BaseDto<Array<DropDownSeries>>
  @Input() dropdownSource: BaseDto<Array<DropDownSource>>
  @Input() dropdownRetailConversionActivity: BaseDto<Array<DropDownRetailConversionActivityType>>
  @Output() autoEnquiryNumber: EventEmitter<string> = new EventEmitter<string>()
  @Input() enquiryCode: BaseDto<Array<EnquiryNoNameThesilDomain>>
  @Output() autoItemNo: EventEmitter<string> = new EventEmitter<string>()
  @Input() itemNo: BaseDto<Array<ItemNo>>
  @Output() onEnquirySearch = new EventEmitter<object>()
  @Input() dataTable: DataTable;
  @Output() productSelected = new EventEmitter<string>()
  @Output() seriesSelected = new EventEmitter<string>()
  @Output() modelSelected = new EventEmitter<string>()
  @Output() itemNumberGetData = new EventEmitter<string>()
  page: number = 0;
  size: number = 10;
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  dropdownSalePerson: BaseDto<Array<DropDownSalePerson>>
  @Input() totalTableRecords: number
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];
  constructor(
    private enquirySearchCriteriaService: EnquirySearchCriteriaService,
    private router: Router,
    private enqRt: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService,
    public dialog: MatDialog,
    private loginFormService: LoginFormService,
    private toastr: ToastrService
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
  }

  ngOnInit() {
    this.clickOnTableFields = [{ 'title': 'enquiryNumber', 'isClickable': true }, { 'title': 'action', 'isClickable': true }]
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate() + 1);
    this.enquiryListForm = this.enquirySearchCriteriaService.searchenquiryListForm();
    this.enquiryListForm.controls.enquiryNumber.valueChanges.subscribe(value => {
      this.autoEnquiryNumber.emit(value)
    });
    this.enquiryListForm.controls.itemNo.valueChanges.subscribe(value => {
      console.log('value', value);
      this.autoItemNo.emit(value)
    });
    this.setEnquirySearchResult();
    this.dropDownSalesPerson()
  }
  ngAfterViewInit() {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.enquiryListForm.get('fromDate').patchValue(backDate);
    this.enquiryListForm.get('toDate').patchValue(new Date());

  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate){
        this.maxDate = this.newdate;
      }
      else
     {   this.maxDate = maxDate;
      if (this.enquiryListForm.get('toDate').value > this.maxDate)
        this.enquiryListForm.get('toDate').patchValue(this.maxDate);
     }
    }
  }
  dropDownSalesPerson() {
    this.enquiryCommonService.getAllSalesPerson().subscribe(response => {
      console.log('salesPerson', response);
      this.dropdownSalePerson = response as BaseDto<Array<DropDownSalePerson>>
    })
  }

  displayFn(enqNum: EnquiryNoNameThesilDomain) {
    // console.log(enqNum);
    return enqNum ? enqNum.enquiryNumber : undefined
  }

  setEnquirySearchResult() {
    const fltEnqObj = this.enquiryListForm.getRawValue() as SearchEnquiryFilterDomain
    fltEnqObj.enquiryNumber = this.enquiryListForm.controls.enquiryNumber.value ? this.enquiryListForm.controls.enquiryNumber.value.enquiryNumber : ''
    fltEnqObj.itemNo = this.enquiryListForm.controls.itemNo.value ? this.enquiryListForm.controls.itemNo.value.itemNumber : ''
    fltEnqObj.page = this.page
    fltEnqObj.size = this.size
    fltEnqObj.userId = this.loginUserId
    Object.keys(fltEnqObj).map(key => fltEnqObj[key] = fltEnqObj[key] == null ? '' : fltEnqObj[key])
    console.log('fltEnqObj---->', fltEnqObj);
    let fromDate = this.enquiryListForm.controls.fromDate.value;
    let toDate = this.enquiryListForm.controls.toDate.value;
    console.log("Dates Selected: "+ fromDate+" and "+toDate);
   
    this.onEnquirySearch.emit(fltEnqObj)
    
  
  }

  clearEnquirySearch() {
    this.enquiryListForm.reset()
    this.enquiryListForm.controls.product.enable()
    this.enquiryListForm.controls.series.enable()
    this.enquiryListForm.controls.model.enable()
    this.enquiryListForm.controls.subModel.enable()
    this.enquiryListForm.controls.variant.enable()
    this.setEnquirySearchResult()
  }

  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.enquiryNumber = recordData.record.enquiryNumber
    this.bttonnAction = recordData.btnAction
    if (recordData.btnAction.toLowerCase() === 'enquirynumber') {
      if (!recordData.record.appEnquiryFlag) {
        this.router.navigate(['view', recordData.record.enquiryNumber], { relativeTo: this.enqRt })
      } else {
        this.router.navigate(['viewMobile', recordData.record.enquiryNumber], { relativeTo: this.enqRt })
      }
      console.log('appEnquiryFlag', recordData.record.appEnquiryFlag);
    }

    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action.toLowerCase() === 'validate') {
        this.router.navigate(['viewMobile', recordData.record.enquiryNumber], { relativeTo: this.enqRt })
      } else if (recordData.record.enquiryStatus.toLowerCase() === 'drop' || recordData.record.enquiryStatus.toLowerCase() === 'lost') {
        // this.router.navigate([])
      } else {
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry/followup', recordData.record.enquiryNumber], { relativeTo: this.enqRt })
      }
    }
  }

  pageChange(event) {
    //console.log(event);
    this.page = event.page;
    this.size = event.size;
    this.setEnquirySearchResult()
  }

  selectProduct(event) {
    this.productSelected.emit(event.value)

  }

  selectSeries(event) {
    console.log('event', event)
    this.seriesSelected.emit(event.value)

  }
  selectModel(event) {
    this.modelSelected.emit(event.value)

  }
  getItemNumber(event) {
    console.log('event', event)
    this.itemNumberGetData.emit(event.option.value.itemNumber)
  }

  enqyuiryDateChanges(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  createEnquiry() {
    this.router.navigate(['/sales-pre-sales/pre-sales/enquiry/create'])
  }

  transferEnquiry() {
    this.router.navigate(['/sales-pre-sales/pre-sales/enquiry/transfer'])
  }
  public selectItemNo(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['itemNumber'] : undefined;
  }
}