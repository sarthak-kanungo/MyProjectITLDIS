import { Component, OnInit, Output } from '@angular/core';
import { InstallationPageStore } from './installation-page.store';
import { InstallationPagePresenter } from './installation-page-presenter';
import { FormGroup, AbstractControl } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { InstallationPageWebService } from './installation-page-web.service';
import { SaveAndSubmitInstallation, ServiceInstallationPhotosList, Installation, InstallationCheckList } from '../../domain/installation-domain';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-installation-page',
  templateUrl: './installation-page.component.html',
  styleUrls: ['./installation-page.component.scss'],
  providers: [InstallationPageStore, InstallationPagePresenter, InstallationPageWebService]
})
export class InstallationPageComponent implements OnInit {

  installationForm: FormGroup
  installationDetailsForm: FormGroup
  dInstallationCheckPointListTable: AbstractControl
  fInstallationCheckPointListTable: AbstractControl
  uploadPhotosForm: FormGroup
  isChassisNoForDICheckList: boolean
  isChassisNoForFICheckList: boolean
  selectedPhotos: Array<File>
  isChassisNo: boolean
  dialogMsg: string;
  isDraft: boolean;
  chassisId: number;
  @Output() isView:boolean;
  sendPatchedPhotos = []
  id: number
  diCheckPointByChassisNumber: Array<InstallationCheckList>
  fiCheckPointByChassisNumber: Array<InstallationCheckList>
  viewDiCheckPoint: Array<InstallationCheckList>
  viewFiCheckPoint: Array<InstallationCheckList>
  editDiCheckPoint: Array<InstallationCheckList>
  editFiCheckPoint: Array<InstallationCheckList>
  installationPhotoList: Array<ServiceInstallationPhotosList>
  isSubmitDisable:boolean = false;
  constructor(
    private installationPagePresenter: InstallationPagePresenter,
    private installRt: ActivatedRoute,
    public dialog: MatDialog,
    private installationPageWebService: InstallationPageWebService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.installationPagePresenter.operation = OperationsUtil.operation(this.installRt)
    this.installationForm = this.installationPagePresenter.installationForm
    this.installationDetailsForm = this.installationPagePresenter.detailsForm
    this.dInstallationCheckPointListTable = this.installationPagePresenter.dInstallationCheckListTableRow
    this.fInstallationCheckPointListTable = this.installationPagePresenter.fInstallationCheckListTableRow
    this.uploadPhotosForm = this.installationPagePresenter.uploadPhotosForm
    this.viewOrEditOrCreate()
    this.installationTypeValueChanges()
  }

  private viewOrEditOrCreate() {
    if (this.installationPagePresenter.operation === Operation.VIEW) {
      this.parseIdAndViewOrEditForm()
      this.installationDetailsForm.disable()
      this.isView = true;
    } else if (this.installationPagePresenter.operation === Operation.EDIT) {
      this.parseIdAndViewOrEditForm()
    }
  }
  private parseIdAndViewOrEditForm() {
    this.installRt.params.subscribe(prms => {
      this.installationPageWebService.getDiById(`` + atob(prms['id'])).subscribe(response => {
        this.chassisId = response.installationViewHeaderData.chassisId
        this.id = response.installationViewHeaderData.id
        this.installationPhotoList = response.machineInstallationPhotoList
        this.installationPagePresenter.patchValueForViewAndEdit(response)
        response.machineInstallationPhotoList.forEach(photos => {
          this.sendPatchedPhotos.push({ file: { name: photos.fileName } })
        })
        if (this.installationPagePresenter.operation === Operation.EDIT) {
          this.isChassisNo = true
          this.installationDetailsForm.get('chassisNo').disable()
          this.installationPagePresenter.selectedPhotos = this.sendPatchedPhotos
          
          if (response.installationViewHeaderData.installationType === 'Field Installation') {
            this.isChassisNoForFICheckList = true
            this.editFiCheckPoint = response.installationCheckpointList
          } else {
            this.isChassisNoForDICheckList = true
            this.editDiCheckPoint = response.installationCheckpointList
          }
        }
        if (this.installationPagePresenter.operation === Operation.VIEW) {
          this.isChassisNo = false
          if (response.installationViewHeaderData.installationType === 'Field Installation') {
            this.isChassisNoForFICheckList = true
            this.viewFiCheckPoint = response.installationCheckpointList
          } else {
            this.isChassisNoForDICheckList = true
            this.viewDiCheckPoint = response.installationCheckpointList
          }
        }
      })
    })
  }

  diCheckpointByChassisNo(event: InstallationCheckList[]) {
    this.diCheckPointByChassisNumber = event
  }

  fiCheckpointByChassisNo(event: InstallationCheckList[]) {
    this.fiCheckPointByChassisNumber = event
  }

  installationTypeValueChanges() {
    this.installationDetailsForm.get('installationType').valueChanges.subscribe(valueChange => {
      if (valueChange === 'Field Installation') {
        this.isChassisNoForFICheckList = true
        this.isChassisNo = true
      } else if (valueChange === 'Delivery Installation') {
        this.isChassisNoForDICheckList = true
        this.isChassisNo = true
      }
    })
  }

  saveAndSubmitForm(isSave: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    if (this.installationForm.status === 'VALID') {
      if (this.installationPagePresenter.selectedPhotos === undefined || this.installationPagePresenter.selectedPhotos.length < 1) {
        this.toastr.error(`File Attachment mandatory`, 'Report Attachment Photo')
      } else {
        this.openConfirmDialog();
      }
    } else {
      this.installationPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  submitData() {
    this.installationPageWebService.addDeliveryInstallation(this.buildJsonForSaveAndSubmitInstallation()).subscribe(response => {
      
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        if (this.installationPagePresenter.operation === Operation.EDIT) {
          this.router.navigate(['../../'], { relativeTo: this.installRt })
        } else {
          this.router.navigate(['../'], { relativeTo: this.installRt })
        }
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  private buildJsonForSaveAndSubmitInstallation() {

    const installationForm = this.installationForm.getRawValue()
    const installation = {} as Installation
    const saveAndSubmitInstallation = {} as SaveAndSubmitInstallation
    installation.serviceMachineInstallation = saveAndSubmitInstallation
    installation.serviceMachineInstallation.machineInstallationType = { id: installationForm.installationDetailsForm.installationId };
    installation.serviceMachineInstallation.representativeType = installationForm.installationDetailsForm.representativeType ? installationForm.installationDetailsForm.representativeType.representativeType : null
    installation.serviceMachineInstallation.customerRepName = installationForm.installationDetailsForm.customerRepName
    installation.serviceMachineInstallation.dealerEmployee = { id: installationForm.installationDetailsForm ? installationForm.installationDetailsForm.searviceStaffName.id : null }
    const csbNumberValue = this.installationDetailsForm.get('csbNumber').value;    
      installation.serviceMachineInstallation.csbNumber = csbNumberValue && csbNumberValue.code
        ? csbNumberValue.code
        : csbNumberValue;
    //  installation.serviceMachineInstallation.csbNumber = this.installationDetailsForm.get('csbNumber').value.code ? this.installationDetailsForm.get('csbNumber').value.code : null
    
     if (this.installationPagePresenter.operation === Operation.EDIT) {
      const csbNumberValue = this.installationDetailsForm.get('csbNumber').value.code;    
      installation.serviceMachineInstallation.csbNumber = csbNumberValue && csbNumberValue.code
        ? csbNumberValue.code
        : csbNumberValue;
    }
    
    if (this.isDraft === true) {
      installation.serviceMachineInstallation.draftFlag = true
    } else {
      installation.serviceMachineInstallation.draftFlag = false
    }
    installation.serviceMachineInstallation.machineInventory = { id: installationForm.installationDetailsForm.chassisNo ? installationForm.installationDetailsForm.chassisNo.id : null }
    installation.multipartFileList = this.installationPagePresenter.selectedPhotos
    if (this.installationPagePresenter.operation === Operation.EDIT) {
      installation.serviceMachineInstallation.id = this.id
      if (this.chassisId) {
        installation.serviceMachineInstallation.machineInventory = { id: this.chassisId }
      }
    }
    if (installationForm.installationDetailsForm.installationType === 'Field Installation') {
      installation.serviceMachineInstallation.serviceFiChassisCheckpointInfo = this.buildJsonForFInstallationCheckList(installationForm.fiTableData.fiDataTable)
    } else {
      installation.serviceMachineInstallation.serviceDiChassisCheckpointInfo = this.buildJsonForDiInstallationCheckList(installationForm.diTableData.diDataTable)
    }
    
    return installation
  }

  private buildJsonForDiInstallationCheckList(diInstallationCheckList: InstallationCheckList[]) {
    const serviceDiChassisCheckpointInfo = []
    diInstallationCheckList.forEach(element => {
      serviceDiChassisCheckpointInfo.push({
        remarks: element.remarks ? element.remarks : null,
        okFlag: element.defaultTick,
        observedSpecification: element.observedSpecification,
        aggregateSequenceNo:element.aggregateSequenceNo,
        checkpointSequenceNo:element.checkpointSequenceNo,
        diChassisCheckpointInfo: {
          serviceMtDeliveryInstallationCheckpoint: { checkpointId: element.checkpointId }
        }
      })
    });
    return serviceDiChassisCheckpointInfo
  }

  private buildJsonForFInstallationCheckList(fiInstallationCheckList: InstallationCheckList[]) {
    const serviceFiChassisCheckpointInfo = []
    fiInstallationCheckList.forEach(element => {
      serviceFiChassisCheckpointInfo.push({
        remarks: element.remarks ? element.remarks : null,
        okFlag: element.defaultTick,
        observedSpecification: element.observedSpecification,
        aggregateSequenceNo:element.aggregateSequenceNo,
        checkpointSequenceNo:element.checkpointSequenceNo,
        fiChassisCheckpointInfo: {
          serviceMtFieldInstallationCheckpoint: { checkpointId: element.checkpointId }
        }
      })
    });
    return serviceFiChassisCheckpointInfo
  }

  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Installation?`;
    if (this.installationPagePresenter.operation === Operation.EDIT) {
      message = 'Do you want to update Installation?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm') {
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
    return confirmationData;
  }
}