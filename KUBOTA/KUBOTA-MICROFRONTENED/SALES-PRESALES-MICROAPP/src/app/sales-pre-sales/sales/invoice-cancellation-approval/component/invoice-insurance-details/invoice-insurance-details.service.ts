import { Injectable } from '@angular/core';
import { InvoiceInsuranceDetailApiService } from './invoice-insurance-detail-api.service';
import { map } from 'rxjs/operators';
import { FormBuilder, FormGroup } from '@angular/forms';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InsuranceDetail } from '../../model/insurance-detail';
import { InsuranceDetailAdaptorService } from '../../model/insurance-detail-adaptor.service';

@Injectable()
export class InvoiceInsuranceDetailsService {

  private insuranceForm: FormGroup;
  constructor(
    private invoiceInsuranceDetailApiService: InvoiceInsuranceDetailApiService,
    private fb: FormBuilder,
    private invoiceStoreService: InvoiceStoreService,
    private insuranceDetailAdaptorService: InsuranceDetailAdaptorService
  ) { }
  createInsuranceForm() {
    this.insuranceForm = this.fb.group({
      insuranceCompanyMaster: [null],
      policyStartDate: [null],
      policyExpiryDate: [null]
    });
    this.invoiceStoreService.saveInsuranceForm(this.insuranceForm);
    return this.insuranceForm;
  }
  searchInsuranceCompanyCode(searchValue: string) {
    return this.invoiceInsuranceDetailApiService.searchInsuranceCompanyCode(searchValue).pipe(map(res => res.result));
  }
  private getCompanyDetailsFromCode(companyCode: string) {
    return this.invoiceInsuranceDetailApiService.getCompanyDetailsFromCode(companyCode).pipe(
      map(res => res.result)
    )
  }
  getInvoiceInsuranceDetail(companyCode?: string) {
    if (companyCode) {
      this.getCompanyDetailsFromCode(companyCode).subscribe(res => {
        this.invoiceStoreService.dispatch(res);
      });
    }
    return this.invoiceStoreService.select(InsuranceDetail);
  }
  adaptInvoiceInsuranceDetailAndDispatchToStore(invoiceInsuranceDetail) {
    const modifiedInvoiceInsuranceDetail = this.insuranceDetailAdaptorService.adapt(invoiceInsuranceDetail);
    this.invoiceStoreService.dispatch(modifiedInvoiceInsuranceDetail);
  }

}
