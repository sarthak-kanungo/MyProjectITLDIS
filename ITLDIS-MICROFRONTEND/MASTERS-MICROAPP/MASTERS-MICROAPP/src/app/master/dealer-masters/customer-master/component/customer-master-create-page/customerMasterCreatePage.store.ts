import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators'

@Injectable()
export class CustomerMasterCreatePageStore {

    private readonly _customerMasterCreateForm: FormGroup
    private readonly _customerMasterChangeRequestForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._customerMasterCreateForm = this.formBuilder.group({
            customerCreateDetailsForm: this.createCustomerFormDetails(),
            createCustomerAddressDetails: this.createCustomerAddressDetails(),
            createCustomerLandDetails: this.createCustomerLandDetails(),

            cropsForm: this.formBuilder.group({
                cropsTable: this.formBuilder.array([])
            }),

            purchaseDetailsForm: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),

            vehicalDetailsForm: this.formBuilder.group({
                vehicalDetailsTable: this.formBuilder.array([])
            })
        })
        
        this._customerMasterChangeRequestForm = this.formBuilder.group({
            customerCreateDetailsForm: this.createCustomerFormDetails(),
            createCustomerAddressDetails: this.createCustomerAddressDetails(),
            createCustomerLandDetails: this.createCustomerLandDetails(),

            cropsForm: this.formBuilder.group({
                cropsTable: this.formBuilder.array([])
            }),

            purchaseDetailsForm: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),

            vehicalDetailsForm: this.formBuilder.group({
                vehicalDetailsTable: this.formBuilder.array([])
            })
        })
        
    }

    get customerDetailsCreateForm() {
        return this._customerMasterCreateForm
    }

    get customerMasterChangeRequestForm() {
        return this._customerMasterChangeRequestForm
    }

    private createCustomerFormDetails() {
        return this.formBuilder.group({
            id : [null, Validators.compose([Validators.required])],
            customerCode: [null, Validators.compose([Validators.required])],
            editCount:[{value:0}],
            //enquiryNo: [null, Validators.compose([])],
            customerType: [null, Validators.compose([Validators.required])],
            //customerGroup: [null, Validators.compose([Validators.required])],
            //groupOwner: [{ value: null, disabled: true }, Validators.compose([])],
            //category: [{ value: null, disabled: true }, Validators.compose([])],
            title: [null, Validators.compose([Validators.required])],
            firstName: [null, Validators.compose([Validators.required])],
            middleName: [null, Validators.compose([])],
            lastName: [null, Validators.compose([Validators.required])],
            mobileNumber: [null, Validators.compose([CustomValidators.mobileNumber,Validators.required])],
            alternateMobileNumber: [null, Validators.compose([CustomValidators.mobileNumber])],
            whatsAppNumber: [null, Validators.compose([CustomValidators.mobileNumber])],
            std: [null, Validators.compose([CustomValidators.numberOnly])],
            telephoneNumber: [null, Validators.compose([CustomValidators.numberOnly])],
            panNumber: [null, Validators.compose([Validators.pattern('[A-Z]{5}[0-9]{4}[A-Z]{1}')])],
            aadharNo: [null, Validators.compose([CustomValidators.numberOnly, CustomValidators.minLength(12)])],
            gstNumber: [null,  Validators.compose([Validators.pattern(/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/)])],
            dateofBirth: [null, Validators.compose([])],
            occupation: [null, Validators.compose([Validators.required])],
            language: [null, Validators.compose([])],
            recordType:[null]
        })
    }

    private createCustomerAddressDetails() {
        return this.formBuilder.group({
            //addressType: [null, Validators.compose([Validators.required])],
            address1: [null, Validators.compose([Validators.required])],
            address2: [null],
            address3: [null],
            pinId: [null, Validators.compose([Validators.required])],
            pinCode: [null, Validators.compose([Validators.required])],
            district: [null, Validators.compose([Validators.required])],
            tehsil: [{ value: null, disabled: true }],
            postOffice: [{ value: null, disabled: true }],
            city: [{ value: null, disabled: true }],
            state: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
        })
    }

    private createCustomerLandDetails() {
        return this.formBuilder.group({
            soilType: [null, Validators.compose([Validators.required])],
            landSize: [null, Validators.compose([Validators.required])],
            cropName: [null, Validators.compose([Validators.required])],
        })
    }


    purchaseDetailsRows() {
        return this.formBuilder.group({
            isSelected: [null],
            brand: [null],
            model: [null],
            yearOfPurchase: [null],
            serialno: [null],
            modelEditable:['N']
        })
    }

    vehicalDetailsForm() {
        return this.formBuilder.group({
            modelcode: [null],
            modeldesc: [null],
            chassisno: [null],
            registrationno: [null],
        })
    }

}