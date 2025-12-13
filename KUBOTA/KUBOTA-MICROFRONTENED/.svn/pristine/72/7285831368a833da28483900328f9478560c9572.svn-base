import { Injectable } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { machineTransportStore } from './machine-transport-store';

@Injectable()
export class machineTransportPresenter {

    readonly createMachineHeaderForm: FormGroup


    private _operations: string

    set operation(type: string) {
        this._operations = type
    }
    get operation() {
        return this._operations
    }

    constructor(
        private store: machineTransportStore,
       

    ) {
        this.createMachineHeaderForm = this.store.fetchCreteMachineHeader
    }
    
   

    get headerForm() {
        console.log('presenter')
        return this.createMachineHeaderForm.get('headerDetails') as FormGroup

    }


    get transportForm() {
        return this.createMachineHeaderForm.get('transportDetails') as FormGroup
    }

    get selfRunForm() {
        return this.createMachineHeaderForm.get('selfRunDetails') as FormGroup
    }

    get selfTruckForm() {
        return this.createMachineHeaderForm.get('selfTruckDetails') as FormGroup
    }

 

}