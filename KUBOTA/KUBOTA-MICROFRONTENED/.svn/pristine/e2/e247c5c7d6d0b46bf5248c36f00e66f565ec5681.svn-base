import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef, AfterViewInit } from '@angular/core';

import { PurchaseOrderSearchWebService } from '../purchase-order-search/purchase-order-search-web.service';
import { PurchaseOrderSearchPagePresenter } from './purchase-order-search-page.presenter';
import { NgswSearchTableService, DataTable, ColumnSearch } from 'ngsw-search-table';
import { PurchaseOrderSearchPageStore } from './purchase-order-search-page.store';
import { IFrameService, UrlSegment, IFrameMessageSource } from '../../../../../root-service/iFrame.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-purchase-order-search-page',
  templateUrl: './purchase-order-search-page.component.html',
  styleUrls: ['./purchase-order-search-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [PurchaseOrderSearchPageStore, PurchaseOrderSearchPagePresenter, PurchaseOrderSearchWebService],
})
export class PurchaseOrderSearchPageComponent implements OnInit, AfterViewInit {
  public totalTableElements: number;
  readonly searchPoForm: FormGroup;
  public searchValue: ColumnSearch;
  public dataTable: DataTable;
  public filterData: object;
  public actionButtons = [];
  public size = 10;
  public page = 0;
  clearSearchRow = new BehaviorSubject<string>("");
  public loginUserType = '';
  isShowAdvanceFilter: boolean = false;
  searchFlag: boolean = true;
  purchaseOrderNumberNgModel: any = "";
  orderTypeNgModel: any = "";
  purchaseOrderDateNgModel: any = "";
  supplierTypeNgModel: any = "";
  supplierNameNgModel: any = "";
  transferModeNgModel: any = "";
  transporterNgModel: any = "";
  freightBorneByNgModel: any = "";
  totalBaseAmountNgModel: any = "";
  totalPoAmountNgModel: any = "";
  purchaseOrderStatusNgModel: any = "";
  dealerCodeNgModel: any = "";
  dealerNameNgModel: any = "";
  searchFilterValues: any;
  key = "posp";
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private router: Router,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private tableDataService: NgswSearchTableService,
    private searchSparesPoPresenter: PurchaseOrderSearchPagePresenter,
    private purchaseOrderSearchService: PurchaseOrderSearchWebService,
    private loginService: LoginFormService,
    private toastr: ToastrService,
  ) {
    this.searchPoForm = this.searchSparesPoPresenter.getSearchPOForm;
    this.loginUserType = this.loginService.getLoginUserType().toLowerCase();
  }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchPoForm.patchValue(this.searchFilterValues)
    }
    if (this.searchPoForm.get('poNumber').value==null && this.searchPoForm.get('poType').value==null && this.searchPoForm.get('fromDate').value==null && this.searchPoForm.get('toDate').value==null && this.searchPoForm.get('poStatus').value==null && this.searchPoForm.get('partNumber').value==null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.searchPoForm.get('fromDate').patchValue(backDate);
      this.searchPoForm.get('toDate').patchValue(new Date());
      this.searchSparesPo()
    }
    else {
      this.searchSparesPo()
    }
  }

  public searchSparesPo() {
    this.resetAllSearchValue()
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

    const formValues = this.searchPoForm.getRawValue();
    
    localStorage.setItem(this.key, JSON.stringify(this.searchPoForm.value))
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    formValues['dealerId'] = this.searchPoForm.value.dealerCode ? this.searchPoForm.value.dealerCode['id'] : null
    formValues['hierId'] = this.searchPoForm.value.orgHierLevel5 ? this.searchPoForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel4 ? this.searchPoForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel3 ? this.searchPoForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel2 ? this.searchPoForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchPoForm.value.orgHierLevel1 ? this.searchPoForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))


    this.filterData = this.purchaseOrderSearchService.removeNullFromObjectAndFormatDate(formValues);
    // this.router.navigate([], { queryParams: { searchFilter: JSON.stringify(this.filterData) } });
    if (this.checkValidDatesInput()) {
      if (this.searchPoForm.get('poNumber').value || this.searchPoForm.get('poType').value || this.searchPoForm.get('fromDate').value || this.searchPoForm.get('toDate').value || this.searchPoForm.get('poStatus').value || this.searchPoForm.get('partNumber').value || this.searchPoForm.get('dealerCode').value) {
        this.purchaseOrderSearchService.searchSparesPO(formValues).subscribe(res => {
          
          if (res) {
            this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
            this.totalTableElements = res['count'] as number;
            this.changeDetectorRef.markForCheck();
          }
        })
      }
      else if (this.searchPoForm.get('fromDate').value == null && this.searchPoForm.get('toDate').value == null && this.searchPoForm.get('dealerCode').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
    /* this.purchaseOrderSearchService.getSpraesPOCount(formValues).subscribe(res => {
       if (res) {
         this.totalTableElements = res['result'] as number;
         this.changeDetectorRef.markForCheck();
       }
     })*/
    this.searchFlag = true;
  }

  checkValidDatesInput() {
    const fltEnqObj = this.searchPoForm.value

    fltEnqObj.fromDate = this.searchPoForm.getRawValue() ? this.searchPoForm.value.fromDate : null
    fltEnqObj.toDate = this.searchPoForm.getRawValue() ? this.searchPoForm.value.toDate : null
    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'nextfollowUpFromDate', 'tentativePurchaseFromDate'];
    let toDates = ['toDate', 'nextFollowUpToDate', 'tentativePurchaseToDate'];
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
  public initialQueryParams(event) {
    if (event) {
      // this.filterData = event as object;
      this.page = event.page;
      this.size = event.size;
      this.patchSearchValueToForm(event);
    }
  }
  private patchSearchValueToForm(event) {
    this.searchPoForm.patchValue(event);
  }
  public onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };

    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);

  }
  public tableAction(event: object) {
    let id = btoa(event['record']['id'])
    let ordertype = btoa(event['record']['orderType'])
    let filterdata = btoa(JSON.stringify(this.filterData))
    if (event['btnAction'].toLowerCase() === 'purchaseordernumber') {
      
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { viewId: id, orderType: ordertype } });
      return;
    }
    if (event['btnAction'].toLowerCase() === 'edit' && event['record']['edit'].toLowerCase() === 'edit') {
      this.router.navigate(['../update'], { relativeTo: this.activatedRoute, queryParams: { updateId: id, orderType: ordertype } });
      return;
    }
    if (event['btnAction'].toLowerCase() === 'edit' && event['record']['edit'].toLowerCase() === 'approve') {
    
      this.router.navigate(['../approve'], { relativeTo: this.activatedRoute, queryParams: { approveId: id, orderType: ordertype } });
      return;
    }
  }
  public pageSizeChange(event: object) {
    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    this.searchSparesPo();
  }
  public etSearchDateValueChange(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }
  public clearForm() {
    this.searchPoForm.reset();
    // this.searchSparesPo();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  ngAfterViewInit() {
    // this.searchSparesPo();
  }

  showAdvanceSearch() {
    this.isShowAdvanceFilter = !this.isShowAdvanceFilter;
  }

  generateReport(){
    const formValues = this.searchPoForm.getRawValue();
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    formValues['dealerId'] = this.searchPoForm.value.dealerCode ? this.searchPoForm.value.dealerCode['id'] : null
    formValues['hierId'] = this.searchPoForm.value.orgHierLevel5 ? this.searchPoForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel4 ? this.searchPoForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel3 ? this.searchPoForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel2 ? this.searchPoForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchPoForm.value.orgHierLevel1 ? this.searchPoForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))

    this.filterData = this.purchaseOrderSearchService.removeNullFromObjectAndFormatDate(formValues);
    this.downloadExcelReport(this.filterData,'Report');
  }
  downloadExcel(event) {
    const formValues = this.searchPoForm.getRawValue();
    formValues['page'] = this.page;
    formValues['size'] = this.size;

    formValues['dealerId'] = this.searchPoForm.value.dealerCode ? this.searchPoForm.value.dealerCode['id'] : null
    formValues['hierId'] = this.searchPoForm.value.orgHierLevel5 ? this.searchPoForm.value.orgHierLevel5['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel4 ? this.searchPoForm.value.orgHierLevel4['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel3 ? this.searchPoForm.value.orgHierLevel3['org_hierarchy_id'] : (this.searchPoForm.value.orgHierLevel2 ? this.searchPoForm.value.orgHierLevel2['org_hierarchy_id'] : this.searchPoForm.value.orgHierLevel1 ? this.searchPoForm.value.orgHierLevel1['org_hierarchy_id'] : 0)))

    this.filterData = this.purchaseOrderSearchService.removeNullFromObjectAndFormatDate(formValues);
    this.downloadExcelReport(this.filterData,'ExportExcel');
  }
  private downloadExcelReport(searchObject, reportType) {
    if (this.searchPoForm.get('fromDate').value == null || this.searchPoForm.get('toDate').value == null) {
      this.toastr.error("Please Select Date Range First");
    }else{
    this.purchaseOrderSearchService.downloadExcelReport(searchObject, reportType).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  }
  resetAllSearchValue() {
    this.purchaseOrderNumberNgModel = "";
    this.orderTypeNgModel = "";
    this.purchaseOrderDateNgModel = "";
    this.supplierTypeNgModel = "";
    this.supplierNameNgModel = ""
    this.transferModeNgModel = "";
    this.transporterNgModel = "";
    this.freightBorneByNgModel = "";
    this.totalBaseAmountNgModel = "";
    this.totalPoAmountNgModel = "";
    this.purchaseOrderStatusNgModel = "";
    this.dealerCodeNgModel = "";
    this.dealerNameNgModel = "";
  }
}
