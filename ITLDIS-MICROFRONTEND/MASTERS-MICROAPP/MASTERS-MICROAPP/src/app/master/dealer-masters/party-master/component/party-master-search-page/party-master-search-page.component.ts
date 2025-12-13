import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, InfoForGetPagination, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { SearchDealerMaster } from 'src/app/master/itldis-masters/dealer-master/domain/dealer-master.domain';
import { IFrameService, IFrameMessageSource, UrlSegment } from 'src/app/root-service/iFrame.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SearchPartyMaster } from '../../domain/party-master-domain';
import { PartyDetailsService } from '../party-details/party-details.service';
import { PartyMasterPageService } from '../party-master-page/party-master-page.service';
import { PartySearchResultComponent } from '../party-search-result/party-search-result.component';
import { PartyMasterSearchPagePresenter } from './partyMasterSearchPagePresenter';
import { PartyMasterSearchPageStore } from './partyMasterSearchPageStore';

@Component({
  selector: 'app-party-master-search-page',
  templateUrl: './party-master-search-page.component.html',
  styleUrls: ['./party-master-search-page.component.scss'],
  providers: [PartyMasterSearchPagePresenter, PartyMasterSearchPageStore, PartyDetailsService,
    PartyMasterPageService]
})
export class PartyMasterSearchPageComponent implements OnInit {

  searchForm: FormGroup
  // partyMasterSearchForm: FormGroup
  page = 0;
  size = 10;
  private searchPartyMaster = {} as SearchPartyMaster;
  clearSearchRow = new BehaviorSubject<string>("");

  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  public totalTableElements: number;
  public searchFilter: any;
  searchFlag: boolean = true
  @ViewChild('partySearchResultComponent',{static: false}) childComponent: PartySearchResultComponent;
  constructor(
    private partyMasterSearchPagePresenter: PartyMasterSearchPagePresenter,
    private partyDetailsService: PartyDetailsService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private iFrameService: IFrameService,
    private partyMasterPageService: PartyMasterPageService

  ) { }

  ngOnInit() {
    // this.partyMasterSearchForm = this.partyMasterSearchPagePresenter.searchPartyMasterForm
    this.searchForm = this.partyMasterSearchPagePresenter.partySearchDetailsForm
    this.searchPartyMaster.page = this.page;
    this.searchPartyMaster.size = this.size;

    this.searchParty(this.searchPartyMaster);
  }
  ngOnChange() {
    this.searchParty(this.searchPartyMaster);
  }
  // ngAfterViewInit() {

  //   this.searchForm.valueChanges.subscribe(val => {
  //     this.searchPartyMaster = val;
  //   })
  //   this.searchPartyMaster.page = this.searchForm.get('page').value;
  //   this.searchPartyMaster.size = this.searchForm.get('size').value;

  //   this.searchDetail();
  //   this.searchParty(this.searchPartyMaster);
  // }
  searchMasterPartyMaster() {
    console.log('search check----------------')
    const searchFormValues = this.searchForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchForm.value))
    if (this.dataTable != undefined) {
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
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    else {
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }

    const temp = this.searchForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.partyDetailsService.searchPartyMaster(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      // .forEach(element => {
      // element.edit='edit'
      // });
      this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  private searchParty(searchPartMaster) {
    this.searchFilter = { ...searchPartMaster };
    this.partyDetailsService.searchPartyMaster(searchPartMaster).subscribe(
      res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
        this.totalTableElements = res['count'];
        res.result.map(((res: any) => {
          if (res.category != 'Co-dealer') {
            res.action = 'Edit';
          }
        })
        )
      },
      err => {
        console.log('err', err);
      }
    );
  }

  pageChange(ev: InfoForGetPagination) {
    // console.log('pageSizeChange_pcr', ev);
    // this.searchPartyMaster.page = ev['page'];
    // this.searchPartyMaster.size = ev['size'];

    // this.searchForm.get('page').patchValue(ev['page']);
    // this.searchForm.get('size').patchValue(ev['size']);
    // this.searchDetail();

    // this.searchFilter = ObjectUtil.removeNulls(this.searchPartyMaster);
    // console.log('this.searchFilter', this.searchFilter);
    
    //commented by Kanhaiya-------------------------------------------------------------------

    this.page = ev.page;
    this.size = ev.size;
    this.searchFlag = false;
    this.searchDetail()
    this.searchMasterPartyMaster()
    // this.searchParty(this.searchPartyMaster);
  }
  // etSearchDateValueChange(searchDate: string, columnKey: string) {
  //   this.searchValue = new ColumnSearch(searchDate, columnKey);
  // }
  search() {
    console.log(this.searchForm.getRawValue())
    this.searchDetail();
    // if (this.searchForm.valid) {
    this.searchFilter = ObjectUtil.removeNulls(this.searchPartyMaster);
    this.searchParty(this.searchPartyMaster);
    // }
    // else {
    //   this.tostr.error('Plese select value from list', 'Error');
    // }

  }

  searchDetail() {
    if (this.searchForm.get('partyName').value != null) {
      this.searchPartyMaster.partyName = this.searchForm.get('partyName').value;
    }
    if (this.searchForm.get('partyCode').value != null) {
      this.searchPartyMaster.partyCode = this.searchForm.get('partyCode').value;
    }
  }

  clearForm() {
    this.searchForm.reset();
    this.searchForm.get('page').patchValue(this.page);
    this.searchForm.get('size').patchValue(this.size);
    this.searchPartyMaster.partyCode = null;
    this.searchPartyMaster.partyName = null;
    this.searchFilter = ObjectUtil.removeNulls(this.searchPartyMaster);
    this.searchParty(this.searchPartyMaster);
    this.clearSearchRow.next("");
    this.childComponent.tableClear()
  }

  /**
  * ----------Following is state management code------------
  */

  initialQueryParams(event: SearchPartyMaster) {
    console.log('initialQueryParams event: ', event);
    /* const searchValue = /%2F/g;
    const newValue = '/'; */
    this.searchForm.patchValue(event);
    if (event.partyName) {
      this.searchForm.get('partyName').patchValue({ value: event.partyName });
    }
    if (event.partyCode) {
      this.searchForm.get('partyCode').patchValue({ value: event.partyCode });
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
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.MASTER, { url, queryParam } as UrlSegment);
  }
}
