import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SourcePageStore } from './source-page.store';

@Injectable()
export class SourcePagePresenter {

    readonly sourceForm: FormGroup

    constructor(
        private sourcePageStore: SourcePageStore
    ) {
        this.sourceForm = this.sourcePageStore.sourceForm
    }

    get detailsForm() {
        return this.sourceForm.get('sourceDetailsForm') as FormGroup
    }

    markFormAsTouched() {
        this.sourceForm.markAllAsTouched();
    }


}