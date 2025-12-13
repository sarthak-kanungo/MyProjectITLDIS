import { Component, OnInit } from '@angular/core';
import { SearchQuotationContainerService } from './search-quotation-container.service';
import { BaseDto } from 'BaseDto';
import { SourceDomain, ProductsDomain, SeariesDomain, ModelDomain, SubModelDomain, VariantDomain, ItemNoDomain2, QuotationListSearchDomain, QuotationSearchFilterDomain } from 'SearchQuotationsModule';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { AddImplementsContainerService } from '../add-implements-container/add-implements-container.service';

@Component({
  selector: 'app-search-quotation-container',
  templateUrl: './search-quotation-container.component.html',
  styleUrls: ['./search-quotation-container.component.scss'],
  providers: [SearchQuotationContainerService, AddImplementsContainerService]
})
export class SearchQuotationContainerComponent implements OnInit {

  searchSourceTypeList: BaseDto<Array<SourceDomain>>
  searchProductTypeList: BaseDto<Array<ProductsDomain>>
  searchSeariesTypeList: BaseDto<Array<SeariesDomain>>
  searchModelTypeList: BaseDto<Array<ModelDomain>>
  searchSubModelTypeList: BaseDto<Array<SubModelDomain>>
  searchVariantTypeList: BaseDto<Array<VariantDomain>>
  getItemNo: BaseDto<Array<ItemNoDomain2>>
  quotationSearchList$: Observable<Array<QuotationListSearchDomain>>

  dataTable: DataTable
  totalTableRecords: number;
  quotationCodeList: [];
  salesPersonList: Array<Object>;
  enquiryStatusList: Array<Object>;
  serverDate: string;

  constructor(
    private searchQuotationContainerService: SearchQuotationContainerService,
    private tableDataService: NgswSearchTableService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private addImplementsContainerService: AddImplementsContainerService
  ) { }

  ngOnInit() {
    this.getDateFromServer();
    this.getSource();
    this.getProduct();
    this.setSalesPersonList();
    this.setEnquiryStatusList();
  }
  private getDateFromServer() {
    this.searchQuotationContainerService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.serverDate = dateRes.result;
      }
    });
  }
  getSource() {
    this.searchQuotationContainerService.searchSourceTypeList().subscribe(res => {
      this.searchSourceTypeList = res as BaseDto<Array<SourceDomain>>
    })
  }

  getProduct() {
    this.searchQuotationContainerService.searchProductTypeList().subscribe(res => {
      this.searchProductTypeList = res as BaseDto<Array<ProductsDomain>>
    })
  }

  getSearies(selectedProduct: string) {
    this.searchQuotationContainerService.searchSeariesTypeList(selectedProduct).subscribe(res => {
      this.searchSeariesTypeList = res as BaseDto<Array<SeariesDomain>>
    });
  }

  getModelBySeries(series: string) {
    this.searchQuotationContainerService.getModelBySeriesList(series).subscribe(res => {
      this.searchModelTypeList = res as BaseDto<Array<ModelDomain>>
    });
  }
  getSubModelByModel(model: string) {
    this.searchQuotationContainerService.searchSubModelTypeList(model).subscribe(res => {
      this.searchSubModelTypeList = res as BaseDto<Array<SubModelDomain>>
    });
  }

  getVariantBySubModel(subModel: string) {
    this.searchQuotationContainerService.searchVariantTypeList(subModel).subscribe(res => {
      this.searchVariantTypeList = res as BaseDto<Array<VariantDomain>>
    });
  }

  autoItemNO(event) {
    /*this.searchQuotationContainerService.getItemNo(event).subscribe(response => {
      this.getItemNo = response as BaseDto<Array<ItemNoDomain2>>
    })*/
    this.addImplementsContainerService.searchItemNo(event, '', 'QUOTATION').subscribe(res => {
      this.getItemNo =res as BaseDto<Array<ItemNoDomain2>>
    });  
  }

  onSearch(filter: QuotationSearchFilterDomain) {
    this.searchQuotationContainerService
      .searchUsingFilter(filter).subscribe(data => {
        this.dataTable = this.tableDataService.convertIntoDataTable(data.result)
        this.totalTableRecords = data.count;
      })
  }

  btnActionValue(event) {
    if (event.btnAction === 'quotationCode') {
      this.router.navigate(['view', btoa(event.record.quotationCode)], { relativeTo: this.activatedRoute });
    }
  }
  quotationCodeChange(searchValue) {
    this.searchQuotationContainerService.getQuotationCodeList(searchValue).subscribe(list => {
      this.quotationCodeList = list['result'];
    });
  }
  setSalesPersonList() {
    this.searchQuotationContainerService.getSalesPerson().subscribe(salesPersonRes => {
      this.salesPersonList = salesPersonRes['result'];
    })
  }
  setEnquiryStatusList() {
    this.searchQuotationContainerService.getEnquiryStatus().subscribe(enquiryStatusRes => {
      this.enquiryStatusList = enquiryStatusRes['result'];
    })
  }
}
