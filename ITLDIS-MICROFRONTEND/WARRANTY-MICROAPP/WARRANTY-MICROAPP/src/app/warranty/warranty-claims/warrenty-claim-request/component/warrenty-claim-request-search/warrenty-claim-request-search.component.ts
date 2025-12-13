import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PcrWebService } from '../../../product-concern-report/component/pcr/pcr-web.service';
import { LogSheetSearchService } from '../../../log-sheet/component/log-sheet-search/log-sheet-search.service';
import { PcrSearchWebService } from '../../../product-concern-report/component/pcr-search/pcr-search-web.service';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { WarrentyClaimRequestSearchWebService } from './warrenty-claim-request-search-web.service'
import {
  SearchPCRAutoComplete,
  SearchByModel,
  FailureType,
  AutoDealerCodeSearch
} from '../../../product-concern-report/domain/product-concern-report.domain';
import { WcrDropdown, WcrFinalstatus } from '../../domain/warrenty-claim-request.domain';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';
import { BaseDto } from 'BaseDto';
import { WarrantyClaimUploadComponent } from '../warranty-claim-upload/warranty-claim-upload.component';

@Component({
  selector: 'app-warrenty-claim-request-search',
  templateUrl: './warrenty-claim-request-search.component.html',
  styleUrls: ['./warrenty-claim-request-search.component.scss'],
  providers: [PcrWebService, PcrPageWebService, PcrSearchWebService, LogSheetSearchService, WarrentyClaimRequestSearchWebService]
})
export class WarrentyClaimRequestSearchComponent implements OnInit {
  @Input() searchForm: FormGroup;
    status: Array<string> = [];
  isAdvanceSearch: boolean;
  chassisData: SearchPCRAutoComplete;
  jobCodeData: SearchPCRAutoComplete;
  model: SearchByModel;
  failureType: FailureType[];
  mobileData: SearchPCRAutoComplete;
  pcrData: SearchPCRAutoComplete;
  toDate: Date;
  toDate1: Date;
  toDate2: Date;
  wcrType: WcrDropdown;
  wcrStatus: WcrDropdown;
  autoCompleteData: SearchPCRAutoComplete;
  fromDate: Date;
  newdate = new Date();
  states:any[] = []
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  branches:any[] = [];
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  loginUser:any;
  
  isKai:boolean=true;
 finalstatus:WcrFinalstatus
 
  constructor(
    private pcrSearchWebService: PcrSearchWebService,
    private pcrService: PcrWebService,
    private pcrPageWebService: PcrPageWebService,
    private logSheetSearchService: LogSheetSearchService,
    private warrentyClaimRequestSearchWebService: WarrentyClaimRequestSearchWebService,
  ) { }

  ngOnInit() {
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    this.formValueChanges();
    this.getBranches(0)
    this.dropDownStatusModel();
    if(this.loginUser.userType!='dealer'){
      this.getLevelByDeprtment();
      this.statesDropdown(0);
    }else{
      this.isKai = false;
    }
    this.formValueChanges();
    this.dropDownFailureType();
    this.dropDownStatusModel();
    this.searchWcrType();
    this.searchStatus();
    this.getSystemDate();
    this.searchfinalStatus()
    this.statesDropdown(0);
    this.searchForm.controls.dealerShip.valueChanges.subscribe((res) => {
      // this.statesDropdown(0);
      // this.getBranches(0);
      if (res && typeof res != 'object' ) {
        this.searchForm.controls.dealerShip.setErrors({'selectFromList':'Select From List'}) 
        this.pcrSearchWebService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }else {
        this.searchForm.controls.dealerShip.setErrors(null) 
      }
    });
  }
  
  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      this.statesDropdown(event.option.value.id);
      this.getBranches(event.option.value.id);
    }
  }
  

  private formValueChanges() {
    this.searchForm.get('pcrNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompletePcrNo(val);
      }
    });
    this.searchForm.get('chassisNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompleteSearchChassisNo(val);
      }
    });

    this.searchForm.get('jobCardNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteJobCode(val);
      }
    });
    this.searchForm.get('mobileNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchCustomerMobileNo(val);
      }
    });
    this.searchForm.get('wcrNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteWcrNo(val);
      }
    })
  }

  private autoCompletePcrNo(txt: string) {
    this.pcrSearchWebService.autoCompletePcrNo(txt).subscribe(res => {
      this.pcrData = res;
    });
  }
  private autoCompleteSearchChassisNo(txt: string) {
    this.pcrSearchWebService.autoCompleteSearchChassisNo(txt).subscribe(res => {
      this.chassisData = res;
    });
  }
  private searchAutoCompleteJobCode(txt: string) {
    this.pcrSearchWebService.searchAutoCompleteJobCode(txt).subscribe(res => {
      this.jobCodeData = res;
    });
  }
  private searchAutoCompleteWcrNo(txt: string) {
    this.warrentyClaimRequestSearchWebService.searchAutoCompleteWcrNo(txt).subscribe(res => {
      this.autoCompleteData = res;
      console.log('this.autoCompleteData', this.autoCompleteData);
    });
  }
  private searchWcrType() {
    this.warrentyClaimRequestSearchWebService.searchWcrType().subscribe(res => {
      this.wcrType = res;
      console.log('res_type', res);
    });
  }
  private searchfinalStatus() {
    this.warrentyClaimRequestSearchWebService.searchFinalStatus().subscribe(res => {
      this.finalstatus = res;
    });
  }
  private searchStatus() {
    this.warrentyClaimRequestSearchWebService.searchStatus().subscribe(res => {
      this.wcrStatus = res;
      console.log('res_status', res);
    });
  }
  private dropDownStatusModel() {
    this.pcrSearchWebService.dropDownStatusModel().subscribe(res => {
      this.model = res;
    });
  }
  private dropDownFailureType() {
    this.pcrService.dropDownFailureType('0').subscribe(
      result => {
        this.failureType = result;
      },
      err => {
        console.log('err', err);
      }
    );
  }
  private searchCustomerMobileNo(txt: string) {
    this.logSheetSearchService.searchCustomerMobileNo(txt).subscribe(
      res => {
        this.mobileData = res;
      },
      err => {
        console.log('err', err);
      }
    );
  }

  private getSystemDate() {
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.toDate = new Date(res);
    });
  }

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    if (fieldName == 'wcr') {
      this.toDate = event.value;
    } else if (fieldName == 'pcr') {
      this.toDate1 = event.value;
    } else {
      this.toDate2 = event.value;
    }
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.searchForm.get('wcrToDate').value > this.toDate)
        this.searchForm.get('wcrToDate').patchValue(this.toDate);
    }
  }

  displayCodeFn(obj: SearchPCRAutoComplete): string | number | undefined {
    return obj ? obj.code : undefined;
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.searchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.pcrSearchWebService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.pcrSearchWebService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.searchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.pcrSearchWebService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  getBranches(dealerId){
    this.searchForm.controls.branch.reset();
    this.pcrSearchWebService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.searchForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  statesDropdown(dealerId) {
    this.pcrSearchWebService.getStates(dealerId).subscribe(response => {
      this.states = response.result;
      this.searchForm.controls.state.reset();
    });
  }
}
