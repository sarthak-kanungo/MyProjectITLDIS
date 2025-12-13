import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ServiceActivityClaimSearchPageWebService } from '../../../service-activity-claim/component/service-activity-claim-search-page/service-activity-claim-search-page-web.service';
import { ServiceClaimService } from '../service-claim.service';
import {saveAs} from 'file-saver';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
@Component({
  selector: 'app-service-claim-search',
  templateUrl: './service-claim-search.component.html',
  styleUrls: ['./service-claim-search.component.scss'],
  providers: [ServiceClaimService, ServiceActivityClaimSearchPageWebService,JobCardSearchWebService]
})
export class ServiceClaimSearchComponent implements OnInit {
  
  public page = 0;
  public size = 10;
  public actionButtons = [];
  public dataTable: DataTable;
  public minToDate = new Date();
  public maxToDate = new Date();
  public todaysDate = new Date();
  public totalTableElements: number;
  private searchFlag: boolean = true;
  public searchValue: ColumnSearch = {} as ColumnSearch
  private searchFilterValues: any;

  totalClaimAmountNgModel=''
  claimAmountNgModel=''
  bonusAmountNgModel=''
  claimNoNgModel=''
  claimDateNgModel=''
  claimStatusNgModel=''
  fromDateNgModel=''
  toDateNgModel=''
  totalApprovedAmountNgModel=''
  lastApprovedByNgModel=''
  rsnNumberNgModel=''
  rsnDateNgModel=''
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter: any;
  claimStatusList : any[];
  todayDate = new Date()
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  claimNumberAutoList:[]
  userType:string
  loginUserType:any
  isAdvanceSearch:Boolean=false;
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number;
  kaiUser:boolean=false;
  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private dialog : MatDialog,
    private formBuilder : FormBuilder,
    private claimService: ServiceClaimService,
    private dateService : DateService,
    private toastr : ToastrService,
    private loginService : LoginFormService,
    private searchTableService: NgswSearchTableService,
    private hierService:JobCardSearchWebService,
    private serviceActivityClaimSearchPageWebService: ServiceActivityClaimSearchPageWebService,
    ) { 
      
      this.loginUserType = loginService.getLoginUserType();
    }

  claimSearchForm:FormGroup =  this.formBuilder.group({
    claimNumber : [null],
    claimStatus : [null],
    fromDate : [null],
    toDate : [null],
    jobcardNumber : [null],
    installationNumber : [null],
    orgHierarchyId:[null]
  })

  ngOnInit() {
    
    this.userType = this.loginService.getLoginUserType();
   console.log(this.userType,'useType')
   if(this.userType!=="dealer"){
    this.getLevelByDeprtment();
    this.kaiUser=true;
   }
   
    let backDate = new Date();
    backDate.setMonth(this.todayDate.getMonth() - 1);
    this.minDate = backDate;

    this.claimSearchForm.get('toDate').patchValue(this.todayDate);
    this.claimSearchForm.get('fromDate').patchValue(backDate);

    this.searchFilterValues = localStorage.getItem('searchFilterSC');
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)));
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.claimSearchForm.patchValue(this.searchFilterValues)
    }
    this.claimService.getLookup("CLAIM_STATUS").subscribe(response => {
      this.claimStatusList = response['result'];
    });
    this.claimSearchForm.get('claimNumber').valueChanges.subscribe(searchTxt => {
      if(searchTxt){
          this.claimService.getClaimNumber(searchTxt).subscribe(response => {
            this.claimNumberAutoList = response['result'];
          })
      }else{
        this.claimNumberAutoList=[];
      }
    })
    this.searchServiceClaim();
    
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
      if (this.claimSearchForm.get('toDate').value > this.maxDate)
        this.claimSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  createclaim(){
    this.router.navigate(['../create'], {
      relativeTo: this.activatedRoute
    })
  }

  onClickAdvanceSearch(event:MouseEvent){
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }


  public dateChanges(event, keyInObject: string) {
    if (event && event['value']) {
      const date: Date = event.value as Date
      const searchValue = {
        searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
        keyInObject
      };
      this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.keyInObject);
    } else {
      this.searchValue = new ColumnSearch("", keyInObject);
    }
  }
  
  public pageChange(event) {
    this.page = event.page;
    this.size = event.size
    this.searchFlag = false;
    this.searchServiceClaim();
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.claimSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.hierService.getOrgLevelByHODept("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.claimSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.hierService.getOrgLevelHierForParent(levelId, orgHierId+"").subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
     this.claimSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.hierService.getOrgLevelHierForParent(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  tableAction(event){
    let searchfilter = btoa(JSON.stringify(this.searchFilter))
    let id = btoa(event['record']['id'])
    if (event['btnAction'].toLowerCase() === 'claimno') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      })
    } else if (event['btnAction'].toLowerCase() === 'action') {
      if(event['record']['action']=='Approve'){
        this.router.navigate(['../approval'], {
          relativeTo: this.activatedRoute, queryParams: { id: id}
        })
      }
      if(event['record']['action'].toLowerCase()=='generate invoice'){
        this.openConfirmDialog(event.record.id);
      } 
    }

  }

   private openConfirmDialog(id): void | any {
    let message = 'Do you want to Generate Invoice?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('result---'+result)
      if (result && result === 'Confirm' ){
        this.generateInvoice(id);
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  searchServiceClaim(){
      let searchObj = this.claimSearchForm.value;
      let key = 'searchFilterSC';
      localStorage.setItem(key, JSON.stringify(this.claimSearchForm.value));

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

      if (searchObj['fromDate'] && searchObj['toDate']) {
        searchObj['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['fromDate']);
        searchObj['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchObj['toDate'])
      }else if (searchObj['fromDate'] || searchObj['toDate']) {
        this.toastr.error("Please Select Date Range");
        return;
      }
      
      Object.keys(searchObj).forEach(key => {
        ((searchObj[key] === null) || (searchObj[key] === '')) ? delete searchObj[key] : searchObj[key];
      });
  
      delete searchObj['page'];
      delete searchObj['size'];
      if(Object.keys(searchObj).length==0){
        this.toastr.error("Please fill atleast one input field");
        return;
      }
      if (Object.keys(searchObj).length>0) {
        if (!this.dateService.checkValidDatesInput(searchObj.fromDate, searchObj.toDate)) {
         this.toastr.error("Please Select Due Date Range.");
         return false;
       }
     }
     
      searchObj['page'] = this.page;
      searchObj['size'] = this.size;
     
      this.searchFlag = true;
      this.claimService.fetchClaimList(searchObj).subscribe(response => {
        if (response) {
          let result = response['result'];
          if(result && this.loginUserType.toLocaleLowerCase()=='dealer'){
            result.forEach(res => {
              delete res['dealerCode'];
              delete res['dealerName'];
              delete res['dealerState'];
            })
          }
          this.dataTable = this.searchTableService.convertIntoDataTable(result);
          this.totalTableElements = response['count'];
        }
      });
    }
  
  clearForm() {
    this.claimSearchForm.reset();
    this.dataTable = null;
    
    this.totalClaimAmountNgModel=''
    this.claimAmountNgModel=''
    this.bonusAmountNgModel=''
    this.claimNoNgModel=''
    this.claimDateNgModel=''
    this.claimStatusNgModel=''
    this.fromDateNgModel=''
    this.toDateNgModel=''
    this.totalApprovedAmountNgModel=''
    this.lastApprovedByNgModel=''
    this.clearSearchRow.next("");
  }

  generateInvoice(claimId:number){
    this.serviceActivityClaimSearchPageWebService.generateInvoice(claimId, 'ServiceClaim').subscribe(response =>{
      if(response){
        this.toastr.success(response['result']['msg']);
        if(response['result']['status']==='OK'){
          this.searchServiceClaim();
        }
      }else {
        this.toastr.error('Invoice Generation Failed');
      }
    })
  }
  downloadServiceClaimExcelReport(event){
    let formValues ={} as any
    const searchFormValues = this.claimSearchForm.getRawValue();
    console.log(searchFormValues,'se')
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    searchFormValues['fromDate'] = searchFormValues['fromDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['fromDate']):null;
    searchFormValues['toDate'] = searchFormValues['toDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['toDate']):null;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    if(this.searchFilter.dealerShip && typeof this.searchFilter.dealerShip == 'object'){
      searchFormValues.dealerId = searchFormValues.dealerShip.id;
      delete searchFormValues.dealerShip;
    }
     this.downloadExcel(searchFormValues)
  }
  downloadExcel(data){
if(this.claimSearchForm.get('claimNumber').value==null && this.claimSearchForm.get('claimStatus').value==null && this.claimSearchForm.get('fromDate').value==null  && this.claimSearchForm.get('toDate').value==null ){
      this.toastr.error("Please Select One Input Field")
    }else{
    this.claimService.downloadServiceClaimExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  }
}
