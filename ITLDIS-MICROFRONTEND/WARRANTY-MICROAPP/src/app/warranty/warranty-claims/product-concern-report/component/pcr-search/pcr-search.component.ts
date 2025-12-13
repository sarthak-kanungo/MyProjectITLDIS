import { Component, OnInit, Input, EventEmitter, Output, ViewChild, AfterViewInit } from '@angular/core';
import { PcrSearchWebService } from './pcr-search-web.service'
import { FormControl, FormGroup } from '@angular/forms';
import { SearchPCRAutoComplete, SearchByModel, FailurePart, PcrStatusModel, AutoDealerCodeSearch } from '../../domain/product-concern-report.domain';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { PcrPageWebService } from '../pcr-page/pcr-page-web.service';
import { PcrSearchPageComponent } from '../pcr-search-page/pcr-search-page.component';
import { StorageLoginUser } from 'LoginDto';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-pcr-search',
  templateUrl: './pcr-search.component.html',
  styleUrls: ['./pcr-search.component.scss'],
  providers: [PcrSearchWebService, PcrPageWebService]
})
export class PcrSearchComponent implements OnInit, AfterViewInit {

  @Input() pcrSearchForm: FormGroup;
  @Input() isAdvanceSearch: boolean;
  states:any[] = []
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr:any[] = []
  orgHierarchyId:number
  pcrData: SearchPCRAutoComplete;
  chassisData: SearchPCRAutoComplete;
  engineData: SearchPCRAutoComplete;
  seriesByProduct
  modelBySeries
  subModelByModel
  variants
  products
  parts
  status: Array<PcrStatusModel>;
  jobCodeData: SearchPCRAutoComplete;
  toDate = new Date();
  fromDate: Date;
  toDate1 = new Date();
  fromDate1: Date;
  newdate = new Date();
  loginUser:any;
  branches:any[] = [];
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  isKai: boolean = true

  @ViewChild('componentchild', { static: false }) childComponent: PcrSearchPageComponent;
  constructor(
    private pcrSearchWebService: PcrSearchWebService
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
    this.pcrSearchForm.controls.dealerShip.valueChanges.subscribe((res) => {
      // this.statesDropdown(0);
      // this.getBranches(0);
      
      if (res && typeof res != 'object' ) {
        this.pcrSearchForm.controls.dealerShip.setErrors({'selectFromList':'Select From List'}) 
        this.pcrSearchWebService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }else {
        this.pcrSearchForm.controls.dealerShip.setErrors(null) 
      }
    });
  }

  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      this.statesDropdown(event.option.value.id);
      this.getBranches(event.option.value.id);
    }
  }

  ngAfterViewInit() {
    this.pcrSearchForm.get('pcrFromDate').valueChanges.subscribe(res => {
      this.fromDate = res
    })
    this.pcrSearchForm.get('jobCardFromDate').valueChanges.subscribe(res => {
      this.fromDate1 = res
    })
  }
  private formValueChanges() {

    this.pcrSearchForm.get('pcrNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompletePcrNo(val);
      }
    });
    this.pcrSearchForm.get('chassisNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompleteSearchChassisNo(val);
      }
    });
    this.pcrSearchForm.get('engineNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteEngineNo(val);
      }
    });
    this.pcrSearchForm.get('jobCardNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteJobCode(val);
      }
    });
    this.pcrSearchForm.get('partNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteParts(val);
      }
    });
  }

  statesDropdown(dealerId) {
    this.pcrSearchWebService.getStates(dealerId).subscribe(response => {
      this.states = response.result;
      this.pcrSearchForm.controls.state.reset();
    });
  }
  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
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
  private searchAutoCompleteEngineNo(txt: string) {
    this.pcrSearchWebService.searchAutoCompleteEngineNo(txt).subscribe(res => {
      this.engineData = res;
    });
  }
  private searchAutoCompleteJobCode(txt: string) {
    this.pcrSearchWebService.searchAutoCompleteJobCode(txt).subscribe(res => {
      this.jobCodeData = res;
    });
  }
  private searchAutoCompleteParts(txt: string){
    this.pcrSearchWebService.getByItemNo(txt).subscribe(res => {
      this.parts = res
    })
  }
  private dropDownStatusModel() {
    this.pcrSearchWebService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.pcrSearchWebService.dropDownStatus().subscribe(res => {
      this.status = res;
    });
  }

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    fieldName == 'pcr' ? this.toDate = event.value : this.toDate1 = event.value;
    if (event && event['value']) {
      fieldName == 'pcr' ? this.fromDate = new Date(event['value']) : this.fromDate1 = new Date(event['value']) ;
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth()+1);
      
      if(maxDate > this.newdate)
        fieldName == 'pcr' ? this.toDate = this.newdate : this.toDate1 = this.newdate;
      else
        fieldName == 'pcr' ? this.toDate = maxDate : this.toDate1 = maxDate;

      if(fieldName == 'pcr' && this.pcrSearchForm.get('pcrToDate').value > this.toDate)
        this.pcrSearchForm.get('pcrToDate').patchValue(this.toDate);  
        
      if(fieldName == 'jobCard' && this.pcrSearchForm.get('jobCardToDate').value > this.toDate1)
        this.pcrSearchForm.get('jobCardToDate').patchValue(this.toDate1);  
    }
  }

  displayCodeFn(obj: FailurePart): string | number | undefined {
    return obj ? obj.code : undefined;
  }

  selectProduct(value) {
    this.pcrSearchWebService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response['result'];
      this.modelBySeries = null;
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectSeries(value) {
    this.pcrSearchWebService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response['result']
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectModel(value) {
    this.pcrSearchWebService.getSubModelByModel(value).subscribe(response => {
      this.subModelByModel = response['result']
      this.variants = null;
    })
  }

  selectSubModel(value) {
    this.pcrSearchWebService.getVariantBySubModel(value).subscribe(response => {
      this.variants = response['result']
    })
  }

  getLevelByDeprtment() {
    if(this.controlsArr.length>0){
      this.controlsArr.forEach(controlName => {
        this.pcrSearchForm.removeControl(controlName);
      });
    }
    this.parentArray=[];
    this.parentArray.length = 0
    this.pcrSearchWebService.getLevelByDepartment("SE").subscribe(res => {
      this.orgHierLevelList = res['result'];
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.pcrSearchForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
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
    this.pcrSearchForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.pcrSearchWebService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  getBranches(dealerId){
    this.pcrSearchForm.controls.branch.reset();
    this.pcrSearchWebService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.pcrSearchForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
}

