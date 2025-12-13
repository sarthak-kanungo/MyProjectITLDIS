import { Component, OnInit, Input } from '@angular/core';
import { EnquirySearchCriteriaContainerService } from './enquiry-search-criteria-container.service';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownModel, DropDownSubModel, DropDownProduct, DropDownVariant, DropDownSeries, SearchEnqiryListDomain, SearchEnquiryFilterDomain, } from 'EnquirySearchCriteria';
import { DropDownSource, DropDownRetailConversionActivityType, EnquiryDomain, EnquiryNoNameThesilDomain } from 'EnquiryCreation';
import { ItemNo } from 'EnquiryProductIntrested';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { SalesPreSalesCommonService } from '../../../../sales-pre-sales-common-service/sales-pre-sales-common.service';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BaseDto } from 'BaseDto';
import { DropDownSalePerson } from 'TransferSearchCriteria';

@Component({
  selector: 'app-enquiry-search-criteria-container',
  templateUrl: './enquiry-search-criteria-container.component.html',
  styleUrls: ['./enquiry-search-criteria-container.component.scss'],
  providers: [EnquirySearchCriteriaContainerService, EnquiryCommonService, SalesPreSalesCommonService, NgswSearchTableService]
})
export class EnquirySearchCriteriaContainerComponent implements OnInit {
  enquiryCode: BaseDto<Array<EnquiryNoNameThesilDomain>>
  dropdownModel: BaseDto<Array<DropDownModel>>
  dropdownSubModel: BaseDto<Array<DropDownSubModel>>
  dropdownProduct: BaseDto<Array<DropDownProduct>>
  // allDataForPatch: BaseDto<Array<DropDownitemAllData>>
  allDataForPatch
  dropdownVariant: BaseDto<Array<DropDownVariant>>
  dropdownseries: BaseDto<Array<DropDownSeries>>
  dropdownSource: BaseDto<Array<DropDownSource>>
  dropdownRetailConversionActivity: BaseDto<Array<DropDownRetailConversionActivityType>>
  itemNo: BaseDto<Array<ItemNo>>
  searchEnqiryListDomain$: BaseDto<Array<SearchEnqiryListDomain>>
  dataTable: DataTable;
  totalTableRecords : number
  actionButtons = [];
  recordeId: number
  viewDataEnquiryById: BaseDto<EnquiryDomain>
  constructor(
    private enquirySearchCriteriaContainerService: EnquirySearchCriteriaContainerService,
    private enquiryCommonService: EnquiryCommonService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private route: ActivatedRoute,
    private salesPreSalesCommonService: SalesPreSalesCommonService
  ) { }

  ngOnInit() {
    this.dropDownProduct()
    this.dropDownVariant()
    this.dropDownsource()
    this.dropDownRetailConversionActivity();
  }

  itemNumberGetData(event) {
    console.log('itemNumberGetData', event)
    this.salesPreSalesCommonService.itemNumberGetDataForPatch(event).subscribe(response => {
      console.log('response', response)
      //console.log('product', response);
      // this.allDataForPatch = response as BaseDto<Array<DropDownitemAllData>>
      this.allDataForPatch = response['result']
      console.log('this.allDataForPatch', this.allDataForPatch)
    })
  }

  dropDownProduct() {
    this.salesPreSalesCommonService.dropdownProduct().subscribe(response => {
      //console.log('product', response);
      this.dropdownProduct = response as BaseDto<Array<DropDownProduct>>
    })
  }

  dropDownVariant() {
    this.enquirySearchCriteriaContainerService.dropdownVariant().subscribe(response => {
      // console.log('variant', response);
      this.dropdownVariant = response as BaseDto<Array<DropDownVariant>>
    })
  }

  productSelected(event) {
    console.log('productSelected', event)
    this.dropDownSeries(event)

  }
  dropDownSeries(productSelected) {
    this.enquirySearchCriteriaContainerService.dropdownSeries(productSelected).subscribe(response => {
      //console.log('series', response);
      this.dropdownseries = response as BaseDto<Array<DropDownSeries>>
    })
  }
  seriesSelected(event) {
    console.log('seriesSelected ================', event)
    this.dropDownModel(event)

  }
  dropDownModel(seriesSelected) {
    this.enquirySearchCriteriaContainerService.dropdownmodel(seriesSelected).subscribe(response => {
      // console.log('model', response);
      this.dropdownModel = response as BaseDto<Array<DropDownModel>>
    })
  }

  modelSelected(event) {
    this.dropDownSubModel(event)

  }
  dropDownSubModel(modelSelected) {
    this.enquirySearchCriteriaContainerService.dropdownSubModel(modelSelected).subscribe(response => {
      //console.log('subModel', response);
      this.dropdownSubModel = response as BaseDto<Array<DropDownSubModel>>
    })
  }
  dropDownsource() {
    this.enquiryCommonService.dropdownSource().subscribe(response => {
      // console.log('source', response);
      this.dropdownSource = response as BaseDto<Array<DropDownSource>>
    })
  }

  dropDownRetailConversionActivity() {
    this.enquiryCommonService.dropdownretailconversionActivityType().subscribe(response => {
      //console.log('retailConversionActivity', response);
      this.dropdownRetailConversionActivity = response as BaseDto<Array<DropDownRetailConversionActivityType>>

    })
  }

  autoEnquiryNumber(event) {
    this.enquiryCommonService.getEnquiryNumberNameMobileNoTehsil(event).subscribe(response => {
      // console.log('response', response);
      this.enquiryCode = response as BaseDto<Array<EnquiryNoNameThesilDomain>>
    })
  }

  autoItemNo(event) {
    this.salesPreSalesCommonService.searchItemNo(event,'').subscribe(response => {
      this.itemNo = response as BaseDto<Array<ItemNo>>
    });
  }

  onEnquirySearch(filter: SearchEnquiryFilterDomain) {
    console.log(filter)
    this.enquirySearchCriteriaContainerService
      .getEnquiryList(filter).subscribe(data => {
        data.result.map((res => {
          if (res.validationDate) {
            if (res.enquiryStatus === 'DROP') {
              res.action = 'Drop';
            } else if (res.enquiryStatus === 'LOST') {
              res.action = 'Lost';
            } else {
              res.action = 'Followup';
            }
            return res;
          }
          res.action = 'Validate';
          return res;
        }))
        this.dataTable = this.tableDataService.convertIntoDataTable(data.result)
        this.totalTableRecords = data.count;
        console.log('dataTable',  this.dataTable); 
      })
  }



}
