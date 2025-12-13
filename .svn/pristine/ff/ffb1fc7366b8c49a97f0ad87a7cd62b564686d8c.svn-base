import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-create-branch-transfer-receipt',
  templateUrl: './create-branch-transfer-receipt.component.html',
  styleUrls: ['./create-branch-transfer-receipt.component.scss']
})
export class CreateBranchTransferReceiptComponent implements OnInit {

  branchTransferReceiptForm: FormGroup;
 
  isView: boolean;
  data: Object;
  disable = true
  branches: string[] = [
    'Branch 1', 'Branch 2'
  ];
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.createbranchTransferReceiptForm()
  }

  createbranchTransferReceiptForm() {
    this.branchTransferReceiptForm = this.fb.group({
      branchtransferissueno: ['', Validators.compose([])],
      receiptno: ['', Validators.compose([])],
      issuingbranch: ['', Validators.compose([])],
      branchtransferreceiptdate: [{ value: '', disabled: true }, Validators.compose([])],
      receivedby: [{ value: '', disabled: true }, Validators.compose([])],
      receivingbranch: [{ value: '', disabled: true }, Validators.compose([])],
    })
  }

  
}