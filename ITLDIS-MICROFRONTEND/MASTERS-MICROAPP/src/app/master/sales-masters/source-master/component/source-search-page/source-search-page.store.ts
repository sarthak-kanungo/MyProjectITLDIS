import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class SourceSearchPageStore {

    private readonly _sourceSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._sourceSearchForm = this.formBuilder.group({
            sourceSearchDetailsForm: this.buildSourcSearcheDetailsForm(),
        })
    }

    get sourceSearchForm() {
        return this._sourceSearchForm
    }

    private buildSourcSearcheDetailsForm() {
        return this.formBuilder.group({
            sourceCode: [null],
            sourceName: [null],
            purpose: [null],
        })
    }

}