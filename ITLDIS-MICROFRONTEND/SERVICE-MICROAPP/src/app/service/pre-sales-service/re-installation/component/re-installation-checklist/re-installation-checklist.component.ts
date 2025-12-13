import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ReInstallationCheckPoint,SpecificationDropDown } from '../../domain/re-installation-domain';
import { MatCheckboxChange } from '@angular/material';
import { ReInstallationPagePresenter } from '../re-installation-page/re-installation-page-presenter';
import { ReInstallationCommonWebService } from '../../service/re-installation-common-web.service'
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
@Component({
  selector: 'app-re-installation-checklist',
  templateUrl: './re-installation-checklist.component.html',
  styleUrls: ['./re-installation-checklist.component.scss'],
  providers : [ReInstallationCommonWebService]
})
export class ReInstallationChecklistComponent implements OnInit {
  specificationDropDown: Array<SpecificationDropDown>
  spans = [];

    isView: boolean
    isEdit: boolean

  @Input() reInstallationCheckPointListTable: FormGroup
  @Input() set reInstallationCheckList(value : ReInstallationCheckPoint[]){
    if(value){
      this.spans = []
      console.log("value--->", value)
      value.forEach(data => {
        console.log("data type --->", typeof data.defaultTick)
        this.reInstallationPagePresenter.addChecklist(data)
        this.rowSpan('aggregate', d => d.aggregate, value)
      })
    }
   
  }
  @Input() set ViewReInstallationCheckList(value : ReInstallationCheckPoint[]){
    if(value){
      this.spans = []
      value.forEach(data => {
        this.reInstallationPagePresenter.addChecklist(data)
        this.rowSpan('aggregate', d => d.aggregate, value)
      })
    } 
  }

  constructor(
    private reInstallationPagePresenter : ReInstallationPagePresenter,
    private reInstallationCommonWebService : ReInstallationCommonWebService
  ) { }

  ngOnInit() {
      this.viewOrEditOrCreate();
  }

  private viewOrEditOrCreate() {
      if (this.reInstallationPagePresenter.operation === Operation.VIEW) {
        this.isView = true
      } else if (this.reInstallationPagePresenter.operation === Operation.EDIT) {
        this.isEdit = true
      }
    }
  
  onChangeOk(event: MatCheckboxChange, checklist: ReInstallationCheckPoint) {
    checklist.defaultTick = event.checked
  }

  getRowSpan(col: object, index: number) {
    return this.spans[index] && this.spans[index]['aggregate'];
  }

  rowSpan(key: string, accessor: Function, listData: Array<object>) {
    for (let i = 0; i < listData.length;) {
        let currentValue = accessor(listData[i]);
        let count = 1;
        for (let j = i + 1; j < listData.length; j++) {
            if (currentValue != accessor(listData[j])) {
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

    getSpecificationDropdown() {
      this.reInstallationCommonWebService.specificationDropdown('0').subscribe(response => {
        this.specificationDropDown = response
      })
    }
    riSpecificationChange(event: MouseEvent, data) {
      this.reInstallationCommonWebService.specificationDropdown(data).subscribe(response => {
        this.specificationDropDown = response
      })
    }
}
