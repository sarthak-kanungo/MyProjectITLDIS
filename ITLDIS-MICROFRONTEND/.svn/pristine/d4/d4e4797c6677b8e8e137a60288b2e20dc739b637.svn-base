import { Component, OnInit, Input } from '@angular/core';
import { TableConfig, PatchValue } from 'editable-table';
import { DefectStorageDamageWebService } from './defect-storage-damage-web.service';
import { AbstractControlOptions, FormGroup, AbstractControl, FormArray } from '@angular/forms';
import { MachineReceiptCheckingPresenter } from '../mrc-details-page/mrc-page.presenter';
import { MrcCreatePageStore } from '../mrc-details-page/mrc-create-page.store';
import { MrcViewResult } from '../../domain/machine-receipt-checking.domain';
import { Operation } from '../../../../../utils/operation-util';
import { ETValidationError } from '../../../../../../../projects/editable-table/src/public-api';
@Component({
  selector: 'app-defect-shortage-and-damage',
  templateUrl: './defect-shortage-and-damage.component.html',
  styleUrls: ['./defect-shortage-and-damage.component.scss'],
  providers: [DefectStorageDamageWebService, MrcCreatePageStore]
})
export class DefectShortageAndDamageComponent implements OnInit {
  @Input() defectShortageDamageForm: FormGroup;
  tableConfig: TableConfig[];
  isView: boolean;
  isEdit: boolean;
  totalQty: number = 0;
  assignTo: { list: any; config: any; searchKey: any; };
  patchValue: { rowIndex: any; tableRowId: any; patchValue: any; }[];
  etControlsConfig: {
    [key: string]: any, options?: AbstractControlOptions | {
      [key: string]: any;
    }
  }
  assignListToSelect: Array<object> = [];
  selectPatchValue: object;
  tableData: any;
  @Input() defectShortageFormControl: AbstractControl;
  @Input() viewEditData: MrcViewResult;
  etValidationError: ETValidationError
  constructor(
    private mrcPageStore: MrcCreatePageStore,
    private presenter: MachineReceiptCheckingPresenter,
    private defectStorageDamageWebService: DefectStorageDamageWebService,
  ) { }
  ngOnChanges() {
    this.viewEditData;
    this.patchDefctedShoratgeListToTable(this.viewEditData);
  }
  ngOnInit() {
    this.assignListToSelect = [{
      formControlName: 'type',
      list: [{ id: 1, value: 'Shortage' }, { id: 2, value: 'Defect' }, { id: 3, value: 'Damage' }]
    }];
    this.defectShortageDamageForm = this.presenter.editableTableData as FormGroup;
    console.log("this.defectShortageDamageForm ", this.defectShortageDamageForm);
    this.defectShortageFormControl = this.presenter.editableTableData;
    console.log("defectShortageFormControl ", this.defectShortageFormControl);
    this.tableConfig = this.getTableConfigurationJSON();
    this.etControlsConfig = this.mrcPageStore.getControlsConfigForTableFormGroup();
    this.viewOrEditOrCreate();
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isView = true;
    }
  }
  editableTableSearchValueChanges(event: any) {
    console.log('event ==================>', event)
    this.searchcByItemNumber(event);
    this.validationForItemNo(event);
    this.validationForQuantity(event);
    // this.getTotalQuantity(event);
  }

  validationForItemNo(event) {
    if (event.tableRow.raiseComplaint) {
      const formControlName = 'itemNo'
      if (event.tableRow.raiseComplaint) {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId, 'Item Number required')
      } else {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId);
      }
    }
    if (event.tableRow.itemNo) {
      const formControlName = 'quantity'
      if (event.tableRow.item !== null) {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId, 'Quantity required')
      } else {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId);
      }
    }
  }
  validationForQuantity(event) {
    console.log("event ", event);
    if (event.tableRow.quantity) {
      const formControlName = 'type'
      if (event.tableRow.quantity !== null) {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId, 'Type required')
      } else {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId);
      }
    }
  }
  searchcByItemNumber(event: any) {
    console.log("event ", event);
    if (event.config.key == "itemNo" && event['key'] !== null) {
      const itemIdArray = [];
      const tableData = this.presenter.mrcForm.getRawValue()
      if (tableData.defectShortageDamageForm !== null) {
        if (tableData.defectShortageDamageForm.length > 0) {
          tableData.defectShortageDamageForm.forEach(element => {
            if (typeof element.itemNo === 'object' && element.itemNo) {
              itemIdArray.push(element.itemNo.id);
              console.log("itemIdArray ", itemIdArray);
            }
          })
        }
      }
      const itemNo = typeof event['key'] == 'object' ? event['key']['itemNo'] : event['key']
      this.defectStorageDamageWebService.searchByItemNumber(itemNo, itemIdArray.join()).subscribe(res => {
        this.assignTo = {
          list: res['result'],
          config: event['config'],
          searchKey: event['key']
        };
      })
    }
    if (event.key == "" || event['key'] === null) {
      this.patchNullDetails(event)
    }
    if (event.tableRow.itemNo) {
      const formControlName = 'quantity'
      if (event.tableRow.item !== null) {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId, 'Quantity required')
      } else {
        this.etValidationError = new ETValidationError(formControlName, event.tableRow.tableRowId);
      }
    }

  }
  private patchNullDetails(event: object) {
    console.log("event ", event);
    this.patchValue = [{
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { itemDescription: '' }
    }];
  }
  getTotalQuantity(event: any) {
    if (event['key'] == "") {
      event['key'] = 0;
    }
    if (event.config.key == 'quantity') {
      this.totalQty = this.totalQty + parseFloat(event['key'])
    }
  }
  optionSelected(event: object) {
    console.log("event ", event);
    this.patchDetailsFromItemNumbers(event);
  }
  private patchDetailsFromItemNumbers(event: object) {
    console.log("event ", event);
    this.patchValue = [{
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { itemDescription: event['option']['itemDescription'] }
    }];
  }
  private patchDefctedShoratgeListToTable(viewEditData: object) {
    console.log("viewEditData ", viewEditData);
    if (this.viewEditData != undefined) {
      const tableData = this.viewEditData.mrcDiscrepancyList;
      let patchValue;
      let arrayForPatch: PatchValue[] = new Array();
      tableData.forEach((element, index) => {
        if (element.type == 'Shortage') {
          patchValue = (this.assignListToSelect[0]['list'] as Array<object>).find((ele) => ele['value'] === 'Shortage');
        } else if (element.type == 'Defect') {
          patchValue = (this.assignListToSelect[0]['list'] as Array<object>).find((ele) => ele['value'] === 'Defect');
        } else {
          patchValue = (this.assignListToSelect[0]['list'] as Array<object>).find((ele) => ele['value'] === 'Damage');
        }
        // console.log("element-------", element);
        // console.log("index-----------", index);
        // console.log('patchValue-------------', patchValue);
        arrayForPatch.push({
          rowIndex: index,
          patchValue: {
            type: patchValue
          }
        });
        this.selectPatchValue = arrayForPatch;
      });
    }
  }
  tableValueChange(event: any) {
    console.log("event ", event);
    event.subscribe(res => {
      console.log("res ", res);
    })

  }
  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Select',
      formControlName: 'isSelected',
      key: 'select',
      inputField: 'checkbox',
    }, {
      title: 'Item No',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'autocomplete',
      displayKey: 'displayValue',
      isNeedValueChanges: true
    },
    {
      title: 'Item Desc',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'input',
      displayKey: 'itemDescription'
    },
    {
      title: 'Quantity',
      formControlName: 'quantity',
      key: 'quantity',
      inputField: 'input',
      isNeedValueChanges: true
    },
    {
      title: 'Remarks',
      formControlName: 'remarks',
      key: 'remarks',
      inputField: 'input',
    },
    {
      title: 'Type',
      formControlName: 'type',
      key: 'type',
      inputField: 'select',
      displayKey: 'value'
    },
    {
      title: 'Complaint to be Raised',
      formControlName: 'raiseComplaint',
      key: 'raiseComplaint',
      inputField: 'checkbox',
      isNeedValueChanges: true
    },
    ]
  }
}
