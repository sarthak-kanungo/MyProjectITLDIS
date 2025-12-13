import { Component, OnInit, Input } from '@angular/core'
import { FormGroup, FormArray } from '@angular/forms'
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter'
import { LabourChargesWebService } from './labour-charges-web.service'
import { Operation } from '../../../../../utils/operation-util'
import {
	LabourChargesItem,
	FrsCodeAutoCompleteList,
} from '../../domain/labour-charges-domain'
import { Observable } from 'rxjs'
import { MatAutocompleteSelectedEvent } from '@angular/material'
import { ServiceCategoryDropDownList, Category, DropDownCategory } from '../../domain/job-card-domain'

@Component({
	selector: 'app-labour-charges',
	templateUrl: './labour-charges.component.html',
	styleUrls: ['./labour-charges.component.scss'],
	providers: [LabourChargesWebService],
})
export class LabourChargesComponent implements OnInit {
	@Input() labourChargesForm: FormGroup
	isView: boolean
	isEdit: boolean
	isCreate: boolean
	frsCodeList
	uuid: any
	ishideForDraftFlag: boolean
	serviceCategoryList: ServiceCategoryDropDownList
	constructor(
		private presenter: JobCardPresenter,
		private labourChargesWebService: LabourChargesWebService,
	) { }
	ngOnInit() {
		this.ViewOrEditOrCreate()
		this.serviceCategoryDropDown()
		console.log('this.presenter.labourCharges',this.presenter.labourCharges)
	}

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
	addRow(list?: LabourChargesItem) {
		const dataTable = this.labourChargesForm.get('labourChargesControl') as FormArray
		if (dataTable.controls) {
			this.presenter.addRowFrs(list)
			this.frsCodeList = null;
			this.getFrsNo()
		}
	}
	deleteRow() {
		this.presenter.deleteRowFrs()
	}

	// getFrsNo() {
	// 	const dataTable = this.labourChargesForm.get('labourChargesControl') as FormArray
	// 	dataTable.controls.forEach(value => {
	// 		value.get('frsCode').valueChanges.subscribe(valueChange => {
	// 			if (valueChange) {
	// 				const frsCode = typeof valueChange == 'object' ? valueChange.code : valueChange
	// 				this.frsCodeList = this.labourChargesWebService.frsAutocomplete(frsCode, this.presenter.jobCardBasicInfo.get('modelId').value)
	// 			}
	// 		})
	// 	})
	// }
	getFrsNo() {
		const dataTable = this.labourChargesForm.get('labourChargesControl') as FormArray

		dataTable.controls.forEach(value => {

			value.get('frsCode').valueChanges.subscribe(valueChange => {
				if (valueChange) {
					const frsCode = typeof valueChange == 'object' ? valueChange.code : valueChange
					this.labourChargesWebService.frsAutocomplete(frsCode, this.presenter.jobCardBasicInfo.get('modelId').value).subscribe(res => {
						const autoList = res

						let category
						dataTable.controls.forEach(value => {
							category = value.get('category').value.category
						})

						const formValue = (this.labourChargesForm.get('labourChargesControl') as FormArray).getRawValue();

						formValue.forEach(element => {

							if (element) {
								if (
									category === element.category.category &&
									element.category.category == 'Customer Paid' &&
									autoList.includes(
										autoList.find(res => res.frsCode === element.frsCode.frsCode),
									)
								) {
									autoList.splice(autoList.findIndex(res => res.frsCode == element.frsCode.frsCode), 1)
								}
							}
						})
						this.frsCodeList = autoList

					})
				}
			})
		})
	}




	frsCodeSelection(event: MatAutocompleteSelectedEvent, index: number) {

		if (event && event['option']['value']) {
			const frsCode = event.option.value.frsCode
			const frsId = event.option.value.id
			const dataTable = this.labourChargesForm.get('labourChargesControl') as FormArray
			const selectedControlUuidValue = dataTable.value[index].uuid
			dataTable.controls.filter((value: FormGroup) => {
				this.uuid = value.get('uuid').value
				if (this.uuid === selectedControlUuidValue) {
					this.labourChargesWebService
						.getDataFromFrsNumber(frsCode, this.presenter.jobCardBasicInfo.get('modelId').value)
						.subscribe(response => {

							value.get('description').patchValue(response.description)
							value.get('hour').patchValue(response.time)
							value.get('amount').patchValue(response.amount);
						})
				}
			})
		}
	}
	onKeyDownFrsCode(event: KeyboardEvent, index: number) {
		if (event) {
			const dataTable = this.labourChargesForm.get('labourChargesControl') as FormArray
			const selectedControlUuidValue = dataTable.value[index].uuid
			dataTable.controls.filter((value: FormGroup) => {
				this.uuid = value.get('uuid').value
				if (this.uuid === selectedControlUuidValue) {
					value.get('description').reset()
					value.get('hour').reset()
				}
			})
		}
	}
	displayLabourCodeFn(frsCode: FrsCodeAutoCompleteList) {
		return frsCode ? frsCode.frsCode : undefined
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

		this.labourChargesWebService.serviceCategory().subscribe(res => {

			this.serviceCategoryList = res
		})
	}
	
	makeAmountEd(event, fg:FormGroup){
	    
	    if (event && event['value']) {
	        if(event['value']['category'] == 'Warranty'){
	            fg.controls.amount.disable();
	        }else{
	            fg.controls.amount.enable();
	        }
	    }else{
	        fg.controls.amount.enable();
	    }
	    fg.controls.amount.reset();
	}
}
