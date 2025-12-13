import { Component, EventEmitter, OnInit, Output } from '@angular/core'
import { FormGroup, AbstractControl, Validators, FormArray } from '@angular/forms'
import { MatDialog } from '@angular/material'
import { ToastrService } from 'ngx-toastr'
import { ActivatedRoute, Router } from '@angular/router'
import { JobCardPresenter } from './job-card-presenter'
import { CreateJobCardStore } from './create-job-card-store'
import { OperationsUtil, Operation } from '../../../../../utils/operation-util'
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction, } from '../../../../../confirmation-box/confirmation-box.component'
import { MachineInventory, ViewJobCard, FinalSaveJobCard, ServiceJobCard, MechanicName, ServiceRepresentative, CustomerMaster, ServiceType, ChassisNumberJobData, ServiceCategory, ServicetypesList, ServiceActivityProposal, DropDownCategory } from '../../domain/job-card-domain'
import { JobCardPageWebService } from './job-card-page-web.service'
import { ServiceBooking } from '../../domain/job-card-domain'
import { JobCardDetailWebService } from '../job-card-details/job-card-detail-web.service'
import { DateService } from '../../../../../root-service/date.service'
import { IFrameService, IFrameMessageSource } from '../../../../../root-service/iFrame.service'
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { SearchJobCardWebService } from '../search-job-card-page/search-job-card-web.service'

@Component({
	selector: 'app-job-card-create',
	templateUrl: './create-job-card-page.component.html',
	styleUrls: ['./create-job-card-page.component.scss'],
	providers: [JobCardPresenter, CreateJobCardStore, JobCardPageWebService, DateService, JobCardDetailWebService, IFrameService, SearchJobCardWebService],
})
export class JobCardCreateComponent implements OnInit {
	mechanicId: number
	jobcardId: number
	isFreeService: boolean = false;
	jc: string
	isReopen: boolean = false;
	serviceRepresentativeId: number
	jobCardForm: FormGroup
	serviceJobForm: FormGroup
	partRequisitionForm: AbstractControl
	labourChargesForm: AbstractControl
	jobCodeForm: AbstractControl
	isView: boolean
	sendListOfView: ViewJobCard
	isEdit: boolean
	dialogMsg: string
	isDraft: boolean
	isShowCustConcern: boolean
	idForPreSales: number
	isCreate: boolean
	serviceBookingId: number
	isShowUpdateButton: boolean
	idFromView: number
	viewPcr: boolean
	viewGoodwill: boolean
	machineIdPage: number
	serviceTypeDropdownRes: ServicetypesList
	serviceCategoryListRes: DropDownCategory[]
	isShowPcr: boolean
	fileFromView
	fileFromEdit
	jobCardNumberId: string
	isCloseJC: boolean
	isPresales: boolean
	isUpdatePcr: boolean
	isShowPcrButton: boolean
	isShowCancel: boolean
	isCreateInvoice: boolean
	isPrintInvoice: boolean
	isGoowillShow: boolean = false
	isTablesShow: boolean = false
	pcrNo: string
	gwNo: string
	jobCodeNumberInvoice: string
	servicePlaceForInvoice: string
	utilityFlag: boolean = true
	isSubmitDisable: boolean = false;
	machineInDate: string
	forRetrofirMent: boolean = false
	retroList: any
	data: any
	abc: any
	labourData: any
	object: any
	vinId: number
	showPdf: boolean

	partListData: any
	warrantydata:any
	patchRetroCheckbox:boolean=false
	add: string
	dataForCheck: string
	implementListForShowData:any;
	showImplementForm: boolean
	constructor(
		private presenter: JobCardPresenter,
		public dialog: MatDialog,
		private toastr: ToastrService,
		private activateRoute: ActivatedRoute,
		private router: Router,
		private jobCardPageWebService: JobCardPageWebService,
		private dateService: DateService,
		private jobCardDetailWebService: JobCardDetailWebService,
		private iFrameService: IFrameService,
		private searchJobCardWebService: SearchJobCardWebService
	) { }


	ngOnInit() {

		this.presenter.operation = OperationsUtil.operation(this.activateRoute)
		this.jobCardForm = this.presenter.jobCardFormGroup
		this.serviceJobForm = this.presenter.serviceFormGroup
		this.partRequisitionForm = this.presenter.partRequisition
		this.labourChargesForm = this.presenter.labourCharges
		this.jobCodeForm = this.presenter.jobCode
		
		this.viewOrEditOrCreate()
		this.checkRouterParams()
		this.getCheckboxValueChanges()
		this.getPreSalesValueChange()
	}
	getPreSalesValueChange() {
		this.presenter.jobCardBasicInfo.get('checkBox').valueChanges.subscribe(res => {

			if (res == true) {
				this.isTablesShow = true
			} else if (res == false) {
				this.isTablesShow = false
			}
		})
	}
	checkRouterParams() {
		this.activateRoute.queryParamMap.subscribe(param => {
			console.log('param--', param)
			console.log('param--1', atob(param.get('id')))

			if (param.has('id')) {
				if (this.isEdit) {
					this.getJobCardForView(atob(param.get('id')))
					this.jc = atob(param.get('id'));
					this.isShowPcrButton = true;
					this.presenter.disableForView();
				}
				if (this.isView) {
					this.getJobCardForView(atob(param.get('id')))
					if (param.get('hasButton') == 'false') {
						this.isShowPcrButton = false
					}
					else if (param.get('hasButton') == 'true') {
						this.isShowPcrButton = true
					}
					this.presenter.disableForView()
				}
			}
			if (param.get('chassisNumber')) {
				this.dataFromPdi(param.get('chassisNumber'))
				this.presenter.jobCardBasicInfo.get('checkBox').setValue(true)

			}
			if (param.has('jcNofromWpcr')) {
				this.getJobCardForView(param.get('jcNofromWpcr'))
			}
		})
	}

	private dataFromPdi(chassisNumber: string) {
		this.presenter.jobCardBasicInfo.get('chassisNo').disable()
		this.presenter.jobCardBasicInfo.get('chassisNo').patchValue(chassisNumber)
		this.presenter.jobCardBasicInfo.get('currentHours').disable()
		this.presenter.jobCardBasicInfo.get('dateofFailure').setErrors(null)
		this.presenter.jobCardBasicInfo.get('dateofFailure').clearValidators()
		this.presenter.outsideJobCharge.get('suggestionAndAdvice').clearValidators()
		this.presenter.outsideJobCharge.get('suggestionAndAdvice').updateValueAndValidity()

		this.jobCardDetailWebService.chassisDetails(chassisNumber, true, true).subscribe(res => {
			this.idForPreSales = res.result.machineInventoryId

			if (!res.result.result)
				this.presenter.patchDataFromResponse(res.result);
			else {
				this.toastr.warning(res.message);
				this.presenter.jobCardBasicInfo.get('chassisNo').reset();
				this.presenter.jobCardBasicInfo.get('chassisNo').enable();
			}

		})
	}

	private viewOrEditOrCreate() {
		if (this.presenter.operation === Operation.VIEW) {
			this.isView = true
			this.isEdit = false
		} else if (this.presenter.operation === Operation.EDIT) {
			this.isEdit = true
		} else if (this.presenter.operation === Operation.CREATE) {
			this.isCreate = true
		}
	}
	private markFormAsTouchedWithoutPreSales() {
		this.jobCardForm.markAllAsTouched()
		// this.serviceJobForm.markAllAsTouched()
		this.serviceJobForm.controls.jobServiceCard.markAllAsTouched()
		this.serviceJobForm.controls.jobCode.markAllAsTouched()
		this.serviceJobForm.controls.partRequisition.markAllAsTouched()
		this.serviceJobForm.controls.labourCharges.markAllAsTouched()
		this.serviceJobForm.controls.outsideJobCharge.get('finalActionTaken').markAllAsTouched()
		this.serviceJobForm.controls.outsideJobCharge.get('suggestionAndAdvice').markAllAsTouched()

	}
	private markFormAsTouchedWithoutPreSalesForNoSuggestion() {
		this.jobCardForm.markAllAsTouched()
		// this.serviceJobForm.markAllAsTouched()
		this.serviceJobForm.controls.jobServiceCard.markAllAsTouched()
		this.serviceJobForm.controls.jobCode.markAllAsTouched()
		this.serviceJobForm.controls.partRequisition.markAllAsTouched()
		this.serviceJobForm.controls.labourCharges.markAllAsTouched()
		this.serviceJobForm.controls.outsideJobCharge.get('finalActionTaken').markAllAsTouched()
	}

	private getJobCardForView(id: any) {

		this.jobCardPageWebService.viewJobCard(id).subscribe(res => {
             this.dataForCheck=res['serviceCategory'].category
			if (res) {
				this.jobcardId = res.id;
				this.jobCodeNumberInvoice = res.jobCardNo
				if (res.serviceCategory.category == 'Pre Sales') {
					this.isPresales = true
					this.presenter.jobCardBasicInfo.get('checkBox').patchValue(true)
					this.presenter.jobCardBasicInfo.get('preSalesFlagSet').patchValue(true)
					this.presenter.jobCardBasicInfo.get('currentHours').clearValidators()
					this.presenter.jobCardBasicInfo.get('currentHours').updateValueAndValidity()
					this.presenter.jobCardBasicInfo.get('dateofFailure').clearValidators()
					this.presenter.jobCardBasicInfo.get('dateofFailure').updateValueAndValidity()
					this.presenter.jobCardBasicInfo.get('placeofService').disable()
					this.dropDownServiceCategory()
					this.isTablesShow = true

				}
				this.jobCardNumberId = res.jobCardNo

				this.machineInDate = res.taskDate;
				this.checkConditionsForButton(res)
				this.presenter.patchDataFromView(res)
				this.sendListOfView = res
				this.idFromView = res.id;
				this.pcrNo = res.pcrNo;
				this.gwNo = res.gwNo;
				// 
				
				this.warrantydata=res['jobcardRetroMappings']
				// const data=res['jobcardRetroMappings'].forEach(ele=>{
				// 	console.log(ele,'leeeeeeeeeeeeeeeeee')
                //  this.warrantydata=ele
				//  console.log(this.warrantydata,'dddddddddddddddddd')
				// })
                // if(res['jobcardRetroMappings'].forEach(ele=>{

				// }))
				// 
				if (this.isEdit && res.status === 'Open') {
					this.fileFromEdit = res.serviceJobcardPhotos
				} else {
					this.fileFromView = res.serviceJobcardPhotos
				}

				this.jobCardDetailWebService.servicetypesListDropDown(res.machineInventory.vinId, res.totalHour, res.serviceCategory.category, res.id).subscribe(res => {
                      
					this.serviceTypeDropdownRes = res
					
					if(this.serviceTypeDropdownRes[0].serviceType==='Retro Fitment'){
						this.patchRetroCheckbox=true
					}else{
						this.patchRetroCheckbox=false
						// console.log('not reteoo')
					}
				})
				
				if (res.customerMaster) {
					this.presenter.jobCardBasicInfo.get('customerMasterId').patchValue(res.customerMaster.id)
				}
				if (res.mechanic) {
					this.mechanicId = res.mechanic.id
				} if (res.machineInventory) {
					this.machineIdPage = res.machineInventory.vinId
					// console.log(this.machineIdPage,'machiiine Id Page')
					this.idForPreSales = res.machineInventory.vinId
				}
				this.isFreeService = res.serviceCategory.category === 'Maintenance Service';
				if (res.serviceRepresentative) {
					this.serviceRepresentativeId = res.serviceRepresentative.id
				}
				if (res.serviceBooking) {
					this.serviceBookingId = res.serviceBooking.id
				}
				this.servicePlaceForInvoice = this.presenter.jobCardBasicInfo.get('placeofService').value

				if (this.isEdit && this.presenter.jobCardBasicInfo.get('status').value == 'Open') {
					this.presenter.outsideJobCharge.get('finalActionTaken').enable()
					this.presenter.outsideJobCharge.get('suggestionAndAdvice').enable()


					// let machineOutDate = this.dateService.getDateIntoYYYYMMDD(
					// 	this.presenter.jobCardBasicInfo.get('machineOutDate').value,
					// )
					// machineOutDate = machineOutDate +" "+ this.presenter.jobCardBasicInfo.get('timeout').value;

					// let machineInDate = this.dateService.getDateIntoYYYYMMDD(
					// 	this.presenter.jobCardBasicInfo.get('date').value,
					// )
					// machineInDate = machineInDate +" "+ this.presenter.jobCardBasicInfo.get('timein').value;


					// if(this.calculateDiff(machineInDate, machineOutDate)>2){
					// 	this.presenter.jobCardBasicInfo.controls.closeDelayReason.enable();
					// 	this.presenter.jobCardBasicInfo.controls.closeDelayReason.setValidators(Validators.compose([Validators.required]));
					// }
				}

			}
		})
	}
	private dropDownServiceCategory() {
		this.jobCardPageWebService.serviceCategory('true').subscribe(res => {
			this.serviceCategoryListRes = res
		})
	}
	private checkConditionsForButton(data: ViewJobCard) {
		if (!data.draftFlag) {

			this.presenter.jobCardBasicInfo.disable()
			if (this.isEdit) {
				if (this.presenter.jobCardBasicInfo.get('status').value == 'Open') {
					this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').enable()
				}
			}
			// this.checckforJobCardClose(data)
			this.checkJCClose(data)

			this.checkForInvoice(data)

			this.checkForRaisePcr(data)
			//this.checkForPreInvoice(data)
			this.checkForUpdatePcr(data)

			this.checkConditionForGoodwill(data)
		}
	}
	private checkJCClose(data: ViewJobCard) {

		if (data.status == 'Open' && data.sparePartRequisitionItem.length == 0 && data.labourCharge.length == 0 && data.outsideJobCharge.length == 0) {
			this.isCloseJC = true
		} else if (data.status == 'Open' && data.pcrRaiseFlag && data.pcrApprovedFlag) {
			this.isCloseJC = true
		}
	}
	private checkForInvoice(data: ViewJobCard) {
		if (data.status === 'Open') {
			this.isShowCancel = true;
			this.isShowUpdateButton = true;
			if (this.isEdit) {
				this.presenter.jobCardBasicInfo.controls.machineOutDate.enable();
				this.presenter.jobCardBasicInfo.controls.timeout.enable();
				this.presenter.jobCardBasicInfo.controls.closeDelayReason.enable();
			}
			//this.presenter.jobCardBasicInfo.controls.machineOutDate.setValidators(Validators.compose([Validators.required]))
			//this.presenter.jobCardBasicInfo.controls.timeout.setValidators(Validators.compose([Validators.required]))
		}
		if (data.status === 'Close') {
			if (data.invoicedFlag)
				this.isPrintInvoice = true;
			else {
				this.isCreateInvoice = true;
				this.isReopen = data.isLatest;
			}
		}
	}
	private checkForRaisePcr(data: ViewJobCard) {
		if (!data.pcrRaiseFlag) {
			const checkPartExist = data.sparePartRequisitionItem.filter(res => res.category.category == "Warranty" || res.category.category == "FOC")
			const checkLabourExist = data.labourCharge.filter(res => res.category.category == "Warranty" || res.category.category == "FOC")
			const checkOutsideChargeExist = data.outsideJobCharge.filter(res => res.category.category == "Warranty" || res.category.category == "FOC")
			this.isShowPcr = (checkPartExist && checkPartExist.length > 0 ? true : false) || (checkLabourExist && checkLabourExist.length > 0 ? true : false) || (checkOutsideChargeExist && checkOutsideChargeExist.length > 0 ? true : false);
			if (this.isShowPcr) {
				this.isCloseJC = false
			} else if (data.status == 'Open') {
				this.isCloseJC = true
			}
		}
	}
	private checkForUpdatePcr(data: ViewJobCard) {
		if (data.pcrRaiseFlag) {
			this.viewPcr = true;
			const checkPartExist = data.sparePartRequisitionItem.filter(res => (res.category.category == "Warranty" || res.category.category == "FOC") && (res.quantity != res.pcrQuantity))
			const checkLabourExist = data.labourCharge.filter(res => (res.category.category == "Warranty" || res.category.category == "FOC") && (res.amount != res.pcrAmount))
			const checkOutsideChargeExist = data.outsideJobCharge.filter(res => (res.category.category == "Warranty" || res.category.category == "FOC") && (res.amount != res.pcrAmount))
			this.isUpdatePcr = (checkPartExist && checkPartExist.length > 0 ? true : false) || (checkLabourExist && checkLabourExist.length > 0 ? true : false) || (checkOutsideChargeExist && checkOutsideChargeExist.length > 0 ? true : false) || data.allowVideoUpload;
			if (this.isUpdatePcr) {
				this.isCloseJC = false;
				this.isShowPcr = false;
			}
		}
	}

	private checkConditionForGoodwill(data: ViewJobCard) {
		this.presenter.isGoowillShow = data.goodwillRequired;

		if (data.goodwillRequired) {
			this.isUpdatePcr = false;
			if (!data.goodwillRaised) {
				this.isGoowillShow = true
			} else {
				this.viewGoodwill = true;
			}
		}
		if (data.goodwillRequired && !(data.goodwillRaised && data.goodwillApproved)) {
			this.isCloseJC = false;
		}
	}

	getCheckboxValueChanges() {
		this.presenter.jobCardBasicInfo.get('checkBox').valueChanges.subscribe(res => {
			if (res === false) {
				this.isShowCustConcern = false
			} else if (res === true) {
				this.isShowCustConcern = true
			}
		})
	}
	pcrClick() {
		const url = 'warranty/warranty-claim/product-concern-report/jobCard';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { id: this.idFromView, fromJobCartToPcr: 'jcToPcr' } })
		/* fromJobCartToPcr:'jcToPcr'  added by vinay */
	}
	pcrUpdateClick() {
		const url = 'warranty/warranty-claim/product-concern-report/edit';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { pcrNo: btoa(this.pcrNo) } })
	}

	routeToInvoice() {
		const url = 'spares/countersales/salesinvoice/create';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { id: this.idFromView, jobCardNumber: this.jobCodeNumberInvoice, jcToInvoice: this.servicePlaceForInvoice } })
	}

	// exit() {
	// 	this.router.navigate(['../'], { relativeTo: this.activateRoute })
	// }

	saveJobCardForPreSales() {
		
		const allData = this.serviceJobForm.getRawValue()
		const finalSaveJobCard = {} as FinalSaveJobCard
		finalSaveJobCard.serviceJobCard = {} as ServiceJobCard
		if (this.isDraft) {
			finalSaveJobCard.serviceJobCard.draftFlag = true
		} else {
			finalSaveJobCard.serviceJobCard.draftFlag = false
		}
		finalSaveJobCard.serviceJobCard.customerName = this.presenter.jobCardBasicInfo.get('customerName').value;
		finalSaveJobCard.serviceJobCard.address = this.presenter.jobCardBasicInfo.get('customerAddress').value;
		finalSaveJobCard.serviceJobCard.mobileNumber = this.presenter.jobCardBasicInfo.get('customerCellNo').value;
		finalSaveJobCard.serviceJobCard.alternateNumber = this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').value;
		// finalSaveJobCard.serviceJobCard.installationDate= this.presenter.jobCardBasicInfo.get('dateofInstallation').value;
		finalSaveJobCard.serviceJobCard.installationDate = this.dateService.getDateIntoDDMMYYYY(this.presenter.jobCardBasicInfo.get('dateofInstallation').value);
		finalSaveJobCard.serviceJobCard.soldDealer = this.presenter.jobCardBasicInfo.get('solddealer').value
		finalSaveJobCard.serviceJobCard.jobCardOkFlag = false
		finalSaveJobCard.serviceJobCard.machineInventory = {} as MachineInventory
		finalSaveJobCard.serviceJobCard.customerMaster = {} as CustomerMaster
		finalSaveJobCard.serviceJobCard.serviceRepresentative = {} as ServiceRepresentative
		finalSaveJobCard.serviceJobCard.mechanic = {} as MechanicName
		finalSaveJobCard.serviceJobCard.serviceCategory = {} as ServiceCategory
		finalSaveJobCard.serviceJobCard.jobcardRetroMappings = this.object
		// finalSaveJobCard.serviceJobCard.jobcardRetroMappings
		// console.log('final submission************',finalSaveJobCard.serviceJobCard.jobcardRetroMappings)
		finalSaveJobCard.serviceJobCard.serviceActivityProposal = {} as ServiceActivityProposal
		if (this.presenter.jobCardBasicInfo.get('activityNo').value) {
			finalSaveJobCard.serviceJobCard.serviceActivityProposal.id = this.presenter.jobCardBasicInfo.get('activityNo').value.id
		} else {
			finalSaveJobCard.serviceJobCard.serviceActivityProposal = null
		}

		if (this.presenter.jobCardBasicInfo.get('serviceCategory').value.id) {
			finalSaveJobCard.serviceJobCard.serviceCategory.id = this.presenter.jobCardBasicInfo.get('serviceCategory').value.id
		} else {
			finalSaveJobCard.serviceJobCard.serviceCategory = null
		}
		finalSaveJobCard.serviceJobCard.serviceType = {} as ServiceType
		if (this.presenter.jobCardBasicInfo.get('placeofService').value) {
			finalSaveJobCard.serviceJobCard.placeOfService = this.presenter.jobCardBasicInfo.get('placeofService').value.placeOfService ? this.presenter.jobCardBasicInfo.get('placeofService').value.placeOfService :
				this.presenter.jobCardBasicInfo.get('placeofService').value
		} else {
			finalSaveJobCard.serviceJobCard.placeOfService = null
		}
		finalSaveJobCard.serviceJobCard.id = this.idFromView
		if (this.presenter.jobCardBasicInfo.get('serviceType').value && !this.isPresales) {
			finalSaveJobCard.serviceJobCard.serviceType.id = this.presenter.jobCardBasicInfo.get('serviceType').value.id
		} else {
			finalSaveJobCard.serviceJobCard.serviceType = null
		}
		finalSaveJobCard.serviceJobCard.serviceBooking = {} as ServiceBooking
		if (this.serviceBookingId || this.presenter.jobCardBasicInfo.get('bookingNo').value) {
			finalSaveJobCard.serviceJobCard.serviceBooking.id = this.serviceBookingId ? this.serviceBookingId : this.presenter.jobCardBasicInfo.get('bookingNo').value.id
		} else {
			finalSaveJobCard.serviceJobCard.serviceBooking = null
		}
		if (this.presenter.jobCardBasicInfo.get('checkBox').value == false || this.presenter.jobCardBasicInfo.get('checkBox').value == '') {
			finalSaveJobCard.serviceJobCard.currentHour = this.presenter.jobCardBasicInfo.get('currentHours').value
			finalSaveJobCard.serviceJobCard.totalHour = this.presenter.jobCardBasicInfo.get('totalHours').value
			finalSaveJobCard.serviceJobCard.previousHour = this.presenter.jobCardBasicInfo.get('previousHours').value
			finalSaveJobCard.serviceJobCard.meterChangeHour = this.presenter.jobCardBasicInfo.get('meterChangeHour').value

		}
		finalSaveJobCard.serviceJobCard.finalActionTaken = this.presenter.outsideJobCharge.get('finalActionTaken').value
		finalSaveJobCard.serviceJobCard.suggestionToCustomer = this.presenter.outsideJobCharge.get('suggestionAndAdvice').value
		finalSaveJobCard.serviceJobCard.registrationNumber = this.presenter.jobCardBasicInfo.get('registrationNo').value
		finalSaveJobCard.serviceJobCard.estimatedAmount = this.presenter.customerConcern.get('estimatedAmount').value
		if (this.presenter.customerConcern.get('estimatedCompletionDate').value) {
			const convertedDateEstimatedDate = this.dateService.getDateIntoDDMMYYYY(this.presenter.customerConcern.get('estimatedCompletionDate').value)
			finalSaveJobCard.serviceJobCard.estimatedCompletionDate = convertedDateEstimatedDate
		}
		if (this.presenter.jobCardBasicInfo.get('dateofFailure').value) {
			const convertedFailureDate = this.dateService.getDateIntoDDMMYYYY(this.presenter.jobCardBasicInfo.get('dateofFailure').value)
			finalSaveJobCard.serviceJobCard.dateOfFailure = convertedFailureDate
		}

		finalSaveJobCard.fsCouponPage1 = this.presenter.couponOneFile
		finalSaveJobCard.fsCouponPage2 = this.presenter.couponTwoFile
		finalSaveJobCard.hourMeterPhoto = this.presenter.couponThreeFile
		finalSaveJobCard.chassisPhoto = this.presenter.couponFourFile
		finalSaveJobCard.signedJobcard = this.presenter.jobPdf
		finalSaveJobCard.retroAcknowledgementForm = this.presenter.ackPdf

		if (this.idForPreSales || this.presenter.jobCardBasicInfo.get('chassisNo').value.machineInventoryId) {
			finalSaveJobCard.serviceJobCard.machineInventory.vinId = this.idForPreSales ? this.idForPreSales : this.presenter.jobCardBasicInfo.get('chassisNo').value.machineInventoryId
			// console.log(finalSaveJobCard.serviceJobCard.machineInventory.vinId, 'iddd')
			this.vinId = finalSaveJobCard.serviceJobCard.machineInventory.vinId
		} else {
			finalSaveJobCard.serviceJobCard.machineInventory = null
		}
		if (this.serviceRepresentativeId || this.presenter.customerConcern.get('serviceRepresentative').value) {
			finalSaveJobCard.serviceJobCard.serviceRepresentative.id = this.presenter.customerConcern.get('serviceRepresentative').value.id ? this.presenter.customerConcern.get('serviceRepresentative').value.id : this.serviceRepresentativeId
		} else {
			finalSaveJobCard.serviceJobCard.serviceRepresentative = null
		}
		if (this.mechanicId || this.presenter.jobServiceCard.get('mechanicName').value) {
			finalSaveJobCard.serviceJobCard.mechanic.id = this.mechanicId ? this.mechanicId : this.presenter.jobServiceCard.get('mechanicName').value.id
		} else {
			finalSaveJobCard.serviceJobCard.mechanic = null
		}

		if (this.presenter.jobCardBasicInfo.get('customerMasterId').value) {
			finalSaveJobCard.serviceJobCard.customerMaster.id = this.presenter.jobCardBasicInfo.get('customerMasterId').value

		} else {
			finalSaveJobCard.serviceJobCard.customerMaster = null
		}
		finalSaveJobCard.serviceJobCard.customerConcern = this.presenter.customerConcern.get('customerConcernEditableTable').value
		finalSaveJobCard.serviceJobCard.mechanicObservation = this.presenter.jobServiceCard.get('serviceJobCardEditable').value

		if (allData.partRequisition.partRequisitionControl.length > 0 || this.presenter.demo.length > 0) {
			finalSaveJobCard.serviceJobCard.sparePartRequisitionItem = this.getSpareParts()
		}
		else {
			finalSaveJobCard.serviceJobCard.sparePartRequisitionItem = this.getSpareParts()
		}
		if (allData.labourCharges.labourChargesControl.length > 0 || this.presenter.deletedFrsRecord.length > 0) {

			finalSaveJobCard.serviceJobCard.labourCharge = this.getLabourCharges()
		} else {
			finalSaveJobCard.serviceJobCard.labourCharge = this.getLabourCharges()
		}
		if (allData.jobCode.jobCodeControl.length > 0 || this.presenter.deletedJobCodeRecord.length > 0) {
			finalSaveJobCard.serviceJobCard.outsideJobCharge = this.getOutsideJobCode()
		}
		const convertedDate = this.dateService.getDateIntoDDMMYYYY(
			this.presenter.jobCardBasicInfo.get('date').value,
		)
		finalSaveJobCard.serviceJobCard.taskDate = convertedDate
		if (this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').value) {
			finalSaveJobCard.serviceJobCard.alternateNumber = this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').value
		}
		finalSaveJobCard.serviceJobCard.taskTime = this.presenter.jobCardBasicInfo.get('timein').value



		this.jobCardPageWebService.saveJobCardPreSales(finalSaveJobCard).subscribe(res => {
			if (res) {
				const displayMessage = res['message']
				this.toastr.success(displayMessage, 'Success')
				if (this.isView) {
					this.router.navigate(['../job-card'], {
						relativeTo: this.activateRoute
					})
				} else this.router.navigate(['../'], { relativeTo: this.activateRoute })
			} else {
				this.isSubmitDisable = false;
				this.toastr.error('Error genearted while saving', 'Transaction failed');
			}
		},
			err => {
				this.isSubmitDisable = false;
				this.toastr.error('Error genearted while saving', 'Transaction failed');
			}
		)
	}


	private getLabourCharges() {
		const allLabourChargesFromTable = this.serviceJobForm.getRawValue()
		const arryForIterationLabourCharges = allLabourChargesFromTable.labourCharges.labourChargesControl
		const temp = [].concat(...this.presenter.deletedFrsRecord)

		let labourDeletedItems = []
		if (temp.length > 0) {
			temp.forEach(el => {
				labourDeletedItems.push({
					serviceMtFrsCode: {
						id: el.frsCode.id
					},
					hour: el.hour,
					amount: parseInt(el.amount),
					labourChargeId: el.labourChargeId,
					category: el.category,
					isSelected: el.isSelected,

				})

				delete el.uuid
			})
		}
		let labourChargeDetailsSend = []
		const allPartsTogether = []
		arryForIterationLabourCharges.forEach(element => {

			allPartsTogether.push({
				serviceMtFrsCode: {
					id: element.frsCode.id
				},
				hour: element.hour,
				amount: parseInt(element.amount),
				labourChargeId: element.labourChargeId,
				category: element.category,
				isSelected: element.isSelected,

			})

			delete element.uuid
		})
		if(this.isCreate && this.forRetrofirMent){
		this.labourData.forEach(ele => {

			allPartsTogether.push({
				serviceMtFrsCode: {
					id: ele.id ? ele.id : null,
				},
				hour: ele.hour ? ele.hour : null,
				amount: ele.amount ? ele.amount : null,
				labourChargeId: ele.labourChargeId ? ele.labourChargeId : null,
				category: { 'category': ele.category, 'id': ele.categoryId },

			})

		}

		)
	}
		labourChargeDetailsSend = [...allPartsTogether, ...labourDeletedItems]

		return labourChargeDetailsSend ? labourChargeDetailsSend : null
	}
	private getOutsideJobCode() {
		const allJobCodeChargesFromTable = this.serviceJobForm.getRawValue()

		const arryForIterationJobCodeCharges = allJobCodeChargesFromTable.jobCode.jobCodeControl
		const temp = [].concat(...this.presenter.deletedJobCodeRecord)

		let deletedItems = []
		if (temp.length > 0) {
			temp.forEach(el => {
				deletedItems.push({
					serviceMtJobcode: {
						id: el.jobCode.id
					},
					amount: parseInt(el.amount),
					jobcodeId: el.jobcodeId,
					category: el.category,
					isSelected: el.isSelected,

				})
				delete el.uuid
			})
		}

		let jobCodeChargeDetailsSend = []
		const allPartsTogether = []
		arryForIterationJobCodeCharges.forEach(element => {
			allPartsTogether.push({
				serviceMtJobcode: {
					id: element.jobCode.id
				},
				amount: parseInt(element.amount),
				jobcodeId: element.jobcodeId,
				category: element.category,
				isSelected: element.isSelected,
			})
			delete element.uuid
		})
		jobCodeChargeDetailsSend = [...allPartsTogether, ...deletedItems]

		return jobCodeChargeDetailsSend ? jobCodeChargeDetailsSend : null
	}
	private getSpareParts() {
		const allDataFromTable = this.serviceJobForm.getRawValue()

		const arryForIteration = allDataFromTable.partRequisition.partRequisitionControl

		const temp = [].concat(...this.presenter.demo)

		let deletedParts = []
		if (temp.length > 0) {
			temp.forEach(el => {
				deletedParts.push({
					sparePartMaster: {
						id: el.id ? el.id : el.partNumber.id,
						itemNo: el.partNumber.value ? el.partNumber.value : el.partNumber,
					},
					reqQuantity: parseInt(el.quantity),
					id: el.sparePartId,
					isSelected: el.isSelected,
					priceUnit: el.mrp,
					amount: el.amount,
					category: el.category,
					approveStatus: el.approved,
				})
				delete el.uuid,
					delete el.qtyChangeFlag,
					delete el.cmpQty
			})
		}

		let sparePartsDetailsSend = []
		const allPartsTogether = []
		arryForIteration.forEach(element => {

			allPartsTogether.push({
				sparePartMaster: {
					id: element.id ? element.id : element.partNumber.id,
					itemNo: element.partNumber.value ? element.partNumber.value : element.partNumber,
				},
				reqQuantity: parseInt(element.quantity),
				id: element.sparePartId,
				isSelected: element.isSelected,
				priceUnit: element.mrp,
				amount: element.amount,
				category: element.category,
				approveStatus: element.approved,
			})
			delete element.uuid
			delete element.qtyChangeFlag
			delete element.cmpQty
		})
		if(this.isCreate && this.forRetrofirMent){
		this.partListData.forEach(ele => {

			allPartsTogether.push({
				sparePartMaster: {
					id: ele.id ? ele.id : ele.partNumber.id,
					itemNo: ele.itemNo ? ele.itemNo : ele.partNumber,
				},
				reqQuantity: ele.quantity,
				id: null,
				priceUnit: ele.mrp,
				amount: ele.amount,
				category: { 'category': ele.category, 'id': ele.categoryId },
			})
	

		}


		)
	}
		sparePartsDetailsSend = [...allPartsTogether, ...deletedParts]

		return sparePartsDetailsSend ? sparePartsDetailsSend : null
	}

	validateSaveSubmitJobCard(isSave?: boolean) {
		// console.log('serviceJobForm', this.serviceJobForm)
		this.isDraft = isSave
		this.dialogMsg = isSave ? 'save' : 'submit'

		if (isSave) {
			this.checkForWithoutSave()
		} else {
			this.checkForWithoutSave()
		}
	}
	private checkForWithoutSave() {
		if (this.jobCardForm.get('jobCardBasicInfo').get('checkBox').value == false) {
			/* below if condition is  added by vinay to enable  hourMeter and chassisNumber in  case of Free Service to set validation effrct */
			if (this.presenter.jobCardBasicInfo.get('serviceCategory').value && this.presenter.jobCardBasicInfo.get('serviceCategory').value.category === 'Maintenance Service') {
				this.presenter.outsideJobCharge.get('hourMeter').enable()
				this.presenter.outsideJobCharge.get('chassisNumber').enable()
				this.serviceJobForm.controls.outsideJobCharge.markAllAsTouched()
			}
			// if (this.jobCardForm.get('jobCardBasicInfo').status === 'VALID' && this.serviceJobForm.get('outsideJobCharge').get('finalActionTaken').valid && this.serviceJobForm.get('outsideJobCharge').get('suggestionAndAdvice').valid) {
			if (this.jobCardForm.get('jobCardBasicInfo').status === 'VALID' && (this.serviceJobForm.get('outsideJobCharge').status === 'VALID' || this.serviceJobForm.get('outsideJobCharge').status === 'DISABLED')) {
				this.openConfirmDialog()
			} else {
				this.markFormAsTouchedWithoutPreSales()
				this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
			}
		} else {
			this.presenter.outsideJobCharge.get('suggestionAndAdvice').clearValidators();
			this.presenter.outsideJobCharge.get('suggestionAndAdvice').updateValueAndValidity();
			this.jobCardForm.get('jobCardBasicInfo').get('serviceType').setErrors(null)			// added by vinay to remove error when serviceType is not required
			if (this.jobCardForm.get('jobCardBasicInfo').status === 'VALID' && this.serviceJobForm.get('outsideJobCharge').get('finalActionTaken').valid) {
				this.openConfirmDialog()
			} else {
				this.markFormAsTouchedWithoutPreSalesForNoSuggestion()
				this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
			}
		}
	}


	private openConfirmDialog(): void | any {
		let message = `Do you want to ${this.dialogMsg} Job Card?`
		if (this.isView) {
			message = `Do you want to ${this.dialogMsg} Job Card?`
		}
		const confirmationData = this.setConfirmationModalData(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Confirm') {
				this.isSubmitDisable = true;
				this.saveJobCardForPreSales()
			}
		})
	}
	private setConfirmationModalDataClose(message: string) {
		const confirmationData = {} as ConfirmDialogData
		confirmationData.buttonAction = [] as Array<ButtonAction>
		confirmationData.title = 'Confirmation'
		confirmationData.message = message
		confirmationData.buttonName = ['Cancel', 'Confirm']
		confirmationData.showHourMeter = true
		confirmationData.hourMeterVal = (this.jobCardForm.get('jobCardBasicInfo') as FormGroup).controls.totalHours.value
		return confirmationData
	}
	private setConfirmationModalData(message: string, isWarrning?: boolean) {
		const confirmationData = {} as ConfirmDialogData
		confirmationData.buttonAction = [] as Array<ButtonAction>
		confirmationData.title = 'Confirmation'
		confirmationData.message = message
		confirmationData.isWarrning = isWarrning
		confirmationData.buttonName = ['Cancel', 'Confirm']
		if (isWarrning) {
			confirmationData.remarkConfig = {
				source: Source.REQUIRED
			}
		}
		return confirmationData
	}
	calculateDiff(machineindate, machineoutdate) {
		let machineindate_ = new Date(machineindate);

		let machineoutdate_ = new Date(machineoutdate);


		let days = Math.floor(((((machineoutdate_.getTime() - machineindate_.getTime()) / 1000) / 60) / 60) / 24);

		return days;
	}
	closeJobCard() {

		if (this.presenter.jobCardBasicInfo.controls.machineOutDate.value &&
			this.presenter.jobCardBasicInfo.controls.timeout.value) {

		} else {
			this.toastr.error('Please Enter Machine Out Date and Time');
			this.presenter.jobCardBasicInfo.controls.machineOutDate.markAsTouched();
			this.presenter.jobCardBasicInfo.controls.timeout.markAsTouched();
			return false;
		}
		// code commit for testing 
		let machineOutDate = this.dateService.getDateIntoYYYYMMDD(
			this.presenter.jobCardBasicInfo.get('machineOutDate').value,
		)
		machineOutDate = machineOutDate + " " + this.presenter.jobCardBasicInfo.get('timeout').value;


		let machineInDate = this.dateService.getDateIntoYYYYMMDD(
			this.presenter.jobCardBasicInfo.get('date').value,
		)
		machineInDate = machineInDate + " " + this.presenter.jobCardBasicInfo.get('timein').value;

		if (this.calculateDiff(machineInDate, machineOutDate) >= 2) {
			if (this.presenter.jobCardBasicInfo.controls.closeDelayReason.value == undefined || this.presenter.jobCardBasicInfo.controls.closeDelayReason.value == null) {
				this.toastr.error('Please Select Reason of Delay');
				this.presenter.jobCardBasicInfo.controls.closeDelayReason.markAsTouched();
				return false;
			}

		}
		// if(this.calculateDiff(machineInDate, machineOutDate)>2 ){
		// 	console.log(this.calculateDiff(machineInDate, machineOutDate),'calculate')
		// 	if(this.presenter.jobCardBasicInfo.controls.closeDelayReason.value==undefined || this.presenter.jobCardBasicInfo.controls.closeDelayReason.value==null){
		// 		this.toastr.error('Please Select Reason of Delay');
		// 		this.presenter.jobCardBasicInfo.controls.closeDelayReason.markAsTouched();
		// 		return false;
		// 	}
		// }

		//	end

		const items = this.partRequisitionForm.get('partRequisitionControl') as FormArray;
		let flag: boolean = true;
		items && items.controls && items.controls.forEach(item => {
			//if (item.get('category').value.category==='Warranty' || item.get('category').value.category==='FOC') {
			if (item.get('utilizedQuantity').value == null || item.get('utilizedQuantity').value < item.get('quantity').value) {
				this.utilityFlag = false
				return false;
			}
			//} 
		});

		if (this.utilityFlag) {
			this.closeJobCardfinal()
		} else {
			this.openPartUtilizedConfirmDialog();
		}
	}

	closeJobCardfinal(remark?: string) {

		const items = this.partRequisitionForm.get('partRequisitionControl') as FormArray;
		let flag: boolean = true;
		items && items.controls && items.controls.forEach(item => {
			if (item.get('category').value.category == 'FOC' && item.get('utilizedQuantity').value != null && parseInt(item.get('utilizedQuantity').value) > parseInt(item.get('approveQuantity').value)) {
				flag = false;
				this.toastr.error('Job Card can not be close as Part Utilized Qty is more than Approved Qty');
				return false;
			}
		});

		if (flag) {
			this.serviceJobForm.get('outsideJobCharge').get('suggestionAndAdvice').clearValidators()
			this.serviceJobForm.get('outsideJobCharge').get('suggestionAndAdvice').updateValueAndValidity()

			// if (this.serviceJobForm.get('outsideJobCharge').status === 'VALID') {
			if (this.serviceJobForm.get('outsideJobCharge').status === 'VALID' || this.serviceJobForm.get('outsideJobCharge').status === 'DISABLED') {
				if (this.presenter.outsideJobCharge.get('finalActionTaken').value) {

				} else {
					this.toastr.error(`Enter Final Action Taken`, 'Report mandatory fields')
					return false;
				}
				if (this.presenter.outsideJobCharge.get('suggestionAndAdvice').value) {

				} else if (!this.isTablesShow) {
					this.toastr.error(`Enter Suggestion And Advice`, 'Report mandatory fields')
					return false;
				}

				if (this.presenter.jobCardBasicInfo.get('serviceCategory').value.category == 'Maintenance Service') {
					if (this.serviceJobForm.get('outsideJobCharge').get('couponOne').value == null) {
						this.toastr.error(`Upload Free Service Coupon One`, 'Report mandatory fields')
						this.presenter.outsideJobCharge.get('couponOne').enable()
						return false;
					}
					if (this.serviceJobForm.get('outsideJobCharge').get('couponTwo').value == null) {
						this.toastr.error(`Upload Free Service Coupon Two`, 'Report mandatory fields')
						this.presenter.outsideJobCharge.get('couponTwo').enable()
						return false;
					}
				}
				if (this.serviceJobForm.get('outsideJobCharge').get('hourMeter').value == null) {
					this.toastr.error(`Upload Hour meter photo`, 'Report mandatory fields')
					this.presenter.outsideJobCharge.get('hourMeter').enable()

					return false;
				}
				if (this.serviceJobForm.get('outsideJobCharge').get('chassisNumber').value == null) {
					this.toastr.error(`Upload Chassis number photo`, 'Report mandatory fields')
					this.presenter.outsideJobCharge.get('chassisNumber').enable()
					return false;
				}
				this.openConfirmDialogCloseJobCard(remark)

			} else {
				this.markFormAsTouchForCloseJc()
				this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')

			}
		}
	}

	markFormAsTouchForCloseJc() {
		this.jobCardForm.markAllAsTouched()
		this.serviceJobForm.markAllAsTouched()
	}
	private openConfirmDialogCloseJobCard(remark: string): void | any {
		let message = `Do you want to Close Job Card?`

		const confirmationData = this.setConfirmationModalDataClose(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Confirm') {
				this.isSubmitDisable = true;
				this.closeJobCardValue(remark)
			}
		})
	}
	private openPartUtilizedConfirmDialog(): void | any {
		let message = "Parts are pending for issue. Do you want to close the Job Card?"

		const confirmationData = this.setConfirmationModalData(message, true)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe((result: DialogResult) => {
			if (result.button === 'Confirm') {
				this.closeJobCardfinal(result.remarkText)
			}
		})
	}
	private closeJobCardValue(remark: string) {
		let machineOutDate = this.dateService.getDateIntoYYYYMMDD(
			this.presenter.jobCardBasicInfo.get('machineOutDate').value,
		)
		machineOutDate = machineOutDate + " " + this.presenter.jobCardBasicInfo.get('timeout').value;

		this.jobCardPageWebService.checkCondition(this.idFromView).subscribe(res => {

			// this.toastr.success(`${res['message']}`)
			// this.router.navigate(['../'], { relativeTo: this.activateRoute });
			this.updateJobCard(machineOutDate, remark);
		})
	}

	updateJobCard(machineOutDate?: string, remark?: string) {
		// debugger
		const allData = this.serviceJobForm.getRawValue()
		const updateJson = {} as FinalSaveJobCard
		updateJson.serviceJobCard = {} as ServiceJobCard
		if (this.isDraft) {
			updateJson.serviceJobCard.draftFlag = true
		} else if (!this.isDraft) {
			updateJson.serviceJobCard.draftFlag = false
		}
		if (machineOutDate) {
			updateJson.serviceJobCard.outDateTime = machineOutDate
		}
		if (remark) {
			updateJson.serviceJobCard.closeRemark = remark;
		}
		updateJson.serviceJobCard.serviceRepresentative = {} as ServiceRepresentative
		updateJson.serviceJobCard.mechanic = {} as MechanicName
		updateJson.serviceJobCard.id = this.idFromView
		if (this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').value) {
			updateJson.serviceJobCard.alternateNumber = this.presenter.jobCardBasicInfo.get('alternateCustomerCellNo').value
		}
		if (this.presenter.jobCardBasicInfo.get('checkBox').value == false || this.presenter.jobCardBasicInfo.get('checkBox').value == '') {
			updateJson.serviceJobCard.currentHour = this.presenter.jobCardBasicInfo.get('currentHours').value
			// updateJson.serviceJobCard.totalHour = this.presenter.jobCardBasicInfo.get('totalHours').value
			updateJson.serviceJobCard.previousHour = this.presenter.jobCardBasicInfo.get('previousHours').value
		}
		updateJson.serviceJobCard.finalActionTaken = this.presenter.outsideJobCharge.get('finalActionTaken').value
		updateJson.serviceJobCard.suggestionToCustomer = this.presenter.outsideJobCharge.get('suggestionAndAdvice').value
		updateJson.serviceJobCard.estimatedAmount = this.presenter.customerConcern.get('estimatedAmount').value
		if (this.presenter.customerConcern.get('estimatedCompletionDate').value) {
			const convertedDateEstimatedDate = this.dateService.getDateIntoDDMMYYYY(this.presenter.customerConcern.get('estimatedCompletionDate').value)
			updateJson.serviceJobCard.estimatedCompletionDate = convertedDateEstimatedDate
		}
		// if (this.serviceRepresentativeId || this.presenter.customerConcern.get('serviceRepresentative').value) {
		// 	updateJson.serviceJobCard.serviceRepresentative.id = this.presenter.customerConcern.get('serviceRepresentative').value.id ? this.presenter.customerConcern.get('serviceRepresentative').value.id : this.serviceRepresentativeId
		// } else {
		// 	updateJson.serviceJobCard.serviceRepresentative = null
		// }
		// if (this.mechanicId || this.presenter.jobServiceCard.get('mechanicName').value) {
		// 	updateJson.serviceJobCard.mechanic.id = this.mechanicId ? this.mechanicId : this.presenter.jobServiceCard.get('mechanicName').value.id
		// } else {
		// 	updateJson.serviceJobCard.mechanic = null
		// }
		updateJson.serviceJobCard.customerConcern = this.presenter.customerConcern.get('customerConcernEditableTable').value
		updateJson.serviceJobCard.mechanicObservation = this.presenter.jobServiceCard.get('serviceJobCardEditable').value
		if (allData.partRequisition.partRequisitionControl.length > 0 || this.presenter.demo.length > 0) {
			updateJson.serviceJobCard.sparePartRequisitionItem = this.getSpareParts()
		}else{
			updateJson.serviceJobCard.sparePartRequisitionItem = this.getSpareParts()
		}
		if (allData.labourCharges.labourChargesControl.length > 0 || this.presenter.deletedFrsRecord.length > 0) {
			updateJson.serviceJobCard.labourCharge = this.getLabourCharges()
		}else{
			updateJson.serviceJobCard.labourCharge = this.getLabourCharges()
		}
		if (allData.jobCode.jobCodeControl.length > 0 || this.presenter.deletedJobCodeRecord.length > 0) {
			updateJson.serviceJobCard.outsideJobCharge = this.getOutsideJobCode()
		}
		updateJson.serviceJobCard.closeDelayReason = this.presenter.jobCardBasicInfo.get('closeDelayReason').value;
		updateJson.fsCouponPage1 = this.presenter.couponOneFile
		updateJson.fsCouponPage2 = this.presenter.couponTwoFile
		updateJson.hourMeterPhoto = this.presenter.couponThreeFile
		updateJson.chassisPhoto = this.presenter.couponFourFile
		updateJson.signedJobcard = this.presenter.jobPdf
		updateJson.retroAcknowledgementForm = this.presenter.ackPdf
		this.jobCardPageWebService.updateJobCardPreSales(updateJson).subscribe(
			res => {
				if (res) {
					const displayMessage = res['message']
					this.toastr.success(displayMessage, 'Success')
					this.router.navigate(['../'], { relativeTo: this.activateRoute })
					// this.router.navigate(['../view'], {
					// 	relativeTo: this.activateRoute, queryParams: { id: this.jobCardNumberId, hasButton: true },
					// })
					// this.router.navigate(['../edit'], {
					// 	relativeTo: this.activateRoute,
					// 	queryParams: { id: this.jc}
					// })
				} else {
					this.isSubmitDisable = false;
					this.toastr.error('Error generated while updating', 'Transaction failed')
				}
			},
			err => {
				this.isSubmitDisable = false;
				this.toastr.error('Error generated while updating', 'Transaction failed')
			},
		)
	}
	validateUpdateJobCard(isSave?: boolean) {
		this.isDraft = isSave
		console.log('outsideJobCharge--', this.serviceJobForm.get('outsideJobCharge'));

		if ((this.jobCardForm.get('jobCardBasicInfo').status === 'VALID' || this.jobCardForm.get('jobCardBasicInfo').status === 'DISABLED')
			&& (this.serviceJobForm.get('outsideJobCharge').status === 'VALID' || this.serviceJobForm.get('outsideJobCharge').status === 'DISABLED')
			&& (this.serviceJobForm.get('jobCode').status === 'VALID' || this.serviceJobForm.get('jobCode').status === 'DISABLED')
			&& (this.serviceJobForm.get('partRequisition').status === 'VALID' || this.serviceJobForm.get('partRequisition').status === 'DISABLED')
			&& (this.serviceJobForm.get('labourCharges').status === 'VALID' || this.serviceJobForm.get('labourCharges').status === 'DISABLED')) {
			this.openConfirmDialogUpdate()
		} else {
			this.markFormAsTouchedWithoutPreSales()
			this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
		}
	}
	private openConfirmDialogUpdate(): void | any {
		let message = `Do you want to Update Job Card?`

		const confirmationData = this.setConfirmationModalData(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Confirm') {
				this.isSubmitDisable = true;
				this.updateJobCard()
			}
		})
	}

	private setConfirmationModalDataCancel(message: string) {
		const confirmationData = {} as ConfirmDialogData;
		confirmationData.buttonAction = [] as Array<ButtonAction>;
		confirmationData.title = 'Confirmation';
		confirmationData.message = message;
		confirmationData.buttonName = ['Cancel', 'Confirm'];

		confirmationData.remarkConfig = {
			source: Source.APPROVE
		}

		return confirmationData;
	}

	private openConfirmDialogCancel(): void | any {
		let message = `Are you sure you want to Cancel?`
		const confirmationData = this.setConfirmationModalDataCancel(message);
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData
		});
		dialogRef.afterClosed().subscribe((result: DialogResult) => {
			if (result.button === 'Confirm') {
				this.isSubmitDisable = true;
				this.jobCardPageWebService.cancelJobCard(this.jobcardId + "", result.remarkText).subscribe(
					res => {
						if (res) {
							const displayMessage = res['message']
							this.toastr.success(displayMessage, 'Success')
							this.router.navigate(['../'], { relativeTo: this.activateRoute })
						} else {
							this.isSubmitDisable = false;
							this.toastr.error('Error generated while cancelling', 'Transaction failed')
						}
					},
					err => {
						this.isSubmitDisable = false;
						this.toastr.error('Error generated while cancelling', 'Transaction failed')
					},
				)
			}
		})
	}

	jobcardCancelClick() {
		const items = this.partRequisitionForm.get('partRequisitionControl') as FormArray;
		let flag: boolean = true;
		items.controls.forEach(item => {
			if (item.get('utilizedQuantity').value != null && parseInt(item.get('utilizedQuantity').value) > 0) {
				flag = false;
				this.toastr.error('Job Card can not be cancel as Part have been already issued');
				return false;
			}
		});
		if (flag) {
			this.openConfirmDialogCancel();
		}
	}

	printPdf(printStatus: string, type: string) {
		this.jobCardDetailWebService.print(this.jobCardNumberId, printStatus, type).subscribe((resp: HttpResponse<Blob>) => {
			if (resp) {
				let headerContentDispostion = resp.headers.get('content-disposition');
				let fileName = headerContentDispostion.split("=")[1].trim();
				const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
				saveAs(file);
			}
		})
	}

	createGoodwill() {
		const url = 'warranty/warranty-claim/goodwill/create';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { pcrNo: btoa(this.pcrNo) } })

	}

	viewPcrClick() {
		const url = 'warranty/warranty-claim/product-concern-report/view';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { pcrNo: btoa(this.pcrNo), name: 'pcrNo', fromJobCartToPcr: 'jcToPcr' } })
	}
	viewGoodwillClick() {
		const url = 'warranty/warranty-claim/goodwill/view';
		this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { goodwillNo: btoa(this.gwNo), name: 'goodwillNo' } })
	}
	reopenJobcard() {
		this.openConfirmDialogReopen(this.jobcardId);
	}
	private openConfirmDialogReopen(jobcardId): void | any {
		let message = `Do you want to Re-Open Job Card?`
		const confirmationData = this.setConfirmationModalData(message)
		const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
			width: '500px',
			panelClass: 'confirmation_modal',
			data: confirmationData,
		})
		dialogRef.afterClosed().subscribe(result => {
			if (result === 'Confirm') {
				this.searchJobCardWebService.reopenJobCard(jobcardId).subscribe(res => {
					if (res['message'] === 'Job Card reopen successfully') {
						this.toastr.success(res['message']);
						this.router.navigate(['../'], { relativeTo: this.activateRoute })
					} else
						this.toastr.error(res['message']);
				});
			}
		})
	}
	retrofitmentAdd(msg) {
		
		if (msg === true) {
			this.forRetrofirMent = true
		} else {
			this.forRetrofirMent = false
		}

	}
	getRetroData(list) {
		this.retroList = list;
		this.data = this.retroList;
	}

	retroDetails(list) {

		this.partListData = list
		this.getSpareParts();
	}
	retroLabourDetails(data) {
		this.labourData = data
		if (this.labourData[0].flag === 'retro') {
			this.showPdf = true
		} else {
			this.showPdf = false
		}

	}
	warrantyObjects(object) {
		this.object = object
	}
	getImplementData(implementList){
	  this.implementListForShowData=implementList
	}

	showHideImplementForm(hideandshowForm){
      if(hideandshowForm==true){
		this.showImplementForm=true;
	  }else{
		// console.log('elses')
		this.showImplementForm=false;
	  }
	}
}
