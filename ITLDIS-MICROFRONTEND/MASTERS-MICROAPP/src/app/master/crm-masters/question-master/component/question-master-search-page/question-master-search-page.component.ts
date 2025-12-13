import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { QuestionCode } from '../../domain/question-master-domain';
import { QuestionMasterService } from '../../question-master-service/question-master.service';
import { QuestionMasterSearchPagePresenter } from './question-master-search-page.presenter';

@Component({
  selector: 'app-question-master-search-page',
  templateUrl: './question-master-search-page.component.html',
  styleUrls: ['./question-master-search-page.component.css'],
})
export class QuestionMasterSearchPageComponent implements OnInit,AfterViewInit {

  questionMasterSearchForm: FormGroup
  questionType: Array<any> = []
  today = new Date();
  toDate: Date;
  toDate1: Date;
  fromDate: Date;
  newdate = new Date();
  questionList$: QuestionCode[] = []
  key = 'question';

  constructor(private presenter:QuestionMasterSearchPagePresenter,
              private service: QuestionMasterService,) { }

  ngOnInit() {
    this.questionMasterSearchForm = this.presenter.questionMasterSearchHeaderForm
    this.getQuestionType()
    if (this.questionMasterSearchForm.get('questionType').value == null && this.questionMasterSearchForm.get('questionCode').value == null && this.questionMasterSearchForm.get('fromDate').value == null && this.questionMasterSearchForm.get('toDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.questionMasterSearchForm.get('fromDate').patchValue(backDate);
      this.questionMasterSearchForm.get('toDate').patchValue(new Date());
    } else {
      localStorage.getItem(this.key)
    }
  }

  ngAfterViewInit() {
    this.questionMasterSearchForm.get('fromDate').valueChanges.subscribe(res => {
      this.fromDate = res
    })
  }

  getQuestionType(){
    this.service.getQuestionType().subscribe(res => {
      this.questionType = res['result']
    })
  }

  getQuestionCode(questionCode:string) {
    if (questionCode) {
      this.service.getAutoQuestionCode(questionCode).subscribe(res => {
        this.questionList$ = res['result']
      })
    }
  }

  displayFnForQuestionCode(selectedOption: QuestionCode) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['question_Code'] : undefined;
  }

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    fieldName == 'pcr' ? this.toDate = event.value : this.toDate1 = event.value;
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth()+1);
      if(maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if( this.questionMasterSearchForm.get('toDate').value > this.toDate)
        this.questionMasterSearchForm.get('toDate').patchValue(this.toDate);  
    }
  }
}
