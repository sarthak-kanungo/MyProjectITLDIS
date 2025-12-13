import { Component, OnInit, Input } from '@angular/core';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { FormGroup, FormArray } from '@angular/forms';
import { PscWebService } from './psc-web.service';
import { AutoChasisNumber } from '../../domain/psc-domain';
import { PscPagePresenter } from '../psc-page/psc-page-presenter';
import { Operation } from '../../../../../utils/operation-util';
import { Observable } from 'rxjs';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-psc',
  templateUrl: './psc.component.html',
  styleUrls: ['./psc.component.scss'],
  providers: [PscWebService]
})
export class PscComponent implements OnInit {

  isView: boolean
  isEdit: boolean
  @Input() pscDetailsForm: FormGroup
  chassisNoList$: Observable<Array<AutoChasisNumber>>
 
  constructor(
    private pscWebService: PscWebService,
    private pscPagePresenter: PscPagePresenter,
    public dialog: MatDialog,
  ) {
  }

  ngOnInit() {
    this.getSystemDate()
    this.getChassisNoList()
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.pscPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.pscPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
  }

  getSystemDate() {
    if (this.pscPagePresenter.operation === Operation.CREATE) {
      this.pscWebService.getSystemGeneratedDate().subscribe(response => {
        const pscDate = response['result']
        this.pscDetailsForm.get('pscDate').patchValue(new Date(pscDate))
      })
    }
  }

  private getChassisNoList() {
    if (this.pscPagePresenter.operation === Operation.CREATE) {
      this.pscDetailsForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
          this.autoChassisNo(chassisNo)
        }
        if (valueChange !== null) {
          this.pscPagePresenter.setErrorForChassisNo()
        }
      })
    }
  }

  autoChassisNo(chassisNo: string){
    this.chassisNoList$ = this.pscWebService.getChassisNo(chassisNo)
  }

  selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
    let control = this.pscPagePresenter.pscCheckListTableRow.get('dataTable') as FormArray
    control.clear();
    /*this.pscWebService.getDetailsByChassisNo(event.option.value.code).subscribe(response => {
      this.pscDetailsForm.patchValue(response)
    })*/
    this.pscDetailsForm.get('engineNo').patchValue(event.option.value.engine_no);
    this.pscDetailsForm.get('model').patchValue(event.option.value.model);
    this.pscWebService.getAllCheckpoints(event.option.value.code).subscribe(response => {
      this.pscPagePresenter.spans = []
      response.forEach(data => {
        this.pscPagePresenter.addRow(data)
        SameRowMearge.rowSpan('aggregate', d => d.aggregate, response)
        this.pscPagePresenter.spans = SameRowMearge.returnSpansArray()
      })
    })
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.pscDetailsForm.get('chassisNo').setErrors(null);
    }
  }

  displayFnChassisNo(chassisNumber: AutoChasisNumber) {
    return chassisNumber ? chassisNumber.code : undefined
  }

  onKeyDownChasisNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.pscPagePresenter.resetForChasssisNo()

    }
  }

  private openConfirmDialog(): void | any {
    let message = `PSC already exist in draft. Click Proceed to Update.`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result == 'Proceed') {
        // this.router.navigate(['../edit', this.mrcIdForEdit], { relativeTo: this.activatedRoute });
      }
      if (result == 'Clear') {
    
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