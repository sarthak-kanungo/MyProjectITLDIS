import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SearchJobCardWebService } from './search-job-card-web.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DateService } from '../../../../../root-service/date.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction, } from '../../../../../confirmation-box/confirmation-box.component'
import { MatDialog } from '@angular/material'
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-job-card-page',
  templateUrl: './search-job-card-page.component.html',
  styleUrls: ['./search-job-card-page.component.scss'],
  providers: [SearchJobCardWebService]
})
export class SearchJobCardPageComponent implements OnInit, AfterViewInit {
  jobCardSearchForm: FormGroup
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  public totalTableElements: number;
  searchValue: ColumnSearch;
  searchFilter;
  searchFlag:boolean=true;
  searchFilterValues: any;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ngswSearchTableService: NgswSearchTableService,
    private searchJobCardWebService: SearchJobCardWebService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private dialog: MatDialog,
    private toastr : ToastrService
  ) { }

  ngOnInit() {
   
  }
  
  ngAfterViewInit() {
    this.searchJobCard();

  }

  searchJobCard() {

    let searchFormValues = {};
    
    if(this.searchFlag==true)
    {
      this.page=0;
      this.size=10;
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    else{
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }

    this.searchJobCardWebService.searchJobCardTable(searchFormValues).subscribe(res => {
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
    this.searchFlag=true;
  }
  tableAction(event: object) {
    let id= btoa(event['record']['jobCardNo'])
    let searchfilter= btoa(JSON.stringify(this.searchFilter))
    if (event['btnAction'].toLowerCase() === 'jobcardno') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: id, hasButton: false }
      })
    }
    if (event['btnAction'].toLowerCase() === 'approve') {    
      this.router.navigate(['../approval'], {
        relativeTo: this.activatedRoute, queryParams: { id: id }
      })
    }
    
  }

  initialQueryParams(event) {
    this.page = event.page
    this.size = event.size
  }

  
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchJobCard();
  }

  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  }
  
  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    console.log('modifiedDate: ', modifiedDate);
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }  
 }

