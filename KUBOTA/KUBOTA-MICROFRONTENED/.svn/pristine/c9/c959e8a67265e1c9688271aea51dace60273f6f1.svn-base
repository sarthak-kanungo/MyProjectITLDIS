import { Component, OnInit, Input } from '@angular/core'
import { FormGroup, FormArray } from '@angular/forms'
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter'
import { PartRequisitionWebService } from './part-requisition-web.service'
import {
	DropDownCategory,
	SparePartRequisitionItem,
	Category,
	SearchAutocomplete,
} from '../../domain/job-card-domain'
import { Operation } from '../../../../../utils/operation-util'
import { ToastrService } from 'ngx-toastr'
import { MatAutocompleteSelectedEvent } from '@angular/material'

@Component({
	selector: 'app-part-requisition',
	templateUrl: './part-requisition.component.html',
	styleUrls: ['./part-requisition.component.scss'],
	providers: [PartRequisitionWebService],
})
export class PartRequisitionComponent implements OnInit {
	uuid: string
	@Input() partRequisitionForm: FormGroup
	serviceCategoryList: DropDownCategory
	isView: boolean
	isEdit: boolean
	isCreate: boolean
	partNumberList
	qtyFocusInValue: any
	// ishideForDraftFlag: boolean
	@Input() public set serviceCategoryListRes(serviceCategoryListRes) {
		this.serviceCategoryList = serviceCategoryListRes
	}
	constructor(
		private presenter: JobCardPresenter,
		private partRequisitionWebService: PartRequisitionWebService,
		private toastr: ToastrService,
	) { }
	ngOnInit() {
		this.ViewOrEditOrCreate()
		this.serviceCategoryDropDown()
		this.getPartNo()
		this.getPreSalesValueChange()
	}
	getPreSalesValueChange() {
		this.presenter.jobCardBasicInfo.get('checkBox').valueChanges.subscribe(res => {
			this.partRequisitionWebService.serviceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value).subscribe(res => {
				this.serviceCategoryList = res
			})
		})
	}

	qtyFocusOut(ev: FocusEvent, controls: FormGroup) {
		if (controls.get('qtyChangeFlag').value == true) {

			if (this.presenter.jobCardBasicInfo.get('pcrApproved').value == true || this.presenter.jobCardBasicInfo.get('partIssued').value == true) {

				if (controls.get('cmpQty').value > ev.target['value']) {
					controls.get('quantity').patchValue(controls.get('cmpQty').value)
					this.toastr.error("Part Issued Done And Pcr Approved Quantity Cannot Be Reduced")
				}
			}
		}

	}

	private ViewOrEditOrCreate() {
		if (this.presenter.operation === Operation.VIEW) {
			this.isView = true
			this.isEdit = false
			this.presenter.jobCardBasicInfo
				.get('draftFlag')
				.valueChanges.subscribe(res => {
					// this.setValueFromView()
				})
		} else if (this.presenter.operation === Operation.EDIT) {
			this.isEdit = true
			this.presenter.jobCardBasicInfo
				.get('draftFlag')
				.valueChanges.subscribe(res => {
					// this.setValueFromView()
				})
		} else if (this.presenter.operation === Operation.CREATE) {
			this.isCreate = true
			// this.addRow()
		}
	}
	// setValueFromView() {
	// 	if (this.presenter.jobCardBasicInfo.get('draftFlag').value == false) {
	// 		this.ishideForDraftFlag = true
	// 	}
	// }
	addRow(list?: SparePartRequisitionItem) {
		const dataTable = this.partRequisitionForm.get('partRequisitionControl') as FormArray
		if (dataTable.controls) {
			this.presenter.addRow(list)
			this.partNumberList = null;
			this.getPartNo()
		}
	}
	deleteRow() {
		this.presenter.deleteRow()
	}
	serviceCategoryDropDown() {
		this.partRequisitionWebService.serviceCategory(this.presenter.jobCardBasicInfo.get('checkBox').value).subscribe(res => {
			this.serviceCategoryList = res
		})
	}
	selectionChangedCategory(event, fg:FormGroup) {
		if (event && event['value']) {			
			fg.get('partNumber').reset()
			fg.get('itemDescription').reset()
			fg.get('quantity').reset()
			fg.get('utilizedQuantity').reset()
			fg.get('mrp').reset()
			fg.get('amount').reset()
			fg.get('approveQuantity').reset()
			fg.get('approveStatus').reset()				
		}
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

	getPartNo() {
		const dataTable = this.partRequisitionForm.get('partRequisitionControl') as FormArray
		dataTable.controls.forEach(value => {
			value.get('partNumber').valueChanges.subscribe(valueChange => {
				if (valueChange) {
					const partNumber = typeof valueChange == 'object' ? valueChange.code : valueChange
					this.partRequisitionWebService.partNumberAutocomplete(partNumber).subscribe(res => {
						const autoList = res
						let category
						dataTable.controls.forEach(value => {
							category = value.get('category').value.category
						})
						const formValue = (this.partRequisitionForm.get('partRequisitionControl') as FormArray).getRawValue();
						formValue.forEach(element => {
							if (element) {
								if (
									category === element.category.category &&
									element.category.category == 'Warranty' &&
									autoList.includes(
										autoList.find(res => res.value === element.partNumber.value),
									)
								) {
									autoList.splice(autoList.findIndex(res => res.value == element.partNumber.value), 1)
								}
								if (
									category === element.category.category &&
									element.category.category == 'Customer Paid' &&
									autoList.includes(autoList.find(res => res.value === element.partNumber.value))
								) {
									autoList.splice(autoList.findIndex(res => res.value == element.partNumber.value), 1)
								}
								if (
									category === element.category.category &&
									element.category.category == 'FOC' &&
									autoList.includes(autoList.find(res => res.value === element.partNumber.value))) {
									autoList.splice(autoList.findIndex(res => res.value == element.partNumber.value), 1)
								}
							}
						})
						this.partNumberList = autoList
					})
				}
			})
		})
	}

	partNoSelection(event: MatAutocompleteSelectedEvent, fg:FormGroup) {
		if (event && event['option']['value']) {
			const partNumber = event.option.value.value
			this.partRequisitionWebService.getDataFromPartNumber(partNumber).subscribe(response => {
			    fg.get('category').disable();
			    fg.get('partNumber').disable();
				fg.get('itemDescription').patchValue(response['itemDescription'])
				fg.get('mrp').patchValue(response['mrp'])
			})
		}
		this.presenter.partNoChange(event)
	}
	onKeyDownPartNo(event: KeyboardEvent, fg:FormGroup) {
		if (event) {		
			fg.get('itemDescription').reset()
			fg.get('mrp').reset()
		}
	}

	displayFnPartNumber(chassisNumber: SearchAutocomplete) {
		return chassisNumber ? chassisNumber.value : undefined
	}
}
