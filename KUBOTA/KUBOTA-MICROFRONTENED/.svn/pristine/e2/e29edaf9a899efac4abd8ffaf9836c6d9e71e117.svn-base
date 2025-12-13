import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PartyMasterSearchPageStore } from './partyMasterSearchPageStore';

@Injectable()
export class PartyMasterSearchPagePresenter {

    readonly searchPartyMasterForm: FormGroup

    constructor(
        private partyMasterSearchPageStore: PartyMasterSearchPageStore
    ) {
        this.searchPartyMasterForm = this.partyMasterSearchPageStore.partySearchForm
    }

    get partySearchDetailsForm() {
        return this.searchPartyMasterForm.get('partySearchDetailsForm') as FormGroup
    }

}