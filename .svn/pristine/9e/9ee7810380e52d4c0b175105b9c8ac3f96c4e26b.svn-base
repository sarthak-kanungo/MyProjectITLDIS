import { of, Observable, BehaviorSubject } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { DataTable } from 'ngsw-search-table';
import { NgswSearchTableService } from 'ngsw-search-table';
import { SearchResultChannelFinanceIndentService } from './search-result-channel-finance-indent.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { DateService } from '../../../../../root-service/date.service';
import { MarketingActivitySearchService } from '../../../../activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-search-result-channel-finance-indent',
  templateUrl: './search-result-channel-finance-indent.component.html',
  styleUrls: ['./search-result-channel-finance-indent.component.scss'],
  providers: [NgswSearchTableService, SearchResultChannelFinanceIndentService, MarketingActivitySearchService]
})
export class SearchResultChannelFinanceIndentComponent implements OnInit {
  public indentNumberAutoList: Observable<(string | object)[]>;
  public searchChannelFinanceIndentForm: FormGroup;
  public totalTableElements: number;
  public dealerCategoryList = [];
  public todaysDate = new Date();
  minDate: Date;
  maxDate: Date
  public loginUserType: string;
  public dealerCodesList = [];
  public dataTable: DataTable;
  public allBanksList = [];
  public actionButtons = [];
  public selectedFromDate: Date;
  public searchFilter: any;
  public page = 0;
  public size = 10;
  dealercodeList: BaseDto<Array<any>>
  isKai: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  statusList = ['Under Process', 'Approved', 'Rejected', 'Processed'];
  searchFlag: boolean = true;
  searchFormValues: object = {} as object;
  indentNumberNgModel: any = "";
  dealerCodeNgModel: any = "";
  dealerNameNgModel: any = "";
  bankNameNgModel: any = "";
  flexiLoanAccountNumberNgModel: any = "";
  indentDateNgModel: any = "";
  dealerCategoryNgModel: any = "";
  numberOfDaysNgModel: any = "";
  indentStatusNgModel: any = "";
  searchFilterValues: any;
  key= 'CFI';
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private dateService: DateService,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private loginFormService: LoginFormService,
    private ngswSearchTableService: NgswSearchTableService,
    private searchResultChannelFinanceIndentService: SearchResultChannelFinanceIndentService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private toastr: ToastrService,
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
  }

  ngOnInit() {
   
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem('CFI')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.getAllBanks();
    this.getAllDealerCategory();
    this.getAllDealerCode();
    this.createSearchChannelFinanceIndentForm();
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchChannelFinanceIndentForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');

    this.searchChannelFinanceIndentForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        if (typeof res === 'string') {
          this.searchChannelFinanceIndentForm.controls.dealerName.patchValue('');
        }
        this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<any>>
        })
      }
    })


    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    if(this.searchChannelFinanceIndentForm.get('indentNumber').value==null && this.searchChannelFinanceIndentForm.get('fromDate').value==null && this.searchChannelFinanceIndentForm.get('toDate').value==null && this.searchChannelFinanceIndentForm.get('dealerCategory').value==null && this.searchChannelFinanceIndentForm.get('bank').value==null && this.searchChannelFinanceIndentForm.get('status').value==null){
    this.maxDate = this.todaysDate
    let backDate = new Date();
    backDate.setMonth(this.todaysDate.getMonth() - 1);
    this.minDate = backDate;
    this.searchChannelFinanceIndentForm.get('fromDate').patchValue(backDate);
    this.searchChannelFinanceIndentForm.get('toDate').patchValue(new Date());
    this.searchCfi();
    }
    else{
      localStorage.getItem(this.key)
      this.searchCfi();
    }
  }
  ngAfterViewInit() {
    // this.maxDate = this.todaysDate
    // let backDate = new Date();
    // backDate.setMonth(this.todaysDate.getMonth() - 1);
    // this.minDate = backDate;
    // this.searchChannelFinanceIndentForm.get('fromDate').patchValue(backDate);
    // this.searchChannelFinanceIndentForm.get('toDate').patchValue(new Date());
    // this.searchCfi();
  }
  getOrgLevelByHODept() {
    this.marketingActivitySearchService.getOrgLevelByHODept('SA').subscribe(response => {
      this.orgLevels = response['result'];
      this.getOrgLevelHierForParent(this.orgLevels[0]['LEVEL_ID'], 0);
    })
  }
  getOrgLevelHierForParent(levelId, hierId) {
    this.marketingActivitySearchService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
      this.orgHierLevels1 = response['result'];
    });
  }

  getOrgLevelHier2(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels2 = response['result'];
      });
    } else {
      this.orgHierLevels2 = [];
      this.searchChannelFinanceIndentForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchChannelFinanceIndentForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchChannelFinanceIndentForm.controls.orgHierLevel3.reset();
    this.searchChannelFinanceIndentForm.controls.orgHierLevel4.reset();
    this.searchChannelFinanceIndentForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchChannelFinanceIndentForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchChannelFinanceIndentForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchChannelFinanceIndentForm.controls.orgHierLevel4.reset();
    this.searchChannelFinanceIndentForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchChannelFinanceIndentForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchChannelFinanceIndentForm.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.searchChannelFinanceIndentForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchChannelFinanceIndentForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchChannelFinanceIndentForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }
  private createSearchChannelFinanceIndentForm() {
    this.searchChannelFinanceIndentForm = this.fb.group({
      indentNumber: [null, Validators.compose([])],
      dealerCode: [null, Validators.compose([])],
      dealerCategory: [null, Validators.compose([])],
      bank: [null, Validators.compose([])],
      fromDate: [null, Validators.compose([])],
      toDate: [null, Validators.compose([])],
      orgHierLevel1: [null],
      orgHierLevel2: [null],
      orgHierLevel3: [null],
      orgHierLevel4: [null],
      orgHierLevel5: [null],
      dealerId: [null],
      hierId: [null],
      dealerName: [null],
      status: [null]
    })
    console.log(this.searchChannelFinanceIndentForm,'searchChannelFinanceIndentForm')
    this.indentNumberChanges();
  }
  private indentNumberChanges() {

    this.searchChannelFinanceIndentForm.controls.indentNumber.valueChanges.subscribe(changedValue => {
      if (changedValue) {
        this.searchResultChannelFinanceIndentService.searchByIndentNumber(changedValue).subscribe(res => {
          this.indentNumberAutoList = of(res['result']);
        })
      }
    })

  }
  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.searchChannelFinanceIndentForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchChannelFinanceIndentForm.controls.dealerName.patchValue('');
    }
  }
  searchCfi() {
    this.indentNumberNgModel = "";
    this.dealerCodeNgModel = "";
    this.dealerNameNgModel = "";
    this.bankNameNgModel = "";
    this.flexiLoanAccountNumberNgModel = "";
    this.indentDateNgModel = "";
    this.dealerCategoryNgModel = "";
    this.numberOfDaysNgModel = "";
    this.indentStatusNgModel = "";
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
      this.searchFlag = true;
    }
    this.searchFormValues = this.searchChannelFinanceIndentForm.getRawValue();
    let key = 'searchFilter';
    if (this.checkValidDatesInput()) {
      if (this.searchFlag == true && this.searchChannelFinanceIndentForm.get('indentNumber').value || this.searchChannelFinanceIndentForm.get('fromDate').value || this.searchChannelFinanceIndentForm.get('toDate').value || this.searchChannelFinanceIndentForm.get('dealerCategory').value || this.searchChannelFinanceIndentForm.get('bank').value || this.searchChannelFinanceIndentForm.get('status').value || this.searchChannelFinanceIndentForm.get('dealerCode').value|| this.searchChannelFinanceIndentForm.get('orgHierLevel1').value|| this.searchChannelFinanceIndentForm.get('orgHierLevel2').value|| this.searchChannelFinanceIndentForm.get('orgHierLevel3').value|| this.searchChannelFinanceIndentForm.get('orgHierLevel4').value|| this.searchChannelFinanceIndentForm.get('orgHierLevel5').value) {
        localStorage.setItem(this.key, JSON.stringify(this.searchChannelFinanceIndentForm.value))
        // this.page = 0;
        // this.size = 10;
        this.searchFormValues['page'] = this.page;
        this.searchFormValues['size'] = this.size;
        this.searchResultChannelFinanceIndentService.searchCfi(this.searchFormValues).subscribe(res => {
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
          this.totalTableElements = res['count'];
        })
        console.log('from-date--', this.searchChannelFinanceIndentForm.get('fromDate').value)
     
      }
      else if (this.searchChannelFinanceIndentForm.get('fromDate').value == null && this.searchChannelFinanceIndentForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
        console.log('from-date--', this.searchChannelFinanceIndentForm.get('fromDate').value)
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
   
    this.searchFormValues['dealerId'] = this.searchChannelFinanceIndentForm.value.dealerCode ? this.searchChannelFinanceIndentForm.value.dealerCode['id'] : null
    this.searchFormValues['orgHierId'] = this.searchChannelFinanceIndentForm.value.orgHierLevel5 ? this.searchChannelFinanceIndentForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchChannelFinanceIndentForm.value.orgHierLevel4 ? this.searchChannelFinanceIndentForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchChannelFinanceIndentForm.value.orgHierLevel3 ? this.searchChannelFinanceIndentForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchChannelFinanceIndentForm.value.orgHierLevel2 ? this.searchChannelFinanceIndentForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchChannelFinanceIndentForm.value.orgHierLevel1 ? this.searchChannelFinanceIndentForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))

    Object.keys(this.searchFormValues).forEach(key => {

      if (this.searchFormValues[key] === null) {
        delete this.searchFormValues[key];
      } else {

        this.searchFormValues[key] === 'orgHierLevel1' && delete this.searchFormValues[key];
        this.searchFormValues[key] === 'orgHierLevel2' && delete this.searchFormValues[key];
        this.searchFormValues[key] === 'orgHierLevel3' && delete this.searchFormValues[key];
        this.searchFormValues[key] === 'orgHierLevel4' && delete this.searchFormValues[key];
        this.searchFormValues[key] === 'orgHierLevel5' && delete this.searchFormValues[key];

        if (this.searchFormValues[key] && (key === 'fromDate') || (key === 'toDate')) {

          this.searchFormValues[key] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues[key]);

        }
      }
    })
    // if (this.checkValidDatesInput()) {
    //   if (this.searchFlag == true && this.searchChannelFinanceIndentForm.get('indentNumber').value || this.searchChannelFinanceIndentForm.get('fromDate').value || this.searchChannelFinanceIndentForm.get('toDate').value || this.searchChannelFinanceIndentForm.get('dealerCategory').value || this.searchChannelFinanceIndentForm.get('bank').value || this.searchChannelFinanceIndentForm.get('status').value) {
    //     localStorage.setItem(this.key, JSON.stringify(this.searchChannelFinanceIndentForm.value))
    //     this.searchResultChannelFinanceIndentService.searchCfi(this.searchFormValues).subscribe(res => {
    //       this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
    //       this.totalTableElements = res['count'];
    //     })
    //   }
    // }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchChannelFinanceIndentForm.value

    fltEnqObj.fromDate = this.searchChannelFinanceIndentForm.getRawValue() ? this.searchChannelFinanceIndentForm.value.fromDate : null
    fltEnqObj.toDate = this.searchChannelFinanceIndentForm.getRawValue() ? this.searchChannelFinanceIndentForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }

    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }

  }

  tableActionClick(event: object) {
    console.log("event ", event);
    if (event['btnAction'].toLowerCase() === 'indentnumber') {
      this.router.navigate(['../view', btoa(event['record']['id'])], { relativeTo: this.activatedRoute });
    }
  }
  pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    this.searchCfi();
  }
  getAllBanks() {
    this.searchResultChannelFinanceIndentService.getAllBanks().subscribe(res => {
      this.allBanksList = res['result'];
    })
  }
  getAllDealerCode() {
    this.searchResultChannelFinanceIndentService.getAllDealerCodes().subscribe(res => {
      this.dealerCodesList = res['result'];
    })
  }
  getAllDealerCategory() {
    this.searchResultChannelFinanceIndentService.getAllDealerCategory().subscribe(res => {
      this.dealerCategoryList = res['result'];
    })
  }
  fromDateSelected(event) {

    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.todaysDate)
        this.maxDate = this.todaysDate;
      else
        this.maxDate = maxDate;
      if (this.searchChannelFinanceIndentForm.get('toDate').value > this.maxDate)
        this.searchChannelFinanceIndentForm.get('toDate').patchValue(this.maxDate);
    }
  }
  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  initialQueryParams(event) {
    this.searchChannelFinanceIndentForm.patchValue(event);
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchChannelFinanceIndentForm.patchValue({
      fromDate: event.fromDate ? new Date(event.fromDate) : null,
      toDate: event.toDate ? new Date(event.toDate) : null,
    });
    this.searchCfi();
  }
  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }

  /*  Added by vinay*/
  clearForm() {
    this.searchChannelFinanceIndentForm.reset();
    // this.searchCfi();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  /*  Added by vinay*/
}

