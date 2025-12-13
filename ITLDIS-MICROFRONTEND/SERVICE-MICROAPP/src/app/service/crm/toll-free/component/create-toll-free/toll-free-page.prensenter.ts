import { Injectable } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { TollFreePageStore } from './toll-free-page.store';

@Injectable()
export class TollFreePagePresenter {

    readonly createTollFreeForm: FormGroup

    tollComplaint: any[];

    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private tollFreePageStore: TollFreePageStore,
        // private toastr: ToastrService,

    ) {
        this.createTollFreeForm = this.tollFreePageStore.tollFreeCreateForm
    }

    get  getTollFreeParentFrom(){
        return this.createTollFreeForm 
    }

    get tollFreecallDetailsForm() {
        return this.createTollFreeForm.get('tollFreecallDetails') as FormGroup
    }


    get customerDetailsForm() {
        return this.createTollFreeForm.get('customerDetails') as FormGroup
    }

    get machineDetailsForm() {
        return this.createTollFreeForm.get('machineDetails') as FormGroup
    }

    get freeServiceHistoryDetailsForm() {
        return this.createTollFreeForm.get('freeServiceHistoryDetails') as FormArray
    }

    get callHistoryDetailsForm() {
        return this.createTollFreeForm.get('callHistoryDetails') as FormArray
    }

    get complaintDetailsForm() {
        return this.createTollFreeForm.get('complaintDetails') as FormArray
    }

    get enquiryDetailsForm() {
        return this.createTollFreeForm.get('enquiryDetails') as FormGroup
    }

    get serveyHistoryForm(){
        return this.createTollFreeForm.get('serveyHistoryDetails') as FormArray
    }
    addRowServiceHistory(data) {
        const fg: FormGroup = this.tollFreePageStore.createFreeServiceForm();
        fg.patchValue(data);
        this.freeServiceHistoryDetailsForm.push(fg);
    }
    addRowCallHistory(data) {
        const fg: FormGroup = this.tollFreePageStore.createCallHistoryForm();
        fg.patchValue(data);
        this.callHistoryDetailsForm.push(fg);
    }
    addRowServeyHistory(data) {
        const fg: FormGroup = this.tollFreePageStore.createServeyHistoryForm();
        fg.patchValue(data);
        this.serveyHistoryForm.push(fg);
    }
    addRowComplaint(data?) {
        if (this.complaintDetailsForm.valid) {
            const fg: FormGroup = this.tollFreePageStore.createComplaintForm();
            if (data) {
                fg.patchValue(data);
            }
            this.complaintDetailsForm.push(fg);
        }else{
            this.complaintDetailsForm.markAllAsTouched();
        }
    }
    deleteRowComplaint(index: number) {
        this.complaintDetailsForm.removeAt(index);
    }
    markFormAsTouched() {
        this.createTollFreeForm.markAllAsTouched();
    }

    allowNumbersOnly(event: KeyboardEvent) {
        const pattern = /[0-9]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

    tollRecordingFiles(files: any[]) {
        this.tollComplaint = files;
    }

}