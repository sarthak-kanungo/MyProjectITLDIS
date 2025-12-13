import { Component, OnInit } from '@angular/core';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { TrainingattendanceSheetSearchPageStore } from './attendance-sheet-search-page.store';
import { TrainingattendanceSheetSearchPagePresenter } from './attendance-sheet-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { TrainingattendanceSheetPagePresenter } from '../attendance-sheet-create-page/attendance-sheet-create-page.presenter';
import { ProgramNumber } from '../../../training-programme-calender/domain/tpc-domain';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { MatDatepickerInput } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
// import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-attendance-sheet-search-page',
  templateUrl: './attendance-sheet-search-page.component.html',
  styleUrls: ['./attendance-sheet-search-page.component.scss'],
})
export class TrainingattendanceSheetSearchPageComponent implements OnInit {
  attendanceSearchForm: FormGroup
  fromDate: Date;
  newdate = new Date();
  key = 'atds';
  programNoList$: ProgramNumber[] = []
  programNo:any
  departmentName:any
  locationList: Array<any>;
  trainingModuleList: Array<any>
  trainingList: any;
  hoOrDealerView:boolean
  type:string
  constructor(
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private presenter: TrainingattendanceSheetSearchPagePresenter,
    private service: TrainingProgCalenderService,
    private loginService: LocalStorageService
  ) { }

  ngOnInit() {
    if (this.loginService.getLoginUser().dealerCode == null) {
      this.hoOrDealerView = true
    }
    this.attendanceSearchForm = this.presenter.attendanceSearchHeaderForm
    this.getProgramLocation()
    this.service.getTrainingType(this.loginService.getLoginUser().departmentName,this.type).subscribe(res =>{
      // this.service.getTrainingType(this.loginService.getLoginUser().departmentId,this.type).subscribe(res =>{
        this.trainingList = res.result
      })
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
  getProgramLocation() {
    this.service.getProgramLocation('edit').subscribe(res => {
      this.locationList = res.result
      console.log('locationList-', this.locationList)
    })
  }
  getTrainingType(departmentName:string,actionType:string){
    this.service.getTrainingType(departmentName,actionType).subscribe(res =>{
      this.trainingList = res.result
    })
}
  getTrainingModule(trainingTypeId: any) {
    this.service.getTrainingModule(trainingTypeId.Training_Type_id, 'edit').subscribe(res => {
      this.trainingModuleList = res.result
      console.log(res)
      this.attendanceSearchForm.get('trainingModule').patchValue(this.trainingModuleList[0])
    })
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
