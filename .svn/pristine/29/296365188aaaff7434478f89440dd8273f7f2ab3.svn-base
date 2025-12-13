import { Injectable } from "@angular/core";
import { FormGroup } from '@angular/forms';
import { JobCardSearchStore } from './search-job-card-store';

@Injectable()
export class JobCardSearchPresenter {
    readonly jobCardSearchForm: FormGroup
    constructor(
        private jobCardSearchStore: JobCardSearchStore
    ) {
        this.jobCardSearchForm = this.jobCardSearchStore.jobCardSearch
    }
    get jobCardBasicSearchForm() {
        return this.jobCardSearchForm.get('basicJobCardSearch') as FormGroup
    }

}