import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatSelectChange } from '@angular/material';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject, Observable } from 'rxjs';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from 'src/app/confirmation-box/confirmation-box.component';
import { BookingDetailsWebService } from 'src/app/service/customer-service/service-booking/component/booking-details/booking-details-web.service';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
import { ToastrService } from 'ngx-toastr';
import { ServiceBookingCommonWebService } from 'src/app/service/customer-service/service-booking/service/service-booking-common-web.service';
import { ActivityNumber, ActivityTypeDropDown, DropDownMechanicName, PlaceOfService, ServiceCategory, ServiceType, SourceOfBooking, ViewServiceBooking } from 'src/app/service/customer-service/service-booking/domain/service-booking-domain';
import { ServiceBookingPagePresenter } from 'src/app/service/customer-service/service-booking/component/service-booking-page/service-booking-page-presenter';
import { ServiceBookingPageStore } from 'src/app/service/customer-service/service-booking/component/service-booking-page/service-booking-page.store';
import { HoursPopUpServiceBookingComponent } from 'src/app/service/customer-service/service-booking/component/hours-pop-up/hours-pop-up-service-booking.component';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { ServiceJobCardHourPopComponent } from '../service-job-card-hour-pop/service-job-card-hour-pop.component';
import { DateService } from 'src/app/root-service/date.service';
import { TollFreeHistoryComponent } from '../../../toll-free/component/toll-free-history/toll-free-history.component';

export interface AutoCompChassisNumber {
  code: string;
  value: string;
  machineInvetoryId: number;
}

@Component({
  selector: 'app-service-booking-details',
  templateUrl: './service-booking-details.component.html',
  styleUrls: ['./service-booking-details.component.css'],
  providers: [ServiceMonitoringBoardService,BookingDetailsWebService,ServiceBookingCommonWebService,ServiceBookingPagePresenter,ServiceBookingPageStore]
})
export class ServiceBookingDetailsComponent implements OnInit {
@Input() bookingDetailsBasicForm:FormGroup
@Input() bookingSearchForm:FormGroup
@Input() typeOfCall: string;
@Input() typeOfCallId:number
@Output() vinId=new EventEmitter<any>();
@Output() customerId=new EventEmitter<any>();
@Output() serviceBookingShowHide=new EventEmitter<boolean>();
@Input() viewServiceBooking:any
@Input() showServiceBookingForm:any
@Input() serviceBookingId:any
  actionButtons = [];
  totalTableElements: number
  public dataTable: DataTable;
  
  page: number = 0
  size: number = 10
  pageLoadCount:number=0
  searchFlag:boolean = true;
  serviceBookingFlag: boolean;
  chassisNo: any;
  serviceType: any;
  dialogMsg: string;
  id: number;
  machineInventoryId: number;
  modelId: number;
  mechanicName: DropDownMechanicName
  serviceCategory: ServiceCategory
  placeOfService: PlaceOfService
  sourceofBooking: SourceOfBooking
  serviceTypeList: ServiceType
  isPlaceOfService: boolean;
  activityType: ActivityTypeDropDown
  totalHour: number;
  activityNoList$: Observable<Array<ActivityNumber>>
  activityTypeId: string;
  chassisData:any;
  jobid:number=0
  customerNameList:any
  customerMobileList:any
  dueTypeList:any
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
  searchValue:any
  historyDataServiceBooking: any;
  maxDate = new Date();
  @Output() checkingServiceBookingDoneOrNotFlag=new EventEmitter<boolean>();
  @Input() hideSubmitButton:any

  constructor(
    private smbService: ServiceMonitoringBoardService,
    private tableDataService: NgswSearchTableService,
    private bookingDetailsWebService: BookingDetailsWebService,
    public dialog: MatDialog,
    private toastr: ToastrService,    
    private serviceBookingCommonWebService: ServiceBookingCommonWebService,    
    private serviceBookingPagePresenter: ServiceBookingPagePresenter,
    private dealerCceService: DelearCustomerCareExCallService,
    private presenter:DelearCustomerCareExCallPagePresenter,
    private activatedRoute:ActivatedRoute,
    private dateService:DateService
  ) { }

  ngOnInit() {
    this.searchTable();
    this.loadAllDropDown();
    this.loadSearchFields();
    this.checkOperation()
    
   

  }
  ngAfterViewInit(){
    debugger
    if (this.presenter.callDetailsForm.controls.callType.value === "Service Booking" && 
    this.presenter.callDetailsForm.controls.status.value === "Completed") {
      this.setServiceBookingValidators();
   console.log('if from Service booling')
   }else{
    console.log()
   }
  }
  setServiceBookingValidators(){
    debugger
    this.bookingDetailsBasicForm.get('mechanic').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('sourceOfBooking').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('currentHour').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('serviceCategory').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('placeOfService').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('appointmentDate').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('appointmentTime').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('remarks').setValidators([Validators.required]);
    // Update value and validity for all controls
    this.bookingDetailsBasicForm.get('mechanic').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('sourceOfBooking').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('currentHour').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('serviceCategory').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('placeOfService').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('appointmentDate').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('appointmentTime').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('remarks').updateValueAndValidity();
  }
  ngOnChanges(){
    // console.log(this.maxDate,'ddd')
    // this.bookingDetailsBasicForm.get('custMobNo').patchValue(this.maxDate)

  }

  private checkOperation(){
  
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true;
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true;
    } else if(this.presenter.operation === Operation.VIEW){
      this.isView=true
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
       
      //  const selectedValue = this.sourceofBooking[0]
      //  this.bookingDetailsBasicForm.get('sourceofBooking').setValue('Dealer CCE')
    })
    this.serviceBookingCommonWebService.getAllActivityType().subscribe(response => {
      this.activityType = response
    })
  }

  loadSearchFields() {
    
      this.bookingSearchForm.get('chassisNo').valueChanges.subscribe( txt => {
        if(txt){
        this.dealerCceService.autoSearchChassisNumber(txt).subscribe(res => {
          this.chassisData = res['result'];
        });
      }
      });   
      
      this.bookingSearchForm.get('mobileNo').valueChanges.subscribe( number => {
        if(number){
          this.dealerCceService.getAutoCompletesSBMobileNumber(number).subscribe(res => {
             this.customerMobileList = res['result']
          })
        }
      })

      this.bookingSearchForm.get('customerName').valueChanges.subscribe( text => {
        if(text){
          this.dealerCceService.autoSBSearchCustomerName(text).subscribe(res => {
            this.customerNameList = res['result']
          })
        }
      })

      this.bookingSearchForm.get('currentServiceDueType').valueChanges.subscribe( text => {
        if(text){
          this.dealerCceService.autoCompleteServiceDueType(text).subscribe(res => {
           this.dueTypeList = res['result']
          })
        }
      })
    
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

  selectionPlaceofService(event: MatSelectChange) {

    if (event.value.placeOfService === 'Event/Activity') {
      this.isPlaceOfService = true
       this.bookingDetailsBasicForm.get('activityNo').reset();
       this.bookingDetailsBasicForm.get('activityType').reset();
      this.bookingDetailsBasicForm.get('activityNo').setValidators([Validators.required])
      this.bookingDetailsBasicForm.get('activityType').setValidators([Validators.required])
      // this.serviceBookingPagePresenter.setValidatorsForPlaceOfService(true)
    } else {
      this.isPlaceOfService = false
      this.bookingDetailsBasicForm.get('activityNo').setValidators(null)
      this.bookingDetailsBasicForm.get('activityType').setValidators([Validators.required])
      // this.serviceBookingPagePresenter.setValidatorsForPlaceOfService(false)
    }
  }

  pageSizeChange(event: any) {
    
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    if(this.pageLoadCount>0){
      this.searchTable();  
    }
    this.pageLoadCount = 1;
    
  }



  searchTable() {

    if (this.dataTable != undefined) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }

    let searchObj = this.bookingSearchForm.getRawValue();
    // searchObj = this.removeNullFromObjectAndFormatDate(searchObj);
    searchObj.page = this.page;
    searchObj.size = this.size
    // let searchobj = {
    //     "page" : this.page,
    //     "size" : this.size,
    //   }
    searchObj['currentServiceDueDate'] = searchObj['currentServiceDueDate']?this.dateService.getDateIntoYYYYMMDD(searchObj['currentServiceDueDate']):null;

       this.dealerCceService.getServiceBookingRecords(searchObj).subscribe(res => {  // ---> new API

        this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        this.totalTableElements = res['count'];
      }, err => {
        this.dataTable = null;
        this.totalTableElements = 0;
      });     
       
  }

  callHistoryTable(chassisNo,typeOfCallId) {
    this.dealerCceService.getCCECallHistory(this.chassisNo,this.typeOfCallId).subscribe(res => {
    
          this.historyDataServiceBooking=res['result']


  })
    
       
  }
  
  scrollToBottom() {
    setTimeout(() => {
      document.getElementById('serviceBookingId').scrollIntoView({ behavior: 'smooth' })
    }, 1000);

  }
 
  serviceBookingValidation:boolean=false;

  tableAction(event){
    if(this.presenter.callDetailsForm.value.crmCallStatus==null || this.presenter.callDetailsForm.value.crmCallStatus==''){
      this.toastr.warning("Please Select Call Status First!")
      this.presenter.callDetailsForm.markAllAsTouched();
      return false;
    }
    if (event['btnAction'].toLowerCase() === 'action') {
      this.serviceBookingValidation=true;
      this.serviceBookingShowHide.emit(true)
      this.scrollToBottom()
      this.chassisNo = event.record['Chassis No'];  
      this.serviceType = event.record['Current Service Due Type'];
      this.bookingDetailsBasicForm.reset();
      this.getChassisDetailsOnchassinNo(this.chassisNo, this.serviceType);
      this.callHistoryTable(this.chassisNo,this.typeOfCallId)
    }else{
      this.serviceBookingValidation=false;;
      this.serviceBookingShowHide.emit(false)
    }
    
    
  }

  getChassisDetailsOnchassinNo(chassisNo, servicetype?:string){
    this.bookingDetailsWebService.getChassisDetailsByChassisNoInJobCard(chassisNo, 'false', 'false').subscribe(response => {
      
      this.vinId.emit(response.result.machineInventoryId);
      this.customerId.emit(response.result.customerId);
      this.dialogMsg = response.message
      if (response.result.draftFlag === true) {
       
        this.id = response.result.id
        this.openConfirmDialog()
      } 
      else if (response.result.draftFlag === false) {
        
        this.toastr.success(response.message)
        
        this.checkingServiceBookingDoneOrNotFlag.emit(true)
        console.log('true')
      } else {
        this.checkingServiceBookingDoneOrNotFlag.emit(false)
      
        this.bookingDetailsBasicForm.get('chassisNo').patchValue(response.result.code)
        this.machineInventoryId=Number(response.result.machineInventoryId)
        let bookingDate = this.bookingDetailsBasicForm.get('bookingDate').value;
        this.bookingDetailsBasicForm.patchValue(response.result)
        this.bookingDetailsBasicForm.get('bookingDate').patchValue(response.result.bookingDate ? new Date(response.result.bookingDate) : bookingDate)
        this.bookingDetailsBasicForm.get('dateofInstallation').patchValue(new Date(response.result.installationDate))
        if (typeof response.result.previousHour === 'number') {
            this.bookingDetailsBasicForm.get('previousHour').patchValue(response.result.previousHour)
        }
        if (typeof response.result.currentHour === 'number') {
            this.bookingDetailsBasicForm.get('currentHour').patchValue(response.result.currentHour)
        }

        if (typeof response.result.totalHour === 'number') {
            this.bookingDetailsBasicForm.get('totalHour').patchValue(response.result.totalHour)
        }

        if (typeof response.result.previousCurrentHour === 'number') {
            this.bookingDetailsBasicForm.get('priviousCurrentHour').patchValue(response.result.previousCurrentHour)
        }


      //   if (typeof response.previousCurrentHour === 'number') {
      //     this.detailsForm.get('priviousCurrentHours').patchValue(response.previousCurrentHour)
      // }
        if( response.result.machineInventoryId!=null ||  response.result.machineInventoryId!=undefined)
          this.machineInventoryId = response.result.machineInventoryId
        // else
        // this.machineInventoryId = machineInventoryId;

        //  if(servicetype){
        //   this.bookingDetailsBasicForm.get('chassisNo').patchValue({'code':chassisNo,'value':chassisNo,'machineInventoryId':this.machineInventoryId});
        // }
        this.modelId = response.result.modelId
      }
    })
  }
  displayFnChassisNo(chassisNumber: AutoCompChassisNumber) {
    return chassisNumber ? chassisNumber.code : undefined
  }
 
  selectionServiceCategory(event: MatSelectChange) {
    console.log('category selected------------------------------------------')
    if(this.bookingDetailsBasicForm.get('totalHour').value==null ||
    this.bookingDetailsBasicForm.get('totalHour').value==undefined )
    {
      this.bookingDetailsBasicForm.get('totalHour').setValidators(Validators.required)
      this.bookingDetailsBasicForm.get('totalHour').updateValueAndValidity()
      
    }
    const totalHour = (this.bookingDetailsBasicForm.get('totalHour').value && parseFloat(this.bookingDetailsBasicForm.get('totalHour').value))

    this.bookingDetailsBasicForm.get('totalHour').valueChanges.subscribe(value => {
      if (value) {
        this.bookingDetailsBasicForm.get('serviceCategory').reset()
        this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').reset()
      }
    })

    console.log('machineInventoryId----------------------------',this.machineInventoryId)

    this.bookingDetailsWebService.getServiceTypeByHour(this.machineInventoryId, totalHour, event.value.category,this.jobid).subscribe(response => {
      this.serviceType = response
    })
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

      // if (result === 'Proceed') {
      //   this.router.navigate(['../edit', this.id], { relativeTo: this.bookingRt })
      // }
      // if (result == 'Clear') {
      //   this.bookingDetailsBasicForm.get('chassisNo').reset()
      // }
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
    

    if (event.target['value'] < this.bookingDetailsBasicForm.get('priviousCurrentHour').value) {
      this.openHoursPopUp()
    }
  }

  getTotalHours(currentHourValue, meterChangeHour, machineInventoryId) {
    this.bookingDetailsWebService.totalHourCalculation(currentHourValue, meterChangeHour, machineInventoryId).subscribe(res => {
      this.bookingDetailsBasicForm.get('totalHour').patchValue(res.totalHour)
      this.totalHour = this.bookingDetailsBasicForm.get('totalHour').value
     
      // this.getServiceType(machineInventoryId, res.totalHour, this.serviceBookingPagePresenter.detailsForm.get('serviceCategory').value)
    })

  }
  openHoursPopUp(): void {
    const dialogRef = this.dialog.open(ServiceJobCardHourPopComponent, {
      width: '40%',
      data: {
         priviousHours: this.bookingDetailsBasicForm.get('priviousCurrentHour').value,
      },
      disableClose: true,
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result == undefined) {
        this.bookingDetailsBasicForm.get('currentHour').reset()
      } else if (result) {

        this.bookingDetailsBasicForm.get('meterChangeHour').patchValue(result.meterChangeValue)
        this.getTotalHours(this.bookingDetailsBasicForm.get('currentHour').value, result.meterChangeValue, this.machineInventoryId)
      }
    })
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

  clearSearchFields() {
    this.bookingSearchForm.reset();
    this.dataTable=null;
  }

  validateNumber(event:any){
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  
  }

  


}
