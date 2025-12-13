import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { indentPresenter } from './branch-indent-presenter';
import { indentStore } from './branch-indent-store';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { BranchIndentServiceService } from '../../service/branch-indent-service.service';
import { MatDialog } from '@angular/material';
import { ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { PartData, submitForm, warrantyHotlinePartReport } from '../domain/domaim';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-branch-transfer-page',
  templateUrl: './branch-transfer-page.component.html',
  styleUrls: ['./branch-transfer-page.component.css'],
  providers: [indentStore, indentPresenter]

})
export class BranchTransferPageComponent implements OnInit {
  branchTransferIndentForm: FormGroup

  indentItemForm: FormArray
  isView: any
  status: any
  loginUser: any
  date: any
  branchName: any
  subBranchData: any
  reqFromBranch: any

  private btnName: String
  isEdit: any; item: any;
  headerId: any
  itemId: any;
  isCreate: any

  requestNumber: any
  reqToBranchId: any;
  editReqToBranch: any;
  editId: any;
  constructor(
    private store: indentStore,
    private presenter: indentPresenter,
    private activatedRoute: ActivatedRoute,
    private location: Location,
    private toaster: ToastrService,
    private localStorage: LocalStorageService,
    private service: BranchIndentServiceService,
    public dialog: MatDialog,
    private router: Router
  ) {

  }
  ngOnInit() {
    this.branchTransferIndentForm = this.presenter.indentForm
    this.indentItemForm = this.presenter.itemDetailsForm;
    this.checkFormOperation()
    this.requestBy()
    this.getSystemDate()
    this.getReqToBranchDeatilsById()
  }


  private viewIndentReport(indentNo) {
    this.service.viewBTIndentByReqNo(indentNo).subscribe((res: any) => {
   
     this.editId=res.reqToBranch.id?res.reqToBranch.id:null
      if (res) {
        this.branchTransferIndentForm.patchValue({
          remarks: res.remarks,
          requestNumber: res.reqNo,
          status: res.status,
          reqToBranch:res.reqToBranch.branchName
        })
      }
      this.headerId = res.id
      this.requestNumber = res.reqNo;
      this.collectPartDetails(res.indentItems);
    })


  }

  private collectPartDetails(partDetails: warrantyHotlinePartReport[]) {
    const partDetail = {} as PartData;
    partDetails.forEach(eltData => {
      partDetail.id = eltData.id ? eltData.id : null;
      partDetail.sparePartMaster = eltData.sparePartMaster ? eltData.sparePartMaster : null;
      partDetail.itemDescription = eltData.sparePartMaster.itemDescription ? eltData.sparePartMaster.itemDescription : null;
      partDetail.reqBranchStockQty = eltData.reqBranchStockQty ? eltData.reqBranchStockQty : null;
      partDetail.supBranchStockQty = eltData.supBranchStockQty ? eltData.supBranchStockQty : null;
      partDetail.reqQty = eltData.reqQty ? eltData.reqQty : null;
      this.presenter.addRowInItemDetails(partDetail);
    })


  }

  private requestBy() {
    this.loginUser = this.localStorage.getLoginUser();
    this.branchTransferIndentForm && this.branchTransferIndentForm.get('reqBy').patchValue(this.loginUser.name);
    this.branchTransferIndentForm && this.branchTransferIndentForm.get('reqById').patchValue(this.loginUser.id);

  }
  private getSystemDate() {
    this.service.getSystemDateUrl().subscribe(res => {
      this.date = res;
      this.branchTransferIndentForm.get('requestDate').patchValue(this.date)

    })
  }
  private getReqToBranchDeatilsById() {
    this.service.getReqToBranchDeatilsById().subscribe(data => {
      this.branchName = data['result']

      this.branchTransferIndentForm.get('reqFromBranch').patchValue(this.branchName.branchName)
      this.branchTransferIndentForm.get('partReceivedById').patchValue(this.branchName.id);
    })
  }

  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.indentItemForm.disable();
     
      this.activeRoute();
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
     
    }

  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewIndentReport(qParam.indentNo);
    });
  }

  clearForm() {
    this.branchTransferIndentForm.reset()
    this.indentItemForm.reset()
    this.presenter.itemDetailsForm.clear()
    this.getSystemDate();
    this.getReqToBranchDeatilsById();
    this.requestBy()
  }
  exitForm() {
    this.location.back();
  }
  submitIndentForm(btnType: String) {
    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'save';
    this.status = 'Submitted';
    this.validateForm();
  }

  branchID: any
  branchId(list) {
    this.branchID = list
  }
  validateForm() {
    let flag: boolean = false;
    if (this.branchTransferIndentForm.invalid) {
      this.branchTransferIndentForm.markAllAsTouched()
      this.toaster.error("Please fill all mandatory fields!")
      return false

    }
   
    this.indentItemForm.controls.forEach((ele: any) => {

      if (ele.status == "INVALID") {
        ele.markAllAsTouched()
        this.toaster.error('Please fill all required field!')
        flag = true
        return false;
      }
    })
    if (flag) {
      return false;
    }
    this.openConfirmDialog();
  }
  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Branch Transfer Indent?`

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {

        this.submitIndentFormData()

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

  private editPayload() {
    if (this.isEdit) {
      const allDataFromTable = this.presenter.itemDetailsForm.getRawValue()
      // console.log(allDataFromTable,'table')
      const arryForIteration = allDataFromTable
      let sparePartsDetailsSend = []
      const allPartsTogether = []
      arryForIteration.forEach(element => {
            
        allPartsTogether.push({
          sparePartMaster: {
             id: element.sparePartMaster.id,
            itemNo: element.sparePartMaster.itemNo ? element.sparePartMaster.itemNo : null,

          },
          reqQty: parseInt(element.reqQty),
          reqBranchStockQty: element.reqBranchStockQty,
          supBranchStockQty: element.supBranchStockQty,
          id: element.id,
          itemDescription: element.itemDescription,
          isSelect: element.isSelect
        })
      })
      sparePartsDetailsSend = [...allPartsTogether]

      return sparePartsDetailsSend ? sparePartsDetailsSend : null
    }
  }
  private getSpareParts() {
    const allDataFromTable = this.indentItemForm.getRawValue()
    const arryForIteration = allDataFromTable
    let sparePartsDetailsSend = []
    const allPartsTogether = []
    arryForIteration.forEach(element => {
      allPartsTogether.push({
        sparePartMaster: {
          id: element.sparePartMaster.id ? element.sparePartMaster.id : null,
           itemNo:element.sparePartMaster.itemNo?element.sparePartMaster.itemNo:null,
        },
        reqQty: parseInt(element.reqQty),
        reqBranchStockQty: element.reqBranchStockQty,
        supBranchStockQty: element.supBranchStockQty,
         id:element.id,
        itemDescription: element.itemDescription,
        isSelect: element.isSelect
      })
    })
    sparePartsDetailsSend = [...allPartsTogether]
    return sparePartsDetailsSend ? sparePartsDetailsSend : null
  }

  submitIndentFormData() {
    let submitIndentForm = {} as submitForm;

    submitIndentForm = this.branchTransferIndentForm.getRawValue()
   
   submitIndentForm['reqToBranch']
    if (this.btnName == 'save')
    submitIndentForm.draftFlag = true;

    else
    submitIndentForm.draftFlag = false;
    if (this.isEdit) {
      submitIndentForm.id = this.headerId
    } else
    submitIndentForm.id = null

    submitIndentForm['reqFromBranch'] = { 'id': submitIndentForm['partReceivedById'] };
    submitIndentForm['reqBy'] = { 'id': submitIndentForm['reqById'] }


    if (this.isEdit) {
      submitIndentForm['reqToBranch'] = {'id':this.editReqToBranch?this.editReqToBranch:this.editId}
      //  submitIndentForm['reqToBranch'] = { 'id': this.reqToBranchIds,'branchName': this.reqToBranchName};
      submitIndentForm.indentItems = this.editPayload()
    } else {
      submitIndentForm.indentItems = this.getSpareParts()
    
      
    }
    
    delete submitIndentForm.reqById
    delete submitIndentForm.partReceivedById
    this.service.saveBTIndent((submitIndentForm)).subscribe(res => {
      if (res) {
        if (this.isEdit) {
          this.toaster.success('Branch Indent Updated Successfully', 'Success');
          this.indentItemForm.reset();
          this.branchTransferIndentForm.reset();
          this.router.navigate(['../'], { relativeTo: this.activatedRoute });
          return;
        }
        this.toaster.success('Branch Indent Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      } else {
        // this.isSubmitDisable = false;
        this.toaster.error('Error generated while saving', 'Transaction failed')
      }
    })



  }

  // Code for pdf download
  printStatus: any
  downloadPdf() {
    this.printStatus = 'true';
    this.service.viewPdf(this.headerId, this.requestNumber, this.printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  editBranchId(event){
    this.editReqToBranch=event
  }

}
