
import { SoSearchPagePresenter } from './so-search-page.presenter';
import { SoSearchPageStore } from './so-search-page.store';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { SoSearch } from '../../domain/so.domain';
import { SoSearchPageWebService } from './so-search-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { IFrameService, UrlSegment, IFrameMessageSource } from '../../../../../root-service/iFrame.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-so-search-page',
  templateUrl: './so-search-page.component.html',
  styleUrls: ['./so-search-page.component.scss'],
  providers: [SoSearchPagePresenter, SoSearchPageStore, SoSearchPageWebService]
})
export class SoSearchPageComponent implements OnInit {
  soSearchResultForm: FormGroup
  soSearchForm: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  public filterData: object;
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  EditNgModel: any = "";
  salesOrderNumberNgModel: any = "";
  salesOrderDateNgModel: any = "";
  quotationNumberNgModel: any = "";
  mobileNumberNgModel: any = "";
  customerNameNgModel: any = "";
  customerTypeNgModel: any = "";
  customerAddress1NgModel: any = "";
  customerAddress2NgModel: any = "";
  totalBasicValueNgModel: any = "";
  totalDiscountValueNgModel: any = "";
  totalSalesOrderAmountNgModel: any = "";
  totalTaxAmountNgModel: any = "";
  searchFilterValues: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  key='sosp';
  isKai:boolean=false;
  constructor(
    private presenter: SoSearchPagePresenter,
    private soSearchPageWebService: SoSearchPageWebService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private loginService: LoginFormService
  ) { 
    if(loginService.getLoginUserType().toLocaleLowerCase()=='kai'){
      this.isKai=true;
    }
  }
  ngAfterViewInit() {

    // call of search table web service.
  
  }
  ngOnInit() {
    // window.onbeforeunload = () => {
    //   localStorage.removeItem(this.key);
    // // }
    // localStorage.removeItem('searchFilter');
    // localStorage.removeItem('searchFilterQuo');
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.soSearchResultForm = this.presenter.soSearch
    this.soSearchForm = this.presenter.soSearch
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.soSearchResultForm.patchValue(this.searchFilterValues)

    }
    // localStorage.removeItem('searchFilterSo');
    // console.log("this.soSearchForm ", this.soSearchForm);
    if (this.soSearchForm.get('customerOrderStatus').value==null && this.soSearchForm.get('customerName').value==null && this.soSearchForm.get('customerType').value==null && this.soSearchForm.get('customerOrderNo').value==null && this.soSearchForm.get('orderFromDate').value==null && this.soSearchForm.get('orderToDate').value==null) {
    this.maxDate= this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth()-1);
    this.minDate = backDate;
    this.soSearchForm.get('orderFromDate').patchValue(backDate);
    this.soSearchForm.get('orderToDate').patchValue(new Date());
    this.searchData();
    }
    else{
      localStorage.getItem(this.key)
      this.searchData();
    }
  }
  clearForm() {
    this.soSearchResultForm.reset();
    // this.searchData();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  searchData() {
    this.resetAllSearchValue()
    let orderFromDate: string
    if (this.soSearchForm.get('orderFromDate').value) {
      orderFromDate = this.dateService.getDateIntoYYYYMMDD(this.soSearchForm.get('orderFromDate').value);
      // console.log("mrcDateFrom ", mrcDateFrom);
    }
    let orderToDate: string
    if (this.soSearchForm.get('orderToDate').value) {
      orderToDate = this.dateService.getDateIntoYYYYMMDD(this.soSearchForm.get('orderToDate').value);
    };
    let salesOrderId: number
    if (this.soSearchForm.get('customerOrderNo').value) {
      salesOrderId = this.soSearchForm.get('customerOrderNo').value.id;
    }
    let customerName: string
    if (this.soSearchForm.get('customerName').value) {
      customerName = this.soSearchForm.get('customerName').value.customerName;
    }
    let sendSearchValue = {} as SoSearch;
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

    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      sendSearchValue.page = this.page
      sendSearchValue.size = this.size
    }
    else {
      sendSearchValue.page = this.page
      sendSearchValue.size = this.size
    }
    //let key = 'searchFilterSo';
    localStorage.setItem(this.key, JSON.stringify(this.soSearchForm.value))
    sendSearchValue.customerName = customerName,
      sendSearchValue.customerType = this.soSearchForm.get('customerType').value,
      sendSearchValue.orderFromDate = orderFromDate,
      sendSearchValue.orderToDate = orderToDate,
      sendSearchValue.orderStatus = this.soSearchForm.get('customerOrderStatus').value,
      sendSearchValue.salesOrderId = salesOrderId,
      // sendSearchValue.page = this.page,
      // sendSearchValue.size = this.size

      console.log("this.soSearchForm.getRawValue(); ", this.soSearchForm.getRawValue());
    const temp = this.soSearchForm.getRawValue();
    temp['page'] = this.page
    temp['size'] = this.size
    // temp['id'] = sendSearchValue.mrcId
    console.log("temp ", temp);
    this.filterData = this.removeNullFromObjectAndFormatDate(temp);
    if (this.checkValidDatesInput()) {
      if (this.soSearchForm.get('customerOrderStatus').value || this.soSearchForm.get('customerName').value || this.soSearchForm.get('customerType').value || this.soSearchForm.get('customerOrderNo').value || this.soSearchForm.get('orderFromDate').value || this.soSearchForm.get('orderToDate').value) {
        this.setSearchResultTable({
          ...sendSearchValue
        });
      }
      else if (this.soSearchForm.get('orderFromDate').value == null && this.soSearchForm.get('orderToDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    } else {
      this.toastr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
  }
  checkValidDatesInput() {
    const fltEnqObj = this.soSearchForm.value

    fltEnqObj.fromDate = this.soSearchForm.getRawValue() ? this.soSearchForm.value.orderFromDate : null
    fltEnqObj.toDate = this.soSearchForm.getRawValue() ? this.soSearchForm.value.orderToDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
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
    this.soSearchForm.patchValue(event);
    if (event.customerOrderNo) {
      this.soSearchForm.get('customerOrderNo').patchValue({ saleOrderNumber: event.customerOrderNo.replace(searchValue, newValue) });
    }
    if (event.customerName) {
      this.soSearchForm.get('customerName').patchValue({ customerName: event.customerName });
    }
    if (event.customerType) {
      this.soSearchForm.get('customerType').patchValue(event.customerType.replace(searchValue, newValue));
    }
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  private setSearchResultTable(searchValue: SoSearch) {
    this.soSearchPageWebService.searchSo(searchValue).subscribe(searchRes => {
      let result = searchRes['result'];
      if(this.isKai){
        result.forEach(res => {
          delete res['invoice'];
          delete res['picklist'];
        })
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(result);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  pageChangeOfSearchTable(event: any) {
    // console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchData();
  }
  actionOnTableRecord(recordData: any) {
    let id = btoa(recordData['record']['id'])
    let custNo = btoa(recordData['record']['salesOrderNumber'])
    let filterdata = btoa(JSON.stringify(this.filterData))
    console.log("recordData ", recordData);
    if (recordData.btnAction.toLowerCase() === 'edit') {
      this.router.navigate(['../edit', id], { relativeTo: this.activatedRoute});
    }
    if (recordData.btnAction.toLowerCase() === 'salesordernumber') {
      this.router.navigate(['view', id], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction.toLowerCase() === 'picklist') {
      this.router.navigate(['../../picklist/create'], { relativeTo: this.activatedRoute, queryParams: { custId: custNo}});
    }
    if (recordData.btnAction.toLowerCase() === 'invoice') {
      this.router.navigate(['../../salesinvoice/create'], { relativeTo: this.activatedRoute, queryParams: { custId: custNo}});
    }
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }

        if (searchObject[element] && (element === 'orderFromDate' || element === 'orderToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
        if (searchObject[element] && element === 'customerOrderNo') {
          searchObject[element] = searchObject['customerOrderNo']['saleOrderNumber']
        }
        if (searchObject[element] && element === 'customerName') {
          searchObject[element] = searchObject['customerName']['customerName']
        }
        // if (searchObject[element] && element === 'customerType') {
        //   searchObject[element] = searchObject['customerType']['customerType']
        // }
      });
      console.log("searchObject ", searchObject);
      return searchObject;
    }
  }
  resetAllSearchValue() {
    this.EditNgModel = "";
    this.salesOrderNumberNgModel = "";
    this.salesOrderDateNgModel = "";
    this.quotationNumberNgModel = "";
    this.mobileNumberNgModel = "";
    this.customerNameNgModel = "";
    this.customerTypeNgModel = "";
    this.customerAddress1NgModel = "";
    this.customerAddress2NgModel = "";
    this.totalBasicValueNgModel = "";
    this.totalDiscountValueNgModel = "";
    this.totalSalesOrderAmountNgModel = "";
    this.totalTaxAmountNgModel = "";
  }

  downloadExcel(event) {
    const formValues = this.soSearchForm.getRawValue();
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    // formValues['dealerId'] = this.soSearchForm.value.dealerCode ? this.soSearchForm.value.dealerCode['id'] : null
    // formValues['hierId'] = this.soSearchForm.value.orgHierLevel5 ? this.soSearchForm.value.orgHierLevel5['org_hierarchy_id'] : (this.soSearchForm.value.orgHierLevel4 ? this.soSearchForm.value.orgHierLevel4['org_hierarchy_id'] : (this.soSearchForm.value.orgHierLevel3 ? this.soSearchForm.value.orgHierLevel3['org_hierarchy_id'] : (this.soSearchForm.value.orgHierLevel2 ? this.soSearchForm.value.orgHierLevel2['org_hierarchy_id'] : this.soSearchForm.value.orgHierLevel1 ? this.soSearchForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))

    this.filterData = this.soSearchPageWebService.removeNullFromObjectAndFormatDate(formValues);
    this.downloadExcelReport(this.filterData);
  }


  private downloadExcelReport(searchObject) {
    this.soSearchPageWebService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('Content-Disposition');
        console.log('downloadExcelReport-->', headerContentDispostion)
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
}