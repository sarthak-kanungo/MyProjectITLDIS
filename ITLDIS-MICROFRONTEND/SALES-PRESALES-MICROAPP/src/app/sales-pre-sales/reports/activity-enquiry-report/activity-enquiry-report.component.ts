import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SalesReportService } from '../sales-report-service/sales-report.service';
import { ActivityEnquiryReportService } from './activity-enquiry-report.service';
import { saveAs } from 'file-saver';
import { MarketingActivitySearchContainerService } from '../../activity/activity-proposal/component/marketing-activity-search-container/marketing-activity-search-container.service';

@Component({
  selector: 'app-activity-enquiry-report',
  templateUrl: './activity-enquiry-report.component.html',
  styleUrls: ['./activity-enquiry-report.component.css'],
  providers:[ActivityEnquiryReportService, SalesReportService, MarketingActivitySearchContainerService]
})
export class ActivityEnquiryReportComponent implements OnInit {

  searchGroupForm:FormGroup;
  max: Date | null
  serverDate: Date;
  minDate: Date;
  maxDate: Date
  newdate= new Date()
  key = 'activityEnquiryReport';
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

  activityStatusList:any[]
  activityNumberList:any[]

  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag:boolean = true;

  constructor(private reportService : SalesReportService,
    private marketingActivitySearchContainerService: MarketingActivitySearchContainerService,
    private enquiryService: ActivityEnquiryReportService,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private toastr : ToastrService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.searchGroupForm = this.fb.group({
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
      activityNumber: [null],
      activityStatus: [null]
    });

    this.getDateFromServer();
    
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))

    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.searchGroupForm.get('fromDate').patchValue(backDate);
    this.searchGroupForm.get('toDate').patchValue(new Date());
    
    this.marketingActivitySearchContainerService.getSearchActivityStatus().subscribe(res => {
      this.activityStatusList = res['result'];
    })
    this.searchGroupForm.controls.activityNumber.valueChanges.subscribe(val => {
      if(val && typeof val == 'string'){        
        this.marketingActivitySearchContainerService.getActivityNo(val,'PROPOSAL').subscribe(response => {
          this.activityNumberList = response['result'];
        });
      }
    })
    

    this.getBranches();
    this.searchGroupForm.get('chassisNo').valueChanges.subscribe( txt => {
      this.reportService.autoCompleteSearchChassisNo(txt).subscribe(res => {
        this.chassisData = res['result'];
      });
    });
    this.reportService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.searchGroupForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.reportService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if(typeof res === 'object'){
            this.branches = this.branches.filter(obj => obj['dealercode']==res['code']);
            this.searchGroupForm.controls.branch.reset();
          }
        })
      }else{
        this.getBranches();
        this.searchGroupForm.controls.branch.reset();
      }
    })
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment('MK');
      this.isKai = true;
    }

    this.searchData();
  }
  getBranches(){
    this.reportService.getBranchCodeList().subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.searchGroupForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  private getDateFromServer() {
    this.reportService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes['result']) {
        this.maxDate = new Date(dateRes['result'])
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
      if (this.searchGroupForm.get('toDate').value > this.maxDate)
        this.searchGroupForm.get('toDate').patchValue(this.maxDate);
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

  getLevelByDeprtment(dept) {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchGroupForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.reportService.getLevelByDepartment(dept).subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchGroupForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.searchGroupForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.reportService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  searchData(){
    let searchobj = this.searchGroupForm.value;

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
        this.toastr.error("Please Select Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      searchobj.dealerId = searchobj.dealerCode?searchobj.dealerCode['dealerId']:null;
      searchobj.branchId = searchobj.branch?searchobj.branch:null;
      searchobj.orgHierId = this.orgHierarchyId;
      
      this.enquiryService.getSearchRecords(searchobj).subscribe(res => {
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
    this.searchGroupForm.reset();
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.getLevelByDeprtment('MK');
    this.dataTable = null;
    this.totalTableElements=0
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
        if (searchObject[element] && element === 'activityNumber') {
          searchObject[element] = searchObject[element]['activityNumber']
        }
      });
      return searchObject;
    }
  }

  activityEnquiryExcelReport(event){
    const searchFormValues = this.searchGroupForm.getRawValue();
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    let searchObject = this.removeNullFromObjectAndFormatDate(searchFormValues);
    this.downloadExcel(searchObject)
  }

  downloadExcel(data){
    this.enquiryService.downloadReport(data).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }

  displayActivityNumberFn(selectedOption){
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['activityNumber'] : undefined;
  }
}
