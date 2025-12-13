import { Injectable } from '@angular/core';
import { Validators } from '@angular/forms';
import { ValidateInt } from '../../../../../utils/custom-validators';
import { TableConfig } from 'editable-table';

@Injectable()
export class AccessoryDetailTableService {

  constructor() { }
  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Item No',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'contenteditable',
      isNeedValueChanges: false,
      // displayKey: 'itemNo'
    }, {
      title: 'Item Description',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'contenteditable',
      // isNeedValueChanges: true,
      // displayKey: 'itemNo'
    }, {
      title: 'Invoice Quantity',
      formControlName: 'invoiceQuantity',
      key: 'quantity',
      inputField: 'contenteditable',
      // isNeedValueChanges: true
    }, {
      title: 'Chassis No.',
      formControlName: 'chassisNo',
      key: 'chassisNo',
      inputField: 'contenteditable',
      // displayKey: 'value',
      // isNeedValueChanges: true
    }, {
      title: 'Engine No.',
      formControlName: 'engineNo',
      key: 'engineNo',
      inputField: 'contenteditable',
      // isNeedValueChanges: true
    }, {
      title: 'Unit Price',
      formControlName: 'unitPrice',
      key: 'unitPrice',
      inputField: 'contenteditable',
      // isNeedValueChanges: true
    }, {
      title: 'GST Amount',
      formControlName: 'gstAmount',
      key: 'gstAmount',
      inputField: 'contenteditable',
      // isNeedValueChanges: true
    }, {
      title: 'Assessable Amount',
      formControlName: 'assessableAmount',
      key: 'assessableAmount',
      inputField: 'contenteditable',
      // isNeedValueChanges: true
    }, {
      title: 'Total Value',
      formControlName: 'totalValue',
      key: 'totalValue',
      inputField: 'contenteditable'
    },

    {
      title: 'Receipt Qty',
      formControlName: 'receiptQuantity',
      key: 'receiptQty',
      inputField: 'input',
      isNeedValueChanges: true
    },
    {
      title: 'Descripancy Qty',
      formControlName: 'descripancyQuantity',
      key: 'descripancyQty',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'Remarks',
      formControlName: 'remarks',
      key: 'remarks',
      inputField: 'input'
    }]
  }
  getControlsConfigForTableFormGroup() {
    return {
      id: [null],
      itemNo: [{ value: null, disabled: true }],
      itemDescription: [{ value: null, disabled: true }],
      invoiceQuantity: [{ value: null, disabled: true }],
      chassisNo: [{ value: null, disabled: true }],
      engineNo: [{ value: null, disabled: true }],
      unitPrice: [{ value: null, disabled: true }],
      gstAmount: [{ value: null, disabled: true }],
      assessableAmount: [{ value: null, disabled: true }],
      totalValue: [{ value: null, disabled: true }],
      receiptQuantity: [{ value: 0, disabled: false }, Validators.compose([Validators.required, ValidateInt])],
      descripancyQuantity: [{ value: 0, disabled: false }, Validators.compose([Validators.required, ValidateInt])],
      remarks: [{ value: null, disabled: false }, Validators.compose([])],
    }
  }
}
