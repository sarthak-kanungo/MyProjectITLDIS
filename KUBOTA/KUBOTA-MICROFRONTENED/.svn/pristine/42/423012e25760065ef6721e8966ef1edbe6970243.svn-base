import { Injectable } from '@angular/core';
import { FormGroup, FormArray, FormArrayName } from '@angular/forms';
import { CustomerMasterCreatePageStore } from '../../customer-master/component/customer-master-create-page/customerMasterCreatePage.store';
import { ProspectCropGrown, ProspectMachineryDetail, Sales} from '../../customer-master/domain/customer-master-domain'
@Injectable()
export class CustomerMasterChangeRequestPresenter {

    readonly customerMasterChangeRequestForm: FormGroup

    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private customerMasterCreatePageStore: CustomerMasterCreatePageStore
    ) {
        this.customerMasterChangeRequestForm = this.customerMasterCreatePageStore.customerMasterChangeRequestForm
    }

    get customerCreateDetailsForm() {
        return this.customerMasterChangeRequestForm.get('customerCreateDetailsForm') as FormGroup
    }

    get createCustomerAddressDetails() {
        return this.customerMasterChangeRequestForm.get('createCustomerAddressDetails') as FormGroup
    }

    get createCustomerLandDetails() {
        return this.customerMasterChangeRequestForm.get('createCustomerLandDetails') as FormGroup
    }
    get purchaseDetails() {
        return this.customerMasterChangeRequestForm.get('purchaseDetailsForm') as FormGroup
    }
    get vehicalDetailsForm() {
        return this.customerMasterChangeRequestForm.get('vehicalDetailsForm') as FormGroup
    }

    vehicalDetailsTableForm(sale?:Sales) {
        var vehicleform:FormGroup = this.customerMasterCreatePageStore.vehicalDetailsForm()       
        if(sale){
            vehicleform.patchValue(sale);
        }
        return vehicleform;
    }

    createpurchaseDetails(machinery?:ProspectMachineryDetail) {
        var purchaseForm:FormGroup = this.customerMasterCreatePageStore.purchaseDetailsRows()
        if(machinery){
            purchaseForm.patchValue(machinery);
        }
        return purchaseForm;
    }

    vehicalDetailsAdd(sale?:Sales) {
        const vehicalDetails = this.vehicalDetailsForm.get('vehicalDetailsTable') as FormArray
        vehicalDetails.push(this.vehicalDetailsTableForm(sale))
    }

    addRow(machinery?:ProspectMachineryDetail) {
        const purchaseDetails = this.purchaseDetails.get('dataTable') as FormArray
        console.log(this.purchaseDetails.get('dataTable').value,'presenter')
        purchaseDetails.push(this.createpurchaseDetails(machinery))
    }

    markFormAsTouched() {
        this.customerMasterChangeRequestForm.markAllAsTouched();
    }
}