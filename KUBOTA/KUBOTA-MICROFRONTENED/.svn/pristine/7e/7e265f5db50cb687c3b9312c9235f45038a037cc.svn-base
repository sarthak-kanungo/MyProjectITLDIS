import { AfterViewInit, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ComplaintOrQueryResolutionSearchPagePresenter } from './complaint-or-query-resolution-search-page.presenter';
import { ComplaintOrQueryResolutionSearchPageStore } from './complaint-or-query-resolution-search-page.store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ComplaintOrQueryResolutionService } from '../../service/complaint-or-query-resolution.service';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { IFrameMessageSource, IFrameService, UrlSegment } from 'src/app/root-service/iFrame.service';
import { MatDialog } from '@angular/material';
import { ComplaintResolutionPopupComponent } from '../complaint-resolution-popup/complaint-resolution-popup.component';

@Component({
  selector: 'app-complaint-or-query-resolution-search-page',
  templateUrl: './complaint-or-query-resolution-search-page.component.html',
  styleUrls: ['./complaint-or-query-resolution-search-page.component.css'],
  providers: [ComplaintOrQueryResolutionSearchPageStore, ComplaintOrQueryResolutionSearchPagePresenter]
})
export class ComplaintOrQueryResolutionSearchPageComponent implements OnInit, AfterViewInit {

  searchForm: FormGroup;
  searchcomplaintOrQueryResolutionForm: FormGroup
  isAdvanceSearch: boolean
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  public filterData: object
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilterValues: any;
  searchFilter;
  complaintId:any
  complaintType:any

  constructor(private pagePresenter: ComplaintOrQueryResolutionSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
    private service: ComplaintOrQueryResolutionService,
    private pageStore: ComplaintOrQueryResolutionSearchPageStore,
    private dialog : MatDialog
  ) { }

  ngOnInit() {
    this.searchForm = this.pagePresenter.searchComplaintOrQueryResolutionForm
    this.searchcomplaintOrQueryResolutionForm = 
    // this.pageStore.getcomplaintOrQueryResolutionSearchForm
    this.pagePresenter.newcomplaintOrQueryResolutionFormSearchForm

    this.searchFilterValues = localStorage.getItem('searchFilter')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');

  }

  searchcomplaintOrQueryResolution() {

    let surveyName: string
    // if (this.modelSurveyMaterForm.get('surveyName').value) {
    //   surveyName=this.modelSurveyMaterForm.get('surveyName').value
    // }


    let searchFormValues = {} as any
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchForm.value))
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
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    else {
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }

    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    searchFormValues.surveyName = surveyName

    this.service.getComplaintOrQueryResolutionSearchTableData(searchFormValues).subscribe(res => {
      // res['result'].forEach(row => {
      //   row.edit = 'edit';
      // });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
  }

  tableAction(recordData) {
    if (recordData.btnAction.toLowerCase() === 'action') {
      console.log('recordData--',recordData)
      const dialogRef = this.dialog.open(ComplaintResolutionPopupComponent, {
        width: '90%',
        panelClass: 'confirmation_modal',
        data: {frt:true, art:true, complaintType:recordData.record.complaintType, complaintId:recordData.record.id},
        maxHeight: '80vh'
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result && result.data){
          this.searchcomplaintOrQueryResolution();
        }
      });
    }
  }

  initialQueryParams(event) {
    this.searchForm.get('searchcomplaintOrQueryResolutionForm').patchValue(event);
    this.page = event.page
    this.size = event.size
    console.log('initialQueryParams event: ', event)
    const searchValue = /%2F/g
    const newValue = '/'
    this.searchcomplaintOrQueryResolutionForm.patchValue(event)
    // if (event.bookingNo) {
    //   this.searchcomplaintOrQueryResolutionForm.get('bookingNo').patchValue(event.bookingNo.replace(searchValue, newValue));
    //   event.bookingNo = event.bookingNo.replace(searchValue, newValue)
    // }
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchcomplaintOrQueryResolution()
  }

  clearForm() { 
    this.searchcomplaintOrQueryResolutionForm.reset()
    this.searchcomplaintOrQueryResolution()
    this.clearSearchRow.next("");
  }

  ngAfterViewInit() {
    // this.searchcomplaintOrQueryResolution()
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate' || element === 'appointmentFromDate' || element === 'appointmentToDate')) {
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

}
