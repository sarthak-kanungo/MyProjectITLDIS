import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { MachineMasterReportPageStore } from "./machine-master-report-page-store";


@Injectable()
export class MachineMasterReportPagePresenter {

    readonly mmReportForm: FormGroup
  

    constructor(private pageStore: MachineMasterReportPageStore) {
        this.mmReportForm = this.pageStore.machineMasterReportForm
        
    }

    get mmrForm() {
        return this.mmReportForm.get('machineMasterReportForm') as FormGroup
    }


}