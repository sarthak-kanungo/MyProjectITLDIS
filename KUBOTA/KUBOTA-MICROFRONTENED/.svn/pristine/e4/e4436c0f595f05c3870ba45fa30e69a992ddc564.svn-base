import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { DataTable } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { TableDataService } from 'src/app/ui/dynamic-table/table-data.service';
import { ReportService } from '../report.service';
import { saveAs } from 'file-saver';
import { PurchaseOrderSearchWebService } from '../../spares-purchase/spares-purchase-order/component/purchase-order-search/purchase-order-search-web.service';

@Component({
  selector: 'app-inventory-movement-report',
  templateUrl: './inventory-movement-report.component.html',
  styleUrls: ['./inventory-movement-report.component.css'],
  providers: [ReportService, PurchaseOrderSearchWebService]
})
export class InventoryMovementReportComponent implements OnInit {

  today = new Date();
  minDate: Date;
  maxDate: Date
  inventoryMovementReportForm:FormGroup;
  isKai:boolean=true;
  jnewdate= new Date();
  key='InventoryMovementReport';
  searchFilterValues:any=null;
  actionButtons = [];
  totalTableElements: number;
  dataTable: DataTable;
  page: number = 0;
  size: number = 10;
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue:any
  dealercodeList: any;
  branches:any[] = [];
  searchFlag:boolean=true;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  seriesByProduct;
  modelBySeries;
  subModelByModel;
  variants;
  products;
  states;
  partNumberAutoList;
  constructor(private fb: FormBuilder,
    private toastr: ToastrService,
    private dateService: DateService,
    private tableDataService: TableDataService,
    private loginService: LoginFormService,
    private reportService: ReportService,
    private searchSparesPoService: PurchaseOrderSearchWebService) { }

  ngOnInit() {
    this.inventoryMovementReportForm = this.fb.group({
      fromDate:[null],
      toDate:[null],
      dealerCode:[null],
      branch:[null],
      orgHierarchyId:[null],
      product:[null],
      series:[null],
      machineModel:[null],
      subModel:[null],
      variant:[null],
      state:[null],
      partNumber:[null]
    });
    let userType = this.loginService.getLoginUserType();
    if(userType=='dealer'){
      this.isKai = false;
    }else{
      this.getLevelByDeprtment();
    }
    this.getBranches(0);
    this.statesDropdown(0);
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.inventoryMovementReportForm != null) {
      this.inventoryMovementReportForm.patchValue(this.searchFilterValues)
      
      this.searchData();
    }
    else{
      if(this.inventoryMovementReportForm.get('fromDate').value){

      }else{
        let maxDate = new Date();
        maxDate.setMonth(maxDate.getMonth() - 1);
        this.inventoryMovementReportForm.get('fromDate').patchValue(maxDate);
      }
      if(this.inventoryMovementReportForm.get('toDate').value){

      }else{
        let maxDate = new Date();
        this.inventoryMovementReportForm.get('toDate').patchValue(maxDate);
      }
      this.searchData();
    }

    this.inventoryMovementReportForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.reportService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if(typeof res === 'object'){
           this.branches = this.branches.filter(obj => obj['dealercode']==res['code']);
           this.inventoryMovementReportForm.controls.branch.reset();
          }
        })
      }else{
        // this.getBranches(0);
        this.inventoryMovementReportForm.controls.branch.reset();
      }
    });

    this.inventoryMovementReportForm.get('partNumber').valueChanges.subscribe((changedValue: string) => {
      if (changedValue) {
        this.partNumberAutoList = this.searchSparesPoService.searchByPartNumber(changedValue);
      }
    });

  }
  public displayPartNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['item_no'] : undefined;
  }

  getBranches(dealerId){
    this.reportService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.inventoryMovementReportForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      this.getBranches(event.option.value.id);
      
      this.statesDropdown(event.option.value.id);
    }
  }

  selectProduct(value) {
    this.reportService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response['result'];
      this.modelBySeries = null;
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectSeries(value) {
    this.reportService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response['result']
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectModel(value) {
    this.reportService.getSubModelByModel(value).subscribe(response => {
      this.subModelByModel = response['result']
      this.variants = null;
    })
  }

  selectSubModel(value) {
    this.reportService.getVariantBySubModel(value).subscribe(response => {
      this.variants = response['result']
    })
  }

  clearForm() {
    this.inventoryMovementReportForm.reset();
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
  statesDropdown(dealerId) {
    this.reportService.getStates(dealerId).subscribe(response => {
      this.states = response.result;
      this.inventoryMovementReportForm.controls.state.reset();
    });
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

  searchData(){
    let searchobj = this.inventoryMovementReportForm.value;
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
    }else if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    if (Object.keys(searchobj).length>0) {
      
      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.reportService.getInventoryMovementReport(searchobj).subscribe(res => {
        if(!this.isKai){
          res['result'].forEach(response => delete res['result']['dealerName']);
          res['result'].forEach(response => delete res['result']['dealerCode']);
        }
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
        this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      });
    
    } else{
      this.toastr.error("Please fill atleast one input field.");
    }

    
    this.searchFlag = true;
  }


  generateReport(){
    let searchobj = this.inventoryMovementReportForm.value;

    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    if (Object.keys(searchobj).length>0) {
      
      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.reportService.exportInventoryMovementReport(searchobj).subscribe((resp: HttpResponse<Blob>) => {
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

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.inventoryMovementReportForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.reportService.getLevelByDepartment("SP").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.inventoryMovementReportForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.reportService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.inventoryMovementReportForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.reportService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  getDealerName(event){
    if (typeof event.option.value === 'object') {
      this.inventoryMovementReportForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.inventoryMovementReportForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  public fromDateChange(event: object) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.inventoryMovementReportForm.get('toDate').value > this.maxDate)
        this.inventoryMovementReportForm.get('toDate').patchValue(this.maxDate);
    }
  }

}
