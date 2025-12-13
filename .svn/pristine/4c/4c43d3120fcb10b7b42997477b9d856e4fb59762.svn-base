import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { DateService } from 'src/app/root-service/date.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { ProgramNumber } from '../../domain/tpc-domain';
import { TrainingProgCalenderService } from '../../service/training-prog-calender.service';
import { TrainingProgrammeCalenderSearchPagePresenter } from './training-prog-calender-search-page.presenter';

@Component({
  selector: 'app-training-prog-calender-search-page',
  templateUrl: './training-prog-calender-search-page.component.html',
  styleUrls: ['./training-prog-calender-search-page.component.scss'],
})
export class TrainingProgrammeCalenderSearchPageComponent implements OnInit {

  tpcSearchForm: FormGroup
  locationList: Array<any>;
  trainingModuleList: Array<any>
  today = new Date();
  toDate: Date;
  toDate1: Date;
  fromDate: Date;
  newdate:any
  type:string
  programNo:string;
  programNoList$: ProgramNumber[] = []
  trainingList: any;
  hoOrDealerView:boolean
  departmentName:any

  constructor(private presenter: TrainingProgrammeCalenderSearchPagePresenter,
    private dataservice: DateService,
    private service: TrainingProgCalenderService,
    private loginService: LocalStorageService
  ) { }

  ngOnInit() {
    if (this.loginService.getLoginUser().dealerCode == null) {
      this.hoOrDealerView = true
    }
    
    this.tpcSearchForm = this.presenter.tpcSearchHeaderForm
    this.getProgramLocation()
    this.newdate = new Date();
    this.newdate.setMonth(this.today.getMonth() + 1);
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

  getProgramLocation() {
    this.service.getProgramLocation('edit').subscribe(res => {
      this.locationList = res.result
      // console.log('locationList-', this.locationList)
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
      // console.log(res)
      this.tpcSearchForm.get('trainingModule').patchValue(this.trainingModuleList[0])
    })
  }

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    fieldName == 'pcr' ? this.toDate = event.value : this.toDate1 = event.value;
    if (event && event['value']) {
      // this.fromDate = new Date(event['value']);
      // let maxDate = new Date(event['value']);
      // maxDate.setMonth(maxDate.getMonth() + 1);
      // if (maxDate > this.newdate)
      //   this.toDate = this.newdate;
      // else
      //   this.toDate = maxDate;
      // if (this.tpcSearchForm.get('toDate').value > this.toDate)
        this.tpcSearchForm.get('toDate').patchValue(new Date(event['value']));
    }
  }

  getProgramNumber(programNo: string) {
    if(programNo){
      this.service.getAutoProgramNo(programNo,this.departmentName).subscribe(res => {
        this.programNoList$ = res['result']
      })
    }
  }

  displayFnForProgramNo(selectedOption: ProgramNumber) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['programNumber'] : undefined;
  }
}
