import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { PartData } from "../domain/domaim";
@Injectable()
export class indentStore {
    private readonly branchTransferIndentForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.branchTransferIndentForm = this.fb.group({
            formIndent: this.createIndentForm(),
            itemDetails: this.fb.array([]),
        });
    }



    private createIndentForm() {
        const formIndent = this.fb.group({
            reqFromBranch: [{ value: '', disabled: true }],
            requestNumber: [{ value: '', disabled: true }],
            reqBy: [{ value: '', disabled: true }],
            reqToBranch: [{ value: '', disabled: false }, Validators.required],
            requestDate: [{ value: '', disabled: true }],
            remarks: [{ value: '', disabled: false }],
            partReceivedById: [{}],
            reqById: [{}],
            id: [{ value: null, disabled: true }],
            status: [{ value: null, disabled: true }]
        });


        return formIndent;
    }
    private createItemDetailsForm(partDetail?: PartData) {
        const indentItemForm = this.fb.group({
            sparePartMaster: [{ value: partDetail == undefined ? null : partDetail.sparePartMaster, disabled: false }, Validators.compose([Validators.required,CustomValidators.selectFromLists()])],
            itemDescription: [{ value: partDetail == undefined ? null : partDetail.itemDescription, disabled: true }],
            reqBranchStockQty: [{ value: partDetail == undefined ? null : partDetail.reqBranchStockQty, disabled: true }],
            supBranchStockQty: [{ value: partDetail == undefined ? null : partDetail.supBranchStockQty, disabled: true }],
            reqQty: [{ value: partDetail == undefined ? null : partDetail.reqQty, disabled: false }, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            isSelected: [{ value: '', disabled: false }],
            id: [{ value: partDetail == undefined ? null : partDetail.id, disabled: false }]
        });
              

        if(partDetail){
        
            if(partDetail.sparePartMaster==null){
                
                 indentItemForm.controls.sparePartMaster.patchValue({'itemNo':partDetail.itemNo,'id':partDetail.itemId})
            }
        }
        return indentItemForm;
    }


    get indentFormAll() {
        if (this.branchTransferIndentForm) {
            return this.branchTransferIndentForm as FormGroup;
        }
    }


    initializePartDetailForm(partDetail?: PartData) {
        return this.createItemDetailsForm(partDetail);
    }
}