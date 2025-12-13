import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { ReInstallationCommonWebService } from '../../service/re-installation-common-web.service';
import { MachineSeries, AutoServiceStaffName, RiViewHeaderData } from '../../domain/re-installation-domain';
import { Observable } from 'rxjs';
import { ReInstallationPagePresenter } from '../re-installation-page/re-installation-page-presenter';
import { Operation } from '../../../../../utils/operation-util';
import { ReInstallationDetailsWebService } from './re-installation-details-web.service';

@Component({
  selector: 'app-re-installation-details',
  templateUrl: './re-installation-details.component.html',
  styleUrls: ['./re-installation-details.component.scss'],
})
export class ReInstallationDetailsComponent implements OnInit {

  isView: boolean
  isEdit: boolean
  machineseries: MachineSeries
  @Input() reInstallationDetailsForm: FormGroup;
  serviceStaffNameList$: Observable<Array<AutoServiceStaffName>>
  @Output() getEvent = new EventEmitter<MatSelectChange>()

  constructor(
    private reInstallationCommonWebService: ReInstallationCommonWebService,
    private reInstallationPagePresenter: ReInstallationPagePresenter,
    private reInstallationDetailsWebService: ReInstallationDetailsWebService
  ) { }

  ngOnInit() {
    this.viewOrEditOrCreate()
    this.getSystemDate()
    this.machineSeriesDropDown()
    this.getServiceStaffNameList()
  }
  private viewOrEditOrCreate() {
    if (this.reInstallationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.reInstallationPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
  }
  getSystemDate() {
    if (this.reInstallationPagePresenter.operation === Operation.CREATE) {
      this.reInstallationDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const reinstallationDateDate = response['result']
        this.reInstallationDetailsForm.get('reinstallationDate').patchValue(new Date(reinstallationDateDate))
      })
    }
  }

  machineSeriesDropDown() {
    this.reInstallationCommonWebService.getSeries().subscribe(response => {
      this.machineseries = response
    })
  }
  selectionMachineSeries(event: MatSelectChange) {
    this.reInstallationPagePresenter.resetForSeries()
    this.getEvent.emit(event)
  }

  private getServiceStaffNameList() {
    // if (this.reInstallationPagePresenter.operation === Operation.CREATE) {
    this.reInstallationDetailsForm.get('serviceStaffName').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const serviceStaffName = typeof valueChange == 'object' ? valueChange.employeeName : valueChange
        this.autoServiceStaffName(serviceStaffName)
      }
      if (valueChange !== null) {
        this.reInstallationPagePresenter.setErrorForServiceStaffName()
      }
    })
    // }
  }
  autoServiceStaffName(serviceStaffName: string) {
    this.serviceStaffNameList$ = this.reInstallationCommonWebService.serviceStaffNameAuto(serviceStaffName)
  }
  selectedServiceStaffName(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.reInstallationDetailsForm.get('serviceStaffName').setErrors(null);
    }
  }
  onKeyDownServiceStaffName(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.reInstallationDetailsForm.get('serviceStaffName').reset()
    }
  }
  displayFnServiceStaffName(serviceStaffName: AutoServiceStaffName) {
    return serviceStaffName ? serviceStaffName.employeeName : undefined
  }

  compareFn(c1: MachineSeries, c2: RiViewHeaderData): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
    }
    return c1 && c2 ? c1.series === c2.series : c1 === c2;
  }
}