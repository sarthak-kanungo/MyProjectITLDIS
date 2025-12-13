import { Component, OnInit, Input } from '@angular/core';
import { MatCheckboxChange } from '@angular/material';
import { ModelNumberData, DropDownDataSpecification, PdiCheckpointList } from '../../domain/pdi-domain';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { PdiPresenter } from '../pdi-page/pre-delivery-inspection-presenter';
import { PdiChecklistWebService } from './pdi-checklist-web.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-pdi-checklist',
  templateUrl: './pdi-checklist.component.html',
  styleUrls: ['./pdi-checklist.component.scss'],
  providers: [PdiPresenter, PdiChecklistWebService]
})
export class PdiChecklistComponent implements OnInit {
  spans = []
  @Input() isShownTable: boolean
  @Input() isEdit: boolean
  @Input() isView: boolean
  @Input() public set sendCheckPointListData(sendCheckPointListData) {
    if (sendCheckPointListData) {
      this.isShownTable = true;
      this.spans = [];
      sendCheckPointListData.forEach(material => {
        this.addRow(material)
        this.cacheSpan('aggregate', d => d.aggregate, sendCheckPointListData);
      })
    }
  }
  @Input() public set rowDataView(rowDataView) {
    if (rowDataView) {
      this.isShownTable = true;
      rowDataView.forEach(material => {
        this.addRowView(material)
        this.cacheSpan('aggregate', d => d.aggregate, rowDataView);
      })
    }
  }
  @Input() public set rowDataEdit(rowDataEdit) {
    if (rowDataEdit) {
      this.isShownTable = true;
      rowDataEdit.forEach(material => {
        this.addRow(material)
        this.cacheSpan('aggregate', d => d.aggregate, rowDataEdit);
      })
    }
  }

  @Input() materialTableForm: FormGroup;
  @Input() materialTableFormSecond: FormGroup;
  pdiCheckListDropDown: DropDownDataSpecification
  checked: boolean = false
  checkVal:boolean=true
  dummyList:any=[{"observedSpecification":"Select"}]

  constructor(
    private pdiChecklistService: PdiChecklistWebService,
    private presenter: PdiPresenter,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.checkViewEdit()
  }
  checkViewEdit() {
    if (this.isEdit || this.isView) {
      this.getDataForEdit()
    }
  }

  onChange(event: MatCheckboxChange, materialTableForm:FormGroup) {
      if(materialTableForm.get('fieldType').value === 'DROP DOWN' && materialTableForm.get('observedSpecification').value === undefined){
          this.toastr.error('Select Specification');
          materialTableForm.get('defaultTick').patchValue(false);
          return;
      }
    this.presenter.setValidationForCheckBox()
  }
  onChangeStatic(event: MatCheckboxChange) {
        
    if (event.checked === false) {
      this.presenter.staticTable.get('staticRemark').markAllAsTouched();
      this.presenter.staticTable.get('staticRemark').setValidators(Validators.required)
      this.presenter.staticTable.get('staticRemark').updateValueAndValidity()
    }
    if (event.checked === true && this.presenter.staticTable.get('staticChecked').value === true) {
      this.presenter.staticTable.get('staticRemark').clearValidators()
      this.presenter.staticTable.get('staticRemark').updateValueAndValidity()
      this.presenter.staticTable.get('staticRemark').markAllAsTouched();
    }
  }

  addRow(material: ModelNumberData) {
    const table = this.materialTableForm.get('table') as FormArray;
    let addedNewForm = this.presenter.createCheckPointTableRow(material);
    table.push(addedNewForm);
  }
  addRowView(material: PdiCheckpointList) {
    const table = this.materialTableForm.get('table') as FormArray;
    let addedNewForm = this.presenter.createCheckPointTableRowView(material)
    addedNewForm.get('remark').disable();
    addedNewForm.get('defaultTick').disable();
    addedNewForm.get('observedSpecification').disable();
    table.push(addedNewForm);
  }


  getDataForEdit() {
    this.pdiChecklistService.getDropDownCheckListData('0').subscribe(res => {
      this.pdiCheckListDropDown = res
    })
  }
  checkListChange(event: MouseEvent, id: any) {
        this.pdiCheckListDropDown=this.dummyList
        this.pdiChecklistService.getDropDownCheckListData(id.id).subscribe(res => {
        this.pdiCheckListDropDown = res
      })
  }

  cacheSpan(key: string, accessor: Function, sendCheckPointListData: Array<object>) {
    for (let i = 0; i < sendCheckPointListData.length;) {
      let currentValue = accessor(sendCheckPointListData[i]);
      let count = 1;
      for (let j = i + 1; j < sendCheckPointListData.length; j++) {
        if (currentValue != accessor(sendCheckPointListData[j])) {
          break;
        }
        count++;
      }
      if (!this.spans[i]) {
        this.spans[i] = {};
      }
      this.spans[i][key] = count;
      i += count;
    }
  }



  getRowSpan(col, index: number) {

    return this.spans[index] && this.spans[index]['aggregate'];
  }

}
