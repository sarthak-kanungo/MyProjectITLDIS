import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput, MatDialog, MatDialogConfig } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { MrcSearchWebService } from 'src/app/service/pre-sales-service/machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
// import { ModalFileUploadComponent } from 'src/app/service/Utility/modal-file-upload/modal-file-upload.component';
import { ServiceClaimInvoiceSearchServiceService } from './service-claim-invoice-search-service.service';
import {saveAs} from 'file-saver'
// import { shareDataBetweenComponent } from 'src/app/service/Utility/modal-file-upload/modal-file-upload.service';
import { DealerAndKaiUploadInvoiceComponent } from '../dealer-and-kai-upload-invoice/dealer-and-kai-upload-invoice.component';
import { shareDataBetweenComponent } from '../dealer-and-kai-upload-invoice/dealer-kai-upload-service';
import { DatePipe } from '@angular/common';
import { DealerKaiInvoiceVerifyComponent } from '../dealer-kai-invoice-verify/dealer-kai-invoice-verify.component';
import { claimInvoicePresenter } from './service-claim-invoice-presenter';
import { claimInvoiceStore } from './service-claim-invoice-store';
// import { shareDataBetweenComponentService } from 'src/app/service/Utility/modal-file-upload/modal-file-upload.service';
@Component({
  selector: 'app-service-claim-invoice-search',
  templateUrl: './service-claim-invoice-search.component.html',
  styleUrls: ['./service-claim-invoice-search.component.scss'],
  providers : [ServiceClaimInvoiceSearchServiceService,claimInvoiceStore,claimInvoicePresenter,shareDataBetweenComponent,DatePipe,DealerAndKaiUploadInvoiceComponent, MrcSearchWebService, ServiceMonitoringBoardService]
})
export class ServiceClaimInvoiceSearchComponent implements OnInit {
  searchFilterValues;
  dealercodeList;
  invoiceList;
  autoActivityNo;
  claimList;
  pageHeader:string;
  invoiceSearchForm:FormGroup;
  dataTable
  clearSearchRow= new BehaviorSubject<string>("");
  totalTableElements
  searchValue
  page:number=0
  size:number=10
  searchFlag:boolean=true;
  key = 'searchFilterClaimInvoice';
  todayDate = new Date()
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  userType:string
  pageLoadCount:number=0
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  invoiceNumberNgModel;
  invoiceDateNgModel;
  claimNumberNgModel;
  claimDateNgModel;
  invoiceTypeNgModel;
  dealerNameNgModel;
  statusNgModel;
  isKai:boolean = false;
  invoiceNo: any;
  invoiceDate: any;
  sendinvoiceNo: any;
  sendInvoiceDate: any;

  constructor(private activatedRoute: ActivatedRoute,
    private matDialog: MatDialog,
    private router: Router,
    private fb: FormBuilder,
    private toastr : ToastrService,
    private searchTableService: NgswSearchTableService,
    private dateService: DateService,
    private loginService: LoginFormService,
    private searchService : ServiceClaimInvoiceSearchServiceService,
    private mrcSearchService: MrcSearchWebService,
    
     private pipe:DatePipe,
     private toaster:ToastrService,
     private presenter:claimInvoicePresenter,
     private store:claimInvoiceStore,
    private smbService: ServiceMonitoringBoardService) { 
      this.invoiceSearchForm = this.fb.group({
        invoiceNumber : [null],
        claimNumber : [null],
        fromDate : [null],
        toDate : [null],
        orgHierarchyId : [null],
        dealerName : [null],
        dealerCode : [null],
        invoiceType : [null],
        activityNumber : [null]
      })
      
      
    }
    
  

  ngOnInit() {
    
    const operation = this.router.url.split('/');
    if(operation[operation.length-1]=='service-claim-invoice'){
        this.pageHeader = 'Service Claim Invoice';
        this.key = 'service-claim-invoice-SK';
    } else if(operation[operation.length-1]=='service-activity-claim-invoice'){
        this.pageHeader = 'Service Activity Claim Invoice'; 
        this.key = 'service-activity-claim-invoice-SK';
    } else if(operation[operation.length-1]=='marketing-activity-claim-invoice'){
        this.pageHeader = 'Marketing Activity Claim Invoice';
        this.key = 'marketing-activity-claim-invoice-SK';
    }
    this.invoiceSearchForm.controls.invoiceType.patchValue(this.pageHeader);
    this.userType = this.loginService.getLoginUserType();
    let backDate = new Date();
    backDate.setMonth(this.todayDate.getMonth() - 1);
    this.minDate = backDate;
    

    if(this.userType!='dealer'){
      this.getLevelByDeprtment();
      this.isKai = true;
    }

    this.invoiceSearchForm.controls.dealerCode && this.invoiceSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
        })
      }
    })

    this.invoiceSearchForm.controls.invoiceNumber && this.invoiceSearchForm.controls.invoiceNumber.valueChanges.subscribe((res) => {
      if (res) {
        this.searchService.getInvoiceList(res, this.pageHeader).subscribe(response => {
          this.invoiceList = response;
        })
      }
    })

    this.invoiceSearchForm.controls.claimNumber && this.invoiceSearchForm.controls.claimNumber.valueChanges.subscribe((res) => {
      if (res) {
        this.searchService.getClaimList(res, this.pageHeader).subscribe(response => {
          this.claimList = response;
        })
      }
    })

    this.invoiceSearchForm.controls.activityNumber && this.invoiceSearchForm.controls.activityNumber.valueChanges.subscribe((res) => {
      if (res) {
        this.searchService.getActivityList(res, this.pageHeader).subscribe(response => {
          this.autoActivityNo = response;
        })
      }
    })

    this.searchFilterValues = localStorage.getItem(this.key);
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)));
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.invoiceSearchForm.patchValue(this.searchFilterValues)
    }

    this.invoiceSearchForm.get('toDate').patchValue(this.todayDate);
    this.invoiceSearchForm.get('fromDate').patchValue(backDate);

   
  }

  pageSizeChange(event){
    this.page = event.page;
    this.size = event.size
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchData();
    }
    this.pageLoadCount = 1;
   
  }
  
  searchData(){
      let searchObj = this.invoiceSearchForm.value;
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
  
      localStorage.setItem(this.key, JSON.stringify(searchObj));

      delete searchObj['page'];
      delete searchObj['size'];
      if(Object.keys(searchObj).length==0){
        this.toastr.error("Please fill atleast one input field");
        return;
      }
      searchObj['page'] = this.page;
      searchObj['size'] = this.size;
      this.searchFlag = true;
      this.searchService.getDealerInvoiceList(searchObj).subscribe(response => {
        if (response) {
          this.dataTable = this.searchTableService.convertIntoDataTable(response['result']);
          this.totalTableElements = response['count'];
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
      if (this.invoiceSearchForm.get('toDate').value > this.maxDate)
        this.invoiceSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  clearForm(){
    this.invoiceSearchForm.reset();
    this.dataTable = null;
    this.clearSearchRow.next("");
    this.invoiceNumberNgModel='';
    this.invoiceDateNgModel='';
    this.claimNumberNgModel='';
    this.claimDateNgModel='';
    this.invoiceTypeNgModel='';
    this.dealerNameNgModel='';
    this.statusNgModel='';
    this.invoiceSearchForm.controls.invoiceType.patchValue(this.pageHeader);
  }

  

  private openConfirmDialogVerification(invoiceId) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    
const loadDataWithDelay = () => {
  dialogConfig.data.invoiceNo = this.sendinvoiceNo;
  dialogConfig.data.invoiceDate = this.sendInvoiceDate;

  console.log(dialogConfig.data);
};
dialogConfig.data ={
  // invoiceNo:this.sendinvoiceNo,
  // invoiceDate:this.sendInvoiceDate
}
setTimeout(loadDataWithDelay, 1000); // 10000 milliseconds = 10 seconds
    const modalDialog = this.matDialog.open(DealerKaiInvoiceVerifyComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      
      this.invoiceNo=result.invoiceNo,
      this.invoiceDate=result.invoiceDate
      let updatedata = { 
        'invoiceId': invoiceId,
        'invoiceNo':this.invoiceNo,
         'invoiceDate':this.invoiceDate
     }
       console.log(updatedata,'updatedata')
      if(result.event === 'upload'){
        // let file:File = result.data;
        this.searchService.kaiVerifyInvoice
        (updatedata).subscribe(res=>{
          if (res.message) {
            this.toastr.success(res['message']);
            this.searchData();
          }else {
            this.toastr.error(res.message)
          }
        })
      }
    })
    // let message = `Do you want to save deatils as Verified?`;

    // const confirmationData = this.setConfirmationModalData(message);
    // const dialogRef = this.matDialog.open(ConfirmationBoxComponent, {
    //   width: "500px",
    //   panelClass: "confirmation_modal",
    //   data: confirmationData
    // });
    // dialogRef.afterClosed().subscribe(result => {
    //   if (result == "Confirm") {
    //     this.searchService.updateAsVerified(invoiceId).subscribe(response =>{
    //       this.toastr.success(response['message']);
    //       this.searchData();
    //     });
    //   }
    // });
  }
  // private setConfirmationModalData(message: string) {
  //   const confirmationData = {} as ConfirmDialogData;
  //   confirmationData.title = "Confirmation";
  //   confirmationData.message = message;
  //   confirmationData.buttonName = ["Confirm", "Cancel"];
  //   return confirmationData;
  // }

  tableAction(event){
    let id = btoa(event['record']['id'])
    if (event['btnAction'].toLowerCase() === 'invoicenumber') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      })
    }else if (event['btnAction'].toLowerCase() === 'action') {
      if(event['record']['action']=='Verify Invoice'){
        this.getInvoiceDetails(event['record']['id']);
        this.openConfirmDialogVerification(event['record']['id']);
      }else if(event['record']['action']=='Upload Invoice'){
        this.dealerinvoiceUpload(event['record']['id']);
      }
    }else if (event['btnAction'].toLowerCase() === 'kaiinvoiceupload') {
      this.invoiceUpload(event['record']['id']);
    }else if (event['btnAction'].toLowerCase() === 'invoice') {
      this.downloadInvoice(event['record']['id']);
    }
  }
  downloadInvoice(id){
    this.searchService.downloadInvoice(id).subscribe( (response: HttpResponse<Blob>) => {
      if(response){
        // console.log('response.headers--------------',response.headers)
        let headerContentDispostion = response.headers.get('Content-Disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([response.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }
  // for dealer
  dealerinvoiceUpload(id){
    const dialogConfig = new MatDialogConfig();
    // let updatedata ={'invoiceId':id}
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.matDialog.open(DealerAndKaiUploadInvoiceComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      this.invoiceNo=result.dataToSend?result.dataToSend:null,
      this.invoiceDate=result.invoiceDate?result.invoiceDate:null
      let updatedata = { 
        'invoiceId': id,
        'invoiceNo':this.invoiceNo,
         'invoiceDate':this.invoiceDate
     }
       
      if(result.event === 'upload'){
        let file:File = result.data;
        this.searchService.uploadFileForDealer(updatedata,file).subscribe(res=>{
          if (res.status == 200) {
            this.toastr.success(res['message']);
            this.searchData();
          }else {
            this.toastr.error(res.message)
          }
        })
      }
    })
  }
  // For kai
  invoiceUpload(id){
    const dialogConfig = new MatDialogConfig();
    let updatedata ={'invoiceId':id}
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.matDialog.open(DealerAndKaiUploadInvoiceComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      if(result.event === 'upload'){
        let file:File = result.data;
        this.searchService.uploadFile(updatedata,file).subscribe(res=>{
          if (res.status == 200) {
            this.toastr.success(res['message']);
            this.searchData();
          }else {
            this.toastr.error(res.message)
          }
        })
      }
    })
  }
  // get detail verfy invoice
  private getInvoiceDetails(wcrId){
    this.presenter.claimSearchForms.reset()
    this.searchService.getInvoiceDetail(wcrId).subscribe(res => {
    
      if(res['result'].invoiceNo !=null ||res['result'].invoiceDate !=null ){      
        this.presenter.claimSearchForms.patchValue({
          invoiceNo:res['result'].invoiceNo?res['result'].invoiceNo:null,
          invoiceDate:res['result'].invoiceDate?res['result'].invoiceDate:null
        })
        this.sendinvoiceNo=this.presenter.claimSearchForms.value.invoiceNo
        this.sendInvoiceDate=this.presenter.claimSearchForms.value.invoiceDate
        console.log(this.sendInvoiceDate,'this.sendInvoiceDate')
      }else{

        this.sendinvoiceNo=null,
        this.sendInvoiceDate=null
      console.warn("Some Server Side Error Or Invoice number Null")
      }
    }
    )
  }
  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.invoiceSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.mrcSearchService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.invoiceSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.invoiceSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
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
      this.invoiceSearchForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.invoiceSearchForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
}
