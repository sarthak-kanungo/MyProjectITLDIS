import { AfterViewInit, Component, OnInit } from '@angular/core';
import { JobCardSearchPresenter } from '../search-job-card-page/search-job-card-presenter';
import { FormGroup } from '@angular/forms';
import { JobCardSearchWebService } from './job-card-search-web.service';
import { AutoCompSubModel, AutoDealerCodeSearch, ExcelView, ModelBySeries, PopulateByItemNo, Product, SearchAutocomplete, SeriesByProduct, Variants } from '../../domain/job-card-domain';
import { ToastrService } from 'ngx-toastr';
import { MatAutocompleteSelectedEvent, MatDatepickerInput } from '@angular/material';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { BaseDto } from 'BaseDto';
import { SearchJobCardWebService } from '../search-job-card-page/search-job-card-web.service';
import { PartRequisitionWebService } from '../part-requisition/part-requisition-web.service';

@Component({
  selector: 'app-job-card-search',
  templateUrl: './job-card-search.component.html',
  styleUrls: ['./job-card-search.component.scss'],
  providers: [JobCardSearchWebService, PartRequisitionWebService]

})
export class JobCardSearchComponent implements OnInit,AfterViewInit {
  jobCardForm: FormGroup
  kaiChassisNoList: Array<SearchAutocomplete>
  branchList: Array<SearchAutocomplete>
  kaiJobCardNoList: Array<SearchAutocomplete>
  kaiEngineNoList: Array<SearchAutocomplete>
  PartNoList: Array<SearchAutocomplete>
  kaiCsbNoList: Array<SearchAutocomplete>
  kaiBookingNoList: Array<SearchAutocomplete>
  statusList
  todayDate = new Date()
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  excelViewList: ExcelView[] = [{value: 'VIEW', viewValue: 'Summary List View'},{value: 'ALLEXCEL', viewValue: 'Detail View Direct Excel'}];
  searchform: boolean
  orgLevels = [];
  orgHierLevels1 = [];
  orgHierLevels2 = [];
  orgHierLevels3 = [];
  orgHierLevels4 = [];
  orgHierLevels5 = [];
  isKai: boolean = true
  loginUserType: string;
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  modelBySeries: Array<ModelBySeries> = []
  subModelByModel: Array<AutoCompSubModel> = []
  model: any
  variants: Array<Variants> = []
  branches:any[] = [];

  constructor(
    private presenter: JobCardSearchPresenter,
    private toastr: ToastrService,
    private searchJobCardWebService: SearchJobCardWebService,
    private jobCardSearchWebService: JobCardSearchWebService,
    private partRequisitionWebService: PartRequisitionWebService,
    private loginFormService: LoginFormService,) { 
      this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
    }
  ngOnInit() {
    this.jobCardForm = this.presenter.jobCardBasicSearchForm
    this.chassisNumberChanges()
    this.getBranches(0)
    this.jobcardNoChanges()
    this.engineNoChanges()
    this.csbNoChanges()
    this.bookingNoChanges()
    this.checkForValueChange()
    this.partNumberSearch()
    
    this.jobCardForm.get('excelView').valueChanges.subscribe(response => {
      this.searchJobCardWebService.excelTypeSubject.next(response)
    })
  
    

    this.jobCardForm.get('excelView').patchValue(this.excelViewList[0].value);

    if (this.loginUserType === 'dealer') {
      this.isKai = false;
    } else {
      this.getOrgLevelByHODept();
    }
    this.jobCardSearchWebService.getjobcardStatusList().subscribe(response => {
      this.statusList = response['result'];
    })
    this.jobCardForm.controls.dealerShip.valueChanges.subscribe((res) => {
      if (res) {
        // if (typeof res === 'string') {
        //   this.jobCardForm.controls.dealerName.patchValue('');
        // }
        this.jobCardSearchWebService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })
    this.getProduct()
   
  }
  ngAfterViewInit() {
 

  }
  dealerSelect(event: MatAutocompleteSelectedEvent){
    if(typeof event.option.value == 'object'){
      //this.statesDropdown(event.option.value.id);
      this.getBranches(event.option.value.id);
    }
  }
  private checkForValueChange() {
    this.jobCardForm.get('jobCardDateFrom').valueChanges.subscribe(res => {
      this.minDate = new Date(res)
    })
  }
  private chassisNumberChanges() {
    this.jobCardForm.get('chassisNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
          this.jobCardSearchWebService.searchByChassisNumber(changedValue).subscribe(res => {
              this.kaiChassisNoList = res;
            }, error => {
              this.toastr.error('Error For ChassisNo Autocomplete')
            })
       }
    })
  }

  private jobcardNoChanges() {
    this.jobCardForm.get('jobCardNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
          this.jobCardSearchWebService.searchByJobCardNumber(changedValue).subscribe(res => {
              this.kaiJobCardNoList = res;
            }, error => {
              this.toastr.error('Error For JobCardNo Autocomplete')
            })
       }
    })
  }
  
  private partNumberSearch(){
    this.jobCardForm.get('partNo').valueChanges.subscribe(partNumber => {
      if(partNumber){
      this.partRequisitionWebService.partNumberAutocomplete(partNumber).subscribe(res => {
        this.PartNoList = res;
      });
    }
    })
  }

  private engineNoChanges() {
    this.jobCardForm.get('engineNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
          this.jobCardSearchWebService.searchByEngineNumber(changedValue).subscribe(res => {
              this.kaiEngineNoList = res;
            }, error => {
              this.toastr.error('Error For EngineNo Autocomplete')
            })
       }
    })
  }

  private csbNoChanges() {
    this.jobCardForm.get('csbNo').valueChanges.subscribe(changedValue => {
      if(changedValue){  
          this.jobCardSearchWebService.searchByCsbNumber(changedValue).subscribe(res => {
            this.kaiCsbNoList = res;
          }, error => {
            this.toastr.error('Error For CsbNo Autocomplete')
          })
      }
    })
  }

  private bookingNoChanges() {
    this.jobCardForm.get('bookingNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
          this.jobCardSearchWebService.searchByBookingNumber(changedValue, true).subscribe(res => {
              this.kaiBookingNoList = res;
            }, error => {
              this.toastr.error('')
            })
      }
    })
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
      if (this.jobCardForm.get('jobCardDateTo').value > this.maxDate)
        this.jobCardForm.get('jobCardDateTo').patchValue(this.maxDate);
    }
  }
  // public displayFnChassisNo(selectedOption?: object): string | undefined {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['value'] : undefined;
  // }
  // public displayFnJobNo(selectedOption?: object): string | undefined {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['value'] : undefined;
  // }
  // public displayFnEngineNo(selectedOption?: object): string | undefined {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['value'] : undefined;
  // }
  // public displayFnCsbNo(selectedOption?: object): string | undefined {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['value'] : undefined;
  // }
  // public displayBookingNo(selectedOption?: object): string | undefined {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['code'] : undefined;
  // }

  getOrgLevelByHODept() {
    this.jobCardSearchWebService.getOrgLevelByHODept('SE').subscribe(response => {
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
    this.jobCardSearchWebService.getOrgLevelHierForParent(levelId, hierId).subscribe(response => {
      this.orgHierLevels1 = response['result'];
    });
  }

  getOrgLevelHier2(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.jobCardSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels2 = response['result'];
      });
    } else {
      this.orgHierLevels2 = [];
      this.jobCardForm.controls.orgHierLevel2.reset();
      if (typeof hier === 'string')
        this.jobCardForm.controls.orgHierLevel1.patchValue(undefined);
    }
    this.orgHierLevels3 = [];
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.jobCardForm.controls.orgHierLevel3.reset();
    this.jobCardForm.controls.orgHierLevel4.reset();
    this.jobCardForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier3(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.jobCardSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels3 = response['result'];
      });
    } else {
      this.orgHierLevels3 = [];
      this.jobCardForm.controls.orgHierLevel3.reset();
      if (typeof hier === 'string')
        this.jobCardForm.controls.orgHierLevel2.patchValue(undefined);
    }
    this.orgHierLevels4 = [];
    this.orgHierLevels5 = [];
    this.jobCardForm.controls.orgHierLevel4.reset();
    this.jobCardForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier4(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.jobCardSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels4 = response['result'];
      });
    } else {
      this.orgHierLevels4 = [];
      this.jobCardForm.controls.orgHierLevel4.reset();
      if (typeof hier === 'string')
        this.jobCardForm.controls.orgHierLevel3.patchValue(undefined);
    }
    this.orgHierLevels5 = [];
    this.jobCardForm.controls.orgHierLevel5.reset();
  }
  getOrgLevelHier5(hier) {
    if (typeof hier !== 'string' && hier['child_level'] != null) {
      this.jobCardSearchWebService.getOrgLevelHierForParent(hier['child_level'], hier['org_hierarchy_id']).subscribe(response => {
        this.orgHierLevels5 = response['result'];
      });
    } else {
      this.orgHierLevels5 = [];
      this.jobCardForm.controls.orgHierLevel5.reset();
      if (typeof hier === 'string')
        this.jobCardForm.controls.orgHierLevel4.patchValue(undefined);
    }
  }
  getOrgLevelHier6(hier) {
    if (typeof hier === 'string') {
      this.jobCardForm.controls.orgHierLevel5.patchValue(undefined);
    }
  }
  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  getProduct(){
    this.jobCardSearchWebService.getAllProduct().subscribe(response => {
      this.products = response.result
    })
  }
  selectProduct(value) {
    this.jobCardSearchWebService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response.result
    })
  }
  selectSeries(value) {
    this.jobCardSearchWebService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response.result
    })
  }

  selectModel(value) {
    this.model = value;
    this.jobCardSearchWebService.getSubModel(value).subscribe(response => {
      this.subModelByModel = response.result
    })
  }

  selectSubModel(value) {
    this.jobCardSearchWebService.getVariant(this.model, value).subscribe(response => {
      this.variants = response
    })
  }
  
  compareFnSeries(c1: SeriesByProduct, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
    }
    return c1 && c2 ? c1.series === c2.series : c1 === c2;
  }

  compareFnModel(c1: ModelBySeries, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.model === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.model;
    }
    return c1 && c2 ? c1.model === c2.model : c1 === c2;
  }

  compareFnSubModel(c1: AutoCompSubModel, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subModel === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subModel;
    }
    return c1 && c2 ? c1.subModel === c2.subModel : c1 === c2;
  }

  getBranches(dealerId){
    this.jobCardSearchWebService.getBranchCodeList(dealerId).subscribe(res => {
      this.branches = res['result'];
      if(this.branches && this.branches.length==1){
        this.jobCardForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      }
    });
  }
  

}
