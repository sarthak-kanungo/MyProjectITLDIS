import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../../direct-survey.service';
import { DirectSurveyPagePresenter } from '../../create-direct-survey/direct-survey-page.presenter';
import { DirectSurveyPageStore } from '../../create-direct-survey/direct-survey-page.store';

@Component({
  selector: 'app-current-survey',
  templateUrl: './current-survey.component.html',
  styleUrls: ['./current-survey.component.scss'],
})
export class CurrentSurveyComponent implements OnInit,OnChanges {

  parentFrom:FormGroup
  currentSurveyForm: FormArray
  questionList:any
  answerList = new Map<string, any[]>();
  subAnswerList:any
  surveyTypeId:number
  questionId:number
  @Input() surveyTypeSelection:string

  @Input() quesAnsListForView:any[]=[]

  isEdit: boolean
  isView: boolean
  isCreate: boolean
  vinId:number
  surveyMstId:number
  isSubscribe:boolean=false;
  currentSurveyForm1: any;

  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
    private directSurveyService: DirectSurveyService,
    private directSurveyPageStore: DirectSurveyPageStore,
    private activityRoute: ActivatedRoute,
    ) { }
    

  ngOnChanges(){
     
  }

  ngOnInit(): void {
    this.currentSurveyForm= this.directSurveyPagePresenter.currentSurveyForm
   
    this.directSurveyPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewEditCreate()
    this.parentFrom = this.directSurveyPagePresenter.getParentFrom()
    console.log(this.parentFrom,'parentform')
    this.currentSurveyForm.clear()
   
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('custMstId')) {
        this.surveyMstId = parseInt(param.get('custMstId'))
      }
    });
    if(!this.isView){
      this.directSurveyService.fetchVinId.subscribe(id => {
        this.vinId = id;
        this.surveyTypeId = this.parentFrom.getRawValue().createSurveyHeader?(this.parentFrom.getRawValue().createSurveyHeader.surveyType?this.parentFrom.getRawValue().createSurveyHeader.surveyType.Survey_type_id:null):null
        if (this.surveyMstId && this.surveyTypeId && this.vinId && !this.isSubscribe) {
          this.isSubscribe = true;
          this.getSurveyQuestion(this.surveyMstId,this.surveyTypeId,this.vinId)
        }
      })
    }
  }

  private viewEditCreate() {
    if (this.directSurveyPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
    if (this.directSurveyPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.directSurveyPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }


  getSurveyQuestion(surveyMstId:any,surveyTypeId:any,vinId:any){
    // debugger
    // this.surveyTypeId = this.parentFrom.getRawValue().createSurveyHeader?this.parentFrom.getRawValue().createSurveyHeader.surveyType.Survey_type_id:null
    this.answerList = new Map<string, any[]>();
    this.directSurveyService.getSurveyQuestion(surveyMstId,surveyTypeId,vinId).subscribe(res=>{
      this.questionList = res['result']['questionList']
      this.questionList.forEach(qestList => {
       this.directSurveyPagePresenter.addRowForCurrentSurvey(qestList);
      });
      this.questionList.forEach(qestList => {
       if (qestList.quesId) {
        let ansListArray:any[]=[]
        this.directSurveyService.getSurveyAnswer(qestList.quesId).subscribe(res=>{
          if (res) {
            ansListArray = res['result']['answerList']
            this.answerList.set(qestList.quesId,ansListArray)
          }
        })
       }
      });
    })
  }


  onSelectingAnswer(event:any,fg:FormGroup){
    this.getSubAnswer(event.value.quesDtlId,fg)
    
  }

  getSubAnswer(id:any,fg:FormGroup){
    if (this.surveyTypeSelection==='TELEPHONIC') {
      this.directSurveyService.getSubAnswer(id).subscribe(res=>{
        this.subAnswerList =  res['result']
        let subQues = fg.get('subAnswer') as FormArray;
        subQues.clear();
        this.subAnswerList.forEach(element => {
          const fg1:FormGroup = this.directSurveyPageStore.currentSurveySubAnswer();
          if ((element.Sub_Answer_Applicable).trim()!=='N') {
            fg1.patchValue(element)
            subQues.push(fg1);
          }
        });
        
      })
    }

  }
  subAnswerSelection(fgSub:FormGroup,faMain:FormGroup){
    if (fgSub.get('Multiple_Answer_Applicable').value.trim()=='N') {
      (faMain.get('subAnswer') as FormArray).controls.forEach(element => {
        if(element.get('quesSubDTLID').value != fgSub.get('quesSubDTLID').value){
          element.get('subAnswer').patchValue(false);
        }
      });
    }
    fgSub.get('Multiple_Answer_Applicable').value
  }

}
