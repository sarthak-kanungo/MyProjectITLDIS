import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class PartyMasterPageStore {

    private readonly _partyMasterCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._partyMasterCreateForm = this.formBuilder.group({
            partyCreateDetailsForm: this.searchPartyFormDetails(),
            partyCreateAddressDetailsForm: this.partyMasterAddressFormDetails(),

        })
    }

    get partyMasterCreateForm() {
        return this._partyMasterCreateForm
    }

    private searchPartyFormDetails() {
        return this.formBuilder.group({
            branchCode: [null, Validators.compose([Validators.required])],
            category: [null, Validators.compose([Validators.required])],
            partyCategoryId: [null, Validators.compose([Validators.required])],
            partyCode: [null],
            partyName: [null, Validators.compose([Validators.required])],
            partyLocation: [null, Validators.compose([Validators.required])],
            gstNumber: [null, Validators.compose([])],
            panNumber: [null, Validators.compose([])],
            adharCardNumber: [null, Validators.compose([])],
            title: [null, Validators.compose([Validators.required])],
            firstName: [null, Validators.compose([Validators.required])],
            middleName: [null, Validators.compose([])],
            lastName: [null, Validators.compose([])],
            designation: [null, Validators.compose([])],
            mobileNumber: [null, Validators.compose([CustomValidators.mobileNumber])],
            // email: [null, Validators.compose([Validators.email])],
        })
    }

    private partyMasterAddressFormDetails() {
        return this.formBuilder.group({
            address1: [null, Validators.compose([Validators.required])],
            address2: [null, Validators.compose([])],
            address3: [null, Validators.compose([])],
            pinId: [null, Validators.compose([Validators.required,])],
            pinCode: [null, Validators.compose([Validators.required,])],
            postOffice:[null, Validators.compose([])],
            district:[null, Validators.compose([])],
            locality: [null, Validators.compose([])],
            tehsil: [ null, Validators.compose([])],
            city: [null, Validators.compose([])],
            state: [ null, Validators.compose([])],
            country: [{ value: null, disabled: true }, Validators.compose([])],
            std: [null, Validators.compose([])],
            telephone: [null, Validators.compose([])],
            email: [null, Validators.compose([Validators.email])],
        })
    }

}