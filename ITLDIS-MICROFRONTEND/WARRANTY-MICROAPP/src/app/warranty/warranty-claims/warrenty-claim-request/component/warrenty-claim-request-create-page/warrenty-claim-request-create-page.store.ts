import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { JobCardHistory, WarrantyPcrImplement } from '../../../product-concern-report/domain/product-concern-report.domain';
import { FailurePart, OutSideLabourCharge, LabourCharge } from '../../domain/warrenty-claim-request.domain';
import { LocalStorageService } from "src/app/root-service/local-storage.service";

@Injectable()
export class WcrPageStore {

    private readonly form: FormGroup;

    constructor(
        private fb:FormBuilder,
        private localStorageService:LocalStorageService
    ) {
        this.getDealerRequiredValidator()
        this.getitldisRequiredValidator()
        this.form = this.fb.group({
            wcrForm: this.wcrForm(),
            implementForm: this.fb.array([]),
            serviceHistoryForm: this.fb.array([]),
            concernForm: this.concernForm(),
            failurePartsForm: this.fb.array([]),
            labourChargeForm: this.fb.array([]),
            outsideChargeForm: this.fb.array([]),    
            remarkForm: this.remarkForm(),
            uploadForm:this.fileUploadForm(),
            kaiVerifyForm:this.kaiInvoiceForm(),
        });
    }

    /* private createForm() {
        this.form = this.fb.group({
            wcrForm: this.wcrForm(),
            implementForm: this.fb.array([]),
            serviceHistoryForm: this.fb.array([]),
            concernForm: this.concernForm(),
            failurePartsForm: this.fb.array([]),
            remarkForm: this.remarkForm()
        });

    } */

    private wcrForm() {
        const wcrForm = this.fb.group({
            dealerName: [{value: null, disabled: true}],
            dealerAddress: [{value: null, disabled: true}],
            dealerEmail: [{value: null, disabled: true}],
            dealerCode: [{value: null, disabled: true}],
            dealerMobNo: [{value: null, disabled: true}],
            wcrNo: [{value: null, disabled: true}],
            wcrDate: [{value: null, disabled: true}],
            wcrType: [{value: null, disabled: true}],
            wcrStatus: [{value: null, disabled: true}],
            jobCardNo: [{value: null, disabled: true}],
            jobCardDate: [{value: null, disabled: true}],
            retroFitmentNo: [{value: null, disabled: true}],
            pcrNo: [{value: null, disabled: true}],
            pcrDate: [{value: null, disabled: true}],
            goodwillNo: [{value: null, disabled: true}],
            goodwillDate: [{value: null, disabled: true}],
            dateofInstallation: [{value: null, disabled: true}],
            dateofFailure: [{value: null, disabled: true}],
            dateofRepair: [{value: null, disabled: true}],
            customerName: [{value: null, disabled: true}],
            customerAddress: [{value: null, disabled: true}],
            customerMobNo: [{value: null, disabled: true}],
            machineModel: [{value: null, disabled: true}],
            chassisNo: [{value: null, disabled: true}],
            engineNo: [{value: null, disabled: true}],
            soldToDealer: [{value: null, disabled: true}],
            hours: [{value: null, disabled: true}],
            failureType: [{value: null, disabled: true}],

        });
        return wcrForm;        
    }
    private implementForm(implementDetails?: WarrantyPcrImplement) {
        const implementForm = this.fb.group({
            implement: [{value: implementDetails == undefined? null : implementDetails.implement, disabled: true}],
            implementCategory: [{value: implementDetails == undefined? null : implementDetails.implementCategory, disabled: true}, Validators.required],
            implementMake: [{value: implementDetails == undefined? null : implementDetails.implementMake, disabled: true}, Validators.required],
            implementSerialNumber: [{value: implementDetails == undefined? null : implementDetails.implementSerialNumber, disabled: true}, Validators.required],
            gearRatio: [{value: implementDetails == undefined? null : implementDetails.gearRatio, disabled: true}, Validators.required],
            engineRpm: [{value: implementDetails == undefined? null : implementDetails.engineRpm, disabled: true}, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            percentOfUsage: [{value: implementDetails == undefined? null : implementDetails.percentOfUsage, disabled: true}, Validators.compose([Validators.required, CustomValidators.floatNumber])  ],
            usageFailure: [{value: implementDetails == undefined? null : implementDetails.usageFailure, disabled: true}],
        });
        return implementForm; 
    }
    private serviceHistoryForm(serviceHistoryDetails?: JobCardHistory) {
        const serviceHistoryForm = this.fb.group({
            serviceType: [{value: serviceHistoryDetails.serviceType, disabled: true}],
            dateIn: [{value: serviceHistoryDetails.dateIn, disabled: true}],
            jobCardNo: [{value: serviceHistoryDetails.jobCardNo, disabled: true}],
            jobCardDate: [{value: serviceHistoryDetails.jobCardDate, disabled: true}],
            hours: [{value: serviceHistoryDetails.hours, disabled: true}],
        });
        return serviceHistoryForm; 
    }
    private concernForm() {
        const concernForm = this.fb.group({
            customerConcern: [{value: null, disabled:true}],
            dealerObservation: [{value: null, disabled:true}],
            actionTaken: [{value: null, disabled:true}]
        });
        return concernForm; 
    }
    private failurePartsForm(failurePartDetails?: FailurePart) {
        const failurePartsForm = this.fb.group({
            itemNo: [{value: failurePartDetails.itemNo, disabled: true}],
            description: [{value: failurePartDetails.description, disabled: true}],
            approvedQuantity: [{value: failurePartDetails.approvedQuantity, disabled: true}],
            unitPrice: [{value: failurePartDetails.unitPrice, disabled: true}],
            claimValue: [{value: failurePartDetails.claimValue, disabled: true}],
            claimApprovedQuantity: [{value: failurePartDetails.finalApprovedQuantity, disabled: false}, CustomValidators.numberOnly],
            claimApprovedAmount: [{value: failurePartDetails.finalApprovedAmount, disabled: false}, CustomValidators.floatNumber],
            id: [failurePartDetails.id],
        });
        return failurePartsForm; 
    }
    public createOutsideLabourChargeForm(outSideLabourCharge?:OutSideLabourCharge) {
        const outsideLabourChargeForm = this.fb.group({
            jobcode: [{value: outSideLabourCharge?outSideLabourCharge.jobcode:null, disabled: true}, Validators.required],
            jobcodeId: outSideLabourCharge?outSideLabourCharge.jobcodeId:null,
            jobcodeDesc: [{value: outSideLabourCharge?outSideLabourCharge.jobcodeDesc:null, disabled: true}],
            claimAmount: [{value: outSideLabourCharge?outSideLabourCharge.claimAmount:null, disabled: true}],
            pcrAmount: [{value: outSideLabourCharge?outSideLabourCharge.pcrAmount:null, disabled: true}],
            approvedAmount : [{value: outSideLabourCharge?outSideLabourCharge.approvedAmount:null, disabled: true}],
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
            pcrAmount: [{value: labourCharge?labourCharge.pcrAmount:null, disabled: true}],
            approvedAmount: [{value: labourCharge?labourCharge.approvedAmount:null, disabled: true}],
        });
        return labourChargeForm;
    }
    private remarkForm() {
        const remarkForm = this.fb.group({
            kaiRemark: [{value: null, disabled: false}],
            inspectionRemark: [{value: null, disabled: false}],
            inspectedDate: [{value: null, disabled: true}],
            inspectedBy: [{value: null, disabled: true}],
            empName: [{value: null, disabled: true}],
            empDesignation: [{value: null, disabled: true}],
        });
        return remarkForm; 
    }
    private fileUploadForm() {
        const fileUploadForm = this.fb.group({
              fileUpload: [null,  Validators.required],
               invoiceNo:[null, this.getDealerRequiredValidator()],
                invoiceDate:[null, this.getDealerRequiredValidator()]
        });
       
        return fileUploadForm; 
    }

    private kaiInvoiceForm(){
        const kaiInvoiceForm=this.fb.group({
            invoiceNo:[null, this.getitldisRequiredValidator()],
            invoiceDate:[null, this.getitldisRequiredValidator()]
        });
        return kaiInvoiceForm;
    }

    loggedInUserType: any;
    getDealerRequiredValidator() {
        this.loggedInUserType=this.localStorageService.getLoginUser()
        if(this.loggedInUserType){
            if(this.loggedInUserType.userType == "dealer"){
            return Validators.required?Validators.required:null
            }else{
                return Validators.nullValidator
            }  
        }
      }
      getitldisRequiredValidator() {
        this.loggedInUserType=this.localStorageService.getLoginUser()
        if(this.loggedInUserType){
            if(this.loggedInUserType.userType == "itldis"){
            return Validators.required?Validators.required:null
            }else{
                return Validators.nullValidator
            }  
        }
      }

    initializeImplementForm(implementDetails?: WarrantyPcrImplement) {
        return this.implementForm(implementDetails);
    }
    initializeServiceHistoryForm(serviceHistoryDetails?: JobCardHistory) {
        return this.serviceHistoryForm(serviceHistoryDetails);
    }
    
    initializeFailurePartForm(failurePartDetails?: FailurePart) {
        return this.failurePartsForm(failurePartDetails);
    }

    get allForm() {
        if(this.form) {
            return this.form as FormGroup;
        }
        /* this.createForm();
        return this.form as FormGroup; */
    }
}