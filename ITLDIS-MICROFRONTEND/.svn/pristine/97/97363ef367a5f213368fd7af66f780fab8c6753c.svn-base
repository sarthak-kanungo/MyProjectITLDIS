import { FileUploadService } from './../../../../../ui/file-upload/file-upload.service';

import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MachineDescripancyComplaintsCreateService } from './machine-descripancy-complaints-create.service';
import { BaseDto } from 'BaseDto';
import { ChasisNumberDomain, DetailsByChasisNoDomain, SaveMachineDescripancyComplaintDomain, GrnMachineDetails, ViewEditMachineDescComplaintDomain } from 'MachineDescripancyComplaintModule';
import { LoginFormService } from '../../../../../root-service/login-form.service';import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MachineDescripancyCommonService } from '../machine-descripancy-common.service';
import { UploadableFile } from 'kubota-file-upload';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-machine-descripancy-complaints-create',
  templateUrl: './machine-descripancy-complaints-create.component.html',
  styleUrls: ['./machine-descripancy-complaints-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    MachineDescripancyComplaintsCreateService,
  ]
})
export class MachineDescripancyComplaintsCreateComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  isApprove: boolean;
  data: Object;
  public dealerId: number
  disable = true;
  selectedChasisNumber: GrnMachineDetails
  isDraft: boolean
  files: Array<UploadableFile> = new Array()
  previewUrl: string
  selectedChasisNo: ChasisNumberDomain
  searchChasisNumber: BaseDto<Array<ChasisNumberDomain>>
  chasisDataDto: BaseDto<DetailsByChasisNoDomain>
  machineGrnFrom: FormGroup
  userType:any
  complaintId:any;
  approvalList:any[]
  grnMachineDetailsId:any;
  constructor(
    private machineDescripancyComplaintsCreateService: MachineDescripancyComplaintsCreateService,
    private loginFormService: LoginFormService,
    private machinedescRt: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private machineDescripancyCommonService: MachineDescripancyCommonService,
    private fb: FormBuilder,
    private router: Router,
    private fileUploadService: FileUploadService,
  ) {
    this.userType = loginFormService.getLoginUserType();
  }

  ngOnInit() {
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
    this.getSystemGeneratedDate()
  }

  private checkOperationType() {
    console.log(this.machinedescRt.snapshot.routeConfig.path)
    this.isView = this.machinedescRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.machinedescRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    this.isCreate = this.machinedescRt.snapshot.routeConfig.path.split('/')[0] == 'create'
    this.isApprove = this.machinedescRt.snapshot.routeConfig.path.split('/')[0] == 'approve'
  }

  private initOperationForm() {
    if (this.isView || this.isApprove) {
      this.machineGrnFrom = this.machineDescripancyComplaintsCreateService.viewMachineDescComplaintForm()
    }
    else {
      this.machineGrnFrom = this.machineDescripancyComplaintsCreateService.createmachineGrnFrom()
    }
  }



  private patchOrCreate() {
    console.log('check whether form is in patchorcreate or not');
    if (this.isView || this.isApprove) {
      this.isView = true
      this.parseIdAndPatchViewForm()
    }
    else if (this.isEdit) {
      console.log('In Edit');
      this.isEdit = true
      this.parseIdAndPatchEditForm()
    }
    else {
      console.log(`CreatingForm`)
      this.formForCreateSetUp()
    }
  }

  private parseIdAndPatchViewForm() {
    this.machinedescRt.queryParamMap.subscribe((queryMap: ParamMap) => {
      if(queryMap && Object.keys(queryMap['params']).length > 0){
        if (queryMap['params']['id']) {
          this.complaintId = atob(queryMap['params']['id']);
          this.fetchDataForId(this.complaintId)
        }
      }
    });
  }

  private parseIdAndPatchEditForm() {
    this.machinedescRt.params.subscribe(prms => this.fetchDataForEditId(prms['id']))
  }

  private fetchDataForEditId(machineComplaintEditId: number) {
    this.machineDescripancyComplaintsCreateService.getMachineDescComplaintById(machineComplaintEditId).subscribe(
      dto => { this.formForEditSetUp(dto['result']) }
    )
  }

  private fetchDataForId(complaintId: number) {
    this.machineDescripancyComplaintsCreateService.getMachineDescComplaintById(complaintId).subscribe(
      dto => { this.formForViewSetup(dto['result']) }
    )
  }
  private formForEditSetUp(domain: ViewEditMachineDescComplaintDomain) {
    if (domain) {
      this.machineGrnFrom.patchValue(domain)
      this.patchDatesForView(domain)
      this.patchComplaintStatus(domain)
      this.patchComplaintItems(domain)
      this.machineGrnFrom.controls.chassisNo.patchValue({ chassisNo: domain.grnMachineDetails.chassisNo })
    }
  }
  private formForViewSetup(domain: ViewEditMachineDescComplaintDomain) {
    if (domain) {
      this.machineGrnFrom.patchValue(domain)
      this.machineGrnFrom.controls.complaintNo.patchValue(domain.complaintNumber)

      this.patchDatesForView(domain)
      this.patchComplaintStatus(domain)
      this.patchComplaintItems(domain)
      this.machineGrnFrom.controls.chassisNo.patchValue({ chassisNo: domain.grnMachineDetails.chassisNo })

      this.approvalList = domain.approvalDetails;
    }
  }
  private patchViewImages(domain: ViewEditMachineDescComplaintDomain) {
    if (domain) {

    }

  }
  private patchDatesForView(domain: ViewEditMachineDescComplaintDomain) {
    this.machineGrnFrom.controls['complaintDate'].patchValue(this.convertToPatchFormat(domain.complaintDate))
    this.machineGrnFrom.controls['kaiInvoiceDate'].patchValue(this.convertToPatchFormat(domain.grnMachineDetails.machineGrn.accPacInvoice.invoiceDate))
    this.machineGrnFrom.controls['grnDate'].patchValue(this.convertToPatchFormat(domain.grnMachineDetails.machineGrn.grnDate))
  }

  private patchComplaintStatus(domain: ViewEditMachineDescComplaintDomain) {
    this.machineGrnFrom.controls.engineNo.patchValue(domain.grnMachineDetails.engineNo)
    this.machineGrnFrom.controls.dmsGrnNumber.patchValue(domain.grnMachineDetails.machineGrn.dmsGrnNumber)
    this.machineGrnFrom.controls.transporterName.patchValue(domain.grnMachineDetails.machineGrn.transporter.transporterName)
    this.machineGrnFrom.controls.transporterVehicleNumber.patchValue(domain.grnMachineDetails.machineGrn.transporterVehicleNumber)
    this.machineGrnFrom.controls.lrNo.patchValue(domain.grnMachineDetails.machineGrn.accPacInvoice.lrNo)


  }

  private patchComplaintItems(domain: ViewEditMachineDescComplaintDomain) {
    this.machineDescripancyCommonService.emComplaintDetails.emit(domain)
  }

  private formForCreateSetUp() {
    this.machineGrnFrom = this.machineDescripancyComplaintsCreateService.createmachineGrnFrom()
    this.machineGrnFrom.controls.chassisNo.valueChanges.subscribe(value => {
      this.autoChasisNumber(value)
    })
  }

  autoChasisNumber(value) {
    this.machineDescripancyComplaintsCreateService.getChasisNumber(value, 1).subscribe(response => {
      console.log('---response', response);
      this.searchChasisNumber = response as BaseDto<Array<ChasisNumberDomain>>
    })
  }

  optionSelectedChasisNo(event: MatAutocompleteSelectedEvent) {
    console.log(event,'evenmtnssj')
    console.log(this.machineGrnFrom)
    this.selectedChasisNo = event.option.value
    this.machineDescripancyComplaintsCreateService.populateDataByChasisNo(this.selectedChasisNo.chassisNo).subscribe(response => {
      console.log('Response', response);
      this.grnMachineDetailsId=response['result'].machineGrnId,
      this.chasisDataDto = response as BaseDto<DetailsByChasisNoDomain>
      this.machineGrnFrom.patchValue(this.chasisDataDto.result)
      this.machineGrnFrom.controls.chassisNo.patchValue(this.selectedChasisNo)
      this.patchDatesForCreate(this.chasisDataDto.result)
    })
  }

  private patchDatesForCreate(domain: DetailsByChasisNoDomain) {
    this.machineGrnFrom.controls.kaiInvoiceDate.patchValue(this.convertToPatchFormat(domain.kaiInvoiceDate))
    this.machineGrnFrom.controls.grnDate.patchValue(this.convertToPatchFormat(domain.grnDate))
  }

  private convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return ''
  }

  saveData() {
    this.machineDescripancyComplaintsCreateService
      .saveMachineDescripancyComplaint(this.buildMachineDescObject())
      .subscribe(res => {
        this.toastr.success('Machine descripancy complaint save successfully')
        this.router.navigate(['../'],{relativeTo: this.machinedescRt})
      }, err => {
        this.toastr.error('Problem in Saving Data')
      })
  }

  private buildMachineDescObject() {
    let formData = this.machineGrnFrom.getRawValue()
    let itemDetails = this.machineDescripancyCommonService.complaintDetailsForm.getRawValue()['complaintFormArray']
    let machineObj: SaveMachineDescripancyComplaintDomain = {} as SaveMachineDescripancyComplaintDomain
    machineObj.machineDescripancyComplaintDetails = []
    machineObj.complaintStatus = 'Waiting For Approval'
    machineObj.dealerEmployeeMaster = { id: this.loginFormService.getLoginUserId() }
    //machineObj.draftMode = true

    machineObj.grnMachineDetails = { id: this.selectedChasisNo.id }

    itemDetails.forEach(dtl => {
      machineObj.machineDescripancyComplaintDetails.push(
        {
          itemNo: dtl['itemNo']['itemNo'],
          itemDescription: dtl['itemDescription'],
          quantity: dtl['quantity'],
          remarks: dtl['remarks'],
          type: dtl['type']['complaintType'],
        }
      )
    })
    var files:UploadableFile[] = this.machineDescripancyCommonService.files;

    return machineObj
  }

  validateForm() {
    if (this.machineGrnFrom.valid && this.validateComplaints()) {
      this.openConfirmDialog()
    }
    else {
      if (!this.machineGrnFrom.valid) {
        this.markFormAsTouched()
        this.toastr.warning('Kindly Select Mandatory Fields')
      }
      if (!this.validateComplaints()) {
        this.markComplaintsAsTouched()
        this.toastr.warning('Kindly Select Mandatory Fields From Complaint')
      }
      if (!(this.machineDescripancyCommonService.files.length > 0 && this.machineDescripancyCommonService.files.length <= 4)) {
        this.toastr.warning('Atleast One Document is Mandetory')
      }

    }
  }

  private markComplaintsAsTouched() {
    let cmpArr = this.machineDescripancyCommonService.complaintDetailsForm.controls.complaintFormArray as FormArray
    cmpArr.controls.forEach(cmp => cmp.markAllAsTouched())
  }

  private validateComplaints() {
    return this.machineDescripancyCommonService.complaintDetailsForm.valid
  }
  private markFormAsTouched() {
    this.machineGrnFrom.markAllAsTouched()
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to save Machine Descripancy Complaint?';
    if (this.isEdit) {
      message = 'Do you want to update Machine Descripancy Complaint?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.saveData()
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

  displayChasisNoFn(chasisNo: ChasisNumberDomain) {
    return chasisNo ? chasisNo.chassisNo : undefined
  }
  getSystemGeneratedDate() {
    this.machineDescripancyComplaintsCreateService.getSystemGeneratedDate().subscribe(response => {
      console.log(response);
      this.machineGrnFrom.controls.complaintDate.patchValue(new Date(response['result']))

    })
  }

  
  private openApprovalConfirmationModal(approvalType){
    let message = `Are you sure you want to ${approvalType}?`
    const confirmationData = this.setApprovalConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
       if (result.button === 'Confirm') {
        const approvalDetails = {
          'approvalStatus' : approvalType,
          'kaiRemarks' : result.remarkText,
          'claimId' : this.complaintId
        }
        this.machineDescripancyComplaintsCreateService.approveClaim(approvalDetails).subscribe(response => {
          this.toastr.success(response['message']);
          this.router.navigate(['../'], { relativeTo: this.machinedescRt });
        });
       }
    })
  }
  private setApprovalConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    if(this.isApprove){
      confirmationData.remarkConfig = {
          source: Source.REQUIRED
      }
  }
    return confirmationData;
  }

  approveRejectClaim(approvalType){
    this.openApprovalConfirmationModal(approvalType);
  }
  
  //for Submit
  submitData() {
    this.machineDescripancyComplaintsCreateService
      .saveMachineDescripancyComplaint(this.buildMachineDescObjectForSubmit())
      .subscribe(res => {
        this.toastr.success('Machine descripancy complaint submit successfully')
        this.router.navigate(['../'],{relativeTo: this.machinedescRt})
      }, err => {
        this.toastr.error('Error While Submit Record!')
      })
  }

  private buildMachineDescObjectForSubmit() {
    let formData = this.machineGrnFrom.getRawValue()
    let itemDetails = this.machineDescripancyCommonService.complaintDetailsForm.getRawValue()['complaintFormArray']
    //console.log('FormData', formData);
    let machineObj: SaveMachineDescripancyComplaintDomain = {} as SaveMachineDescripancyComplaintDomain
    machineObj.machineDescripancyComplaintDetails = []
    machineObj.complaintStatus = 'Waiting For Approval'
    machineObj.dealerEmployeeMaster = { id: this.loginFormService.getLoginUserId() }
    //machineObj.draftMode = false

    // machineObj.grnMachineDetails = { id: this.selectedChasisNo.id }
    machineObj.grnMachineDetails = { id: this.grnMachineDetailsId }

    itemDetails.forEach(dtl => {
      machineObj.machineDescripancyComplaintDetails.push(
        {
          itemNo: dtl['itemNo']['itemNo'],
          itemDescription: dtl['itemDescription'],
          quantity: dtl['quantity'],
          remarks: dtl['remarks'],
          type: dtl['type']['complaintType'],
        }
      )
    })
    return machineObj
  }

  validateFormForSubmit() {
    if (this.machineGrnFrom.valid && this.validateComplaints()) {
      
      if (!(this.machineDescripancyCommonService.files.length > 0 && this.machineDescripancyCommonService.files.length <= 4)) {
        this.toastr.warning('Atleast One Document is Mandetory')
      }else{
        this.openConfirmDialogForSubmit()
      }
    }
    else {
      if (!this.machineGrnFrom.valid) {
        this.markFormAsTouched()
        this.toastr.warning('Kindly Select Mandatory Fields')
      }
      if (!this.validateComplaints()) {
        this.markComplaintsAsTouched()
        this.toastr.warning('Kindly Select Mandatory Fields From Complaint')
      }
      if (!(this.machineDescripancyCommonService.files.length > 0 && this.machineDescripancyCommonService.files.length <= 4)) {
        this.toastr.warning('Atleast One Document is Mandetory')
      }
    }
  }

  private openConfirmDialogForSubmit(): void | any {
    let message = 'Do you want to submit Machine Descripancy Complaint?';
    if (this.isEdit) {
      message = 'Do you want to update Machine Descripancy Complaint?';
    }
    const confirmationData = this.setConfirmationModalDataForSubmit(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.submitData()

      }

    });
  }
  private setConfirmationModalDataForSubmit(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  fileSelctionChanges(fileInput: any) {
    if (!this.isFilesCountFour()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.machineDescripancyCommonService.files = this.files
    }
  }

  isFilesCountFour() {
    return this.fileUploadService.fileCount() == 4
  }
  deleteImage(id: string) {
    console.log(this.files)
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
  }
  clearAll() {
    this.machineGrnFrom.reset()
    this.machineDescripancyCommonService.complaintDetailsForm.reset()
    this.files = []
  }

}