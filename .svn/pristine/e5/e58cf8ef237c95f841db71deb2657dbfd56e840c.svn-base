import { Observable, of } from 'rxjs';
import { MatOption } from '@angular/material';
import { Component, OnInit, forwardRef,Input } from '@angular/core';
import { FormGroup, NG_VALUE_ACCESSOR, NG_VALIDATORS, ControlValueAccessor, Validator, ValidationErrors, AbstractControl } from '@angular/forms';
import { DeliveryChallanCreateService } from '../../pages/delivery-challan-create/delivery-challan-create.service';
import { DeliveryInsuranceDetailsService } from './delivery-insurance-details.service';

@Component({
  selector: 'app-delivery-insurance-details',
  templateUrl: './delivery-insurance-details.component.html',
  styleUrls: ['./delivery-insurance-details.component.scss'],
  providers: [DeliveryInsuranceDetailsService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DeliveryInsuranceDetailsComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => DeliveryInsuranceDetailsComponent),
      multi: true
    }
  ]
})
export class DeliveryInsuranceDetailsComponent implements OnInit, ControlValueAccessor, Validator {
  public insuranceCompanyCodeAutoList: Observable<(string | object)[]>;
  public dcInsuranceDetailsForm: FormGroup;
  public insuranceCompaniesList: string[];
  public expiryDate: Date;
  public isCancel: boolean = false;
  public isApprove: boolean = false;
  public isEdit: boolean = false;
  @Input() public isView: boolean = false;

  constructor(
    private deliveryInsuranceDetailsService: DeliveryInsuranceDetailsService,
    private deliveryChallanCreateService: DeliveryChallanCreateService
  ) { }

  ngOnInit() {
    this.dcInsuranceDetailsForm = this.deliveryInsuranceDetailsService.createDcInsuranceDetailsForm();
    if(!this.isView){
        this.companyCodeChanges();
        this.saveDataAfterFormValid();
        this.clearTocuhedForm();
    }
  }
  private clearTocuhedForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.subscribe(value => {
      value && value['key'] === 'mark' ? this.dcInsuranceDetailsForm.markAllAsTouched() : this.dcInsuranceDetailsForm.reset();
      //   if (value && value['key'] === 'mark') {
      //     this.dcInsuranceDetailsForm.markAllAsTouched();
      //     return;
      //   }
      //   if (value && value['key'] === 'clear') {
      //     this.dcInsuranceDetailsForm.reset();
      //     return;
      //   }
      })
    }
  validate(control: AbstractControl): ValidationErrors {
      return
  }
  registerOnValidatorChange ? (fn: () => void): void {
        // throw new Error("Method not implemented.");
      }
  writeValue(patchObjectData): void {
      if(patchObjectData) {
        this.isView = patchObjectData.isView;
        this.isEdit = patchObjectData.isEdit;
        this.isCancel = patchObjectData.isCancel;
        this.isApprove = patchObjectData.isApprove;
        if(patchObjectData.insuranceCompanyMaster!=null && patchObjectData.insuranceCompanyMaster!=undefined){
            this.dcInsuranceDetailsForm.patchValue(patchObjectData.insuranceCompanyMaster);
            this.dcInsuranceDetailsForm.controls.insuranceCompanyMaster.patchValue({ id: patchObjectData.insuranceCompanyMaster.id, companyCode: patchObjectData.insuranceCompanyMaster.companyCode });
        }
        if (patchObjectData.policyStartDate !== null) {
          this.dcInsuranceDetailsForm.controls.policyStartDate.patchValue(new Date(patchObjectData.policyStartDate));
        }
        if (patchObjectData.policyExpiryDate !== null) {
          this.dcInsuranceDetailsForm.controls.policyExpiryDate.patchValue(new Date(patchObjectData.policyExpiryDate));
        }
      }

    }
  registerOnChange(fn: any): void {
      // throw new Error("Method not implemented.");
    }
  registerOnTouched(fn: any): void {
      // throw new Error("Method not implemented.");
    }
  setDisabledState ? (isDisabled: boolean): void {
        if (isDisabled) {
          this.dcInsuranceDetailsForm.disable();
        }
      }
  setExpiryDate(event) {
      this.expiryDate = new Date(event.value);
    }
  private saveDataAfterFormValid() {
      this.dcInsuranceDetailsForm.valueChanges.subscribe(changedValue => {
        let deliveryChallanMainData = this.deliveryChallanCreateService.deliveryChallanMainData;
        deliveryChallanMainData.isInsuranceFormValid = this.dcInsuranceDetailsForm.valid;
        if (this.dcInsuranceDetailsForm.valid) {
          this.deliveryChallanCreateService.deliveryChallanMainData = { ...deliveryChallanMainData, ...this.dcInsuranceDetailsForm.value };
        }
      })
    }
  companyCodeSelected(event: MatOption) {
      if(event && event['option'] && event['option']['value']) {
      this.getDetailsFromCompanyCode(event['option']['value']['companyCode']);
    }
  }
  private getDetailsFromCompanyCode(companyCode: string) {
    this.deliveryInsuranceDetailsService.getCompanyDetailsFromCode(companyCode).subscribe(res => {
      if (res) {
        this.dcInsuranceDetailsForm.patchValue(res['result']);
      }
    })
  }
  public displayCompanyCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['companyCode'] : undefined;
  }
  // private getInsuranceCompanyNames() {
  //   this.deliveryInsuranceDetailsService.getInsuranceCompanyNames().subscribe(res => {
  //     if (res) {
  //       this.insuranceCompaniesList = res['result'];
  //     }
  //   })
  // }
  private companyCodeChanges() {
    this.dcInsuranceDetailsForm.controls.insuranceCompanyMaster.valueChanges.subscribe(value => {
      if (value && typeof value === 'string') {
        this.dcInsuranceDetailsForm.controls['companyName'].reset();
        this.dcInsuranceDetailsForm.controls['address1'].reset();
        this.dcInsuranceDetailsForm.controls['address2'].reset();
        this.dcInsuranceDetailsForm.controls['address3'].reset();
        this.dcInsuranceDetailsForm.controls['pinCode'].reset();
        this.dcInsuranceDetailsForm.controls['locality'].reset();
        this.dcInsuranceDetailsForm.controls['tehsil'].reset();
        this.dcInsuranceDetailsForm.controls['city'].reset();
        this.dcInsuranceDetailsForm.controls['state'].reset();
        this.dcInsuranceDetailsForm.controls['country'].reset();
        this.dcInsuranceDetailsForm.controls['std'].reset();
        this.dcInsuranceDetailsForm.controls['telephoneNumber'].reset();
        this.dcInsuranceDetailsForm.controls['email'].reset();
        this.dcInsuranceDetailsForm.controls['policyStartDate'].reset();
        this.dcInsuranceDetailsForm.controls['policyExpiryDate'].reset();
      }
      this.searchByCompanyCode(value);
    })
  }
  private searchByCompanyCode(changedValue: string) {
    this.deliveryInsuranceDetailsService.searchByInsuranceCompanyCode(changedValue).subscribe(res => {
      this.insuranceCompanyCodeAutoList = of(res['result']);
    })
  }
}
