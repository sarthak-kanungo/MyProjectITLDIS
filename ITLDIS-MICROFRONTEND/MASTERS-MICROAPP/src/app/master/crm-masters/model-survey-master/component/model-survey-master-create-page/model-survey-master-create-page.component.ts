import { Component, OnInit } from '@angular/core';
import { FormArray } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from 'src/app/confirmation-box/confirmation-box.component';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { ModelSurveyMasterSubmiteDto } from '../../domain/model-survey-master-domain';
import { ModelSurveyMasterService } from '../../service/model-survey-master.service';
import { ModelSurveyMasterCreatePagePresenter } from './model-survey-master-create-page.presenter';
import { ModelSurveyMasterCreatePageStore } from './model-survey-master-create-page.store';

@Component({
  selector: 'app-model-survey-master-create-page',
  templateUrl: './model-survey-master-create-page.component.html',
  styleUrls: ['./model-survey-master-create-page.component.css'],
  providers:[ModelSurveyMasterCreatePageStore,ModelSurveyMasterCreatePagePresenter]
})
export class ModelSurveyMasterCreatePageComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  modelSurveyMasterForm: FormArray
  toppingList = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
  selectedToppings;


  constructor(private pagePresenter:ModelSurveyMasterCreatePagePresenter,
              private activityRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              public  dialog: MatDialog,
              private service: ModelSurveyMasterService) { }

  ngOnInit() {
    this.modelSurveyMasterForm=this.pagePresenter.modelServeyFormArray
    this.pagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
    this.pagePresenter.addRowForManualEntry()
  }

  viewOrEditOrCreate() {
      if (this.pagePresenter.operation === Operation.VIEW) {
        this.isView=true
      } else if (this.pagePresenter.operation === Operation.EDIT) {
        this.isEdit = true
      }
      else if (this.pagePresenter.operation === Operation.CREATE) {
        this.isCreate = true
      }
  }

  addRow(){
    if (this.modelSurveyMasterForm.status=='VALID') {
      this.pagePresenter.addRowForManualEntry()
    }else{
      this.modelSurveyMasterForm.markAllAsTouched()
    }
  }

  deleteRow(index:number){
    if (this.modelSurveyMasterForm.length>1) {
      this.pagePresenter.removeRow(index)
    }
  }

  buildJsonForModelSurveyMater() {
    const formValue = this.modelSurveyMasterForm.getRawValue()
    let submitData = {} as ModelSurveyMasterSubmiteDto
    let modelSurveyMasterEntity:Array<any> = []
    formValue.forEach(element=>{
      modelSurveyMasterEntity.push({
        active:     element['active'] ? 'Y' : 'N',
        select:     element['select'] ? 'Y' : 'N',
        surveyName: element['surveyName'],
        subModel:   element['subModel'],
        surveyType: element['surveyType'],
        noOfDays:   element['noOfDays'],
      })
    })
    submitData.modelSurveyMasterEntity = modelSurveyMasterEntity
    return submitData
    
  }

  validateForm(){
    if (this.modelSurveyMasterForm.status==='INVALID') {
      this.toastr.error('Please check Mandatory Fields',"Mandatory Field")
      this.modelSurveyMasterForm.markAllAsTouched()
    }else{
        this.openConfirmDialog()
    }
  }

  submitForm(){
    this.service.submitModelSurveyMaster(this.buildJsonForModelSurveyMater()).subscribe(res=>{
      if (res.status == "200") {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      } else {
        this.toastr.error(res.message)
      }
    })
  }

   
  clearForm(){
    this.modelSurveyMasterForm.clear()
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
      if (result === 'Confirm') {
        this.submitForm()
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
 
}
