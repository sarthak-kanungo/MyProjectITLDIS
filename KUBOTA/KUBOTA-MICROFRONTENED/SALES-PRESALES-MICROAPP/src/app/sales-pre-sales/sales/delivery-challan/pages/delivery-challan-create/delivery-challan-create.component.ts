import { MatDialog } from '@angular/material';
import { Router, ActivatedRoute, } from '@angular/router';
import { Component, OnInit, OnDestroy, Output } from '@angular/core';

import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { DeliveryChallanCreateService } from './delivery-challan-create.service';
import { ToastrService } from 'ngx-toastr';
import { FormGroup } from '@angular/forms';
import { CreateDeliveryChallan } from 'DeliveryChallanCreate';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-delivery-challan-create',
  templateUrl: './delivery-challan-create.component.html',
  styleUrls: ['./delivery-challan-create.component.scss']
})
export class DeliveryChallanCreateComponent implements OnInit, OnDestroy {
  public isDealerTransferDc: boolean;
  public dataForEdit: object;
  public isCancel: boolean = false;
  public isEdit: boolean = false;
  @Output() public isView: boolean = false;
  public isApprove: boolean = false;
  public deliverableCheckList = [];
  public dcMainForm: FormGroup;
  public recordId: any;
  isSubmitDisable: boolean = false;
  isCancelDisable: boolean = false;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toasterService: ToastrService,
    private activatedRoute: ActivatedRoute,
    private deliveryChallanCreateService: DeliveryChallanCreateService
  ) {
    this.deliveryChallanCreateService.deliveryChallanMainData = {} as CreateDeliveryChallan;
    this.deliveryChallanCreateService.deliveryChallanMainData.isImplementsFormValid = true;
  }

  ngOnInit() {
    this.dcMainForm = this.deliveryChallanCreateService.createForm();
    this.activatedRoute.paramMap.subscribe(paramsMap => {
      if (paramsMap && paramsMap['params']) {
        if (paramsMap['params']['viewId']) {
          this.isView = true;
          this.dcMainForm.disable();
          this.recordId = atob(paramsMap['params']['viewId'])
          this.getDcDetailsFromId(atob(paramsMap['params']['viewId']));
        } else if (paramsMap['params']['editId']) {
          this.isEdit = true;
          this.recordId = atob(paramsMap['params']['editId']);
          this.getDcDetailsFromId(atob(paramsMap['params']['editId']));
        } else if (paramsMap['params']['cancelId']) {
          this.isCancel = true;
          this.dcMainForm.disable();
          this.dcMainForm.controls.challanCancelForm.enable();
          this.recordId = atob(paramsMap['params']['cancelId']);
          this.getDcDetailsFromId(atob(paramsMap['params']['cancelId']));
        } else if (paramsMap['params']['approveId']) {
          this.isApprove = true;
          this.dcMainForm.disable();
          this.recordId = atob(paramsMap['params']['approveId']);
          this.getDcDetailsFromId(atob(paramsMap['params']['approveId']));
        }
      }
    })
  }
  private getDcDetailsFromId(dcId: string) {
    this.deliveryChallanCreateService.getDcDetailsFromId(dcId).subscribe(res => {
      if (res) {
        let dcDetailFromId = res['result']['deliveryChallanEditResponse'];
        this.deliverableCheckList = res['result']['deliveryChallanEditResponse']['deliverableChecklist'];
        this.dataForEdit = { ...{ ...res['result']['deliveryChallanEditResponse'], ...res['result']['allotmentResponse'] } }
        this.convertDataToFinalPatchData();
        this.dcMainForm.controls.deliveryChallan.patchValue(this.dataForEdit);
        this.dcMainForm.controls.implementsForm.patchValue(this.dataForEdit);
        this.dcMainForm.controls.prospectForm.patchValue(this.dataForEdit);
        this.dcMainForm.controls.insuranceForm.patchValue(this.dataForEdit);
        this.dcMainForm.controls.challanCancelForm.patchValue(this.dataForEdit);
      }
    })
  }
  private convertDataToFinalPatchData() {
    console.log('this.dataForEdit',this.dataForEdit);
    this.dataForEdit['isView'] = this.isView;
    this.dataForEdit['isEdit'] = this.isEdit;
    this.dataForEdit['isCancel'] = this.isCancel;
    this.dataForEdit['isApprove'] = this.isApprove;
    if(this.dataForEdit['dealerMachineTransfer']){
      this.dataForEdit['requestNo']=this.dataForEdit['dealerMachineTransfer']['requestNumber'];
      this.isDealerTransferDc = true;

      this.dataForEdit['dealerCode']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['dealerCode'];
      this.dataForEdit['dealerName']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['dealerName'];
      this.dataForEdit['address1']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingAddress1'];
      this.dataForEdit['address2']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingAddress2'];
      this.dataForEdit['address3']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingAddress3'];
      this.dataForEdit['pinCode']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingPincode'];
      this.dataForEdit['postOffice']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingPostOffice'];
      this.dataForEdit['tehsil']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingTehsil'];
      this.dataForEdit['city']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingCity'];
      this.dataForEdit['district']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingDistrict'];
      this.dataForEdit['state']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['billingState'];
      this.dataForEdit['gstInNumber']=this.dataForEdit['dealerMachineTransfer']['dealerMaster']['gstNo'];
    }
    let machineDetailsConsolidated = [];
    if (this.dataForEdit['dcMachineDetail'].length > 0) {
      this.dataForEdit['dcMachineDetail'].forEach(element => {
        let oldMachine = { ...element }
        machineDetailsConsolidated.push({ ...{ ...oldMachine, ...oldMachine.machineInventory, ...oldMachine.machineInventory.machineMaster } })
      });
    }
    
    this.dataForEdit['machineDetailsConsolidated'] = machineDetailsConsolidated;
  }
  dcTypeChanges(event) {
    if (event && event.value) {
      this.isDealerTransferDc = event.value.toLowerCase() === 'dealer transfer' ? true : false;
      // this.deliveryChallanCreateService.deliveryChallanMainData.isInsuranceFormValid = this.isDealerTransferDc;
    }
  }
  validateForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouched('mark');
    if (this.deliveryChallanCreateService.deliveryChallanMainData.dcMachineDetailFirst && this.deliveryChallanCreateService.deliveryChallanMainData.dcMachineDetailFirst.length > 0) {
      if (
        this.deliveryChallanCreateService.deliveryChallanMainData.isDcFormValid &&
        this.deliveryChallanCreateService.deliveryChallanMainData.isImplementsFormValid &&
        this.deliveryChallanCreateService.deliveryChallanMainData.isProspectFormValid
        // this.deliveryChallanCreateService.deliveryChallanMainData.isInsuranceFormValid
      ) {
        this.openConfirmDialog();
      } else {
        this.toasterService.error('Please fill all mandatory fields');
      }
    } else {
      this.toasterService.error('DC cannot be created without machine details');
    }

  }

  submitData() {
    // Service is having some logic to convert into final object
    let toasterMessage = 'Delivery challan created successfully !';
    if (this.isEdit) {
      this.deliveryChallanCreateService.deliveryChallanMainData.deliveryChallanId = this.recordId;
      toasterMessage = 'Delivery challan updated successfully !';
    }
    this.deliveryChallanCreateService.saveDeliveryChallan().subscribe(res => {
      if (res && res['result']) {
        this.toasterService.success(toasterMessage, 'Success');
        this.router.navigate([this.isEdit ? '../../' : '../'], { relativeTo: this.activatedRoute })
      }else{
        this.toasterService.error(res['message'], 'Error');
        this.isSubmitDisable = false;
      }
    }, err => {
      this.toasterService.error('Somthing goes wrong.', 'Error');
      this.isSubmitDisable = false;
    })
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Delivery Challan?';
    if (this.isEdit) {
      message = 'Do you want to update Delivery Challan?';
    }
    if (this.isCancel) {
      message = 'Do you want to cancel this Delivery Challan?';
    }
    if (this.isApprove) {
      message = 'Do you want to approve this Delivery Challan?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        if (this.isCancel) {
          this.isCancelDisable = true;
          this.cancelDc();
        } else if (this.isApprove) {
          this.approveDc();
        } else {
          this.isSubmitDisable = true;
          this.submitData();
        }
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
  private approveDc() {
    this.deliveryChallanCreateService.approveDc(this.recordId.toString()).subscribe(res => {
      if (res) {
        this.toasterService.success('DC approved successfully', 'Success');
        this.router.navigate([this.isApprove ? '../../' : '../'], { relativeTo: this.activatedRoute })
      }
    })
  }
  validateCancelForm() {
    if (this.dcMainForm.status === 'VALID' || this.dcMainForm.status === 'DISABLED') {
      this.openConfirmDialog();
    } else {
      this.dcMainForm.controls.challanCancelForm.markAllAsTouched();
    }
  }
  private cancelDc() {
    let cancelFormValue = this.dcMainForm.controls.challanCancelForm.value;
    cancelFormValue['id'] = this.recordId;
    this.deliveryChallanCreateService.cancelDc(cancelFormValue).subscribe(res => {
      if (res) {
        this.toasterService.success('DC Cancelled successfully', 'Success');
        this.router.navigate([this.isCancel ? '../../' : '../'], { relativeTo: this.activatedRoute })
      }
    }, err => {
      this.toasterService.error('Somthing goes wrong.', 'Error');
      this.isCancelDisable = false;
    })
  }
  clearOrMarkAsTouched(key: string) {
    this.deliveryChallanCreateService.clearOrMarkAsTouched(key)
  }
  ngOnDestroy() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.next(null);
    this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.next(null);
  }
  
  printGatepass(printStatus){
    this.deliveryChallanCreateService.printGatepass(this.dataForEdit['gatePassNumber'], printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
    })
  }
  
  printDC(printStatus){
   this.deliveryChallanCreateService.printDC(this.dataForEdit['deliveryChallanNumber'],this.dataForEdit['gatePassNumber'], printStatus).subscribe((resp: HttpResponse<Blob>) => {
     if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
     })
  }
  
}
