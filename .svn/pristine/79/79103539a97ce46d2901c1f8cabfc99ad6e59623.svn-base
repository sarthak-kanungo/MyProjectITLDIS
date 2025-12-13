import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LogSheetSearchPagePresenter } from './log-sheet-search-page.presenter';
import { LogSheetSearchPageStore } from './log-sheet-search-page.store';
import { FormGroup } from '@angular/forms';
import { LogSheetSearchPageWebService } from './log-sheet-search-page-web.service';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { SearchWarrantyLogSheet } from '../../domain/log-sheet.domain';
import { ObjectUtil } from '../../../../../utils/object-util';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { HttpResponse } from '@angular/common/http';
import {saveAs} from 'file-saver';
@Component({
  selector: 'app-log-sheet-search-page',
  templateUrl: './log-sheet-search-page.component.html',
  styleUrls: ['./log-sheet-search-page.component.scss'],
  providers: [LogSheetSearchPageStore, LogSheetSearchPagePresenter, LogSheetSearchPageWebService]
})
export class LogSheetSearchPageComponent implements OnInit, AfterViewInit {
 
  searchForm: FormGroup;
  isAdvanceSearch: boolean;
  actionButtons = [];
  key = 'pcrsp';
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  pageLoadCount:number=0
  private warrantyLogsheet = {} as SearchWarrantyLogSheet;
  page: number= 0;
  searchFlag:boolean=false
  size = 10;
  searchFilter: any;
  clearSearchRow = new BehaviorSubject<string>("");

  constructor(
    private router:Router,
    private activatedRoute: ActivatedRoute,
    private logsheetSearchPresenter: LogSheetSearchPagePresenter,
    private logSheetSearchPageWebService: LogSheetSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private toastr : ToastrService,
    private matDialog:MatDialog,
    private iFrameService: IFrameService,
    private dateService:NgswSearchTableService
  ) { }

  ngOnInit() {
    this.searchForm = this.logsheetSearchPresenter.logsheetSearchForm;
    //  this.warrantyLogsheet.page = this.searchForm.get('page').value;
    //  this.warrantyLogsheet.size = this.searchForm.get('size').value;
  }
  ngAfterViewInit() {
    this.searchForm.valueChanges.subscribe(val => {
      this.warrantyLogsheet = val;
    })
    
    // this.searchDetail();
    // this.searchWarrantyLogSheet(this.warrantyLogsheet);
  }
  
  private searchWarrantyLogSheet(warrantyLogsheet: SearchWarrantyLogSheet) {
    
    this.logSheetSearchPageWebService.searchWarrantyLogSheet(warrantyLogsheet).subscribe(res => {
      
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });
  }

  tableAction(event: object) {
   if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'logsheetno') {
      
     this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { logsheetNo: event['record']['logsheetNo']} });
   }
   if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'action') {
     if(event['record']['action']=='Edit'){
        this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { logsheetNo: event['record']['logsheetNo']} });
     }
     if(event['record']['action']=='Close'){
      this.openConfirmDialogVerification(event['record']['id']);
    }
  }
 }

 private openConfirmDialogVerification(id) {
  let message = `Do you want to Close Logsheet?`;

  const confirmationData = this.setConfirmationModalData(message);
  const dialogRef = this.matDialog.open(ConfirmationBoxComponent, {
    width: "500px",
    panelClass: "confirmation_modal",
    data: confirmationData
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result == "Confirm") {
      this.logSheetSearchPageWebService.closeLogsheet(id).subscribe(response =>{
        this.toastr.success(response['message']);
        this.search();
      });
    }
  });
}
private setConfirmationModalData(message: string) {
  const confirmationData = {} as ConfirmDialogData;
  confirmationData.title = "Confirmation";
  confirmationData.message = message;
  confirmationData.buttonName = ["Confirm", "Cancel"];
  return confirmationData;
}
 pageSizeChange(ev: InfoForGetPagination) {
  // debugger
  this.page = ev['page'];
    this.size = ev['size'];

    this.searchFlag = false;
    this.search()
    //  this.warrantyLogsheet.page = ev['page'];
    //  this.warrantyLogsheet.size = ev['size'];
    //  this.searchForm.get('page').patchValue(ev['page']);
    //   this.searchForm.get('size').patchValue(ev['size']);
    //  if(this.pageLoadCount > 0){
    //   this.search();
    // }
    // this.pageLoadCount = 1;    
    //  this.searchFilter = ObjectUtil.removeNulls(this.warrantyLogsheet);
    //  this.searchWarrantyLogSheet(this.warrantyLogsheet);
   
 }
 search() {
    
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
  }
  else {
    this.page = 0;
    this.size = 10;
  }

  this.searchFlag = true;
  const filterObj = this.searchForm.value as SearchWarrantyLogSheet;

  if(this.searchForm.get('logsheetNo').value != null) {
    this.warrantyLogsheet.logsheetNo = this.searchForm.get('logsheetNo').value.code;
  }
  if(this.searchForm.get('jobCardNo').value != null) {
    this.warrantyLogsheet.jobCardNo = this.searchForm.get('jobCardNo').value.code;
  }
  if(this.searchForm.get('chassisNo').value != null) {
    this.warrantyLogsheet.chassisNo = this.searchForm.get('chassisNo').value.code;
  }
  if(this.searchForm.get('mobileNo').value != null) {
    this.warrantyLogsheet.mobileNo = this.searchForm.get('mobileNo').value.code;
  }
  filterObj.logsheetFromDate = filterObj.logsheetFromDate ? ObjectUtil.convertDate(filterObj.logsheetFromDate) : null
  filterObj.logsheetToDate = filterObj.logsheetToDate ? ObjectUtil.convertDate(filterObj.logsheetToDate) : null

  filterObj.jobCardFromDate = filterObj.jobCardFromDate ? ObjectUtil.convertDate(filterObj.jobCardFromDate) : null
  filterObj.jobCardToDate = filterObj.jobCardToDate ? ObjectUtil.convertDate(filterObj.jobCardToDate) : null
  // this.warrantyLogsheet.logsheetToDate = this.searchForm.get('logsheetToDate').value?ObjectUtil.convertDate(this.searchForm.get('logsheetToDate').value):null;
  //  this.warrantyLogsheet.logsheetFromDate = this.searchForm.get('logsheetFromDate').value?ObjectUtil.convertDate(this.searchForm.get('logsheetFromDate').value):null;
  //this.warrantyLogsheet.jobCardToDate = this.searchForm.get('jobCardToDate').value?ObjectUtil.convertDate(this.searchForm.get('jobCardToDate').value):null;
  //this.warrantyLogsheet.jobCardFromDate = this.searchForm.get('jobCardFromDate').value?ObjectUtil.convertDate(this.searchForm.get('jobCardFromDate').value):null;

  this.searchFilter = ObjectUtil.removeNulls(filterObj);
  delete this.searchFilter.page;
  delete this.searchFilter.size;

  if (!this.checkValidDatesInput(this.searchFilter.logsheetFromDate, this.searchFilter.logsheetToDate)) {
    this.toastr.error("Please Select PCR Date Range.");
    return false;
  }
  if (!this.checkValidDatesInput(this.searchFilter.jobCardFromDate, this.searchFilter.jobCardToDate)) {
    this.toastr.error("Please Select Job Card Date Range.");
    return false;
  }

  if (Object.keys(this.searchFilter).length>0) {

    localStorage.setItem(this.key, JSON.stringify(this.searchFilter))

    this.searchFilter.page = this.page
    this.searchFilter.size = this.size

    if(this.searchFilter.dealerShip && typeof this.searchFilter.dealerShip == 'object'){
      this.searchFilter.dealerId = this.searchFilter.dealerShip.id;
      delete this.searchFilter.dealerShip;
    }
    this.searchWarrantyLogSheet(this.searchFilter);
  
  } else{
    this.toastr.error("Please fill atleast one input field.");
  }
    
}
 etSearchDateValueChange(searchDate: string, columnKey: string) {
  let modifiedDate = null;
  if (searchDate) {
    modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
  }
   this.searchValue = new ColumnSearch(modifiedDate, columnKey);
 }

//  search () {
//   debugger
//   this.searchDetail();
//   this.searchFilter = ObjectUtil.removeNulls(this.warrantyLogsheet);
//   delete this.searchFilter.page;
//   delete this.searchFilter.size;

//   if (Object.keys(this.searchFilter).length>0) {

    
//     this.searchFilter.page = this.page
//     this.searchFilter.size = this.size

//     this.searchWarrantyLogSheet(this.searchFilter);
  
//   } else{
//     this.toastr.error("Please fill atleast one input field.");
//   }
//  }

//  searchDetail() {
//   if(this.searchForm.get('logsheetNo').value != null) {
//     this.warrantyLogsheet.logsheetNo = this.searchForm.get('logsheetNo').value.code;
//   }
//   if(this.searchForm.get('jobCardNo').value != null) {
//     this.warrantyLogsheet.jobCardNo = this.searchForm.get('jobCardNo').value.code;
//   }
//   if(this.searchForm.get('chassisNo').value != null) {
//     this.warrantyLogsheet.chassisNo = this.searchForm.get('chassisNo').value.code;
//   }
//   if(this.searchForm.get('mobileNo').value != null) {
//     this.warrantyLogsheet.mobileNo = this.searchForm.get('mobileNo').value.code;
//   }

//   this.warrantyLogsheet.logsheetToDate = this.searchForm.get('logsheetToDate').value?ObjectUtil.convertDate(this.searchForm.get('logsheetToDate').value):null;
//   this.warrantyLogsheet.logsheetFromDate = this.searchForm.get('logsheetFromDate').value?ObjectUtil.convertDate(this.searchForm.get('logsheetFromDate').value):null;
//   this.warrantyLogsheet.jobCardToDate = this.searchForm.get('jobCardToDate').value?ObjectUtil.convertDate(this.searchForm.get('jobCardToDate').value):null;
//   this.warrantyLogsheet.jobCardFromDate = this.searchForm.get('jobCardFromDate').value?ObjectUtil.convertDate(this.searchForm.get('jobCardFromDate').value):null;

//   // this.searchForm.patchValue(this.warrantyLogsheet);
//   // this.warrantyLogsheet = this.searchForm.getRawValue();
//  }


 clear() {
   this.searchForm.reset();
   this.clearSearchRow.next("");
   this.dataTable=null
 }
 checkValidDatesInput(fromDate:string, toDate:string){
  if(fromDate && toDate){
    return true;
  } else if(fromDate || toDate){
    return false;
  } else {
    return true;
  }
}
  /**
  * ----------Following is state management code------------
  */

 initialQueryParams(event: SearchWarrantyLogSheet){ 
  console.log('initialQueryParams event: ', event);
  const searchValue = /%2F/g;
  const newValue = '/';
  this.searchForm.patchValue(event);
  if(event.logsheetNo) {
    this.searchForm.get('logsheetNo').patchValue({code : event.logsheetNo.replace(searchValue, newValue)});
    event.logsheetNo = event.logsheetNo.replace(searchValue, newValue);
  }
  if(event.chassisNo) {
    this.searchForm.get('chassisNo').patchValue({code : event.chassisNo.replace(searchValue, newValue)});
    event.chassisNo = event.chassisNo.replace(searchValue, newValue);
  }
  if(event.jobCardNo) {
    this.searchForm.get('jobCardNo').patchValue({code : event.jobCardNo.replace(searchValue, newValue)});
    event.jobCardNo = event.jobCardNo.replace(searchValue, newValue);
  }
  if(event.mobileNo) {
    this.searchForm.get('mobileNo').patchValue({code : event.mobileNo.replace(searchValue, newValue)});
    event.mobileNo = event.mobileNo.replace(searchValue, newValue);
  }
  this.page = event.page;
  this.size = event.size;
  this.searchForm.get('page').patchValue(event.page);
  this.searchForm.get('size').patchValue(event.size);
}

//  onUrlChange(event) {
//   console.log('onUrlChange event: ', event);
//   if (!event) {
//   return;
//   }
//   const {queryParam=null, url=''} = {...event};
//   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.WARRANTY, { url, queryParam } as UrlSegment);
//   }


logsheetReport(event:any){
 
    let searchobj = this.searchForm.value;
     
 
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj.logsheetFromDate = searchobj.logsheetFromDate ? ObjectUtil.convertDate(searchobj.logsheetFromDate) : null
    searchobj.logsheetToDate = searchobj.logsheetToDate ? ObjectUtil.convertDate(searchobj.logsheetToDate) : null
  //  searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    
    this.searchFlag = true;
    
    if (Object.keys(searchobj).length>0) {
      // if (!this.dateService.checkValidDatesInput(searchobj.fromDate, searchobj.toDate)) {
      //   this.toastr.error("Please Select Due Date Range.");
      //   return false;
      // }

      localStorage.setItem(this.key, JSON.stringify(searchobj))

      searchobj.page = this.page
      searchobj.size = this.size
     
      this.logSheetSearchPageWebService.downloadExportExcel(searchobj).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
        
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
      })
    }else{
      this.toastr.error("Please fill atleast one input field.");
    }
  }
}


