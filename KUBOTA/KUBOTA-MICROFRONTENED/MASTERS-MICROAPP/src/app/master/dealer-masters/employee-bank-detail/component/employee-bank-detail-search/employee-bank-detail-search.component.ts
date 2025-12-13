import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';

import { AutoEmployeeCode, AutoEmployeeName, AutoEmployeeNumber, DepartmentCodeAndName, DepartmentCodeAuto, DesignationAuto } from '../model/employee';
import { EmployeServiceService } from '../service/employe-service.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-employee-bank-detail-search',
  templateUrl: './employee-bank-detail-search.component.html',
  styleUrls: ['./employee-bank-detail-search.component.css']
})
export class EmployeeBankDetailSearchComponent implements OnInit {

  searcbankDetails:FormGroup
  designation: any;
  designationList: any;
  dataTable:DataTable
  departmentsCode: DepartmentCodeAuto;
  departmentsCodeAndName: DepartmentCodeAndName;
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue:ColumnSearch
  // departmentsCode: any;
  dealerUser:boolean=false;
  constructor(
    private fb:FormBuilder,
    private servivce:EmployeServiceService,
    private tableDataService: NgswSearchTableService,
    private toastr: ToastrService,
    private dateService:DateService,
    private localStorageService:LocalStorageService,
    ) { 
      const userTypelist=this.localStorageService.getLoginUser();
      let userType=userTypelist['userType'];
      if(userType==='dealer'){
         this.dealerUser=true;
      }else{
        this.dealerUser=false;
      }
    }
  employeeCodeList=[]
  employeeMobileList=[]
  employeeNameList=[]
  page: number = 0
  size: number = 10
  searchFlag:boolean = true;
  
  totalTableElements: number
  employeeCodeNgModel:any
  employeeNameNgModel:any
  mobileNumberNgModel:any
  departmentNameNgModel:any
  bankNameNgModel:any
  bankBranchNgModel:any
  ifsCodeNgModel:any
  bankAccountNoNgModel:any
  designationNgModel:any
  remarkNgModel:any
  lastApprovedByNgModel:any
  pageLoadCount:number=0
 
  ngOnInit() {

    this.searcbankDetails = this.fb.group({
      employeeCode: [''],
      employeeName: [''],
      mobileNumber: [''],
      departmentname:[''],
      designation: [''],
      departmentCode:[''],
    });
    this.getEmployeeCode()
    this.getDepartmentName()
  
    }
    ngOnChanges() {
      this.getEmployeeCode()
      this.getDepartmentName()
      // this.getEmployeename()
    }

    onKeyEmployeeeCode(event: KeyboardEvent) {
      this.onFocusGetEmployeeCodeList(event);
    }
  
    onFocusGetEmployeeCodeList(value){
      if (value == null || value == undefined)
          value = '';
    
      if(typeof value !== 'object'){
        //  this.getEmployeeCode(value);
      }
      else{
        this.employeeCodeList = null;
      }
    }
    onKeyEmployeeeName(event: KeyboardEvent) {
      this.onKeyEmployeeeName(event);
    }
  
    c(value){
      if (value == null || value == undefined)
          value = '';
    
      if(typeof value !== 'object'){
        //  this.getEmployeeCode(value);
      }
      else{
        this.employeeNameList = null;
      }
    }
    etSearchDateValueChange(searchDate: string, columnKey: string) {
      let modifiedDate = null;
      if (searchDate) {
        modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
      }
       this.searchValue = new ColumnSearch(modifiedDate, columnKey);
     }
    getEmployeeCode(){
      this.searcbankDetails.get('employeeCode').valueChanges.subscribe((code) => {
        if (code && typeof code != 'object' ) {
          this.searcbankDetails.get('employeeCode').setErrors({'selectFromList':'Select From List'}) 
          this.servivce.getEmployeeCodeAuto(null,code).subscribe(res => {
            this.employeeCodeList=res
          })
        }else {
          this.searcbankDetails.get('employeeCode').setErrors(null) 
        }
      });
       
        this.searcbankDetails.get('mobileNumber').valueChanges.subscribe(mobile => {
          if(mobile){
          
          this.servivce.getEmployeeCodeAuto(mobile,null).subscribe(res=>{
            this.employeeMobileList=res
            })
          }
        })

      }
    clearForm(){
      this.searcbankDetails.reset()
      this.dataTable=null
    }
   
    initialQueryParams(event:any){

    }
    onUrlChange(event:any){

    }
   getEmployeename(value){
    this.searcbankDetails.get('employeeName').valueChanges.subscribe((res) => {
      if (res && typeof res != 'object' ) {
        this.searcbankDetails.get('employeeName').setErrors({'selectFromList':'Select From List'}) 
        this.servivce.getEmployeeNameAuto(value).subscribe(res => {
          this.employeeNameList=res
        })
      }else {
        this.searcbankDetails.get('employeeName').setErrors(null) 
      }
    });

    }
    getDesignation(value){
      this.searcbankDetails.get('designation').valueChanges.subscribe((res) => {
        if (res && typeof res != 'object' ) {
          this.searcbankDetails.get('designation').setErrors({'selectFromList':'Select From List'}) 
          this.servivce.searchDesignation(value).subscribe(res => {
            this.designationList=res
          })
        }else {
          this.searcbankDetails.get('designation').setErrors(null) 
        }
      });
    }

    displayFnDesignation(displayString: DesignationAuto) {
      return displayString ? displayString.displayString : undefined;
    }
 
    getDepartmentName(){
      this.servivce.searchDepartmentCode('').subscribe(res=>{
        this.departmentsCodeAndName=res
      })
    }
  // For Search
 
  searchData(){
    let departmentName:string
    if (this.searcbankDetails.get('departmentname').value) {
      departmentName=this.searcbankDetails.get('departmentname').value.name
      
    }
    let employeeCode:string
    if (this.searcbankDetails.get('employeeCode').value) {
      employeeCode=this.searcbankDetails.get('employeeCode').value.employeeCode
      
    }
    let employeeName:string
    if (this.searcbankDetails.get('employeeName').value) {
      employeeName=this.searcbankDetails.get('employeeName').value.employeeName
      
    }
    let mobileNumber:string
    if (this.searcbankDetails.get('mobileNumber').value) {
      mobileNumber=this.searcbankDetails.get('mobileNumber').value.mobilenumber
      
    }
    let designation:string
    if (this.searcbankDetails.get('designation').value) {
      designation=this.searcbankDetails.get('designation').value.name
      
    }
    let searchobj = this.searcbankDetails.value;
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
      searchobj.employeeCode=employeeCode,
      searchobj.employeeName=employeeName
      searchobj.departmentName=departmentName
      searchobj.mobileNo=mobileNumber
      searchobj.designation=designation
      
     
      this.servivce.searchEmployeeBankDetail(searchobj).subscribe(res => {
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
  pageChange(event:any){
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    if(this.pageLoadCount > 0){
      this.searchData()
    }
    this.pageLoadCount = 1;
    
  }
  // End
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
  actionOnTableRecord(event:any){
    
  }
    displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
      return employeeCode ? employeeCode.employeeCode : undefined;
    }
    displayFnEmployeeName(employeeName:AutoEmployeeName){
      return employeeName?employeeName.employeeName:undefined
    }
    displayFnEmployeeNumber(mobilenumber:AutoEmployeeNumber){
          return mobilenumber?mobilenumber.mobilenumber:undefined
    }
}
