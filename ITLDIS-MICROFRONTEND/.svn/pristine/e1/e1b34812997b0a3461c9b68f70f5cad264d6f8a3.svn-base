import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { TableConfig } from 'editable-table';
import { HttpClient,HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { SaveGRN, MachineInventory } from './create-goods-receipt-note-dto';
import { ValidateInt, ValidateMax } from '../../../../../utils/custom-validators';

@Injectable()
export class CreateGrnFormService {

  private readonly createMachineGrnUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.grn}${urlService.createMachineGrn}`;
  private readonly getGrnByDmsGrnNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.grn}${urlService.getGrnByDmsGrnNumber}`;
  private readonly printGrnUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.grn}${urlService.printGrnUrl}`;
  
  private createGrnForm: FormGroup;
  private loginUser = { id: 0 }
  etFormArray: FormArray;
  constructor(
    private httpClient: HttpClient,
    private fb: FormBuilder
  ) { }
  /**
   * generateCreateGrnForm
   */
  public generateGrnForm() {
    this.createGrnForm = new FormGroup({
      goodsReceiptNote: new FormControl(null, Validators.compose([Validators.required])),
      itemDetails: new FormControl(null, Validators.compose([Validators.required])),
      accessoryDetail: new FormControl(null)
    });
    return this.createGrnForm;
  }

  public get itemDetailControl(): AbstractControl {
    if (this.createGrnForm) {
      return this.createGrnForm.get('itemDetails');
    }
  }
  public get accessoryDetailControl(): AbstractControl {
    if (this.createGrnForm) {
      return this.createGrnForm.get('accessoryDetail');
    }
  }

  getFormArrayForEditableTable(): FormArray {
    this.etFormArray = this.fb.array([this.fb.group(
      this.getControlsConfigForTableFormArray()
    )]);
    (this.etFormArray.controls[0] as FormGroup).controls.receiptQuantity.setValidators(ValidateMax((this.etFormArray.controls[0] as FormGroup).controls.invoiceQuantity));
    (this.etFormArray.controls[0] as FormGroup).controls.receiptQuantity.updateValueAndValidity();

    (this.etFormArray.controls[0] as FormGroup).controls.receiptQuantity.valueChanges.subscribe(res => {

    })

    return this.etFormArray;
  }
  getControlsConfigForTableFormArray() {
    return {
      id: [null],
      itemNo: [{ value: null, disabled: false }],
      itemDescription: [{ value: null, disabled: false }],
      invoiceQuantity: [{ value: null, disabled: false }],
      chassisNo: [{ value: null, disabled: true },],
      engineNo: [{ value: null, disabled: true }],
      unitPrice: [{ value: null, disabled: true }],
      totalValue: [{ value: null, disabled: true }],
      assessableAmount: [{ value: null, disabled: true }],
      gstAmount: [{ value: null, disabled: true }],
      receiptQuantity: [{ value: 0, disabled: false }, Validators.compose([Validators.required, ValidateInt])],
      descripancyQuantity: [{ value: 0, disabled: false }, Validators.compose([Validators.required, ValidateInt])],
      remarks: [{ value: null, disabled: false }, Validators.compose([CreateGrnFormService.disableReceiptQuantity])],
    }
  }
  static disableReceiptQuantity(controls: AbstractControl) {
    console.log('disableReceiptQuantity: ', controls);
    if (controls.parent) {
      controls.parent.get('receiptQuantity')
      console.log('controls.parent.get(receiptQuantity): ', controls.parent.get('receiptQuantity'));
    }
    return null
  }
  createSaveMachineGrnJSON(grossTotalValue: number, grnStatus?: string): SaveGRN {
    const grnFormData = this.createGrnForm.getRawValue();
    let { accPacInvoice = null,
      driverMobile = null,
      driverName = null,
      grnBy = null,
      lrNo = null,
      transporter = { id: null },
      transporterVehicleNumber = null,
      grnDate = null } = { ...grnFormData.goodsReceiptNote };
      let grnMachineDetails = [];
      grnMachineDetails = grnFormData.itemDetails as Array<MachineInventory>
      if (grnFormData.accessoryDetail && grnFormData.accessoryDetail.length > 0) {
        grnMachineDetails = [...grnMachineDetails, ...grnFormData.accessoryDetail]
      }
    return ({
      accPacInvoice,
      driverMobile,
      driverName,
      grnBy,
      lrNo,
      transporter,
      transporterVehicleNumber,
      grnDate,
      grnStatus,
      grossTotalValue,
      grnMachineDetails,
      grnType: grnFormData.goodsReceiptNote.grnType.value,
      createdBy: this.loginUser.id 
    })
  }
  createMachineGrn(saveGRN: SaveGRN) {
    return this.httpClient.post(this.createMachineGrnUrl, saveGRN);
  }
  getGrnByDmsGrnNumber(grnId) {
    return this.httpClient.get(this.getGrnByDmsGrnNumberUrl + `/${grnId}`);
  }
  
  printGrn(grnNumber: string, printStatus:string) {
      return this.httpClient.get<Blob>(this.printGrnUrl, {
          params: new HttpParams().set('grnNumber', grnNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
