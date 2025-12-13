import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { DataTable } from 'projects/ngsw-search-table/src/public-api';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { MrcSearchWebService } from '../../pre-sales-service/machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ServiceMonitoringBoardService } from '../service-monitoring-board/service-monitoring-board.service';
import { FillRatioReportService } from './fill-ratio-report.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-fill-ratio-report',
  templateUrl: './fill-ratio-report.component.html',
  styleUrls: ['./fill-ratio-report.component.css'],
  providers:[FillRatioReportService,ServiceMonitoringBoardService,MrcSearchWebService]
})
export class FillRatioReportComponent implements OnInit {
  cminDate: Date;
  cmaxDate: Date;
  cnewdate= new Date();
  jminDate: Date;
  jmaxDate: Date;
  jnewdate= new Date();
  fillReportForm:FormGroup;
  key='FillRatioReport';
  searchFilterValues:any=null;
  actionButtons = [];
  totalTableElements: number;
  dataTable: DataTable;
  page: number = 0;
  size: number = 10;
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue:any
  isKai:boolean = true;
  dealercodeList: any;
  branches:any[] = [];
  searchFlag:boolean=true;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  pageLoadCount:number=0

  constructor(private fillReportService: FillRatioReportService,
    private formBuilder : FormBuilder,
    private tableDataService: NgswSearchTableService,
    private toastr: ToastrService,
    private dateService : DateService,
    private loginService : LoginFormService,
    private smbService: ServiceMonitoringBoardService,
    private mrcSearchService: MrcSearchWebService) { 
      
  }

  ngOnInit() {
  
    this.fillReportForm = this.formBuilder.group({
      cfromDate : [null],
      ctoDate: [null],
      jfromDate : [null],
      jtoDate: [null],
      dealerCode:[null],
      branch:[null],
      orgHierarchyId:[null]
    });
    let userType = this.loginService.getLoginUserType();
    if(userType=='dealer'){
      this.isKai = false;
    }else{
      this.getLevelByDeprtment();
    }
    
    this.getDateFromServer();
    this.getBranches(0);
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.fillReportForm != null) {
      this.fillReportForm.patchValue(this.searchFilterValues)
      // this.searchData();
    }
    else{
      this.cmaxDate = this.cnewdate
      this.jmaxDate = this.jnewdate
      let backDate = new Date();
      backDate.setMonth(this.cnewdate.getMonth() - 1);
      this.cminDate = backDate;
      this.jminDate = backDate;
      this.fillReportForm.get('cfromDate').patchValue(backDate);
      this.fillReportForm.get('ctoDate').patchValue(new Date());
      this.fillReportForm.get('jfromDate').patchValue(backDate);
      this.fillReportForm.get('jtoDate').patchValue(new Date());
      
      // this.searchData();
    }

    this.fillReportForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if(typeof res === 'object'){
           this.branches = this.branches.filter(obj => obj['dealercode']==res['code']);
           this.fillReportForm.controls.branch.reset();
          }
        })
      }else{
        // this.getBranches(0);
        this.fillReportForm.controls.branch.reset();
      }
    })
  }

  private getDateFromServer() {
    this.fillReportService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.cmaxDate = new Date(dateRes.result)
      }
    });
  }
  getBranches(dealerId){
    this.smbService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.fillReportForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      //this.statesDropdown(event.option.value.id);
      this.getBranches(event.option.value.id);
    }
  }
  setToDateC(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.cminDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.cnewdate)
        this.cmaxDate = this.cnewdate;
      else
        this.cmaxDate = maxDate;
      if (this.fillReportForm.get('ctoDate').value > this.cmaxDate)
        this.fillReportForm.get('ctoDate').patchValue(this.cmaxDate);
    }
  }
  setToDateJ(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.jminDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.jnewdate)
        this.jmaxDate = this.jnewdate;
      else
        this.jmaxDate = maxDate;
      if (this.fillReportForm.get('jtoDate').value > this.jmaxDate)
        this.fillReportForm.get('jtoDate').patchValue(this.jmaxDate);
    }
  }
  clearForm() {
    this.fillReportForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
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
        if (searchObject[element] && (element === 'cfromDate' || element === 'ctoDate' || element === 'jfromDate' || element === 'jtoDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }

  searchData(){
    let searchobj = this.fillReportForm.value;
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
      if (!this.dateService.checkValidDatesInput(searchobj.jfromDate, searchobj.jtoDate)) {
        this.toastr.error("Please Select Job Card Date Range.");
        return false;
      }
      if (!this.dateService.checkValidDatesInput(searchobj.cfromDate, searchobj.ctoDate)) {
        this.toastr.error("Please Select Counter Sales Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.fillReportService.searchRecords(searchobj).subscribe(res => {
        if(!this.isKai){
          res['result'].forEach(response => delete res['result']['dealerName']);
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


  clickExportToExcel(){
    let searchobj = this.fillReportForm.value;

    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.jfromDate, searchobj.jtoDate)) {
        this.toastr.error("Please Select Job Card Date Range.");
        return false;
      }
      if (!this.dateService.checkValidDatesInput(searchobj.cfromDate, searchobj.ctoDate)) {
        this.toastr.error("Please Select Counter Sales Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.fillReportService.exportSearchRecords(searchobj).subscribe((resp: HttpResponse<Blob>) => {
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
        this.fillReportForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SP").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.fillReportForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.fillReportForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
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
      this.fillReportForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.fillReportForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
}
