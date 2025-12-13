import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { JobCardSearchStore } from './search-job-card-store';
import { JobCardSearchPresenter } from './search-job-card-presenter';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SearchJobCardWebService } from './search-job-card-web.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DateService } from '../../../../../root-service/date.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction, } from '../../../../../confirmation-box/confirmation-box.component'
import { MatDialog } from '@angular/material'
import { ToastrService } from 'ngx-toastr';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-search-job-card-page',
  templateUrl: './search-job-card-page.component.html',
  styleUrls: ['./search-job-card-page.component.scss'],
  providers: [JobCardSearchPresenter, JobCardSearchStore, SearchJobCardWebService]
})
export class SearchJobCardPageComponent implements OnInit, AfterViewInit {
  jobCardSearchForm: FormGroup
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  public totalTableElements: number;
  searchValue: ColumnSearch;
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  key = "jobcardpage";
  pageLoadCount:number=0
  public showExcel:boolean =  false;
  constructor(
    private presenter: JobCardSearchPresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ngswSearchTableService: NgswSearchTableService,
    private searchJobCardWebService: SearchJobCardWebService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private tostr: ToastrService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.jobCardSearchForm = this.presenter.jobCardBasicSearchForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.jobCardSearchForm != null) {
      this.jobCardSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.jobCardSearchForm.get('chassisNo').value==null && this.jobCardSearchForm.get('jobCardNo').value==null && this.jobCardSearchForm.get('engineNo').value==null && this.jobCardSearchForm.get('bookingNo').value==null && this.jobCardSearchForm.get('jobCardDateFrom').value==null && this.jobCardSearchForm.get('jobCardDateTo').value==null) {
      this.maxDate = this.newdate
      let backDate = new Date();
      this.dataTable=null
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.minDate = backDate;
      this.jobCardSearchForm.get('jobCardDateFrom').patchValue(backDate);
      this.jobCardSearchForm.get('jobCardDateTo').patchValue(new Date());
      this.data()
  
    }
    else {
      localStorage.getItem(this.key)
      // this.searchJobCard();
    }

   
  }
  private data(){
    this.searchJobCardWebService.excelTypeSubject.subscribe(response => {
      if(response){
      if(response=='VIEW'){
        this.showExcel = false;
     
      }else{
        this.showExcel = true;
        
      }
    }
  });
  
  }
  createJobCard() {
    this.router.navigate(['../create'], {
      relativeTo: this.activatedRoute
    })
  }
  ngAfterViewInit() {
    console.log('ngAfterViewInit')
    // this.searchJobCard();
  }

  onClickClearJobCard() {
    this.jobCardSearchForm.reset()
    // this.searchJobCard()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }
  searchJobCard() {
    console.log("search job card")
    const searchFormValues = this.jobCardSearchForm.getRawValue()
    const formValues = {} as any
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.jobCardSearchForm.value))
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
      formValues['page'] = this.page;
      formValues['size'] = this.size;
    }
    else {
      formValues['page'] = this.page;
      formValues['size'] = this.size;
    }

    const temp = this.jobCardSearchForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    if (Object.keys(searchFormValues).length>0) {
      if (!this.dateService.checkValidDatesInput(searchFormValues.fromMachineInDate, searchFormValues.toMachineInDate)) {
       this.tostr.error("Please Select Due Date Range.");
       return false;
     }
   }
    // bookingNo: null
    // branch: null
    // chassisNo: null
    // csbNo: null
    // dealerShip: null
    // engineNo: null
    formValues['chassisNo'] = searchFormValues['chassisNo']? searchFormValues['chassisNo'] : null
    formValues['jobCardNo'] = searchFormValues['jobCardNo']? searchFormValues['jobCardNo'] : null
    formValues['engineNo'] = searchFormValues['engineNo']? searchFormValues['engineNo'] : null
    formValues['bookingNo'] = searchFormValues['bookingNo']? searchFormValues['bookingNo'] : null

    formValues['jobCardDateFrom'] = searchFormValues['jobCardDateFrom']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardDateFrom']):null;
    formValues['jobCardDateTo'] = searchFormValues['jobCardDateTo']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardDateTo']):null;
    formValues['status']= searchFormValues['status']? searchFormValues['status'] : null
    formValues['isFor'] = 'VIEW';
    formValues['branch'] = searchFormValues['branch']? searchFormValues['branch'] : null
    formValues['dealerId'] = searchFormValues['dealerShip']? searchFormValues['dealerShip'].id : null
    formValues['fromMachineInDate'] =  searchFormValues['fromMachineInDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['fromMachineInDate']):null;
    formValues['toMachineInDate'] =  searchFormValues['toMachineInDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['toMachineInDate']):null;
    formValues['product'] = searchFormValues['product']? searchFormValues['product']:null
    formValues['series'] = searchFormValues['series']? searchFormValues['series']:null
    formValues['model'] = searchFormValues['model']? searchFormValues['model']:null
    formValues['subModel'] = searchFormValues['subModel']? searchFormValues['subModel']:null
    formValues['variant'] = searchFormValues['variant']? searchFormValues['variant']:null
    formValues['hierId'] = searchFormValues.orgHierLevel5 ? searchFormValues.orgHierLevel5['org_hierarchy_id'] : (searchFormValues.orgHierLevel4 ? searchFormValues.orgHierLevel4['org_hierarchy_id'] : (searchFormValues.orgHierLevel3 ? searchFormValues.orgHierLevel3['org_hierarchy_id'] : (searchFormValues.orgHierLevel2 ? searchFormValues.orgHierLevel2['org_hierarchy_id'] : searchFormValues.orgHierLevel1 ? searchFormValues.orgHierLevel1['org_hierarchy_id'] : 0)))
    formValues['partNo'] = searchFormValues['partNo']? searchFormValues['partNo']:null
    this.searchFilter = ObjectUtil.removeNulls(formValues);
    console.log('formValues', searchFormValues)
    

    if (this.checkValidDatesInput()) {
      if (this.jobCardSearchForm.get('chassisNo').value || this.jobCardSearchForm.get('jobCardNo').value || this.jobCardSearchForm.get('engineNo').value || 
      this.jobCardSearchForm.get('partNo').value || this.jobCardSearchForm.get('status').value || this.jobCardSearchForm.get('bookingNo').value || 
      this.jobCardSearchForm.get('jobCardDateFrom').value ||this.jobCardSearchForm.get('fromMachineInDate').value||this.jobCardSearchForm.get('toMachineInDate').value || this.jobCardSearchForm.get('jobCardDateTo').value|| this.jobCardSearchForm.get('dealerShip').value ||this.jobCardSearchForm.get('branch').value||this.jobCardSearchForm.get('product').value||this.jobCardSearchForm.get('series').value||this.jobCardSearchForm.get('model').value||this.jobCardSearchForm.get('subModel').value||this.jobCardSearchForm.get('variant').value) {
        localStorage.setItem(this.key, JSON.stringify(this.jobCardSearchForm.value))
        this.searchJobCardWebService.searchJobCardTable(formValues).subscribe(res => {
          if(res){
          this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
          this.totalTableElements = res['count'];
          }
        })
      }
     
      else if (this.jobCardSearchForm.get('jobCardDateFrom').value == null && this.jobCardSearchForm.get('jobCardDateTo').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
    this.searchFlag = true;
  }
  tableAction(event: object) {
    console.log('event', event)
    let jobcardno = btoa(event['record']['jobCardNo'])
    let searchfilter = btoa(JSON.stringify(this.searchFilter))
    if (event['btnAction'].toLowerCase() === 'jobcardno') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: jobcardno, hasButton: false }
      })
    }
    if (event['btnAction'].toLowerCase() === 'edit') {
      if (event['record']['edit'] == 'Re-Open') {
        this.openConfirmDialogReopen(event['record']['id']);
      } else {
        this.router.navigate(['../edit'], {
          relativeTo: this.activatedRoute, queryParams: { id: jobcardno }
        })
      }
    }
  }
  /**
  * ----------Following is state management code------------
  */

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.jobCardSearchForm.get('basicJobCardSearch').patchValue(event);
    this.page = event.page
    this.size = event.size
  }
  checkValidDatesInput() {
    const fltEnqObj = this.jobCardSearchForm.value

    fltEnqObj.fromDate = this.jobCardSearchForm.getRawValue() ? this.jobCardSearchForm.value.jobCardDateFrom : null
    fltEnqObj.toDate = this.jobCardSearchForm.getRawValue() ? this.jobCardSearchForm.value.jobCardDateTo : null

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

  pageChange(event: any) {
    console.log('pageChange')
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.searchJobCard();
    }
    this.pageLoadCount = 1;
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url } as UrlSegment);
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    console.log('modifiedDate: ', modifiedDate);
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }

  private openConfirmDialogReopen(jobcardId): void | any {
    let message = `Do you want to Re-Open Job Card?`
    const confirmationData = this.setConfirmationModalData(message)
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        this.searchJobCardWebService.reopenJobCard(jobcardId).subscribe(res => {
          if (res['message'] === 'Job Card reopen successfully') {
            this.toastr.success(res['message']);
            this.searchJobCard();
          } else
            this.toastr.error(res['message']);
        });
      }
    })
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  print() {
    this.searchJobCardWebService.print().subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  jcExcelReport(event) {
    let formValues ={} as any
    const searchFormValues = this.jobCardSearchForm.getRawValue();
    if (this.jobCardSearchForm.get('excelView').value != null) {
        formValues['page'] = this.page;
        formValues['size'] = this.size;
        formValues['chassisNo'] = searchFormValues['chassisNo']? searchFormValues['chassisNo'] : null
        formValues['jobCardNo'] = searchFormValues['jobCardNo']? searchFormValues['jobCardNo'] : null
        formValues['engineNo'] = searchFormValues['engineNo']? searchFormValues['engineNo'] : null
        formValues['bookingNo'] = searchFormValues['bookingNo']? searchFormValues['bookingNo'] : null

        formValues['jobCardDateFrom'] = searchFormValues['jobCardDateFrom']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardDateFrom']):null;
        formValues['jobCardDateTo'] = searchFormValues['jobCardDateTo']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardDateTo']):null;
        formValues['status']= searchFormValues['status']? searchFormValues['status'] : null
    
        formValues['isFor'] = this.jobCardSearchForm.get('excelView').value
        formValues['dealerId'] = searchFormValues['dealerShip']? searchFormValues['dealerShip'].id : null
        formValues['fromMachineInDate'] =  searchFormValues['fromMachineInDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['fromMachineInDate']):null;
        formValues['toMachineInDate'] =  searchFormValues['toMachineInDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['toMachineInDate']):null;
        formValues['product'] = searchFormValues['product']? searchFormValues['product']:null
        formValues['series'] = searchFormValues['series']? searchFormValues['series']:null
        formValues['model'] = searchFormValues['model']? searchFormValues['model']:null
        formValues['subModel'] = searchFormValues['subModel']? searchFormValues['subModel']:null
        formValues['variant'] = searchFormValues['variant']? searchFormValues['variant']:null
        formValues['partNo'] = searchFormValues['partNo']? searchFormValues['partNo']:null
        formValues['hierId'] = searchFormValues.orgHierLevel5 ? searchFormValues.orgHierLevel5['org_hierarchy_id'] : (searchFormValues.orgHierLevel4 ? searchFormValues.orgHierLevel4['org_hierarchy_id'] : (searchFormValues.orgHierLevel3 ? searchFormValues.orgHierLevel3['org_hierarchy_id'] : (searchFormValues.orgHierLevel2 ? searchFormValues.orgHierLevel2['org_hierarchy_id'] : searchFormValues.orgHierLevel1 ? searchFormValues.orgHierLevel1['org_hierarchy_id'] : 0)))

        this.searchFilter = ObjectUtil.removeNulls(formValues);
        //console.log('formValues', searchFormValues)
        this.downloadExcel(formValues)
    }else{
      this.jobCardSearchForm.get('excelView').setErrors({
        jcExcelError:'Please Select  Excel View First'
      })
      if (this.jobCardSearchForm.get('excelView').status == 'INVALID') {
        this.jobCardSearchForm.get('excelView').markAllAsTouched()
      }
    }

  }
  downloadExcel(data){
    console.log(this.jobCardSearchForm,'form')
  if(this.jobCardSearchForm.get('jobCardDateFrom').value ==null && this.jobCardSearchForm.get('jobCardDateTo').value ==null && this.jobCardSearchForm.get('chassisNo').value ==null && this.jobCardSearchForm.get('jobCardNo').value ==null&& this.jobCardSearchForm.get('engineNo').value ==null&& this.jobCardSearchForm.get('partNo').value ==null&& this.jobCardSearchForm.get('bookingNo').value ==null&& this.jobCardSearchForm.get('status').value ==null&& this.jobCardSearchForm.get('dealerShip').value ==null &&this.jobCardSearchForm.get('fromMachineInDate').value ==null && this.jobCardSearchForm.get('toMachineInDate').value ==null&& this.jobCardSearchForm.get('branch').value ==null&& this.jobCardSearchForm.get('product').value ==null&& this.jobCardSearchForm.get('series').value ==null&& this.jobCardSearchForm.get('model').value ==null && this.jobCardSearchForm.get('subModel').value ==null&& this.jobCardSearchForm.get('variant').value ==null&& this.jobCardSearchForm.get('model').value ==null&& this.jobCardSearchForm.get('model').value ==null){
    this.tostr.error("Please Select Atleast One Input Field");
  }else{
    this.searchJobCardWebService.downloadJcExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
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
