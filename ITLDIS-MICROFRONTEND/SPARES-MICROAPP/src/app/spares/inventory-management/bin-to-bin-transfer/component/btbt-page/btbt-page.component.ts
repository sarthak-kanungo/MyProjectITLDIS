import { Component, OnInit } from '@angular/core';
import { BtBtPagePresenter } from './btbt-page.presenter';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormArray} from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { DateService } from '../../../../../root-service/date.service';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { BtbtPageWebService } from './btbt-page-web.service';
import { SaveBtBt } from '../../domain/bin-to-bin-transfer';
import { BtBtPageStore } from './btbt-page.store';
import { reverse } from 'dns';

@Component({
  selector: 'app-btbt-page',
  templateUrl: './btbt-page.component.html',
  styleUrls: ['./btbt-page.component.css'],
  providers: [BtBtPagePresenter, BtBtPageStore, BtbtPageWebService]
})
export class BtbtPageComponent implements OnInit {
  btBtForm: FormGroup
  isView: boolean
  isEdit: boolean
  isCreate: boolean
  dialogMsg: string;
  isDraft: boolean;
  isSubmitDisable: boolean = false;
  constructor(
    private presenter: BtBtPagePresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
    private btBtPageWebService: BtbtPageWebService
  ) {

  }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.btBtForm = this.presenter.btBtForm
    this.viewOrEditOrCreate();
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      
      this.isView = true
      this.isEdit = false
    } else if (this.presenter.operation === Operation.EDIT) {
      
      this.isView = false
      this.isEdit = true
      this.isCreate = true
    } else if (this.presenter.operation === Operation.CREATE) {
      
      this.isCreate = true
    }
  }
  submitData() {
    this.btBtPageWebService.postBtBtDetails(this.buildJsonForSave()).subscribe(response => {
      let displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate([this.isEdit ? '../../' : '../'], { relativeTo: this.activateRoute });
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving', 'Transaction failed');
    })
  }
  
  buildJsonForSave() {
    const btBtRowValue = this.btBtForm.getRawValue()
    
    let saveAndSubmitBtBt = {} as SaveBtBt;
    saveAndSubmitBtBt = { ...{'itemDetails':btBtRowValue.itemDetailsTable.itemDetailsRowData} };
    let flag:boolean = true;
    saveAndSubmitBtBt.itemDetails.forEach(item => {
        /*if(item.toLocation == null){
            flag = false;
            return false;
        }*/
        if(typeof item.toLocation !== 'object'){
            item.toLocation = {value:item.toLocation,id:null};
        }
    });
    if(flag)
        return saveAndSubmitBtBt;
    else
        return null;
  }
 
  saveAndSubmitForm(isSave?: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.validateForm();
  }
  validateForm() {
    let itemList = this.btBtForm.get('itemDetailsTable').get('itemDetailsRowData') as FormArray;
    
    if (this.btBtForm.get('itemDetailsTable').valid && itemList && itemList.length>0) {
      this.presenter.markFormAsTouched();
      this.openConfirmDialog()
    } else {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Details?`;
    if (this.isEdit) {
      message = 'Do you want to update Details?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
    
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
}
