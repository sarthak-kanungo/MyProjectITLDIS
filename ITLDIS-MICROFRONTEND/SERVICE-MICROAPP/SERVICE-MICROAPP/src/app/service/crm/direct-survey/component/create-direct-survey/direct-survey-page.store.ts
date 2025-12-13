import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class DirectSurveyPageStore {

    private readonly _directSurveyCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._directSurveyCreateForm = this.formBuilder.group({
            createSurveyHeader: this.searchDirectSurveyCreateDetails(),
            surveyCustomerDetails: this.searchDirectSurveyCreateCustomerDetails(),
            surveyMachineDetails: this.searchDirectSurveyCreateMachineDetails(),
          
            otherMachineDetails: new FormArray([]),
            referenceDetails:this.referenceDetails(),
            calAttemptDetails:this.calAttemptDetails(),
            crop:this.searchCurrentSurveyMajorCropsDetailsDetails(),
            currentSurvey: new FormArray([]),
            complainMain:this.formBuilder.group({
                complain:new FormArray([]),
                complainRecording:this.complainRecording(),
            }),
            freeService:new FormArray([]),
            surveyHistory: new FormArray([]),
            contactMachineImplement:this.formBuilder.group({
                searchCurrentSurveyContactPersonDetailsDetails: this.searchCurrentSurveyContactPersonDetailsDetails(),
                searchCurrentSurveyMachineDetailsDetails: this.searchCurrentSurveyMachineDetailsDetails(),
                majorImplement:new FormArray([]),
            }),
        })
    }

    get directSurveyCreateForm() {
        return this._directSurveyCreateForm
    }

    private searchDirectSurveyCreateDetails() {
        return this.formBuilder.group({
            surveyType:[{ value: null, disabled: true }],
            // directSurveyNo: [null, Validators.compose([Validators.required])],
            directSurveyNo:[{value:null, disabled:true}],
            directSurveyDate: [{ value: null, disabled: true }],
            status:  [{ value: null, disabled: true }],
            surveyDoneBye:[{ value: null, disabled: true }],
            soldToDealer:[null, Validators.compose([Validators.required])]
        })
    }

    private searchDirectSurveyCreateAddressDetails() {
        return this.formBuilder.group({
            address1: [null],
            address2: [null],
            address3: [null],
            country: [null],
            state: [null],
            district: [null],
            tehsil: [null],
            city: [null],
            pinCode: [null],
        })
    }
    private searchDirectSurveyCreateCustomerDetails() {
        return this.formBuilder.group({
            customerType: [null, Validators.compose([Validators.required])],
            // customerType:[],
            name: [null],
            mobileNo: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
            village: [{ value: null, disabled: true },],
            tehsil: [{ value: null, disabled: true }],
            district: [{ value: null, disabled: true }],
            state: [{ value: null, disabled: true }],
            territorySalesManager: [{ value: null, disabled: true }],
            tsmContactNo: [{ value: null, disabled: true }],
            dealerName: [{ value: null, disabled: true }],
            dealerLocation: [{ value: null, disabled: true }],
            dealerContactNo: [{ value: null, disabled: true }],
            customerCode: [null],
            title: [{ value: null, disabled: true }],
            satisfactionLevel: [{ value: null, disabled: true }],
            soldDealerName: [{ value: null, disabled: true }],
            firstName: [{ value: null, disabled: true }],
            preferedLanguage: [{ value: null, disabled: true }],
            mobileNumber: [{ value: null,disabled:true}],
            middleName: [{ value: null, disabled: true }],
            panNo: [{ value: null, disabled: true }],
            alternateMobileNo: [{ value: null, disabled: true }],
            lastName: [{ value: null, disabled: true }],
            gstin: [{ value: null, disabled: true }],
            telephone: [{ value: null, disabled: true }],
            contactPersonName: [{ value: null, disabled: true }],
            contactPersonMobile: [{ value: null, disabled: true }],
            relationWithCustomer: [{ value: null, disabled: true }],
            address1: [{ value: null, disabled: true }],
            address2: [{ value: null, disabled: true }],
            address3: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true },],
            city: [{ value: null, disabled: true }],
            pinCode: [{ value: null, disabled: true }],
        })
    }
    private searchDirectSurveyCreateMachineDetails() {
        return this.formBuilder.group({
            chassisNo: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
            product: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            subModel: [{ value: null, disabled: true }],
            variant: [{ value: null, disabled: true }],
            item: [{ value: null, disabled: true }],
            dateOfInstallation: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            warrantyValidTill: [{ value: null, disabled: true }],
            dealerId:[]
        })
    }
    private searchCurrentSurveyContactPersonDetailsDetails() {
        return this.formBuilder.group({
            name: [null],
            mobileNo: [null, Validators.compose([ CustomValidators.numberOnly, CustomValidators.minLength(10)])],
            profile: [null],
            age: [null,Validators.compose([ CustomValidators.numberOnly, CustomValidators.maxLength(3)])],
        })
    }
    private searchCurrentSurveyMachineDetailsDetails() {
        return this.formBuilder.group({
            ageOfMachine: [{value:null,disabled:true}, Validators.compose([CustomValidators.numberOnly])],
            houseMeterReading: [null, Validators.compose([CustomValidators.numberOnly])],
            firstTimeBuyer: [null],
            brand: [{value:null,disabled:true}],
            // model: [{value:null,disabled:true}],
            factorInfluenced: [null],
        })
    }
     searchCurrentSurveyMajorImplementDetailsDetail() {
        return this.formBuilder.group({
            implementName: [null],
            hours: [null,Validators.compose([ CustomValidators.numberOnly])],
            deleteFlag:[null]
        })
    }
    searchCurrentSurveyMajorCropsDetailsDetails() {
        return this.formBuilder.group({
            cropGrown: [null],
        })
    }

    otherMachineDetails(){
        return this.formBuilder.group({
            brand:[],
            model:[],
            yearOfPurchase:[null,Validators.compose([CustomValidators.numberOnly])],
            serialNo:[]
        })
    }

    referenceDetails(){
        return this.formBuilder.group({
            customerName1:[],
            customerName2:[],
            mobileNo1:[null,Validators.compose([CustomValidators.numberOnly,CustomValidators.minLength(10),CustomValidators.maxLength(10)])],
            mobileNo2:[null,Validators.compose([CustomValidators.numberOnly,CustomValidators.minLength(10),CustomValidators.maxLength(10)])],
            address1:[],
            address2:[],
            village1:[],
            village2:[],
            additionalComment:[null,Validators.maxLength(500)]
        })
    }

    calAttemptDetails(){
        return this.formBuilder.group({
            responseType:[null, Validators.compose([Validators.required])],
            callAttemptDate:[null, Validators.compose([Validators.required])],
            callAttemptTime:[null, Validators.compose([Validators.required])],
            additinalComments:[null, Validators.compose([Validators.required])],
            // attachRecording:[null,  Validators.compose([Validators.required])]
            attachRecording:[null]
        })
    }

    currentSurvey(){
        return this.formBuilder.group({
            question:[],
            dept:[],
            answer:[null, Validators.compose([Validators.required])],
            // subAnswer:[null, Validators.compose([Validators.required])],
            remarks:[],
            quesId:[],
            quesDtlId:[],
            subAnswer:new FormArray([])
        })
    }
    currentSurveySubAnswer(){
        return this.formBuilder.group({
            subAnswer:[null, Validators.compose([Validators.required])],
            subAnswerDesc:[null],
            quesSubDTLID:[],
            Multiple_Answer_Applicable:[],
            quesSubDtlId:[]
        })
    }

    complain(){
        return this.formBuilder.group({
            complaintNo:[null],
            department:[null],
            typeOfComplaint:[null],
            actionTaken:[],
            description:[null],
            assignTo: [{value:null, disabled:true}],
            hoUserId: [{value:null, disabled:true}]
        })
    }
    complainRecording(){
        return this.formBuilder.group({
            complainRecording:[]
        })
    }

    public createFreeServiceForm(){
        return this.formBuilder.group({
            serviceDate: [{value:null, disabled:true}],
            serviceType: [{value:null, disabled:true}],
            totalHour: [{value:null, disabled:true}],
            pcrNo: [{value:null, disabled:true}],
            jobCardNo: [{value:null}],
            wcrNo: [{value:null, disabled:true}]
        });
    }

    public surveyHistoryForm(){
        return this.formBuilder.group({
            surveyCode: [{value:null, disabled:true}],
            date: [{value:null, disabled:true}],
            typeOfSurvey: [{value:null, disabled:true}],
            doneBy: [{value:null, disabled:true}],
            satisfied: [{value:null, disabled:true}],
            additionalComment: [{value:null}]
        });
    }

}