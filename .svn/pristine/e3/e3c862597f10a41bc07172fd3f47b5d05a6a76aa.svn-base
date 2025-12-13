import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { ActivityReportJobCardDetailsWebService } from './activity-report-job-card-details-web.service';
import { ServiceActivityReportCreatePagePresenter } from '../service-activity-report-create-page/service-activity-report-create-page.presenter';
import { JobCardDetailsByActivityNo, ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-activity-report-job-card-details',
  templateUrl: './activity-report-job-card-details.component.html',
  styleUrls: ['./activity-report-job-card-details.component.scss'],
  providers: [ActivityReportJobCardDetailsWebService]
})
export class ActivityReportJobCardDetailsComponent implements OnInit, OnChanges {

  headsTable: Array<JobCardDetailsByActivityNo>
  @Input() viewActivityReport : ViewServiceActivityReport

  constructor(
    private activityReportJobCardDetailsWebService: ActivityReportJobCardDetailsWebService,
    private serviceActivityReportCreatePagePresenter: ServiceActivityReportCreatePagePresenter,
    private dateService : DateService
  ) { }

  ngOnChanges(){
    if(this.viewActivityReport){
      this.headsTable = this.viewActivityReport.activityJobCardReport
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
        this.ftechJobCardDetails(valueChange.id,
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value),
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value));
      }
    });

    this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').valueChanges.subscribe(valueChange => {
      if (valueChange && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value) {
          this.ftechJobCardDetails(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value.id,
          this.dateService.getDateIntoDDMMYYYY(valueChange),
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').value));
      }
    });

    this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualToDate').valueChanges.subscribe(valueChange => {
      if (valueChange && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value && 
        this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value) {
          this.ftechJobCardDetails(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('activityNo').value.id,
          this.dateService.getDateIntoDDMMYYYY(this.serviceActivityReportCreatePagePresenter.activityReportForm.get('actualFromDate').value),
          this.dateService.getDateIntoDDMMYYYY(valueChange));
      }
    });
  }

  ftechJobCardDetails(id:number, fromDate:string, toDate:string){
    this.activityReportJobCardDetailsWebService.getJobCardDetails(id, fromDate, toDate).subscribe(response => {
      this.headsTable = response
      response.forEach(data => {
        this.serviceActivityReportCreatePagePresenter.addJobCardDetails(data)
      })
    })
  }

}
