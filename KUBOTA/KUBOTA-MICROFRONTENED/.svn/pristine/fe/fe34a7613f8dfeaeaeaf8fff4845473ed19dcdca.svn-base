import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormArray } from '@angular/forms';
import { PdiPresenter } from '../pdi-page/pre-delivery-inspection-presenter';
import { PdiWebService } from './pdi-web.service';
import { ChasisNumberData, Autocomplete } from '../../domain/pdi-domain';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-pdi',
  templateUrl: './pdi.component.html',
  styleUrls: ['./pdi.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }, PdiPresenter, PdiWebService
  ]
})
export class PdiComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;

  @Output() sendData = new EventEmitter<string>()
  @Input() pdiDetailsForm: FormGroup;
  kaiChassisNoList: Array<Autocomplete>;
  dataFromChasisNumber: ChasisNumberData
  loginDealerId: number;
  isShowPdiNo: boolean;
  dialogMsg: string
  id: any;

  constructor(
    public dialog: MatDialog,
    private presenter: PdiPresenter,
    private pdiWebService: PdiWebService,
    private activateRoute: ActivatedRoute,
    private router: Router,

  ) { }

  ngOnInit() {
    this.chassisNoChanges()
    this.getOperationType()
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)

  }
  getOperationType() {
    const operationType = this.activateRoute.snapshot.routeConfig.path.split('/')[0]
    if (operationType == 'edit') {
      this.isShowPdiNo = true
    }
    if (operationType == 'view') {
      this.isShowPdiNo = true
    }
    if (operationType == 'create') {
      this.isShowPdiNo = false
      this.getSystemDate()
    }
  }
  getSystemDate() {
    this.pdiWebService.getSystemDate().subscribe(res => {
      this.presenter.basicDetails.get('pdiDate').patchValue(res['result'])
    })
  }

  private chassisNoChanges() {
    this.pdiDetailsForm.get('chassisNo').valueChanges.subscribe(changedValue => {
      if (changedValue !== null) {
        this.pdiDetailsForm.get('chassisNo').setErrors({
          selectFromList: 'Please select from list',
        });
      }
      this.pdiWebService.searchByChassisNumber(changedValue).subscribe(res => {
        this.kaiChassisNoList = res;
      })
    })
  }
  selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.pdiDetailsForm.get('chassisNo').setErrors(null);
    }
    const control = <FormArray>this.presenter.tableData.get('table');
    control.clear();

    this.pdiWebService.getDataFromChasisNumber(event.option.value.code).subscribe(res => {
      // this.dialogMsg = res['message']
      // console.log('res333333333333333333333333333333', res)
      if (res['draftFlag']) {
        this.id = res['id']
        this.openConfirmDialog()

      } else {

        this.presenter.setValidationForCheckBox()
        this.dataFromChasisNumber = res
        if (this.dataFromChasisNumber.model) {
          this.sendData.emit(this.dataFromChasisNumber.chassisNo)
          this.presenter.basicDetails.get('machineModel').patchValue(this.dataFromChasisNumber.model)
        }
        this.presenter.basicDetails.get('kaiInvoiceNo').patchValue(this.dataFromChasisNumber.invoiceNumber)
        this.presenter.basicDetails.get('engineNo').patchValue(this.dataFromChasisNumber.engineNo)
        this.presenter.basicDetails.get('dmsgrnNo').patchValue(this.dataFromChasisNumber.dmsGrnNumber)
        this.presenter.basicDetails.get('dmsgrnDate').patchValue(this.dataFromChasisNumber.grnDate)
      }
    })
  }
  private openConfirmDialog(): void | any {
    const message = `Chassis Number is Already in draft mode`
    const confirmationData = this.setConfirmationModalData(message)
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Continue') {
        this.router.navigate(['../edit'], {
          relativeTo: this.activateRoute,
          queryParams: { id: this.id },
        })
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData
    confirmationData.buttonAction = [] as Array<ButtonAction>
    confirmationData.title = 'Confirmation'
    confirmationData.message = message
    confirmationData.buttonName = ['Cancel', 'Continue']
    return confirmationData
  }
  onKeyDownChasisNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.presenter.basicDetails.reset()
      const control = <FormArray>this.presenter.tableData.get('table');
      control.clear();

    }
  }

  displayFnChassisNo(chassisNo: Autocomplete) {
    return chassisNo ? chassisNo.code : undefined
  }
}
