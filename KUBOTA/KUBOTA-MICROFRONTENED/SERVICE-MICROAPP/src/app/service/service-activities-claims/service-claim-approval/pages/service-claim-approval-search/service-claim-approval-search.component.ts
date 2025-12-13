import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDatepickerInput, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, NgswSearchTableService, } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ServiceClaimApprovalSearchService } from './service-claim-approval-search.service';

@Component({
  selector: 'app-service-claim-approval-search',
  templateUrl: './service-claim-approval-search.component.html',
  styleUrls: ['./service-claim-approval-search.component.scss'],
  providers: [ServiceClaimApprovalSearchService, NgswSearchTableService]
})
export class ServiceClaimApprovalSearchComponent implements OnInit {
  page:number=0;
  size:number=10;
  searchFlag:boolean = true;
  claimSearchForm:FormGroup;
  key:string="ConsolidatedClaimApproval";
  todayDate = new Date()
  minDate: Date;
  newdate= new Date();
  maxDate: Date;
  dataTable;
  clearSearchRow= new BehaviorSubject<string>("");
  totalTableElements:number;
  searchValue;
  isKai:boolean=true;
  documentNumberNgModel:string;
  documentDateNgModel:string;
  pageLoadCount:number=0
  statusNgModel:string;
  totalClaimCountNgModel:string;
  totalClaimAmountNgModel:string;
  docType:string;
  statusList:string[]=["Waiting For Approval","Approved","Rejected"];
  constructor(private formBuilder: FormBuilder,
    private dateService: DateService,
    private toastr: ToastrService,
    private searchService: ServiceClaimApprovalSearchService,
    private searchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginFormService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.claimSearchForm = this.formBuilder.group({
      fromDate : [null],
      toDate : [null],
      status: [null]
    });
    const operationType = this.router.url.split('/');
    if(operationType[operationType.length-1]=='service-claim-approval'){
        this.docType = 'Service Claim';
    } else if(operationType[operationType.length-1]=='wcr-claim-approval'){
      this.docType = 'WCR Claim';
    } else if(operationType[operationType.length-1]=='service-activity-claim-approval'){
      this.docType = 'Service Activity Claim';
    } else if(operationType[operationType.length-1]=='marketing-activity-claim-approval'){
      this.docType = 'Marketing Activity Claim';
    }

    if(this.loginService.getLoginUserType().toLowerCase()=='dealer'){
      this.isKai = false;
    }
    let backDate = new Date();
    backDate.setMonth(this.todayDate.getMonth() - 1);
    this.minDate = backDate;

    this.claimSearchForm.get('toDate').patchValue(this.todayDate);
    this.claimSearchForm.get('fromDate').patchValue(backDate);

    this.searchData();
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
      let searchObj = this.claimSearchForm.value;
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
      searchObj['claimType'] = this.docType;
      this.searchFlag = true;
      this.searchService.getClaimList(searchObj).subscribe(response => {
        // let data=response['result'];
        // data.forEach(response=>response.action="Approved")
        // console.log(data,'data')
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
      if (this.claimSearchForm.get('toDate').value > this.maxDate)
        this.claimSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }

  clearForm(){
    this.claimSearchForm.reset();
    this.dataTable = null;
    this.documentNumberNgModel='';
    this.documentDateNgModel='';
    this.statusNgModel='';
    this.totalClaimCountNgModel='';
    this.totalClaimAmountNgModel='';
    this.clearSearchRow.next("");
  }

  tableAction(event){
    let id = btoa(event['record']['id'])
    if (event['btnAction'].toLowerCase() === 'documentnumber') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      })
    }else if (event['btnAction'].toLowerCase() === 'action') {
      this.router.navigate(['../approval'], {
        relativeTo: this.activatedRoute, queryParams: { id: id}
      })
    }
    // else if (event['btnAction'].toLowerCase() === 'Verified') {
    //   this.router.navigate(['../view'], {
    //     relativeTo: this.activatedRoute, queryParams: { id: id}
    //   })
    // }
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
}
