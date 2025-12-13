import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { StockAdjustmentSearchPagePresenter } from '../search-stock-adjustment-page/search-stock-adjustment.presenter';
import { SearchPageWebService } from '../search-stock-adjustment-page/search-page-web.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { BaseDto } from 'BaseDto';
import { AutoDealerCodeSearch } from 'SearchInventoryModule';

@Component({
  selector: 'app-search-inventory-adjustment',
  templateUrl: './search-inventory-adjustment.component.html',
  styleUrls: ['./search-inventory-adjustment.component.scss'],
  providers : [SearchPageWebService]
})
export class SearchInventoryAdjustmentComponent implements OnInit {
  
  stockSearchForm: FormGroup
  adjustmentNos$
  today = new Date();
  minDate: Date;
  maxDate: Date
  adjustmentList:any
  loginUserType:string
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  isKai: boolean = true;
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  status: any;
  constructor(
    private presenter: StockAdjustmentSearchPagePresenter,
    private activateRoute: ActivatedRoute,
    public searchPageWebService: SearchPageWebService,
    private loginService:LoginFormService
  ) {
    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
  }
  
  ngOnInit() {
    this.dropDownStatusModel();
    this.getOrgLevelByHODept();
    this.stockSearchForm = this.presenter.stockSearch;
    this.stockSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        if (typeof res === 'string') {
          this.stockSearchForm.controls.dealerName.patchValue('');
        }
        this.searchPageWebService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })
    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    this.stockSearchForm.get('stockAdjustmentNo').valueChanges.subscribe(val => {
        if(val && typeof val != 'object'){
            this.stockSearchForm.get('stockAdjustmentNo').setErrors({ selectFromList: 'Please select from list' });
            this.searchPageWebService.searchAdjustmentNoAuto(val).subscribe(response => {
                this.adjustmentNos$ = response;
            })
        }else{
            this.stockSearchForm.get('stockAdjustmentNo').setErrors(null);
            this.adjustmentNos$ = null;
        }
    })
  }
  ngAfterViewInit(){
    
  }
  getOrgLevelByHODept() {
    this.searchPageWebService.getOrgLevelByHODept('SP').subscribe(response => {
      this.orgLevels = response['result'];
      this.getOrgLevelHierForParent(this.orgLevels[0]['LEVEL_ID'], 0);
    })
  }
  getOrgLevelHierForParent(levelId, hierId) {
    this.searchPageWebService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
      this.orgHierLevels1 = response['result'];
    });
  }

  getOrgLevelHier2(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchPageWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels2 = response['result'];
      });
    } else {
      this.orgHierLevels2 = [];
      this.stockSearchForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.stockSearchForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.stockSearchForm.controls.orgHierLevel3.reset();
    this.stockSearchForm.controls.orgHierLevel4.reset();
    this.stockSearchForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchPageWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.stockSearchForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.stockSearchForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.stockSearchForm.controls.orgHierLevel4.reset();
    this.stockSearchForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchPageWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.stockSearchForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.stockSearchForm.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.stockSearchForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchPageWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.stockSearchForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.stockSearchForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.stockSearchForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.stockSearchForm.controls.dealerName.patchValue('');
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
      if (this.stockSearchForm.get('adjustmentToDate').value > this.maxDate)
        this.stockSearchForm.get('adjustmentToDate').patchValue(this.maxDate);
    }
  }
 dropDownStatusModel() {
    this.searchPageWebService.getLookupByCode('adjustment_status').subscribe(res => {
      this.adjustmentList = res['result'];
    });
  }
  displayFnAdjustNo(adjustmentNo){
      if (typeof adjustmentNo === 'string') {
          return adjustmentNo;
        }
        return adjustmentNo ? adjustmentNo.adjustmentNo : undefined
  }
}
