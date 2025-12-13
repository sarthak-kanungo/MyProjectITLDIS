import { Component, OnInit, Input, OnChanges, forwardRef } from '@angular/core';
import { DeliveryProspectDetailsService } from './delivery-prospect-details.service';
import { FormGroup, Validators, NG_VALUE_ACCESSOR, NG_VALIDATORS, ControlValueAccessor, Validator, AbstractControl, ValidationErrors } from '@angular/forms';
import { DeliveryChallanCreateService } from '../../pages/delivery-challan-create/delivery-challan-create.service';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Component({
  selector: 'app-delivery-prospect-details',
  templateUrl: './delivery-prospect-details.component.html',
  styleUrls: ['./delivery-prospect-details.component.scss'],
  providers: [DeliveryProspectDetailsService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DeliveryProspectDetailsComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => DeliveryProspectDetailsComponent),
      multi: true
    }
  ]
})
export class DeliveryProspectDetailsComponent implements OnInit, OnChanges, ControlValueAccessor, Validator {
  @Input() public isDealerTransferDc: boolean;
  public prospectDetailsForm: FormGroup;
  public isCancel: boolean = false;
  public isEdit: boolean = false;
  @Input() public isView: boolean = false;
  public isApprove: boolean = false;

  constructor(
    private deliveryProspectDetailsService: DeliveryProspectDetailsService,
    private deliveryChallanCreateService: DeliveryChallanCreateService
  ) { }

  ngOnInit() {
    this.prospectDetailsForm = this.deliveryProspectDetailsService.createProspectDetailsForm();
    if(!this.isView){
        this.saveDataAfterFormValid();
        this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.subscribe(data => {
          if (data !== null) {
            let customerDetails = this.isDealerTransferDc ? data['dealerDetails'] : data['enquiryProspectDetailsResponse'];
            this.prospectDetailsForm.patchValue(customerDetails);
            if (!this.isDealerTransferDc) {
              this.prospectDetailsForm.controls.invoiceCustomerName.patchValue(customerDetails['customerName'] ? customerDetails['customerName'] : customerDetails['companyName']);
            }
          }
        })
        this.clearTocuhedForm();
    }
    this.prospectDetailsForm.get("customerType").valueChanges.subscribe(customerType => {
      console.log("customerType---->",customerType);
      if (customerType == "Prospect") {
        this.prospectDetailsForm.get("customerType").patchValue("Customer");
      }
    })
  }
  private clearTocuhedForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.subscribe(value => {
      if (value && value['key'] === 'mark') {
        this.prospectDetailsForm.markAllAsTouched();
        return;
      }
      if (value && value['key'] === 'clear') {
        this.prospectDetailsForm.reset();
        return;
      }
    })
  }
  ngOnChanges() {
    if (this.isDealerTransferDc !== undefined) {
      this.setAndRemoveDcTypeValidators();
    }
  }
  validate(control: AbstractControl): ValidationErrors {
    // throw new Error("Method not implemented.");
    return
  }
  registerOnValidatorChange?(fn: () => void): void {
    // throw new Error("Method not implemented.");
  }
  writeValue(patchObjectData: any): void {
    if (patchObjectData) {
      this.isView = patchObjectData.isView;
      this.isEdit = patchObjectData.isEdit;
      this.isCancel = patchObjectData.isCancel;
      this.isApprove = patchObjectData.isApprove;
      this.prospectDetailsForm.patchValue(patchObjectData);
      this.prospectDetailsForm.patchValue(patchObjectData.customerMaster);
    }
  }
  registerOnChange(fn: any): void {
    // throw new Error("Method not implemented.");
  }
  registerOnTouched(fn: any): void {
    // throw new Error("Method not implemented.");
  }
  setDisabledState?(isDisabled: boolean): void {
    if (isDisabled) {
      this.prospectDetailsForm.disable();
    }
  }
  private setAndRemoveDcTypeValidators() {
    if (this.isDealerTransferDc) {
      this.prospectDetailsForm.controls.invoiceCustomerName.setValidators(Validators.nullValidator);
      this.prospectDetailsForm.controls.invoiceCustomerName.updateValueAndValidity();
      this.prospectDetailsForm.controls.dealerName.setValidators(Validators.required);
      this.prospectDetailsForm.controls.dealerName.updateValueAndValidity();
      this.prospectDetailsForm.controls.dealerCode.setValidators(Validators.required);
      this.prospectDetailsForm.controls.dealerCode.updateValueAndValidity();
      this.prospectDetailsForm.controls.mobileNumber.setValidators(Validators.nullValidator);
      this.prospectDetailsForm.controls.mobileNumber.updateValueAndValidity();
    } else {
      this.prospectDetailsForm.controls.invoiceCustomerName.setValidators(Validators.required);
      this.prospectDetailsForm.controls.invoiceCustomerName.updateValueAndValidity();
      this.prospectDetailsForm.controls.dealerName.setValidators(Validators.nullValidator);
      this.prospectDetailsForm.controls.dealerName.updateValueAndValidity();
      this.prospectDetailsForm.controls.dealerCode.setValidators(Validators.nullValidator);
      this.prospectDetailsForm.controls.dealerCode.updateValueAndValidity();
      this.prospectDetailsForm.controls.mobileNumber.setValidators([Validators.required, CustomValidators.mobileNumber, CustomValidators.numberOnly]);
      this.prospectDetailsForm.controls.mobileNumber.updateValueAndValidity();
    }
  }
  private saveDataAfterFormValid() {
    this.prospectDetailsForm.valueChanges.subscribe(changedValue => {
      let deliveryChallanMainData = this.deliveryChallanCreateService.deliveryChallanMainData;
      deliveryChallanMainData.isProspectFormValid = this.prospectDetailsForm.valid;
      if (this.prospectDetailsForm.valid) {
        this.deliveryChallanCreateService.deliveryChallanMainData = { ...deliveryChallanMainData};
        this.deliveryChallanCreateService.deliveryChallanMainData.customerMobileNumber = this.prospectDetailsForm.controls.mobileNumber.value;
        this.deliveryChallanCreateService.deliveryChallanMainData.invoiceCustomerName = this.prospectDetailsForm.controls.invoiceCustomerName.value;
      }
    })
  }
}
