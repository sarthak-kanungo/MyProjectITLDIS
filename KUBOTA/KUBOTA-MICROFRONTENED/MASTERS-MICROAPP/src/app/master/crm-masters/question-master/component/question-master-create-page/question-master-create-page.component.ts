import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatCheckboxChange, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DateService } from 'src/app/root-service/date.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { MainAnswer, QuestionMasterHeader, ResponseAnswer, ResponseSub, ResponseSubAnswer, SubAns, SubAnswer } from '../../domain/question-master-domain';
import { QuestionMasterService } from '../../question-master-service/question-master.service';
import { QuestionMasterCreatePagePresenter } from './question-master-create-page.presenter';
import { QuestionMasterCreatePageStore } from './question-master-create-page.store';

@Component({
  selector: 'app-question-master-create-page',
  templateUrl: './question-master-create-page.component.html',
  styleUrls: ['./question-master-create-page.component.css'],
  providers:[QuestionMasterCreatePageStore,QuestionMasterCreatePagePresenter]
})
export class QuestionMasterCreatePageComponent implements OnInit {
  toppingList = [{active:'active', select:'select', subAnswer:'CONTINIOUS CALLING'},{active:'active', select:'select', subAnswer:'EASY TO CONTACT'},{active:'active', select:'select', subAnswer:'OTHER'}];
  questionMasterHeader:FormGroup
  questionMasterQuesFormArray: FormArray
  subAnsFormArray: FormArray
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  creationDate = new Date();
  questionType: Array<any> = []
  questionId:number

  constructor(private pagePresenter:QuestionMasterCreatePagePresenter,
              private activityRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              public  dialog: MatDialog,
              private service: QuestionMasterService,
              private dateService: DateService,
              private pageStore:QuestionMasterCreatePageStore) {
               }

  ngOnInit() {
    this.questionMasterHeader = this.pagePresenter.questionMasterHeader
    this.questionMasterQuesFormArray=this.pagePresenter.questionMasterFormArray
    this.subAnsFormArray = this.pagePresenter.subAnsFormArray
    this.pagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.questionMasterHeader.get('')
    this.viewOrEditOrCreate()
    this.enableDisable()
  }

  viewOrEditOrCreate() {
      if (this.pagePresenter.operation === Operation.VIEW) {
        this.isView=true
        this.getDataFromSearchPage()
      } else if (this.pagePresenter.operation === Operation.EDIT) {
        this.isEdit = true
        this.getDataFromSearchPage()
      }
      else if (this.pagePresenter.operation === Operation.CREATE) {
        this.isCreate = true
        this.getQuestionType()
        let fg:FormGroup = this.pagePresenter.addRowForManualEntry()
        this.questionMasterHeader.get('active').patchValue(true)
      }
  }

  getDataFromSearchPage(){
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('questionCode')) {
        this.questionMasterHeader.get('questionCode').patchValue(param.get('questionCode'))
      }
      if (param.has('id')) {
        this.patchDataViewEdit(parseInt( param.get('id')))
        this.getQuestionType()
      }
    })
  }

  getQuestionType(){
    this.service.getQuestionType().subscribe(res => {
      this.questionType = res['result']
    })
  }

  patchDataViewEdit(questionId:number){
    let ansJson = {} as ResponseAnswer
    let  subArr = {} as ResponseSubAnswer
    this.service.getDataforViewEdit(questionId).subscribe(res =>{
      this.questionId = res['result'].quesId 
      this.questionMasterHeader.get('questionType').patchValue(this.questionType.filter(type => type.Survey_type_id == res['result'].surveyTypeId)[0])
      this.questionMasterHeader.get('question').patchValue(res['result'].questionDesc)
      this.questionMasterHeader.get('creationDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(res['result'].createdDate))
      res['result'].isactive == 'Y' ? this.questionMasterHeader.get('active').patchValue(true):this.questionMasterHeader.get('active').patchValue(false)
      this.questionMasterHeader.disable()
      

      res['result'].mainAnswer.forEach(element => {
        let  subAnsArray: FormArray
        ansJson.quesDtlId = element.quesDtlId
        ansJson.active =  element.isactive == 'Y' ? true:false
        ansJson.mainAnswer = element.mainAnsDesc
        ansJson.subAnswer = element.subAnswerApplicable == 'Y' ? true:false
        ansJson.multipleAnswer = element.multipleAnswer == 'Y' ? true:false
        ansJson.considerDissatisfied = element.considerDissatisfied == 'Y' ? true:false
        this.pagePresenter.patchRow(ansJson)

        element.subAnswer.forEach(list => {
        this.questionMasterQuesFormArray.controls.forEach(fg => {
          subAnsArray = fg.get('subQuest') as FormArray
          subAnsArray.controls.forEach(sub =>{
            sub.get('isactive').enable()
            sub.get('isotherapplicable').enable()
          })
         })
          subArr.quesSubDtlId=list.quesSubDtlId,
          subArr.isactive=list.isactive == 'Y' ? true:false,
          subArr.subAnsDesc=list.subAnsDesc,
          subArr.isotherapplicable= list.isotherapplicable == 'Y' ? true:false
          const fg:FormGroup = this.pageStore.buildSubQuestionArray();
          fg.patchValue(subArr)
          subAnsArray.push(fg);
        });
        this.enableDisable()
      });
    })
  }

  


  addRow(){
    if (this.questionMasterQuesFormArray.status=='VALID') {
      this.pagePresenter.addRowForManualEntry()
    }else{
      this.questionMasterQuesFormArray.markAllAsTouched()
    }
  }

  deleteRow(index:number){
    if (this.questionMasterQuesFormArray.length>1) {
      this.pagePresenter.removeRow(index)
    }
  }


  enableDisable(){
    this.questionMasterQuesFormArray.controls.forEach(fg => {
      if (!this.isCreate) {
        fg.disable()
        fg.get('active').enable()
      }
      let  subForm: FormArray = fg.get('subQuest') as FormArray
      subForm.controls.forEach(sub =>{
        if (!this.isCreate) {
          sub.disable()
          sub.get('isactive').enable()
        }else{
          sub.get('isactive').disable()
          sub.get('isotherapplicable').disable()
          sub.get('subAnsDesc').disable()
        }
      })
    })
  }

  addSubQuestRow(fg1){
    this.questionMasterQuesFormArray.controls.forEach(fg => {
      if (fg.get('subAnswer').value == true) {
        this.pagePresenter.addSubAns(fg1)
        let subAnsArray = fg.get('subQuest') as FormArray
        subAnsArray.controls.forEach(sub =>{
          sub.get('isactive').enable()
          sub.get('isotherapplicable').enable()
        })
      }else{
        this.toastr.error('Please Select Sub Answer First')
      }
    })
  }

  deleteSubQuestRow(fa:FormArray,index:number){
      this.pagePresenter.removeSubAns(fa,index)
  }

  enableSubAns(event:MatCheckboxChange){
      this.questionMasterQuesFormArray.controls.forEach(fg => {
        let subAnsArray = fg.get('subQuest') as FormArray
        subAnsArray.controls.forEach(sub =>{
          if (event.checked == true) {
            sub.get('isactive').enable()
            sub.get('subAnsDesc').enable()
          }else{
            sub.get('isactive').disable()
            sub.get('subAnsDesc').disable()
          }
        })
      })
  }

  validateForm(){
    if (this.questionMasterQuesFormArray.status==='INVALID') {
      this.toastr.error('Please check Mandatory Fields',"Mandatory Field")
      this.questionMasterQuesFormArray.markAllAsTouched()
      this.questionMasterHeader.markAllAsTouched()
    }else{
        this.openConfirmDialog()
    }
  }
/*
  builJsonForSubmit(){
    let questionHeader = {} as QuestionMasterHeader
    let ansList : MainAnswer[]=[]
    let ansFormValue = {} as MainAnswer
    let subList : SubAnswer[]=[]
    let subFormValue = {} as SubAnswer
    
    questionHeader.surveyTypeId = this.questionMasterHeader.get('questionType').value ? this.questionMasterHeader.get('questionType').value.Survey_type_id : null
    questionHeader.questionDesc = this.questionMasterHeader.get('question').value 
    questionHeader.createdDate = this.questionMasterHeader.get('creationDate').value ? this.dateService.getDateIntoDDMMYYYY(this.questionMasterHeader.get('creationDate').value) : null 
    questionHeader.isactive =  (this.questionMasterHeader.get('active').value == true ? 'Y':'N')

    // questionHeader.mainAnswer = this.questionMasterQuesFormArray
    this.questionMasterQuesFormArray.controls.forEach(ansForm => {
      
      ansFormValue.active = (ansForm.get('active').value == true ? 'Y':'N')
      ansFormValue.mainAnsDesc =  ansForm.get('mainAnswer').value
      ansFormValue.subAnswerApplicable = (ansForm.get('subAnswer').value == true ? 'Y':'N')
      ansFormValue.multipleAnswerApplicable = (ansForm.get('multipleAnswer').value == true ? 'Y':'N')
      ansFormValue.considerdissatisfied = (ansForm.get('considerDissatisfied').value == true ? 'Y':'N')

      ansList.push(ansFormValue)
      questionHeader.mainAnswer = ansList

      let subForm = ansForm.get('subQuest') as FormArray
      subForm.controls.forEach(element => {
        subFormValue.isactive = (element.get('active').value == true ? 'Y':'N')
        subFormValue.subAnsDesc =  element.get('subAnswer').value
        subFormValue.isotherapplicable = element.get('forOther').value == true ? 'Y':'N'
        
        subList.push(subFormValue)
        ansFormValue.subAnswer = subList
        
      });
    })
    
    return questionHeader
  }
*/

builJsonForSubmit(){
  console.log('vinay1----',this.questionMasterQuesFormArray);
  let questionHeader = {} as QuestionMasterHeader
  let ansList : MainAnswer[]=[]
  if (this.isEdit) {
    questionHeader.quesId = this.questionId
  }
  
  questionHeader.surveyTypeId = this.questionMasterHeader.get('questionType').value ? this.questionMasterHeader.get('questionType').value.Survey_type_id : null
  questionHeader.questionDesc = this.questionMasterHeader.get('question').value 
  questionHeader.createdDate = this.questionMasterHeader.get('creationDate').value ? this.dateService.getDateIntoDDMMYYYY(this.questionMasterHeader.get('creationDate').value) : null 
  questionHeader.isactive =  (this.questionMasterHeader.get('active').value == true ? 'Y':'N')
  questionHeader.mainAnswer = ansList

  this.questionMasterQuesFormArray.controls.forEach(ansForm => {
    let sub: ResponseSub[]=[]
    sub =(ansForm.get('subQuest').value as Array<any>).map((res:SubAns)=>{
      return new ResponseSub(
        res.subAnsDesc,
        res.isactive == true ? 'Y':'N',
        res.isotherapplicable == true ? 'Y':'N',
        res.quesSubDtlId,
      )
    })

    if (questionHeader.mainAnswer !=undefined) {
      questionHeader.mainAnswer.push({
        quesDtlId: ansForm.get('quesDtlId').value,
        // active : (ansForm.get('active').value == true ? 'Y':'N'),
        mainAnsDesc :  ansForm.get('mainAnswer').value,
        subAnswerApplicable : (ansForm.get('subAnswer').value == true ? 'Y':'N'),
        multipleAnswer : (ansForm.get('multipleAnswer').value == true ? 'Y':'N'),
        considerDissatisfied : (ansForm.get('considerDissatisfied').value == true ? 'Y':'N'),
        subAnswer:sub
      })
    }
  })
  console.log('submit---',questionHeader);
  return questionHeader
}

  submitForm(){
    this.service.submitQuestionMaster(this.builJsonForSubmit()).subscribe(res => {
     // console.log('vinay---',res);
      
    })
  }

  updateForm(){
    this.service.updateQuestionMaster(this.builJsonForSubmit()).subscribe(res => {
     // console.log('vinay---',res);
      
    })
  }
    
  private openConfirmDialog(): void | any {
    const message = `Are you sure you want to submit?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && this.isCreate) {
        this.submitForm()
      }else  if (result === 'Confirm' && this.isEdit) {
        this.updateForm()
      }
    })
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

  getQuestionCode(value){}

  displayFnQuestionCode(displayString: any) {
    return displayString ? displayString.displayString : undefined;
  }

  clearForm(){
    this.builJsonForSubmit()
  }

}
