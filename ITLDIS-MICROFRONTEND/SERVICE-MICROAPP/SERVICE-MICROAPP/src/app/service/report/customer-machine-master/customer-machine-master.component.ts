import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { MrcSearchWebService } from '../../pre-sales-service/machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ServiceMonitoringBoardService } from '../service-monitoring-board/service-monitoring-board.service';
import { CustomerMachineMasterService } from './customer-machine-master.service';
import { saveAs } from 'file-saver';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-customer-machine-master',
  templateUrl: './customer-machine-master.component.html',
  styleUrls: ['./customer-machine-master.component.css'],
  providers:[ServiceMonitoringBoardService, MrcSearchWebService, CustomerMachineMasterService]
})
export class CustomerMachineMasterComponent implements OnInit {

  searchForm:FormGroup
  key = 'customerMachineReport';
  isKai:boolean=false;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  loginUser:any;
  searchFilterValues: any;
  searchobj:any
  dealercodeList: any;
  branches:any[] = [];
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag:boolean = true;
  customerList:any;
  pageLoadCount:number=0
  minDate: Date;
  maxDate: Date
  newdate= new Date()

  constructor(private fb: FormBuilder,
    private mrcSearchService:  MrcSearchWebService,
    private smbService: ServiceMonitoringBoardService,
    private customerMachineService: CustomerMachineMasterService,
    private tableDataService: NgswSearchTableService,
    private toastr: ToastrService,
    private dateService: DateService,) { 
      
    }

  ngOnInit() {
    this.searchForm = this.fb.group({
      customerId: [null],
      orgHierarchyId: [null],
      dealerCode: [null],
      branch: [null],
      fromDate:[null],
      toDate:[null],
      dealerName: [{value:null, disabled:true}]
    });
    this.searchobj = localStorage.getItem(this.key)
    this.searchobj = JSON.parse(JSON.parse(JSON.stringify(this.searchobj)))
    
    this.maxDate = new Date();
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;

    if (this.searchobj != null || this.searchobj != undefined && this.searchForm != null) {
      this.searchForm.patchValue(this.searchobj)
      // this.searchData();
    }
    else{
      this.searchForm.get('fromDate').patchValue(backDate);
      this.searchForm.get('toDate').patchValue(new Date());
      // this.searchData();
    }

    this.getBranches(0);
    this.searchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if(typeof res === 'object'){
           this.branches = this.branches.filter(obj => obj['dealercode']==res['code']);
           this.searchForm.controls.branch.reset();
          }
        })
      }else{
        this.searchForm.controls.branch.reset();
      }
    });
    
    this.searchForm.controls.customerId.valueChanges.subscribe((res) => {
      this.searchForm.controls.customerId.setErrors({'selectFromList':'Select From List'});
      if (res && typeof res == 'string') {
        this.customerMachineService.getMobileNoList(res).subscribe(response => {
          this.customerList = response['result'];
        })
      } else if(res && typeof res == 'object'){
        this.searchForm.controls.customerId.setErrors(null);
      }
    });

    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
      this.isKai = true;
    }
  }
  getBranches(dealerId){
    this.smbService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.searchForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      this.getBranches(event.option.value.id);
    }
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.searchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
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
      this.searchForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  displayCustomerMobileFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['mobileNumber'] : undefined;
  }

  searchData(){
    let searchobj = this.searchForm.value;
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
    
    this.searchFlag = true;
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }
    
    //if (Object.keys(searchobj).length>0) {

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      if(searchobj['customerId']){
        searchobj.customerId = searchobj['customerId']['id'];
      }
      searchobj.branchId = searchobj.branch;
      
      this.customerMachineService.getSearchList(searchobj).subscribe(res => {
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
  
    // } else{
    //   this.toastr.error("Please fill atleast one input field.");
    // }

  }
  

  

  clearForm() {
    this.searchForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  dateSelectionChange(event: MatDatepickerInput<Date>) {
    this.searchForm.get('toDate').setErrors(null);
    if (event && event['value']) {
      if (this.searchForm.get('toDate').value < this.searchForm.get('fromDate').value){
        this.searchForm.get('toDate').setErrors({'invalidDateRange':'Please select valid date range'});
        this.searchForm.get('toDate').markAsTouched();
      }
    }
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
      if (this.searchForm.get('toDate').value > this.maxDate)
        this.searchForm.get('fromDate').patchValue(this.maxDate);
    }
  }

  pageSizeChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchData();
    }
    this.pageLoadCount = 1;
  
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
 

  clickExportToExcel(){
    console.log(this.searchForm.value)
    if(this.searchForm.get('dealerCode').value ==null &&
    this.searchForm.get('branch').value ==null && this.searchForm.get('customerId').value ==null && this.searchForm.get('fromDate').value ==null && this.searchForm.get('toDate').value ==null && this.searchForm.get('AREA-SERVICE').value ==null &&this.searchForm.get('HO-SERVICE').value ==null&&this.searchForm.get('TERRITORY-SERVICE').value ==null&&this.searchForm.get('ZONE-SERVICE').value ==null
    ){
      this.toastr.error("Please fill atleast one input field.");
    }else{
    let searchobj = this.searchForm.value;
    
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    //if (Object.keys(searchobj).length>0) {
      
      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      if(searchobj['customerId']){
        searchobj.customerId = searchobj['customerId']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.customerMachineService.exportSearchList(searchobj).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
        
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    // }else{
    //   this.toastr.error("Please fill atleast one input field.");
    // }
  }
}
}

