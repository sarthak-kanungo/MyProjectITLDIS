import { Component, OnInit } from '@angular/core';
import { issueStore } from './branch-transfer-store';
import { issuePresenter } from './branch-transfer-presenter';
import { AbstractControl, FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { BranchTransferIssueServiceService } from '../../service/branch-transfer-issue-service.service';
import { PartIssueForm, PartIssueItem } from './branch-transfer-domain';
// import { PartIssueItem } from 'src/app/spares/workshop-sales/part-issue/domain/part-issue.domain';
import { MatDialog } from '@angular/material';
import { ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-branch-transfer-issue-page',
  templateUrl: './branch-transfer-issue-page.component.html',
  styleUrls: ['./branch-transfer-issue-page.component.css'],
  providers: [issueStore, issuePresenter]
})
export class BranchTransferIssuePageComponent implements OnInit {
  branchTransferIssueForm: FormGroup
  loginUser: any
  issueToList: any
  issueBranchList: any
  systemDate: any;
  itemDetailsTable: AbstractControl;
  private btnName: String
  draftFlag: string
  isView: any
  isEdit: any;
  finalAmount: any;
  editResponse: any
  isCreate: any
  partIssue: any
  editResponse1: any
  partListData: FormGroup[];
  partIssueEdit: any
  id: any
  allDataFromTable: FormGroup[];
  toBranchId: any;
  headerId: any;
  editFinalAmount: number;
  viewfinalAmount: number;
  issueNo: any;
  editReqToBranchId: any;
  abc: any;
  editId: any;
  
  constructor(private store: issueStore,
    private presenter: issuePresenter,
    private location: Location,
    private localStorage: LocalStorageService,
    private service: BranchTransferIssueServiceService,
    public dialog: MatDialog,
    private toaster: ToastrService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    this.branchTransferIssueForm = this.presenter.issueForm
    this.getLoginUser()
    this.assignToBranch()
    this.getSystemDate()
    this.itemDetailsTable = this.presenter.partReturnItemFormArray;
    this.checkFormOperation()
    this.branchTransferIssueForm.get('reqNo').disable()
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.presenter.partReturnItemFormArray.disable();
      this.activeRoute();
      return
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
      return
      // this.indentItemForm.enable();
      // this.indentItemForm.enable();
    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true

      // this.indentItemForm.enable();
      // this.indentItemForm.enable();
    }

  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewBranchIssue(qParam.issueNo);
    });
  }


  private viewBranchIssue(indentNo) {
    this.service.getIssueNumberForView(indentNo).subscribe((res: any) => {
      console.log(res,'ressssss')
      this.editId=res.toBranchMaster.id?res.toBranchMaster.id:null
      this.issueNo=res.issueItems[0].transferIndent.reqNo;
      this.editResponse = res;
      this.headerId = this.editResponse.id
      this.branchTransferIssueForm.get('toBranchId').patchValue(this.editResponse.toBranchMaster.id)
      if (res && this.isView) {
        this.branchTransferIssueForm.patchValue({
          branchTransferIssueNo: res.issueNo ? res.issueNo : null,
          status: res.status ? res.status : null,
          toBranchMaster: res.toBranchMaster.branchName + ' | ' + res.toBranchMaster.branchCode ? res.toBranchMaster.branchName + ' | ' + res.toBranchMaster.branchCode : null,
        })
      }

      if (res && this.isEdit) {
        this.branchTransferIssueForm.patchValue({
          toBranchMaster: res.toBranchMaster.branchName ? res.toBranchMaster.branchName : null,
          branchTransferIssueNo: res.issueNo ? res.issueNo : null,
          status: res.status ? res.status : null,
        })

      }
      if (res && this.isEdit) {
        let totSum: number[] = [];
        let i = 0;
        res['issueItems'].forEach(issueItem => {
          this.presenter.addRow(issueItem);
          let num = 0
          const data = num + issueItem.itemValue
          totSum[i] = data;
          i++;
        });
        let sum = 0;
        for (let j = 0; j < totSum.length; j++) {
          sum += totSum[j];

        }
        this.editFinalAmount = sum
        

      }
      if (res && this.isView) {
        let totSum: number[] = [];
        let i = 0;
        res['issueItems'].forEach(issueItem => {
          this.presenter.addRow(issueItem);
          let num = 0
          const data = num + issueItem.itemValue
          totSum[i] = data;
          i++;
          this.branchTransferIssueForm.patchValue({
            reqNo: issueItem.transferIndent.reqNo,

          })

        });
        let sum = 0;
        for (let j = 0; j < totSum.length; j++) {
          sum += totSum[j];

        }
        this.viewfinalAmount = sum
      }


    })
  }

  private getLoginUser() {
    this.loginUser = this.localStorage.getLoginUser();
    this.branchTransferIssueForm && this.branchTransferIssueForm.get('employeeMaster').patchValue(this.loginUser.name);
    this.branchTransferIssueForm && this.branchTransferIssueForm.get('employeeId').patchValue(this.loginUser.id);
  }

  private getSystemDate() {
    this.service.getSystemDate().subscribe(date => {
      this.systemDate = date
      this.branchTransferIssueForm.get('issueDate').patchValue(this.systemDate.result);

    })
  }

  private assignToBranch() {
    this.service.getIssueingBranch().subscribe(issueBranch => {
      this.issueBranchList = issueBranch['result']
     

      this.branchTransferIssueForm.get('byBranchMasterId').patchValue(this.issueBranchList.id)
      this.branchTransferIssueForm.get('byBranchMaster').patchValue(this.issueBranchList.branchName + ' ' + '|' + ' ' + this.issueBranchList.branchCode)
    })
  }
  submitIssueForm(btnType: string) {
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
    if (this.branchTransferIssueForm.invalid) {
      this.branchTransferIssueForm.markAllAsTouched()
      return false
    }
    if (this.presenter.partReturnItemFormArray) {
      (this.presenter.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {
        if (fg['controls'].issuedQty.value === null || fg['controls'].issuedQty.value === undefined) {
          this.toaster.error("Please Fill All Required Filed")
          flag = true
          return false
        } else if (fg['controls'].storeMaster.value === null || fg['controls'].storeMaster.value === undefined) {
          this.toaster.error("Please Fill All Required Filed")
          flag = true
          return false
        } else if (fg['controls'].binLocationMaster.status === "INVALID" || fg['controls'].binLocationMaster.status === "INVALID" || fg['controls'].binLocationMaster.value===null) {
          this.toaster.error("Please Fill All Required Filed")
          flag = true
          return false
        }
        else if (fg['controls'].binLocationMaster.value.id === undefined  || fg['controls'].binLocationMaster.value.value===undefined) {
          this.toaster.error("Please Fill All Required Filed")
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
       
        this.submitIssueFormData()

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


  submitIssueFormData() {

    const partIssueRow = this.presenter.issueFormAll.getRawValue() as PartIssueForm;
    let items = partIssueRow.branchIssueItem as PartIssueItem[];

    this.partIssue = {
      ...partIssueRow.formIssue,
      issueItems: items,
      draftFlag: this.draftFlag
    }

    if (this.isEdit) {
      this.partIssue['toBranchMaster'] = { 'id': this.editReqToBranchId?this.editReqToBranchId:this.editId }
      this.partIssue['id'] = this.headerId;
    }
    this.partIssue['employeeMaster'] = { 'id': partIssueRow.formIssue['employeeId'] };
    this.partIssue['byBranchMaster'] = { 'id': partIssueRow.formIssue['byBranchMasterId'] }
    {
      this.service.saveBTIssue((this.partIssue as any)).subscribe(res => {
        if (res) {
          this.toaster.success('Branch Issue Saved Successfully', 'Success');
          this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        }
      })
    }



  }

  exitForm() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.location.back()
  }
  clearForm() {
    this.branchTransferIssueForm.reset()
    this.itemDetailsTable.reset()
    this.itemDetailsTable = null
    this.presenter.finalAmount.clear()
  }

  printStatus: any
  downloadIssuePdf() {
    this.printStatus = 'false';
    this.service.viewPdf(this.headerId, this.issueNo, this.printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }
  issueToBranchId(event){
    
    this.editReqToBranchId=event
  }
}
