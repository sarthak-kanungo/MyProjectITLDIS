import { MatDialog } from '@angular/material';
import { Router, ActivatedRoute, } from '@angular/router';
import { Component, OnInit, OnDestroy, Output } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { DeliveryChallanCreateService } from './delivery-challan-create.service';
import { ToastrService } from 'ngx-toastr';
import { FormGroup } from '@angular/forms';
//import { CreateDeliveryChallan, ApprovalHierDetails } from 'DeliveryChallanCreateCancel';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { ApprovalHierDetails } from '../../../invoice-cancellation-approval/model/insurance-detail';
import { CreateDeliveryChallan } from './delivery-challan-create-cancel.domain';

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
  isSubmitDisable:boolean = false;
  @Output()
  public approvalHierDetails : ApprovalHierDetails[]

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
        this.approvalHierDetails = res['result']['approvalHier'];
        // this.dcMainForm.controls.challanCancelForm.patchValue(this.dataForEdit);
      }
    })
  }
  private convertDataToFinalPatchData() {
    this.dataForEdit['isView'] = this.isView;
    this.dataForEdit['isEdit'] = this.isEdit;
    this.dataForEdit['isCancel'] = this.isCancel;
    this.dataForEdit['isApprove'] = this.isApprove;

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

 
  private openConfirmDialog(msg): void | any {      
      let message = `Are you sure you want to ${msg}?`
      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe((result:DialogResult) => {
         if (result.button === 'Confirm') {
            this.isSubmitDisable = true;
            this.approveDc(result.remarkText, msg);
         }
      })      
  }
  private setConfirmationModalData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Cancel', 'Confirm'];
      if(this.isApprove){
          confirmationData.remarkConfig = {
              source: Source.APPROVE
          }
      }
      return confirmationData;
  }
  private approveDc(remarks:string, approvalFlag:string) {
    this.deliveryChallanCreateService.approveDc(this.recordId.toString(), remarks, approvalFlag).subscribe(res => {
      if (res) {
        this.toasterService.success(res['message'], 'Success');
        this.router.navigate([this.isApprove ? '../../' : '../'], { relativeTo: this.activatedRoute })
      }else{
        this.toasterService.error('Error generated while saving','Transaction Failed');
        this.isSubmitDisable = false;
      }
    },error=>{
      this.toasterService.error('Error generated while saving','Transaction Failed');
      this.isSubmitDisable = false;
    })
  }
  validateCancelForm(msg) {
    if (this.dcMainForm.status === 'VALID' || this.dcMainForm.status === 'DISABLED') {
      this.openConfirmDialog(msg);
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
      }else{
        this.isSubmitDisable = false;
      }
    },error=>{
      this.isSubmitDisable = false;
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
      this.deliveryChallanCreateService.printDC(this.dataForEdit['deliveryChallanNumber'], printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
             let headerContentDispostion = resp.headers.get('content-disposition');
             let fileName = headerContentDispostion.split("=")[1].trim();
             const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
             saveAs(file);
           }
        })
     }
}
