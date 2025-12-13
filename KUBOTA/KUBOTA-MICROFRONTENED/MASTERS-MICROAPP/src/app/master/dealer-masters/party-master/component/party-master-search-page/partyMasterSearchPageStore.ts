import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class PartyMasterSearchPageStore {

    private readonly _partyMasterSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._partyMasterSearchForm = this.formBuilder.group({
            partySearchDetailsForm: this.searchPartyFormDetails(),

        })
    }

    get partySearchForm() {
        return this._partyMasterSearchForm
    }

    private searchPartyFormDetails() {
        return this.formBuilder.group({

            partyCode: [null, Validators.compose([])],
            partyName: [null, Validators.compose([])],
            page: [null],
            size:[null]
        })
    }
}