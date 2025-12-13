import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class TransporterPageStore {

    private readonly _transporterCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._transporterCreateForm = this.formBuilder.group({
            transporterDetailsForm: this.buildTransporterDetailsForm(),
            addressDetailsForm: this.buildAddressDetailsForm()
        })
    }

    get transporterCreateForm() {
        return this._transporterCreateForm
    }

    private buildTransporterDetailsForm(){
        return this.formBuilder.group({
            transporterCode: ['',],
            transporterName: ['', Validators.compose([Validators.required])],
            transporterLocation:['', Validators.compose([Validators.required])],
            gstNumber:['',],
            panNumber: ['',],
            adharNumber:['',],
            title:['', Validators.compose([Validators.required])],
            firstName:['', Validators.compose([Validators.required])],
            middleName:['',],
            lastName:['',],
            designation:['',],
            email:[''],
            mobileNo:['', Validators.compose([Validators.required])]    
        })
    }
    
    private buildAddressDetailsForm() {
        return this.formBuilder.group({
            address1: ['', Validators.compose([Validators.required])],
            address2: ['', Validators.compose([])],
            address3: ['', Validators.compose([])],
            pinCode: ['', Validators.compose([Validators.required])],
            locality: ['', Validators.compose([Validators.required])],
            tehsil: [{ value: '', disabled: true }, Validators.compose([])],
            city: [{ value: '', disabled: true }, Validators.compose([])],
            state: [{ value: '', disabled: true }, Validators.compose([])],
            country: [{ value: '', disabled: true }, Validators.compose([])],
            email: ['', Validators.compose([])],
            telephone: ['', Validators.compose([])]
        })
    }
}