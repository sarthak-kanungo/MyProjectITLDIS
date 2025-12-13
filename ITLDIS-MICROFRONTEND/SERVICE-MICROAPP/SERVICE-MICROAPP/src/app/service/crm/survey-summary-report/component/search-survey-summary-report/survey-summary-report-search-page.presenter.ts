import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { SurveySummaryReportSearchPageStore } from "./survey-summary-report-search-page.store";


@Injectable()
export class SurveySummaryReportSearchPagePresenter {

    readonly _surveySummaryReportSearchForm: FormGroup

    private _operations: string

    set operation(type: string) {
      this._operations = type
    }
    get operation() {
      return this._operations
    }
  

    constructor(
        private surveySummaryReportSearchPageStore: SurveySummaryReportSearchPageStore
    ) {
        this._surveySummaryReportSearchForm = this.surveySummaryReportSearchPageStore.searchSurveySummaryReportForm
    }

    markFormAsTouched() {
        this._surveySummaryReportSearchForm.markAllAsTouched();
    }

    get surveySummaryReportForm() {
        return this._surveySummaryReportSearchForm.get('surveySummaryReportForm') as FormGroup
    }


}