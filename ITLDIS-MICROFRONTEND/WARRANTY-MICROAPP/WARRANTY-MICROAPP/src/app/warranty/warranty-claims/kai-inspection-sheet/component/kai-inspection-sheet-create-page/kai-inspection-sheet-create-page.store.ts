import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MaterialDetails ,OutSideCharge, LabourCharge} from '../../domain/kai-inspection-sheet.domain';

@Injectable()
export class KisPageStore {
  private readonly form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      kisForm: this.kisForm(),
      materialDetailForm: this.fb.array([]),
      labourChargeForm: this.fb.array([]),
      outsideLabourChargeForm: this.fb.array([]),
      transporterDetailForm: this.transporterDetailForm(),
      kQuickForm: this.kQuickForm(),
      uploadFileForm: this.uploadFileForm(),
    });
  }

  /* private createForm() {
    this.form = this.fb.group({
      kisForm: this.kisForm(),
      materialDetailForm: this.fb.array([]),
      labourChargeForm: this.fb.array([]),
      outsideLabourChargeForm: this.fb.array([]),
      transporterDetailForm: this.transporterDetailForm(),
      kQuickForm: this.kQuickForm(),
      uploadFileForm: this.uploadFileForm(),
    });
  } */

  private kisForm() {
    const kisForm = this.fb.group({
        inspectionNo: [{value: null, disabled: true}, Validators.required],
        inspectionDate: [{value: null, disabled: true}],
        inspectedBy: [{value: null, disabled: true}],
        dcNo: [{value: null, disabled: true}],
        wcrNo: [{value: null, disabled: true}],
        wcrType: [{value: null, disabled: true}],
        claimDate: [{value: null, disabled: true}],
        dealerCode: [{value: null, disabled: true}],
        dealerName: [{value: null, disabled: true}],
        dealerAddress: [{value: null, disabled: true}],
        wcrRemark: [{value: null, disabled: false}, Validators.required],
    });
    return kisForm;
  }
  private materialDetailForm(materialDetail?: MaterialDetails) {
    const materialDetailForm = this.fb.group({
        itemNo: [{value: materialDetail.itemNo, disabled:true}],
        itemDescription: [{value: materialDetail.itemDescription, disabled:true}],
        unitPrice: [{value: materialDetail.unitPrice, disabled:true}],
        quantity: [{value: materialDetail.quantity, disabled:true}],
        value: [{value: materialDetail.value, disabled:true}],
        approvedQty: [{value: materialDetail.approvedQty, disabled:false},Validators.compose([Validators.required, Validators.min(0),Validators.max(materialDetail.quantity)])],
        approvedVal: [{value: materialDetail.approvedVal, disabled:true}],
        inspectionRemark: [{value: materialDetail.inspectionRemark == null? null: materialDetail.inspectionRemark, disabled:false}],
        claimable: [{value: materialDetail.claimable, disabled:false}],
        keyPartNumber: [{value: materialDetail.keyPartNumber, disabled:false}],
        selectedRadioBtn: [null],
        id: [materialDetail.id],
        sparePartMaster: [materialDetail.sparePartMaster]
    });
    return materialDetailForm;
  }
  private labourChargeForm(labourChargeDetail?:LabourCharge) {
    const labourChargeForm = this.fb.group({
        id:[labourChargeDetail?labourChargeDetail.id:null],
        jobCode: [{value: labourChargeDetail?labourChargeDetail.labourCode:null, disabled:true}],
        description: [{value: labourChargeDetail?labourChargeDetail.labourDesc:null, disabled:true}],
        hours: [{value: labourChargeDetail?labourChargeDetail.labourHour:null, disabled:true}],
        claimAmount: [{value: labourChargeDetail?labourChargeDetail.claimApprovedAmount:null, disabled:true}],
        finalApprovedAmount: [{value: labourChargeDetail?labourChargeDetail.finalApprovedAmount:null, disabled:false},Validators.compose([Validators.required, Validators.min(0),Validators.max(labourChargeDetail.claimApprovedAmount)])],
        inspectionRemarks: [{value: labourChargeDetail?labourChargeDetail.inspectionRemark:null, disabled:false}],
        claimableFromVendor: [{value: labourChargeDetail?labourChargeDetail.claimable:null, disabled:false}],
    });
    return labourChargeForm;
  }
  private outsideLabourChargeForm(outsideLabourChargeDetail?:OutSideCharge) {
    const outsideLabourChargeForm = this.fb.group({
        id:[outsideLabourChargeDetail?outsideLabourChargeDetail.id:null],
        jobcode: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.jobcode:null, disabled:true}],
        description: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.jobcodeDesc:null, disabled:true}],
        claimAmount: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.claimApprovedAmount:null, disabled:true}],
        finalApprovedAmount: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.finalApprovedAmount:null, disabled:false},Validators.compose([Validators.required, Validators.min(0),Validators.max(outsideLabourChargeDetail.claimApprovedAmount)])],
        inspectionRemarks: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.inspectionRemark:null, disabled:false}],
        claimableFromVendor: [{value: outsideLabourChargeDetail?outsideLabourChargeDetail.claimable:null, disabled:false}],
    });
    return outsideLabourChargeForm;
  }
  private transporterDetailForm() {
    const transporterDetailForm = this.fb.group({
        transporterName: [{value: null, disabled:true}],
        docketNo: [{value: null, disabled:true}],
        numberOfBox: [{value: null, disabled:true}],
        dispatchDate: [{value: null, disabled:true}],
    });
    return transporterDetailForm;
  }
  private kQuickForm() {
    const kQuickForm = this.fb.group({
        failureMode: [{value: null, disabled:false}, Validators.required],
        failureUnit: [{value: null, disabled:false}, Validators.required],
        typeOfUse: [{value: null, disabled:false}, Validators.required],
        symptom: [{value: null, disabled:false}, Validators.required],
        defect: [{value: null, disabled:false}, Validators.required],
        remedy: [{value: null, disabled:false}, Validators.required],
    });
    return kQuickForm;
  }
  private uploadFileForm() {
    const uploadFileForm = this.fb.group({
      uploadFile: [null, Validators.required],
    });
    return uploadFileForm;
  }

  initializeMaterialDetailForm(materialDetail?: MaterialDetails) {
    return this.materialDetailForm(materialDetail);
  }
  initializeLabourChargeForm(labourChargeDetail?) {
    return this.labourChargeForm(labourChargeDetail);
  }
  initializeOutsideLabourChargeForm(outsideLabourChargeDetail?) {
    return this.outsideLabourChargeForm(outsideLabourChargeDetail);
  }

  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
   /*  this.createForm();
    return this.form as FormGroup; */
  }
}
