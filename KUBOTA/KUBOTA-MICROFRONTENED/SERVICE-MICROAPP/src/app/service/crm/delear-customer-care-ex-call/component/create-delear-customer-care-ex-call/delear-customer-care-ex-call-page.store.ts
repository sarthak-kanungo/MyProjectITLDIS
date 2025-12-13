import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class DelearCustomerCareExCallPageStore {

    private readonly _delearCustomerCareExCallCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._delearCustomerCareExCallCreateForm = this.formBuilder.group({
            callDetails: this.callDetailsForm(),
            customerDetails: this.customerDetailsForm(),
            machineDetails: this.machineDetailsForm(),
            freeServiceHistoryDetails: this.freeServiceHistoryDetailsForm(),
            callHistoryDetails: this.callHistoryDetailsForm(),
            complaintDetails: this.complaintDetailsForm(),
            enquiryDetails: this.enquiryDetailsForm(),
            createEnquiry: this.createEnquiryForm(),
            serviceBookingDetails:this.createServiceBookingForm(),
            serviceBookingcustomerDetails:this.createserviceBookingCustomerDetails(),
            serviceBookingSearchDetails: this.createserviceBookingSearchDetails(),
            postServiceFeedBack:new FormArray([]),
            postSalesFeedBack:new FormArray([]),
            
        })
    }

    get delearCustomerCareExCallCreateForm() {
        
        return this._delearCustomerCareExCallCreateForm
    }

    private callDetailsForm() {
        return this.formBuilder.group({
            callDate: [{value:null, disabled:true}, Validators.compose([Validators.required])],
            callNo: [{value:null, disabled:true}],
            status: [{value:null, disabled:true}],
            callerName: [{value:null, disabled:true}],
            callerMobile: [{value:null, disabled:true}],
            callType: [{value:null, disabled:false},Validators.required],
            crmCallStatus: [{value:null, disabled:false}],
            callDetails: [{value:null, disabled:false}],
             startTime: [{value:null, disabled:true}],
             endTime:[{value:null,disabled:true}],
             dealerLocation: [{value:null, disabled:true}],
             dealerName: [{value:null, disabled:true}],
            dealerContact: [{value:null, disabled:true}],
            // customerType: [null, Validators.compose([Validators.required])],
            // contactPersonName: [null, Validators.compose([Validators.required])],
            // contactPersonMobile: [null, Validators.compose([Validators.required, CustomValidators.numberOnly, CustomValidators.minLength(10)])],
            // relationWithCustomer: [null, Validators.compose([Validators.required])],
           
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

            custCode: [{value:null, disabled:true}],
            soldDealerName: [{value:null, disabled:true}],
            mobileNumber: [{value:null,disabled:true}],
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
            chassisNo: [{value:null,disabled:true}],
            product: [{value:null, disabled:true}],
            model: [{value:null, disabled:true}],
            subModel: [{value:null, disabled:true}],
            variant: [{value:null, disabled:true}],
            item: [{value:null, disabled:true}],
            dateOfInstallation: [{value:null, disabled:true}],
            engineNo: [{value:null, disabled:true}],
            warrantyValidTill: [{value:null, disabled:true}],
            dealerId: [{ value: null, disabled: true }],
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
    private callHistoryDetailsForm() {
        return new FormArray([
            
        ]);
    }

    public createCallHistoryForm(){
        return this.formBuilder.group({
            callNo: [{value:null, disabled:true}],
            callDate: [{value:null, disabled:true}],
            comment: [{value:null, disabled:true}]
        })
    }

    private complaintDetailsForm(){
        return new FormArray([
            // this.formBuilder.group({
            //     department: [null, Validators.required],
            //     complaint: [null, Validators.required],
            //     actionTaken: [null, Validators.required]
            // })
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
            pincode: [null, Validators.required],
            postOffice: [{value:null, disabled:true}],
            city: [{value:null, disabled:true}],
            tehsil: [{value:null, disabled:true}],
            district: [{value:null, disabled:true}],
            state: [{value:null, disabled:true}],
            tsm: [{value:null, disabled:true}],
            tsmContactNo: [{value:null, disabled:true}],
            enquiry: [null, Validators.required],
        })
    }
    private createEnquiryForm(){
        return this.formBuilder.group({
            // enquiryNumber:[null],
            enquiryDate: [{value:null,disabled:true}],
            source: [{value:null, disabled:true}],
            model: [{value:null, disabled:false}],
            subModel: [{value:null, disabled:false}],
            variant: [{value:null, disabled:false}],
            // mobileNumber:[{}],
            mobileNumber: [{value:null, disabled:false}],
            firstName: [{value:null, disabled:false}],
            lastName: [{value:null, disabled:false}],
            tentativePurchaseDate: [{value:null, disabled:false}],
            nextFollowUpDate: [{value:null, disabled:false}],
            remarks: [{value:null, disabled:false}],
        })
    }
    public createServiceBookingForm(){
        return this.formBuilder.group({
            
            bookingNo: [{ value: null, disabled: true }],
            bookingDate: [{ value: null, disabled: true }],
            status: [{ value: null, disabled: true }],
            chassisNo: [{value:null,disabled:true}],
            machineInventoryId: [null],
            customerId: [null],
            
            mechanic: [{value:null,disabled:false}],
            customerName: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            dateofInstallation: [{ value: null, disabled: true }],
            currentHour: [{value:null,disabled:false}],
            previousHour: [{ value: null, disabled: true }],
            totalHour: [{ value: null, disabled: true }],
            meterChangeHour: [null],
            priviousCurrentHour: [],
            sourceOfBooking: [null],
            serviceCategory: [{value:null,disabled:false}],
            placeOfService: [{value:null,disabled:false}],
            serviceMtServiceTypeInfo: [{value:null,disabled:false}],
            appointmentDate: [{value:null,disabled:false}],
            activityType: [null],
            activityNo: [{value:null,disabled:false}],
            appointmentTime: [{value:null,disabled:false}],
            remarks: [{value:null,disabled:false}],
            registrationNumber: [{ value: null, disabled: true }],
           

        })
    }
    public createserviceBookingCustomerDetails(){
         return this.formBuilder.group({
            address: [{ value: null, disabled: true }],
            mobileNumber: [{ value: null, disabled: true }],
            previousServiceType: [{ value: null, disabled: true }],
            previousServiceDate: [{ value: null, disabled: true }],
            previousServiceHour: [{ value: null, disabled: true }],
            nextDueServiceType: [{ value: null, disabled: true }]
         })
    }

    public createserviceBookingSearchDetails(){
        return this.formBuilder.group({
           chassisNo: [{ value: null, disabled: false }],
           mobileNo: [{ value: null, disabled: false },Validators.compose([CustomValidators.mobileNumber])],
           customerName: [{ value: null, disabled: false }],
           currentServiceDueType: [{ value: null, disabled: false }],
           currentServiceDueDate: [{ value: null, disabled: false }],
        })
   }

    public postServiceFeedBack(){
        return this.formBuilder.group({
            Quesionnaire:[{value:null,disabled:false}],
            rating:[{value:null,disabled:false}],
            remarks:[{value:null,disabled:false}],
            jcId:[{value:null,disabled:true}],
            id:[{value:null,disbaled:true}]
        })
    }
    public  postSalesFeedBack(){
        return this.formBuilder.group({
            Quesionnaire:[{value:null,disabled:false}],
            rating:[{value:null,disabled:false},Validators.required],
            remarks:[{value:null,disabled:false}],
            dcId:[{value:null,disabled:true}],
            id:[{value:null,disbaled:true}]
        })
    }
}