import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { TollFreeService } from '../../../toll-free/service/toll-free.service';


@Component({
  selector: 'app-service-job-card-post-sales',
  templateUrl: './service-job-card-post-sales.component.html',
  styleUrls: ['./service-job-card-post-sales.component.css'],
  providers:[ServiceMonitoringBoardService]
})
export class ServiceJobCardPostSalesComponent implements OnInit {
  jobCardSearchForm:FormGroup
  @Output() dcId=new EventEmitter<any>();
  @Output() showSalesForm=new EventEmitter<boolean>();
  @Output() customerDetails=new EventEmitter<any>();
  @Output() machineDetails=new EventEmitter<any>();
  // @Output() getchassisNo=new EventEmitter<any>();
  @Output() historyDataforSales=new EventEmitter<any>();
  @Input() typeOfCallId:any
  @Input() salesJobCardId:any
  customerMobileList: any;
  customerNameList: any;
  dueTypeList: any;
  chassisData: any;
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
  getchassisNo: any;
  loadCount:number=0
  mobileNo:any
  oldCustomerCode: any;
  customerCode: any;
  
    constructor(
    private fb:FormBuilder,
    private dealerCceService:DelearCustomerCareExCallService,
    private smbService:ServiceMonitoringBoardService,
   private ngswSearchTableService:NgswSearchTableService,
   private presenter:DelearCustomerCareExCallPagePresenter,
   private tollFreeService:TollFreeService
    ) { }

  ngOnInit() {
    this.jobCardSearchForm=this.fb.group({
      chassisNumber:[{value:null,disabled:false}],
      customerMobileNumber:[{value:null,disabled:false}],
      customerName:[{value:null,disabled:false}],
      serviceDueType:[{value:null,disabled:false}]
    })
    this.loadSearchFields()
    this.searchDcList()
  }

  searchDcList() {
      
      
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
    // searchObj = this.removeNullFromObjectAndFormatDate(searchObj);
    searchObj.page = this.page;
    searchObj.size = this.size

      this.dealerCceService.getDcList(searchObj).subscribe(res => {
              let data=res['result']
              console.log(data,'ddd')
              data.forEach(ele=>{
                console.log(ele,'eleee')
                ele.Action='Post Sales FeedBack'
              })
              data.forEach(cancel=>cancel='Post Sales Feed Back')
        this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);

        this.totalTableElements = res['count'];
      }, err => {
        this.dataTable = null;
        this.totalTableElements = 0;
      });     
       


  }
  pageSizeChange(event){
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;

    if(this.pageLoadCount > 0){
      this.searchDcList()
    }
    this.pageLoadCount = 1;
   
  }

  loadSearchFields() {
    
    this.jobCardSearchForm.get('chassisNumber').valueChanges.subscribe( txt => {
      if(txt){
      this.dealerCceService.autoSearchChassisNumber(txt).subscribe(res => {
        this.chassisData = res['result'];
      });
    }
    });   
    
    this.jobCardSearchForm.get('customerMobileNumber').valueChanges.subscribe( number => {
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
  
    this.jobCardSearchForm.get('serviceDueType').valueChanges.subscribe( text => {
      if(text){
        this.dealerCceService.autoCompleteServiceDueType(text).subscribe(res => {
         this.dueTypeList = res['result']
        })
      }
    })
  
  }
  clearSearchFields(){
    this.jobCardSearchForm.reset()
    this.dataTable=null
  }

  searchTable(){
    this.searchDcList()
  }
  scrollToBottom() {
    setTimeout(() => {
      document.getElementById('salesJobCardId').scrollIntoView({ behavior: 'smooth' })
    }, 1000);

  }
  tableAction(event:any){
    // if(event.record.mobileNumber!=null || event.record.mobileNumber!=''){
      this.mobileNo=event.record.mobileNumber
      this.customerCode=event.record.customerCode
    
      
    
 
    this.scrollToBottom()
    if(event.record.Action=="Post Sales FeedBack"){
      this.showSalesForm.emit(true)
     this.dcId.emit(event.record.id);
     this.getchassisNo=event.record.chassisNumber
     this.presenter.postSalesFeedBackForm.value.forEach(ele=>{
      
      if(ele.rating===null || ele.rating==='' && ele.remarks===null || ele.remarks===''){
        
      }else{
        this.presenter.postSalesFeedBackForm['controls'].forEach(ele=>{
          ele.get('rating').reset();
           ele.get('remarks').reset();
           ele.get('remarks').clearValidators();
            ele.get('remarks').updateValueAndValidity();
  
        })
      }
        
      })
     
     this.callHistoryTable(this.getchassisNo,this.typeOfCallId)
      this.getCustomerDetails(this.customerCode)
   }else{
    this.showSalesForm.emit(false)
   }
 
  }

  callHistoryTable(chassisNo,typeOfCallId) {
    this.dealerCceService.getCCECallHistory(this.getchassisNo,this.typeOfCallId).subscribe(res => {
           this.historyDataforSales.emit(res['result']);
    })  
  }

  callApiForCustomerMobileNumber(mobileNo){
    // this.tollFreeService.getAutoCompleteMobileNumber(this.mobileNo,'').subscribe(res=>{
    //  if(res){
    //  if(res['result'].length>0){
  
    //   const custCode=res['result'][0].oldCustomerCode;
    //   const dealerId=res['result'][0].id;
    //   this.getCustomerMachineDetails(dealerId) 
    //   this.getCustomerDetails(custCode)

    //  }else{
      
    //   this.presenter.customerDetailsForm.reset();
    //   this.presenter.machineDetailsForm.reset();
    //  }
    //   } 
    //   }
    // )
  }

  getCustomerDetails(code){
    this.tollFreeService.getCustomerDtl(code).subscribe(res=>{
      if(res){
        const id=res['result'].customerMasterId
      this.customerDetails.emit(res['result'])
      this.getCustomerMachineDetails(id)
      }

    })

  }
  getCustomerMachineDetails(id){
    this.tollFreeService.getMachineDtl(id).subscribe(res=>{
     if(res){
     this.machineDetails.emit(res['result'])
    
     }
    })

  }



  

}
