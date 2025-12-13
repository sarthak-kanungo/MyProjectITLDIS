import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { Operation } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../direct-survey.service';
import { MobileNo } from '../../domain/direct-survey-domain';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';

@Component({
  selector: 'app-direct-survey-customer-detail',
  templateUrl: './direct-survey-customer-detail.component.html',
  styleUrls: ['./direct-survey-customer-detail.component.scss'],
})
export class DirectSurveyCustomerDetailComponent implements OnInit,OnChanges {

  directSurveyCreateCustomerDetailsForm: FormGroup
   mobileNo$: Observable<Array<MobileNo>>;

  satisfactionLevel:Array<any>
  @Input() surveyTypeSelection:string
  forDirect:boolean

  isEdit: boolean
  isView: boolean
  isCreate: boolean
  isSubscribe:boolean=false;
  chassisNoAndCustomerId:any = {}

  @Input() dataFromSummery


  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
              private directSurveyService: DirectSurveyService,
              private activityRoute: ActivatedRoute,) { }



  ngOnInit(): void {
    this.directSurveyCreateCustomerDetailsForm = this.directSurveyPagePresenter.searchDirectSurveyCreateCustomerDetailsForm
   // this.routerParamsFromSurveySummary() 
    this.getSurveyStatus()
    this.viewOrEditOrCreate()

  }

  ngOnChanges(){
    
    if (this.dataFromSummery && this.dataFromSummery.get('chassisNo') && !this.isSubscribe) {
      this.isSubscribe = true;
      if (this.dataFromSummery.has('mobileNo') && this.dataFromSummery.has('chassisNo')) {
        this.chassisNoAndCustomerId.chassisNo = this.dataFromSummery.get('chassisNo')
        this.getCustomerDetails(this.dataFromSummery.get('mobileNo'))
      }
    }
  }

  private viewOrEditOrCreate() {
    if (this.directSurveyPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
    if (this.directSurveyPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.directSurveyPagePresenter.operation === Operation.VIEW) {
      this.isView = true
      if (this.dataFromSummery) {
        if (this.dataFromSummery.has('satisFactoryLevel')) {
          this.directSurveyCreateCustomerDetailsForm.get('satisfactionLevel').patchValue(this.dataFromSummery.get('satisFactoryLevel'))
        }
      }
    }
  }

  getCustomerDetails(mobileNo:any) {
    this.directSurveyService.getDataByMobileNo(mobileNo).subscribe(response =>{
      this.patchValueForMobileNo(response);
      this.directSurveyCreateCustomerDetailsForm.patchValue(response);
      this.directSurveyService.fetchCustomerId.next(response.id);
      this.chassisNoAndCustomerId.customerId = response.id
      
      if (this.chassisNoAndCustomerId.chassisNo ) {
        this.directSurveyService.fetchChassisNoAndCustomerId.next(this.chassisNoAndCustomerId)
      }
    })
  }

  getSurveyStatus(){
    this.directSurveyService.getSatisfactionLevel().subscribe(res=>{
     this.satisfactionLevel=res['result']
    })
  }

  valueChangesForAutoComplate() {
    this.directSurveyCreateCustomerDetailsForm.get("mobileNumber").valueChanges.subscribe((value) => {
        if (value) {
          let mobileNumber =
            typeof value == "object" ? value.mobileNumber : value;
          this.autoMobileNo(mobileNumber);
        }
      });
  }

  autoMobileNo(mobileNumber) {
    this.mobileNo$ = this.directSurveyService.getMobileNumber(mobileNumber);
  }

  displayFnForMObileNo(mobNum: MobileNo) {
    return mobNum ? mobNum.mobileNumber : undefined;
  }


  optionSelectedMobileNo(event) {
    let res:any[]=[]
    let value = event.option.value;
    if (res.length == 0) {
      this.directSurveyService.getDataByMobileNo(event.option.value.mobileNumber).subscribe((response) => {
        res = response
        this.directSurveyCreateCustomerDetailsForm.patchValue(response);
        this.directSurveyService.fetchCustomerId.next(value.id);
        this.patchValueForMobileNo(response);
      });
    }
  }


  patchValueForMobileNo(response: any) {
    this.directSurveyCreateCustomerDetailsForm.get("mobileNumber").patchValue({ mobileNumber: response.mobileNumber });
  }

  onKeyPressAllowNumbersOnly(event: KeyboardEvent) {
    this.directSurveyPagePresenter.allowNumbersOnly(event);
  }

}
