import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../direct-survey.service';
import { VillageSearch } from '../../domain/direct-survey-domain';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';

@Component({
  selector: 'app-other-machine-details',
  templateUrl: './other-machine-details.component.html',
  styleUrls: ['./other-machine-details.component.css']
})
export class OtherMachineDetailsComponent implements OnInit {

  otherMachineDeatilsForm:FormArray
  referenceDetailsForm:FormGroup
  customerDetailsForm: FormGroup
  today = new Date();
  manuFacturerList:any[]=[]
  villageList: VillageSearch[]=[]
  isEdit: boolean
  isView: boolean
  isCreate: boolean


  constructor( private pagePresenter:DirectSurveyPagePresenter,
               private service:DirectSurveyService,
               private activityRoute: ActivatedRoute,
               private toastr: ToastrService,) { }



  ngOnInit() {
    this.otherMachineDeatilsForm = this.pagePresenter.otherMachineDetailsForm
    this.referenceDetailsForm = this.pagePresenter.referenceDetails
    this.customerDetailsForm = this.pagePresenter.searchDirectSurveyCreateCustomerDetailsForm
    this.pagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewEditCreate()
    this.getManuFacturer()

  }

  private viewEditCreate() {
    if (this.pagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.pagePresenter.addRowForOtherMachineDetails()
    }
    if (this.pagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.pagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }

  getManuFacturer(){
    this.service.getManuFacturer().subscribe(res=>{
      this.manuFacturerList = res['result']
    })
  }

  checkYearOfPurchase(fb:FormGroup){
    const currentYear = new Date().getFullYear();
    let year = fb.get('yearOfPurchase').value
    if (year < 1900) {
      fb.get('yearOfPurchase').setErrors({
        invalidYear:'Please Enter a Valid Year'
      })
    }else if (year > currentYear) {
      fb.get('yearOfPurchase').setErrors({
        invalidYear:'Please Enter a Valid Year'
      })
    }
  }

  
  addRow(){
    this.otherMachineDeatilsForm.controls.forEach(fg => {
      if (fg.get('brand').value !=null || fg.get('model').value !=null || fg.get('model').value !=null){
         this.pagePresenter.addRowForOtherMachineDetails()
      }else{
        fg.get('brand').setValidators(Validators.required)
        fg.get('brand').updateValueAndValidity({emitEvent: false});
        fg.get('model').setValidators(Validators.required)
        fg.get('model').updateValueAndValidity({emitEvent: false});
        fg.get('yearOfPurchase').setValidators(Validators.required)
        fg.get('yearOfPurchase').updateValueAndValidity({emitEvent: false});
        fg.get('serialNo').setValidators(Validators.required)
        fg.get('serialNo').updateValueAndValidity({emitEvent: false});
        this.otherMachineDeatilsForm.markAllAsTouched()
      }
    })
  }

  deleteRow(index:number){
      this.pagePresenter.removeRowForOtherMachineDetails(index)
  }

  getVillageAuto(value){
    let state = this.customerDetailsForm.get('state').value
    let district = this.customerDetailsForm.get('district').value
    if (value!=null || value!=undefined && typeof value !== 'object') {
        this.service.villageAuto (value,state,district).subscribe(res=>{
        this.villageList=res
      })
    }
  }

  displayFnVillage(village: VillageSearch) {
    return village ? village.villageName : undefined;
  }

  villageSelect1(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.referenceDetailsForm.get('village1').setErrors(null);
    }
  }
  villageSelect2(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.referenceDetailsForm.get('village2').setErrors(null);
    }
  }

  villageValue1(event){
    if (typeof event=='string') {
      this.referenceDetailsForm.get('village1').setErrors({
        selectFromList:'Please select from List'
      })
    }
  }

  villageValue2(event){
    if (typeof event=='string') {
      this.referenceDetailsForm.get('village2').setErrors({
        selectFromList:'Please select from List'
      })
    }
  }

}
