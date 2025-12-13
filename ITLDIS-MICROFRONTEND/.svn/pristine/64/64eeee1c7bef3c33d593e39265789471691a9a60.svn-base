import { Component, OnInit } from '@angular/core';
import { FormGroup, AbstractControl } from '@angular/forms';
import { PdiPresenter } from './pre-delivery-inspection-presenter';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { PdiWebService } from '../pdi/pdi-web.service';
import { ModelNumberData, SavePdiObject, ServicePdiChassisCheckpointInfoSet, MachineInventory, ChassisCheckpointId, PdiCheckpointList, PdiHeaderData } from '../../domain/pdi-domain';
import { ActivatedRoute, Router } from '@angular/router';
import { PdiPageWebService } from './pdi-page-web.service';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { OperationsUtil } from '../../../../../utils/operation-util';
import { ViewStore } from './view-page.store';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';

@Component({
  // changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-create-pdi',
  templateUrl: './create-pdi.component.html',
  styleUrls: ['./create-pdi.component.scss'],
  providers: [PdiPresenter, PdiWebService, ViewStore, PdiPageWebService]
})
export class CreatePdiComponent implements OnInit {
  createPdiDetailsForm: FormGroup
  isEdit: boolean;
  isShownTable: boolean
  isUpdate: boolean
  isView: boolean;
  data: Object;
  disable = true;
  chassisNumberSend: string
  isSave: boolean = false
  sendCheckPointListData = new BehaviorSubject<ModelNumberData>(undefined)
  checkPointListData = new BehaviorSubject<any>(undefined)
  rowDataView: PdiCheckpointList
  rowDataEdit: PdiCheckpointList
  isButtonShow: boolean = false
  pdiDetailsForm: AbstractControl
  materialTableForm: AbstractControl
  materialTableFormSecond: AbstractControl
  saveId: number;
  isSubmitted: boolean;
  hasJobCard: boolean;
  isSubmitDisable:boolean = false;
  constructor(
    private presenter: PdiPresenter,
    public dialog: MatDialog,
    private pdiWebService: PdiWebService,
    private activateRoute: ActivatedRoute,
    private pdiPageWebService: PdiPageWebService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.createPdiDetailsForm = this.presenter.pdiForm
    this.pdiDetailsForm = this.createPdiDetailsForm.controls.createBasicDetailsPdiForm
    this.materialTableForm = this.createPdiDetailsForm.controls.tableData
    this.materialTableFormSecond = this.createPdiDetailsForm.controls.staticTable
    this.getOperationType()
    this.checkParams()
  }
  checkParams() {
    this.activateRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        if (this.isEdit) {
          this.getMachineTransferForEdit(atob(param.get('id')));
        }
        if (this.isView) {
         
          this.getMachineTransferForView(atob(param.get('id')));
          this.presenter.disableFields()

        }
      }
    })
  }
  getMachineTransferForView(id: any) {
    this.pdiPageWebService.getViewData(id).subscribe(res => {
      if (res['pdiCheckpointList']) {
        this.rowDataView = res['pdiCheckpointList'] as PdiCheckpointList
      }
      this.presenter.patchValueTodetails(res['pdiHeaderData'] as PdiHeaderData)
    })
  }
  getMachineTransferForEdit(id: any) {

    this.saveId = id
    this.pdiPageWebService.getViewData(id).subscribe(res => {
      if (res['pdiCheckpointList']) {
        this.rowDataEdit = res['pdiCheckpointList'] as PdiCheckpointList
      }
      this.presenter.patchValueTodetails(res['pdiHeaderData'] as PdiHeaderData)
    })
  }
  getModelSendData(event: string) {
    this.pdiWebService.getCheckPoint(event).subscribe(res => {

      this.sendCheckPointListData.next(res)
      this.checkPointListData.next(res)as unknown as Array<any>
      this.isButtonShow = true
    })
  }
  getOperationType() {
    const operationType = this.activateRoute.snapshot.routeConfig.path.split('/')[0]
    if (operationType == 'edit') {
      this.isEdit = true;
      this.isButtonShow = true
      this.isShownTable = true
    }
    if (operationType == 'view') {
      this.isView = true;
      this.isShownTable = true
    }
  }
  validateSaveForm() {
    
    if (this.presenter.formArrayCheckPoints.value.length == 0) {
      this.toastr.error('Aggregate and Checkpoints Not available')
      return false;
    }
    this.isSave = true
    this.markFormAsTouched();
    if (this.createPdiDetailsForm.valid) {
      this.openConfirmDialog();
    }else{
      this.toastr.error(`Please fill all the mandatory fields`, 'Required mandatory fields')
    }
  }
  private markFormAsTouched() {
    this.createPdiDetailsForm.markAllAsTouched();
  }

  validateSubmitForm() {
    
    if (this.presenter.formArrayCheckPoints.value.length == 0) {
      this.toastr.success('Aggregate and Checkpoints Not available')
    }
    this.isSave = false
    this.markFormAsTouched();
    if (this.createPdiDetailsForm.valid) {

      if (this.presenter.staticTable.get('staticChecked').value === true) {
        this.openConfirmDialog();
      } else {
        this.openConfirmDialogJobCard();
        this.chassisNumberSend = this.presenter.basicDetails.get('chassisNo').value.code
        

      }
    }else{
      this.toastr.error('Please fill mandatory fields','Mandatory Fields')
    }
  }

  saveData() {

    const pdi = {} as SavePdiObject
    if (this.isEdit) {
      pdi.id = this.saveId
    }
    if (!this.isSave) {
      pdi.draftFlag = false
    } else
      if (this.isSave) {
        pdi.draftFlag = true
      }
    if (this.presenter.basicDetails.get('pdiNo').value) {
      pdi.pdiNo = this.presenter.basicDetails.get('pdiNo').value
    }
    pdi.machineInventory = {} as MachineInventory

    pdi.machineInventory.id = this.presenter.basicDetails.get('chassisNo').value.id
    pdi.servicePdiChassisCheckpointInfoSet = [] as ServicePdiChassisCheckpointInfoSet[]
    const formDataArry = this.presenter.formArrayCheckPoints
    formDataArry.value.forEach(element => {
      const servicePdiChassisCheckpointInfoSet = {} as ServicePdiChassisCheckpointInfoSet
      servicePdiChassisCheckpointInfoSet.chassisCheckpointId = {} as ChassisCheckpointId
      servicePdiChassisCheckpointInfoSet.observedSpecification = element.observedSpecification
      servicePdiChassisCheckpointInfoSet.okFlag = element.defaultTick
      servicePdiChassisCheckpointInfoSet.remarks = element.remark
      servicePdiChassisCheckpointInfoSet.aggregateSequenceNo = element.aggregateSequenceNo;
      servicePdiChassisCheckpointInfoSet.checkpointSequenceNo = element.checkpointSequenceNo;
      servicePdiChassisCheckpointInfoSet.chassisCheckpointId.serviceMtCheckPoint = { checkpointId: element.checkpointId.id || null }
      pdi.servicePdiChassisCheckpointInfoSet.push(servicePdiChassisCheckpointInfoSet)
    });
    pdi.okFlag = this.presenter.staticTable.get('staticChecked').value
    pdi.remarks = this.presenter.staticTable.get('staticRemark').value
    this.pdiPageWebService.savePdiData(pdi).subscribe(res => {
      if(res){
        const displayMessage = res['message']
        this.toastr.success(displayMessage, 'Success');
        if (this.hasJobCard) {
          this.router.navigate(['../../../customerService/job-card/pdi'], {
            relativeTo: this.activateRoute, queryParams: { chassisNumber: this.chassisNumberSend }
          })
        } else {
          this.router.navigate(['../'], { relativeTo: this.activateRoute })
        }
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving', 'Transaction failed');
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Save Pre-Delivery Inspection?';
    if (this.isSave) {
      message = 'Do you want to Save Pre-Delivery Inspection?';
    }
    if (!this.isSave) {
      message = 'Do you want to Submit Pre-Delivery Inspection?';
    }
    if (this.isEdit) {
      message = 'Do you want to update Pre-Delivery Inspection?';
    }

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.saveData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.saveData();
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

  private openConfirmDialogJobCard(): void | any {
    const message = 'Do you want to Submit Pre-Delivery Inspection?';

    const confirmationData = this.setConfirmationModalDataJC(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Submit') {
        this.saveData();
      }
      if (result === 'Submit And Proceed To Job Card') {
        this.hasJobCard = true
        this.saveData();

      }
    });
  }
  private setConfirmationModalDataJC(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Submit', 'Submit And Proceed To Job Card'];
    return confirmationData;
  }

  viewPdiPrint(printStatus:string){
    this.pdiWebService.printPreSalesServicePdi(this.pdiDetailsForm.get('pdiNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
  }
}
