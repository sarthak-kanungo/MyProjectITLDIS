import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DirectSurveyService } from '../../direct-survey.service';
import { SoldToDealer } from '../../domain/direct-survey-domain';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';

@Component({
  selector: 'app-direct-survey-machine-detail',
  templateUrl: './direct-survey-machine-detail.component.html',
  styleUrls: ['./direct-survey-machine-detail.component.scss'],
  

})
export class DirectSurveyMachineDetailComponent implements OnInit {


  directSurveyCreateMachineDetailsForm:FormGroup
  directSurveyCreateDetailsFrom:FormGroup
  chassisList:any[]=[]

  chassisNo:string
  customerId:any
  constructor(private directSurveyPagePresenter:DirectSurveyPagePresenter,
              private surveyService: DirectSurveyService,
              private activityRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.directSurveyCreateMachineDetailsForm = this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm
    this.directSurveyCreateDetailsFrom = this.directSurveyPagePresenter.searchDirectSurveyCreateDetailsFrom
    
    this.surveyService.fetchChassisNoAndCustomerId.subscribe(element =>{
      if (element && element.customerId && element.chassisNo && this.chassisList.length==0) {
        this.getMachineDetailsByCustomerId(element.customerId, element.chassisNo);
      }
    }) 
  }
  getMachineDetailsByCustomerId(customerId:string, chassisNo:any){
    this.surveyService.getMachineDetails(customerId,chassisNo).subscribe(response => {
      this.chassisList = response['result'];
      if(response['result'] && response['result'].length == 1){
        this.directSurveyCreateMachineDetailsForm.patchValue(response['result'][0]);
        this.surveyService.fetchVinId.next(response['result'][0].vinId)
        this.surveyService.viewMachine.next( this.chassisList)
        let sold:SoldToDealer={
          id: response['result'][0].dealerId,
          code: response['result'][0].dealerCode,
          displayString:''
        }
        this.directSurveyCreateDetailsFrom.get('soldToDealer').patchValue(sold)
      }
    });
  }


}
