import { Injectable } from "@angular/core";
import { WcrReportPageStore } from "./wcr-report-create-page.store";
import { FormGroup, FormArray } from "@angular/forms";
import { WcrDetail, CreditDetails } from "../../domain/wcr-report.domain";

@Injectable()
export class WcrReportPagePresenter {
  private _operation: string;
  selectedWcrId: string = '';
  constructor(private wcrReportPageStore: WcrReportPageStore) {}

  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }

  get wcrReportForm(): FormGroup {
    return this.wcrReportPageStore.allForm.get("wcrReportForm") as FormGroup;
  }
  get wcrReportDetailForm(): FormArray {
    return this.wcrReportPageStore.allForm.get("wcrReportDetailForm") as FormArray;
  }
  get wcrReportCreditDetailForm(): FormArray {
    return this.wcrReportPageStore.allForm.get("wcrReportCreditDetailForm") as FormArray;
  }

  addRowInWcrReportDetail(details?: WcrDetail) {
    const control = this.wcrReportDetailForm;
    const newForm = this.wcrReportPageStore.initializeWcrReportDetailForm(details);
    control.push(newForm);
  }

  
  addRowInWcrReportCreditDetail(creditDetails?: WcrDetail) {
    
    const control = this.wcrReportCreditDetailForm;
    const newForm = this.wcrReportPageStore.initializeWcrReportCreditDetailForm(
      this.creditDetailModel(creditDetails)
    );
    control.push(newForm);
  }
  wcrId(id: string) {
    if (id != null) {
      this.selectedWcrId = id;
    }
  }

  creditDetailModel(details: WcrDetail) {
    const creditDetail = {} as CreditDetails;

    creditDetail.installationDate = details.installationDate;
    creditDetail.failureDate = details.failureDate;
    creditDetail.invoiceNo = details.invoiceNo;
    creditDetail.creditNoteNo = details.creditNoteNo;
    creditDetail.creditNotedate = details.creditNotedate;
    creditDetail.creditNoteReqNo = details.creditNoteReqNo;
    creditDetail.status = details.status;
    creditDetail.serviceDealerName = details.serviceDealerName;
    creditDetail.amount = details.amount;

    return creditDetail;
  }
}
