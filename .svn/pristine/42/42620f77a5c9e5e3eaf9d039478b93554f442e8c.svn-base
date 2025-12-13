import { Injectable, Optional } from '@angular/core';
import { SelectList } from '../../../core/model/select-list.model';
import { BehaviorSubject, Subject, Observable, of } from 'rxjs';
import { CustomerByCustomerCode } from './model/invoice-customer-detail-by-customer-code-adaptor.service';
import { InvoiceDcByCustomerCode } from './model/invoice-dc-by-customer-code-adaptor.service';
import { InvoiceMaterialByDc } from './model/invoice-material-by-dc-adaptor.service';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SaveInvoice, SaveInvoiceAdaptorService, UpdateFinalInvoiceRecord } from './model/save-invoice-adaptor.service';
import { InvoiceForm } from './model/invoice-form-adaptor.service';
import { InsuranceDetail } from './model/insurance-detail';


@Injectable()
export class InvoiceStoreService {
  invoiceTypeSelectionBehaviour: BehaviorSubject<string> = new BehaviorSubject<string>('');
 
  invoiceTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  hypothecation$ = new BehaviorSubject<SelectList[]>(undefined);
  customerCodeList$ = new BehaviorSubject<SelectList[]>(undefined);
  customerDetail$ = new BehaviorSubject<CustomerByCustomerCode>(undefined);
  dcByCustomerCode$ = new BehaviorSubject<InvoiceDcByCustomerCode[]>(undefined);
  invoiceMaterialByDc$ = new BehaviorSubject<InvoiceMaterialByDc[]>(undefined);
  unselectedDeliveryChallanTableId$ = new BehaviorSubject<string>(undefined);
  private accessoryTable: FormArray;
  private materialTable: FormArray;
  private saveInvoiceJSON$ = new BehaviorSubject<SaveInvoice>(undefined);
  private getMaterialAndAccessoryTable$ = new BehaviorSubject<{
    accessory: any[],
    material: any[]
  }>({
    accessory: undefined,
    material: undefined
  });
  updateTotalInvoiceCalcForm$ = new BehaviorSubject<object>(undefined);
  private invoiceStatus$ = new BehaviorSubject<boolean>(undefined);
  invoiceStatusChanges = this.invoiceStatus$.asObservable();
  private invoiceStatusMap = new Map<string, boolean>();
  private accessoryTableForm: FormGroup;
  private materialTableForm: FormGroup;
  private invoiceForm: FormGroup;
  private totalInvoiceCalcForm: FormGroup;
  private insuranceForm: FormGroup;
  private invoiceInsuranceDetail$ = new BehaviorSubject<object>(undefined);
  private invoiceCancellationFrom: FormGroup;
  constructor(
    private saveInvoiceAdaptorService: SaveInvoiceAdaptorService,
    private fb: FormBuilder
  ) { }
  saveAccessoryTable(tableForm: FormGroup) {
    this.accessoryTable = tableForm.get('table') as FormArray;
    this.accessoryTableForm = tableForm;
  }
  saveMaterialTable(materialTableForm: FormGroup) {
    this.materialTable = materialTableForm.get('table') as FormArray;
    this.materialTableForm = materialTableForm;
  }
  saveInvoiceForm(invoiceForm: FormGroup) {
    this.invoiceForm = invoiceForm;
  }
  saveTotalInvoiceCalcForm(totalInvoiceCalcForm: FormGroup) {
    this.totalInvoiceCalcForm = totalInvoiceCalcForm;
  }
  saveInsuranceForm(insuranceForm: FormGroup) {
    this.insuranceForm = insuranceForm;
  }
  private createInvoiceCancellationFrom() {
    this.invoiceCancellationFrom = this.fb.group({
      cancellationType: ['', Validators.compose([Validators.required])],
      invoiceCancellationReason: ['', Validators.compose([])],
      /*brand: ['', Validators.compose([])],
      model: ['', Validators.compose([])],*/
      reason: ['', Validators.compose([])],
      remarks: ['', Validators.compose([Validators.required])],
    })
    return this.invoiceCancellationFrom;
  }

  public get getInvoiceCancellationFrom(): FormGroup {
    if (this.invoiceCancellationFrom) {
      return this.invoiceCancellationFrom;
    }
    return this.createInvoiceCancellationFrom();
  }
  getInvoiceCancellationFormData() {
    return this.invoiceCancellationFrom.getRawValue()
  }
  clearInvoiceCancellationFrom() {
    this.invoiceCancellationFrom.reset();
  }

  getMaterialAndAccessoryTable() {
    const materialAndAccessoryTable = {} as { accessory: object[], material: object[] }
    console.log('this.accessoryTable.getRawValue()---------',this.accessoryTable.getRawValue())
    if (this.accessoryTable && this.accessoryTable.getRawValue()) {
      materialAndAccessoryTable.accessory = this.accessoryTable.getRawValue();
    }
    if (this.materialTable && this.materialTable.getRawValue()) {
      materialAndAccessoryTable.material = this.materialTable.getRawValue();
    }
    this.getMaterialAndAccessoryTable$.next(materialAndAccessoryTable)
    return this.getMaterialAndAccessoryTable$.value;
  }
  calcTotalInvoiceAmount(reCalcColumn: string) {
    let accessoryMaterial = this.getMaterialAndAccessoryTable();
    let accessoryMaterialList = [...accessoryMaterial.accessory || [], ...accessoryMaterial.material || []];
    let changedValue = 0;
    accessoryMaterialList.forEach(row => {
      changedValue = changedValue + (row[reCalcColumn] || 0);
    });
    console.log('accessoryMaterialList',accessoryMaterialList)
    let formObj = {}
    if (reCalcColumn === 'basicAmount') {
      formObj['baseAmount'] = changedValue;
    }
    if (reCalcColumn === 'gstAmount') {
      formObj['totalGSTAmount'] = changedValue;
    }
    this.updateTotalInvoiceCalcForm$.next(formObj);
  }
  dispatch(state: any, type?: string) {
    console.log('InvoiceForm.type', state.constructor.type);
    switch (state && state.constructor && state.constructor.type) {
      case '[Invoice] InsuranceDetail':
        this.invoiceInsuranceDetail$.next(state);
        break;

      default:
        break;
    }
  }
  select(state: any) {
    console.log('state.type', state.type);

    switch (state && state.type) {
      case '[Invoice] InsuranceDetail':
        return this.invoiceInsuranceDetail$.asObservable();
      default:
        return of(null);
    }
  }
  /* -------- save invoice form data & update valid status --------- */
  private updateFinalInvoiceRecord() {
    console.log('UUUUUUUUU inv acc insu', this.invoiceForm.getRawValue(), this.accessoryTableForm.getRawValue(), this.insuranceForm.getRawValue());

    let record = {} as UpdateFinalInvoiceRecord
    record.invoiceForm = this.invoiceForm.getRawValue();
    record.accessoryTableForm = this.accessoryTableForm.getRawValue();
    record.materialTableForm = this.materialTableForm.getRawValue();
    record.totalInvoiceCalcForm = this.totalInvoiceCalcForm.getRawValue();
    record.insuranceForm = this.insuranceForm.getRawValue();
    console.log('record', record);
    const updatedProperties = this.saveInvoiceAdaptorService.saveAdapt<UpdateFinalInvoiceRecord>(record);
    console.log('updatedProperties!!!!!!!', updatedProperties);
    this.saveInvoiceJSON$.next(updatedProperties);
  }
  getFinalInvoiceRecord() {
    if (this.validateInvoice()) {
      this.updateFinalInvoiceRecord();
    }
    return this.saveInvoiceJSON$.value;
  }
  updateInvoiceStatus(formName: string, status: boolean) {
    this.invoiceStatusMap.set(formName, status);
    let invoiceStatus = true;
    this.invoiceStatusMap.forEach(stus => {
      console.log('stus', stus);
      if (!stus) {
        invoiceStatus = false;
      }
    });
    this.invoiceStatus$.next(invoiceStatus);
  }
  validateCancelInvoice(): boolean {
    this.invoiceCancellationFrom.markAllAsTouched();
    return this.invoiceCancellationFrom.valid;
  }
  validateInvoice(): boolean {
    this.markAllAsTouched();
    console.log(this.invoiceForm.valid , this.accessoryTableForm.valid , this.materialTableForm.valid , this.totalInvoiceCalcForm.valid , this.insuranceForm.valid)
    if (this.invoiceForm.valid && this.accessoryTableForm.valid && this.materialTableForm.valid && this.totalInvoiceCalcForm.valid && this.insuranceForm.valid) {
      this.invoiceStatus$.next(true);
      return true;
    }
    this.invoiceStatus$.next(false);
    return false;
  }
  private markAllAsTouched() {
    this.invoiceForm.markAllAsTouched();
    this.accessoryTableForm.markAllAsTouched();
    this.materialTableForm.markAllAsTouched();
    this.totalInvoiceCalcForm.markAllAsTouched();
    this.insuranceForm.markAllAsTouched();
  }
  resetAllForm() {
    this.invoiceForm.reset();
    (this.accessoryTableForm.get('table') as FormArray).clear();
    (this.materialTableForm.get('table') as FormArray).clear();
    this.totalInvoiceCalcForm.reset();
    this.insuranceForm.reset();
    this.invoiceStatus$.next(false);
    this.dcByCustomerCode$.next(null);
    this.customerDetail$.next(null);
    this.invoiceInsuranceDetail$.next(null);
  }
  resetMaterialAndAccessoryTable() {
    if(this.accessoryTableForm){
        (this.accessoryTableForm.get('table') as FormArray).clear();
    }
    if(this.materialTableForm){  
        (this.materialTableForm.get('table') as FormArray).clear();
    }
    if(this.totalInvoiceCalcForm){
        this.totalInvoiceCalcForm.reset();
    }
  }
  /* --------------------------------------------------------------- */
}
