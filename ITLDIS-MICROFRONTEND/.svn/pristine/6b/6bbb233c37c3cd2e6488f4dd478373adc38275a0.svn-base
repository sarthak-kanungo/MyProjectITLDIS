import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../direct-survey.service';
import { FirstTimeBuyer } from '../../domain/direct-survey-domain';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';
import { DirectSurveyPageStore } from '../create-direct-survey/direct-survey-page.store';

@Component({
  selector: 'app-contact-machine-implement-crop',
  templateUrl: './contact-machine-implement-crop.component.html',
  styleUrls: ['./contact-machine-implement-crop.component.css'],
})
export class ContactMachineImplementCropComponent implements OnInit,OnChanges {

  contactDetailForm:FormGroup
  machineForm:FormGroup
  implementForm:FormArray
  cropForm:FormGroup
  buyerType: FirstTimeBuyer[]
  factorInfluencedList : any[]=[];
  profileList:any[]=[]
  cropList:[]
  meterHours:number
  vinId:number

  isEdit: boolean
  isView: boolean
  isCreate: boolean

  constructor(private presenteragePresenter: DirectSurveyPagePresenter,
              private service:DirectSurveyService,
              private activityRoute: ActivatedRoute,) { }


  ngOnChanges() {
  }

  ngOnInit() {
    this.contactDetailForm = this.presenteragePresenter.contactDetailsForm
    this.machineForm = this.presenteragePresenter.machineForm
    this.implementForm = this.presenteragePresenter.majorImplementForm
    this.cropForm = this.presenteragePresenter.cropForm
    this.buyerType = this.presenteragePresenter.buyerType

    this.presenteragePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewEditCreate()
    this. getFactorInfluenced()
    this.getProfile()
    this.getMajorCrops()
    // this.presenteragePresenter.addRowCrop()
    //this.addRowImplement()
    this.service.fetchVinId.subscribe(id=>{
      if (id!=null) {
        this.vinId = id
      }
    })
    this.getAgeOfMachine()
  }
  private viewEditCreate() {
    if (this.presenteragePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.presenteragePresenter.addRowImplement()
    }
    if (this.presenteragePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.presenteragePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }

  getFactorInfluenced(){
    this.service.getLookupByCode("FACTORS_INFLUENCED").subscribe(response => {
      this.factorInfluencedList = response['result'];
      this.service.viewFactorInfluenced.next(this.factorInfluencedList)
    });
  }

  getProfile(){
    this.service.getLookupByCode("SURVEY_CONTACT_PROFILE").subscribe(response => {
      this.profileList = response['result'];
      this.service.viewProfile.next(this.profileList)
    });
  }

  getMajorCrops(){
    this.service.getMajorCropsGrown().subscribe(response => {
      this.cropList = response['result'];
      this.service.viewCropSelected.next(this.cropList)
    });
  }

  factorInfluencedOptSelect(selectedValue:string){
    if (selectedValue==='N') {
      this.machineForm.get('brand').enable()
    }else{
      this.machineForm.get('brand').disable()
    }
  }

  addRowImplement(){
    this.implementForm.controls.forEach(fg => {
      if (fg.get('implementName').value !=null || fg.get('hours').value !=null){
        this.presenteragePresenter.addRowImplement()
      }else{
        fg.get('implementName').setValidators(Validators.required)
        fg.get('implementName').updateValueAndValidity({emitEvent: false});
        fg.get('hours').setValidators(Validators.required)
        fg.get('hours').updateValueAndValidity({emitEvent: false});
        this.implementForm.markAllAsTouched()
      }
    })
  }

  deleteRowImplement(index:number){
      this.presenteragePresenter.removeRowImplement(index)
  }

  getAgeOfMachine(){
    if (this.vinId) {
      this.service.getAgeOfMachine(this.vinId).subscribe(res=>{
        this.machineForm.get('ageOfMachine').patchValue(res['result'][0].Machine_Age)
      })
    }
      
  }

  getMeterHours(enteredVal:number){
    if (enteredVal) {
      this.service.getMeterHours(this.vinId).subscribe(res=>{
        this.meterHours = res['result'][0].total_hour
        if (enteredVal<=res['result'][0].total_hour) {
          this.machineForm.get('houseMeterReading').setErrors({
            hmrError:'Entered Value Should be greater than'
          })
        }
      })
    }
    
  }


  // addRowCrop(){
  //   if (this.cropForm.status=='VALID') {
  //     this.presenteragePresenter.addRowCrop()
  //   }else{
  //     this.cropForm.markAllAsTouched()
  //   }
  // }

  // deleteRowCrop(index:number){
  //     this.presenteragePresenter.removeRowCrop(index)
  // }

}
