import { FormGroup } from '@angular/forms';
import { Observable, BehaviorSubject } from 'rxjs';
import { Component, OnInit, ChangeDetectionStrategy, Input, ChangeDetectorRef, Output, EventEmitter } from '@angular/core';

import { SparesPurchaseOrderWebService } from './spares-purchase-order-web.service';
import { PurchaseOrderCreatePagePresenter } from '../purchase-order-create-page/purchase-order-create-page.presenter';
import { SparesPOPartDetails, SparePartMaster } from '../../domain/spares-purchase-order.domain';
import { MatOptionSelectionChange, MatAutocompleteSelectedEvent } from '@angular/material';

@Component({
  selector: 'app-purchase-order',
  templateUrl: './purchase-order.component.html',
  styleUrls: ['./purchase-order.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparesPurchaseOrderWebService]
})
export class PurchaseOrderComponent implements OnInit {
  public dealerOutstanding: BehaviorSubject<(string | object)[]> = new BehaviorSubject<(string | object)[]>(null);
  public itemDetailsDataFromJobCard: SparesPOPartDetails = {} as SparesPOPartDetails;
  @Input() markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public purchaseOrderCreate: FormGroup;
  @Input() public isEdit: boolean;
  @Input() public isView: boolean;
  @Input() public isApprove: boolean;
  @Input() public editRecordId: number;
  @Input() poTotalForm: FormGroup;
  kaiMonthlyOrder: boolean = false;
  public supplierNameList: Observable<(string | object)[]>;
  // public orderPlanningList: Observable<(string | object)[]>;
  public poNumberAutoList: Observable<(string | object)[]>;
  public dealerCodeAutoList: Observable<(string | object)[]>;
  public jobCardNoAutoList: Observable<(string | object)[]>;
  public opAutoSearchList: Observable<(string | object)[]>;
  private readonly FRIGHT_BORNE_BY_LIMIT: number = 25000;
  private readonly FRIGHT_BORNE_BY_DEALER: string = 'DEALER';
  private readonly FRIGHT_BORNE_BY_KAI: string = 'KAI';
  public modeOfTransportList = [];
  public kaiStatusHeading = [];
  public supplierTypeList = [];
  public transporterList = [];
  public orderTypeList = [];
  public priceTypeList = [];

  public isMonthlyPo: boolean;
  public isEmergencyPo: boolean;
  public isMachineDownPo: boolean;
  public isKaiSupplier: boolean;
  public isCoDealerPo: boolean;
  public isCoDealerOilPo: boolean;

  public isOtherPo: boolean;
  public isFocPo: boolean;
  public isOilAndLubricantPo: boolean;
  showOrderPlanningDetails: boolean = false;
  @Output() showAddrowDeleteRowandUploadExcel: EventEmitter<any> = new EventEmitter();
  //  @Output() showAddrowDeleteRowandUploadExcel:boolean=false;
  constructor(
    private _changeDetectorRef: ChangeDetectorRef,
    private sparesPurchaseOrderWebService: SparesPurchaseOrderWebService,
    private purchaseOrderCreatePagePresenter: PurchaseOrderCreatePagePresenter
  ) { }

  ngOnInit() {
    this.kaiStatusHeading = ['Credit Limit', 'Current O/s', 'Payment Under Process', 'Available Limit', 'Overdue O/s', 'Order Under Process', 'Net Amt. Payable'];
    this.getDealerOutstandingDetails();
    this.formFieldValueChanges();
    this.getModeOfTransport();
    this.getTransportNames();
    this.getSupplierType();
    this.calculateNetAmountPayableFromTotalPOAmount();
    if (this.isView || this.isEdit || this.isApprove) {
      if (this.purchaseOrderCreate.controls.coDealerMaster)
        this.purchaseOrderCreate.controls.coDealerMaster.disable();
      if (this.purchaseOrderCreate.controls.priceType)
        this.purchaseOrderCreate.controls.priceType.disable();
      if (this.purchaseOrderCreate.controls.serviceJobCard)
        this.purchaseOrderCreate.controls.serviceJobCard.disable();
    }
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    })
  }
  public compareOrderTypeFn = this.sparesPurchaseOrderWebService.compareOrderTypeFn;
  public displaySupplierNameFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['supplierName'] : undefined;
  }
  public displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['dealerCode'] : undefined;
  }
  public displayPlanningNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['purchaseOrderNumber'] : undefined;
  }
  // display Op Number
  displayOrderPlanningNo(selectedOption?: Object):string| undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['opsNo'] : undefined;
  }
  public displayJobCardNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['jobcardNo'] : undefined;
  }
  private convertToTwoDigitsAfterDecimal(val: any): any {
    // return val!=null && parseFloat(val.toFixed(2));
    return val != null && parseFloat(val).toFixed(2);
  }
  // Selected Order Planning Sheet No selectOrderPlanningNo
  selectOrderPlanningNo(event: any) {
    let itemResponse = [];
    let id = event.option.value.id
    this.purchaseOrderCreatePagePresenter.getItemDetailsForm.clear();
    this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails();

    this.sparesPurchaseOrderWebService.getOrderPlanningSheetDetails(id, null, '').subscribe(res => {
      this.purchaseOrderCreate.get('orderType').patchValue(res.orderType);
      this.purchaseOrderCreate.get('orderType').disable();
      // this.purchaseOrderCreate.get('opSheetNoId').patchValue(res.orderTypeId);
      this.purchaseOrderCreate.get('orderTypeId').patchValue(res.orderTypeId);
      if (this.purchaseOrderCreate.get('orderPlanningNo').value == null || this.purchaseOrderCreate.get('orderPlanningNo').value == '') {
        //  this.showAddrowDeleteRowandUploadExcel=true;
        this.showAddrowDeleteRowandUploadExcel.emit(false);
      } else {
        this.showAddrowDeleteRowandUploadExcel.emit(true);
        // this.showAddrowDeleteRowandUploadExcel=true;
      }
      if (res) {
        itemResponse = res['itemDetailsList'];
        let items: Array<SparesPOPartDetails> = itemResponse;
        let totalBaseAmount: number = this.poTotalForm.controls.totalBasicAmount.value;
        let totalGSTAmount: number = this.poTotalForm.controls.totalGstAmount.value;
        let totalAmount: number = this.poTotalForm.controls.totalPOAmount.value;
        if (items != null && items.length > 0) {
          items.forEach(data => {
            if (data) {
              // console.log(this.purchaseOrderCreatePagePresenter.getItemDetailsForm,'ietmshsh')
              // this.purchaseOrderCreatePagePresenter.getItemDetailsForm.disable();
              this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(data, true);

              totalBaseAmount = (totalBaseAmount == null ? 0 : totalBaseAmount) + data.baseAmount;
              totalGSTAmount = (totalGSTAmount == null ? 0 : totalGSTAmount) + data.gstAmount;
              totalAmount = totalBaseAmount + totalGSTAmount;
            } else {

            }
          })
          console.log(totalGSTAmount,'totalGSTAmount')
          this.poTotalForm.controls.totalBasicAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalBaseAmount));
          this.poTotalForm.controls.totalGstAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalGSTAmount));
          this.poTotalForm.controls.totalPOAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
        }
        // this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(res['itemDetailsList'])
      }
    })
  }
  private formFieldValueChanges() {
    this.supplierTypeChanges();
    this.orderTypeChanges();
    this.supplierNameChanges();
    this.jobCardNumberChanges();
    this.dealerCodeValueChanges();
    this.autoSearchOpNo();
  }
  private calculateNetAmountPayableFromTotalPOAmount() {
    this.purchaseOrderCreatePagePresenter.sendTotalPOAmountToKaiOutStanding.subscribe(totalPOAmount => {
      /*const poAmtTotal = typeof totalPOAmount === 'string' ? parseInt(totalPOAmount) : totalPOAmount;
      const overdue = typeof this.purchaseOrderCreate.getRawValue().overduesOutStanding === 'string' ? parseInt(this.purchaseOrderCreate.getRawValue().overduesOutStanding) : this.purchaseOrderCreate.getRawValue().overduesOutStanding;
      const paymentUnderProcess = typeof this.purchaseOrderCreate.getRawValue().paymentUnderProcess === 'string' ? parseInt(this.purchaseOrderCreate.getRawValue().paymentUnderProcess) : this.purchaseOrderCreate.getRawValue().paymentUnderProcess;
      const availLimit = typeof this.purchaseOrderCreate.getRawValue().availableLimit === 'string' ? parseInt(this.purchaseOrderCreate.getRawValue().availableLimit) : this.purchaseOrderCreate.getRawValue().availableLimit;

      const netOverdue = (overdue - paymentUnderProcess) as number;
      const amountPayable = Math.max(netOverdue, (poAmtTotal - availLimit));
      const netAmountPayable = Math.max(netOverdue, amountPayable);
      if (this.dealerOutstanding.value && this.dealerOutstanding.value[0]) {
        this.dealerOutstanding.value[0]['netAmountPayable'] = netAmountPayable;
      }
      this.purchaseOrderCreate.get('netAmountPayable').patchValue(netAmountPayable);*/
      let amountPayable: any = ((this.purchaseOrderCreate.get('availableLimit').value == null ? 0 : this.purchaseOrderCreate.get('availableLimit').value) -
        (this.purchaseOrderCreate.get('ordersUnderProcess').value == null ? 0 : this.purchaseOrderCreate.get('ordersUnderProcess').value) -
        (totalPOAmount == null ? 0 : totalPOAmount)).toFixed(2);
      let netAmount: any = (Math.max((this.purchaseOrderCreate.get('overduesOutStanding').value == null ? 0 : this.purchaseOrderCreate.get('overduesOutStanding').value), amountPayable)).toFixed(2);
      this.purchaseOrderCreate.get('netAmountPayable').patchValue(netAmount);
      /* toFixed(2) is added by vinay to fix no, two place of decimal*/
      let obj = [{
        creditLimit: this.purchaseOrderCreate.get('creditLimit').value,
        currentOutStanding: this.purchaseOrderCreate.get('currentOutStanding').value,
        paymentUnderProcess: this.purchaseOrderCreate.get('paymentUnderProcess').value,
        availableLimit: this.purchaseOrderCreate.get('availableLimit').value,
        overDuesOutStanding: this.purchaseOrderCreate.get('overduesOutStanding').value,
        orderUnderProcess: this.purchaseOrderCreate.get('ordersUnderProcess').value,
        netPayableAmount: this.purchaseOrderCreate.get('netAmountPayable').value
      }]

      this.dealerOutstanding.next(obj);
      this.checkFreightBorneBy(totalPOAmount);
      this._changeDetectorRef.markForCheck();
    })
  }
  private checkFreightBorneBy(totalPOAmount: number) {
    if (totalPOAmount) {
      const orderType = this.purchaseOrderCreate.get('orderType').value.orderType;
      switch (orderType.toLowerCase()) {
        case 'kai monthly order':
          this.freightBorneByForMonthly(totalPOAmount);
          break;
        case 'kai emergency order':
        case 'kai machine down order':
          this.purchaseOrderCreate.get('freightBorneBy').patchValue(this.FRIGHT_BORNE_BY_DEALER);
          this.purchaseOrderCreate.get('freightBorneBy').disable();
          break;
        case 'free of cost(foc)':
          this.purchaseOrderCreate.get('freightBorneBy').patchValue(this.FRIGHT_BORNE_BY_KAI);
          this.purchaseOrderCreate.get('freightBorneBy').disable();
          break;
        case 'oil and lubricant':
        case 'co-dealer oil order':
        case 'co-dealer order':
        case 'other suppliers':
          this.purchaseOrderCreate.get('freightBorneBy').enable();
          break;
        default:
          this.purchaseOrderCreate.get('freightBorneBy').disable();
          break;
      }
    }
  }

  public clearItemDetailFormAndAddOneRow(event: MatOptionSelectionChange) {

    if (event && event.source) {
      this.purchaseOrderCreatePagePresenter.getItemDetailsForm.clear();
      this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails();
      this.purchaseOrderCreatePagePresenter.getPoTotalForm.get('totalBasicAmount').reset();
      this.purchaseOrderCreatePagePresenter.getPoTotalForm.get('totalGstAmount').reset();
      this.purchaseOrderCreatePagePresenter.getPoTotalForm.get('totalPOAmount').reset();
      // this.purchaseOrderCreatePagePresenter.getPoTotalForm.reset();
      this.purchaseOrderCreatePagePresenter.orderTypeChangeEvent.next(event.source.value.orderType);
      if (event.source.value.orderType == 'KAI Monthly Order') {
        this.kaiMonthlyOrder = true;
      } else {
        this.kaiMonthlyOrder = false;
      }
      this._changeDetectorRef.markForCheck();
    }
  }
  private supplierTypeChanges() {
    this.purchaseOrderCreate.get('supplierType').valueChanges.subscribe(value => {
      if (value) {
        this.checkSupplierType(value);
        this.getOrderTypesFromSupplierType(value);
      }
    })
  }
  private checkSupplierType(supplierType: string) {
    this.isKaiSupplier = supplierType && supplierType.toLowerCase() === 'kai' ? true : false;
    if (this.isKaiSupplier === true) {
      this.showOrderPlanningDetails = true;
    } else {
      this.showOrderPlanningDetails = false;
      this.purchaseOrderCreate.get('orderPlanningNo').reset();
      this.purchaseOrderCreate.get('orderType').reset();
      this.purchaseOrderCreate.get('orderPlanningNo').enable()
      this.purchaseOrderCreate.get('orderType').enable();
    }
    if (this.purchaseOrderCreate.get('orderPlanningNo').value == null || this.purchaseOrderCreate.get('orderPlanningNo').value == '') {
      //  this.showAddrowDeleteRowandUploadExcel=true;
      this.showAddrowDeleteRowandUploadExcel.emit(false);
      // this.purchaseOrderCreatePagePresenter.
    } else {
      this.showAddrowDeleteRowandUploadExcel.emit(true);

    }
  }
  private dealerCodeValueChanges() {
    this.purchaseOrderCreate.get('coDealerMaster').valueChanges.subscribe(value => {
      if (value) {
        this.getDealerCodeAutocompleteList(value);
      }
    });
  }
  public setGSTOnSupplierChange(event: MatAutocompleteSelectedEvent) {
    if (event && event.option.value) {
      this.purchaseOrderCreate.get('gstNumber').patchValue(event.option.value.gstNumber);
    }
  }
  public dealerCodeSelected(event: MatAutocompleteSelectedEvent) {
    if (event && event.option.value) {
      this.purchaseOrderCreate.get('coDealerName').patchValue(event.option.value.coDealerName);
      this.purchaseOrderCreate.get('gstNumber').patchValue(event.option.value.gstNumber);
    }
  }
  private getDealerCodeAutocompleteList(searchKey: string) {
    this.dealerCodeAutoList = this.sparesPurchaseOrderWebService.getDealerCodeAutocompleteList(searchKey)
  }
  private orderTypeChanges() {
    this.purchaseOrderCreate.get('orderType').valueChanges.subscribe(value => {
      if (value && value['orderType']) {
        this.checkOrderType(value['orderType']);
        if (this.purchaseOrderCreate.get('supplierName')) {
          this.purchaseOrderCreate.get('supplierName').reset();
        }
        /*this.purchaseOrderCreate.get('orderType').disable();
        this.purchaseOrderCreate.get('supplierType').disable();*/
      }
    })
  }
  private freightBorneByForMonthly(totalPOAmount: number) {
    if (totalPOAmount > this.FRIGHT_BORNE_BY_LIMIT) {
      this.purchaseOrderCreate.get('freightBorneBy').patchValue(this.FRIGHT_BORNE_BY_KAI);
      this.purchaseOrderCreate.get('freightBorneBy').disable();
      return;
    }
    this.purchaseOrderCreate.get('freightBorneBy').patchValue(this.FRIGHT_BORNE_BY_DEALER);
    this.purchaseOrderCreate.get('freightBorneBy').disable();
    return
  }
  private supplierNameChanges() {
    this.purchaseOrderCreate.get('supplierName').valueChanges.subscribe(value => {
      if (this.purchaseOrderCreate.get('orderType').value != null && this.purchaseOrderCreate.get('orderType').value.orderType === 'Other Suppliers') {
        this.supplierNameList = this.getVendorNameList(value);
      } else {
        this.supplierNameList = this.getSupplierNameList(value);
      }
    })
  }
  // Order Planning Sheet
  private autoSearchOpNo() {
    this.purchaseOrderCreate.get('orderPlanningNo').valueChanges.subscribe(value => {
      this.opAutoSearchList = this.getOrderPlanningSheetNo(value);
      // console.log(this.opAutoSearchList, 'this.opAutoSearchList')
      // console.log("jobCardNoAutoList--->", this.jobCardNoAutoList);
    })
  }

  private jobCardNumberChanges() {
    this.purchaseOrderCreate.get('serviceJobCard').valueChanges.subscribe(value => {
      // console.log("jobCardNumberChanges--->", value);
      this.jobCardNoAutoList = this.getJobCardAutoList(value);
      // console.log("jobCardNoAutoList--->", this.jobCardNoAutoList);
    })
  }
  jobCardNumberValue(event) {
    if (typeof event == 'string') {
      this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('serviceJobCard').setErrors({
        serviceJobCard: 'Please enter Valid Job Card Number'
      })
    }
  }
  private getSupplierNameList(value: string) {
    return this.sparesPurchaseOrderWebService.getSupplierNameList(value)
  }
  private getVendorNameList(value: string) {
    return this.sparesPurchaseOrderWebService.getVendorNameList(value)
  }

  private getJobCardAutoList(value: string) {
    return this.sparesPurchaseOrderWebService.getJobCardAutoList(value)
  }
  // Order Planning Sheet
  private getOrderPlanningSheetNo(value: string) {
    return this.sparesPurchaseOrderWebService.getOrderPlanningSheetNo(value)
  }
  // 
  private getSupplierType() {
    this.sparesPurchaseOrderWebService.getSupplierType().subscribe(res => {
      // console.log("Supplier Type--->", res)
      if (res) {
        this.supplierTypeList = res['result'];
      }
    })
  }
  public jobCardNumberSelected(event: MatAutocompleteSelectedEvent) {
    // console.log("jobCardNumberSelected--->", event);
    if (event && event.option.value) {
      this.getJobcardDetailsFromJobCardId(event.option.value.id);
    }
  }
  private getJobcardDetailsFromJobCardId(jobCardId: string) {
    this.purchaseOrderCreatePagePresenter.getItemDetailsForm.clear();
    // console.log("jobCardId--->", jobCardId);
    this.sparesPurchaseOrderWebService.getJobcardDetailsFromJobCardId(jobCardId).subscribe(res => {
      console.log("getJobcardDetailsFromJobCardId", res)
      if (res) {
        res['result'].forEach(data => {
          this.itemDetailsDataFromJobCard = data;
          this.itemDetailsDataFromJobCard.sparePartMaster = {} as SparePartMaster;
          this.itemDetailsDataFromJobCard.sparePartMaster.id = data['itemId'];
          this.itemDetailsDataFromJobCard.sparePartMaster.itemNo = data['itemNo'];
          this.itemDetailsDataFromJobCard.isJobCardItem = true;
          this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(this.itemDetailsDataFromJobCard);
          this.purchaseOrderCreatePagePresenter.setValueToPartOrderForm(data);
        })
      }
    })
  }
  // public supplierTypeChanges(event) {
  //   if (event && event['value']) {
  //     this.getOrderTypesFromSupplierType(event['value'])
  //   }
  // }
  private getOrderTypesFromSupplierType(supplierType: string) {
    this.sparesPurchaseOrderWebService.getOrderTypesFromSupplierType(supplierType).subscribe(res => {
      if (res)
        this.orderTypeList = res['result'];
      this._changeDetectorRef.markForCheck();
    })
  }
  private getModeOfTransport() {
    this.sparesPurchaseOrderWebService.getModeOfTransport().subscribe(res => {
      if (res)
        this.modeOfTransportList = res['result'];
    })
  }
  private getTransportNames() {
    this.sparesPurchaseOrderWebService.getTransportNames().subscribe(res => {
      if (res)
        this.transporterList = res['result'];
    })
  }
  private getDealerOutstandingDetails() {

    this.sparesPurchaseOrderWebService.getDealerOutstandingDetails(this.editRecordId).subscribe(res => {
      if (res) {
        this.dealerOutstanding.next([res['result']]);
        const dealerData = res['result'];
        this.purchaseOrderCreate.get('creditLimit').patchValue(dealerData['creditLimit']);
        this.purchaseOrderCreate.get('currentOutStanding').patchValue(dealerData['currentOutStanding']);
        this.purchaseOrderCreate.get('paymentUnderProcess').patchValue(dealerData['paymentUnderProcess']);
        this.purchaseOrderCreate.get('availableLimit').patchValue(dealerData['availableLimit']);
        this.purchaseOrderCreate.get('overduesOutStanding').patchValue(dealerData['overDuesOutStanding']);
        this.purchaseOrderCreate.get('ordersUnderProcess').patchValue(dealerData['orderUnderProcess']);
        this.purchaseOrderCreate.get('netAmountPayable').patchValue(dealerData['netPayableAmount']);
      }
    })
  }

  private getPriceTypeForCoDealer() {
    if (this.priceTypeList.length === 0) {
      this.sparesPurchaseOrderWebService.getPriceTypeForCoDealer().subscribe(res => {
        if (res)
          this.priceTypeList = res['result'];
      })
    }
  }
  // public orderTypeChange(event) {
  //   if (event && event['value']) {
  //     this.checkOrderType(event['value']['orderType']);
  //     // this.checkFreightBorneBy(0);
  //   }
  // }
  private checkOrderType(orderType: string) {
    this.makeAllOrderTypeFlagFalse();   //Make all order types false
    switch (orderType.toLowerCase()) {
      case 'kai monthly order':
        this.isMonthlyPo = true;
        const controlsToHideAndShow = ['serviceJobCard', 'transportMode', 'transporter', 'coDealerMaster', 'supplierName', 'priceType'];
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType([]);
        break;
      case 'kai emergency order':
        this.isEmergencyPo = true;
        this.isMachineDownPo = false;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType([]);
        break;
      case 'kai machine down order':
        this.isMachineDownPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType(['serviceJobCard', 'transportMode', 'transporter']);
        break;
      case 'co-dealer order':
        this.isCoDealerPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType(['coDealerMaster', 'priceType']);
        this.getPriceTypeForCoDealer();
        break;
      case 'co-dealer oil order':
        this.isCoDealerOilPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType(['coDealerMaster']);
        this.getPriceTypeForCoDealer();
        break;
      case 'other suppliers':
        this.isOtherPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType(['supplierName']);
        break;
      case 'oil and lubricant':
        this.isOilAndLubricantPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType(['supplierName']);
        break;
      case 'free of cost(foc)':
        this.isFocPo = true;
        this.purchaseOrderCreatePagePresenter.setAndRemoveValidatorsBasedOnOrderType([]);
        break;

      default:
        break;
    }
  }
  private makeAllOrderTypeFlagFalse() {
    this.isMonthlyPo = false;
    this.isEmergencyPo = false;
    this.isMachineDownPo = false;
    this.isCoDealerPo = false;
    this.isOtherPo = false;
    this.isOilAndLubricantPo = false;
    this.isFocPo = false;
  }
}
