import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { QaReportPageStore } from "./qa-report-page.store";


@Injectable()
export class QaReportPagePagePresenter {

    readonly _qaForm: FormGroup
  

    constructor(private pageStore: QaReportPageStore) {
        this._qaForm = this.pageStore.qaReportFormForm
        
    }

    get reportForm() {
        return this._qaForm.get('qaReportFormForm') as FormGroup
    }


}