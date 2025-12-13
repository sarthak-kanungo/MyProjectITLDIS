import { Injectable } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { UploadableFile } from 'kubota-file-upload';
import { ToastrService } from 'ngx-toastr';
import { FirstTimeBuyer } from '../../domain/direct-survey-domain';
import { DirectSurveyPageStore } from './direct-survey-page.store';

@Injectable()
export class DirectSurveyPagePresenter {

    readonly createDirectSurveyForm: FormGroup
    buyerType: FirstTimeBuyer[]=[{value: 'Y', viewValue: 'Yes'},{value: 'N', viewValue: 'No'},];

    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    surveyType:string
    complaintFiles: UploadableFile[];
    callAttempttFiles: UploadableFile[];

    constructor(
        private directSurveyPageStore: DirectSurveyPageStore,
        private toastr: ToastrService,

    ) {
        this.createDirectSurveyForm = this.directSurveyPageStore.directSurveyCreateForm
    }
    getParentFrom(){
        return this.createDirectSurveyForm 
    }

    get searchDirectSurveyCreateDetailsFrom() {
        return this.createDirectSurveyForm.get('createSurveyHeader') as FormGroup
    }


    get searchDirectSurveyCreateCustomerDetailsForm() {
        return this.createDirectSurveyForm.get('surveyCustomerDetails') as FormGroup
    }

    get searchDirectSurveyCreateMachineDetailsForm() {
        return this.createDirectSurveyForm.get('surveyMachineDetails') as FormGroup
    }

    get searchCurrentSurveyContactPersonDetailsDetailsForm() {
        return this.createDirectSurveyForm.get('searchCurrentSurveyContactPersonDetailsDetails') as FormGroup
    }

    get searchCurrentSurveyMachineDetailsDetailsForm() {
        return this.createDirectSurveyForm.get('searchCurrentSurveyMachineDetailsDetails') as FormGroup
    }

    get searchCurrentSurveyMajorImplementDetailsDetailForm() {
        return this.createDirectSurveyForm.get('searchCurrentSurveyMajorImplementDetailsDetail') as FormGroup
    }

    get searchCurrentSurveyMajorCropsDetailsDetailsForm() {
        return this.createDirectSurveyForm.get('searchCurrentSurveyMajorCropsDetailsDetails') as FormGroup
    }

    get otherMachineDetailsForm() {
        return this.createDirectSurveyForm.get('otherMachineDetails') as FormArray
    }

    get currentSurveyForm() {
        return this.createDirectSurveyForm.get('currentSurvey') as FormArray
    }

    get referenceDetails() {
        return this.createDirectSurveyForm.get('referenceDetails') as FormGroup
    }

    get calAttemptDetails() {
        return this.createDirectSurveyForm.get('calAttemptDetails') as FormGroup
    }

    

    get complainMainForm() {
        return this.createDirectSurveyForm.get('complainMain') as FormGroup
    }

    get complainForm() {
        return this.complainMainForm.get('complain') as FormArray
    }

    get complainRecordingForm() {
        return this.complainMainForm.get('complainRecording') as FormGroup
    }

    get contactMachineImplementForm() {
        return this.createDirectSurveyForm.get('contactMachineImplement') as FormGroup
    }

    get contactDetailsForm() {
        return this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails') as FormGroup
    }

    get machineForm() {
        return this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails') as FormGroup
    }

    get majorImplementForm() {
        return this.contactMachineImplementForm.get('majorImplement') as FormArray
    }

    get cropForm() {
        return this.createDirectSurveyForm.get('crop') as FormGroup
    }
    

    get freeServiceForm() {
        return this.createDirectSurveyForm.get('freeService') as FormArray
    }
    get surveyHistoryForm() {
        return this.createDirectSurveyForm.get('surveyHistory') as FormArray
    }


    

    addRowForOtherMachineDetails(row?:any){
        const fg = this.directSurveyPageStore.otherMachineDetails();
        if(row){
            fg.patchValue(row);
        }
        this.otherMachineDetailsForm.push(fg)
    }

    removeRowForOtherMachineDetails(index:number){
        this.otherMachineDetailsForm.removeAt(index)
    }

    addRowForCurrentSurvey(row?:any){
        const fg = this.directSurveyPageStore.currentSurvey();
        const subQues=fg.get('subAnswer') as FormArray
        subQues.push(this.directSurveyPageStore.currentSurveySubAnswer());
            if(row){
                fg.patchValue(row);
                // console.log(row,'row')
            }
         this.currentSurveyForm.push(fg)
    }

    removeRowCurrentSurvey(index:number){
        this.currentSurveyForm.removeAt(index)
    }

    addRowComplain(){
        const fg = this.directSurveyPageStore.complain();
        this.complainForm.push(fg)
    }
    addComplain(row?:any){
        const fg = this.directSurveyPageStore.complain();
        if(row){
            fg.patchValue(row);
        }
        this.complainForm.push(fg)
    }

    removeRowComplain(index:number){
        this.complainForm.removeAt(index)
    }

    addRowImplement(row?:any){
        const fg = this.directSurveyPageStore.searchCurrentSurveyMajorImplementDetailsDetail();
        if(row){
            fg.patchValue(row);
        }
        this.majorImplementForm.push(fg)
    }

    removeRowImplement(index:number){
        this.majorImplementForm.removeAt(index)
    }

    // addRowCrop(){
    //     const fg = this.directSurveyPageStore.searchCurrentSurveyMajorCropsDetailsDetails();
    //     this.cropForm.push(fg)
    // }

    // removeRowCrop(index:number){
    //     this.cropForm.removeAt(index)
    // }
    addRowForFreeServiceSurvey(row?:any){
        const fg = this.directSurveyPageStore.createFreeServiceForm();
            if(row){
                fg.patchValue(row);
            }
        this.freeServiceForm.push(fg)
    }

    addRowForSurveyHistory(row?:any){
        const fg = this.directSurveyPageStore.surveyHistoryForm();
            if(row){
                fg.patchValue(row);
            }
        this.surveyHistoryForm.push(fg)
    }


    allowNumbersOnly(event: KeyboardEvent) {
        const pattern = /[0-9]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    complaintRecordingFiles(files: UploadableFile[]) {
        this.complaintFiles = files;
    }

    callAttemptRecordingFiles(files: UploadableFile[]) {
        this.callAttempttFiles = files;
    }

}