import { Component, OnInit } from '@angular/core';
import { ActivityReportSearchTableContainerService } from './activity-report-search-table-container.service';
import { BaseDto } from 'BaseDto';
import { activityStatusSearchDomain } from 'ActualReportModule';

@Component({
  selector: 'app-activity-report-search-table-container',
  templateUrl: './activity-report-search-table-container.component.html',
  styleUrls: ['./activity-report-search-table-container.component.scss'],
  providers: [ActivityReportSearchTableContainerService]
})
export class ActivityReportSearchTableContainerComponent implements OnInit {
  searchActivityStatusList: BaseDto<Array<activityStatusSearchDomain>>
  totalTableElements: number;

  constructor(private activityReportSearchTableContainerService: ActivityReportSearchTableContainerService) { }

  ngOnInit() {
    this.getSearchActivityStatus();

  }
  private getSearchActivityStatus() {
    this.activityReportSearchTableContainerService.getSearchActivityStatus().subscribe(res => {
      this.searchActivityStatusList = res as BaseDto<Array<activityStatusSearchDomain>>
    })
  }
}
