import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BackOrderService } from '../../service/back-order.service';
import { BehaviorSubject } from 'rxjs';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { searchBackOrderCancellation } from './search-block-order-cancellation-domain';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-search-back-order-cancellation',
  templateUrl: './search-back-order-cancellation.component.html',
  styleUrls: ['./search-back-order-cancellation.component.css'],
  
})
export class SearchBackOrderCancellationComponent implements OnInit {
  searchOrderPlanningSheet:FormGroup
  bocList: any;
  dealerList: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  page:number=0
  size:number=0
  searchFlag:boolean=false
  dataTable: DataTable;
  totalTableElements: number=0;
  totalSearchRecordCount:number=0
  actionButtons
  searchValue: ColumnSearch;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter:any
  loginUser:any;
  bocNoNgModel:''
  dealerNameNgModel:''
  dealerCodeNgModel:''
  reqGivenByNgModel:''
  statusNgModel:''
  constructor(
    private fb:FormBuilder,
    private service:BackOrderService,
    private router:Router,
    private toaster:ToastrService,
    private activatedRoute:ActivatedRoute,
    private tableDataService:NgswSearchTableService,
    private loginFormService: LoginFormService,
    ) { }

  ngOnInit() {
    this.searchOrderPlanningSheet=this.fb.group({
      dealerCode:[{value:null,disabled:false}],
      requestStatus:[{value:null,disabled:false}],
      bocNo:[{value:null,disabled:false}],
      fromDate:[{value:null,disabled:false}],
      toDate:[{value:null,disabled:false}]
    })
    if (this.searchOrderPlanningSheet.get('bocNo').value==null || this.searchOrderPlanningSheet.get('dealerCode').value==null || this.searchOrderPlanningSheet.get('fromDate').value==null || this.searchOrderPlanningSheet.get('toDate').value==null) {
     
      this.maxDate = this.today
      let backDate = new Date();
       backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.searchOrderPlanningSheet.get('fromDate').patchValue(backDate);
      this.searchOrderPlanningSheet.get('toDate').patchValue(new Date());
     
     
    }
    this.formValueChanges()
    this.loginUser = this.loginFormService.getLoginUserType()
  }


  private formValueChanges() {
    this.searchOrderPlanningSheet.get('bocNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoGetBOCNo(val);
      }
    });

    this.searchOrderPlanningSheet.get('dealerCode').valueChanges.subscribe(val => {
      if (val) {
        this.getDealercode(val);
      }
    });
  }
  private autoGetBOCNo(txt: string) {
    this.service.autoGenerateBOC(txt).subscribe(res => {
      this.bocList = res;
      console.log(this.bocList,'boclist')
    });
  }

  private getDealercode(txt: string) {
    this.service.searchDealercode(txt).subscribe(res => {
      this.dealerList = res;
    });
  }

  searchData(){
    if (this.dataTable != undefined || this.dataTable != null) {
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
    else {
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;

     const filterObj = this.searchOrderPlanningSheet.value as searchBackOrderCancellation
    filterObj.page = this.page
    filterObj.size = this.size
  
       filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
       filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null
   
  
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchOrderPlanningSheet.get('bocNo').value || this.searchOrderPlanningSheet.get('dealerCode').value || this.searchOrderPlanningSheet.get('fromDate').value || this.searchOrderPlanningSheet.get('toDate').value) {
        this.searchIssueForm(filterObj);
      }
      
      else if (this.searchOrderPlanningSheet.get('fromDate').value === null && this.searchOrderPlanningSheet.get('toDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");
        
      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  private searchIssueForm(backData:searchBackOrderCancellation){
    this.service.searchBOC(backData).subscribe(res => {
      let result=res['result']
      if(this.loginUser==='kubota'){
        result.forEach(res => {   
          delete res['action'],
          delete res['approve']
        })
        }else{
          result.forEach(res => {   
            delete res['approve']
          })
        }
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });

  }
checkValidDatesInput() {
    const fltEnqObj = this.searchOrderPlanningSheet.value

     fltEnqObj.fromDate = this.searchOrderPlanningSheet.getRawValue() ? this.searchOrderPlanningSheet.value.fromDate : null
     fltEnqObj.toDate = this.searchOrderPlanningSheet.getRawValue() ? this.searchOrderPlanningSheet.value.toDate : null

    // console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
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
  actionOnTableRecord(event:any){
    console.log(event.btnAction)
    if(event.btnAction==='bocNo'){
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { bocNo: event['record']['bocNo'] } });
    }
    if(event.btnAction==='action'){
      this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { bocNo: event['record']['bocNo'] } });
    }

  }
  pageLoadCount:number=0
  pageChange(event:any){
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    if(this.pageLoadCount > 0){
     this.searchData()
    }
    this.pageLoadCount = 1;

  }

  clearForm(){
    this.searchOrderPlanningSheet.reset()
    this.dataTable=null
  }

  onUrlChange(event:any){

  }
  initialQueryParams(event:any){

  }
  fromDateSelected(event:any){
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchOrderPlanningSheet.get('toDate').value > this.maxDate)
        this.searchOrderPlanningSheet.get('toDate').patchValue(this.maxDate);
    }
  }

}




 
