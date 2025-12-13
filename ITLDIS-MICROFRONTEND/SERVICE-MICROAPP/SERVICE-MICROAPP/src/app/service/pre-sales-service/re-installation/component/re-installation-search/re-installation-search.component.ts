import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MachineSeries, AutoServiceStaffName, ReinstallationNumber } from '../../domain/re-installation-domain';
import { ReInstallationCommonWebService } from '../../service/re-installation-common-web.service';
import { Observable } from 'rxjs';
import { ReInstallationSearchWebService } from './re-installation-search-web.service';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-re-installation-search',
  templateUrl: './re-installation-search.component.html',
  styleUrls: ['./re-installation-search.component.scss'],
  providers: [ReInstallationSearchWebService]
})
export class ReInstallationSearchComponent implements OnInit, AfterViewInit {

  @Input() reInstallationSearchForm: FormGroup
  machineseries: MachineSeries
  serviceStaffNameList$: Observable<Array<AutoServiceStaffName>>
  reinstallationNumber$: Observable<Array<ReinstallationNumber>>
  minDate: Date;
  newdate = new Date()
  maxDate: Date

  constructor(
    private reInstallationCommonWebService: ReInstallationCommonWebService,
    private reInstallationSearchWebService: ReInstallationSearchWebService
  ) { }

  ngOnInit() {
    this.machineSeriesDropDown()
    this.getServiceStaffNameList()
    this.getReinstallationNoList()
  
  }
  ngAfterViewInit() {
  

  }
  machineSeriesDropDown() {
    this.reInstallationCommonWebService.getSeries().subscribe(response => {
      this.machineseries = response
    })
  }

  private getServiceStaffNameList() {
    this.reInstallationSearchForm.get('serviceStaffName').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const serviceStaffName = typeof valueChange == 'object' ? valueChange.employeeName : valueChange
        this.autoServiceStaffName(serviceStaffName)
      }
    })
  }
  autoServiceStaffName(serviceStaffName: string) {
    this.serviceStaffNameList$ = this.reInstallationCommonWebService.serviceStaffNameAuto(serviceStaffName)
  }

  private getReinstallationNoList() {
    this.reInstallationSearchForm.get('reInstallationNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const reinstallationNo = typeof valueChange == 'object' ? valueChange.reinstallationNumber : valueChange
        this.autoReinstallationNo(reinstallationNo)
      }
    })
  }
  autoReinstallationNo(reinstallationNo: string) {
    this.reinstallationNumber$ = this.reInstallationSearchWebService.reInstallationNumberAuto(reinstallationNo)
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate){
        this.maxDate = this.newdate;
      }
      else
     {   this.maxDate = maxDate;
      if (this.reInstallationSearchForm.get('toDate').value > this.maxDate)
        this.reInstallationSearchForm.get('toDate').patchValue(this.maxDate);
     }
    }
  }
}
