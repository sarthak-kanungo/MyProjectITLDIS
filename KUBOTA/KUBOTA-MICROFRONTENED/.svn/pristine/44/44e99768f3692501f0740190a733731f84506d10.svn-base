import { Injectable } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { ServiceBookingPageStore } from './service-booking-page.store';
import { DetailsByChassisNumber, ViewServiceBooking } from '../../domain/service-booking-domain';
import { MatCheckboxChange } from '@angular/material';

@Injectable()
export class ServiceBookingPagePresenter {

    readonly serviceBookingForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private serviceBookingPageStore: ServiceBookingPageStore
    ) {
        this.serviceBookingForm = this.serviceBookingPageStore.serviceBookingForm
    }

    get detailsForm() {
        return this.serviceBookingForm.get('bookingDetailsBasicInfo') as FormGroup
    }

    get customerMachineDetailsForm() {
        return this.serviceBookingForm.get('customerMachineDetailsInfo') as FormGroup
    }

    get bookingCancellationForm() {
        return this.serviceBookingForm.get('bookingCancellation') as FormGroup
    }

    get viewJobCardForm() {
        return this.serviceBookingForm.get('viewJobCardInfo') as FormGroup
    }

    setErrorForChassisNo() {
        this.detailsForm.get('chassisNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }
    resetFieldForChassisNo() {
        this.detailsForm.get('bookingDate').reset()
        this.detailsForm.get('chassisNo').reset()
        this.detailsForm.get('customerName').reset()
        this.detailsForm.get('engineNo').reset()
        this.detailsForm.get('model').reset()
        this.detailsForm.get('dateofInstallation').reset()
        this.customerMachineDetailsForm.reset()
    }

    patchValueForChassisNo(response: DetailsByChassisNumber) {
        let bookingDate = this.detailsForm.get('bookingDate').value;
        this.detailsForm.patchValue(response)
        this.customerMachineDetailsForm.patchValue(response)
        this.detailsForm.get('bookingDate').patchValue(response.bookingDate ? new Date(response.bookingDate) : bookingDate)
        this.detailsForm.get('dateofInstallation').patchValue(new Date(response.installationDate))
        this.customerMachineDetailsForm.get('previousServiceDate').patchValue(response.previousServiceDate ? new Date(response.previousServiceDate) : null)
        if (typeof response.previousHour === 'number') {
            this.detailsForm.get('previousHours').patchValue(response.previousHour)
        }
        if (typeof response.currentHour === 'number') {
            this.detailsForm.get('currentHours').patchValue(response.currentHour)
        }

        if (typeof response.totalHour === 'number') {
            this.detailsForm.get('totalHours').patchValue(response.totalHour)
        }

        if (typeof response.previousCurrentHour === 'number') {
            this.detailsForm.get('priviousCurrentHours').patchValue(response.previousCurrentHour)
        }

    }

    patchValueForViewBookingDetails(response: ViewServiceBooking) {
        
        this.detailsForm.patchValue(response)
        this.customerMachineDetailsForm.patchValue(response)
        this.bookingCancellationForm.patchValue(response)
        this.detailsForm.get('currentHours').patchValue(response.currentHour)
        this.detailsForm.get('previousHours').patchValue(response.previousHour)
        this.detailsForm.get('totalHours').patchValue(response.totalHour)
        this.detailsForm.get('bookingDate').patchValue(response.bookingDate ? new Date(response.bookingDate) : null)
        this.detailsForm.get('chassisNo').patchValue({ code: response.chassisNo })
        this.detailsForm.get('mechanic').patchValue({ id: response.mechanicId, name: response.name })
        this.detailsForm.get('dateofInstallation').patchValue(response.dateOfInstallation ? new Date(response.dateOfInstallation) : null)
        this.detailsForm.get('sourceofBooking').patchValue({ id: response.sourceOfBookingId, sourceOfBooking: response.sourceOfBooking })
        this.detailsForm.get('serviceCategory').patchValue({ id: response.serviceCategoryId, category: response.category })
        this.detailsForm.get('placeofService').patchValue({ id: response.placeOfServiceId, placeOfService: response.placeOfService })
        this.detailsForm.get('serviceType').patchValue({ id: response.serviceTypeId, serviceType: response.serviceType })
        this.detailsForm.get('activityType').patchValue({ id: response.activityTypeId, value: response.activityType })
        this.detailsForm.get('activityNo').patchValue({ id: response.activityNoId, value: response.activityNumber })
        this.detailsForm.get('appointmentDate').patchValue(response.appointmentDate ? new Date(response.appointmentDate) : null)
        this.customerMachineDetailsForm.get('previousServiceDate').patchValue(response.previousServiceDate ? new Date(response.previousServiceDate) : null)
    }

    patchValueForViewJobCard() {
        this.viewJobCardForm.get('jobCardNo').patchValue('')
        this.viewJobCardForm.get('serviceDate').patchValue(new Date())
        this.viewJobCardForm.get('actualMechanic').patchValue('')
    }

    setValidatorsForReasonForCancellation(event: MatCheckboxChange) {
        if (event.checked) {
            this.bookingCancellationForm.get('reasonForCancellation').reset()
            this.bookingCancellationForm.get('reasonForCancellation').setValidators(Validators.required)
            this.bookingCancellationForm.get('reasonForCancellation').updateValueAndValidity()
        } else {
            this.bookingCancellationForm.get('reasonForCancellation').clearValidators()
            this.bookingCancellationForm.get('reasonForCancellation').updateValueAndValidity()
        }
    }

    setValidatorsForPlaceOfService(isEvent: boolean) {
        if (isEvent) {
            this.detailsForm.get('activityType').setValidators(Validators.required)
            this.detailsForm.get('activityType').updateValueAndValidity()
            this.detailsForm.get('activityNo').setValidators(Validators.required)
            this.detailsForm.get('activityNo').updateValueAndValidity()
        } else {
            this.detailsForm.get('activityType').clearValidators()
            this.detailsForm.get('activityType').updateValueAndValidity()
            this.detailsForm.get('activityNo').clearValidators()
            this.detailsForm.get('activityNo').updateValueAndValidity()
        }
    }

    markFormAsTouched() {
        this.serviceBookingForm.markAllAsTouched();
    }
}