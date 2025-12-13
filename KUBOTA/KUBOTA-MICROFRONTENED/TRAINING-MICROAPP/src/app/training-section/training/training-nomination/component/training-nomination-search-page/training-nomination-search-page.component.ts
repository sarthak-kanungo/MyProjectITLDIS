import { Component, OnInit } from '@angular/core';
import { TrainingnominationSearchPageWebService } from './training-nomination-search-page-web.service';
import { TrainingnominationSearchPageStore } from './training-nomination-search-page.store';
import { TrainingnominationSearchPagePresenter } from './training-nomination-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { DateService } from 'src/app/root-service/date.service';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { ProgramNumber } from '../../../training-programme-calender/domain/tpc-domain';
import { TrainingnominationService } from '../../service/training-nomination.service';
import { MatDatepickerInput } from '@angular/material';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-training-nomination-search-page',
  templateUrl: './training-nomination-search-page.component.html',
  styleUrls: ['./training-nomination-search-page.component.scss'],
})
export class TrainingnominationSearchPageComponent implements OnInit {
 
  nominationSearchForm: FormGroup
  locationList: Array<any>;
  trainingModuleList:Array<any>
  fromDate: Date;
  newdate = new Date();
  programNo:string
  departmentName:any;
  programNoList$: ProgramNumber[] = []
  nominationNo$: any[] = []
  constructor(private presenter:TrainingnominationSearchPagePresenter,
              private dateservice: DateService,
              private nominationService: TrainingnominationService,
              private tpcservice: TrainingProgCalenderService,
              private loginService: LocalStorageService
  ) { }

  ngOnInit() {
    this.nominationSearchForm = this.presenter.tpcSearchHeaderForm
    this.tpcservice.getAutoProgramNo(this.programNo,this.loginService.getLoginUser().departmentName).subscribe(res =>{
      // this.service.getTrainingType(this.loginService.getLoginUser().departmentId,this.type).subscribe(res =>{
        var getDepartment=((this.loginService.getLoginUser().departmentName))
        this.departmentName=getDepartment;
        
        this.programNoList$ = res['result']
      
      })
  }

  getProgramLocation() {
    // this.service.getProgramLocation('edit').subscribe(res => {
    //   this.locationList = res.result
    // })
  }

  getTrainingModule(trainingTypeId:any){
    // this.service.getTrainingModule(trainingTypeId.Training_Type_id,'edit').subscribe(res =>{
    //   this.trainingModuleList = res.result
    // })
  }
  getProgramNumber(programNo:string) {
    if (programNo) {
      this.tpcservice.getAutoProgramNo(programNo,this.departmentName).subscribe(res => {
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

  getNominatinoNumber(nominationNo:string) {
    if (nominationNo) {
      this.nominationService.getAutoNominationNo(nominationNo).subscribe(res => {
        this.nominationNo$ = res['result']
      })
    }
  }
  displayFnForNominationNo(selectedOption: any) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['nominationNumber'] : undefined;
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
 
}
