import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchMtrListService } from './search-mtr-list.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, ColumnSearch } from 'ngsw-search-table';
import { NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DateService } from 'src/app/root-service/date.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-search-mtr-list',
  templateUrl: './search-mtr-list.component.html',
  styleUrls: ['./search-mtr-list.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchMtrListService
  ]
})
export class SearchMtrListComponent implements OnInit {
  // requeststatus: string[] = [
  //   'Submitted', 'Approved', 'Rejected', 'Transferred'
  // ];
  products = [];
  // activityOnTableFields = [{ title: 'status', icon: 'done_outline', iconClass: 'material-icons' }];
  fromDate = new Date();
  searchValue: ColumnSearch;

  requeststatus = [];
  seriesList = [];
  clearSearchRow = new BehaviorSubject<string>("");
  models = [];
  submodels = [];
  variants = [];
  searchFilter
  searchFlag: boolean = true;
  // products: string[] = [
  //   'Tractor', 'Harvester', 'Power Tiller', 'Rice-Transplanter', 'Seeding Machine', 'Rotary Tiller'
  // ];
  // seriesList: string[] = [
  //   'MU', 'DG68G', 'RIDE ON', 'Walk Behind', 'PEM', 'Automatic', ' Manual', 'KRM', 'KRX'
  // ];
  // models: string[] = [
  //   'MU5501', 'MU4501', 'L4508', 'A211N', 'DC68G', 'NSPU68C', 'SPV6MD', 'PEM140 DI', 'SRK800', 'KRM180D', 'FL1021'
  // ];
  // submodels: string[] = [
  //   '2WD', '4WD', 'I-Trac', 'G-Plus', 'HK', 'STD', 'With Seat', 'Without Seat'
  // ];
  // variants: string[] = [
  //   'DTPO-FA', 'RPTO-VA', 'STD-FA', 'STD', 'NS',
  // ];

  machineTransferRequestListForm: FormGroup;
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  public totalTableElements: number;
  // changedInDate = new Date()

  requestNoList: Observable<(string | object)[]>;
  itemNoList: Observable<(string | object)[]>;
  today = new Date();
  minDate: Date;
  maxDate: Date
  private searchFormValues: any;

  productNgModel: any = "";
  seriesNgModel: any = "";
  modelNgModel: any = "";
  variantNgModel: any = "";
  itemDescNgModel: any = "";
  itemNumberNgModel: any = "";
  requestDateNgModel: any = "";
  requestNumberNgModel: any = "";
  statusNgModel: any = "";
  coDealerCodeNgModel: any = '';
  coDealerNameNgModel: any = '';
  searchFilterValues: any;
  key = 'mtr';
  selectedFromDate: Date;
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dateService: DateService,
    private searchMtrListService: SearchMtrListService,
    private ngswSearchTableService: NgswSearchTableService,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem('mtr')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchMachineTrnasferForm()
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.machineTransferRequestListForm != null) {
      this.machineTransferRequestListForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('mtr');
    this.requeststatusDropDown()
    this.productsDropDown()
    this.variantsDropDown()
    this.requestNoChanges()
    this.requestitemNo()
    // this.actionButtons.push(this.ngswSearchTableService.addActionButton('Clear', 'done_outline', 'Clear'));
    if (this.machineTransferRequestListForm.get('requestNumber').value == null && this.machineTransferRequestListForm.get('subModel').value == null && this.machineTransferRequestListForm.get('itemNo').value == null && this.machineTransferRequestListForm.get('variant').value == null && this.machineTransferRequestListForm.get('model').value == null && this.machineTransferRequestListForm.get('requestFromDate').value == null && this.machineTransferRequestListForm.get('requestToDate').value == null && this.machineTransferRequestListForm.get('requestStatus').value == null && this.machineTransferRequestListForm.get('product').value == null && this.machineTransferRequestListForm.get('series').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.machineTransferRequestListForm.get('requestFromDate').patchValue(backDate);
      this.machineTransferRequestListForm.get('requestToDate').patchValue(new Date());
      this.searchMtr();
    }
    else {
      localStorage.getItem(this.key)
      this.searchMtr();
    }
  }

  private requestNoChanges() {
    this.machineTransferRequestListForm.get('requestNumber').valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.searchMtrListService.searchByrequestNo(changedValue).subscribe(res => {
          this.requestNoList = of(res['result']);
        })
      }
    })
  }
  selectRequestNo(value) {
    return (value!==null && typeof value == 'object') ? value.requestNo : value;
  }
  private requestitemNo() {
    this.machineTransferRequestListForm.get('itemNo').valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.machineTransferRequestListForm.controls.itemNo.setErrors({ 'selectFromList': 'Select From List' });
        if (typeof changedValue === 'object') {
          this.machineTransferRequestListForm.controls.itemNo.setErrors(null);
          changedValue = changedValue['itemNo'];
        }
        this.searchMtrListService.searchByitemNo(changedValue).subscribe(res => {
          this.itemNoList = of(res['result']);
        })
      } else {
        this.itemNoList = null;
        this.machineTransferRequestListForm.controls.product.enable()
        this.machineTransferRequestListForm.controls.series.enable()
        this.machineTransferRequestListForm.controls.model.enable()
        this.machineTransferRequestListForm.controls.subModel.enable()
        this.machineTransferRequestListForm.controls.variant.enable()
        this.machineTransferRequestListForm.controls.itemNo.setErrors(null);
      }
    })
  }

  searchMachineTrnasferForm() {
    this.machineTransferRequestListForm = this.fb.group({
      requestNumber: [null, Validators.compose([])],
      requestStatus: [null, Validators.compose([])],
      product: [null, Validators.compose([])],
      series: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      subModel: [null, Validators.compose([])],
      variant: [null, Validators.compose([])],
      itemNo: [null, Validators.compose([])],
      requestFromDate: [null, Validators.compose([])],
      requestToDate: [null, Validators.compose([])],
    })
  }

  displayFnItemNo(itemNo: any) {
    if (typeof itemNo === 'string') {
      return itemNo;
    }
    return itemNo ? itemNo.itemNo : undefined
  }

  requeststatusDropDown() {
    this.searchMtrListService.requeststatusDropDownServ().subscribe(res => {
      this.requeststatus = res['result']
    })
  }
  productsDropDown() {
    this.searchMtrListService.productsDropDownServ().subscribe(res => {
      this.products = res['result']
    })
  }
  selectProduct(event) {
    let selectedProduct = event.value
    this.seriesListDropDown(selectedProduct);
    this.machineTransferRequestListForm.get('series').reset();
    this.machineTransferRequestListForm.get('model').reset();
    this.machineTransferRequestListForm.get('subModel').reset();
    this.machineTransferRequestListForm.get('variant').reset();
  }
  seriesListDropDown(selectedProduct) {
    this.searchMtrListService.seriesListDropDownServ(selectedProduct).subscribe(res => {
      this.seriesList = res['result']
    })
  }
  selectSeries(event) {
    let selectedSeries = event.value
    this.modelsDropDown(selectedSeries)
    this.machineTransferRequestListForm.get('model').reset();
    this.machineTransferRequestListForm.get('subModel').reset();
    this.machineTransferRequestListForm.get('variant').reset();
  }

  modelsDropDown(selectedSeries) {
    this.searchMtrListService.modelsDropDownServ(selectedSeries).subscribe(res => {
      this.models = res['result']
    })
  }
  selectModel(event) {
    let selectedModel = event.value
    this.submodelsDropDown(selectedModel)
    this.machineTransferRequestListForm.get('subModel').reset();
    this.machineTransferRequestListForm.get('variant').reset();

  }
  submodelsDropDown(selectedModel) {
    this.searchMtrListService.submodelsDropDownServ(selectedModel).subscribe(res => {
      this.submodels = res['result']
    })
  }
  selectSubModel(event) {
    let selectedSubModel = event.value
    this.machineTransferRequestListForm.get('variant').reset();

  }
  variantsDropDown() {
    this.searchMtrListService.variantsDropDownServ().subscribe(res => {
      this.variants = res['result']
    })
  }


  createActivityClaim() {
    this.router.navigate(['../create'], {
      relativeTo: this.activatedRoute
    })
    // this.router.navigate(['sales-pre-sales/purchase/machine-transfer-request/create'])
  }

  itemNoValue(event) {

    this.searchMtrListService.getDataByItemNumber(event.option.value).subscribe(res => {

      this.machineTransferRequestListForm.controls.product.disable()
      this.machineTransferRequestListForm.controls.series.disable()
      this.machineTransferRequestListForm.controls.model.disable()
      this.machineTransferRequestListForm.controls.subModel.disable()
      this.machineTransferRequestListForm.controls.variant.disable()

      this.machineTransferRequestListForm.controls.product.patchValue(res['result']['product'])
      this.seriesListDropDown(res['result']['product'])
      this.machineTransferRequestListForm.controls.series.patchValue(res['result']['series'])
      this.modelsDropDown(res['result']['series'])
      this.machineTransferRequestListForm.controls.model.patchValue(res['result']['model'])
      this.submodelsDropDown(res['result']['model'])
      this.machineTransferRequestListForm.controls.subModel.patchValue(res['result']['subModel'])
      this.machineTransferRequestListForm.controls.variant.patchValue(res['result']['variant'])

    })
  }

  fromDateChange(event) {

    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.machineTransferRequestListForm.get('requestToDate').value > this.maxDate)
        this.machineTransferRequestListForm.get('requestToDate').patchValue(this.maxDate);
    }

  }
  ngAfterViewInit() {

    // this.searchMtr();

  }
  searchMtr() {
    this.productNgModel = "";
    this.seriesNgModel = "";
    this.modelNgModel = "";
    this.variantNgModel = "";
    this.itemDescNgModel = "";
    this.itemNumberNgModel = "";
    this.requestDateNgModel = "";
    this.requestNumberNgModel = "";
    this.statusNgModel = "";
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
      this.searchFlag = true;
    }
    this.searchFormValues = this.machineTransferRequestListForm.getRawValue();
    let key = 'searchFilter';
    
    this.searchFilter = ObjectUtil.removeNulls(this.searchFormValues);
    Object.keys(this.searchFormValues).forEach(key => {
      if (this.searchFormValues[key] === null) {
        delete this.searchFormValues[key];
      } else {
        if (this.searchFormValues[key] && (key === 'requestFromDate') || (key === 'requestToDate')) {
          this.searchFormValues[key] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues[key]);
        }
        if (this.searchFormValues[key] && (key === 'requestNumber')) {
          this.searchFormValues[key] = this.searchFormValues[key]!=null?this.searchFormValues[key]['requestNo']:null
        }
        if (this.searchFormValues[key] && (key === 'itemNo')) {
          this.searchFormValues['itemNumber'] = this.searchFormValues[key]!=null?this.searchFormValues[key]['code']:null;
          delete this.searchFormValues[key];
        }
      }
    })
    if (this.checkValidDatesInput()) {
      delete this.searchFormValues['page']
      delete this.searchFormValues['size']
      
      if (Object.keys(this.searchFormValues).length>0) {
        this.searchFormValues['page'] = this.page;
        this.searchFormValues['size'] = this.size;
        localStorage.setItem(this.key, JSON.stringify(this.machineTransferRequestListForm.value))
        this.searchMtrListService.searchMtr(this.searchFormValues).subscribe(res => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
          this.totalTableElements = res['count'];

        })
      }
      else if (this.machineTransferRequestListForm.get('requestFromDate').value == null && this.machineTransferRequestListForm.get('requestToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.machineTransferRequestListForm.value

    fltEnqObj.fromDate = this.machineTransferRequestListForm.getRawValue() ? this.machineTransferRequestListForm.value.requestFromDate : null
    fltEnqObj.toDate = this.machineTransferRequestListForm.getRawValue() ? this.machineTransferRequestListForm.value.requestToDate : null

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
    this.machineTransferRequestListForm.reset();
    // this.searchMtr();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  tableActionClick(event: object) {
    if (event['btnAction'].toLowerCase() === 'requestnumber') {
      this.router.navigate(['../view', btoa(event['record']['requestNumber'])], { relativeTo: this.activatedRoute});
    }
    if (event['btnAction'].toLowerCase() === 'action') {
      this.router.navigate(['../approve', btoa(event['record']['requestNumber'])], { relativeTo: this.activatedRoute});
    }
  }
  pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false
    this.searchMtr();
  }
  /**
   * ----------Following is state management code------------
   */

  initialQueryParams(event) {
    if (event && event['requestFromDate']) {
      event['requestFromDate'] = new Date(event['requestFromDate']);
      this.selectedFromDate = new Date(event['requestFromDate'])
    }
    if (event && event['requestToDate']) event['requestToDate'] = new Date(event['requestToDate']);
    this.machineTransferRequestListForm.patchValue(event);
    this.page = event['page'];
    this.size = event['size'];
    this.searchMtr();
  }

  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}
