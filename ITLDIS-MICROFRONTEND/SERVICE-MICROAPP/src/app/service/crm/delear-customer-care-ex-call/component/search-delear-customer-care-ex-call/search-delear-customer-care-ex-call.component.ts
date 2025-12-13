import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { SearchDelearCustomerCareExCallStore } from './search-dealer-customer-care-ex-call-store';
import { SearchDelearCustomerCareExCallPresenter } from './search-dealer-customer-care-ex-call.presenter';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-search-delear-customer-care-ex-call',
  templateUrl: './search-delear-customer-care-ex-call.component.html',
  styleUrls: ['./search-delear-customer-care-ex-call.component.css'],
  providers:[SearchDelearCustomerCareExCallStore, SearchDelearCustomerCareExCallPresenter, DelearCustomerCareExCallService]
})
export class SearchDelearCustomerCareExCallComponent implements OnInit {
  callSearchForm:FormGroup
  actionButtons= [];
  newdate = new Date()
  fromDate: Date
  toDate: Date
  totalTableElements:number
  searchFlag:boolean=true;
  page:number = 0;
  size:number = 10;
  dataTable:DataTable
  searchValue:ColumnSearch
  customerNameNgModel:any="";
  mobileNoNgModel:any="";
  callDateNgModel:any="";
  callNoNgModel:any="";
  clearSearchRow = new BehaviorSubject<string>("");
  minDate: Date;
  // newdate = new Date()
  maxDate: Date
  callList: any;
  callStatusList: any;
  id: string;
  callTypeNgModel: string;
  callStatusNgModel: string;
  pageLoadCount:number=0
  customerMobileList: any;
  customerName: any;
  customerNameList: any;
  constructor(private presenter: SearchDelearCustomerCareExCallPresenter,
    private service: DelearCustomerCareExCallService,
    private tableDataService: NgswSearchTableService,
    private router : Router,
    private route: ActivatedRoute,
    private dateService: DateService,
   private dealerCceService:DelearCustomerCareExCallService,
    private tostr: ToastrService) { }

  ngOnInit() {
    this.callSearchForm = this.presenter.callSearchForm;
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.callSearchForm.get('fromDate').patchValue(backDate);
    this.callSearchForm.get('toDate').patchValue(new Date());
    // this.searchForm();
    this.getCallTypeList()
    this.getCallStatus()
    this.loadAllDropDown()
  }

  loadAllDropDown(){
    this.callSearchForm.get('mobileNo').valueChanges.subscribe( number => {
      if(number){
        this.dealerCceService.autoCompleteMobileNo(number).subscribe(res => {
           this.customerMobileList = res['result']
        })
      }
    })
    this.callSearchForm.get('customerName').valueChanges.subscribe( number => {
      if(number){
        this.dealerCceService.autoCompleteCustomerName(number).subscribe(res => {
           this.customerNameList = res['result']
        })
      }
    })
  }
  searchForm(){
   if(this.callSearchForm.valid){
    console.log(this.callSearchForm)
    this.customerNameNgModel="";
    this.mobileNoNgModel="";
    this.callDateNgModel="";
    this.callNoNgModel="";
    this.callTypeNgModel="";
    this.callStatusNgModel=""

    if(this.dataTable!=undefined)
    {
      if(this.searchFlag==true)
      {
        this.page=0;
        this.size=10;
        this.dataTable['PageIndex']=this.page    
        this.dataTable['PageSize']=this.size
      }
      else{
        this.dataTable['PageIndex']=this.page    
        this.dataTable['PageSize']=this.size
      }
    }
    let searchObj = this.callSearchForm.getRawValue();
    searchObj = this.removeNullFromObjectAndFormatDate(searchObj);
    searchObj.page = this.page;
    searchObj.size = this.size
    
    if (this.checkValidDatesInput()) {
      if (this.callSearchForm.get('fromDate').value || this.callSearchForm.get('toDate').value || this.callSearchForm.get('mobileNo').value|| this.callSearchForm.get('customerName').value || this.callSearchForm.get('callType').value ||this.callSearchForm.get('callStatus').value) {        
        this.service.searchCallDetails(searchObj).subscribe(response => {
          
          let data=response['result']
          data.forEach(ele=>{
           
             delete ele.callTypeId
          })
          this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
          this.totalTableElements = response['count']
        })
      }else {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
    this.searchFlag=true;
  }
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

  clearForm(){
    this.callSearchForm.reset();
 
    this.clearSearchRow.next("");
    this.dataTable=null
  }

  getCallTypeList() {
    this.service.getCallTypeList().subscribe(res => {
      if(res){
      this.callList = res['result']
      }
    }) 
  }

  getCallStatus(){
    this.service.getCallStatusList().subscribe(res=>{
      if(res){
        this.callStatusList=res['result']
      }
    })
  }
  pageChange(event){
    this.page = event.page
    this.size = event.size
    this.searchFlag=false;
    if(this.pageLoadCount > 0){
      this.searchForm()
    }
    this.pageLoadCount = 1;
    
  }

  tableAction(recordData: any) {
    
    if (recordData.btnAction.toLowerCase() === 'callno' && recordData.record.callType==='Sales Enquiry') {
      this.id='1';
//           const encodedCallNo = encodeURIComponent(recordData['record']['callNo']);
//           console.log(encodedCallNo,'callNO')
//           const encodedId = encodeURIComponent(this.id);
//           console.log(encodedId,'id')
// this.router.navigate(['view', encodedCallNo, encodedId], { relativeTo: this.route });
      // this.router.navigate(['../edit', btoa(recordData['record']['callNo'],this.id)], { relativeTo: this.route });
      this.router.navigate(['view'], { relativeTo: this.route, queryParams:{callNo:recordData.record.callNo ,id:this.id}})
    }
    if (recordData.btnAction.toLowerCase() === 'callno' && recordData.record.callType==='Service Booking') {
      this.id='2';
      this.router.navigate(['view'], { relativeTo: this.route, queryParams:{callNo:recordData.record.callNo ,id:this.id}})
    }
    if (recordData.btnAction.toLowerCase() === 'callno' && recordData.record.callType==='Post Service Feedback') {
      this.id='3';
      this.router.navigate(['view'], { relativeTo: this.route, queryParams:{callNo:recordData.record.callNo ,id:this.id}})
    }
    if (recordData.btnAction.toLowerCase() === 'callno' && recordData.record.callType==='Post Sales Feedback') {
      this.id='4';
      this.router.navigate(['view'], { relativeTo: this.route, queryParams:{callNo:recordData.record.callNo ,id:this.id}})
    }
    
    
  }
  checkValidDatesInput() {
    const fltEnqObj = this.callSearchForm.value

    fltEnqObj.fromDate = this.callSearchForm.getRawValue() ? this.callSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.callSearchForm.getRawValue() ? this.callSearchForm.value.toDate : null

    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }
    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }
  }

  onKeyPressAllowCharsOnly(event){
   this.allowCharactersOnly(event)
  }
  allowCharactersOnly(event: KeyboardEvent) {
    const pattern = /[a-zA-Z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
}
setToDate(event: MatDatepickerInput<Date>) {
  if (event && event['value']) {
    this.fromDate = new Date(event['value']);
    let maxDate = new Date(event['value']);
    maxDate.setMonth(maxDate.getMonth() + 1);
    if (maxDate > this.newdate)
      this.toDate = this.newdate;
    else
      this.toDate = maxDate;
    if (this.callSearchForm.get('toDate').value > this.toDate)
      this.callSearchForm.get('toDate').patchValue(this.toDate);
  }
}
}
