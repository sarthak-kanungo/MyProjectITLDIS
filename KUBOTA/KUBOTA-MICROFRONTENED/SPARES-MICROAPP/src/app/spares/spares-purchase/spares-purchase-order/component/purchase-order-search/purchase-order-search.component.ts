import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { Component, OnInit, Input, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { PurchaseOrderSearchWebService } from './purchase-order-search-web.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { AutoDealerCodeSearch } from 'PurchaseSearchModule'
@Component({
  selector: 'app-purchase-order-search',
  templateUrl: './purchase-order-search.component.html',
  styleUrls: ['./purchase-order-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PurchaseOrderSearchComponent implements OnInit {
  @Input() public searchSparesPoForm: FormGroup;
  @Input() set filterState(state: object) {
    if (state) {
      // this.patchSearchFilter();
    }
  }

  public poNumberAutoList: Observable<(string | object)[]>;
  public partNumberAutoList: Observable<(string | object)[]>;
  public poTypesList: object[] = [];
  public poStatusList: object[] = [];
  //public poDealershipList: object[] = [];
  //public poBranchList: object[] = [];
  public todaysDate: Date = new Date();
  public selectedFromDate: Date;

  loginUserType: string;
  isKai: boolean = true;
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  @Input() public isShowAdvanceFilter
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private searchSparesPoService: PurchaseOrderSearchWebService,
    private changeDetectorRef: ChangeDetectorRef,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginFormService
  ) {
    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
  }

  ngOnInit() {
    this.formFieldValueChanges();
    this.getAllPoTypes();
    this.getAllPoStatus();
    //this.getAllPoBranch();
    //this.getDealerships();

    this.searchSparesPoForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        if (typeof res === 'string') {
          this.searchSparesPoForm.controls.dealerName.patchValue('');
        }
        this.searchSparesPoService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })

    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
   
  }
  ngAfterViewInit() {

  }
  // private patchSearchFilter() {
  //   this.activatedRoute.queryParamMap.subscribe((queyMap: ParamMap) => {
  //     if (queyMap && Object.keys(queyMap['params']).length > 0) {
  //       this.searchSparesPoForm.patchValue(JSON.parse(queyMap.get('searchFilter')));
  //       setTimeout(() => {
  //         this.changeDetectorRef.markForCheck();
  //       }, 1000);
  //     }
  //   })
  // }

  getOrgLevelByHODept() {
    this.searchSparesPoService.getOrgLevelByHODept('SP').subscribe(response => {
      this.orgLevels = response['result'];
      this.getOrgLevelHierForParent(this.orgLevels[0]['LEVEL_ID'], 0);
    })
  }
  getOrgLevelHierForParent(levelId, hierId) {
    this.searchSparesPoService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
      this.orgHierLevels1 = response['result'];
    });
  }

  getOrgLevelHier2(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchSparesPoService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels2 = response['result'];
      });
    } else {
      this.orgHierLevels2 = [];
      this.searchSparesPoForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.searchSparesPoForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchSparesPoForm.controls.orgHierLevel3.reset();
    this.searchSparesPoForm.controls.orgHierLevel4.reset();
    this.searchSparesPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchSparesPoService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.searchSparesPoForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.searchSparesPoForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.searchSparesPoForm.controls.orgHierLevel4.reset();
    this.searchSparesPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchSparesPoService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.searchSparesPoForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.searchSparesPoForm.controls.orgHierLevel3.patchValue(undefined);

    }
    this.orgHierLevels5 = [];
    this.searchSparesPoForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.searchSparesPoService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.searchSparesPoForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.searchSparesPoForm.controls.orgHierLevel4.patchValue(undefined);
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
      this.searchSparesPoForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchSparesPoForm.controls.dealerName.patchValue('');
    }
  }

  public displayPoNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['purchaseOrderNumber'] : undefined;
  }
  public comparePoTypeFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.poType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.poType;
    }
    return c1 && c2 ? c1.poType === c2.poType : c1 === c2;
  }
  public comparePoStatusFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.purchaseOrderStatus === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.purchaseOrderStatus;
    }
    return c1 && c2 ? c1.purchaseOrderStatus === c2.purchaseOrderStatus : c1 === c2;
  }
  public fromDateChange(event: object) {
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
      if (this.searchSparesPoForm.get('toDate').value > this.maxDate)
        this.searchSparesPoForm.get('toDate').patchValue(this.maxDate);
    }
  }
  private formFieldValueChanges() {
    this.poNumberValueChages();
    this.itemNumberValueChages();
  }
  private poNumberValueChages() {
    this.searchSparesPoForm.get('poNumber').valueChanges.subscribe((changedValue: string) => {
      if (changedValue) {
        this.poNumberAutoList = this.searchSparesPoService.searchByPONumber(changedValue);
      }
    })
  }
  private itemNumberValueChages() {
    this.searchSparesPoForm.get('partNumber').valueChanges.subscribe((changedValue: string) => {
      if (changedValue) {
        this.partNumberAutoList = this.searchSparesPoService.searchByPartNumber(changedValue);
      }
    })

  }
  private getAllPoTypes() {
    this.searchSparesPoService.getSparePoTypes().subscribe(res => {
      if (res) {
        this.poTypesList = res['result'];
      }
    })
  }
  private getAllPoStatus() {
    this.searchSparesPoService.getSparePoStatus().subscribe(res => {
      if (res) {
        this.poStatusList = res['result'];
      }
    })
  }
  /*private getAllPoBranch() {
    this.searchSparesPoService.getAllPoBranch().subscribe(res => {
      this.poBranchList = res['result'];
    })
  }
  private getDealerships() {
    this.searchSparesPoService.getDealerships().subscribe(res => {
      this.poDealershipList = res['result'];
    })
  }*/
  /*
  selectProduct(value) {
      console.log(value);
      this.searchEnquiryV2WebService.getSeriesByProduct(value).subscribe(response => {
        console.log(response);
        this.seriesList = response.result
        this.modelsList = [];
        this.submodelsList = [];
        this.variantsList = [];
      })
    }

    selectSeries(value) {
      this.searchEnquiryV2WebService.getModelBySeries(value).subscribe(response => {
        this.modelsList = response.result
        this.submodelsList = [];
        this.variantsList = [];
      })
    }

    selectModel(value) {
      this.model = value;  
      this.productInterestedV2WebService.getSubModel(value).subscribe(response => {
        this.submodelsList = response.result
        this.variantsList = [];
      })
    }

    selectSubModel(value) {
      this.productInterestedV2WebService.getVariant(this.model, value).subscribe(response => {
        this.variantsList = response
      })

    }*/
}
