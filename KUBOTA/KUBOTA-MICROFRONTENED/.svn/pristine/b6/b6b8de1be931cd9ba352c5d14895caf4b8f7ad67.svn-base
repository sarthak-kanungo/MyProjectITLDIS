import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { OPSService } from '../service/ops.service';
import { MatDatepickerInput } from '@angular/material';
import { searchOrderPlanning } from './search-order-planning-domain';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { SparesPurchaseOrderWebService } from '../../spares-purchase-order/component/purchase-order/spares-purchase-order-web.service';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-search-order-planning-sheet',
  templateUrl: './search-order-planning-sheet.component.html',
  styleUrls: ['./search-order-planning-sheet.component.css'],
  
  providers:[SparesPurchaseOrderWebService]
})
export class SearchOrderPlanningSheetComponent implements OnInit {
  searchOrderPlanningSheet:FormGroup
  sheetNumberData: any;
  todayDate = new Date()
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  page: number = 0
  size: number = 0
  searchFlag: boolean = true
  dataTable: DataTable;
  totalTableElements: number=0;
  actionButtons
  searchValue: ColumnSearch;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter: any
  totalSearchRecordCount: number = 0

  orderTypeList:any
  logicData: any;
  opSheetNo:any
  
  opSheetNoNgModel:''
  constructor(private fb:FormBuilder,
    private service:OPSService,
    private toaster:ToastrService,
    private tableDataService:NgswSearchTableService,
   private sparesPurchaseOrderWebService:SparesPurchaseOrderWebService,
   private router:Router,
   private activatedRouter:ActivatedRoute
    ) {

    this.searchOrderPlanningSheet=this.fb.group({
      opSheetNo:[{value:null,disabled:false}],
      orderType:[{value:null,disabled:false}],
      logic:[{value:null,disabled:false}],
      fromDate:[{value:null,disabled:false}],
      toDate:[{value:null,disabled:false}]
    })
    if (this.searchOrderPlanningSheet.get('opSheetNo').value == null && this.searchOrderPlanningSheet.get('orderType').value == null  && this.searchOrderPlanningSheet.get('fromDate').value == null && this.searchOrderPlanningSheet.get('toDate').value == null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.searchOrderPlanningSheet.get('fromDate').patchValue(backDate);
      this.searchOrderPlanningSheet.get('toDate').patchValue(new Date());

    }
   }

  ngOnInit() {
    this.formValueChange()
    this.getOrderTypesFromSupplierType()
    this.getlogicData()
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
      if (this.searchOrderPlanningSheet.get('toDate').value > this.maxDate)
        this.searchOrderPlanningSheet.get('toDate').patchValue(this.maxDate);
    }
  }
  initialQueryParams(event:any){

  }
  onUrlChange(event:any){

  }
  private formValueChange(){
    this.searchOrderPlanningSheet.get('opSheetNo').valueChanges.subscribe(res=>{
      if(res){
        this.getSheetNumber(res)
      }
    })
  }
  private getSheetNumber(txt){
    try{
    this.service.autoGenerateSheetNo(txt).subscribe(res=>{
        this.sheetNumberData=res
    })
  }catch{
    

  }
  }
  searchData() {
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

    const filterObj = this.searchOrderPlanningSheet.value as searchOrderPlanning
    filterObj.page = this.page
    filterObj.size = this.size
    filterObj.orderTypeId=this.searchOrderPlanningSheet.get('orderType').value;
    filterObj.logicId=this.searchOrderPlanningSheet.get('logic').value

    filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
    filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null


    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if (this.searchOrderPlanningSheet.get('opSheetNo').value ||this.searchOrderPlanningSheet.get('logic').value || this.searchOrderPlanningSheet.get('orderType').value  || this.searchOrderPlanningSheet.get('fromDate').value  ||this.searchOrderPlanningSheet.get('toDate').value) {
        this.searchOrderPlanningForm(filterObj);
      }

      else if (this.searchOrderPlanningSheet.get('fromDate').value === null && this.searchOrderPlanningSheet.get('toDate').value === null) {
        this.toaster.error("Please fill atleast one input field.");

      }
    } else {
      this.toaster.error("Please Select Date Range.");
    }

  }

  private searchOrderPlanningForm(planningData: searchOrderPlanning) {
    this.service.seachOrderPlanningSheet(planningData).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalSearchRecordCount = res.count;
    }, err => {
      // console.log('err', err);
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
  clearForm(){
   this.searchOrderPlanningSheet.reset();
   this.dataTable=null;
   this.opSheetNoNgModel='';
  }

  private getOrderTypesFromSupplierType() {
    this.sparesPurchaseOrderWebService.getOrderTypesFromSupplierType('KAI').subscribe(res => {
      if (res)
        this.orderTypeList = res['result'];
       this.orderTypeList.shift()
    })
  }
  private getlogicData(){
    this.service.getLogic().subscribe(res=>{
     if(res){
       this.logicData=res['result'];
     }
    
    })
 }
 pageChange(event:any){
   this.page=event.page;
   this.size=event.size;
   this.searchFlag=false;
   this.searchData();
 }
 actionOnTableRecord(event:any){
 
  this.opSheetNo=event.record.opSheetNo
  if(event.btnAction==='action'){
    const encodedOpSheetNo = btoa(this.opSheetNo);
    this.router.navigate(['./edit'], {relativeTo: this.activatedRouter,queryParams: { opSheetNo: encodedOpSheetNo }
    
   });
   return false;
  }
  if(event.btnAction=== 'opSheetNo'){
    const encodedOpSheetNo = btoa(this.opSheetNo);
    this.router.navigate(['./view'], {relativeTo: this.activatedRouter,queryParams: { opSheetNo: encodedOpSheetNo }
    
   });
   return false;
  }
  
 }
 fromDateSelected1(event:any){
  
 }
}
