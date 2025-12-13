import { Component, OnInit, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { DropDownSeries, DropDownModel, DropDownSubModel, DropDownVariant, DropDownProduct, autoItemNumber, searchItemMaster } from 'SearchMachineItem';
import { SearchMachineItemService } from './search-machine-item.service';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { Observable, forkJoin, BehaviorSubject } from 'rxjs';
import { ObjectUtil } from 'src/app/utils/object-util';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-search-machine-item',
  templateUrl: './search-machine-item.component.html',
  styleUrls: ['./search-machine-item.component.scss']
})
export class SearchMachineItemComponent implements OnInit, AfterViewInit {
  machineItemForm: FormGroup;
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  itemNumberList: BaseDto<Array<autoItemNumber>>
  productsList: BaseDto<Array<DropDownProduct>>
  seriesList: BaseDto<Array<DropDownSeries>>
  modelsList: BaseDto<Array<DropDownModel>>
  subModelsList: BaseDto<Array<DropDownSubModel>>
  variantsList: BaseDto<Array<DropDownVariant>>
  clearSearchRow = new BehaviorSubject<string>("");
  filterData: searchItemMaster
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  alternatorNoNg: any
  batteryNoNg:any
  bcdNg:any
  cgstNg:any
  chassisNoReqNg:any
  crawlerNoLeftNg:any
  crawlerNoRightNg:any
  engineModelNg:any
  etaInDaysNg:any
  etdInDaysNg:any
  fipNoNg:any
  frontRimLeftNg:any
  frontRimRightNg:any
  frontTyreLeftNg:any
  frontTyreMakeNg:any
  frontTyreRightNg:any
  hsnCodeNg:any
  hstNoNg:any
  hydraulicPumpNoNg:any
  igstNg:any
  isAssemblyReqNg:any
  isFGNg:any
  isPalletReqNg:any
  isSFGNg:any
  itemDesciptionNg:any
  leadTimeNg:any
  modelNg:any
  noOfItemInPalletNg:any
  palletItemNoNg:any
  portOfLoadingNg:any
  productNg:any
  productGroupNg:any
  purchaseNg:any
  radiatorNoNg:any
  realRimLeftNg:any
  realRimRightNg:any
  realTyreRightNg:any
  rearTyreLeftNg:any
  rtoRegistrationNg:any
  seriesNg:any
  sfgPartNoNg:any
  sgstNg:any
  starterNoNg:any
  subModelNg:any
  swingMotorNoNg:any
  swsNg:any
  transmissionNoNg:any
  variantNg:any
  vendorCodeNg:any
    @Output() actionOnTableCell = new EventEmitter<Object>();
  constructor(private fb: FormBuilder,
    private searchMachineItemService: SearchMachineItemService,
    private ngswSearchTableService: NgswSearchTableService,
  ) { }

  ngOnInit() {
    this.searchmachineItemForm();

    this.loadAllDropDownData().subscribe(dt => {
      this.productsList = dt[0] as BaseDto<Array<DropDownProduct>>
      this.seriesList = dt[1] as BaseDto<Array<DropDownSeries>>
      this.modelsList = dt[2] as BaseDto<Array<DropDownModel>>
      this.subModelsList = dt[3] as BaseDto<Array<DropDownSubModel>>
      this.variantsList = dt[4] as BaseDto<Array<DropDownVariant>>
      // this.patchOrCreate()
    })
  }
  ngAfterViewInit() {
    // this.searchData()
    this.searchMasterItemMaster();

  }

  searchmachineItemForm() {
    this.machineItemForm = this.fb.group({
      itemNo: [''],
      product: [''],
      series: [''],
      model: [''],
      subModel: [''],
      variant: ['']
    })
    this.machineItemForm.controls.itemNo.valueChanges.subscribe(value => {
      this.autoItemNo(value)
    })
  }
  autoItemNo(value) {
    this.searchMachineItemService.searchItemNo(value).subscribe(response => {
      console.log(response);
      this.itemNumberList = response
    })
  }
  displayFnItemNo(itemNumber: autoItemNumber) {
    return itemNumber ? itemNumber.itemNo : undefined
  }
  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.searchMachineItemService.getProductList())
    dropDownTask.push(this.searchMachineItemService.getSeriesList())
    dropDownTask.push(this.searchMachineItemService.getModelList())
    dropDownTask.push(this.searchMachineItemService.getSubModelList())
    dropDownTask.push(this.searchMachineItemService.getVariantList())
    return forkJoin(...dropDownTask)
  }
  // searchData() {
  //   // this.setSearchResultTable({ ...this.machineItemForm.value, page: this.page, size: this.size });
  // }
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    this.searchMasterItemMaster()
  }

  private setSearchResultTable(searchValue: searchItemMaster) {
    this.searchMachineItemService.searchItemMaster(searchValue).subscribe(searchRes => {
      console.log('Item Master searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
      console.log('dataTable------',this.dataTable)
      console.log('count-----------------', this.totalSearchRecordCount)
    });
  }
  
  searchMasterItemMaster() {
    if (this.machineItemForm.valid) {
      if (this.dataTable != undefined) {
        if (this.searchFlag == true) {
          this.page = 0;
          this.size = 10;
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
        else {
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
      }

    const temp = this.machineItemForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.filterData = this.removeNullFromObjectAndFormatDate(temp);
    console.log('item master filter--------', this.filterData)
    this.setSearchResultTable(this.filterData)
  
} else {
  this.machineItemForm.markAllAsTouched();
}
  }
  public removeNullFromObjectAndFormatDate(searchObject: searchItemMaster) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
      });
      return searchObject;
    }
  }
  tableClear(){
    this.alternatorNoNg=""
    this.batteryNoNg=""
    this.bcdNg=""
    this.cgstNg=""
    this.chassisNoReqNg=""
    this.crawlerNoLeftNg=""
    this.crawlerNoRightNg=""
    this.engineModelNg=""
    this.etaInDaysNg=""
    this.etdInDaysNg=""
    this.fipNoNg=""
    this.frontRimLeftNg=""
    this.frontRimRightNg=""
    this.frontTyreLeftNg=""
    this.frontTyreMakeNg=""
    this.frontTyreRightNg=""
    this.hsnCodeNg=""
    this.hstNoNg=""
    this.hydraulicPumpNoNg=""
    this.igstNg=""
    this.isAssemblyReqNg=""
    this.isFGNg=""
    this.isPalletReqNg=""
    this.isSFGNg=""
    this.itemDesciptionNg=""
    this.leadTimeNg=""
    this.modelNg=""
    this.noOfItemInPalletNg=""
    this.palletItemNoNg=""
    this.portOfLoadingNg=""
    this.productNg=""
    this.productGroupNg=""
    this.purchaseNg=""
    this.radiatorNoNg=""
    this.realRimLeftNg=""
    this.realRimRightNg=""
    this.realTyreRightNg=""
    this.rearTyreLeftNg=""
    this.rtoRegistrationNg=""
    this.seriesNg=""
    this.sfgPartNoNg=""
    this.sgstNg=""
    this.starterNoNg=""
    this.subModelNg=""
    this.swingMotorNoNg=""
    this.swsNg=""
    this.transmissionNoNg=""
    this.variantNg=""
    this.vendorCodeNg=""
    
  }
  clearMachineItem() {
    this.machineItemForm.reset();
    this.searchMasterItemMaster()
    this.clearSearchRow.next("");
    this.tableClear()
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.actionOnTableCell.emit(recordData)
  }
}
