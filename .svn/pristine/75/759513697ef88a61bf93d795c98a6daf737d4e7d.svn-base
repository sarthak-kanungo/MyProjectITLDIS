import { Component, OnInit } from '@angular/core';
import { ReInstallationPagePresenter } from './re-installation-page-presenter';
import { ReInstallationPageStore } from './re-installation-page.store';
import { FormGroup, AbstractControl, FormArray } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog, MatSelectChange } from '@angular/material';
import { ReInstallationPageWebService } from './re-installation-page-web.service';
import { ReInstallationCheckPoint, SaveAndSubmitReInstallation, ViewReInstallation } from '../../domain/re-installation-domain';
import { ToastrService } from 'ngx-toastr';
import { ReInstallationDetailsWebService } from '../re-installation-details/re-installation-details-web.service';

@Component({
  selector: 'app-re-installation-page',
  templateUrl: './re-installation-page.component.html',
  styleUrls: ['./re-installation-page.component.scss'],
  providers: [ReInstallationPageStore, ReInstallationPagePresenter, ReInstallationPageWebService, ReInstallationDetailsWebService]
})
export class ReInstallationPageComponent implements OnInit {

  reInstallationForm: FormGroup
  reInstallationDetailsForm: FormGroup
  reInstallationCheckPointListTable: AbstractControl
  machineDetailsTable: AbstractControl
  representativesDetailsTable: AbstractControl
  reInstallationCheckPoint: ReInstallationCheckPoint
  isSelectMachineSeries: boolean
  dialogMsg: string;
  isDraft: boolean;
  isShowBtn: boolean
  id: number
  representativesDetailsdataTable: FormArray;
  reInstallationCheckList: ReInstallationCheckPoint[]
  ViewReInstallationCheckList: ReInstallationCheckPoint[]
  viewReInstallation : ViewReInstallation
  isSubmitDisable:boolean = false;
  constructor(
    private reInstallationPagePresenter: ReInstallationPagePresenter,
    private reInstallRt: ActivatedRoute,
    public dialog: MatDialog,
    private reInstallationPageWebService: ReInstallationPageWebService,
    private toastr: ToastrService,
    private router: Router,
    private reInstallationDetailsWebService: ReInstallationDetailsWebService
  ) { }

  ngOnInit() {
    this.reInstallationPagePresenter.operation = OperationsUtil.operation(this.reInstallRt)
    this.reInstallationForm = this.reInstallationPagePresenter.reInstallationForm
    this.reInstallationDetailsForm = this.reInstallationPagePresenter.detailsForm
    this.reInstallationCheckPointListTable = this.reInstallationPagePresenter.reInstallationCheckListTableRow
    this.machineDetailsTable = this.reInstallationPagePresenter.machineDetailsTableRow
    this.representativesDetailsTable = this.reInstallationPagePresenter.representativesDetailsTableRow
    this.viewOrEditOrCreate()
    this.dialogResult()
  }

  private viewOrEditOrCreate() {
    if (this.reInstallationPagePresenter.operation === Operation.VIEW) {
      this.isShowBtn = false
      this.parseIdAndViewOrEditForm()
    } else if (this.reInstallationPagePresenter.operation === Operation.EDIT) {
      this.isShowBtn = true
      this.parseIdAndViewOrEditForm()
    }
  }

  private parseIdAndViewOrEditForm() {
    this.reInstallRt.params.subscribe(prms => {
      this.reInstallationPageWebService.getRiById(`` + atob(prms['id'])).subscribe(response => {
        this.id = response.riViewHeaderData.id
        this.viewReInstallation = response
        this.reInstallationPagePresenter.patchValueForViewReInstallation(response)
        this.ViewReInstallationCheckList = response.reInstallationCheckpointList
        this.isSelectMachineSeries = true
        response.reInstallationMachineDetailsList.forEach(ele => {
          const data = response.reInstallationRepresentativeDetailsList.filter(value => value.chassisId === ele.chassisId)
          data.forEach(value => {
            this.reInstallationPagePresenter.addRepresentativesDetails(value)
          })
        })
        if (this.reInstallationPagePresenter.operation === Operation.VIEW) {
          this.reInstallationForm.disable()
        }
      })
    })
  }

  dialogResult() {
    this.representativesDetailsdataTable = this.reInstallationPagePresenter.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
  }

  getCheckPoint(event: MatSelectChange) {
    this.reInstallationDetailsWebService.getAllReInstallationDetails(event.value.series).subscribe(response => {
      
      this.reInstallationCheckList = response
      if (response) {
        this.isSelectMachineSeries = true
        this.isShowBtn = true
      }
    })
  }

  saveAndSubmitForm(isSave: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.setValidationForSubmit()
  }

  setValidationForSubmit() {
    const dataTable = this.machineDetailsTable.get('machineDetailsdataTable') as FormArray;
    
    const representativesdataTable = this.reInstallationPagePresenter.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
    let selectFlag: boolean
    dataTable.controls.forEach(ele => {
      representativesdataTable.controls.filter(el => {
        if (el.value.chassisNo === ele.value.chassisNo.code || el.value.chassisId === ele.value.chassisNo.id) {
          selectFlag = true
        } else {
          selectFlag = false
        }
      })
    })
    if (this.reInstallationForm.status === 'VALID') {
      if (selectFlag === true) {
        this.openConfirmDialog();
      } else{
        this.toastr.error(`Please fill the Representative Type`, 'Report mandatory field')
      }
    } else {
      this.reInstallationPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitData() {
    this.reInstallationPageWebService.saveAndSubmitReInstallation(this.buildJsonForReInstallation()).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        if(this.reInstallationPagePresenter.operation === Operation.EDIT){
          this.router.navigate(['../../'], { relativeTo: this.reInstallRt })
        }else {
          this.router.navigate(['../'], { relativeTo: this.reInstallRt })
        }
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error =>{
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  private buildJsonForReInstallation() {
    const reInstallation = {
      ...this.buildJsonForReInstallationDetails(),
      ...this.buildJsonForServiceReinstallationChassisInfo(),
      ...this.buildJsonForReInstallationDetailsCheckPoint()
    }
    
    return reInstallation
  }
  private buildJsonForReInstallationDetails() {
    const reinstallationForm = this.reInstallationForm.getRawValue()
    const saveAndSubmitInstallation = {} as SaveAndSubmitReInstallation
    if (this.isDraft) {
      saveAndSubmitInstallation.draftFlag = true
    } else {
      saveAndSubmitInstallation.draftFlag = false
    }
    saveAndSubmitInstallation.machineSeries = {
      id: reinstallationForm.reInstallationDetailsForm.series ? reinstallationForm.reInstallationDetailsForm.series.id : null
    }
    saveAndSubmitInstallation.dealerEmployee = {
      id: reinstallationForm.reInstallationDetailsForm.serviceStaffName ? reinstallationForm.reInstallationDetailsForm.serviceStaffName.id : null
    }
    if (this.reInstallationPagePresenter.operation === Operation.EDIT) {
      saveAndSubmitInstallation.id = this.id
    }
    return saveAndSubmitInstallation
  }
  private buildJsonForServiceReinstallationChassisInfo() {
    const reinstallationForm = this.reInstallationForm.getRawValue()
    const saveAndSubmitInstallation = {} as SaveAndSubmitReInstallation
    const serviceReinstallationChassisInfo = []
    reinstallationForm.machineDetailstableData.machineDetailsdataTable.forEach(element => {
      const machineDetails = reinstallationForm.representativesDetailstableData.representativesDetailsdataTable.filter(value => value.uui === element.uuid || value.chassisId === element.chassisNo.id)
      
      machineDetails.forEach(dt => {
        delete dt.uuid
        delete dt.chassisNo
        delete dt.chassisId
      })
      serviceReinstallationChassisInfo.push({
        machineInventory: { id: element.chassisNo.id },
        representativeCount: (element.representativeCount && parseFloat(element.representativeCount)),
        serviceRepresentativeInfo: machineDetails
      })
    })
    saveAndSubmitInstallation.serviceReinstallationChassisInfo = serviceReinstallationChassisInfo
    return saveAndSubmitInstallation
  }
  private buildJsonForReInstallationDetailsCheckPoint() {
    const reinstallationForm = this.reInstallationForm.getRawValue()
    const saveAndSubmitInstallation = {} as SaveAndSubmitReInstallation
    const serviceRiChassisCheckpointInfo = []
    reinstallationForm.tableData.dataTable.forEach(element => {
      serviceRiChassisCheckpointInfo.push({
        okFlag: element.defaultTick,
        observedSpecification: element.observedSpecification,
        checkpointSequenceNo : element.checkpointSequenceNo,
        aggregateSequenceNo : element.aggregateSequenceNo,
        riChassisCheckpointId: {
          serviceMtReinstallationAggregate: { id: element.aggregateId },
          serviceMtReinstallationCheckpoint: { id: element.checkpointId }
        }
      })
    });
    saveAndSubmitInstallation.serviceRiChassisCheckpointInfo = serviceRiChassisCheckpointInfo
    return saveAndSubmitInstallation
  }
  private openConfirmDialog(): void | any {
    const message = `Do you want to ${this.dialogMsg} Re-Installation?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.buildJsonForReInstallation();
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