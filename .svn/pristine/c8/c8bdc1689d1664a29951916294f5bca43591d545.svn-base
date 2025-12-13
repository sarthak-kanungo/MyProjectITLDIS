import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { issuePresenter } from '../branch-transfer-issue-page/branch-transfer-presenter';
import { SparesGrnApiService } from 'src/app/spares/spares-purchase/spares-grn/component/spares-grn/spares-grn-api.service';
import { Observable } from 'rxjs';
import { SelectList } from 'src/app/core/model/select-list.model';
import { BtbtPageWebService } from '../../../bin-to-bin-transfer/component/btbt-page/btbt-page-web.service';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from 'src/app/confirmation-box/confirmation-box.component';
import { issueStore } from '../branch-transfer-issue-page/branch-transfer-store';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { TypeOf } from 'src/app/utils/check-typeof';

@Component({
  selector: 'app-branch-transfer-item-details',
  templateUrl: './branch-transfer-item-details.component.html',
  styleUrls: ['./branch-transfer-item-details.component.css'],
  providers: [SparesGrnApiService, BtbtPageWebService]
})
export class BranchTransferItemDetailsComponent implements OnInit {

  @Input() viewfinalAmount;
  @Input() editFinalAmount;
  public itemDetailsArray: Array<FormGroup> = [];

  storeList$: Observable<SelectList[]>;
  fromLocations$: Observable<SelectList[]>;
  issueQuantity: any;
  itemNo: string
  storeId: any;
  xyz: any
  isView: any
  isEdit: any
  isCreate: any;
  TotalValue: any;
  TotalValueMrp: any;
  storeList: Observable<SelectList[]>;

  constructor(
    private presenter: issuePresenter,
    private sparesGrnApiService: SparesGrnApiService,
    private btbtPageWebService: BtbtPageWebService,
    private dialog: MatDialog,
    private store: issueStore,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {



    this.presenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
      this.itemDetailsArray = res;
    })
    this.presenter.finalAmount

    this.getStoresName()
    this.checkForOperation()
    
  

  }
  optionSelectedFromBin(event: any) {

  }

  private getStoresName() {
    this.storeList$ = this.sparesGrnApiService.getStoresName('BIN2BIN');
    this.storeValueChanges()
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

  

  selectedLogic(event){
    console.log(event,'xddd')
    this.storeValueChanges()
  }


  quantity(event) {

    this.presenter.quantitycalculation()
    this.TotalValueMrp = this.presenter.finalAmount
   
    if(this.isEdit){
      this.editFinalAmount=this.TotalValueMrp
      }

  }
  displayFnFromLocation(fromLocation: SelectList) {
   
    if (typeof fromLocation === 'string') {
      return fromLocation;
    }
   
    return fromLocation ? fromLocation.value : undefined
  }

  public storeValueChanges(fg?) {

    if (this.presenter.partReturnItemFormArray.getRawValue()) {
      (this.presenter.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {
        fg.controls.storeMaster.valueChanges.subscribe(val => {
          this.itemNo = fg.controls.itemNo.value;

          this.storeId = val.id;
          fg.controls.binLocationMaster.reset()
          fg.controls.issuedQty.reset()
          fg.controls.pendingQty.reset()
          fg.controls.itemValue.reset()
          // this.presenter.finalAmount.setValue('0')
          this.fromLocations$ = this.btbtPageWebService.getToBinLocationList(this.storeId, '', this.itemNo, '');

        })
      })
    }
  }
  public getFromBinLocation(event: any) {


    this.fromLocations$ = this.btbtPageWebService.getToBinLocationList(this.storeId, '', this.itemNo, '');
    if (this.presenter.partReturnItemFormArray.getRawValue()) {
      (this.presenter.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {

      })
    }
  }

  binLocationFormate(fg: FormGroup) {
    if (this.presenter.partReturnItemFormArray.getRawValue()) {
    
      (this.presenter.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {
        let val = fg.controls.binLocationMaster.value;
          
        if (val && typeof val !== 'object') {
          // fg.controls.currentStock.reset();
          // fg.controls.transferQty.reset();
          // fg.controls.adjustmentType.reset();
          const pattern = /^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]/;
          let binValue = this.presenter.partReturnItemFormArray.getRawValue();
          
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
}
