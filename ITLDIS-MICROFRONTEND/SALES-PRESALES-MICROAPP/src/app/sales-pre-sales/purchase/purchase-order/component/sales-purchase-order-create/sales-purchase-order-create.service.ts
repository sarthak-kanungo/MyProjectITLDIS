import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {BehaviorSubject} from 'rxjs';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class SalesPurchaseOrderCreateService {
  createPoForm: FormGroup;
   
  constructor(
    private formBuilder: FormBuilder
  ) { }

  createForm(): FormGroup {
    this.createPoForm = this.formBuilder.group({
      poType: [null, Validators.required],
      depot: [{ value: null, disabled: false }, Validators.required],
      poNumber: [{ value: null, disabled: true }, Validators.required],
      poDate: [{ value: new Date(), disabled: true }, Validators.required],
      totalQuantity: [{ value: null, disabled: true }, Validators.required],
      poStatus: [{ value: null, disabled: true }, Validators.required],
      totalCreditLimit: [{ value: null, disabled: true }, Validators.required],
      availableLimit: [{ value: null, disabled: true }, Validators.required],
      dealerCode:[null],
      dealerName:[null],
      billingState:[null],
      chequeNumber:[{value: null, disabled: false},Validators.compose([CustomValidators.numberOnly])], //added by mahesh.kumar
      chequeDate:[{value: new Date(), disabled: false}], //added by mahesh.kumar
      chequeAmount:[{value: null, disabled: false},Validators.compose([ CustomValidators.numberOnly])], //added by mahesh.kumar
      machineType:[{value:null,disabled:false},Validators.required]
      // totalOs: [null, Validators.required],
      // currentOs: [null, Validators.required],
      // os0To30Days: [null, Validators.required],
      // os31To60Days: [null, Validators.required],
      // os61To90Days: [null, Validators.required],
      // os90Days: [null, Validators.required],
      // paymentPending: [null, Validators.required],
      // netOs: [null, Validators.required],
      // channelFinanceAvailable: [null, Validators.required],

      // basicAmount:[null,Validators.required],
      // draftMode: boolean;
      // gstAmount: number;
      // machineDetails: MachineDetails[];
      // totalAmount: number;
    })
    return this.createPoForm;
  }
}
