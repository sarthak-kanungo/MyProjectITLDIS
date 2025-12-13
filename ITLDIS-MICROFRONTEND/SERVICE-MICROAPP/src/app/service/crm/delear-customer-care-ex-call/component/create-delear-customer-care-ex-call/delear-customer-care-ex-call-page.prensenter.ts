import { Injectable } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { DelearCustomerCareExCallPageStore } from './delear-customer-care-ex-call-page.store';
import { Operation } from 'src/app/utils/operation-util';

@Injectable()
export class DelearCustomerCareExCallPagePresenter {

    readonly customerCareExeCallForm: FormGroup

    private _operations: string
    private _operation: any;

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private delearCustomerCareExCallPageStore: DelearCustomerCareExCallPageStore,
        private toastr: ToastrService,

    ) {
        this.customerCareExeCallForm = this.delearCustomerCareExCallPageStore.delearCustomerCareExCallCreateForm
    }

    get callDetailsForm() {
        return this.customerCareExeCallForm.get('callDetails') as FormGroup
    }


    get customerDetailsForm() {
        return this.customerCareExeCallForm.get('customerDetails') as FormGroup
    }

    get machineDetailsForm() {
        return this.customerCareExeCallForm.get('machineDetails') as FormGroup
    }

    get freeServiceHistoryDetailsForm() {
        return this.customerCareExeCallForm.get('freeServiceHistoryDetails') as FormArray
    }

    get callHistoryDetailsForm() {
        return this.customerCareExeCallForm.get('callHistoryDetails') as FormArray
    }

    get complaintDetailsForm() {
        return this.customerCareExeCallForm.get('complaintDetails') as FormArray
    }

    get enquiryDetailsForm() {
        return this.customerCareExeCallForm.get('enquiryDetails') as FormGroup
    }

    get createEnquiryForm() {
        return this.customerCareExeCallForm.get('createEnquiry') as FormGroup
    }

    get serviceBookingForm(){
        return this.customerCareExeCallForm.get('serviceBookingDetails') as FormGroup
    }
    get serviceBookingSearchForm() {
        return this.customerCareExeCallForm.get('serviceBookingSearchDetails') as FormGroup
    }
    get postServiceFeedBackForm(){
        
        return this.customerCareExeCallForm.get('postServiceFeedBack') as FormArray;
    }

    get postSalesFeedBackForm(){
        return this.customerCareExeCallForm.get('postSalesFeedBack') as FormArray;
    }
    addRowForService(row?:any){
        const fg = this.delearCustomerCareExCallPageStore.postServiceFeedBack();
        
            if(row){
                fg.patchValue(row);
            }
           
         this.postServiceFeedBackForm.push(fg)
    }
    addRowForSales(row?:any){
        const fg = this.delearCustomerCareExCallPageStore.postSalesFeedBack();
          
            if(row){
                fg.patchValue(row);
             
            }
           
         this.postSalesFeedBackForm.push(fg)
        
    }

    addRowServiceHistory(data){
        const fg:FormGroup = this.delearCustomerCareExCallPageStore.createFreeServiceForm();
        fg.patchValue(data);
        this.freeServiceHistoryDetailsForm.push(fg);
    }
    addRowCallHistory(data){
        const fg:FormGroup = this.delearCustomerCareExCallPageStore.createCallHistoryForm();
        fg.patchValue(data);
        this.callHistoryDetailsForm.push(fg);
    }
    addRowComplaint(data?){
        if(this.complaintDetailsForm.valid){
            const fg:FormGroup = this.delearCustomerCareExCallPageStore.createComplaintForm();
            if(data){
                fg.patchValue(data);
            }
            this.complaintDetailsForm.push(fg);
        }
    }
    deleteRowComplaint(index:number){
        this.complaintDetailsForm.removeAt(index);
    }
    markFormAsTouched() {
        this.customerCareExeCallForm.markAllAsTouched();
    }
    
    allowNumbersOnly(event: KeyboardEvent) {
        const pattern = /[0-9]/;
        let inputChar = String.fromCharCode(event.charCode);
        if (!pattern.test(inputChar)) {
            event.preventDefault();
        }
    }

}