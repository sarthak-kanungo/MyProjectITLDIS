import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class TollFreePageStore {

    private readonly _tollFreeCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._tollFreeCreateForm = this.formBuilder.group({
            tollFreecallDetails: this.tollFreecallDetailsForm(),
            customerDetails: this.customerDetailsForm(),
            machineDetails: this.machineDetailsForm(),
            freeServiceHistoryDetails: this.freeServiceHistoryDetailsForm(),
            callHistoryDetails: this.callHistoryDetailsForm(),
            complaintDetails: this.complaintDetailsForm(),
            enquiryDetails: this.enquiryDetailsForm(),
            serveyHistoryDetails : this.serveyHistoryDetailsForm()
        })
    }

    get tollFreeCreateForm() {
        return this._tollFreeCreateForm
    }

    private tollFreecallDetailsForm() {
        return this.formBuilder.group({
            callNo: [{ value: null, disabled: true }],
            callDate: [{ value: null, disabled: true }],
            status: [{ value: null, disabled: true }],
            customerType: [null, Validators.compose([Validators.required])],
            contactPersonName: [null, Validators.compose([Validators.required])],
            contactPersonMobile: [null, Validators.compose([Validators.required, CustomValidators.numberOnly, CustomValidators.minLength(10)])],
            relationWithCustomer: [null, Validators.compose([Validators.required])],
            districtId : [null],
            additionalComment : [null],
            smstextoCustomer: [null],
        })
    }

    private customerDetailsForm() {
        return this.formBuilder.group({
            customerMasterId: [null],
            title: [{value:null, disabled:true}],
            firstName: [{value:null, disabled:true}],
            middleName: [{value:null, disabled:true}],
            lastName: [{value:null, disabled:true}],
            contactPersonName: [{value:null, disabled:true}],

            custCode: [{value:null, disabled:true}, Validators.compose([Validators.required])],
            soldDealerName: [{value:null, disabled:true}],
            mobileNumber: [null, Validators.compose([Validators.required])],
            alternateMobileNo: [{value:null, disabled:true}],
            telephone: [{value:null, disabled:true}],
            
            preferedLanguage: [{value:null, disabled:true}],
            panNo: [{value:null, disabled:true}],
            gstin: [{value:null, disabled:true}],
            contactPersonMobile: [{value:null, disabled:true}],
            relationWithCustomer: [{value:null, disabled:true}],

            
            address1: [{value:null, disabled:true}],
            address2: [{value:null, disabled:true}],
            address3: [{value:null, disabled:true}],
            locality: [{value:null, disabled:true}],
            village: [{value:null, disabled:true}],
            tehsil: [{value:null, disabled:true}],
            city: [{value:null, disabled:true}],
            district: [{value:null, disabled:true}],
            state: [{value:null, disabled:true}],
            country: [{value:null, disabled:true}],
            pinCode: [{value:null, disabled:true}],
            pinCodeId: [{value:null, disabled:true}],
        })
    }
    private machineDetailsForm() {
        return this.formBuilder.group({
            chassisNo: [null,Validators.required],
            product: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            subModel: [{ value: null, disabled: true }],
            variant: [{ value: null, disabled: true }],
            item: [{ value: null, disabled: true }],
            dateOfInstallation: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            warrantyValidTill: [{ value: null, disabled: true }],
            dealerId: [{ value: null, disabled: true }],
            soldToDealer : [{ value: null, disabled: false },Validators.required]
        })
    }

    private callHistoryDetailsForm() {
        return new FormArray([
            
        ]);
    }

    public createCallHistoryForm(){
        return this.formBuilder.group({
            id: [{value:null, disabled:true}],
            callNo: [{value:null, disabled:true}],
            callDate: [{value:null, disabled:true}],
            department: [{value:null, disabled:true}],
            complaint: [{value:null, disabled:true}],
            description: [{value:null, disabled:true}],
            actionTaken: [{value:null, disabled:true}],
            actionDate: [{value:null, disabled:true}]
        })
    }

    private complaintDetailsForm(){
        return new FormArray([
           
        ]);
    }

    public createComplaintForm(){
        return this.formBuilder.group({
            complaintNumber: [{value:null, disabled:true}],
            department: [null, Validators.compose([Validators.required])],
            description: [null, Validators.compose([Validators.required])],
            complaintType: [null, Validators.compose([Validators.required])],
            actionTaken: [{value:null, disabled:true}],
            assignTo: [{value:null, disabled:true}],
            assignToId: [{value:null, disabled:true}],
        })
    }
    private enquiryDetailsForm(){
        return this.formBuilder.group({
            pinID:[null],
            village:[{value:null, disabled:true}],
            pincode: [null, Validators.compose([Validators.required])],
            postOffice: [{value:null, disabled:true}],
            city: [{value:null, disabled:true}],
            tehsil: [{value:null, disabled:true}],
            district: [{value:null, disabled:false}, Validators.required],
            state: [{value:null, disabled:true}],
            tsm: [{value:null, disabled:true}],
            tsmContactNo: [{value:null, disabled:true}],
            dealerName:[{value:null, disabled:true}],
            dealerLocation:[{value:null, disabled:true}],
            dealerContactNo:[{value:null, disabled:true}],
            enquiry:[null, Validators.required]
        })
    }
    private freeServiceHistoryDetailsForm() {
        return new FormArray([
            
        ]);
    }
    public createFreeServiceForm(){
        return this.formBuilder.group({
            serviceDate: [{value:null, disabled:true}],
            serviceType: [{value:null, disabled:true}],
            totalHour: [{value:null, disabled:true}],
            pcrNo: [{value:null, disabled:true}],
            jobCardNo: [{value:null, disabled:true}],
            wcrNo: [{value:null, disabled:true}]
        });
    }
    private serveyHistoryDetailsForm() {
        return new FormArray([
            
        ]);
    }
    public createServeyHistoryForm(){
        return this.formBuilder.group({
            id: [{value:null, disabled:true}],
            surveyCode: [{value:null, disabled:true}],
            date: [{value:null, disabled:true}],
            typeOfSurvey: [{value:null, disabled:true}],
            doneBy: [{value:null, disabled:true}],
            satisfied: [{value:null, disabled:true}],
            additionalComment: [{value:null, disabled:true}]
        });
    }
}