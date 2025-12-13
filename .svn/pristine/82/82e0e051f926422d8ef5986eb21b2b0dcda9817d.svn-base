import { Component, OnInit, AfterViewInit } from '@angular/core';
import { DeliveryChallanCancellationApprovalService } from './delivery-challan-cancellation-approval.service';
import { NgswSearchTableService, DataTable, ColumnSearch } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../root-service/iFrame.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DateService } from '../../../root-service/date.service';

@Component({
  selector: 'app-delivery-challan-cancellation-approval',
  templateUrl: './delivery-challan-cancellation-approval.component.html',
  styleUrls: ['./delivery-challan-cancellation-approval.component.css'],
  providers: [DeliveryChallanCancellationApprovalService, {
      provide: DateAdapter, useClass: AppDateAdapter
  },
  {
    provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
  }]
})
export class DeliveryChallanCancellationApprovalComponent implements OnInit, AfterViewInit {
  public page = 0;
  public size = 10;
  public dataTable: DataTable;
  public totalTableElements: number;
  public searchValue: ColumnSearch = {} as ColumnSearch
  searchFlag: boolean = true;
  
  constructor(
          private deliveryChallanCancellationApprovalService: DeliveryChallanCancellationApprovalService,
          private tableDataService: NgswSearchTableService,
          private iFrameService: IFrameService,
          private router : Router,
          private activatedRoute: ActivatedRoute) {  }

  ngOnInit() {
      
  }

  ngAfterViewInit() {
      this.getDeliveryPendingListForApproval();
  }
  getDeliveryPendingListForApproval(){
    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
      this.deliveryChallanCancellationApprovalService.getDeliveryPendingListForApproval(this.page,this.size).subscribe(res => {
          if (res) {
              this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
              this.totalTableElements = res['count'];
            }
      });
  }
  
  public pageChange(event) {
      if (!!event && event.page >= 0) {
        this.page = event.page;
        this.size = event.size
        this.searchFlag= false
        this.getDeliveryPendingListForApproval();
      }
  }
  
  public actionOnTableRecord(recordData) {
      if (recordData['btnAction'] === 'deliveryChallanNumber') {
        this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
      }
      if (recordData['btnAction'] === 'approve') {
          this.router.navigate(['approve', btoa(recordData.record.id)], { relativeTo: this.activatedRoute }) ;
      }
    }
  public dateChanges(event, keyInObject: string) {
      if (event && event['value']) {
        const date: Date = event.value as Date
        const searchValue = {
          searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
          keyInObject
        };
        this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.keyInObject);
      } else {
        this.searchValue = new ColumnSearch("", keyInObject);
      }
    }
}
