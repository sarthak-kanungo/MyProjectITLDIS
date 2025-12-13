import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SchemesCommonService } from '../../../schemes-common-service/schemes-common.service';
import { DropDownSchemeTypeDomain } from 'IncentiveSchemeMasterModule';
import { BaseDto } from 'BaseDto';
import { DataTable } from 'ngsw-search-table';
import { NgswSearchTableService } from 'ngsw-search-table';
import { IncentiveSchemeSearchService } from './incentive-scheme-search.service';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-incentive-scheme-master-search-criteria',
  templateUrl: './incentive-scheme-master-search-criteria.component.html',
  styleUrls: ['./incentive-scheme-master-search-criteria.component.scss'],
  providers: [SchemesCommonService]
})
export class IncentiveSchemeMasterSearchCriteriaComponent implements OnInit {
  maxDate: Date | null
  minDate: Date | null
  todaysDate = new Date();
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  private searchFormValues: object = {} as object;
  page = 0;
  size = 10
  key:string = "IncentiveSchemeMaster";
  clearSearchRow = new BehaviorSubject<string>("");

  schemeNoNgModel: any = '';
  schemeDateNgModel: any = '';
  schemeTypeNgModel: any = '';
  statusNgModel: any = '';
  validFromNgModel: any = '';
  validToNgModel: any = '';
  referenceNoNgModel: any = '';

  statusList: string[] = [
    'ACTIVE', 'INACTIVE',
  ];
  schemetypes: string[] = [
    'Wholesale', 'Retail', 'Exchange', 'DSE', 'TM/BM/SM', 'GM', 'Loyalty', 'Referal', 'Booking Scheme'
  ];
  autoComSchemes : any[];
  dropdownSchemeTypeDomain: BaseDto<Array<DropDownSchemeTypeDomain>>
  SearchIncentiveSchemes: FormGroup
  searchFilterValues: any;
  searchFlag:boolean = true;
  userType:string
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private schemesCommonService: SchemesCommonService,
    private ngswSearchTableService: NgswSearchTableService,
    private incentiveSchemeSearchService: IncentiveSchemeSearchService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginFormService
  ) { }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.createSearchIncentiveSchemes()
    
    this.dropDownSchemeType()
    this.minDate = this.todaysDate
    let nextDate = new Date();
    nextDate.setMonth(this.todaysDate.getMonth() + 1);
    this.maxDate = nextDate;
    this.userType = this.loginService.getLoginUserType();    
    if (this.SearchIncentiveSchemes.get('schemefromdate').value == null && this.SearchIncentiveSchemes.get('schemetodate').value == null) {
      this.SearchIncentiveSchemes.get('schemefromdate').patchValue(this.todaysDate);
      this.SearchIncentiveSchemes.get('schemetodate').patchValue(this.maxDate);
      this.searchIncentive();
    }
    else {
      localStorage.getItem(this.key)
      this.searchIncentive();
    }
    this.SearchIncentiveSchemes.get('schemeno').valueChanges.subscribe(value => {
      if(value && typeof value == 'string'){
        this.SearchIncentiveSchemes.get('schemeno').setErrors({selectFromList:'selectFromList'});
        this.incentiveSchemeSearchService.getSchemeNoList(value).subscribe(response => {
          this.autoComSchemes = response['result'];
        })
      }else{
        this.SearchIncentiveSchemes.get('schemeno').setErrors(null);
      }
    })
  }

  displaySchemeNoFn(itm:any){
    return itm ? itm.schemeNo : undefined
  }

  createSchemeMaster() {
    this.router.navigate(['/sales-pre-sales/schemes/incentive-scheme-master/create'])
  }

  createSearchIncentiveSchemes() {
    this.SearchIncentiveSchemes = this.fb.group({
      schemeno: [null, Validators.compose([])],
      status: [null, Validators.compose([])],
      schemeType: [null, Validators.compose([])],
      schemefromdate: [null, Validators.compose([])],
      schemetodate: [null, Validators.compose([])],

    })
  }
  dropDownSchemeType() {
    this.schemesCommonService.dropdownSchemeType().subscribe(response => {
      this.dropdownSchemeTypeDomain = response as BaseDto<Array<DropDownSchemeTypeDomain>>
    })
  }
  actionOnTableRecord(event) {
    if(event['btnAction'].toLowerCase() == 'schemeno'){
      this.router.navigate(['../view'], { queryParams: {id:btoa(event['record']['schemeNo'])},relativeTo: this.activatedRoute })
    }
  }
  
  pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    this.searchIncentive();
  }

  clearSearchForm(){
    
    this.schemeNoNgModel = '';
    this.schemeDateNgModel = '';
    this.schemeTypeNgModel = '';
    this.statusNgModel = '';
    this.validFromNgModel = '';
    this.validToNgModel = '';
    this.referenceNoNgModel = '';
    this.SearchIncentiveSchemes.reset();
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key);
    this.dataTable=null;
  }

  searchIncentive() {
    this.searchFormValues = this.SearchIncentiveSchemes.value;
    localStorage.setItem(this.key, JSON.stringify(this.SearchIncentiveSchemes.value))

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
    this.searchFlag = true;

    this.searchFormValues['page'] = this.page;
    this.searchFormValues['size'] = this.size;
    if(this.searchFormValues['schemeno']){
      this.searchFormValues['schemeno'] = this.searchFormValues['schemeno']['schemeNo']
    }
    if(this.searchFormValues['schemefromdate']){
      this.searchFormValues['schemefromdate'] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues['schemefromdate']);
    }
    if(this.searchFormValues['schemetodate']){
      this.searchFormValues['schemetodate'] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues['schemetodate']);
    }
    this.incentiveSchemeSearchService.searchSchemeMaster(this.searchFormValues).subscribe(res => {
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
  }

  fromDateChange(event) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
    }
  }
 
}
