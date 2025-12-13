import { Injectable } from '@angular/core';
import { TableConfig } from 'editable-table';

@Injectable()
export class EditableTableHandlerService {

  constructor() { }

  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
        title: '',
        formControlName: 'isSelected',
        inputField: 'checkbox'
      },  {
      title: 'Sr. No.',
      formControlName: 'serialNumber',
      inputField: 'input'
    }, {
        title: 'Picklist No',
        formControlName: 'picklistNumber',
        inputField: 'input'
    }, {
      title: 'Item No',
      formControlName: 'itemNo',
      inputField: 'input'
    }, {
      title: 'Item Description',
      formControlName: 'itemDescription',
      inputField: 'input'
    }, {
      title: 'HSN Code',
      formControlName: 'hsnCode',
      inputField: 'input',
    }, {
      title: 'Unit Price',
      formControlName: 'unitPrice',
      inputField: 'input',
    }, {
      title: 'Quantity',
      formControlName: 'quantity',
      inputField: 'input',
    },
    {
      title: 'Amount',
      formControlName: 'amount',
      inputField: 'input',
    }, {
      title: 'Discount ',
      formControlName: 'discountPercent',
      inputField: 'input',
    }, {
      title: 'Discount Amount',
      formControlName: 'discountAmount',
      inputField: 'input',
    }, {
      title: 'Nett Amount',
      formControlName: 'netAmount',
      inputField: 'input',
    }, {
      title: 'CGST %',
      formControlName: 'cgstPercent',
      inputField: 'input',
    }, {
      title: 'CGST Amount',
      formControlName: 'cgstAmount',
      inputField: 'input',
    }, {
      title: 'SGST %',
      formControlName: 'sgstPercent',
      inputField: 'input',
    }, {
      title: 'SGST Amount',
      formControlName: 'sgstAmount',
      inputField: 'input',
    }, {
      title: 'IGST %',
      formControlName: 'igstPercent',
      inputField: 'input',
    }, {
      title: 'IGST Amount',
      formControlName: 'igstAmount',
      inputField: 'input',
    }
    ]
  }
  getTableFormGroupJSON() {
    return {
      isSelected: [{ value: null, disabled: false }],  
      serialNumber: [{ value: null, disabled: true }],
      picklistNumber: [{ value: null, disabled: true }],
      itemNo: [{ value: null, disabled: true }],
      itemDescription: [{ value: null, disabled: true }],
      hsnCode: [{ value: null, disabled: true }],
      unitPrice: [{ value: null, disabled: true }],
      quantity: [{ value: null, disabled: true }],
      amount: [{ value: null, disabled: true }],
      discountPercent: [{ value: null, disabled: true }],
      discountAmount: [{ value: null, disabled: true }],
      netAmount: [{ value: null, disabled: true }],
      cgstPercent: [{ value: null, disabled: true }],
      cgstAmount: [{ value: null, disabled: true }],
      sgstPercent: [{ value: null, disabled: true }],
      sgstAmount: [{ value: null, disabled: true }],
      igstPercent: [{ value: null, disabled: true }],
      igstAmount: [{ value: null, disabled: true }]
    }
  }

}
