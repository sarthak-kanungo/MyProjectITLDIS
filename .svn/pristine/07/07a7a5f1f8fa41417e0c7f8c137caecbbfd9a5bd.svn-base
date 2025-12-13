import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class ServiceBookingPageStore {

    private readonly _serviceBookingForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceBookingForm = this.formBuilder.group({
            bookingDetailsBasicInfo: this.buildServiceBookingForm(),
            customerMachineDetailsInfo: this.buildCustomerMachineDetailsInfo(),
            bookingCancellation: this.buildBookingCancellation(),
            viewJobCardInfo: this.buildViewJobCardInfo()
        })
    }

    get serviceBookingForm() {
        return this._serviceBookingForm
    }

    private buildServiceBookingForm() {
        return this.formBuilder.group({

            bookingNo: [{ value: null, disabled: true }],
            bookingDate: [{ value: null, disabled: true }],
            status: [{ value: null, disabled: true }],
            chassisNo: [null, Validators.compose([Validators.required])],
            machineInventoryId: [null],
            customerId: [null],
            id: [null],
            mechanic: [null, Validators.compose([Validators.required])],
            customerName: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            dateofInstallation: [{ value: null, disabled: true }],
            currentHours: [null, Validators.compose([])],
            previousHours: [{ value: null, disabled: true }, Validators.compose([])],
            totalHours: [{ value: null, disabled: true }, Validators.compose([])],
            meterChangeHour: [null, Validators.compose([])],
            priviousCurrentHours: [],
            sourceofBooking: [null, Validators.compose([])],
            serviceCategory: [null, Validators.compose([Validators.required])],
            placeofService: [null, Validators.compose([Validators.required])],
            serviceType: [null, Validators.compose([Validators.required])],
            appointmentDate: [null, Validators.compose([Validators.required])],
            activityType: [null, Validators.compose([Validators.required])],
            activityNo: [null, Validators.compose([])],
            appointmentTime: [null, Validators.compose([Validators.required])],
            remarks: [null, Validators.compose([])],
            registrationNumber: [{ value: null, disabled: true }],
        })
    }

    buildCustomerMachineDetailsInfo() {
        return this.formBuilder.group({
            address: [{ value: null, disabled: true }],
            mobileNumber: [{ value: null, disabled: true }],
            previousServiceType: [{ value: null, disabled: true }],
            previousServiceDate: [{ value: null, disabled: true }],
            previousServiceHour: [{ value: null, disabled: true }],
            nextDueServiceType: [{ value: null, disabled: true }]
        })
    }

    buildBookingCancellation() {
        return this.formBuilder.group({
            cancelBookingFlag: [{ value: false, disabled: false }],
            reasonForCancellation: [null, Validators.compose([])],
        })
    }

    buildViewJobCardInfo() {
        return this.formBuilder.group({
            jobCardNo: [{ value: null, disabled: true }],
            serviceDate: [{ value: null, disabled: true }],
            actualMechanic: [{ value: null, disabled: true }],
        })
    }


}