import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";
import { MaterialData, PartData } from "../../domain/hotline-report.domain";

@Injectable()
export class HotlineReportPageStore {
  private form: FormGroup;

  constructor(private fb: FormBuilder) { }

  private createForm() {
    this.form = this.fb.group({
      hotlineReportForm: this.hotlineReportForm(),
      materialDetailForm: this.fb.array([]),
      partDetailForm: this.fb.array([]),
      vendorRespomseForm: this.vendorRespomseForm()
    });
  }

  hotlineReportForm() {
    const hotlineReportForm = this.fb.group({
      hotlineNo: [{ value: null, disabled: true }],
      hotlineDate: [{ value: null, disabled: true }],
      status: [{ value: '', disabled: false }],
      id: [{ value: '', disabled: false }],
      // status:[{value:null}],
      complaint: [{ value: null, disabled: false }],
      remarks: [{ value: null, disabled: false }],
      hotlinePlantMaster: [{ value: null, disabled: false }, Validators.required],
      departmentMaster: [{ value: null, disabled: false }, Validators.required],
      employeeMaster: [{ value: null, disabled: false }, Validators.required],
      dealerDepotMapping: [{ value: null, disabled: false }, Validators.required],
      failureDate: [{ value: null, disabled: false }, Validators.required],
      conditionFailureMaster: [{ value: null, disabled: false }, Validators.required],
      failureType: [{ value: null, disabled: false }, Validators.required],


    });
    return hotlineReportForm;
  }
  materialDetailForm(materialDetail?: MaterialData) {
    const materialDetailForm = this.fb.group({
      isSelected: [{ value: null, disabled: false }],
      id: [{ value: materialDetail == undefined ? null : materialDetail.containerNo, disabled: false }],
      // machineVinMaster: [{value: materialDetail == undefined ? null : materialDetail.machineVinMaster, disabled: false }],
      machineVinMaster: [{ value: materialDetail == undefined ? null : materialDetail.machineVinMaster, disabled: false }, Validators.compose([Validators.required, CustomValidators.selectFromList()])],
      itemDescription: [{ value: materialDetail == undefined ? null : materialDetail.itemDescription, disabled: true }],
      vendorCode: [{ value: materialDetail == undefined ? null : materialDetail.vendorCode, disabled: false }],
      vendorName: [{ value: materialDetail == undefined ? null : materialDetail.vendorName, disabled: false }],
      vendorInvoiceNo: [{ value: materialDetail == undefined ? null : materialDetail.vendorInvoiceNo, disabled: true }],
      vendorInvoiceDate: [{ value: materialDetail == undefined ? null : materialDetail.vendorInvoiceDate, disabled: true }],
      containerNo: [{ value: materialDetail == undefined ? null : materialDetail.containerNo, disabled: false }],
    });
    return materialDetailForm;
  }
  partDetailForm(partDetail?: PartData) {
    const partDetailForm = this.fb.group({
      isSelected: [{ value: false, disabled: false }],
      sparePartMaster: [{ value: partDetail == undefined ? null : partDetail.sparePartMaster, disabled: false }, Validators.compose([Validators.required, CustomValidators.selectFromList()])],
      partDescription: [{ value: partDetail == undefined ? null : partDetail.partDescription, disabled: true }],
      isClaimmable: [{ value: partDetail == undefined ? null : partDetail.isClaimmable, disabled: false }],
      quantity: [{ value: partDetail == undefined ? null : partDetail.quantity, disabled: false }, Validators.compose([Validators.required, CustomValidators.numberOnly])]
    });
    return partDetailForm;
  }
  vendorRespomseForm() {
    const vendorRespomseForm = this.fb.group({
      responseFromVendor: [{ value: '', disabled: false }, Validators.required],
      id: [{ value: '', disabled: false }]
    });
    return vendorRespomseForm;
  }
  initializeMaterialDetailForm(materialDetail?: MaterialData) {
    return this.materialDetailForm(materialDetail);
  }
  initializePartDetailForm(partDetail?: PartData) {
    return this.partDetailForm(partDetail);
  }
  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    this.createForm();
    return this.form as FormGroup;
  }
}
