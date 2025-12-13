import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ServiceBookingCommonWebService } from '../../service/service-booking-common-web.service';
import { DropDownMechanicName, ServiceCategory, PlaceOfService, SourceOfBooking, AutoCompChassisNumber, BookingNo, MachineModel, ServiceType, Status, EngineNo, ActivityTypeDropDown, ActivityNumber } from '../../domain/service-booking-domain';
import { Observable } from 'rxjs';
import { ServiceBookingSearchWebService } from './service-booking-search-web.service';
import { MatDatepickerInput, MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-service-booking-search',
  templateUrl: './service-booking-search.component.html',
  styleUrls: ['./service-booking-search.component.scss'],
  providers: [ServiceBookingSearchWebService]
})
export class ServiceBookingSearchComponent implements OnInit {

  @Input() serviceBookingSearchForm: FormGroup
  @Input() isAdvanceSearch: boolean
  mechanicName: DropDownMechanicName
  serviceCategory: ServiceCategory
  placeOfService: PlaceOfService
  sourceofBooking: SourceOfBooking
  machineModel: MachineModel
  status: Status
  serviceType: ServiceType
  chassisNoList$: Observable<Array<AutoCompChassisNumber>>
  bookingNoList$: Observable<Array<BookingNo>>
  engineNoList$: Observable<Array<EngineNo>>
  activityType: ActivityTypeDropDown
  activityNoList$: Observable<Array<ActivityNumber>>
  modelId: number
  minDate: Date;
  newdate = new Date()
  maxDate: Date
  constructor(
    private serviceBookingCommonWebService: ServiceBookingCommonWebService,
    private serviceBookingSearchWebService: ServiceBookingSearchWebService
  ) { }

  ngOnInit() {
    this.loadAllDropDown()
    this.getValueChangesList()
    
  }
  ngAfterViewInit() {
    
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate){
        this.maxDate = this.newdate;
      }
      else
     {   this.maxDate = maxDate;
      if (this.serviceBookingSearchForm.get('bookingToDate').value > this.maxDate)
        this.serviceBookingSearchForm.get('bookingToDate').patchValue(this.maxDate);
     }
    }
  }
  loadAllDropDown() {
    this.serviceBookingCommonWebService.getDropDownMechanicName().subscribe(response => {
      this.mechanicName = response
    })
    this.serviceBookingCommonWebService.dropDownServiceCategory('false', 'false').subscribe(response => {
      this.serviceCategory = response
    })
    this.serviceBookingCommonWebService.dropDownPlaceOfService('false').subscribe(response => {
      this.placeOfService = response
    })
    this.serviceBookingCommonWebService.dropDownSourceOfBooking().subscribe(response => {
      this.sourceofBooking = response
    })
    this.serviceBookingSearchWebService.dropDownModel().subscribe(response => {
      this.machineModel = response
    })
    this.serviceBookingSearchWebService.dropDownBookingStatus().subscribe(response => {
      this.status = response
    })
    this.serviceBookingCommonWebService.getAllActivityType().subscribe(response => {
      this.activityType = response
    })
  }
  selectionMachineModel(event: MatSelectChange) {
    this.modelId = event.value.id
  }
  selectionServiceCategory(event: MatSelectChange) {
    this.serviceBookingSearchWebService.dropDownServiceType(event.value.id, this.modelId).subscribe(response => {
      this.serviceType = response
    })
  }
  private getValueChangesList() {
    this.serviceBookingSearchForm.get('chassisNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoChassisNo(chassisNo)
      }
    })
    this.serviceBookingSearchForm.get('bookingNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const bookingNo = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoBookingNo(bookingNo)
      }
    })
    this.serviceBookingSearchForm.get('engineNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const engineNo = typeof valueChange == 'object' ? valueChange.engineNo : valueChange
        this.autoEngineNo(engineNo)
      }
    })
  }
  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.serviceBookingCommonWebService.autoCompleteChassisNoInJobCard(chassisNo, 'false')
  }
 
  autoBookingNo(bookingNo: string) {
    this.bookingNoList$ = this.serviceBookingSearchWebService.autoCompleteBookingNo(bookingNo, 'false')
  }

  autoEngineNo(engineNo: string) {
    this.engineNoList$ = this.serviceBookingSearchWebService.autoCompleteEngineNumber(engineNo)
  }
  
  activityChanged(event: MatSelectChange) {
		this.getActivityNoList(event.value.id)
	}
  private getActivityNoList(activityTypeId: string) {
    this.serviceBookingSearchForm.get('activityNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.value : valueChange
        this.autoActivityNo(activityTypeId, activityNo)
      }
    })
  }
  autoActivityNo(activityTypeId : string, activityNo: string) {
    this.activityNoList$ = this.serviceBookingCommonWebService.autoCompleteActivityNo(activityTypeId,activityNo)
  }

  displayFnActivityNo(value: ActivityNumber) {
    return value ? value.value : undefined
  }
}