import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class ServiceBookingSearchPageStore {

    private readonly _serviceBookingSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceBookingSearchForm = this.formBuilder.group({
            serviceBookingSearchDetailsForm: this.buildServiceBookingSearchForm(),
        })
    }

    get serviceBookingSearchForm() {
        return this._serviceBookingSearchForm
    }

    private buildServiceBookingSearchForm() {
        return this.formBuilder.group({
            bookingNo: [null],
            status: [null],
            chassisNo: [null],
            bookingFromDate: [null],
            bookingToDate: [null],
            mechanicName: [null],
            engineNo: [null],
            machineSubModel: [null],
            sourceOfBooking: [null],
            serviceCategory: [null],
            placeOfService: [null],
            activityType: [null],
            activityNo: [null],
            serviceType: [null],
            appointmentFromDate: [null],
            appointmentToDate: [null],
        })
    }

}