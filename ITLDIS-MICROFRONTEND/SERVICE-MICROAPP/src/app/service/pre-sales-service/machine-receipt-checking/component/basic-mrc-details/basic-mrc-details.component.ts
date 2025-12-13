import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { Observable } from 'rxjs';
import { KaiInvoiceNumber, ChassisNumber } from '../../domain/machine-receipt-checking.domain';
import { MrcCreateWebService } from './mcr-create-web.service';
import { MachineReceiptCheckingPresenter } from '../mrc-details-page/mrc-page.presenter';
import { MrcCreatePageStore } from '../mrc-details-page/mrc-create-page.store';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
@Component({
  selector: 'app-basic-mrc-details',
  templateUrl: './basic-mrc-details.component.html',
  styleUrls: ['./basic-mrc-details.component.scss'],
  providers: [MrcCreateWebService, MrcCreatePageStore]
})
export class BasicMrcDetailsComponent implements OnInit {
  @Input() basicMrcDetails: FormGroup;
  kaiInvoiceNoList$: Observable<Array<KaiInvoiceNumber>>;
  chassisNoList: Array<ChassisNumber>;
  @Output() chassisSelectEvent = new EventEmitter();
  mrcIdForEdit: string
  accpacInvoiceId: string
  isEdit: boolean
  isView: boolean
  isCreate: boolean
  constructor(
    private presenter: MachineReceiptCheckingPresenter,
    private mrcCreateWebService: MrcCreateWebService,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }
  ngOnInit() {
    // this.basicMrcDetails = this.presenter.basicMrcDetails as FormGroup;
    // console.log(" this.basicMrcDetails  ", this.basicMrcDetails);
    this.presenter.operation = OperationsUtil.operation(this.activateRoute);
    this.ViewOrEditOrCreate()

  }
  private ViewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true
    } else if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.valueChangesForAutoComplete();
      this.getDateFromServer();
    }
  }
  private getDateFromServer() {
    this.mrcCreateWebService.getSystemGeneratedDate().subscribe(dateRes => {
      // console.log("dateRes ", dateRes);
      if (dateRes.result) {
        this.basicMrcDetails.get('mrcDate').patchValue(new Date(dateRes.result));
      }
    });
  }
  valueChangesForAutoComplete() {

    this.basicMrcDetails.get('kaiInvoiceNo').valueChanges.subscribe(value => {
      
      if (value) {
        this.accpacInvoiceId = value.id
        const invoiceNumber = typeof value == 'object' ? value.invoiceNumber : value;
        
        this.basicMrcDetails.get('transporterName').patchValue(value.transports);
        this.basicMrcDetails.get('lrNo').patchValue(value.lrNumber);
        this.basicMrcDetails.get('lrDate').patchValue(new Date(value.lrDate));
        this.autoKaiInvoiceNo(invoiceNumber);
        const invoiceNumberId = value.id;
        
        if (invoiceNumberId != null && invoiceNumberId != undefined) {
          this.autoKaiChassisNo(invoiceNumberId);
        }
      }
      if (value !== null) {
        this.presenter.kaiInvoiceNoSetValidation();
      }
    });
    this.basicMrcDetails.get('chassisNo').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        if (value.id && this.accpacInvoiceId) {
          this.mrcCreateWebService.getCheckChassisNumber(value.id, this.accpacInvoiceId).subscribe(res => {
            console.log("res ", res);
            this.mrcIdForEdit = res['result'].id
            if (res['message'] == 'Service MRC already save in draft mode') {
              this.openConfirmDialog()
            }
          })
        }
      }
      if (value !== null) {
        this.presenter.chassisNoSetValidation();
      }
      if (value) {
        console.log('machinModel-->',value.model)
        console.log('engineNo-->',value.engineNumber)
        this.basicMrcDetails.get('machineModel').patchValue(value.model);
        this.basicMrcDetails.get('engineNo').patchValue(value.engineNumber);
        this.basicMrcDetails.get('PendingMRC').patchValue(value.PendingMRC);
        this.basicMrcDetails.get('CompletedMRC').patchValue(value.CompletedMRC);
      }
    });
  }
  autoKaiInvoiceNo(invoiceNumber: any) {
    this.kaiInvoiceNoList$ = this.mrcCreateWebService.getKaiInvoiceNumber(invoiceNumber);
  }
  kaiInvoiceNoSelect(event: MatAutocompleteSelectedEvent) {
    // console.log("event ", event);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.basicMrcDetails.get('kaiInvoiceNo').setErrors(null);
    }
    this.basicMrcDetails.get('chassisNo').reset();
    this.basicMrcDetails.get('machineModel').reset();
    this.basicMrcDetails.get('engineNo').reset();
    this.chassisNoList = [];
  }
  chassisNoSelect(event: MatAutocompleteSelectedEvent) {
    // console.log("event ", event);
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.basicMrcDetails.get('chassisNo').setErrors(null);
    }
    let control = this.presenter.checkListTableRow.get('dataTable') as FormArray
    control.clear();
    this.chassisSelectEvent.emit(event);
  }
  displayFnKaiInvoiceNo(invoiceNum: KaiInvoiceNumber) {
    return invoiceNum ? invoiceNum.invoiceNumber : undefined;
  }
  autoKaiChassisNo(chassisNumber: string) {
    this.mrcCreateWebService.getChassisNumber(chassisNumber).subscribe(value => {
      console.log("value ", value);
      this.chassisNoList = value.result
      if (value.message === 'MRC Already Saved In Draft Mode') {
        console.log(" in massage ");
      } else {
        console.log(" in else ");
      }
    });
    console.log(" this.chassisNoList$ ", this.chassisNoList);
  }
  displayFnChassisNo(chassisNum: ChassisNumber) {
    return chassisNum ? chassisNum.chassisNumber : undefined;
  }
  onKeyDownForKai(event: KeyboardEvent) {
    console.log("event ", event);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.chassisNoList = [];
      this.presenter.basicMrcDetails.reset();
      this.presenter.toResetCheckPointDetails();
      this.presenter.toResetItemDetails();
      this.getDateFromServer();
    }
  }
  onKeyDown(event: KeyboardEvent) {
    console.log("event ", event);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.presenter.toResetCheckPointDetails();
    }
  }
  private openConfirmDialog(): void | any {
    let message = `MRC already exist in draft. Click Proceed to Update.`;
    const confirmationData = this.setConfirmationModalData(message);
    //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
      disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result == 'Proceed') {
        this.router.navigate(['../edit', this.mrcIdForEdit], { relativeTo: this.activatedRoute });
      }
      if (result == 'Clear') {
        this.basicMrcDetails.reset();
        this.chassisNoList = [];
        this.presenter.toResetCheckPointDetails();
      }
      if (result == undefined) {
        this.basicMrcDetails.reset();
        this.chassisNoList = [];
        this.presenter.toResetCheckPointDetails();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Proceed', 'Clear'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
}
