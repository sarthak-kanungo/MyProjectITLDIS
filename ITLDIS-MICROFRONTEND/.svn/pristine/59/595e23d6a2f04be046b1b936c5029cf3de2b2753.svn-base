import { Component, OnInit } from '@angular/core';
import { SourceSearchPageStore } from './source-search-page.store';
import { SourceSearchPagePresenter } from './source-search-page-presenter';
import { FormGroup } from '@angular/forms';
import { DataTable, ColumnSearch, NgswSearchTableService, ActionOnTableRecord } from 'ngsw-search-table';
import { FilterSourceSearch } from '../../domain/source-domain';
import { SourceSearchPageWebService } from './source-search-page-web.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-source-search-page',
  templateUrl: './source-search-page.component.html',
  styleUrls: ['./source-search-page.component.scss'],
  providers: [SourceSearchPageStore, SourceSearchPagePresenter, SourceSearchPageWebService]
})
export class SourceSearchPageComponent implements OnInit {

  searchForm: FormGroup
  sourceSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  activeStatusNg: any
  createdDateNg: any
  lastModifiedDateNg: any
  purposeNg: any
  sourceCodeNg: any
  sourceNameNg: any
  clearSearchRow = new BehaviorSubject<string>("");

  constructor(
    private sourceSearchPagePresenter: SourceSearchPagePresenter,
    private tableDataService: NgswSearchTableService,
    private sourceSearchPageWebService: SourceSearchPageWebService,
    public dialog: MatDialog,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchForm = this.sourceSearchPagePresenter.sourceSearchForm
    this.sourceSearchForm = this.sourceSearchPagePresenter.sourceSearchDetailsForm
    this.onClickSearchSourceDetails()
  }

  onClickSearchSourceDetails() {
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
    this.sourceSearchPageWebService.searchEnquiryMaster(this.buildObjForSearchSource()).subscribe(response => {
      this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
      this.totalTableElements = response['count']
    })
  }

  private buildObjForSearchSource() {
    const filtObj = {} as FilterSourceSearch
    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.sourceCode = this.sourceSearchForm.value.sourceCode ? this.sourceSearchForm.value.sourceCode.sourceCode : null
    filtObj.sourceName = this.sourceSearchForm.value.sourceName ? this.sourceSearchForm.value.sourceName.source : null
    filtObj.purpose = this.sourceSearchForm.value.purpose ? this.sourceSearchForm.value.purpose.purpose : null
    return filtObj
  }
  tableClear() {
    this.activeStatusNg = ""
    this.createdDateNg = ""
    this.lastModifiedDateNg = ""
    this.purposeNg = ""
    this.sourceCodeNg = ""
    this.sourceNameNg = ""
  }
  onClickClearSourceDetails() {
    this.sourceSearchForm.reset()
    this.onClickSearchSourceDetails()
    this.clearSearchRow.next("");
    this.tableClear()
  }

  actionOnTableRecord(recordData: any) {
    console.log("recordData ", recordData);
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    }
  }

  serviceSourceDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  pageChange(event) {
    this.page = event.page
    this.size = event.size
    this.searchFlag = false;
    this.onClickSearchSourceDetails()
  }

  private openConfirmDialog(emitedId: ActionOnTableRecord): void | any {
    let message = 'Are you sure you want to change status?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.changeActiveStatus(emitedId);
      }
    });
  }
  private changeActiveStatus(emitedId: ActionOnTableRecord) {
    this.sourceSearchPageWebService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
      this.toastr.success('Active status changed successfully!', 'Success', {
        timeOut: 1500
      });
    }, err => {
      this.toastr.error('Changing active status of record is failed!');
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

}
