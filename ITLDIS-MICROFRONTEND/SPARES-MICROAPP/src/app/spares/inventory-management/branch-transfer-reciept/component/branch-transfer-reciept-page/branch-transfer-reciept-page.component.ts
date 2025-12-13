import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { recieptPresenter } from './branch-transfer-reciept-presenter';

import { Location } from '@angular/common';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { recieptStore } from './branch-transfer-reciept-store';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { BranchTransferRecieptService } from '../../service/branch-transfer-reciept.service';
import { ToastrService } from 'ngx-toastr';
import { ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { PartIssueItem, partRecieptForm } from './branch-transfer-reciept-create-domain';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-branch-transfer-reciept-page',
  templateUrl: './branch-transfer-reciept-page.component.html',
  styleUrls: ['./branch-transfer-reciept-page.component.scss'],
  providers: [recieptStore, recieptPresenter]
})
export class BranchTransferRecieptPageComponent implements OnInit {
  branchTransferRecieptForm: FormGroup
  @Input() totalSum
  isView: any;
  isEdit: any;
  loginUser: any;
  systemDate: any
  private btnName: string;
  draftFlag: any
  partIssue: any
  editViewFinalAmount: number;
  headerId: any;
  recieptNo: any;
  transferIssueId: any;
  issuingBranchId: any;
  totalValue: any;
  constructor(
    private strore: recieptStore,
    private presenter: recieptPresenter,
    private location: Location,
    private activatedRoute: ActivatedRoute,
    private localStorage: LocalStorageService,
    private service: BranchTransferRecieptService,
    private toaster: ToastrService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {

    this.branchTransferRecieptForm = this.presenter.recieptForm;
    this.checkFormOperation()
    this.getLoginUser()
    this.getSystemDates()
    this.getRecieveBranch()
  }
  private getLoginUser() {
    this.loginUser = this.localStorage.getLoginUser()
    this.branchTransferRecieptForm.get('receivedBy').patchValue(this.loginUser.name)
    this.branchTransferRecieptForm.get('receivedById').patchValue(this.loginUser.id)
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.activeRoute()
      this.branchTransferRecieptForm.disable()

    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
      this.branchTransferRecieptForm.disable()

    }

  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewBranchTransferReciept(qParam.receiptNo);
    });
  }
  private viewBranchTransferReciept(receiptNo) {
    this.service.getRecieptView(receiptNo).subscribe(res => {
      this.headerId = res.id ? res.id : null
      this.recieptNo = res.receiptNo ? res.receiptNo : null
      this.branchTransferRecieptForm.get('transferIssue').patchValue(res.transferIssue.issueNo)
      this.branchTransferRecieptForm.get('issueBranch').patchValue(res['issuingBranchId'].branchName)
      this.branchTransferRecieptForm.get('recieptNo').patchValue(res.receiptNo);

      this.transferIssueId = res.transferIssue.id ? res.transferIssue.id : null;
      this.issuingBranchId = res.issuingBranchId.id ? res.issuingBranchId.id : null
      if (res && this.isView || this.isEdit) {
        let totSum: number[] = [];
        let i = 0;
        res['receiptItems'].forEach(recieptItem => {
          this.presenter.addRow(recieptItem);
          let num = 0
          const data = num + recieptItem.receiptMrpValue
          totSum[i] = data;
          i++;
        })
        let sum = 0;
        for (let j = 0; j < totSum.length; j++) {
          sum += totSum[j];

        }
        this.editViewFinalAmount = sum

      }
    })

  }
  private getSystemDates() {
    this.service.getSystemDate().subscribe(date => {
      this.systemDate = date
      this.branchTransferRecieptForm.get('recieptDate').patchValue(this.systemDate.result);

    })
  }
  private getRecieveBranch() {
    this.service.getReceivingBranch().subscribe(recievData => {
      this.branchTransferRecieptForm.get('recieveBranch').patchValue(recievData['result'].branchName)
      this.branchTransferRecieptForm.get('receivingBranchId').patchValue(recievData['result'].id)
    })
  }
  clearForm() {
    this.branchTransferRecieptForm.reset();
    this.getSystemDates()
    this.getLoginUser()
    this.getRecieveBranch()
  }
  exitForm() {
    this.location.back()
  }
  submitIndentForm() {

  }

  submitRecieptForm(btnType: string) {
    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'save';
    if (this.btnName === 'submit') {
      this.draftFlag = 'false'
    } else {
      this.draftFlag = 'true'
    }


    this.validateForm();
  }
  validateForm() {
    let flag: boolean = false;
  
    if (this.branchTransferRecieptForm.invalid) {
      this.branchTransferRecieptForm.markAllAsTouched()
      return false
    }
    if(this.presenter.itemDetailsArray.length===0){
      
        this.toaster.error("Part Details Not Found")
        return false
    }
    if (this.presenter.itemDetailsArray) {
      (this.presenter.itemDetailsArray.controls as FormGroup[]).forEach(fg => {
        // console.log(fg['controls'].binLocationMaster, '12233n storeMaster binLocationMaster')
         if (fg['controls'].storeMaster.value === null || fg['controls'].storeMaster.value === undefined) {
          this.toaster.error("Please Fill All Required filed")
          flag = true
          return false
        } else if (fg['controls'].binLocationMaster.value === null || fg['controls'].binLocationMaster.value === '' || fg['controls'].binLocationMaster.value === undefined) {
          this.toaster.error("Please Fill All Required filed")
          flag = true
          return false
        }
      })
    }
    if (flag) {
      return false;
    }
    this.openConfirmDialog();
  }
  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Branch Transfer Issue?`

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {

        this.submitRecieptFormData()

      }

    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  submitRecieptFormData() {
    const partIssueRow = this.presenter.RecieptFormAll.getRawValue() as partRecieptForm;

    let items = partIssueRow.itemDetails as PartIssueItem[];
    items.forEach(items => {
      if (typeof items.binLocationMaster !== 'object') {
        items.binLocationMaster = { id: null, 'binLocation': items.binLocationMaster }
      }

    })
    if (typeof partIssueRow.formReciept.transferIssue !== 'object') {
      
      partIssueRow.formReciept.transferIssue = { 'id': this.transferIssueId, 'issueNo': partIssueRow.formReciept.transferIssue }
    }
    this.partIssue = {
      ...partIssueRow.formReciept,
      receiptItems: items,
      draftFlag: this.draftFlag
    }
    if (this.isEdit) {
      partIssueRow.formReciept.issuingBranchId = this.issuingBranchId
      this.partIssue['id'] = this.headerId
    }
    this.partIssue['receivedBy'] = { 'id': partIssueRow.formReciept['receivedById'] };
    this.partIssue['receivingBranchId'] = { 'id': partIssueRow.formReciept['receivingBranchId'] }
    this.partIssue['issuingBranchId'] = { 'id': partIssueRow.formReciept['issuingBranchId'] }
    delete this.partIssue.receivedById
    delete this.partIssue.issueBranch
    delete this.partIssue.recieveBranch
    this.service.saveBTReciept(this.partIssue).subscribe(res => {
      let displayMsg = res['message']
      if (res) {
        this.toaster.success(displayMsg, 'Success')
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        this.branchTransferRecieptForm.reset();
        this.presenter.itemDetailsArray.clear();
        // this.toaster.success("Data Save Successfully");
      } else {
        this.toaster.error(displayMsg, 'Error')
      }
    })
  }


  printStatus: any
  downloadRecieptPdf() {
    this.printStatus = 'false';
    this.service.viewPdf(this.headerId, this.recieptNo, this.printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  totalValueCalculate(event){
    this.totalValue=event
  }
}
