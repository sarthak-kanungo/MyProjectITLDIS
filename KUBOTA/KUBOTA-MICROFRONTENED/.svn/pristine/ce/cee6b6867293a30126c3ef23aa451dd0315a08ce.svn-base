import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { WarrantyDeliveryChallan } from '../../domain/warranty-parts-delivery-challan.domain';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class WpdcStore {
  private readonly form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      wpdcForm: this.wpdcForm(),
      materialDetailForm: this.fb.array([]),
      transporterDetailForm: this.transporterDetailForm()
    });
  }

  /* private createForm() {
    this.form = this.fb.group({
      wpdcForm: this.wpdcForm(),
      materialDetailForm: this.fb.array([]),
      transporterDetailForm: this.transporterDetailForm()
    });
  }
 */
  private wpdcForm() {
    const wpdcForm = this.fb.group({
      dealerName: [{ value: null, disabled: true }],
      dealerAddress: [{ value: null, disabled: true }],
      dealerEmail: [{ value: null, disabled: true }],
      dealerCode: [{ value: null, disabled: true }],
      dealerMobNo: [{ value: null, disabled: true }],
      deliveryChallanNo: [{ value: null, disabled: true }],
      deliveryChallanDate: [{ value: null, disabled: true }]
    });
    return wpdcForm;
  }
  private materialDetailForm(materialDetail?: WarrantyDeliveryChallan) {
    const materialDetailForm = this.fb.group({
      isSelect: [{ value: materialDetail.isSelect, disabled: false }],
      wcrNo: [{ value: materialDetail.wcrNo, disabled: true }],
      itemNo: [{ value: materialDetail.itemNo, disabled: true }],
      itemDescription: [{ value: materialDetail.itemDescription, disabled: true }],
      hsnCode: [{ value: materialDetail.hsnCode, disabled: true }],
      unitPrice: [{ value: materialDetail.unitPrice, disabled: true }],
      quantity: [{ value: materialDetail.quantity, disabled: true }],
      value: [{ value: materialDetail.value, disabled: true }],
      gstPercent: [{ value: materialDetail.gstPercent, disabled: true }],
      gstAmount: [{ value: materialDetail.gstAmount, disabled: true }],
      totalAmount: [{ value: materialDetail.totalAmount, disabled: true }]
    });
    return materialDetailForm;
  }
  private transporterDetailForm() {
    const transporterDetailForm = this.fb.group({
      transporterName: [{ value: null, disabled: false }],
      docketNumber: [{ value: null, disabled: false }],
      noOfBoxes: [{ value: null, disabled: false }, CustomValidators.numberOnly],
      dispatchDate: [{ value: null, disabled: false }]
    });
    return transporterDetailForm;
  }
  initializeMaterialDetailForm(materialDetail?: WarrantyDeliveryChallan) {
    return this.materialDetailForm(materialDetail);
  }
  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    /* this.createForm();
    return this.form as FormGroup; */
  }
}
