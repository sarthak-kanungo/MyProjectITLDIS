import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { ChassisNo, CheckpointListByModel } from '../../domain/pdc-domain';
import { PdcPagePresenter } from '../pdc-page/pdc-page-presenter';
import { PreDeliveryCheckWebService } from './pre-delivery-check-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pre-delivery-check',
  templateUrl: './pre-delivery-check.component.html',
  styleUrls: ['./pre-delivery-check.component.scss'],
  providers: [
    PreDeliveryCheckWebService
  ]
})
export class PreDeliveryCheckComponent implements OnInit {

  isView: boolean
  isEdit: boolean
  @Input() pdcDetailsForm: FormGroup;
  chassisNoList$: Observable<Array<ChassisNo>>
  dialogMsg: string
  id: number
  @Output() checkPoint = new EventEmitter<Array<CheckpointListByModel>>()

  constructor(
    private pdcPagePresenter: PdcPagePresenter,
    private preDeliveryCheckWebService: PreDeliveryCheckWebService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private pdcRt: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.viewOrEditOrCreate()
    this.getSystemDate()
    this.getChassisNoList()
  }

  private viewOrEditOrCreate() {
    if (this.pdcPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.pdcPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
  }

  getSystemDate() {
    if (this.pdcPagePresenter.operation === Operation.CREATE) {
      this.preDeliveryCheckWebService.getSystemGeneratedDate().subscribe(response => {
        const pdcDate = response['result']
        this.pdcDetailsForm.get('pdcDate').patchValue(new Date(pdcDate))
      })
    }
  }

  private getChassisNoList() {
    if (this.pdcPagePresenter.operation === Operation.CREATE) {
      this.pdcDetailsForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const chassisNo = typeof valueChange == 'object' ? valueChange.chassisNo : valueChange
          this.autoChassisNo(chassisNo)
        }
        if (valueChange !== null && typeof valueChange !== 'object') {
          this.pdcPagePresenter.setErrorForChassisNo()
        }
      })
    }
  }

  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.preDeliveryCheckWebService.getChassisNumberAutoComplete(chassisNo)
  }

  selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
    this.pdcPagePresenter.resetCheckPoint()
    this.preDeliveryCheckWebService.getChassisDetailsByChassisNo(event.option.value.chassisNo).subscribe(response => {
      this.dialogMsg = response.message
      if (response.result.draftFlag === true) {
        this.id = response.result.id
        this.openConfirmDialog()
      }else {
        this.pdcDetailsForm.patchValue(response.result)
        this.patchValueForChecklist(event.option.value.chassisNo)
      }
    })
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.pdcDetailsForm.get('chassisNo').setErrors(null);
    }
  }

  patchValueForChecklist(model: string) {
    this.preDeliveryCheckWebService.getAggregateAndCheckPointByModel(model).subscribe(response => {
      console.log(response)  
      this.checkPoint.emit(response)
    })
  }

  onKeyDownChasisNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.pdcPagePresenter.resetForChasssisNo()
      this.pdcPagePresenter.resetCheckPoint()
    }
  }

  displayFnChassisNo(chassisNumber: ChassisNo) {
    return chassisNumber ? chassisNumber.chassisNo : undefined
  }

  private openConfirmDialog(): void | any {
    let message = `${this.dialogMsg}. Click Proceed to Update.`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result == 'Proceed') {
        this.router.navigate(['../edit', this.id], { relativeTo: this.pdcRt });
      }
      if (result == 'Clear') {
        this.pdcDetailsForm.get('chassisNo').reset()
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Proceed', 'Clear'];
    return confirmationData;
  }

}