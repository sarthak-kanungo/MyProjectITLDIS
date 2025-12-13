import { Injectable }from '@angular/core';
import { FormBuilder,FormControl, FormGroup, FormArray, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import * as uuid from 'uuid';

@Injectable()
export class PartIssuePageStore {
    private readonly _partIssuePageForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._partIssuePageForm = this.createPartIssuePageForm();
    }
    private createPartIssuePageForm() {
        return this.fb.group({
            partIssue: this.createPartIssueForm(),
            partIssueItem: this.fb.array([])
        });
    }
    private createPartIssueForm() {
        return this.fb.group({
            id: [null],
            issueType: [null, Validators.compose([Validators.required])],
            issueAgainst: [null, Validators.compose([Validators.required])],
            issueTo: [null, Validators.compose([Validators.required])],
            issueDate: [{ value: null, disabled: true }],
            partsIssuedBy: [{ value: null, disabled: true }],
            partIssueStatus: [{ value: null, disabled: true }],
            sparePartRequisition: [null],
            requisitionDate: [{ value: null, disabled: true }],
            jobCardNo: [{ value: null, disabled: false }],
            jobCardDate: [{ value: null, disabled: true }],
            partIssueNo: [{ value: null, disabled: true }]
        });
    }
    createItemDetailTableRow(data?){
       let fg = this.fb.group({
            uuid: [uuid.v4()],
            isSelected:[null],
            id: [data ? data.id : null],
            category:[{ value: data ? { id: data.category_id, category: data.category } : null, disabled: true }],
            sparePartMaster: [{ value: data ? { id: data.sparePartId, itemNo: data.itemNo } : null, disabled: true }],
            advancedSparePartIssue: [{ value: (data && data.apiId) ? { id: data.apiId} : null, disabled: true  }],
            binLocationMaster: [{ value: (data && data.binId) ? { id: data.binId } : null, disabled: true  }],
            storeMaster: [{ value: (data && data.storeId) ? { id: data.storeId } : null, disabled: true  }],
            itemNo: [{ value: data ? data.itemNo : null, disabled: true  }, [Validators.required]],
            itemDescription: [{ value: data ? data.itemDescription : null, disabled: true  }],
            uom: [{ value: data ? data.uom : null, disabled: true  }],
            availableStock: [{ value: data ? data.availableStock : null, disabled: true  }],
            reqQuantity: [{ value: data ? data.reqQuantity : null, disabled: true  }],
            balancedQuantity: [{ value: data ? data.balancedQuantity : null, disabled: true  }, [Validators.required, CustomValidators.numberOnly]],
            issuedQuantity: [{ value: data ? data.issuedQuantity : null, disabled: true  }, Validators.compose([CustomValidators.numberOnly,Validators.required,CustomValidators.min(new FormControl(1), "Min Qty required") ])],
            remark: [{ value: data ? data.remark : null, disabled: false  }],
            mrp: [{ value: data ? data.mrp : null, disabled: true  }],
            store: [{ value: data ? data.store : null, disabled: true  }],
            binLocation: [{ value: data ? data.binLocation : null, disabled: true  }],
            unitPrice:[null],
            spmgst:[null],
            spegst:[null],
            spmrp:[null]
        })
        
        return fg;
    }
    public get partIssuePageForm(): FormGroup {
        return this._partIssuePageForm;
    }
    public get partIssueForm(): FormGroup {
        return this._partIssuePageForm.get('partIssue') as FormGroup;
    }
    public get partIssueItemFormArray(): FormArray {
        return this._partIssuePageForm.get('partIssueItem') as FormArray;
    }
}