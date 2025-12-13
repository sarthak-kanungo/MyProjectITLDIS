import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { TollFreeService } from '../../service/toll-free.service';
import {saveAs } from 'file-saver';

@Component({
  selector: 'app-search-toll-free',
  templateUrl: './search-toll-free.component.html',
  styleUrls: ['./search-toll-free.component.css'],
  providers:[TollFreeService]
})
export class SearchTollFreeComponent implements OnInit {

  callSearchForm = this.formBuilder.group({
    mobileNo : [null],
    fromDate : [null],
    toDate : [null]
  });
  actionButtons= [];
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
  key = "tollFreepage";
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  customerCodeList:any
  constructor(private formBuilder: FormBuilder,
    private service: TollFreeService,
    private tableDataService: NgswSearchTableService,
    private router : Router,
    private route: ActivatedRoute,
    private dateService: DateService,
    private tostr: ToastrService ) { }

  ngOnInit() {
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.callSearchForm.get('fromDate').patchValue(backDate);
    this.callSearchForm.get('toDate').patchValue(new Date());
    this.searchForm();

    this.callSearchForm.get('mobileNo').valueChanges.subscribe(searchValue => {
      if(searchValue){
        
        this.callSearchForm.get('mobileNo').setErrors({'selectFromList':'Select From List'});
        if(typeof searchValue === 'object'){
          this.callSearchForm.get('mobileNo').setErrors(null);
          searchValue = searchValue.mobileNumber;
        }else{
          this.service.getAutoCompleteMobileNumber(searchValue,"TollFreeSearch").subscribe(response => {
            this.customerCodeList = response['result'];
          })
        }
      } else{
        this.callSearchForm.get('mobileNo').setErrors(null);
      }
    });

  }
  clearTollFreeForm() { 
    this.customerNameNgModel="";
    this.mobileNoNgModel="";
    this.callDateNgModel="";
    this. callNoNgModel="";  
    this.callSearchForm.reset();
    this.clearSearchRow.next("");
  }
  displayFnMobileNo(mobileNumber){
    return (mobileNumber && typeof mobileNumber==='object')?mobileNumber.mobileNumber:undefined;
  }
  searchForm(){
    this.customerNameNgModel="";
    this.mobileNoNgModel="";
    this.callDateNgModel="";
    this.callNoNgModel="";

    if(!this.callSearchForm.valid){
      return false;
    }

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
    if(searchObj.mobileNo){
      searchObj.mobileNo = searchObj.mobileNo['mobileNumber']
    }
    if (this.checkValidDatesInput()) {
      if (this.callSearchForm.get('fromDate').value || this.callSearchForm.get('toDate').value || this.callSearchForm.get('mobileNo').value) {        
        this.service.searchCallDetails(searchObj).subscribe(response => {
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
  pageChange(event){
    this.page = event.page
    this.size = event.size
    this.searchFlag=false;
    this.searchForm()
  }

  tableAction(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'callno') {
      this.router.navigate(['view'], { relativeTo: this.route, queryParams:{id:recordData.record.id}})
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
  generateReport(){
    let searchobj = this.callSearchForm.value;
    let mobileNo:string
    if (this.callSearchForm.get('mobileNo').value) {
      mobileNo=this.callSearchForm.get('mobileNo').value.mobileNumber
      
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    if (Object.keys(searchobj).length>0) {
      if (Object.keys(searchobj).length>0) {
         if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
          this.tostr.error("Please Select Due Date Range.");
          return false;
        }
      }
      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
      searchobj.mobileNo=mobileNo
      if(searchobj['dealerCode']){
        searchobj.dealerId = searchobj['dealerCode']['id'];
      }
      searchobj.branchId = searchobj.branch;
      this.service.exportReport(searchobj).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
        
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    }else{
      this.tostr.error("Please fill atleast one input field.");
    }
  }

}
