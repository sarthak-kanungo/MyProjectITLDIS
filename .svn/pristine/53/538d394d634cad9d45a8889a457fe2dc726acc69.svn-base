import { Component, OnInit } from '@angular/core';
import { FormArray } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ComplaintOrQueryResolutionSubmiteDto, SubmitJson } from '../../domain/complaint-or-query-resolution-domain';
import { ComplaintOrQueryResolutionService } from '../../service/complaint-or-query-resolution.service';
import { ComplaintOrQueryResolutionCreatePagePresenter } from './complaint-or-query-resolution-create-page.presenter';
import { ComplaintOrQueryResolutionCreatePageStore } from './complaint-or-query-resolution-create-page.store';

@Component({
  selector: 'app-complaint-or-query-resolution-create-page',
  templateUrl: './complaint-or-query-resolution-create-page.component.html',
  styleUrls: ['./complaint-or-query-resolution-create-page.component.css'],
  providers:[ComplaintOrQueryResolutionCreatePageStore,ComplaintOrQueryResolutionCreatePagePresenter]
})
export class ComplaintOrQueryResolutionCreatePageComponent implements OnInit {


  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  complaintOrQueryResolutionForm: FormArray
  toppingList = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
  selectedToppings;


  constructor(private pagePresenter:ComplaintOrQueryResolutionCreatePagePresenter,
              private activityRoute: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              public  dialog: MatDialog,
              private service:ComplaintOrQueryResolutionService) { }

  ngOnInit() {
    this.complaintOrQueryResolutionForm=this.pagePresenter.complaintOrQueryResolutionFormArray
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
    if (this.complaintOrQueryResolutionForm.status=='VALID') {
      this.pagePresenter.addRowForManualEntry()
    }else{
      this.complaintOrQueryResolutionForm.markAllAsTouched()
    }
  }

  deleteRow(index:number){
    if (this.complaintOrQueryResolutionForm.length>1) {
      this.pagePresenter.removeRow(index)
    }
  }

  buildJsonForcomplaintOrQueryResolution() {
    const formValue = this.complaintOrQueryResolutionForm.getRawValue()
    let submitData = {} as ComplaintOrQueryResolutionSubmiteDto
    let complaintOrQueryResolutionEntity:Array<any> = []
    formValue.forEach(element=>{
      complaintOrQueryResolutionEntity.push({
        active:     element['active'] ? 'Y' : 'N',
        select:     element['select'] ? 'Y' : 'N',
        surveyName: element['surveyName'],
        subModel:   element['subModel'],
        surveyType: element['surveyType'],
        noOfDays:   element['noOfDays'],
      })
    })
    submitData.complaintOrQueryResolutionEntity = complaintOrQueryResolutionEntity
    return submitData
    
  }

  validateForm(){
    if (this.complaintOrQueryResolutionForm.status==='INVALID') {
      this.toastr.error('Please check Mandatory Fields',"Mandatory Field")
      this.complaintOrQueryResolutionForm.markAllAsTouched()
    }else{
        this.openConfirmDialog()
    }
  }

  submitForm(){
    // this.service.submitComplaintOrQueryResolution(this.buildJsonForcomplaintOrQueryResolution()).subscribe(res=>{
    //   if (res.status == "200") {
    //     this.toastr.success(res.message, 'Success');
    //     this.router.navigate(['../'], { relativeTo: this.activityRoute })
    //   } else {
    //     this.toastr.error(res.message)
    //   }
    // })
  }

   
  clearForm(){
    this.complaintOrQueryResolutionForm.clear()
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

