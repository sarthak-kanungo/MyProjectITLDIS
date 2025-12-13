import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DateService } from '../../../../../root-service/date.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';

import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { StockAdjustmentSearchPagePresenter } from './search-activity-claim-presenter';
import { StockAdjustmentSearchStore } from './search-activity-claim-store';
import { SearchActivityClaimService } from './search-activity-claim.service';
import { SearchActivityClaimListDomain } from 'ActivityClaimModule';
import { MatDialog } from '@angular/material';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { BaseDto } from 'BaseDto';
import { ActivityClaimNumberDomain } from 'ActivityClaimModule';
import { ActivityTypeOfClaimDropdownDomain } from 'ActivityClaimModule';
import { ClaimStatusDomain } from 'ActivityClaimModule';
import { ActivityNumberDomain } from 'ActivityClaimModule';
import { MarketingActivitySearchService } from '../../../activity-proposal/component/marketing-activity-search/marketing-activity-search.service';

@Component({
  selector: 'app-search-activity-claim',
  templateUrl: './search-activity-claim.component.html',
  styleUrls: ['./search-activity-claim.component.scss'],
  providers:[StockAdjustmentSearchStore,StockAdjustmentSearchPagePresenter,MarketingActivitySearchService]
})
export class SearchActivityClaimComponent implements OnInit {
  mainSearchActivityForm: FormGroup
  activityClaimSearchForm: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0;
  size: number = 10;
  searchFlag: boolean = true;
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  public filterData:SearchActivityClaimListDomain ;
  clearSearchRow = new BehaviorSubject<string>("");
  key = 'stap';
  key1='pagechange'
  today = new Date();
  minDate: Date;
  maxDate: Date;
  usertype:string
  searchFilterValues: any;
  isKai:boolean=false;
  dropdownActivityType: BaseDto<Array<ActivityTypeOfClaimDropdownDomain>>
  dropdownActivityClaimStatus: BaseDto<Array<ClaimStatusDomain>>
  autoActivityNo: BaseDto<Array<ActivityNumberDomain>>
  autoClaimNo: BaseDto<Array<ActivityClaimNumberDomain>>
  selectedActivityNo: ActivityNumberDomain
  lastApprovedByNgModel = ''
  claimNumberNgModel: any = "";
  claimStatusNgModel: any = "";
  claimDateNgModel: any = "";
  activityNumberNgModel: any = "";
  activityCreationDateNgModel: any = "";
  activityTypeNgModel: any = "";
  numberOfDaysNgModel: any = "";
  fromDateNgModel: any = "";
  toDateNgModel: any = "";
  actualFromDateNgModel: any = "";
  actualToDateNgModel: any = "";
  locationNgModel: any = "";
  hotEnquiriesNgModel: any = "";
  warmEnquriesNgModel: any = "";
  coldEnquiriesNgModel: any = "";
  costPerEnquiryNgModel: any = "";
  costPerHotEnquiryNgModel: any = "";
  totalBookingsNgModel: any = "";
  costPerBookingNgModel: any = "";
  totalRetailsNgModel: any = "";
  costPerRetailNgModel: any = "";
  activityEffectivenessNgModel: any = "";
  approvedAmountNgModel: any = "";
  actualClaimAmountNgModel: any = "";
  rsnDateNgModel: any = "";
  rsnNumberNgModel: any = "";
  approveNgModel: any = "";
  dealercodeList: any;
  parentArray: any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr: any[] = []
  orgHierarchyId: number;
  states: Object[];
 
  constructor(

    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    public dialog: MatDialog,
    private router: Router,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private loginService : LoginFormService,
    private presenter:StockAdjustmentSearchPagePresenter,
    private store:StockAdjustmentSearchStore,
    private searchActivityClaimService:SearchActivityClaimService,
    private actClaimRt:ActivatedRoute,
    private activityProposalSearchService:MarketingActivitySearchService
  ) { 
    this.usertype = loginService.getLoginUserType();
    if(this.usertype.toLocaleLowerCase()=='kubota'){
      this.isKai = true;
    }
  }

  ngAfterViewInit() {

  }
  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);  
      localStorage.removeItem(this.key1);
    }
    // this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key))))

    this.mainSearchActivityForm = this.presenter.stockAdjustmentSearchForm
    this.activityClaimSearchForm = this.presenter.stockSearch;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.activityClaimSearchForm.patchValue(this.searchFilterValues)
    }
    this.getActivityClaimStatus();
    this.getActivityType();
    if (this.usertype === 'kubota') {
      this.isKai = true;
      this.statesDropdown(0);
      this.getLevelByDeprtment();
    }
    this.activityClaimSearchForm.controls.activityNumber.valueChanges.subscribe(value => {
      if (value) {
        this.autoActivityNumber(value)
      }
    })
    this.activityClaimSearchForm.controls.claimNumber.valueChanges.subscribe(value => {
      if (value) {
        this.autoClaimNumber(value)
      }
    });

    this.activityClaimSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.activityProposalSearchService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
        })
      }
    });
  
    if (this.activityClaimSearchForm.get('fromDate').value == null && this.activityClaimSearchForm.get('toDate').value == null) {
      // debugger
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.activityClaimSearchForm.get('fromDate').patchValue(backDate);
      this.activityClaimSearchForm.get('toDate').patchValue(new Date());
       this.searchData();
    }
    else {
      localStorage.getItem(this.key)
      this.searchData();
    }

  }
  clearForm() {
    this.mainSearchActivityForm.reset();
    // this.searchData();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  autoActivityNumber(event) {
    this.searchActivityClaimService.getActivityNumber(event).subscribe(response => {
      // console.log('response', response);
      this.autoActivityNo = response as BaseDto<Array<ActivityNumberDomain>>
    })
  }

  autoClaimNumber(event) {
    this.searchActivityClaimService.getClaimNumber(event).subscribe(response => {
      // console.log('response', response);
      this.autoClaimNo = response as BaseDto<Array<ActivityClaimNumberDomain>>
    })
  }
  private getActivityType() {
    this.searchActivityClaimService.getActivityType().subscribe(res => {
      this.dropdownActivityType = res as BaseDto<Array<ActivityTypeOfClaimDropdownDomain>>
    })
  }
  private getActivityClaimStatus() {
    this.searchActivityClaimService.getClaimStatus().subscribe(res => {
      this.dropdownActivityClaimStatus = res as BaseDto<Array<ClaimStatusDomain>>
    })
  }
  searchData() {
    
    if (this.activityClaimSearchForm.valid) {
      if (this.dataTable != undefined) {
        if (this.searchFlag == true) {
           this.page = 0;
           this.size = 10;
          // this.activityClaimSearchForm.patchValue({
          //   page: this.page ,
          //   size:this.size
          // })
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
        else {
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
      }
      const temp = this.activityClaimSearchForm.getRawValue();
      console.log(temp,'temp')
      localStorage.setItem(this.key, JSON.stringify(this.activityClaimSearchForm.value))

      let adc = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key1))))
    
      if(adc){
      temp['page'] = Number(adc.page) 
      temp['size'] = Number(adc.size)
      }else{
        temp['page'] = this.page 
        temp['size'] = this.size 
      }
      temp['dealerId'] = this.activityClaimSearchForm.value.dealerCode ? this.activityClaimSearchForm.value.dealerCode['id'] : null
      temp['hierId'] = this.activityClaimSearchForm.value.orgHierLevel5 ? this.activityClaimSearchForm.value.orgHierLevel5['org_hierarchy_id'] : (this.activityClaimSearchForm.value.orgHierLevel4 ? this.activityClaimSearchForm.value.orgHierLevel4['org_hierarchy_id'] : (this.activityClaimSearchForm.value.orgHierLevel3 ? this.activityClaimSearchForm.value.orgHierLevel3['org_hierarchy_id'] : (this.activityClaimSearchForm.value.orgHierLevel2 ? this.activityClaimSearchForm.value.orgHierLevel2['org_hierarchy_id'] : this.activityClaimSearchForm.value.orgHierLevel1 ? this.activityClaimSearchForm.value.orgHierLevel1['org_hierarchy_id'] : null)))
      this.filterData = this.removeNullFromObjectAndFormatDate(temp);
      console.log(this.filterData,'filterData')
      if (this.checkValidDatesInput()) {
        if (this.activityClaimSearchForm.get('claimStatus').value ||this.activityClaimSearchForm.get('claimNumber').value ||this.activityClaimSearchForm.get('activityNumber').value  ||this.activityClaimSearchForm.get('activityType').value  || this.activityClaimSearchForm.get('fromDate').value || this.activityClaimSearchForm.get('toDate').value) {
          this.setSearchResultTable(
            this.filterData
          );
        }
        else if (this.activityClaimSearchForm.get('fromDate').value == null && this.activityClaimSearchForm.get('toDate').value == null&& this.activityClaimSearchForm.get('claimStatus').value == null) {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }
    } else {
      this.activityClaimSearchForm.markAllAsTouched();
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.activityClaimSearchForm.value

    fltEnqObj.fromDate = this.activityClaimSearchForm.getRawValue() ? this.activityClaimSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.activityClaimSearchForm.getRawValue() ? this.activityClaimSearchForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'fromDate1'];
    let toDates = ['toDate', 'toDate2'];
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
  initialQueryParams(event) {
    const searchValue = /%2F/g;
    const newValue = '/';
      this.page = event.page
     this.size = event.size
  this.activityClaimSearchForm.get('page').patchValue(event.page);
  this.activityClaimSearchForm.get('size').patchValue(event.size);

  }

  fromDateSelected(event) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.activityClaimSearchForm.get('toDate').value > this.maxDate)
        this.activityClaimSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    // console.log('etSearchDateValueChange', searchDate);
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  private setSearchResultTable(searchValue: any) {
    this.searchActivityClaimService.searchUsingFilter(searchValue).subscribe(searchRes => {
      if(!this.isKai){
        searchRes['result'].forEach( result => {
          delete result['dealerCode'];
          delete result['dealerName'];
        })
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
      console.log( this.totalSearchRecordCount)
    });
  }
  pageChangeOfSearchTable(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    // this.activityClaimSearchForm.patchValue({
    //   page: this.page ,
    //   size:this.size
    // })
    const setItem={
      page:this.page,
      size:this.size
    }
    localStorage.setItem(this.key1, JSON.stringify(setItem))
    this.searchData();
  }
  actionOnTableRecord(recordData: any) {
    if (recordData['btnAction'].toLocaleLowerCase() === 'claimnumber') {
      this.router.navigate(['view', btoa(recordData.record.id)], { relativeTo: this.actClaimRt })
    }
    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action.toLowerCase() == 'approve') {
        this.router.navigate(['approve', btoa(recordData.record.id)], { relativeTo: this.actClaimRt })
      }

      if (recordData.record.action.toLowerCase() == 'generate invoice') {
        this.openConfirmDialog(recordData.record.id);
      }
    }
    if (recordData.record.edit === 'Edit' && recordData.btnAction == 'edit') {
      this.router.navigate(['edit', btoa(recordData.record.id)], { relativeTo: this.actClaimRt })
    }
  }
  private openConfirmDialog(id): void | any {
    let message = 'Do you want to Generate Invoice?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      this.generateInvoice(id);
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
  generateInvoice(claimId: number) {
    this.searchActivityClaimService.generateInvoice(claimId, 'MarketingActivityClaim').subscribe(response => {
      if (response) {
        this.toastr.success(response['result']['msg']);
        if (response['result']['status'] === 'OK') {
          this.searchData();
        }
      } else {
        this.toastr.error('Invoice Generation Failed');
      }
    })
  }
  
  public removeNullFromObjectAndFormatDate(searchObject: any) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'fromDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && (element === 'toDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        

      });
      return searchObject;
    }
  }
  getLevelByDeprtment() {
    if (this.controlsArr.length > 0) {
      this.controlsArr.forEach(controlName => {
        this.activityClaimSearchForm.removeControl(controlName);
      });
    }
    this.parentArray = [];
    this.parentArray.length = 0
    this.activityProposalSearchService.getOrgLevelByHODept("MK").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if (this.orgHierLevelList && this.orgHierLevelList.length > 0) {
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.activityClaimSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.activityClaimSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.activityProposalSearchService.getOrgLevelHierForParent(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index + 1] = this.childArray;
      for (let i = index + 2; i < this.parentArray.length; i++) {
        this.parentArray[i] = [];
      }
    })
  }

  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.activityProposalSearchService.getOrgLevelHierForParent(levelId, orgHierId + "").subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0] = this.childArray;
    })
  }
  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  statesDropdown(dealerId) {
    this.searchActivityClaimService.getStates(dealerId).subscribe(response => {
      this.states = response.result;
      this.activityClaimSearchForm.controls.state.reset();
    });
  }

  



  
  
}
