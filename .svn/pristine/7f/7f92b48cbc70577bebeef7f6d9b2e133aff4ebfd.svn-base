import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { MatDialog, MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';
import { FormGroup,Validators } from '@angular/forms';
import { BookingDetailsWebService } from './booking-details-web.service';
import { DropDownMechanicName, ServiceCategory, PlaceOfService, SourceOfBooking, AutoCompChassisNumber, ServiceType, ViewServiceBooking, ActivityTypeDropDown, ActivityNumber } from '../../domain/service-booking-domain';
import { ServiceBookingPagePresenter } from '../service-booking-page/service-booking-page-presenter';
import { Operation } from '../../../../../utils/operation-util';
import { Observable } from 'rxjs';
import { ServiceBookingCommonWebService } from '../../service/service-booking-common-web.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { HoursPopUpComponent } from '../../../job-card/component/hours-pop-up/hours-pop-up.component';
import { HoursPopUpServiceBookingComponent } from '../hours-pop-up/hours-pop-up-service-booking.component';
import { ServiceBookingPageWebService } from '../service-booking-page/service-booking-page-web.service';


@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.scss'],
  providers: [BookingDetailsWebService]
})
export class BookingDetailsComponent implements OnInit, OnChanges {

  isView: boolean
  isEdit: boolean
  @Input() bookingDetailsBasicForm: FormGroup;
  @Input()
  min: Date | null
  today = new Date();
  mechanicName: DropDownMechanicName
  serviceCategory: ServiceCategory
  placeOfService: PlaceOfService
  sourceofBooking: SourceOfBooking
  chassisNoList$: Observable<Array<AutoCompChassisNumber>>
  modelId: number
  machineInventoryId: number
  serviceType: ServiceType
  activityType: ActivityTypeDropDown
  activityNoList$: Observable<Array<ActivityNumber>>
  dialogMsg: string
  id: number
  activityTypeId: string
  isPlaceOfService: boolean
  @Input() viewServiceBooking: ViewServiceBooking
  totalHour: number
  jobid:number=0

  constructor(
    public dialog: MatDialog,
    private bookingDetailsWebService: BookingDetailsWebService,
    private serviceBookingPagePresenter: ServiceBookingPagePresenter,
    private serviceBookingCommonWebService: ServiceBookingCommonWebService,
    private serviceBookingPageWebService: ServiceBookingPageWebService,
    private toastr: ToastrService,
    private router: Router,
    private bookingRt: ActivatedRoute
  ) { }

  ngOnChanges() {
    if (this.viewServiceBooking) {

      this.machineInventoryId = this.viewServiceBooking.machineInventoryId
      this.modelId = this.viewServiceBooking.modelId
      this.bookingDetailsBasicForm.get('totalHours').valueChanges.subscribe(value => {
        if (value) {
          this.bookingDetailsBasicForm.get('serviceCategory').reset()
          this.bookingDetailsBasicForm.get('serviceType').reset()
        }
      })
      this.bookingDetailsWebService.getServiceTypeByHour(this.machineInventoryId, this.viewServiceBooking.totalHour, this.viewServiceBooking.category,this.jobid).subscribe(response => {
        this.serviceType = response
      })
      if (this.viewServiceBooking.placeOfService === 'Event/Activity') {
        this.isPlaceOfService = true
      } else {
        this.isPlaceOfService = false
      }
    }
  }

  ngOnInit() {
    this.today.setDate(this.today.getDate());
    this.viewOrEditOrCreate()
    this.getSystemDate()
    this.loadAllDropDown()
    this.getChassisNoList()

    this.serviceBookingPageWebService.chassisDetailSubject.subscribe(obj => {
      if(obj){
        this.getChassisDetailsOnchassinNo(obj.chassis, obj.servicetype);
      }
    })
  }
  private viewOrEditOrCreate() {
    if (this.serviceBookingPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.serviceBookingPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
      this.bookingDetailsBasicForm.get('chassisNo').disable();
    }
  }
  getSystemDate() {
    if (this.serviceBookingPagePresenter.operation === Operation.CREATE) {
      this.bookingDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const pdcDate = response['result']
        this.bookingDetailsBasicForm.get('bookingDate').patchValue(new Date(pdcDate))
      })
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
      const selectedValue = this.sourceofBooking[0]
      this.bookingDetailsBasicForm.get('sourceofBooking').setValue(selectedValue)
    })
    this.serviceBookingCommonWebService.getAllActivityType().subscribe(response => {
      this.activityType = response
    })
  }

  selectionServiceCategory(event: MatSelectChange) {
    if(this.bookingDetailsBasicForm.get('totalHours').value==null ||
    this.bookingDetailsBasicForm.get('totalHours').value==undefined )
    {
      this.bookingDetailsBasicForm.get('totalHours').setValidators(Validators.required)
      this.bookingDetailsBasicForm.get('totalHours').updateValueAndValidity()
      
    }
    const totalHour = (this.bookingDetailsBasicForm.get('totalHours').value && parseFloat(this.bookingDetailsBasicForm.get('totalHours').value))

    this.bookingDetailsBasicForm.get('totalHours').valueChanges.subscribe(value => {
      if (value) {
        this.bookingDetailsBasicForm.get('serviceCategory').reset()
        this.bookingDetailsBasicForm.get('serviceType').reset()
      }
    })


    this.bookingDetailsWebService.getServiceTypeByHour(this.machineInventoryId, totalHour, event.value.category,this.jobid).subscribe(response => {
      this.serviceType = response
    })
  }
  private getChassisNoList() {
    if (this.serviceBookingPagePresenter.operation === Operation.CREATE || this.serviceBookingPagePresenter.operation === Operation.EDIT) {
      this.bookingDetailsBasicForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
          this.autoChassisNo(chassisNo)
        }
        if (valueChange !== null && typeof valueChange !== 'object') {
          this.serviceBookingPagePresenter.setErrorForChassisNo()
        }
      })
    }
  }
  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.serviceBookingCommonWebService.autoCompleteChassisNoInJobCard(chassisNo, 'false')
  }
  selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.bookingDetailsBasicForm.get('chassisNo').setErrors(null);
    }
    this.getChassisDetailsOnchassinNo(event.option.value.code);//, event.option.value.machineInventoryId)
  }
  getChassisDetailsOnchassinNo(chassisNo, servicetype?:string){
    this.bookingDetailsWebService.getChassisDetailsByChassisNoInJobCard(chassisNo, 'false', 'false').subscribe(response => {
      this.dialogMsg = response.message
      if (response.result.draftFlag === true) {
        this.id = response.result.id
        this.openConfirmDialog()
      } else if (response.result.draftFlag === false) {
        this.toastr.success(response.message)
      } else {
        this.serviceBookingPagePresenter.patchValueForChassisNo(response.result)
        if( response.result.machineInventoryId!=null ||  response.result.machineInventoryId!=undefined)
          this.machineInventoryId = response.result.machineInventoryId
        // else
        // this.machineInventoryId = machineInventoryId;

        if(servicetype){
          this.bookingDetailsBasicForm.get('chassisNo').patchValue({'code':chassisNo,'value':chassisNo,'machineInventoryId':this.machineInventoryId});
        }
        this.modelId = response.result.modelId
      }
    })
  }
  displayFnChassisNo(chassisNumber: AutoCompChassisNumber) {
    return chassisNumber ? chassisNumber.code : undefined
  }
  onKeyDownChassisNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.serviceBookingPagePresenter.resetFieldForChassisNo()
    }
  }


  selectionPlaceofService(event: MatSelectChange) {

    if (event.value.placeOfService === 'Event/Activity') {
      this.isPlaceOfService = true
      this.serviceBookingPagePresenter.setValidatorsForPlaceOfService(true)
    } else {
      this.isPlaceOfService = false
      this.serviceBookingPagePresenter.setValidatorsForPlaceOfService(false)
    }
  }

  activityChanged(event: MatSelectChange) {
    this.getActivityNoList(event.value.id)
  }

  private getActivityNoList(activityTypeId: string) {
    this.bookingDetailsBasicForm.get('activityNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.value : valueChange
        this.autoActivityNo(activityTypeId, activityNo)
      }
    })
  }
  autoActivityNo(activityTypeId: string, activityNo: string) {
    this.activityTypeId = activityTypeId
    this.activityNoList$ = this.serviceBookingCommonWebService.autoCompleteActivityNo(activityTypeId, activityNo)
  }

  displayFnActivityNo(value: ActivityNumber) {
    return value ? value.value : undefined
  }
  compareFnMechanic(c1: DropDownMechanicName, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.name === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.name;
    }
    return c1 && c2 ? c1.name === c2.name : c1 === c2;
  }
  compareFnSourceofBooking(c1: SourceOfBooking, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.sourceOfBooking === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.sourceOfBooking;
    }
    return c1 && c2 ? c1.sourceOfBooking === c2.sourceOfBooking : c1 === c2;
  }
  compareFnServiceCategory(c1: ServiceCategory, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.category === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.category;
    }
    return c1 && c2 ? c1.category === c2.category : c1 === c2;
  }
  compareFnServiceType(c1: ServiceType, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.serviceType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.serviceType;
    }
    return c1 && c2 ? c1.serviceType === c2.serviceType : c1 === c2;
  }
  compareFnPlaceofService(c1: PlaceOfService, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.placeOfService === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.placeOfService;
    }
    return c1 && c2 ? c1.placeOfService === c2.placeOfService : c1 === c2;
  }
  compareFnActivityType(c1: ActivityTypeDropDown, c2: ViewServiceBooking): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.activityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.activityType;
    }
    return c1 && c2 ? c1.activityType === c2.activityType : c1 === c2;
  }
  private openConfirmDialog(): void | any {
    const message = `${this.dialogMsg}`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {

      if (result === 'Proceed') {
        this.router.navigate(['../edit', this.id], { relativeTo: this.bookingRt })
      }
      if (result == 'Clear') {
        this.bookingDetailsBasicForm.get('chassisNo').reset()
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Proceed', 'Clear'];
    return confirmationData;
  }


  onBlurCurrentHours(event: FocusEvent) {

    this.getTotalHours(event.target['value'], 0, this.machineInventoryId)
     console.log(this.serviceBookingPagePresenter.detailsForm.get('priviousCurrentHours').value,'jjjjjj')
    if (event.target['value'] < this.serviceBookingPagePresenter.detailsForm.get('priviousCurrentHours').value) {
      this.openHoursPopUp()
    }
  }
  getTotalHours(currentHourValue, meterChangeHour, machineInventoryId) {
    this.bookingDetailsWebService.totalHourCalculation(currentHourValue, meterChangeHour, machineInventoryId).subscribe(res => {
      this.serviceBookingPagePresenter.detailsForm.get('totalHours').patchValue(res.totalHour)
      this.totalHour = this.serviceBookingPagePresenter.detailsForm.get('totalHours').value
      // this.getServiceType(machineInventoryId, res.totalHour, this.serviceBookingPagePresenter.detailsForm.get('serviceCategory').value)
    })

  }
  openHoursPopUp(): void {
    const dialogRef = this.dialog.open(HoursPopUpServiceBookingComponent, {
      width: '40%',
      data: {
        priviousHours: this.serviceBookingPagePresenter.detailsForm.get('priviousCurrentHours').value,
      },
      disableClose: true,
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result == undefined) {
        this.serviceBookingPagePresenter.detailsForm.get('currentHours').reset()
      } else if (result) {

        this.serviceBookingPagePresenter.detailsForm.get('meterChangeHour').patchValue(result.meterChangeValue)
        this.getTotalHours(this.serviceBookingPagePresenter.detailsForm.get('currentHours').value, result.meterChangeValue, this.machineInventoryId)
      }
    })
  }
}