import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { SapPagePresenter } from '../sap-page/sap-page-presenter';
import { SubActivityWebService } from './sub-activity-web.service';
import { SubActivity, ServiceActivityProposalSubActivity, ViewServiceActivityProposal, MaxAllowedBudget } from '../../domain/sap.domain';
import { Operation } from '../../../../../utils/operation-util';
import { MatSelectChange } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ActivityTrainingProposalDetailsWebService } from '../activity-training-proposal-details/activity-training-proposal-details-web.service';

@Component({
  selector: 'app-sub-activity',
  templateUrl: './sub-activity.component.html',
  styleUrls: ['./sub-activity.component.scss'],
  providers: [SubActivityWebService, ActivityTrainingProposalDetailsWebService]
})
export class SubActivityComponent implements OnInit, OnChanges {

  @Input() subActivityForm: FormGroup
  isActivityType: boolean
  subActivity: Array<SubActivity>
  total: number
  isEdit: boolean
  isCreate: boolean
  subActivityDetails: FormArray
  @Input() serviceActivityProposalDetails: ViewServiceActivityProposal
   

  constructor(
    private sapPagePresenter: SapPagePresenter,
    private subActivityWebService: SubActivityWebService,
    private toastr: ToastrService,
    private activityTrainingProposalDetailsWebService: ActivityTrainingProposalDetailsWebService,
  ) { }

  ngOnChanges(){
    if(this.serviceActivityProposalDetails){
      this.total = this.sapPagePresenter.calculationForSubActivity()
      this.totalAmount()
    }
  }

  ngOnInit() {
    this.valueChangesActivityType()
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
     if (this.sapPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    } else if (this.sapPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.addRow()
    }
  }

  valueChangesActivityType() {
    this.sapPagePresenter.activityTrainingProposal.get('activityType').valueChanges.subscribe(value => {
      if (value.activityType === '5 in 1 Camp') {
        this.isActivityType = true
        this.sapPagePresenter.setValidationForSubActivityAmount()
        this.subActivityWebService.getSubActivityByActivityTypeId(value.id).subscribe(response => {
          this.subActivity = response
        })
      } else {
        this.isActivityType = false
        this.sapPagePresenter.clearValidationForSubActivityAmount()
      }
      this.totalAmount()
    })
  }

  totalAmount() {
    const subActivityDetails = this.subActivityForm.get('subActivityTable') as FormArray
    subActivityDetails.controls.forEach((ele: FormGroup) => {
      ele.get('amount').valueChanges.subscribe(value => {
        this.total = this.sapPagePresenter.calculationForSubActivity()
        const proposeBudgetAmt =  this.sapPagePresenter.calculationForProposedBudget();
        this.sapPagePresenter.headsTotalForm.get('totalAmount').patchValue(proposeBudgetAmt);
        this.valueChangesProposedBudget()

        
      })
    })
  }

  valueChangesProposedBudget() {
    this.sapPagePresenter.activityTrainingProposal.get('totalSubActivity').valueChanges.subscribe(value => {
      this.activityTrainingProposalDetailsWebService.calculationForActivityType(this.buildJsonForMaxAllowedBudget(value)).subscribe(response => {
        this.sapPagePresenter.activityTrainingProposal.get('maxAllowedBudget').patchValue(response['result'])
      })
    })
  }

  buildJsonForMaxAllowedBudget(value : number) {
    const activityProposal =  this.sapPagePresenter.activityTrainingProposal.getRawValue()
    let totalSubActivity: number
    if (activityProposal.activityType.activityType === '5 in 1 Camp') {
      totalSubActivity = value
    }
    const maxAllowedBudget = {} as MaxAllowedBudget
    maxAllowedBudget.activityTypeId = activityProposal.activityType.id
    maxAllowedBudget.targetedNumbers = activityProposal.targetedNumbers && activityProposal.targetedNumbers!=null ? parseFloat(activityProposal.targetedNumbers) : 0; 
    maxAllowedBudget.totalSubActivity = totalSubActivity ? totalSubActivity : 0;
    maxAllowedBudget.proposedBudget = activityProposal.proposedBudget && activityProposal.proposedBudget!=null ? parseFloat(activityProposal.proposedBudget) : 0
    return maxAllowedBudget
  }

  selectionSubActivity(event: MatSelectChange, i : any) {
    console.log("i ", i);
    console.log("event ", event.value);
    this.subActivityDetails = this.subActivityForm.get('subActivityTable') as FormArray
    console.log("subActivityDetails ", this.subActivityDetails);
    // if (this.subActivityDetails.length > 1) {
    //   this.subActivityDetails.controls.forEach(element => {
    //     console.log("value ", element);
    //     if (element.value.subActivity.subActivity[i] === event.value.subActivity[i]) {
    //       console.log('1');
    //       element.get('subActivity').setErrors({
    //         selectFromList: 'Please select from list',
    //       });
    //     } else if (element.value.subActivity.subActivity !== event.value.subActivity) {
    //       console.log('2');
    //       element.get('subActivity').setErrors(null)
    //     }
    //   })
    // }

  }

  addRow(list?: ServiceActivityProposalSubActivity) {
    const subActivityDetails = this.subActivityForm.get('subActivityTable') as FormArray
    if(subActivityDetails.status === 'VALID'){
      this.sapPagePresenter.addRowForSubActivity(list)
      this.totalAmount()
      this.sapPagePresenter.setValidationForSubActivityAmount()
    }else{
      this.toastr.error(`Please select Sub Activity`, 'Report mandatory field')
    }
  
  }

  deleteRow() {
    this.sapPagePresenter.deleteRowForSubActivity()
    this.total = this.sapPagePresenter.calculationForSubActivity()
    this.sapPagePresenter.calculationForProposedBudget();
  }

  compareFnSubActivity(c1: SubActivity, c2: ServiceActivityProposalSubActivity): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subActivity === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subActivity;
    }
    return c1 && c2 ? c1.subActivity === c2.subActivity : c1 === c2;

  }


}