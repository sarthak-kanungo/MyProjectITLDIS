import { Component, OnInit, Input } from '@angular/core'
import { FormGroup, FormArray, Validators } from '@angular/forms'
import { TableConfig } from 'editable-table'
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter'
import { UploadableFile } from 'itldis-file-upload'
import { OutsideJobWebService } from './outside-job-web.service'
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service'
import { ActivatedRoute } from '@angular/router'
import { Operation } from '../../../../../utils/operation-util'
import { JobCardPageWebService } from '../create-job-card-page/job-card-page-web.service'
import { JobCardUrl } from '../../url-util/job-card-url'
import { JobCodeTableData } from '../../domain/job-code-table'
import { MatAutocompleteSelectedEvent } from '@angular/material'
import { Observable } from 'rxjs'
import { SearchAutocompleteJobCode, ServiceCategoryDropDownList, Category, DropDownCategory } from '../../domain/job-card-domain'

@Component({
	selector: 'app-outside-job-charges',
	templateUrl: './outside-job-charges.component.html',
	styleUrls: ['./outside-job-charges.component.scss'],
	providers: [OutsideJobWebService, FileUploadService, JobCardPageWebService],
})
export class OutsideJobChargesComponent implements OnInit {

	@Input() jobCodeForm: FormGroup
	uuid: string
	isCreate: boolean
	ishideForDraftFlag: boolean
	isFreeService: boolean
	@Input() isTablesShow: boolean
	@Input() public set fileFromView(fileFromView) {
		if (fileFromView) {
			fileFromView.forEach(res => {
				if (res.fileType == 'free service coupon 1') {
					this.selectedPhotosOne.push(res)
					this.presenter.outsideJobCharge.get('couponOne').clearValidators()
					this.presenter.outsideJobCharge.get('couponOne').updateValueAndValidity()
					console.log('this.presenter.outsideJobCharge.get(\'couponOne\')', this.presenter.outsideJobCharge.get('couponOne'))
				}
				if (res.fileType == 'free service coupon 2') {
					this.selectedPhotosTwo.push(res)
					this.presenter.outsideJobCharge.get('couponTwo').clearValidators()
					this.presenter.outsideJobCharge.get('couponTwo').updateValueAndValidity()
					console.log('this.presenter.outsideJobCharge.get(\'couponTwo\')', this.presenter.outsideJobCharge.get('couponTwo'))
				}
				if (res.fileType == 'hour meter ') {
					console.log('res545646565465465465464', res)
					this.selectedPhotosCoupon.push(res)
					this.presenter.outsideJobCharge.get('hourMeter').clearValidators()
					this.presenter.outsideJobCharge.get('hourMeter').updateValueAndValidity()
				}
				if (res.fileType == 'chassis') {
					this.selectedPhotosChassis.push(res)
					this.presenter.outsideJobCharge.get('chassisNumber').clearValidators()
					this.presenter.outsideJobCharge.get('chassisNumber').updateValueAndValidity()
				}
			})
		}
	}
	@Input() public set fileFromEdit(fileFromEdit) {
		console.log('fileFromEdit', fileFromEdit)
		if (fileFromEdit) {
			fileFromEdit.forEach(res => {
				if (res.fileType === 'free service coupon 1') {
					console.log('res97888888888888888888888888888888888888888888888122', res)
					this.files.push(res)
					this.presenter.outsideJobCharge.get('couponOne').clearValidators()
					this.presenter.outsideJobCharge.get('couponOne').updateValueAndValidity()
					console.log(' couponOne ', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'free service coupon 2') {
					this.serviceCouponList.push(res)
					this.presenter.outsideJobCharge.get('couponTwo').clearValidators()
					this.presenter.outsideJobCharge.get('couponTwo').setErrors(null)
					this.presenter.outsideJobCharge.get('couponTwo').updateValueAndValidity()
					console.log(' (\'couponTwo\')', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'hour meter ') {
					this.hourMeterList.push(res)
					this.presenter.outsideJobCharge.get('hourMeter').clearValidators()
					this.presenter.outsideJobCharge.get('hourMeter').updateValueAndValidity()
					console.log(' (\'hourMeter\')', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'chassis') {
					this.chassisNumberList.push(res)
					this.presenter.outsideJobCharge.get('chassisNumber').clearValidators()
					this.presenter.outsideJobCharge.get('chassisNumber').updateValueAndValidity()
					console.log(' (\'chassis number\')', this.presenter.outsideJobCharge)

				}
			})
		}
	}
	selectedPhotosOne = []
	selectedPhotosTwo = []
	selectedPhotosCoupon = []
	selectedPhotosChassis = []
	outsideJobChargeForm: FormGroup
	fileStaticPath: string = JobCardUrl.staticPath
	jobCodeList: Observable<Array<SearchAutocompleteJobCode>>
	files: Array<UploadableFile> = new Array()
	serviceCouponList: Array<UploadableFile> = new Array()
	hourMeterList: Array<UploadableFile> = new Array()
	chassisNumberList: Array<UploadableFile> = new Array()
	tableConfig: TableConfig[]
	isView: boolean
	isEdit: boolean
	serviceCategoryList: ServiceCategoryDropDownList
	constructor(
		private presenter: JobCardPresenter,
		private outsideJobWebService: OutsideJobWebService,
		private activateRoute: ActivatedRoute,
	) { }

	ngOnInit() {
		this.outsideJobChargeForm = this.presenter.outsideJobCharge
		console.log('this.outsideJobChargeForm', this.outsideJobChargeForm)
		this.ViewOrEditOrCreate();
		this.freeServiceCondition();
		this.serviceCategoryDropDown()
	}
	freeServiceCondition(){
		this.presenter.jobCardBasicInfo.get('serviceCategory').valueChanges.subscribe(res => {
			if (res.category == "Maintenance Service") {
				this.isFreeService = true;
			}else{
				this.isFreeService = false;
			}
		});
	}
	// freeServiceCondition() {
	// 	this.presenter.jobCardBasicInfo.get('serviceCategory').valueChanges.subscribe(res => {
	// 		console.log('servieCategory111111111111111111111111111', res)
	// 		if (res.category == "Free Service") {
	// 			this.isFreeService = true
	// 			console.log('this.isFreeServicettttttttttt', this.isFreeService)
	// 			this.presenter.outsideJobCharge.get('couponOne').setValidators(Validators.required)
	// 			this.presenter.outsideJobCharge.get('couponTwo').setValidators(Validators.required)
	// 			this.presenter.outsideJobCharge.get('hourMeter').setValidators(Validators.required)
	// 			this.presenter.outsideJobCharge.get('chassisNumber').setValidators(Validators.required)
	// 			console.log('this.presenter.outsideJobCharge', this.presenter.outsideJobCharge)
	// 		} else {
	// 			this.isFreeService = false
	// 			console.log('this.isFreeServicefffffffffff', this.isFreeService)
	// 			this.presenter.outsideJobCharge.get('couponOne').clearValidators()
	// 			this.presenter.outsideJobCharge.get('couponOne').updateValueAndValidity()
	// 			this.presenter.outsideJobCharge.get('couponTwo').clearValidators()
	// 			this.presenter.outsideJobCharge.get('couponTwo').updateValueAndValidity()
	// 			this.presenter.outsideJobCharge.get('hourMeter').setValidators(Validators.required)
	// 			this.presenter.outsideJobCharge.get('chassisNumber').setValidators(Validators.required)
	// 		}
	// 	})





	// }
	private ViewOrEditOrCreate() {
		if (this.presenter.operation === Operation.VIEW) {
			this.isView = true
			this.isEdit = false
			this.presenter.jobCardBasicInfo.get('draftFlag').valueChanges.subscribe(res => { this.setValueFromView() })

		} else if (this.presenter.operation === Operation.EDIT) {
			this.isEdit = true
			this.presenter.jobCardBasicInfo.get('draftFlag').valueChanges.subscribe(res => { this.setValueFromView() })

		} else if (this.presenter.operation === Operation.CREATE) {
			this.isCreate = true
			// this.addRow()
		}
	}
	setValueFromView() {
		if (this.presenter.jobCardBasicInfo.get('draftFlag').value == false) {
			this.ishideForDraftFlag = true
		}
	}

	fileSelectionChanges(fileInput: Event) {
		console.log('fileInput', fileInput)
		if (this.files.length >= 1) {
			this.files = []
		}
		if (!this.isFilesCountFive()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'couponOne')
			this.files = this.outsideJobWebService.listUploadableFiles()
			console.log('this.files', this.files)
			this.presenter.couponOnePresenter(this.files)
		}
	}
	fileSelectionServiceCoupon2Changes(fileInput: Event) {
		if (!this.isFilesCountFiveCoupon()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'couponTwo')
			this.serviceCouponList = this.outsideJobWebService.listUploadableFilesServiceCouponList()
			this.presenter.couponTwoPresenter(this.serviceCouponList)
		}
	}
	fileSelectionHourMeterChanges(fileInput: Event) {
		if (!this.isFilesCountFiveMeter()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'hourMeter')
			this.hourMeterList = this.outsideJobWebService.listUploadableFilesHourMeterList()
			this.presenter.couponThreePresenter(this.hourMeterList)
		}
	}
	fileSelectionChasisNumberChanges(fileInput: Event) {
		if (!this.isFilesCountFiveChassis()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'chassisNumber')
			this.chassisNumberList = this.outsideJobWebService.listUploadableFilesChassisNumberList()
			this.presenter.couponFourPresenter(this.chassisNumberList)
		}
	}



	deleteImage(id: string) {
		if (this.presenter.outsideJobCharge.get('couponOne').value) {
			this.presenter.outsideJobCharge.get('couponOne').reset()
		}
		this.outsideJobWebService.deleteFile(id)
		this.files = this.outsideJobWebService.listUploadableFiles()
	}
	deleteImageCouponTwo(id: string) {
		if (this.presenter.outsideJobCharge.get('couponTwo').value) {
			this.presenter.outsideJobCharge.get('couponTwo').reset()
		}
		this.outsideJobWebService.deleteFileCouponTwo(id)
		this.serviceCouponList = this.outsideJobWebService.listUploadableFilesCoupon()
	}
	deleteImageHour(id: string) {

		if (this.presenter.outsideJobCharge.get('hourMeter').value) {
			this.presenter.outsideJobCharge.get('hourMeter').reset()
		}
		this.outsideJobWebService.deleteFileHour(id)
		this.hourMeterList = this.outsideJobWebService.listUploadableFileshourMeter()
	}
	deleteImageChassis(id: string) {
		if (this.presenter.outsideJobCharge.get('chassisNumber').value) {
			this.presenter.outsideJobCharge.get('chassisNumber').reset()
		}
		this.chassisNumberList = this.outsideJobWebService.listUploadableFilesChassis()
		this.outsideJobWebService.deleteFileChassis(id)
	}

	isFilesCountFive() {
		return this.outsideJobWebService.fileCount() == 1
	}
	isFilesCountFiveCoupon() {
		return this.outsideJobWebService.fileCountCoupon() == 1
	}
	isFilesCountFiveMeter() {
		return this.outsideJobWebService.fileCountMeter() == 1
	}
	isFilesCountFiveChassis() {
		return this.outsideJobWebService.fileCountChassis() == 1
	}
	addRow(list?: JobCodeTableData) {
		console.log('list', list)
		const dataTable = this.jobCodeForm.get('jobCodeControl') as FormArray
		console.log('dataTable', dataTable)
		if (dataTable.controls) {
			console.log('dataTable.controls', dataTable.controls)
			this.presenter.addRowJobCode(list)
			this.jobCodeList = null;
			this.getJobCode()
		}
	}
	deleteRow() {
		this.presenter.deleteRowJobCode()
	}

	private getJobCode() {
		const dataTable = this.jobCodeForm.get('jobCodeControl') as FormArray
		dataTable.controls.forEach(value => {
			value.get('jobCode').valueChanges.subscribe(valueChange => {
				if (valueChange) {
					const jobCode =
						typeof valueChange == 'object' ? valueChange.code : valueChange
					this.jobCodeList = this.outsideJobWebService.jobCodeAutocomplete(jobCode)
				}
			})
		})
	}
	jobCodeSelection(event: MatAutocompleteSelectedEvent, index: number) {
		if (event && event['option']['value']) {
			const jobCode = event.option.value.jobcode
			const jobId = event.option.value.id
			const dataTable = this.jobCodeForm.get('jobCodeControl') as FormArray
			const selectedControlUuidValue = dataTable.value[index].uuid
			dataTable.controls.filter((value: FormGroup) => {
				this.uuid = value.get('uuid').value
				if (this.uuid === selectedControlUuidValue) {
					this.outsideJobWebService.getDataFromJobCardNumber(jobCode).subscribe(response => {
						value.get('description').patchValue(response[0].description)

					})
				}
			})
		}
	}
	displayJobCodeFn(jobcode: SearchAutocompleteJobCode) {
		return jobcode ? jobcode.jobcode : undefined
	}
	serviceCategoryCompare(c1: Category, c2: DropDownCategory): boolean {
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.category === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.category
		}
		return c1 && c2 ? c1.category === c2.category : c1 === c2
	}
	serviceCategoryDropDown() {
		this.outsideJobWebService.serviceCategory().subscribe(res => {
			this.serviceCategoryList = res
		})
	}
}
