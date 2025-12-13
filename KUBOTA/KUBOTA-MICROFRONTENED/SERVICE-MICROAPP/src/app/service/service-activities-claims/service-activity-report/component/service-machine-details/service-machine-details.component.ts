import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { ServiceActivityReportCreatePagePresenter } from '../service-activity-report-create-page/service-activity-report-create-page.presenter';
import { ServiceMachineDetailsWebService } from './service-machine-details-web.service';
import { MachineDetailsByActivityNo, ServiceDetailsByActivityNo, ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { FormArray } from '@angular/forms';
import { element } from 'protractor';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-service-machine-details',
  templateUrl: './service-machine-details.component.html',
  styleUrls: ['./service-machine-details.component.scss'],
  providers: [ServiceMachineDetailsWebService]
})
export class ServiceMachineDetailsComponent implements OnInit, OnChanges {

  machineDetailsTable: Array<MachineDetailsByActivityNo>
  serviceDetailsTable: Array<ServiceDetailsByActivityNo>
  isMachineDetails: boolean
  isServiceDetails: boolean
  totalForMachine = 0
  totalForService = 0
  @Input() viewActivityReport: ViewServiceActivityReport

  constructor(
    private serviceActivityReportCreatePagePresenter: ServiceActivityReportCreatePagePresenter,
    private serviceMachineDetailsWebService: ServiceMachineDetailsWebService,
    private dateService:DateService
  ) { }

  ngOnChanges() {
    if (this.viewActivityReport) {
      this.machineDetailsTable = this.viewActivityReport.activityMachineReport
      this.serviceDetailsTable = this.viewActivityReport.activityServiceReport
      this.isMachineDetails = true
      this.isServiceDetails = true
      this.machineDetailsTable.forEach(element => {
        this.totalForMachine += element.noOfMachines
      })
      this.serviceDetailsTable.forEach(element => {
        this.totalForService += element.serviceTypeCount
      })
    }
  }

  ngOnInit() {
    this.selectActivityNumberEvent()
  }

  selectActivityNumberEvent() {
    this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').valueChanges.subscribe(valueChange => {
      if (valueChange.id && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value) {
        this.fetchMachineDetails(valueChange.id,
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value),
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value));
      }
    });
    
    this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').valueChanges.subscribe(valueChange => {
      if (valueChange && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value) {
          this.fetchMachineDetails(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value.id,
          this.dateService.getDateIntoDDMMYYYY(valueChange),
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value));
      }
    });

    this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').valueChanges.subscribe(valueChange => {
      if (valueChange && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value) {
          this.fetchMachineDetails(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value.id,
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value),
          this.dateService.getDateIntoDDMMYYYY(valueChange));
      }
    });

  }
//

  fetchMachineDetails(activityId:number, fromDate:string, toDate:string){
    this.totalForMachine = 0
    this.totalForService = 0
    this.isMachineDetails = true
    this.isServiceDetails = true
    this.serviceMachineDetailsWebService.getMachineDetails(activityId, fromDate, toDate).subscribe(response => {
      this.machineDetailsTable = response
      response.forEach(data => {
        this.serviceActivityReportCreatePagePresenter.addMachineDetails(data)
      })
      this.totalCalculationForMachine()
    })
    this.serviceMachineDetailsWebService.getServiceDetails(activityId,fromDate, toDate).subscribe(response => {
      this.serviceDetailsTable = response
      response.forEach(data => {
        this.serviceActivityReportCreatePagePresenter.addServiceDetails(data)
      })
      this.totalCalculationForService()
    })
  }
  totalCalculationForMachine() {
    this.machineDetailsTable.forEach(element => {
      this.totalForMachine += element.noOfMachines
    })
  }


  totalCalculationForService() {
    this.serviceDetailsTable.forEach(element => {
      this.totalForService += element.serviceTypeCount
    })
  }

}
