import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataTable , ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
import { MonthlyFailureCodeSummaryReportService } from './monthly-failure-code-summary-report.service';
import {saveAs} from 'file-saver';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Component({
  selector: 'app-monthly-failure-code-summary-report',
  templateUrl: './monthly-failure-code-summary-report.component.html',
  styleUrls: ['./monthly-failure-code-summary-report.component.css'],
  providers: [MonthlyFailureCodeSummaryReportService, ServiceMonitoringBoardService]
})
export class MonthlyFailureCodeSummaryReportComponent implements OnInit {

  reportSearchForm:FormGroup
  key = 'monthlyFailureCodeSummaryReport';
  isKai:boolean=false;
  loginUser:any;
  searchFilterValues: any;
  seriesByProduct;
  modelBySeries;
  subModelByModel;
  variants;
  products;

  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag:boolean = true;

  constructor(private fb: FormBuilder,
    private smbService: ServiceMonitoringBoardService,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private toastr: ToastrService,
    private service: MonthlyFailureCodeSummaryReportService) { 
    }

  ngOnInit() {

    this.reportSearchForm = this.fb.group({
      fromYear : [null,  Validators.compose([Validators.required, CustomValidators.numberOnly])],
      toYear: [null,  Validators.compose([Validators.required, CustomValidators.numberOnly, Validators.maxLength(4), Validators.minLength(4)])],
      product: [null],
      series: [null],
      machineModel: [null],
      subModel: [null],
      variant: [null],
      complaintCode: [null]
    });

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.reportSearchForm != null) {
      this.reportSearchForm.patchValue(this.searchFilterValues)
    }
    if(!this.reportSearchForm.valid){
      let currentYear: number = new Date(). getFullYear();
      this.reportSearchForm.controls.fromYear.patchValue(currentYear-1);
      this.reportSearchForm.controls.toYear.patchValue(currentYear);
    }
    this.searchData();

    this.smbService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.loginUser = localStorage.getItem('kubotaUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.isKai = true;
    }
  }
  selectProduct(value) {
    this.smbService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response['result'];
      this.modelBySeries = null;
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectSeries(value) {
    this.smbService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response['result']
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectModel(value) {
    this.smbService.getSubModelByModel(value).subscribe(response => {
      this.subModelByModel = response['result']
      this.variants = null;
    })
  }

  selectSubModel(value) {
    this.smbService.getVariantBySubModel(value).subscribe(response => {
      this.variants = response['result']
    })
  }

  searchData(){
    this.reportSearchForm.markAllAsTouched();
    
    if(!this.reportSearchForm.valid){
      this.toastr.error("Please fill required fields.");
      return;
    }
    let searchobj = this.reportSearchForm.value;
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    this.searchFlag = true;
    
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      this.service.getSummaryReport(searchobj).subscribe(res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
        this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalSearchRecordCount = 0;
      });
    
    } else{
      this.toastr.error("Please fill atleast one input field.");
    }

  }

  clearForm() {
    this.reportSearchForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  pageSizeChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchData();
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }

  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }

  clickExportToExcel(){
    let searchobj = this.reportSearchForm.value;
     
 
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    this.searchFlag = true;
    
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      searchobj.fromYear=searchobj.fromYear.toString()
      searchobj.toYear=searchobj.toYear.toString()
      this.service.exportFailurecodeReport(searchobj).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
        
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    }else{
      this.toastr.error("Please fill atleast one input field.");
    }
  }
}
