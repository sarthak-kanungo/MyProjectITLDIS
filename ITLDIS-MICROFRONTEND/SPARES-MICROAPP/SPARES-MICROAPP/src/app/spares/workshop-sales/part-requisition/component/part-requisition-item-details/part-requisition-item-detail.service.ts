import { Injectable } from '@angular/core';
import { TableConfig } from 'editable-table';
import { Validators, FormControl } from '@angular/forms';
import { CustomValidators, ValidateList } from '../../../../../utils/custom-validators';

@Injectable()
export class PartRequisitionItemDetailService {

  // static itemNumberListForValidation = new ValidateList('id');
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
      patchKey: 'itemNo'
    }, {
      title: 'Item Description',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'input',
    }, {
      title: 'UOM',
      formControlName: 'uom',
      inputField: 'input',
    }, {
      title: 'Requisition Qty',
      formControlName: 'reqQuantity',
      inputField: 'input'
    }]
  }
  getTableFormGroupJSON(data?) {
    return {
      id:null,
      itemNo: [{ value: data ? { id: data.sparePartId, itemNo: data.itemNo } : null, disabled: false }, Validators.compose([Validators.required])],
      itemDescription: [{ value: null, disabled: true }],
      uom: [{ value: null, disabled: true }],
      reqQuantity: [{ value: null, disabled: false }, Validators.compose([CustomValidators.numberOnly,Validators.required, CustomValidators.min(new FormControl(0),'Should be greater than 0')])],
      isSelected: [{ value: null, disabled: false }],
      sparePartMaster: data ? { id: data.sparePartId, itemNo: data.itemNo } : null,
      priceUnit: 0,
      amount: 0,
    }
  }
}
