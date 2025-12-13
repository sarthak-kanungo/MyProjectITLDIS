import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { TransporterSearchPagePresenter } from './transporter-search-page-presenter';
import { NgswSearchTableService, DataTable, ColumnSearch, ActionOnTableRecord } from 'ngsw-search-table';
import { TransporterSearchPageWebService } from './transporter-search-page-web.service';
import { FilterTransporterSearch } from '../../domain/transporter-domain';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { TransporterSearchPageStore } from './transporter-search-page.store';

@Component({
  selector: 'app-transporter-search-page',
  templateUrl: './transporter-search-page.component.html',
  styleUrls: ['./transporter-search-page.component.scss'],
  providers: [TransporterSearchPagePresenter, TransporterSearchPageStore, TransporterSearchPageWebService, NgswSearchTableService]
})
export class TransporterSearchPageComponent implements OnInit {

  searchForm: FormGroup
  transporterSearchForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10

  constructor(
    private transporterSearchPagePresenter: TransporterSearchPagePresenter,
    private tableDataService: NgswSearchTableService,
    private transporterSearchPageWebService: TransporterSearchPageWebService,
    public dialog: MatDialog,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchForm = this.transporterSearchPagePresenter.transporterSearchForm
    this.transporterSearchForm = this.transporterSearchPagePresenter.transporterSearchDetailsForm
    this.onClickSearchTransporterDetails()
  }

  onClickSearchTransporterDetails() {
    this.transporterSearchPageWebService.searchTransporterMaster(this.buildObjForSearchTransporter()).subscribe(response => {
      this.dataTable = this.tableDataService.convertIntoDataTable(response['result'])
      this.totalTableElements = response['count']
    })
  }

  private buildObjForSearchTransporter() {
    const filtObj = {} as FilterTransporterSearch
    filtObj.page = this.page
    filtObj.size = this.size
    filtObj.transporterCode = this.transporterSearchForm.value.transporterCode ? this.transporterSearchForm.value.transporterCode.transporterCode : null
    filtObj.transporterName = this.transporterSearchForm.value.transporterName ? this.transporterSearchForm.value.transporterName.transporter : null
    return filtObj
  }

  onClickClearTransporterDetails() {
    this.transporterSearchForm.reset()
    this.onClickSearchTransporterDetails()
  }

  actionOnTableRecord(recordData: any) {
    console.log("recordData ", recordData);
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    }
  }

  serviceTransporterDateChanges(searchDate: string, ColumnKey: string) {
    this.searchValue = new ColumnSearch(searchDate, ColumnKey);
  }

  pageChange(event) {
    this.page = event.page
    this.size = event.size
    this.onClickSearchTransporterDetails()
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
    this.transporterSearchPageWebService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
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
