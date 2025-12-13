import { Component, OnInit } from '@angular/core';
import { SpareDescripancyClaimStore } from '../store-presenter/spare-descripancy-claim-store';
import { SpareDescripancyClaimPresenter } from '../store-presenter/spare-descripancy-claim-presenter';
import { FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable } from 'ngsw-search-table';
import { BehaviorSubject, Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { SpareClaimService } from '../service/spare-claim.service';
import { DateService } from 'src/app/root-service/date.service';
import { TableDataService } from 'src/app/ui/dynamic-table/table-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-search-spare-descripancy-claim',
  templateUrl: './search-spare-descripancy-claim.component.html',
  styleUrls: ['./search-spare-descripancy-claim.component.css'],
  providers:[SpareDescripancyClaimPresenter,SpareDescripancyClaimStore,TableDataService,DatePipe]
})
export class SearchSpareDescripancyClaimComponent implements OnInit {
  searchSpareDescripancyClaimForm:FormGroup
  dataTable:DataTable
  totalTableElements:number=0;
  clickOnTableFields:any
  searchValue: ColumnSearch;
  actionButtons
  page: number = 0
  size: number = 10
  // public blockList:blockList[]
  public filteredItemNumberList:[];
  public blockList: Observable<(string | object)[]>;
  searchFlag:boolean=false
  list:FormGroup
  clearSearchRow = new BehaviorSubject<string>("");
  minDate=new Date;
  maxDate=new Date;
  statusList:any;
  claimNoList:any;
  recordData:any
  claimNo:any;
  userType:any;
  grnNoList:any;
  constructor(
    private presenter:SpareDescripancyClaimPresenter,
    private store:SpareDescripancyClaimStore,
    private toastr:ToastrService,
    private service:SpareClaimService,
    private dateService:DateService,
    private tableDataService:TableDataService,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private localStorageService:LocalStorageService,
    public datepipe: DatePipe
  ) { }

  ngOnInit() {
    this.searchSpareDescripancyClaimForm=this.presenter.searchSpareDesClaimForm;
  this.getStatus();
  this.userType=this.localStorageService.getLoginUser();
  console.log(this.userType.userType,'userType')
  }

  getStatus(){
    this.service.getStatus().subscribe(status=>{
      if(status){
        this.statusList=status['result']
      }
    })
  }

  searchSpareDescripancyClaim(){
    let searchobj = this.searchSpareDescripancyClaimForm.getRawValue();
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
  
    this.searchFlag = true;
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }
    let fromDates =this.datepipe.transform(searchobj.fromDate, 'yyyy-MM-dd');
    let toDates =this.datepipe.transform(searchobj.toDate, 'yyyy-MM-dd');
      searchobj.page = this.page
      searchobj.size = this.size
      searchobj.fromDate=fromDates
      searchobj.toDate=toDates
      this.service.searchSpareClaim(searchobj).subscribe(res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
         this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      });
    }else{
      this.toastr.error("Please Select Atleast One Input Fields");
    }
    
     

     
  }
  if (searchObject) {
    Object.keys(searchObject).forEach(element => {
      if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
        delete searchObject[element];
      }
    });
    return searchObject;
  }
  clearForm(){
    this.searchSpareDescripancyClaimForm.reset();
    this.dataTable=null
  }
  initialQueryParams(event:any){

  }
  onUrlChange(event:any){

  }

  actionOnTableRecord(event:any){
    // console.log(event,'event')
    this.recordData=event;
    this.claimNo=this.recordData.record.claimReqNo
    
    if(this.recordData.btnAction==="claimReqNo"){
       const encodedClaimNo = btoa(this.claimNo);
        this.router.navigate(['./view'], {relativeTo: this.activatedRoute,queryParams: { id: encodedClaimNo }
       });
    }else if(this.recordData.record.action==="Approve"){
      const encodedClaimNo = btoa(this.claimNo);
        this.router.navigate(['./approve'], {relativeTo: this.activatedRoute,queryParams: { id: encodedClaimNo }
       });
    }
    else{
      const encodedClaimNo = btoa(this.claimNo);
      this.router.navigate(['./edit'], {relativeTo: this.activatedRoute,queryParams: { id: encodedClaimNo }
     });
     
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
      });
      return searchObject;
    }
  }

pageLoadCount:number=0;
  pageChange(event:any){
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag=false
    // if(this.pageLoadCount > 0){
    //  this.searchSpareDescripancyClaim()
    // }
    // this.pageLoadCount = 1;
  }

  fromDateSelected1(event:any){

  }

  searchAutoClaim(){
    this.searchSpareDescripancyClaimForm.get('claimNo').valueChanges.subscribe(val => {
      if(val && typeof val != 'object'){
          
          this.service.autoSearchClaimNo(val).subscribe(response => {
            if(response){
              this.claimNoList = response['result'];    
            }
          })
      }
  })
  }

  autoSearchGrnNo(){
    this.searchSpareDescripancyClaimForm.get('grnNo').valueChanges.subscribe(grn => {
      if(grn && typeof grn != 'object'){
          
          this.service.autoSearchGrn(grn,'MRR').subscribe(response => {
            console.log(response,'response')
            if(response){
             this.grnNoList = response['result'];    
            }
          })
      }
  })
  }
  

}

