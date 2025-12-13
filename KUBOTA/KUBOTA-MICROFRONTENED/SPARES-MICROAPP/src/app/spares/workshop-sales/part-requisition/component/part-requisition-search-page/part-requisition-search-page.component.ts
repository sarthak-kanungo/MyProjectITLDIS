import { Component, OnInit, AfterViewInit } from '@angular/core';
import { DataTable, ColumnSearch, TableHeading, NgswSearchTableService } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { PartRequisitionSearchPageApiService } from './part-requisition-search-page-api.service';
import { SparePartRequisitionSearchFilter } from '../../domain/part-requisition.domain';
import { PartRequisitionSearchPagePresenter } from './part-requisition-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { ObjectUtil } from '../../../../../utils/object-util';
// import { NgswSearchTableService, DataTable, ColumnSearch, TableHeading } from '../../../../../../../projects/ngsw-search-table/src/public-api';
import { DateService } from '../../../../../root-service/date.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-part-requisition-search-page',
  templateUrl: './part-requisition-search-page.component.html',
  styleUrls: ['./part-requisition-search-page.component.scss'],
  providers: [PartRequisitionSearchPageApiService, PartRequisitionSearchPagePresenter]
})
export class PartRequisitionSearchPageComponent implements OnInit, AfterViewInit {

  dataTable: DataTable;
  totalTableElements: number;
  actionButtons
  searchValue: ColumnSearch;
  clickOnTableFields: Array<TableHeading> = [{ title: 'requisitionNo', isClickable: true }, { title: 'edit', isClickable: true }];
  partRequisitionSearchForm: FormGroup;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  clearSearchRow = new BehaviorSubject<string>("");
  requisitionNoNgModel: any = "";
  requisitionPurposeNgModel: any = "";
  requestedByNgModel: any = "";
  requisitionDateNgModel: any = "";
  jobCardNoNgModel: any = "";
  jobCardDateNgModel: any = "";
  searchFilterValues: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  key = 'partRsp';
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dateService: DateService,
    private ngswSearchTableService: NgswSearchTableService,
    private partRequisitionSearchPageApiService: PartRequisitionSearchPageApiService,
    private partRequisitionSearchPagePresenter: PartRequisitionSearchPagePresenter
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key);
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.partRequisitionSearchForm = this.partRequisitionSearchPagePresenter.partRequisitionSearchForm;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.partRequisitionSearchForm != null) {
      this.partRequisitionSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    if (this.partRequisitionSearchForm.get('jobCardNo').value == null && this.partRequisitionSearchForm.get('requisitionNo').value == null && this.partRequisitionSearchForm.get('requisitionPurpose').value == null && this.partRequisitionSearchForm.get('fromDate').value == null && this.partRequisitionSearchForm.get('toDate').value == null) {
      this.maxDate = this.today
      let backDate = new Date();
      backDate.setMonth(this.today.getMonth() - 1);
      this.minDate = backDate;
      this.partRequisitionSearchForm.get('fromDate').patchValue(backDate);
      this.partRequisitionSearchForm.get('toDate').patchValue(new Date());
      this.searchPartRequisition();
    }
    else {
      localStorage.getItem(this.key)
      this.searchPartRequisition();
    }
  }
  ngAfterViewInit(): void {

  }
  pageChangeOfSearchTable(event) {
    console.log('event: ', event);
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    // this.partRequisitionSearchForm.patchValue(event);
    this.searchPartRequisition();
  }
  actionOnTableRecord(recordData) {
    let id = btoa(recordData.record.id)
    if (recordData.btnAction === 'requisitionNo') {
      this.router.navigate(['view/', id], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'edit') {
      this.router.navigate(['update/', id], { relativeTo: this.activatedRoute })
    }
  }

  // etSearchDateValueChange(searchDate, ColumnKey) {
  //   console.log('searchDate: ', searchDate);
  //   const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
  //   console.log('modifiedDate: ', modifiedDate);
  //   this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  // }

  etSearchDateValueChange(searchDate, ColumnKey) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
    console.log("selected Date_after: ", this.searchValue);
  }
  columnLengthChanges(tableColumn: string) {
    this.partRequisitionSearchForm.patchValue({ tableColumn });
    this.searchPartRequisition();
  }
  searchPartRequisition() {
    this.cleartable()

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
    const searchPartRequisition: SparePartRequisitionSearchFilter = this.partRequisitionSearchForm.getRawValue();

    searchPartRequisition.page = this.page
    searchPartRequisition.size = this.size
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.partRequisitionSearchForm.value))
    ObjectUtil.removeNulls(searchPartRequisition);
    if (searchPartRequisition['jobCardNo'] != null || searchPartRequisition['requisitionNo'] != null || searchPartRequisition['requisitionPurpose'] != null || searchPartRequisition['fromDate'] != null || searchPartRequisition['toDate'] != null) {
      this.partRequisitionSearchPageApiService.searchPartRequisition(searchPartRequisition).subscribe(searchRes => {
        this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes.result);
        this.totalTableElements = searchRes.count;
      })
    }
    this.searchFlag = true;
  }

  resetAllSearchValue() {
    this.cleartable()
    this.dataTable = null
    localStorage.removeItem(this.key)
  }
  cleartable() {
    this.requisitionNoNgModel = "";
    this.requisitionPurposeNgModel = "";
    this.requestedByNgModel = "";
    this.requisitionDateNgModel = "";
    this.jobCardNoNgModel = "";
    this.jobCardDateNgModel = "";
  }
}
