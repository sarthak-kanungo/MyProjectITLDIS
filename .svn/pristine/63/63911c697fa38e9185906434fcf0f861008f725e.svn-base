import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { WcrSearchPageStore } from './warrenty-claim-request-search-page.store';
import { WcrSearchPagePresenter } from './warrenty-claim-request-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { WarrentyClaimRequestSearchWebService } from '../warrenty-claim-request-search/warrenty-claim-request-search-web.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { SearchWcr } from '../../domain/warrenty-claim-request.domain';
import { ToastrService } from 'ngx-toastr';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { DateService } from 'src/app/root-service/date.service';
import { saveAs } from 'file-saver';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { ModalFileUploadComponent } from 'src/app/warranty/modal-file-upload/modal-file-upload.component';
import { WarrantyClaimUploadComponent } from '../warranty-claim-upload/warranty-claim-upload.component';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';
import { WcrPageStore } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.store';
import { SharedDataService } from '../warranty-claim-upload/warranty-claim-upload-service';
import { InvoiceVerifyModalComponent } from '../invoice-verify-modal/invoice-verify-modal.component';
import { invoiceVerifyService } from '../invoice-verify-modal/invoice-verify-modal-service';

@Component({
  selector: 'app-warrenty-claim-request-search-page',
  templateUrl: './warrenty-claim-request-search-page.component.html',
  styleUrls: ['./warrenty-claim-request-search-page.component.scss'],
  providers: [WcrSearchPageStore,WcrPagePresenter,WcrPageStore, WcrSearchPagePresenter, WarrentyClaimRequestSearchWebService]
})
export class WarrentyClaimRequestSearchPageComponent implements OnInit, AfterViewInit {
  page = 0;
  size = 10;
  @ViewChild(WarrantyClaimUploadComponent, { static: true}) child: WarrantyClaimUploadComponent;

  private warrantyWcr = {} as SearchWcr;
  searchForm: FormGroup;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  searchFilter: any;
  searchFlag: boolean = true
  clearSearchRow = new BehaviorSubject<string>("");
  fromDate: Date;
  newdate = new Date()
  key = 'wcrsp';
  searchFilterValues: any;
  usertype: string;
  pageLoadCount: number = 0
  departmentName: any;
  receivedData: any;
  getRecieveDate: any;

  constructor(
    private wcrSearchPagePresenter: WcrSearchPagePresenter,
    private presenter:WcrPagePresenter,
    private warrentyClaimRequestSearchWebService: WarrentyClaimRequestSearchWebService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private dialog: MatDialog,
    private sharedDataService: SharedDataService,
    private service:invoiceVerifyService,
  ) {
     this.receivedData = this.sharedDataService.getData();
    this.getRecieveDate= this.sharedDataService.getDatas()
    

   
   }

  ngOnInit() {
   

    let loginUser = localStorage.getItem('kubotaUser');
    loginUser = JSON.parse(JSON.parse(JSON.stringify(loginUser)))

    this.usertype = loginUser['userType'];
    this.departmentName = loginUser['departmentName']

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchForm = this.wcrSearchPagePresenter.wcrSearchForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    if (this.searchForm.get('wcrNo').value == null && this.searchForm.get('wcrType').value == null && this.searchForm.get('wcrStatus').value == null && this.searchForm.get('jobCardNo').value == null && this.searchForm.get('pcrNo').value == null && this.searchForm.get('chassisNo').value == null && this.searchForm.get('wcrFromDate').value == null && this.searchForm.get('wcrToDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.searchForm.get('wcrFromDate').patchValue(backDate);
      this.searchForm.get('wcrToDate').patchValue(new Date());
      this.search();
    } else {
      localStorage.getItem(this.key)
      this.search();
    }
  }

  ngAfterViewInit() {
   
    this.searchForm.valueChanges.subscribe(val => {
      this.warrantyWcr = val;
    })
    // this.searchWcr(this.warrantyWcr);
  }

  tableAction(event: object) {
    if (event && event['btnAction'] && (event['btnAction'].toLowerCase() === 'wcrno')) {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { wcrNo: btoa(event['record']['wcrNo']), name: event['btnAction'] } });
    }
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'action' && event['record']['action'].toLowerCase() === 'received') {
      this.openConfirmDialog(event['record']['id']);
    }
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'action' && event['record']['action'].toLowerCase() === 'upload invoice') {
      this.invoiceUploadForDealer(event['record']['id']);
    }
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'action' && event['record']['action'].toLowerCase() === 'verify invoice') {
     this.getInvoiceDetails(event['record']['id'])
      this.openConfirmDialogVerification(event['record']['id']);
    }
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'kaiinvoiceupload') {
      
      this.invoiceUploadForKai(event['record']['id']);
    
    }
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'invoice') {
      this.downloadInvoice(event['record']['id']);
    }

  }
  downloadInvoice(id) {
    this.warrentyClaimRequestSearchWebService.downloadInvoice(id).subscribe((response: HttpResponse<Blob>) => {
      if (response) {
        console.log('response.headers--------------', response.headers)
        let headerContentDispostion = response.headers.get('Content-Disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([response.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }
  pageSizeChange(event: InfoForGetPagination) {
    this.page = event['page'];
    this.size = event['size'];

    this.searchFlag = false;
    if (this.pageLoadCount > 0) {
      this.search();
    }
    this.pageLoadCount = 1;

  }
  etSearchDateValueChange(searchDate: string, columnKey: string) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, columnKey);
  }

  // Invoice Upload For Kai User By Ankit Rai
  invoiceUploadForKai(wcrId){
    const dialogConfig = new MatDialogConfig(); 
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.dialog.open(WarrantyClaimUploadComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      let updatedata = { 
        'wcrId': wcrId,    
     }
      if (result.event === 'upload') {
        let file: File = result.data;
        this.warrentyClaimRequestSearchWebService.kaiInvoiceUpload(updatedata, file).subscribe(res => {
          if (res.status == 200) {
            this.tostr.success(res['message']);
            this.search();
          } else {
            this.tostr.error(res.message)
          }
        })
      }
    })
  }
// Invoice Upload for Dealer User By Ankit rai
  invoiceUploadForDealer(wcrId) {
    const dialogConfig = new MatDialogConfig(); 
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.dialog.open(WarrantyClaimUploadComponent, dialogConfig);
    
    modalDialog.afterClosed().subscribe(result => {
      this.receivedData = this.sharedDataService.getData();
      this.getRecieveDate= this.sharedDataService.getDatas()
      
      let updatedata = { 
        'wcrId': wcrId,
        'invoiceNo':this.receivedData,
         'invoiceDate':this.getRecieveDate
     }
      if (result.event === 'upload') {
        let file: File = result.data;
        this.warrentyClaimRequestSearchWebService.uploadFile(updatedata, file).subscribe(res => {
          if (res.status == 200) {
            this.tostr.success(res['message']);
            this.search();
          } else {
            this.tostr.error(res.message)
          }
        })
      }
    })
  }

  private openConfirmDialog(wcrId) {
    let message = `Do you want to save deatils as Received?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "Confirm") {
        this.warrentyClaimRequestSearchWebService.updateWcrAsReceived(wcrId).subscribe(response => {
          this.tostr.success(response['message']);
          this.search();
        });
      }
    });
  }
  private getInvoiceDetails(wcrId){
    this.warrentyClaimRequestSearchWebService.getInvoiceDetail(wcrId).subscribe(res => {
       if(res['result'].invoiceNo !=null ||res['result'].invoiceDate !=null ){      
        this.presenter.kaiInvoiceForm.patchValue({
          invoiceNo:res['result'].invoiceNo?res['result'].invoiceNo:null,
          invoiceDate:res['result'].invoiceDate?res['result'].invoiceDate:null
        })
        const sendinvoiceno=this.presenter.kaiInvoiceForm.value.invoiceNo
         this.service.setInvoiceNo(sendinvoiceno);
         const sendInvoiceDate=this.presenter.kaiInvoiceForm.value.invoiceDate
         this.service.setInvoiceDate(sendInvoiceDate)
      }else{
        this.tostr.error("Invoice Number Or Invoice Date not empty")
      }
    })
  }

  // Add new code for invoice Verification
  private openConfirmDialogVerification(wcrId) {
   

    // const confirmationData = this.setConfirmationModalData(message);
    // const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
    //   width: "500px",
    //   panelClass: "confirmation_modal",
    //   data: confirmationData
    // });
    // dialogRef.afterClosed().subscribe(result => {
    //   if (result == "Confirm") {
    //     this.warrentyClaimRequestSearchWebService.updateWcrAsVerified(wcrId).subscribe(response => {
    //       this.tostr.success(response['message']);
    //       this.search();
    //     });
    //   }
    // });
   let message = `Do you want to save deatils as Verified?`;
    const dialogConfig = new MatDialogConfig(); 
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.dialog.open(InvoiceVerifyModalComponent, dialogConfig);
    
    modalDialog.afterClosed().subscribe(result => {
      this.receivedData = this.service.getInvoiceNo()
      this.getRecieveDate= this.service.getInvoiceDate()
      
      let updatedata = { 
        'wcrId': wcrId,
        'invoiceNo':this.receivedData,
         'invoiceDate':this.getRecieveDate
     }
      if (result) { 
        this.warrentyClaimRequestSearchWebService.updateWcrAsVerified(updatedata).subscribe(res => {
          if (res) {
            this.tostr.success(res['message']);
            this.search();
          } else {
            this.tostr.error(res['message'])
          }
        })
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    return confirmationData;
  }

  search() {


    if (this.dataTable != undefined || this.dataTable != null) {
      console.log('if1')
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
    // else {
    //   this.page = 0;
    //   this.size = 10;
    // }
    this.searchFlag = true;
    const filterObj = this.searchForm.value as SearchWcr
    filterObj.page = this.page
    filterObj.size = this.size

    filterObj.wcrFromDate = filterObj.wcrFromDate ? ObjectUtil.convertDate(filterObj.wcrFromDate) : null
    filterObj.wcrToDate = filterObj.wcrToDate ? ObjectUtil.convertDate(filterObj.wcrToDate) : null
    filterObj.pcrToDate = filterObj.pcrToDate ? ObjectUtil.convertDate(filterObj.pcrToDate) : null
    filterObj.pcrFromDate = filterObj.pcrFromDate ? ObjectUtil.convertDate(filterObj.pcrFromDate) : null
    filterObj.jobCardFromDate = filterObj.jobCardFromDate ? ObjectUtil.convertDate(filterObj.jobCardFromDate) : null
    filterObj.jobCardToDate = filterObj.jobCardToDate ? ObjectUtil.convertDate(filterObj.jobCardToDate) : null

    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    delete this.searchFilter.page;
    delete this.searchFilter.size;

    if (Object.keys(this.searchFilter).length > 0) {


      this.searchFilter.page = this.page
      this.searchFilter.size = this.size
      if (!this.checkValidDatesInputs(filterObj.wcrFromDate, filterObj.wcrToDate)) {
        this.tostr.error("Please Select Wcr  Date Range.");
        return false;
      }
      if (!this.checkValidDatesInputs(filterObj.jobCardFromDate, filterObj.jobCardToDate)) {
        this.tostr.error("Please Select Job Card Date Range.");
        return false;
      }
      if (!this.checkValidDatesInputs(filterObj.pcrFromDate, filterObj.pcrToDate)) {
        this.tostr.error("Please Select PCR  Date Range.");
        return false
      }
      if (this.searchFilter.dealerShip && typeof this.searchFilter.dealerShip == 'object') {
        this.searchFilter.dealerId = this.searchFilter.dealerShip.id;
        delete this.searchFilter.dealerShip;
      }


      this.searchWcr(this.searchFilter);

    } else {
      this.tostr.error("Please fill atleast one input field.");
    }

    //  if (this.searchForm.valid) {

  }
  private searchWcr(warrantyWcr: SearchWcr) {
    this.warrentyClaimRequestSearchWebService.searchWcr(warrantyWcr).subscribe(res => {
      let result = res['result'];
      if (result && this.usertype == 'dealer' || (this.usertype == 'kubota' && this.departmentName == 'QA') ) {

      } else {
        result.forEach(res => {
          // console.log(res,'less')
          delete res['action']
        })
      }
      
      if (this.usertype == 'kubota') {

      } else {
        res.result.forEach(result => {
          delete result.kaiInvoiceUpload;
        })
      }
     

      this.dataTable = this.tableDataService.convertIntoDataTable(res.result)
      this.totalTableElements = res.count;
    });
  }

  //commented by kanhaiya
  // searchDetail() {

  //   if (this.searchForm.get('jobCardNo').value != null) {
  //     this.warrantyWcr.jobCardNo = this.searchForm.get('jobCardNo').value.code;
  //   }
  //   if (this.searchForm.get('mobileNo').value != null) {
  //     this.warrantyWcr.customerMobileNo = this.searchForm.get('mobileNo').value.code;
  //   }
  //   if (this.searchForm.get('chassisNo').value != null) {
  //     this.warrantyWcr.chassisNo = this.searchForm.get('chassisNo').value.code;
  //   }
  //   if (this.searchForm.get('wcrNo').value != null) {
  //     this.warrantyWcr.wcrNo = this.searchForm.get('wcrNo').value.code;
  //   }
  //   if (this.searchForm.get('pcrNo').value != null) {
  //     this.warrantyWcr.pcrNo = this.searchForm.get('pcrNo').value.code;
  //   }
  //   this.warrantyWcr.pcrToDate = ObjectUtil.convertDate(this.searchForm.get('pcrToDate').value);
  //   this.warrantyWcr.pcrFromDate = ObjectUtil.convertDate(this.searchForm.get('pcrFromDate').value);
  //   this.warrantyWcr.jobCardToDate = ObjectUtil.convertDate(this.searchForm.get('jobCardToDate').value);
  //   this.warrantyWcr.jobCardFromDate = ObjectUtil.convertDate(this.searchForm.get('jobCardFromDate').value);
  //   this.warrantyWcr.wcrFromDate = ObjectUtil.convertDate(this.searchForm.get('wcrFromDate').value);
  //   this.warrantyWcr.wcrToDate = ObjectUtil.convertDate(this.searchForm.get('wcrToDate').value);


  //   if (this.dataTable) {
  //     if (this.searchFlag == true) {
  //       this.page = 0;
  //       this.size = 10;
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //     else {
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //   }
  //   this.warrantyWcr.page = this.page;
  //   this.warrantyWcr.size = this.size;

  //   this.searchFlag = true;

  // }
  checkValidDatesInputs(fromDate: string, toDate: string) {
    if (fromDate && toDate) {
      return true;
    } else if (fromDate || toDate) {
      return false;
    } else {
      return true;
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchForm.value

    fltEnqObj.fromDate = this.searchForm.getRawValue() ? this.searchForm.value.wcrFromDate : null
    fltEnqObj.toDate = this.searchForm.getRawValue() ? this.searchForm.value.wcrToDate : null

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

  clear() {
    this.searchForm.reset();
    this.dataTable = null
    //  this.warrantyWcr = this.searchForm.getRawValue(); 
    //  this.warrantyWcr.page = 0;
    //  this.warrantyWcr.size = this.size; 
    //  this.warrantyWcr =  ObjectUtil.removeNulls(this.warrantyWcr);
    //  this.searchWcr(this.warrantyWcr);
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

  /**
   * ----------Following is state management code------------
   */

  initialQueryParams(event: SearchWcr) {
    const searchValue = /%2F/g;
    const newValue = '/';
    this.searchForm.patchValue(event);
    console.log(this.searchForm, 'form')
    // if (event.pcrNo) {
    //   this.searchForm.get('pcrNo').patchValue({ code: event.pcrNo.replace(searchValue, newValue) });
    //   event.pcrNo = event.pcrNo.replace(searchValue, newValue);
    // }
    // if (event.wcrNo) {
    //   this.searchForm.get('wcrNo').patchValue({ code: event.wcrNo.replace(searchValue, newValue) });
    //   event.wcrNo = event.wcrNo.replace(searchValue, newValue);
    // }
    // if (event.chassisNo) {
    //   this.searchForm.get('chassisNo').patchValue({ code: event.chassisNo.replace(searchValue, newValue) });
    //   event.chassisNo = event.chassisNo.replace(searchValue, newValue);
    // }
    // if (event.engineNo) {
    //   this.searchForm.get('engineNo').patchValue({ code: event.engineNo.replace(searchValue, newValue) });
    //   event.engineNo = event.engineNo.replace(searchValue, newValue);
    // }
    // if (event.jobCardNo) {
    //   this.searchForm.get('jobCardNo').patchValue({ code: event.jobCardNo.replace(searchValue, newValue) });
    //   event.jobCardNo = event.jobCardNo.replace(searchValue, newValue);
    // }
    this.page = event.page;
    this.size = event.size;
    this.searchForm.get('page').patchValue(event.page);
    this.searchForm.get('size').patchValue(event.size);
  }

  onUrlChange(event) {
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.WARRANTY, { url } as UrlSegment);
  }


  wcrExcelReport(event) {
    let formValues = {} as any
    const searchFormValues = this.searchForm.getRawValue();
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    if (Object.keys(searchFormValues).length > 0) {
      if (!this.dateService.checkValidDatesInput(searchFormValues.pcrFromDate, searchFormValues.pcrToDate)) {
        this.tostr.error("Please Select Pcr Due Date Range.");
        return false;
      }
    }
    if (Object.keys(searchFormValues).length > 0) {
      if (!this.dateService.checkValidDatesInput(searchFormValues.jobCardFromDate, searchFormValues.jobCardToDate)) {
        this.tostr.error("Please Select Job Card Due Date Range.");
        return false;
      }

    }
    if (Object.keys(searchFormValues).length > 0) {
      if (!this.dateService.checkValidDatesInput(searchFormValues.wcrFromDate, searchFormValues.wcrToDate)) {
        this.tostr.error("Please Select Wcr  Due Date Range.");
        return false;
      }

    }
    searchFormValues['wcrFromDate'] = searchFormValues['wcrFromDate'] ? this.dateService.getDateIntoYYYYMMDD(searchFormValues['wcrFromDate']) : null;
    searchFormValues['wcrToDate'] = searchFormValues['wcrToDate'] ? this.dateService.getDateIntoYYYYMMDD(searchFormValues['wcrToDate']) : null;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    if (this.searchFilter.dealerShip && typeof this.searchFilter.dealerShip == 'object') {
      searchFormValues.dealerId = searchFormValues.dealerShip.id;
      delete searchFormValues.dealerShip;
    }
    this.downloadExcel(searchFormValues)

  }
  downloadExcel(data) {
    console.log(this.searchForm, 'search')
    if (this.searchForm.get('wcrFromDate').value == null && this.searchForm.get('wcrToDate').value == null && this.searchForm.get('dealerShip').value == null && this.searchForm.get('branch').value == null && this.searchForm.get('state').value == null && this.searchForm.get('wcrNo').value == null && this.searchForm.get('wcrType').value == null && this.searchForm.get('wcrStatus').value == null && this.searchForm.get('finalStatus').value == null && this.searchForm.get('pcrNo').value == null && this.searchForm.get('jobCardNo').value == null && this.searchForm.get('chassisNo').value == null && this.searchForm.get('model').value == null && this.searchForm.get('failureType').value == null && this.searchForm.get('mobileNo').value == null && this.searchForm.get('registrationNo').value == null && this.searchForm.get('jobCardFromDate').value == null && this.searchForm.get('jobCardToDate').value == null && this.searchForm.get('pcrFromDate').value == null && this.searchForm.get('pcrToDate').value == null && this.searchForm.get('AREA-SERVICE').value == null && this.searchForm.get('HO-SERVICE').value == null && this.searchForm.get('REGION-SERVICE').value == null && this.searchForm.get('TERRITORY-SERVICE').value == null && this.searchForm.get('ZONE-SERVICE').value == null) {
      this.tostr.error("Please Select One input field !")
    } else {
      this.warrentyClaimRequestSearchWebService.downloadWcrExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
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
