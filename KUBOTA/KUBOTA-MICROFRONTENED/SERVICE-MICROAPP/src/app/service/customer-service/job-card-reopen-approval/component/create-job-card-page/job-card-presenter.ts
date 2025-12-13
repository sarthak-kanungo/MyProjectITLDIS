import { Injectable } from '@angular/core'
import { FormGroup, FormArray } from '@angular/forms'
import { CreateJobCardStore } from './create-job-card-store'
import {
	ViewJobCard,
	BookingNoJobData,
	SparePartRequisitionItem,
	LabourChargeView,
	ChassisNumberJobData,
	OutsideJobChargeView,
	ServiceActivityProposalView,
} from '../../domain/job-card-domain'
import { MatAutocompleteSelectedEvent } from '@angular/material'
import { Operation } from '../../../../../utils/operation-util'
import { LabourChargesItem } from '../../domain/labour-charges-domain'
import { JobCodeTableData } from '../../domain/job-code-table'

@Injectable()
export class JobCardPresenter {
	couponOne = []
	coupontwo = []
	hourMeters = []
	chassisList = []
	couponOneFile: File[]
	couponTwoFile: File[]
	couponThreeFile: File[]
	couponFourFile: File[]
	readonly jobCardFormGroup: FormGroup
	readonly serviceFormGroup: FormGroup
	demo = []
	deletedJobCodeRecord = []
	deletedFrsRecord = []
	private _operation: string

	set operation(type: string) {
		this._operation = type
	}
	get operation() {
		return this._operation
	}
	constructor(private createJobCardStore: CreateJobCardStore) {
		this.jobCardFormGroup = this.createJobCardStore.jobCardForm
		this.serviceFormGroup = this.createJobCardStore.serviceJobForm
	}
	get jobCardBasicInfo() {
		return this.jobCardFormGroup.get('jobCardBasicInfo') as FormGroup
	}
	get customerConcern() {
		return this.jobCardFormGroup.get('customerConcern') as FormGroup
	}
	get jobServiceCard() {
		return this.serviceFormGroup.get('jobServiceCard') as FormGroup
	}
	get outsideJobCharge() {
		return this.serviceFormGroup.get('outsideJobCharge') as FormGroup
	}
	get partRequisition() {
		return this.serviceFormGroup.get('partRequisition') as FormGroup
	}
	get labourCharges() {
		return this.serviceFormGroup.get('labourCharges') as FormGroup
	}
	get jobCode() {
		return this.serviceFormGroup.get('jobCode') as FormGroup
	}

	addRowView(list: SparePartRequisitionItem) {
		const machineDetails = this.partRequisition.get(
			'partRequisitionControl',
		) as FormArray
		const addedNewForm = this.createMachineDetailsTableRow(list)
		addedNewForm.get('quantity').disable()
		addedNewForm.get('isSelected').disable()
		addedNewForm.get('category').disable()
		addedNewForm.get('partNumber').disable()
		addedNewForm.get('itemDescription').disable()
		addedNewForm.get('utilizedQuantity').disable()
		addedNewForm.get('mrp').disable()
		addedNewForm.get('amount').disable()
		addedNewForm.get('approveQuantity').disable()
		addedNewForm.get('approveStatus').disable()
		machineDetails.push(addedNewForm)
	}
	addRowJobCodeView(list: JobCodeTableData) {
		const jobCodeDetails = this.jobCode.get('jobCodeControl') as FormArray
		const addedNewJobCode = this.createRowForJobCodeCharges(list)
		addedNewJobCode.get('isSelected').disable()
		addedNewJobCode.get('jobCode').disable()
		addedNewJobCode.get('description').disable()
		addedNewJobCode.get('amount').disable()
		addedNewJobCode.get('approveAmount').disable()
		jobCodeDetails.push(addedNewJobCode)
	}
	addRowFrsView(list: LabourChargesItem) {
		const labourDetailsData = this.labourCharges.get('labourChargesControl') as FormArray
		const addedNewLabourCharges = this.createRowForLabourCharges(list)
		addedNewLabourCharges.get('isSelected').disable()
		addedNewLabourCharges.get('frsCode').disable()
		addedNewLabourCharges.get('description').disable()
		addedNewLabourCharges.get('hour').disable()
		addedNewLabourCharges.get('amount').disable()
		addedNewLabourCharges.get('approveAmount').disable()
		labourDetailsData.push(addedNewLabourCharges)
	}
	addRowJobCode(list: JobCodeTableData) {
		const jobCodeDetails = this.jobCode.get('jobCodeControl') as FormArray
		if(jobCodeDetails.valid){
		    let addedNewJobCode = this.createRowForJobCodeCharges(list)
		    if(addedNewJobCode.get('jobCode').value!=null){
        	    addedNewJobCode.get('category').disable()
                addedNewJobCode.get('jobCode').disable()
		    }
            jobCodeDetails.push(addedNewJobCode);
		}
	}
	createRowForJobCodeCharges(data: JobCodeTableData) {
		return this.createJobCardStore.createJobCodeRow(data)
	}
	deleteRowJobCode() {
		const jobTableDetails = this.jobCode.get('jobCodeControl') as FormArray
		const nonSelected = jobTableDetails.controls.filter(
			machinery => !machinery.value.isSelected
		)
		const selected = jobTableDetails.controls.filter(machinery => machinery.value.isSelected)
		
		this.deletedJobCodeRecord.push(jobTableDetails.controls.filter(machinery => machinery.value.isSelected && machinery.value.jobcodeId).map(ele => ele.value))
		
		jobTableDetails.clear()
		nonSelected.forEach(el => jobTableDetails.push(el))
		const dataTable = this.jobCode.get('jobCodeControl') as FormArray
		selected.forEach(element => {
			const deleteData = dataTable.controls.filter(
				ele => ele.value.uuid === element.value.uuid
			)
			deleteData.forEach(el => dataTable.removeAt(el.value))
		})
	}
	addRowFrs(list: LabourChargesItem) {
		const labourDetails = this.labourCharges.get(
			'labourChargesControl',
		) as FormArray
		if(labourDetails.valid){
		    let addedNewJobCode = this.createRowForLabourCharges(list)
		    if(addedNewJobCode.get('frsCode').value!=null){
    		    addedNewJobCode.get('category').disable()
                addedNewJobCode.get('frsCode').disable()
		    }
		    labourDetails.push(addedNewJobCode);
		}
	}
	createRowForLabourCharges(data: LabourChargesItem) {
	    console.log('LabourChargesItem', data);
		return this.createJobCardStore.createLabourChargesRow(data)
	}
	deleteRowFrs() {
		const frsTableDetails = this.labourCharges.get('labourChargesControl') as FormArray
		const nonSelected = frsTableDetails.controls.filter(machinery => !machinery.value.isSelected)
		const selected = frsTableDetails.controls.filter(machinery => machinery.value.isSelected)
		this.deletedFrsRecord.push(frsTableDetails.controls.filter(machinery => machinery.value.isSelected && machinery.value.labourChargeId).map(ele => ele.value))

		frsTableDetails.clear()
		nonSelected.forEach(el => frsTableDetails.push(el))
		const dataTable = this.labourCharges.get('labourChargesControl') as FormArray
		selected.forEach(element => {
			const deleteData = dataTable.controls.filter(
				ele =>
					ele.value.uuid === element.value.uuid
			)
			deleteData.forEach(el => dataTable.removeAt(el.value))
		})
	}
	addRow(list: SparePartRequisitionItem) {
		const machineDetails = this.partRequisition.get('partRequisitionControl') as FormArray
		if(machineDetails.valid)
		    machineDetails.push(this.createMachineDetailsTableRow(list))
	}
	addRowForDisable(list: SparePartRequisitionItem, dataForCheckCondition: ViewJobCard) {
		
		const machineDetails = this.partRequisition.get('partRequisitionControl') as FormArray
		const currentResp = this.createMachineDetailsTableRow(list)
		if (!dataForCheckCondition.draftFlag) {
			currentResp.get('category').disable()
			currentResp.get('partNumber').disable()
			currentResp.get('isSelected').disable()
			currentResp.get('qtyChangeFlag').patchValue(true)
		}
		machineDetails.push(currentResp)
		

	}
	createMachineDetailsTableRow(list: SparePartRequisitionItem) {
		return this.createJobCardStore.createItemDetailsTableRow(list)
	}

	deleteRow() {
		const machineDetails = (this.partRequisition.get('partRequisitionControl') as FormArray)
		const nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
		
		const selected = machineDetails.controls.filter(machinery => machinery.value.isSelected)
		
		this.demo.push(machineDetails.controls.filter(machinery => machinery.value.isSelected && machinery.value.sparePartId).map(ele => ele.value))
		

		machineDetails.clear()
		nonSelected.forEach(el => machineDetails.push(el))
		const dataTable = this.partRequisition.get('partRequisitionControl') as FormArray
		selected.forEach(element => {
		
			const deleteData = dataTable.controls.filter(ele => ele.value.uuid === element.value.uuid)
		
			deleteData.forEach(el => dataTable.removeAt(el.value))
		})
	}
	partNoChange(event: MatAutocompleteSelectedEvent) {
		
		const partId = event.option.value.id
		if (partId) {
			this.quantityChange()
		}
	}
	quantityChange() {
		if (this.partRequisition.get('partRequisitionControl')) {
			const tableData = (this.partRequisition.get(
				'partRequisitionControl') as FormArray) as FormArray
			tableData.controls.forEach((element: FormGroup) => {
				element.get('quantity').valueChanges.subscribe(result => {
					this.quantityCalculation()
				})
			})
		}
	}
	quantityCalculation() {
		if (this.partRequisition.get('partRequisitionControl')) {
			const tableData = this.partRequisition.get(
				'partRequisitionControl'
			) as FormArray
			
			tableData.controls.forEach((element: FormGroup) => {
				element
					.get('amount')
					.patchValue(
						((element.get('quantity').value &&
							parseFloat(element.get('quantity').value)) ||
							0) *
						((element.get('mrp').value && parseFloat(element.get('mrp').value)) ||
							0),
					)
			})
		}
	}
	patchDataFromView(viewData: ViewJobCard) {
		
		this.isPresalesCheckCondition(viewData.serviceCategory.category)
		this.customerConcern.get('customerConcernEditableTable').patchValue(viewData.customerConcern)
		if (viewData.serviceRepresentative) {
			this.customerConcern.get('serviceRepresentative').patchValue(viewData.serviceRepresentative.name)
		}
		if (viewData['estimatedCompletionDate']) {
			const conertedEstimatedDate = new Date(viewData['estimatedCompletionDate'])
			this.customerConcern.get('estimatedCompletionDate').patchValue(conertedEstimatedDate)
		}
		if (viewData.dateOfFailure) {
			const convertedFailureDate = new Date(viewData.dateOfFailure)
			this.jobCardBasicInfo.get('dateofFailure').patchValue(convertedFailureDate)
		}
		this.customerConcern.get('estimatedAmount').patchValue(viewData['estimatedAmount'])
		this.jobServiceCard.get('serviceJobCardEditable').patchValue(viewData.mechanicObservation)
		this.jobServiceCard.get('mechanicName').patchValue(viewData.mechanic)
		this.jobCardBasicInfo.get('bookingNo').disable()
		if (viewData.serviceBooking) {
			if (viewData.serviceBooking.bookingDate != null) {
				const convertedBookingdate = new Date(viewData.serviceBooking.bookingDate)
				this.jobCardBasicInfo.get('bookingDate').patchValue(convertedBookingdate)
			}
			this.jobCardBasicInfo.get('bookingNo').patchValue(viewData.serviceBooking.bookingNo)
		}
		this.jobCardBasicInfo.get('modelId').patchValue(viewData.machineInventory['machineMaster'].id)

		this.outsideJobCharge.get('finalActionTaken').patchValue(viewData.finalActionTaken)
		this.outsideJobCharge.get('suggestionAndAdvice').patchValue(viewData.suggestionToCustomer)
		this.jobCardBasicInfo.get('chassisNo').disable()
		this.jobCardBasicInfo.get('chassisNo').clearValidators()
		this.jobCardBasicInfo.get('chassisNo').updateValueAndValidity()
		// if (viewData.customerMaster) {
		this.jobCardBasicInfo.get('customerName').patchValue(viewData.customerName)
		this.jobCardBasicInfo.get('customerAddress').patchValue(viewData.address)
		this.jobCardBasicInfo.get('customerCellNo').patchValue(viewData.mobileNumber)
		
		this.jobCardBasicInfo.get('dateofInstallation').patchValue(viewData.installationDate)
        this.jobCardBasicInfo.get('alternateCustomerCellNo').patchValue(viewData.alternateNumber)
        this.jobCardBasicInfo.get('solddealer').patchValue(viewData.soldDealer)
           
        
		this.jobCardBasicInfo.get('pcrApproved').patchValue(viewData.pcrApprovedFlag)
		this.jobCardBasicInfo.get('partIssued').patchValue(viewData.partIssueFlag)

		// }
		if (viewData.serviceCategory.category == "Retro Fitment") {
			this.jobCardBasicInfo.get('retroFitment').patchValue(true)
		}
		if (viewData.serviceActivityProposal) {
			this.linkActivityPraposalPatch(viewData.serviceActivityProposal as ServiceActivityProposalView)
		}
		this.jobCardBasicInfo.patchValue({
			serviceCategory: viewData.serviceCategory,
			serviceType: viewData.serviceType,
			currentHours: viewData.currentHour,
			chassisNo: viewData.machineInventory.chassisNo,
			engineNo: viewData.machineInventory.engineNo,
			jobCardNo: viewData.jobCardNo,
			placeofService: viewData.placeOfService,
			timein: viewData.taskTime,
			machineModel: viewData.machineInventory['machineMaster'].model,
			previousHours: viewData.previousHour,
			totalHours: viewData.totalHour,
			jobCardDate: viewData.jobCardDate,
			date: viewData.taskDate,
		})
		if (viewData.machineInventory.registrationNumber) {
			this.jobCardBasicInfo.get('registrationNo').patchValue(viewData.machineInventory.registrationNumber)
		}
		if (viewData.machineInventory.csbNumber) {
			this.jobCardBasicInfo.get('csbNo').patchValue(viewData.machineInventory.csbNumber)
		}
		if (viewData.sparePartRequisitionItem.length > 0) {
			this.patchToPartRequisition(viewData as ViewJobCard
			)
		}
		if (viewData.labourCharge.length > 0) {
			this.patchToLabourCahrges(viewData.labourCharge as LabourChargeView[])
		}
		if (viewData.outsideJobCharge.length > 0) {
			this.patchToOusideLabourJobCard(viewData.outsideJobCharge as OutsideJobChargeView[])
		}
		this.jobCardBasicInfo.get('draftFlag').patchValue(viewData['draftFlag'])

		viewData.serviceJobcardPhotos.forEach(photos => {
			if (photos.fileType == 'free service coupon 1') {
				this.couponOne.push(photos.fileName)
			}
			if (photos.fileType == 'free service coupon 2') {
				this.coupontwo.push(photos.fileName)
			}
			if (photos.fileType == 'hour meter ') {
				this.hourMeters.push(photos.fileName)
			}
			if (photos.fileType == 'chassis') {
				this.chassisList.push(photos.fileName)
			}
		})
	}
	private linkActivityPraposalPatch(patchDataToActivity: ServiceActivityProposalView) {
		this.jobCardBasicInfo.get('activityType').patchValue(patchDataToActivity.serviceMtActivityType);
		this.jobCardBasicInfo.get('activityNo').patchValue({ value: patchDataToActivity.activityNumber, id: patchDataToActivity.id });
	}
	private patchToLabourCahrges(labourChargesForPatch: LabourChargeView[]) {
		const labourChargesData = []
		labourChargesForPatch.forEach(data => {
		    labourChargesData.push({
				category: { category: data.category.category, id: data.category.id },
				frsCode: {
					frsCode: data.serviceMtFrsCode.jobCodeNo,
					id: data.serviceMtFrsCode.id
				},
				description: data.serviceMtFrsCode.description,
				hour: data.serviceMtFrsCode.time,
				amount: data.amount,
				labourChargeId: data.labourChargeId
			})
		})
		labourChargesData.forEach(ele => {
			if (this.operation === Operation.VIEW) {
				this.addRowFrsView(ele)
			} else if (this.operation === Operation.EDIT) {
				this.addRowFrs(ele)
			}
		})
	}
	private patchToOusideLabourJobCard(jobCodeDataPatchToTable: OutsideJobChargeView[]) {
		
		const jobodeDataStore = []
		jobCodeDataPatchToTable.forEach(data => {
			jobodeDataStore.push({
				category: data.category,
				jobCode: {
					jobcode: data.serviceMtJobcode.jobcode,
					id: data.serviceMtJobcode.id
				},
				description: data.serviceMtJobcode.description,
				amount: data.amount,
				jobcodeId: data.jobcodeId
			})
		})
		jobodeDataStore.forEach(ele => {
			if (this.operation === Operation.VIEW) {
				this.addRowJobCodeView(ele)
			} else if (this.operation === Operation.EDIT) {
				this.addRowJobCode(ele)
			}
		})
	}
	private patchToPartRequisition(
		partRequisitionItemsForPatch: ViewJobCard
	) {
		const partRequsitionData = []
		partRequisitionItemsForPatch.sparePartRequisitionItem.forEach(data => {
			partRequsitionData.push({
				category: data.category,
				partNumber: {
					value: data.sparePartMaster.partNumber,
					id: data.sparePartMaster.id,
				},
				itemDescription: data.sparePartMaster.itemDescription,
				quantity: data.quantity,
				cmpQty: data.quantity,
				utilizedQuantity: data.utilisedQuantity,
				mrp: data.mrp,
				amount: data.amount,
				approveQuantity: data.approveQuantity,
				approveStatus: data.approveStatus,
				sparePartId: data.sparePartId
			})
		})
		partRequsitionData.forEach(ele => {
			
			if (this.operation === Operation.VIEW) {
				this.addRowView(ele)
			} else if (this.operation === Operation.EDIT) {
				this.addRowForDisable(ele, partRequisitionItemsForPatch)
			}
		})
		this.quantityChange()
	}

	isPresalesCheckCondition(category: string) {
		if (category == 'Pre Sales') {
			this.jobCardBasicInfo.get('checkBox').patchValue(true)
		} else {
			this.jobCardBasicInfo.get('checkBox').patchValue(false)
		}
	}
	patchValueFromBookingNumber(patchData: BookingNoJobData) {
		this.jobCardBasicInfo.patchValue({
			engineNo: patchData.engineNo,
			machineModel: patchData.model,
			placeofService: patchData.placeOfService,
			// serviceCategory: patchData.serviceCategory,
			// serviceType: patchData.serviceType,
			customerName: patchData.customerName,
			customerAddress: patchData.address,
			customerCellNo: patchData.mobileNo,
			previousHours: patchData.previousHour,
		})
		this.jobCardBasicInfo.get('bookingDate').enable()

		if (patchData.previousCurrentHour) {
			this.jobCardBasicInfo.get('priviousCurrentHours').patchValue(patchData.previousCurrentHour)
		}
		if (patchData.chassisNo) {
			this.jobCardBasicInfo.get('chassisNo').patchValue({ code: patchData.chassisNo, machineInvetoryId: patchData.machineInventoryId })
		}
		if (patchData.serviceCategory) {
			this.jobCardBasicInfo.get('serviceCategory').patchValue({ category: patchData.serviceCategory, id: patchData.categoryId })
		}
		if (patchData.serviceType) {
			this.jobCardBasicInfo.get('serviceType').patchValue({ serviceType: patchData.serviceType, id: patchData.serviceTypeId })
		}
		if (typeof patchData.previousHour === 'number') {
			this.jobCardBasicInfo.get('previousHours').patchValue(patchData.previousHour)
		}
		if (typeof patchData.currentHour === 'number') {
			this.jobCardBasicInfo.get('currentHours').patchValue(patchData.currentHour)
		}
		if (typeof patchData.totalHour === 'number') {
			this.jobCardBasicInfo.get('totalHours').patchValue(patchData.totalHour)
		}
		if (patchData.csbNumber) {
			this.jobCardBasicInfo.get('csbNo').patchValue(patchData.csbNumber)
		}
		if (patchData.soldDealer) {
			this.jobCardBasicInfo.get('solddealer').patchValue(patchData.soldDealer)
		}
		if (patchData.registrationNumber) {
			this.jobCardBasicInfo.get('registrationNo').patchValue(patchData.registrationNumber)
		}
		this.jobCardBasicInfo.get('chassisNo').disable()
		const bookingConvertedDate = new Date(patchData.bookingDate)
		const installationConverteddate = new Date(patchData.installationDate)
		this.jobCardBasicInfo.get('bookingDate').patchValue(bookingConvertedDate),
			this.jobCardBasicInfo.get('dateofInstallation').patchValue(installationConverteddate)
	}

	bookingNoKey(event: KeyboardEvent) {
		if (event.key === 'Backspace' || event.key === 'Delete') {
			this.jobCardBasicInfo.get('chassisNo').reset()
			this.jobCardBasicInfo.get('engineNo').reset()
			this.jobCardBasicInfo.get('csbNo').reset()
			// this.jobCardBasicInfo.get('bookingNo').reset()
			this.jobCardBasicInfo.get('bookingDate').reset()
			this.jobCardBasicInfo.get('jobCardNo').reset()
			this.jobCardBasicInfo.get('dateofInstallation').reset()
			this.jobCardBasicInfo.get('dateofFailure').reset()
			this.jobCardBasicInfo.get('jobCardDate').reset()
			this.jobCardBasicInfo.get('customerName').reset()
			this.jobCardBasicInfo.get('machineModel').reset()
			this.jobCardBasicInfo.get('registrationNo').reset()
			this.jobCardBasicInfo.get('customerAddress').reset()
			this.jobCardBasicInfo.get('customerCellNo').reset()
			this.jobCardBasicInfo.get('alternateCustomerCellNo').reset()
			this.jobCardBasicInfo.get('solddealer').reset()
			this.jobCardBasicInfo.get('serviceCategory').reset()
			this.jobCardBasicInfo.get('serviceType').reset()
			this.jobCardBasicInfo.get('placeofService').reset()
			this.jobCardBasicInfo.get('currentHours').reset()
			this.jobCardBasicInfo.get('previousHours').reset()
			this.jobCardBasicInfo.get('totalHours').reset()
			this.jobCardBasicInfo.get('activityType').reset()
			this.jobCardBasicInfo.get('activityNo').reset()
			this.jobCardBasicInfo.get('date').reset()
			this.jobCardBasicInfo.get('timein').reset()
			this.jobCardBasicInfo.get('chassisNo').enable()
		}
	}

	chassisNoKey(event: KeyboardEvent) {
		if (event.key === 'Backspace' || event.key === 'Delete') {
			// this.jobCardBasicInfo.get('chassisNo').reset()
			this.jobCardBasicInfo.get('bookingNo').enable()

			this.jobCardBasicInfo.get('engineNo').reset()
			this.jobCardBasicInfo.get('csbNo').reset()
			this.jobCardBasicInfo.get('bookingNo').reset()
			this.jobCardBasicInfo.get('bookingDate').reset()
			this.jobCardBasicInfo.get('jobCardNo').reset()
			this.jobCardBasicInfo.get('dateofInstallation').reset()
			this.jobCardBasicInfo.get('dateofFailure').reset()
			this.jobCardBasicInfo.get('jobCardDate').reset()
			this.jobCardBasicInfo.get('customerName').reset()
			this.jobCardBasicInfo.get('machineModel').reset()
			this.jobCardBasicInfo.get('registrationNo').reset()
			this.jobCardBasicInfo.get('customerAddress').reset()
			this.jobCardBasicInfo.get('customerCellNo').reset()
			this.jobCardBasicInfo.get('alternateCustomerCellNo').reset()
			this.jobCardBasicInfo.get('solddealer').reset()
			this.jobCardBasicInfo.get('serviceCategory').reset()
			this.jobCardBasicInfo.get('serviceType').reset()
			this.jobCardBasicInfo.get('placeofService').reset()
			this.jobCardBasicInfo.get('currentHours').reset()
			this.jobCardBasicInfo.get('previousHours').reset()
			this.jobCardBasicInfo.get('totalHours').reset()
			this.jobCardBasicInfo.get('activityType').reset()
			this.jobCardBasicInfo.get('activityNo').reset()
			this.jobCardBasicInfo.get('date').reset()
			this.jobCardBasicInfo.get('timein').reset()
			this.jobCardBasicInfo.get('bookingNo').enable()
		}
	}
	selectionChange() {
		this.jobCardBasicInfo.get('chassisNo').reset()
		this.jobCardBasicInfo.get('engineNo').reset()
		this.jobCardBasicInfo.get('csbNo').reset()
		this.jobCardBasicInfo.get('bookingNo').reset()
		this.jobCardBasicInfo.get('bookingDate').reset()
		this.jobCardBasicInfo.get('jobCardNo').reset()
		this.jobCardBasicInfo.get('dateofInstallation').reset()
		this.jobCardBasicInfo.get('dateofFailure').reset()
		this.jobCardBasicInfo.get('jobCardDate').reset()
		this.jobCardBasicInfo.get('customerName').reset()
		this.jobCardBasicInfo.get('machineModel').reset()
		this.jobCardBasicInfo.get('registrationNo').reset()
		this.jobCardBasicInfo.get('customerAddress').reset()
		this.jobCardBasicInfo.get('customerCellNo').reset()
		this.jobCardBasicInfo.get('alternateCustomerCellNo').reset()
		this.jobCardBasicInfo.get('solddealer').reset()
		this.jobCardBasicInfo.get('serviceCategory').reset()
		this.jobCardBasicInfo.get('serviceType').reset()
		this.jobCardBasicInfo.get('placeofService').reset()
		this.jobCardBasicInfo.get('currentHours').reset()
		this.jobCardBasicInfo.get('previousHours').reset()
		this.jobCardBasicInfo.get('totalHours').reset()
		this.jobCardBasicInfo.get('activityType').reset()
		this.jobCardBasicInfo.get('activityNo').reset()
		this.jobCardBasicInfo.get('date').reset()
		this.jobCardBasicInfo.get('timein').reset()
		this.jobCardBasicInfo.get('bookingNo').enable()
	}
	patchDataFromResponse(data: ChassisNumberJobData) {
		
		this.jobCardBasicInfo.get('engineNo').patchValue(data.engineNo)
		this.jobCardBasicInfo.get('solddealer').patchValue(data.dealerName)
		this.jobCardBasicInfo.get('machineModel').patchValue(data.model)
		this.jobCardBasicInfo.get('placeofService').patchValue(data.placeOfService)
		this.jobCardBasicInfo.get('serviceCategory').patchValue({ id: 1, category: data.category })
		this.jobCardBasicInfo.get('customerMasterId').patchValue(data.customerId)
	}
	patchValueFromChassisNumber(selectedChassisNumber: ChassisNumberJobData) {
		
		this.jobCardBasicInfo.get('dateofInstallation').setValidators(null)
		this.jobCardBasicInfo.get('currentHours').setValidators(null)
		this.jobCardBasicInfo.get('dateofFailure').setValidators(null)
		this.jobCardBasicInfo.get('bookingNo').disable()
		if (selectedChassisNumber.previousCurrentHour) {
			this.jobCardBasicInfo.get('priviousCurrentHours').patchValue(selectedChassisNumber.previousCurrentHour)
		}
		if (selectedChassisNumber.engineNo) {
			this.jobCardBasicInfo.get('engineNo').patchValue(selectedChassisNumber.engineNo)
		}
		if (selectedChassisNumber.model) {
			this.jobCardBasicInfo.get('machineModel').patchValue(selectedChassisNumber.model)
		}
		if (selectedChassisNumber.placeOfService) {
			this.jobCardBasicInfo.get('placeofService').patchValue(selectedChassisNumber.placeOfService)
		}
		if (selectedChassisNumber.category) {
			this.jobCardBasicInfo.get('serviceCategory').patchValue({ category: selectedChassisNumber.category, id: selectedChassisNumber.categoryId })
		}
		if (selectedChassisNumber.serviceType) {
			this.jobCardBasicInfo.get('serviceType').patchValue({ serviceType: selectedChassisNumber.serviceType, id: selectedChassisNumber.serviceTypeId })
		}
		if (selectedChassisNumber.placeOfService) {
			this.jobCardBasicInfo.get('placeofService').patchValue({ placeOfService: selectedChassisNumber.placeOfService, id: 1 })
		}
		if (selectedChassisNumber.customerName) {
			this.jobCardBasicInfo.get('customerName').patchValue(selectedChassisNumber.customerName)
		}
		if (selectedChassisNumber.registrationNumber) {
			this.jobCardBasicInfo.get('registrationNo').patchValue(selectedChassisNumber.registrationNumber)
		}
		if (selectedChassisNumber.address) {
			this.jobCardBasicInfo.get('customerAddress').patchValue(selectedChassisNumber.address)
		}
		if (selectedChassisNumber.mobileNumber) {
			this.jobCardBasicInfo.get('customerCellNo').patchValue(selectedChassisNumber.mobileNumber)
		}
		if (selectedChassisNumber.alternateNumber) {
            this.jobCardBasicInfo.get('alternateCustomerCellNo').patchValue(selectedChassisNumber.alternateNumber)
        }
		if (selectedChassisNumber.bookingDate != null) {

			const convertedBookingDate = new Date(selectedChassisNumber.bookingDate)
			this.jobCardBasicInfo.get('bookingDate').patchValue(convertedBookingDate)
		}

		if (selectedChassisNumber.bookingNo) {
			this.jobCardBasicInfo.get('bookingNo').patchValue({ code: selectedChassisNumber.bookingNo, id: selectedChassisNumber.seviceBookingId })
			this.jobCardBasicInfo.get('bookingNo').disable()
			this.jobCardBasicInfo.get('bookingDate').enable()
		}
		if (typeof selectedChassisNumber.previousHour === 'number') {
			this.jobCardBasicInfo.get('previousHours').patchValue(selectedChassisNumber.previousHour)
		}
		if (typeof selectedChassisNumber.currentHour === 'number') {
			this.jobCardBasicInfo.get('currentHours').patchValue(selectedChassisNumber.currentHour)
		}

		if (typeof selectedChassisNumber.totalHour === 'number') {
			this.jobCardBasicInfo.get('totalHours').patchValue(selectedChassisNumber.totalHour)
		}
		if (selectedChassisNumber.installationDate) {
			const convertedInstallationDate = new Date(selectedChassisNumber.installationDate)
			this.jobCardBasicInfo.get('dateofInstallation').patchValue(convertedInstallationDate)
		}
		if (selectedChassisNumber.retrofitmentFlag) {
			this.jobCardBasicInfo.get('retrofitmentFlag').patchValue(selectedChassisNumber.retrofitmentFlag)
		}
		if (selectedChassisNumber.dateOfFailure) {
			const conertedFailureDate = new Date(selectedChassisNumber.dateOfFailure)
			this.jobCardBasicInfo.get('dateofFailure').patchValue(conertedFailureDate)
		}

		if (selectedChassisNumber.csbNumber) {
			this.jobCardBasicInfo.get('csbNo').patchValue(selectedChassisNumber.csbNumber)
		}
		if (selectedChassisNumber.soldDealer) {
			this.jobCardBasicInfo.get('solddealer').patchValue(selectedChassisNumber.soldDealer)
		}
	}
	couponOnePresenter(fileOne) {
		this.couponOneFile = fileOne
	}
	couponTwoPresenter(fileTwo) {
		this.couponTwoFile = fileTwo
	}
	couponThreePresenter(fileThree) {
		this.couponThreeFile = fileThree
	}
	couponFourPresenter(fileFour) {
		this.couponFourFile = fileFour
	}
	disableForView() {
		this.jobCardBasicInfo.disable()
		this.customerConcern.disable()
		this.jobServiceCard.disable()
		this.outsideJobCharge.disable()
		this.partRequisition.disable()
		this.labourCharges.disable()
	}
	
	resetAlredyCreatedJobCard() {
		this.jobCardBasicInfo.get('chassisNo').reset()
		this.jobCardBasicInfo.get('engineNo').reset()
		this.jobCardBasicInfo.get('csbNo').reset()
		this.jobCardBasicInfo.get('bookingNo').reset()
		this.jobCardBasicInfo.get('bookingDate').reset()
		this.jobCardBasicInfo.get('jobCardNo').reset()
		this.jobCardBasicInfo.get('dateofInstallation').reset()
		this.jobCardBasicInfo.get('dateofFailure').reset()
		this.jobCardBasicInfo.get('jobCardDate').reset()
		this.jobCardBasicInfo.get('customerName').reset()
		this.jobCardBasicInfo.get('machineModel').reset()
		this.jobCardBasicInfo.get('registrationNo').reset()
		this.jobCardBasicInfo.get('customerAddress').reset()
		this.jobCardBasicInfo.get('customerCellNo').reset()
		this.jobCardBasicInfo.get('alternateCustomerCellNo').reset()
		this.jobCardBasicInfo.get('solddealer').reset()
		this.jobCardBasicInfo.get('serviceCategory').reset()
		this.jobCardBasicInfo.get('serviceType').reset()
		this.jobCardBasicInfo.get('placeofService').reset()
		this.jobCardBasicInfo.get('currentHours').reset()
		this.jobCardBasicInfo.get('previousHours').reset()
		this.jobCardBasicInfo.get('totalHours').reset()
		this.jobCardBasicInfo.get('activityType').reset()
		this.jobCardBasicInfo.get('activityNo').reset()
		this.jobCardBasicInfo.get('date').reset()
		this.jobCardBasicInfo.get('timein').reset()
		this.jobCardBasicInfo.get('bookingNo').enable()
	}
}
