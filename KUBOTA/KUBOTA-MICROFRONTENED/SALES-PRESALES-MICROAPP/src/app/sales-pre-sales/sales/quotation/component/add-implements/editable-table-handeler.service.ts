import { Injectable } from '@angular/core';
import { TableConfig } from 'editable-table';
import { Validators } from '@angular/forms';
import { ValidateMaxDecimalPoint } from '../../../../../utils/custom-validators';
@Injectable()
export class EditableTableHandelerService {

  constructor() { }
  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Select',
      formControlName: 'isSelected',
      key: 'select',
      inputField: 'checkbox',
      // isNeedValueChanges: true
    }, {
      title: 'Item No',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'autocomplete',
      isNeedValueChanges: true,
      displayKey: 'value',
      patchKey: 'code'
    }, {
      title: 'Item Description',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'input',
      // isNeedValueChanges: true,
      // displayKey: 'itemNo'
    }, {
      title: 'Color',
      formControlName: 'color',
      key: 'color',
      inputField: 'input',
      // isNeedValueChanges: true
    }, {
      title: 'Qty',
      formControlName: 'qty',
      key: 'qty',
      inputField: 'input',
      // isNeedValueChanges: true
    }, {
      title: 'Rate (Excluding GST)',
      formControlName: 'rate',
      key: 'rate',
      inputField: 'input',
      // displayKey: 'value',
      isNeedValueChanges: true
    }, 
    {
        title: 'Basic Price',
        formControlName: 'basicPrice',
        key: 'basicPrice',
        inputField: 'input',
        isNeedValueChanges: true
      },
    {
      title: 'Gross Discount',
      formControlName: 'grossDiscount',
      key: 'grossDiscount',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'Amount After Discount',
      formControlName: 'amountAfterDiscount',
      key: 'amountAfterDiscount',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'IGST %',
      formControlName: 'igst',
      key: 'igst',
      inputField: 'input',
      isNeedValueChanges: true
    },

    {
      title: 'SGST %',
      formControlName: 'sgst',
      key: 'sgst',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'CGST %',
      formControlName: 'cgst',
      key: 'sgst',
      inputField: 'input',
      isNeedValueChanges: true
    },
    {
      title: 'GST Amount',
      formControlName: 'gstAmount',
      key: 'gstAmount',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'Total Amount',
      formControlName: 'totalAmount',
      key: 'totalAmount',
      inputField: 'input'
    }]
  }
  getTableFormGroupJSON() {
    return {
      itemNo: [{ value: '', disabled: false }, Validators.compose([Validators.required])],
      itemDescription: [{ value: '', disabled: true }],
      color: [{ value: '', disabled: true }],
      qty: [{ value: 1, disabled: true }],
      rate: [{ value: '', disabled: false }, Validators.compose([Validators.required, ValidateMaxDecimalPoint(2)])],
      basicPrice: [{ value: 0, disabled: true }],
      grossDiscount: [{ value: '', disabled: false }, Validators.compose([ValidateMaxDecimalPoint(2)])],
      amountAfterDiscount: [{ value: '', disabled: true }],
      igst: [{ value: '', disabled: true }],
      sgst: [{ value: '', disabled: true }],
      cgst: [{ value: '', disabled: true }],
      gstAmount: [{ value: '', disabled: true }],
      totalAmount: [{ value: '', disabled: true }],
      isSelected: [{ value: false, disabled: false }]
    }
  }
}
