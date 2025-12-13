import { Observable, of } from 'rxjs';
import { DeliveryChallanService } from './delivery-challan.service';
import { Component, OnInit, Output, Input, EventEmitter, forwardRef } from '@angular/core';
import { DeliveryChallanCreateService } from '../../pages/delivery-challan-create/delivery-challan-create.service';
import { FormGroup, Validators, NG_VALUE_ACCESSOR, NG_VALIDATORS, Validator, ControlValueAccessor, AbstractControl, ValidationErrors } from '@angular/forms';
import { DcCustomerMaster, IdMaster } from 'DeliveryChallanCreate';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
@Component({
  selector: 'app-delivery-challan',
  templateUrl: './delivery-challan.component.html',
  styleUrls: ['./delivery-challan.component.scss'],
  providers: [DeliveryChallanService,EnquiryCommonService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DeliveryChallanComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => DeliveryChallanComponent),
      multi: true
    }
  ]
})
export class DeliveryChallanComponent implements OnInit, ControlValueAccessor, Validator {
  @Output() challanTypeChanges: EventEmitter<object> = new EventEmitter<object>();
  public enquiryNumberAutoList: Observable<(object | string)[]>;
  public requestNumberAutoList: Observable<(object | string)[]>;
  public allotmentNumberList: Observable<(object | string)[]>;
  public enquiryProspectDetailsResponse = [];
  public deliverableChecklistResponse = [];
  public machineDetailsResponse = [];
  @Input()
  public isDealerTransfer: boolean;
  public isAllotment: boolean;
  public isCancel: boolean = false;
  public isApprove: boolean = false;
  public isEdit: boolean = false;
  @Input() isView: boolean = false;
  public todaysServerDate: Date;
  public implementData = [];
  public dcTypes: string[];
  public dcForm: FormGroup;

  constructor(
    private deliveryChallanService: DeliveryChallanService,
    private deliveryChallanCreateService: DeliveryChallanCreateService,
    private enquiryCommonService:EnquiryCommonService
  ) { }

  ngOnInit() {
    this.getDcType();
    this.getServerDate();
    this.dcForm = this.deliveryChallanService.createDeliveryChallanFrom();
    if(!this.isView){
        this.enquiryNumberChanges();
        this.allotmentNumberChanges();
        this.requestNumberChanges();
        this.saveDataAfterFormValid();
        this.clearTocuhedForm();
    }
  }
  private saveDataAfterFormValid() {
      
      this.dcForm.valueChanges.subscribe(changedValue => {
          if(!this.isView){
              let deliveryChallanMainData = this.deliveryChallanCreateService.deliveryChallanMainData;
              deliveryChallanMainData.isDcFormValid = this.dcForm.valid;
              deliveryChallanMainData.deliveryChallanType = this.dcForm.controls.deliveryChallanType.value;
              if (this.dcForm.valid) {
                  
                this.deliveryChallanCreateService.deliveryChallanMainData = {
                  ...deliveryChallanMainData, ...this.dcForm.value, ...{ dcMachineDetailFirst: this.machineDetailsResponse }
                };
                
                if(this.isAllotment){
                    this.deliveryChallanCreateService.deliveryChallanMainData = {
                            ...deliveryChallanMainData,...{ dcMachineDetailFirst: this.implementData }
                    };
                }
              }
          }
        })
    
  }
  private clearTocuhedForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.subscribe(value => {
      if (value && value['key'] === 'mark') {
        this.dcForm.markAllAsTouched();
        return;
      }
      if (value && value['key'] === 'clear') {
        this.dcForm.reset();
        this.machineDetailsResponse = [];
        this.implementData = [];
        this.dcForm.get('enquiry').enable();
        this.dcForm.get('machineAllotment').enable();
        this.dcForm.get('deliveryChallanType').enable();
        this.dcForm.get('deliveryChallanDate').patchValue(this.todaysServerDate)
        return;
      }
    })
  }
  validate(control: AbstractControl): ValidationErrors {
    // throw new Error("Method not implemented.");
    return
  }
  registerOnValidatorChange?(fn: () => void): void {
    // throw new Error("Method not implemented.");
  }
  writeValue(patchObjectData: any): void {    //Used to patch value to form for edit
    console.log("patchObjectDataCREATE ", patchObjectData);
    if (patchObjectData) {
      this.isView = patchObjectData.isView;
      this.isEdit = patchObjectData.isEdit;
      this.isCancel = patchObjectData.isCancel;
      this.isApprove = patchObjectData.isApprove;
      this.isDealerTransfer = patchObjectData.deliveryChallanType.toLowerCase() === 'dealer transfer' ? true : false;
      this.dcForm.patchValue(patchObjectData);
      if(patchObjectData.enquiry!=null){
          this.dcForm.get('enquiryType').patchValue(patchObjectData.enquiry.enquiryType);
          this.dcForm.get('enquiry').patchValue(patchObjectData.enquiry.value);
      }
      if(patchObjectData.machineAllotment != null){
          if (patchObjectData.allotmentDate !== null) {
            this.dcForm.get('allotmentDate').patchValue(new Date(patchObjectData.machineAllotment.allotmentDate));
          }
          this.dcForm.get('allotmentNumber').patchValue(patchObjectData.machineAllotment.allotmentNumber);
      }
      
      if (patchObjectData.deliveryChallanDate !== null) {
        this.dcForm.get('deliveryChallanDate').patchValue(new Date(patchObjectData.deliveryChallanDate));
        this.dcForm.get('gatePassDate').patchValue(new Date(patchObjectData.deliveryChallanDate));
      }

      //this.setAndRemoveValidator();
      if (patchObjectData.machineDetailsConsolidated.length > 0) {
        if(patchObjectData.deliveryChallanType.toLowerCase() === 'only implements'){
            this.implementData = patchObjectData.machineDetailsConsolidated;
            this.implementData.forEach(element => {
                element.itemNumber = element.machineInventory.machineVinMaster.machineMaster.itemNumber;
                element.itemDescription = element.machineInventory.machineVinMaster.machineMaster.itemDescription;
                element.chassisNo = element.machineInventory.machineVinMaster.chassisNo;
                element.engineNumber = element.machineInventory.machineVinMaster.engineNumber;
            })
        }else{
            this.machineDetailsResponse = patchObjectData.machineDetailsConsolidated;//.filter(element => element.machineInventory.machineVinMaster.machineMaster.productGroup.toLowerCase() === 'machinery' && element.deleteFlag === false);
            this.machineDetailsResponse.forEach(element => {
                element.itemNumber = element.machineInventory.machineVinMaster.machineMaster.itemNumber;
                element.itemDescription = element.machineInventory.machineVinMaster.machineMaster.itemDescription;
                element.chassisNo = element.machineInventory.machineVinMaster.chassisNo;
                element.engineNumber = element.machineInventory.machineVinMaster.engineNumber;
            })
        }
      }
      
      if (this.isEdit) {
        this.dcForm.get('deliveryChallanType').disable();
        this.dcForm.get('enquiry').disable();
      }
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
      this.dcForm.disable();
    }
    // throw new Error("Method not implemented.");
  }

  private getServerDate() {
    this.deliveryChallanService.getServerDate().subscribe(res => {
      if (res) {
        this.todaysServerDate = res['result'];
      }
    });
  }
  allotmentNumberChanges() {
    this.dcForm.get('machineAllotment').valueChanges.subscribe(value => {
        if(this.dcForm.controls.machineAllotment.value!=null){  
          this.searchByAllotmentNumber(value)
        }
    })
  }
  private enquiryNumberChanges() {
    this.dcForm.get('enquiry').valueChanges.subscribe(value => {
        if(this.dcForm.controls.enquiry.value!=null && this.dcForm.controls.enquiryType.value==null){
            this.searchByEnquiryNumber(value, this.dcForm.get('deliveryChallanType').value)
        }
    })
  }
  public displayEnquiryNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['enquiryNumber'] : undefined;
  }
  public displayAllotmentNumberFn(selectedOption?: object): string | undefined {
      if (!!selectedOption && typeof selectedOption === 'string') {
        return selectedOption;
      }
      return selectedOption ? selectedOption['code'] : undefined;
    }
  
  public displayRequestNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['requestNumber'] : undefined;
  }
  private getDcType() {
    this.deliveryChallanService.getDcType().subscribe(res => {
      if (res) {
        this.dcTypes = res['result'];
      }
    })
  }
  private searchByEnquiryNumber(chnagedValue: string, dcType: string) {
    /*this.deliveryChallanService.searchByEnquiryNumber(chnagedValue, dcType).subscribe(res => {
      this.enquiryNumberAutoList = of(res['result']);
    })*/
      this.enquiryCommonService.searchEnquiryCode(chnagedValue, 'DC').subscribe(response => {
          this.enquiryNumberAutoList = of(response['result'])
      })    
  }
  enquiryNumberSelected(event: MatAutocompleteSelectedEvent) {
    if (event && event.option) {
      if (event.option.value && event.option.value.customerId) {
        const customer = {} as DcCustomerMaster;
        customer.id = event.option.value.customerId;
        this.deliveryChallanCreateService.deliveryChallanMainData.customerMaster = customer;
      }
      this.getMachineImplementsAndChecklistFromEnquiryNumber(event.option.value.enquiryNumber);
      this.dcForm.get('enquiry').disable();
      this.dcForm.get('deliveryChallanType').disable();
    }
  }
  private searchByAllotmentNumber(chnagedValue: string) {
    this.deliveryChallanService.searchByAllotmentNumber(chnagedValue).subscribe(res => {
      this.allotmentNumberList = of(res['result']);
    })
  }
  allotmentNumberSelected(event: MatAutocompleteSelectedEvent) {
    if (event && event instanceof MatAutocompleteSelectedEvent) {
      const allotment = {} as IdMaster;
      allotment.id = event.option.value.id;
      this.deliveryChallanCreateService.deliveryChallanMainData.machineAllotment = allotment;
      if (event.option.value.customerId) {
          const customer = {} as DcCustomerMaster;
          customer.id = event.option.value.customerId;
          this.deliveryChallanCreateService.deliveryChallanMainData.customerMaster = customer;
      }
      
      this.getDealerAndAllotmentDetailsById(event.option.value.id);
      this.dcForm.get('machineAllotment').disable();
      this.dcForm.get('deliveryChallanType').disable();
      return
    }
    this.deliveryChallanCreateService.deliveryChallanMainData.machineAllotment = {} as IdMaster;
  }
  private getDealerAndAllotmentDetailsById(allotmentId: string) {
    this.deliveryChallanService.getImplementsDetailFromAllotment(allotmentId).subscribe(res => {
      console.log('res',res)  
      if (res) {
          this.implementData = res['result']['machineAllotmentDetails'];//.filter(ele => ele.productGroup.toLowerCase() === 'implements');
          if(this.implementData!=null && this.implementData.length>0){
              this.implementData.forEach(element => {
                  const machineInventory = {} as IdMaster;
                  machineInventory.id = element.machineInventory;
                  element['machineInventory']=machineInventory;
              })
          }
          //this.dcForm.patchValue(res['result']['enquiryProspectDetailsResponse']);
          this.dcForm.get('allotmentDate').patchValue(new Date(res['result']['enquiryProspectDetailsResponse']['allotmentDate']));
          
          this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.next(res['result']);
        }
    })
  }
  requestNumberSelected(event) {
    if (event && event.option) {
      this.getMachineImplementsAndChecklistFromRequestNumber(event.option.value.requestNumber);
    }
  }
  private requestNumberChanges() {
    this.dcForm.get('dealerMachineTransfer').valueChanges.subscribe(changed => {
      if (changed) {
        this.searchByRequestNumber(changed);
      }
    })
  }
  private searchByRequestNumber(changedValue: string) {
    this.deliveryChallanService.searchByRequestNumber(changedValue).subscribe(res => {
      this.requestNumberAutoList = of(res['result']);
    })
  }
  private getMachineImplementsAndChecklistFromEnquiryNumber(enqNumber: string) {
    this.deliveryChallanService.getMachineDetailsFromEnquiry(enqNumber).subscribe(res => {
      if (res) {
        this.machineDetailsResponse = res['result']['machineDetailsResponse'];//.filter(ele => ele.productGroup.toLowerCase() === 'machinery');
        if(this.machineDetailsResponse!=null && this.machineDetailsResponse.length>0){
            this.machineDetailsResponse.forEach(element => {
                const machineInventory = {} as IdMaster;
                machineInventory.id = element.machineInventory;
                element['machineInventory']=machineInventory;
            })
        }
        
        this.dcForm.patchValue(res['result']['enquiryProspectDetailsResponse']);
        const allotment = {} as IdMaster;
        allotment.id = res['result']['enquiryProspectDetailsResponse']['allotmentId'];
        this.deliveryChallanCreateService.deliveryChallanMainData.machineAllotment = allotment;
        this.dcForm.get('machineAllotment').patchValue({id:res['result']['enquiryProspectDetailsResponse']['allotmentId']});
        this.dcForm.get('allotmentDate').patchValue(new Date(res['result']['enquiryProspectDetailsResponse']['allotmentDate']));
        
        this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.next(res['result']);
      }
    })
  }
  private getMachineImplementsAndChecklistFromRequestNumber(reqNumber: string) {
    this.deliveryChallanService.getMachineDetailsFromRequest(reqNumber).subscribe(res => {
      if (res) {
        this.machineDetailsResponse = res['result']['machineDetailsResponse'];//.filter(ele => ele.productGroup.toLowerCase() === 'machinery');

        if(this.machineDetailsResponse!=null && this.machineDetailsResponse.length>0){
          this.machineDetailsResponse.forEach(element => {
              const machineInventory = {} as IdMaster;
              machineInventory.id = element.machineInventory;
              element['machineInventory']=machineInventory;
          })
      }
        this.dcForm.get('machineAllotment').patchValue({id:res['result']['allotmentDetails']['id']});
        this.dcForm.patchValue(res['result']['allotmentDetails']);
        this.dcForm.get('allotmentDate').patchValue(new Date(res['result']['allotmentDetails']['allotmentDate']));
        this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.next(res['result']);
      }
    })
  }

  dcTypeSelection(event) {
    if (event && event.value) {
      this.dcForm.get('enquiry').enable();
      this.dcForm.get('machineAllotment').enable();
      this.isDealerTransfer = event.value.toLowerCase() === 'dealer transfer' ? true : false;
      this.isAllotment = event.value.toLowerCase() === 'only implements' ? true : false;
      this.setAndRemoveValidator();
      if(this.isDealerTransfer){
        this.searchByRequestNumber('');
      }
      this.challanTypeChanges.emit(event)
    }
  }
  private setAndRemoveValidator() {
    if (this.isDealerTransfer) {
      this.dcForm.get('dealerMachineTransfer').reset();
      this.dcForm.get('machineAllotment').reset();
      this.dcForm.get('enquiry').setValidators(Validators.nullValidator);
      this.dcForm.get('enquiry').updateValueAndValidity();
      this.dcForm.get('machineAllotment').setValidators(Validators.nullValidator);
      this.dcForm.get('machineAllotment').updateValueAndValidity();
      this.dcForm.get('dealerMachineTransfer').setValidators(Validators.required);
      this.dcForm.get('dealerMachineTransfer').updateValueAndValidity();
      this.dcForm.get('enquiryType').setValidators(Validators.nullValidator);
      this.dcForm.get('enquiryType').updateValueAndValidity();
      return;
    } else if (this.isAllotment) {
      this.dcForm.get('machineAllotment').setValidators(Validators.required);
      this.dcForm.get('machineAllotment').updateValueAndValidity();
      this.dcForm.get('enquiry').reset();
      this.dcForm.get('enquiry').setValidators(Validators.nullValidator);
      this.dcForm.get('enquiry').updateValueAndValidity();
      this.dcForm.get('dealerMachineTransfer').reset();
      this.dcForm.get('dealerMachineTransfer').setValidators(Validators.nullValidator);
      this.dcForm.get('dealerMachineTransfer').updateValueAndValidity();
    } else {
      this.dcForm.get('enquiry').reset();
      this.dcForm.get('enquiry').setValidators(Validators.required);
      this.dcForm.get('enquiry').updateValueAndValidity();

      this.dcForm.get('dealerMachineTransfer').reset();
      this.dcForm.get('dealerMachineTransfer').setValidators(Validators.nullValidator);
      this.dcForm.get('dealerMachineTransfer').updateValueAndValidity();

      this.dcForm.get('machineAllotment').reset();
      this.dcForm.get('machineAllotment').setValidators(Validators.nullValidator);
      this.dcForm.get('machineAllotment').updateValueAndValidity();

      this.dcForm.get('enquiryType').setValidators(Validators.required);
      this.dcForm.get('enquiryType').updateValueAndValidity();
    }
  }

}
