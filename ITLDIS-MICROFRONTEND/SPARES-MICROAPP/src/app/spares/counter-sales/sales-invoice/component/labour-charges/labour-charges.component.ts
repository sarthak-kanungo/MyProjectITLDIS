import { Component, OnInit, Input } from '@angular/core'
import { FormGroup, FormArray } from '@angular/forms'
import { LabourChargesWebService } from './labour-charges-web.service'
import { Operation } from '../../../../../utils/operation-util'
import { Category} from '../../domain/spares-sales-invoice.domain'
import { Observable } from 'rxjs'
import { MatAutocompleteSelectedEvent } from '@angular/material'
import { SparesSalesInvoiceCreatePresenter } from '../spares-sales-invoice-create-page/spares-sales-invoice-create-page.presenter';

@Component({
	selector: 'app-labour-charges',
	templateUrl: './labour-charges.component.html',
	styleUrls: ['./labour-charges.component.scss'],
	providers: [LabourChargesWebService],
})
export class LabourChargesComponent implements OnInit {
	labourChargesForm: FormGroup
	@Input() isView: boolean
	@Input() isEdit: boolean
	@Input() isCreate: boolean
	@Input() public draftFlag:boolean;
	uuid: any
	labourDetailsArray:FormGroup[]
	isSalesOrder:boolean=true;
	constructor(
		private sparesSalesInvoiceCreatePresenter: SparesSalesInvoiceCreatePresenter,
		private labourChargesWebService: LabourChargesWebService,
	) { }
	
	ngOnInit() {
		this.labourChargesForm = this.sparesSalesInvoiceCreatePresenter.getLabourChargeForm;
        this.sparesSalesInvoiceCreatePresenter.labourDetailsGroup.subscribe( response => {
            this.labourDetailsArray = response as FormGroup[];
        });
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
