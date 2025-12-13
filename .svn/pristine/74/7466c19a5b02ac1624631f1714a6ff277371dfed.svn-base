import { Component, OnInit, AfterViewInit } from '@angular/core';
import { DealerMasterSearchPageStore } from './dealer-master-search.store';
import { DealerMasterSearchPagePresenter } from './dealer-master-search.presenter';
import { DealerMasterSearchService } from './dealer-master-search.service'
import { FormGroup } from '@angular/forms';
import  { SearchDealerMaster } from '../../domain/dealer-master.domain';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { ObjectUtil } from '../../../../../utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';

@Component({
  selector: 'app-dealer-master-search',
  templateUrl: './dealer-master-search.component.html',
  styleUrls: ['./dealer-master-search.component.scss'],
  providers: [DealerMasterSearchPageStore, DealerMasterSearchPagePresenter, DealerMasterSearchService]
})
export class DealerMasterSearchComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup;
  page=0;
  size=10;
  private searchDealerMaster = {} as SearchDealerMaster;
  isAdvanceSearch: boolean;

  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  searchFilter: void;

  constructor(
    private dealerMasterSearchPagePresenter: DealerMasterSearchPagePresenter,
    private dealerMasterSearchService: DealerMasterSearchService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private iFrameService: IFrameService
  ) { }

  ngOnInit() {
    this.searchForm = this.dealerMasterSearchPagePresenter.SearchForm;
    
  }

  ngAfterViewInit() {
    
    this.searchForm.valueChanges.subscribe(val => {
      this.searchDealerMaster = val;
    })
    this.searchDealerMaster.page = this.searchForm.get('page').value;
    this.searchDealerMaster.size = this.searchForm.get('size').value;

    this.searchDetail();
    this.searchDealer(this.searchDealerMaster);
  }

  private searchDealer(searchDealerMaster) {
    this.dealerMasterSearchService.searchDealer(searchDealerMaster).subscribe(
      res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
        this.totalTableElements = res.count;
      },
      err => {
        console.log('err', err);
      }
      );
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }

  tableAction(event: object) {
    console.log('event_pcr', event)
    if ( event && event['btnAction'] && (event['btnAction'] === 'dealerCode')) {
     this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { dealerCode: event['record']['dealerCode']} });
    }
  }

  pageSizeChange(ev: InfoForGetPagination) {
    console.log('pageSizeChange_pcr', ev);
      this.searchDealerMaster.page = ev['page'];
      this.searchDealerMaster.size = ev['size'];

      this.searchForm.get('page').patchValue(ev['page']);
      this.searchForm.get('size').patchValue(ev['size']);    
      this.searchDetail();  

      this.searchFilter = ObjectUtil.removeNulls(this.searchDealerMaster);
      console.log('this.searchFilter', this.searchFilter);
      this.searchDealer(this.searchDealerMaster);
  }
  etSearchDateValueChange(searchDate: string, columnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, columnKey);
  }
  search() {
    
    this.searchDetail();
    if (this.searchForm.valid) {
      this.searchFilter = ObjectUtil.removeNulls(this.searchDealerMaster);
      this.searchDealer(this.searchDealerMaster);
    }
    else {
      this.tostr.error('Plese select value from list', 'Error');
    }

  }

  searchDetail() {
    if(this.searchForm.get('dealerName').value != null) {
      this.searchDealerMaster.dealerName = this.searchForm.get('dealerName').value.value;
    }
    if(this.searchForm.get('dealerCode').value != null) {
      this.searchDealerMaster.dealerCode = this.searchForm.get('dealerCode').value.value;
    } 
  }

  clearForm () {
    this.searchForm.reset();
    this.searchForm.get('page').patchValue(this.page);
    this.searchForm.get('size').patchValue(this.size); 
    this.searchFilter =  ObjectUtil.removeNulls(this.searchDealerMaster);
    this.searchDealer(this.searchDealerMaster);
  }

  /**
  * ----------Following is state management code------------
  */

 initialQueryParams(event: SearchDealerMaster){ 
  console.log('initialQueryParams event: ', event);
  /* const searchValue = /%2F/g;
  const newValue = '/'; */
  this.searchForm.patchValue(event);
  if(event.dealerName) {
    this.searchForm.get('dealerName').patchValue({value : event.dealerName});
  }
  if(event.dealerCode) {
    this.searchForm.get('dealerCode').patchValue({value : event.dealerCode});
  }
  this.page = event.page;
  this.size = event.size;
  this.searchForm.get('page').patchValue(event.page);
  this.searchForm.get('size').patchValue(event.size);
}

 onUrlChange(event) {
  console.log('onUrlChange event: ', event);
  if (!event) {
  return;
  }
  const {queryParam=null, url=''} = {...event};
  this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.MASTER, { url, queryParam } as UrlSegment);
  }
}

