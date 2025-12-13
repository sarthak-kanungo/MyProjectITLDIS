import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { WcrDetail, CreditDetails } from '../../domain/wcr-report.domain';
@Injectable()
export class WcrReportPageStore {
  private readonly form: FormGroup;
  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      wcrReportForm: this.wcrReportForm(),
      wcrReportDetailForm: this.fb.array([]),
      wcrReportCreditDetailForm: this.fb.array([])
    });
  }

  /* private createForm() {
    this.form = this.fb.group({
      wcrReportForm: this.wcrReportForm(),
      wcrReportDetailForm: this.fb.array([]),
      wcrReportCreditDetailForm: this.fb.array([])
    });
  } */

  private wcrReportForm() {
    const wcrReportForm = this.fb.group({
      wcrType: [{value: null, disabled: false}, Validators.required],
      status: [{value: null, disabled: false}],
      fromDate: [{value: null, disabled: false}],
      toDate: [{value: null, disabled: false}],
      model: [{value: null, disabled: false}],
      chassisNo: [{value: null, disabled: false}],
      partNo: [{value: null, disabled: false}],
      dealerCode: [{value: null, disabled: false}],
      state: [{value: null, disabled: false}],
      creditNoteReqNo: [{value: null, disabled: false}],
      dealerName: [{value: null, disabled: false}],
    });
    return wcrReportForm;
  }
  private wcrReportDetailForm(details?: WcrDetail) {
    
    const wcrReportDetailForm = this.fb.group({
      isSelect: [{value: details.isSelect, disabled: false}],
      wcrNo: [{value: details.wcrNo, disabled: false}],
      wcrDate: [{value: details.wcrDate, disabled: false}],
      jobCardNo: [{value: details.jobCardNo, disabled: false}],
      pcrNo: [{value: details.pcrNo, disabled: false}],
      model: [{value: details.model, disabled: false}],
      chassisNo: [{value: details.chassisNo, disabled: false}],
      engineNo: [{value: details.engineNo, disabled: false}],
      hour: [{value: details.hour, disabled: false}],
      typeOfClaim: [{value: details.typeOfClaim, disabled: false}],
      noOfTime: [{value: details.noOfTime, disabled: false}],
      claimValue: [{value: details.claimValue, disabled: false}],
      id: [details.id]
    });
    return wcrReportDetailForm;
  }
  private wcrReportCreditDetailForm(creditDetails?: CreditDetails) {
    const wcrReportCreditDetailForm = this.fb.group({
      installationDate: [{value: creditDetails.installationDate, disabled: false}],
      failureDate: [{value: creditDetails.failureDate, disabled: false}],
      invoiceNo: [{value: creditDetails.invoiceNo, disabled: false}],
      creditNoteNo: [{value: creditDetails.creditNoteNo, disabled: false}],
      creditNotedate: [{value: creditDetails.creditNotedate, disabled: false}],
      creditNoteReqNo: [{value: creditDetails.creditNoteReqNo, disabled: false}],
      status: [{value: creditDetails.status, disabled: false}],
      serviceDealerName: [{value: creditDetails.serviceDealerName, disabled: false}],
      amount: [{value: creditDetails.amount, disabled: false}],
    });
    return wcrReportCreditDetailForm;
  }

  initializeWcrReportDetailForm(details?: WcrDetail) {
    details.isSelect = false;
    return this.wcrReportDetailForm(details);
  }

  initializeWcrReportCreditDetailForm(creditDetails?: CreditDetails) {
    return this.wcrReportCreditDetailForm(creditDetails);
  }

  get allForm() {
    if (this.form) {
      return this.form as FormGroup;
    }
    /* this.createForm();
    return this.form as FormGroup; */
  }
}
