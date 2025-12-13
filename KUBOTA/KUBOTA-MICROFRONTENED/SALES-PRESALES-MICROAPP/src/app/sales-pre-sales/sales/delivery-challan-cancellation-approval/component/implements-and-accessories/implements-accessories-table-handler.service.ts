import { Injectable } from '@angular/core';
import { TableConfig } from 'editable-table';
import { Validators } from '@angular/forms';
import { ValidateInt } from '../../../../../utils/custom-validators';

@Injectable()
export class ImplementsAccessoriesTableHandlerService {

  constructor() { }

  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Select',
      formControlName: 'isSelected',
      key: 'select',
      inputField: 'checkbox',
    }, {
      title: 'Item No',
      formControlName: 'itemNumber',
      key: 'itemNo',
      inputField: 'autocomplete',
      isNeedValueChanges: true,
      displayKey: 'itemNo'
    }, {
      title: 'Item Description',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'input',
      // displayKey: 'itemNo'
    }, {
      title: 'Quantity',
      formControlName: 'quantity',
      key: 'quantity',
      inputField: 'input',
      // displayKey: 'itemNo'
    }]
  }
  getTableFormGroupJSON() {
    return {
      itemNumber: [{ value: '', disabled: false, }, Validators.compose([])],
      quantity: [{ value: null, disabled: false }, Validators.compose([ValidateInt])],
      itemDescription: [{ value: null, disabled: true }],
      machineMaster: [null, Validators.compose([Validators.required])],
      isSelected: [{ value: false, disabled: false }]/*,
      deleteFlag: [false, Validators.compose([Validators.required])]*/
    }
  }
}
