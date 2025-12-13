import { Component, OnInit } from '@angular/core';
import { StockAdjustmentSearchPagePresenter } from './search-stock-adjustment.presenter';
import { StockAdjustmentSearchStore } from './search-stock-adjustment.store';
import { SearchPageWebService } from './search-page-web.service';
import { FormGroup } from '@angular/forms';
import { DateService } from '../../../../../root-service/date.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { SearchStockAdjustment } from '../../domain/stock-adjustment.domain';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-search-stock-adjustment-page',
  templateUrl: './search-stock-adjustment-page.component.html',
  styleUrls: ['./search-stock-adjustment-page.component.css'],
  providers: [StockAdjustmentSearchPagePresenter, StockAdjustmentSearchStore, SearchPageWebService]
})
export class SearchStockAdjustmentPageComponent implements OnInit {
  stockAdjustmentSearchResultForm: FormGroup
  stockSearch: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  public filterData: SearchStockAdjustment;
  clearSearchRow = new BehaviorSubject<string>("");
  key = 'stap';
  key1='pagechange'
  today = new Date();
  minDate: Date;
  maxDate: Date;
  usertype:string
  searchFilterValues: any;
  isKai:boolean=false;
  constructor(
    private presenter: StockAdjustmentSearchPagePresenter,
    public searchPageWebService: SearchPageWebService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private loginService : LoginFormService
  ) { 
    this.usertype = loginService.getLoginUserType();
    if(this.usertype.toLocaleLowerCase()=='itldis'){
      this.isKai = true;
    }
  }

  ngAfterViewInit() {

  }
  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);  
      localStorage.removeItem(this.key1);
    }
    // this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key))))

    this.stockAdjustmentSearchResultForm = this.presenter.stockAdjustmentSearchForm
    this.stockSearch = this.presenter.stockSearch;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.stockSearch.patchValue(this.searchFilterValues)
    }
  
    if (this.stockSearch.get('stockAdjustmentNo').value == null && this.stockSearch.get('adjustmentStatus').value == null && this.stockSearch.get('adjustmentFromDate').value == null && this.stockSearch.get('adjustmentToDate').value == null) {
      // debugger
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.stockSearch.get('adjustmentFromDate').patchValue(backDate);
      this.stockSearch.get('adjustmentToDate').patchValue(new Date());
      this.searchData();
    }
    else {
      localStorage.getItem(this.key)
      this.searchData();
    }

  }
  clearForm() {
    this.stockAdjustmentSearchResultForm.reset();
    // this.searchData();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  searchData() {
    if (this.stockSearch.valid) {
      if (this.dataTable != undefined) {
        if (this.searchFlag == true) {
          this.page = 0;
          this.size = 10;
          // this.stockSearch.patchValue({
          //   page: this.page ,
          //   size:this.size
          // })
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
        else {
          this.dataTable['PageIndex'] = this.page
          this.dataTable['PageSize'] = this.size
        }
      }
      const temp = this.stockSearch.getRawValue();
      localStorage.setItem(this.key, JSON.stringify(this.stockSearch.value))
      let adc = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key1))))
      if(adc){
        temp['page'] = adc.page 
      temp['size'] = adc.size
      }else{
        temp['page'] = this.page 
        temp['size'] = this.size 
      }
      temp['dealerId'] = this.stockSearch.value.dealerCode ? this.stockSearch.value.dealerCode['id'] : null
      temp['hierId'] = this.stockSearch.value.orgHierLevel5 ? this.stockSearch.value.orgHierLevel5['org_hierarchy_id'] : (this.stockSearch.value.orgHierLevel4 ? this.stockSearch.value.orgHierLevel4['org_hierarchy_id'] : (this.stockSearch.value.orgHierLevel3 ? this.stockSearch.value.orgHierLevel3['org_hierarchy_id'] : (this.stockSearch.value.orgHierLevel2 ? this.stockSearch.value.orgHierLevel2['org_hierarchy_id'] : this.stockSearch.value.orgHierLevel1 ? this.stockSearch.value.orgHierLevel1['org_hierarchy_id'] : null)))
  
  
      
      this.filterData = this.removeNullFromObjectAndFormatDate(temp);
      if (this.checkValidDatesInput()) {
        if (this.stockSearch.get('stockAdjustmentNo').value ||this.stockSearch.get('adjustmentStatus').value||this.stockSearch.get('dealerCode').value|| this.stockSearch.get('orgHierLevel1').value|| this.stockSearch.get('adjustmentFromDate').value || this.stockSearch.get('adjustmentToDate').value) {
          this.setSearchResultTable(
            this.filterData
          );
        }
        else if (this.stockSearch.get('adjustmentFromDate').value == null && this.stockSearch.get('adjustmentToDate').value == null) {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }
    } else {
      this.stockSearch.markAllAsTouched();
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.stockSearch.value

    fltEnqObj.fromDate = this.stockSearch.getRawValue() ? this.stockSearch.value.adjustmentFromDate : null
    fltEnqObj.toDate = this.stockSearch.getRawValue() ? this.stockSearch.value.adjustmentToDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'fromDate1'];
    let toDates = ['toDate', 'toDate2'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }

    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }

  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    const searchValue = /%2F/g;
    const newValue = '/';
    this.page = event.page
    this.size = event.size
    this.stockAdjustmentSearchResultForm.patchValue(event);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  private setSearchResultTable(searchValue: SearchStockAdjustment) {
    this.searchPageWebService.searchStock(searchValue).subscribe(searchRes => {
      if(!this.isKai){
        searchRes['result'].forEach( result => {
          delete result['dealerCode'];
          delete result['dealerName'];
        })
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  pageChangeOfSearchTable(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    // this.stockSearch.patchValue({
    //   page: this.page ,
    //   size:this.size
    // })
    const setItem={
      page:this.page,
      size:this.size
    }
    localStorage.setItem(this.key1, JSON.stringify(setItem))
    this.searchData();
  }
  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData.record.id)
    if (recordData.btnAction.toLowerCase() === 'adjustmentno') {
      this.router.navigate(['view', id], { relativeTo: this.activatedRoute, queryParams: {} })
    }
    if (recordData.btnAction.toLowerCase() === 'action' && recordData.record.action==='Approve') {
      this.router.navigate(['approve', id], { relativeTo: this.activatedRoute, queryParams: {} })
    }
  }
  public removeNullFromObjectAndFormatDate(searchObject: SearchStockAdjustment) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'adjustmentFromDate' || element === 'adjustmentFromDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && (element === 'adjustmentToDate' || element === 'adjustmentToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && element === 'stockAdjustmentNo') {
          searchObject[element] = searchObject['stockAdjustmentNo']['adjustmentNo']
        }

      });
      return searchObject;
    }
  }

  generateReport(){
    const formValues = this.stockSearch.getRawValue();
    this.filterData = this.removeNullFromObjectAndFormatDate(formValues);

    this.filterData['page'] = this.page;
    this.filterData['size'] = this.size;


    if (this.checkValidDatesInput()) {
      if (this.stockSearch.get('stockAdjustmentNo').value || this.stockSearch.get('adjustmentFromDate').value || this.stockSearch.get('adjustmentToDate').value) {
        this.downloadReport(this.filterData)
      }
    }
  }

  private downloadReport(searchObject) {
    if (this.stockSearch.get('adjustmentFromDate').value == null && this.stockSearch.get('adjustmentToDate').value == null) {
      this.toastr.error("Please fill atleast one input field."); 
     } else {
    this.searchPageWebService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  }
  
}
