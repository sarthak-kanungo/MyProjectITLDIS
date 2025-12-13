import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { privateDecrypt } from 'crypto';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { IFrameService } from 'src/app/root-service/iFrame.service';
import { SearchJobCardWebService } from 'src/app/service/customer-service/job-card/component/search-job-card-page/search-job-card-web.service';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
import { CustomValidators } from 'src/app/utils/custom-validators';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { TollFreeService } from '../../../toll-free/service/toll-free.service';

@Component({
  selector: 'app-service-jobcard-details',
  templateUrl: './service-jobcard-details.component.html',
  styleUrls: ['./service-jobcard-details.component.css'],
  providers:[SearchJobCardWebService,JobCardSearchWebService]
})
export class ServiceJobcardDetailsComponent implements OnInit {
  jobCardSearchForm: FormGroup

  @Output() notifyTicketBook = new EventEmitter<boolean>();
  @Output() showAllForm=new EventEmitter<boolean>();
  @Output() sendChassisNo=new EventEmitter<any>();
  @Input() typeOfCallId:number
  @Output() jcId=new EventEmitter<any>()
  @Output() historyData=new EventEmitter<any>();
  @Output() serviceCustomerDetails=new EventEmitter<any>();
  @Output() serviceMachineDetails=new EventEmitter<any>();
  @Input() serviceJobCardId:number
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  public totalTableElements: number;
  searchValue: ColumnSearch;
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key = "jobcardpage";
  pageLoadCount:number=0
  showPostServiceFeedback: boolean;
 
  mobileNo:any
  callDataTable: any;
  getchassisNo: any;
  customerMobileList: any;
  chassisData: any;
  customerNameList: any;
  dueTypeList: any;
  customerCode: any;
  
 
 
  constructor(
    private ngswSearchTableService: NgswSearchTableService,
    private service:DelearCustomerCareExCallService,
    private fb:FormBuilder,
    private dealerCceService:DelearCustomerCareExCallService,
    private tollFreeService:TollFreeService,
    private jobCardSearchWebService:JobCardSearchWebService,
    private presenter:DelearCustomerCareExCallPagePresenter,
    private toaster:ToastrService
  ) { }

  ngOnInit() {
    this.jobCardSearchForm=this.fb.group({
      chassisNo:[{value:null,disabled:false}],
      mobileNo:[{value:null,disabled:false},Validators.compose([CustomValidators.mobileNumber])],
      customerName:[{value:null,disabled:false}],
      currentServiceDueType:[{value:null,disabled:false}]
    })
    this.searchJobCard()
    this.loadSearchFields()
   
  }
  pageSizeChange(event){
    
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchJobCard()
    }
    this.pageLoadCount = 1;
    
    
  }
  searchJobCard() {
     
      
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
    }
    
    let searchObj = this.jobCardSearchForm.getRawValue();
    searchObj.page = this.page;
    searchObj.size = this.size
      this.service.searchJobCardList(searchObj).subscribe(res => {
      
        this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
        this.dataTable = null;
        this.totalTableElements = 0;
      });     
       


  }
  scrollToBottom() {
    setTimeout(() => {
      document.getElementById('serviceJobCardId').scrollIntoView({ behavior: 'smooth' })
    }, 1000);

  }
  tableAction(event:any){
    if(this.presenter.callDetailsForm.value.crmCallStatus==null || this.presenter.callDetailsForm.value.crmCallStatus==''){
      this.toaster.warning("Please Select Call Status First!")
      this.presenter.callDetailsForm.markAllAsTouched();
      return false;
    }
    this.scrollToBottom()
    this.getchassisNo=event.record.chassisNo
    this.customerCode=event.record.customerCode
    
   if(event.record.edit==="Post service Feedback"){
   
    this.presenter.postServiceFeedBackForm['controls'].forEach(element=>{
      element.get('remarks').setValidators(null);
    })
    // this.presenter.postServiceFeedBackForm.reset();
    this.presenter.postServiceFeedBackForm.value.forEach(ele=>{
      
    if(ele.rating===null || ele.rating==='' && ele.remarks===null || ele.remarks===''){
      this.presenter.postServiceFeedBackForm['controls'].forEach(ele=>{
       
        
         ele.get('remarks').setValidators(null)
      })
    }else{
      // ele.rating.clear()
      
      // ele.remarks.clear()
      this.presenter.postServiceFeedBackForm['controls'].forEach(ele=>{
       
        ele.get('rating').reset();
         ele.get('remarks').reset()
      })
    }
    })
    // console.log(this.presenter.postServiceFeedBackForm,'service feedback')
    this.showAllForm.emit(true)
    this.jcId.emit(event.record.id);
    
    this.callHistoryTable(this.getchassisNo,this.typeOfCallId)
    this.getServiceCustomerDetails(this.customerCode)
    // this.callApiForCustomerMobileNumber(this.mobileNo)
     
  }else{
    this.showAllForm.emit(false)
  }
}
callHistoryTable(chassisNo,typeOfCallId) {
  this.dealerCceService.getCCECallHistory(this.getchassisNo,this.typeOfCallId).subscribe(res => {
      
         this.historyData.emit(res['result']);
  }) 
}
// callApiForCustomerMobileNumber(mobileNo){
//   this.tollFreeService.getAutoCompleteMobileNumber(this.mobileNo,'').subscribe(res=>{
//     if(res){
//       if(res['result'].length>0){
    
//        const custCode=res['result'][0].oldCustomerCode;
//        console.log(custCode,'code')
//        const dealerId=res['result'][0].id;
       
//        this.getServiceCustomerMachineDetails(dealerId) 
//        this.getServiceCustomerDetails(custCode)
 
//       }else{
//        console.log('sls')
//        this.presenter.customerDetailsForm.reset();
//        this.presenter.machineDetailsForm.reset();
//       }
//   }
// })
// }

getServiceCustomerDetails(code){
  this.tollFreeService.getCustomerDtl(code).subscribe(res=>{
    if(res){
      const id=res['result'].customerMasterId
      this.getServiceCustomerMachineDetails(id)
    // this.customerDetails=res['result'];
    this.serviceCustomerDetails.emit(res['result'])
    
    }

  })

}
getServiceCustomerMachineDetails(id){
  this.tollFreeService.getMachineDtl(id).subscribe(res=>{
   if(res){
    console.log(res,'res')
   this.serviceMachineDetails.emit(res['result'])
  //  console.log(this.serviceMachineDetails,'jnjemit')
   }
  })

}


loadSearchFields() {
    
  this.jobCardSearchForm.get('chassisNo').valueChanges.subscribe( txt => {
    if(txt){
    this.dealerCceService.autoSearchChassisNumber(txt).subscribe(res => {
      this.chassisData = res['result'];
     
    });
  }
  });   
  
  this.jobCardSearchForm.get('mobileNo').valueChanges.subscribe( number => {
    if(number){
      this.dealerCceService.getAutoCompletesSBMobileNumber(number).subscribe(res => {
         this.customerMobileList = res['result']
      })
    }
  })

  this.jobCardSearchForm.get('customerName').valueChanges.subscribe( text => {
    if(text){
      this.dealerCceService.autoSBSearchCustomerName(text).subscribe(res => {
        this.customerNameList = res['result']
      })
    }
  })

  this.jobCardSearchForm.get('currentServiceDueType').valueChanges.subscribe( text => {
    if(text){
      this.dealerCceService.autoCompleteServiceDueType(text).subscribe(res => {
       this.dueTypeList = res['result']
      })
    }
  })

}

searchTable(){
  this.searchJobCard()
}
clearSearchFields(){
  this.dataTable=null
  this.jobCardSearchForm.reset()
  
}


}
