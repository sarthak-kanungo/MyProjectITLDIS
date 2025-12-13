import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, FormArray, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

import * as uuid from 'uuid';
import { ValidateInt, CustomValidators } from '../../../../../utils/custom-validators';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { PoMachineDetailsDataHandleService } from './po-machine-details-data-handle.service';
import { PurchaseOrderCreateService } from '../../pages/purchase-order-create/purchase-order-create.service';
import { ToastrService } from 'ngx-toastr';
import { SalesPurchaseOrderCreateService } from '../sales-purchase-order-create/sales-purchase-order-create.service';

@Injectable()
export class PoMachineDetailsService {
  public machineDetailsForm: FormGroup;
  public getTotalBaseAmountAndGst$: BehaviorSubject<object> = new BehaviorSubject<object>(null);
  private readonly getDiscountTypeListUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getPoDiscountType}`;
  public isApproveReject: boolean = false;
  public isView = false;
  private readonly tcsPercListUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getTcsPercList}`;
  private readonly tcsPercentageListUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getTcsPercentageList}`;
  public flagPercentage: boolean = false;
  public flagAmount: boolean = false;
  public sameValueAmount: any;
  public sameValuePercentage: any;
  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private purchaseOrderCreateService: PurchaseOrderCreateService,
    private poMachineDetailsDataHandleService: PoMachineDetailsDataHandleService,
    private toastrService: ToastrService,
    private service:SalesPurchaseOrderCreateService
  ) {
    this.activatedRoute.paramMap.subscribe(params => {
      if (params && params.has('approveId')) {
        this.isApproveReject = true;
      }
      if (params && params.has('viewId')) {
        this.isView = true;
      }
    })

  }

  public createMachineDetailsForm(isSalesAdminPerson?: boolean) {
    if (this.isView)
      return this.createMachineDetailsFormIsView(isSalesAdminPerson);
    else
      return this.createMachineDetailsFormIsNotView(isSalesAdminPerson);
  }
  createMachineDetailsFormIsView(isSalesAdminPerson?: boolean) {
    this.machineDetailsForm = this.formBuilder.group({
      basicAmount: [{ value: null, disabled: true }, Validators.required],
      totalGstAmount: [{ value: null, disabled: true }, Validators.required],
      totalAmount: [{ value: null, disabled: true }, Validators.required],
      tcsPercent: [{ value: null, disabled: true }, Validators.required],
      tcsValue: [{ value: null, disabled: true }, Validators.required],
      isApprovalRequired:[false],
      machineDetails: this.formBuilder.array([this.isApproveReject ? this.initApproveMachineForm(isSalesAdminPerson) : this.initMachineForm()])
    });
    this.totalAmountValueChanges();
    return this.machineDetailsForm;
  }
  createMachineDetailsFormIsNotView(isSalesAdminPerson?: boolean) {
    this.machineDetailsForm = this.formBuilder.group({
      basicAmount: [{ value: null, disabled: true }, Validators.required],
      totalGstAmount: [{ value: null, disabled: true }, Validators.required],
      totalAmount: [{ value: null, disabled: true }, Validators.required],
      tcsPercent: [{ value: null, disabled: true }, Validators.required],
      tcsValue: [{ value: null, disabled: true }, Validators.required],
      isApprovalRequired:[false],
      machineDetails: this.formBuilder.array([this.isApproveReject ? this.initApproveMachineForm(isSalesAdminPerson) : this.initMachineForm()])
    });
    this.totalAmountValueChanges();
    return this.machineDetailsForm;
  }
  private totalAmountValueChanges() {
    this.machineDetailsForm.get('totalAmount').valueChanges.subscribe(value => {
      this.purchaseOrderCreateService.getPoTotalAmountForExposure$.next(value)
    })
  }
  public initApproveMachineForm(isSalesAdmin: boolean) {
    let machineForm = this.formBuilder.group({
      uuid: [uuid.v4()],
      id: [{ value: null, disabled: true }],
      machineMaster: [{ value: null, disabled: true }, Validators.compose([Validators.required, this.selectFromList()])],
      itemDescription: [{ value: null, disabled: true }],
      variant: [{ value: null, disabled: true }],
      colour: [{ value: null, disabled: true }],
      quantity: [{ value: null, disabled: true }, Validators.compose([Validators.required, ValidateInt, CustomValidators.validateNumberExceptStartingZero])],
      accpacStockQuantity: [{ value: null, disabled: true }],
      accpacLocationCode: [{ value: null, disabled: true }],
      unitPrice: [{ value: null, disabled: true }],
      amount: [{ value: null, disabled: true }],
      // discountAmount: [{ value: null, disabled: !isSalesAdmin }, Validators.compose([ValidateInt,])],
      discountAmount: [{ value: null, disabled: !isSalesAdmin },Validators.compose([Validators.pattern('(^([0-9]{0,9})(\.[0-9]{1,2})?$)')])],

      // discountPercentage: [{ value: null, disabled: !isSalesAdmin }, Validators.compose([ValidateInt])],
      discountPercentage: [{ value: null, disabled: !isSalesAdmin },Validators.compose([Validators.pattern('(^100(\.0{1,2})?$)|(^([1-9]([0-9])?|0)(\.[0-9]{1,2})?$)')])],

      discountType: [{ value: null, disabled: !isSalesAdmin }],

      isSelected: [{ value: false, disabled: false }],
      igst: [null]
    });
    this.itemNumberValueChanges(machineForm);
    this.quantityValueChanges(machineForm);
    this.unitPriceValueChanges(machineForm);
    this.amountValueChanges(machineForm);
    this.discountAmountValueChanges(machineForm);
    this.discountPercentageValueChanges(machineForm);
    return machineForm;
  }
  public initMachineForm() {
    if (this.isView) {
      let machineForm = this.formBuilder.group({
        uuid: [uuid.v4()],
        id: [{ value: null, disabled: true }],
        machineMaster: [{ value: null, disabled: true }, Validators.compose([Validators.required, this.selectFromList()])],
        itemDescription: [{ value: null, disabled: true }],
        variant: [{ value: null, disabled: true }],
        colour: [{ value: null, disabled: true }],
        quantity: [{ value: null, disabled: true }, Validators.compose([Validators.required, ValidateInt, CustomValidators.validateNumberExceptStartingZero])],
        // accpacStockQuantity: [{ value: null, disabled: true }],
        // accpacLocationCode: [{ value: null, disabled: true }],
        unitPrice: [{ value: null, disabled: true }],
        amount: [{ value: null, disabled: true }],
        // discountAmount: [{ value: null, disabled: true }, Validators.compose([ValidateInt])],
        discountAmount: [{ value: null, disabled: true},Validators.compose([Validators.pattern('^\d{0,6}(\.\d{0,2})?$')])],

        // discountPercentage: [{ value: null, disabled: true }, Validators.compose([ValidateInt])],
        discountPercentage: [{ value: null, disabled: true },Validators.compose([Validators.pattern('^\d{0,2}(\.\d{0,2})?$')])],

        discountType: [{ value: null, disabled: true }],
        isSelected: [{ value: false, disabled: true }],
        igst: [null]
      });
      this.itemNumberValueChanges(machineForm);
      this.quantityValueChanges(machineForm);
      this.unitPriceValueChanges(machineForm);
      this.amountValueChanges(machineForm);
      return machineForm;
    }
    else {
      let machineForm = this.formBuilder.group({
        uuid: [uuid.v4()],
        id: [{ value: null, disabled: true }],
        machineMaster: [{ value: null, disabled: false }, Validators.compose([Validators.required, this.selectFromList()])],
        itemDescription: [{ value: null, disabled: true }],
        variant: [{ value: null, disabled: true }],
        colour: [{ value: null, disabled: true }],
        quantity: [{ value: null, disabled: false }, Validators.compose([Validators.required, ValidateInt, CustomValidators.validateNumberExceptStartingZero])],
        // accpacStockQuantity: [{ value: null, disabled: true }],
        // accpacLocationCode: [{ value: null, disabled: true }],
        unitPrice: [{ value: null, disabled: true }],
        amount: [{ value: null, disabled: true }],
        isSelected: [{ value: false, disabled: false }],
        igst: [null]
      });
      this.itemNumberValueChanges(machineForm);
      this.quantityValueChanges(machineForm);
      this.unitPriceValueChanges(machineForm);
      this.amountValueChanges(machineForm);
      return machineForm;
    }

  }
  private itemNumberValueChanges(machineForm) {
   
    machineForm.controls.machineMaster.valueChanges.subscribe(res => {
      if (res) {
        console.log(this.service.createForm)
        this.poMachineDetailsDataHandleService.emitItemNumberChange(res);
      }
    })
  }
  private discountAmountValueChanges(machineForm) {
    machineForm.controls.discountAmount.valueChanges.subscribe(res => {
      if (this.sameValueAmount != res) {
        // if (this.flagAmount == false) {
        //   if (this.flagPercentage == false) {
        if (res == null) {
          this.sameValueAmount = 0;
        }
        else {
          this.sameValueAmount = res;
        }
        this.flagAmount = true;
        let discountAmount = parseFloat(machineForm.getRawValue().discountAmount);
        let discountPercentage = parseFloat(machineForm.getRawValue().discountPercentage);
        if(isNaN(discountAmount)) {
					discountAmount = 0;
				}
				if(isNaN(discountPercentage)) {
					discountPercentage = 0;
				}
        let quantity = typeof machineForm.getRawValue().quantity === 'string' ? parseFloat(machineForm.getRawValue().quantity) : machineForm.getRawValue().quantity;
        let unitPrice = typeof machineForm.getRawValue().unitPrice === 'string' ? parseFloat(machineForm.getRawValue().unitPrice) : machineForm.getRawValue().unitPrice;

        let amount = (quantity * unitPrice) - (quantity * discountAmount) - (((quantity * unitPrice) - (quantity * discountAmount))*(discountPercentage / 100));
        // let amount = (quantity * unitPrice) - (discountAmount || 0);

        machineForm.controls.amount.patchValue(parseFloat(amount.toString()).toFixed(2));   //discAmount*Qty because discount is for per unit item
        // if (unitPrice != undefined && unitPrice != null && unitPrice != NaN &&
        //   unitPrice != 0 && unitPrice!="NaN") {
        // if (discountAmount != undefined && discountAmount != null && discountAmount != NaN &&
        //   discountAmount != 0) {
        //   this.sameValuePercentage = ((Number(discountAmount) / (Number(unitPrice)*Number(quantity))) * 100).toFixed(2);
        //   if (this.sameValuePercentage != undefined && this.sameValuePercentage != null &&
        //     this.sameValuePercentage != "NaN" && this.sameValuePercentage != NaN) {
        //     machineForm.controls.discountPercentage.patchValue(this.sameValuePercentage);
        //   }
        //   else{
        //     machineForm.controls.discountPercentage.patchValue(0);
        //   }
        // }
        // }
      }
    })

  }
  private discountPercentageValueChanges(machineForm) {

    machineForm.controls.discountPercentage.valueChanges.subscribe(res => {
      if (this.sameValuePercentage != res) {
        // if (this.flagPercentage == false) {
        //   if (this.flagAmount == false) {
        if (res == null) {
          this.sameValuePercentage = 0;
        }
        else {
          this.sameValuePercentage = res;
        }
        this.flagPercentage = true;
        let discountAmount = parseFloat(machineForm.getRawValue().discountAmount);
        let discountPercentage = parseFloat(machineForm.getRawValue().discountPercentage);
        if(isNaN(discountAmount)) {
					discountAmount = 0;
				}
				if(isNaN(discountPercentage)) {
					discountPercentage = 0;
				}
        let quantity = typeof machineForm.getRawValue().quantity === 'string' ? parseFloat(machineForm.getRawValue().quantity) : machineForm.getRawValue().quantity;
        let unitPrice = typeof machineForm.getRawValue().unitPrice === 'string' ? parseFloat(machineForm.getRawValue().unitPrice) : machineForm.getRawValue().unitPrice;

        let amount = (quantity * unitPrice) - (quantity * discountAmount) - (((quantity * unitPrice) - (quantity * discountAmount))*(discountPercentage / 100));
        // let amount = (quantity * unitPrice) - (((quantity * unitPrice)) * ((discountPercentage || 0) / 100));

        machineForm.controls.amount.patchValue(parseFloat(amount.toString()).toFixed(2));   //discAmount*Qty because discount is for per unit item
        // if (unitPrice != undefined && unitPrice != null && unitPrice != NaN &&
        //   unitPrice != 0 && unitPrice!="NaN") {
        // if (discountPercentage != undefined && discountPercentage != null && discountPercentage != NaN &&
        //   discountPercentage != 0) {
        //   this.sameValueAmount = ((Number(discountPercentage) * Number(unitPrice) *Number(quantity)) / 100).toFixed(2);
        //   if (this.sameValueAmount != undefined && this.sameValueAmount != null &&
        //     this.sameValueAmount != "NaN" && this.sameValueAmount != NaN ) {
        //     machineForm.controls.discountAmount.patchValue(this.sameValueAmount);
        //   }
        //   else{
        //     machineForm.controls.discountAmount.patchValue(0.00);
        //   }
        // }
        // }
      }
    })
  }

  private quantityValueChanges(machineForm) {
    machineForm.controls.quantity.valueChanges.subscribe(res => {
      if (machineForm.getRawValue().quantity !== null) {
        this.calculateRowAmount(machineForm);
      }
    })
  }
  private unitPriceValueChanges(machineForm) {
    machineForm.controls.unitPrice.valueChanges.subscribe(res => {
      if (machineForm.getRawValue().unitPrice !== null) {
        this.calculateRowAmount(machineForm);
      };
    })
  }
  private calculateRowAmount(machineForm) {
    if (machineForm.getRawValue().quantity !== (null)) {
      let unitPrice = typeof machineForm.getRawValue().unitPrice === 'string' ? parseFloat(machineForm.getRawValue().unitPrice) : machineForm.getRawValue().unitPrice;
      machineForm.controls.amount.patchValue(((unitPrice) * parseFloat(machineForm.getRawValue().quantity)))
      this.calcualateTotalQuantity();
    } else {
      machineForm.controls.amount.patchValue(typeof machineForm.getRawValue().unitPrice === 'string' ? parseFloat(machineForm.getRawValue().unitPrice) : machineForm.getRawValue().unitPrice)
    }
  }
  private calcualateTotalQuantity() {
    let totalValue = this.machineDetailsForm.getRawValue().machineDetails.reduce((initialValue, arrayValue) => {
      let orderQuantity = typeof arrayValue.quantity === 'string' ? parseFloat(arrayValue.quantity) : arrayValue.quantity;
      initialValue = initialValue + orderQuantity;
      return initialValue;
    }, 0)
    this.purchaseOrderCreateService.getTotalMachineDetailsQuantity$.next(totalValue);
  }
  private amountValueChanges(machineForm) {
    machineForm.controls.amount.valueChanges.subscribe(changed => {
      this.calcualteTotalAmountAndGst();
    })
  }
  private calcualteTotalAmountAndGst() {
    this.getTotalBaseAmountAndGst$.next({
      totalBase: this.calculateBaseAmount(),
      totalGst: this.calculateGstTotal()
    });
  }
  private calculateBaseAmount() {
    return this.machineDetailsForm.getRawValue().machineDetails.reduce((initialValue, arrayValue) => {
      if(typeof arrayValue.amount==="string")
      {
        arrayValue.amount=Number(arrayValue.amount)
      }
      if(typeof initialValue==="string")
      {
        initialValue=Number(initialValue)
      }
      initialValue = initialValue + arrayValue.amount;
      return initialValue;
    }, 0)
  }
  private calculateGstTotal() {
    return this.machineDetailsForm.getRawValue().machineDetails.reduce((initialValue, arrayValue) => {
      initialValue = initialValue + (arrayValue.amount * (arrayValue.igst / 100));
      return initialValue;
    }, 0)
  }
  public addMachineDetailsRow(): string {
    if (this.machineDetailsForm.controls.machineDetails.valid) {
      let machineControl = <FormArray>this.machineDetailsForm.controls.machineDetails;
      let newForm = this.isApproveReject ? this.initApproveMachineForm(false) : this.initMachineForm();
      machineControl.push(newForm);
      return newForm.controls.uuid.value;
    } else {
      this.toastrService.error("Fill Required fields");
    }
  }
  public deleteRow() {
    let selectedToDelete = [];
    selectedToDelete = this.machineDetailsForm.value.machineDetails.filter(val => val.isSelected === true);
    if ((this.machineDetailsForm.value.machineDetails.length - selectedToDelete.length) >= 1) {
      let machineDetails = this.machineDetailsForm.controls.machineDetails as FormArray;
      let nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
      machineDetails.clear();
      nonSelected.forEach(el => machineDetails.push(el));
      this.calcualteTotalAmountAndGst();
      this.calcualateTotalQuantity();
    };
  }
  public getDiscountTypeList() {
    return this.httpClient.get(this.getDiscountTypeListUrl)
  }
  private selectFromList(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control && typeof control.value === 'object') {
        return null;
      }
      return { selectFromList: true };
    }
  }
  public tcsPercList() {
    return this.httpClient.get(this.tcsPercListUrl);
  }

  public tcsPercentageList() {
    return this.httpClient.get(this.tcsPercentageListUrl)
  }
}
