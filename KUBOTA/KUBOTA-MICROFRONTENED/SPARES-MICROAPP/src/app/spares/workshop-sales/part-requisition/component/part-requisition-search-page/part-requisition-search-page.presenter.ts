import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PartRequisitionSearchPageStore } from './part-requisition-search-page.store';

@Injectable()
export class PartRequisitionSearchPagePresenter {
    private partRequisitionSearchPageStore:PartRequisitionSearchPageStore;
    public readonly partRequisitionSearchForm: FormGroup;
    constructor(fb: FormBuilder) {
        this.partRequisitionSearchPageStore = new PartRequisitionSearchPageStore(fb);
        this.partRequisitionSearchForm = this.partRequisitionSearchPageStore.partRequisitionSearchForm;
    }

}