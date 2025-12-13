import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivityType, TargetedProduct, MaxAllowedBudget, ServiceMtActivityType, ViewServiceActivityProposal } from '../../domain/sap.domain';
import { MatSelectChange } from '@angular/material';
import { SapPagePresenter } from '../sap-page/sap-page-presenter';
import { SapCommonWebService } from '../../service/sap-common-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { ActivityTrainingProposalDetailsWebService } from './activity-training-proposal-details-web.service';

@Component({
  selector: 'app-activity-training-proposal-details',
  templateUrl: './activity-training-proposal-details.component.html',
  styleUrls: ['./activity-training-proposal-details.component.scss'],
  providers: [ActivityTrainingProposalDetailsWebService]
})
export class ActivityTrainingProposalDetailsComponent implements OnInit, OnChanges {

  isView: boolean;
  isEdit: boolean
  isActivityType: boolean
  isActivityTypeUss: boolean
  isApproval: boolean
  isActivityTypeFdflAndUss: boolean
  @Input()
  min: Date | null
  today = new Date();
  maxToDate = new Date();
  isSelectedActivityType: boolean = true
  @Input() activityProposalDetailsForm: FormGroup;
  activityType: ActivityType
  targetedProduct: TargetedProduct
  selectedActivityType: string
  public minToDate = new Date();
  minFromDate = new Date();
  subActivity = [{ subActivity: 'Service Camp' }, { subActivity: 'Do it Yourself (DIY)' }, { subActivity: 'Feel The Difference (FTD)' }]
  @Input() serviceActivityProposalDetails: ViewServiceActivityProposal

  constructor(
    private activityTrainingProposalDetailsWebService: ActivityTrainingProposalDetailsWebService,
    private sapPagePresenter: SapPagePresenter,
    private sapCommonWebService: SapCommonWebService
  ) {
    this.minFromDate.setDate(this.today.getDate()+5);

    this.minToDate = new Date(this.minFromDate);
    this.maxToDate = new Date(this.minFromDate);
    this.maxToDate.setMonth(this.maxToDate.getMonth()+1);
   }

  ngOnChanges() {
    if (this.serviceActivityProposalDetails) {
      this.displayFieldByActivityTypeForView()
      if (this.serviceActivityProposalDetails.approvedAmount) {
        this.isApproval = true
      }
      this.activityProposalDetailsForm.get('numberOfPerson').patchValue(this.serviceActivityProposalDetails.targetedNumbers)
      this.activityProposalDetailsForm.get('proposedBudget').valueChanges.subscribe(value => {
        this.activityTrainingProposalDetailsWebService.calculationForActivityType(this.buildJsonForMaxAllowedBudgetForView(this.serviceActivityProposalDetails, value)).subscribe(response => {
          this.activityProposalDetailsForm.get('maxAllowedBudget').patchValue(response['result'])
        })
      })
      if (this.serviceActivityProposalDetails.serviceMtActivityType.activityType === 'USS') {
        this.activityProposalDetailsForm.get('numberOfPerson').valueChanges.subscribe(value => {
          if (value) {
            this.activityTrainingProposalDetailsWebService.getMaxAllowedBudgetByNumberPerson(value).subscribe(response => {
              this.activityProposalDetailsForm.get('maxAllowedBudget').patchValue(response['result'])
            })
          }
        })
      }
    }
  }

  displayFieldByActivityTypeForView() {
    if (this.serviceActivityProposalDetails.serviceMtActivityType.activityType === '5 in 1 Camp') {
      this.isActivityType = true
      this.isActivityTypeUss = false
      this.isSelectedActivityType = true
      this.isActivityTypeFdflAndUss = true
    } else if (this.serviceActivityProposalDetails.serviceMtActivityType.activityType === 'USS') {
      this.isActivityTypeUss = true
      this.isSelectedActivityType = false
      this.isActivityType = false
      this.isActivityTypeFdflAndUss = false
      this.activityProposalDetailsForm.get('numberOfPerson').reset()
    } else if (this.serviceActivityProposalDetails.serviceMtActivityType.activityType === 'FDFL') {
      this.isActivityTypeUss = false
      this.isActivityType = false
      this.isSelectedActivityType = true
      this.isActivityTypeFdflAndUss = false
    } else {
      this.isActivityType = false
      this.isActivityTypeUss = false
      this.isSelectedActivityType = true
      this.isActivityTypeFdflAndUss = true
    }
  }

  buildJsonForMaxAllowedBudgetForView(value: ViewServiceActivityProposal, proposedBudget: number) {
    const activityProposalDetails = this.sapPagePresenter.sapForm.getRawValue()
    const activityProposal = value
    let totalSubActivity = 0
    if (activityProposal.serviceMtActivityType.activityType === '5 in 1 Camp') {
      totalSubActivity = activityProposalDetails.activityTrainingProposalForm.totalSubActivity
    }
    const maxAllowedBudget = {} as MaxAllowedBudget
    maxAllowedBudget.activityTypeId = activityProposal ? activityProposalDetails.activityTrainingProposalForm.activityType.id : activityProposal.serviceMtActivityType.id
    maxAllowedBudget.targetedNumbers = activityProposal ? (activityProposalDetails.activityTrainingProposalForm.targetedNumbers && parseFloat(activityProposalDetails.activityTrainingProposalForm.targetedNumbers)) : activityProposal.targetedNumbers
    maxAllowedBudget.totalSubActivity = totalSubActivity ? totalSubActivity : 0
    maxAllowedBudget.proposedBudget = proposedBudget
    return maxAllowedBudget
  }

  ngOnInit() {
    this.today.setDate(this.today.getDate());
    this.getSystemDate()
    this.viewOrEditOrCreate()
    this.loadAllDropDown()
    this.sapPagePresenter.countNoOfDays()
  }

  private viewOrEditOrCreate() {
    if (this.sapPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.sapPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    } else if (this.sapPagePresenter.operation === Operation.CREATE) {
      this.valueChangesProposedBudget()
      this.valueChangesNumberOfPerson()
    } else if (this.sapPagePresenter.operation === Operation.APPROVAL) {
      this.isApproval = true
    }
  }

  getSystemDate() {
    if (this.sapPagePresenter.operation === Operation.CREATE) {
      this.activityTrainingProposalDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const activityCreationDate = response['result']
        this.activityProposalDetailsForm.get('activityCreationDate').patchValue(new Date(activityCreationDate))
      })
    }
  }

  loadAllDropDown() {
    this.sapCommonWebService.getAllActivityType().subscribe(response => {
      this.activityType = response
    })
    this.sapCommonWebService.getAllProduct().subscribe(response => {
      this.targetedProduct = response
    })
  }

  selectionActivityType(event: MatSelectChange) {
    this.selectedActivityType = event.value.activityType
    
    this.activityProposalDetailsForm.get('fromDate').reset();
    this.activityProposalDetailsForm.get('toDate').reset();

    if (event.value.activityType === '5 in 1 Camp') {
      this.isActivityType = true
      this.isActivityTypeUss = false
      this.isSelectedActivityType = true
      this.isActivityTypeFdflAndUss = true
      this.activityProposalDetailsForm.get('maxAllowedBudget').reset()
    } else if (event.value.activityType === 'USS') {
      this.isActivityTypeUss = true
      this.isSelectedActivityType = false
      this.isActivityType = false
      this.isActivityTypeFdflAndUss = false
      this.activityProposalDetailsForm.get('numberOfPerson').reset()
      this.activityProposalDetailsForm.get('maxAllowedBudget').reset()
    } else if (event.value.activityType === 'FDFL') {
      const maxAllowedBudget = 5000
      this.activityProposalDetailsForm.get('maxAllowedBudget').patchValue(maxAllowedBudget)
      this.isActivityTypeUss = false
      this.isActivityTypeFdflAndUss = false
      this.isSelectedActivityType = true
      this.isActivityType = false
    } else {
      this.activityProposalDetailsForm.get('maxAllowedBudget').reset()
      this.isActivityType = false
      this.isActivityTypeUss = false
      this.isSelectedActivityType = true
      this.isActivityTypeFdflAndUss = true
    }
  }

  valueChangesNumberOfPerson() {
    this.activityProposalDetailsForm.get('numberOfPerson').valueChanges.subscribe(value => {
      if (value) {
        this.activityTrainingProposalDetailsWebService.getMaxAllowedBudgetByNumberPerson(value).subscribe(response => {
          this.activityProposalDetailsForm.get('maxAllowedBudget').patchValue(response['result'])
        })
      }
    })
  }

  valueChangesProposedBudget() {
    this.activityProposalDetailsForm.get('proposedBudget').valueChanges.subscribe(value => {
      console.log(value);
      this.activityTrainingProposalDetailsWebService.calculationForActivityType(this.buildJsonForMaxAllowedBudget(value)).subscribe(response => {
        this.activityProposalDetailsForm.get('maxAllowedBudget').patchValue(response['result'])
      })
    })
  }

  buildJsonForMaxAllowedBudget(value: number) {
    let totalSubActivity: number
    if (this.activityProposalDetailsForm.controls.activityType.value.activityType === '5 in 1 Camp') {
      totalSubActivity = this.activityProposalDetailsForm.value.totalSubActivity
    }
    const maxAllowedBudget = {} as MaxAllowedBudget
    maxAllowedBudget.activityTypeId = this.activityProposalDetailsForm.value.activityType.id
    maxAllowedBudget.targetedNumbers = (this.activityProposalDetailsForm.value.targetedNumbers && parseFloat(this.activityProposalDetailsForm.value.targetedNumbers))
    maxAllowedBudget.totalSubActivity = totalSubActivity ? totalSubActivity : 0
    maxAllowedBudget.proposedBudget = value
    console.log("maxAllowedBudget ", maxAllowedBudget);
    return maxAllowedBudget
  }

  public fromDateSelected(event: any) {
    if (event && event['value']) {
      this.minToDate = new Date(event['value']);
      this.maxToDate = new Date(event['value']);
      this.maxToDate.setMonth(this.maxToDate.getMonth()+1)
      if(this.activityProposalDetailsForm.controls.activityType.value.activityType === 'DTD Campaign'
        || this.activityProposalDetailsForm.controls.activityType.value.activityType === 'Service Camp'){
          
        this.maxToDate = new Date(event['value']);
        this.maxToDate.setDate(this.maxToDate.getDate()+2)
        this.minToDate = new Date(event['value']);
        this.minToDate.setDate(this.minToDate.getDate()+1)
      }
    }
  }

  compareFnActivityType(c1: ActivityType, c2: ServiceMtActivityType): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.activityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.activityType;
    }
    return c1 && c2 ? c1.activityType === c2.activityType : c1 === c2;
  }

  compareFnTargetedProduct(c1: TargetedProduct, c2: TargetedProduct): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.product === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.product;
    }
    return c1 && c2 ? c1.product === c2.product : c1 === c2;
  }

}
