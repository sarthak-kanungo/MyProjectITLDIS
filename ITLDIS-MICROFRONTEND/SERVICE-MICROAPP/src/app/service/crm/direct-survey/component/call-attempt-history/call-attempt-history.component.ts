import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { DirectSurveyService } from '../../direct-survey.service';
import { DirectSurveyApi } from '../../url-util/direct-survey-urls';

@Component({
  selector: 'app-call-attempt-history',
  templateUrl: './call-attempt-history.component.html',
  styleUrls: ['./call-attempt-history.component.css']
})
export class CallAttemptHistoryComponent implements OnInit,OnChanges {

  callHistoryList:any[]=[]
  public fileStaticPath: string = DirectSurveyApi.staticPath;
  @Input() dataFromSummery:any
  downloadingFileName:string

  constructor(private service: DirectSurveyService,
              private loginService: LoginFormService,) { }

  ngOnInit() {
  }

  ngOnChanges(){
    if (this.dataFromSummery) {
      if (this.dataFromSummery.has('reminderId') && this.callHistoryList.length == 0) {
        if (this.dataFromSummery.get('survetType')=='TELEPHONIC') {
            this.getCallHistory(this.dataFromSummery.get('reminderId'))
        }
      }
    }
  }

  getCallHistory(surveyReminderId:number){
    this.service.getCallHistory(surveyReminderId).subscribe(res => {
      this.callHistoryList = res
    })
  }


}
