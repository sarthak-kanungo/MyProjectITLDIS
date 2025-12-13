import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InstallationPagePresenter } from '../installation-page/installation-page-presenter';
import { MatCheckboxChange } from '@angular/material';
import { InstallationCheckList, DropDownSpecification } from '../../domain/installation-domain';
import { DeliveryInstallationWebService } from './delivery-installation-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';

@Component({
  selector: 'app-delivery-installation',
  templateUrl: './delivery-installation.component.html',
  styleUrls: ['./delivery-installation.component.scss'],
  providers: [DeliveryInstallationWebService]
})
export class DeliveryInstallationComponent implements OnInit {
  @Input() isView :boolean
  @Input() dInstallationCheckPointListTable: FormGroup
  specificationDropDown: Array<DropDownSpecification>
  spans = []
  @Input() set diCheckPointByChassisNumber(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.diAddRow(data)
        this.rowSpan('aggregate', d => d.aggregate, checklist)
      })
    }
  }
  @Input() set viewDiCheckPoint(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.diAddRowForView(data)
        this.rowSpan('aggregate', d => d.aggregate, checklist)
      })
    }
  }
  @Input() set editDiCheckPoint(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.diAddRow(data)
        this.rowSpan('aggregate', d => d.aggregate, checklist)
      })
    }
  }

  constructor(
    private installationPagePresenter: InstallationPagePresenter,
    private deliveryInstallationWebService: DeliveryInstallationWebService
  ) { }

  ngOnInit() {
    if (this.installationPagePresenter.operation === Operation.VIEW || this.installationPagePresenter.operation === Operation.EDIT) {
      this.getSpecificationDropdown()
    }
  }

  onChangeOk(event: MatCheckboxChange, checklist: InstallationCheckList) {
    checklist.defaultTick = event.checked
    this.installationPagePresenter.setValidationForDICheckBox()
  }

  getSpecificationDropdown() {
    this.deliveryInstallationWebService.specificationDropdown('11').subscribe(response => {
      this.specificationDropDown = response
    })
  }

  installationSpecificationChange(event: MouseEvent, data) {
    this.deliveryInstallationWebService.specificationDropdown(data).subscribe(response => {
      this.specificationDropDown = response
    })
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