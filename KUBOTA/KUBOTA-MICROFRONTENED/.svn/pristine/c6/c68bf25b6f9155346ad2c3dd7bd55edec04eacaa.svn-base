import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { AttendancReporteHeader, AttendancReporteHeader1 } from '../../domain/attendance-training-report.domain';
import { TrainingattendanceTrainingReportService } from '../../service/attendance-training-report.service';
import { TrainingattendanceTrainingReportSearchPagePresenter } from '../attendance-training-report-search-page/attendance-training-report-search-page.presenter';
import { TrainingattendanceTrainingReportSearchPageStore } from '../attendance-training-report-search-page/attendance-training-report-search-page.store';

@Component({
  selector: 'app-attendance-training-report-search-table',
  templateUrl: './attendance-training-report-search-table.component.html',
  styleUrls: ['./attendance-training-report-search-table.component.css'],
  providers: [TrainingattendanceTrainingReportService, TrainingattendanceTrainingReportSearchPageStore, TrainingattendanceTrainingReportSearchPagePresenter]
})
export class AttendanceTrainingReportSearchTableComponent implements OnInit {
  attendancetrainingReportSearchForm: FormGroup
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
  programNumberNgModel:string
  
  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private dateservice: DateService,
    private toastr: ToastrService,
    private tableDataService: NgswSearchTableService,
    private presenter: TrainingattendanceTrainingReportSearchPagePresenter) { }

  ngOnInit() {
    this.attendancetrainingReportSearchForm= this.presenter.attendanceReportSearchHeaderForm
  }
  getattendanceReportSearchFormsearch() {
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
    const filterObj = this.attendancetrainingReportSearchForm.getRawValue()
    let searchData = {} as AttendancReporteHeader1
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(filterObj))

    searchData.page = this.page
    searchData.size = this.size

    searchData.fromDate = filterObj.fromDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
    searchData.toDate = filterObj.toDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
    this.searchFilter = ObjectUtil.removeNulls(searchData);
    if (this.attendancetrainingReportSearchForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.attendancetrainingReportSearchForm.get('trainingModule').value || this.attendancetrainingReportSearchForm.get('fromDate').value || this.attendancetrainingReportSearchForm.get('toDate').value) {

        }
        else if (this.attendancetrainingReportSearchForm.get('fromDate').value == null && this.attendancetrainingReportSearchForm.get('toDate').value == null) {
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
  checkValidDatesInput() {
    const fltEnqObj = this.attendancetrainingReportSearchForm.value
    fltEnqObj.fromDate = this.attendancetrainingReportSearchForm.getRawValue() ? this.attendancetrainingReportSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.attendancetrainingReportSearchForm.getRawValue() ? this.attendancetrainingReportSearchForm.value.toDate : null
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

  clear() {
    this.attendancetrainingReportSearchForm.reset()
  }

  tableAction(event: object) {
    console.log('event---', event)
    if (event['btnAction'].toLowerCase() === 'edit') {
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
    this.getattendanceReportSearchFormsearch()
  }
}
