import { Injectable } from "@angular/core";
import { FormGroup, FormArray } from "@angular/forms";
import { HotlineReportPageStore } from './hotline-report-create-page.store';
import { ToastrService } from 'ngx-toastr';
import { UploadableFile } from "src/app/ui/file-upload/file-upload";
import { Htr, MaterialData, PartData } from "../../domain/hotline-report.domain";
import { Operation } from "src/app/utils/operation-util";

@Injectable()
export class HotlineReportPagePresenter {
  private _operation: string;
  collectFiles: UploadableFile[];

  constructor(private hotlineReportPageStore: HotlineReportPageStore) { }

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }

  get hotlineReportForm(): FormGroup {
    return this.hotlineReportPageStore.allForm.get('hotlineReportForm') as FormGroup;
  }
  get materialDetailForm(): FormArray {
    return this.hotlineReportPageStore.allForm.get('materialDetailForm') as FormArray;
  }
  get partDetailForm(): FormArray {
    return this.hotlineReportPageStore.allForm.get('partDetailForm') as FormArray;
  }
  get vendorRespomseForm(): FormGroup {
    return this.hotlineReportPageStore.allForm.get('vendorRespomseForm') as FormGroup;
  }
  addRowInMaterialDetail(materialDetail?: MaterialData) {
    const control = this.materialDetailForm;
    const newForm = this.hotlineReportPageStore.initializeMaterialDetailForm(materialDetail);
    control.push(newForm);
    if (this._operation == Operation.VIEW) {
      newForm.disable();
    }
    if (this._operation == Operation.EDIT) {
      newForm.disable();
    }
  }
  addRowInPartDetail(partDetail?: PartData) {
    const controls = this.partDetailForm;
    const newForms = this.hotlineReportPageStore.initializePartDetailForm(partDetail);

    controls.push(newForms);
    if (this._operation == Operation.VIEW) {
      newForms.disable();
    }
    if (this._operation == Operation.EDIT) {
      newForms.disable();
    }
  }

  deleteRow(form: FormArray) {
    const deleteRow = form.value.filter(val => val.isSelected);
    if ((form.value.length - deleteRow.length) > 0) {
      const control = form;
      const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelected);
      control.clear();
      notSelected.forEach(elt => { control.push(elt) });
    }
  }

  collectPCRFiles(files: UploadableFile[]) {
    this.collectFiles = files;
  }




}
