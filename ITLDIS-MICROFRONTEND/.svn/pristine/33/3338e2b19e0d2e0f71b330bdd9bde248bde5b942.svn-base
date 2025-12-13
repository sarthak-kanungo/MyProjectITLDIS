import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class machineTransportStore {

    private readonly createMachineHeaderForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        console.log('store')
        this.createMachineHeaderForm = this.formBuilder.group({
            headerDetails: this.headerDetailsForm(),
            transportDetails: this.transportDetailsForm(),
            selfRunDetails: this.selfRunDetailsForm(),
            selfTruckDetails:this.selfTruckDetailsForm()
           
        })
    }

    get fetchCreteMachineHeader() {
        return this.createMachineHeaderForm
    }

    private headerDetailsForm() {
        return this.formBuilder.group({
            poType: [{ value: null, disabled: true }],
            depot: [{ value: null, disabled: true }],
            poNo: [{ value: null, disabled: true }],
            poDate: [{value:null,disabled:true}],
            totalQty:[{value:null,disabled:true}],
            orderStatus:[{value:null,disabled:true}],
            typeOfDelivery:[{value:null,disabled:false}],
            modeDelivery:[{value:null,disabled:false}]
        })
    }

    private transportDetailsForm() {
        return this.formBuilder.group({
            transporterName:[{value:null},Validators.required],
            truckNumber:[{value:null},Validators.required],
            driverName:[{value:null},Validators.required],
            driverMobNumber:[{value:null},Validators.required],
            gstNumber:[{value:null},Validators.required],
            terms:[false,Validators.required]

        })
    }
    private selfRunDetailsForm() {
        return this.formBuilder.group({
            personName: [{value:null, disabled:false},Validators.required],
            licenseNumber: [{value:null, disabled:false},Validators.required],
            validTrc: [{value:null, disabled:false},Validators.required],
        })
    }

    

    public selfTruckDetailsForm(){
        return this.formBuilder.group({
            truckNumber: [null, [Validators.required, Validators.pattern('^[A-Z]{2}[\\ -]{0, 1}[0-9]{2}[\\ -]{0, 1}[A-Z]{1, 2}[\\ -]{0, 1}[0-9]{4}$')]],
            // truckNumber: [{value:null, disabled:false},Validators.required,Validators.pattern('^[A-Z]{2}[-][0-9]{1,2}[-][A-Z]{1,2}[-][0-9]{3,4}$')],
            driverName: [{value:null, disabled:false},Validators.required],
            driverMobNumber: [null,[Validators.required,Validators.pattern('^[6-9]\d{9}$')]],
        })
    }


}