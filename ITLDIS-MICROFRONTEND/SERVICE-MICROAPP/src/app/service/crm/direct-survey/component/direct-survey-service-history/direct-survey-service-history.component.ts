import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ButtonAction, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { IFrameMessageSource, IFrameService } from 'src/app/root-service/iFrame.service';
import { JobCardCreateComponent } from 'src/app/service/customer-service/job-card/component/create-job-card-page/create-job-card-page.component';
import { DirectSurveyService } from '../../direct-survey.service';
import { CreateDirectSurveyComponent } from '../create-direct-survey/create-direct-survey.component';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';
import { JobCardViewModalComponent } from '../job-card-view-modal/job-card-view-modal.component';
import { PcrViewModalComponent } from '../pcr-view-modal/pcr-view-modal.component';
import { WcrViewModalComponent } from '../wcr-view-modal/wcr-view-modal.component';

@Component({
  selector: 'app-direct-survey-service-history',
  templateUrl: './direct-survey-service-history.component.html',
  styleUrls: ['./direct-survey-service-history.component.css'],
})
export class DirectSurveyServiceHistoryComponent implements OnInit, OnChanges {

  freeServiceHistorySurveyForm: FormArray;
  surveyHistoryForm: FormArray;
  freeServiceHistoryList: any[];
  directServeyHistoryList: any[];
  @Input() surveyTypeSelection: string
  isTelePhonic: boolean
  serviceHistory: any[]=[];
  directHistory: any[]=[];
  vinId:number
  customerId:any



  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
    private directSurveyService: DirectSurveyService,
    public dialog: MatDialog,
    private iFrameService: IFrameService,) { }

  ngOnInit() {
    this.freeServiceHistorySurveyForm = this.directSurveyPagePresenter.freeServiceForm
    this.surveyHistoryForm = this.directSurveyPagePresenter.surveyHistoryForm
    this.directSurveyService.fetchVinId.subscribe(id => {
      this.vinId = id
    })
    this.directSurveyService.fetchCustomerId.subscribe(id => {
      this.customerId = id
    })

    if (this.surveyTypeSelection!=undefined && this.vinId && this.customerId && this.directHistory.length == 0) {
      this.getDirectSurveyData(this.surveyTypeSelection, this.vinId, this.customerId)
    }
    if (this.vinId && this.customerId && this.serviceHistory.length == 0) {
      this.getFreeServiceData(this.customerId, this.vinId)
    }
  }

  ngOnChanges() {
    if (this.surveyTypeSelection !== undefined) {
      if (this.surveyTypeSelection === 'TELEPHONIC') {
        this.isTelePhonic = true
      } else if (this.surveyTypeSelection === 'DIRECT') {
        this.isTelePhonic = false
      }
    }
  }

  getFreeServiceData(customerId:any, vinId:number) {
            this.directSurveyService.getServiceHistory(customerId, vinId).subscribe(response => {
              this.serviceHistory = response['result'];
              this.freeServiceHistorySurveyForm.clear();
              this.serviceHistory.forEach(element => {
                this.directSurveyPagePresenter.addRowForFreeServiceSurvey(element);
              });
            })
  }
  getDirectSurveyData(type:string, vinId:number, customerId:any) {
      this.directSurveyService.getDirectSurveyDetails('', vinId, 0).subscribe(response => {
        this.directHistory = response['result'];
        this.surveyHistoryForm.clear();
        this.directHistory.forEach(element => {
            this.directSurveyPagePresenter.addRowForSurveyHistory(element);
          });
        })
  }


  jcModal(event): void {
    // this.directSurveyService.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(JobCardViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'jobcode':event.target.innerHTML},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

  pcrModal(event): void {
    // this.directSurveyService.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(PcrViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'pcrNo':event.target.innerHTML},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

  wcrModal(event): void {
    // this.directSurveyService.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(WcrViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'wcrNo':event.target.innerHTML},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

  
  surveyModal(surveyNo): void {
    const dialogRef = this.dialog.open(CreateDirectSurveyComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'surveyNo':surveyNo},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }



}
