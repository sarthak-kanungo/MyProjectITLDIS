import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs-compat';
import { DateService } from 'src/app/root-service/date.service';
import { Autocomplete } from '../models/models';
import { TransportServiceService } from '../service/transport-service.service';

@Component({
  selector: 'app-search-machine-transport',
  templateUrl: './search-machine-transport.component.html',
  styleUrls: ['./search-machine-transport.component.css']
})
export class SearchMachineTransportComponent implements OnInit {
  searchmachineTransport:FormGroup
  searchFilter
  dataTable:DataTable
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue: ColumnSearch;
  searchFlag:boolean=false
  page:0
  size=10;
  totalTableElements:number=0
  pageLoadCount:number=0
 
  constructor(private fb:FormBuilder,private toaster:ToastrService,private dateService:DateService,private service:TransportServiceService,private tableDataService:NgswSearchTableService) { }

  ngOnInit() {
    
    this.searchmachineTransport = this.fb.group({
      poNumber: [],
      requestStatus:[],
      fromDate:[],
      toDate:[{}]
    });
  
  }
  // Search For PO Number
  // public getPoNumberAutocomplete(searchKey: any) {
  //   this.service.getPoNumberAutocompleteList(searchKey).subscribe(res => {
  //     this.poNumberAutocomplete = res['result'];
  //   })
  // }
  tableActionClick(event:any){

  }
  
  // searchData(){
  //   // let departmentName:string
  //   // if (this.searcbankDetails.get('departmentname').value) {
  //   //   departmentName=this.searcbankDetails.get('departmentname').value.name
      
  //   // }
  //   // let employeeCode:string
  //   // if (this.searcbankDetails.get('employeeCode').value) {
  //   //   employeeCode=this.searcbankDetails.get('employeeCode').value.employeeCode
      
  //   // }
 
  //   let searchobj = this.searchmachineTransport.value;
  //   if (this.dataTable != undefined) {
  //     if (this.searchFlag == true) {
  //       this.page = 0;
  //       this.size = 10;
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //     else {
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //   }else if (this.searchFlag == true) {
  //     this.page = 0;
  //     this.size = 10;
  //   }
  //   searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
  //   // delete searchobj.page;
  //   // delete searchobj.size;  
  //   this.searchFlag = true;
  //   if (Object.keys(searchobj).length>0) {
  //     if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
  //       this.toaster.error("Please Select Due Date Range.");
  //       return false;
  //     }
  //     searchobj.page = this.page
  //     searchobj.size = this.size
  //     // searchobj.employeeCode=employeeCode,
 
      
     
  //     this.service.searchEmployeeBankDetail(searchobj).subscribe(res => {
  //       this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
  //       this.totalTableElements = res['count'];
  //     }, err => {
  //        this.toaster.error("Search Failed.");
  //       this.dataTable = null;
  //       this.totalTableElements = 0;
  //     });
  //   }else{
  //     this.toaster.error("Please Select Atleast One Input Field");
  //   }
     

  // }
  removeNullFromObjectAndFormatDate(searchobj){

  }
  pageChange(event:any){
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    if(this.pageLoadCount > 0){
      // this.searchData()
    }
    this.pageLoadCount = 1;
    
  }
  initialQueryParams(event:any){

  }
  onUrlChange(event:any){

  }
  // getEmpCode(){
  //   this.searchmachineTransport.get('employeeCode').valueChanges.subscribe((code) => {
  //     if (code && typeof code != 'object' ) {
  //       this.searchmachineTransport.get('employeeCode').setErrors({'selectFromList':'Select From List'}) 
  //       this.service.getEmployeeCodeAuto(null,code).subscribe(res => {
  //         this.employeeCodeList=res
  //       })
  //     }else {
  //       this.searchmachineTransport.get('employeeCode').setErrors(null) 
  //     }
  //   });
  // }
  clear(){
   this.dataTable=null,
   this.searchmachineTransport.reset()
  }
  displayFnEmployeeCode(employeeCode:Autocomplete){
    return employeeCode ? employeeCode.employeeCode : undefined;
  }
  displayPoNumberFn(){

  }
  fromDateChange(event:any){

  }
  searchData(){

  }
}
