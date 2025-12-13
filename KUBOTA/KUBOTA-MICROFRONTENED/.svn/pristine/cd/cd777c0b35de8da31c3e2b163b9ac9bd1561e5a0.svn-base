import { Component, OnInit } from '@angular/core';
import { PscPageStore } from './psc-page.store';
import { FormGroup, AbstractControl } from '@angular/forms';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { PscPagePresenter } from './psc-page-presenter';
import { PscWebService } from '../psc/psc-web.service';
import { CheckListByChassisNo, SaveAndSubmitPsc, ViewPsc } from '../../domain/psc-domain';
import { ToastrService } from 'ngx-toastr';
import { PscPageWebService } from './psc-page-web.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-psc-page',
  templateUrl: './psc-page.component.html',
  styleUrls: ['./psc-page.component.scss'],
  providers: [PscPageStore, PscPagePresenter, PscPageWebService]
})
export class PscPageComponent implements OnInit {

  pscForm: FormGroup
  pscDetailsForm: FormGroup
  pscCheckListTableForm: AbstractControl
  checkPointsList: CheckListByChassisNo
  isChassisNo: boolean
  isView:boolean
  isChassisNoForCheckList: boolean
  dialogMsg: string;
  isDraft: boolean;
  chassisId: number
  id: number
  isSubmitDisable:boolean = false;
  constructor(
    private pscPagePresenter: PscPagePresenter,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private pscPageWebService: PscPageWebService,
    private pscRt: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.pscPagePresenter.operation = OperationsUtil.operation(this.pscRt)
    this.pscForm = this.pscPagePresenter.pscForm
    this.pscDetailsForm = this.pscPagePresenter.detailsForm
    this.pscCheckListTableForm = this.pscPagePresenter.pscCheckListTableRow
    this.viewOrEditOrCreate()
    this.selectChassisNumber()
  }

  private viewOrEditOrCreate() {
    if (this.pscPagePresenter.operation === Operation.VIEW) {
      this.isChassisNo = false
      this.isView = true
      this.parseIdAndViewOrEditForm()
    } else if (this.pscPagePresenter.operation === Operation.EDIT) {
      this.parseIdAndViewOrEditForm()
    }
  }

  private parseIdAndViewOrEditForm() {
    this.pscRt.params.subscribe(prms => {
      this.pscPageWebService.getPscById(`` + atob(prms['id'])).subscribe(response => {
        this.patchValueForViewOrEditPsc(response)
        this.chassisId = response.pscViewHeaderData.chassisId
        this.id = response.pscViewHeaderData.id
        if (response.pscCheckpointList) {
          this.isChassisNoForCheckList = true
        }
        if (this.pscPagePresenter.operation === Operation.EDIT) {
          this.isChassisNo = true
        }
      })
    })
  }

  private patchValueForViewOrEditPsc(response: ViewPsc) {
    this.pscDetailsForm.patchValue(response.pscViewHeaderData)
    this.pscDetailsForm.get('chassisNo').patchValue({ code: response.pscViewHeaderData.chassisNo })
    this.pscPagePresenter.spans = []
    response.pscCheckpointList.forEach(data => {
      this.pscPagePresenter.addRow(data)
      SameRowMearge.rowSpan('aggregate', d => d.aggregate, response.pscCheckpointList)
        this.pscPagePresenter.spans = SameRowMearge.returnSpansArray()
    })
    this.pscDetailsForm.get('chassisNo').disable()
    if (this.pscPagePresenter.operation === Operation.VIEW) {
      this.pscCheckListTableForm.disable()
    }
  }

  selectChassisNumber(){
    if (this.pscPagePresenter.operation === Operation.CREATE){
      this.pscDetailsForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if(valueChange.code){
          this.isChassisNo = true
          this.isChassisNoForCheckList = true
        }
      })
    }
  }

  saveAndSubmitForm(isSave?: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    if (this.pscForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.pscPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Required mandatory fields')
    }
  }

  submitAndSaveProductStorageCheckSheet() {
    this.pscPageWebService.saveAndSubmitPsc(this.buildJsonForSaveAndSubmitPsc()).subscribe(response => {
      
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        if(this.pscPagePresenter.operation === Operation.EDIT){
          this.router.navigate(['../../'], { relativeTo: this.pscRt })
        }else {
          this.router.navigate(['../'], { relativeTo: this.pscRt })
        }
      } else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving','Transaction failed');
    })
  }

  private buildJsonForSaveAndSubmitPsc() {
    const pscForm = {
      ...this.buildJsonForPscDetalis(),
      ...this.buildJsonForPscChecklist()
    }
    return pscForm
  }

  private buildJsonForPscDetalis() {
    const pscForm = this.pscForm.getRawValue()
    const saveAndSubmitPsc = {} as SaveAndSubmitPsc
    if (this.isDraft === true) {
      saveAndSubmitPsc.draftFlag = true
    } else {
      saveAndSubmitPsc.draftFlag = false
    }
    saveAndSubmitPsc.machineInventory = { id: pscForm.pscDetailsForm.chassisNo ? pscForm.pscDetailsForm.chassisNo.id : null }
    if (this.pscPagePresenter.operation === Operation.EDIT) {
      if (this.chassisId) {
        saveAndSubmitPsc.machineInventory = { id: this.chassisId }
      }
      if (this.isDraft === false) {
        saveAndSubmitPsc.id = this.id
      }
    }
    return saveAndSubmitPsc
  }

  private buildJsonForPscChecklist() {
    const pscForm = this.pscForm.getRawValue()
    const saveAndSubmitPsc = {} as SaveAndSubmitPsc
    const servicePscChassisCheckpointInfo = []
    pscForm.tableData.dataTable.forEach(element => {
      servicePscChassisCheckpointInfo.push({
        remarks: element ? element.remarks : null,
        okFlag: element.defaultTick,
        observedSpecification:element.observedSpecification,
        aggregateSequenceNo:element.aggregateSequenceNo,
        checkpointSequenceNo:element.checkpointSequenceNo,
        chassisCheckpointId: {
            serviceMtCheckPoint: { checkpointId: element.checkpointId }
        }
      })
    });
    saveAndSubmitPsc.servicePscChassisCheckpointInfo = servicePscChassisCheckpointInfo
    return saveAndSubmitPsc
  }

  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Product Storage Checksheet?`;
    if (this.pscPagePresenter.operation === Operation.EDIT) {
      message = 'Do you want to update Product Storage Checksheet?';
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
        this.submitAndSaveProductStorageCheckSheet();
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

  viewPscPrint(printStatus:string){
    this.pscPageWebService.printPreSalesServicePsc(this.pscDetailsForm.get('pscNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
  }
}