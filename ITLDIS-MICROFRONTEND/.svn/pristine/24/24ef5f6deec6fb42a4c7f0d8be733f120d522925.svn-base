import { Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms'
import { Injectable } from '@angular/core'
import { CustomValidators,ValidateMin } from '../../../../../utils/custom-validators'
import * as uuid from 'uuid'

@Injectable()
export class CreateJobCardStore {
	private readonly _jobCardFormGroup: FormGroup
	private readonly _serviceFormGroup: FormGroup

	constructor(private formBuilder: FormBuilder) {
		this._jobCardFormGroup = this.formBuilder.group({
			jobCardBasicInfo: this.buildJobCardFormGroup(),
			customerConcern: this.buildCustomerConcernFormGroup(),
		})
		this._serviceFormGroup = this.formBuilder.group({
			jobServiceCard: this.buildServiceJobGroup(),
			outsideJobCharge: this.buildOutsideJobChargeFormGroup(),
			jobCode: this.formBuilder.group({
				jobCodeControl: this.formBuilder.array([]),
			}),
			partRequisition: this.formBuilder.group({
				partRequisitionControl: this.formBuilder.array([]),
			}),
			labourCharges: this.formBuilder.group({
				labourChargesControl: this.formBuilder.array([]),
			}),
		})
	}

	get jobCardForm() {
		return this._jobCardFormGroup
	}

	get serviceJobForm() {
		return this._serviceFormGroup
	}
	buildServiceJobGroup() {
		return this.formBuilder.group({
			mechanicName: [{ value: null, disabled: false }, Validators.compose([])],
			serviceJobCardEditable: [null],
		})
	}

	buildOutsideJobChargeFormGroup() {
		return this.formBuilder.group({
			finalActionTaken: [{ value: null, disabled: false }],
			suggestionAndAdvice: [{ value: null, disabled: false }],
			couponOne: [{ value: null, disabled: false }],
			couponTwo: [{ value: null, disabled: false }],
			hourMeter: [{ value: null, disabled: false }],
			chassisNumber: [{ value: null, disabled: false }],

		})
	}
	buildJobCardFormGroup() {
		return this.formBuilder.group({
			modelId: null,
			pcrApproved: [{ value: false, disabled: false }],
			priviousCurrentHours: [{ value: null, disabled: false }],
			meterChangeHour: [{ value: null, disabled: false }],
			partIssued: [{ value: false, disabled: false }],
			checkBox: [{ value: false, disabled: false }],
			retroFitment: [{ value: false, disabled: true }],
			retrofitmentFlag: [{ value: false, disabled: false }],
			customerMasterId: [null],
			preSalesFlagSet: [{ value: false, disabled: false }],
			draftFlag: [{ value: null }],
			chassisNo: [{ value: null, disabled: false }],
			engineNo: [{ value: null, disabled: true }],
			csbNo: [{ value: null, disabled: true }],
			bookingNo: [{ value: null, disabled: false }],
			bookingDate: [{ value: null, disabled: true }],
			jobCardNo: [{ value: null, disabled: true }],
			dateofInstallation: [{ value: null, disabled: true }],
			dateofFailure: [{ value: null, disabled: false }, Validators.required],
			jobCardDate: [{ value: null, disabled: true }],
			customerName: [{ value: null, disabled: true }],
			machineModel: [{ value: null, disabled: true }],
			registrationNo: [{ value: null, disabled: true }],
			customerAddress: [{ value: null, disabled: true }],
			customerCellNo: [{ value: null, disabled: true }],
			alternateCustomerCellNo: [{ value: null, disabled: false }, Validators.compose([Validators.pattern('[1-9]\\d{9}'), CustomValidators.numberOnly])],
			solddealer: [{ value: null, disabled: true }],
			serviceCategory: [{ value: null, disabled: false }],
			serviceType: [{ value: null, disabled: false }],
			placeofService: [{ value: null, disabled: false }],
			currentHours: [{ value: null, disabled: false }, CustomValidators.maxDecimalPoint(1)],
			previousHours: [{ value: null, disabled: true }, CustomValidators.maxDecimalPoint(1)],
			totalHours: [{ value: null, disabled: true }, CustomValidators.maxDecimalPoint(1)],
			activityType: [{ value: null, disabled: false }],
			activityNo: [{ value: null, disabled: false }],
			date: [{ value: null, disabled: false }],
			timein: [{ value: null, disabled: false }],
		})
	}
	buildCustomerConcernFormGroup() {
		return this.formBuilder.group({
			customerConcernEditableTable: [null],
			serviceRepresentative: [null],
			estimatedCompletionDate: [null],
			estimatedAmount: [null, CustomValidators.numberOnly],
		})
	}

	createItemDetailsTableRow(data?): FormGroup {
		let fg= this.formBuilder.group({
			uuid: [uuid.v4()],
			id: [data ? data.id : null],
			qtyChangeFlag: [{ value: false, disabled: false }],
			sparePartId: [data ? data.sparePartId : null],
			isSelected: [{ value: false, disabled: false }],
			category: [{ value: data ? data.category : null, disabled: false }, Validators.required],
			partNumber: [{ value: data ? data.partNumber : null, disabled: false }, Validators.compose([CustomValidators.selectFromListDynamic(),Validators.required])],
			itemDescription: [{ value: data ? data.itemDescription : null, disabled: true }],
			quantity: [{ value: data ? data.quantity : null, disabled: false }],
			cmpQty: [{ value: data ? data.quantity : null, disabled: false }],
			utilizedQuantity: [{ value: data ? data.utilizedQuantity : null, disabled: true }],
			mrp: [{ value: data ? data.mrp : null, disabled: true }],
			amount: [{ value: data ? data.amount : null, disabled: true }],
			approveQuantity: [{ value: data ? data.approveQuantity : null, disabled: true },
			],
			approveStatus: [
				{ value: data ? data.approveStatus : null, disabled: true },
			],
		})
		fg.controls.quantity.setValidators(Validators.compose([CustomValidators.numberOnly,Validators.required, ValidateMin(fg.controls.utilizedQuantity,"Quantity Should be Greater than equal to Utilized Qty")]));
		return fg;
	}

	createLabourChargesRow(data?): FormGroup {
		return this.formBuilder.group({
			uuid: [uuid.v4()],
			id: [data ? data.id : null],
			category: [data ? data.category : null, Validators.required],
			labourChargeId: [data ? data.labourChargeId : null],
			isSelected: [{ value: false, disabled: false }],
			frsCode: [{ value: data ? data.frsCode : null, disabled: false }, Validators.compose([CustomValidators.selectFromListDynamic(),Validators.required])],
			description: [{ value: data ? data.description : null, disabled: true }],
			hour: [{ value: data ? data.hour : null, disabled: true }],
			amount: [{ value: data ? data.amount : null, disabled: false }, Validators.compose([CustomValidators.numberOnly,Validators.required])],
			approveAmount: [{ value: data ? data.approveAmount : null, disabled: true }],
		})
	}
	createJobCodeRow(data?): FormGroup {
		return this.formBuilder.group({
			uuid: [uuid.v4()],
			id: [data ? data.id : null],
			jobcodeId: [data ? data.jobcodeId : null],
			isSelected: [{ value: false, disabled: false }],
			category: [data ? data.category : null, Validators.required],
			jobCode: [{ value: data ? data.jobCode : null, disabled: false }, Validators.compose([CustomValidators.selectFromListDynamic(),Validators.required])],
			description: [{ value: data ? data.description : null, disabled: true }],
			amount: [{ value: data ? data.amount : null, disabled: false }, Validators.compose([CustomValidators.numberOnly,Validators.required])],
			approveAmount: [{ value: data ? data.approveAmount : null, disabled: true }],
		})
	}
}
