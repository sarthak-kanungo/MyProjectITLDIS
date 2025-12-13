import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { AbstractControlOptions, FormGroup } from '@angular/forms';
import { MachineTransferRequestCreateService } from '../../component/machine-transfer-request-create/machine-transfer-request-create.service';
import { CreateMachineTransferRequestService } from './create-machine-transfer-request.service';
import { TableConfig } from 'editable-table';

import { SaveMAchineTransfer, DealerMachineTransferDetail, CoDealer } from './machineTransfer.Dto';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { OperationsUtil } from 'src/app/utils/operation-util';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
@Component({
  selector: 'app-create-machine-transfer-request',
  templateUrl: './create-machine-transfer-request.component.html',
  styleUrls: ['./create-machine-transfer-request.component.scss'],
  providers: [CreateMachineTransferRequestService]
})
export class CreateMachineTransferRequestComponent implements OnInit {
  show: boolean;
  isView: boolean;
  isEdit: boolean;
  isApprove:boolean;
  sendViewResultToCreate: object
  requestnumber:string
  public createTransferForm: FormGroup;
  operation:string
  sendCoDealerIDCreate: any;
  loginId: any;
  userType:string
  approvalList:any

  constructor(public dialog: MatDialog,
    private toasterService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private loginService: LoginFormService,
    private createMachineTransferRequestService: CreateMachineTransferRequestService) {

  }


  ngOnInit() {
    this.createTransferForm = this.createMachineTransferRequestService.generateCreateTransferForm();
    this.operation = OperationsUtil.operation(this.activatedRoute);
    this.loginId = this.loginService.getLoginUserId()
    this.userType = this.loginService.getLoginUserType();
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('requestNumber')) {
        this.isView = true;
        this.getMachineTransferForView(atob(param.get('requestNumber')));
        this.requestnumber = atob(param.get('requestNumber'));
        if(this.operation === 'approve'){
          this.isApprove = true;
        }
      }
    });

  }

  getMachineTransferForView(id) {
    this.createMachineTransferRequestService.getViewData(id).subscribe(res => {
      this.sendViewResultToCreate = res['result'];
      let dealerMachineTransferDetails = this.sendViewResultToCreate['dealerMachineTransferDetails'].map(element => {
        element = { ...element, ...element.machineMaster }
        return element
      });
      let totalQty:number = 0;
      dealerMachineTransferDetails.forEach(element => {
         const fg = this.createMachineTransferRequestService.addRow(element);
         totalQty = totalQty + parseInt(fg.get('quantity').value);
         fg.disable();
      });
      this.sendViewResultToCreate['totalQuantity'] = totalQty;
    });
  }

  getMachineTraxnsferCreateData(event) {
    this.sendCoDealerIDCreate = event
  }

  clearrForm() {
    this.createTransferForm.controls.itemDetails.reset()
    this.createTransferForm.controls.transferForm.reset()
  }
  exitForm() {
    this.router.navigate(['../../'], {
      relativeTo: this.activatedRoute
    })
  }

  submitData() {
    let saveMAchineTransfe = {} as SaveMAchineTransfer

    saveMAchineTransfe.coDealer = {} as CoDealer
    saveMAchineTransfe.coDealer.id = this.sendCoDealerIDCreate;
    saveMAchineTransfe.totalQty = this.createTransferForm.controls.transferForm.get('totalQuantity').value;

    saveMAchineTransfe.dealerMachineTransferDetails = [] as DealerMachineTransferDetail[]
    this.createTransferForm.controls.itemDetails.value.forEach(element => {
      
      let valueForterms = {} as DealerMachineTransferDetail
      valueForterms.machineMaster = {} as CoDealer
      valueForterms.machineMaster.id = element.id
      valueForterms.quantity = element.quantity
      saveMAchineTransfe.dealerMachineTransferDetails.push(valueForterms)
    });

    this.createMachineTransferRequestService.saveMAchineTransfer(saveMAchineTransfe).subscribe(res => {
      if (this.isEdit) {
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        this.toasterService.success('Machine Transfer saved successfully !', 'Success')

        return;
      }
      this.toasterService.success('Machine Transfer saved successfully !', 'Success')

      this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Machine Transfer Request?';
    if (this.isEdit) {
      message = 'Do you want to update Machine Transfer Request?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
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
    if(this.isApprove){
      confirmationData.remarkConfig = {
          source: Source.APPROVE
      }
  }
    return confirmationData;
  }




  validateForm() {
    this.markFormAsTouched();
    if (this.createTransferForm.valid) {
      this.openConfirmDialog();
    }
  }



  private markFormAsTouched() {
    this.createTransferForm.markAllAsTouched();
  }

  approveReject(approvalType){
    this.openApprovalConfirmationModal(approvalType)
  }

  private openApprovalConfirmationModal(approvalType){
    let message = `Are you sure you want to ${approvalType}?`
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
       if (result.button === 'Confirm') {
         this.createMachineTransferRequestService.rejectOrApprove({'remarks':result.remarkText, 'requestNo':this.requestnumber, 'approvalType':approvalType}).subscribe(res => {
           this.toasterService.success(res['result']);
           this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
         });
       }
    })
  }
}
