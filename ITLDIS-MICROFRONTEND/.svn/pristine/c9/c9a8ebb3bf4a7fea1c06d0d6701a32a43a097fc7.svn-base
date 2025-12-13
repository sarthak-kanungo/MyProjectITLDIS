import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { DataTable, ColumnSearch } from 'ngsw-search-table';
import { AutoModel, ChassisNoSelect, DealerDetails, FieldLevel, LevelHierarchy, SubModel } from '../../domain/survey-summary-report-domain';
import { SurveySummaryReportService } from '../../survey-summary-report.service';
import { SurveySummaryReportSearchPagePresenter } from '../search-survey-summary-report/survey-summary-report-search-page.presenter';
import { SurveySummaryReportSearchPageStore } from '../search-survey-summary-report/survey-summary-report-search-page.store';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AssignOrgHierarchyToDealerPopUpService } from '../../assign-org-hierarchy-to-dealer-pop-up.service';
import { FirstTimeBuyer } from '../../../direct-survey/domain/direct-survey-domain';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
import { AutoCompSubModel, ModelBySeries, PopulateByItemNo, Product, SeriesByProduct, Variants } from 'src/app/service/customer-service/job-card/domain/job-card-domain';

@Component({
  selector: 'app-survey-summary-report-search-result',
  templateUrl: './survey-summary-report-search-result.component.html',
  styleUrls: ['./survey-summary-report-search-result.component.scss'],
  providers:[JobCardSearchWebService]
})
export class SurveySummaryReportSearchResultComponent implements OnInit {
    dcMaxDate=new Date();
  surveySummaryReportForm: FormGroup;
  dealerList$: DealerDetails[] = []
  chassisNoList$: ChassisNoSelect[] = []
  surveyName$: BaseDto<Array<any>>;
  @Input() surveyStatusList: BaseDto<Array<any>>
  customerSatisfactionList: BaseDto<Array<any>>
  @Input() surveyDateList: BaseDto<Array<any>>
  surveyNameList: BaseDto<Array<any>>
  allocateTerritoryList: BaseDto<Array<any>>
  areaLevelList: BaseDto<Array<any>>

  dropdownZoneDomain: BaseDto<Array<any>>
  dropdownRegionDomain: BaseDto<Array<any>>
  dropdownAreaDomain: BaseDto<Array<any>>
  dropdownTerritoryDomain: BaseDto<Array<any>>
  selectedZones: any = null
  selectedRegions:any = null
  selectedArea:any = null
  selectedTerritory:any=null
  selectedModels: Array<any> = null
  selectedSubModels: Array<any> = null

  @Input() dataTable: DataTable
  @Output() pageChangeValue = new EventEmitter();
  @Input() totalTableElements: number;
  @Input() actionButtons = [];
  @Input() searchFilter: any;
  public searchValue: ColumnSearch;
  searchFlag: boolean = true;
  public filterData: object
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];

 // model: BaseDto<Array<AutoModel>>
  subModel: BaseDto<Array<SubModel>>

  departmentId: any
  tableList: any[] = []
  levelId: any
  orgHierarchyId: any

  fieldLevelList: Array<FieldLevel> = [];
  levelHierList: Array<LevelHierarchy> = [];
  hierarchy: any;
  hierarchyLevelCode: any
  lastHierarchyId: any
  flagStop: boolean = false;
  parentArray:any[]=[];
  guidelines: any
  dateView:boolean
  today = new Date();
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  surveyType:string

  surveyNo: any = []
  reportType: FirstTimeBuyer[]=[{value: 'With Questionaries', viewValue: 'With Questionaries'},{value: 'Without Questionaries', viewValue: 'Without Questionaries'},{value:'Dissatisfied Report',viewValue:'Dissatisfied Report'}];
  syurveyType:any[]=[]
  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  variants: Array<Variants> = []
  subModelByModel: Array<AutoCompSubModel> = []
  modelBySeries: Array<ModelBySeries> = []
  model: any
  questionList: any=[]


  constructor(
    private surveySummaryReportService: SurveySummaryReportService,
    private surveySummaryReportSearchPagePresenter: SurveySummaryReportSearchPagePresenter,
    private assignOrgHierarchyToDealerPopUpService:AssignOrgHierarchyToDealerPopUpService,
    private fb: FormBuilder,
    private jobCardSearchWebService: JobCardSearchWebService,

  ) { }

  ngOnInit() {
    this.surveySummaryReportForm = this.surveySummaryReportSearchPagePresenter.surveySummaryReportForm
    this.clickOnTableFields = [{ 'title': 'action', 'isClickable': true }]
    this.formValueChanges();
   // this.getSurveyNameDropDown();
   // this.getSurveyDateDropDown();
    this.getSurveySatisfactionDropDown();
    //this.getSurveyStatusDropDown();
    this.dropDownAllZones()
    this.getLevelByDeprtment(1);
    this.getSurveyType()
    this.getProduct()
    this.getQuestionList()
    // alert(this.surveySummaryReportForm.get('asOnDate').value)
    this.surveyType = this.surveySummaryReportForm.get('surveyName').value
    // if (this.surveyType =='TELEPHONIC') {
    //   this.dateView=true
    //   //this.surveySummaryReportForm.get('asOnDate').patchValue('DATE RANGE')
    // }
    // else if (this.surveyType =='DIRECT') {
    //   this.dateView=false
    // }
      if (this.surveyType =='DIRECT') {
        this.dateView=true
        this.maxDate = this.newdate
        let backDate = new Date();
        backDate.setMonth(this.newdate.getMonth() - 1);
        this.minDate = backDate;
        this.surveySummaryReportForm.get('fromDate').patchValue(backDate);
        this.surveySummaryReportForm.get('toDate').patchValue(new Date());
      }
    
  }
  async getLevelByDeprtment(departmentId) {
    this.assignOrgHierarchyToDealerPopUpService.getLevelByDepartment(departmentId).subscribe(async (res) => {
      console.log(res);
      this.fieldLevelList = res.result;
      this.getGuideline(this.fieldLevelList);
      let length = this.guidelines.length;
      for (let i = 1; i < length; i++) {
        this.surveySummaryReportForm.controls[this.guidelines[i].LEVEL_CODE].disable();
      }
      await this.assignOrgHierarchyToDealerPopUpService.getHierarchyByLevel(this.fieldLevelList[0].LEVEL_ID, 1).subscribe((res: any) => {
        this.levelHierList = res.result;
        this.parentArray.push(this.levelHierList)
        console.log(this.levelHierList)
      });
    })
  }


  getGuideline(fieldLevelList) {
    fieldLevelList.forEach((element,index)=>{
      if(element.LEVEL_CODE=='HO')fieldLevelList.splice(index,1);
   });
    this.guidelines = fieldLevelList;
    this.surveySummaryReportForm = this.createGroup(this.surveySummaryReportForm);
  }

  createGroup(surveySummaryReportForm:FormGroup) {
    // const group = this.fb.group({});
    this.guidelines.forEach(control => surveySummaryReportForm.addControl(control.LEVEL_CODE, this.fb.control('',Validators.required)));
    return surveySummaryReportForm;
  }
  compareFnFieldLevel(c1: any, c2: any): boolean {
    // if (typeof c1 !== typeof c2) {
    if (typeof c1 === 'object' && typeof c2 === 'string') return c1.hierarchy_desc === c2;
    if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.hierarchy_desc;
    // }
  }

  selectFieldLevel(event, index) {
    this.levelId = event.value.level_id;
    this.orgHierarchyId = event.value.org_hierarchy_id;
    let indexNumber = index;
    this.hierarchy = "";
    this.hierarchyLevelCode = this.surveySummaryReportForm.controls[this.guidelines[indexNumber].LEVEL_CODE]
    this.hierarchy = this.surveySummaryReportForm.controls[this.guidelines[indexNumber].LEVEL_CODE].value
    // this.form.controls[this.guidelines[indexNumber].LEVEL_CODE].
    //   patchValue(this.hierarchy);
    this.assignOrgHierarchyToDealerPopUpService.getHierarchyByLevel(this.levelId, this.orgHierarchyId).subscribe(res => {
      this.levelHierList = res.result;
      this.parentArray.push(this.levelHierList)
      console.log(this.parentArray)
      for (let i = 1; i < this.guidelines.length; i++) {
        if (this.guidelines[i].SEQ_NO == indexNumber + 3) {
          this.surveySummaryReportForm.controls[this.guidelines[i].LEVEL_CODE].enable();
          this.surveySummaryReportForm.controls[this.guidelines[i].LEVEL_CODE].reset();
        }
      }
      
    });
  }


  dropDownAllZones() {
    this.surveySummaryReportService.getAllZones().subscribe(response => {
      this.dropdownZoneDomain = response as BaseDto<Array<any>>
    })
  }

  selectionZones(zone: Array<any>) {
    this.selectedZones = zone
    this.selectedRegions=null;
    this.selectedArea = null
    this.selectedTerritory = null
    this.surveySummaryReportService.getRegionByZoneId(this.selectedZones).subscribe(response => {
      this.dropdownRegionDomain = response as BaseDto<Array<any>>
    })
  }
  selectionRegions(region: Array<any>) {
    this.selectedRegions = region
    this.selectedArea = null
    this.selectedTerritory = null
    this.surveySummaryReportService.getAreaByRegionId(this.selectedZones,this.selectedRegions).subscribe(response => {
      console.log('Region', response);
      this.dropdownAreaDomain = response as BaseDto<Array<any>>
    })
  }
  selectionArea(area: Array<any>) {
    this.selectedArea = area
    this.selectedTerritory = null
    this.surveySummaryReportService.getTerritoryByAreaId(this.selectedZones,this.selectedRegions,
      this.selectedArea).subscribe(response => {
      console.log('Region', response);
      this.dropdownTerritoryDomain = response as BaseDto<Array<any>>
    })
  }
  selectionTerritory(territory: Array<any>) {
    this.selectedTerritory = territory
  }
  // getSurveyNameDropDown() {
  //   this.surveySummaryReportService.getSurveyName().subscribe(response => {
  //     this.syurveyType = response.result;
  //   })
  // }
  
  getSurveyType() {
    this.surveySummaryReportService.getQASurveyName().subscribe(response => {
      this.syurveyType = response['result'];
    })
  }

  // getSurveyType(){
  //   this.service.getLookupByCode("Survey_Type").subscribe(response => {
  //     this.syurveyType = response['result'];
  //   });
  // }
  // getSurveyDateDropDown() {
  //   this.surveySummaryReportService.getSurveyDate().subscribe(response => {
  //     this.surveyDateList = response
  //     this.surveySummaryReportForm.get('asOnDate').patchValue(this.surveyDateList['result'][0].lookupval)
  //   })
  // }
  
  // getSurveyStatusDropDown() {
  //   this.surveySummaryReportService.getSurveyStatus().subscribe(response => {
  //     this.surveyStatusList = response
  //     this.surveySummaryReportForm.get('surveyStatus').patchValue(this.surveyStatusList['result'][1].lookupval)
  //   })
  // }
  getSurveySatisfactionDropDown() {
    this.surveySummaryReportService.getSurveySatisfaction().subscribe(response => {
      this.customerSatisfactionList = response
    })
  }
  formValueChanges() {
    this.surveySummaryReportForm.get('dealer').valueChanges.subscribe(value => {
      if (value) {
        let dealer = typeof value == "object" ? value.dealer : value;
        this.autoDealerCode(dealer);
      }
    });
    this.surveySummaryReportForm.get('chassisNo').valueChanges.subscribe(value => {
      if (value) {
        let chassisNo = typeof value == "object" ? value.chassisNo : value;
        this.autoChassisNo(chassisNo);
      }
    });
    // this.surveySummaryReportForm.controls.model.valueChanges.subscribe(value => {
    //   if (value) {
    //     this.autoModel(value)
    //   }
    // });
    this.surveySummaryReportForm.controls.surveyNo.valueChanges.subscribe(value => {
      if (value && typeof value == 'string') {
        this.surveySummaryReportService.surveyNoAuto(value).subscribe(response => {
          this.surveyNo = response
        });
      }
    });
  }
  // autoModel(event) {
  //   this.surveySummaryReportService.searchModel(event).subscribe(response => {
  //     this.model = response as BaseDto<Array<AutoModel>>
  //     this.autoSubModel(this.model.result[0].model)
  //   });
  // }
  // autoSubModel(event) {
  //   this.surveySummaryReportService.searchSubModel(event).subscribe(response => {
  //     this.subModel = response as BaseDto<Array<SubModel>>
  //   });
  // }
  autoDealerCode(autoDealerCode) {
    if (autoDealerCode) {
      this.surveySummaryReportService.dealerAuto(autoDealerCode).subscribe(res => {
        this.dealerList$ = res
      })
    }
  }

  getSurveyNoAnuto(autoDealerCode) {
    if (autoDealerCode) {
      this.surveySummaryReportService.dealerAuto(autoDealerCode).subscribe(res => {
        this.dealerList$ = res
      })
    }
  }

  displayFnForDealerCode(selectedOption: DealerDetails) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['displayString'] : undefined;
  }

  displayFnForSurveyNo(selectedOption: any) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['Survey_No'] : undefined;
  }

  selectionAsOnDate(event) {
    if (event == 'AS ON DATE') {
      this.dateView=false
      this.surveySummaryReportForm.get('fromDate').reset()
      this.surveySummaryReportForm.get('toDate').reset()
    }
    else {
      this.dateView=true
      if (event == 'DATE RANGE') {
        this.maxDate = this.newdate
        let backDate = new Date();
        backDate.setMonth(this.newdate.getMonth() - 1);
        this.minDate = backDate;
        this.surveySummaryReportForm.get('fromDate').patchValue(backDate);
        this.surveySummaryReportForm.get('toDate').patchValue(new Date());
      }
    }
  }
  onKeyDownModel(event: any) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.surveySummaryReportForm.controls.subModel.reset()
    }
  }

  displayFnChassisNo(selectedOption: ChassisNoSelect) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['chassisNo'] : undefined;
  }

  autoChassisNo(chassisNo) {
    if (chassisNo) {
      this.surveySummaryReportService.chassisNoAuto(chassisNo).subscribe(res => {
        this.chassisNoList$ = res
      })
    }

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

  getQuestionList(){
    this.surveySummaryReportService.getQuestion().subscribe(response => {
      this.questionList = response['result']
    })
  }
  


   
}
