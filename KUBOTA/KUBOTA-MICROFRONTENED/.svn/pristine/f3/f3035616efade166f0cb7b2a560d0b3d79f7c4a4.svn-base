import { Injectable } from "@angular/core";
import { TableConfig } from 'editable-table';
import { Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class PartReturnTableService {
  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Item No',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'input',
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
    }, {
      title: 'issued Qty',
      formControlName: 'issuedQuantity',
      inputField: 'input'
    }, {
      title: 'Pending Qty',
      formControlName: 'pendingQuantity',
      inputField: 'input'
    }, {
      title: 'Return Qty',
      formControlName: 'returnQuantity',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'Store',
      formControlName: 'store',
      inputField: 'input'
    }, {
      title: 'Bin Location',
      formControlName: 'binLocation',
      inputField: 'input'
    }, {
      title: 'Remark',
      formControlName: 'remark',
      inputField: 'input'
    }]
  }
  getTableFormGroupJSON(data?) {
    return {
      id: [{ value: data ? data.id : null, disabled: true }],
      sparePartMaster: [{ value: data ? { id: data.sparePartId, itemNo: data.itemNo } : null, disabled: true }],
      binLocationMaster: [{ value: data ? { id: data.binId } : null, disabled: true }],
      storeMaster: [{ value: data ? { id: data.storeId } : null, disabled: true }],
      sparePartIssue: [{ value: data ? { id: data.partIssueId } : null, disabled: true }],
      itemNo: [{ value: data ? data.itemNo : null, disabled: true }],
      itemDescription: [{ value: null, disabled: true }],
      uom: [{ value: null, disabled: true }],
      reqQuantity: [{ value: null, disabled: true }],
      issuedQuantity: [{ value: null, disabled: true }],
      pendingQuantity: [{ value: null, disabled: true }],
      returnQuantity: [{ value: null, disabled: false }, Validators.compose([CustomValidators.numberOnly, Validators.required])],
      store: [{ value: null, disabled: true }],
      binLocation: [{ value: null, disabled: true }],
      remark: [{ value: null, disabled: false }],
    }
  }
}