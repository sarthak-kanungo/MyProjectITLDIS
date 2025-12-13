import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { AttendanceHeader } from '../../domain/attendance-sheet.domain';
import { TrainingattendanceSheetService } from '../../service/attendance-sheet.service';
import { TrainingattendanceSheetSearchPagePresenter } from '../attendance-sheet-search-page/attendance-sheet-search-page.presenter';
import { TrainingattendanceSheetSearchPageStore } from '../attendance-sheet-search-page/attendance-sheet-search-page.store';

@Component({
  selector: 'app-attendance-sheet-search-table',
  templateUrl: './attendance-sheet-search-table.component.html',
  styleUrls: ['./attendance-sheet-search-table.component.css'],
  providers: [TrainingattendanceSheetService, TrainingattendanceSheetSearchPageStore, TrainingattendanceSheetSearchPagePresenter]
})
export class AttendanceSheetSearchTableComponent implements OnInit {
  attendanceSearchForm: FormGroup
  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  recordData: any
  page = 0;
  size = 10
  newResult: any = {}
  newResultFinal: any = []
  key = "atds";
  clearSearchRow = new BehaviorSubject<string>("");
  programNumberNgModel: string
  newdate = new Date();
  fromDate: Date;
  programDateNgModel: string;
  startDateNgModel: string;
  endDateNgModel: string;
  trainingModuleDescNgModel: string;
  locationNgModel: string;
  nominationNumberNgModel: string;
  nomineeDateNgModel: string;
  trainingDateNgModel: string;
  attendanceNgModel: string;
  employeeCodeNgModel: string;
  dealerNameNgModel: string;
  employeeNameNgModel: string;
  trainingLocDescNgModel: string;
  
  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private dateservice: DateService,
    private toastr: ToastrService,
    private tableDataService: NgswSearchTableService,
    private presenter: TrainingattendanceSheetSearchPagePresenter,
    private attendanceService: TrainingattendanceSheetService,
    private loginService: LocalStorageService) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.attendanceSearchForm = this.presenter.attendanceSearchHeaderForm
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.attendanceSearchForm.patchValue(this.searchFilterValues)
    }
    if (this.attendanceSearchForm.get('fromDate').value == null && this.attendanceSearchForm.get('toDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.attendanceSearchForm.get('fromDate').patchValue(backDate);
      this.attendanceSearchForm.get('toDate').patchValue(new Date());
      this.getAttendanceSearchFormsearch()
    } else {
      localStorage.getItem(this.key)
      this.getAttendanceSearchFormsearch()
    }
  }
  /* getAttendanceSearchFormsearch()
  Search by 
  programNumber, 
  trainingLocationId, 
  trainingModuleId,
  fromDate and 
  toDate*/
  getAttendanceSearchFormsearch() {
    this.tableClear()
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
    const filterObj = this.attendanceSearchForm.getRawValue()
    let searchData = {} as AttendanceHeader
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(filterObj))

    searchData.page = this.page
    searchData.size = this.size
    // searchData.departmentName = this.loginService.getLoginUser().departmentName
    searchData.programNumber = filterObj.programNumber ? filterObj.programNumber.programNumber : null
    searchData.trainingLocationId = filterObj.trainingLocation ? filterObj.trainingLocation['Training_loc_id'] : null
    searchData.trainingModuleId = filterObj.trainingModule ? filterObj.trainingModule['Training_Module_id'] : null
    searchData.fromDate = filterObj.fromDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
    searchData.toDate = filterObj.toDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
    this.searchFilter = ObjectUtil.removeNulls(searchData);
    if (this.attendanceSearchForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.attendanceSearchForm.get('programNumber').value || this.attendanceSearchForm.get('trainingLocation').value || this.attendanceSearchForm.get('trainingModule').value || this.attendanceSearchForm.get('fromDate').value || this.attendanceSearchForm.get('toDate').value) {
          this.attendanceService.attendaceSheetSearch(searchData).subscribe(res => {
            // res['result'].forEach(row => {
            //   row.edit = 'edit';
            // });
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count;
          })
        }
        else if (this.attendanceSearchForm.get('fromDate').value == null && this.attendanceSearchForm.get('toDate').value == null) {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }
    }
    else {
      this.toastr.error('Plese select value from list', 'Error');
    }
  }

  /* checkValidDatesInput()
  validate date range fromDate and toDate*/

  checkValidDatesInput() {
    const fltEnqObj = this.attendanceSearchForm.value
    fltEnqObj.fromDate = this.attendanceSearchForm.getRawValue() ? this.attendanceSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.attendanceSearchForm.getRawValue() ? this.attendanceSearchForm.value.toDate : null
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
tableClear(){
  this.programNumberNgModel=''
  this.programDateNgModel=''
  this.startDateNgModel=''
  this.endDateNgModel=''
  this.trainingModuleDescNgModel=''
  this.locationNgModel=''
  this.nominationNumberNgModel=''
  this.nomineeDateNgModel=''
  this.trainingDateNgModel=''
  this.attendanceNgModel=''
  this.employeeCodeNgModel=''
  this.dealerNameNgModel=''
  this.employeeNameNgModel=''
  this.trainingLocDescNgModel=''
}
  clear() {
    this.attendanceSearchForm.reset()
    this.dataTable = null
    localStorage.removeItem(this.key)
    this.clearSearchRow.next("");
    this.tableClear()
  }

  tableAction(event: object) {
    console.log('event---', event)
    if (event['btnAction'] === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], questionCode: event['record']['programNumber'] }
      })
    }
    if (event['btnAction'] === 'programNumber') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], questionCode: event['record']['programNumber'] }
      })
    }
  }
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.getAttendanceSearchFormsearch()
  }
}
