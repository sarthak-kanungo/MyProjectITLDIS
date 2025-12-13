import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { CustomValidators } from '../../../../../utils/custom-validators';
import { WarrantyPcrImplement, JobCardHistory, FailurePart, WarrantyPart,OutSideLabourCharge,LabourCharge } from '../../../product-concern-report/domain/product-concern-report.domain';
@Injectable()
export class GoodwillPageStore {
    form: FormGroup;
    constructor(private fb: FormBuilder) {}

    private createForm() {
        this.form = this.fb.group({
            goodwillForm: this.goodwillForm(),
            implementForm: this.fb.array([]),
            serviceHistoryForm: this.fb.array([]),
            concernForm: this.concernForm(),
            failurePartsForm: this.fb.array([]),
            remarkForm: this.remarkForm(),
            outsideChargeForm : this.fb.array([]),
            labourChargeForm : this.fb.array([]),
            uploadFileForm: this.uploadFileForm(),
        });
    }
    private uploadFileForm() {
        const uploadFileForm = this.fb.group({
            uploadFile: [null]
        });
        return uploadFileForm;
    }
    private goodwillForm() {
        const goodwillForm = this.fb.group({
            goodwillNo:[{value: null, disabled: true}],
            goodwillDate: [{value: null, disabled: true}],
            status: [{value: null, disabled: true}],
            goodwillType: [{value: null, disabled: false}, Validators.required],
            pcrNo: [{value: null, disabled: true}],
            pcrDate: [{value: null, disabled: true}],
            jobCardNo: [{value: null, disabled: true}],
            jobCardDate: [{value: null, disabled: true}],
            dateOfInstallation: [{value: null, disabled: true}],
            customerName: [{value: null, disabled: true}],
            model: [{value: null, disabled: true}],
            dateOfFailure: [{value: null, disabled: true}],
            address: [{value: null, disabled: true}],
            mobileNo: [{value: null, disabled: true}],
            registrationNumber: [{value: null, disabled: true}],
            chassisNo: [{value: null, disabled: true}],
            engineNo: [{value: null, disabled: true}],
            totalHour: [{value: null, disabled: true}],
            soldToDealer: [{value: null, disabled: true}],
            serviceDealer: [{value: null, disabled: true}],
            serviceDealerAddress: [{value: null, disabled: true}],
            crop: [{value: null, disabled: true}],
            cropCondition: [{value: null, disabled: true}],
            failureType: [{value: null, disabled: true}],
            soilType: [{value: null, disabled: true}],
            fieldCondition: [{value: null, disabled: true}],
            failureCount:[{value: null, disabled: true}],
            budgetConsumed:[{value: 0, disabled: true}, Validators.compose([Validators.min(0),CustomValidators.numberOnly])]
            
        });
        return goodwillForm;
    }
    private implementForm(implementDetails?: WarrantyPcrImplement) {
        const implementForm = this.fb.group({
            implement: [{value: implementDetails == undefined? null : implementDetails.implement, disabled: true}],
            implementCategory: [{value: implementDetails == undefined? null : implementDetails.implementCategory, disabled: true}, Validators.required],
            implementMake: [{value: implementDetails == undefined? null : implementDetails.implementMake, disabled: true}, Validators.required],
            implementSerialNumber: [{value: implementDetails == undefined? null : implementDetails.implementSerialNumber, disabled: true}, Validators.compose([Validators.required, CustomValidators.serialNumber])],
            gearRatio: [{value: implementDetails == undefined? null : implementDetails.gearRatio, disabled: true}, Validators.required],
            engineRpm: [{value: implementDetails == undefined? null : implementDetails.engineRpm, disabled: true}, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            percentOfUsage: [{value: implementDetails == undefined? null : implementDetails.percentOfUsage, disabled: true}, Validators.compose([Validators.required, CustomValidators.floatNumber])  ],
            usageFailure: [{value: implementDetails == undefined? null : implementDetails.usageFailure, disabled: true}],
            isSelected: [{value: false, disabled: true}],
        });
        return implementForm;
    }
    private serviceHistoryForm(serviceHistoryDetails?: JobCardHistory) {
        const serviceHistoryForm = this.fb.group({
            serviceType: [{value: serviceHistoryDetails.serviceType, disabled: true}],
            dateIn: [{value: serviceHistoryDetails.dateIn, disabled: true}],
            jobCardNo: [{value: serviceHistoryDetails.jobCardNo, disabled: true}],
            jobCardDate: [{value: serviceHistoryDetails.jobCardDate, disabled: true}],
            hours: [{value: serviceHistoryDetails.hours, disabled: true}]
        });
        return serviceHistoryForm;
    }
    private concernForm() {
        const concernForm = this.fb.group({
            customerConcern: [{value: null, disabled:true}],
            dealerObservation: [{value: null, disabled:true}],
            actionTaken: [{value: null, disabled:true}],
            mechanicObservation: [{value: null, disabled:true}],
            goodwillReason: [{value: null, disabled:false}, Validators.required],
        });
        return concernForm;
    }
    private failurePartsForm(failurePartDetails?: WarrantyPart) {

        const failurePartsForm = this.fb.group({
            itemNo: [{value: failurePartDetails.partNo, disabled: true}],
            description: [{value: failurePartDetails.description, disabled: true}],
            claimQuantity: [{value: failurePartDetails.claimQuantity, disabled: true}],
            rejectedQuantity: [{value: failurePartDetails.rejectedQuantity, disabled: true}],
            goodwillClaimQuantity: [{value: failurePartDetails.gwClaimQuantity, disabled: true}, Validators.compose([Validators.min(1),Validators.required,CustomValidators.numberOnly,Validators.max(failurePartDetails.rejectedQuantity)])],
            goodwillClaimAcceptedQuantity: [{value: failurePartDetails.gwClaimApprovedQuantity, disabled: true}],
            priceType: [{value: failurePartDetails.priceType, disabled: failurePartDetails?!failurePartDetails.hodLogin:true}],
            acceptedPercentage: [{value: failurePartDetails.gwApprovedPercent, disabled: failurePartDetails?!failurePartDetails.hodLogin:true},Validators.compose([CustomValidators.numberOnly,Validators.max(100)])],
            failureCode: [{value: failurePartDetails.code, disabled: true}],
            failureDescription: [{value: failurePartDetails.complaintDescription, disabled: true}],
            id: [failurePartDetails.sparePartRequisitionId],
        });
        return failurePartsForm;
    }
    private remarkForm() {
        const remarkForm = this.fb.group({
            dealerRemarks: [{value: null, disabled: false}, Validators.required],
            pcrRemark: [{value: null, disabled: true}],
            kaiRemarks: [{value: null, disabled: false}]
        });
        return remarkForm;
    }
    public createOutsideLabourChargeForm(outSideLabourCharge?:OutSideLabourCharge) {
        const outsideLabourChargeForm = this.fb.group({
            jobcode: [{value: outSideLabourCharge?outSideLabourCharge.jobcode:null, disabled: true}, Validators.required],
            jobcodeId: outSideLabourCharge?outSideLabourCharge.jobcodeId:null,
            jobcodeDesc: [{value: outSideLabourCharge?outSideLabourCharge.jobcodeDesc:null, disabled: true}],
            claimAmount: [{value: outSideLabourCharge?outSideLabourCharge.claimAmount:null, disabled: true},CustomValidators.numberOnly],
            rejectedAmount:[{value: outSideLabourCharge?outSideLabourCharge.rejectedAmount:null, disabled: true}],
            goodwillClaimAmount:[{value: outSideLabourCharge?outSideLabourCharge.goodwillClaimAmount:null, disabled: true},Validators.compose([CustomValidators.numberOnly, Validators.required, Validators.min(0), Validators.max(outSideLabourCharge.rejectedAmount)])],
            goodwillApprovedAmount: [{value: outSideLabourCharge?outSideLabourCharge.goodwillApprovedAmount:null, disabled: true},CustomValidators.numberOnly],
            //acceptedPercentage:[{value: outSideLabourCharge?outSideLabourCharge.acceptedPercentage:null, disabled: true},CustomValidators.numberOnly],
        });
        return outsideLabourChargeForm;
    }
    public createLabourChargeForm(labourCharge?:LabourCharge) {
        const labourChargeForm = this.fb.group({
            labourCode: [{value: labourCharge?labourCharge.labourCode:null, disabled: true}, Validators.required],
            labourChargeId: labourCharge?labourCharge.labourChargeId:null,
            labourDesc: [{value: labourCharge?labourCharge.labourDesc:null, disabled: true}],
            labourHour: [{value: labourCharge?labourCharge.labourHour:null, disabled: true}],
            claimAmount: [{value: labourCharge?labourCharge.claimAmount:null, disabled: true}],
            rejectedAmount:[{value: labourCharge?labourCharge.rejectedAmount:null, disabled: true}],
            goodwillClaimAmount:[{value: labourCharge?labourCharge.goodwillClaimAmount:null, disabled: true},Validators.compose([CustomValidators.numberOnly, Validators.required, Validators.min(0), Validators.max(labourCharge.rejectedAmount)])],
            goodwillApprovedAmount: [{value: labourCharge?labourCharge.goodwillApprovedAmount:null, disabled: true},CustomValidators.numberOnly],
            //acceptedPercentage:[{value: labourCharge?labourCharge.acceptedPercentage:null, disabled: true},CustomValidators.numberOnly],
        });
        return labourChargeForm;
    }
    initializeImplementForm(implementDetails?: WarrantyPcrImplement) {
        return this.implementForm(implementDetails);
    }
    initializeServiceHistoryForm(serviceHistoryDetails?: JobCardHistory) {
        return this.serviceHistoryForm(serviceHistoryDetails);
    }
    
    initializeFailurePartForm(failurePartDetails?: WarrantyPart) {
        return this.failurePartsForm(failurePartDetails);
    }

    get allForm() {
        if(this.form) {
            return this.form as FormGroup;
        }
        this.createForm();
        return this.form as FormGroup;
    }
}