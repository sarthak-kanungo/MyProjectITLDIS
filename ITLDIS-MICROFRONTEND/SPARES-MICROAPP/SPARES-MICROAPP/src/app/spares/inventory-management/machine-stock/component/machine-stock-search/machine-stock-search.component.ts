import { Component, OnInit } from '@angular/core';
import { MachineStockSearchPagePersenter } from '../machine-stock-search-page/machine-stock-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { SubModel, Product, Variants, ItemNumber, PopulateByItemNo, Series, Model, AutoDealerCodeSearch, SearchMachineStock } from '../../domain/machine-stock';
import { PurchaseOrderSearchWebService } from '../../../../spares-purchase/spares-purchase-order/component/purchase-order-search/purchase-order-search-web.service';
import { CurrentStockService } from '../../../current-stock/component/search-current-stock/current-stock.service';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { MachineStockSearchPageService } from '../machine-stock-search-page/machine-stock-search-page.service';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { MatDatepickerInput } from '@angular/material';

@Component({
  selector: 'app-machine-stock-search',
  templateUrl: './machine-stock-search.component.html',
  styleUrls: ['./machine-stock-search.component.css'],
  providers : [PurchaseOrderSearchWebService, CurrentStockService]
})
export class MachineStockSearchComponent implements OnInit {

  machineStockSearchForm: FormGroup;
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  minDate=new Date()
  maxDate=new Date()
  newdate=new Date()
  products: Array<Product> = []
  seriesByProduct: Array<Series> = []
  modelBySeries: Array<Model> = []
  subModelByModel: Array<SubModel> = []
  variants: Array<Variants> = []
  model: any
  
  loginUserType:string;
  isKai:boolean = true;
  public dealercodeList : BaseDto<Array<AutoDealerCodeSearch>>
  public partNumberAutoList;
  
  constructor(private searchPagePresenter : MachineStockSearchPagePersenter,
          public purchaseOrderSearchWebService :PurchaseOrderSearchWebService,
          private activatedRoute: ActivatedRoute,
          private loginService : LoginFormService,
          private machineStockSearchPageService : MachineStockSearchPageService,
          private currentStockService: CurrentStockService) { 
      
      this.machineStockSearchForm = this.searchPagePresenter.machineStockSearchForm;
      this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
      
  }
  statusList = [
    // { id: 1, name: "Active",value:"Y" },
    { id: 1, name: "Dealer Inventory",value:"1" },
    {id:2, name:"Machine In-transit",value:"0"}
  ];

   ngOnInit() {
     
      this.getItemNumber()
      this.getDealerCode()
      if(this.loginUserType === 'dealer'){
          this.isKai = false;
      }else{
          this.getOrgLevelByHODept();
      }
      this.getProduct();
   }
   
   getDealerName(event){
      if (typeof event.option.value === 'object') {
          this.machineStockSearchForm.controls.dealerName.patchValue(event.option.value.name);
      }else{
          this.machineStockSearchForm.controls.dealerName.patchValue('');
      }
   }
   dateSelectionChange(event: MatDatepickerInput<Date>) {
    this.machineStockSearchForm.get('invoiceToDate').setErrors(null);
    if (event && event['value']) {
      if (this.machineStockSearchForm.get('invoiceToDate').value < this.machineStockSearchForm.get('invoiceFromDate').value){
        this.machineStockSearchForm.get('invoiceToDate').setErrors({'invalidDateRange':'Please select valid date range'});
        this.machineStockSearchForm.get('invoiceToDate').markAsTouched();
      }
    }
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.machineStockSearchForm.get('invoiceToDate').value > this.maxDate)
        this.machineStockSearchForm.get('invoiceFromDate').patchValue(this.maxDate);
    }
  }


   getItemNumber(){
    this.machineStockSearchForm.get('itemNo').valueChanges.subscribe((changedValue: string) => {
      if(changedValue){
      this.currentStockService.searchItemNo(changedValue).subscribe(response => {
        this.partNumberAutoList = response['result'];
        
      });
    }
  })
   }

   getDealerCode(){
    this.machineStockSearchForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res || typeof res === 'string') {
          this.machineStockSearchForm.controls.dealerName.patchValue('');
      this.purchaseOrderSearchWebService.getDealerCodeList(res).subscribe(response => {
        this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
      })
    }
  });
   }
  
   displayDealerCodeFn(selectedOption?: object): string | undefined {
      if (!!selectedOption && typeof selectedOption === 'string') {
        return selectedOption;
      }
      return selectedOption ? selectedOption['code'] : undefined;
    }
    getOrgLevelByHODept() {
      this.purchaseOrderSearchWebService.getOrgLevelByHODept('SA').subscribe(response => {
        this.orgLevels = response['result'];
        this.getOrgLevelHierForParent(this.orgLevels[0]['LEVEL_ID'], 0);
      })
    }
    getOrgLevelHierForParent(levelId, hierId) {
      this.purchaseOrderSearchWebService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
        this.orgHierLevels1 = response['result'];
      });
    }

    getOrgLevelHier2(hier) {
      if (typeof hier !== 'string' && hier['child_level'] != null) {
        this.purchaseOrderSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
          this.orgHierLevels2 = response['result'];
        });
      } else {
        this.orgHierLevels2 = [];
        this.machineStockSearchForm.controls.orgHierLevel2.reset();
        if (typeof hier === 'string')
          this.machineStockSearchForm.controls.orgHierLevel1.patchValue(undefined);
      }
      this.orgHierLevels3 = [];
      this.orgHierLevels4 = [];
      this.orgHierLevels5 = [];
      this.machineStockSearchForm.controls.orgHierLevel3.reset();
      this.machineStockSearchForm.controls.orgHierLevel4.reset();
      this.machineStockSearchForm.controls.orgHierLevel5.reset();
    }
    getOrgLevelHier3(hier) {
      if (typeof hier !== 'string' && hier['child_level'] != null) {
        this.purchaseOrderSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
          this.orgHierLevels3 = response['result'];
        });
      } else {
        this.orgHierLevels3 = [];
        this.machineStockSearchForm.controls.orgHierLevel3.reset();
        if (typeof hier === 'string')
          this.machineStockSearchForm.controls.orgHierLevel2.patchValue(undefined);
      }
      this.orgHierLevels4 = [];
      this.orgHierLevels5 = [];
      this.machineStockSearchForm.controls.orgHierLevel4.reset();
      this.machineStockSearchForm.controls.orgHierLevel5.reset();
    }
    getOrgLevelHier4(hier) {
      if (typeof hier !== 'string' && hier['child_level'] != null) {
        this.purchaseOrderSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
          this.orgHierLevels4 = response['result'];
        });
      } else {
        this.orgHierLevels4 = [];
        this.machineStockSearchForm.controls.orgHierLevel4.reset();
        if (typeof hier === 'string')
          this.machineStockSearchForm.controls.orgHierLevel3.patchValue(undefined);
      }
      this.orgHierLevels5 = [];
      this.machineStockSearchForm.controls.orgHierLevel5.reset();
    }
    getOrgLevelHier5(hier) {
      if (typeof hier !== 'string' && hier['child_level'] != null) {
        this.purchaseOrderSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
          this.orgHierLevels5 = response['result'];
        });
      } else {
        this.orgHierLevels5 = [];
        this.machineStockSearchForm.controls.orgHierLevel5.reset();
        if (typeof hier === 'string')
          this.machineStockSearchForm.controls.orgHierLevel4.patchValue(undefined);
      }
    }
    getOrgLevelHier6(hier) {
      if (typeof hier === 'string') {
        this.machineStockSearchForm.controls.orgHierLevel5.patchValue(undefined);
      }
    }
    
    compareFnSeries(c1: Series, c2: PopulateByItemNo): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
        if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
      }
      return c1 && c2 ? c1.series === c2.series : c1 === c2;
    }

    compareFnModel(c1: Model, c2: PopulateByItemNo): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === 'object' && typeof c2 === 'string') return c1.model === c2;
        if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.model;
      }
      return c1 && c2 ? c1.model === c2.model : c1 === c2;
    }

    compareFnSubModel(c1: SubModel, c2: PopulateByItemNo): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subModel === c2;
        if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subModel;
      }
      return c1 && c2 ? c1.subModel === c2.subModel : c1 === c2;
    }

    getProduct(){
        this.machineStockSearchPageService.getAllProduct().subscribe(response => {
            this.products = response.result
          })
    }
      selectProduct(value) {
        this.machineStockSearchPageService.getSeriesByProduct(value).subscribe(response => {
          this.seriesByProduct = response
          this.modelBySeries = null;
          this.subModelByModel = null;
          this.variants = null;
          this.machineStockSearchForm.controls.series.reset();
          this.machineStockSearchForm.controls.model.reset();
          this.machineStockSearchForm.controls.subModel.reset();
          this.machineStockSearchForm.controls.variant.reset();
        })
      }
    
      selectSeries(value) {
        this.machineStockSearchPageService.getModelBySeries(value).subscribe(response => {
          this.modelBySeries = response;
          this.subModelByModel = null;
          this.variants = null;
          this.machineStockSearchForm.controls.model.reset();
          this.machineStockSearchForm.controls.subModel.reset();
          this.machineStockSearchForm.controls.variant.reset();
        })
      }
    
      selectModel(value) {
        this.model = value;
        this.machineStockSearchPageService.getSubModel(value).subscribe(response => {
          this.subModelByModel = response;
          this.variants = null;
          this.machineStockSearchForm.controls.subModel.reset();
          this.machineStockSearchForm.controls.variant.reset();
        })
      }
    
      selectSubModel(value) {
        this.machineStockSearchPageService.getVariant(this.model, value).subscribe(response => {
          this.variants = response;
          this.machineStockSearchForm.controls.variant.reset();
        })
    
      }
      // private searchChassisNumber(chassisNumber) {
      //   this.searchAllotmentDeAllotmentService.searchChassisNumbers(chassisNumber).subscribe(chassisNumberRes => {
      //     this.chassisNumberList = chassisNumberRes.result;
      //   })
      // }
      // private searchEngineNumber(engineNumber) {
      //   this.searchAllotmentDeAllotmentService.searchByEngineNumber(engineNumber, this.loginUser.id.toString()).subscribe(engineNumberRes => {
      //     this.engineNumberList = engineNumberRes.result;
      //   })
      // }
}
