import { Component, OnInit, Input, AfterViewChecked } from '@angular/core'
import { FormGroup, FormArray, Validators } from '@angular/forms'
import { TableConfig } from 'editable-table'
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter'
import { UploadableFile } from 'kubota-file-upload'
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
import { ToastrService } from 'ngx-toastr'
@Component({
	selector: 'app-outside-job-charges',
	templateUrl: './outside-job-charges.component.html',
	styleUrls: ['./outside-job-charges.component.scss'],
	providers: [OutsideJobWebService, FileUploadService, JobCardPageWebService],
})
export class OutsideJobChargesComponent implements OnInit, AfterViewChecked {

	@Input() jobCodeForm: FormGroup
	uuid: string
	status: string
	isCreate: boolean
	ishideForDraftFlag: boolean
	@Input() isFreeService: boolean
	@Input() isTablesShow: boolean
	@Input() patchRetroCheckbox
	@Input() forRetrofirMent
	@Input() public set fileFromView(fileFromView) {
		if (fileFromView) {
			fileFromView.forEach(res => {
				// console.log(res,'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@')
				if (res.fileType == 'free service coupon 1') {
					this.selectedPhotosOne.push(res)
					this.presenter.outsideJobCharge.get('couponOne').clearValidators()
					this.presenter.outsideJobCharge.get('couponOne').updateValueAndValidity()
					// console.log('this.presenter.outsideJobCharge.get(\'couponOne\')', this.presenter.outsideJobCharge.get('couponOne'))
				}
				if (res.fileType == 'free service coupon 2') {
					this.selectedPhotosTwo.push(res)
					this.presenter.outsideJobCharge.get('couponTwo').clearValidators()
					this.presenter.outsideJobCharge.get('couponTwo').updateValueAndValidity()
					// console.log('this.presenter.outsideJobCharge.get(\'couponTwo\')', this.presenter.outsideJobCharge.get('couponTwo'))
				}
				if (res.fileType == 'hour meter ') {
					// console.log('res545646565465465465464', res)
					this.selectedPhotosCoupon.push(res)
					this.presenter.outsideJobCharge.get('hourMeter').clearValidators()
					this.presenter.outsideJobCharge.get('hourMeter').updateValueAndValidity()
				}
				if (res.fileType == 'chassis') {
					this.selectedPhotosChassis.push(res)
					this.presenter.outsideJobCharge.get('chassisNumber').clearValidators()
					this.presenter.outsideJobCharge.get('chassisNumber').updateValueAndValidity()
				}
				if (res.fileType == 'Signed Job Card') {
					this.selectedJobPdf.push(res)
					this.presenter.outsideJobCharge.get('signedJobcard').clearValidators()
					this.presenter.outsideJobCharge.get('signedJobcard').updateValueAndValidity()
				}
				if (res.fileType == 'Retrofitment Acknowledgement Form') {
					this.selectedAckPdf.push(res)
					this.presenter.outsideJobCharge.get('retroAcknowledgementForm').clearValidators()
					this.presenter.outsideJobCharge.get('retroAcknowledgementForm').updateValueAndValidity()
				}
			})
		}
	}
	@Input() public set fileFromEdit(fileFromEdit) {
		this.presenter.outsideJobCharge.get('hourMeter').enable()
		this.presenter.outsideJobCharge.get('chassisNumber').enable()
		this.presenter.outsideJobCharge.get('signedJobcard').enable()
		this.presenter.outsideJobCharge.get('retroAcknowledgementForm').enable()
		this.presenter.outsideJobCharge.get('couponOne').enable()
		this.presenter.outsideJobCharge.get('couponTwo').enable()
		if (fileFromEdit) {
			fileFromEdit.forEach(res => {
				if (res.fileType === 'free service coupon 1') {
					// console.log('res', res)
					this.presenter.outsideJobCharge.get('couponOne').enable()
					this.files.push(res)
					this.presenter.outsideJobCharge.get('couponOne').clearValidators()
					this.presenter.outsideJobCharge.get('couponOne').updateValueAndValidity()
					this.presenter.outsideJobCharge.get('couponOne').patchValue(res.fileName)
					// console.log(' couponOne ', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'free service coupon 2') {
					this.presenter.outsideJobCharge.get('couponTwo').enable()
					this.serviceCouponList.push(res)
					this.presenter.outsideJobCharge.get('couponTwo').clearValidators()
					this.presenter.outsideJobCharge.get('couponTwo').setErrors(null)
					this.presenter.outsideJobCharge.get('couponTwo').updateValueAndValidity()
					this.presenter.outsideJobCharge.get('couponTwo').patchValue(res.fileName)
					// console.log(' (\'couponTwo\')', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'hour meter ') {
					this.presenter.outsideJobCharge.get('hourMeter').enable()
					this.hourMeterList.push(res)
					this.presenter.outsideJobCharge.get('hourMeter').clearValidators()
					this.presenter.outsideJobCharge.get('hourMeter').updateValueAndValidity()
					this.presenter.outsideJobCharge.get('hourMeter').patchValue(res.fileName)
					// console.log(' (\'hourMeter\')', this.presenter.outsideJobCharge)
				}
				if (res.fileType === 'chassis') {
					this.presenter.outsideJobCharge.get('chassisNumber').enable()
					this.chassisNumberList.push(res)
					this.presenter.outsideJobCharge.get('chassisNumber').clearValidators()
					this.presenter.outsideJobCharge.get('chassisNumber').updateValueAndValidity()
					this.presenter.outsideJobCharge.get('chassisNumber').patchValue(res.fileName)
					// console.log(' (\'chassis number\')', this.presenter.outsideJobCharge)


				}
				if (res.fileType == 'Signed Job Card') {
					this.presenter.outsideJobCharge.get('signedJobcard').enable()
					this.jobCardList.push(res)
					this.presenter.outsideJobCharge.get('signedJobcard').clearValidators()
					this.presenter.outsideJobCharge.get('signedJobcard').updateValueAndValidity()
				}
				if (res.fileType == 'Retrofitment Acknowledgement Form') {
					this.presenter.outsideJobCharge.get('signedJobcard').enable()
					this.ackList.push(res)
					this.presenter.outsideJobCharge.get('retroAcknowledgementForm').clearValidators()
					this.presenter.outsideJobCharge.get('retroAcknowledgementForm').updateValueAndValidity()
				}
			})
		}
	}
	selectedPhotosOne = []
	selectedPhotosTwo = []
	selectedPhotosCoupon = []
	selectedPhotosChassis = []
	selectedJobPdf=[]
	selectedAckPdf=[]
	outsideJobChargeForm: FormGroup
	fileStaticPath: string = JobCardUrl.staticPath
	jobCodeList: Observable<Array<SearchAutocompleteJobCode>>
	files: Array<UploadableFile> = new Array()
	serviceCouponList: Array<UploadableFile> = new Array()
	hourMeterList: Array<UploadableFile> = new Array()
	chassisNumberList: Array<UploadableFile> = new Array()
	jobCardList: Array<UploadableFile> = new Array()
	ackList: Array<UploadableFile>=new Array()
	tableConfig: TableConfig[]
	isView: boolean
	isEdit: boolean
	isKaiUser: boolean = true
	serviceCategoryList: ServiceCategoryDropDownList[]
	showPdfs: boolean = false
	constructor(
		private presenter: JobCardPresenter,
		private outsideJobWebService: OutsideJobWebService,
		private activateRoute: ActivatedRoute,
		private fileUploadService: FileUploadService,
		private toastr: ToastrService
	) { }
	ngOnChanges() {
		const data = this.forRetrofirMent
		// console.log(data,'data')
		if (data === true) {
			this.showPdfs = true
		} else {
			this.showPdfs = false
		}
	}
	ngOnInit() {
		this.outsideJobChargeForm = this.presenter.outsideJobCharge
		this.ViewOrEditOrCreate()
		this.freeServiceCondition()
		this.serviceCategoryDropDown()
		if (this.presenter.loginUser.dealerCode) {
			this.isKaiUser = false;
		}
	}
	ngAfterViewChecked() {
		if (this.presenter.operation === Operation.CREATE) {
			this.status = 'Open'
		} else {
			this.status = this.presenter.jobCardBasicInfo.get('status').value
		}
	}
	freeServiceCondition() {
		this.presenter.jobCardBasicInfo.get('serviceCategory').valueChanges.subscribe(res => {
			if (res && res.category == "Maintenance Service") {
				this.isFreeService = true;
			} else {
				this.isFreeService = false;
			}
		});
	}
	// freeServiceCondition() {
	// 	this.presenter.jobCardBasicInfo.get('serviceCategory').valueChanges.subscribe(res => {
	// 		console.log('servieCategory11111111111111111111111111', res)
	// 		if (res && res.category == "Free Service") {
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

	// 			this.presenter.outsideJobCharge.get('hourMeter').clearValidators()
	// 			this.presenter.outsideJobCharge.get('hourMeter').updateValueAndValidity()
	// 			this.presenter.outsideJobCharge.get('chassisNumber').clearValidators()
	// 			this.presenter.outsideJobCharge.get('chassisNumber').updateValueAndValidity()

	// 			// this.presenter.outsideJobCharge.get('hourMeter').setValidators(Validators.required)
	// 			// this.presenter.outsideJobCharge.get('chassisNumber').setValidators(Validators.required)
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
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0], "JC_couponOne")
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('couponOne').reset()
			return false;
		}
		if (!this.isFilesCountFive()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'couponOne')
			this.files = this.outsideJobWebService.listUploadableFiles()
			this.presenter.couponOnePresenter(this.files)
		}
	}
	fileSelectionServiceCoupon2Changes(fileInput: Event) {
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0], "JC_couponTwo")
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('couponTwo').reset()
			return false;
		}
		if (!this.isFilesCountFiveCoupon()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'couponTwo')
			this.serviceCouponList = this.outsideJobWebService.listUploadableFilesServiceCouponList()
			this.presenter.couponTwoPresenter(this.serviceCouponList)
		}
	}
	fileSelectionHourMeterChanges(fileInput: Event) {
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0], "JC_hourMeter")
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('hourMeter').reset()
			return false;
		}
		if (!this.isFilesCountFiveMeter()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'hourMeter')
			this.hourMeterList = this.outsideJobWebService.listUploadableFilesHourMeterList()
			this.presenter.couponThreePresenter(this.hourMeterList)
		}
	}
	fileSelectionChasisNumberChanges(fileInput: Event) {
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0], "JC_chassisNumber")
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('chassisNumber').reset()
			return false;
		}
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
		// console.log('list', list)
		const dataTable = this.jobCodeForm.get('jobCodeControl') as FormArray
		// console.log('dataTable', dataTable)
		if (dataTable.controls) {
			// console.log('dataTable.controls', dataTable.controls)
			const fg: FormGroup = this.presenter.addRowJobCode(list);
			this.jobCodeList = null;
			this.getJobCode()
			if (fg && this.presenter.isGoowillShow) {
				fg.get('category').valueChanges.subscribe(val => {
					if (val.category === 'Warranty' || val.category === 'FOC') {
						this.toastr.warning("Category can not be Warranty or FOC as Job Card is under Goodwill", "Warning");
						fg.get('category').reset();
					}
				})
				/*this.serviceCategoryList = this.serviceCategoryList.filter(obj => {
					obj.category!='Warranty' && obj.category!='FOC'
				});*/
			}
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
			console.log(event)
			const jobCode = event.option.value.jobCodeNo
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
		return jobcode ? jobcode.jobCodeNo : undefined
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
	fileSelectionSign(fileInput: Event) {
		
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0])
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('signedJobcard').reset()
			return false;
		}
		if (!this.isFilesCountFive()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'signedJobcard')
			this.jobCardList = this.outsideJobWebService.listUploadableJobPdf()
			this.presenter.jobCardfile(this.jobCardList)
		}
	}
	deleteImageJobCard(id: string) {
		if (this.presenter.outsideJobCharge.get('signedJobcard').value) {
			this.presenter.outsideJobCharge.get('signedJobcard').reset()
		}
		this.jobCardList = this.outsideJobWebService.listUploadableJobPdf()
		this.outsideJobWebService.deleteFileJobcard(id)
	}
	fileSelectRetroAckForm(fileInput: Event) {
		const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0])
		if (msg != 'OK') {
			this.toastr.warning(msg);
			this.presenter.outsideJobCharge.get('retroAcknowledgementForm').reset()
			return false;
		}
		if (!this.isFilesCountFive()) {
			this.outsideJobWebService.onFileSelect(fileInput, 'retroAcknowledgementForm')
			this.ackList = this.outsideJobWebService.listuploadAckForm()
			this.presenter.AckFormfile(this.ackList)
		}
	}
	deleteImageAckForm(id: string) {
		if (this.presenter.outsideJobCharge.get('retroAcknowledgementForm').value) {
			this.presenter.outsideJobCharge.get('retroAcknowledgementForm').reset()
		}
		this.ackList = this.outsideJobWebService.listuploadAckForm()
		this.outsideJobWebService.deleteFileAckform(id)
	}


}
