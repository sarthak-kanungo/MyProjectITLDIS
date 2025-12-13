import { Injectable } from "@angular/core";
import { WpdcStore } from "./wpdc-page.store";
import { FormGroup, FormArray, Validators } from "@angular/forms";
import { WarrantyDeliveryChallan, WpdcView, WpdcViewMaster } from '../../domain/warranty-parts-delivery-challan.domain';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { CustomValidators } from '../../../../../utils/custom-validators';
@Injectable()
export class WpdcPagePresenter {
  private _operation: string;
  totalAmount: number;
  totalGstAmount: number;
  totalValue: number;

  constructor(
    private wpdcStore: WpdcStore,
    private localStorageService: LocalStorageService
    ) {}

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }
  get wpdcForm(): FormGroup {
    return this.wpdcStore.allForm.get("wpdcForm") as FormGroup;
  }
  get materialDetailForm(): FormArray {
    return this.wpdcStore.allForm.get("materialDetailForm") as FormArray;
  }
  get transporterDetailForm(): FormGroup {
    return this.wpdcStore.allForm.get("transporterDetailForm") as FormGroup;
  }
  get loginUser() {
    return this.localStorageService.getLoginUser();
  }

  addRowInMaterialDetail(materialDetail?: WarrantyDeliveryChallan) {
    const control = this.materialDetailForm;
    const newForm = this.wpdcStore.initializeMaterialDetailForm(materialDetail);
    control.push(newForm);
  }

  value(val: number) {
    this.totalValue = val;
  }
  amount(amt: number) {
    this.totalAmount = amt;
  }
  gstAmount(gstAmt: number) {
    this.totalGstAmount = gstAmt;
  }

  viewWpdc(detailMaster: WpdcViewMaster) {
    const detail = detailMaster.warrantyDeliveryChallanViewDto;

    this.transporterDetailForm.get('transporterName').patchValue(detail.transporterName);
    this.transporterDetailForm.get('docketNumber').patchValue(detail.docketNumber);
    this.transporterDetailForm.get('noOfBoxes').patchValue(detail.numberOfBox);
    this.transporterDetailForm.get('dispatchDate').patchValue(detail.dispatchDate);
    
    this.wpdcForm.get('dealerName').patchValue(detailMaster.dealerMaster.serviceDealer);
    this.wpdcForm.get('dealerEmail').patchValue(detailMaster.dealerMaster.emailId);
    this.wpdcForm.get('dealerCode').patchValue(detailMaster.dealerMaster.dealerCode);
    this.wpdcForm.get('dealerMobNo').patchValue(detailMaster.dealerMaster.mobileNo);
    this.wpdcForm.get('dealerAddress').patchValue(detailMaster.dealerMaster.serviceDealerAddress);
    
    this.wpdcForm.get('deliveryChallanNo').patchValue(detail.dcNo);
    this.wpdcForm.get('deliveryChallanDate').patchValue(detail.dcDate);
    const arr = detailMaster.warrantyPart.map(elt => {
      const patchData = {} as WarrantyDeliveryChallan;
      patchData.itemNo = elt.partNo;
      patchData.itemDescription = elt.description;
      patchData.quantity = elt.approvedQuantity;
      patchData.unitPrice = elt.priceUnit;
      patchData.gstPercent = elt.gstPercent;
      patchData.gstAmount = elt.gstAmount;
      patchData.hsnCode = elt.hsnCode;
      patchData.wcrNo = elt.wcrNo;
      patchData.totalAmount = elt.totalAmount;
      patchData.value = patchData.quantity*patchData.unitPrice;
      this.addRowInMaterialDetail(patchData);
      return patchData;
    })
    this.value(this.sum(arr, 'value'));
    this.amount(this.sum(arr, 'totalAmount'));
    this.gstAmount(this.sum(arr, 'gstAmount'));
  }

  sum(items: Array<WarrantyDeliveryChallan>, prop: string) {
    return items.reduce((prevVal, currVal) => prevVal + currVal[prop], 0).toFixed(2);
  }

  transporterDetailFormValidation() {
    this.transporterDetailForm.get('transporterName').setValidators(Validators.required);
    this.transporterDetailForm.get('docketNumber').setValidators(Validators.required);
    this.transporterDetailForm.get('noOfBoxes').setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly]));
    this.transporterDetailForm.get('dispatchDate').setValidators(Validators.required);
  }
}
