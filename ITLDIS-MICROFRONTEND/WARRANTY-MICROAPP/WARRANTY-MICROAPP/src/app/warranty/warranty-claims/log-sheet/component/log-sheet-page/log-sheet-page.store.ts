import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { JobCardPart, LogsheetImplements } from '../../domain/log-sheet.domain';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { JobCardHistory } from '../../../product-concern-report/domain/product-concern-report.domain';
import { ObjectUtil } from '../../../../../utils/object-util';


@Injectable()
export class LogSheetPageStore {
    
    private readonly logForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        console.log('---------Logsheet Form Created---------------');
        this.logForm = this.fb.group({
            logsheetForm: this.createLogForm(),
            implement: this.fb.array([]),
            serviceHistory: this.fb.array([]),
            failurePart: this.fb.array([]),
            remark: this.createRemarkForm(),
        });
    }

   /*  private createForm() {
        console.log('---------Logsheet Form Created---------------');
        this.logForm = this.fb.group({
            logsheetForm: this.createLogForm(),
            implement: this.fb.array([]),
            serviceHistory: this.fb.array([]),
            failurePart: this.fb.array([]),
            remark: this.createRemarkForm(),
        });
    } */

    private createLogForm() {

        const logsheetForm = this.fb.group({
            id:[null],
            logsheetNo: [{value: '', disabled: true}],
            logsheetDate: [{value: '', disabled: true}],
            status: [{value: '', disabled: true}],
            logsheetType: [{value: '', disabled: false}, Validators.required],
            jobCardNo: [{value: '', disabled: false}, Validators.compose([Validators.required, CustomValidators.selectFromList()])],
            jobCardDate: [{value: '', disabled: true}],
            
            installationDate: [{value: '', disabled: true}],
            customerName: [{value: '', disabled: true}],
            model: [{value: '', disabled: true}],
            failureDate: [{value: '', disabled: false}, Validators.required],
            address: [{value: '', disabled: true}],
            mobileNumber: [{value: '', disabled: true}],
            registrationNumber: [{value: '', disabled: true}],
            chassisNo: [{value: '', disabled: false}, Validators.compose([Validators.required, CustomValidators.selectFromList()])],
            engineNo: [{value: '', disabled: true}],
            hours: [{value: '', disabled: false}, Validators.compose([Validators.required, CustomValidators.floatNumber])],
            soldToDealer: [{value: '', disabled: true}],
            serviceDealer: [{value: '', disabled: true}],
            serviceDealerAddress: [{value: '', disabled: true}],
            crop: [{value: '', disabled: false}],
            cropCondition: [{value: '', disabled: false}],
            failureType: [{value: '', disabled: false}],
            soilType: [{value: '', disabled: false}],
            fieldCondition: [{value: '', disabled: false}],
            noOfTime:[{value: '', disabled: false}, Validators.compose([Validators.min(2), CustomValidators.numberOnly])],
            repeatFailure: [{value: false, disabled: false}],
            probableCause:[{value: '', disabled: false}, Validators.required],
            resultOfConfirmation:[{value: '', disabled: false}, Validators.required],
            tentativeAction:[{value: '', disabled: false}, Validators.required],
            remarks:[{value: '', disabled: false}, Validators.required],
            qaRemarks:[{value:'',disabled:false}],
            serviceRemarks:[{value:'',disabled:false}],
            linkjobCardNo:[{value: null, disabled: false}],
            pcrNumber:[{value: null, disabled: true}],
        });
        return logsheetForm;
    }

    private createImplementForm(implementDetails?: LogsheetImplements) {
        console.log('implementDetails', implementDetails);
        const form = this.logForm.controls.implement as FormArray;
        const maxPercentage = 100-ObjectUtil.sum(form.controls, 'percentOfUsage');
        const implementForm = this.fb.group({
            implement: [{value: implementDetails == undefined?null:implementDetails.implement, disabled: false}, Validators.required],
            impCategory: [{value: implementDetails == undefined?null:implementDetails.implementCategory, disabled: false}, Validators.required],
            impMake: [{value: implementDetails == undefined?null:implementDetails.implementMake, disabled: false}, Validators.required],
            impSLNo: [{value: implementDetails == undefined?null:implementDetails.implementSerialNumber, disabled: false}, CustomValidators.serialNumber],
            gearRatio: [{value: implementDetails == undefined?null:implementDetails.gearRation, disabled: false}, Validators.pattern('^[1-9]{1,3}([/:][1-9]{1,3})?$')],
            engineRPM: [{value: implementDetails == undefined?null:implementDetails.engineRpm, disabled: false}, CustomValidators.numberOnly],
            usage: [{value: implementDetails == undefined?null:implementDetails.percentOfUsage, disabled: false}, Validators.compose([CustomValidators.floatNumber, Validators.min(1), Validators.max(maxPercentage)])],
            failure: [{value: implementDetails == undefined?null:implementDetails.usageFailure, disabled: false}, ],
            isSelected: [{value: '', disabled: false}, ],
            id:[implementDetails?implementDetails.id:null]
        });
        return implementForm;
    }
   
    private createFailureForm(failurePart?: JobCardPart) {
        const failurePartForm =  this.fb.group({
            partNo:[{value: failurePart == undefined ? '' : failurePart.partNo, disabled: failurePart == undefined? false: true}, CustomValidators.selectFromList()],
            description:[{value: failurePart == undefined ? '' : failurePart.description, disabled: true}, Validators.required],
            claimQuantity:[{value: failurePart == undefined ? '' : failurePart.claimQuantity, disabled: false}, Validators.compose([Validators.required, CustomValidators.numberOnly]) ],
            isSelected: [{value: '', disabled: failurePart == undefined? false: true}],
            id: [failurePart == undefined ? '' : failurePart.id],
            itemNo: [failurePart == undefined ? '' : failurePart.partNo],
            failureDescriptions:[failurePart == undefined ? '' : failurePart.failureDescription],
            failureDescription:[{value:failurePart == undefined? '':failurePart.failureDescription, disabled: true}],
            failureCode:[{value: failurePart == undefined ? '' : failurePart.failureCode, disabled: false}, Validators.compose([Validators.required]) ],
            keyPartNumber:[{value: failurePart == undefined ? '' : failurePart.keyPartNumber, disabled: false} ],
              selectedRadioBtn: [false],
              sparePartMasterId:['Ankit']
              
        });
        return failurePartForm;
    }
    private createServiceHistoryForm(addServiceHistory: JobCardHistory) {
        const serviceHistoryForm = this.fb.group({
            serviceType: [{value: addServiceHistory.serviceType, disabled: true}],
            dateIn: [{value: addServiceHistory.dateIn, disabled: true}],
            jobCardNo: [{value: addServiceHistory.jobCardNo, disabled: true}],
            jobCardDate: [{value: addServiceHistory.jobCardDate, disabled: true}],
            hours: [{value: addServiceHistory.hours, disabled: true}]
        });
        return serviceHistoryForm;
    }
    private createRemarkForm() {
        const remarkForm = this.fb.group({
            dealerRemark: [{value: '', disabled: true}, Validators.required],
            KAIRemark: [{value: '', disabled: true}, Validators.required],
            empName: [{value: '', disabled: true}, Validators.required],
            empDesignation: [{value: '', disabled: true}, Validators.required],
            approvedBy: [{value: '', disabled: true}, Validators.required],
            approvedDate: [{value: '', disabled: true}, Validators.required],
            attachFile: this.fb.array([this.remarkUploadFile()]),
            attachVideo: [{value: '', disabled: true}, Validators.required],
        });
        return remarkForm;
    }

    private remarkUploadFile() {
        return this.fb.group({
            attachfile: [{value: '', disabled: true}, Validators.required]
        });
    }

    get logSheetForm() {
        if(this.logForm) {
            return this.logForm as FormGroup;
        }
        /* this.createForm();
        return this.logForm as FormGroup; */
    }

    initializeCreateImplementForm(implementDetails?: LogsheetImplements) {
        return this.createImplementForm(implementDetails);
    }
    initializeCreateServiceHistoryForm(addServiceHistory: JobCardHistory) {
        return this.createServiceHistoryForm(addServiceHistory);
    }
    initializeFailurePartForm (failurePart?: JobCardPart) {
        return this.createFailureForm(failurePart);
    }
    initializeRemarkUploadFile () {
        return this.remarkUploadFile();
    }
}