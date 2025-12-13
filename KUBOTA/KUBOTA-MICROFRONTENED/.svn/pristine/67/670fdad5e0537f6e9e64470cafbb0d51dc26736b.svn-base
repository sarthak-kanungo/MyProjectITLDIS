import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material';
import { ViewServiceBooking } from '../../domain/service-booking-domain';
import { ServiceBookingPagePresenter } from '../service-booking-page/service-booking-page-presenter';

@Component({
  selector: 'app-booking-cancellation',
  templateUrl: './booking-cancellation.component.html',
  styleUrls: ['./booking-cancellation.component.scss']
})
export class BookingCancellationComponent implements OnInit, OnChanges {

  isCancelBookingFlag: boolean
  @Input() bookingCancellationForm: FormGroup
  @Input() viewServiceBooking : ViewServiceBooking

  constructor(
    private serviceBookingPagePresenter : ServiceBookingPagePresenter
  ) { }

  ngOnChanges() {
    if (this.viewServiceBooking) {
      if(this.viewServiceBooking.cancelBookingFlag === true){
        this.isCancelBookingFlag = true
      }
    }
  }

  ngOnInit() {
  }

  onChangeOk(event: MatCheckboxChange) {
    this.serviceBookingPagePresenter.setValidatorsForReasonForCancellation(event)
    if (event.checked) {
      this.isCancelBookingFlag = true
    } else {
      this.isCancelBookingFlag = false
    }
  }

}