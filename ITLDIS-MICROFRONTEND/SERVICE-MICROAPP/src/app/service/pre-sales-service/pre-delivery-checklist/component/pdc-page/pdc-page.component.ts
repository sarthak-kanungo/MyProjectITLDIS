import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PdcPageStore } from './pdc-page.store';
import { PdcPagePresenter } from './pdc-page-presenter';
import { FormGroup, AbstractControl, FormArray } from '@angular/forms';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { PdcPageWebService } from './pdc-page-web.service';
import { SaveAndSubmitPdc, CheckpointListByModel } from '../../domain/pdc-domain';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';
import { IFrameMessageSource, IFrameService } from '../../../../../root-service/iFrame.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-pdc-page',
  templateUrl: './pdc-page.component.html',
  styleUrls: ['./pdc-page.component.scss'],
  providers: [PdcPageStore, PdcPagePresenter, PdcPageWebService, IFrameService]
})
export class PdcPageComponent implements OnInit {

  pdcForm: FormGroup
  pdcDetailsForm: FormGroup
  pdcCheckPointListTable: AbstractControl
  pdcStaticTable: AbstractControl
  aggregateAndCheckPointList: CheckpointListByModel
  isChassisNoForCheckPoint: boolean
  isChassisNo: boolean
  isView: boolean
  dialogMsg: string;
  isDraft: boolean;
  id: number
  chassisId: number
  pdcCheckPoint: Array<CheckpointListByModel>
  viewPdcCheckPoint: Array<CheckpointListByModel>
  editPdcCheckPoint: Array<CheckpointListByModel>
  isSubmitDisable: boolean = false;
  constructor(
    private pdcPagePresenter: PdcPagePresenter,
    public dialog: MatDialog,
    private pdcPageWebService: PdcPageWebService,
    private toastr: ToastrService,
    private router: Router,
    private pdcRt: ActivatedRoute,
    private iFrameService: IFrameService,
  ) { }

  ngOnInit() {
    this.pdcPagePresenter.operation = OperationsUtil.operation(this.pdcRt)
    this.pdcForm = this.pdcPagePresenter.pdcForm
    this.pdcDetailsForm = this.pdcPagePresenter.detailsForm
    this.pdcCheckPointListTable = this.pdcPagePresenter.pdcCheckListTableRow
    this.pdcStaticTable = this.pdcPagePresenter.pdcStaticTable
    this.viewOrEditOrCreate()
    this.selectChassisNo()
  }

  private viewOrEditOrCreate() {
    if (this.pdcPagePresenter.operation === Operation.VIEW) {
      this.isChassisNo = false
      this.isView = true
      this.parseIdAndViewOrEditForm()
    } else if (this.pdcPagePresenter.operation === Operation.EDIT) {
      this.parseIdAndViewOrEditForm()
    } else if (this.pdcPagePresenter.operation === Operation.CREATE) {
    }
  }
  private parseIdAndViewOrEditForm() {
    this.pdcRt.params.subscribe(prms => {
      this.pdcPageWebService.getPdcById(`` + atob(prms['id'])).subscribe(response => {
        this.chassisId = response.pdcViewHeaderResponse.chassisId
        this.id = response.pdcViewHeaderResponse.pdcId
        if (response.pdcCheckpointList) { this.isChassisNoForCheckPoint = true }
        this.patchValueForViewOrEditPdc(response)
      })
    })
  }
  private patchValueForViewOrEditPdc(response) {
    this.pdcDetailsForm.patchValue(response.pdcViewHeaderResponse)
    this.pdcDetailsForm.get('pdcDate').patchValue(new Date(response.pdcViewHeaderResponse.pdcDate))
    this.pdcDetailsForm.get('chassisNo').patchValue({ chassisNo: response.pdcViewHeaderResponse.chassisNo })
    if (this.pdcPagePresenter.operation === Operation.VIEW) {
      this.viewPdcCheckPoint = response.pdcCheckpointList
      this.pdcStaticTable.disable()
    }
    if (this.pdcPagePresenter.operation === Operation.EDIT) {
      this.editPdcCheckPoint = response.pdcCheckpointList
      this.isChassisNo = true
    }
    this.pdcStaticTable.get('staticChecked').patchValue(response.pdcViewHeaderResponse.okFlag)
    this.pdcStaticTable.get('staticRemark').patchValue(response.pdcViewHeaderResponse.remarks)
    this.pdcDetailsForm.get('chassisNo').disable()

  }
  selectChassisNo() {
    if (this.pdcPagePresenter.operation === Operation.CREATE) {
      this.pdcDetailsForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange.chassisNo) {
          this.isChassisNoForCheckPoint = true
          this.isChassisNo = true
        }else {
          this.isChassisNoForCheckPoint = false
          this.isChassisNo = false
        }
      })
    }
  }
  getCheckPoint(event: Array<CheckpointListByModel>) {
    this.pdcCheckPoint = event
  }

  saveAndSubmitForm(isSave: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    if (this.pdcForm.status === 'VALID') {
      if (this.isDraft === true) {
        this.openConfirmDialog();
      } else {
        const pdcForm = this.pdcForm.getRawValue()
        if (pdcForm.staticTable.staticChecked === true) {
          this.openConfirmDialog();
        } else {
          this.openConfirmDialogForSubmit()
        }
      }
    } else {
      this.pdcPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Required mandatory fields')
    }
  }
  submitData() {
    this.pdcPageWebService.saveAndSubmitPdc(this.buildJsonForSaveAndSubmitPdc()).subscribe(response => {
      if(response){
        const displayMsg = response['message']
        this.toastr.success(displayMsg, 'Success')
        const pdcForm = this.pdcForm.getRawValue()
        if (pdcForm.staticTable.staticChecked === true) {
          if (this.pdcPagePresenter.operation === Operation.EDIT) {
            this.router.navigate(['../../'], { relativeTo: this.pdcRt })
          } else {
            this.router.navigate(['../'], { relativeTo: this.pdcRt })
          }
        }
      } else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  private buildJsonForSaveAndSubmitPdc() {
    const saveAndSubmitPdc = {
      ...this.buildJsonForPdc(),
      ...this.buildJsonForservicePdcChassisCheckpointInfoSet()
    }
    return saveAndSubmitPdc
  }
  private buildJsonForPdc() {
    const pdcForm = this.pdcForm.getRawValue()
    const saveAndSubmitPdc = {} as SaveAndSubmitPdc
    if (this.isDraft === true) {
      saveAndSubmitPdc.draftFlag = true
    } else {
      saveAndSubmitPdc.draftFlag = false
    }
    saveAndSubmitPdc.machineInventory = { id: pdcForm.pdcDetailsForm.chassisNo ? pdcForm.pdcDetailsForm.chassisNo.id : null }
    if (this.pdcPagePresenter.operation === Operation.EDIT) {
      saveAndSubmitPdc.id = this.id
      if (this.chassisId) {
        saveAndSubmitPdc.machineInventory = { id: this.chassisId }
      }
    }
    saveAndSubmitPdc.okFlag = pdcForm.staticTable ? pdcForm.staticTable.staticChecked : null
    saveAndSubmitPdc.remarks = pdcForm.staticTable ? pdcForm.staticTable.staticRemark : null
    return saveAndSubmitPdc
  }
  private buildJsonForservicePdcChassisCheckpointInfoSet() {
    const pdcForm = this.pdcForm.getRawValue()
    const saveAndSubmitPdc = {} as SaveAndSubmitPdc
    const servicePdcChassisCheckpointInfoSet = []
    pdcForm.tableData.dataTable.forEach(element => {
      servicePdcChassisCheckpointInfoSet.push({
        remarks: element ? element.remarks : null,
        okFlag: element.defaultTick,
        observedSpecification: element.observedSpecification,        
        aggregateSequenceNo: element.aggregateSequenceNo,
        checkpointSequenceNo: element.checkpointSequenceNo,
        chassisCheckpointId: {
          serviceMtCheckPoint: { checkpointId : element.checkpointId }
        }
      })
    });
    saveAndSubmitPdc.servicePdcChassisCheckpointInfoSet = servicePdcChassisCheckpointInfoSet
    return saveAndSubmitPdc
  }
  private navigateToJobCard() {
    const pdcForm = this.pdcForm.getRawValue()
    const chassisNo = pdcForm.pdcDetailsForm.chassisNo.chassisNo
    this.pdcPageWebService.getChassisDetailsByChassisNoInJobCard(chassisNo, 'true', 'false').subscribe(response => {
      
      if (response) {
        this.router.navigate(['../../../customerService/job-card/pdc'], {
          queryParams: { chassisNumber: chassisNo }
        })
      }
    })
  }
  // private navigateToDeAllot() {
  //   const pdcForm = this.pdcForm.getRawValue()
  //   const machineAllotmentId = pdcForm.pdcDetailsForm.chassisNo.machineAllotmentId
  //   const url = `sales-pre-sales/sales/allot-deallot/deallot/${machineAllotmentId}`;
  //   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url,})
  // }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Pre-Delivery Checklist?`;
    if (this.pdcPagePresenter.operation === Operation.EDIT) {
      message = 'Do you want to update Pre-Delivery Checklist?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm') {
        this.isSubmitDisable = false;
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
    return confirmationData;
  }
  private openConfirmDialogForSubmit(): void | any {
    const message = `1) Click Procced to Job Card (PDC will be created). 2) Click Submit to create PDC.`;
    const confirmationData = this.setConfirmationModalDataForSubmit(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Submit') { 
        this.isSubmitDisable = true;
        this.submitData() 
      }
      // if (result === 'De-Allot') {
      //   this.navigateToDeAllot()
      // }
      if (result === 'Procced to Job Card') {
        this.isSubmitDisable = true;
        this.submitData();
        this.navigateToJobCard()
      }
    });
  }
  private setConfirmationModalDataForSubmit(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Submit', 'Procced to Job Card'];
    return confirmationData;
  }

  viewPdcPrint(printStatus:string){
    this.pdcPageWebService.printPreSalesServicePdc(this.pdcDetailsForm.get('pdcNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
  }
}