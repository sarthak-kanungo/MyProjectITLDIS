import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { itldisPatterns } from '../../../../../utils/itldis-patterns';
import { Brand, ViewEnquiry, CurrentMachine } from '../../domains/enquiry';
import { CurrentMachineryDetailsV2WebService } from '../../services/current-machinery-details-v2-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-current-machinery-details-v2',
  templateUrl: './current-machinery-details-v2.component.html',
  styleUrls: ['./current-machinery-details-v2.component.scss'],
  providers: [CurrentMachineryDetailsV2WebService]
})
export class CurrentMachineryDetailsV2Component implements OnInit, OnChanges {

  currentMachineryDetailsForm: FormGroup
  brand: Array<Brand>
  isShowBtn: boolean
  @Input() enquiryByEnquiryNumber: ViewEnquiry
  
  constructor(
    private enquiryPresenter: EnquiryPresenter,
    private itldisPatterns: itldisPatterns,
    private currentMachineryDetailsV2WebService: CurrentMachineryDetailsV2WebService,
    private formBuilder : FormBuilder,
    private toastr: ToastrService
  ) { }

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {
      this.enquiryByEnquiryNumber.enquiryMachineryDetails.forEach(dtl => {
        this.addRowMachinery(dtl)
      })
      if(this.enquiryByEnquiryNumber.enquiryStatus === 'LOST' || this.enquiryByEnquiryNumber.enquiryStatus === 'DROP'){
        this.isShowBtn = false
        this.currentMachineryDetailsForm.disable()
      }
    }
  }

  ngOnInit() { 
    this.patchOrCreate()
    this.hideAndShowFieldForViewAndCreate()
    this.loadDropDownBrands()
    this.changesYearOfPurchase()
  }
  private patchOrCreate() {
    this.currentMachineryDetailsForm = this.enquiryPresenter.enquiryForm.get('currentMachineryDetails') as FormGroup
  }

  private hideAndShowFieldForViewAndCreate() {
    if (this.enquiryPresenter.operation === Operation.VIEW) {
      this.isShowBtn = true
    } else if (this.enquiryPresenter.operation === Operation.VIEW_MOBILE) {
      this.isShowBtn = true
    } else if (this.enquiryPresenter.operation === Operation.CREATE) {
      this.isShowBtn = true
    }
  }

  onKeyPressAllowNumbersOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowNumbersOnly(event)
  }

  loadDropDownBrands() {
    this.currentMachineryDetailsV2WebService.getBrands().subscribe(response => {
      this.brand = response.result as Array<Brand>
    })
  }

  addRowMachinery(dtl?: CurrentMachine) {
    this.enquiryPresenter.addMachineryDetails(dtl)    
  }

  deleteRowMachinery() {
    this.enquiryPresenter.deleteMachineryDetails()
  }
  changesYearOfPurchase(){
    console.log("currentMachineryDetailsForm---->", this.currentMachineryDetailsForm);
    const currentYear = new Date().getFullYear();

    this.currentMachineryDetailsForm.valueChanges.subscribe(change =>{  
      console.log("change---->", change);
      change.machineryDetails.forEach(row => {
        if(row.yearOfPurchase != null){
        let year = parseInt(row.yearOfPurchase);
        if (year.toString().length == 4) {
          console.log("year--->", typeof year, "==== value--->", year)
          if (year < 1900 || year > currentYear ) {
            this.toastr.warning("Year of Purchase is Invalid")  
            this.currentMachineryDetailsForm['controls'].machineryDetails['controls'].forEach((fg:FormGroup) => {
              if(fg.get('yearOfPurchase').value < 1900 || fg.get('yearOfPurchase').value > currentYear  ){
                fg.get('yearOfPurchase').setErrors({
                  invalidYear: 'Invalid Year',
                });
              }
              // fg.get('yearOfPurchase').setErrors({
              //   invalidYear: 'Invalid Year',
              // });
            });
          }
        }else {
          this.currentMachineryDetailsForm['controls'].machineryDetails['controls'].forEach((fg:FormGroup) => {
            if(fg.get('yearOfPurchase').value==null){
              fg.get('yearOfPurchase').setErrors({
                invalidYear: 'Invalid Year',
              });
            }
            // fg.get('yearOfPurchase').setErrors({
            //   invalidYear: 'Invalid Year',
            // });
          });
        } 
      }  
      });
    })
  }
}
