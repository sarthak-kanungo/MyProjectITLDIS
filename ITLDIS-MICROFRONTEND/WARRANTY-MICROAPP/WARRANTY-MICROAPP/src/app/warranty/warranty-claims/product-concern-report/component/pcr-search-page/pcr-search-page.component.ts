import { Component, OnInit, AfterViewInit ,Input} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { PcrSearchPageWebService } from './pcr-search-page-web.service';
import { SearchWarrantyList, SearchWarrantyPcr } from '../../domain/product-concern-report.domain';
import { PCRSearchPageStore } from './pcr-search-page.store';
import { PCRSearchPagePresenter } from './pcr-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { DateService } from 'src/app/root-service/date.service';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-pcr-search-page',
  templateUrl: './pcr-search-page.component.html',
  styleUrls: ['./pcr-search-page.component.scss'],
  providers: [PcrSearchPageWebService, PCRSearchPageStore, PCRSearchPagePresenter]
})
export class PcrSearchPageComponent implements OnInit {

  @Input() pcrSearchForm: FormGroup;
  isAdvanceSearch: boolean;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  page = 0;
  size = 10;
  private warrantyPcr = {} as SearchWarrantyPcr;
  searchFilter: any;
  dealerCode: string;
  searchFlag: boolean = true
  clearSearchRow = new BehaviorSubject<string>("");
  toDate: Date;
  toDate1: Date;
  fromDate: Date;
  newdate = new Date();
  key = 'pcrsp';
  searchFilterValues: any;
  pageLoadCount:number=0
  constructor(
    private router: Router,
    private pcrSearchPageWebService: PcrSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private activatedRoute: ActivatedRoute,
    private pcrSearchPagePresenter: PCRSearchPagePresenter,
    private tostr: ToastrService,
    private iFrameService: IFrameService,
    private dateService: DateService,
    private dialog: MatDialog,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem(this.key)
    this.pcrSearchForm = this.pcrSearchPagePresenter.PCRSearchForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      
      this.pcrSearchForm.patchValue(this.searchFilterValues)
      this.isAdvanceSearch = true;
    }
    
    this.dealerCode = this.pcrSearchPagePresenter.loginUser.dealerCode;
    
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.fromDate = backDate;
    this.pcrSearchForm.get('pcrFromDate').patchValue(backDate);
    this.pcrSearchForm.get('pcrToDate').patchValue(new Date());
    
    // this.search();
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }

  private searchWarrantyPcr(warrantyPcr: SearchWarrantyPcr) {
    this.pcrSearchPageWebService.searchWarrantyPcr(warrantyPcr).subscribe(res => {
      let data:SearchWarrantyList[] = res.result;
      if (this.dealerCode != null) {
        data.forEach(response => {delete response.approve;delete response.dealerName;delete response.branch;})
      } 
      this.dataTable = this.tableDataService.convertIntoDataTable(data);
      this.totalTableElements = res.count;
    }, err => {
      
    });
  }

  tableAction(event: object) {
    let pcrno = btoa(event['record']['pcrNo'])
    let searchfilter = btoa(JSON.stringify(this.searchFilter))
    if (event && event['btnAction'] && (event['btnAction'] === 'pcrNo' || (event['btnAction'] === 'approve' && event['record']['approve']=='Approve'))) {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { pcrNo: pcrno, name: event['btnAction'] } });
    }
    if (event && event['btnAction'] && event['btnAction'] === 'jobCardNo') {
      let url = ''
      if(this.pcrSearchPagePresenter.loginUser.userType==='dealer' && event['record']['status']==='Hold')
        url = 'service/customerService/job-card/edit';
      else
        url = 'service/customerService/job-card/view';
      
		  this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: {id:btoa(event['record']['jobCardNo'])} })
    }
    if(event['btnAction'] === 'approve' && event['record']['approve']=='Special Approval'){
      this.openConfirmationDialog(event['record']['id']);
    }
  }

  openConfirmationDialog(pcrId){
    const message = `Do you want to send PCR for Special Approval?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result == 'Confirm' || result.button == 'Confirm') {
        this.pcrSearchPageWebService.specialApprovalRequired(pcrId, result.remarkText,).subscribe(response => {
          this.toastr.success("PCR Special Approval are done");
          this.search();
        },error=>{
          this.toastr.error("Error in special approval record generation");
        })
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  pageSizeChange(ev: InfoForGetPagination) {

    this.page = ev['page'];
    this.size = ev['size'];

    this.searchFlag = false;
    if(this.pageLoadCount > 0){
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
    const filterObj = this.pcrSearchForm.value as SearchWarrantyPcr;

    filterObj.pcrFromDate = filterObj.pcrFromDate ? ObjectUtil.convertDate(filterObj.pcrFromDate) : null
    filterObj.pcrToDate = filterObj.pcrToDate ? ObjectUtil.convertDate(filterObj.pcrToDate) : null

    filterObj.jobCardFromDate = filterObj.jobCardFromDate ? ObjectUtil.convertDate(filterObj.jobCardFromDate) : null
    filterObj.jobCardToDate = filterObj.jobCardToDate ? ObjectUtil.convertDate(filterObj.jobCardToDate) : null

    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    delete this.searchFilter.page;
    delete this.searchFilter.size;

    if (!this.checkValidDatesInput(this.searchFilter.pcrFromDate, this.searchFilter.pcrToDate)) {
      this.tostr.error("Please Select PCR Date Range.");
      return false;
    }
    if (!this.checkValidDatesInput(this.searchFilter.jobCardFromDate, this.searchFilter.jobCardToDate)) {
      this.tostr.error("Please Select Job Card Date Range.");
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
      this.searchWarrantyPcr(this.searchFilter);
    
    } else{
      this.tostr.error("Please fill atleast one input field.");
    }
      
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
  clearForm() {
    this.pcrSearchForm.reset();
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
    this.pcrSearchForm.controls.dealerShip.reset();
  }

  pcrExcelReport(event) {
    let formValues ={} as any
    const searchFormValues = this.pcrSearchForm.getRawValue();
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    if (Object.keys(searchFormValues).length>0) {
      if (!this.dateService.checkValidDatesInput(searchFormValues.pcrFromDate, searchFormValues.pcrToDate)) {
       this.tostr.error("Please Select Due Date Range.");
       return false;
     }
   }
   if (Object.keys(searchFormValues).length>0) {
    if (!this.dateService.checkValidDatesInput(searchFormValues.jobCardFromDate, searchFormValues.jobCardToDate)) {
     this.tostr.error("Please Select Due Date Range.");
     return false;
   }
 }
    searchFormValues['pcrFromDate'] = searchFormValues['pcrFromDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['pcrFromDate']):null;
    searchFormValues['pcrToDate'] = searchFormValues['pcrToDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['pcrToDate']):null;

    searchFormValues['jobCardFromDate'] = searchFormValues['jobCardFromDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardFromDate']):null;
    searchFormValues['jobCardToDate'] = searchFormValues['jobCardToDate']?this.dateService.getDateIntoYYYYMMDD(searchFormValues['jobCardToDate']):null;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    if(this.searchFilter.dealerShip && typeof this.searchFilter.dealerShip == 'object'){
      searchFormValues.dealerId = searchFormValues.dealerShip.id;
      delete searchFormValues.dealerShip;
    }
    this.downloadExcel(searchFormValues)

  }
  downloadExcel(data){

    if(this.pcrSearchForm.get('pcrFromDate').value==null && this.pcrSearchForm.get('pcrFromDate').value==null &&this.pcrSearchForm.get('pcrNo').value==null  &&this.pcrSearchForm.get('status').value==null && this.pcrSearchForm.get('product').value==null&& this.pcrSearchForm.get('series').value==null&& this.pcrSearchForm.get('machineModel').value==null && this.pcrSearchForm.get('subModel').value==null&& this.pcrSearchForm.get('variant').value==null&& this.pcrSearchForm.get('partNo').value==null&& this.pcrSearchForm.get('engineNo').value==null&& this.pcrSearchForm.get('chassisNo').value==null&& this.pcrSearchForm.get('jobCardFromDate').value==null&& this.pcrSearchForm.get('jobCardToDate').value==null&& this.pcrSearchForm.get('jobCardNo').value==null&& this.pcrSearchForm.get('state').value==null&& this.pcrSearchForm.get('branch').value==null&& this.pcrSearchForm.get('dealerShip').value==null && this.pcrSearchForm.controls['AREA-SERVICE']==null && this.pcrSearchForm.controls['HO-SERVICE']==null && this.pcrSearchForm.controls['REGION-SERVICE']==null && this.pcrSearchForm.controls['TERRITORY-SERVICE']==null && this.pcrSearchForm.controls['ZONE-SERVICE']==null){
      this.toastr.error("Please Select Atleast one input!")
    }else{
    this.pcrSearchPageWebService.downloadPcrExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
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
