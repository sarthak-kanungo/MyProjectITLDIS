import { Component, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { EventEmitter } from '@angular/core';
import { PartyMasterSearchPagePresenter } from '../party-master-search-page/partyMasterSearchPagePresenter';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';

@Component({
  selector: 'app-party-search-result',
  templateUrl: './party-search-result.component.html',
  styleUrls: ['./party-search-result.component.scss'],
  providers: [NgswSearchTableService,
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },]
})
export class PartySearchResultComponent implements OnInit {

  @Input() dataTable: DataTable
  @Output() pageChangeValue = new EventEmitter();
  @Input() totalTableElements: number;
  @Input() actionButtons = [];
  @Input() searchFilter: any;
  actionNg: any
address1Ng: any
address2Ng: any
address3Ng: any
adharCardNumberNg: any
categoryNg: any
cityNg: any
contactNameNg: any
countryNg: any
designationNg: any
districtNg: any
emailNg: any
gstNumberNg: any
localityNg: any
mobileNumberNg: any
panNumberNg: any
partyCodeNg: any
partyLocationNg: any
partyNameNg: any
pinCodeNg: any
postOfficeNg: any
stateNg: any
tehsilNg: any
telephoneNg: any
titleNg: any
  // @Output() onUrlChangeEvent: EventEmitter<object> = new EventEmitter<object>();
  // @Output() initialQueryParamsEvent: EventEmitter<object> = new EventEmitter<object>();

  partyMasterSearchForm: FormGroup;
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  isView: boolean;
  public searchValue: ColumnSearch;

  public todaysDate = new Date();

  // size = 10;
  // page = 0;
  hiddenField: boolean = true
  searchFlag: boolean = true;
  public filterData: object
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];
  constructor(private partyMasterSearchPagePresenter: PartyMasterSearchPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  ngOnInit() {
    this.clickOnTableFields = [{ 'title': 'partyCode', 'isClickable': true }, { 'title': 'action', 'isClickable': true }]
    this.isView = true
    this.partyMasterSearchForm = this.partyMasterSearchPagePresenter.partySearchDetailsForm

  }
  actionOnTableRecord(event: object) {
    console.log('event_pcr', event)
    if (event && event['btnAction'] && (event['btnAction'] === 'partyCode')) {
      this.router.navigate(['../view', event['record']['id']], { relativeTo: this.activatedRoute, queryParams: { partyCode: event['record']['partyCode'] } });
    }
    if (event && event['btnAction'] && (event['btnAction'] === 'action')) {
      this.router.navigate(['../edit', event['record']['id']], { relativeTo: this.activatedRoute, queryParams: { partyCode: event['record']['partyCode'] } });
    }
  }
  pageChange(event) {
    if (!!event && event.page >= 0) {
      this.pageChangeValue.emit(event)
      // this.searchFlag=false;
    }
  }
tableClear(){
  this.actionNg=""
  this.address1Ng=""
  this.address2Ng=""
  this.address3Ng=""
  this.adharCardNumberNg=""
  this.categoryNg=""
  this.cityNg=""
  this.contactNameNg=""
  this.countryNg=""
  this.designationNg=""
  this.districtNg=""
  this.emailNg=""
  this.gstNumberNg=""
  this.localityNg=""
  this.mobileNumberNg=""
  this.panNumberNg=""
  this.partyCodeNg=""
  this.partyLocationNg=""
  this.partyNameNg=""
  this.pinCodeNg=""
  this.postOfficeNg=""
  this.stateNg=""
  this.tehsilNg=""
  this.telephoneNg=""
  this.titleNg=""
}
}

