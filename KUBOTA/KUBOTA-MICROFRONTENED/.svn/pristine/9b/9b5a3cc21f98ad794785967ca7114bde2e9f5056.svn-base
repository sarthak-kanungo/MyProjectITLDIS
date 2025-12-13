import { Component, OnInit } from '@angular/core'
import { FormGroup, AbstractControl, Validators,FormArray } from '@angular/forms'
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

@Component({
	selector: 'app-job-card-create',
	templateUrl: './create-job-card-page.component.html',
	styleUrls: ['./create-job-card-page.component.scss'],
	providers: [JobCardPresenter, CreateJobCardStore, JobCardPageWebService, DateService, JobCardDetailWebService, IFrameService],
})
export class JobCardCreateComponent implements OnInit {
	mechanicId: number
	jobcardId:number
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
	machineIdPage: number
	serviceTypeDropdownRes: ServicetypesList
	serviceCategoryListRes: DropDownCategory
	isShowPcr: boolean
	fileFromView
	fileFromEdit
	isPreInvoiceInvoice: boolean
	isPrintPreInvoice: boolean
	isPreInvoice: boolean
	isCloseJC: boolean
	isPresales: boolean
	jobCardNumberId: string
	isUpdatePcr: boolean
	isShowPcrButton: boolean
	isTablesShow: boolean = false
	pcrNo: string
	jobCodeNumberInvoice: string
	approvalHierDetails:any[]
	isSubmitDisable:boolean = false;
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
			if (param.has('id')) {
				if (this.isEdit) {
					this.getJobCardForView(atob(param.get('id')))
					if(this.presenter.operation === Operation.APPROVAL){
					    this.presenter.disableForView()
					     const parts =  this.partRequisitionForm.get('partRequisitionControl') as FormArray;
					     if(parts && parts.controls){
					         parts.controls.forEach(part => {
					             part.get('approveQuantity').enable();
					         })
					     }
					     const labours =  this.labourChargesForm.get('labourChargesControl') as FormArray;
                         if(labours && labours.controls){
                             labours.controls.forEach(labour => {
                                 labour.get('approveAmount').enable();
                             })
                         }
                         const outsidecharges =  this.jobCodeForm.get('jobCodeControl') as FormArray;
                         if(outsidecharges && outsidecharges.controls){
                             outsidecharges.controls.forEach(outsidecharge => {
                                 outsidecharges.get('approveAmount').enable();
                             })
                         }
					}
				}
				if (this.isView) {
					this.getJobCardForView(atob(param.get('id')))
					this.presenter.disableForView()
				}
			}
		})
	}
	private viewOrEditOrCreate() {
		if (this.presenter.operation === Operation.VIEW) {
			this.isView = true
			this.isEdit = false
		} else if (this.presenter.operation === Operation.APPROVAL) {
			this.isEdit = true
			this.isView = false
		}
	}
	private markFormAsTouchedWithoutPreSales() {
		this.jobCardForm.markAllAsTouched()
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

			if (res) {
			    this.jobcardId=res.id;
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
				this.presenter.patchDataFromView(res)
				this.sendListOfView = res
				this.idFromView = res.id;
				this.pcrNo = res.pcrNo;

				if (this.isView) {
					this.fileFromView = res.serviceJobcardPhotos
				}
				if (this.isEdit) {
					this.fileFromEdit = res.serviceJobcardPhotos
				}
				this.jobCardDetailWebService.servicetypesListDropDown(res.machineInventory.vinId, res.totalHour, res.serviceCategory.category).subscribe(res => {

					this.serviceTypeDropdownRes = res
				})
				if (res.customerMaster) {
					this.presenter.jobCardBasicInfo.get('customerMasterId').patchValue(res.customerMaster.id)
				}
				if (res.mechanic) {
					this.mechanicId = res.mechanic.id
				} if (res.machineInventory) {
					this.machineIdPage = res.machineInventory.vinId
					this.idForPreSales = res.machineInventory.vinId
				}

				if (res.serviceRepresentative) {
					this.serviceRepresentativeId = res.serviceRepresentative.id
				}
				if (res.serviceBooking) {
					this.serviceBookingId = res.serviceBooking.id
				}
				this.jobCardPageWebService.getJobCardApprovalDetails(res.id+"").subscribe(res => {
				    this.approvalHierDetails = res['result'];
				})
			}
		})
	}
	private dropDownServiceCategory() {
		this.jobCardPageWebService.serviceCategory('true').subscribe(res => {
			this.serviceCategoryListRes = res
		})
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
					hour:el.hour,
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
				hour:element.hour,
				amount: parseInt(element.amount),
				labourChargeId: element.labourChargeId,
				category: element.category,
				isSelected: element.isSelected,

			})
			delete element.uuid
		})
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
		sparePartsDetailsSend = [...allPartsTogether, ...deletedParts]

		return sparePartsDetailsSend ? sparePartsDetailsSend : null
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
	
	private openConfirmDialogCancel(approvalStatus): void | any {      
	      let message = `Are you sure you want to ${approvalStatus} Job Card?`
	      const confirmationData = this.setConfirmationModalDataCancel(message);
	      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
	        width: '500px',
	        panelClass: 'confirmation_modal',
	        data: confirmationData
	      });
	      dialogRef.afterClosed().subscribe((result:DialogResult) => {
	         if (result.button === 'Confirm') {
				 this.isSubmitDisable = true;
	             this.jobCardPageWebService.jobCardReopenApproval(this.jobcardId+"",result.remarkText,approvalStatus).subscribe(
	                     res => {
							 if(res){
								const displayMessage = res['message']
								this.toastr.success(displayMessage, 'Success')
								this.router.navigate(['../'], { relativeTo: this.activateRoute })
							 } else {
								 this.isSubmitDisable = false;
								 this.toastr.error('Somthing goes wrong.', 'Transaction failed')
							 }
	                     },
	                     err => {
							this.isSubmitDisable = false;
	                        this.toastr.error('Somthing goes wrong.', 'Transaction failed')
	                     },
	                 )
	         }
	      })      
	}
	 
	jobcardApprovalClick(){
	    this.openConfirmDialogCancel("Approve");
	}
	
	jobcardRejectClick(){
        this.openConfirmDialogCancel("Reject");
    }
}
