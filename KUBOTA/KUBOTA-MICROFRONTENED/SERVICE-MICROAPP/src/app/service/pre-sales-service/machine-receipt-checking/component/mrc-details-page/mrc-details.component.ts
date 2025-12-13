import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormGroup, AbstractControl, FormControl, FormArray } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MrcCreateWebService } from '../basic-mrc-details/mcr-create-web.service';
import { Mrc, MrcCheckPoint, FinalMrc, MrcViewResult, MrcCheckpointList, MrcDiscrepancyList } from '../../domain/machine-receipt-checking.domain';
import { CheckPointWebService } from '../check-point/check-point-web.service';
import { MrcPhotoUploadService } from '../photos/mrc-photo-upload.service';
import { MachineReceiptCheckingPresenter } from './mrc-page.presenter';
import { MrcCreatePageStore } from './mrc-create-page.store';
import { MrcPageService } from './mrc-page-web.service';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { LoginFormService } from 'src/app/root-service/login-form.service';


@Component({
  selector: 'app-mrc-details',
  templateUrl: './mrc-details.component.html',
  styleUrls: ['./mrc-details.component.scss'],
  viewProviders: [MachineReceiptCheckingPresenter],
  providers: [MrcCreateWebService, CheckPointWebService, MrcPhotoUploadService, MrcCreatePageStore, MrcPageService]
})
export class MrcDetailsComponent implements OnInit {
  mrcDetailsForm: FormGroup;
  isEdit: boolean;
  isView: boolean;
  data: Object;
  sendCheckPointListData: Array<MrcCheckPoint> = [];
  editCheckPointListData: Array<MrcCheckpointList> = [];
  viewCheckPointListData: Array<MrcCheckpointList> = [];

  defectShortageDamageList: Array<MrcDiscrepancyList> = [];
  selectedPhotos: Array<File>;
  basicMrcDetails: FormGroup;
  checkPointTableForm: AbstractControl;
  defectShortageDamageForm: FormGroup;
  defectShortageFormControl: FormControl;

  defectShortageDamageFormTable: FormGroup;
  photoUploadFileForm: FormGroup;
  dialogMsg: string;
  isDraft: boolean;
  mrcDiscrepancyList = [];
  isButtonsShow: boolean = true;
  kaiInvoiceNoId: number;
  chassisNoId: number;
  idForUpdate: number;
  sendPatchedPhotos = [];
  viewEditData: MrcViewResult;
  isSubmitDisable: boolean = false;
  isShowFormF:boolean=false;
  isShowForm22:boolean=false;
  constructor(
    private presenter: MachineReceiptCheckingPresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private mrcPageService: MrcPageService,
    private checkPointWebService: CheckPointWebService,
    private loginService: LoginFormService
  ) { }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.mrcDetailsForm = this.presenter.mrcForm;
    this.basicMrcDetails = this.mrcDetailsForm.get('basicMrcDetails') as FormGroup;
    this.checkPointTableForm = this.presenter.checkListTableRow;
    this.defectShortageDamageForm = this.mrcDetailsForm.get('defectShortageDamageForm') as FormGroup;
    this.photoUploadFileForm = this.mrcDetailsForm.get('photoUploadFileForm') as FormGroup;

    this.defectShortageDamageFormTable = this.mrcDetailsForm.get('defectShortageDamageFormTable') as FormGroup;
    
    this.toCheckRouteValue();
    this.viewOrEditOrCreate();
  }
  toCheckRouteValue() {
    
    this.activateRoute.paramMap.subscribe(param => {
    
      if (param.has('id')) {
        this.isView = true;
        this.getMrcForView(atob(param.get('id')));
        this.idForUpdate = + atob(param.get('id'));
      }
    });
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      
      this.mrcDetailsForm.disable()
      this.isView = true;
      this.isButtonsShow = false;
      this.isEdit = false;
    } else if (this.presenter.operation === Operation.EDIT) {
      
      this.isButtonsShow = true;
      this.isEdit = true;
      this.isView = false;
    } else if (this.presenter.operation === Operation.CREATE) {
      
    }
  }
  back() {
    this.router.navigate(['../../../machine-receipt-checking'], { relativeTo: this.activateRoute })
  }

  getChassisEvent(event) {
    
    if (this.presenter.operation === Operation.CREATE) {
      this.checkPointWebService.getMCRCheckPoint(event.option.value.chassisNumber).subscribe(res => {
    
        this.sendCheckPointListData = res;
      })
    }
  }
  saveAndSubmitForm(isSave?: boolean) {
    
    this.isDraft = isSave;
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.validateForm();
  }
  getSelectedPhotos(event) {
    
    this.selectedPhotos = event;
  }
  validateForm() {
    
    this.presenter.markFormAsTouched();
    if (this.mrcDetailsForm.valid) {
      this.openConfirmDialog();
    } else {
      this.toastr.error(`Please fill all the mandatory fields`, 'Required mandatory fields')
    }
  }
  submitData() {
    this.submitMCRDetailsForCreate()
  }
  submitMCRDetailsForCreate() {
    const mcrDetails = this.presenter.mrcForm.getRawValue()
    
    const finalMrc = {} as FinalMrc;
    const mrcObj = {} as Mrc;
    finalMrc.serviceMrc = mrcObj;
    finalMrc.multipartFileList = this.selectedPhotos;
    if (this.isEdit) {
      finalMrc.multipartFileList = this.sendPatchedPhotos;
      finalMrc.serviceMrc.id = this.idForUpdate;
      finalMrc.serviceMrc.accPacInvoice = { id: this.kaiInvoiceNoId || null };
      finalMrc.serviceMrc.accPacInvoicePartDetails = { id: this.chassisNoId || null };
    }
    if (this.isDraft) {
      finalMrc.serviceMrc.draftFlag = true;
    } else {
      finalMrc.serviceMrc.draftFlag = false;
    }
    if (this.presenter.operation === Operation.CREATE) {
      finalMrc.serviceMrc.accPacInvoice = { id: mcrDetails.basicMrcDetails['kaiInvoiceNo']['id'] }
      finalMrc.serviceMrc.accPacInvoicePartDetails = { id: mcrDetails.basicMrcDetails['chassisNo']['id'] };
    }
    // finalMrc.serviceMrc.serviceMrcDiscrepancySet = this.getServiceMrcDiscrepancySet(mcrDetails.defectShortageDamageForm);
    let remainingParts = [];
    remainingParts = this.getServiceMrcDiscrepancySet(mcrDetails.defectShortageDamageFormTable.defectShortageDamageData);
    
    let deletedParts = [];
    deletedParts = this.getServiceMrcDiscrepancySet(this.presenter.deletedParts)
    deletedParts.forEach(ele => {
      ele.deleteFlag = true
    })
    
    finalMrc.serviceMrc.serviceMrcDiscrepancySet = [...remainingParts, ...deletedParts]
    finalMrc.serviceMrc.serviceMrcChassisCheckpointInfSet = this.getServiceMrcChassisCheckpointList(mcrDetails.tableData.dataTable)
    // this.selectedPhotos.forEach(file => mrcObj.serviceMrcPhotosList.push(file))
    
    this.mrcPageService.postSaveMRCDetails(finalMrc).subscribe(formData => {
      if(formData){
        this.toastr.success('Machine Receipt Checking added successfully!', 'Success');
        this.router.navigate([this.isEdit ? '../../' : '../'], { relativeTo: this.activateRoute })
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  getServiceMrcDiscrepancySet(defectShortageDamageData) {
    
    const accPacInvoicePartDetailsIdAndItem = [];
    if (defectShortageDamageData) {
      defectShortageDamageData.forEach(element => {
        
        if (element.quantity !== null) {
          accPacInvoicePartDetailsIdAndItem.push({
            sparePartMaster: {
              id: (element.sparePartMasterId ? element.sparePartMasterId : (element.id ? element.id : element.itemNo.id)) || null,
              itemNo: (element.itemNo.itemNo ? element.itemNo.itemNo : element.itemNo) || null
            },
            quantity: parseInt(element.quantity) || null,
            remarks: element.remarks || null,
            // type: element.type ? element.type.value : null || null,
            type: element.type || null,
            raiseComplaint: element.raiseComplaint || false,
            deleteFlag: false,
            id: element.id || null,
          });
        }
        
      });
    }
    return accPacInvoicePartDetailsIdAndItem;
  }
  getServiceMrcChassisCheckpointList(dataTable) {
    const serviceMrcChassisCheckpointList = [];
    dataTable.forEach(element => {
      delete element.aggregate;
      delete element.checkpointDesc;
      serviceMrcChassisCheckpointList.push({
        serviceMrcChassisCheckpointId: {
          serviceMtCheckPoint: { checkpointId: element.checkpointId.value || null },
          //serviceMtMrcAggregate: { id: element.aggregateId.value || null }
        },
        okFlag: element.defaultTick,
        remarks: element.remarks,
        observedSpecification:element.observedSpecification,
        aggregateSequenceNo:element.aggregateSequenceNo,
        checkpointSequenceNo:element.checkpointSequenceNo,
      });
      
    });
    return serviceMrcChassisCheckpointList;
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Machine Receipt Checking?`;
    if (this.isEdit) {
      message = 'Do you want to update Machine Receipt Checking?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
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
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
  getMrcForView(id) {
    
    this.mrcPageService.getMrcViewdata(id).subscribe(viewResult => {
    
      this.viewEditData = viewResult;
      if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.EDIT) {
        this.basicMrcDetails.get('kaiInvoiceNo').disable();
        this.basicMrcDetails.get('chassisNo').disable();
      }
      this.kaiInvoiceNoId = viewResult.mrcHeaderData.kaiInvoiceId;
      this.basicMrcDetails.get('mrcNo').patchValue(viewResult.mrcHeaderData.mrcNumber);
      this.basicMrcDetails.get('kaiInvoiceNo').patchValue({
        invoiceNumber: viewResult.mrcHeaderData.kaiInvoiceNumber,
        id: viewResult.mrcHeaderData.kaiInvoiceId
      });
      this.chassisNoId = viewResult.mrcHeaderData.chassisNoId;
      this.basicMrcDetails.get('chassisNo').patchValue({
        chassisNumber: viewResult.mrcHeaderData.chassisNumber,
        id: viewResult.mrcHeaderData.chassisNoId
      });
      this.basicMrcDetails.get('machineModel').patchValue(viewResult.mrcHeaderData.machineModel)
      this.basicMrcDetails.get('engineNo').patchValue(viewResult.mrcHeaderData.engineNumber)
      this.basicMrcDetails.get('transporterName').patchValue(viewResult.mrcHeaderData.transporter_name);
      this.basicMrcDetails.get('lrNo').patchValue(viewResult.mrcHeaderData.lrNumber)
      this.basicMrcDetails.get('lrDate').patchValue(new Date(viewResult.mrcHeaderData.lrDate))
      this.basicMrcDetails.get('mrcDate').patchValue(new Date(viewResult.mrcHeaderData.mrcDate))
      
      // viewResult.mrcCheckpointList.forEach(element => {
      //   this.addRow(element)
      // });
      if (this.isEdit) {
        this.editCheckPointListData = viewResult.mrcCheckpointList;
      }
      
      if (this.isView) {
        this.viewCheckPointListData = viewResult.mrcCheckpointList;
      }
      // this.defectShortageDamageForm.patchValue(viewResult.mrcDiscrepancyList);
      this.defectShortageDamageList = viewResult.mrcDiscrepancyList

      this.isShowFormF = viewResult.mrcHeaderData.isShowFormF && this.isView;
      this.isShowForm22 = viewResult.mrcHeaderData.isShowForm22 && this.isView;

      viewResult.mrcPhotoList.forEach(photos => {
      
        this.sendPatchedPhotos.push({ file: { name: photos.fileName } })
      
      });
    });
  }
  addRow(checkList): void {
    const table = this.checkPointTableForm.get('dataTable') as FormArray;
    table.push(this.presenter.createCheckPointTableRow(checkList));
  }

  ShowFormF(formType){
    this.mrcPageService.printForm(this.basicMrcDetails.get('mrcNo').value,formType, "mrc","true").subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          // let headerContentDispostion = resp.headers.get('content-disposition');
          // let fileName = headerContentDispostion.split("=")[1].trim();
          // const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          // saveAs(file);

              const blobUrl = window.URL.createObjectURL(resp.body);
              const iframe = document.createElement('iframe');
              iframe.style.display = 'none';
              iframe.src = blobUrl;
              document.body.appendChild(iframe);
              iframe.contentWindow.print();

              if(this.loginService.getLoginUserType().toLowerCase()=='dealer'){
                if(formType=='FormF'){
                  this.isShowFormF = false;
                }
                if(formType=='Form22'){
                  this.isShowForm22 = false;
                }
              }
        }
   })
  }

  viewMrcPrint(printStatus:string){
    this.mrcPageService.printPreSalesServiceMrc(this.basicMrcDetails.get('mrcNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
  }

}
