import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, Validators, FormArray } from '@angular/forms';
import { PscPagePresenter } from '../psc-page/psc-page-presenter';
import { MatCheckboxChange } from '@angular/material';
import { Operation } from '../../../../../utils/operation-util';
import { CheckListByChassisNo ,DropDownDataSpecification} from '../../domain/psc-domain';
import { PdiChecklistWebService } from '../../../pre-delivery-inspection/component/pdi-checklist/pdi-checklist-web.service';
@Component({
  selector: 'app-checklist',
  templateUrl: './checklist.component.html',
  styleUrls: ['./checklist.component.scss'],
  providers: [PdiChecklistWebService]
})
export class ChecklistComponent implements OnInit {
  isView:boolean = false
  @Input() pscCheckListTableForm: FormGroup
  pscCheckListDropDown: DropDownDataSpecification
  constructor(
    private pscPagePresenter: PscPagePresenter,
    private pdiChecklistService: PdiChecklistWebService
  ) { }

  ngOnInit() {
      if (this.pscPagePresenter.operation === Operation.VIEW) {
          this.isView = true;
      }
  }

  onChangeOk(event: MatCheckboxChange, checklist: CheckListByChassisNo) {
    checklist.defaultTick = event.checked
    this.pscPagePresenter.setValidationForCheckBox()
  }

  getRowSpan(col: object, index: number) {
    return this.pscPagePresenter.getRowSpan(col, index)
  }

  getDataForEdit() {
      this.pdiChecklistService.getDropDownCheckListData('0').subscribe(res => {
        this.pscCheckListDropDown = res
      })
    }
    checkListChange(event: MouseEvent, id: any) {
      this.pdiChecklistService.getDropDownCheckListData(id.id).subscribe(res => {
        this.pscCheckListDropDown = res
      })
    }
}