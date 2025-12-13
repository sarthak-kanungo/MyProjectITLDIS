import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { AutoCompSubModel, AutoCompVariant, Model } from '../../domain/delear-customer-care-ex-call-domain';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-enquiry-details',
  templateUrl: './enquiry-details.component.html',
  styleUrls: ['./enquiry-details.component.css']
})
export class EnquiryDetailsComponent implements OnInit {

  @Input()
  createEnquiryForm: FormGroup
  @Input() viewSalesEnquiry:any
  model: string
  model$: any;
  subModels: any[];
  modelData: any;
  variant$: any;
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
  min: Date | null
  today = new Date();
  newExpectedDate: Date;
  nextFollowpDate: any;
  expectedDate= new Date()
  constructor(
    private service: DelearCustomerCareExCallService,
    private presenter:DelearCustomerCareExCallPagePresenter,
    private activatedRoute:ActivatedRoute,
    public datepipe: DatePipe
  ) { }

  ngOnInit() {
    this.formValueChange()
    this.createEnquiryForm.get('subModel').disable();
    this.createEnquiryForm.get('variant').disable();
    
      this.createEnquiryForm.get('source').setValue("Dealer CCE")
    
    
  this.checkForOperation()
    
  }

  private checkForOperation(){
  this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
  if (this.presenter.operation === Operation.CREATE) {
    this.isCreate = true;
  } else if (this.presenter.operation === Operation.EDIT) {
    this.isEdit = true;
  } else if (this.presenter.operation === Operation.VIEW) {
    this.isView = true;
   
  }
}

  private formValueChange(){
    this.createEnquiryForm.get('model').valueChanges.subscribe(res=>{
      if(res){
        this.autoModel(res)
      }
    })
  }

  autoModel(model) {
    this.model$ = this.service.getModel(model)
  }

  optionSelectedModel(event:any){
    
    this.modelData=event.option.value;
    this.service.getSubModel(event.option.value).subscribe(response => {
    this.subModels = response.result
    this.createEnquiryForm.get('subModel').enable();
    })
  }

  optionSelectedSubModel(event:any){
    this.variant$ = this.service.getVariant(this.modelData, event.option.value)
    this.createEnquiryForm.get('variant').enable();
  }


  displayFnModel(value: Model) {
    // console.log(value)
    return value ? value : undefined
  }

  displayFnSubModel(value: AutoCompSubModel) {
    // console.log(value)
    return value ? value : undefined
  }

  displayFnvariant(value: AutoCompVariant) {
    return value ? value : undefined
  }
  onKeyPressAllowCharsOnly(event:KeyboardEvent){
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }
  pickDate(event){
    this.nextFollowpDate = event.value;

   this.expectedDate = new Date(this.nextFollowpDate);

  //  console.log(this.expectedDate,'dddddddddddd')
  //  console.log(typeof(this.expectedDate,'dddddddddddddddddd'))
    
  //  /  this.expectedDate = this.datepipe.transform(newDate)
    // console.log(this.expectedDate,'ffffffffffff')
  }

}
