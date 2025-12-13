import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from 'src/app/confirmation-box/confirmation-box.component';
import { DialogResult } from 'src/app/confirmation-box/confirmation-data';
import { CustomValidators } from 'src/app/utils/custom-validators';
import { WcrPartsStatusService } from './wcr-parts-status.service';

@Component({
  selector: 'app-wcr-parts-status',
  templateUrl: './wcr-parts-status.component.html',
  styleUrls: ['./wcr-parts-status.component.css'],
  providers: [
    WcrPartsStatusService
  ]
})
export class WcrPartsStatusComponent implements OnInit {

  isEdit: boolean;
  isCreate: boolean
  isView: boolean;
  data: Object;
  disable = true;
  total: number;
  wcrStatusForm: FormGroup;
  allChecked:boolean=false;
  finalstatus
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private toast: ToastrService,
    private wcrPartsStatusService: WcrPartsStatusService
  ) { }

  ngOnInit() {
    this.createWcrForm();
    this.fetchWcrForStatusChange();

    this.wcrPartsStatusService.searchFinalStatus().subscribe(res => {
      this.finalstatus = res;
    });

  }

  fetchWcrForStatusChange(){
    (this.wcrStatusForm.controls.wcr as FormArray).clear();
    this.wcrPartsStatusService.getWcrForStatusChange().subscribe(response => {
        if(response!=null && response['result']!=null){
          response['result'].forEach(element => {
            this.addWcr(element);
          });
        }
    });
  }
  createWcrForm() {
    this.wcrStatusForm = this.fb.group({
      wcr: new FormArray([])
    });
  }

  addWcr(data){
    let FG  = this.fb.group({
      isSelect:[null],
      id:[null],
      wcrNo: [{ value: '', disabled: true }],
      wcrDate: [{ value: '', disabled: true }],
      wcrType: [{ value: '', disabled: true }],
      status: [{ value: '', disabled: true }],
      dealerCode: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      workshopAddress: [{ value: '', disabled: true }],
      dealerMobileNo: [{ value: '', disabled: true }],
      jobCardNo: [{ value: '', disabled: true }],
      jobCardDate: [{ value: '', disabled: true }],
      goodwillNo: [{ value: '', disabled: true }],
      goodwillDate: [{value:'', disabled:false}],
      pcrNo: [{ value: '', disabled: true }],
      pcrDate: [{ value: '', disabled: true }],
      installationDate: [{ value: '', disabled: true }],
      dateOfFailure: [{ value: '', disabled: true }],
      dateOfRepair: [{ value: '', disabled: true }],
      customerName: [{ value: '', disabled: true }],
      customerAddress: [{ value: '', disabled: true }],
      customerMobileNo: [{ value: '', disabled: true }],
      model: [{ value: '', disabled: true }],
      chassisNo: [{ value: '', disabled: true }],
      engineNo: [{ value: '', disabled: true }],
      soldToDealer: [{value:'', disabled:false}],
      hour : [{value:'', disabled:false}],
      failureType: [{ value: '', disabled: true }],
      rcnNumber: [{ value: '', disabled: true }],
      rcnDate: [{value:'', disabled:false}],
      lastApprovedBy : [{value:'', disabled:false}]
    });
    FG.patchValue(data);
    (this.wcrStatusForm.controls.wcr as FormArray).push(FG);
  }
  
  validateForm() {
      let flag = false;
      (this.wcrStatusForm.controls.wcr as FormArray).controls.forEach(element => {
         
        if(element.get('isSelect').value==true){
          flag = true;
        }
      });
      if(!flag){
        this.toast.error("Please check Claim for status change");
        return false;
      }
    
    this.openConfirmDialog();
    
  }

  submitData(result: DialogResult) {
    let data = (this.wcrStatusForm.controls.wcr as FormArray).getRawValue();
    data.forEach(d=>{
      d.status = result.wcrStatus;
    })
    
    this.wcrPartsStatusService.updateStatus(data).subscribe(res => {
      this.toast.success(res['message'], 'Updated');
      this.fetchWcrForStatusChange();
    }, err => {
      this.toast.error('Status Change failed','Transaction Error');
    })
  
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to update status of Selected WCRs?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });

    dialogRef.afterClosed().subscribe((result:DialogResult) => {
      if (result) {
        if (result.button === 'Confirm') {
          this.submitData(result);
        }
      }
    });
  }

  checkAll(event){
    this.allChecked = !this.allChecked;
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Wcr Status Selection';
    confirmationData.message = message;
    confirmationData.buttonType = 'WCR_STATUS';
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    confirmationData.wcrStatus = this.finalstatus;
    return confirmationData;
  }
}
