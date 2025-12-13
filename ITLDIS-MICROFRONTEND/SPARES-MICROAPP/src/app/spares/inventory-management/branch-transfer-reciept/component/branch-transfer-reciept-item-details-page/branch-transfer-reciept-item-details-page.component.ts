import { Component, Input, OnInit } from '@angular/core';
import { recieptPresenter } from '../branch-transfer-reciept-page/branch-transfer-reciept-presenter';
import { FormGroup } from '@angular/forms';
import { SelectList } from 'src/app/core/model/select-list.model';
import { SparesGrnApiService } from 'src/app/spares/spares-purchase/spares-grn/component/spares-grn/spares-grn-api.service';
import { Observable } from 'rxjs';
import { BtbtPageWebService } from '../../../bin-to-bin-transfer/component/btbt-page/btbt-page-web.service';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-branch-transfer-reciept-item-details-page',
  templateUrl: './branch-transfer-reciept-item-details-page.component.html',
  styleUrls: ['./branch-transfer-reciept-item-details-page.component.scss'],
  providers: [SparesGrnApiService, BtbtPageWebService]
})
export class BranchTransferRecieptItemDetailsPageComponent implements OnInit {
  itemDetailsArray: any
   @Input() totalValue
  isView:any
  isEdit:any
  isCreate:any
  @Input() editViewFinalAmount
  storeList$: Observable<SelectList[]>;
  fromLocations$: Observable<SelectList[]>;
  constructor(
    private presenter: recieptPresenter,
    private sparesGrnApiService: SparesGrnApiService,
    private btbtPageWebService: BtbtPageWebService,
    private dialog: MatDialog,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    
    this.presenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
      this.itemDetailsArray = res;
    })
    this.getStoresName()
    this.checkForOperation()

  }
  displayFnFromLocation(fromLocation: SelectList) {
    if (typeof fromLocation === 'string') {
      return fromLocation;
    }
    return fromLocation ? fromLocation.value : undefined
  }
  private getStoresName() {
    this.storeList$ = this.sparesGrnApiService.getStoresName('BIN2BIN');
  }

  private checkForOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      // this.branchTransferIssueForm.disable();


    } else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true;
      // this.branchTransferIndentForm.disable();


    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true;
      // this.branchTransferIndentForm.disable();


    }
  }

  storeChange(event:any) {
    this.storeValueChanges()
  }

  public storeValueChanges() {
    let storeId: number;
    let itemNo: string;
    if (this.presenter.itemDetailsArray.getRawValue()) {
      (this.presenter.itemDetailsArray.controls as FormGroup[]).forEach(fg => {
      
        fg.controls.storeMaster.valueChanges.subscribe(val => {
          fg.controls.binLocationMaster.reset();
          itemNo = fg.controls.itemNo.value
          storeId = val.id
          this.fromLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, '', itemNo, '');

        })
      })
    }
  }

  binLocationFormate(event:any) {
    // debugger
    if (this.presenter.itemDetailsArray.getRawValue()) {
      (this.presenter.itemDetailsArray.controls as FormGroup[]).forEach(fg => {
        let val = fg.controls.binLocationMaster.value;
        if (val && typeof val !== 'object') {
          const pattern = /^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]/;
          let binValue = this.presenter.itemDetailsArray.getRawValue();
          binValue.forEach(element => {
            if (!pattern.test(element.binLocationMaster)) {
              fg.get('binLocationMaster').setErrors({
                binError: 'Location should be in Pattern A00-00-00'
              })
            }
          });
        }
        if (fg.get('binLocationMaster').status == 'VALID') {
          this.openConfirmDialog(fg);
        }
      })
    }


  }


  private openConfirmDialog(fg: FormGroup): void | any {
    let message = `Do you want to create new location?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {

      } else {
        fg.controls.binLocation.reset();
      }
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
  optionSelectedFromBin(event:any){

  }

}
