import { Injectable } from '@angular/core';
import { KisPageStore } from './kai-inspection-sheet-create-page.store';
import { FormGroup, FormArray } from '@angular/forms';
import { KaiInspectionSheet, MaterialDetails, KaiDropDown,LabourCharge,OutSideCharge } from '../../domain/kai-inspection-sheet.domain';
import { Operation } from '../../../../../utils/operation-util';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
@Injectable()
export class KisPagePresenter {
  private _operation: string;
  collectFiles: UploadableFile[];
  failureMode: KaiDropDown;
  failureUnit: KaiDropDown;
  typeOfUse: KaiDropDown;

  constructor(private kisPageStore: KisPageStore) {}

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }

  get kisForm(): FormGroup {
    return this.kisPageStore.allForm.get('kisForm') as FormGroup;
  }
  get materialDetailForm(): FormArray {
    return this.kisPageStore.allForm.get('materialDetailForm') as FormArray;
  }
  get labourChargeForm(): FormArray {
    return this.kisPageStore.allForm.get('labourChargeForm') as FormArray;
  }
  get outsideLabourChargeForm(): FormArray {
    return this.kisPageStore.allForm.get('outsideLabourChargeForm') as FormArray;
  }
  get transporterDetailForm(): FormGroup {
    return this.kisPageStore.allForm.get('transporterDetailForm') as FormGroup;
  }
  get kQuickForm(): FormGroup {
    return this.kisPageStore.allForm.get('kQuickForm') as FormGroup;
  }
  get uploadFileForm(): FormGroup {
    return this.kisPageStore.allForm.get('uploadFileForm') as FormGroup;
  }
  addRowInMaterialDetail(materialDetail?: MaterialDetails) {
    const control = this.materialDetailForm;
    const newForm = this.kisPageStore.initializeMaterialDetailForm(materialDetail);
    control.push(newForm);
    if (this.operation == Operation.VIEW) {
      newForm.disable();
    }
    
  }
  addRowInLabourCharge(labourChargeDetail?:LabourCharge) {
    const control = this.labourChargeForm;
    const newForm = this.kisPageStore.initializeLabourChargeForm(
      labourChargeDetail
    );
    control.push(newForm);
    if (this.operation == Operation.VIEW) {
        newForm.disable();
      }
  }
  addRowInOutsideLabourCharge(outsideLabourChargeDetail?:OutSideCharge) {
    const control = this.outsideLabourChargeForm;
    const newForm = this.kisPageStore.initializeOutsideLabourChargeForm(
      outsideLabourChargeDetail
    );
    control.push(newForm);
    if (this.operation == Operation.VIEW) {
        newForm.disable();
      }
  }
  collectPCRFiles(files: UploadableFile[]) {
    this.collectFiles = files;
  }

  disableForm() {
    this.kisForm.disable();
    this.transporterDetailForm.disable();
    this.kQuickForm.disable();
  }

  kaiPatchValue(detail: KaiInspectionSheet) {
    const warrantyDeliveryChallanView = detail.wcrDcDetail;
    
    this.transporterDetailForm.get('transporterName').patchValue(warrantyDeliveryChallanView.transporterName);
    this.transporterDetailForm.get('docketNo').patchValue(warrantyDeliveryChallanView.docketNumber);
    this.transporterDetailForm.get('numberOfBox').patchValue(warrantyDeliveryChallanView.numberOfBox);
    this.transporterDetailForm.get('dispatchDate').patchValue(warrantyDeliveryChallanView.dispatchDate);

    this.kisForm.get('dcNo').patchValue(warrantyDeliveryChallanView.dcNo);
    this.kisForm.get('wcrNo').patchValue(warrantyDeliveryChallanView.wcrNo);
    this.kisForm.get('wcrType').patchValue(warrantyDeliveryChallanView.wcrType);
    this.kisForm.get('claimDate').patchValue(warrantyDeliveryChallanView.wcrDate);
    this.kisForm.get('dealerCode').patchValue(warrantyDeliveryChallanView.dealerCode);
    this.kisForm.get('dealerName').patchValue(warrantyDeliveryChallanView.dealerName);
    this.kisForm.get('dealerAddress').patchValue(warrantyDeliveryChallanView.address);


    if(detail.kaiInspectionSheetView) {
      this.kisForm.get('inspectionNo').patchValue(detail.kaiInspectionSheetView.inspectionNo);
      this.kisForm.get('inspectionDate').patchValue(detail.kaiInspectionSheetView.inspectionDate);
      this.kQuickForm.get('failureMode').patchValue(detail.kaiInspectionSheetView.failureMode);
      this.kQuickForm.get('failureUnit').patchValue(detail.kaiInspectionSheetView.failureUnit);
      this.kQuickForm.get('typeOfUse').patchValue(detail.kaiInspectionSheetView.typeOfUse);
      this.kQuickForm.get('symptom').patchValue(detail.kaiInspectionSheetView.symptom);
      this.kQuickForm.get('defect').patchValue(detail.kaiInspectionSheetView.defect);
      this.kQuickForm.get('remedy').patchValue(detail.kaiInspectionSheetView.remedy);
      this.kisForm.get('wcrRemark').patchValue(detail.kaiInspectionSheetView.remark);
      this.uploadFileForm.get('uploadFile').patchValue(detail.kaiInspectionSheetView.kaiInspectionSheetPhoto);
      console.log('detail.kaiInspectionSheetView.kaiInspectionSheetPhoto', detail.kaiInspectionSheetView.kaiInspectionSheetPhoto)
      console.log('this.uploadFileForm', this.uploadFileForm)

    }
    if(detail.warrantyPart){
       detail.warrantyPart.forEach(elt => {
          const materialDetail = {} as MaterialDetails;
          materialDetail.itemNo = elt.partNo;
          materialDetail.itemDescription = elt.description;
          materialDetail.unitPrice = elt.unitPrice;
          materialDetail.quantity = elt.claimQuantity;
          materialDetail.value = elt.unitPrice*elt.claimQuantity;
          materialDetail.approvedQty = elt.claimApprovedQuantity;
          materialDetail.approvedVal = elt.claimApprovedAmount;
          materialDetail.id = elt.id;
          materialDetail.claimable = elt.claimable;
          materialDetail.keyPartNumber = elt.keyPartNumber;
          materialDetail.inspectionRemark = elt.inspectionRemark;
          this.addRowInMaterialDetail(materialDetail);
        })
    }
    if(detail.labourCharge){
        detail.labourCharge.forEach(elt => {
             this.addRowInLabourCharge(elt);
        });
    }
    
    if(detail.outSideLabourCharge){
        detail.outSideLabourCharge.forEach(elt => {
            this.addRowInOutsideLabourCharge(elt);
       });
    }
  }
}
