import { Observable, of } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { SalesPurchaseOrderSearchContainerServiceService } from './sales-purchase-order-search-container-service.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { AddImplementsContainerService } from '../../../../sales/quotation/component/add-implements-container/add-implements-container.service';
import { ToastrService } from 'ngx-toastr';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-sales-purchase-order-search-container',
  templateUrl: './sales-purchase-order-search-container.component.html',
  styleUrls: ['./sales-purchase-order-search-container.component.scss'],
  providers: [SalesPurchaseOrderSearchContainerServiceService, AddImplementsContainerService]
})
export class SalesPurchaseOrderSearchContainerComponent implements OnInit {
  public poNumberAutocomplete: Observable<(object | string)[]>;
  public itemNumberAutocomplete: Observable<(object | string)[]>;
  private searchFormValues: object = {} as object;
  public isShowAdvanceFilter:boolean=false
  public totalTableElements: number;
  public searchValue: ColumnSearch;
  public dataTable: DataTable;
  public actionButtons = [];
  public poTypesList = [];
  public depotsList = [];
  public poStatusList = [];
  public dealerList = [];
  public productsList = [];
  public seriesList = [];
  public modelsList = [];
  public submodelsList = [];
  public variantsList = [];
  private page = 0;
  private size = 10;
  public searchFilter: any;
  searchFlag: boolean = true;
  pageLoadCount:number=0
  constructor(
    private salesPurchaseOrderSearchContainerServiceService: SalesPurchaseOrderSearchContainerServiceService,
    private tableDataService: NgswSearchTableService,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private addImplementsContainerService: AddImplementsContainerService,
    private dateService: DateService,
  ) { }

  ngOnInit() {
    let key = 'Blog';
    localStorage.setItem(key, "My first blog")
    this.getQueryParams();
    this.searchPo();
    this.getPoTypesList();
    this.getDepoList();
    this.getPoStatusList();
    // this.getDealerList();
    this.getProductsList();
    this.getSeriesList();
    this.getModelsList();
    this.getSubmodelsList();
    this.getVariantsList();
    // isShowAdvanceFilter:boolean=true
  }
  private getQueryParams() {
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        const paramsObj = { ...queryMap['params'] } as object;
        Object.keys(paramsObj).forEach(key => paramsObj[key] === "null" && delete paramsObj[key]);
        this.searchFormValues = paramsObj;
      }
    })
  }

  public searchPo() {
    console.log('sssss----',this.searchFormValues)
    if (this.searchFlag == true && this.searchFormValues['poNumber'] || this.searchFormValues['poType'] || this.searchFormValues['depot'] || this.searchFormValues['itemNo'] || this.searchFormValues['fromDate'] || this.searchFormValues['toDate'] || this.searchFormValues['dealerCode']) {
      // this.page = 0;
      // this.size = 10;
      this.searchFormValues['page'] = this.page;
      this.searchFormValues['size'] = this.size;

    }
    else {
      this.searchFormValues['page'] = this.page;
      this.searchFormValues['size'] = 0;
      // this.toastr.error("Please fill atleast one input field.");
    }
    if (this.dataTable != undefined || this.dataTable != null) {
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
      this.searchFlag = true;
    }
    Object.keys(this.searchFormValues).forEach(key => {
      this.searchFormValues[key] === null && delete this.searchFormValues[key];
console.log(this.searchFormValues[key],'this.searchFormValues[key]')
      if (this.searchFormValues[key] && (((key === 'fromDate') || (key === 'toDate')))) {
        this.searchFormValues[key] = this.dateService.getDateIntoYYYYMMDD(this.searchFormValues[key])
      }
    });
    this.searchFilter = { ...this.searchFormValues };
    if (this.searchFlag == true && this.searchFormValues['poNumber'] || this.searchFormValues['poType'] || this.searchFormValues['depot'] || this.searchFormValues['itemNo'] || this.searchFormValues['fromDate'] || this.searchFormValues['toDate'] || this.searchFormValues['dealerCode'] || this.searchFormValues['poStatus'] || this.searchFormValues['product']||this.searchFormValues['series'] || this.searchFormValues['model'] ||this.searchFormValues['subModel'] ||this.searchFormValues['variant'] ||this.searchFormValues['orgHierLevel1'] ||this.searchFormValues['orgHierLevel2']||this.searchFormValues['orgHierLevel3']||this.searchFormValues['orgHierLevel4']||this.searchFormValues['orgHierLevel5']) {
      this.salesPurchaseOrderSearchContainerServiceService.searchPo(this.searchFormValues).subscribe(res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      })
    }
    // else if (this.searchFormValues['fromDate'] == null && this.searchFormValues['toDate'] == null) {
    //   this.toastr.error("Please fill atleast one input field.");
    // }


  }

  public tableAction(event: object) {
    console.log('po----',event['record']['id'])
    if (event['btnAction'].toLowerCase() === 'approve') {
      this.router.navigate(['../approve', btoa(event['record']['id'])], { relativeTo: this.activatedRoute });
    }
    if (event['btnAction'].toLowerCase() === 'p_o_number') {
      this.router.navigate(['../view', btoa(event['record']['id'])], { relativeTo: this.activatedRoute });
    }
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../update', btoa(event['record']['id'])], { relativeTo: this.activatedRoute });
    }
  }
  public getSearchFormValues(event: object) {
    console.log("this.searchFormValues1111ee ", event);

    this.searchFormValues = { ...event };
    this.searchPo()
  }
  public displayPoNumber(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['poNumber'] : undefined;
  }
  public displayItemNumber(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['itemNo'] : undefined;
  }
  public getPoNumberAutocomplete(searchKey: string) {
    this.salesPurchaseOrderSearchContainerServiceService.getPoNumberAutocompleteList(searchKey).subscribe(res => {
      this.poNumberAutocomplete = res['result'];
    })
  }
  public getPoTypesList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllPoTypesList().subscribe(res => {
      this.poTypesList = res['result'];
    })
  }
  public getDepoList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllDepoList().subscribe(res => {
      this.depotsList = res['result'];
    })
  }
  public getPoStatusList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllPoStatusList().subscribe(res => {
      this.poStatusList = res['result'];
    })
  }
  public getDealerList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllDealerList().subscribe(res => {
      this.dealerList = res['result'];
    })
  }
  public getProductsList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllProductsList().subscribe(res => {
      this.productsList = res['result'];
    })
  }
  public getSeriesList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllSeriesList().subscribe(res => {
      this.seriesList = res['result'];
      this.seriesList.shift();
    })
  }
  public getModelsList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllModelsList().subscribe(res => {
      this.modelsList = res['result'];
      this.modelsList.shift();
    })
  }
  public getSubmodelsList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllSubmodelsList().subscribe(res => {
      this.submodelsList = res['result'];
      this.submodelsList.shift();
    })
  }
  public getVariantsList() {
    this.salesPurchaseOrderSearchContainerServiceService.getAllVariantsList().subscribe(res => {
      this.variantsList = res['result'];
      this.variantsList.shift();
    })
  }
  public poNumberValueChanges(searchKey: string) {
    this.salesPurchaseOrderSearchContainerServiceService.getPoNumberAutocompleteList(searchKey).subscribe(res => {
      this.poNumberAutocomplete = of(res['result']);
    })
  }
  public itemNumberValueChanges(searchKey: string) {
    /*this.salesPurchaseOrderSearchContainerServiceService.getItemNumberAutocompleteList(searchKey).subscribe(res => {
      this.itemNumberAutocomplete = of(res['result']);
    })*/
    this.addImplementsContainerService.searchItemNo(searchKey, '', 'PO').subscribe(res => {
      this.itemNumberAutocomplete = of(res['result'])
    }
    );
  }
  public pageChange(event) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;

    if(this.pageLoadCount > 0){
      this.searchPo();
    }
    this.pageLoadCount = 1;
    
  }
  public initialQueryParams(event: object) {
    const { page = 0, size = 10 } = { ...event };
    this.page = page;
    this.size = size;
    this.searchPo();
  }
  public onUrlChange(event: object) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url } as UrlSegment);
  }
}
