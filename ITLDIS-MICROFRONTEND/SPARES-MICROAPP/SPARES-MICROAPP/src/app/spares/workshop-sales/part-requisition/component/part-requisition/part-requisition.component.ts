import { Component, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormArray,ControlValueAccessor, Validator, AbstractControl, ValidationErrors, NG_VALUE_ACCESSOR, NG_VALIDATORS } from '@angular/forms';
import { PartRequisitionPagePresenter, PartRequisitionPresenter } from '../part-requisition-page/part-requisition-page.presenter';
import { map } from 'rxjs/operators';
import { PartRequisitionApiService } from './part-requisition-api.service';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Component({
  selector: 'app-part-requisition',
  templateUrl: './part-requisition.component.html',
  styleUrls: ['./part-requisition.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => PartRequisitionComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => PartRequisitionComponent),
      multi: true
    },
    PartRequisitionApiService
  ]
})
export class PartRequisitionComponent implements OnInit, ControlValueAccessor, Validator {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  partRequisitionPresenter: PartRequisitionPresenter
  partRequisitionForm: FormGroup;
  onTouch: any;
  requisitionPurposeList$: Observable<SelectList[]>;
  loginUser: any;
  constructor(
    partRequisitionPresenter: PartRequisitionPagePresenter,
    private partRequisitionApiService: PartRequisitionApiService,
    private localStorageService: LocalStorageService
  ) {
    this.partRequisitionPresenter = partRequisitionPresenter;
    this.partRequisitionForm = this.partRequisitionPresenter.partRequisitionForm;
  }

  ngOnInit() {
    // this.createpartRequisitionForm();
    this.loginUser = this.localStorageService.getLoginUser();
    this.getRequisitionPurpose();
    this.partRequisitionForm && this.partRequisitionForm.get('requestedBy').patchValue(this.loginUser.name);
    this.partRequisitionApiService.getServerDate().subscribe(sevrDate => {
      this.partRequisitionForm.get('requisitionDate').patchValue(sevrDate.result);
    });
  }
  private getRequisitionPurpose() {
    this.requisitionPurposeList$ = this.partRequisitionApiService.getRequisitionPurpose();
  }
  compareFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.value === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.value;
    }
    return c1 && c2 ? c1.value === c2.value : c1 === c2;
  }
  validate(control: AbstractControl): ValidationErrors {
    // throw new Error("Method not implemented.");
    return this.partRequisitionForm.errors
  }
  registerOnValidatorChange?(fn: () => void): void {
    // throw new Error("Method not implemented.");
    fn();
  }
  writeValue(obj: any): void {
    // throw new Error("Method not implemented.");
    this.partRequisitionForm.patchValue(obj);
  }
  registerOnChange(fn: any): void {
    fn && this.partRequisitionForm.get('requisitionNo').valueChanges.pipe(
      map(val => val)
    ).subscribe(fn);
  }
  registerOnTouched(fn: any): void {
    // throw new Error("Method not implemented.");
    this.onTouch = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    // throw new Error("Method not implemented.");
    this.partRequisitionForm.disable();
  }

}
