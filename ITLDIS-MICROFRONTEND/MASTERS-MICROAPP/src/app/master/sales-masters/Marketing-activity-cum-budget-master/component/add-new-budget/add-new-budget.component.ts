import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { AddNewBudgetService } from './add-new-budget.service';
import { APP_DATE_FORMATS, AppDateAdapter } from '../../../../../date.adapter';
import { BaseDto } from 'BaseDto';
import { activityTypeDropDown, budgetTypeDropDown } from 'ActivityBudget';
import { Observable, forkJoin } from 'rxjs';
import { SaveBudget, HeadDropDown } from './saveBudget.Dto';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-new-budget',
  templateUrl: './add-new-budget.component.html',
  styleUrls: ['./add-new-budget.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    AddNewBudgetService,
  ]
})

export class AddNewBudgetComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  isMaxLimit: boolean;
  isMaxDay: boolean;
  isMaxPer: boolean;
  data: Object;
  selected = 10
  activityTypes: BaseDto<Array<activityTypeDropDown>>
  heads: BaseDto<Array<HeadDropDown>>
  budgetTypes: BaseDto<Array<budgetTypeDropDown>>
  recordPerPageList: Array<number> = [5, 10, 25, 50];

  activityTypeBudgetFrom: FormGroup;

  constructor(
    private fb: FormBuilder,
    private addNewBudgetService: AddNewBudgetService,
    public dialog: MatDialog,
    private toast: ToastrService,
    private router : Router,
    private budgetRt: ActivatedRoute
  ) { }

  ngOnInit() {
    this.createactivityTypeBudgetFrom();
    this.loadAllDropDownData().subscribe(dt => {
      this.activityTypes = dt[0] as BaseDto<Array<activityTypeDropDown>>
      this.budgetTypes = dt[1] as BaseDto<Array<budgetTypeDropDown>>
      // this.patchOrCreate()
    })
  }

  createactivityTypeBudgetFrom() {
    this.activityTypeBudgetFrom = this.fb.group({
      activityType: ['', Validators.compose([Validators.required])],
      addHead: ['', Validators.compose([Validators.required])],
      budgetType: ['', Validators.compose([Validators.required])],
      maximumLimit: ['', Validators.compose([Validators.required])],
      maximumDayMonth: ['', Validators.compose([Validators.required])],
      kaiShare: ['', Validators.compose([Validators.required])],
    })
    this.ativityTypeDropDown()
    this.headDropDown()
    this.budgetTypeDropDown()
  }

  ativityTypeDropDown() {
    this.addNewBudgetService.getActivityTypeData().subscribe(res => {
      this.activityTypes = res as BaseDto<Array<activityTypeDropDown>>
    })
  }
  headDropDown() {
    this.addNewBudgetService.getHeadData().subscribe(res => {
      this.heads = res as BaseDto<Array<HeadDropDown>>
    })
  }
  budgetTypeDropDown() {
    this.addNewBudgetService.getbudgetTypeData().subscribe(res => {
      this.budgetTypes = res as BaseDto<Array<budgetTypeDropDown>>
    })
  }
  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.addNewBudgetService.getActivityTypeData())
    dropDownTask.push(this.addNewBudgetService.getbudgetTypeData())
    return forkJoin(...dropDownTask)
  }
  validateForm() {
    this.markFormAsTouched()
    if (this.activityTypeBudgetFrom.valid) {
      this.openConfirmDialog();
    }
  }

  selectBudgetType(event) {
    console.log('selectBudgetType', event)
    if (event.value == "No Budget") {
      this.isMaxLimit = true
      this.isMaxDay = true
      this.isMaxPer = true
      this.activityTypeBudgetFrom.get('kaiShare').clearValidators();
      this.activityTypeBudgetFrom.get('kaiShare').updateValueAndValidity();

      this.activityTypeBudgetFrom.get('maximumDayMonth').clearValidators();
      this.activityTypeBudgetFrom.get('maximumDayMonth').updateValueAndValidity();

      this.activityTypeBudgetFrom.get('maximumLimit').clearValidators();
      this.activityTypeBudgetFrom.get('maximumLimit').updateValueAndValidity();
    }
    if (event.value == "Fixed") {
      this.isMaxLimit = false
      this.isMaxDay = true
      this.isMaxPer = false
      this.activityTypeBudgetFrom.get('maximumDayMonth').clearValidators();
      this.activityTypeBudgetFrom.get('maximumDayMonth').updateValueAndValidity();
      console.log('this.activityTypeBudgetFrom', this.activityTypeBudgetFrom)
    }
    if (event.value == "Daily") {
      this.isMaxLimit = false
      this.isMaxDay = false
      this.isMaxPer = false

    }
  }
  submitData() {
    let saveBudget = {} as SaveBudget
    saveBudget.enquirySourceMaster = {
      id: this.activityTypeBudgetFrom.controls.activityType.value.id
    }
    saveBudget.activityType = this.activityTypeBudgetFrom.controls.activityType.value.activityType
    saveBudget.budgetType = this.activityTypeBudgetFrom.controls.budgetType.value
    saveBudget.marketingActivityTypeHeads =  this.activityTypeBudgetFrom.controls.addHead.value
    if (this.activityTypeBudgetFrom.controls.kaiShare.value) {

      saveBudget.kaiShare = (this.activityTypeBudgetFrom.controls.kaiShare.value && parseFloat(this.activityTypeBudgetFrom.controls.kaiShare.value))
    }
    if (this.activityTypeBudgetFrom.controls.maximumDayMonth.value) {

      saveBudget.maximumDayMonth = (this.activityTypeBudgetFrom.controls.maximumDayMonth.value && parseFloat(this.activityTypeBudgetFrom.controls.maximumDayMonth.value))
    }
    if (this.activityTypeBudgetFrom.controls.maximumLimit.value) {

      saveBudget.maximumLimit = (this.activityTypeBudgetFrom.controls.maximumLimit.value && parseFloat(this.activityTypeBudgetFrom.controls.maximumLimit.value))
    }
    console.log('saveBudget', saveBudget); 
    this.addNewBudgetService.saveData(saveBudget).subscribe(
      res => {
        const displayMsg = res['message']
        this.toast.success(displayMsg, 'Success')
        this.router.navigate(['../'], { relativeTo: this.budgetRt })
      },
      err => this.toast.error('problem in creating budget', 'Error')
    )
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit KAI Activity Type Budget Master?';
    if (this.isEdit) {
      message = 'Do you want to update  KAI Activity Type Budget Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  private markFormAsTouched() {
    this.activityTypeBudgetFrom.markAllAsTouched();
  }

  clearForm() {
    this.activityTypeBudgetFrom.reset()
  }




  keyPressMaxLimit(event: any) {
    this.addNewBudgetService.keyPressMaxLimit(event)
  }
  keyPressMaxDay(event: any) {
    this.addNewBudgetService.keyPressMaxDay(event)
  }
  keyPressShare(event: any) {
    this.addNewBudgetService.keyPressShare(event)
  }
}
