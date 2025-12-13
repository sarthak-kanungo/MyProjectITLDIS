import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { CreateDirectSurveyComponent } from '../../../direct-survey/component/create-direct-survey/create-direct-survey.component';
import { TollFreeService } from '../../service/toll-free.service';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';

@Component({
  selector: 'app-survey-history',
  templateUrl: './survey-history.component.html',
  styleUrls: ['./survey-history.component.css']
})
export class SurveyHistoryComponent implements OnInit {
  surveyHistoryList:any
  @Input()
  surveyHistoryForm: FormArray;
  constructor(private service: TollFreeService, 
    private pagePresenter: TollFreePagePresenter,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.service.fetchServiceCallHistorySubject.subscribe(obj => {
      if(obj['customerId'] && obj['customerId']!=null && obj['vinId']!=null){
        this.service.getDirectSurveyDetails(obj['customerId'], obj['vinId']).subscribe(response => {
          this.surveyHistoryList = response['result'];
          this.surveyHistoryForm.clear();
          this.surveyHistoryList.forEach(element => {
            this.pagePresenter.addRowServeyHistory(element);
          });
        });
      }
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
