import { AfterViewInit, Component, OnInit } from '@angular/core';
import { MachineStockSearchPagePersenter } from './machine-stock-search-page-presenter';
import { MachineStockSearchPageStore } from './machine-stock-search-page-store';
import { FormGroup } from '@angular/forms';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { MachineStockSearchPageService } from './machine-stock-search-page.service';
import { SearchMachineStock } from '../../domain/machine-stock';
import { BehaviorSubject } from 'rxjs';

import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ToastrService } from 'ngx-toastr';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-machine-stock-search-page',
  templateUrl: './machine-stock-search-page.component.html',
  styleUrls: ['./machine-stock-search-page.component.css'],
  providers: [MachineStockSearchPagePersenter, MachineStockSearchPageStore, MachineStockSearchPageService]
})
export class MachineStockSearchPageComponent implements OnInit, AfterViewInit {

  machineStockSearchForm: FormGroup;
  machineStockSearchPageForm: FormGroup;
  totalSearchRecordCount: number;
  dataTable: DataTable;
  searchFilter;
  filterData: SearchMachineStock;
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  page: number = 0;
  size: number = 10;
  newdate=new Date()
  fromDate=new Date();
  minDate=new Date()
  maxDate=new Date()
  pageLoadCount:number=0;
  userType: string
  constructor(private searchPagePresenter: MachineStockSearchPagePersenter,
    private ngswSearchTableService: NgswSearchTableService,
    private machineStockSearchPageService: MachineStockSearchPageService,
    private iFrameService: IFrameService,
    private loginService: LoginFormService,
    private toaster: ToastrService,
    private dateService:DateService) {

    this.userType = loginService.getLoginUserType();

  }

  ngOnInit() {
    this.machineStockSearchPageForm = this.searchPagePresenter.machineStockSearchFormGroup;
    this.machineStockSearchForm = this.searchPagePresenter.machineStockSearchForm;
      
    // let backDate = new Date();
    // backDate.setMonth(this.newdate.getMonth() - 1);
    // this.fromDate = backDate;
    // this.machineStockSearchForm.get('invoiceFromDate').patchValue(backDate);
    // this.machineStockSearchForm.get('invoiceToDate').patchValue(new Date());
    // this.filterData = localStorage.getItem(this.key)
    // this.filterData = JSON.parse(JSON.parse(JSON.stringify(this.filterData)))
    
    this.maxDate = new Date();
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;

    if (this.filterData != null || this.filterData != undefined && this.filterData != null) {
      this.machineStockSearchForm.patchValue(this.filterData)
      // this.searchData();
    }
    else{
      this.machineStockSearchForm.get('invoiceFromDate').patchValue(backDate);
      this.machineStockSearchForm.get('invoiceToDate').patchValue(new Date());
      // this.searchData();
    }
    
  }

  ngAfterViewInit() {
    // this.searchData();
  }

  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url, queryParam } as UrlSegment);
  }
  
  pageChange(event) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchData();
    }
    this.pageLoadCount = 1;

  }

  private setSearchResultTable(searchValue: SearchMachineStock) {
    this.machineStockSearchPageService.searchStock(searchValue).subscribe(searchRes => {
      let obj:[] = searchRes['result']
      if(this.userType.toLocaleLowerCase() != 'kubota'){
        obj.forEach(o => {
            delete o['zone'];
            delete o['region'];
            delete o['area'];
            delete o['dealerCode'];
            delete o['dealerName'];
            delete o['dealerLocation'];
        })
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(obj);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  searchData() {
  
    if (this.machineStockSearchForm.valid) {
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

      const temp = this.machineStockSearchForm.getRawValue();
      temp['page'] = this.page
      temp['size'] = this.size
      temp['dealerCode'] = temp.dealerCode ? temp.dealerCode.code:null
      this.filterData = this.removeNullFromObjectAndFormatDate(temp);
      if (this.machineStockSearchForm.get('dealerCode').value||this.machineStockSearchForm.get('invoiceFromDate').value ||this.machineStockSearchForm.get('invoiceToDate').value|| this.machineStockSearchForm.get('dealerName').value || this.machineStockSearchForm.get('product').value || this.machineStockSearchForm.get('series').value || this.machineStockSearchForm.get('model').value ||this.machineStockSearchForm.get('grnDoneFlag').value || this.machineStockSearchForm.get('subModel').value || this.machineStockSearchForm.get('variant').value || this.machineStockSearchForm.get('itemNo').value || this.machineStockSearchForm.get('orgHierLevel1').value || this.machineStockSearchForm.get('orgHierLevel2').value || this.machineStockSearchForm.get('orgHierLevel3').value || this.machineStockSearchForm.get('orgHierLevel4').value || this.machineStockSearchForm.get('orgHierLevel5').value) {
      this.setSearchResultTable(
        this.filterData
      );
      }
    } else {
      this.machineStockSearchForm.markAllAsTouched();
      this.toaster.error("Please fill atleast one input field.");
    }
  }

  public removeNullFromObjectAndFormatDate(searchObject: SearchMachineStock) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'invoiceFromDate' || element === 'invoiceToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }
  public clearForm() {
    this.machineStockSearchForm.reset();
    this.clearSearchRow.next("");
    // this.searchData();
    this.dataTable=null
  }

  machineStockExcelReport(event) {
    let searchObject= this.machineStockSearchForm.getRawValue();
    searchObject['page'] = this.page;
    searchObject['size'] = this.size;
    searchObject['dealerCode'] = searchObject.dealerCode ? searchObject.dealerCode.code:null
    this.machineStockSearchPageService.downloadMachineStockExcel(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
       
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
}
