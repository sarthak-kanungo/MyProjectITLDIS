import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core'
import { MatDialog, MatCheckboxChange, MatAutocompleteSelectedEvent, MatSelectChange, MatSelect, } from '@angular/material'
import { FormGroup, Validators } from '@angular/forms'
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter'
import { JobCardDetailWebService } from './job-card-detail-web.service'
import { SearchAutocomplete, ServiceCategoryDropDownList, PlaceOfServiceDropDownList, ActivityEventTypesList, ViewJobCard, ServiceType, ServiceCategoryView, ServicetypesList, ServiceMtActivityType, } from '../../domain/job-card-domain'
import { ActivatedRoute, Router } from '@angular/router'
import { Operation } from '../../../../../utils/operation-util'
import { HoursPopUpComponent } from '../hours-pop-up/hours-pop-up.component'
import { ToastrService } from 'ngx-toastr'
import { JobCardPageWebService } from '../create-job-card-page/job-card-page-web.service'
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component'
import { CustomValidators } from 'src/app/utils/custom-validators'

@Component({
	selector: 'app-job-card-details',
	templateUrl: './job-card-details.component.html',
	styleUrls: ['./job-card-details.component.scss'],
	providers: [JobCardDetailWebService, JobCardPageWebService],
})
export class JobCardDetailsComponent implements OnInit {
	@Output() retrofitmentCreated = new EventEmitter();
	@Output() retroList = new EventEmitter();
	@Output() implementListData =new EventEmitter();
	@Output() showImplementForm=new EventEmitter();
	@Input() patchRetroCheckbox
	showRetromentHeader: boolean = false
	isShowEvent: any
	lastTrnsDate: Date
	delayReasons;
	chassisNo: string
	data: any
	buttonDisabled: any
	@Input() public set isPresales(isPresales) {
		if (isPresales) {
			this.isHideForPresales = true
			this.getServiceCategory(true, this.jobCardDetailsForm.get('retrofitmentFlag').value)
		}
	}
	@Input() public set machineIdPage(machineIdPage) {
		this.machineId = machineIdPage
	}
	@Input() public set serviceTypeDropdownRes(serviceTypeDropdownRes) {
		this.servicetypes = serviceTypeDropdownRes
	}
	today = new Date();
	jobCardDetailsForm: FormGroup
	isHideForPresales: boolean
	checked: boolean = false
	isView: boolean
	activityNoList: Array<SearchAutocomplete>
	kaibookingNoList: Array<SearchAutocomplete>
	kaiChassisNoList: Array<SearchAutocomplete>
	serviceCategories: ServiceCategoryDropDownList
	placeOfServices: PlaceOfServiceDropDownList
	servicetypes: ServicetypesList
	activityEventTypes: ActivityEventTypesList
	isEdit: boolean
	isCreate: boolean
	machineId: number
	totalHour: number
	modelId: number
	dialogMsg: string
	id: string
	machineDate: Date
	@Input() machineInDate: Date
	machineInTime: Date
	constructor(
		public dialog: MatDialog,
		private presenter: JobCardPresenter,
		private jobCardDetailWebService: JobCardDetailWebService,
		private toastr: ToastrService,
		private activateRoute: ActivatedRoute,
		private router: Router,
	) { }
	ngOnInit() {
		this.patchOrCreate()
		this.getSystemDate()
		this.chassisNumberChange()
		this.bookingNumberChange()
		this.getActivityEvent()
		this.viewOrEditOrCreate()
		this.checkRouterValues()
		this.machineDateCheck()
		this.jobCardDetailWebService.lookupByCode('SV_CLOSE_DELAY_REASON').subscribe(response => {
			this.delayReasons = response['result'];
		})

	this.jobCardDetailsForm.get('retroFitment').disable()

	}

	checkRouterValues() {
		this.activateRoute.queryParamMap.subscribe(param => {
			if (param.get('chassisNumber')) {
				this.isHideForPresales = true
				this.presenter.jobCardBasicInfo.get('checkBox').setValue(true)
				this.presenter.jobCardBasicInfo.get('currentHours').clearValidators()
				this.presenter.jobCardBasicInfo.get('dateofFailure').clearValidators()
				this.presenter.jobCardBasicInfo.get('dateofFailure').setErrors(null)
				this.getServiceCategory(true, this.jobCardDetailsForm.get('retrofitmentFlag').value)
				this.getPlaceOfCategory(true)

			}
		})
	}
	setValueFromView() {
		if (this.presenter.jobCardBasicInfo.get('checkBox').value == true) {
			this.getServiceCategory(true, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			this.getPlaceOfCategory(true)

			this.isHideForPresales = true
			this.presenter.jobCardBasicInfo.get('currentHours').clearValidators()
			this.presenter.jobCardBasicInfo.get('dateofFailure').clearValidators()
			this.presenter.jobCardBasicInfo.get('dateofFailure').setErrors(null)
			// console.log('this.isHideForPresales', this.isHideForPresales)
		} else if (this.presenter.jobCardBasicInfo.get('checkBox').value == false) {
			this.getPlaceOfCategory(false)

			this.getServiceCategory(false, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			this.isHideForPresales = false
			// console.log('this.isHideForPresales', this.isHideForPresales)
		}
	}
	private viewOrEditOrCreate() {
		if (this.presenter.operation === Operation.VIEW) {
			this.isView = true
			this.isEdit = false
			this.setValueFromView()
			this.getServiceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			this.getPlaceOfCategory(this.presenter.jobCardBasicInfo.get('checkBox').value)

		} else if (this.presenter.operation === Operation.EDIT) {
			this.setValueFromView()
			this.isEdit = true
			this.getServiceCategory(
				this.presenter.jobCardBasicInfo.get('checkBox').value, 'true'
			)
			this.getPlaceOfCategory(this.presenter.jobCardBasicInfo.get('checkBox').value)

		} else if (this.presenter.operation === Operation.CREATE) {
			this.isCreate = true
			this.getPlaceOfCategory(this.presenter.jobCardBasicInfo.get('checkBox').value)

		}
	}
	getSystemDate() {
		this.jobCardDetailWebService.getSystemDate().subscribe(res => {
			this.presenter.jobCardBasicInfo.get('jobCardDate').patchValue(res['result'])
		})
	}
	getServiceCategory(flag: any, retrofitmentFlag: any) {
		// console.log('retrofitmentFlag', retrofitmentFlag)
		// console.log('flag', flag)
		this.jobCardDetailWebService.serviceCategoryDropDown(flag, retrofitmentFlag).subscribe(res => {
			this.serviceCategories = res
		})
	}
	getPlaceOfCategory(preSalesFlag: any) {
		this.jobCardDetailWebService.placeOfServiceDropDown(preSalesFlag).subscribe(res => {
			this.placeOfServices = res
		})
	}
	getActivityEvent() {
		this.jobCardDetailWebService.activityEventTypesListDropDown().subscribe(res => {
			// console.log('activityEventTypesListDropDown', res)
			this.activityEventTypes = res
		})
	}
	private patchOrCreate() {
		this.jobCardDetailsForm = this.presenter.jobCardBasicInfo

		this.presenter.jobCardBasicInfo.get('placeofService').valueChanges.subscribe(
			res => {
				if (res) {

					if (res.placeOfService == "Event/Activity" || res == "Event/Activity") {
						this.isShowEvent = true;
						this.jobCardDetailsForm.get('activityNo').setValidators(Validators.required)
						this.jobCardDetailsForm.get('activityType').setValidators(Validators.required)
					}
					else {
						this.isShowEvent = false;
						this.jobCardDetailsForm.get('activityNo').clearValidators()
						this.jobCardDetailsForm.get('activityType').clearValidators()
					}
				}
			})

	}
	private chassisNumberChange() {
		this.jobCardDetailsForm.get('chassisNo').valueChanges.subscribe(changedValue => {
			this.jobCardDetailsForm.get('retroFitment').reset()

			if (changedValue !== null) {
				this.jobCardDetailsForm.get('chassisNo').setErrors({
					selectFromList: 'Please select from list',
				})
			}
			this.jobCardDetailWebService.chassisNumberAuto(changedValue, this.presenter.jobCardBasicInfo.get('checkBox').value ? this.presenter.jobCardBasicInfo.get('checkBox').value : false).subscribe(resChassisNumber => {
				this.kaiChassisNoList = resChassisNumber
			})
		})
	}
	onBlurAlternateMobileNo(event: FocusEvent) {
		// console.log('event', event)
		if (this.jobCardDetailsForm.get('customerCellNo').value == this.jobCardDetailsForm.get('alternateCustomerCellNo').value) {
			// console.log('alt numm', this.jobCardDetailsForm.get('alternateCustomerCellNo').value)
			// console.log('custNum', this.jobCardDetailsForm.get('customerCellNo').value)
			this.jobCardDetailsForm.get('alternateCustomerCellNo').setErrors({
				sameAsCustomerNumber: 'Same Number Not Allowed'
			})
		}
	}
	selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
		this.jobCardDetailWebService.getImplementByVinId(event.option.value.machineInventoryId).subscribe(res=>{
			if (res.implementDetails.length>0) {
				this.implementListData.emit(res.implementDetails);
				this.showImplementForm.emit(true);
			} else {
				this.showImplementForm.emit(false)
				this.toastr.warning("Implement details are not available");
			}
		})
		if (event instanceof MatAutocompleteSelectedEvent) {
			this.jobCardDetailsForm.get('chassisNo').setErrors(null)
		}
		
		this.presenter.jobCardBasicInfo.get('bookingNo').reset()

		this.jobCardDetailWebService.chassisDetails(event.option.value.code, this.presenter.jobCardBasicInfo.get('checkBox').value ? this.presenter.jobCardBasicInfo.get('checkBox').value : false, true).subscribe(selectedChassisNumber => {
			this.chassisNo = selectedChassisNumber.result['code']
			// console.log('selectedChassisNumber', selectedChassisNumber.result['retroFlag'] === 'Retro-Chassis')
			if (selectedChassisNumber.result['retroFlag'] === 'Retro-Chassis') {
				this.toastr.info("This chassis number under Retrofitment")
			}
			this.presenter.customerConcern.get('customerConcernEditableTable').patchValue(selectedChassisNumber.result.ServiceBookingRemarks)
			this.dialogMsg = selectedChassisNumber['message']
			if (selectedChassisNumber['result'].result === true) {
				this.toastr.warning(selectedChassisNumber['message']);
				this.jobCardDetailsForm.get('chassisNo').reset();
			} else if (selectedChassisNumber['result'].draftFlag === true) {
				this.id = selectedChassisNumber['result']['jobCardNo']
				this.openConfirmDialog()
			} else if (selectedChassisNumber['result'].draftFlag === false) {
				this.toastr.success(selectedChassisNumber['message'])
				this.id = selectedChassisNumber['result']['jobCardNo']
				this.openConfirmDialog()
				// this.presenter.resetAlredyCreatedJobCard()
			} else {
				this.presenter.patchValueFromChassisNumber(selectedChassisNumber['result'])
				if (selectedChassisNumber.result['retroFlag'] === 'Retro-Chassis') {
					this.getServiceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value, 'true')
					this.jobCardDetailsForm.get('retroFitment').enable()
					// return true
				} else{
					this.jobCardDetailsForm.get('retroFitment').disable()
					this.getServiceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value, this.jobCardDetailsForm.get('retrofitmentFlag').value)
				}
				// {
					
				//this.getServiceType(selectedChassisNumber['result'].machineInventoryId, selectedChassisNumber['result'].totalHour, selectedChassisNumber['result'].category)
				this.presenter.jobCardBasicInfo.get('customerMasterId').patchValue(selectedChassisNumber['result'].customerId)

				let previousServiceDate = selectedChassisNumber['result']['previousServiceDate'];
				if (previousServiceDate) {
					//const d = previousServiceDate.split('-');
					this.lastTrnsDate = new Date(previousServiceDate);
				}
				this.machineId = selectedChassisNumber['result'].machineInventoryId
				if (selectedChassisNumber['result'].modelId) {
					this.presenter.jobCardBasicInfo.get('modelId').patchValue(selectedChassisNumber['result'].modelId)
				}
				this.totalHour = selectedChassisNumber['result'].totalHour
			// }
			}
		},
			error => {
				this.toastr.error('Error While Getting Details From Chassis Number')
			})
	}
	private bookingNumberChange() {
		const jobCardFlag: boolean = true
		this.presenter.jobCardBasicInfo.get('bookingNo').valueChanges.subscribe(changedValue => {
			if (changedValue !== null) {
				this.jobCardDetailsForm.get('bookingNo').setErrors({
					selectFromList: 'Please select from list',
				})
			}
			this.jobCardDetailWebService.bookingNumberAuto(changedValue, jobCardFlag).subscribe(resChassisNumber => {
				this.kaibookingNoList = resChassisNumber
			})
		})
	}
	selectedbookingNo(event: MatAutocompleteSelectedEvent) {
		if (event instanceof MatAutocompleteSelectedEvent) {
			this.presenter.jobCardBasicInfo.get('bookingNo').setErrors(null)
		}
		this.presenter.jobCardBasicInfo.get('chassisNo').reset()

		this.jobCardDetailWebService.bookingNoDetails(event.option.value.id).subscribe(selectedChassisNumber => {
			// console.log('selectedChassisNumber', selectedChassisNumber)
			this.presenter.patchValueFromBookingNumber(selectedChassisNumber)
			this.machineId = selectedChassisNumber.machineInventoryId
			this.presenter.jobCardBasicInfo.get('customerMasterId').patchValue(selectedChassisNumber.customerId)
			this.getServiceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			//this.getServiceType(selectedChassisNumber.machineInventoryId, selectedChassisNumber.totalHour, selectedChassisNumber.serviceCategory)
			this.presenter.customerConcern.get('customerConcernEditableTable').patchValue(selectedChassisNumber.ServiceBookingRemarks)
		})
	}
	private openConfirmDialog(): void | any {
		const message = `${this.dialogMsg}`
		const confirmationData = this.setConfirmationModalData(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Continue') {
				this.router.navigate(['../edit'], {
					relativeTo: this.activateRoute,
					queryParams: { id: this.id },
				})
			}
		})
	}
	private setConfirmationModalData(message: string) {
		const confirmationData = {} as ConfirmDialogData
		confirmationData.buttonAction = [] as Array<ButtonAction>
		confirmationData.title = 'Confirmation'
		confirmationData.message = message
		confirmationData.buttonName = ['Cancel', 'Continue']
		return confirmationData
	}
	selectionChangedServiceCategory(event: MatSelectChange) {
		if (this.presenter.jobCardBasicInfo.get('currentHours').value == null) {
			this.toastr.error('Please fill Current Hours')
			this.presenter.jobCardBasicInfo.get('serviceCategory').reset();
			return;
		}
		this.getServiceType(this.machineId, this.presenter.jobCardBasicInfo.get('totalHours').value, event.value.category)

	}
	checkCondition() {
		if (this.presenter.jobCardBasicInfo.get('currentHours').value == null ||
			this.presenter.jobCardBasicInfo.get('serviceCategory').value == null) {
			this.toastr.error('Please Enter Current Hours And Service Category');
			this.presenter.jobCardBasicInfo.get('serviceType').reset();
		}
	}

	getServiceType(machineIdSend: any, totalHour: any, servicetype: any) {
		this.jobCardDetailWebService.servicetypesListDropDown(machineIdSend, totalHour, servicetype, 0).subscribe(res => {
			this.servicetypes = res
		})
	}
	public displayFnChassisNo(selectedOption?: object): string | undefined {
		if (!!selectedOption && typeof selectedOption === 'string') {
			return selectedOption
		}
		return selectedOption ? selectedOption['code'] : undefined
	}
	public displayFnActivityNo(selectedOption?: object): string | undefined {
		if (!!selectedOption && typeof selectedOption === 'string') {
			return selectedOption
		}
		return selectedOption ? selectedOption['value'] : undefined
	}

	displayFnBookingNo(selectedOption?: object): string | undefined {
		if (!!selectedOption && typeof selectedOption === 'string') {
			return selectedOption
		}
		return selectedOption ? selectedOption['code'] : undefined
	}
	activityChanged(event: MatSelectChange) {
		this.activityNoChanged(event.value.id)

	}
	placeChanged(event: MatSelectChange) {
		console.log('event', event)
		if (event.value.placeOfService == "Customer Place") {
			this.jobCardDetailsForm.get('activityNo').reset()
			this.jobCardDetailsForm.get('activityType').reset()
			console.log('this.jobCardDetailsForm', this.jobCardDetailsForm)
			this.jobCardDetailsForm.get('activityNo').setValidators(null)
			this.jobCardDetailsForm.get('activityType').setValidators(null)
		} if (event.value.placeOfService == "Dealer Workshop") {
			this.jobCardDetailsForm.get('activityNo').reset()
			this.jobCardDetailsForm.get('activityType').reset()
			this.jobCardDetailsForm.get('activityNo').setValidators(null)
			console.log('this.jobCardDetailsForm', this.jobCardDetailsForm)
			this.jobCardDetailsForm.get('activityType').setValidators(null)
		} if (event.value.placeOfService == "Event/Activity") {
			this.jobCardDetailsForm.get('activityNo').setValidators(Validators.required)
			this.jobCardDetailsForm.get('activityType').setValidators(Validators.required)
		}
	}
	private activityNoChanged(activityTypeId: any) {
		this.jobCardDetailsForm.get('activityNo').valueChanges.subscribe(changedValue => {
			if (changedValue !== null) {
				this.jobCardDetailsForm.get('activityNo').setErrors({
					selectFromList: 'Please select from list',
				})
			}
			this.jobCardDetailWebService.activityNumberAuto(activityTypeId, changedValue).subscribe(reActivityNumber => {
				this.activityNoList = reActivityNumber
			})
		})
	}

	selectedActivityNumber(event: MatAutocompleteSelectedEvent) {
		if (event instanceof MatAutocompleteSelectedEvent) {
			this.presenter.jobCardBasicInfo.get('activityNo').setErrors(null)
		}
	}

	onKeyDownBookingNo(event: KeyboardEvent) {
		this.presenter.bookingNoKey(event)
	}
	onKeyDownChassisNo(event: KeyboardEvent) {
		this.presenter.chassisNoKey(event)
	}

	checkBoxActionChange(event: MatCheckboxChange) {
		if (event.checked == true) {
			this.isHideForPresales = true
			this.getServiceCategory(true, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			this.getPlaceOfCategory(true)
			this.jobCardDetailsForm.get('placeofService').disable()
			this.jobCardDetailsForm.get('serviceCategory').disable()
			this.jobCardDetailsForm.get('chassisNo').enable()
			this.jobCardDetailsForm.get('placeofService').reset()
			this.jobCardDetailsForm.get('serviceCategory').reset()
			this.presenter.jobCardBasicInfo.get('currentHours').clearValidators()
			this.presenter.jobCardBasicInfo.get('currentHours').setErrors(null)
			this.presenter.jobCardBasicInfo.get('dateofFailure').setErrors(null)
			this.presenter.jobCardBasicInfo.get('dateofFailure').clearValidators()
			this.presenter.outsideJobCharge.get('suggestionAndAdvice').clearValidators();
			this.presenter.outsideJobCharge.get('suggestionAndAdvice').updateValueAndValidity();
			if (this.presenter.jobCardBasicInfo.get('chassisNo').value) {
				this.openConfirmDialogCheckBox()
			}
		} else if (event.checked == false) {
			this.getServiceCategory(false, this.jobCardDetailsForm.get('retrofitmentFlag').value)
			this.getPlaceOfCategory(false)

			this.isHideForPresales = false
			this.jobCardDetailsForm.get('placeofService').enable()
			this.jobCardDetailsForm.get('chassisNo').enable()
			this.jobCardDetailsForm.get('serviceCategory').enable()
			this.jobCardDetailsForm.get('placeofService').reset()
			this.jobCardDetailsForm.get('serviceCategory').reset()
			if (this.presenter.jobCardBasicInfo.get('chassisNo').value) {
				this.openConfirmDialogCheckBox()
			}
		}
	}
	private openConfirmDialogCheckBox(): void | any {
		const message = `"PDI/PDC failed chassis number will be accepted, are you sure ?"`
		const confirmationData = this.setConfirmationModalDataCheckBox(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Clear') {
				this.presenter.selectionChange()
			}
		})
	}
	private setConfirmationModalDataCheckBox(message: string) {
		const confirmationData = {} as ConfirmDialogData
		confirmationData.buttonAction = [] as Array<ButtonAction>
		confirmationData.title = 'Confirmation'
		confirmationData.message = message
		confirmationData.buttonName = ['Proceed', 'Clear']
		return confirmationData
	}

	serviceCategoryCompareFn(c1: ServiceCategoryDropDownList, c2: ServiceCategoryView,): boolean {
		// console.log('service cat@@@@@@@',c1)
		// console.log(c2,'32444444444444443')
	
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.category === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.category
		}
		return c1 && c2 ? c1.category === c2.category : c1 == c2
	}
	serviceTypeCompareFn(c1: ServicetypesList, c2: ServiceType): boolean {
		// console.log('ServiceType', c2)
		// console.log('ServicetypesList', c1)
		
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.serviceType === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.serviceType
		}
		return c1 && c2 ? c1.serviceType === c2.serviceType : c1 === c2
	}
	placecompareFn(c1: PlaceOfServiceDropDownList, c2: ViewJobCard): boolean {
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.placeOfService === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.placeOfService
		}
		return c1 && c2 ? c1.placeOfService === c2.placeOfService : c1 === c2
	}
	activityTypeFn(c1: ActivityEventTypesList, c2: ServiceMtActivityType): boolean {
		// console.log('ServiceMtActivityTypeView', c2)
		// console.log('ActivityEventTypesList', c1)
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.activityType === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.activityType
		}
		return c1 && c2 ? c1.activityType === c2.activityType : c1 === c2
	}
	onBlurCurrentHours(event: FocusEvent) {
		this.presenter.jobCardBasicInfo.get('meterChangeHour').patchValue(0);
		if (
			event.target['value'] <
			this.presenter.jobCardBasicInfo.get('priviousCurrentHours').value
		) {
			this.openHoursPopUp()
		} else {

			this.getTotalHours(event.target['value'], 0, this.machineId)
		}
	}
	getTotalHours(currentHourValue, meterChangeHour, machineId) {
		this.jobCardDetailWebService.totalHourCalculation(currentHourValue, meterChangeHour, machineId).subscribe(res => {
			this.presenter.jobCardBasicInfo.get('totalHours').patchValue(res.totalHour)
			this.totalHour = this.presenter.jobCardBasicInfo.get('totalHours').value
			// this.getServiceType(machineId, res.totalHour, this.presenter.jobCardBasicInfo.get('serviceCategory').value)
		})
	}
	openHoursPopUp(): void {
		const dialogRef = this.dialog.open(HoursPopUpComponent, {
			width: '40%',
			data: {
				priviousHours: this.presenter.jobCardBasicInfo.get('priviousCurrentHours').value,
			},
			disableClose: true,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result == undefined) {
				this.presenter.jobCardBasicInfo.get('currentHours').reset()
			} else if (result) {
				this.presenter.jobCardBasicInfo.get('meterChangeHour').patchValue(result.meterChangeValue)
				this.getTotalHours(this.presenter.jobCardBasicInfo.get('currentHours').value, result.meterChangeValue, this.machineId)
			}
		})
	}
	machineDateCheck() {
		this.jobCardDetailsForm.get('dateofFailure').valueChanges.subscribe(
			res => {
				const dateForValidation = res
				this.machineDate = new Date(dateForValidation)
			}
		)
		// this.jobCardDetailsForm.get('date').valueChanges.subscribe(
		// 	res => {
		// 		const dateForValidation = res
		// 		this.machineInDate = new Date(dateForValidation);
		// 		this.machineInOutDateValidation('date');
		// 	}	
		// )
		this.jobCardDetailsForm.get('machineOutDate').valueChanges.subscribe(
			res => {
				this.machineInOutDateValidation('machineOutDate');
			}
		)
		// this.jobCardDetailsForm.get('timein').valueChanges.subscribe(
		// 	res => {
		// 		this.machineInOutDateValidation('timein');
		// 	}	
		// )
		this.jobCardDetailsForm.get('timeout').valueChanges.subscribe(
			res => {
				this.machineInOutDateValidation('timeout');
			}
		)
	}

	machineInOutDateValidation(controlName) {
		if (this.jobCardDetailsForm.get('machineOutDate').value && this.jobCardDetailsForm.get('timeout').value) {
			let inDate = this.jobCardDetailsForm.get('date').value;
			let inTime = this.jobCardDetailsForm.get('timein').value;

			let newDate = new Date();

			newDate.setDate((inDate.split('-'))[2])
			newDate.setMonth(((inDate.split('-'))[1]) - 1)
			newDate.setFullYear((inDate.split('-'))[0])

			newDate.setHours((inTime.split(':'))[0])
			newDate.setMinutes((inTime.split(':'))[1])

			let outDate: Date = new Date();
			if (typeof this.jobCardDetailsForm.get('machineOutDate').value == 'string') {
				const outdatetemp = this.jobCardDetailsForm.get('machineOutDate').value;
				outDate.setDate((outdatetemp.split('-'))[2])
				outDate.setMonth(((outdatetemp.split('-'))[1]) - 1)
				outDate.setFullYear((outdatetemp.split('-'))[0])

			} else {
				outDate = this.jobCardDetailsForm.get('machineOutDate').value;
			}

			let outTime = this.jobCardDetailsForm.get('timeout').value

			outDate.setHours((outTime.split(':'))[0])
			outDate.setMinutes((outTime.split(':'))[1])

			if (outDate <= newDate) {
				this.toastr.error('Machine out date should be after machine in date');
				this.jobCardDetailsForm.get(controlName).reset();
			}

		}
	}

	resetFieldsOncurrentHoursChanges() {
		this.jobCardDetailsForm.get('serviceCategory').reset()
		this.jobCardDetailsForm.get('serviceType').reset()
		this.jobCardDetailsForm.get('palceofservice').reset()
	}
	getretroFitmentList(event: any) {
		this.jobCardDetailWebService.getRetrofitmentDetails(this.chassisNo).subscribe(result => {
			console.log(result,'result@@@@@@@@@@@@@')
			this.retroList.emit(result)

		})
	}
	changeRetroFitment(event: any) {
		console.log(event.checked)
		if (event.checked === true) {
			this.buttonDisabled = true
			this.retrofitmentCreated.emit(event.checked)
			// this.bookTitleCreated.emit(event.checked);
		} else {
			this.buttonDisabled = false
		}

	}
}
