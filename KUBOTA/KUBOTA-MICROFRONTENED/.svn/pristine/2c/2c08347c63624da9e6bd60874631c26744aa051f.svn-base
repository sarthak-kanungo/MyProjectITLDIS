import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { CurrentMachine } from '../domains/enquiry';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class EnquiryPresenter {

    private _enquiryFormGroup: FormGroup
    private _operation: string
    isExchangerequired: boolean
    isCashSelected: boolean
    isFinanceStatus: boolean
    isSubSidySelect: boolean
    deleteDataRow = []

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private formBuilder: FormBuilder,
        private toastr: ToastrService
    ) {
    }

    get enquiryForm() {
        return this._enquiryFormGroup
    }

    set enquiryForm(enqFrm: FormGroup) {
        this._enquiryFormGroup = enqFrm
    }
    get createEnquiryForm() {
        this.enquiryForm = this.formBuilder.group({
            generalInfo: this.buildGeneralInfoFormGroup(),
            productIntrested: this.buildProductIntrestedFormGroup(),
            prospectDetails: this.buildProspectDetailsFormGroup(),
            prospectBackground: this.buildProspectBackgroundFormGroup(),
            currentMachineryDetails: this.formBuilder.group({
                machineryDetails: this.buildCurrentMachineryDetailsFormArray()
            }),
            retailFinanceDetails: this.buildRetailFinanceDetailsFormGroup()
        })

        return this.enquiryForm
    }

    get viewEnquiryForm() {
        this.enquiryForm = this.formBuilder.group({
            generalInfo: this.buildGeneralInfoFormGroupForView(),
            productIntrested: this.buildProductIntrestedFormGroupForView(),
            prospectDetails: this.buildProspectDetailsFormGroupForView(),
            prospectBackground: this.buildProspectBackgroundFormGroupForView(),
            currentMachineryDetails: this.formBuilder.group({
                machineryDetails: this.buildCurrentMachineryDetailsFormArray()
            }),
            retailFinanceDetails: this.buildRetailFinanceDetailsFormGroupForView()
        })
        return this.enquiryForm
    }

    get viewMobileEnquiryForm() {
        this.enquiryForm = this.formBuilder.group({
            generalInfo: this.buildGeneralInfoFormGroupForViewMobile(),
            productIntrested: this.buildProductIntrestedFormGroupForViewMobile(),
            prospectDetails: this.buildProspectDetailsFormGroupForMobile(),
            prospectBackground: this.buildProspectBackgroundFormGroupForViewMobile(),
            currentMachineryDetails: this.formBuilder.group({
                machineryDetails: this.buildCurrentMachineryDetailsFormArray()
            }),
            retailFinanceDetails: this.buildRetailFinanceDetailsFormGroupForMobile()
        })
        return this.enquiryForm
    }

    private buildGeneralInfoFormGroup(): FormGroup {
        return this.formBuilder.group({
            enquiryDate: [null, Validators.compose([Validators.required])],
            salesPerson: [null, Validators.compose([Validators.required])],
            validationDate: [{ value: new Date(), disabled: true }],
            enquiryType: [{ value: null, disabled: true }],
            followUpType: [null, Validators.compose([Validators.required])],
            source: [null, Validators.compose([Validators.required])],
            referralPersonName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
            purposeOfPurchase: [null, Validators.compose([Validators.required])],
            generationActivityType: [null],
            generationActivityNumber: [null],
            tentativePurchaseDate: [null, Validators.compose([Validators.required])],
            nextFollowUpDate: [null, Validators.compose([Validators.required])],
            remarks: [null, Validators.compose([Validators.required])],
        })
    }

    private buildGeneralInfoFormGroupForView(): FormGroup {
        return this.formBuilder.group({
            enquiryNumber: [{ value: null, disabled: true }],
            enquiryDate: [{ value: null, disabled: true }],
            salesPerson: [{ value: null, disabled: true }],
            validationDate: [{ value: null, disabled: true }],
            enquiryType: [{ value: null, disabled: true }],
            followUpType: [{ value: null, disabled: true }],
            source: [{ value: null, disabled: true }],
            referralPersonName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
            purposeOfPurchase: [{ value: null, disabled: true }],
            generationActivityType: [null],
            generationActivityNumber: [null],
            tentativePurchaseDate: [{ value: null, disabled: true }],
            nextFollowUpDate: [{ value: null, disabled: true }],
            remarks: [{ value: null, disabled: true }],
            conversionActivityNumber: [null],
            enquiryStatus: [{ value: null, disabled: true }],
            conversionActivityType: [null],
            retailConversionActivity: [null]
        })
    }

    private buildGeneralInfoFormGroupForViewMobile(): FormGroup {
        return this.formBuilder.group({
            enquiryNumber: [{ value: null, disabled: true }],
            enquiryDate: [{ value: null, disabled: true }],
            salesPerson: [{ value: null, disabled: true }],
            validationDate: [{ value: new Date(), disabled: true }],
            enquiryType: [{ value: null, disabled: true }],
            followUpType: [null, Validators.compose([Validators.required])],
            source: [{ value: null, disabled: true }],
            referralPersonName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
            purposeOfPurchase: [null, Validators.compose([Validators.required])],
            generationActivityType: [null],
            generationActivityNumber: [null],
            enquiryStatus: [{ value: null, disabled: true }],
            tentativePurchaseDate: [null, Validators.compose([Validators.required])],
            nextFollowUpDate: [null, Validators.compose([Validators.required])],
            remarks: [null, Validators.compose([Validators.required])],
        })
    }

    private buildProductIntrestedFormGroup(): FormGroup {
        return this.formBuilder.group({
            itemNo: [null, Validators.compose([])],
            itemDescription: [{ value: null, disabled: true }],
            series: [{ value: null, disabled: true }],
            product: [{ value: null, disabled: true }],
            exchangeBrand: [null],
            exchangeModel: [null],
            exchangeModelYear: [null],
            estimatedExchangePrice: [0.0, Validators.compose([])],
            variant: [null, Validators.compose([Validators.required])],
            subModel: [null, Validators.compose([Validators.required])],
            model: [null, Validators.compose([Validators.required])],
            exchangeRequired: [null, Validators.compose([Validators.required])],
            exchangeReceived:[null]
        })
    }

    private buildProductIntrestedFormGroupForView(): FormGroup {
        return this.formBuilder.group({
            itemNo: [null, Validators.compose([])],
            itemDescription: [{ value: null, disabled: true }],
            series: [{ value: null, disabled: true }],
            product: [{ value: null, disabled: true }],
            exchangeBrand: [null],
            exchangeModel: [null],
            exchangeModelYear: [null],
            estimatedExchangePrice: [0.0, Validators.compose([])],
            variant: [{ value: null, disabled: true }],
            subModel: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            exchangeRequired: [{ value: null, disabled: false }],
            exchangeReceived :[null]
        })
    }

    private buildProductIntrestedFormGroupForViewMobile(): FormGroup {
        return this.formBuilder.group({
            itemNo: [null, Validators.compose([])],
            itemDescription: [{ value: null, disabled: true }],
            series: [{ value: null, disabled: true }],
            product: [{ value: null, disabled: true }],
            exchangeBrand: [null],
            exchangeModel: [null],
            exchangeModelYear: [null],
            estimatedExchangePrice: [0.0, Validators.compose([])],
            variant: [{ value: null, disabled: true }],
            subModel: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            exchangeRequired: [null, Validators.compose([Validators.required])],
        })
    }

    private buildProspectDetailsFormGroup(): FormGroup {
        return this.formBuilder.group({
            prospectCode: [{ value: null, disabled: true }],
            customerCode: [null],
            prosId: [null],
            oldCustomerCode: [null],
            companyName: [null],
            prospectType: [null, Validators.compose([Validators.required])],
            title: [null, Validators.compose([Validators.required])],
            firstName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
            middleName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
            lastName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
            fatherName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
            mobileNumber: [null, Validators.compose([Validators.required])],
            whatsAppNumber: [null, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{9}')])],
            alternateMobileNumber: [null, Validators.compose([Validators.pattern('[1-9]\\d{9}')])],
            std: [{ value: null, disabled: true },  Validators.compose([])],
            telephoneNumber: [null],
            emailId: [null, Validators.compose([Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
            address1: [null, Validators.compose([Validators.required])],
            address2: [null, Validators.compose([])],
            address3: [null, Validators.compose([])],
            pinCode: [null, Validators.compose([Validators.required,])],
            pinId: [null, Validators.compose([Validators.required,])],
            postOffice: [{ value: null, disabled: true }],
            city: [{ value: null, disabled: true }],
            tehsil: [{ value: null, disabled: true }],
            district: [null, Validators.compose([Validators.required])],
            state: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
            language: [null],
            dob: [null],
            anniversaryDate: [null,Validators.compose([])],
            gstNumber: [null, Validators.compose([Validators.pattern(/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/)])],
            panNumber: [null, Validators.compose([Validators.pattern('[A-Z]{5}[0-9]{4}[A-Z]{1}')])],
            recordType: [null],
        })
    }

    private buildProspectDetailsFormGroupForView(): FormGroup {
        return this.formBuilder.group({
            recordType: [null],
            prospectCode: [{ value: null, disabled: true }],
            customerCode: [{ value: null, disabled: true }],
            customerType: [{ value: null, disabled: true }],
            oldCustomerCode: [{ value: null, disabled: true }],
            companyName: [{ value: null, disabled: true }],
            prospectType: [{ value: null, disabled: true }],
            title: [{ value: null, disabled: true }],
            firstName: [{ value: null, disabled: true }],
            middleName: [{ value: null, disabled: true }],
            lastName: [{ value: null, disabled: true }],
            fatherName: [{ value: null, disabled: true }],
            mobileNumber: [{ value: null, disabled: true }],
            whatsAppNumber: [{ value: null, disabled: true }],
            alternateMobileNumber: [{ value: null, disabled: true }],
            std: [{ value: null, disabled: true }],
            telephoneNumber: [{ value: null, disabled: true }],
            emailId: [{ value: null, disabled: true }],
            address1: [{ value: null, disabled: true }],
            address2: [{ value: null, disabled: true }],
            address3: [{ value: null, disabled: true }],
            pinCode: [{ value: null, disabled: true }],
            pinId: [{ value: null, disabled: true }],
            postOffice: [{ value: null, disabled: true }],
            city: [{ value: null, disabled: true }],
            tehsil: [{ value: null, disabled: true }],
            district: [{ value: null, disabled: true }],
            state: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
            language: [null],
            dob: [null],
            anniversaryDate: [null],
            gstNumber: [null],
            panNumber: [null, Validators.compose([Validators.pattern('^[a-zA-Z0-9]+')])]
        })
    }

    private buildProspectDetailsFormGroupForMobile(): FormGroup {
        return this.formBuilder.group({
            prospectCode: [{ value: null, disabled: true }],
            customerCode: [null],
            oldCustomerCode: [null],
            companyName: [null],
            prosId: [null],
            prospectType: [null, Validators.compose([Validators.required])],
            title: [{ value: null, disabled: true }],
            firstName: [{ value: null, disabled: true }],
            middleName: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])],
            lastName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
            fatherName: [null, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
            mobileNumber: [{ value: null, disabled: true }],
            whatsAppNumber: [null, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{9}')])],
            alternateMobileNumber: [null, Validators.compose([Validators.pattern('[1-9]\\d{9}')])],
            std: [null, Validators.compose([])],
            telephoneNumber: [null],
            emailId: [null, Validators.compose([Validators.email])],
            address1: [null, Validators.compose([Validators.required])],
            address2: [null, Validators.compose([])],
            address3: [null, Validators.compose([])],
            pinCode: [null, Validators.compose([Validators.required,])],
            pinId: [null, Validators.compose([Validators.required,])],
            postOffice: [{ value: null, disabled: true }],
            city: [{ value: null, disabled: true }],
            tehsil: [{ value: null, disabled: true }],
            district: [null, Validators.compose([Validators.required])],
            state: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
            language: [null],
            dob: [null],
            anniversaryDate: [null],
            gstNumber: [null],
            panNumber: [null, Validators.compose([Validators.pattern('^[a-zA-Z0-9]+')])],
            recordType: [null],
        })
    }

    private buildProspectBackgroundFormGroup(): FormGroup {
        return this.formBuilder.group({
            landSize: [null, Validators.compose([Validators.required])],
            occupation: [null, Validators.compose([Validators.required])],
            soilName: [null, Validators.compose([])],
            cropName: [null, Validators.compose([])],
            otherOccupation: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])]
        })
    }

    private buildProspectBackgroundFormGroupForView(): FormGroup {
        return this.formBuilder.group({
            landSize: [{ value: null, disabled: true }],
            occupation: [null, Validators.compose([Validators.required])],
            soilName: [null, Validators.compose([])],
            cropName: [null, Validators.compose([])],
            otherOccupation: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])]
        })
    }

    private buildProspectBackgroundFormGroupForViewMobile(): FormGroup {
        return this.formBuilder.group({
            landSize: [null, Validators.compose([Validators.required])],
            occupation: [null, Validators.compose([Validators.required])],
            soilName: [null, Validators.compose([])],
            cropName: [null, Validators.compose([])],
            otherOccupation: [null, Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])]
        })
    }

    private buildCurrentMachineryDetailsFormArray(): FormArray {
        return this.formBuilder.array([])
    }

    private buildCurrentMachineryDetailsFormGroup(product?: CurrentMachine): FormGroup {
        return this.formBuilder.group({
            id: this.formBuilder.control(product ? product.id : null),
            isSelected: this.formBuilder.control(false),
            brand: this.formBuilder.control(product ? product.brand : null),
            model: this.formBuilder.control(product ? product.model : null),
            yearOfPurchase: this.formBuilder.control(product ? product.yearOfPurchase : null, Validators.compose([Validators.minLength(4)]))
        })
    }

    private buildRetailFinanceDetailsFormGroup(): FormGroup {
        return this.formBuilder.group({
            cashLoan: [null, Validators.compose([Validators.required])],
            assetValue: [null, Validators.compose([Validators.required])],
            financier: [null],
            financierId: [null],
            financeLoggedInDate: [null],
            estimatedFinanceAmount: [{ value: 0.0, disabled: false }],
            financeStatus: [null],
            disbursedDate: [{ value: null, disabled: false }],
            disbursedFinanceAmount: [{ value: 0.0, disabled: false }],
            finalExchangePrice: [{ value: 0.0, disabled: true }],
            subsidy: [null, Validators.compose([Validators.required])],
            subsidyAmount: [0.0],
            marginAmount: [{ value: 0.0, disabled: true }],
            totalReceived:[null]
        })
    }

    private buildRetailFinanceDetailsFormGroupForView(): FormGroup {
        return this.formBuilder.group({
            cashLoan: [{ value: null, disabled: false }],
            assetValue: [null, Validators.compose([Validators.required])],
            financier: [null],
            financierId: [null],
            financeLoggedInDate: [null],
            estimatedFinanceAmount: [{ value: 0.0, disabled: false }],
            financeStatus: [null],
            disbursedDate: [{ value: null, disabled: true }],
            disbursedFinanceAmount: [{ value: 0.0, disabled: true }],
            finalExchangePrice: [{ value: 0.0, disabled: true }],
            subsidy: [{ value: null, disabled: false }],
            subsidyAmount: [0.0],
            marginAmount: [{ value: 0.0, disabled: true }],
            totalReceived:[{ value: 0.0, disabled:true}]
        })
    }

    private buildRetailFinanceDetailsFormGroupForMobile(): FormGroup {
        return this.formBuilder.group({
            cashLoan: [null, Validators.compose([Validators.required])],
            assetValue: [null, Validators.compose([Validators.required])],
            financier: [null],
            financierId: [null],
            financeLoggedInDate: [null],
            estimatedFinanceAmount: [{ value: 0.0, disabled: false }],
            financeStatus: [null],
            disbursedDate: [{ value: null, disabled: true }],
            disbursedFinanceAmount: [{ value: 0.0, disabled: true }],
            finalExchangePrice: [{ value: 0.0, disabled: true }],
            subsidy: [null],
            subsidyAmount: [0.0],
            marginAmount: [{ value: 0.0, disabled: true }],
        })
    }

    resetAll() {
        this.enquiryForm.reset()
    }

    resetProductHierarchyForItemNumber() {
        let productIntrested = this.enquiryForm.get('productIntrested') as FormGroup
        productIntrested.get('itemDescription').reset()
        productIntrested.get('model').reset()
        productIntrested.get('subModel').reset()
        productIntrested.get('variant').reset()
        productIntrested.get('series').reset()
        productIntrested.get('product').reset()
    }

    resetProductHierarchyForModel() {
        let productIntrested = this.enquiryForm.get('productIntrested') as FormGroup
        productIntrested.get('itemDescription').reset()
        productIntrested.get('itemNo').reset()
        productIntrested.get('subModel').reset()
        productIntrested.get('variant').reset()
        productIntrested.get('series').reset()
        productIntrested.get('product').reset()
    }

    resetExchangeForExchanreRequired() {
        let productIntrested = this.enquiryForm.get('productIntrested') as FormGroup
        productIntrested.get('exchangeBrand').reset()
        productIntrested.get('exchangeModel').reset()
        productIntrested.get('exchangeModelYear').reset()
        productIntrested.get('estimatedExchangePrice').reset()
    }

    resetProspectDetailsForMobileNumber() {
        let prospectDetails = this.enquiryForm.get('prospectDetails') as FormGroup
        prospectDetails.get('prospectCode').reset()
        prospectDetails.get('customerCode').reset()
        prospectDetails.get('companyName').reset()
        prospectDetails.get('prospectType').reset()
        prospectDetails.get('firstName').reset()
        prospectDetails.get('middleName').reset()
        prospectDetails.get('lastName').reset()
        prospectDetails.get('fatherName').reset()
        prospectDetails.get('whatsAppNumber').reset()
        prospectDetails.get('alternateMobileNumber').reset()
        prospectDetails.get('std').reset()
        prospectDetails.get('telephoneNumber').reset()
        prospectDetails.get('emailId').reset()
        prospectDetails.get('address1').reset()
        prospectDetails.get('address2').reset()
        prospectDetails.get('address3').reset()
        prospectDetails.get('pinCode').reset()
        prospectDetails.get('postOffice').reset()
        prospectDetails.get('city').reset()
        prospectDetails.get('tehsil').reset()
        prospectDetails.get('district').reset()
        prospectDetails.get('state').reset()
        prospectDetails.get('country').reset()
        prospectDetails.get('language').reset()
        prospectDetails.get('dob').reset()
        prospectDetails.get('anniversaryDate').reset()
        prospectDetails.get('gstNumber').reset()
        prospectDetails.get('panNumber').reset()
        prospectDetails.get('title').reset()
    }

    resetProspectBackgroundForMobileNo() {
        let prospectBackground = this.enquiryForm.get('prospectBackground') as FormGroup
        //prospectBackground.get('landSize').reset()
        prospectBackground.get('occupation').reset()
        prospectBackground.get('soilName').reset()
        prospectBackground.get('cropName').reset()
        prospectBackground.get('otherOccupation').reset()
    }


    disableProspectDetailsForMobileNumber() {
        let prospectDetails = this.enquiryForm.get('prospectDetails') as FormGroup
        prospectDetails.get('prospectCode').disable()
        prospectDetails.get('companyName').disable()
        prospectDetails.get('prospectType').disable()
        prospectDetails.get('title').disable()
        prospectDetails.get('firstName').disable()
        prospectDetails.get('middleName').disable()
        prospectDetails.get('lastName').disable()
        prospectDetails.get('fatherName').disable()
        prospectDetails.get('alternateMobileNumber').disable()
        prospectDetails.get('std').disable()
        prospectDetails.get('telephoneNumber').disable()
        prospectDetails.get('emailId').disable()
        prospectDetails.get('address1').disable()
        prospectDetails.get('address2').disable()
        prospectDetails.get('address3').disable()
        prospectDetails.get('pinCode').disable()
        prospectDetails.get('district').disable()
        prospectDetails.get('language').disable()
        prospectDetails.get('dob').disable()
        prospectDetails.get('anniversaryDate').disable()
        prospectDetails.get('gstNumber').disable()
        prospectDetails.get('panNumber').disable()
    }

    enableProspectDetailsForMobileNumber() {
        let prospectDetails = this.enquiryForm.get('prospectDetails') as FormGroup
        prospectDetails.get('companyName').enable()
        prospectDetails.get('prospectType').enable()
        prospectDetails.get('title').enable()
        prospectDetails.get('firstName').enable()
        prospectDetails.get('middleName').enable()
        prospectDetails.get('lastName').enable()
        prospectDetails.get('fatherName').enable()
        prospectDetails.get('alternateMobileNumber').enable()
        // prospectDetails.get('std').enable()
        prospectDetails.get('std').disable()        /* comment and added by vinay */
        prospectDetails.get('telephoneNumber').enable()
        prospectDetails.get('emailId').enable()
        prospectDetails.get('address1').enable()
        prospectDetails.get('address2').enable()
        prospectDetails.get('address3').enable()
        prospectDetails.get('pinCode').enable()
        prospectDetails.get('language').enable()
        prospectDetails.get('dob').enable()
        prospectDetails.get('anniversaryDate').enable()
        prospectDetails.get('gstNumber').enable()
        prospectDetails.get('panNumber').enable()
        prospectDetails.get('district').enable()
        prospectDetails.get('title').enable()
        prospectDetails.get('district').setValidators(Validators.compose([Validators.required]))
    }

    resetProspectDetailsForPinCode() {
        let prospectDetails = this.enquiryForm.get('prospectDetails') as FormGroup
        prospectDetails.get('postOffice').reset()
        prospectDetails.get('city').reset()
        prospectDetails.get('tehsil').reset()
    }

    mandatoryFieldForGenerationActivityType(isMandatory: boolean) {
        let generalInfo = this.enquiryForm.get('generalInfo') as FormGroup
        if (isMandatory) {
            generalInfo.get('generationActivityNumber').setValidators(Validators.compose([Validators.required]))
            generalInfo.get('generationActivityNumber').updateValueAndValidity();
        } else {
            generalInfo.get('generationActivityNumber').clearValidators();
            generalInfo.get('generationActivityNumber').updateValueAndValidity();
        }
    }

    mandatoryFieldForConversionActivityType(isMandatory: boolean) {
        let generalInfo = this.enquiryForm.get('generalInfo') as FormGroup
        if (isMandatory) {
            generalInfo.get('retailConversionActivity').setValidators(Validators.compose([Validators.required]))
            generalInfo.get('retailConversionActivity').updateValueAndValidity();
            generalInfo.get('conversionActivityNumber').setValidators(Validators.compose([Validators.required]))
            generalInfo.get('conversionActivityNumber').updateValueAndValidity();
        } else {
            generalInfo.get('retailConversionActivity').clearValidators();
            generalInfo.get('retailConversionActivity').updateValueAndValidity();
            generalInfo.get('conversionActivityNumber').clearValidators();
            generalInfo.get('conversionActivityNumber').updateValueAndValidity();
        }
    }

    mandatoryFieldForExchangeRequired(isMandatory: boolean) {
        let productIntrested = this.enquiryForm.get('productIntrested') as FormGroup
        if (isMandatory) {
            productIntrested.get('exchangeBrand').setValidators(Validators.compose([Validators.required]))
            productIntrested.get('exchangeBrand').updateValueAndValidity();
            productIntrested.get('exchangeModel').setValidators(Validators.compose([Validators.required]))
            productIntrested.get('exchangeModel').updateValueAndValidity();
            productIntrested.get('exchangeModelYear').setValidators(Validators.compose([Validators.required]))
            productIntrested.get('exchangeModelYear').updateValueAndValidity();
            productIntrested.get('estimatedExchangePrice').setValidators(Validators.compose([Validators.required]))
            productIntrested.get('estimatedExchangePrice').updateValueAndValidity();
        } else {
            productIntrested.get('exchangeBrand').clearValidators();
            productIntrested.get('exchangeBrand').updateValueAndValidity();
            productIntrested.get('exchangeModel').clearValidators()
            productIntrested.get('exchangeModel').updateValueAndValidity();
            productIntrested.get('exchangeModelYear').clearValidators()
            productIntrested.get('exchangeModelYear').updateValueAndValidity();
            productIntrested.get('estimatedExchangePrice').clearValidators()
            productIntrested.get('estimatedExchangePrice').updateValueAndValidity();
        }
    }

    mandatoryFieldForProspectType(isMandatory: boolean) {
        let prospectDetails = this.enquiryForm.get('prospectDetails') as FormGroup
        if (isMandatory) {
            prospectDetails.get('companyName').setValidators(Validators.compose([Validators.required]))
            prospectDetails.get('companyName').updateValueAndValidity();
            prospectDetails.get('gstNumber').setValidators(Validators.compose([Validators.required]))
            prospectDetails.get('gstNumber').updateValueAndValidity();
        } else {
            prospectDetails.get('companyName').clearValidators();
            prospectDetails.get('companyName').reset();
            prospectDetails.get('companyName').updateValueAndValidity();
            prospectDetails.get('gstNumber').clearValidators();
            prospectDetails.get('gstNumber').reset();
            prospectDetails.get('gstNumber').updateValueAndValidity();
        }
    }

    mandatoryFieldForSubsidy(isMandatory: boolean) {
        let retailFinanceDetails = this.enquiryForm.get('retailFinanceDetails') as FormGroup
        if (isMandatory) {
            retailFinanceDetails.get('subsidyAmount').setValidators(Validators.compose([Validators.required]))
            retailFinanceDetails.get('subsidyAmount').updateValueAndValidity();
        } else {
            retailFinanceDetails.get('subsidyAmount').clearValidators();
            retailFinanceDetails.get('subsidyAmount').updateValueAndValidity();
        }
    }

    addMachineryDetails(dtl?: CurrentMachine) {
        let currentMachineryDetails = this.enquiryForm.get('currentMachineryDetails') as FormGroup
        let machineryDetails = currentMachineryDetails.get('machineryDetails') as FormArray
        console.log('addRow', machineryDetails);
        machineryDetails.push(this.buildCurrentMachineryDetailsFormGroup(dtl))
        
    }

    deleteMachineryDetails() {
        let currentMachineryDetails = this.enquiryForm.get('currentMachineryDetails') as FormGroup
        let machineryDetails = currentMachineryDetails.get('machineryDetails') as FormArray
        let nonSelected = machineryDetails.controls.filter(machinery => !machinery.value.isSelected)
        let selected = machineryDetails.controls.filter(machinery => machinery.value.isSelected)
        selected.forEach(el => {
            this.deleteDataRow.push({
                id: el.value.id,
                isSelected: el.value.isSelected,
                brand: el.value.brand,
                model: el.value.model,
                yearOfPurchase: el.value.yearOfPurchase
            })
        })
        if (machineryDetails.length - nonSelected.length) {
            machineryDetails.clear()
            nonSelected.forEach(el => machineryDetails.push(el))
        } else {
            this.toastr.warning('Atleast one Select Row', 'Report Delete Row')
        }
    }

    markFormAsTouched() {
        this.enquiryForm.markAllAsTouched();
    }

    get finalExchangePrice() {
        return this.retailFinanceDetailsForm.get('finalExchangePrice')
    }

    get retailFinanceDetailsForm() {
        return this.enquiryForm.get('retailFinanceDetails') as FormGroup
    }

    resetFinacerForCashLoan() {
        this.retailFinanceDetailsForm.get('financier').reset()
        this.retailFinanceDetailsForm.get('financeLoggedInDate').reset()
        this.retailFinanceDetailsForm.get('estimatedFinanceAmount').reset()
        this.retailFinanceDetailsForm.get('financeStatus').reset()
    }
    resetDisbursedForFinanceStatus() {
        this.retailFinanceDetailsForm.get('disbursedDate').reset()
        this.retailFinanceDetailsForm.get('disbursedFinanceAmount').reset()
        this.retailFinanceDetailsForm.get('disbursedDate').enable();
        this.retailFinanceDetailsForm.get('disbursedFinanceAmount').enable();
    }
    resetForSubSidy() {
        this.retailFinanceDetailsForm.get('subsidyAmount').reset()
    }

    calculateMarginAmount() {
        let asset = this.retailFinanceDetailsForm.get('assetValue').value;
        let assetValue = Number.parseFloat((asset==undefined||asset==null||asset=='')?0.0:asset);
        let marginAmount = assetValue;
        
        if(this.isSubSidySelect){
            let subAmt = this.retailFinanceDetailsForm.get('subsidyAmount').value;
            let subsidy = Number.parseFloat((subAmt==undefined||subAmt==null||subAmt=='')?0.0:subAmt)
            marginAmount = marginAmount - subsidy;
        }
        if(this.isExchangerequired){
            let exchangePrc = this.finalExchangePrice.value;
            let exchange = Number.parseFloat((exchangePrc==undefined||exchangePrc==null||exchangePrc=='')?0.0:exchangePrc);
            marginAmount = marginAmount - exchange;
        }
        /* commented and added by vinay to maintain the flow loan>>financer>>estimatedFinanceAmount*/
        // if (!this.isCashSelected){
            if (!this.isCashSelected && this.retailFinanceDetailsForm.get('financier').value || this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value ){
                console.log('checkpoint1--',this.retailFinanceDetailsForm.get('subsidy').value);
                
            if(this.retailFinanceDetailsForm.get('financeStatus').value.financeStatus ==='Payment Disbursed'){
                let disAmt = this.retailFinanceDetailsForm.get('disbursedFinanceAmount').value;
                let efa = Number.parseFloat((disAmt==undefined||disAmt==null||disAmt=='')?0.0:disAmt);
                marginAmount = marginAmount - efa;
            }else{
                let finAmt = this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value;
                let efa = Number.parseFloat((finAmt==undefined||finAmt==null||finAmt=='')?0.0:finAmt)
                marginAmount = marginAmount - efa;
            }
        }
        this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        
        /*if (this.isCashSelected && !this.isSubSidySelect && !this.isExchangerequired) {
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(this.retailFinanceDetailsForm.get('assetValue').value)
        }
        if (this.isCashSelected && this.isSubSidySelect && !this.isExchangerequired) {
            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let subsidy = Number.parseFloat(this.retailFinanceDetailsForm.get('subsidyAmount').value)
            let marginAmount = av - subsidy
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (this.isCashSelected && !this.isSubSidySelect && this.isExchangerequired) {
            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let exchange = Number.parseFloat(this.finalExchangePrice.value)
            console.log(`Av ${av} Exchange ${exchange}`)
            let marginAmount = (Number.isNaN(av) ? 0 : av) - exchange
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (this.isCashSelected && this.isSubSidySelect && this.isExchangerequired) {
            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let exchange = Number.parseFloat(this.finalExchangePrice.value)
            let subsidy = Number.parseFloat(this.retailFinanceDetailsForm.get('subsidyAmount').value)
            let marginAmount = av - (exchange + subsidy)
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (!this.isCashSelected && !this.isSubSidySelect && !this.isExchangerequired) {
            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let efa = Number.parseFloat(this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value)
            let marginAmount = av - efa
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (!this.isCashSelected && this.isSubSidySelect && !this.isExchangerequired) {
            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let efa = Number.parseFloat(this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value)
            let subsidy = Number.parseFloat(this.retailFinanceDetailsForm.get('subsidyAmount').value)
            let marginAmount = av - (efa + subsidy)
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (!this.isCashSelected && !this.isSubSidySelect && this.isExchangerequired) {

            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let efa = Number.parseFloat(this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value)
            let exchange = Number.parseFloat(this.finalExchangePrice.value)
            let marginAmount = av - (efa + exchange)
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }
        if (!this.isCashSelected && this.isSubSidySelect && this.isExchangerequired) {
            console.log('passing');

            let av = Number.parseFloat(this.retailFinanceDetailsForm.get('assetValue').value)
            let efa = Number.parseFloat(this.retailFinanceDetailsForm.get('estimatedFinanceAmount').value)
            let exchange = Number.parseFloat(this.finalExchangePrice.value)
            let subsidy = Number.parseFloat(this.retailFinanceDetailsForm.get('subsidyAmount').value)
            let marginAmount = av - (efa + exchange + subsidy)
            this.retailFinanceDetailsForm.get('marginAmount').patchValue(marginAmount)
        }*/
    }
}