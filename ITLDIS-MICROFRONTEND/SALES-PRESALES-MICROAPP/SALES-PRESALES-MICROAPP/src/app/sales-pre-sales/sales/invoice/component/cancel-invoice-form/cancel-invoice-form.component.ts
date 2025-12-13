import { Component, OnInit, Input,OnChanges,SimpleChanges } from '@angular/core';
import { CancelInvoiceFormService } from './cancel-invoice-form.service';
import { FormGroup, Validators } from '@angular/forms';
import { CancelInvoiceFormApiService } from './cancel-invoice-form-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-cancel-invoice-form',
  templateUrl: './cancel-invoice-form.component.html',
  styleUrls: ['./cancel-invoice-form.component.scss'],
  providers: [CancelInvoiceFormService, CancelInvoiceFormApiService]
})
export class CancelInvoiceFormComponent implements OnInit, OnChanges {
  invoiceCancellationFrom: FormGroup
  labelPosition
  show

  cencellationTypes: SelectList[];
  cancellationReasons: SelectList[];

  brands: SelectList[];

  @Input() isCancelInvoice: boolean;
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  @Input() cancelRequestDetail:any;
  cancellationOtherReason: SelectList[];

  constructor(private cancelInvoiceFormService: CancelInvoiceFormService) { }

  ngOnInit() {

    this.invoiceCancellationFrom = this.cancelInvoiceFormService.createInvoiceCancellationFrom();
    this.setInvoiceCancellationType();
    this.setInvoiceCancellationReason()
    //this.setBrand()
    this.setInvoiceCancellationOtherReason();
    if(this.isView){
        this.invoiceCancellationFrom.disable();
    }
  }
  
  ngOnChanges(changes: SimpleChanges): void { 
      if (!!changes.cancelRequestDetail && changes.cancelRequestDetail.currentValue) {
          this.show = true;
          this.invoiceCancellationFrom.controls.cancellationType.patchValue(changes.cancelRequestDetail.currentValue.invCancellationType);
          this.invoiceCancellationFrom.controls.invoiceCancellationReason.patchValue(changes.cancelRequestDetail.currentValue.invCancellationReason);
          this.invoiceCancellationFrom.controls.reason.patchValue(changes.cancelRequestDetail.currentValue.invCancellationOtherReason);
          this.invoiceCancellationFrom.controls.remarks.patchValue(changes.cancelRequestDetail.currentValue.invCancelRemark);
      }
    
  }
  
  private setInvoiceCancellationType() {
    this.cancelInvoiceFormService.getInvoiceCancellationType().subscribe(res => {
      this.cencellationTypes = res;
    })
  }
  private setInvoiceCancellationReason() {
    this.cancelInvoiceFormService.getInvoiceCancellationReason().subscribe(res => {
      this.cancellationReasons = res;
    })
  }
 /* private setBrand() {
    this.cancelInvoiceFormService.getBrand().subscribe(res => {
      this.brands = res;
    })
  }*/
  private setInvoiceCancellationOtherReason() {
    this.cancelInvoiceFormService.getInvoiceCancellationOtherReason().subscribe(res => {
      this.cancellationOtherReason = res;
    })
  }
  cancellationTypeSelectionChange(selectedCancellationType: MatSelectChange) {
    switch (selectedCancellationType.value.toLocaleLowerCase()) {
      case 'lost':
        this.applyCustomValidatorToInvoiceCancellationForm();
        break;
      case 'machine change':
        this.clearCustomValidatorToInvoiceCancellationForm();
        break;
      case 'quality issue':
        this.applyCustomValidatorToInvoiceCancellationForm();
        break;
      default:
        this.clearCustomValidatorToInvoiceCancellationForm();
    }
  }
  applyCustomValidatorToInvoiceCancellationForm() {
    this.invoiceCancellationFrom.get('invoiceCancellationReason').setValidators(Validators.required);
    //this.invoiceCancellationFrom.get('brand').setValidators(Validators.required);
    //this.invoiceCancellationFrom.get('model').setValidators(Validators.required);
    this.invoiceCancellationFrom.get('reason').setValidators(Validators.required);
    this.invoiceCancellationFrom.get('invoiceCancellationReason').updateValueAndValidity();
    /*this.invoiceCancellationFrom.get('brand').updateValueAndValidity();
    this.invoiceCancellationFrom.get('model').updateValueAndValidity();*/
    this.invoiceCancellationFrom.get('reason').updateValueAndValidity();
  }
  clearCustomValidatorToInvoiceCancellationForm() {
    this.invoiceCancellationFrom.get('invoiceCancellationReason').clearValidators();
    //this.invoiceCancellationFrom.get('brand').clearValidators();
    //this.invoiceCancellationFrom.get('model').clearValidators();
    this.invoiceCancellationFrom.get('reason').clearValidators();
    this.invoiceCancellationFrom.get('invoiceCancellationReason').updateValueAndValidity();
    /*this.invoiceCancellationFrom.get('brand').updateValueAndValidity();
    this.invoiceCancellationFrom.get('model').updateValueAndValidity();*/
    this.invoiceCancellationFrom.get('reason').updateValueAndValidity();
  }
}
