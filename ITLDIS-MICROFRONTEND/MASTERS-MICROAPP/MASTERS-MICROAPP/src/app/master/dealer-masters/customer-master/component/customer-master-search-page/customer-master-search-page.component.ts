import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { CustomerMasterSearchPagePresenter } from './customerMasterSearchPage.presenter';
import { CustomerMasterSearchPageStore } from './customerMasterSearchPage.store';
import { SubmitSearchDto } from '../../domain/customer-master-domain';
import { CustomerMasterSearchPageService } from './customer-master-search-page.service';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-customer-master-search-page',
  templateUrl: './customer-master-search-page.component.html',
  styleUrls: ['./customer-master-search-page.component.scss'],
  providers: [CustomerMasterSearchPagePresenter, CustomerMasterSearchPageStore, CustomerMasterSearchPageService]
})
export class CustomerMasterSearchPageComponent implements OnInit {
  
page = 0;
 size = 10;
  searchForm: FormGroup
  customerMasterSearchForm: FormGroup
  customerSearchDto = {} as SubmitSearchDto
  searchFlag:boolean=true
  dataTable: DataTable;
  actionButtons = [];
  searchValue: ColumnSearch;
  totalTableElements: number;
  
  constructor(
    private customerMasterSearchPagePresenter: CustomerMasterSearchPagePresenter,
    private customerMasterSearchPageService: CustomerMasterSearchPageService,
    private tableDataService: NgswSearchTableService,
    private router : Router,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,private dateService:DateService
  ) { }

  ngOnInit() {
    this.searchForm = this.customerMasterSearchPagePresenter.searchCustomerMasterForm
    this.customerMasterSearchForm = this.customerMasterSearchPagePresenter.customerSearchDetails
    this.search()
  }

  ngAfterViewInit() {
   
  }


  clearForm() {
    this.searchForm.reset()
    this.dataTable=null
  }

  // searchCustomer() {
  //   // debugger
  //     if(this.customerMasterSearchForm.get('customerCode').value === undefined || this.customerMasterSearchForm.get('customerCode').value == null) {
  //         this.customerSearchDto.customerCode = null;
  //     }else{
  //         this.customerSearchDto.customerCode = this.customerMasterSearchForm.get('customerCode').value['customerCode']
  //     }
  //     if(this.customerMasterSearchForm.get('mobileNo').value === undefined || this.customerMasterSearchForm.get('mobileNo').value == null) {
  //         this.customerSearchDto.mobileNo = null;
  //     }else{
  //         this.customerSearchDto.mobileNo = this.customerMasterSearchForm.get('mobileNo').value['mobileNumber']
  //     }
      
  //     this.customerMasterSearchPageService.submitSearchForm(this.customerSearchDto).subscribe(response => {
  //         this.dataTable = this.tableDataService.convertIntoDataTable(response.result);
  //         this.totalTableElements = response.count;
  //     });
  // }

  // search() {
    
  //   console.log('search')
  //     if(this.customerMasterSearchForm.valid){
  //         if(this.dataTable != undefined){
  //             if(this.searchFlag==true)
  //             {
  //               this.page=0;
  //               this.size=10;
  //               this.dataTable['PageIndex']=this.page    
  //               this.dataTable['PageSize']=this.size
  //             }
  //             else{
  //               this.dataTable['PageIndex']=this.page    
  //               this.dataTable['PageSize']=this.size
  //             }
  //         }    
  //         this.searchFlag==true;
  //         this.customerSearchDto.page = this.page;
  //         this.customerSearchDto.size = this.size;
  //         this.searchCustomer();
  //      }
  //  }
   search(){
   
    let customerCode:string
    if (this.customerMasterSearchForm.get('customerCode').value) {
      customerCode=this.customerMasterSearchForm.get('customerCode').value.customerCode
      
    }
   
    let mobileNo:string
    if (this.customerMasterSearchForm.get('mobileNo').value) {
      mobileNo=this.customerMasterSearchForm.get('mobileNo').value.mobileNumber
      
    }
   
    let searchobj = this.customerMasterSearchForm.value;
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
    // searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;  
    this.searchFlag = true;
    if (Object.keys(searchobj).length>0) {
      if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }
      searchobj.page = this.page
      searchobj.size = this.size
      searchobj.customerCode=customerCode,
   
      searchobj.mobileNo=mobileNo
      
      
     
      this.customerMasterSearchPageService.submitSearchForm(searchobj).subscribe(res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
         this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      });
    }else{
      this.toastr.error("Please Select Atleast One Input Field");
    }
     

  }
  
  tableAction(event: object) {
      if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'customercode') {
       this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { customerCode: event['record']['customerCode'] } });
     }else if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'edit') {
       this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { customerCode: event['record']['customerCode'] } });
     }
  }
   
  pageSizeChange(event: InfoForGetPagination) {
       this.page = event['page'];
       this.size = event['size'];
       this.searchFlag=false;
       this.search();
   }
  
}
