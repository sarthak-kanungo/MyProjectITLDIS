import { Component, OnInit } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ServiceBookingPageStore } from './service-booking-page.store';
import { ServiceBookingPagePresenter } from './service-booking-page-presenter';
import { FormGroup } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { SaveAndSubmitServiceBooking, ViewServiceBooking } from '../../domain/service-booking-domain';
import { ServiceBookingPageWebService } from './service-booking-page-web.service';
import { ToastrService } from 'ngx-toastr';
import { DateService } from '../../../../../root-service/date.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-service-booking-page',
  templateUrl: './service-booking-page.component.html',
  styleUrls: ['./service-booking-page.component.scss'],
  providers: [ServiceBookingPageStore, ServiceBookingPagePresenter, ServiceBookingPageWebService]
})
export class ServiceBookingPageComponent implements OnInit {

  isView: boolean
  dialogMsg: string;
  isDraft: boolean;
  isShowViewJobCard: boolean
  isViewJobCard: boolean
  serviceBookingForm: FormGroup
  bookingDetailsBasicForm: FormGroup
  customerMachineDetailsForm: FormGroup
  bookingCancellationForm: FormGroup
  viewJobCardForm: FormGroup
  viewServiceBooking: ViewServiceBooking
  isViewForCancel: boolean
  isViewbookingCancellation: boolean
  isSubmitDisable: boolean = false;
  bookingStatus:string='Draft';
  constructor(
    private serviceBookingPagePresenter: ServiceBookingPagePresenter,
    public dialog: MatDialog,
    private bookingRt: ActivatedRoute,
    private dateService: DateService,
    private serviceBookingPageWebService: ServiceBookingPageWebService,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public location: Location
  ) { }

  ngOnInit() {
    
    this.serviceBookingPagePresenter.operation = OperationsUtil.operation(this.bookingRt)
    this.serviceBookingForm = this.serviceBookingPagePresenter.serviceBookingForm
    this.bookingDetailsBasicForm = this.serviceBookingPagePresenter.detailsForm
    this.customerMachineDetailsForm = this.serviceBookingPagePresenter.customerMachineDetailsForm
    this.bookingCancellationForm = this.serviceBookingPagePresenter.bookingCancellationForm
    this.viewJobCardForm = this.serviceBookingPagePresenter.viewJobCardForm
    this.viewOrEditOrCreate()
  }
  
  private viewOrEditOrCreate() {
    
    if (this.serviceBookingPagePresenter.operation === Operation.VIEW) {
      this.isView = true
      this.serviceBookingForm.disable()
      this.parseIdAndViewOrEditForm()
    } else if (this.serviceBookingPagePresenter.operation === Operation.EDIT) {
      this.parseIdAndViewOrEditForm()
    } else if (this.serviceBookingPagePresenter.operation === Operation.CREATE && this.activatedRoute.queryParamMap) {
        this.activatedRoute.queryParamMap.subscribe(map => {
          if(map && map.get('chassis') && map.get('servicetype')){
            this.serviceBookingPageWebService.chassisDetailSubject.next({'chassis':atob(map.get('chassis')), servicetype:atob(map.get('servicetype'))});
          }
        });
    }
  }

  private parseIdAndViewOrEditForm() {
    
    this.bookingRt.params.subscribe(prms => {
      this.serviceBookingPageWebService.viewServiceBookingById(`` + atob(prms['id'])).subscribe(response => {
        this.viewServiceBooking = response
        if (response.jobCardId) {
          this.isViewJobCard = true
        }
        if (response.draftFlag === false) {
          this.isViewbookingCancellation = true
          this.bookingCancellationForm.enable()
          this.cancelBtnDisplayForView()
        }
        if (response.cancelBookingFlag === true) {
          this.bookingCancellationForm.disable()
        }
        this.serviceBookingPagePresenter.patchValueForViewBookingDetails(response)
        this.bookingStatus = response.status;
      })
    })
  }

  private cancelBtnDisplayForView() {
    this.bookingCancellationForm.get('cancelBookingFlag').valueChanges.subscribe(flag => {
      if (flag) {
        this.isViewForCancel = true
      } else {
        this.isViewForCancel = false
      }
    })
  }

  saveAndSubmitServiceBooking(isSave: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    
    if (this.serviceBookingForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.serviceBookingPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  bookingCancellation() {
    this.dialogMsg = 'cancel'
    if (this.serviceBookingForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.serviceBookingPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill Reason for Cancellation the mandatory field`, 'Report mandatory field')
    }
  }
  cancelServiceBooking() {
    const serviceBookingForm = this.serviceBookingForm.getRawValue()
    const id = serviceBookingForm.bookingDetailsBasicInfo.id
    const reason = serviceBookingForm.bookingCancellation.reasonForCancellation
    
    this.serviceBookingPageWebService.cancelServiceBooking(id, reason).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate(['../../'], { relativeTo: this.bookingRt })
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while updating', 'Transaction failed')
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while updating', 'Transaction failed')
    })
  }

  submitData() {
    // this.buildJsonServiceBooking()
    this.serviceBookingPageWebService.saveAndsubmitServiceBooking(this.buildJsonServiceBooking()).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        if (this.serviceBookingPagePresenter.operation === Operation.EDIT) {
          this.router.navigate(['../../'], { relativeTo: this.bookingRt })
        } else {
          this.router.navigate(['../'], { relativeTo: this.bookingRt })
        }
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while updating', 'Transaction failed')
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while updating', 'Transaction failed')
    })
  }
  private buildJsonServiceBooking() {
    const serviceBooking = {
      ...this.buildJsonServiceBookingDetails(),
      ...this.buildJsonForBookingInfo()
    }
    
    return serviceBooking
  }
  private buildJsonServiceBookingDetails() {
    const serviceBookingForm = this.serviceBookingForm.getRawValue()
    
    const saveAndSubmitServiceBooking = {} as SaveAndSubmitServiceBooking
    if (this.isDraft === true) {
      saveAndSubmitServiceBooking.draftFlag = true
    } else {
      saveAndSubmitServiceBooking.draftFlag = false
    }
    saveAndSubmitServiceBooking.mechanic = {
      id: serviceBookingForm.bookingDetailsBasicInfo.mechanic.id
    }
    saveAndSubmitServiceBooking.customerMaster = {
      id: serviceBookingForm.bookingDetailsBasicInfo.customerId
    }
    saveAndSubmitServiceBooking.machineInventory = {
      vinId: serviceBookingForm.bookingDetailsBasicInfo.machineInventoryId
    }
    if (serviceBookingForm.bookingDetailsBasicInfo.id) {
      saveAndSubmitServiceBooking.id = serviceBookingForm.bookingDetailsBasicInfo.id
    }

    saveAndSubmitServiceBooking.currentHour = serviceBookingForm.bookingDetailsBasicInfo.currentHours
    saveAndSubmitServiceBooking.totalHour = serviceBookingForm.bookingDetailsBasicInfo.totalHours
    saveAndSubmitServiceBooking.previousHour = serviceBookingForm.bookingDetailsBasicInfo.previousHours

    saveAndSubmitServiceBooking.cancelBookingFlag = serviceBookingForm.bookingCancellation.cancelBookingFlag
    saveAndSubmitServiceBooking.reasonForCancellation = serviceBookingForm.bookingCancellation.reasonForCancellation
    return saveAndSubmitServiceBooking
  }
  private buildJsonForBookingInfo() {
    const serviceBookingForm = this.serviceBookingForm.getRawValue()
    const saveAndSubmitServiceBooking = {} as SaveAndSubmitServiceBooking
    saveAndSubmitServiceBooking.hours = (serviceBookingForm.bookingDetailsBasicInfo.hours && parseFloat(serviceBookingForm.bookingDetailsBasicInfo.hours))
    saveAndSubmitServiceBooking.sourceOfBooking = {
      id: serviceBookingForm.bookingDetailsBasicInfo.sourceofBooking.id
    }
    saveAndSubmitServiceBooking.placeOfService = {
      id: serviceBookingForm.bookingDetailsBasicInfo.placeofService.id
    }
    saveAndSubmitServiceBooking.serviceCategory = {
      id: serviceBookingForm.bookingDetailsBasicInfo.serviceCategory.id
    }
    if( serviceBookingForm.bookingDetailsBasicInfo.serviceType.id==undefined ||  serviceBookingForm.bookingDetailsBasicInfo.serviceType.id==null)
      {
        this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
      }
    saveAndSubmitServiceBooking.serviceMtServiceTypeInfo = {
      id: serviceBookingForm.bookingDetailsBasicInfo.serviceType.id
      
    }
    if (serviceBookingForm.bookingDetailsBasicInfo.placeofService.placeOfService === 'Event/Activity') {
      saveAndSubmitServiceBooking.serviceMtActivityType = {
        id: serviceBookingForm.bookingDetailsBasicInfo.activityType.id
      }
      saveAndSubmitServiceBooking.serviceActivityProposal = {
        id: serviceBookingForm.bookingDetailsBasicInfo.activityNo.id
      }
    }


    saveAndSubmitServiceBooking.appointmentDate = serviceBookingForm.bookingDetailsBasicInfo.appointmentDate ? this.dateService.getDateIntoDDMMYYYY(serviceBookingForm.bookingDetailsBasicInfo.appointmentDate) : null
    saveAndSubmitServiceBooking.appointmentTime = serviceBookingForm.bookingDetailsBasicInfo.appointmentTime
    saveAndSubmitServiceBooking.remarks = serviceBookingForm.bookingDetailsBasicInfo.remarks
    return saveAndSubmitServiceBooking
  }
  private openConfirmDialog(): void | any {
    const message = `Do you want to ${this.dialogMsg} Service Booking?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm' && this.serviceBookingPagePresenter.operation === Operation.CREATE || this.serviceBookingPagePresenter.operation === Operation.EDIT) {
        this.isSubmitDisable = true;
        if(this.dialogMsg=='cancel'){
          this.cancelServiceBooking();  
        }else{
          this.submitData();
        }
      }
      // if (result === 'Confirm' && this.serviceBookingPagePresenter.operation === Operation.VIEW) {
      //   this.isSubmitDisable = true;
      //   this.cancelServiceBooking();
      // }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }
}