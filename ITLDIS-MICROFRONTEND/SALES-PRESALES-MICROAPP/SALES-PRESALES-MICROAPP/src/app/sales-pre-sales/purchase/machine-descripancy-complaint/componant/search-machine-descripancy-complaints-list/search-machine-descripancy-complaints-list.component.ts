

import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchMachineDescripancyComplaintsListService } from './search-machine-descripancy-complaints-list.service';
import { Router, ActivatedRoute } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { ComplaintNumberDomain, GrnNumberDomain, ChasisNumberDomain, ComplaintStatusDomain, SearchMachineDescComplaintDomain } from 'MachineDescripancyComplaintModule';
import { NgswSearchTableService, DataTable, ColumnSearch } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-search-machine-descripancy-complaints-list',
  templateUrl: './search-machine-descripancy-complaints-list.component.html',
  styleUrls: ['./search-machine-descripancy-complaints-list.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchMachineDescripancyComplaintsListService,
    NgswSearchTableService
  ]
})
export class SearchMachineDescripancyComplaintsListComponent implements OnInit {

  machineDescripancyComplaintListForm: FormGroup;
  complaintNoData: BaseDto<Array<ComplaintNumberDomain>>
  grnNumberData: BaseDto<Array<GrnNumberDomain>>
  chasisNoData: BaseDto<Array<ChasisNumberDomain>>
  dropdownComplaintStatus: BaseDto<Array<ComplaintStatusDomain>>
  dataTable: DataTable
  page: number = 0;
  size: number = 10;
  clearSearchRow = new BehaviorSubject<string>("");
  totalTableElements;
  @Input()
  max: Date
  tomorrow = new Date();
  actionButtons = [];
  @Input()
  min: Date
  today = new Date();
  minDate: Date;
  maxDate: Date
  searchValue: ColumnSearch = {} as ColumnSearch
  complaintstatus: string[] = [
    'Rejected', 'Partially Approved',
  ];
  searchFlag: boolean = true;
  key='MDC';
  complaintIdNgModel: any = "";
  complaintNumberNgModel: any = "";
  complaintStatusNgModel: any = "";
  chassisNoNgModel: any = "";
  engineNoNgModel: any = "";
  dmsGrnNumberNgModel: any = "";
  kaiInvoiceDateNgModel: any = "";
  grnDateNgModel: any = "";
  transporterNameNgModel: any = "";
  transporterVehicleNumberNgModel: any = "";
  lrNoNgModel: any = "";
  searchFilterValues: any;
  searchFormValues: any;
  userType: any;
  constructor(
    private searchMachineDescripancyComplaintsListService: SearchMachineDescripancyComplaintsListService,
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private fb: FormBuilder,
    private machineDescRt: ActivatedRoute,
    private toastr: ToastrService,
    private loginService: LoginFormService
  ) { 
    this.userType = loginService.getLoginUserType();
  }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.searchMachineDescComplaintForm()
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.machineDescripancyComplaintListForm.patchValue(this.searchFilterValues)
    }

    this.getComplaintStatus()
    this.machineDescripancyComplaintListForm.controls.complaintNo.valueChanges.subscribe(value => {
      this.autoComplaintNumber(value)
    });
    this.machineDescripancyComplaintListForm.controls.grnNo.valueChanges.subscribe(value => {
      this.autoGrnNumber(value)
    });
    this.machineDescripancyComplaintListForm.controls.chassisNo.valueChanges.subscribe(value => {
      this.autoChasisNumber(value)
    });

  }
  ngAfterViewInit() {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.machineDescripancyComplaintListForm.get('complaintFromDate').patchValue(backDate);
    this.machineDescripancyComplaintListForm.get('complaintToDate').patchValue(new Date());
    this.onSearch();
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.machineDescripancyComplaintListForm.get('complaintToDate').value > this.maxDate)
        this.machineDescripancyComplaintListForm.get('complaintToDate').patchValue(this.maxDate);
    }
  }
  autoComplaintNumber(event) {
    this.searchMachineDescripancyComplaintsListService.getComplaintNumber(event).subscribe(response => {
      console.log('response', response);
      this.complaintNoData = response as BaseDto<Array<ComplaintNumberDomain>>
    })
  }
  private getComplaintStatus() {
    this.searchMachineDescripancyComplaintsListService.getComplaintStatus().subscribe(res => {
      this.dropdownComplaintStatus = res as BaseDto<Array<ComplaintStatusDomain>>
    })
  }
  autoGrnNumber(event) {
    this.searchMachineDescripancyComplaintsListService.getGrnNumber(event).subscribe(response => {
      console.log('response', response);
      this.grnNumberData = response as BaseDto<Array<GrnNumberDomain>>
    })
  }
  autoChasisNumber(event) {
    this.searchMachineDescripancyComplaintsListService.getExistingChasisNumber(event).subscribe(response => {
      console.log('response', response);
      this.chasisNoData = response as BaseDto<Array<ChasisNumberDomain>>
    })
  }

  pageChange(event) {
    console.log(event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.onSearch()
  }
  onSearch() {
    this.complaintIdNgModel = "";
    this.complaintNumberNgModel = "";
    this.complaintStatusNgModel = "";
    this.chassisNoNgModel = "";
    this.engineNoNgModel = "";
    this.dmsGrnNumberNgModel = "";
    this.kaiInvoiceDateNgModel = "";
    this.grnDateNgModel = "";
    this.transporterNameNgModel = "";
    this.transporterVehicleNumberNgModel = "";
    this.lrNoNgModel = "";
    this.searchFormValues = this.machineDescripancyComplaintListForm.getRawValue();
    if (!this.checkValidDatesInput()) {
        this.toastr.error("Please Select Date Range.");
    }
    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = 0
      }
    }

    this.searchFlag = true;
    let formData = this.machineDescripancyComplaintListForm.value
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.machineDescripancyComplaintListForm.value))
    let filterobj = {} as SearchMachineDescComplaintDomain
    filterobj.size = this.size;
    filterobj.page = this.page;
    filterobj.complaintStatus = formData['complaintStatus'] ? formData['complaintStatus']['complaintStatus'] : null
    filterobj.complaintNumber = formData['complaintNo'] ? formData['complaintNo']['complaintNumber'] : null
    filterobj.dmsGrnNumber = formData['grnNo'] ? formData['grnNo']['dmsGrnNumber'] : null
    filterobj.chassisNo = formData['chassisNo'] ? formData['chassisNo']['chassisNo'] : null
    filterobj.fromDate = this.convertDateToServerFormat(formData['complaintFromDate'] ? formData['complaintFromDate'] : null)
    filterobj.toDate = this.convertDateToServerFormat(formData['complaintToDate'] ? formData['complaintToDate'] : null)
    ObjectUtil.removeNulls(filterobj)
    this.searchFormValues=filterobj
    this.searchMachineDescripancyComplaintsListService.searchUsingFilter(filterobj).subscribe(data => {
       const result=data.result;
       result.forEach(res=>{
        console.log(this.userType,'userTyoe')
        if(this.userType=='dealer'){
          console.log('if')
          delete res['action'];
        }else{
        console.log('else')
        }
        
       })
      this.dataTable = this.tableDataService.convertIntoDataTable(data.result)
    })
  }
  checkValidDatesInput() {
    const fltEnqObj = this.machineDescripancyComplaintListForm.value 

    fltEnqObj.complaintFromDate = this.machineDescripancyComplaintListForm.getRawValue() ? this.machineDescripancyComplaintListForm.value.complaintFromDate : null
    fltEnqObj.complaintToDate = this.machineDescripancyComplaintListForm.getRawValue() ? this.machineDescripancyComplaintListForm.value.complaintToDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
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
  sortByDate(ent, searchColumnName) {
    console.log('ent', ent);
    const date: Date = ent.value as Date
    const searchValue = {
      searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
      searchColumnName
    };
    this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.searchColumnName);
  }
  searchMachineDescComplaintForm() {
    this.machineDescripancyComplaintListForm = this.fb.group({
      complaintNo: [null],
      complaintStatus: [null],
      grnNo: [null],
      chassisNo: [null],
      complaintFromDate: [null],
      complaintToDate: [null],
    })
  }
  actionOnTableRecord(recordData) {
    if (recordData['btnAction'] === 'complaintNumber') {
      
      this.router.navigate(['../view'], { queryParams: {id:btoa(recordData.record.id)},relativeTo: this.machineDescRt })
    }
    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action === 'Approve'){
        this.router.navigate(['approve'], {queryParams: {id:btoa(recordData.record.id)}, relativeTo: this.machineDescRt })
      }
    }
    // if (recordData.record.complaintStatus == "draft") {
    //   this.router.navigate(['edit', recordData.record.complaintId], { relativeTo: this.machineDescRt })
    //   console.log('in edit Of actionOnTableRecord');
    // }
  }

  displayComplaintNoFn(cmpNo: ComplaintNumberDomain) {
    return cmpNo ? cmpNo.complaintNumber : undefined
  }
  displayGrnNoFn(grnNo: GrnNumberDomain) {
    return grnNo ? grnNo.dmsGrnNumber : undefined
  }

  displayChasisNoFn(chasisNo: ChasisNumberDomain) {
    return chasisNo ? chasisNo.chassisNo : undefined
  }
  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      console.log(formattedDate)
      return formattedDate
    }
    return null
  }
  private daysDateDiff = (from, to) => Math.ceil(Math.abs(to - from) / (1000 * 60 * 60 * 24))

  clearMachineDescComplaintSearch() {
    this.machineDescripancyComplaintListForm.reset()
    this.dataTable=null
    this.clearSearchRow.next("");
  }
}