import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { MrcSearchWebService } from '../../../pre-sales-service/machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ServiceMonitoringBoardService } from '../../../report/service-monitoring-board/service-monitoring-board.service';
import { CustomerSatisfactionService } from './customer-satisfaction.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-customer-satisfaction-report',
  templateUrl: './customer-satisfaction-report.component.html',
  styleUrls: ['./customer-satisfaction-report.component.css'],
  providers: [CustomerSatisfactionService, MrcSearchWebService, ServiceMonitoringBoardService]
})
export class CustomerSatisfactionReportComponent implements OnInit {
  reportTypes:any[]=[{code:'Dealer',value:'Dealer Wise'}, {code:'State',  value:'State Wise'},
  {code: 'Dept', value:'Dept Wise'}, {code:'Month', value:'Month Wise'}]
  departmentList:any[]
  reportSearchForm:FormGroup
  max: Date | null
  serverDate: Date;
  minDate: Date;
  maxDate: Date
  newdate= new Date()
  key = 'customerSatisfactionReport';
  isKai:boolean=false;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;
  searchFilterValues: any;
  dealercodeList: any;
  branches:any[] = [];
  chassisData;
  engineData;
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
  reportType:string='Dealer';
  constructor(private fb: FormBuilder,
    private mrcSearchService: MrcSearchWebService,
    private smbService: ServiceMonitoringBoardService,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private toastr: ToastrService,
    private service: CustomerSatisfactionService) { 
    }

  ngOnInit() {

    this.service.getLookupByCode("QA_DEPARTMENT").subscribe(response => {
      this.departmentList = response['result'];
    });

    this.reportSearchForm = this.fb.group({
      fromDate : [null],
      toDate: [null],
      product: [null],
      series: [null],
      machineModel: [null],
      subModel: [null],
      variant: [null],
      chassisNo: [null],
      orgHierarchyId: [null],
      dealerCode: [null],
      branch: [null],
      department: [null],
      reportType: [null],
      dealerName: [{value:null, disabled:true}]
    });
    this.getDateFromServer();

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.reportSearchForm != null) {
      this.reportSearchForm.patchValue(this.searchFilterValues)
      this.searchData();
    }
    else{
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.reportSearchForm.get('fromDate').patchValue(backDate);
      this.reportSearchForm.get('toDate').patchValue(new Date());
      this.searchData();
    }
    this.smbService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.reportSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
        })
      }
    });
    // this.reportSearchForm.controls.reportType.valueChanges.subscribe(val => {
    //   this.reportType = val;
    // })
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
      this.isKai = true;
    }
  }
  private getDateFromServer() {
    this.mrcSearchService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.maxDate = new Date(dateRes.result)
      }
    });
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.reportSearchForm.get('toDate').value > this.maxDate)
        this.reportSearchForm.get('toDate').patchValue(this.maxDate);
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

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.reportSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("CS").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.reportSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.mrcSearchService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.reportSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.mrcSearchService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  getDealerName(event){
    if (typeof event.option.value === 'object') {
      this.reportSearchForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.reportSearchForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  searchData(){
    let searchobj = this.reportSearchForm.value;
    if(searchobj && searchobj.reportType){
      this.reportType = searchobj.reportType;
    }
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
      this.service.getSatisfactionScoreReport(searchobj).subscribe(res => {
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

      this.service.downloadExcel(searchobj).subscribe((resp: HttpResponse<Blob>) => {
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
