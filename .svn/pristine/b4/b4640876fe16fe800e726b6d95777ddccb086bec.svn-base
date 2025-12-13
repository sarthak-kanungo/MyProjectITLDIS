import { Injectable } from '@angular/core';
import { FormGroup, FormArray, FormArrayName } from '@angular/forms';
import { CustomerMasterCreatePageStore } from './customerMasterCreatePage.store';
import { ProspectCropGrown, ProspectMachineryDetail, Sales} from '../../domain/customer-master-domain'
@Injectable()
export class CustomerMasterCreatePagePresenter {

    readonly createCustomerMasterForm: FormGroup

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
        this.createCustomerMasterForm = this.customerMasterCreatePageStore.customerDetailsCreateForm
    }

    get customerCreateDetailsForm() {
        return this.createCustomerMasterForm.get('customerCreateDetailsForm') as FormGroup
    }

    get createCustomerAddressDetails() {
        return this.createCustomerMasterForm.get('createCustomerAddressDetails') as FormGroup
    }

    get createCustomerLandDetails() {
        return this.createCustomerMasterForm.get('createCustomerLandDetails') as FormGroup
    }
    get purchaseDetails() {
        return this.createCustomerMasterForm.get('purchaseDetailsForm') as FormGroup
    }
    get vehicalDetailsForm() {
        return this.createCustomerMasterForm.get('vehicalDetailsForm') as FormGroup
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
            if(machinery.brand === 'Kubota'){
                purchaseForm.controls.modelEditable.patchValue('N')
            }else{
                purchaseForm.controls.modelEditable.patchValue('Y')
            }
        }
        return purchaseForm;
    }

    vehicalDetailsAdd(sale?:Sales) {
        const vehicalDetails = this.vehicalDetailsForm.get('vehicalDetailsTable') as FormArray
        vehicalDetails.push(this.vehicalDetailsTableForm(sale))
    }

    addRow(machinery?:ProspectMachineryDetail) {
        const purchaseDetails = this.purchaseDetails.get('dataTable') as FormArray
        let fg:FormGroup = this.createpurchaseDetails(machinery);
        purchaseDetails.push(fg)
        return fg;
    }

    dltRow() {
        const purchaseDetails = this.purchaseDetails.controls.dataTable as FormArray
        const nonSelected = purchaseDetails.controls.filter(et => !et.value.isSelected)
        purchaseDetails.clear()
        nonSelected.forEach(el => purchaseDetails.push(el))
    }

    markFormAsTouched() {
        this.createCustomerMasterForm.markAllAsTouched();
    }
}