import { NgswSearchTableService } from 'ngsw-search-table';
import { BaseDto } from 'BaseDto';
import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS, MatDialogRef } from '@angular/material';
import { activityTypeSearchDomain, activityStatusSearchDomain, AutoActivityNoSearch, SearchFilterActivityProposalDomain, AutoDealerCodeSearch } from 'ActivityProposalModule';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { MarketingActivitySearchService } from './marketing-activity-search.service';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { Router, ActivatedRoute, NavigationEnd, NavigationStart } from '@angular/router';
import { ColumnSearch } from 'ngsw-search-table';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { DateService } from '../../../../../root-service/date.service';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-marketing-activity-search',
  templateUrl: './marketing-activity-search.component.html',
  styleUrls: ['./marketing-activity-search.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    MarketingActivitySearchService, NgswSearchTableService]

})

export class MarketingActivitySearchComponent implements OnInit {

  activityProposalList: FormGroup;
  @Input() totalTableElements: number
  //selected = 10;
  @Input() page:number=0
  @Input() size: number=10;
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue: ColumnSearch = {} as ColumnSearch
  @Input()
  max: Date
  tomorrow = new Date();
  @Input()
  min: Date
  today = new Date();
  minDate: Date;
  maxDate: Date
  isKai: boolean = true;
  searchform: boolean = false;
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  @Input() getActivityType: BaseDto<Array<activityTypeSearchDomain>>
  @Input() getSearchActivityStatus: BaseDto<Array<activityStatusSearchDomain>>
  @Input() dataTable: DataTable
  @Output() autoActivityNumber = new EventEmitter<string>()
  @Input() autoActivityNo: BaseDto<Array<AutoActivityNoSearch>>
  @Output() onSearch: EventEmitter<object> = new EventEmitter<object>()
  actionButtons = [];
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  states
  @Output()
  public loginUserType: string;
  public filterData: object
  searchFlag: boolean = true;

  approveNgModel: any = "";
  activityStatusNgModel: any = "";
  activityNumberNgModel: any = "";
  dealerCodeNgModel: any = "";
  dealerNameNgModel: any = "";
  activityPurposeNgModel: any = "";
  activityTypeNgModel: any = "";
  locationNgModel: any = "";
  numberOfDaysNgModel: any = "";
  proposedBudgetNgModel: any = "";
  maxAllowedBudgetNgModel: any = "";
  approvedAmountNgModel: any = "";
  lastApprovedByNgModel:'';

  searchFilterValues: any;
  key='mas';
  key1="pageChangeValue"


  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private router: Router,
    private actRt: ActivatedRoute,
    private loginFormService: LoginFormService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private marketingActivitySearchService: MarketingActivitySearchService,
    private toastr: ToastrService,
    private dialod: MatDialog,
    private cdr: ChangeDetectorRef
  ) { 
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase();
     this.isButtonEnabled();
    
   }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);  
      localStorage.removeItem(this.key1);
    }
    if(this.loginUserType==='kubota'){
      this.statesDropdown(0);
    }
    this.searchActivityProposalList()
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.activityProposalList.patchValue(this.searchFilterValues);
      this.searchFlag = this.searchFilterValues.searchFlag;
    }
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.activityProposalList.controls.fromDate.valueChanges.subscribe(fromDate => {
      
    })
    
      this.activityProposalList.controls.activityNumber.valueChanges.subscribe((res) => {
        if(res){
          this.autoActivityNumber.emit(res);
        }
      })
    
      this.activityProposalList.controls.dealerCode.valueChanges.subscribe((res) => {
        if(res){
          if (typeof res === 'string') {
            this.activityProposalList.controls.dealerName.patchValue('');
          }
          this.marketingActivitySearchService.getDealerCodeList(res).subscribe(response => {
            this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
          })
        }
      })
    
    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    if (this.activityProposalList.get('activityNumber').value==null && this.activityProposalList.get('activityType').value==null && this.activityProposalList.get('activityStatus').value==null && this.activityProposalList.get('fromDate').value==null && this.activityProposalList.get('toDate').value==null) {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.activityProposalList.get('fromDate').patchValue(backDate);
    this.activityProposalList.get('toDate').patchValue(new Date());
    this.setActivityApprovalSearchResult();
    }
    else{
      localStorage.getItem(this.key)
       this.setActivityApprovalSearchResult();
    }
  }
  
  ngAfterViewInit() {

    
  }
  
  isButtonEnabled(): boolean {
     const currentDate = new Date();
    const currentDay = currentDate.getDate();
    // const create= this.actRt.snapshot.routeConfig.path.split('/')[0] == 'create';
    
    if(currentDay<=15){
      return currentDay<=15 ;
    }
    
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.activityProposalList.get('toDate').value > this.maxDate)
        this.activityProposalList.get('toDate').patchValue(this.maxDate);
    }
  }
  getOrgLevelByHODept() {
    this.marketingActivitySearchService.getOrgLevelByHODept('MK').subscribe(response => {
      this.orgLevels = response['result'];
      /*
      this.orgLevels.forEach( level => {
          this.activityProposalList.addControl(level['LEVEL_CODE'], new FormControl() )
      })
      */
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
      this.activityProposalList.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.activityProposalList.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.activityProposalList.controls.orgHierLevel3.reset();
    this.activityProposalList.controls.orgHierLevel4.reset();
    this.activityProposalList.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.activityProposalList.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.activityProposalList.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.activityProposalList.controls.orgHierLevel4.reset();
    this.activityProposalList.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.activityProposalList.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.activityProposalList.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.activityProposalList.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.marketingActivitySearchService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.activityProposalList.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.activityProposalList.controls.orgHierLevel4.patchValue(undefined);
    }
  }


  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  displayActivityFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['activityNumber'] : undefined;
  }
  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.activityProposalList.controls.dealerName.patchValue(event.option.value.name);
        this.statesDropdown(event.option.value.id);
    } else {
      this.activityProposalList.controls.dealerName.patchValue('');
      this.statesDropdown(0);
    }
  }

  setActivityApprovalSearchResult() {
    this.approveNgModel = "";
    this.activityStatusNgModel = "";
    this.activityNumberNgModel = "";
    this.dealerCodeNgModel = "";
    this.dealerNameNgModel = "";
    this.activityPurposeNgModel = "";
    this.activityTypeNgModel = "";
    this.locationNgModel = "";
    this.numberOfDaysNgModel = "";
    this.proposedBudgetNgModel = "";
    this.maxAllowedBudgetNgModel = "";
    this.approvedAmountNgModel = "";
    this.lastApprovedByNgModel = "";
    if (this.dataTable != undefined || this.dataTable==null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }else{
      this.page = 0;
        this.size = 10;
    }
   
  
    const filtObj = this.activityProposalList.value as SearchFilterActivityProposalDomain;
     let adc = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key1))))
     if(adc){
      filtObj['page'] = adc.page 
      filtObj['size'] = adc.size
    }
    else{

      filtObj['page'] = this.page 
      filtObj['size'] = this.size 
    }
    //  this.searchFlag=true

    filtObj.activityNumber = filtObj.activityNumber ? filtObj.activityNumber['activityNumber'] : null

    filtObj.fromDate = filtObj.fromDate ? this.convertDateToServerFormat(filtObj.fromDate) : null
    filtObj.toDate = filtObj.toDate ? this.convertDateToServerFormat(filtObj.toDate) : null
    filtObj.dealerId = filtObj.dealerCode ? filtObj.dealerCode['id'] : null
    filtObj.hierId = filtObj.orgHierLevel5 ? filtObj.orgHierLevel5['org_hierarchy_id'] : (filtObj.orgHierLevel4 ? filtObj.orgHierLevel4['org_hierarchy_id'] : (filtObj.orgHierLevel3 ? filtObj.orgHierLevel3['org_hierarchy_id'] : (filtObj.orgHierLevel2 ? filtObj.orgHierLevel2['org_hierarchy_id'] : filtObj.orgHierLevel1 ? filtObj.orgHierLevel1['org_hierarchy_id'] : 0)))
    
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);

    if ((filtObj['fromDate'] === null || filtObj['fromDate'] === "" || filtObj['fromDate'] === undefined)) {
      if ((filtObj['toDate'] === null || filtObj['toDate'] === "" || filtObj['toDate'] === undefined)) {
        if (filtObj.searchFlag == true && this.activityProposalList.get('activityNumber').value || this.activityProposalList.get('activityType').value || this.activityProposalList.get('activityStatus').value || this.activityProposalList.get('fromDate').value || this.activityProposalList.get('fromDate').value || this.activityProposalList.get('state').value) {
          this.onSearch.emit(filtObj)
        }
        else {
          this.toastr.error("Please fill atleast one input field");
        }
      }
    } else if ((filtObj['fromDate'] !== null || filtObj['fromDate'] !== "" || filtObj['fromDate'] !== undefined)) {
      if ((filtObj['toDate'] === null || filtObj['toDate'] === "" || filtObj['toDate'] === undefined)) {
        this.toastr.error("Please Select Date Range.");
      } else {
        this.onSearch.emit(filtObj)
      }
    }

  }

  initialQueryParams(event) {
    const searchValue = /%2F/g
    const newValue = '/'
    this.page = event.page
    this.size = event.size
  
    this.activityProposalList.patchValue(event);
    if (event.activityNumber) {
      this.activityProposalList.get('activityNumber').patchValue(event.activityNumber.replace(searchValue, newValue));
      event.activityNumber = event.activityNumber.replace(searchValue, newValue)
    }
  }
  statesDropdown(dealerId) {
    this.marketingActivitySearchService.getStates(dealerId).subscribe(response => {
      this.states = response.result;
      this.activityProposalList.controls.state.reset();
    });
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
  
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }


  clearActivityApprovalSearch() {
    this.activityProposalList.reset()
    // this.setActivityApprovalSearchResult()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
    localStorage.removeItem(this.key1)
   
  }

  actionOnTableRecord(recordData) {
    if (recordData['btnAction'].toLocaleLowerCase() === 'activitynumber') {
      this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.actRt })
    }
    if (recordData['btnAction'].toLowerCase() === 'action') {
      if(recordData.record.action=='Approve')
      {
        this.router.navigate(['approve', btoa(recordData.record.id)], { relativeTo: this.actRt })
      }
      if(recordData.record.action=='Assign Grace Period') 
      {
        this.openConfirmationDialog(recordData.record.id);
      }

    }
  }
  openConfirmationDialog(id){
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: this.setConfirmationModalData('Do you want to assign grace period to selected proposal?'),
    });

    dialogRef.afterClosed().subscribe(response => {
        if(response == 'Confirm'){
          this.marketingActivitySearchService.updateActivityGracePeriod(id).subscribe(response => {
            this.toastr.success('Grace Period has been updated','Success');
            this.setActivityApprovalSearchResult();
          }, error => {
            this.toastr.error('Error while updating details','Transaction Failed')
          })
        }
    });

  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];

    return confirmationData;
  }

 
  pageChangeOfSearchTable(event:any) {
    if (this.page !== event.page || this.size !== event.size) {
      this.page = event.page;
      this.size = event.size;
      this.searchFlag = false;
      const setItem = {
        page: this.page,
        size: this.size,
      };
      localStorage.setItem(this.key1, JSON.stringify(setItem));
      this.setActivityApprovalSearchResult();
    } else {

      const retrievedValue = localStorage.getItem(this.key1); // Use this.key1 instead of 'key1'
      if (retrievedValue) {
        const parsedObject = JSON.parse(retrievedValue);
        this.page = parsedObject.page;
        this.size = parsedObject.size;
        this.searchFlag = false;
      } else {
       
      }

      // console.log('Returned to the same page and size');
    }
  }


  etSearchDateValueChange(searchDate, ColumnKey) {
    console.log(ColumnKey,'coulmnkey')
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  searchActivityProposalList() {
    this.activityProposalList = this.fb.group({
      activityNumber: [null],
      activityType: [null],
      activityStatus: [null],
      fromDate: [null],
      toDate: [null],
      dealerCode: [null],
      dealerName: [{ value: '', disabled: true }],
      orgHierLevel1: [null],
      orgHierLevel2: [null],
      orgHierLevel3: [null],
      orgHierLevel4: [null],
      orgHierLevel5: [null],
      state: [null],
      page:[null],
      size:[null]
    })
  }

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      console.log(formattedDate)
      return formattedDate
    }
    return ''
  }

 

}

