
import { ColumnSearch } from 'ngsw-search-table';

import { Component, OnInit } from '@angular/core';
import { MarketingActivitySearchContainerService } from './marketing-activity-search-container.service';
import { activityTypeSearchDomain, activityStatusSearchDomain, AutoActivityNoSearch, ActivityProposalListSearchDomain, SearchFilterActivityProposalDomain } from 'ActivityProposalModule';
import { DataTable } from 'ngsw-search-table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { BaseDto } from 'BaseDto';
@Component({
  selector: 'app-marketing-activity-search-container',
  templateUrl: './marketing-activity-search-container.component.html',
  styleUrls: ['./marketing-activity-search-container.component.scss'],
  providers: [MarketingActivitySearchContainerService]
})
export class MarketingActivitySearchContainerComponent implements OnInit {

  searchActivityTypeList: BaseDto<Array<activityTypeSearchDomain>>
  searchActivityStatusList: BaseDto<Array<activityStatusSearchDomain>>
  autoActivityNo: BaseDto<Array<AutoActivityNoSearch>>
  activitySearchList$: Observable<Array<ActivityProposalListSearchDomain>>
  recordId: number
  dataTable: DataTable
  dataset: any[];
  //For SearchFilter
  searchValue: ColumnSearch = {} as ColumnSearch
  totalTableElements: number;
 page
 size

  constructor(
    private marketingActivitySearchContainerService: MarketingActivitySearchContainerService,
    private tableDataService: TableDataService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.getActivityType();
    this.getSearchActivityStatus();
    
  } 

  private getActivityType() {
    this.marketingActivitySearchContainerService.getSearchActivityType().subscribe(res => {
      this.searchActivityTypeList = res as BaseDto<Array<activityTypeSearchDomain>>
    })
  }
  private getSearchActivityStatus() {
    this.marketingActivitySearchContainerService.getSearchActivityStatus().subscribe(res => {
      this.searchActivityStatusList = res as BaseDto<Array<activityStatusSearchDomain>>
      console.log(this.searchActivityStatusList);
    })
  }

  autoActivityNumber(event) {
    this.marketingActivitySearchContainerService.getActivityNo(event,'PROPOSAL').subscribe(response => {
      console.log('response', response);
      this.autoActivityNo = response as BaseDto<Array<AutoActivityNoSearch>>
    });
  }

  onSearch(filter: SearchFilterActivityProposalDomain) {
    this.marketingActivitySearchContainerService
      .searchUsingFilter(filter).subscribe(data => {
       this.dataTable = this.tableDataService.convertIntoDataTable(data.result)  
        this.totalTableElements = data.count
       })
  }
 
}
