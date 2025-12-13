import { Component, OnInit, Input } from '@angular/core';
import { InvoiceInsuranceDetailsService } from './invoice-insurance-details.service';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { InvoiceInsuranceDetailApiService } from './invoice-insurance-detail-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { InsuranceDetailAdaptorService } from '../../model/insurance-detail-adaptor.service';
import { DateService } from '../../../../../root-service/date.service';

@Component({
  selector: 'app-invoice-insurance-details',
  templateUrl: './invoice-insurance-details.component.html',
  styleUrls: ['./invoice-insurance-details.component.scss'],
  providers: [
    InvoiceInsuranceDetailsService,
    InvoiceInsuranceDetailApiService,
    InsuranceDetailAdaptorService
  ]
})
export class InvoiceInsuranceDetailsComponent implements OnInit {

  disable = true;
  insuranceCompanyCodeList$: Observable<SelectList[]>;
  selectedInsuranceCompanyCode: any;
  invoiceInsuranceDetail: any;
  insuranceForm: FormGroup
  minPolicyExpiryDate: Date;
  @Input() isCancelInvoice: boolean;
  @Input() insuranceCompanyDetail
  constructor(
    private invoiceInsuranceDetailsService: InvoiceInsuranceDetailsService,
    private dateService: DateService
  ) { }

  ngOnInit() {
    this.insuranceForm = this.invoiceInsuranceDetailsService.createInsuranceForm();
    this.patchValueToInsuranceForm();
    this.disableInsuranceFormAtCancelInvoice();
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    if (!!changes.insuranceCompanyDetail && changes.insuranceCompanyDetail.currentValue) {
      this.invoiceInsuranceDetailsService.adaptInvoiceInsuranceDetailAndDispatchToStore(changes.insuranceCompanyDetail.currentValue);
      this.invoiceInsuranceDetailsService.getInvoiceInsuranceDetail().subscribe(res => {
        this.invoiceInsuranceDetail = res;
        this.patchValueToInsuranceForm();
      });
    }
    //if (!!changes.isCancelInvoice) {
      this.disableInsuranceFormAtCancelInvoice();
    //}
  }
  private disableInsuranceFormAtCancelInvoice() {
    if (this.insuranceForm) {
      this.insuranceForm.disable();
    }
  }
  private patchValueToInsuranceForm() {
    if (this.insuranceForm && this.invoiceInsuranceDetail) {
      this.insuranceForm.get('insuranceCompanyMaster').patchValue({
        value: this.invoiceInsuranceDetail.companyCode
      });
      if (this.insuranceCompanyDetail.policyStartDate) {
        this.insuranceForm.get('policyStartDate').patchValue(this.insuranceCompanyDetail.policyStartDate);
      }
      if (this.insuranceCompanyDetail.policyExpiryDate) {
        this.insuranceForm.get('policyExpiryDate').patchValue(this.insuranceCompanyDetail.policyExpiryDate)
      }
    }
  }
  insuranceCompanyCodeChanges(seachValue: string) {
    console.log('event', event);
    this.insuranceCompanyCodeList$ = this.invoiceInsuranceDetailsService.searchInsuranceCompanyCode(seachValue);

  }
  insuranceCompanyCodeSelected(event: MatAutocompleteSelectedEvent) {
    this.selectedInsuranceCompanyCode = event.option.value;
    if (this.selectedInsuranceCompanyCode) {
      this.invoiceInsuranceDetailsService.getInvoiceInsuranceDetail(this.selectedInsuranceCompanyCode.value).subscribe(res => {
        this.invoiceInsuranceDetail = res;
        console.log('invoiceInsuranceDetail', this.invoiceInsuranceDetail);
      })
    }
  }

  displayFn(obj: SelectList): string | number | undefined {
    return obj ? obj.value : undefined;
  }
}
