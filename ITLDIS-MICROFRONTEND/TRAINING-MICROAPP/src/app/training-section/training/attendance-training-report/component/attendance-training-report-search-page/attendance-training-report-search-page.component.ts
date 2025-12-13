import { Component, OnInit } from '@angular/core';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { TrainingattendanceTrainingReportSearchPageStore } from './attendance-training-report-search-page.store';
import { TrainingattendanceTrainingReportSearchPagePresenter } from './attendance-training-report-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { ProgramNumber } from '../../../training-programme-calender/domain/tpc-domain';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { MatDatepickerInput } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
// import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-attendance-training-report-search-page',
  templateUrl: './attendance-training-report-search-page.component.html',
  styleUrls: ['./attendance-training-report-search-page.component.scss'],
  providers: [TrainingattendanceTrainingReportSearchPageStore, TrainingattendanceTrainingReportSearchPagePresenter]
})
export class TrainingattendanceTrainingReportSearchPageComponent implements OnInit {
 
  attendanceReportSearchForm: FormGroup
  fromDate: Date;
  newdate = new Date();
  key = 'atdrs';
  programNoList$: ProgramNumber[] = []
  programNo:any
  departmentName:any
  constructor(
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    // private dataservice: DateService
    private presenter: TrainingattendanceTrainingReportSearchPagePresenter,
    private service: TrainingProgCalenderService,
    private loginService:LocalStorageService
  ) { }

  ngOnInit() {
    this.attendanceReportSearchForm= this.presenter.attendanceReportSearchHeaderForm
    if (this.attendanceReportSearchForm.get('fromDate').value == null && this.attendanceReportSearchForm.get('toDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.attendanceReportSearchForm.get('fromDate').patchValue(backDate);
      this.attendanceReportSearchForm.get('toDate').patchValue(new Date());
    } else {
      localStorage.getItem(this.key)
    }
    this.service.getAutoProgramNo(this.programNo,this.loginService.getLoginUser().departmentName).subscribe(res =>{
      // this.service.getTrainingType(this.loginService.getLoginUser().departmentId,this.type).subscribe(res =>{
        var getDepartment=((this.loginService.getLoginUser().departmentName))
        this.departmentName=getDepartment;
        
        this.programNoList$ = res['result']
      
      })
  }
  getProgramNumber(programNo:string) {
    if (programNo) {
      this.service.getAutoProgramNo(programNo,this.departmentName).subscribe(res => {
        this.programNoList$ = res['result']
      })
    }
  }
  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    // if (event && event['value']) {
    //   this.toDate1 = new Date(event['value']);
    //   let maxDate = new Date(event['value']);
    //   maxDate.setMonth(maxDate.getMonth() + 1);
    //   if (maxDate > this.newdate)
    //     this.toDate2 = this.newdate;
    //   else
    //     this.toDate2 = maxDate;
    //   if (this.searchForm.get('goodwillToDate').value > this.toDate2)
    //     this.searchForm.get('goodwillToDate').patchValue(this.toDate2);
    // }
  }
  
  displayFnForProgramNo(selectedOption: ProgramNumber) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['programNumber'] : undefined;
  }
}
