import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ServiceActivityClaimSearchPageStore } from './service-activity-claim-search-page.store';
import { ServiceActivityClaimSearchPagePresenter } from './service-activity-claim-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { FilterSearchServiceActivityClaim } from '../../domain/service-activity-claim.domain';
import { DateService } from '../../../../../root-service/date.service';
import { ServiceActivityClaimSearchPageWebService } from './service-activity-claim-search-page-web.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-service-activity-claim-search-page',
  templateUrl: './service-activity-claim-search-page.component.html',
  styleUrls: ['./service-activity-claim-search-page.component.scss'],
  providers: [ServiceActivityClaimSearchPageStore, ServiceActivityClaimSearchPagePresenter, ServiceActivityClaimSearchPageWebService]
})
export class ServiceActivityClaimSearchPageComponent implements OnInit, AfterViewInit {
  searchFlag:boolean=true
  searchForm: FormGroup
  searchServiceActivityClaimForm: FormGroup
  isAdvanceSearch: boolean
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  loginUserType: string;
  page: number = 0
  size: number = 10
  public filterData : object
  searchFilterValues: any;
  pageLoadCount:number=0
  clearSearchRow = new BehaviorSubject<string>("");
  constructor(
    private serviceActivityClaimSearchPagePresenter: ServiceActivityClaimSearchPagePresenter,
    private router: Router,
    private dialog: MatDialog,
    private tableDataService: NgswSearchTableService,
    private actClaimRt: ActivatedRoute,
    private dateService: DateService,
    private serviceActivityClaimSearchPageWebService: ServiceActivityClaimSearchPageWebService,
    private loginFormService: LoginFormService,
    private toastr: ToastrService,
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
   }

  ngOnInit() {
    this.searchFilterValues=localStorage.getItem('searchFilterSAC')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.serviceActivityClaimSearchPagePresenter.serviceActivityClaimSearchForm
    this.searchServiceActivityClaimForm = this.serviceActivityClaimSearchPagePresenter.detailsForm
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.searchServiceActivityClaimForm.patchValue(this.searchFilterValues)
    }

  }


  ngAfterViewInit(){
    // this.onClickSearchServiceActivityClaim()
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch
  }

  onClickSearchServiceActivityClaim() {
    let searchObject = this.buildObjForSearchServiceActivityClaim();
    let key = 'searchFilterSAC';
    localStorage.setItem(key, JSON.stringify(searchObject));
    if (searchObject.fromDate && searchObject.toDate) {
      
    }else if (searchObject.fromDate || searchObject.toDate) {
      this.toastr.error("Please Select Date Range");
      return;
    }
    
    delete searchObject['page'];
    delete searchObject['size'];
    if(Object.keys(searchObject).length==0){
      this.toastr.error("Please fill atleast one input field");
      return;
    }

    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      searchObject['page'] = this.page;
      searchObject['size'] = this.size;
    }else{
      searchObject['page'] = this.page;
      searchObject['size'] = this.size;
    }
    this.searchFlag = true;

    this.serviceActivityClaimSearchPageWebService.serviceActivityClaimSearch(searchObject).subscribe(response => {
      let result = response['result'];
      //  result.forEach(result=>result.action="Approve")
      if(result && this.loginUserType.toLocaleLowerCase()=='dealer'){
        result.forEach(res => {
          delete res['dealerCode'];
          delete res['dealerName'];
          delete res['approve'];
          delete res['dealerState'];
        })
      }
      this.dataTable = this.tableDataService.convertIntoDataTable(result)
      this.totalTableElements = response['count']
    })
  }

  private buildObjForSearchServiceActivityClaim() {
    const filtObj = {} as FilterSearchServiceActivityClaim
    const searchServiceActivityClaimForm = this.searchServiceActivityClaimForm.value
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.searchServiceActivityClaimForm.value))
    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.activityNumber = searchServiceActivityClaimForm ? searchServiceActivityClaimForm.activityNumber : null
    filtObj.activityClaimNumber = searchServiceActivityClaimForm ? searchServiceActivityClaimForm.activityClaimNumber : null
    filtObj.activityClaimStatus = searchServiceActivityClaimForm ? searchServiceActivityClaimForm.activityClaimStatus : null
    filtObj.activityEffectiveness = searchServiceActivityClaimForm ? searchServiceActivityClaimForm.activityEffectiveness : null
    filtObj.activityType = searchServiceActivityClaimForm ? searchServiceActivityClaimForm.activityType : null
    filtObj.fromDate = searchServiceActivityClaimForm.fromDate ? this.dateService.getDateIntoYYYYMMDD(searchServiceActivityClaimForm.fromDate) : null
    filtObj.toDate = searchServiceActivityClaimForm.toDate ? this.dateService.getDateIntoYYYYMMDD(searchServiceActivityClaimForm.toDate) : null
    filtObj.dealerId = searchServiceActivityClaimForm.dealerCode?searchServiceActivityClaimForm.dealerCode['id']:null;
    filtObj.orgHierId = searchServiceActivityClaimForm.orgHierarchyId?searchServiceActivityClaimForm.orgHierarchyId:null;
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  // initialQueryParams(event) {
  //   console.log('initialQueryParams event: ', event)
  //   const searchValue = /%2F/g
  //   const newValue = '/'
  //   this.searchServiceActivityClaimForm.patchValue(event)
  //   if (event.activityClaimNumber) {
  //     this.searchServiceActivityClaimForm.get('activityNumber').patchValue(event.activityClaimNumber.replace(searchValue, newValue));
  //     event.activityClaimNumber = event.activityClaimNumber.replace(searchValue, newValue)
  //   } 
  //   if (event.activityNumber) {
  //     this.searchServiceActivityClaimForm.get('activityClaimNumber').patchValue(event.activityNumber.replace(searchValue, newValue));
  //     event.activityNumber = event.activityNumber.replace(searchValue, newValue)
  //   }  
  // }


  onClickClearServiceActivityClaim() {
    this.searchServiceActivityClaimForm.reset();
    this.dataTable = null;
    this.clearSearchRow.next("");
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  // onUrlChange(event) {
  //   console.log('onUrlChange event: ', event);
  //   if (!event) {
  //     return;
  //   }
  //   const { queryParam = null, url = '' } = { ...event };
  //   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam } as UrlSegment);
  // }

  actionOnTableRecord(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'claimnumber') {
      this.router.navigate(['view', recordData.record.id], { relativeTo: this.actClaimRt, queryParams: {filterData: JSON.stringify(this.filterData) } })
    }
    if (recordData.btnAction.toLowerCase() === 'action') {
      // if(recordData.record.action.toLowerCase()=='hold'){
      //   this.openConfirmDialogStatus(recordData.record.id);
      // }
      if(recordData.record.action.toLowerCase()=='generate invoice'){
        this.openConfirmDialog(recordData.record.id);
      } 
      if(recordData.record.action.toLowerCase()=='edit'){
        this.router.navigate(['edit', recordData.record.id], { relativeTo: this.actClaimRt, queryParams: {filterData: JSON.stringify(this.filterData) } })
      }
    }
    if(recordData.btnAction.toLowerCase()=='approve'){
      this.router.navigate(['approval', recordData.record.id], { relativeTo: this.actClaimRt, queryParams: {filterData: JSON.stringify(this.filterData) } })
    }
   
  }

   private openConfirmDialog(id): void | any {
    let message = 'Do you want to Generate Invoice?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      this.generateInvoice(id);
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  // private openConfirmDialogStatus(id) {
  //   let message = `Do you want to keep claim on Hold?`;

  //   const confirmationData = this.setConfirmationModalDataStatus(message);
  //   const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
  //     width: "500px",
  //     panelClass: "confirmation_modal",
  //     data: confirmationData
  //   });
  //   dialogRef.afterClosed().subscribe((result:DialogResult) => {
  //     if (result.button == "Confirm") {
  //       this.serviceActivityClaimSearchPageWebService.updateStatusHold(id, result.remarkText).subscribe(repone => {
  //         this.toastr.success('Record Saved Succesfully');
  //         this.onClickSearchServiceActivityClaim();
  //       });
  //     }
  //   });
  // }
  private setConfirmationModalDataStatus(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    confirmationData.remarkConfig = {
      source : Source.APPROVE
    }
    return confirmationData;
  }

  generateInvoice(claimId:number){
    this.serviceActivityClaimSearchPageWebService.generateInvoice(claimId, 'ServiceActivityClaim').subscribe(response =>{
      if(response){
        this.toastr.success(response['result']['msg']);
        if(response['result']['status']==='OK'){
          this.onClickSearchServiceActivityClaim();
        }
      }else {
        this.toastr.error('Invoice Generation Failed');
      }
    })
  }

  pageChange(event: any) {
    this.page = event.page
    this.size = event.size
    this.searchFlag=false;
    if(this.pageLoadCount > 0){
      this.onClickSearchServiceActivityClaim()
    }
    this.pageLoadCount = 1;
    // this.onClickSearchServiceActivityClaim()
  }

  serviceActivityClaimDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}