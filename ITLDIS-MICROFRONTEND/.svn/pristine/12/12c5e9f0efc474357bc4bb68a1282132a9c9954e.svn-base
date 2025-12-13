import { Component, OnInit } from '@angular/core';
import { KisPageStore } from './kai-inspection-sheet-create-page.store';
import { KisPagePresenter } from './kai-inspection-sheet-create-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { KaiInspectionSheetCreatePageWebService } from './kai-inspection-sheet-create-page-web.service';
import { ToastrService } from 'ngx-toastr';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { KaiInspectionSheet, KaiSheet, SubmitKaisheet, KaiInspectionSheetPhoto , LabourCharge, OutSideCharge, SparePartRequisitionItem} from '../../domain/kai-inspection-sheet.domain';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-kai-inspection-sheet-create-page',
  templateUrl: './kai-inspection-sheet-create-page.component.html',
  styleUrls: ['./kai-inspection-sheet-create-page.component.scss'],
  providers: [KisPageStore, KisPagePresenter, KaiInspectionSheetCreatePageWebService]
})
export class KaiInspectionSheetCreatePageComponent implements OnInit {
  kisForm: FormGroup;
  materialDetailForm: FormArray;
  labourChargeForm: FormArray;
  outsideLabourChargeForm: FormArray;
  transporterDetailForm: FormGroup;
  kQuickForm: FormGroup;
  uploadFileForm: FormGroup;
  btnName: string;
  inspectionDetails: KaiInspectionSheet;
  kaiInspectionSheetPhoto: KaiInspectionSheetPhoto[];
  isKaiPhoto : boolean;
  operation: string;
  printInspectionNo:any;
  isView:boolean
  isApprove:boolean;
  isSubmitDisable:boolean = false;
  constructor(
    private kisPagePresenter: KisPagePresenter,
    private router: Router,
    private tostr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private kaiInspectionSheetCreatePageWebService: KaiInspectionSheetCreatePageWebService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.kisForm = this.kisPagePresenter.kisForm;
    this.materialDetailForm = this.kisPagePresenter.materialDetailForm;
    this.labourChargeForm = this.kisPagePresenter.labourChargeForm;
    this.outsideLabourChargeForm = this.kisPagePresenter.outsideLabourChargeForm;
    this.transporterDetailForm = this.kisPagePresenter.transporterDetailForm;
    this.kQuickForm = this.kisPagePresenter.kQuickForm;
    this.uploadFileForm = this.kisPagePresenter.uploadFileForm;

    this.activateRoute();
  }

  private activateRoute() {
    this.kisPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    this.operation = this.kisPagePresenter.operation;
    this.activatedRoute.queryParams.subscribe(qParam=> {
      this.printInspectionNo=qParam.inspectionNo
      if (this.kisPagePresenter.operation == Operation.CREATE) {
      this.wcrDcForKaiInspectionSheet(qParam.wcrNo)
      } else if(this.kisPagePresenter.operation == Operation.VIEW) {
        this.isView = true;
        this.kisPagePresenter.disableForm();
        this.viewKaiInspectionSheet(qParam.inspectionNo, qParam.wcrNo);
      } else if(this.kisPagePresenter.operation == Operation.APPROVE) {
        this.isApprove = true;
        this.viewKaiInspectionSheet(qParam.inspectionNo, qParam.wcrNo);
      }
    });
  }

  private wcrDcForKaiInspectionSheet(wcrNo: string) {
    this.kaiInspectionSheetCreatePageWebService.wcrDcForKaiInspectionSheet(wcrNo).subscribe(res => {
    
    this.inspectionDetails = res;
    this.isKaiPhoto = this.kaiInspectionSheetPhoto == undefined;
    this.kisPagePresenter.kaiPatchValue(res)
    }, err => {
      this.tostr.error('Error while fetching details', 'Error');
    });
  }

  private viewKaiInspectionSheet(inspectionNo: string, wcrNo: string) {
    this.kaiInspectionSheetCreatePageWebService.viewKaiInspectionSheet(inspectionNo, wcrNo).subscribe(res => {
      this.kaiInspectionSheetPhoto = res.kaiInspectionSheetView.kaiInspectionSheetPhoto;
      this.isKaiPhoto = this.kaiInspectionSheetPhoto.length > 0 || this.isApprove;
      this.inspectionDetails = res;
      this.kisPagePresenter.kaiPatchValue(res)
      }, err => {
        this.tostr.error('Error while fetching details', 'Error');
      });
  }

  formValid(btnType: string) {
    btnType == "submit" ? (this.btnName = "submit") : (this.btnName = "hold");
    if(this.kisForm.valid && (this.btnName == "hold" || 
        (this.uploadFileForm.valid && this.materialDetailForm.valid && this.labourChargeForm.valid && this.outsideLabourChargeForm.valid && this.kQuickForm.valid)) ) {
      this.openConfirmDialog();
    }
    else {
      this.kisForm.markAllAsTouched();
      if(this.btnName == "submit"){
        this.uploadFileForm.markAllAsTouched();
        if(this.materialDetailForm)
            this.materialDetailForm.markAllAsTouched();
        if(this.labourChargeForm)
            this.labourChargeForm.markAllAsTouched();
        if(this.outsideLabourChargeForm)
            this.outsideLabourChargeForm.markAllAsTouched();
        if(this.kQuickForm)
            this.kQuickForm.markAllAsTouched();
      }
      this.tostr.error('Please fill all mandatory fields', 'Error');
    }
  }

  private openConfirmDialog() {
    const message = `Do you want to ${this.btnName} inspection sheet?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "Confirm") {
        //if (this.btnName == "submit") {
          this.isSubmitDisable = true;
          this.submitKaiInspectionSheetForm();
        //}
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    return confirmationData;
  }

  private submitKaiInspectionSheetForm() {
    const submitInspectionSheet = {} as SubmitKaisheet; 
    const kaiInspectionSheet = {} as KaiSheet;
    const labours:LabourCharge[] = [];
    const outsideCharges:OutSideCharge[] = [];
    const parts:SparePartRequisitionItem[] = [];
    if(this.inspectionDetails.kaiInspectionSheetView && this.inspectionDetails.kaiInspectionSheetView.id){
      kaiInspectionSheet.id = this.inspectionDetails.kaiInspectionSheetView.id
    }

    kaiInspectionSheet.actionType = this.btnName;
    kaiInspectionSheet.failureMode = this.kQuickForm.get('failureMode').value;
    kaiInspectionSheet.failureUnit = this.kQuickForm.get('failureUnit').value;
    kaiInspectionSheet.typeOfUse = this.kQuickForm.get('typeOfUse').value;
    kaiInspectionSheet.symptom = this.kQuickForm.get('symptom').value;
    kaiInspectionSheet.defect = this.kQuickForm.get('defect').value;
    kaiInspectionSheet.remedy = this.kQuickForm.get('remedy').value;
    kaiInspectionSheet.claimFinalRemark = this.kisForm.get('wcrRemark').value; 
    kaiInspectionSheet.warrantyWcr = {id: Number(this.inspectionDetails.wcrDcDetail.id), wcrType: this.inspectionDetails.wcrDcDetail.wcrType};
    

    if(this.materialDetailForm){
        this.materialDetailForm.getRawValue().forEach(elt => {
            parts.push({
                id : elt.id,
                claimable:elt.claimable,
                inspectionRemark:elt.inspectionRemark,
                claimApprovedAmount:elt.approvedVal,
                claimApprovedQuantity:elt.approvedQty,
                keyPartNumber:elt.keyPartNumber
            })
        })
    }
    
    if(this.labourChargeForm){
        this.labourChargeForm.getRawValue().forEach(elt => {
            labours.push({
                labourChargeId : elt.id,
                claimable:elt.claimableFromVendor,
                inspectionRemark:elt.inspectionRemarks,
                claimApprovedAmount:elt.finalApprovedAmount
            })
        })
    }
    if(this.outsideLabourChargeForm){
        this.outsideLabourChargeForm.getRawValue().forEach(elt => {
            outsideCharges.push({
                jobcodeId : elt.id,
                claimable:elt.claimableFromVendor,
                inspectionRemark:elt.inspectionRemarks,
                claimApprovedAmount:elt.finalApprovedAmount
            })
        })
    }
    kaiInspectionSheet.sparePartRequisitionItem = parts;
    kaiInspectionSheet.labourJobCharge = labours;
    kaiInspectionSheet.outsideJobCharge = outsideCharges;
    submitInspectionSheet.kaiInspectionSheet = kaiInspectionSheet;

    if (this.kisPagePresenter.collectFiles) {
      submitInspectionSheet.multipartFileList = this.kisPagePresenter.collectFiles;
    }

    this.kaiInspectionSheetCreatePageWebService.saveKaiInspectionSheet(submitInspectionSheet).subscribe(res => {
      if(res){
        this.tostr.success(res['message'], 'Success');
        this.router.navigate(['..'], {relativeTo: this.activatedRoute});
      }else{
        this.isSubmitDisable = false;
        this.tostr.error('Error generated while saving', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.tostr.error('Error generated while saving', 'Transaction failed');
    });

  }

  viewPrint(printStatus:string){
    this.kaiInspectionSheetCreatePageWebService.kaiInspecPrint(this.printInspectionNo, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
 }


}
