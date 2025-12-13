import { Component, OnInit, Input } from '@angular/core'
import { FormGroup, FormArray, Validators } from '@angular/forms'
import { TableConfig } from 'editable-table'
import { UploadableFile } from 'itldis-file-upload'
import { OutsideJobWebService } from './outside-job-web.service'
import { ActivatedRoute } from '@angular/router'
import { Operation } from '../../../../../utils/operation-util'
import { Category } from '../../domain/spares-sales-invoice.domain'
import { MatAutocompleteSelectedEvent } from '@angular/material'
import { Observable, BehaviorSubject } from 'rxjs'
import { SparesSalesInvoiceCreatePresenter } from '../spares-sales-invoice-create-page/spares-sales-invoice-create-page.presenter';

@Component({
	selector: 'app-outside-job-charges',
	templateUrl: './outside-job-charges.component.html',
	styleUrls: ['./outside-job-charges.component.scss'],
	providers: [OutsideJobWebService],
})
export class OutsideJobChargesComponent implements OnInit {

	outsideJobChargeForm: FormGroup
	@Input() isView: boolean
	@Input() isEdit: boolean
	chargeDetailsArray : FormGroup[]
	isSalesOrder:boolean = true;
	@Input() public draftFlag:boolean;
	constructor(
	    private sparesSalesInvoiceCreatePresenter: SparesSalesInvoiceCreatePresenter,
		private outsideJobWebService: OutsideJobWebService
	) { }

	ngOnInit() {
		this.outsideJobChargeForm = this.sparesSalesInvoiceCreatePresenter.getOutsideChargeForm;
		this.sparesSalesInvoiceCreatePresenter.outsideChargeDetailsGroup.subscribe( response => {
		    this.chargeDetailsArray = response as FormGroup[];
		})

	    this.sparesSalesInvoiceCreatePresenter.refernceDocChange.subscribe(docType => {
	       if(docType === 'Job Card'){
	           this.isSalesOrder = false; 
	       }else{
	           this.isSalesOrder = true;
	       } 
	    });
	}
	
	serviceCategoryCompare(c1: Category, c2: Category): boolean {
		if (typeof c1 !== typeof c2) {
			if (typeof c1 === 'object' && typeof c2 === 'string')
				return c1.category === c2
			if (typeof c2 === 'object' && typeof c1 === 'string')
				return c1 === c2.category
		}
		return c1 && c2 ? c1.category === c2.category : c1 === c2
	}
	
}
