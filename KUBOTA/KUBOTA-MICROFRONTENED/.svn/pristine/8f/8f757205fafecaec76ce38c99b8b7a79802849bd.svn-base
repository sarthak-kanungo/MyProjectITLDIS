import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { SpecificationDropDown, CheckpointListByModel } from '../../domain/pdc-domain';
import { PdcPagePresenter } from '../pdc-page/pdc-page-presenter';
import { MatCheckboxChange } from '@angular/material';
import { PdcChecklistWebService } from './pdc-checklist-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-pdc-checklist',
  templateUrl: './pdc-checklist.component.html',
  styleUrls: ['./pdc-checklist.component.scss'],
  providers: [PdcChecklistWebService]
})
export class PdcChecklistComponent implements OnInit {

  spans = []
  @Input() pdcCheckPointListTable: FormGroup
  specificationDropDown: Array<SpecificationDropDown>
  checked: boolean = false
  isView:boolean=false
  @Input() pdcStaticTable: FormGroup
  dummyList:any=[{"observedSpecification":"Select"}]
  @Input() set pdcCheckPoint(pdcCheckList: Array<CheckpointListByModel>) {
    if (pdcCheckList) {
      this.spans = []
      pdcCheckList.forEach(data => {
        this.pdcPagePresenter.addRow(data)
        this.rowSpan('aggregate', d => d.aggregate, pdcCheckList)
      })
    }
  }
  @Input() set viewPdcCheckPoint(pdcCheckList: Array<CheckpointListByModel>) {
    if (pdcCheckList) {
      this.spans = []
      pdcCheckList.forEach(data => {
        this.pdcPagePresenter.addRowForView(data)
        this.rowSpan('aggregate', d => d.aggregate, pdcCheckList)
      })
    }
  }
  @Input() set editPdcCheckPoint(pdcCheckList: Array<CheckpointListByModel>) {
    if (pdcCheckList) {
      this.spans = []
      pdcCheckList.forEach(data => {
        this.pdcPagePresenter.addRow(data)
        this.rowSpan('aggregate', d => d.aggregate, pdcCheckList)
      })
    }
  }

  constructor(
    private pdcPagePresenter: PdcPagePresenter,
    private pdcChecklistWebService: PdcChecklistWebService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    if (this.pdcPagePresenter.operation === Operation.VIEW || this.pdcPagePresenter.operation === Operation.EDIT) {
      this.getSpecificationDropdown()
      if (this.pdcPagePresenter.operation === Operation.VIEW) {
          this.isView = true;
      }
    }
  }

  onChangeOk(event: MatCheckboxChange, checklist: CheckpointListByModel) {
      if(checklist.fieldType === 'DROP DOWN' && checklist.observedSpecification === undefined){
          this.toastr.error('Select Specification');
          checklist.defaultTick = false;
          return;
      }  
    checklist.defaultTick = event.checked
    this.pdcPagePresenter.setValidationForCheckBox()
  }

  getSpecificationDropdown() {
    this.pdcChecklistWebService.specificationDropdown('0').subscribe(response => {
      this.specificationDropDown = response
    })
  }
  pdcSpecificationChange(event: MouseEvent, data) {
    this.specificationDropDown=this.dummyList
    this.pdcChecklistWebService.specificationDropdown(data).subscribe(response => {
      this.specificationDropDown = response
    })
  }
  onChangeStatic(event: MatCheckboxChange) {
    this.pdcPagePresenter.setValidationForStaticCheckBox()
  }

  getRowSpan(col: object, index: number) {
    return this.spans[index] && this.spans[index]['aggregate'];
  }

  rowSpan(key: string, accessor: Function, listData: Array<object>) {
    console.log("key ", key);
    console.log("accessor ", accessor);
    console.log("listData ", listData);
    for (let i = 0; i < listData.length;) {
      let currentValue = accessor(listData[i]);
      console.log("currentValue ", currentValue);
      let count = 1;
      for (let j = i + 1; j < listData.length; j++) {
        if (currentValue != accessor(listData[j])) {
          break;
        }
        count++;
      }
      if (!this.spans[i]) {
        this.spans[i] = {};
        console.log("this.spans[i] ", this.spans[i]);
      }
      this.spans[i][key] = count;
      console.log(" this.spans ", this.spans);
      i += count;
    }
  }
}