import { SchemesCalcService } from './../schemes.calc.service';
import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS, MatCheckboxChange } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { DropDownSchemeTypeDomain, DropDownZoneDomain, DropDownRegionDomain, AutoComItemNoDomain, AutoComActivityNoDomain, DropDownProductDomain, AutoPopolateDataByItemNo, DropDownSeriesDomain, DropDownModelDomain, DropDownSubModelDomain, DropDownVariantDomain, IncentiveScheme, IncentiveSchemeDetails } from 'IncentiveSchemeMasterModule';
import { CreateIncentiveSchemeMasterService } from './create-incentive-scheme-master.service';
import { SalesPreSalesCommonService } from '../../../../sales-pre-sales-common-service/sales-pre-sales-common.service';
import { SchemesCommonService } from '../../../schemes-common-service/schemes-common.service';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { saveAs } from 'file-saver';
import { ExcelFileUploadService } from '../../excel-file-upload.service';
import { DropDownProduct, DropDownModel, DropDownSubModel, DropDownSeries, DropDownVariant } from 'EnquirySearchCriteria';
import { HttpResponse } from '@angular/common/http';
import { DateService } from 'src/app/root-service/date.service';
import { MarketingActivitySearchService } from 'src/app/sales-pre-sales/activity/activity-proposal/component/marketing-activity-search/marketing-activity-search.service';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { UploadableFile } from 'itldis-file-upload';

@Component({
  selector: 'app-create-incentive-scheme-master',
  templateUrl: './create-incentive-scheme-master.component.html',
  styleUrls: ['./create-incentive-scheme-master.component.scss'],

  providers: [
    CreateIncentiveSchemeMasterService,
    SalesPreSalesCommonService,
    SchemesCommonService,
    ExcelFileUploadService,
    MarketingActivitySearchService
  ]
})
export class CreateIncentiveSchemeMasterComponent implements OnInit {
  maximumQtys=new FormControl;
  minDate: Date | null
  file = new FormControl;
  todayDate = new Date();
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  data: Object;
  isMaximumQtySelected: boolean
  disable = true;
  labelPosition = 'before';
  zoneIds: number[]
  proId: number[]
  seriesId: number[]
  modelId: number[]
  subModelId: number[]
  display: boolean
  status: string[] = [
    'ACTIVE', 'INACTIVE',
  ];
  selectedZoneId = [];
  showUploadFile: boolean = false;
  dropdownSchemeTypeDomain: BaseDto<Array<DropDownSchemeTypeDomain>>
  dropdownZoneDomain: BaseDto<Array<DropDownZoneDomain>>
  dropdownRegionDomain: BaseDto<Array<DropDownRegionDomain>>
  autoComItemNoDomain: BaseDto<Array<AutoComItemNoDomain>>
  autoComActivityNoDomain: BaseDto<Array<AutoComActivityNoDomain>>
  dropdownProductDomain: BaseDto<Array<DropDownProductDomain>>
  dropdownSeriesDomain: BaseDto<Array<DropDownSeriesDomain>>
  dropdownModelDomain: BaseDto<Array<DropDownModelDomain>>
  dropdownSubModelDomain: BaseDto<Array<DropDownSubModelDomain>>
  dropdownVariantDomain: BaseDto<Array<DropDownVariantDomain>>
  autoPopolateDataByItemNo: BaseDto<AutoPopolateDataByItemNo>
  incentiveSchemeMaster: FormGroup

  selectedZones: number[] = null
  selectedRegions: Array<DropDownRegionDomain> = null
  selectedProducts: Array<DropDownProduct> = null
  selectedSeries: Array<DropDownSeries> = null
  selectedModels: Array<DropDownModel> = null
  selectedSubModels: Array<DropDownSubModel> = null
  selectedVarient: Array<DropDownVariant> = null
  selectedItem: Array<AutoComItemNoDomain>


  uniqueVariant = new Set();
  uniqueSubModel = new Set();
  uniqueModel = new Set();
  uniqueSeries = new Set();
  schemeMasterId: any = null;

  xlsData: any[][];
  invalidDetails: any[];
  incentiveSchemeDetails: IncentiveSchemeDetails[];
  SelecteditemList: any;
  sendPayLoadSelectedItem: any[];
  files: Array<UploadableFile> = new Array();
  sendFilePreview: any;
  collectFiles: UploadableFile[];
  schemeType: string;
  uploadFileName: string;
  modifiedFileName: string;
  claimAttachmentTrueOrFalse: boolean;
  claimAttachmentPhoto: any;
  fileStaticPath: string = CreateIncentiveSchemeMasterService.staticPath;
  showEmployeeDetails: boolean = false;
  viewShowForm: boolean = false;
  pushZoneId: any;
  maximumQty:boolean;
  claimForProceed:boolean=false;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public dialog: MatDialog,
    private inscheRt: ActivatedRoute,
    private createIncentiveSchemeMasterService: CreateIncentiveSchemeMasterService,
    private salesPreSalesCommonService: SalesPreSalesCommonService,
    private schemesCommonService: SchemesCommonService,
    private toastr: ToastrService,
    private excelFileUploadService: ExcelFileUploadService,

    private dateService: DateService,
    private fileUploadService: FileUploadService,
  ) { }

  ngOnInit() {
    this.minDate = this.todayDate;
    this.checkOperationType()
    this.intiOperationForm()
    this.patchOrCreate()
    this.dropDownSchemeType()
    if (!this.isView) {
      this.dropDownAllZones()
      this.dropDownProduct()
      this.getschemeDate()
    }
  }

  private patchOrCreate() {
    if (this.isView) {
      this.display = true
      //this.isView = true
    } else {
      this.formForCreateSetup()
      this.display = false
    }
  }

  private formForCreateSetup() {
    this.incentiveSchemeMaster = this.createIncentiveSchemeMasterService.createincentiveSchemeMaster()
    // this.incentiveSchemeMaster.controls.itemNo.valueChanges.subscribe(value => {
    //   this.autoComItemNo(value)
    // })
    this.incentiveSchemeMaster.controls.activityNo.valueChanges.subscribe(value => {
      this.autoComActivityNo(value)
    })
    this.incentiveSchemeMaster.controls.schemeType.valueChanges.subscribe(value => {
      if (value == 'BOOKING SCHEME') {
        this.incentiveSchemeMaster.get('activityNo').setValidators([Validators.required])
      } else {
        this.incentiveSchemeMaster.controls['activityNo'].clearValidators();
      }
      this.incentiveSchemeMaster.controls['activityNo'].updateValueAndValidity()
    })
    this.onReadXLS()
  }

  autoComItemNo() {
    var searchBy: string = '';
    var searchByValue: string = '';
    this.autoComItemNoDomain = null;

    if (this.selectedVarient) {
      searchBy = 'variant'
      searchByValue = this.selectedVarient.map(obj => obj.variant).join(",");
    } else if (this.selectedSubModels) {
      searchBy = 'sub_model';
      searchByValue = this.selectedSubModels.map(obj => obj.subModel).join(",");
    } else if (this.selectedModels) {
      searchBy = 'model';
      searchByValue = this.selectedModels.map(obj => obj.model).join(",");
    } else if (this.selectedSeries) {
      searchBy = 'series';
      searchByValue = this.selectedSeries.map(obj => obj.series).join(",");
    } else if (this.selectedProducts) {
      searchBy = 'product';
      searchByValue = this.selectedProducts.map(obj => obj.product).join(",");
    }
    this.salesPreSalesCommonService.searchItemNo('', searchByValue).subscribe(response => {
      this.autoComItemNoDomain = response as BaseDto<Array<AutoComItemNoDomain>>
    })

  }

  autoComActivityNo(value) {
    this.incentiveSchemeMaster.controls.activityNo.setErrors(null);
    if (value) {
      if (typeof value != 'object') {
        this.incentiveSchemeMaster.controls.activityNo.setErrors({ selectFromList: 'Please select from list' })
      }
      this.salesPreSalesCommonService.getActivityNo(value).subscribe(response => {
        this.autoComActivityNoDomain = response as BaseDto<Array<AutoComActivityNoDomain>>
      })
    }
  }

  optionClicked = (event: Event, data: AutoComItemNoDomain): void => {
    event.stopPropagation();
    this.toggleSelection(data);
  };

  toggleSelection = (data: AutoComItemNoDomain): void => {
    if (data.selected === undefined) {
      data.selected = false; // Initialize if undefined
    }
    data.selected = !data.selected;

    if (!this.selectedItem) {
      this.selectedItem = [];
    }

    if (data.selected) {
      this.selectedItem.push(data);
    } else {
      const i = this.selectedItem.findIndex(value => value.itemNumber === data.itemNumber);
      if (i !== -1) {
        this.selectedItem.splice(i, 1);
      }
    }
    this.incentiveSchemeMaster.get('itemNo').patchValue(this.selectedItem);
  };

  displayItemNoFn(data: AutoComItemNoDomain | AutoComItemNoDomain[]): string | undefined {
    if (Array.isArray(data)) {
      return data.map(item => item.itemNo).join(', ');
    } else if (data) {
      return data.itemNo;
    }
    return undefined;
  }





  // optionSelectedItemNo(event: AutoComItemNoDomain) {

  //   if (!this.selectedProducts) {
  //     this.selectedProducts = [{ product: event.product }]
  //     this.dropdownProductDomain = { result: [{ product: event.product }] }
  //     this.incentiveSchemeMaster.controls.product.patchValue(this.dropdownProductDomain.result);
  //   }

  //   if (!this.selectedSeries) {
  //     this.selectedSeries = [{ series: event.series }]
  //     this.dropdownSeriesDomain = { result: [{ series: event.series, product: event.product }] }
  //     this.incentiveSchemeMaster.controls.series.patchValue(this.dropdownSeriesDomain.result);
  //   }

  //   if (!this.selectedModels) {
  //     this.selectedModels = [{ model: event.model }]
  //     this.dropdownModelDomain = { result: [{ model: event.model, series: event.series, product: event.product }] }
  //     this.incentiveSchemeMaster.controls.model.patchValue(this.dropdownModelDomain.result);
  //   }

  //   if (!this.selectedSubModels) {
  //     this.selectedSubModels = [{ subModel: event.sub_model }]
  //     this.dropdownSubModelDomain = { result: [{ subModel: event.sub_model, model: event.model, series: event.series, product: event.product }] }
  //     this.incentiveSchemeMaster.controls.submodels.patchValue(this.dropdownSubModelDomain.result);
  //   }

  //   if (!this.selectedVarient) {
  //     this.selectedVarient = [{ variant: event.variant }]
  //     this.dropdownVariantDomain = { result: [{ variant: event.variant, subModel: event.sub_model, model: event.model, series: event.series, product: event.product }] }
  //     this.incentiveSchemeMaster.controls.variant.patchValue(this.dropdownVariantDomain.result);
  //   }
  // }

  // displayItemNoFn(itm : AutoComItemNoDomain) {
  //   return itm ? itm.itemNumber : undefined
  // }

  displayActivityNoFn(itm: AutoComActivityNoDomain) {

    return itm ? itm.activityNumber : undefined
  }

  getschemeDate() {
    this.createIncentiveSchemeMasterService.getSchemeDate().subscribe(response => {
      this.incentiveSchemeMaster.controls.schemeDate.patchValue(new Date(response['result']))
    })
  }

  dropDownSchemeType() {
    this.schemesCommonService.dropdownSchemeType().subscribe(response => {
      this.dropdownSchemeTypeDomain = response as BaseDto<Array<DropDownSchemeTypeDomain>>
    })
  }

  dropDownAllZones(zones?: any[]) {
    this.createIncentiveSchemeMasterService.getAllZones().subscribe(response => {
      this.dropdownZoneDomain = response['result'];
      if (zones) {
        var patchList = [];
        response['result'].forEach(obj => {

          if (zones.find(s => s == obj.code)) {
            patchList.push(obj);
          }
        });
        console.log(patchList, 'From All Zone DropDownb')
        this.incentiveSchemeMaster.controls.zones.patchValue(patchList);
      }

    });
  }


  checkValidationForUpload(event: any) {
    if (event.checked == true) {
      this.showUploadFile = true;
      this.file.setValidators([Validators.required]);
    } else {
      this.file.setValidators(null);
      this.showUploadFile = false;
    }
  }

  selectionZones(zones: any) {
    this.selectedZoneId = [];
    this.selectedZones = zones.value;
    if (this.isCreate) {
      for (let i = 0; i < this.selectedZones.length; i++) {
        this.selectedZoneId.push(this.selectedZones[i]['id']);
      }
    } else {
      this.pushZoneId = [];
      this.createIncentiveSchemeMasterService.getAllZones().subscribe(response => {
        response['result'].forEach(zone => {
          let filteredUsers = this.incentiveSchemeMaster.controls.zones.value.filter(user => user.code === zone.code);
          for (let i = 0; i < filteredUsers.length; i++) {
            this.pushZoneId.push(filteredUsers[i].id)
          }
        });
        for (let i = 0; i < this.pushZoneId.length; i++) {
          console.log(this.pushZoneId[i]);
        }
      });
     

    }
    this.selectedRegions = null;
    this.dropdownRegionDomain = null;
    if (this.isCreate) {
      this.createIncentiveSchemeMasterService.getRegionByZoneId(this.selectedZoneId.join(",")).subscribe(res => {
        this.dropdownRegionDomain = res as BaseDto<Array<DropDownRegionDomain>>
      });
    } else {
      this.createIncentiveSchemeMasterService.getRegionByZoneId(this.pushZoneId.join(",")).subscribe(res => {
        this.dropdownRegionDomain = res as BaseDto<Array<DropDownRegionDomain>>
        if (zones) {
          var patchList = [];
          this.dropdownRegionDomain.result.forEach(obj => {
            if (zones.find(s => s == obj.region)) {
              patchList.push(obj);
            }
          });
          this.incentiveSchemeMaster.controls.regions.patchValue(patchList);
        }
      });
    }


  }

  selectionRegions(regions: Array<DropDownRegionDomain>) {
    this.selectedZones = null
    this.selectedRegions = regions;
    // if (regions) {
    //   var patchList = [];
    //   this.dropdownRegionDomain.result.forEach(obj => {

    //   });
    //   this.incentiveSchemeMaster.controls.regions.patchValue(patchList);
    // }
  }

  dropDownProduct(products?: any[]) {
    this.salesPreSalesCommonService.dropdownProduct().subscribe(response => {
      this.dropdownProductDomain = response as BaseDto<Array<DropDownProductDomain>>
      if (products) {
        var patchList = [];
        this.dropdownProductDomain.result.forEach(obj => {
          if (products.find(s => s == obj.product)) {
            patchList.push(obj);
          }
        });
        this.incentiveSchemeMaster.controls.product.patchValue(patchList);
      }
    })
  }

  selectionProduct(products: Array<DropDownProduct>, seriesList?: any[]) {
    this.selectedProducts = products
    this.selectedSeries = null;
    this.uniqueSeries.clear();
    this.selectedModels = null;
    this.uniqueModel.clear();
    this.selectedSubModels = null;
    this.uniqueSubModel.clear();
    this.selectedVarient = null;
    this.uniqueVariant.clear();
    this.selectedItem = null;
    this.autoComItemNoDomain = null;
    this.incentiveSchemeMaster.controls.itemNo.reset();

    this.createIncentiveSchemeMasterService.seriesDropdown(products.map(el => el['product']).join(',')).subscribe(response => {
      this.dropdownSeriesDomain = response as BaseDto<Array<DropDownSeriesDomain>>
      var series = "";
      var patchList: any[] = [];
      this.dropdownSeriesDomain.result.forEach(obj => {
        if (series != obj.series) {
          const e = { series: obj.series };
          this.uniqueSeries.add(e);
          if (seriesList && seriesList.length && seriesList.find(s => s == obj.series)) {
            patchList.push(e);
          }
          series = obj.series;
        }
      });
      if (patchList && patchList.length > 0) {
        this.incentiveSchemeMaster.controls.series.patchValue(patchList);
      }

    });
  }

  selectionSeries(series: Array<DropDownSeries>, modelList?: any[]) {
    this.selectedSeries = series
    this.selectedModels = null;
    this.uniqueModel.clear();
    this.selectedSubModels = null;
    this.uniqueSubModel.clear();
    this.selectedVarient = null;
    this.uniqueVariant.clear();
    this.selectedItem = null;
    this.autoComItemNoDomain = null;
    this.incentiveSchemeMaster.controls.itemNo.reset();

    this.createIncentiveSchemeMasterService.modelDropdown(series.map(el => el['series']).join(',')).subscribe(response => {
      this.dropdownModelDomain = response as BaseDto<Array<DropDownModelDomain>>
      var model = "";
      var patchList: any[] = [];
      this.dropdownModelDomain.result.forEach(obj => {
        if (model != obj.model) {
          const e = { model: obj.model };
          this.uniqueModel.add(e);
          if (modelList && modelList.length && modelList.find(s => s == obj.model)) {
            patchList.push(e);
          }
          model = obj.model;
        }
      });
      if (patchList && patchList.length > 0) {
        this.incentiveSchemeMaster.controls.model.patchValue(patchList);
      }
    });
  }

  selectionModel(models: Array<DropDownModel>, subModelList?: any[]) {
    this.selectedModels = models
    this.selectedSubModels = null;
    this.uniqueSubModel.clear();
    this.selectedVarient = null;
    this.uniqueVariant.clear();
    this.selectedItem = null;
    this.autoComItemNoDomain = null;
    this.incentiveSchemeMaster.controls.itemNo.reset();

    this.createIncentiveSchemeMasterService.subModelDropdown(models.map(el => el['model']).join(',')).subscribe(response => {
      this.dropdownSubModelDomain = response as BaseDto<Array<DropDownSubModelDomain>>
      var submodel = "";
      var patchList: any[] = [];
      this.dropdownSubModelDomain.result.forEach(obj => {
        if (submodel != obj.subModel) {
          const e = { subModel: obj.subModel };
          this.uniqueSubModel.add(e);
          if (subModelList && subModelList.length && subModelList.find(s => s == obj.subModel)) {
            patchList.push(e);
          }
          submodel = obj.subModel;
        }
      });
      if (patchList && patchList.length > 0) {
        this.incentiveSchemeMaster.controls.submodels.patchValue(patchList);
      }
    });
  }

  selectionSubmodel(subModles: Array<DropDownSubModel>, variantList?: any[]) {
    this.selectedSubModels = subModles
    this.selectedVarient = null;
    this.uniqueVariant.clear();
    this.selectedItem = null;
    this.autoComItemNoDomain = null;
    this.incentiveSchemeMaster.controls.itemNo.reset();

    this.createIncentiveSchemeMasterService.variantDropdown(subModles.map(el => el['subModel']).join(',')).subscribe(response => {
      this.dropdownVariantDomain = response as BaseDto<Array<DropDownVariantDomain>>
      var variant = "";
      var patchList: any[] = [];
      this.dropdownVariantDomain.result.forEach(obj => {
        if (variant != obj.variant) {
          const e = { variant: obj.variant };
          this.uniqueVariant.add(e);
          if (variantList && variantList.length && variantList.find(s => s == obj.variant)) {
            patchList.push(e);
          }
          variant = obj.variant;
        }
      });
      if (patchList && patchList.length > 0) {
        this.incentiveSchemeMaster.controls.variant.patchValue(patchList);
      }
    });
  }

  selectedItemNo(event: any) {
    this.SelecteditemList = event;
    this.sendPayLoadSelectedItem = []; // Initialize sendPayLoadSelectedItem as an empty array
    for (let i = 0; i < this.SelecteditemList.length; i++) {
      this.sendPayLoadSelectedItem.push(this.SelecteditemList[i].itemNo);
    }
  }


  selectionVariants(varients: Array<DropDownVariant>) {
    this.selectedVarient = varients
    this.selectedItem = null;
    this.autoComItemNoDomain = null;
    this.incentiveSchemeMaster.controls.itemNo.reset();
    this.autoComItemNo();

  }

  // code for Upload Excel
  uploadExcelFileChanges(event: any) {
    this.uploadFileName = event.target.files[0].name;
    this.modifiedFileName = this.uploadFileName.replace(/\s*\(\d+\)\s*/, '').trim();
    this.excelFileUploadService.readXls(event);
    event.target['value'] = null;

  }
  // code for Read Exls
  private onReadXLS() {
    this.excelFileUploadService.emXls.subscribe(data => {
      if (this.incentiveSchemeMaster.get('schemeType').value == null) {
        this.toastr.warning("Please Select Scheme Type First !");
        return false;
      }
      if (this.schemeType === 'DSE' || this.schemeType === 'TM/BM/SM' || this.schemeType === 'GM') {
        if (this.modifiedFileName.includes('SchemaMasterEmployeeSpecUpload.xlsx')) {
          // Allow the modification
          this.incentiveSchemeMaster.controls.maxQty.enable()
          this.xlsData = data.slice(2)
          this.incentiveSchemeDetails = new Array();
          this.xlsData.forEach(data => {
            var dtl: IncentiveSchemeDetails = {
              t1Quantity: data[4],
              t1IncentivePerQuantity: data[5],
              t2Quantity: data[6],
              t2IncentivePerQuantity: data[7],
              t3Quantity: data[8],
              t3IncentivePerQuantity: data[9],
              t4Quantity: data[10],
              t4IncentivePerQuantity: data[11],
              t5Quantity: data[12],
              t5IncentivePerQuantity: data[13],
              dealerCode: data[0],
              dealerName: data[1],
              dealerEmployeeCode: data[2],
              dealerEmployeeName: data[3],
              maximumQuantity:null
            }
            this.incentiveSchemeDetails.push(dtl);
          });
        } else {
          this.toastr.warning("Please Select Employee Scheme Type");
          // alert("Please check your Excel File");
        }
      } else {
        //  dealerCode: data[0],
        // dealerName: data[1],

        if (this.modifiedFileName.includes('SchemaMasterDealerSpecUpload.xlsx')) {
          // Allow the modification
          this.incentiveSchemeMaster.controls.maxQty.enable()
          this.xlsData = data.slice(2)
          console.log(data, 'formshshsh')
          this.incentiveSchemeDetails = new Array();
          this.xlsData.forEach(data => {
            var dtl: IncentiveSchemeDetails = {
              t1Quantity: data[2],
              t1IncentivePerQuantity: data[3],
              t2Quantity: data[4],
              t2IncentivePerQuantity: data[5],
              t3Quantity: data[6],
              t3IncentivePerQuantity: data[7],
              t4Quantity: data[8],
              t4IncentivePerQuantity: data[9],
              t5Quantity: data[10],
              t5IncentivePerQuantity: data[11],
              dealerCode: data[0],
              dealerName: data[1],
              //  dealerEmployeeCode: data[2],
              dealerEmployeeCode: 'null',
              dealerEmployeeName: 'null',
              // dealerEmployeeName: data[3]
              maximumQuantity:null
            }
            this.incentiveSchemeDetails.push(dtl);
          });
        } else {
          this.toastr.warning("Please Select Dealer Scheme Type");
          // alert("Please check your Excel File");
        }
      }

    });
  }

  private checkOperationType() {
    this.isView = this.inscheRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.inscheRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    if (!this.isView && !this.isEdit) {
      this.isCreate = true;
    }
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        if (queryMap['params']['id']) {
          this.schemeMasterId = atob(queryMap['params']['id']);
          this.viewSchemeDetails();
        }
      }
    });
  }

  private intiOperationForm() {
    if (this.isView) {
      this.incentiveSchemeMaster = this.createIncentiveSchemeMasterService.viewincentiveSchemeMaster()
    } else {
      this.incentiveSchemeMaster = this.createIncentiveSchemeMasterService.createincentiveSchemeMaster()
    }
  }

  selectMaximumQty(event: MatCheckboxChange) {
    this.isMaximumQtySelected = event.checked
  }

  validateForm() {

    this.markFormAsTouched()
    const schemeType = this.incentiveSchemeMaster.get('schemeType').value;
    const activityNo = this.incentiveSchemeMaster.get('activityNo').value
    if (schemeType == 'BOOKING SCHEME' || activityNo) {
      if (!activityNo)
        this.incentiveSchemeMaster.get('activityNo').setErrors({ required: 'reqired' });
      else if (typeof activityNo != 'object')
        this.incentiveSchemeMaster.get('activityNo').setErrors({ selectFromList: 'reqired' });
    } else {
      this.incentiveSchemeMaster.get('activityNo').setErrors(null);
    }

    if (this.incentiveSchemeMaster.invalid) {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    } else {
      if (this.incentiveSchemeDetails && this.incentiveSchemeDetails.length > 0) {
        this.openConfirmDialog();
      } else {
        this.toastr.error(`Please upload incentive details`, 'Report mandatory fields')
      }
    }
  }
  // code for Reset Form
  clearForm() {

    this.incentiveSchemeMaster.reset();
    this.incentiveSchemeDetails = [];
  }
  submitData() {
    // Code for Submit
    var incentiveMaster: IncentiveScheme = this.incentiveSchemeMaster.getRawValue();
    console.log(incentiveMaster,'master')
    delete incentiveMaster.schemeNo;
    delete incentiveMaster.status;
    incentiveMaster.schemeDate = this.dateService.getDateIntoDDMMYYYY(incentiveMaster.schemeDate);
    incentiveMaster.validFrom = this.dateService.getDateIntoDDMMYYYY(incentiveMaster.validFrom);
    incentiveMaster.validTo = this.dateService.getDateIntoDDMMYYYY(incentiveMaster.validTo);

    if (incentiveMaster.regions) {
      incentiveMaster.regions = incentiveMaster.regions.map(p => p['region']);
    } else {
      incentiveMaster.regions = null
    }
    if (incentiveMaster.zones) {
      console.log(incentiveMaster.zones)
      incentiveMaster.zones = incentiveMaster.zones.map(p => p['code']);

    } else {
      incentiveMaster.zones = null
    }

    if (incentiveMaster.product) {
      incentiveMaster.product = incentiveMaster.product.map(p => p['product']);
    } else {
      incentiveMaster.product = null
    }

    if (incentiveMaster.series) {
      incentiveMaster.series = incentiveMaster.series.map(p => p['series']);
    } else {
      incentiveMaster.series = null;
    }

    if (incentiveMaster.model) {
      incentiveMaster.model = incentiveMaster.model.map(p => p['model']);
    } else {
      incentiveMaster.model = null;
    }

    if (incentiveMaster.submodels) {
      incentiveMaster.submodels = incentiveMaster.submodels.map(p => p['subModel']);
    } else {
      incentiveMaster.submodels = null;
    }

    if (incentiveMaster.variant) {
      incentiveMaster.variant = incentiveMaster.variant.map(p => p['variant']);
    } else {
      incentiveMaster.variant = null
    }

    if (this.sendPayLoadSelectedItem) {
      incentiveMaster.itemNo = this.sendPayLoadSelectedItem;
    } else {
      incentiveMaster.itemNo = null;
    }

    if (incentiveMaster.activityNo) {
      incentiveMaster.activityProposal = { activityProposalId: incentiveMaster.activityNo.id };
    } else {
      incentiveMaster.activityProposal = null;
    }
    if(this.incentiveSchemeDetails){
      incentiveMaster.incentiveSchemeDetails = [];
    for (let row of this.incentiveSchemeDetails) {
      incentiveMaster.incentiveSchemeDetails.push(row.maximumQuantity);
  }
}
  incentiveMaster.incentiveSchemeDetails = [...this.incentiveSchemeDetails];
    // if(this.showUploadFile!=true){
      incentiveMaster.multipartFile = this.collectFiles;
    // }
   
    this.createIncentiveSchemeMasterService.saveScheme(incentiveMaster).subscribe(response => {
      if (response['message'] == 'Invalid Details') {
        this.toastr.error('Error while saving scheme details', 'Transaction Failed')
        this.invalidDetails = response['result']['incentiveSchemeDetails'];
      } else {
        this.toastr.success('Incentive Schemes Master Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }
    }, error => {
      this.toastr.error('Error while saving scheme details', 'Transaction Failed')
    });

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Incentive Schemes Master?';
    if (this.isEdit) {
      message = 'Do you want to update Incentive Schemes Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
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
  // Validate Form
  private markFormAsTouched() {
    for (const key in this.incentiveSchemeMaster.controls) {
      if (this.incentiveSchemeMaster.controls.hasOwnProperty(key)) {
        this.incentiveSchemeMaster.controls[key].markAsTouched();
      }
    }
  }
  //  code for Scheme Select Type
  selectSchemeType(event: any) {
    this.schemeType = event.value;
    this.incentiveSchemeDetails = [];
    if (this.schemeType === 'DSE' || this.schemeType === 'TM/BM/SM' || this.schemeType === 'GM') {
      this.showEmployeeDetails = true;
    } else {
      this.showEmployeeDetails = false;
    }
  }

  //  Code For Download Excel
  downloadTemplate() {
    if (this.incentiveSchemeMaster.get('schemeType').value == null) {
      this.toastr.warning("Please Select Scheme Type First!");
      return false;
    }
    if (this.schemeType === 'DSE' || this.schemeType === 'TM/BM/SM' || this.schemeType === 'GM') {
      this.createIncentiveSchemeMasterService.downloadTemplate("SchemaMasterEmployeeSpecUpload.xlsx").subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          const file = new File([resp.body], "SchemaMasterEmployeeSpecUpload.xlsx", { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    } else {
      this.createIncentiveSchemeMasterService.downloadTemplate("SchemaMasterDealerSpecUpload.xlsx").subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          const file = new File([resp.body], "SchemaMasterDealerSpecUpload.xlsx", { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    }


  }
  // Code for View Data
  viewSchemeDetails() {
    this.createIncentiveSchemeMasterService.viewScheme(this.schemeMasterId).subscribe(response => {
      this.incentiveSchemeMaster.get('referenceSchemeNo').patchValue(response['result'].referenceSchemeNumber);
      this.incentiveSchemeMaster.get('schemeType').patchValue(response['result'].schemeType);
      this.incentiveSchemeMaster.get('schemeNo').patchValue(response['result'].schemeNumber)
      if(response['result'].activityProposal==null || response['result'].activityProposal==''){
        
      }else{
        this.incentiveSchemeMaster.get('activityNo').patchValue(response['result'].activityProposal.activityNumber);
      }
      this.incentiveSchemeMaster.get('regions').patchValue(response['result'].sregion)
      this.claimAttachmentTrueOrFalse = response['result'].claimAttachmentRequired;
      this.claimForProceed=response['result'].incentiveAllowedForExceededQuantity;
      this.maximumQty=response['result'].maximumQuantity;
      if (this.claimAttachmentTrueOrFalse == true) {
        this.claimAttachmentPhoto = response['result'].schemeAttachment.fileName;
      }
      let schemeType = response['result'].schemeType;
      if (schemeType === 'DSE' || schemeType === 'TM/BM/SM' || schemeType === 'GM') {
        this.viewShowForm = true;
      } else {
        this.viewShowForm = false;
      }
      const obj = response['result'];
      var d1 = obj['schemeDate'].split("-");
      var schemeDate = new Date(d1[2] + "-" + d1[1] + "-" + d1[0]);
      obj['schemeDate'] = schemeDate;

      var d2 = obj['validFrom'].split("-");
      var validFrom = new Date(d2[2] + "-" + d2[1] + "-" + d2[0]);
      obj['validFrom'] = validFrom;

      var d3 = obj['validTo'].split("-");
      var validTo = new Date(d3[2] + "-" + d3[1] + "-" + d3[0]);
      obj['validTo'] = validTo;

      this.incentiveSchemeMaster.patchValue(obj)
      if (obj['maxQty']) {
        this.isMaximumQtySelected = true;
      }
      this.incentiveSchemeDetails = obj['incentiveSchemeDetails'];
      var _products: any[] = [];
      var _series: any[] = [];
      var _models: any[] = [];
      var _submodels: any[] = [];
      var _zone: any[] = [];
      var _region: any[] = [];
      if (obj.sproduct) {
        const items = obj.sproduct.split(",");
        items.forEach(item => {
          _products.push({ product: item })
        });
        this.dropDownProduct(items);
      }
      if (obj.sseries) {
        const items = obj.sseries.split(",");
        items.forEach(item => {
          _series.push({ series: item })
        });
        this.selectionProduct(_products, items);
      }
      if (obj.smodel) {
        const items = obj.smodel.split(",");
        items.forEach(item => {
          _models.push({ model: item })
        });
        this.selectionSeries(_series, items);
      }
      if (obj.ssubModel) {
        const items = obj.ssubModel.split(",");
        items.forEach(item => {
          _submodels.push({ subModel: item })
        });
        this.selectionModel(_models, items);
      }
      if (obj.svariant) {
        const items = obj.svariant.split(",");
        this.selectionSubmodel(_submodels, items);
      }

      if (obj.szone) {
        const items = obj.szone.split(",");
        items.forEach(item => {
          _zone.push({ id: item })
        });
        this.dropDownAllZones(items);
      }
      if (obj.sregion) {
        const items = obj.sregion.split(",");
        items.forEach(item => {
          _region.push({ id: item })
        });
        this.selectionZones(items);
      }

      if (obj.sitem) {
        const items = obj.sitem.split(",");
        var itemList = new Array();
        items.forEach(item => {
          itemList.push({ itemNumber: item, selected: true })
        });
        this.autoComItemNoDomain = { result: itemList };
        this.selectedItem = itemList;
        this.incentiveSchemeMaster.controls.itemNo.patchValue(obj.sitem);
      }
      this.incentiveSchemeDetails.forEach(dtl => {
        dtl.dealerCode = dtl.dealer.dealerCode;
        dtl.dealerName = dtl.dealer.dealerName;
        if (dtl.dealerEmployee != null) {
          dtl.dealerEmployeeCode = dtl.dealerEmployee.employeeCode ? dtl.dealerEmployee.employeeCode : null;
          dtl.dealerEmployeeName = dtl.dealerEmployee.firstName + (dtl.dealerEmployee.middleName ? dtl.dealerEmployee.middleName + " " : "") + (dtl.dealerEmployee.lastName ? dtl.dealerEmployee.lastName : "");
        }


      })
    }, error => {
      this.toastr.error('error in fetching incentive scheme details', 'Error');
    })
  }

  fromDateChange(event) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
    }
  }

  fileSelectionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0])
    if (msg != 'OK') {
      return false;
    }
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.collectFilesForData(this.files);
      // if (this.files.length > 0) {
      //   this.sendFilePreview = delete this.files[0].previewUrl;
      // }

    } else {

    }
  }
  // Delete Code for Image
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
  }
  collectFilesForData(files: UploadableFile[]) {
    this.collectFiles = files;
  }



  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

  // For Exit
  exit() {
    this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    // this.router.navigateByUrl([''])
  }
  validateInput(event:any){
    if(isNaN(event.key) && event.key !== 'Backspace') {
      event.preventDefault();
    }
  }

}
