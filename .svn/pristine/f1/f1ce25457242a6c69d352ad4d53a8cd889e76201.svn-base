import { Component, OnInit, AfterViewInit } from '@angular/core';
import { SapSearchPageStore } from './sap-search-page.store';
import { SapSearchPagePresenter } from './sap-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SapSearchPageWebService } from './sap-search-page-web.service';
import { FilterSearchServiceActivityProposal } from '../../domain/sap.domain';
import { DateService } from '../../../../../root-service/date.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-sap-search-page',
  templateUrl: './sap-search-page.component.html',
  styleUrls: ['./sap-search-page.component.scss'],
  providers: [SapSearchPageStore, SapSearchPagePresenter, SapSearchPageWebService]
})
export class SapSearchPageComponent implements OnInit, AfterViewInit {
  private searchFlag: boolean = true;
  searchForm: FormGroup
  searchSapForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  loginUserType: string;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  pageLoadCount:number=0
  public filterData : object
  searchFilterValues: any;
  clearSearchRow = new BehaviorSubject<string>("");
  isAdvanceSearch:boolean=false;
  key1='pageChangeValue';
  constructor(
    private sapSearchPagePresenter: SapSearchPagePresenter,
    private router: Router,
    private sapSearchPageWebService: SapSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private sapRt: ActivatedRoute,
    private dateService: DateService,
    private loginFormService: LoginFormService,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private matDialog: MatDialog
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
  }

  ngOnInit() {
    this.searchFilterValues=localStorage.getItem('searchFilterSAP')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))

    this.searchForm = this.sapSearchPagePresenter.sapSearchForm
    this.searchSapForm = this.sapSearchPagePresenter.detailsForm
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.searchSapForm.patchValue(this.searchFilterValues)
    }
    else {
      localStorage.getItem(this.key1)
      this.onClickSearchActivityProposal()
    }
    
  }

  ngAfterViewInit(){
    // this.onClickSearchActivityProposal()
  }
  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch
  }

  onClickSearchActivityProposal() {

    let searchObject = this.buildObjForSearchServiceActivityProposal();
    let key = 'searchFilterSAP';
    localStorage.setItem(key, JSON.stringify(searchObject));

    if (searchObject.activityProposalFromDate && searchObject.activityProposalToDate) {
      
    }else if (searchObject.activityProposalFromDate || searchObject.activityProposalToDate) {
      
      this.toastr.error("Please Select Date Range");
      return;
    }

    delete searchObject['page'];
    delete searchObject['size'];
    if(Object.keys(searchObject).length==0){
      this.toastr.error("Please fill atleast one input field");
      return;
    }else{
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        searchObject['page'] = this.page;
        searchObject['size'] = this.size;
      }else{
        searchObject['page'] = this.page;
        searchObject['size'] = this.size;
      }
    }
    this.searchFlag == true;
    this.sapSearchPageWebService.serviceActivityProposalSearch(searchObject).subscribe(response => {
      let result = response['result'];
      //result.forEach(result=>result.Approve="Approve")
      if(result && this.loginUserType.toLocaleLowerCase()=='dealer'){
        result.forEach(res => {
          delete res['dealerCode'];
          delete res['dealerName'];
          delete res['dealerState'];
        })
      }
      this.dataTable = this.tableDataService.convertIntoDataTable(result);
      this.totalTableElements = response['count']
    })
  }

  private buildObjForSearchServiceActivityProposal() {
    const filtObj = {} as FilterSearchServiceActivityProposal
    const searchSapForm = this.searchSapForm.value
    console.log(searchSapForm,'searchSnapForm')
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.searchSapForm.value))
    let adc = JSON.parse(JSON.parse(JSON.stringify(localStorage.getItem(this.key1))))
    if(adc){
     filtObj['page'] = adc.page 
     filtObj['size'] = adc.size
   }
   else{
     filtObj['page'] = this.page 
     filtObj['size'] = this.size 
   }
    filtObj.activityNumber = searchSapForm ? searchSapForm.activityNumber : null
    filtObj.activityStatus = searchSapForm ? searchSapForm.activityStatus : null
    filtObj.activityType = searchSapForm ? searchSapForm.activityType : null
    filtObj.activityProposalFromDate = searchSapForm.activityProposalFromDate ? this.dateService.getDateIntoYYYYMMDD(searchSapForm.activityProposalFromDate) : null
    filtObj.activityProposalToDate = searchSapForm.activityProposalToDate ? this.dateService.getDateIntoYYYYMMDD(searchSapForm.activityProposalToDate) : null
    filtObj.orgHierId = searchSapForm.orgHierarchyId?searchSapForm.orgHierarchyId:null;
    this.filterData = this.removeNullFromObjectAndFormatDate(filtObj);
    return filtObj
  }

  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchSapForm.patchValue(event)
    if (event.activityNumber) {
      this.searchSapForm.get('activityNumber').patchValue(event.activityNumber.replace(searchValue, newValue));
      event.activityNumber = event.activityNumber.replace(searchValue, newValue)
    }  
  }


  onClickClearActivityProposal() {
    this.searchSapForm.reset()
    this.dataTable = null;
    this.clearSearchRow.next("");
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'activityProposalFromDate' || element === 'activityProposalToDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }

      });
      return searchObject;
    }
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam } as UrlSegment);
  }

  actionOnTableRecord(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'activity_proposal_number') {
      this.router.navigate(['view'], {
        queryParams: { activityNumber: recordData.record.Activity_Proposal_Number, filterData: JSON.stringify(this.filterData) },  
        relativeTo: this.sapRt, 
      })
    }
    if (recordData.btnAction.toLowerCase() === 'edit') {
      if(recordData.record.Edit == 'Edit'){
        this.router.navigate(['edit'], {
          queryParams: { activityNumber: recordData.record.Activity_Proposal_Number, filterData: JSON.stringify(this.filterData) },
          relativeTo: this.sapRt
        })
      }

      if(recordData.record.Edit == 'Hold'){
        this.openConfirmDialog(recordData.record.id);
      }
    }
    if (recordData.btnAction.toLowerCase() === 'approve') {
      this.router.navigate(['approval'], {
        queryParams: { activityNumber: recordData.record.Activity_Proposal_Number, filterData: JSON.stringify(this.filterData) },
        relativeTo: this.sapRt
      })
    }
  }

  private openConfirmDialog(id) {
    let message = `Do you want to keep proposal on Hold?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.matDialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
      if (result.button == "Confirm") {
        this.sapSearchPageWebService.updateStatusHold(id, result.remarkText).subscribe(reponse => {
          this.toastr.success('Record Saved Succesfully');
          this.onClickClearActivityProposal();
        });
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    confirmationData.remarkConfig = {
      source : Source.APPROVE
    }
    return confirmationData;
  }

  pageChange(event: any) {
    if (this.page !== event.page || this.size !== event.size) {
      this.page = event.page;
      this.size = event.size;
      this.searchFlag = false;
      const setItem = {
        page: this.page,
        size: this.size,
      };
      localStorage.setItem(this.key1, JSON.stringify(setItem));
      this.onClickSearchActivityProposal();
    } else {

      const retrievedValue = localStorage.getItem(this.key1); // Use this.key1 instead of 'key1'
      if (retrievedValue) {
        const parsedObject = JSON.parse(retrievedValue);
        this.page = parsedObject.page;
        this.size = parsedObject.size;
        this.searchFlag = false;
      } else {
       
      }

      // console.log('Returned to the same page and size');
    }
  
  
  }

  serviceActivityDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

}
