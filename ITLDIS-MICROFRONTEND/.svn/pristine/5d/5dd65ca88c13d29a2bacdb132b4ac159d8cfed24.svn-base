import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class SourcePageStore {

    private readonly _sourceCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._sourceCreateForm = this.formBuilder.group({
            sourceDetailsForm: this.buildSourceDetailsForm(),
        })
    }

    get sourceForm() {
        return this._sourceCreateForm
    }

    private buildSourceDetailsForm() {
        return this.formBuilder.group({
            sourceCode: ['', Validators.compose([Validators.required])],
            sourceName: ['', Validators.compose([Validators.required])],
            purpose: ['', Validators.compose([Validators.required])],
        })
    }

}