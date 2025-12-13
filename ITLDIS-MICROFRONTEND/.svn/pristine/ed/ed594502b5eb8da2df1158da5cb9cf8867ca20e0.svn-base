import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DropDownSpecification, InstallationCheckList } from '../../domain/installation-domain';
import { InstallationPagePresenter } from '../installation-page/installation-page-presenter';
import { MatCheckboxChange } from '@angular/material';
import { Operation } from '../../../../../utils/operation-util';
import { DeliveryInstallationWebService } from '../delivery-installation/delivery-installation-web.service';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';

@Component({
  selector: 'app-field-installation',
  templateUrl: './field-installation.component.html',
  styleUrls: ['./field-installation.component.scss'],
  providers: [DeliveryInstallationWebService]
})
export class FieldInstallationComponent implements OnInit {
  @Input() isView :boolean
  @Input() fInstallationCheckPointListTable: FormGroup
  specificationDropDown: Array<DropDownSpecification>
  spans = []
  @Input() set fiCheckPointByChassisNumber(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.fiAddRow(data)
        this.rowSpan('aggregate', d => d.aggregate, checklist)
      })
    }
  }
  @Input() set viewFiCheckPoint(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.fiAddRowForView(data)
        this.rowSpan('aggregate', d => d.aggregate, checklist)
      })
    }
  }
  @Input() set editFiCheckPoint(checklist: Array<InstallationCheckList>) {
    if (checklist) {
      this.spans = []
      checklist.forEach(data => {
        this.installationPagePresenter.fiAddRow(data)
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
    this.installationPagePresenter.setValidationForFICheckBox()
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
