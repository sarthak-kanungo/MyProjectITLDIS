import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { MachineReceiptCheckingPresenter } from '../mrc-details-page/mrc-page.presenter';
import { Operation } from '../../../../../utils/operation-util';
import { ItemNumberAuto, MrcDiscrepancyList } from '../../domain/machine-receipt-checking.domain';
import { DefectShoratageDamageService } from './defect-shoratage-damage.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-defect-shortage-damage-table',
  templateUrl: './defect-shortage-damage-table.component.html',
  styleUrls: ['./defect-shortage-damage-table.component.css'],
  providers: [DefectShoratageDamageService]
})
export class DefectShortageDamageTableComponent implements OnInit {
  @Input() defectShortageDamageFormTable: FormGroup;
  isView: boolean
  isEdit: boolean
  isCreate: boolean
  typeList = ['Shortage', 'Defect', 'Damage'];
  itemNumberList$: Observable<Array<ItemNumberAuto>>;
  @Input() public set defectShortageDamageList(defectShortageDamageList: MrcDiscrepancyList[]) {
    console.log("defectShortageDamageList ", defectShortageDamageList);
    defectShortageDamageList.forEach(element => {
      console.log("element ", element);
      this.addRow(element);
      const tableData = this.defectShortageDamageFormTable.get('defectShortageDamageData') as FormArray;
      tableData.controls.forEach(tableValues => {
        if (this.isEdit) {
          if (element.raiseComplaint !== null || (element.itemNo !== null && element.quantity !== null && element.type !== null)) {
            tableValues.get('raiseComplaint').enable()
          }
        }
        if (this.isView) {
          tableValues.disable();
        }
      })
    });
  }
  constructor(
    private presenter: MachineReceiptCheckingPresenter,
    private defectStorageDamageService: DefectShoratageDamageService,
  ) { }

  ngOnInit() {
    this.ViewOrEditOrCreate()
    if (this.isCreate) {
      this.addRow();
    }
  }
  private ViewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isView = true
      this.isEdit = false
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true
    } else if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true
      // this.addRow()
    }
  }
  addRow(data?: object) {
    this.presenter.addItemRow(data);
    this.itemNumberValueChanges();
    this.quantityValueChange();
  }
  deleteRow() {
    this.presenter.deleteRow()
  }
  private itemNumberValueChanges() {
    const tableData = this.defectShortageDamageFormTable.get('defectShortageDamageData') as FormArray;
    tableData.controls.forEach(value => {
      value.get('itemNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const itemNoValue = typeof valueChange === 'object' ? valueChange.itemNo : valueChange
          const itemsIdArray = [];
          const tableData1 = this.defectShortageDamageFormTable.getRawValue()
          if (tableData1.defectShortageDamageData.length > 0) {
            tableData1.defectShortageDamageData.forEach(element => {
              console.log("element ", element);
              if (typeof element.itemNo === 'object' && element.itemNo) {
                itemsIdArray.push(element.itemNo.id);
              }
            })
          }
          console.log("itemsIdArray ", itemsIdArray);
          this.itemNumberList$ = this.defectStorageDamageService.searchByItemNumber(itemNoValue, itemsIdArray.join())

        }
        // if (valueChange !== null) {
        //   this.presenter.itemNoSetValidation();
        // }
      })
    })

  }
  quantityValueChange() {
    const tableData = this.defectShortageDamageFormTable.get('defectShortageDamageData') as FormArray;
    tableData.controls.forEach(fg => {
      fg.get('quantity').valueChanges.subscribe(value => {
        console.log("value ", value);
        if (fg.get('itemNo').value !== null) {
          if (value === null) {
            fg.get('quantity').setErrors({ errors: 'Quantity Required' });
            // fg.get('type').reset();
            // fg.get('type').markAllAsTouched();
            // fg.get('type').setErrors({ errors: 'Type Required' });
            fg.get('raiseComplaint').setValue(null);
            fg.get('raiseComplaint').disable();

          }
        }
        if (fg.get('itemNo').value && fg.get('quantity').value && fg.get('type').value) {
          fg.get('raiseComplaint').enable();
        } else {
          fg.get('raiseComplaint').disable();
        }
      })
    })
  }
  qauntityChange(event: Event, fg: FormGroup) {
    console.log("fg ", fg);
    console.log("event ", event);
    if (fg.get('itemNo').value && fg.get('quantity').value && fg.get('type').value) {
      fg.get('raiseComplaint').enable();
    } else {
      fg.get('raiseComplaint').disable();
    }
  }
  validateQuantityAndType(fg: FormGroup) {
    if (fg.get('itemNo').value) {
      if (fg.get('quantity').value === null) {
        fg.get('quantity').setErrors({ errors: 'Quantity Required' });
      }
      if (fg.get('type').value === null) {
        fg.get('type').setErrors({ errors: 'Type Required' });
      }
      return;
    }
    fg.get('quantity').setErrors(null);
    fg.get('type').setErrors(null);

  }
  displayFnItemNumber(itemNo: ItemNumberAuto) {
    if (typeof itemNo === 'string') {
      return itemNo;
    }
    return itemNo ? itemNo.itemNo : undefined
  }
  optionSelectedItem(event: any, fg: FormGroup) {
    if (event && event['option']['value']) {
      const itemDesc = event.option.value.itemDescription
      fg.get('itemDescription').patchValue(itemDesc)
    }
    if (event && event['option']['value']) {
      fg.get('quantity').markAllAsTouched();
      fg.get('type').markAllAsTouched();
      this.validateQuantityAndType(fg)
    }
  }
  selectionChangedType(event: any, fg: FormGroup) {
    console.log("fg ", fg);
    console.log("event ", event);
    if (fg.get('itemNo').value && fg.get('quantity').value && fg.get('type').value) {
      fg.get('raiseComplaint').enable();
    } else {
      fg.get('raiseComplaint').disable();
    }
  }

  onKeyDownForItemNo(event: any, fg: FormGroup) {
    console.log("fg ", fg);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      fg.reset();
      fg.clearValidators();
      fg.setValidators(null);
      fg.updateValueAndValidity();
    }
  }
  // typeCompare(c1: Type, c2: TypeCategory): boolean {
  //   if (typeof c1 !== typeof c2) {
  //     if (typeof c1 === 'object' && typeof c2 === 'string')
  //       return c1.category === c2
  //     if (typeof c2 === 'object' && typeof c1 === 'string')
  //       return c1 === c2.category
  //   }
  //   return c1 && c2 ? c1.category === c2.category : c1 === c2
  // }
}
