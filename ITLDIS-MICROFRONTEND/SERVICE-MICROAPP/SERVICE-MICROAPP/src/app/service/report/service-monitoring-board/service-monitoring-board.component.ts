import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { MrcSearchWebService } from '../../pre-sales-service/machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ServiceMonitoringBoardService } from './service-monitoring-board.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-service-monitoring-board',
  templateUrl: './service-monitoring-board.component.html',
  styleUrls: ['./service-monitoring-board.component.css'],
  providers: [ServiceMonitoringBoardService, MrcSearchWebService]
})
export class ServiceMonitoringBoardComponent implements OnInit {
  smbSearchForm:FormGroup

  max: Date | null
  serverDate: Date;
  minDate: Date;
  maxDate: Date
  newdate= new Date()
  key = 'smb';
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
  pageLoadCount:number=0
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag:boolean = true;

  constructor(private fb: FormBuilder,
    private mrcSearchService: MrcSearchWebService,
    private smbService: ServiceMonitoringBoardService,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { 
    }

  ngOnInit() {
    this.smbSearchForm = this.fb.group({
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
      dealerName: [{value:null, disabled:true}]
    });
    //this.getDateFromServer();

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    
    this.maxDate = new Date('12-31-9999');
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;

    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.smbSearchForm != null) {
      this.smbSearchForm.patchValue(this.searchFilterValues)
      // this.searchData();
    }
    else{
      this.smbSearchForm.get('fromDate').patchValue(backDate);
      this.smbSearchForm.get('toDate').patchValue(new Date());
      // this.searchData();
    }
    this.getBranches(0);
   this.getChassisNumber()
    this.smbService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.smbSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        // if (typeof res === 'string') {
        //   this.smbSearchForm.controls.dealerName.patchValue('');
        // }
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if(typeof res === 'object'){
           this.branches = this.branches.filter(obj => obj['dealercode']==res['code']);
           this.smbSearchForm.controls.branch.reset();
          }
        })
      }else{
       // this.getBranches(0);
        this.smbSearchForm.controls.branch.reset();
      }
    })
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
      this.isKai = true;
    }
  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      //this.statesDropdown(event.option.value.id);
      this.getBranches(event.option.value.id);
    }
  }
  getBranches(dealerId){
    this.smbService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.smbSearchForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  getChassisNumber(){
    this.smbSearchForm.get('chassisNo').valueChanges.subscribe( txt => {
      if(txt){
      this.smbService.autoCompleteSearchChassisNo(txt).subscribe(res => {
        this.chassisData = res['result'];
      });
    }
    });
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
      if (this.smbSearchForm.get('toDate').value > this.maxDate)
        this.smbSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  dateSelectionChange(event: MatDatepickerInput<Date>) {
    this.smbSearchForm.get('toDate').setErrors(null);
    if (event && event['value']) {
      if (this.smbSearchForm.get('toDate').value < this.smbSearchForm.get('fromDate').value){
        this.smbSearchForm.get('toDate').setErrors({'invalidDateRange':'Please select valid date range'});
        this.smbSearchForm.get('toDate').markAsTouched();
      }
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
        this.smbSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.smbSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.smbSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
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
      this.smbSearchForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.smbSearchForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  searchData(){
    if( this.smbSearchForm.valid){
      let searchobj = this.smbSearchForm.value;
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

        localStorage.setItem(this.key, JSON.stringify(searchobj))

        searchobj.page = this.page
        searchobj.size = this.size
        if(searchobj['dealerCode']){
          searchobj.dealerId = searchobj.dealerCode.id;
        }
        searchobj.branchId = searchobj.branch;
        this.smbService.getSMBSearchRecords(searchobj).subscribe(res => {
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
    }
  }

  clearForm() {
    this.smbSearchForm.reset();
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

  tableAction(event){
    if (event['btnAction'].toLowerCase() === 'action') {
      this.router.navigate(['../../customerService/service-booking/create'], {
        relativeTo: this.activatedRoute, queryParams: { chassis: btoa(event.record['Chassis No']), servicetype: btoa(event.record['Current Service Due Type'])}
      })
    }
  }

  clickExportToExcel(){
    let searchobj = this.smbSearchForm.value;
   
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.smbService.exportSMBSearchRecords(searchobj).subscribe((resp: HttpResponse<Blob>) => {
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
